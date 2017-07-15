package code.gui;
import java.awt.Component;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public final class SetStyle {

    private SetStyle() {
    }

    public static void setupStyle(Component _frame) {
        try {
            /*Permet d avoir une application graphique comme si c etait Windows*/
            String className_ = UIManager.getSystemLookAndFeelClassName();
            UIManager.setLookAndFeel(className_);
            SwingUtilities.updateComponentTreeUI(_frame);
        } catch (RuntimeException _0) {
        } catch (ClassNotFoundException _0) {
        } catch (InstantiationException _0) {
        } catch (IllegalAccessException _0) {
        } catch (UnsupportedLookAndFeelException _0) {
        }
    }
}
