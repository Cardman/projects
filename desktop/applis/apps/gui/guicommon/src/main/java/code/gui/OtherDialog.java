package code.gui;

import code.gui.images.AbstractImage;
import code.gui.images.AbstractImageFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowListener;

public final class OtherDialog implements ChangeableTitle,WithListener {
    private final JDialog dialog = new JDialog();
    private Ownable owner;
    private AbstractImage image;

    @Override
    public String getTitle() {
        return dialog.getTitle();
    }

    @Override
    public void setTitle(String _title) {
        dialog.setTitle(_title);
    }

    @Override
    public Point getLocationOnScreen() {
        return dialog.getLocationOnScreen();
    }

    Window getComponent() {
        return dialog;
    }

    void setIconImage(AbstractImageFactory _fact, AbstractImage _group) {
        dialog.setIconImage(((ImageIcon)PreparedLabel.buildIcon(_fact,_group)).getImage());
    }
    @Override
    public AbstractImage getImageIconFrame() {
        return image;
    }

    public void setImage(AbstractImageFactory _fact, AbstractImage _image) {
        image = _image;
        setIconImage(_fact,_image);
    }

    @Override
    public void setLocationRelativeTo(CustComponent _c) {
        dialog.setLocationRelativeTo(_c.getComponent());
    }

    @Override
    public void setLocationRelativeTo(OtherFrame _c) {
        dialog.setLocationRelativeTo(_c.getComponent());
    }

    @Override
    public void setLocationRelativeTo(OtherDialog _c) {
        dialog.setLocationRelativeTo(_c.getComponent());
    }

    @Override
    public void setLocationRelativeToNull() {
        dialog.setLocationRelativeTo(null);
    }

    @Override
    public boolean isVisible() {
        return dialog.isVisible();
    }

    @Override
    public void dispose() {
        dialog.dispose();
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

    public void setJMenuBar(MenuBar _menuBar) {
        dialog.setJMenuBar(_menuBar.getMenuBar());
    }
}
