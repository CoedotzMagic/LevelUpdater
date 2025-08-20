package com.coedotzmagic.levelupdater;

import com.coedotzmagic.levelupdater.utils.Updater;

public class LevelUpdater extends Updater {

    public void CheckForUpdate() {
        if (currentVersion == null) {
            showDialog(errorCurrentVer, errorTitle, true);
            return;
        }

        if (checkUpdateURL == null) {
            showDialog(errorCheckUpdateUrl, errorTitle, true);
            return;
        }

        if (downloadFileUrl == null) {
            if (activeWarn) showDialog(errorDownloadFileUrl, errorTitle, true);
            if (isLockout) return;
            isDirectDownload = false;
            activeWarn = true;
            isLockout = true;
        }

        if (downloadPageUrl == null) {
            if (activeWarn) showDialog(errorDownloadPageUrl, errorTitle, true);
            if (isLockout) return;
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