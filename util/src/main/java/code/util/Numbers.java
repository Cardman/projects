package code.util;
import code.util.annot.CapacityInit;
import code.util.comparators.ComparatorNatNumber;
import code.util.ints.Equallable;
import code.util.ints.Listable;

public final class Numbers<T extends Number> extends AbEqList<T> implements Equallable<Numbers<T>> {

    public Numbers() {
    }

    public Numbers(Listable<T> _list) {
        for (T n:_list) {
            add(n);
        }
    }

    public Numbers(T... _array) {
        super(_array);
    }

    @CapacityInit
    public Numbers(CollCapacity _capacity) {
        super(_capacity);
    }

    public static boolean equalsSetBytes(Listable<Byte> _list1,Listable<Byte> _list2) {
        for (Number c: _list2) {
            boolean contains_ = false;
            for (Number d: _list1) {
                if (c == null) {
                    if (d == null) {
                        contains_ = true;
                        break;
                    }
                    continue;
                }
                if (d != null && eq(c, d)) {
                    contains_ = true;
                    break;
                }
            }
            if (!contains_) {
                return false;
            }
        }
        for (Number c: _list1) {
            boolean contains_ = false;
            for (Number d: _list2) {
                if (c == null) {
                    if (d == null) {
                        contains_ = true;
                        break;
                    }
                    continue;
                }
                if (d != null && eq(c, d)) {
                    contains_ = true;
                    break;
                }
            }
            if (!contains_) {
                return false;
            }
        }
        return true;
    }
    public static boolean equalsSetShorts(Listable<Short> _list1,Listable<Short> _list2) {
        for (Number c: _list2) {
            boolean contains_ = false;
            for (Number d: _list1) {
                if (c == null) {
                    if (d == null) {
                        contains_ = true;
                        break;
                    }
                    continue;
                }
                if (d != null && eq(c, d)) {
                    contains_ = true;
                    break;
                }
            }
            if (!contains_) {
                return false;
            }
        }
        for (Number c: _list1) {
            boolean contains_ = false;
            for (Number d: _list2) {
                if (c == null) {
                    if (d == null) {
                        contains_ = true;
                        break;
                    }
                    continue;
                }
                if (d != null && eq(c, d)) {
                    contains_ = true;
                    break;
                }
            }
            if (!contains_) {
                return false;
            }
        }
        return true;
    }
    public static int mod(int _one, int _two) {
        return _one - _two * quot(_one, _two);
    }

    public static int quot(int _one, int _two) {
        if (_one >= 0) {
            if (_two >= 0) {
                return _one/_two;
            }
        }
        if (_one <= 0) {
            if (_two <= 0) {
                if ((-_one) % (-_two) == 0) {
                    return (-_one)/(-_two);
                }
                return (-_one)/(-_two)+1;
            }
        }
        if (_one >= 0) {
            //_two < 0
            return -(_one/(-_two));
        }
        //_one < 0 && _two > 0
        if ((-_one) % _two == 0) {
            return -((-_one)/_two);
        }
        return -((-_one)/_two)-1;
    }
    public static long mod(long _one, long _two) {
        return _one - _two * quot(_one, _two);
    }

    public static long quot(long _one, long _two) {
        if (_one >= 0) {
            if (_two >= 0) {
                return _one/_two;
            }
        }
        if (_one <= 0) {
            if (_two <= 0) {
                if ((-_one) % (-_two) == 0) {
                    return (-_one)/(-_two);
                }
                return (-_one)/(-_two)+1;
            }
        }
        if (_one >= 0) {
            //_two < 0
            return -(_one/(-_two));
        }
        //_one < 0 && _two > 0
        if ((-_one) % _two == 0) {
            return -((-_one)/_two);
        }
        return -((-_one)/_two)-1;
    }
    public static boolean eq(Number _nb1,Number _nb2) {
        if (_nb1 instanceof Double || _nb1 instanceof Float) {
            return _nb1.doubleValue() == _nb2.doubleValue();
        }
        if (_nb2 instanceof Double || _nb2 instanceof Float) {
            return _nb1.doubleValue() == _nb2.doubleValue();
        }
        return _nb1.longValue() == _nb2.longValue();
    }

    public static boolean eq(long _nb1,long _nb2) {
        return _nb1 == _nb2;
    }

    public static int compare(Number _nb1,Number _nb2) {
        if (_nb1.longValue() < _nb2.longValue()) {
            return CustList.NO_SWAP_SORT;
        }
        if (_nb1.longValue() > _nb2.longValue()) {
            return CustList.SWAP_SORT;
        }
        return CustList.EQ_CMP;
    }

    public EqList<Numbers<Integer>> getAllIndexes() {
        EqList<Numbers<Integer>> e_;
        e_ = new EqList<Numbers<Integer>>();
        if (isEmpty()) {
            return e_;
        }
        int f_ = first().intValue();
        int sdims_ = size();
        for (int i = 0; i < f_; i++) {
            Numbers<Integer> nbs_ = new Numbers<Integer>(i);
            e_.add(nbs_);
        }
        for (int i = 1; i < sdims_; i++) {
            EqList<Numbers<Integer>> newIndexes_;
            newIndexes_ = new EqList<Numbers<Integer>>();
            for (Numbers<Integer> p: e_) {
                int d_ = get(i).intValue();
                for (int j = 0; j < d_; j++) {
                    Numbers<Integer> n_;
                    n_ = new Numbers<Integer>(p);
                    n_.add(j);
                    newIndexes_.add(n_);
                }
            }
            e_ = newIndexes_;
        }
        return e_;
    }
    public void sort() {
        //setModified();
        sortElts(new ComparatorNatNumber<T>());
    }

//    @Override
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
//                if (eq(get(i_),get(j_))) {
//                    removeAt(j_);
//                } else {
//                    j_++;
//                }
//            }
//            i_++;
//        }
//    }

    public T getMinimum() {
        if (isEmpty()) {
            return null;
        }
        T min_ = get(FIRST_INDEX);
        int size_ = size();
        for (int i=SECOND_INDEX;i<size_;i++) {
            if (min_.longValue()>get(i).longValue()) {
                min_ = get(i);
            }
        }
        return min_;
    }

    public T getMaximum() {
        if (isEmpty()) {
            return null;
        }
        T max_ = get(FIRST_INDEX);
        int size_ = size();
        for (int i=SECOND_INDEX;i<size_;i++) {
            if (max_.longValue()<get(i).longValue()) {
                max_ = get(i);
            }
        }
        return max_;
    }

    public void removeOneNumber(Number _n) {
        //setModified();
        if (_n == null) {
            removeObj(null);
            return;
        }
        for (T e:this) {
            if (e != null && eq(e,_n)) {
                removeObj(e);
                break;
            }
        }
    }
    public boolean contains(Number _element) {
        return indexOf(_element) != INDEX_NOT_FOUND_ELT;
    }
    public int indexOf(Number _element) {
        if (_element == null) {
            return indexOfNull();
        }
        int index_ = FIRST_INDEX;
        for (T e:this) {
            if (e != null && eq(_element,e)) {
                return index_;
            }
            index_++;
        }
        return INDEX_NOT_FOUND_ELT;
    }

    @Override
    public Numbers<T> subAbEq(int _from, int _to) {
        return sub(_from, _to);
    }

    @Override
    public Numbers<T> sub(int _from, int _to) {
        if (_from > _to) {
            return new Numbers<T>();
        }
        return new Numbers<T>(super.sub(_from, _to));
    }

    @Override
    public Numbers<T> mid(int _beginIndex, int _nbElements) {
        return new Numbers<T>(sub(_beginIndex, _beginIndex+_nbElements));
    }

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
            if (eq(_element, e_)) {
                return i;
            }
        }
        return INDEX_NOT_FOUND_ELT;
    }

//    @Override
//    public Numbers<Integer> indexesOfObj(T _number) {
//        Numbers<Integer> list_ = new Numbers<Integer>();
//        int size_ = size();
//        if (_number == null) {
//            for (int i=FIRST_INDEX;i<size_;i++) {
//                if (get(i) == _number) {
//                    list_.add(i);
//                }
//            }
//        } else {
//            for (int i=FIRST_INDEX;i<size_;i++) {
//                if (get(i) == null) {
//                    continue;
//                }
//                if (eq(get(i),_number)) {
//                    list_.add(i);
//                }
//            }
//        }
//        return list_;
//    }

    public Numbers<Integer> indexesOfNumber(T _number) {
        Numbers<Integer> list_ = new Numbers<Integer>();
        int size_ = size();
        if (_number == null) {
            for (int i=FIRST_INDEX;i<size_;i++) {
                if (get(i) == _number) {
                    list_.add(i);
                }
            }
        } else {
            for (int i=FIRST_INDEX;i<size_;i++) {
                if (get(i) == null) {
                    continue;
                }
                if (eq(get(i),_number)) {
                    list_.add(i);
                }
            }
        }
        return list_;
    }
    public void removeAllNb(T _obj) {
        //setModified();
        while (contains(_obj)) {
            removeObj(_obj);
        }
    }
//    public static <K extends Number,L extends Number,V> V get(Map<K,V> _map, L _key) {
//        for (EntryCust<K,V> k: _map.entryList()) {
//            if (eq(k.getKey(),_key)) {
//                return k.getValue();
//            }
//        }
//        return null;
//    }
//
//    public static <K extends Number,L extends Number,V> boolean containsKey(Map<K,V> _map, L _key) {
//        for (EntryCust<K,V> k: _map.entryList()) {
//            if (eq(k.getKey(),_key)) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//    public static <K extends Number,L extends Number,V> void remove(Map<K,V> _map, L _key) {
//        for (EntryCust<K,V> k: _map.entryList()) {
//            if (eq(k.getKey(),_key)) {
//                _map.remove(k);
//                return;
//            }
//        }
//    }
//
//    public static <K extends Number,L extends Number,V> void put(Map<K,V> _map, L _key, V _value) {
//        for (EntryCust<K,V> k: _map.entryList()) {
//            if (eq(k.getKey(),_key)) {
//                k.setValueOnly(_value);
//                return;
//            }
//        }
//    }

    public static boolean checkLong(String _number) {
        try {
            Long.parseLong(_number);
            return true;
        } catch (NumberFormatException _0) {
            return false;
        }
    }

    public static boolean checkDouble(String _number) {
        try {
            Double.parseDouble(_number);
            return true;
        } catch (NumberFormatException _0) {
            return false;
        }
    }

    @Override
    public Numbers<T> getReverse() {
        Numbers<T> list_ = new Numbers<T>(this);
        int i_ = FIRST_INDEX;
        int j_ = list_.size();
        j_--;
        while (i_ < j_) {
            list_.swapIndexes(i_, j_);
            i_++;
            j_--;
        }
        return list_;
    }

    @Override
    public boolean eq(Numbers<T> _g) {
        if (_g == null) {
            return false;
        }
        int len_ = size();
        if (_g.size() != len_) {
            return false;
        }
        for (int i = FIRST_INDEX; i < len_; i++) {
            T e_ = get(i);
            T f_ = _g.get(i);
            if (e_ == null) {
                if (f_ != null) {
                    return false;
                }
                continue;
            }
            if (f_ == null) {
                return false;
            }
            if (!eq(e_, f_)) {
                return false;
            }
        }
        return true;
    }
}
