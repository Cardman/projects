package code.gui;
import java.awt.*;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;

import javax.swing.*;

import code.gui.events.CrossClosingDialogEvent;
import code.gui.images.AbstractImage;
import code.gui.images.AbstractImageFactory;

public abstract class Dialog implements ChangeableTitle {

    private String accessFile;

    private AbstractImage imageIconFrame;

    private Panel contentPane  = Panel.newLineBox();

    private final JDialog dialog = new JDialog();
    private Ownable owner;
    protected Dialog() {
        dialog.setModal(true);
        dialog.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        dialog.addWindowListener(new CrossClosingDialogEvent(this));
    }


    public void setResizable(boolean _resizable) {
        dialog.setResizable(_resizable);
    }

    public void addWindowListener(WindowListener _l) {
        dialog.addWindowListener(_l);
    }

    public void setDefaultCloseOperation(int _operation) {
        dialog.setDefaultCloseOperation(_operation);
    }

    protected Window getComponent() {
        return dialog;
    }

    public void setLocationRelativeTo(CommonFrame _onwer) {
        dialog.setLocationRelativeTo(_onwer.getFrame());
    }

    public void setLocationRelativeTo(Dialog _onwer) {
        dialog.setLocationRelativeTo(_onwer.getComponent());
    }

    public void setLocationRelativeTo(OtherDialog _onwer) {
        dialog.setLocationRelativeTo(_onwer.getComponent());
    }

    public void setLocationRelativeToWindow(Iconifiable _i) {
        if (_i instanceof CommonFrame) {
            setLocationRelativeTo((CommonFrame) _i);
        } else if (_i instanceof Dialog) {
            setLocationRelativeTo((Dialog) _i);
        }
    }

    protected String getAccessFile() {
        return accessFile;
    }

    protected void setAccessFile(String _accessFile) {
        accessFile = _accessFile;
    }

    public void closeWindow() {
        setVisible(false);
        contentPane.removeAll();
    }

    protected void setDialogIcon(AbstractImageFactory _fact, Iconifiable _group) {
        dialog.setIconImage(((ImageIcon)PreparedLabel.buildIcon(_fact,_group.getImageIconFrame())).getImage());
        imageIconFrame = _group.getImageIconFrame();
    }

    @Override
    public AbstractImage getImageIconFrame() {
        return imageIconFrame;
    }

    public void setContentPane(Panel _contentPane) {
        dialog.setContentPane(_contentPane.getComponent());
        contentPane = _contentPane;
    }

    public void setContentPane(ScrollPane _contentPane) {
        Panel p_ = Panel.newLineBox();
        p_.add(_contentPane);
        setContentPane(p_);
    }
    public Panel getPane() {
        return contentPane;
    }
    protected void setModal(boolean _modal) {
        dialog.setModal(_modal);
    }
    @Override
    public boolean isVisible() {
        return dialog.isVisible();
    }
    @Override
    public void pack() {
        dialog.pack();
    }
    @Override
    public String getTitle() {
        return dialog.getTitle();
    }
    @Override
    public void setTitle(String _str) {
        dialog.setTitle(_str);
    }
    public void setVisible(boolean _b) {
        dialog.setVisible(_b);
    }

    @Override
    public Ownable getOwner() {
        return owner;
    }

    @Override
    public void setOwner(Ownable _owner) {
        owner = _owner;
    }

    public Point getLocationOnScreen() {
        return dialog.getLocationOnScreen();
    }
}
