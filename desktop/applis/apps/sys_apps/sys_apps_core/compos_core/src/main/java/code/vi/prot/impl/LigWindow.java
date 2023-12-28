package code.vi.prot.impl;

import code.gui.*;
import code.gui.events.AbsWindowListener;
import code.gui.events.AbsWindowListenerClosing;
import code.gui.images.AbstractImage;
import code.gui.images.MetaPoint;
import code.util.CustList;
import code.util.IdMap;
import code.vi.prot.impl.gui.CustComponent;
import code.vi.prot.impl.gui.MenuBar;
import code.vi.prot.impl.gui.Panel;
import code.vi.prot.impl.gui.events.WrWindowListener;

import javax.swing.*;

public abstract class LigWindow extends CustComponent implements ChangeableTitle, WithListener {
    private String title="";
    private final JRootPane center = new JRootPane();

    private final IdMap<AbsWindowListener, WrWindowListener> mapWindow = new IdMap<AbsWindowListener, WrWindowListener>();
    private boolean visible;
    private AbsPanel pane;
    private int locationFirst;
    private int locationSecond;
    protected LigWindow(AbsPanel _p) {
        pane = _p;
    }

    @Override
    public MetaPoint getLocationOnScreen() {
        return new MetaPoint(locationFirst,locationSecond);
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void setTitle(String _title) {
        this.title = _title;
    }
    @Override
    public void pack() {
        GuiBaseUtil.recalculate(this);
    }

    public void setContentPane(AbsPanel _contentPane) {
        getCenter().setContentPane(((Panel)_contentPane).getNatComponent());
        pane = _contentPane;
    }
    public void setLocationRelativeTo(AbsOtherDialog _a) {
        locationFirst = ((LigWindow)_a).getLocationOnScreen().getXcoord()+((LigWindow)_a).getWidth()/2;
        locationSecond = ((LigWindow)_a).getLocationOnScreen().getYcoord()+((LigWindow)_a).getHeight()/2;
    }

    public void setLocationRelativeTo(AbsCustComponent _c) {
        locationFirst = _c.getXcoords()+_c.getWidth()/2;
        locationSecond = _c.getYcoords()+_c.getHeight()/2;
    }

    public void setLocationRelativeTo(AbsOtherFrame _f) {
        locationFirst = ((LigWindow)_f).getLocationOnScreen().getXcoord()+((LigWindow)_f).getWidth()/2;
        locationSecond = ((LigWindow)_f).getLocationOnScreen().getYcoord()+((LigWindow)_f).getHeight()/2;
    }

    public void setLocationRelativeToNull() {
        locationFirst = 0;
        locationSecond = 0;
    }

    public void dispose() {
        setVisible(false);
    }

    public AbsPanel getContentPane() {
        return pane;
    }
    @Override
    public void addWindowListener(AbsWindowListener _l) {
        WrWindowListener wr_ = new WrWindowListener(_l);
        mapWindow.addEntry(_l,wr_);
    }

    @Override
    public AbsWindowListenerClosing addWindowListener(AbsWindowListenerClosing _l) {
        return _l;
    }

    @Override
    public void removeWindowListener(AbsWindowListener _l) {
        mapWindow.removeKey(_l);
    }

    @Override
    public AbsWindowListenerClosing removeWindowListener(AbsWindowListenerClosing _l) {
        return _l;
    }

    @Override
    public CustList<AbsWindowListener> getWindowListeners() {
        return mapWindow.getKeys();
    }

    @Override
    public void setJMenuBar(AbsMenuBar _menu) {
        center.setJMenuBar(((MenuBar)_menu).getMeBar());
    }
    @Override
    public void setVisible(boolean _v) {
        visible = _v;
    }

    @Override
    public boolean isVisible() {
        return visible;
    }

    public JRootPane getCenter() {
        return center;
    }

    @Override
    public JComponent getNatComponent() {
        return getCenter();
    }

    @Override
    public AbstractImage getImageIconFrame() {
        return null;
    }
}
