package com.kbrahney.updater.Model;

/**
 * Created with IntelliJ IDEA.
 * User: Chris
 * Date: 24/05/13
 * Time: 17:45
 * To change this template use File | Settings | File Templates.
 */
public class DownloadedFile {



    private byte[] _downloadedFile;


    public DownloadedFile(int fileSize){
        this._downloadedFile = new byte[fileSize];
    }

    public byte[] get_downloadedFile() {
        return _downloadedFile;
    }

    public void set_downloadedFile(byte[] _downloadedFile) {
        this._downloadedFile = _downloadedFile;
    }


}
