package com.coedotzmagic.levelupdater.utils;

import com.coedotzmagic.levelupdater.utils.downloader.DownloadFile;
import javax.swing.*;
import java.io.File;

public class FileChooser extends JFrame {

    public FileChooser(String url) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Save File");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

        int result = showDialog(fileChooser);
        if (result == JFileChooser.APPROVE_OPTION) {
            handleFileSelection(fileChooser, url);
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
}