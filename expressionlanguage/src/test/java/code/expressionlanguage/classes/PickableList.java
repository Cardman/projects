package code.expressionlanguage.classes;
import code.util.CustList;

public class PickableList {

    private final CustList<Object> list = new CustList<Object>();

    public boolean removeAndExistAfter(int _i) {
        if (_i < list.size()) {
            list.remove(_i);
            return true;
        }
        return false;
    }

    public CustList<Object> getList() {
        return list;
    }
}
