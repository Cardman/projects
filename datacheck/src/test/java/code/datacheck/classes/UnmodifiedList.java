package code.datacheck.classes;
import code.util.AbEqList;
import code.util.ints.Listable;

class UnmodifiedList<T> extends AbEqList<T> implements Listable<T>{

    private transient boolean addRemove;

    UnmodifiedList() {
    }
    UnmodifiedList(Listable<T> _list) {
        super(_list);
    }

    @Override
    public void add(T _e) {
        if (addRemove) {
            super.add(_e);
            return;
        }
        throw new ModifyingException();
    }
    @Override
    public void add(int _index, T _element) {
        if (addRemove) {
            super.add(_index, _element);
            return;
        }
        throw new ModifyingException();
    }

    @Override
    public void clear() {
        if (addRemove) {
            super.clear();
            return;
        }
        throw new ModifyingException();
    }
    @Override
    public void remove(int _index) {
        if (addRemove) {
            super.remove(_index);
            return;
        }
        throw new ModifyingException();
    }

    boolean isAddRemove() {
        return addRemove;
    }

    void setAddRemove(boolean _addRemove) {
        addRemove = _addRemove;
    }

//    @Override
//    public Listable<T> sub(int _from, int _to) {
//        List<T> l_ = new List<T>();
//        for (T e: subList(_from, _to)) {
//            l_.add(e);
//        }
//        return l_;
//    }

    @Override
    public T first() {
        return get(0);
    }

    @Override
    public T last() {
        return get(size()-1);
    }

    @Override
    public String join(String _join) {
        if (isEmpty()) {
            return "";
        }
        StringBuilder return_ = new StringBuilder(String.valueOf(first()));
        int size_ = size();
        for (int i=1;i<size_;i++) {
            return_.append(_join);
            return_.append(get(i));
        }
        return return_.toString();
    }

    @Override
    public String join(char _join) {
        if (isEmpty()) {
            return "";
        }
        StringBuilder return_ = new StringBuilder(String.valueOf(first()));
        int size_ = size();
        for (int i=1;i<size_;i++) {
            return_.append(_join);
            return_.append(get(i));
        }
        return return_.toString();
    }

    @Override
    public void removeAt(Number _index) {
        if (addRemove) {
            super.remove(_index.intValue());
            return;
        }
        throw new ModifyingException();
    }

    @Override
    public void addAllElts(Listable<T> _c) {
        if (addRemove) {
            for (T e: _c) {
                add(e);
            }
            return;
        }
        throw new ModifyingException();
    }

    @Override
    public int indexOfObj(T _element, int _from) {
//        if (_element == null) {
//            return indexOfNull(_from);
//        }
        int s_ = size();
        for (int i = _from; i < s_; i++) {
            T e_ = get(i);
            if (e_ == _element) {
                return i;
            }
        }
        return INDEX_NOT_FOUND_ELT;
    }

    @Override
    public AbEqList<T> subAbEq(int _from, int _to) {
        return new UnmodifiedList<T>(sub(_from, _to));
    }

//    @Override
//    public Numbers<Integer> indexesOfObj(T _element) {
//        Numbers<Integer> indexes_;
//        indexes_ = new Numbers<Integer>();
//        int s_ = size();
//        for (int i = FIRST_INDEX; i < s_; i++) {
//            T e_ = get(i);
//            if (_element == e_) {
//                indexes_.add(i);
//            }
//        }
//        return indexes_;
//    }
//    @Override
//    public int indexOfObj(T _element) {
//        int i_ = FIRST_INDEX;
//        for (T e: this) {
//            if (e == _element) {
//                return i_;
//            }
//            i_++;
//        }
//        return INDEX_NOT_FOUND_ELT;
//    }
}
