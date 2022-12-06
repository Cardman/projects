package code.vi.prot.impl;

import code.gui.AbsMenuBar;
import code.gui.ChangeableTitle;
import code.gui.Ownable;
import code.gui.WithListener;
import code.gui.events.AbsWindowListener;
import code.gui.events.AbsWindowListenerClosing;
import code.util.CustList;
import code.util.IdMap;
import code.vi.prot.impl.gui.MenuBar;
import code.vi.prot.impl.gui.events.WrWindowListener;

import javax.swing.*;

public abstract class LigWindow implements ChangeableTitle, WithListener {
    private String title="";
    private final JRootPane center = new JRootPane();

    private final IdMap<AbsWindowListener, WrWindowListener> mapWindow = new IdMap<AbsWindowListener, WrWindowListener>();
    private boolean visible;
    private Ownable owner;
    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void setTitle(String _title) {
        this.title = _title;
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

    @Override
    public Ownable getOwner() {
        return owner;
    }

    @Override
    public void setOwner(Ownable _owner) {
        owner = _owner;
    }

    public JRootPane getCenter() {
        return center;
    }
}
