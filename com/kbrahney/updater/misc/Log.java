package com.kbrahney.updater.misc;

import java.io.IOException;
import java.util.logging.*;

/**
 *
 * @author Kieran
 * File: Log.java
 * Created: 23/05/2013 10:20:32
 */
public class Log {

    private static FileHandler fh = null;
    
    public void init() {
        try {
            fh = new FileHandler("update.log", false);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Logger l = Logger.getLogger("");
        fh.setFormatter(new SimpleFormatter());
        l.addHandler(fh);
        l.setLevel(Level.ALL);
    }

}
