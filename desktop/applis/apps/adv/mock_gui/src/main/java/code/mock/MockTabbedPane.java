package code.mock;

import code.gui.AbsCustComponent;
import code.gui.AbsTabbedPane;
import code.util.StringList;

public final class MockTabbedPane extends MockCustComponent implements AbsTabbedPane {
    private int selectedIndex;
    private final StringList titles = new StringList();
    private final StringList tooltips = new StringList();
    @Override
    public int getComponentCount() {
        return getChildren().size();
    }

    @Override
    public int getSelectedIndex() {
        return selectedIndex;
    }

    @Override
    public void setSelectedIndex(int _i) {
        if (getChildren().isValidIndex(_i)) {
            selectIndex(_i);
        }
    }

    @Override
    public void selectIndex(int _i) {
        selectedIndex = _i;
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
        getChildren().get(_i).setParent(null);
        _c.setParent(this);
        getChildren().set(_i, _c);
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
        getChildren().get(_i).setParent(null);
        getChildren().remove(_i);
        titles.remove(_i);
        tooltips.remove(_i);
    }

    @Override
    public void removeAll() {
        for (AbsCustComponent a: getChildren()) {
            a.setParent(null);
        }
        innerRemoveAll();
    }

    @Override
    public void innerRemoveAll() {
        titles.clear();
        tooltips.clear();
        getChildren().clear();
    }
}
