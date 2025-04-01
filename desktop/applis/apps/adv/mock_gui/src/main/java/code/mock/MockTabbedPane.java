package code.mock;

import code.gui.AbsCustComponent;
import code.gui.AbsTabbedPane;
import code.gui.events.AbsChangeListener;
import code.util.CustList;
import code.util.IdList;
import code.util.StringList;

public final class MockTabbedPane extends MockCustComponent implements AbsTabbedPane {
    private final IdList<AbsChangeListener> changeListeners = new IdList<AbsChangeListener>();
    private int selectedIndex;
    private final StringList titles = new StringList();
    private final StringList tooltips = new StringList();

    @Override
    public void addChangeListener(AbsChangeListener _l) {
        addChangeListenerMap(_l);
    }

    @Override
    public void addChangeListenerMap(AbsChangeListener _list) {
        getChangeListeners().add(_list);
    }

    @Override
    public void removeChangeListener(AbsChangeListener _list) {
        removeChangeListenerMap(_list);
    }

    @Override
    public void removeChangeListenerMap(AbsChangeListener _list) {
        changeListeners.removeObj(_list);
    }

    public CustList<AbsChangeListener> getChangeListeners() {
        return changeListeners;
    }
    @Override
    public int getComponentCount() {
        return getChildren().size();
    }

    @Override
    public int getSelectedIndex() {
        return selectedIndex;
    }

    public void setSelectedIndex(int _i) {
        if (getChildren().isValidIndex(_i)) {
            selectIndex(_i);
        }
    }

    @Override
    public void selectIndex(int _i) {
        int old_ = selectedIndex;
        selectedIndex = _i;
        if (old_ != _i) {
            events();
        }
    }

    @Override
    public void events() {
        stateChanged();
    }

    private void stateChanged() {
        for (AbsChangeListener c: changeListeners) {
            c.stateChanged();
        }
    }

    @Override
    public void add(String _s, AbsCustComponent _c) {
        if (_c.getParent() == null) {
            addIntTab(_s, _c);
        }
    }

    @Override
    public void addIntTab(String _s, AbsCustComponent _c) {
        addIntTab(_s,_c,"");
    }

    @Override
    public void addIntTab(String _s, AbsCustComponent _c, String _tooltip) {
        _c.setParent(this);
        getChildren().add(_c);
        titles.add(_s);
        tooltips.add(_tooltip);
        if (getChildren().size() == 1) {
            selectIndex(0);
        }
    }

    @Override
    public boolean setTab(int _i, AbsCustComponent _c) {
        if (_c.getParent() == null) {
            setTabComponentAt(_i, _c);
            return true;
        }
        return false;
    }

    @Override
    public void setTabComponentAt(int _i, AbsCustComponent _c) {
        if (getChildren().isValidIndex(_i)) {
            getChildren().get(_i).setParent(null);
            _c.setParent(this);
            getChildren().set(_i, _c);
        } else {
            _c.setParent(this);
            getChildren().add(_c);
        }
    }

    @Override
    public String getTitle(int _i) {
        return getTitleAt(_i);
    }

    @Override
    public String getTitleAt(int _i) {
        return titles.get(_i);
    }

    @Override
    public String getToolTipAt(int _index) {
        return tooltips.get(_index);
    }

    @Override
    public boolean setTitle(int _i, String _s) {
        setTitleAt(_i, _s);
        return true;
    }

    @Override
    public void setTitleAt(int _i, String _s) {
        titles.set(_i,_s);
    }

    @Override
    public void setToolTipAt(int _index, String _title) {
        tooltips.set(_index,_title);
    }

    @Override
    public void remove(int _i) {
        int select_ = selectedIndex;
        getChildren().get(_i).setParent(null);
        getChildren().remove(_i);
        titles.remove(_i);
        tooltips.remove(_i);
        if (select_ > _i) {
            selectIndex(select_-1);
        } else if (select_ >= getChildren().size()) {
            selectIndex(select_-1);
        } else if (_i == select_) {
            events();
        }
    }

    @Override
    public void removeAll() {
        for (AbsCustComponent a: getChildren()) {
            a.setParent(null);
        }
        innerRemoveAll();
        selectedIndex=-1;
    }

//    @Override
    public void innerRemoveAll() {
        titles.clear();
        tooltips.clear();
        getChildren().clear();
    }
}
