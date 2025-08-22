package com.coedotzmagic.levelupdater.utils.downloader;

import javax.swing.*;
import java.awt.*;

public class DownloadFile extends JFrame {
    BackgroundProcess backgroundProcess = new BackgroundProcess();

    public DownloadFile(String url, String path) {
        JDialog progressDialog = new JDialog(this, "Downloading...", true);
        progressDialog.setLayout(new BorderLayout());

        JProgressBar progressBar = new JProgressBar(0, 100);
        progressBar.setStringPainted(true);
        JLabel progressLabel = new JLabel("Starting download...");

        progressDialog.add(progressLabel, BorderLayout.NORTH);
        progressDialog.add(progressBar, BorderLayout.CENTER);
        progressDialog.setSize(400, 100);
        progressDialog.setLocationRelativeTo(this);
        progressDialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);

        new Thread(() -> {
            String message;
            boolean success = false;

            try {
                success = backgroundProcess.downloadFileFromURL(url, path, (downloadedBytes, totalBytes) -> {
                    int percent = totalBytes > 0 ? (int)((downloadedBytes * 100) / totalBytes) : 0;

                    SwingUtilities.invokeLater(() -> {
                        progressBar.setValue(percent);
                        progressLabel.setText(
                                formatSize(downloadedBytes) + " / " + formatSize(totalBytes) + " (" + percent + "%)"
                        );
                    });
                });

                message = success ? "Download successful!" : "Download failed due to an unknown error.";
            } catch (Exception ex) {
                message = "Download failed: " + ex.getMessage();
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