package code.util.core;

import code.util.ints.Listable;

public final class NumberUtil {
    private static final int DEFAULT_RADIX = 10;

    private NumberUtil() {
    }
    public static boolean isValidIndex(int _index, int _max) {
        if (_index < IndexConstants.FIRST_INDEX) {
            return false;
        }
        return _index < _max;
    }
    public static boolean equalsSetBytes(Listable<Byte> _list1, Listable<Byte> _list2) {
        for (Byte c: _list2) {
            boolean contains_ = containsByte(_list1, c);
            if (!contains_) {
                return false;
            }
        }
        for (Byte c: _list1) {
            boolean contains_ = containsByte(_list2, c);
            if (!contains_) {
                return false;
            }
        }
        return true;
    }

    private static boolean containsByte(Listable<Byte> _list1, Byte _c) {
        boolean contains_ = false;
        for (Byte d: _list1) {
            if (eq(_c, d)) {
                contains_ = true;
                break;
            }
        }
        return contains_;
    }

    public static boolean equalsSetShorts(Listable<Short> _list1, Listable<Short> _list2) {
        for (Short c: _list2) {
            boolean contains_ = containsShort(_list1, c);
            if (!contains_) {
                return false;
            }
        }
        for (Short c: _list1) {
            boolean contains_ = containsShort(_list2, c);
            if (!contains_) {
                return false;
            }
        }
        return true;
    }

    private static boolean containsShort(Listable<Short> _list1, Short _c) {
        boolean contains_ = false;
        for (Short d: _list1) {
            if (eq(_c, d)) {
                contains_ = true;
                break;
            }
        }
        return contains_;
    }

    public static int mod(int _one, int _two) {
        return (int) mod(_one,(long)_two);
    }

    public static int quot(int _one, int _two) {
        return (int) quot(_one,(long)_two);
    }

    public static long mod(long _one, long _two) {
        return _one - _two * quot(_one, _two);
    }

    public static long quot(long _one, long _two) {
        if (_two == 0) {
            return 0;
        }
        if (_two == Long.MIN_VALUE) {
            if (_one >= 0) {
                return 0;
            }
            return 1;
        }
        return quotNormal(_one, _two);
    }

    private static long quotNormal(long _one, long _two) {
        if (_one == Long.MIN_VALUE) {
            if (_two <= 0) {
                return (-(_one+1))/(-_two)+1;
            }
            return -(-(_one+1)/_two)-1;
        }
        long absDiv_ = Math.abs(_two);
        long absNb_ = Math.abs(_one);
        long q_ = absNb_ / absDiv_;
        long sigDiv_ = signum(_two);
        q_ *= signum(_one) * sigDiv_;
        if (_one >= 0) {
            return q_;
        }
        long m_ = absNb_ % absDiv_;
        if (m_ != 0) {
            // sigDiv_ == 1 || sigDiv_ == -1
            q_ += -sigDiv_;
        }
        return q_;
    }

    public static boolean eq(long _nb1, long _nb2) {
        return _nb1 == _nb2;
    }

    public static int compareLg(long _nb1, long _nb2) {
        if (_nb1 < _nb2) {
            return SortConstants.NO_SWAP_SORT;
        }
        if (_nb1 > _nb2) {
            return SortConstants.SWAP_SORT;
        }
        return SortConstants.EQ_CMP;
    }

    public static int[] wrapIntArray(int... _ints) {
        return _ints;
    }

    public static byte[] wrapByteArray(byte... _ints) {
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

        if (_string.charAt(0) == '-') {
            negative_ = true;
            i_++;
        }
        if (i_ >= _string.length()) {
            return 0;
        }
        long result_ = simpleParse(i_,_string);
        if (negative_) {
            return result_;
        }
        return -result_;
    }
    public static long simpleParse(int _i,String _string) {
        int i_ = _i;
        int max_ = _string.length();
        int ch_ = _string.charAt(i_);
        i_++;
        int digit_ = ch_ - '0';
        long result_ = -digit_;
        while (i_ < max_) {
            ch_ = _string.charAt(i_);
            i_++;
            digit_ = ch_ - '0';
            result_ *= DEFAULT_RADIX;
            result_ -= digit_;
        }
        return result_;
    }

    public static byte signum(long _v) {
        if (_v < 0) {
            return (byte)-1;
        }
        if (_v > 0) {
            return (byte)1;
        }
        return (byte)0;
    }
}
