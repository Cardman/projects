package code.vi.sys.impl.gui;

import code.gui.*;
import code.gui.events.AbsWindowListener;
import code.vi.prot.impl.gui.CustComponent;
import code.vi.prot.impl.gui.Panel;
import code.vi.prot.impl.gui.MenuBar;
import code.vi.sys.impl.gui.events.WrWindowListener;
import code.gui.images.AbstractImage;
import code.gui.images.MetaPoint;
import code.gui.initialize.AbstractProgramInfos;
import code.vi.prot.impl.DefImage;
import code.vi.prot.impl.DefImageFactory;
import code.util.CustList;
import code.util.IdMap;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;

public final class CommonFrame implements AbsCommonFrame {

    private final AbstractProgramInfos frames;
    private String accessFile;

    private AbsPanel contentPane = Panel.newLineBox();

    private Ownable owner;
    private final JFrame frame = new JFrame();
    private AbsMenuBar menuBar;
    private String languageKey;
    private final IdMap<AbsWindowListener, WrWindowListener> mapWindow = new IdMap<AbsWindowListener, WrWindowListener>();
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

    @Override
    public int getLocationFirst() {
        return frame.getLocation().x;
    }

    @Override
    public int getLocationSecond() {
        return frame.getLocation().y;
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
        frame.setDefaultCloseOperation(GuiConstants.EXIT_ON_CLOSE);
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

    public void setContentPane(AbsPanel _contentPane) {
        frame.setContentPane(((Panel)_contentPane).getNatComponent());
        contentPane = _contentPane;
    }

    public void setContentPane(AbsScrollPane _contentPane) {
        Panel p_ = Panel.newLineBox();
        p_.add(_contentPane);
        setContentPane(p_);
    }

    public AbsPanel getPane() {
        return contentPane;
    }

    public AbsMenuBar getJMenuBar() {
        return menuBar;
    }
    public void setJMenuBar(AbsMenuBar _menu) {
        frame.setJMenuBar(((MenuBar)_menu).getMeBar());
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

    public void setLocationRelativeTo(AbsCustComponent _c) {
        frame.setLocationRelativeTo(((CustComponent)_c).getNatComponent());
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
    public MetaPoint getLocationOnScreen() {
        Point pt_ = frame.getLocationOnScreen();
        return new MetaPoint(pt_.x, pt_.y);
    }
}
