package code.vi.sys.impl.gui;

import code.gui.*;
import code.gui.events.AbsWindowListener;
import code.gui.events.AbsWindowListenerClosing;
import code.vi.prot.impl.gui.CustComponent;
import code.vi.prot.impl.gui.Panel;
import code.vi.prot.impl.gui.MenuBar;
import code.vi.prot.impl.gui.events.WrWindowListener;
import code.gui.images.AbstractImage;
import code.gui.images.MetaPoint;
import code.util.CustList;
import code.util.IdMap;
import code.vi.prot.impl.gui.events.WrWindowListenerClos;

import javax.swing.*;
import java.awt.*;

public final class OtherFrame implements AbsOtherFrame, ChangeableTitle,PlacableWindow {

    private AbstractImage imageIconFrame;

    private boolean mainFrame;

    private Ownable owner;
    private final JFrame frame = new JFrame();
    private final IdMap<AbsWindowListener, WrWindowListener> mapWindow = new IdMap<AbsWindowListener, WrWindowListener>();
    private final IdMap<AbsWindowListenerClosing, WrWindowListenerClos> mapWindowDef = new IdMap<AbsWindowListenerClosing, WrWindowListenerClos>();

    public OtherFrame() {
        frame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
    }

    public void setMainFrame(boolean _mainFrame) {
        mainFrame = _mainFrame;
    }

    @Override
    public MetaPoint getLocationOnScreen() {
        Point pt_ = frame.getLocationOnScreen();
        return new MetaPoint(pt_.x, pt_.y);
    }
    @Override
    public String getTitle() {
        return frame.getTitle();
    }

    @Override
    public void setTitle(String _title) {
        frame.setTitle(_title);
    }
    public void dispose() {
        setVisible(false);
    }

    public boolean isMainFrame() {
        return mainFrame;
    }

    @Override
    public AbstractImage getImageIconFrame() {
        return imageIconFrame;
    }

    public void setFocusableWindowState(boolean _focusableWindowState) {
        frame.setFocusableWindowState(_focusableWindowState);
    }

    public void setFocusable(boolean _focusable) {
        frame.setFocusable(_focusable);
    }

    @Override
    public void addWindowListener(AbsWindowListener _l) {
        WrWindowListener wr_ = new WrWindowListener(_l);
        mapWindow.addEntry(_l,wr_);
        frame.addWindowListener(wr_);
    }

    @Override
    public AbsWindowListenerClosing addWindowListener(AbsWindowListenerClosing _l) {
        WrWindowListenerClos wr_ = new WrWindowListenerClos(_l);
        frame.addWindowListener(wr_);
        mapWindowDef.addEntry(_l,wr_);
        return _l;
    }
    @Override
    public void removeWindowListener(AbsWindowListener _l) {
        WrWindowListener wr_ = mapWindow.getVal(_l);
        frame.removeWindowListener(wr_);
        mapWindow.removeKey(_l);
    }

    @Override
    public AbsWindowListenerClosing removeWindowListener(AbsWindowListenerClosing _l) {
        WrWindowListenerClos wr_ = mapWindowDef.getVal(_l);
        frame.removeWindowListener(wr_);
        return _l;
    }
    @Override
    public CustList<AbsWindowListener> getWindowListeners() {
        return mapWindow.getKeys();
    }

    @Override
    public void setVisible(boolean _v) {
        frame.setVisible(_v);
    }
    public void setJMenuBar(AbsMenuBar _menu) {
        frame.setJMenuBar(((MenuBar) _menu).getMeBar());
    }
    @Override
    public void setContentPane(AbsPanel _p) {
        frame.setContentPane(((Panel)_p).getNatComponent());
    }

    @Override
    public void setLocationRelativeTo(AbsCustComponent _c) {
        frame.setLocationRelativeTo(((CustComponent)_c).getNatComponent());
    }

    @Override
    public void setLocationRelativeTo(AbsOtherDialog _c) {
        frame.setLocationRelativeTo(((OtherDialog)_c).getComponent());
    }

    @Override
    public void setLocationRelativeTo(AbsOtherFrame _c) {
        frame.setLocationRelativeTo(((OtherFrame)_c).getComponent());
    }

    @Override
    public void setLocationRelativeToNull() {
        frame.setLocationRelativeTo(null);
    }

    @Override
    public boolean isVisible() {
        return frame.isVisible();
    }

    @Override
    public void pack() {
        frame.pack();
    }

    @Override
    public Ownable getOwner() {
        return owner;
    }

    @Override
    public void setOwner(Ownable _owner) {
        owner = _owner;
    }

    Window getComponent() {
        return frame;
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
}
