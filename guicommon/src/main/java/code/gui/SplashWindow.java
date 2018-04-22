package code.gui;
import java.awt.Image;

import javax.swing.JWindow;

public final class SplashWindow implements Iconifiable {

    private Panel contentPane;

    private JWindow window;
    private Ownable owner;

    public SplashWindow() {
        window = new JWindow();
        WindowUtils.addInArray(window);
    }

    public SplashWindow(Ownable _owner) {
        if (_owner instanceof CommonFrame) {
            window = new JWindow(((CommonFrame)_owner).getComponent());
        } else {
            window = new JWindow(((Dialog)_owner).getComponent());
        }
        owner = _owner;
    }

    public void setVisible(boolean _b) {
        window.setVisible(_b);
        if (!_b) {
            WindowUtils.removeWindow(window);
        }
    }
    public void setContentPane(Panel _contentPane) {
        window.setContentPane(_contentPane.getComponent());
        contentPane = _contentPane;
    }
    public Panel getPane() {
        return contentPane;
    }

    @Override
    public Image getImageIconFrame() {
        return null;
    }

    @Override
    public boolean isVisible() {
        return window.isVisible();
    }

    @Override
    public void pack() {
        window.pack();
    }

    @Override
    public Ownable getOwner() {
        return owner;
    }

    @Override
    public void setOwner(Ownable _owner) {
        owner = _owner;
    }

    public void setIconImage(Image _imageIconFrame) {
        window.setIconImage(_imageIconFrame);
    }

    public void dispose() {
        window.dispose();
    }

    public void removeAll() {
        window.removeAll();
    }

    public void setLocationRelativeTo(CommonFrame _owner) {
        window.setLocationRelativeTo(_owner.getFrame());
    }
}
