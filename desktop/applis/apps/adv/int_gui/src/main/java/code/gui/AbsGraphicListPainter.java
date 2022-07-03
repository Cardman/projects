package code.gui;

import code.expressionlanguage.structs.Struct;
import code.gui.images.AbstractImageFactory;

public interface AbsGraphicListPainter {
    void selectPaint(AbsGraphicListDefBase _list, boolean _sel, int _index);
    boolean selectOneAmongIntervalPaint(AbsGraphicListDefBase _list, boolean _sel, int _index);
    AbsPreparedLabel selectedOneAmongIntervalPaint(AbsGraphicListDefBase _list, boolean _sel, int _index);
    Interval selectIntervalPaint(AbsGraphicListDefBase _list, boolean _sel, int _index);
    Interval selectIntervalKeyPaint(AbsGraphicListDefBase _list, boolean _sel, int _index);
    void setValue(Struct _value);
    AbstractImageFactory getFact();
}
