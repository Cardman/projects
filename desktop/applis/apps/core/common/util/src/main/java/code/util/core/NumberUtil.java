package code.util.core;

import code.util.ints.Listable;

public final class NumberUtil {
    private static final int DEFAULT_RADIX = 10;

    private NumberUtil() {
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

    private static boolean containsByte(Listable<Byte> _list1, Byte c) {
        boolean contains_ = false;
        for (Byte d: _list1) {
            if (eq(c, d)) {
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

    private static boolean containsShort(Listable<Short> _list1, Short c) {
        boolean contains_ = false;
        for (Short d: _list1) {
            if (eq(c, d)) {
                contains_ = true;
                break;
            }
        }
        return contains_;
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
}
