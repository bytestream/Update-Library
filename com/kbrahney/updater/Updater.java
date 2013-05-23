package com.kbrahney.updater;

import java.util.ArrayList;
import com.kbrahney.updater.IO.Download;

/**
 *
 * @author Kieran
 * File: Updater.java
 * Created: 23/05/2013 09:59:53
 */
public class Updater {

    private String VERSION_URL = "";

    /**
     * Check if an update is available at VERSION_URL
     * @param currentVersion The current version of given project
     * @return boolean - update available or not
     */
    public boolean isUpdateAvailable(int currentVersion) {
        if (!getVersionURL().equals("")) {
            return false;
        }
        else {
            ArrayList<String> data = Download.getFileData(getVersionURL());
            if (data == null) {
                return false;
            } else {
                float version = Float.parseFloat(data.get(0));
                
                if (version > currentVersion)
                    return true;
                else
                    return false;
            }
        }
    }

    /**
     * Get the URL from which displays the version for a given project<br />
     * For example: http://mydomain.com/projects/getversion.php?proj=server
     * @return String
     */
    public String getVersionURL() {
        return VERSION_URL;
    }

    /**
     * Set the URL from which displays the version of a given project<br />
     * For example: http://mydomain.com/projects/getversion.php?proj=server
     * @param VERSION_URL String URL
     */
    public void setVersionURL(String VERSION_URL) {
        this.VERSION_URL = VERSION_URL;
    }

}
