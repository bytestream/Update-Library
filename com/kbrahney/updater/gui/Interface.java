package com.kbrahney.updater.gui;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Kieran
 * File: Interface.java
 * Created: 23/05/2013 10:03:12
 */
public class Interface extends JFrame {

    /**
     * Send an information dialog box to the user telling them
     * that there are no available updates.<br />
     * <b>Best used after isUpdateAvailable() has returned false.</b>
     * @param message <b>String</b> Message to display to the user (in the dialog box)
     * @param title <b>String</b> Title of the dialog box
     */
    public void displayNoUpdate(String message, String title) {
        String msg = (message == null || message.equals("")) ? "You are "
                + "currently running the most up to date version of this software" :
            message;
        String ttle = (title == null || title.equals("")) ? "No update available" :
            title;

        JOptionPane.showMessageDialog(this, msg,  ttle, JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Send a dialog box to the user telling them that an update is available
     * for the given software package. The user is given the option to update
     * to the latest software version or cancel.<br />
     * <b>Best used after isUpdateAvailable() has returned true</b>
     * @param message <b>String</b> Message to display to the user (in the dialog box)
     * @param title <b>String</b> Title of the dialog box
     * @return <b>int</b> Check this value against JOptionPane.OK_OPTION and proceed
     *                    accordingly (value of OK_OPTION assumes they want to update.)
     */
    public int displayUpdateAvailable(String message, String title) {
        String msg = (message == null || message.equals("")) ? "An update is available."
                + " Would you like to update to the latest version?" : message;
        String ttle = (title == null || title.equals("")) ? "Update available!" :
            title;

        return JOptionPane.showConfirmDialog(this, msg, ttle, JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE);
    }

}
