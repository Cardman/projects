package code.gui;

import code.expressionlanguage.structs.Struct;

public interface AbsGraphicListPainter {
    void selectPaint(AbsBasicGraphicList _list, boolean _sel, int _index);
    boolean selectOneAmongIntervalPaint(AbsBasicGraphicList _list, boolean _sel, int _index);
    void afterSelectOneAmongIntervalPaint(AbsBasicGraphicList _list, boolean _sel, int _index);
    Interval selectIntervalPaint(AbsBasicGraphicList _list, boolean _sel, int _index);
    Interval selectIntervalKeyPaint(AbsBasicGraphicList _list, boolean _sel, int _index);
    void setValue(Struct _value);
}
