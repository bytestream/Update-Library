package com.kbrahney.updater.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

    //holds all of the visible interface components
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

        this.lblUpdateText = new JLabel("Initialising. . .");

        this.updateText = new JTextArea(10, 40);
        this.updateText.setEnabled(false);
        this.updateText.setVisible(false);

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

        this.buttonPanel.add(this.btnCancel);
        this.buttonPanel.add(this.btnDetails);

        this.commandPanel.setLayout(new BorderLayout());
        this.commandPanel.add(this.lblUpdateText, BorderLayout.WEST);
        this.commandPanel.add(this.buttonPanel, BorderLayout.EAST);

       this.detailsPanel.add(this.updateText);











        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
    }


    //methods
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.btnDetails) {
            if(!this.updateText.isVisible())
                this.updateText.setVisible(true);
            else
                this.updateText.setVisible(false);
            this.pack();
        }
    }




}
