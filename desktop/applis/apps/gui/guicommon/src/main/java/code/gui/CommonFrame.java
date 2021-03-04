package code.gui;
import java.awt.*;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

import code.sml.stream.ExtractFromFiles;
import code.util.StringMap;

public abstract class CommonFrame extends AbsFrame implements ChangeableTitle {

    private String accessFile;

    private Panel contentPane = Panel.newLineBox();

    private Ownable owner;
    private final JFrame frame = new JFrame();
    private MenuBar menuBar;
    private String languageKey;
    protected CommonFrame(String _languageKey) {
        languageKey = _languageKey;
    }
    public String getLanguageKey() {
        return languageKey;
    }
    protected void setLanguageKey(String _language) {
        languageKey = _language;
    }
    public void dispose() {
        frame.dispose();
    }

    public void requestFocus() {
        frame.requestFocus();
    }

    public Point getLocation() {
        return frame.getLocation();
    }

    public void setLocation(int _x, int _y) {
        frame.setLocation(_x, _y);
    }

    public void setLocation(Point _p) {
        frame.setLocation(_p);
    }

    public int getWidth() {
        return frame.getWidth();
    }

    public int getHeight() {
        return frame.getHeight();
    }

    @Override
    public void pack() {
        frame.pack();
    }

    public void setFocusableWindowState(boolean _focusableWindowState) {
        frame.setFocusableWindowState(_focusableWindowState);
    }

    public void setFocusable(boolean _focusable) {
        frame.setFocusable(_focusable);
    }

    public void addWindowListener(WindowListener _l) {
        frame.addWindowListener(_l);
    }

    public void setDefaultCloseOperation(int _operation) {
        frame.setDefaultCloseOperation(_operation);
    }

    @Override
    protected Window getComponent() {
        return frame;
    }
    public void revalidateFrame() {
        PackingWindowAfter.pack(this);
    }

    protected void setIconImage(BufferedImage _image) {
        frame.setIconImage(_image);
    }

    protected String getAccessFile() {
        return accessFile;
    }

    protected void setAccessFile(String _accessFile) {
        accessFile = _accessFile;
    }
    
    public void setContentPane(Panel _contentPane) {
        frame.setContentPane(_contentPane.getComponent());
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

    protected MenuBar getJMenuBar() {
        return menuBar;
    }
    public void setJMenuBar(MenuBar _menu) {
        frame.setJMenuBar(_menu.getMenuBar());
        menuBar = _menu;
    }
    JFrame getFrame() {
        return frame;
    }
    @Override
    public Ownable getOwner() {
        return owner;
    }

    @Override
    public void setOwner(Ownable _owner) {
        owner = _owner;
    }

    public void setVisible(boolean _b) {
        frame.setVisible(_b);
    }

    public void setLocationRelativeTo(CustComponent _c) {
        if (_c != null) {
            frame.setLocationRelativeTo(_c.getComponent());
        } else {
            frame.setLocationRelativeTo(null);
        }
    }

    public void setLocationRelativeTo(CommonFrame _c) {
        if (_c != null) {
            frame.setLocationRelativeTo(_c.getComponent());
        } else {
            frame.setLocationRelativeTo(null);
        }
    }

    public void setLocationRelativeToNull() {
        frame.setLocationRelativeTo(null);
    }

    @Override
    public boolean isVisible() {
        return frame.isVisible();
    }

    @Override
    public String getTitle() {
        return frame.getTitle();
    }

    @Override
    public void setTitle(String _title) {
        frame.setTitle(_title);
    }

    public BufferedImage printAll() {
        int w_ = getWidth();
        int h_ = getHeight();
        BufferedImage b_ = new BufferedImage(w_, h_, BufferedImage.TYPE_INT_ARGB);
        Graphics g_ = b_.createGraphics();
        frame.printAll(g_);
        return b_;
    }
    public Point getLocationOnScreen() {
        return frame.getLocationOnScreen();
    }
}
