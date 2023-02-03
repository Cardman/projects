package code.expressionlanguage.gui.unit;

import code.util.CustList;
import code.util.StringMap;

public interface ProgTestBarInt {

    StringMap<String> getMessages();
    int getMax();

    void setMax(int _m);

    int getMin();

    void setMin(int _m);

    int getCurrent();

    void setCurrent(int _v);

    String getCurrentMethod();

    void setCurrentMethod(String _c);

    String getDoneTestsCount();

    void setDoneTestsCount(String _d);

    CustList<ResTestRow> getResults();

    void setResults(CustList<ResTestRow> _r);

    void setRowCount(int _len);

    void setValueAt(String _v, int _i, int _j);

    void success(int _ind);

    void fail(int _ind);

    void append(String _str);
}
