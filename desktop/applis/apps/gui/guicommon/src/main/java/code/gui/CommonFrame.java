package code.gui;
import code.gui.events.AbsWindowListener;
import code.gui.events.WrWindowListener;
import code.gui.images.AbstractImage;
import code.gui.initialize.AbstractProgramInfos;
import code.util.CustList;
import code.util.IdMap;

import java.awt.*;
import java.awt.event.WindowEvent;

import javax.swing.*;

public abstract class CommonFrame implements ChangeableTitle {

    private String accessFile;

    private Panel contentPane = Panel.newLineBox();

    private Ownable owner;
    private final JFrame frame = new JFrame();
    private MenuBar menuBar;
    private String languageKey;
    private final IdMap<AbsWindowListener, WrWindowListener> mapWindow = new IdMap<AbsWindowListener, WrWindowListener>();
    protected CommonFrame(String _languageKey) {
        languageKey = _languageKey;
    }
    protected abstract AbstractProgramInfos getFrames();
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

    public void addWindowListener(AbsWindowListener _l) {
        WrWindowListener wr_ = new WrWindowListener(_l);
        frame.addWindowListener(wr_);
        mapWindow.addEntry(_l,wr_);
    }
    public void removeWindowListener(AbsWindowListener _l) {
        WrWindowListener wr_ = mapWindow.getVal(_l);
        frame.removeWindowListener(wr_);
        mapWindow.removeKey(_l);
    }

    public CustList<AbsWindowListener> getWindowListeners() {
        return mapWindow.getKeys();
    }
    public void dispatchExit() {
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
    }
    public void setDefaultCloseOperation(int _operation) {
        frame.setDefaultCloseOperation(_operation);
    }

    protected void setIconImage(AbstractImage _image) {
        frame.setIconImage(((ImageIcon)PreparedLabel.buildIcon(getFrames().getImageFactory(),_image)).getImage());
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
    protected JFrame getFrame() {
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
        frame.setLocationRelativeTo(_c.getComponent());
    }

    public void setLocationRelativeTo(CommonFrame _c) {
        frame.setLocationRelativeTo(_c.frame);
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

//    public AbstractImage printAll() {
//        int w_ = getWidth();
//        int h_ = getHeight();
//        AbstractImage b_ = new BufferedImage(w_, h_, BufferedImage.TYPE_INT_ARGB);
//        Graphics g_ = b_.createGraphics();
//        frame.printAll(g_);
//        return b_;
//    }
    public Point getLocationOnScreen() {
        return frame.getLocationOnScreen();
    }
}
