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
            if (_number <= 26) {
                return Character.toString((char)(_number + firstLetter_ - 1));
            }
            int powTwo_ = 26 * 26;
            if (_number <= 26 + powTwo_) {
                StringBuilder str_ = new StringBuilder(2);
                int rem_ = _number % 26;
                if (rem_ == 0) {
                    char diff_ = (char)((_number-1)/26 + firstLetter_ - 1);
                    str_.append(diff_);
                    diff_ = (char)(firstLetter_ + 26 - 1);
                    str_.append(diff_);
                } else {
                    char diff_ = (char)(_number/26 + firstLetter_ - 1);
                    str_.append(diff_);
                    diff_ = (char)(firstLetter_ + rem_ - 1);
                    str_.append(diff_);
                }
                return str_.toString();
            }
            if (_number <= 26 + powTwo_ + powTwo_ * 26) {
                StringBuilder str_ = new StringBuilder(3);
                int nb_ = (_number- powTwo_ -26)/ (powTwo_);
                char diff_ = (char)(nb_ + firstLetter_);
                int quot_ = (_number - nb_ * powTwo_- powTwo_)/26;
                nb_ = (_number - nb_ * powTwo_- powTwo_)%26;
                if (nb_ == 0) {
                    nb_ = 26;
                    quot_--;
                }
                if (quot_ == 0) {
                    quot_=26;
                    diff_--;
                }
                str_.append(diff_);
                diff_ = (char)(quot_ + firstLetter_ - 1);
                str_.append(diff_);

                diff_ = (char)(nb_ + firstLetter_-1);
                str_.append(diff_);
                return str_.toString();

            }
            Ints parts_ = new Ints();
            int current_ = _number;
            while (current_ > 0) {
                int rem_ = current_ % 26;
                parts_.add(rem_);
                current_ /= 26;
            }
            StringBuilder str_ = new StringBuilder(parts_.size());
            int delta_ = 1;
            for (int i: parts_.getReverse()) {
                char diff_ = (char)(i + firstLetter_ - delta_);
                str_.append(diff_);
                delta_ = 1;
            }
            return str_.toString();
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

    public MetaNumberBase getBase() {
        return base;
    }
}
