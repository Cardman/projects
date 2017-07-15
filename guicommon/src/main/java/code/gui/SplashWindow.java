package code.gui;
import java.awt.Frame;
import java.awt.Window;

import javax.swing.JWindow;

public final class SplashWindow extends JWindow {

    public SplashWindow() {
        WindowUtils.addInArray(this);
    }

    public SplashWindow(Frame _owner) {
        super(_owner);
    }

    public SplashWindow(Window _owner) {
        super(_owner);
    }

    @Override
    public void setVisible(boolean _b) {
        super.setVisible(_b);
        if (!_b) {
            WindowUtils.removeWindow(this);
        }
    }
}
