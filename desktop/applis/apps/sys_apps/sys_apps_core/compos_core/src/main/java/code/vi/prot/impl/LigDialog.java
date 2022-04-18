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

public final class LigDialog implements AbsOtherDialog, ChangeableTitle {
    private Ownable owner;
    private boolean visible;
    private boolean modal;
    private String title="";

    private final IdMap<AbsWindowListener, WrWindowListener> mapWindow = new IdMap<AbsWindowListener, WrWindowListener>();
    private AbsPanel contentPane = Panel.newGrid(0,1);

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void setTitle(String _title) {
        this.title = _title;
    }

    @Override
    public MetaPoint getLocationOnScreen() {
        return new MetaPoint(0,0);
    }

    @Override
    public AbstractImage getImageIconFrame() {
        return null;
    }

    @Override
    public void setLocationRelativeTo(AbsCustComponent _c) {
        //
    }

    @Override
    public void setLocationRelativeTo(AbsOtherFrame _c) {
        //
    }

    @Override
    public void setLocationRelativeTo(AbsOtherDialog _c) {
        //
    }

    @Override
    public void setLocationRelativeToNull() {
        //
    }

    @Override
    public boolean isVisible() {
        return visible;
    }

    @Override
    public void dispose() {
        setVisible(false);
    }

    public void setVisible(boolean _b) {
        visible = _b;
    }
    public boolean isModal() {
        return modal;
    }
    public void setModal(boolean _modal) {
        this.modal = _modal;
    }
    @Override
    public void pack() {
        FrameUtil.recalculate(contentPane);
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

    public void setContentPane(AbsPanel _contentPane) {
        contentPane = _contentPane;
    }

    @Override
    public AbsPanel getContentPane() {
        return contentPane;
    }

    @Override
    public CustList<AbsWindowListener> getWindowListeners() {
        return mapWindow.getKeys();
    }
    public void setDefaultCloseOperation(int _operation) {
        //
    }

    @Override
    public Ownable getOwner() {
        return owner;
    }

    @Override
    public void setOwner(Ownable _owner) {
        owner = _owner;
    }

    public void setJMenuBar(AbsMenuBar _menuBar) {
        //
    }
}

