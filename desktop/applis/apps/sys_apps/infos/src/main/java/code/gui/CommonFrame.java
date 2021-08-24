package code.gui;

import code.gui.events.AbsWindowListener;
import code.gui.events.WrWindowListener;
import code.gui.images.AbstractImage;
import code.gui.initialize.AbstractProgramInfos;
import code.sys.impl.DefImage;
import code.sys.impl.DefImageFactory;
import code.util.CustList;
import code.util.IdMap;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;

public final class CommonFrame implements AbsCommonFrame {

    private String accessFile;

    private Panel contentPane = Panel.newLineBox();

    private Ownable owner;
    private final JFrame frame = new JFrame();
    private MenuBar menuBar;
    private String languageKey;
    private final IdMap<AbsWindowListener, WrWindowListener> mapWindow = new IdMap<AbsWindowListener, WrWindowListener>();
    private final AbstractProgramInfos frames;
    private AbstractImage imageIconFrame;
    public CommonFrame(String _languageKey, AbstractProgramInfos _frames, AbstractImage _imageIconFrame) {
        languageKey = _languageKey;
        frames = _frames;
        imageIconFrame = _imageIconFrame;
    }

    @Override
    public AbstractImage getImageIconFrame() {
        return imageIconFrame;
    }

    public void setImageIconFrame(AbstractImage _imageIconFrame) {
        imageIconFrame = _imageIconFrame;
    }

    public AbstractProgramInfos getFrames(){
        return frames;
    }
    public String getLanguageKey() {
        return languageKey;
    }
    public void setLanguageKey(String _language) {
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

    public void setIconImage(AbstractImage _image) {
        frame.setIconImage(DefImageFactory.icon((DefImage) _image).getImage());
    }

    public String getAccessFile() {
        return accessFile;
    }

    public void setAccessFile(String _accessFile) {
        accessFile = _accessFile;
    }

    public void setContentPane(Panel _contentPane) {
        frame.setContentPane(_contentPane.getNatComponent());
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

    public MenuBar getJMenuBar() {
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
        frame.setLocationRelativeTo(_c.getNatComponent());
    }

    @Override
    public void setLocationRelativeTo(AbsGroupFrame _c) {
        frame.setLocationRelativeTo(((CommonFrame)_c.getCommonFrame()).frame);
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
