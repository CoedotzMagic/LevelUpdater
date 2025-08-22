package com.coedotzmagic.levelupdater.utils.downloader;

public interface DownloadProgressListener {
    void onProgress(long downloadedBytes, long totalBytes);
}
