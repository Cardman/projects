package code.util;
import code.util.comparators.ComparatorNatNumber;
import code.util.ints.Displayable;
import code.util.ints.Listable;

public final class Numbers<T extends Number> extends CustList<T> implements Displayable {

    private static final int DEFAULT_RADIX = 10;
    private static final long MULTMIN_RADIX_TEN = Long.MIN_VALUE / DEFAULT_RADIX;
    private static final long N_MULTMAX_RADIX_TEN = -Long.MAX_VALUE / DEFAULT_RADIX;

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

    
    public Numbers(CollCapacity _capacity) {
        super(_capacity);
    }

    static String nullableToString(Number _nb) {
        if (_nb == null) {
            return EMPTY_STRING;
        }
        return toString(_nb);
    }
    public static String toString(Number _nb) {
        if (_nb instanceof Double) {
            return _nb.toString();
        }
        if (_nb instanceof Float) {
            return _nb.toString();
        }
        return Long.toString(_nb.longValue());
    }

    public static boolean equalsSetBytes(Listable<Byte> _list1,Listable<Byte> _list2) {
        for (Byte c: _list2) {
            boolean contains_ = false;
            for (Byte d: _list1) {
                if (eq(c.byteValue(), d.byteValue())) {
                    contains_ = true;
                    break;
                }
            }
            if (!contains_) {
                return false;
            }
        }
        for (Byte c: _list1) {
            boolean contains_ = false;
            for (Byte d: _list2) {
                if (eq(c.byteValue(), d.byteValue())) {
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
        for (Short c: _list2) {
            boolean contains_ = false;
            for (Short d: _list1) {
                if (eq(c.shortValue(), d.shortValue())) {
                    contains_ = true;
                    break;
                }
            }
            if (!contains_) {
                return false;
            }
        }
        for (Short c: _list1) {
            boolean contains_ = false;
            for (Short d: _list2) {
                if (eq(c.shortValue(), d.shortValue())) {
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
            //_two < 0
            return -(_one/(-_two));
        }
        //_one < 0
        if (_two <= 0) {
            if ((-_one) % (-_two) == 0) {
                return (-_one)/(-_two);
            }
            return (-_one)/(-_two)+1;
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
            //_two < 0
            return -(_one/(-_two));
        }
        //_one < 0
        if (_two <= 0) {
            if ((-_one) % (-_two) == 0) {
                return (-_one)/(-_two);
            }
            return (-_one)/(-_two)+1;
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

    public static boolean lt(Number _nb1,Number _nb2) {
        if (_nb1 instanceof Double || _nb1 instanceof Float) {
            return _nb1.doubleValue() < _nb2.doubleValue();
        }
        if (_nb2 instanceof Double || _nb2 instanceof Float) {
            return _nb1.doubleValue() < _nb2.doubleValue();
        }
        return _nb1.longValue() < _nb2.longValue();
    }

    public static boolean gt(Number _nb1,Number _nb2) {
        if (_nb1 instanceof Double || _nb1 instanceof Float) {
            return _nb1.doubleValue() > _nb2.doubleValue();
        }
        if (_nb2 instanceof Double || _nb2 instanceof Float) {
            return _nb1.doubleValue() > _nb2.doubleValue();
        }
        return _nb1.longValue() > _nb2.longValue();
    }
    public static boolean eq(long _nb1,long _nb2) {
        return _nb1 == _nb2;
    }

    public static int compareGene(Number _nb1,Number _nb2) {
        if (_nb1 instanceof Double || _nb1 instanceof Float) {
            if (_nb1.doubleValue() < _nb2.doubleValue()) {
                return CustList.NO_SWAP_SORT;
            }
            if (_nb1.doubleValue() > _nb2.doubleValue()) {
                return CustList.SWAP_SORT;
            }
            return CustList.EQ_CMP;
        }
        if (_nb2 instanceof Double || _nb2 instanceof Float) {
            if (_nb1.doubleValue() < _nb2.doubleValue()) {
                return CustList.NO_SWAP_SORT;
            }
            if (_nb1.doubleValue() > _nb2.doubleValue()) {
                return CustList.SWAP_SORT;
            }
            return CustList.EQ_CMP;
        }
        if (_nb1.longValue() < _nb2.longValue()) {
            return CustList.NO_SWAP_SORT;
        }
        if (_nb1.longValue() > _nb2.longValue()) {
            return CustList.SWAP_SORT;
        }
        return CustList.EQ_CMP;
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
    public static int compareLg(long _nb1,long _nb2) {
        if (_nb1 < _nb2) {
            return CustList.NO_SWAP_SORT;
        }
        if (_nb1 > _nb2) {
            return CustList.SWAP_SORT;
        }
        return CustList.EQ_CMP;
    }
    public static byte[] wrapByteArray(byte... _ints) {
        return _ints;
    }

    public static int[] wrapIntArray(int... _ints) {
        return _ints;
    }
    public static int parseInt(String _string) {
        long int_ = parseLongZero(_string);
        if (int_ < Integer.MIN_VALUE) {
            return 0;
        }
        if (int_ > Integer.MAX_VALUE) {
            return 0;
        }
        return (int) int_;
    }
    //this long parser is very naive
    public static long parseLongZero(String _string) {
        if (_string.isEmpty()) {
            return 0;
        }
        boolean negative_ = false;
        int i_ = 0;
        int max_ = _string.length();
        int digit_;

        if (_string.charAt(0) == '-') {
            negative_ = true;
            i_++;
        }
        if (i_ >= _string.length()) {
            return 0;
        }
        int ch_ = _string.charAt(i_);
        i_++;
        digit_ = ch_ - '0';
        long result_ = -digit_;
        while (i_ < max_) {
            // Accumulating negatively avoids surprises near MAX_VALUE
            ch_ = _string.charAt(i_);
            i_++;
            digit_ = ch_ - '0';
            result_ *= DEFAULT_RADIX;
            result_ -= digit_;
        }
        if (negative_) {
            return result_;
        }
        return -result_;
    }

    public CustList<Numbers<Integer>> getAllIndexes() {
        CustList<Numbers<Integer>> e_;
        e_ = new CustList<Numbers<Integer>>();
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
            CustList<Numbers<Integer>> newIndexes_;
            newIndexes_ = new CustList<Numbers<Integer>>();
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

    public void removeOneNumber(long _n) {
        for (Number e:this) {
            long lg_ = e.longValue();
            if (eq(lg_,_n)) {
                removeObj(lg_);
                break;
            }
        }
    }
    public boolean contains(long _element) {
        return indexOf(_element) != INDEX_NOT_FOUND_ELT;
    }
    public int indexOf(long _element) {
        int index_ = FIRST_INDEX;
        for (T e:this) {
            if (_element == e.longValue()) {
                return index_;
            }
            index_++;
        }
        return INDEX_NOT_FOUND_ELT;
    }

    public String join(String _join) {
        if (isEmpty()) {
            return EMPTY_STRING;
        }
        StringBuilder return_ = new StringBuilder(nullableToString(get(FIRST_INDEX)));
        int size_ = size();
        for (int i=SECOND_INDEX;i<size_;i++) {
            return_.append(_join);
            return_.append(nullableToString(get(i)));
        }
        return return_.toString();
    }

    public String join(char _join) {
        if (isEmpty()) {
            return EMPTY_STRING;
        }
        StringBuilder return_ = new StringBuilder(toString(get(FIRST_INDEX)));
        int size_ = size();
        for (int i=SECOND_INDEX;i<size_;i++) {
            return_.append(_join);
            return_.append(toString(get(i)));
        }
        return return_.toString();
    }

    public void removeAllElements(Listable<T> _c) {
        for (Number s: _c) {
            long lg_ = s.longValue();
            if (containsObj(lg_)) {
                removeAllObj(lg_);
            }
        }
    }

    public void removeAllObj(long _obj) {
        while (containsObj(_obj)) {
            removeObj(_obj);
        }
    }

    public void removeObj(long _obj) {
        int index_ = indexOfObj(_obj);
        if (index_ == CustList.INDEX_NOT_FOUND_ELT) {
            return;
        }
        removeAt(index_);
    }
    public boolean containsAllObj(Listable<T> _list) {
        for (Number e: _list) {
            long nb_ = e.longValue();
            if (!containsObj(nb_)) {
                return false;
            }
        }
        return true;
    }
    public boolean containsObj(long _obj) {
        return indexOfObj(_obj) != INDEX_NOT_FOUND_ELT;
    }
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

    public void removeDuplicates() {
        int i_ = FIRST_INDEX;
        while (true) {
            if(i_ >= size()) {
                break;
            }
            long e_ = get(i_).longValue();
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
        }
    }
    public int indexOfObj(long _element) {
        return indexOfObj(_element, FIRST_INDEX);
    }
    public int indexOfObj(long _element, int _from) {
        int s_ = size();
        for (int i = _from; i < s_; i++) {
            long e_ = get(i).longValue();
            if (eq(_element, e_)) {
                return i;
            }
        }
        return INDEX_NOT_FOUND_ELT;
    }
    public Numbers<Integer> indexesOfObj(long _element) {
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

    public void removeAllLong(long _obj) {
        int i_ = FIRST_INDEX;
        while (i_ < size()) {
            long elt_ = get(i_).longValue();
            if (elt_ == _obj) {
                remove(i_);
            } else {
                i_++;
            }
        }
    }

    @Override
    public String display() {
        return StringList.concat("[",join(","),"]");
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

    public void retainAllElements(Numbers<T> _c) {
        int i_ = FIRST_INDEX;
        while (i_ < size()) {
            long e_ = get(i_).longValue();
            if (!_c.containsObj(e_)) {
                removeAt(i_);
            } else {
                i_++;
            }
        }
    }
}
