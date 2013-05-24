package com.kbrahney.updater.IO;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.kbrahney.updater.Misc.Log;

/**
 *
 * @author Kieran
 * File: Download.java
 * Created: 23/05/2013 10:29:21
 */
public class Download {
    /**
     * Pull a string array (ArrayList) of each line from a given URL
     * @param urlString A web address to connect to
     * @return ArrayList of line separated data
     */
    public static ArrayList getFileData(String urlString) {
        ArrayList<String> data = new ArrayList<String>();

        try {
            URL url = new URL(urlString);
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

    /**
     * Return the content length value from the HTTP headers of a given URL.<br />
     * <b>Note:</b> this value may not accurately represent the file size, hence
     * when downloading, we download in chunks until data size is > buffer size.
     * @param urlString <b>String</b> The URL to get the content-length of
     * @return <b>int</b> size of requested file at given URL
     */
    public static int getFileSize(String urlString) {
        try {
            URL url = new URL(urlString);
            URLConnection conn = url.openConnection();
            return conn.getContentLength();
        } catch (MalformedURLException ex) {
            Logger.getLogger(Log.class.getName()).log(Level.WARNING, null, ex);
            return -1;
        } catch (IOException ioe) {
            Logger.getLogger(Log.class.getName()).log(Level.WARNING, null, ioe);
            return -1;
        }
    }

    /**
     * Downloads a file from a given URL.
     * @param urlString <b>String</b> The URL of which to download the file from
     * @return <b>byte[]</b> a byte array of the downloaded file
     */
    public static byte[] downloadFile(String urlString) {
        InputStream in;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        try {
            URL url = new URL(urlString);
            URLConnection conn = url.openConnection();
            in = conn.getInputStream();

            byte[] bytes = new byte[4096];
            int len;
            while ((len = in.read(bytes)) > 0) {
                baos.write(bytes, 0, len);
            }
            return baos.toByteArray();
        } catch (MalformedURLException ex) {
            Logger.getLogger(Log.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (IOException ioe) {
            Logger.getLogger(Log.class.getName()).log(Level.SEVERE, null, ioe);
            return null;
        }
    }


}
