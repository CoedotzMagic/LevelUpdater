package com.coedotzmagic.levelupdater;

import com.coedotzmagic.levelupdater.utils.Updater;

public class LevelUpdater extends Updater {
    private String errorTitle = "Error!";
    private String errorCurrentVer = "You must set Current Version of this App!";

    public void CheckForUpdate() {
        if (currentVersion == null) {
            showDialog(errorCurrentVer, errorTitle, true);
            return;
        }

        if (updateURL == null) {
            showDialog("", errorTitle, true);
            return;
        }

        if (downloadUrl == null) {
            showDialog("", errorTitle, true);
            return;
        }

        checkForUpdate();
    }

    public void SetCurrentVersion(String currentVersion) {
        this.currentVersion = currentVersion;
    }

    public void SetUpdateURL(String updateURL) {
        this.updateURL = updateURL;
    }

    public void SetIsDirectDownload(boolean isDirectDownload) {
        this.isDirectDownload = isDirectDownload;
    }

    public void SetDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

}
