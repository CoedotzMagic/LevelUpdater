package com.coedotzmagic.levelupdater.utils.downloader;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class BackgroundProcess {

    public boolean downloadFileFromURL(String fileURL, String savePath, DownloadProgressListener listener) throws IOException, InterruptedException {
        URL url = new URL(fileURL);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestMethod("GET");
        httpURLConnection.setConnectTimeout(5000);
        httpURLConnection.setReadTimeout(5000);

        long totalSize = httpURLConnection.getContentLengthLong();

        try (InputStream inputStream = httpURLConnection.getInputStream();
             OutputStream outputStream = new FileOutputStream(savePath)) {

            byte[] buffer = new byte[4096];
            int bytesRead;
            long downloaded = 0;

            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
                downloaded += bytesRead;

                if (listener != null) {
                    listener.onProgress(downloaded, totalSize);
                }
            }
        }

        return true;
    }
}