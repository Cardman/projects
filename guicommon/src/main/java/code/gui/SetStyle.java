package code.gui;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

final class SetStyle {

    private SetStyle() {
    }

    static void setupStyle(AbsFrame _frame) {
        try {
            /*Permet d avoir une application graphique comme si c etait Windows*/
            String className_ = UIManager.getSystemLookAndFeelClassName();
            UIManager.setLookAndFeel(className_);
            SwingUtilities.updateComponentTreeUI(_frame.getComponent());
        } catch (Exception _0) {
            //skip exception
        }
    }
}
