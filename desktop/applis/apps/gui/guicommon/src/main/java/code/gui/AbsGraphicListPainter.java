package code.gui;

import code.expressionlanguage.structs.Struct;

public interface AbsGraphicListPainter {
    void selectPaint(AbsGraphicListDefBase _list, boolean _sel, int _index);
    boolean selectOneAmongIntervalPaint(AbsGraphicListDefBase _list, boolean _sel, int _index);
    void afterSelectOneAmongIntervalPaint(AbsGraphicListDefBase _list, boolean _sel, int _index);
    Interval selectIntervalPaint(AbsGraphicListDefBase _list, boolean _sel, int _index);
    Interval selectIntervalKeyPaint(AbsGraphicListDefBase _list, boolean _sel, int _index);
    void setValue(Struct _value);
}
