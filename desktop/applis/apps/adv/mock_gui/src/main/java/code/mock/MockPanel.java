package code.mock;

import code.gui.*;
import code.util.CustList;

public final class MockPanel extends MockCustComponent implements AbsPanel {
    private final MockLayout layout;

    public MockPanel(MockLayout _l) {
        this.layout = _l;
    }

    public MockLayout getLayout() {
        return layout;
    }

    @Override
    public int getComponentCount() {
        return getChildren().size();
    }

    @Override
    public AbsCustComponent getComponent(int _i) {
        return getChildren().get(_i);
    }

    @Override
    public void add(AbsCustComponent _c) {
        if (_c.getParent() == null) {
            innerAdd(_c);
        }
    }

    @Override
    public void innerAdd(AbsCustComponent _c) {
        _c.setParent(this);
        innAdd(_c);
    }

    @Override
    public void add(AbsCustComponent _c, int _i) {
        if (_c.getParent() == null) {
            innerAdd(_c, _i);
        }
    }

    @Override
    public void innerAdd(AbsCustComponent _c, int _i) {
        _c.setParent(this);
        getChildren().add(_i, _c);
    }

    @Override
    public void add(AbsCustComponent _comp, AbsGridConstraints _constraints) {
        innerAdd(_comp);
    }

    @Override
    public void add(AbsCustComponent _c, String _s) {
        if (_c.getParent() == null) {
            innerAdd(_c, _s);
        }
    }

    @Override
    public void innerAdd(AbsCustComponent _c, String _s) {
        _c.setParent(this);
        getChildren().add(_c);
    }

    @Override
    public void remove(int _i) {
        CustList<AbsCustComponent> ch_ = getChildren();
        ch_.get(_i).setParent(null);
        ch_.remove(_i);
    }

    @Override
    public int remove(AbsCustComponent _c) {
        CustList<AbsCustComponent> ch_ = getChildren();
        int len_ = ch_.size();
        for (int i = 0; i < len_; i++) {
            if (ch_.get(i) == _c) {
                _c.setParent(null);
                ch_.remove(i);
                return i;
            }
        }
        return -1;
    }

    @Override
    public void innAdd(AbsCustComponent _c) {
        getChildren().add(_c);
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
        getChildren().clear();
    }

}
