package com.kbrahney.updater.gui;

import javax.swing.*;
import javax.swing.text.DefaultCaret;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author chris
 * @date 23/05/2013
 * @time 21:54
 */
public class GUIInterface extends JFrame implements ActionListener {

    //root of the windows
    private JPanel rootPanel;

    //holds all of the main interface components
    private JPanel topPanel;
    private JPanel progressPanel;
    private JPanel commandPanel;
    private JPanel buttonPanel;

    //holds the optional/hidden messages
    private JPanel detailsPanel;

    //interface elements
    private JProgressBar progressBar;
    private JButton btnCancel;
    private JButton btnDetails;
    private JLabel lblUpdateText;

    private JScrollPane scrollPane;
    private JTextArea updateText;

    //constructor
    public GUIInterface() {
        //init all of the components
        rootPanel = new JPanel();
        topPanel = new JPanel();
        progressPanel = new JPanel();
        commandPanel = new JPanel();
        buttonPanel = new JPanel();
        detailsPanel = new JPanel();

        progressBar = new JProgressBar();
        progressBar.setPreferredSize(new Dimension(500, 30));

        btnDetails = new JButton("Details");
        btnDetails.addActionListener(this);

        btnCancel = new JButton("Cancel");
        btnCancel.addActionListener(this);

        lblUpdateText = new JLabel("Initialising. . .");
        lblUpdateText.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 0));

        updateText = new JTextArea(10, 40);
        updateText.setBorder(BorderFactory.createLoweredBevelBorder());
        updateText.setEnabled(false);
        updateText.setVisible(true);

        scrollPane = new JScrollPane(updateText);
        scrollPane.setAutoscrolls(true);
        scrollPane.setVisible(false);
        DefaultCaret caret = (DefaultCaret) updateText.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

        //add components to the interface
        add(rootPanel);

        //sets up the root of the window
        rootPanel.setLayout(new BorderLayout());
        rootPanel.add(topPanel, BorderLayout.NORTH);
        rootPanel.add(detailsPanel, BorderLayout.SOUTH);

        //the top half of the screen
        topPanel.setLayout(new BorderLayout());
        topPanel.add(progressPanel, BorderLayout.NORTH);
        topPanel.add(commandPanel, BorderLayout.SOUTH);

        progressPanel.add(progressBar);

        buttonPanel.add(btnDetails);
        buttonPanel.add(btnCancel);

        commandPanel.setLayout(new BorderLayout());
        commandPanel.add(lblUpdateText, BorderLayout.WEST);
        commandPanel.add(buttonPanel, BorderLayout.EAST);

        detailsPanel.add(scrollPane);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }


    //methods
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnDetails) {
            if (!scrollPane.isVisible())
                scrollPane.setVisible(true);
            else
                scrollPane.setVisible(false);
            pack();
        }
        if (e.getSource() == btnCancel) {
            // handle clean exit of update
            System.exit(1);
        }
    }

    /**
     * Update the GUI with the latest update feed. This updates both the
     * label and also appends to the details pane.
     *
     * @param message <b>String</b> Message to show to the user
     */
    public void addMessageToView(String message) {
        updateText.append(message + "\n");
        changeUpdateText(message);
    }

    /**
     * Updates the label in the GUI with the latest update feed
     *
     * @param message <b>String</b> Message to display to the user
     */
    public void changeUpdateText(String message) {
        lblUpdateText.setText(message);
    }

    /**
     * Sets the progress bars current value to n
     *
     * @param value <b>int</b> Number to update to
     */
    public void setProgressBar(int value) {
        progressBar.setValue(value);
    }

    /**
     * Returns the progress bar's current value
     *
     * @return <b>int</b> the current value of the progress bar
     */
    public int getProgressValue() {
        return progressBar.getValue();
    }

    /**
     * Send an information dialog box to the user telling them
     * that there are no available updates.<br />
     * <b>Best used after isUpdateAvailable() has returned false.</b>
     *
     * @param message <b>String</b> Message to display to the user (in the dialog box)
     * @param title   <b>String</b> Title of the dialog box
     */
    public void displayNoUpdate(String message, String title) {
        String msg = (message == null || message.equals("")) ? "You are "
                + "currently running the most up to date version of this software" :
                message;
        String ttle = (title == null || title.equals("")) ? "No update available" :
                title;

        JOptionPane.showMessageDialog(this, msg, ttle, JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Send a dialog box to the user telling them that an update is available
     * for the given software package. The user is given the option to update
     * to the latest software version or cancel.<br />
     * <b>Best used after isUpdateAvailable() has returned true</b>
     *
     * @param message <b>String</b> Message to display to the user (in the dialog box)
     * @param title   <b>String</b> Title of the dialog box
     * @return <b>int</b> Check this value against JOptionPane.OK_OPTION and proceed
     *         accordingly (value of OK_OPTION assumes they want to update.)
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
