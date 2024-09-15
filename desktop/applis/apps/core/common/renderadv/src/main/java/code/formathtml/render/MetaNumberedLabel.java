package code.formathtml.render;

import code.sml.RendKeyWordsDefs;
import code.util.Ints;

public final class MetaNumberedLabel extends MetaLabel {

    private final String number;

    public MetaNumberedLabel(MetaContainer _parent, int _number, MetaNumberBase _base, RendKeyWordsDefs _defs) {
        super(_parent);
        number = convert(_number,_base, _defs);
    }
    static String convert(int _number, MetaNumberBase _base, RendKeyWordsDefs _defs) {
        if (_base == MetaNumberBase.NUMBER) {
            return Long.toString(_number);
        }
        if (_base == MetaNumberBase.LETTER || _base == MetaNumberBase.MAJ_LETTER) {
            String firstLetter_;
            if (_base == MetaNumberBase.LETTER) {
                firstLetter_ = _defs.getDefMinLetter();
            } else {
                firstLetter_ = _defs.getDefMajLetter();
            }
            return str(_number,firstLetter_);
        }
        if (_base == MetaNumberBase.LATIN_MAJ) {
            return roman(_number, _defs.getDefMajLatin());
        }
        return roman(_number, _defs.getDefMinLatin());
    }

    private static String roman(int _number, String _defs) {
        char firstUnit_ = _defs.charAt(0);
        char firstDemi_ = _defs.charAt(1);
        char secondUnit_ = _defs.charAt(2);
        char secondDemi_ = _defs.charAt(3);
        char thirdUnit_ = _defs.charAt(4);
        char thirdDemi_ = _defs.charAt(5);
        char fourthUnit_ = _defs.charAt(6);
        char fourthDemit_ = _defs.charAt(7);
        Ints parts_ = initParts(_number);
        StringBuilder str_ = new StringBuilder(parts_.size() * 4);
        for (int i: parts_.getReverse()) {
            if (i == 0) {
                str_.append('|');
            } else if (i < 10) {
                str_.append(getLatinString(i, firstUnit_, firstDemi_, secondUnit_));
            } else if (i < 100) {
                str_.append(getLatinString(i / 10 % 10, secondUnit_, secondDemi_, thirdUnit_));
                str_.append(getLatinString(i % 10, firstUnit_, firstDemi_, secondUnit_));
            } else if (i < 1000) {
                str_.append(getLatinString(i / 100 % 10, thirdUnit_, thirdDemi_, fourthUnit_));
                str_.append(getLatinString(i / 10 % 10, secondUnit_, secondDemi_, thirdUnit_));
                str_.append(getLatinString(i % 10, firstUnit_, firstDemi_, secondUnit_));
            } else {
                bigNb(str_, i, fourthUnit_, fourthDemit_);
                str_.append(getLatinString(i  / 100 % 10, thirdUnit_, thirdDemi_, fourthUnit_));
                str_.append(getLatinString(i / 10 % 10, secondUnit_, secondDemi_, thirdUnit_));
                str_.append(getLatinString(i % 10, firstUnit_, firstDemi_, secondUnit_));
            }
            str_.append("+");
        }
        str_.deleteCharAt(str_.length() - 1);
        return str_.toString();
    }

    private static void bigNb(StringBuilder _str, int _i, char _fourthUnit, char _fourthDemi) {
        int d_ = _i /1000-1;
        if (d_ < 3) {
            for (int j = 0; j <= d_; j++) {
                _str.append(_fourthUnit);
            }
        } else if (d_ == 3){
            _str.append(_fourthUnit);
            _str.append(_fourthDemi);
        } else {
            _str.append(_fourthDemi);
            for (int j = 0; j < d_- 4; j++) {
                _str.append(_fourthUnit);
            }
        }
    }

    private static Ints initParts(int _number) {
        Ints parts_ = new Ints();
        int current_ = _number;
        while (current_ > 0) {
            parts_.add(current_ % 10000);
            current_ /= 10000;
        }
        return parts_;
    }

    private static String str(int _nb, String _firstLetter) {
        int n_ = 1;
        long s_ = 0;
        int len_ = _firstLetter.length();
        while (s_ < _nb) {
            s_ += pow(n_, len_);
            n_++;
        }
        n_--;
        long inf_ = (pow(n_, len_)-1)/(len_ - 1);
        StringBuilder str_ = new StringBuilder();
        long base_ = _nb - inf_;
        for (int i = 0; i < n_; i++) {
            long q_ = base_ / len_;
            long r_ = base_ % len_;
            str_.insert(0,_firstLetter.charAt((int) r_));
            base_ = q_;
        }
        return str_.toString();
    }
    private static long pow(int _n, int _nb) {
        long p_ = 1;
        for (int i = 0; i < _n; i++) {
            p_ *= _nb;
        }
        return p_;
    }
    private static StringBuilder getLatinString(int _digit, char _unit, char _demi, char _nextUnit) {
        StringBuilder str_ = new StringBuilder(4);
        if (_digit < 4) {
            for (int j = 0; j < _digit; j++) {
                str_.append(_unit);
            }
        } else if (_digit == 4) {
            str_.append(_unit);
            str_.append(_demi);
        } else if (_digit < 9) {
            str_.append(_demi);
            for (int j = 0; j < _digit - 5; j++) {
                str_.append(_unit);
            }
        } else {
            str_.append(_unit);
            str_.append(_nextUnit);
        }
        return str_;
    }
    public String getNumber() {
        return number;
    }

}
