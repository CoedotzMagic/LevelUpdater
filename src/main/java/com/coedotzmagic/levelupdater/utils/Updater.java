package com.coedotzmagic.levelupdater.utils;

import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;

public class Updater extends Dialog {
    protected Boolean isLockout = false;
    protected Boolean activeWarn =  false;

    protected String currentVersion = null;
    protected String checkUpdateURL = null;
    protected String downloadPageUrl = null;
    protected String downloadFileUrl = null;
    protected boolean isDirectDownload = true;

    protected String errorTitle = "Error!";
    protected String errorCurrentVer = "You must set Current Version of this App!";
    protected String errorCheckUpdateUrl = "You must set Check Update URL!";
    protected String errorDownloadPageUrl = "You must set Download Page URL!";
    protected String errorDownloadFileUrl = "You must set Download File URL!";

    protected void checkForUpdate() {
        try {
            URL url = new URL(checkUpdateURL);
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
            String latestVersion = in.readLine().trim();
            in.close();

            if (isNewerVersion(latestVersion, currentVersion)) {
                if (showDialogOKCancel("A new version (" + latestVersion + ") is available!", "Update Available")) {
                    if (isDirectDownload) new FileChooser(downloadFileUrl);
                    else Desktop.getDesktop().browse(new URI(downloadPageUrl));
                }
            } else {
                showDialog("You are using the latest version.", "No Updates", false);
            }

        } catch (Exception e) {
            showDialog("Failed to check for updates: " + e.getMessage(), "Error", true);
        }
    }

    private boolean isNewerVersion(String remote, String local) {
        String[] rParts = remote.split("\\.");
        String[] lParts = local.split("\\.");
        for (int i = 0; i < Math.max(rParts.length, lParts.length); i++) {
            int r = i < rParts.length ? Integer.parseInt(rParts[i]) : 0;
            int l = i < lParts.length ? Integer.parseInt(lParts[i]) : 0;
            if (r > l) return true;
            if (r < l) return false;
        }
        return false;
    }
}
