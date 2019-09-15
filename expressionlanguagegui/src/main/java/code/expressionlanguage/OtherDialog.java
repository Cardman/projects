package code.expressionlanguage;

import code.gui.ChangeableTitle;
import code.gui.MenuBar;
import code.gui.Ownable;
import code.gui.Panel;
import code.gui.WithListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;

public final class OtherDialog implements ChangeableTitle,WithListener {
    private JDialog dialog = new JDialog();
    private Ownable owner;
    private BufferedImage image;

    @Override
    public String getTitle() {
        return dialog.getTitle();
    }

    @Override
    public void setTitle(String _title) {
        dialog.setTitle(_title);
    }

    @Override
    public Window getComponent() {
        return dialog;
    }

    @Override
    public Image getImageIconFrame() {
        return image;
    }

    public void setImage(BufferedImage _image) {
        image = _image;
    }

    @Override
    public boolean isVisible() {
        return dialog.isVisible();
    }
    public void setVisible(boolean _b) {
        dialog.setVisible(_b);
    }
    public boolean isModal() {
        return dialog.isModal();
    }
    public void setModal(boolean _modal) {
        dialog.setModal(_modal);
    }
    @Override
    public void pack() {
        dialog.pack();
    }

    public void addWindowListener(WindowListener _l) {
        dialog.addWindowListener(_l);
    }

    @Override
    public void removeWindowListener(WindowListener _l) {
        dialog.removeWindowListener(_l);
    }

    public void setContentPane(Panel _contentPane) {
        dialog.setContentPane(_contentPane.getComponent());
    }
    @Override
    public WindowListener[] getWindowListeners() {
        return dialog.getWindowListeners();
    }
    public void setDefaultCloseOperation(int _operation) {
        dialog.setDefaultCloseOperation(_operation);
    }

    @Override
    public Ownable getOwner() {
        return owner;
    }

    @Override
    public void setOwner(Ownable _owner) {
        owner = _owner;
    }

    public void setJMenuBar(MenuBar menuBar) {
        dialog.setJMenuBar(menuBar.getMenuBar());
    }
}
