package code.gui.initialize;

import javax.swing.UIManager;

public final class UpdateStyleImpl implements UpdateStyle {
    @Override
    public String update() {
        String className_ = UIManager.getSystemLookAndFeelClassName();
        try {
            /*Permet d avoir une application graphique comme si c etait Windows*/
            UIManager.setLookAndFeel(className_);
            return className_;
        } catch (Exception e) {
            return null;
        }
    }
}
