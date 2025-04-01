package code.vi.sys.impl.gui;

import code.gui.*;
import code.gui.events.AbsWindowListenerClosing;
import code.gui.images.AbstractImage;
import code.gui.images.MetaPoint;
import code.util.CustList;
import code.util.IdMap;
import code.vi.prot.impl.DefImage;
import code.vi.prot.impl.DefImageFactory;
import code.vi.prot.impl.gui.CustComponent;
import code.vi.prot.impl.gui.MenuBar;
import code.vi.prot.impl.gui.Panel;
import code.vi.prot.impl.gui.events.WrWindowListenerClos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;

public final class CommonFrame implements AbsCommonFrame,Packable {

    //    private String accessFile;

    private AbsPanel contentPane = Panel.newLineBox();

    private final JFrame frame = new JFrame();
    private AbsMenuBar menuBar;
    private final IdMap<AbsWindowListenerClosing, WrWindowListenerClos> mapWindowDef = new IdMap<AbsWindowListenerClosing, WrWindowListenerClos>();
    private AbstractImage imageIconFrame;
    public CommonFrame() {
        frame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
    }

    @Override
    public AbstractImage getImageIconFrame() {
        return imageIconFrame;
    }

    public void setImageIconFrame(AbstractImage _imageIconFrame) {
        imageIconFrame = _imageIconFrame;
    }

    public void dispose() {
        frame.dispose();
    }

    public void requestFocus() {
        frame.requestFocusInWindow();
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

    @Override
    public AbsWindowListenerClosing addWindowListener(AbsWindowListenerClosing _l) {
        WrWindowListenerClos wr_ = new WrWindowListenerClos(_l);
        frame.addWindowListener(wr_);
        mapWindowDef.addEntry(_l,wr_);
        return _l;
    }

    @Override
    public AbsWindowListenerClosing removeWindowListener(AbsWindowListenerClosing _l) {
        WrWindowListenerClos wr_ = mapWindowDef.getVal(_l);
        frame.removeWindowListener(wr_);
        mapWindowDef.removeKey(_l);
        return _l;
    }

    @Override
    public CustList<AbsWindowListenerClosing> getWindowListenersDef() {
        return mapWindowDef.getKeys();
    }

    public void dispatchExit() {
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
    }

    public void setIconImage(AbstractImage _image) {
        frame.setIconImage(DefImageFactory.icon((DefImage) _image).getImage());
    }

//    public String getAccessFile() {
//        return accessFile;
//    }
//
//    public void setAccessFile(String _accessFile) {
//        accessFile = _accessFile;
//    }

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

    public void setVisible(boolean _b) {
        frame.setVisible(_b);
    }

    public void setLocationRelativeTo(AbsCustComponent _c) {
        frame.setLocationRelativeTo(((CustComponent)_c).getNatComponent());
    }

    @Override
    public void setLocationRelativeTo(AbsCommonFrame _c) {
        frame.setLocationRelativeTo(((CommonFrame)_c).frame);
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
