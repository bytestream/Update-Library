package com.kbrahney.updater.GUI;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.text.DefaultCaret;

/**
 * Created with IntelliJ IDEA.
 * User: chris
 * Date: 23/05/2013
 * Time: 21:54
 * To change this template use File | Settings | File Templates.
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
        this.rootPanel = new JPanel();
        this.topPanel = new JPanel();
        this.progressPanel = new JPanel();
        this.commandPanel = new JPanel();
        this.buttonPanel = new JPanel();
        this.detailsPanel = new JPanel();

        this.progressBar = new JProgressBar();
        this.progressBar.setPreferredSize(new Dimension(500, 30));

        this.btnDetails = new JButton("Details");
        this.btnDetails.addActionListener(this);

        this.btnCancel = new JButton("Cancel");
        this.btnCancel.addActionListener(this);

        this.lblUpdateText = new JLabel("Initialising. . .");
        this.lblUpdateText.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 0));

        this.updateText = new JTextArea(10, 40);
        this.updateText.setBorder(BorderFactory.createLoweredBevelBorder());
        this.updateText.setEnabled(false);
        this.updateText.setVisible(true);

        this.scrollPane = new JScrollPane(this.updateText);
        this.scrollPane.setAutoscrolls(true);
        this.scrollPane.setVisible(false);
        DefaultCaret caret = (DefaultCaret)this.updateText.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

        //add components to the interface
        this.add(this.rootPanel);

        //sets up the root of the window
        this.rootPanel.setLayout(new BorderLayout());
        this.rootPanel.add(this.topPanel, BorderLayout.NORTH);
        this.rootPanel.add(this.detailsPanel, BorderLayout.SOUTH);

        //the top half of the screen
        this.topPanel.setLayout(new BorderLayout());
        this.topPanel.add(this.progressPanel, BorderLayout.NORTH);
        this.topPanel.add(this.commandPanel, BorderLayout.SOUTH);

        this.progressPanel.add(this.progressBar);

        this.buttonPanel.add(this.btnDetails);
        this.buttonPanel.add(this.btnCancel);

        this.commandPanel.setLayout(new BorderLayout());
        this.commandPanel.add(this.lblUpdateText, BorderLayout.WEST);
        this.commandPanel.add(this.buttonPanel, BorderLayout.EAST);

        this.detailsPanel.add(this.scrollPane);

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
    }


    //methods
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.btnDetails) {
            if(!this.scrollPane.isVisible())
                this.scrollPane.setVisible(true);
            else
                this.scrollPane.setVisible(false);
            this.pack();
        }
        if (e.getSource() == this.btnCancel) {
            // handle clean exit of update
            System.exit(1);
        }
    }

    /**
     * Update the GUI with the latest update feed. This updates both the
     * label and also appends to the details pane.
     * @param message <b>String</b> Message to show to the user
     */
    public void addMessageToView(String message) {
        this.updateText.append(message + "\n");
        changeUpdateText(message);
    }

    /**
     * Updates the label in the GUI with the latest update feed
     * @param message <b>String</b> Message to display to the user
     */
    public void changeUpdateText(String message) {
        this.lblUpdateText.setText(message);
    }

    /**
     * Sets the progress bars current value to n
     * @param value <b>int</b> Number to update to
     */
    public void updateProgressBar(int value) {
        this.progressBar.setValue(value);
    }

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
