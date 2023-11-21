package code.vi.prot.impl.gui;

import code.gui.AbsCustComponent;
import code.gui.AbsTabbedPane;
import code.gui.FrameUtil;
import code.gui.GuiBaseUtil;
import code.gui.events.AbsChangeListener;
import code.util.CustList;
import code.util.IdMap;
import code.vi.prot.impl.gui.events.WrChangeListener;

import javax.swing.JComponent;
import javax.swing.JTabbedPane;

public final class TabbedPane extends CustComponent implements AbsTabbedPane {

    private final JTabbedPane component = new JTabbedPane();
    private final IdMap<AbsChangeListener,WrChangeListener> evtsMap = new IdMap<AbsChangeListener, WrChangeListener>();
    private final IdMap<AbsChangeListener,WrChangeListener> evts = new IdMap<AbsChangeListener, WrChangeListener>();

    public void addChangeListener(AbsChangeListener _l) {
        WrChangeListener wr_ = new WrChangeListener(_l);
        evts.addEntry(_l,wr_);
        evtsMap.addEntry(_l,wr_);
    }

    @Override
    public void addChangeListenerMap(AbsChangeListener _l) {
        WrChangeListener wr_ = new WrChangeListener(_l);
        evtsMap.addEntry(_l,wr_);
    }

    @Override
    public void removeChangeListener(AbsChangeListener _list) {
        evts.removeKey(_list);
        evtsMap.removeKey(_list);
    }

    @Override
    public void removeChangeListenerMap(AbsChangeListener _list) {
        evtsMap.removeKey(_list);
    }

    @Override
    public CustList<AbsChangeListener> getChangeListeners() {
        return evts.getKeys();
    }

    public int getComponentCount() {
        return getChildren().size();
    }

    public int getSelectedIndex() {
        return component.getSelectedIndex();
    }

    @Override
    public void events() {
        GuiBaseUtil.stateChanged(this);
    }

    public void selectIndex(int _index) {
        int pr_ = component.getSelectedIndex();
        component.setSelectedIndex(_index);
        GuiBaseUtil.stateChanged(this,pr_,_index);
    }

    public void add(String _title, AbsCustComponent _component) {
        FrameUtil.added(_title, _component,this);
    }

    public void addIntTab(String _title, AbsCustComponent _component) {
        addIntTab(_title,_component,"");
    }

    @Override
    public void addIntTab(String _title, AbsCustComponent _component, String _tooltip) {
        _component.setParent(this);
        getChildren().add(_component);
        int pr_ = component.getSelectedIndex();
        component.addTab(_title, null,((CustComponent) _component).getNatComponent(),_tooltip);
        GuiBaseUtil.stateChanged(this,pr_,component.getSelectedIndex());
    }

    public boolean setTab(int _index,AbsCustComponent _component) {
        try {
            return FrameUtil.setTab(_index, _component,this);
        } catch (Exception e) {
            return false;
        }
    }

    public void setTabComponentAt(int _index, AbsCustComponent _component) {
        getChildren().get(_index).setParent(null);
        _component.setParent(this);
        getChildren().set(_index, _component);
        int pr_ = component.getSelectedIndex();
        component.setTabComponentAt(_index,((CustComponent) _component).getNatComponent());
        GuiBaseUtil.stateChanged(this,pr_,component.getSelectedIndex());
    }

    public String getTitle(int _index) {
        try {
            return getTitleAt(_index);
        } catch (Exception e) {
            return "";
        }
    }

    public String getTitleAt(int _index) {
        return component.getTitleAt(_index);
    }

    @Override
    public String getToolTipAt(int _index) {
        return component.getToolTipTextAt(_index);
    }

    public boolean setTitle(int _index, String _title) {
        try {
            setTitleAt(_index, _title);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void setTitleAt(int _index, String _title) {
        component.setTitleAt(_index, _title);
    }

    @Override
    public void setToolTipAt(int _index, String _title) {
        component.setToolTipTextAt(_index, _title);
    }

    public void remove(int _index) {
        getChildren().get(_index).setParent(null);
        getChildren().remove(_index);
        int pr_ = component.getSelectedIndex();
        component.removeTabAt(_index);
        GuiBaseUtil.stateChanged(this,pr_,component.getSelectedIndex());
    }

    @Override
    public JComponent getNatComponent() {
        return component;
    }

    public void removeAll() {
        FrameUtil.remAllFromPanel(getChildren());
        innerRemoveAll();
    }

    public void innerRemoveAll() {
        getChildren().clear();
        int pr_ = component.getSelectedIndex();
        component.removeAll();
        GuiBaseUtil.stateChanged(this,pr_,component.getSelectedIndex());
    }

}
