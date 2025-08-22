package com.coedotzmagic.levelupdater.utils.downloader;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class DownloadFile extends JFrame {
    private volatile boolean cancelled = false;
    private final BackgroundProcess backgroundProcess = new BackgroundProcess();

    public DownloadFile(String url, String originalPath) {
        String tempPath = originalPath + ".cdtzmgcdownload";

        // Progress Dialog UI
        JDialog progressDialog = new JDialog(this, "Downloading...", true);
        progressDialog.setLayout(new BorderLayout());

        JProgressBar progressBar = new JProgressBar(0, 100);
        progressBar.setStringPainted(true);

        JLabel progressLabel = new JLabel("Starting download...");

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> {
            cancelled = true;
            progressDialog.dispose();
        });

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.add(cancelButton);

        progressDialog.add(progressLabel, BorderLayout.NORTH);
        progressDialog.add(progressBar, BorderLayout.CENTER);
        progressDialog.add(bottomPanel, BorderLayout.SOUTH);
        progressDialog.setSize(400, 120);
        progressDialog.setLocationRelativeTo(this);
        progressDialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);

        new Thread(() -> {
            String message;
            boolean success = false;

            try {
                success = backgroundProcess.downloadFileFromURL(url, tempPath, (downloaded, total) -> {
                    if (cancelled) try {
                        throw new InterruptedException("Download cancelled.");
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                    int percent = total > 0 ? (int)((downloaded * 100) / total) : 0;
                    SwingUtilities.invokeLater(() -> {
                        progressBar.setValue(percent);
                        progressLabel.setText(
                                formatSize(downloaded) + " / " + formatSize(total) + " (" + percent + "%)"
                        );
                    });
                });

                if (success && !cancelled) {
                    File tempFile = new File(tempPath);
                    File finalFile = new File(originalPath);
                    if (!tempFile.renameTo(finalFile)) {
                        message = "Download completed, but failed to rename the file.";
                        success = false;
                    } else {
                        message = "Download successful!";
                    }
                } else {
                    message = "Download was cancelled.";
                }
            } catch (InterruptedException ex) {
                message = "Download cancelled.";
                success = false;
            } catch (Exception ex) {
                message = "Download failed: " + ex.getMessage();
            }

            if (!success || cancelled) {
                new File(tempPath).delete();
            }

            final String finalMessage = message;
            final boolean finalSuccess = success;

            SwingUtilities.invokeLater(() -> {
                progressDialog.dispose();
                JOptionPane.showMessageDialog(
                        DownloadFile.this,
                        finalMessage,
                        "Updater",
                        finalSuccess ? JOptionPane.INFORMATION_MESSAGE : JOptionPane.ERROR_MESSAGE
                );
            });
        }).start();

        progressDialog.setVisible(true);
    }

    private String formatSize(long bytes) {
        if (bytes < 1024) return bytes + " B";
        int unit = 1024;
        double kb = bytes / (double) unit;
        if (kb < unit) return String.format("%.2f KB", kb);
        double mb = kb / unit;
        if (mb < unit) return String.format("%.2f MB", mb);
        double gb = mb / unit;
        return String.format("%.2f GB", gb);
    }
}