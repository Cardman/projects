package code.expressionlanguage.common;

import code.expressionlanguage.inherits.ClassArgumentMatching;
import code.expressionlanguage.structs.*;
import code.util.CustList;
import code.util.Numbers;
import code.util.Replacement;
import code.util.StringList;

public final class NumParsers {
    private static final int DEFAULT_RADIX = 10;
    private static final long N_MULTMAX_RADIX_TEN = -Long.MAX_VALUE / DEFAULT_RADIX;
    private static final long MULTMIN_RADIX_TEN = Long.MIN_VALUE / DEFAULT_RADIX;
    private static final byte HEX_BASE = 16;
    private static final char DOT_VAR = '.';
    private static final char EXP_UPP = 'E';
    private static final char EXP = 'e';
    private static final char PLUS_CHAR = '+';
    private static final char MINUS_CHAR = '-';
    private static final byte MAX_DIGITS_DOUBLE = 18;

    private NumParsers() {
    }
    public static Struct parseNb(NumberInfos _infosNb) {
        char suffix_ = _infosNb.getSuffix();
        if (_infosNb.isError()) {
            return NullStruct.NULL_VALUE;
        }
        if (suffix_ == 'D' || suffix_ == 'd' || suffix_ == 'F' || suffix_ == 'f') {
            DoubleInfo doubleInfo_ = NumParsers.parseDoubleOrInvalid(_infosNb);
            if (suffix_ == 'D' || suffix_ == 'd') {
                return new DoubleStruct(doubleInfo_.getValue());
            }
            if (doubleInfo_.outOfRange(Float.MIN_VALUE,Float.MAX_VALUE)) {
                return NullStruct.NULL_VALUE;
            }
            return new FloatStruct((float) doubleInfo_.getValue());
        }
        StringBuilder nbFormatted_ = _infosNb.getIntPart();
        String nb_ = StringList.removeChars(StringList.removeAllSpaces(nbFormatted_.toString()), '_');
        if (_infosNb.getBase() == 16) {
            if (nb_.length() > 16) {
                return NullStruct.NULL_VALUE;
            }
            boolean[] bits_ = NumParsers.parseLongSixteenToBits(nb_);
            if (suffix_ == 'L' || suffix_ == 'l') {
                long longValue_ = NumParsers.toLong(bits_);
                return new LongStruct(longValue_);
            }
            if (suffix_ == 'I' || suffix_ == 'i') {
                if (nb_.length() > 8) {
                    return NullStruct.NULL_VALUE;
                }
                int int_ = NumParsers.extractInt(bits_);
                return new IntStruct(int_);
            }
            if (suffix_ == 'C' || suffix_ == 'c') {
                if (nb_.length() > 4) {
                    return NullStruct.NULL_VALUE;
                }
                char int_ = NumParsers.parseCharSixteen(nb_);
                return new CharStruct(int_);
            }
            if (suffix_ == 'S' || suffix_ == 's') {
                if (nb_.length() > 4) {
                    return NullStruct.NULL_VALUE;
                }
                short int_ = NumParsers.extractShort(bits_);
                return new ShortStruct(int_);
            }
            if (nb_.length() > 2) {
                return NullStruct.NULL_VALUE;
            }
            byte int_ = NumParsers.extractByte(bits_);
            return new ByteStruct(int_);
        }
        if (_infosNb.getBase() == 2) {
            if (nb_.length() > 64) {
                return NullStruct.NULL_VALUE;
            }
            boolean[] bits_ = NumParsers.parseLongBinaryToBits(nb_);
            if (suffix_ == 'L' || suffix_ == 'l') {
                long longValue_ = NumParsers.toLong(bits_);
                return new LongStruct(longValue_);
            }
            if (suffix_ == 'I' || suffix_ == 'i') {
                if (nb_.length() > 32) {
                    return NullStruct.NULL_VALUE;
                }
                int int_ = NumParsers.extractInt(bits_);
                return new IntStruct(int_);
            }
            if (suffix_ == 'C' || suffix_ == 'c') {
                if (nb_.length() > 16) {
                    return NullStruct.NULL_VALUE;
                }
                char int_ = (char) NumParsers.extractShort(bits_);
                return new CharStruct(int_);
            }
            if (suffix_ == 'S' || suffix_ == 's') {
                if (nb_.length() > 16) {
                    return NullStruct.NULL_VALUE;
                }
                short int_ = NumParsers.extractShort(bits_);
                return new ShortStruct(int_);
            }
            if (nb_.length() > 8) {
                return NullStruct.NULL_VALUE;
            }
            byte int_ = NumParsers.extractByte(bits_);
            return new ByteStruct(int_);
        }
        if (_infosNb.getBase() == 8) {
            if (suffix_ == 'L' || suffix_ == 'l') {
                if (nb_.length() > 22) {
                    return NullStruct.NULL_VALUE;
                }
                int sub_ = 0;
                boolean rev_ = false;
                if (nb_.length() == 22) {
                    if (nb_.charAt(0) != '0' && nb_.charAt(0) != '1') {
                        return NullStruct.NULL_VALUE;
                    }
                    rev_ = nb_.charAt(0) == '1';
                    sub_ = 1;
                }
                String subString_ = nb_.substring(sub_);
                LongInfo lg_ = NumParsers.parseLong(subString_, 8);
                if (!lg_.isValid()) {
                    return NullStruct.NULL_VALUE;
                }
                boolean[] bitsOutTrunc_ = NumParsers.parseLongOctalToBits(subString_);
                long longValue_ = NumParsers.toLong(bitsOutTrunc_,rev_,0,63);
                return new LongStruct(longValue_);
            }
            if (suffix_ == 'C' || suffix_ == 'c') {
                if (nb_.length() > 6) {
                    return NullStruct.NULL_VALUE;
                }
                if (nb_.length() == 6) {
                    if (nb_.charAt(0) != '0' && nb_.charAt(0) != '1') {
                        return NullStruct.NULL_VALUE;
                    }
                }
                LongInfo lg_ = NumParsers.parseLong(nb_, 8);
                if (!lg_.isValid()) {
                    return NullStruct.NULL_VALUE;
                }
                return new CharStruct((char) lg_.getValue());
            }
            if (suffix_ == 'I' || suffix_ == 'i') {
                if (nb_.length() > 11) {
                    return NullStruct.NULL_VALUE;
                }
                if (nb_.length() == 11) {
                    if (nb_.charAt(0) != '0' && nb_.charAt(0) != '1' && nb_.charAt(0) != '2' && nb_.charAt(0) != '3') {
                        return NullStruct.NULL_VALUE;
                    }
                }
            } else if (suffix_ == 'S' || suffix_ == 's') {
                if (nb_.length() > 6) {
                    return NullStruct.NULL_VALUE;
                }
                if (nb_.length() == 6) {
                    if (nb_.charAt(0) != '0' && nb_.charAt(0) != '1' && nb_.charAt(0) != '2' && nb_.charAt(0) != '3') {
                        return NullStruct.NULL_VALUE;
                    }
                }
            } else {
                if (nb_.length() > 3) {
                    return NullStruct.NULL_VALUE;
                }
                if (nb_.length() == 3) {
                    if (nb_.charAt(0) != '0' && nb_.charAt(0) != '1' && nb_.charAt(0) != '2' && nb_.charAt(0) != '3') {
                        return NullStruct.NULL_VALUE;
                    }
                }
            }
            LongInfo lg_ = NumParsers.parseLong(nb_, 8);
            if (!lg_.isValid()) {
                return NullStruct.NULL_VALUE;
            }
            long value_ = lg_.getValue();
            if (suffix_ == 'I' || suffix_ == 'i') {
                if (value_ >= Integer.MAX_VALUE + 1L) {
                    while (value_ >= 0) {
                        value_ -= Integer.MAX_VALUE;
                        value_--;
                    }
                }
                return new IntStruct((int) value_);
            }
            if (suffix_ == 'S' || suffix_ == 's') {
                if (value_ >= Short.MAX_VALUE + 1L) {
                    while (value_ >= 0) {
                        value_ -= Short.MAX_VALUE;
                        value_--;
                    }
                }
                return new ShortStruct((short) value_);
            }
            if (value_ >= Byte.MAX_VALUE + 1L) {
                while (value_ >= 0) {
                    value_ -= Byte.MAX_VALUE;
                    value_--;
                }
            }
            return new ByteStruct((byte) value_);
        }
        LongInfo longValue_ = NumParsers.parseLong(nb_, 10);
        if (!longValue_.isValid()) {
            String str_  = StringList.concat("-",nb_);
            LongInfo oppLongValue_ = NumParsers.parseLong(str_, 10);
            if (oppLongValue_.isValid()) {
                if (suffix_ == 'L' || suffix_ == 'l') {
                    return new LongStruct(Long.MIN_VALUE);
                }
            }
            return NullStruct.NULL_VALUE;
        }
        long value_ = longValue_.getValue();
        if (suffix_ == 'L' || suffix_ == 'l') {
            return new LongStruct(value_);
        }
        if (suffix_ == 'I' || suffix_ == 'i') {
            if (value_ > (long) Integer.MAX_VALUE) {
                if (value_ == Integer.MAX_VALUE + 1L) {
                    return new IntStruct(Integer.MIN_VALUE);
                }
                return NullStruct.NULL_VALUE;
            }
            return new IntStruct((int) value_);
        }
        if (suffix_ == 'S' || suffix_ == 's') {
            if (value_ > (long) Short.MAX_VALUE) {
                if (value_ == Short.MAX_VALUE + 1L) {
                    return new ShortStruct(Short.MIN_VALUE);
                }
                return NullStruct.NULL_VALUE;
            }
            return new ShortStruct((short) value_);
        }
        if (suffix_ == 'B' || suffix_ == 'b') {
            if (value_ > (long) Byte.MAX_VALUE) {
                if (value_ == Byte.MAX_VALUE + 1L) {
                    return new ByteStruct(Byte.MIN_VALUE);
                }
                return NullStruct.NULL_VALUE;
            }
            return new ByteStruct((byte) value_);
        }
        if (value_ > (long) Character.MAX_VALUE) {
            return NullStruct.NULL_VALUE;
        }
        return new CharStruct((char) value_);
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
        LongInfo expNb_;
        if (exp_.length() == 0) {
            expNb_ = new LongInfo(0);
        } else {
            String str_ = exp_.toString();
            if (str_.startsWith("+")) {
                str_ = str_.substring(1);
            }
            expNb_ = parseLong(str_, 10);
        }
        if (!expNb_.isValid()) {
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
        long expNbLong_ = expNb_.getValue();
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
                p_ *= 2.0;
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
                p_ *= 2.0;
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
                p_ *= 2.0;
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
                long_ = (double)parseQuickLongTen(int_.toString());
                if (!positive_) {
                    return -long_;
                }
                return long_;
            }
            double long_;
            if (int_.length() > MAX_DIGITS_DOUBLE) {
                long_ = (double)parseQuickLongTen(int_.substring(0, MAX_DIGITS_DOUBLE + 1));
                expNbLong_ += int_.length() - MAX_DIGITS_DOUBLE - 1;
            } else {
                long_ = (double)parseQuickLongTen(int_.toString());
            }
            double power_ = 1.0;
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
            double long_ = (double)parseQuickLongTen(number_.toString());
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
                value_ = (double) parseQuickLongTen(decCopy_.substring(0, MAX_DIGITS_DOUBLE + 1));
                diff_ = (int) (-expNbLong_ - int_.length() + MAX_DIGITS_DOUBLE + 1 + nbLeadingZeros_);
            } else {
                value_ = (double) parseQuickLongTen(decCopy_.toString());
                diff_ = (int) (-expNbLong_ + dec_.length());
            }
            double power_ = 1.0;
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
        double longValue_ = (double)parseQuickLongTen(numberInt_.toString());
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
        double power_ = 1.0;
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
        double long_ = (double) parseQuickLongTen(_nb.substring(0, (int) MAX_DIGITS_DOUBLE + 1));
        double power_ = 1.0;
        int logDec_ = _nb.length() - (int) MAX_DIGITS_DOUBLE - 1;
        for (int i = 0; i < logDec_; i++) {
            power_ *= 10d;
        }
        double out_ = long_ * power_;
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
            t_ = (long) Integer.MAX_VALUE + _l + 1;
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
        return toLong(_bits,_bits[0],1,64);
    }

    public static long toLong(boolean[] _bits, boolean _reverse, int _from, int _to) {
        long s_ = 0;
        for (int i = _from; i < _to; i++) {
            s_ *= 2;
            if (_bits[i]) {
                s_++;
            }
        }
        if (_reverse) {
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

    private static double parseDoubleSixteen(String _string) {
        long result_ = 0;
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
        return (double)result_;
    }

    private static double parseDoubleOctal(String _string) {
        long result_ = 0;
        int i_ = 0;
        int max_ = _string.length();
        while (i_ < max_) {
            int ch_ = _string.charAt(i_);
            i_++;
            int digit_ = ch_ - '0';
            result_ *= 8;
            result_ += digit_;
        }
        return (double)result_;
    }

    private static double parseDoubleBinary(String _string) {
        long result_ = 0;
        int i_ = 0;
        int max_ = _string.length();
        while (i_ < max_) {
            int ch_ = _string.charAt(i_);
            i_++;
            int digit_ = ch_ - '0';
            result_ *= 2;
            result_ += digit_;
        }
        return (double)result_;
    }

    private static long parseQuickLongTen(String _string) {
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

    public static LongInfo parseLong(String _string, int _radix) {
        if (_radix < Character.MIN_RADIX) {
            return new LongInfo();
        }
        if (_radix > Character.MAX_RADIX) {
            return new LongInfo();
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
                    return new LongInfo();
                }
                if (ch_ >= 'A' && ch_ <= 'Z') {
                    ch_ = ch_ - 'A' + 'a';
                }
                i_++;
                int dig_ = Math.min(ch_ - '0', 10) + Math.max(ch_ - 'a', 0);
                if (dig_ >= _radix) {
                    return new LongInfo();
                }
                digit_ = dig_;
                result_ = -digit_;
            }
            while (i_ < max_) {
                // Accumulating negatively avoids surprises near MAX_VALUE
                int ch_ = _string.charAt(i_);
                if (!parsableChar(ch_)) {
                    return new LongInfo();
                }
                if (ch_ >= 'A' && ch_ <= 'Z') {
                    ch_ = ch_ - 'A' + 'a';
                }
                i_++;
                int dig_ = Math.min(ch_ - '0', 10) + Math.max(ch_ - 'a', 0);
                if (dig_ >= _radix) {
                    return new LongInfo();
                }
                digit_ = dig_;
                if (result_ < multmin_) {
                    return new LongInfo();
                }
                result_ *= _radix;
                if (result_ < limit_ + digit_) {
                    return new LongInfo();
                }
                result_ -= digit_;
            }
        } else {
            return new LongInfo();
        }
        if (negative_) {
            if (i_ > 1) {
                return new LongInfo(result_);
            }
            return new LongInfo();
        }
        return new LongInfo(-result_);
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

    public static DoubleInfo splitDouble(String _nb) {
        NumberInfos nb_ = trySplitDouble(_nb);
        return parseDoubleOrInvalid(nb_);
    }
    public static DoubleInfo parseDoubleOrInvalid(NumberInfos _nb) {
        if (_nb == null) {
            return new DoubleInfo();
        }
        return new DoubleInfo(parseDouble(_nb));
    }
    private static NumberInfos trySplitDouble(String _nb) {
        if (_nb.isEmpty()) {
            return null;
        }
        NumberInfos infos_ = new NumberInfos();
        int i_ = 0;
        if (!StringExpUtil.isDigit(_nb.charAt(i_))) {
            if (_nb.charAt(i_) != MINUS_CHAR) {
                if (_nb.charAt(i_) != DOT_VAR && _nb.charAt(i_) != PLUS_CHAR) {
                    return null;
                }
                infos_.setPositive(true);
                if (_nb.charAt(i_) == PLUS_CHAR) {
                    i_++;
                }
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
            if (!StringExpUtil.isDigit(cur_)) {
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
        if (intPart_.length() > 0 && i_ >= len_) {
            return infos_;
        }
        if (_nb.charAt(i_) == DOT_VAR) {
            i_++;
            while (i_ < len_) {
                char cur_ = _nb.charAt(i_);
                if (!StringExpUtil.isDigit(cur_)) {
                    if (cur_ != EXP && cur_ != EXP_UPP) {
                        return null;
                    }
                    break;
                }
                decimalPart_.append(cur_);
                i_++;
            }
        }
        if (intPart_.length() + decimalPart_.length() == 0L) {
            return null;
        }
        if (i_ >= len_) {
            return infos_;
        }
        i_++;
        if (i_ >= len_) {
            return null;
        }
        char cur_ = _nb.charAt(i_);
        if (!StringExpUtil.isDigit(cur_) && cur_ != MINUS_CHAR && cur_ != PLUS_CHAR) {
            return null;
        }
        i_++;
        exponentialPart_.append(cur_);
        int nbDig_ = 0;
        if (StringExpUtil.isDigit(cur_)) {
            nbDig_++;
        }
        while (i_ < len_) {
            cur_ = _nb.charAt(i_);
            if (!StringExpUtil.isDigit(cur_)) {
                return null;
            }
            exponentialPart_.append(cur_);
            nbDig_++;
            i_++;
        }
        if (nbDig_ == 0) {
            return null;
        }
        return infos_;
    }

    public static StringStruct exportValue(NumberStruct _nb, String _infinity, String _nan, String _exp) {
        return getStringValue(_nb,_infinity,_nan,_exp);
    }

    public static StringStruct getStringValue(NumberStruct _nb, String _infinity, String _nan, String _exp) {
        if (_nb instanceof DoubleStruct) {
            return getDoubleString(_nb,_infinity, _nan, _exp);
        }
        if (_nb instanceof FloatStruct) {
            return getFloatString(_nb,_infinity, _nan, _exp);
        }
        return new StringStruct(Long.toString(_nb.longStruct()));
    }

    public static StringStruct getFloatString(NumberStruct _nb, String _infinity, String _nan, String _exp) {
        float f_ = _nb.floatStruct();
        if (Float.isInfinite(f_)) {
            if (f_ > 0.0) {
                return new StringStruct(_infinity);
            }
            return new StringStruct(StringList.concat("-",_infinity));
        }
        if (Float.isNaN(f_)) {
            return new StringStruct(_nan);
        }
        return new StringStruct(StringList.replace(Float.toString(f_),"E",_exp));
    }

    public static StringStruct getDoubleString(NumberStruct _nb, String _infinity, String _nan, String _exp) {
        double d_ = _nb.doubleStruct();
        if (Double.isInfinite(d_)) {
            if (d_ > 0.0) {
                return new StringStruct(_infinity);
            }
            return new StringStruct(StringList.concat("-",_infinity));
        }
        if (Double.isNaN(d_)) {
            return new StringStruct(_nan);
        }
        return new StringStruct(StringList.replace(Double.toString(d_),"E",_exp));
    }

    public static int compareGene(NumberStruct _nb1, NumberStruct _nb2) {
        if (_nb1 instanceof DoubleStruct || _nb1 instanceof FloatStruct || _nb2 instanceof DoubleStruct || _nb2 instanceof FloatStruct) {
            if (_nb1.doubleStruct() < _nb2.doubleStruct()) {
                return CustList.NO_SWAP_SORT;
            }
            if (_nb1.doubleStruct() > _nb2.doubleStruct()) {
                return CustList.SWAP_SORT;
            }
            return CustList.EQ_CMP;
        }
        return compare(_nb1,_nb2);
    }

    public static int compare(NumberStruct _nb1, NumberStruct _nb2) {
        return Numbers.compareLg(_nb1.longStruct(),_nb2.longStruct());
    }

    public static double asDouble(Struct _struct, StringList list_, Struct[] _args) {
        double one_;
        if (list_.isEmpty()) {
            NumberStruct instance_ = ClassArgumentMatching.convertToNumber(_struct);
            one_ = instance_.doubleStruct();
        } else {
            one_ = (ClassArgumentMatching.convertToNumber(_args[0])).doubleStruct();
        }
        return one_;
    }

    public static int getRadix(StringList _list, Struct[] _args) {
        int radix_ = DEFAULT_RADIX;
        if (_list.size() != 1) {
            radix_ = (ClassArgumentMatching.convertToNumber(_args[1])).intStruct();
        }
        return radix_;
    }

    public static IntStruct cmpBool(BooleanStruct _one, BooleanStruct _two) {
        if (_one.sameReference(_two)) {
            return new IntStruct(CustList.EQ_CMP);
        }
        if (BooleanStruct.isTrue(_one)) {
            return new IntStruct(CustList.SWAP_SORT);
        }
        return new IntStruct(CustList.NO_SWAP_SORT);
    }

    public static boolean sameReference(NumberStruct _first, NumberStruct _other) {
        if (isFloatType(_first) && !isFloatType(_other)) {
            return false;
        }
        if (!isFloatType(_first) && isFloatType(_other)) {
            return false;
        }
        return cmpWide(_first, _other);
    }

    public static boolean sameValue(Struct _first, Struct _other) {
        NumberStruct first_ = ClassArgumentMatching.convertToNumber(_first);
        NumberStruct other_ = ClassArgumentMatching.convertToNumber(_other);
        return cmpWide(first_, other_);
    }

    public static boolean cmpWide(NumberStruct _first, NumberStruct _other) {
        if (isFloatType(_first, _other)) {
            return compareFloat(_first, _other);
        }
        return _first.longStruct() == _other.longStruct();
    }

    public static boolean isFloatType(Struct _first, Struct _other) {
        return isFloatType(_first) || isFloatType(_other);
    }

    public static boolean compareFloat(NumberStruct _first, NumberStruct _other) {
        double f_ = _first.doubleStruct();
        double d_ = _other.doubleStruct();
        return Double.compare(f_,d_) == 0;
    }

    public static boolean isFloatType(Struct _value) {
        return _value instanceof DoubleStruct || _value instanceof FloatStruct;
    }

    public static StringStruct exportValue(CharSequenceStruct _ch) {
        StringBuilder out_ = new StringBuilder();
        out_.append("\"");
        for (char c: _ch.toStringInstance().toCharArray()) {
            if (c == '"' || c == '\\') {
                out_.append("\\");
                out_.append(c);
                continue;
            }
            if (c == 0) {
                out_.append("\\u0000");
                continue;
            }
            if (c < 16) {
                out_.append("\\u000");
                out_.append(Integer.toHexString(c));
                continue;
            }
            if (c < 31) {
                out_.append("\\u00");
                out_.append(Integer.toHexString(c));
                continue;
            }
            out_.append(c);
        }
        out_.append("\"");
        return new StringStruct(out_.toString());
    }

    public static boolean sameEq(CharSequenceStruct _current, Struct _other) {
        if (!(_other instanceof CharSequenceStruct)) {
            return false;
        }
        CharSequenceStruct other_ = getCharSeq(_other);
        int len_ = _current.length();
        if (len_ != other_.length()) {
            return false;
        }
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            if (_current.charAt(i) != other_.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isDisplay(StringList list_, Struct arg_) {
        return list_.size() == 1 && arg_ instanceof DisplayableStruct;
    }

    public static Struct getArg(StringList list_, Struct[] _args) {
        Struct arg_;
        if (list_.size() == 1) {
            arg_ = _args[0];
        } else {
            arg_ = _args[2];
        }
        return arg_;
    }

    public static boolean okArray(char[] arr_, int one_, int two_) {
        return one_ < 0 || two_ < 0 || one_ + two_ > arr_.length;
    }

    public static String getStringValue(Struct _oldChar) {
        String old_;
        if (_oldChar instanceof StringStruct) {
            old_ = ((StringStruct)_oldChar).getInstance();
        } else {
            old_ = null;
        }
        return old_;
    }

    public static AnnotatedStruct getAnnotated(Struct _struct) {
        if (_struct instanceof MethodMetaInfo) {
            return (MethodMetaInfo) _struct;
        }
        if (_struct instanceof ConstructorMetaInfo) {
            return (ConstructorMetaInfo) _struct;
        }
        if (_struct instanceof FieldMetaInfo) {
            return (FieldMetaInfo) _struct;
        }
        return getClass(_struct);
    }

    public static MethodMetaInfo getMethod(Struct _struct) {
        if (_struct instanceof MethodMetaInfo) {
            return (MethodMetaInfo) _struct;
        }
        return new MethodMetaInfo();
    }

    public static ConstructorMetaInfo getCtor(Struct _struct) {
        if (_struct instanceof ConstructorMetaInfo) {
            return (ConstructorMetaInfo) _struct;
        }
        return new ConstructorMetaInfo();
    }

    public static FieldMetaInfo getField(Struct _struct) {
        if (_struct instanceof FieldMetaInfo) {
            return (FieldMetaInfo) _struct;
        }
        return new FieldMetaInfo();
    }

    public static ClassMetaInfo getClass(Struct _struct) {
        if (_struct instanceof ClassMetaInfo) {
            return (ClassMetaInfo) _struct;
        }
        return new ClassMetaInfo();
    }

    public static ReplacementStruct getReplacement(Struct _previous) {
        if (_previous instanceof ReplacementStruct) {
            return (ReplacementStruct) _previous;
        }
        Replacement r_ = new Replacement();
        r_.setOldString("");
        r_.setNewString("");
        return new ReplacementStruct(r_);
    }

    public static CharSequenceStruct getCharSeq(Struct _previous) {
        if (_previous instanceof StringBuilderStruct) {
            return (StringBuilderStruct) _previous;
        }
        return getString(_previous);
    }

    public static StringBuilderStruct getStrBuilder(Struct _previous) {
        if (_previous instanceof StringBuilderStruct) {
            return (StringBuilderStruct) _previous;
        }
        return new StringBuilderStruct(new StringBuilder());
    }

    public static String getNameOfEnum(Struct _arg) {
        if (_arg instanceof EnumerableStruct) {
            return ((EnumerableStruct)_arg).getName();
        }
        return ";";
    }

    public static DisplayableStruct getDisplayableStruct(Struct _previous) {
        if (_previous instanceof DisplayableStruct) {
            return (DisplayableStruct) _previous;
        }
        return new StringStruct("");
    }

    public static StringStruct getString(Struct _previous) {
        if (_previous instanceof StringStruct) {
            return (StringStruct) _previous;
        }
        return new StringStruct("");
    }
}
