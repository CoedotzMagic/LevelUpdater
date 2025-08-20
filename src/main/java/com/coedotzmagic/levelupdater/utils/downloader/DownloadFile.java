package com.coedotzmagic.levelupdater.utils.downloader;

import javax.swing.*;

public class DownloadFile extends JFrame {
    BackgroundProcess backgroundProcess = new BackgroundProcess();

    public DownloadFile(String url, String path) {
        new Thread(() -> {
            String message;
            boolean success = false;
            try {
                success = backgroundProcess.downloadFileFromURL(url, path);
                if (success) {
                    message = "Download successful!";
                } else {
                    message = "Download failed due to an unknown error.";
                }
            } catch (Exception ex) {
                message = "Download failed: " + ex.getMessage();
            }

            // Show the result message (success or failure) on the Event Dispatch Thread
            final String finalMessage = message;
            final boolean finalSuccess = success;
            SwingUtilities.invokeLater(() -> {
                JOptionPane.showMessageDialog(DownloadFile.this,
                        finalMessage,
                        "Download Document",
                        finalSuccess ? JOptionPane.INFORMATION_MESSAGE : JOptionPane.ERROR_MESSAGE);
            });
        }).start();
    }
}
