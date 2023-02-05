package code.expressionlanguage.gui.unit;

import code.expressionlanguage.ContextEl;

public interface ProgTestBarInt {

    void setCalls(long _c);
    void setMax(int _m);

    void setMin(int _m);

    void achieve();

    void setCurrent(int _v);

    void setCurrentMethod(String _c);

    void setDoneTestsCount(String _d);

    void setValueAt(String _v, int _i, int _j);

    String success();

    String fail();

    void add(ResTestRow _res, ContextEl _ctx);
}
