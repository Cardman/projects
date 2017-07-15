package code.util;
import code.util.ints.Listable;

public abstract class AbEqList<T> extends CustList<T> {

    public AbEqList() {
    }

    public AbEqList(Listable<? extends T> _c) {
        super(_c);
    }

    public AbEqList(T... _elements) {
        super(_elements);
    }

    public void removeAllElements(Listable<? extends T> _c) {
        for (T s: _c) {
            if (containsObj(s)) {
                //_list.containsObj(s)
                removeAllObj(s);
            }
        }
    }

//    public boolean removeAll(Listable<? extends T> _c) {
//        boolean r_ = false;
//        for (T s: _c) {
//            if (containsObj(s)) {
//                r_ = true;
//                //_list.containsObj(s)
//                removeAllObj(s);
//            }
//        }
//        return r_;
//    }
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
        removeAt(index_);
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
    
    public boolean containsAllObj(Listable<? extends T> _list) {
        for (T e: _list) {
            if (!containsObj(e)) {
                return false;
            }
        }
        return true;
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

//    protected boolean areDuplicates(int _i, int _j) {
//        if (_i == _j) {
//            return false;
//        }
//        T one_ = get(_i);
//        T two_ = get(_j);
//        return indexOfObj(one_) == indexOfObj(two_);
//    }

    public void removeDuplicates()  {
        //setModified();
        int i_ = FIRST_INDEX;
        while (true) {
            if(i_ >= size()) {
                break;
            }
            T e_ = get(i_);
            boolean rem_ = false;
            int next_ = indexOfObj(e_, i_ + 1);
            while (next_ != INDEX_NOT_FOUND_ELT) {
                removeAt(next_);
                rem_ = true;
                next_ = indexOfObj(e_, next_ + 1);
            }
            if (!rem_) {
                i_++;
            }
//            if (next_ == INDEX_NOT_FOUND_ELT) {
//                i_++;
//            } else {
//                removeAt(next_);
//            }
//            int j_ = i_ + 1;
//            while (true) {
//                if (j_ >= size()) {
//                    break;
//                }
//                if (areDuplicates(i_, j_)) {
//                    removeAt(j_);
//                } else {
//                    j_++;
//                }
////                if (get(i_) == null) {
////                    if (get(j_) == null) {
////                        removeAt(j_);
////                    } else {
////                        j_++;
////                    }
////                    continue;
////                }
////                if (eq(get(i_),get(j_))) {
////                    removeAt(j_);
////                } else {
////                    j_++;
////                }
//            }
//            i_++;
        }
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

    public abstract AbEqList<T> subAbEq(int _from, int _to);
}
