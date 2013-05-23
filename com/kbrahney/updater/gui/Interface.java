package com.kbrahney.updater.GUI;

import javax.swing.*;

/**
 *
 * @author Kieran
 * File: Interface.java
 * Created: 23/05/2013 10:03:12
 */
public class Interface extends JFrame {

    /**
     * Shitty GUI generator... needs a severe rework.
     */
    public Interface() {
        progressBar = new javax.swing.JProgressBar();
        jScrollPane1 = new javax.swing.JScrollPane();
        detailsPane = new javax.swing.JTextArea();
        jPanel1 = new javax.swing.JPanel();
        detailsLabel = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        detailsButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Updating...");
        setMinimumSize(new java.awt.Dimension(490, 108));
        setResizable(false);

        detailsPane.setColumns(20);
        detailsPane.setEditable(false);
        detailsPane.setFont(new java.awt.Font("Monospaced", 0, 11));
        detailsPane.setForeground(new java.awt.Color(51, 51, 51));
        detailsPane.setLineWrap(true);
        detailsPane.setRows(5);
        detailsPane.setText("[ Updater Lib by Kieran Brahney <http://miami-nice.co.uk> ]");
        jScrollPane1.setVisible(false);
        jScrollPane1.setViewportView(detailsPane);

        detailsLabel.setText("Initialising...");

        jPanel2.setLayout(new java.awt.BorderLayout());

        detailsButton.setText("Details");
        detailsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                detailsButtonActionPerformed(evt);
            }
        });
        jPanel2.add(detailsButton, java.awt.BorderLayout.LINE_START);

        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });
        jPanel2.add(cancelButton, java.awt.BorderLayout.LINE_END);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(detailsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(detailsLabel)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(progressBar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
        setVisible(true);
    }

    private void detailsButtonActionPerformed(java.awt.event.ActionEvent evt) {
        jScrollPane1.setVisible(jScrollPane1.isVisible() ? false : true);
        if (jScrollPane1.isVisible())
            setSize(490, 350);
        else
            setSize(490, 108);
    }

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // Handle clean exit
        System.exit(1);
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

    // Variables declaration - do not modify
    private javax.swing.JButton cancelButton;
    private javax.swing.JButton detailsButton;
    private javax.swing.JLabel detailsLabel;
    private javax.swing.JTextArea detailsPane;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JProgressBar progressBar;
    // End of variables declaration

}
