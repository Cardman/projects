package code.formathtml.render;

import code.util.Ints;

public final class MetaNumberedLabel extends MetaLabel {

    private String number;
    private MetaNumberBase base;

    public MetaNumberedLabel(MetaContainer _parent, int _number, MetaNumberBase _base) {
        super(_parent);
        number = convert(_number,_base);
        base = _base;
    }
    static String convert(int _number, MetaNumberBase _base) {
        if (_base == MetaNumberBase.NUMBER) {
            return Integer.toString(_number);
        }
        if (_base == MetaNumberBase.LETTER || _base == MetaNumberBase.MAJ_LETTER) {
            char firstLetter_;
            if (_base == MetaNumberBase.LETTER) {
                firstLetter_ = 'a';
            } else {
                firstLetter_ = 'A';
            }
            return str(_number,firstLetter_);
        }
        char firstUnit_ = 'i';
        char firstDemi_ = 'v';
        char secondUnit_ = 'x';
        char secondDemi_ = 'l';
        char thirdUnit_ = 'c';
        char thirdDemi_ = 'd';
        char fourthUnit_ = 'm';
        char fourthDemi_ = 'q';
        Ints parts_ = new Ints();
        int current_ = _number;
        while (current_ > 0) {
            parts_.add(current_ % 10000);
            current_ /= 10000;
        }
        StringBuilder str_ = new StringBuilder(parts_.size() * 4);
        for (int i: parts_.getReverse()) {
            if (i < 10) {
                str_.append(getLatinString(i, firstUnit_, firstDemi_, secondUnit_));
            } else if (i < 100) {
                str_.append(getLatinString(i / 10 % 10, secondUnit_, secondDemi_, thirdUnit_));
                str_.append(getLatinString(i % 10, firstUnit_, firstDemi_, secondUnit_));
            } else if (i < 1000) {
                str_.append(getLatinString(i / 100 % 10, thirdUnit_, thirdDemi_, fourthUnit_));
                str_.append(getLatinString(i / 10 % 10, secondUnit_, secondDemi_, thirdUnit_));
                str_.append(getLatinString(i % 10, firstUnit_, firstDemi_, secondUnit_));
            } else {
                int d_ = i/1000-1;
                if (d_ < 3) {
                    for (int j = 0; j <= d_ ; j++) {
                        str_.append(fourthUnit_);
                    }
                } else if (d_ == 3){
                    str_.append(fourthUnit_);
                    str_.append(fourthDemi_);
                } else {
                    str_.append(fourthDemi_);
                    for (int j = 0; j < d_- 4; j++) {
                        str_.append(fourthUnit_);
                    }
                }
                str_.append(getLatinString(i  / 100 % 10, thirdUnit_, thirdDemi_, fourthUnit_));
                str_.append(getLatinString(i / 10 % 10, secondUnit_, secondDemi_, thirdUnit_));
                str_.append(getLatinString(i % 10, firstUnit_, firstDemi_, secondUnit_));
            }
            str_.append(" ");
        }
        str_.deleteCharAt(str_.length() - 1);
        if (_base == MetaNumberBase.LATIN_MAJ) {
            return toLatinUpperCase(str_.toString());
        }
        return str_.toString();
    }
    private static String str(int _nb, char _firstLetter) {
        int n_ = 1;
        long s_ = 0;
        while (s_ < _nb) {
            s_ += pow(n_);
            n_++;
        }
        n_--;
        long inf_ = (pow(n_)-1)/25;
        StringBuilder str_ = new StringBuilder();
        long base_ = _nb - inf_;
        for (int i = 0; i < n_; i++) {
            long q_ = base_ /26;
            long r_ = base_ %26;
            str_.insert(0,(char)(r_+_firstLetter));
            base_ = q_;
        }
        return str_.toString();
    }
    private static long pow(int _n) {
        long p_ = 1;
        for (int i = 0; i < _n; i++) {
            p_ *= 26;
        }
        return p_;
    }
    private static String toLatinUpperCase(String _string) {
        int len_ = _string.length();
        StringBuilder str_ = new StringBuilder(len_);
        for (int i = 0; i < len_; i++) {
            char curr_ = _string.charAt(i);
            if (curr_ <= ' ') {
                str_.append(" ");
                continue;
            }
            int char_ = curr_ - 'a' + 'A';
            str_.append((char)char_);
        }
        return str_.toString();
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
