package code.util;
import code.util.ints.Listable;

public abstract class Numbers<T> extends CustList<T> {

    private static final int DEFAULT_RADIX = 10;

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

    public static boolean equalsSetBytes(Listable<Byte> _list1,Listable<Byte> _list2) {
        for (Byte c: _list2) {
            boolean contains_ = false;
            for (Byte d: _list1) {
                if (eq(c, d)) {
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
                if (eq(c, d)) {
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
                if (eq(c, d)) {
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
                if (eq(c, d)) {
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

    public static boolean eq(long _nb1,long _nb2) {
        return _nb1 == _nb2;
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

    public static int[] wrapIntArray(int... _ints) {
        return _ints;
    }
    static byte[] wrapByteArray(byte... _ints) {
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

    public final void sort() {
        //setModified();
        int len_ = size();
        for (int i = FIRST_INDEX; i <len_; i++) {
            for (int j = i + 1; j <len_; j++) {
                long one_ = getLong(i);
                long two_ = getLong(j);
                int res_ = Numbers.compareLg(one_, two_);
                if (res_ > EQ_CMP) {
                    swapIndexes(i,j);
                }
            }
        }
    }

    public final long getMinimum(long _def) {
        if (isEmpty()) {
            return _def;
        }
        long min_ = getLong(FIRST_INDEX);
        int size_ = size();
        for (int i=SECOND_INDEX;i<size_;i++) {
            long cur_ = getLong(i);
            if (min_> cur_) {
                min_ = cur_;
            }
        }
        return min_;
    }

    public final long getMaximum(long _def) {
        if (isEmpty()) {
            return _def;
        }
        long max_ = getLong(FIRST_INDEX);
        int size_ = size();
        for (int i=SECOND_INDEX;i<size_;i++) {
            long cur_ = getLong(i);
            if (max_< cur_) {
                max_ = cur_;
            }
        }
        return max_;
    }
    public final void removeOneNumber(long _n) {
        int s_ = size();
        for (int i = 0; i < s_; i++) {
            long lg_ = getLong(i);
            if (eq(lg_,_n)) {
                removeObj(lg_);
                break;
            }
        }
    }
    public final boolean contains(long _element) {
        return indexOf(_element) != INDEX_NOT_FOUND_ELT;
    }
    public final int indexOf(long _element) {
        int s_ = size();
        for (int i = 0; i < s_; i++) {
            if (_element == getLong(i)) {
                return i;
            }
        }
        return INDEX_NOT_FOUND_ELT;
    }


    public final void removeObj(long _obj) {
        int index_ = indexOfObj(_obj);
        if (index_ == CustList.INDEX_NOT_FOUND_ELT) {
            return;
        }
        removeAt(index_);
    }

    public final boolean containsObj(long _obj) {
        return indexOfObj(_obj) != INDEX_NOT_FOUND_ELT;
    }

    public final void removeDuplicates() {
        int i_ = FIRST_INDEX;
        while (i_ < size()) {
            long e_ = getLong(i_);
            boolean rem_ = false;
            int next_ = indexOfObj(e_, i_ + 1);
            while (next_ != INDEX_NOT_FOUND_ELT) {
                removeAt(next_);
                rem_ = true;
                next_ = indexOfObj(e_, i_ + 1);
            }
            if (!rem_) {
                i_++;
            }
        }
    }
    public final boolean hasDuplicates() {
        int i_ = FIRST_INDEX;
        while (i_ < size()) {
            long e_ = getLong(i_);
            int next_ = indexOfObj(e_, i_ + 1);
            if (next_ > INDEX_NOT_FOUND_ELT) {
                return true;
            }
            i_++;
        }
        return false;
    }
    public final int indexOfObj(long _element) {
        return indexOfObj(_element, FIRST_INDEX);
    }
    public final int indexOfObj(long _element, int _from) {
        int s_ = size();
        for (int i = _from; i < s_; i++) {
            long e_ = getLong(i);
            if (eq(_element, e_)) {
                return i;
            }
        }
        return INDEX_NOT_FOUND_ELT;
    }
    public final Ints indexesOfObj(long _element) {
        Ints indexes_;
        indexes_ = new Ints();
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

    public final void removeAllLong(long _obj) {
        int i_ = FIRST_INDEX;
        while (i_ < size()) {
            long elt_ = getLong(i_);
            if (elt_ == _obj) {
                remove(i_);
            } else {
                i_++;
            }
        }
    }

    abstract long getLong(int _index);
}
