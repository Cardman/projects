package code.gui;


import code.util.CustList;

public interface AbsGraphicListDefBase extends AbsGraphicListDef {
    boolean selectOneAmongIntervalPaint(boolean _sel, int _index);
    Interval selectIntervalKeyPaint(boolean _sel, int _index);
    Interval selectIntervalPaint(boolean _sel, int _index);
    Interval selectIntervalPaintBase(boolean _sel, int _index);
    void selectOneAmongIntervalPaintBase(boolean _sel, int _index);
    CustList<AbsPreparedLabel> getListComponents();
    void updateGraphics();
    void setListener(ListSelection _listener);
}
