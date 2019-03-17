package code.expressionlanguage.stds;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.instr.NumberInfos;

public final class NumParsers {
    private static final int DEFAULT_RADIX = 10;
    private static final long N_MULTMAX_RADIX_TEN = -Long.MAX_VALUE / DEFAULT_RADIX;
    private static final long MULTMIN_RADIX_TEN = Long.MIN_VALUE / DEFAULT_RADIX;
    private static final byte HEX_BASE = 16;
    private static final char DOT_VAR = '.';
    private static final char EXP_UPP = 'E';
    private static final char EXP = 'e';
    private static final char MINUS_CHAR = '-';
    private static final byte MAX_DIGITS_DOUBLE = 18;

    private NumParsers() {
    }

    public static double parseDouble(NumberInfos _nb) {
        StringBuilder int_ = new StringBuilder(_nb.getIntPart());
        while(int_.indexOf("_") >= 0) {
            int_.deleteCharAt(int_.indexOf("_"));
        }
        StringBuilder dec_ = new StringBuilder(_nb.getDecimalPart());
        while(dec_.indexOf("_") >= 0) {
            dec_.deleteCharAt(dec_.indexOf("_"));
        }
        StringBuilder exp_ = new StringBuilder(_nb.getExponentialPart());
        while(exp_.indexOf("_") >= 0) {
            exp_.deleteCharAt(exp_.indexOf("_"));
        }
        boolean positive_ = _nb.isPositive();
        Long expNb_;
        if (exp_.length() == 0) {
            expNb_ = 0L;
        } else {
            expNb_ = parseLongTen(exp_.toString());
        }
        if (expNb_ == null) {
            if (positive_) {
                if (exp_.charAt(0) == '-') {
                    return 0.0;
                }
                return Double.POSITIVE_INFINITY;
            }
            if (exp_.charAt(0) == '-') {
                return -0.0;
            }
            return Double.NEGATIVE_INFINITY;
        }
        long expNbLong_ = expNb_;
        if (_nb.getBase() == 16) {
            StringBuilder merged_ = new StringBuilder(int_.length()+dec_.length());
            merged_.append(int_);
            merged_.append(dec_);
            double parsed_ = parseDoubleSixteen(merged_.toString());
            long delta_ = expNbLong_;
            delta_ -= 4 * dec_.length();
            double p_ = 1.0;
            long absExpNbLong_ = Math.abs(delta_);
            for (int i = 0; i < absExpNbLong_; i++) {
                p_ *= 2;
            }
            if (delta_ > 0) {
                return parsed_ * p_;
            }
            return parsed_ / p_;
        }
        if (_nb.getBase() == 2) {
            StringBuilder merged_ = new StringBuilder(int_.length()+dec_.length());
            merged_.append(int_);
            merged_.append(dec_);
            double parsed_ = parseDoubleBinary(merged_.toString());
            long delta_ = expNbLong_;
            delta_ -= dec_.length();
            double p_ = 1.0;
            long absExpNbLong_ = Math.abs(delta_);
            for (int i = 0; i < absExpNbLong_; i++) {
                p_ *= 2;
            }
            if (delta_ > 0) {
                return parsed_ * p_;
            }
            return parsed_ / p_;
        }
        if (_nb.getBase() == 8) {
            StringBuilder merged_ = new StringBuilder(int_.length()+dec_.length());
            merged_.append(int_);
            merged_.append(dec_);
            double parsed_ = parseDoubleOctal(merged_.toString());
            long delta_ = expNbLong_;
            delta_ -= 3*dec_.length();
            double p_ = 1.0;
            long absExpNbLong_ = Math.abs(delta_);
            for (int i = 0; i < absExpNbLong_; i++) {
                p_ *= 2;
            }
            if (delta_ > 0) {
                return parsed_ * p_;
            }
            return parsed_ / p_;
        }
        if (dec_.length() == 0) {
            if (expNbLong_ == 0) {
                double long_;
                if (int_.length() > MAX_DIGITS_DOUBLE) {
                    return processBigNumbers(int_, positive_);
                }
                long_ = parseQuickLongTen(int_.toString());
                if (!positive_) {
                    return -long_;
                }
                return long_;
            }
            double long_;
            if (int_.length() > MAX_DIGITS_DOUBLE) {
                long_ = parseQuickLongTen(int_.substring(0, MAX_DIGITS_DOUBLE + 1));
                expNbLong_ += int_.length() - MAX_DIGITS_DOUBLE - 1;
            } else {
                long_ = parseQuickLongTen(int_.toString());
            }
            double power_ = 1;
            long absExp_ = Math.abs(expNbLong_);
            for (long i = 0; i < absExp_; i++) {
                power_ *= 10d;
            }
            if (!positive_) {
                if (expNbLong_ > 0) {
                    return -long_ * power_;
                }
                return -long_ / power_;
            }
            if (expNbLong_ > 0) {
                return long_ * power_;
            }
            return long_ / power_;
        }
        if (expNbLong_ >= dec_.length()) {
            //try to get "double" as int
            StringBuilder number_ = new StringBuilder(int_.length()+dec_.length());
                    number_.append(int_);
                    number_.append(dec_);
                    int diff_ = (int)expNbLong_-dec_.length();
                    for (long i = 0; i < diff_; i++) {
                        number_.append("0");
                    }
                    if (number_.length() > MAX_DIGITS_DOUBLE) {
                        return processBigNumbers(number_, positive_);
                    }
                    double long_ = parseQuickLongTen(number_.toString());
                    if (!positive_) {
                        return -long_;
                    }
                    return long_;
        }
        if (-expNbLong_ >= int_.length()) {
            StringBuilder number_ = new StringBuilder(int_);
            number_.append(dec_);
            int nbLeadingZeros_;
            StringBuilder decCopy_ = new StringBuilder();
            int index_ = 0;
            while (index_ < number_.length()) {
                if (number_.charAt(index_) != '0') {
                    break;
                }
                index_++;
            }
            nbLeadingZeros_ = index_;
            decCopy_.append(number_.substring(nbLeadingZeros_));
            if (decCopy_.length() == 0) {
                if (!positive_) {
                    return -0.0;
                }
                return 0.0;
            }
            double value_;
            int diff_;
            if (decCopy_.length() > MAX_DIGITS_DOUBLE) {
                value_ = parseQuickLongTen(decCopy_.substring(0, MAX_DIGITS_DOUBLE + 1));
                diff_ = (int) (-expNbLong_ - int_.length() + MAX_DIGITS_DOUBLE + 1 + nbLeadingZeros_);
            } else {
                value_ = parseQuickLongTen(decCopy_.toString());
                diff_ = (int) (-expNbLong_ + dec_.length());
            }
            double power_ = 1;
            for (int i = 0; i < diff_; i++) {
                power_ *= 10d;
            }
            if (!positive_) {
                return -value_ / power_;
            }
            return value_ / power_;
        }
        StringBuilder numberInt_ = new StringBuilder();
        StringBuilder numberDec_ = new StringBuilder();
        if (expNbLong_ > 0) {
            //expNbLong_ < dec_.length() => dec_.length() > 0 => numberInt_.length() > 0
                    //-expNbLong_ < int_.length()
                    numberInt_.append(int_);
            numberInt_.append(dec_.substring(0, (int) expNbLong_));
            numberDec_.append(dec_.substring((int)expNbLong_));
        } else if (expNbLong_ == 0) {
            //expNbLong_ < dec_.length() => 0 < dec_.length()
                    //-expNbLong_ < int_.length() => 0 < int_.length() => numberInt_.length() > 0
                    numberInt_.append(int_);
                    numberDec_.append(dec_);
        } else {
            //expNbLong_ < 0
            int del_ = int_.length() +(int)expNbLong_;
            //-expNbLong_ < int_.length() => 0 < -expNbLong_ < int_.length() => 0 < int_.length()
                    //-expNbLong_ < int_.length() => 0 < expNbLong_ + int_.length() => numberInt_.length() > 0
                    numberInt_.append(int_.substring(0, del_));
                    numberDec_.append(int_.substring(del_));
                    numberDec_.append(dec_);
        }
        if (numberInt_.length() > MAX_DIGITS_DOUBLE) {
            return processBigNumbers(numberInt_, positive_);
        }
        double longValue_ = parseQuickLongTen(numberInt_.toString());
        StringBuilder decCopy_ = new StringBuilder();
        int nbLeadingZeros_;
        int index_ = 0;
        while (index_ < numberDec_.length()) {
            if (numberDec_.charAt(index_) != '0') {
                break;
            }
            index_++;
        }
        nbLeadingZeros_ = index_;
        decCopy_.append(numberDec_.substring(nbLeadingZeros_));
        decCopy_.delete(Math.min(MAX_DIGITS_DOUBLE + 1, decCopy_.length()), decCopy_.length());
        if (decCopy_.length() == 0) {
            if (!positive_) {
                return -longValue_;
            }
            return longValue_;
        }
        double decValue_ = parseQuickLongTen(decCopy_.toString());
        double power_ = 1;
        int logDec_ = numberDec_.length();
        for (int i = 0; i < logDec_; i++) {
            power_ *= 10d;
        }
        if (!positive_) {
            return -longValue_ - decValue_ / power_;
        }
        return longValue_ + decValue_ / power_;
    }

    private static double processBigNumbers(StringBuilder _nb, boolean _positive) {
        Long long_ = parseQuickLongTen(_nb.substring(0, (int) MAX_DIGITS_DOUBLE + 1));
        double power_ = 1;
        int logDec_ = _nb.length() - (int) MAX_DIGITS_DOUBLE - 1;
        for (int i = 0; i < logDec_; i++) {
            power_ *= 10d;
        }
        double out_ = long_.doubleValue() * power_;
        if (_positive) {
            return out_;
        }
        return -out_;
    }

    //this long parser is very naive
    public static char parseCharSixteen(String _string) {
        int result_ = 0;
        int i_ = 0;
        int max_ = _string.length();
        while (i_ < max_) {
            int ch_ = _string.charAt(i_);
            if (ch_ >= 'A' && ch_ <= 'F') {
                ch_ = ch_ - 'A' + 'a';
            }
            i_++;
            int digit_ = Math.min(ch_ - '0', 10) + Math.max(ch_ - 'a', 0);
            result_ *= HEX_BASE;
            result_ += digit_;
        }
        return (char)result_;
    }

    public static boolean[] toBits(long _l) {
        boolean[] bits_ = new boolean[64];
        long t_ = _l;
        if (_l < 0) {
            bits_[0] = true;
            t_ = Long.MAX_VALUE + _l + 1;
        }
        int k_ = 63;
        for (int i = 0; i < 63; i++) {
            if (t_ % 2 == 1) {
                bits_[k_] = true;
            }
            k_--;
            t_ /= 2;
        }
        return bits_;
    }

    public static boolean[] toBits(int _l) {
        boolean[] bits_ = new boolean[32];
        long t_ = _l;
        if (_l < 0) {
            bits_[0] = true;
            t_ = Integer.MAX_VALUE + _l + 1;
        }
        int k_ = 31;
        for (int i = 0; i < 31; i++) {
            if (t_ % 2 == 1) {
                bits_[k_] = true;
            }
            k_--;
            t_ /= 2;
        }
        return bits_;
    }

    public static long toLong(boolean[] _bits) {
        long s_ = 0;
        for (int i = 1; i < 64; i++) {
            s_ *= 2;
            if (_bits[i]) {
                s_++;
            }
        }
        if (_bits[0]) {
            return s_ - Long.MAX_VALUE - 1;
        }
        return s_;
    }

    public static int toInt(boolean[] _bits) {
        int s_ = 0;
        for (int i = 1; i < 32; i++) {
            s_ *= 2;
            if (_bits[i]) {
                s_++;
            }
        }
        if (_bits[0]) {
            return s_ - Integer.MAX_VALUE - 1;
        }
        return s_;
    }

    public static int extractInt(boolean[] _bits) {
        int s_ = 0;
        for (int i = 33; i < 64; i++) {
            s_ *= 2;
            if (_bits[i]) {
                s_++;
            }
        }
        if (_bits[32]) {
            return s_ - Integer.MAX_VALUE - 1;
        }
        return s_;
    }

    public static short extractShort(boolean[] _bits) {
        int s_ = 0;
        for (int i = 49; i < 64; i++) {
            s_ *= 2;
            if (_bits[i]) {
                s_++;
            }
        }
        if (_bits[48]) {
            return (short) (s_ - Short.MAX_VALUE - 1);
        }
        return (short) s_;
    }

    public static byte extractByte(boolean[] _bits) {
        int s_ = 0;
        for (int i = 57; i < 64; i++) {
            s_ *= 2;
            if (_bits[i]) {
                s_++;
            }
        }
        if (_bits[56]) {
            return (byte) (s_ - Byte.MAX_VALUE - 1);
        }
        return (byte) s_;
    }

    public static int toUnsignedInt(boolean[] _bits, int _max) {
        int s_ = 0;
        for (int i = 32 - _max+1; i < 32; i++) {
            s_ *= 2;
            if (_bits[i]) {
                s_++;
            }
        }
        return s_;
    }

    public static long toUnsignedLong(boolean[] _bits, int _max) {
        int s_ = 0;
        for (int i = 64 - _max +1; i < 64; i++) {
            s_ *= 2;
            if (_bits[i]) {
                s_++;
            }
        }
        return s_;
    }

    public static boolean[] parseLongSixteenToBits(String _string) {
        StringBuilder str_;
        if (_string.length() < 16) {
            str_ = new StringBuilder();
            int add_ = 16 - _string.length();
            for (int i = 0; i < add_; i++) {
                str_.append("0");
            }
            for (int i = add_; i < 16; i++) {
                str_.append(_string.charAt(i - add_));
            }
        } else {
            str_ = new StringBuilder(_string);
        }
        boolean[] out_ = new boolean[str_.length() * 4];
        int i_ = 0;
        int j_ = 0;
        int max_ = str_.length();
        while (i_ < max_) {
            // Accumulating negatively avoids surprises near MAX_VALUE
            int ch_ = str_.charAt(i_);
            if (ch_ >= 'A' && ch_ <= 'F') {
                ch_ = ch_ - 'A' + 'a';
            }
            i_++;
            int t_ = Math.min(ch_ - '0', 10) + Math.max(ch_ - 'a', 0);
            int k_ = 3;
            for (int j = 0; j < 4; j++) {
                if (t_ % 2 == 1) {
                    out_[j_ + k_] = true;
                }
                k_--;
                t_ /= 2;
            }
            j_ += 4;
        }
        return out_;
    }

    public static boolean[] parseLongOctalToBits(String _string) {
        StringBuilder str_;
        if (_string.length() < 21) {
            str_ = new StringBuilder();
            int add_ = 21 - _string.length();
            for (int i = 0; i < add_; i++) {
                str_.append("0");
            }
            for (int i = add_; i < 21; i++) {
                str_.append(_string.charAt(i - add_));
            }
        } else {
            str_ = new StringBuilder(_string);
        }
        int j_ = 0;
        boolean[] out_ = new boolean[str_.length()*3];
        int i_ = 0;
        int max_ = str_.length();
        while (i_ < max_) {
            int ch_ = str_.charAt(i_);
            i_++;
            int t_ = ch_ - '0';
            int k_ = 2;
            for (int j = 0; j < 3; j++) {
                if (t_ % 2 == 1) {
                    out_[j_ + k_] = true;
                }
                k_--;
                t_ /= 2;
            }
            j_ += 3;
        }
        return out_;
    }

    public static boolean[] parseLongBinaryToBits(String _string) {
        StringBuilder str_;
        if (_string.length() < 64) {
            str_ = new StringBuilder();
            int add_ = 64 - _string.length();
            for (int i = 0; i < add_; i++) {
                str_.append("0");
            }
            for (int i = add_; i < 64; i++) {
                str_.append(_string.charAt(i - add_));
            }
        } else {
            str_ = new StringBuilder(_string);
        }
        boolean[] out_ = new boolean[str_.length()];
        int i_ = 0;
        int max_ = str_.length();
        while (i_ < max_) {
            // Accumulating negatively avoids surprises near MAX_VALUE
            int ch_ = str_.charAt(i_);
            if (ch_ == '1') {
                out_[i_] = true;
            }
            i_++;
        }
        return out_;
    }

    public static double parseDoubleSixteen(String _string) {
        double result_ = 0;
        int i_ = 0;
        int max_ = _string.length();
        while (i_ < max_) {
            int ch_ = _string.charAt(i_);
            if (ch_ >= 'A' && ch_ <= 'F') {
                ch_ = ch_ - 'A' + 'a';
            }
            i_++;
            int digit_ = Math.min(ch_ - '0', 10) + Math.max(ch_ - 'a', 0);
            result_ *= HEX_BASE;
            result_ += digit_;
        }
        return result_;
    }

    public static double parseDoubleOctal(String _string) {
        double result_ = 0;
        int i_ = 0;
        int max_ = _string.length();
        while (i_ < max_) {
            int ch_ = _string.charAt(i_);
            i_++;
            int digit_ = ch_ - '0';
            result_ *= 8;
            result_ += digit_;
        }
        return result_;
    }

    public static double parseDoubleBinary(String _string) {
        double result_ = 0;
        int i_ = 0;
        int max_ = _string.length();
        while (i_ < max_) {
            int ch_ = _string.charAt(i_);
            i_++;
            int digit_ = ch_ - '0';
            result_ *= 2;
            result_ += digit_;
        }
        return result_;
    }

    //this long parser is very naive
    public static Long parseLongTen(String _string) {
        long result_ = 0;
        boolean negative_ = false;
        int i_ = 0;
        int max_ = _string.length();
        long limit_;
        long multmin_;
        int digit_;

        if (_string.charAt(0) == '-') {
            negative_ = true;
            limit_ = Long.MIN_VALUE;
            i_++;
        } else {
            limit_ = -Long.MAX_VALUE;
        }
        if (negative_) {
            multmin_ = MULTMIN_RADIX_TEN;
        } else {
            multmin_ = N_MULTMAX_RADIX_TEN;
        }
        int ch_ = _string.charAt(i_);
        i_++;
        digit_ = ch_ - '0';
        result_ = -digit_;
        while (i_ < max_) {
            // Accumulating negatively avoids surprises near MAX_VALUE
            ch_ = _string.charAt(i_);
            i_++;
            digit_ = ch_ - '0';
            if (result_ < multmin_) {
                return null;
            }
            result_ *= DEFAULT_RADIX;
            if (result_ < limit_ + digit_) {
                return null;
            }
            result_ -= digit_;
        }
        if (negative_) {
            return result_;
        }
        return -result_;
    }

    public static long parseQuickLongTen(String _string) {
        int i_ = 0;
        int max_ = _string.length();
        int digit_;
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
        return -result_;
    }

    public static Long parseLong(String _string, int _radix) {
        if (_radix < Character.MIN_RADIX) {
            return null;
        }
        if (_radix > Character.MAX_RADIX) {
            return null;
        }

        long result_ = 0;
        boolean negative_ = false;
        int i_ = 0;
        int max_ = _string.length();
        long limit_;
        long multmin_;
        int digit_;

        if (max_ > 0) {
            if (_string.charAt(0) == '-') {
                negative_ = true;
                limit_ = Long.MIN_VALUE;
                i_++;
            } else {
                limit_ = -Long.MAX_VALUE;
            }
            if (_radix == DEFAULT_RADIX) {
                if (negative_) {
                    multmin_ = MULTMIN_RADIX_TEN;
                } else {
                    multmin_ = N_MULTMAX_RADIX_TEN;
                }
            } else {
                multmin_ = limit_ / _radix;
            }
            if (i_ < max_) {
                int ch_ = _string.charAt(i_);
                if (!parsableChar(ch_)) {
                    return null;
                }
                if (ch_ >= 'A' && ch_ <= 'Z') {
                    ch_ = ch_ - 'A' + 'a';
                }
                i_++;
                int dig_ = Math.min(ch_ - '0', 10) + Math.max(ch_ - 'a', 0);
                if (dig_ >= _radix) {
                    return null;
                }
                digit_ = dig_;
                result_ = -digit_;
            }
            while (i_ < max_) {
                // Accumulating negatively avoids surprises near MAX_VALUE
                int ch_ = _string.charAt(i_);
                if (!parsableChar(ch_)) {
                    return null;
                }
                if (ch_ >= 'A' && ch_ <= 'Z') {
                    ch_ = ch_ - 'A' + 'a';
                }
                i_++;
                int dig_ = Math.min(ch_ - '0', 10) + Math.max(ch_ - 'a', 0);
                if (dig_ >= _radix) {
                    return null;
                }
                digit_ = dig_;
                if (result_ < multmin_) {
                    return null;
                }
                result_ *= _radix;
                if (result_ < limit_ + digit_) {
                    return null;
                }
                result_ -= digit_;
            }
        } else {
            return null;
        }
        if (negative_) {
            if (i_ > 1) {
                return result_;
            }
            return null;
        }
        return -result_;
    }

    private static boolean parsableChar(int _ch) {
        if (_ch >= '0' && _ch <= '9') {
            return true;
        }
        if (_ch >= 'a' && _ch <= 'z') {
            return true;
        }
        return _ch >= 'A' && _ch <= 'Z';
    }

    public static NumberInfos trySplitDouble(String _nb) {
        if (_nb.isEmpty()) {
            return null;
        }
        NumberInfos infos_ = new NumberInfos();
        int i_ = 0;
        if (!ContextEl.isDigit(_nb.charAt(i_))) {
            if (_nb.charAt(i_) != MINUS_CHAR) {
                if (_nb.charAt(i_) != DOT_VAR) {
                    return null;
                }
                infos_.setPositive(true);
            } else {
                infos_.setPositive(false);
                i_++;
            }
        } else {
            infos_.setPositive(true);
        }
        int len_ = _nb.length();
        StringBuilder intPart_ = new StringBuilder();
        infos_.setIntPart(intPart_);
        StringBuilder decimalPart_ = new StringBuilder();
        infos_.setDecimalPart(decimalPart_);
        StringBuilder exponentialPart_ = new StringBuilder();
        infos_.setExponentialPart(exponentialPart_);
        while (i_ < len_) {
            char cur_ = _nb.charAt(i_);
            if (!ContextEl.isDigit(cur_)) {
                if (cur_ != DOT_VAR) {
                    if (cur_ != EXP && cur_ != EXP_UPP) {
                        return null;
                    }
                }
                break;
            }
            intPart_.append(cur_);
            i_++;
        }
        if (i_ >= len_) {
            return infos_;
        }
        if (_nb.charAt(i_) == DOT_VAR) {
            i_++;
            while (i_ < len_) {
                char cur_ = _nb.charAt(i_);
                if (!ContextEl.isDigit(cur_)) {
                    if (cur_ != EXP && cur_ != EXP_UPP) {
                        return null;
                    }
                    break;
                }
                decimalPart_.append(cur_);
                i_++;
            }
        }
        if (i_ >= len_) {
            return infos_;
        }
        i_++;
        if (i_ >= len_) {
            return null;
        }
        char cur_ = _nb.charAt(i_);
        if (!ContextEl.isDigit(cur_) && cur_ != MINUS_CHAR) {
            return null;
        }
        i_++;
        exponentialPart_.append(cur_);
        while (i_ < len_) {
            cur_ = _nb.charAt(i_);
            if (!ContextEl.isDigit(cur_)) {
                return null;
            }
            exponentialPart_.append(cur_);
            i_++;
        }
        return infos_;
    }
}
