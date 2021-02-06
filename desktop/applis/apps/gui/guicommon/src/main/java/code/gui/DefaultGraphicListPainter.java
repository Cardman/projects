package code.gui;

import code.expressionlanguage.structs.Struct;

public final class DefaultGraphicListPainter implements AbsGraphicListPainter {
    @Override
    public void selectPaint(AbsBasicGraphicList _list, boolean _sel, int _index) {
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
    public boolean selectOneAmongIntervalPaint(AbsBasicGraphicList _list, boolean _sel, int _index) {
        return _list.selectOneAmongIntervalPaint(_sel, _index);
    }

    @Override
    public void afterSelectOneAmongIntervalPaint(AbsBasicGraphicList _list, boolean _sel, int _index) {
        //
    }

    @Override
    public Interval selectIntervalPaint(AbsBasicGraphicList _list, boolean _sel, int _index) {
        return _list.selectIntervalPaint(_sel, _index);
    }

    @Override
    public Interval selectIntervalKeyPaint(AbsBasicGraphicList _list, boolean _sel, int _index) {
        return _list.selectIntervalKeyPaint(_sel, _index);
    }

    @Override
    public void setValue(Struct _value) {

    }
}
