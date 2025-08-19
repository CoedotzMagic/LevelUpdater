package com.coedotzmagic.levelupdater.utils;

import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;

public class Updater extends Dialog {
    protected String currentVersion = "1.0.0";
    protected String updateURL = "https://coedotzmagic.com/latest-version.txt";
    protected String downloadUrl = "https://coedotzmagic.com/download";
    protected boolean isDirectDownload = true;

    protected void checkForUpdate() {
        try {
            URL url = new URL(updateURL);
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
            String latestVersion = in.readLine().trim();
            in.close();

            if (isNewerVersion(latestVersion, currentVersion)) {
                showDialog("A new version (" + latestVersion + ") is available!", "Update Available", false);
                //Desktop.getDesktop().browse(new URI(downloadUrl));
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
