package com.coedotzmagic.levelupdater;

import com.coedotzmagic.levelupdater.utils.Updater;

public class LevelUpdater extends Updater {

    public void CheckForUpdate() {
        if (currentVersion == null || currentVersion.isEmpty()) {
            showDialog(errorCurrentVer, errorTitle, true);
            return;
        }

        if (checkUpdateURL == null || checkUpdateURL.isEmpty()) {
            showDialog(errorCheckUpdateUrl, errorTitle, true);
            return;
        }

        if (downloadPageUrl == null ||  downloadPageUrl.isEmpty()) {
            if (!isDirectDownload) {
                if (downloadFileUrl != null && !downloadFileUrl.equalsIgnoreCase("")) {
                    isDirectDownload = true;
                } else {
                    showDialog(errorDownloadPageUrl, errorTitle, true);
                    return;
                }
            } else {
                if (activeWarn) showDialog(errorDownloadPageUrl, errorTitle, true);
                if (isLockout) return;
                activeWarn = true;
                isLockout = true;
            }
        }

        if (downloadFileUrl == null || downloadFileUrl.isEmpty()) {
            if (isDirectDownload) {
                if (downloadPageUrl != null && !downloadPageUrl.equalsIgnoreCase("")) {
                    isDirectDownload = false;
                } else {
                    if (activeWarn) showDialog(errorDownloadFileUrl, errorTitle, true);
                    if (isLockout) return;
                }
            }
        }

        checkForUpdate();
    }

    public void SetCurrentVersion(String currentVersion) {
        this.currentVersion = currentVersion;
    }

    public void SetCheckUpdateURL(String checkUpdateURL) {
        this.checkUpdateURL = checkUpdateURL;
    }

    public void SetIsDirectDownload(boolean isDirectDownload) {
        this.isDirectDownload = isDirectDownload;
    }

    public void SetDownloadFileUrl(String downloadFileUrl) {
        this.downloadFileUrl = downloadFileUrl;
    }

    public void SetDownloadPageUrl(String downloadPageUrl) {
        this.downloadPageUrl = downloadPageUrl;
    }
}