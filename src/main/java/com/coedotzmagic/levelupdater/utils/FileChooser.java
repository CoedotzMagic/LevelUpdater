package com.coedotzmagic.levelupdater.utils;

import com.coedotzmagic.levelupdater.utils.downloader.DownloadFile;
import javax.swing.*;
import java.io.File;
import java.net.URL;

public class FileChooser extends JFrame {

    public FileChooser(String url) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Save File");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

        String suggestedFileName = getFileNameFromURL(url);
        fileChooser.setSelectedFile(new File(suggestedFileName));

        int result = showDialog(fileChooser);
        if (result == JFileChooser.APPROVE_OPTION) {
            handleFileSelection(fileChooser, url);
        } else {
            Dialog.showDialog("Download Cancelled","Updater", false);
        }
    }

    private int showDialog(JFileChooser fileChooser) {
        return fileChooser.showSaveDialog(this);
    }

    private void handleFileSelection(JFileChooser fileChooser, String url) {
        File selectedFile = fileChooser.getSelectedFile();
        String filePath = selectedFile.getAbsolutePath();
        new DownloadFile(url, filePath);
    }

    private String getFileNameFromURL(String fileUrl) {
        try {
            URL url = new URL(fileUrl);
            String path = url.getPath();
            String fileName = path.substring(path.lastIndexOf('/') + 1);
            return fileName.isEmpty() ? "downloaded_file" : fileName;
        } catch (Exception e) {
            System.err.println("Failed to extract filename: " + e.getMessage());
            return "downloaded_file";
        }
    }
}