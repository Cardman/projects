package code.gui;

import javax.swing.UIManager;

final class SetStyle {

    private SetStyle() {
    }

    static void setupStyle() {
        try {
            /*Permet d avoir une application graphique comme si c etait Windows*/
            String className_ = UIManager.getSystemLookAndFeelClassName();
            UIManager.setLookAndFeel(className_);
        } catch (Exception e) {
            //skip exception
        }
    }
}
