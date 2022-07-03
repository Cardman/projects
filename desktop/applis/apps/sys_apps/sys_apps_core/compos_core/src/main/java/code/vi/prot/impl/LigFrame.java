package code.vi.prot.impl;

import code.gui.*;
import code.gui.events.AbsWindowListener;
import code.gui.events.AbsWindowListenerClosing;
import code.gui.images.AbstractImage;
import code.gui.images.MetaPoint;
import code.util.CustList;
import code.util.IdMap;
import code.vi.prot.impl.gui.Panel;
import code.vi.prot.impl.gui.events.WrWindowListener;


public final class LigFrame implements AbsOtherFrame, ChangeableTitle {

    private boolean mainFrame;
    private boolean visible;
    private String title="";

    private AbsPanel pane = Panel.newLineBox();
    private Ownable owner;
    private final IdMap<AbsWindowListener, WrWindowListener> mapWindow = new IdMap<AbsWindowListener, WrWindowListener>();

    public void setMainFrame(boolean _mainFrame) {
        mainFrame = _mainFrame;
    }

    @Override
    public MetaPoint getLocationOnScreen() {
        return new MetaPoint(0,0);
    }
    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void setTitle(String _title) {
        this.title = _title;
    }
    public void dispose() {
        setVisible(false);
    }

    public boolean isMainFrame() {
        return mainFrame;
    }

    @Override
    public AbstractImage getImageIconFrame() {
        return null;
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
    public void setDefaultCloseOperation(int _option) {
        //
    }

    @Override
    public void setVisible(boolean _v) {
        visible = _v;
    }
    public void setJMenuBar(AbsMenuBar _menu) {
        //
    }
    @Override
    public void setContentPane(AbsPanel _p) {
        pane = _p;
    }

    @Override
    public boolean isVisible() {
        return visible;
    }

    @Override
    public void pack() {
        GuiBaseUtil.recalculate(pane);
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
