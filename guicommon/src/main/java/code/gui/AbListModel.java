package code.gui;
import code.util.CustList;
import code.util.Numbers;


public abstract class AbListModel<T> extends CustListModel<T> {

    public AbListModel() {
    }

    public void removeAllObj(T _obj) {
        //setModified();
        while (containsObj(_obj)) {
            removeObj(_obj);
        }
    }

    public void removeObj(T _obj) {
        int index_ = indexOfObj(_obj);
        if (index_ == CustList.INDEX_NOT_FOUND_ELT) {
            return;
        }
        removeElementAt(index_);
    }

    public CustList<T> intersect(CustList<T> _list) {
        CustList<T> list_ = new CustList<T>();
        for (T s: _list) {
            if (containsObj(s)) {
                //_list.containsObj(s)
                list_.add(s);
            }
        }
        return list_;
    }

    public boolean containsObj(T _obj) {
//        return !indexesOfObj(_obj).isEmpty();
        return indexOfObj(_obj) != INDEX_NOT_FOUND_ELT;
    }

    public int indexOfObj(T _obj) {
        return indexOfObj(_obj, FIRST_INDEX);
//        Numbers<Integer> indexes_;
//        indexes_ = indexesOfObj(_obj);
//        if (indexes_.isEmpty()) {
//            return INDEX_NOT_FOUND_ELT;
//        }
//        return indexes_.first();
    }

    public int lastIndexOfObj(T _obj) {
        Numbers<Integer> indexes_;
        indexes_ = indexesOfObj(_obj);
        if (indexes_.isEmpty()) {
            return INDEX_NOT_FOUND_ELT;
        }
        return indexes_.last();
    }

    public Numbers<Integer> indexesOfObj(T _element) {
        Numbers<Integer> indexes_;
        indexes_ = new Numbers<Integer>();
        int i_ = FIRST_INDEX;
        while (true) {
            int found_ = indexOfObj(_element, i_);
            if (found_ == INDEX_NOT_FOUND_ELT) {
                break;
            }
            indexes_.add(found_);
            i_ = found_ + 1;
        }
        return indexes_;
    }
    public abstract int indexOfObj(T _element, int _from);
}
