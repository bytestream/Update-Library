package com.kbrahney.updater.Driver;


import com.kbrahney.updater.GUI.GUIInterface;

/**
 * Created with IntelliJ IDEA.
 * User: chris
 * Date: 23/05/2013
 * Time: 21:49
 */
public class Driver {

    public static void main (String[] args) {
        //Interface g = new Interface();
        GUIInterface g = new GUIInterface();
        Driver.viewUpdateTest(g);
    }

    static private void viewUpdateTest(GUIInterface view) {
        for(int i = 0; i <= 10; i++) {
            view.addMessageToView("The update is " + i * 10 + "%");
            view.updateProgressBar(i*10);
            try {
                Thread.sleep(1000);
            }
            catch(InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
