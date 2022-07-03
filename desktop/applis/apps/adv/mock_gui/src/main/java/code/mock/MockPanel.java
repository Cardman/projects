package code.mock;

import code.gui.*;
import code.gui.images.AbstractImageFactory;
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
    public void add(AbsClock _cl) {
        add(_cl.getComponent());
    }

    @Override
    public void add(AbsMetaLabelComInt _meta) {
        add(_meta.getPaintableLabel());
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
    public void add(AbsMetaLabelComInt _l, int _i) {
        add(_l.getPaintableLabel(),_i);
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
    public void add(AbsMetaLabelComInt _lab, String _s) {
        add(_lab.getPaintableLabel(),_s);
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

    @Override
    public void repaintSecondChildren(AbstractImageFactory _i) {
        for (AbsCustComponent c: getChildren()) {
            if (c instanceof MockPaintableLabel) {
                ((MockPaintableLabel) c).repaintLabel(_i);
            }
            if (c instanceof MockPanel) {
                for (AbsCustComponent d: c.getChildren()) {
                    if (d instanceof MockPaintableLabel) {
                        ((MockPaintableLabel) d).repaintLabel(_i);
                    }
                }
            }
        }
    }

    @Override
    public void repaintChildren(AbstractImageFactory _i) {
        for (AbsCustComponent c: getChildren()) {
            if (c instanceof MockPaintableLabel) {
                ((MockPaintableLabel) c).repaintLabel(_i);
            }
        }
    }
}
