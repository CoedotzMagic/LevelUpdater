package com.coedotzmagic.levelupdater.utils.downloader;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class BackgroundProcess {

    public boolean downloadFileFromURL(String fileURL, String savePath) throws IOException {
        // Create URL object
        URL url = new URL(fileURL);

        // Open a connection to the URL
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestMethod("GET");
        httpURLConnection.setConnectTimeout(5000);
        httpURLConnection.setReadTimeout(5000);

        // Get the input stream to read the file
        try (InputStream inputStream = httpURLConnection.getInputStream();
             OutputStream outputStream = new FileOutputStream(savePath)) {

            byte[] buffer = new byte[4096];
            int bytesRead;

            // Read the file from the URL and write it to the file on disk
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
        } catch (IOException e) {
            throw new IOException("Failed to download file: " + e.getMessage());
        }

        return true; // If everything went fine, return success
    }
}
