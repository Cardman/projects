package code.gui;

import code.gui.images.AbstractImageFactory;

public final class DefaultGraphicListPainter extends AbsGraphicListPainterImpl {
    private AbstractImageFactory fact;
    public DefaultGraphicListPainter(AbstractImageFactory _fact) {
        fact = _fact;
    }

    @Override
    public void selectPaint(AbsGraphicListDefBase _list, boolean _sel, int _index) {
        if (!_list.isEnabled()) {
            return;
        }
        _list.getSelectedIndexes().clear();
        if (_sel) {
            _list.getSelectedIndexes().add(_index);
        }
        int index_ = -1;
        if (_sel) {
            index_ = _index;
        }
        _list.setSelectedIndice(index_);
    }

    @Override
    public boolean selectOneAmongIntervalPaint(AbsGraphicListDefBase _list, boolean _sel, int _index) {
        return _list.selectOneAmongIntervalPaint(_sel, _index);
    }

    @Override
    public AbsPreparedLabel selectedOneAmongIntervalPaint(AbsGraphicListDefBase _list, boolean _sel, int _index) {
        return null;
    }

    @Override
    public Interval selectIntervalPaint(AbsGraphicListDefBase _list, boolean _sel, int _index) {
        return _list.selectIntervalPaint(_sel, _index);
    }

    @Override
    public Interval selectIntervalKeyPaint(AbsGraphicListDefBase _list, boolean _sel, int _index) {
        return _list.selectIntervalKeyPaint(_sel, _index);
    }

    @Override
    public AbstractImageFactory getFact() {
        return fact;
    }

}
