package com.kbrahney.updater.io;

import java.io.*;

/**
 * @author Kieran
 *         FileIO: FileIO.java
 *         Created: 24/05/2013 17:59:12
 */
public class FileIO {

    /**
     * Executes a given file.
     * <b>Note:</b> only runs exe and jar files
     *
     * @param filePath <b>String</b> The absolute file path to the executable
     * @return <b>boolean</b> Whether or not the execution of the file was completed successfully
     */
    public boolean executeFile(String filePath) {
        if (filePath.substring(filePath.lastIndexOf(".")).equals(".exe")) {
            try {
                Runtime.getRuntime().exec("run \"" + filePath + "\"");
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        if (filePath.substring(filePath.lastIndexOf(".")).equals(".jar")) {
            try {
                Runtime.getRuntime().exec("java -jar \"" + filePath + "\"");
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }

    /**
     * Write the given bytes to the specified file location
     *
     * @param filePath <b>String</b> Absolute path to the file location you wish to write to
     * @param bytes    <b>byte[]</b> byte array that essentially builds file
     * @return <b>boolean</b> Whether or not writing the file was successful
     */
    public boolean writeFile(String filePath, byte[] bytes) {
        final int bufferSize = Integer.MAX_VALUE;
        OutputStream out = null;
        File f = new File(filePath);

        try {
            if (!f.exists())
                f.createNewFile();

            out = new BufferedOutputStream(new FileOutputStream(f));

            byte[] buffer = new byte[bufferSize];
            for (int i = 0; i < bytes.length; i++) {
                buffer[i % bufferSize] = bytes[i];
                // every n bytes write to the file (save resource intensive write for every byte..)
                if ((i % bufferSize) == 0) {
                    out.write(buffer, 0, bufferSize);
                }
                // only so few bytes left so write once we reach the end
                else if (i == (bytes.length - 1)) {
                    out.write(buffer, 0, buffer.length);
                }
            }

            return true;
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            return false;
        } catch (IOException ioe) {
            ioe.printStackTrace();
            return false;
        } finally {
            try {
                out.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * Copy a file from one location to another
     *
     * @param fromFileName <b>String</b> Absolute path to the source file location
     * @param toFileName   <b>String</b> Absolute path to the destination file location
     * @return <b>boolean</b> Whether or not the copy was successful
     */
    public boolean copyFile(String fromFileName, String toFileName) {
        InputStream in = null;
        OutputStream out = null;

        try {
            in = new FileInputStream(new File(fromFileName));
            File dstFile = new File(toFileName);
            if (!dstFile.exists())
                dstFile.createNewFile();

            out = new FileOutputStream(dstFile);
            byte[] buf = new byte[1024];
            int len;
            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }

            return true;
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            return false;
        } catch (IOException ioe) {
            ioe.printStackTrace();
            return false;
        } finally {
            try {
                in.close();
                out.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

}
