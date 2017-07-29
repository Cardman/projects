package code.util;
import code.util.annot.CapacityInit;
import code.util.ints.Equallable;
import code.util.ints.Listable;

/** Not compared in tests */
public final class EqList<T extends Equallable<T>> extends AbEqList<T> implements Equallable<EqList<T>> {
 
    public EqList() {
    }

    public EqList(T... _elements) {
        super(_elements);
    }
    
    public EqList(Listable<? extends T> _c) {
        super(_c);
    }

    @CapacityInit
    public EqList(CollCapacity _capacity) {
        super(_capacity);
    }

//    public EqList(Iterable<? extends T> _c) {
//        super(_c);
//    }

//    public void removeAllElements(Listable<? extends T> _c) {
//        for (T s: _c) {
//            if (containsObj(s)) {
//                //_list.containsObj(s)
//                removeAllObj(s);
//            }
//        }
//    }
//
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
//
//    public void retainAllElements(Listable<? extends T> _c) {
//        List<T> common_ = new List<T>();
//        for (T s: _c) {
//            if (containsObj(s)) {
//                common_.add(s);
//            }
//        }
//        int i_ = FIRST_INDEX;
//        while (i_ < size()) {
//            T e_ = get(i_);
//            if (!common_.containsObj(e_)) {
//                removeAt(i_);
//            } else {
//                i_++;
//            }
//        }
//    }
//    public boolean containsAllObj(EqList<T> _list) {
//        for (T e: _list) {
//            if (!containsObj(e)) {
//                return false;
//            }
//        }
//        return true;
//    }
    @Override
    public int indexOfObj(T _element, int _from) {
        if (_element == null) {
            return indexOfNull(_from);
        }
        int s_ = size();
        for (int i = _from; i < s_; i++) {
            T e_ = get(i);
            if (e_ == null) {
                continue;
            }
            if (_element.eq(e_)) {
                return i;
            }
        }
        return INDEX_NOT_FOUND_ELT;
    }
//    @Override
//    public Numbers<Integer> indexesOfObj(T _element) {
//        if (_element == null) {
//            return indexesOfNull();
//        }
//        Numbers<Integer> indexes_;
//        indexes_ = new Numbers<Integer>();
//        int s_ = size();
//        for (int i = FIRST_INDEX; i < s_; i++) {
//            T e_ = get(i);
//            if (e_ == null) {
//                continue;
//            }
//            if (_element.eq(e_)) {
//                indexes_.add(i);
//            }
//        }
//        return indexes_;
//    }
//    public Numbers<Integer> indexesOfObject(T _string) {
//        Numbers<Integer> list_ = new Numbers<Integer>();
//        int size_ = size();
//        if (_string == null) {
//            for (int i=FIRST_INDEX;i<size_;i++) {
//                if (get(i) == _string) {
//                    list_.add(i);
//                }
//            }
//        } else {
//            for (int i=FIRST_INDEX;i<size_;i++) {
//                if (get(i) == null) {
//                    continue;
//                }
//                if (get(i).eq(_string)) {
//                    list_.add(i);
//                }
//            }
//        }
//        return list_;
//    }
//    public void removeAllObj(T _obj) {
//        //setModified();
//        while (containsObj(_obj)) {
//            removeObj(_obj);
//        }
//    }
//    public void removeObj(T _obj) {
//        int size_ = size();
//        if (_obj == null) {
//            for (int i = FIRST_INDEX; i < size_; i++) {
//                if (get(i) == null) {
//                    removeAt(i);
//                    return;
//                }
//            }
//            return;
//        }
//        for (int i = FIRST_INDEX; i < size_; i++) {
//            T e_ = get(i);
//            if (e_ == null) {
//                continue;
//            }
//            if (_obj.eq(e_)) {
//                removeAt(i);
//                return;
//            }
//        }
//    }
//    public EqList<T> intersect(EqList<T> _list) {
//        EqList<T> list_ = new EqList<T>();
//        for (T s: _list) {
//            if (containsObj(s)) {
//                //_list.containsObj(s)
//                list_.add(s);
//            }
//        }
//        return list_;
//    }
//    public boolean containsObj(T _element) {
//        if (_element == null) {
//            for (T e:this) {
//                if (e == null) {
//                    return true;
//                }
//            }
//            return false;
//        }
//        for (T s: this) {
//            if (_element.eq(s)) {
//                return true;
//            }
//        }
//        return false;
//    }
//    public void removeDuplicates() {
//        //setModified();
//        int i_ = FIRST_INDEX;
//        while (true) {
//            if(i_ >= size()) {
//                break;
//            }
//            int j_ = i_ + 1;
//            while (true) {
//                if (j_ >= size()) {
//                    break;
//                }
//                if (get(i_) == null) {
//                    if (get(j_) == null) {
//                        removeAt(j_);
//                    } else {
//                        j_++;
//                    }
//                    continue;
//                }
//                if (get(i_).eq(get(j_))) {
//                    removeAt(j_);
//                } else {
//                    j_++;
//                }
//            }
//            i_++;
//        }
//    }
    @Override
    public boolean eq(EqList<T> _b) {
        if (_b == null) {
            return false;
        }
        int len_ = size();
        if (_b.size() != len_) {
            return false;
        }
        for (int i = FIRST_INDEX; i < len_; i++) {
            T e_ = get(i);
            T f_ = _b.get(i);
            if (e_ == null) {
                if (f_ != null) {
                    return false;
                }
                continue;
            }
            if (f_ == null) {
                return false;
            }
            if (!e_.eq(f_)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public EqList<T> subAbEq(int _from, int _to) {
        return sub(_from, _to);
    }

    @Override
    public EqList<T> sub(int _from, int _to) {
        if (_from > _to) {
            return new EqList<T>();
        }
        return new EqList<T>(super.sub(_from, _to));
    }

//    @Override
//    public EqList<T> subList(int _from, int _to) {
//        if (_from > _to) {
//            return new EqList<T>();
//        }
//        return new EqList<T>(subList(Math.max(_from,FIRST_INDEX), Math.min(_to, size())));
//    }

}
