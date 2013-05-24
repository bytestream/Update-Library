package com.kbrahney.updater.Misc;

import com.kbrahney.updater.GUI.GUIInterface;
import com.kbrahney.updater.IO.Download;
import com.kbrahney.updater.Model.DownloadedFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: Chris
 * Date: 24/05/13
 * Time: 17:16
 * To change this template use File | Settings | File Templates.
 */
public class DownloadTask implements Runnable{
    private GUIInterface _view;
    private DownloadedFile _file;
    private String _urlString;
    private int _filesize;

    public DownloadTask(GUIInterface view, String urlString) {
        this._view = view;
        this._urlString = urlString;
    }

    //code that is run when the thread is started
    @Override
    public void run() {
       this._filesize =  Download.getFileSize(this._urlString);
       this._file = new DownloadedFile(this._filesize);

        InputStream in;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            URL url = new URL(this._urlString);
            URLConnection conn = url.openConnection();
            in = conn.getInputStream();

            byte[] bytes = new byte[4096];
            int len;
            while ((len = in.read(bytes)) > 0) {
                baos.write(bytes, 0, len);
            }
            this._file.set_downloadedFile(baos.toByteArray());
        } catch (MalformedURLException ex) {
            Logger.getLogger(Log.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ioe) {
            Logger.getLogger(Log.class.getName()).log(Level.SEVERE, null, ioe);
        }
    }
}
