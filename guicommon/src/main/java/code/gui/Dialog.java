package code.gui;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Window;
import java.awt.event.WindowListener;

import javax.swing.JDialog;
import javax.swing.WindowConstants;

import code.gui.events.CrossClosingDialogEvent;
import code.gui.stream.ExtractFromFiles;
import code.util.StringMap;

public abstract class Dialog implements ChangeableTitle {

    private String accessFile;

    private Image imageIconFrame;

    private Panel contentPane  = new Panel();

    private JDialog dialog = new JDialog();
    private Ownable owner;

    public void setSize(Dimension _d) {
        dialog.setSize(_d);
    }

    public void setSize(int _width, int _height) {
        dialog.setSize(_width, _height);
    }

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
    @Override
    public Window getComponent() {
        return dialog;
    }

    public void setLocationRelativeTo(ChangeableTitle _onwer) {
        dialog.setLocationRelativeTo(_onwer.getComponent());
    }

    public void setLocationRelativeToWindow(Iconifiable _i) {
        if (_i instanceof Component) {
            dialog.setLocationRelativeTo((Component)_i);
        }
    }

    protected StringMap<String> getMessages(CommonFrame _lg,String _messageFolder) {
        String lg_ = _lg.getLanguageKey();
        return ExtractFromFiles.getMessagesFromLocaleClass(_messageFolder, lg_, accessFile);
    }

    protected void setAccessFile(String _accessFile) {
        accessFile = _accessFile;
    }

    public void closeWindow() {
        setVisible(false);
        dialog.getContentPane().removeAll();
    }

    protected void setDialogIcon(Iconifiable _group) {
        dialog.setIconImage(_group.getImageIconFrame());
        imageIconFrame = _group.getImageIconFrame();
    }

    @Override
    public Image getImageIconFrame() {
        return imageIconFrame;
    }

    public void setContentPane(Panel _contentPane) {
        dialog.setContentPane(_contentPane.getComponent());
        contentPane = _contentPane;
    }

    public void setContentPane(ScrollPane _contentPane) {
        Panel p_ = new Panel();
        p_.add(_contentPane);
        setContentPane(p_);
    }
    public Panel getPane() {
        return contentPane;
    }
    public void setModal(boolean _modal) {
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
}
