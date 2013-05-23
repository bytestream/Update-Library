package com.kbrahney.updater.io;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.kbrahney.updater.misc.Log;

/**
 *
 * @author Kieran
 * File: Download.java
 * Created: 23/05/2013 10:29:21
 */
public class Download {

    /**
     * Pull a string array (ArrayList) of each line from a given URL
     * @param urll A web address to connect to
     * @return ArrayList of line separated data
     */
    public static ArrayList getURL(String urll) {
        ArrayList<String> data = new ArrayList<String>();

        try {
            URL url = new URL(urll);
            URLConnection conn = url.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            String dataLine;
            while ((dataLine = in.readLine()) != null) {
                data.add(dataLine);
            }
            in.close();
        } catch (MalformedURLException ex) {
            Logger.getLogger(Log.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (IOException ioe) {
            Logger.getLogger(Log.class.getName()).log(Level.SEVERE, null, ioe);
            return null;
        }

        return data;
    }

}
