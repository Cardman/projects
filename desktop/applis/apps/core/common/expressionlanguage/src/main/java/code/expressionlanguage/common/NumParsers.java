package code.expressionlanguage.common;

import code.expressionlanguage.stds.PrimitiveTypes;
import code.expressionlanguage.structs.*;
import code.util.CustList;
import code.util.Replacement;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.IndexConstants;
import code.util.core.NumberUtil;
import code.util.core.SortConstants;
import code.util.core.StringUtil;

public final class NumParsers {
    private static final long RATIO = 31L;
    private static final String LOWER_EQ = "<=";
    private static final String LOWER = "<";
    private static final String GREATER_EQ = ">=";
    private static final String GREATER = ">";
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
        String nb_ = StringUtil.removeChars(StringUtil.removeAllSpaces(nbFormatted_.toString()), '_');
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
            String str_  = StringUtil.concat("-",nb_);
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
            if (value_ > Integer.MAX_VALUE) {
                if (value_ == Integer.MAX_VALUE + 1L) {
                    return new IntStruct(Integer.MIN_VALUE);
                }
                return NullStruct.NULL_VALUE;
            }
            return new IntStruct((int) value_);
        }
        if (suffix_ == 'S' || suffix_ == 's') {
            if (value_ > Short.MAX_VALUE) {
                if (value_ == Short.MAX_VALUE + 1L) {
                    return new ShortStruct(Short.MIN_VALUE);
                }
                return NullStruct.NULL_VALUE;
            }
            return new ShortStruct((short) value_);
        }
        if (suffix_ == 'B' || suffix_ == 'b') {
            if (value_ > Byte.MAX_VALUE) {
                if (value_ == Byte.MAX_VALUE + 1L) {
                    return new ByteStruct(Byte.MIN_VALUE);
                }
                return NullStruct.NULL_VALUE;
            }
            return new ByteStruct((byte) value_);
        }
        if (value_ > Character.MAX_VALUE) {
            return NullStruct.NULL_VALUE;
        }
        return new CharStruct((char) value_);
    }


    public static DoubleInfo parseDouble(NumberInfos _nb) {
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
                    return new DoubleInfo(0.0);
                }
                return new DoubleInfo(Double.POSITIVE_INFINITY);
            }
            if (exp_.charAt(0) == '-') {
                return new DoubleInfo(-0.0);
            }
            return new DoubleInfo(Double.NEGATIVE_INFINITY);
        }
        long expNbLong_ = expNb_.getValue();
        if (_nb.getBase() == 16) {
            StringBuilder merged_ = new StringBuilder(int_.length()+dec_.length());
            merged_.append(int_);
            merged_.append(dec_);
            long longValue_ = parseLongSixteen(merged_.toString());
            double parsed_ = (double) longValue_;
            long delta_ = expNbLong_;
            delta_ -= 4L * dec_.length();
            double p_ = 1.0;
            long absExpNbLong_ = Math.abs(delta_);
            for (int i = 0; i < absExpNbLong_; i++) {
                p_ *= 2.0;
            }
            boolean zero_ = longValue_ == 0;
            if (delta_ > 0) {
                return new DoubleInfo(parsed_ * p_,zero_);
            }
            return new DoubleInfo(parsed_ / p_,zero_);
        }
        if (_nb.getBase() == 2) {
            StringBuilder merged_ = new StringBuilder(int_.length()+dec_.length());
            merged_.append(int_);
            merged_.append(dec_);
            long longValue_ = parseLongBase(merged_.toString(), 2);
            double parsed_ = (double) longValue_;
            long delta_ = expNbLong_;
            delta_ -= dec_.length();
            double p_ = 1.0;
            long absExpNbLong_ = Math.abs(delta_);
            for (int i = 0; i < absExpNbLong_; i++) {
                p_ *= 2.0;
            }
            boolean zero_ = longValue_ == 0;
            if (delta_ > 0) {
                return new DoubleInfo(parsed_ * p_,zero_);
            }
            return new DoubleInfo(parsed_ / p_,zero_);
        }
        if (_nb.getBase() == 8) {
            StringBuilder merged_ = new StringBuilder(int_.length()+dec_.length());
            merged_.append(int_);
            merged_.append(dec_);
            long longValue_ = parseLongBase(merged_.toString(), 8);
            double parsed_ = (double) longValue_;
            long delta_ = expNbLong_;
            delta_ -= 3L*dec_.length();
            double p_ = 1.0;
            long absExpNbLong_ = Math.abs(delta_);
            for (int i = 0; i < absExpNbLong_; i++) {
                p_ *= 2.0;
            }
            boolean zero_ = longValue_ == 0;
            if (delta_ > 0) {
                return new DoubleInfo(parsed_ * p_,zero_);
            }
            return new DoubleInfo(parsed_ / p_,zero_);
        }
        if (dec_.length() == 0) {
            if (expNbLong_ == 0) {
                if (int_.length() > MAX_DIGITS_DOUBLE) {
                    return new DoubleInfo(processBigNumbers(int_, positive_));
                }
                long longValue_ = parseQuickLongTen(int_.toString());
                double value_ = (double) longValue_;
                boolean zero_ = longValue_ == 0;
                if (!positive_) {
                    return new DoubleInfo(-value_,zero_);
                }
                return new DoubleInfo(value_,zero_);
            }
            double value_;
            long longValue_;
            if (int_.length() > MAX_DIGITS_DOUBLE) {
                longValue_ = parseQuickLongTen(int_.substring(0, MAX_DIGITS_DOUBLE + 1));
                value_ = (double)longValue_;
                expNbLong_ += int_.length() - MAX_DIGITS_DOUBLE - 1;
            } else {
                longValue_ = parseQuickLongTen(int_.toString());
                value_ = (double)longValue_;
            }
            double power_ = 1.0;
            long absExp_ = Math.abs(expNbLong_);
            for (long i = 0; i < absExp_; i++) {
                power_ *= 10.0;
            }
            boolean zero_ = longValue_ == 0;
            if (!positive_) {
                if (expNbLong_ > 0) {
                    return new DoubleInfo(-value_ * power_,zero_);
                }
                return new DoubleInfo(-value_ / power_,zero_);
            }
            if (expNbLong_ > 0) {
                return new DoubleInfo(value_ * power_,zero_);
            }
            return new DoubleInfo(value_ / power_,zero_);
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
                return new DoubleInfo(processBigNumbers(number_, positive_));
            }
            long longValue_ = parseQuickLongTen(number_.toString());
            double value_ = (double) longValue_;
            boolean zero_ = longValue_ == 0;
            if (!positive_) {
                return new DoubleInfo(-value_,zero_);
            }
            return new DoubleInfo(value_,zero_);
        }
        if (-expNbLong_ >= int_.length()) {
            StringBuilder number_ = new StringBuilder(int_);
            number_.append(dec_);
            StringBuilder decCopy_ = new StringBuilder();
            int index_ = 0;
            while (index_ < number_.length()) {
                if (number_.charAt(index_) != '0') {
                    break;
                }
                index_++;
            }
            int nbLeadingZeros_ = index_;
            decCopy_.append(number_.substring(nbLeadingZeros_));
            if (decCopy_.length() == 0) {
                if (!positive_) {
                    return new DoubleInfo(-0.0,true);
                }
                return new DoubleInfo(0.0,true);
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
                power_ *= 10.0;
            }
            if (!positive_) {
                return new DoubleInfo(-value_ / power_);
            }
            return new DoubleInfo(value_ / power_);
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
            return new DoubleInfo(processBigNumbers(numberInt_, positive_));
        }
        long longValue_ = parseQuickLongTen(numberInt_.toString());
        double value_ = (double) longValue_;
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
            boolean zero_ = longValue_ == 0;
            if (!positive_) {
                return new DoubleInfo(-value_,zero_);
            }
            return new DoubleInfo(value_,zero_);
        }
        long decLongValue_ = parseQuickLongTen(decCopy_.toString());
        double decValue_ = (double) decLongValue_;
        double power_ = 1.0;
        int logDec_ = numberDec_.length();
        for (int i = 0; i < logDec_; i++) {
            power_ *= 10.0;
        }
        if (!positive_) {
            return new DoubleInfo(-value_ - decValue_ / power_);
        }
        return new DoubleInfo(value_ + decValue_ / power_);
    }

    private static double processBigNumbers(StringBuilder _nb, boolean _positive) {
        double long_ = (double) parseQuickLongTen(_nb.substring(0, MAX_DIGITS_DOUBLE + 1));
        double power_ = 1.0;
        int logDec_ = _nb.length() - MAX_DIGITS_DOUBLE - 1;
        for (int i = 0; i < logDec_; i++) {
            power_ *= 10.0;
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

    private static boolean[] toBits(long _l) {
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

    private static boolean[] toBits(int _l) {
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

    private static long toLong(boolean[] _bits) {
        return toLong(_bits,_bits[0],1,64);
    }

    private static long toLong(boolean[] _bits, boolean _reverse, int _from, int _to) {
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
    private static int toInt(boolean[] _bits) {
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

    private static int extractInt(boolean[] _bits) {
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

    private static short extractShort(boolean[] _bits) {
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

    private static byte extractByte(boolean[] _bits) {
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

    private static int toUnsignedInt(boolean[] _bits) {
        int s_ = 0;
        for (int i = 32 - 5 +1; i < 32; i++) {
            s_ *= 2;
            if (_bits[i]) {
                s_++;
            }
        }
        return s_;
    }

    private static int toUnsignedLong(boolean[] _bits) {
        int s_ = 0;
        for (int i = 64 - 6 +1; i < 64; i++) {
            s_ *= 2;
            if (_bits[i]) {
                s_++;
            }
        }
        return s_;
    }

    private static boolean[] parseLongSixteenToBits(String _string) {
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

    private static boolean[] parseLongOctalToBits(String _string) {
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

    private static boolean[] parseLongBinaryToBits(String _string) {
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

    private static long parseLongSixteen(String _string) {
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
        return result_;
    }

    private static long parseLongBase(String _string, int _base) {
        long result_ = 0;
        int i_ = 0;
        int max_ = _string.length();
        while (i_ < max_) {
            int ch_ = _string.charAt(i_);
            i_++;
            int digit_ = ch_ - '0';
            result_ *= _base;
            result_ += digit_;
        }
        return result_;
    }

    private static long parseQuickLongTen(String _string) {
        return -NumberUtil.simpleParse(0,_string);
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
                if (nonParsableChar(ch_)) {
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
                if (nonParsableChar(ch_)) {
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

    private static boolean nonParsableChar(int _ch) {
        if (_ch >= '0' && _ch <= '9') {
            return false;
        }
        if (_ch >= 'a' && _ch <= 'z') {
            return false;
        }
        return _ch < 'A' || _ch > 'Z';
    }

    public static DoubleInfo splitDouble(String _nb) {
        NumberInfos nb_ = trySplitDouble(_nb);
        return parseDoubleOrInvalid(nb_);
    }
    private static DoubleInfo parseDoubleOrInvalid(NumberInfos _nb) {
        if (_nb == null) {
            return new DoubleInfo();
        }
        return parseDouble(_nb);
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
        if (StringExpUtil.nextCharIs(_nb,i_,len_,DOT_VAR)) {
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
            return new StringStruct(StringUtil.concat("-",_infinity));
        }
        if (Float.isNaN(f_)) {
            return new StringStruct(_nan);
        }
        return new StringStruct(StringUtil.replace(Float.toString(f_),"E",_exp));
    }

    public static StringStruct getDoubleString(NumberStruct _nb, String _infinity, String _nan, String _exp) {
        double d_ = _nb.doubleStruct();
        if (Double.isInfinite(d_)) {
            if (d_ > 0.0) {
                return new StringStruct(_infinity);
            }
            return new StringStruct(StringUtil.concat("-",_infinity));
        }
        if (Double.isNaN(d_)) {
            return new StringStruct(_nan);
        }
        return new StringStruct(StringUtil.replace(Double.toString(d_),"E",_exp));
    }

    public static int compareGene(NumberStruct _nb1, NumberStruct _nb2) {
        if (_nb1 instanceof DoubleStruct || _nb1 instanceof FloatStruct || _nb2 instanceof DoubleStruct || _nb2 instanceof FloatStruct) {
            if (_nb1.doubleStruct() < _nb2.doubleStruct()) {
                return SortConstants.NO_SWAP_SORT;
            }
            if (_nb1.doubleStruct() > _nb2.doubleStruct()) {
                return SortConstants.SWAP_SORT;
            }
            return SortConstants.EQ_CMP;
        }
        return compare(_nb1,_nb2);
    }

    public static int compare(NumberStruct _nb1, NumberStruct _nb2) {
        return NumberUtil.compareLg(_nb1.longStruct(),_nb2.longStruct());
    }

    public static double asDouble(Struct _struct, StringList _list, Struct[] _args) {
        double one_;
        if (_list.isEmpty()) {
            NumberStruct instance_ = convertToNumber(_struct);
            one_ = instance_.doubleStruct();
        } else {
            one_ = (convertToNumber(_args[0])).doubleStruct();
        }
        return one_;
    }

    public static int getRadix(StringList _list, Struct[] _args) {
        int radix_ = DEFAULT_RADIX;
        if (_list.size() != 1) {
            radix_ = (convertToNumber(_args[1])).intStruct();
        }
        return radix_;
    }

    public static IntStruct cmpBool(Struct _one, Struct _two) {
        if (_one.sameReference(_two)) {
            return new IntStruct(SortConstants.EQ_CMP);
        }
        if (BooleanStruct.isTrue(_one)) {
            return new IntStruct(SortConstants.SWAP_SORT);
        }
        return new IntStruct(SortConstants.NO_SWAP_SORT);
    }

    public static boolean sameValue(Struct _first, Struct _other) {
        NumberStruct first_ = convertToNumber(_first);
        NumberStruct other_ = convertToNumber(_other);
        return cmpWide(first_, other_);
    }

    private static boolean cmpWide(NumberStruct _first, NumberStruct _other) {
        if (isFloatType(_first, _other)) {
            return compareFloat(_first, _other);
        }
        return compareRelative(_first, _other);
    }

    public static boolean compareRelative(NumberStruct _first, NumberStruct _other) {
        return _first.longStruct() == _other.longStruct();
    }

    private static boolean isFloatType(Struct _first, Struct _other) {
        return isFloatType(_first) || isFloatType(_other);
    }

    public static boolean compareFloat(NumberStruct _first, NumberStruct _other) {
        double f_ = _first.doubleStruct();
        double d_ = _other.doubleStruct();
        return Double.compare(f_,d_) == 0;
    }

    private static boolean isFloatType(Struct _value) {
        return _value instanceof DoubleStruct || _value instanceof FloatStruct;
    }

    public static StringStruct exportValue(CharSequenceStruct _ch) {
        StringBuilder out_ = new StringBuilder();
        out_.append("\"");
        for (char c: _ch.toStringInstance().toCharArray()) {
            out_.append(exportChar(c));
        }
        out_.append("\"");
        return new StringStruct(out_.toString());
    }

    private static String exportChar(char _char) {
        if (_char == '"' || _char == '\\') {
            return "\\"+_char;
        }
        if (_char == 0) {
            return "\\u0000";
        }
        if (_char < 16) {
            return "\\u000"+StringExpUtil.toGeneHex(_char);
        }
        if (_char < 31) {
            return "\\u00"+StringExpUtil.toGeneHex(_char);
        }
        return Character.toString(_char);
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
        for (int i = IndexConstants.FIRST_INDEX; i < len_; i++) {
            if (_current.charAt(i) != other_.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isDisplay(StringList _list, Struct _arg) {
        return _list.size() == 1 && _arg instanceof DisplayableStruct;
    }

    public static Struct getArg(StringList _list, Struct[] _args) {
        Struct arg_;
        if (_list.size() == 1) {
            arg_ = _args[0];
        } else {
            arg_ = _args[2];
        }
        return arg_;
    }

    public static boolean koArray(char[] _arr, int _one, int _two) {
        return _one < 0 || _two < 0 || _one + _two > _arr.length;
    }

    public static CustList<Replacement> getReplValue(Struct _seps) {
        CustList<Replacement> repls_;
        if (!(_seps instanceof ArrayStruct)) {
            repls_ = null;
        } else {
            repls_ = new CustList<Replacement>();
            ArrayStruct arrSep_ = (ArrayStruct) _seps;
            int lenSeps_ = arrSep_.getLength();
            for (int i = 0; i < lenSeps_; i++) {
                Struct curSep_ = arrSep_.get(i);
                if (!(curSep_ instanceof ReplacementStruct)) {
                    repls_.add(null);
                } else {
                    repls_.add(((ReplacementStruct)curSep_).getInstance());
                }
            }
        }
        return repls_;
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

    public static StackTraceElementStruct getStack(Struct _str) {
        if (_str instanceof StackTraceElementStruct) {
            return (StackTraceElementStruct) _str;
        }
        return new StackTraceElementStruct("",-1,-1,-1,"","");
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

    public static StringStruct getString(Struct _previous) {
        if (_previous instanceof StringStruct) {
            return (StringStruct) _previous;
        }
        return new StringStruct("");
    }

    public static NumberStruct convertToNumber(byte _match, Struct _obj) {
        if (_obj instanceof NumberStruct) {
            return convertObject(_match, (NumberStruct)_obj);
        }
        return convertObject(_match,new ByteStruct((byte)0));
    }

    public static Struct convertObject(byte _match, Struct _obj) {
        if (_obj instanceof NumberStruct) {
            return convertObject(_match, (NumberStruct)_obj);
        }
        return _obj;
    }

    private static Struct convertStrictObject(byte _match, Struct _obj) {
        if (_obj instanceof NumberStruct) {
            return convertStrictObject(_match, (NumberStruct)_obj);
        }
        return _obj;
    }

    private static NumberStruct convertObject(byte _match, NumberStruct _obj) {
        if (_match == PrimitiveTypes.DOUBLE_WRAP) {
            return new DoubleStruct(_obj.doubleStruct());
        }
        if (_match == PrimitiveTypes.FLOAT_WRAP) {
            return new FloatStruct(_obj.floatStruct());
        }
        return convertIntNb(_match, _obj);
    }

    private static NumberStruct convertStrictObject(byte _match, NumberStruct _obj) {
        if (isFloatType(_obj)) {
            return convertToFloat(_match, _obj);
        }
        return convertToInt(_match, _obj);
    }

    public static NumberStruct convertToInt(byte _match, NumberStruct _obj) {
        return convertIntNb(_match, _obj);
    }

    public static NumberStruct convertToFloat(byte _match, NumberStruct _obj) {
        if (isInternFloat(_match)) {
            return new FloatStruct(_obj.floatStruct());
        }
        return new DoubleStruct(_obj.doubleStruct());
    }

    private static NumberStruct convertIntNb(byte _match, NumberStruct _obj) {
        if (_match == PrimitiveTypes.LONG_WRAP) {
            return new LongStruct(_obj.longStruct());
        }
        if (_match == PrimitiveTypes.INT_WRAP) {
            return new IntStruct(_obj.intStruct());
        }
        if (_match == PrimitiveTypes.SHORT_WRAP) {
            return new ShortStruct(_obj.shortStruct());
        }
        if (_match == PrimitiveTypes.BYTE_WRAP) {
            return new ByteStruct(_obj.byteStruct());
        }
        if (_match == PrimitiveTypes.CHAR_WRAP) {
            return new CharStruct((char) _obj.intStruct());
        }
        return _obj;
    }

    public static Struct unwrapObject(byte _match, Struct _obj) {
        return convertStrictObject(_match, _obj);
    }

    private static boolean isLessInt(byte _class) {
        return _class < PrimitiveTypes.INT_WRAP;
    }

    private static boolean isByte(byte _class) {
        return _class == PrimitiveTypes.BYTE_WRAP;
    }

    private static boolean isShort(byte _class) {
        return _class == PrimitiveTypes.SHORT_WRAP;
    }

    private static boolean isChar(byte _class) {
        return _class == PrimitiveTypes.CHAR_WRAP;
    }

    private static boolean isIntOrLess(byte _class) {
        return isInt(_class) || isLessInt(_class);
    }

    private static boolean isInt(byte _class) {
        return _class == PrimitiveTypes.INT_WRAP;
    }

    private static boolean isLong(byte _class) {
        return _class == PrimitiveTypes.LONG_WRAP;
    }

    private static boolean isFloat(byte _class) {
        return isInternFloat(_class);
    }

    private static boolean isInternFloat(byte _class) {
        return _class == PrimitiveTypes.FLOAT_WRAP;
    }

    public static Struct convert(byte _cast) {
        if (_cast == PrimitiveTypes.BOOL_WRAP) {
            return BooleanStruct.of(false);
        }
        if (_cast == PrimitiveTypes.DOUBLE_WRAP) {
            return new DoubleStruct(0);
        }
        if (_cast == PrimitiveTypes.FLOAT_WRAP) {
            return new FloatStruct(0);
        }
        if (_cast == PrimitiveTypes.LONG_WRAP) {
            return new LongStruct(0);
        }
        if (_cast == PrimitiveTypes.INT_WRAP) {
            return new IntStruct(0);
        }
        if (_cast == PrimitiveTypes.CHAR_WRAP) {
            return new CharStruct((char)0);
        }
        if (_cast == PrimitiveTypes.SHORT_WRAP) {
            return new ShortStruct((short)0);
        }
        if (_cast == PrimitiveTypes.BYTE_WRAP) {
            return new ByteStruct((byte)0);
        }
        return NullStruct.NULL_VALUE;
    }

    public static String getSingleNameOrEmpty(StringList _className) {
        if (!_className.onlyOneElt()) {
            return "";
        }
        return _className.first();
    }

    public static BooleanStruct convertToBoolean(Struct _arg) {
        if (_arg instanceof BooleanStruct) {
            return (BooleanStruct) _arg;
        }
        return BooleanStruct.of(false);
    }

    public static CharStruct convertToChar(Struct _arg) {
        if (_arg instanceof CharStruct) {
            return (CharStruct) _arg;
        }
        return new CharStruct((char)0);
    }

    public static RangeStruct convertToRange(Struct _arg) {
        if (_arg instanceof RangeStruct) {
            return (RangeStruct) _arg;
        }
        return new RangeStruct(0);
    }
    public static NumberStruct convertToNumber(Struct _arg) {
        if (_arg instanceof NumberStruct) {
            return (NumberStruct) _arg;
        }
        return new ByteStruct((byte)0);
    }

    public static BooleanStruct compareNb(String _op, Struct _one, Struct _two) {
        boolean complement_ = false;
        String useOp_ = _op;
        if (StringUtil.quickEq(_op, LOWER_EQ)) {
            complement_ = true;
            useOp_ = GREATER;
        } else if (StringUtil.quickEq(_op, GREATER_EQ)) {
            complement_ = true;
            useOp_ = LOWER;
        }
        BooleanStruct arg_;
        if (StringUtil.quickEq(useOp_, LOWER)) {
            arg_ = quickCalculateLowerNb(_one, _two);
        } else {
            arg_ = quickCalculateGreaterNb(_one, _two);
        }
        if (complement_) {
            arg_ = arg_.neg();
        }
        return arg_;
    }

    public static BooleanStruct compareStr(String _op, Struct _one, Struct _two) {
        boolean complement_ = false;
        String useOp_ = _op;
        if (StringUtil.quickEq(_op, LOWER_EQ)) {
            complement_ = true;
            useOp_ = GREATER;
        } else if (StringUtil.quickEq(_op, GREATER_EQ)) {
            complement_ = true;
            useOp_ = LOWER;
        }
        BooleanStruct arg_;
        if (StringUtil.quickEq(useOp_, LOWER)) {
            arg_ = quickCalculateLowerStr(_one, _two);
        } else {
            arg_ = quickCalculateGreaterStr(_one, _two);
        }
        if (complement_) {
            arg_ = arg_.neg();
        }
        return arg_;
    }

    public static BooleanStruct quickCalculateLowerNb(Struct _a, Struct _b) {
        if (isFloatType(_a,_b)) {
            return BooleanStruct.of(convertToNumber(_a).doubleStruct() < convertToNumber(_b).doubleStruct());
        }
        return BooleanStruct.of(convertToNumber(_a).longStruct() < convertToNumber(_b).longStruct());
    }

    public static BooleanStruct quickCalculateGreaterNb(Struct _a, Struct _b) {
        if (isFloatType(_a,_b)) {
            return BooleanStruct.of(convertToNumber(_a).doubleStruct() > convertToNumber(_b).doubleStruct());
        }
        return BooleanStruct.of(convertToNumber(_a).longStruct() > convertToNumber(_b).longStruct());
    }

    private static BooleanStruct quickCalculateLowerStr(Struct _a, Struct _b) {
        String first_ = getCharSeq(_a).toStringInstance();
        String second_ = getCharSeq(_b).toStringInstance();
        return BooleanStruct.of(StringUtil.compareStrings(first_,second_) < 0);
    }

    private static BooleanStruct quickCalculateGreaterStr(Struct _a, Struct _b) {
        String first_ = getCharSeq(_a).toStringInstance();
        String second_ = getCharSeq(_b).toStringInstance();
        return BooleanStruct.of(StringUtil.compareStrings(first_,second_) > 0);
    }

    public static NumberStruct idNumber(NumberStruct _a, byte _cast) {
        return convertToNumber(_cast, _a);
    }

    public static NumberStruct negBinNumber(NumberStruct _a, byte _cast) {
        if (_cast <= PrimitiveTypes.INT_WRAP) {
            int left_ = _a.intStruct();
            boolean[] bits_ = toBits(left_);
            int len_ = bits_.length;
            for (int i = 0; i<len_; i++) {
                bits_[i] = !bits_[i];
            }
            return new IntStruct(toInt(bits_));
        }
        long left_ = _a.longStruct();
        boolean[] bits_ = toBits(left_);
        int len_ = bits_.length;
        for (int i = 0; i<len_; i++) {
            bits_[i] = !bits_[i];
        }
        return new LongStruct(toLong(bits_));
    }

    public static NumberStruct calculateIncr(NumberStruct _a, int _dir, byte _cast) {
        if (isByte(_cast)) {
            byte left_ = _a.byteStruct();
            left_+=_dir;
            return new ByteStruct(left_);
        }
        if (isShort(_cast)) {
            short left_ = _a.shortStruct();
            left_+=_dir;
            return new ShortStruct(left_);
        }
        if (isChar(_cast)) {
            char left_ = (char)_a.intStruct();
            left_+=_dir;
            return new CharStruct(left_);
        }
        if (isInt(_cast)) {
            int left_ = _a.intStruct();
            int nb_ = left_ + _dir;
            return new IntStruct(nb_);
        }
        if (isLong(_cast)) {
            long left_ = _a.longStruct();
            long nb_ = left_ + _dir;
            return new LongStruct(nb_);
        }
        double left_ = _a.doubleStruct();
        double nb_ = left_ + (double)_dir;
        if (isFloat(_cast)) {
            return new FloatStruct((float)nb_);
        }
        return new DoubleStruct(nb_);
    }

    public static NumberStruct calculateSum(NumberStruct _a, NumberStruct _b, byte _cast) {
        if (isIntOrLess(_cast)) {
            int left_ = _a.intStruct();
            int right_ = _b.intStruct();
            int nb_ = left_ + right_;
            return new IntStruct(nb_);
        }
        if (isLong(_cast)) {
            long left_ = _a.longStruct();
            long right_ = _b.longStruct();
            long nb_ = left_ + right_;
            return new LongStruct(nb_);
        }
        double left_ = _a.doubleStruct();
        double right_ = _b.doubleStruct();
        double nb_ = left_ + right_;
        if (isFloat(_cast)) {
            return new FloatStruct((float)nb_);
        }
        return new DoubleStruct(nb_);
    }

    public static NumberStruct opposite(NumberStruct _a, byte _cast) {
        NumberStruct tmp_;
        if (isIntOrLess(_cast)) {
            tmp_ = new IntStruct(-_a.intStruct());
        } else if (isLong(_cast)) {
            tmp_ = new LongStruct(-_a.longStruct());
        } else if (isFloat(_cast)){
            tmp_ = new FloatStruct(-_a.floatStruct());
        } else {
            tmp_ = new DoubleStruct(-_a.doubleStruct());
        }
        return tmp_;
    }

    public static NumberStruct calculateDiff(NumberStruct _a, NumberStruct _b, byte _cast) {
        if (isIntOrLess(_cast)) {
            int left_ = _a.intStruct();
            int right_ = _b.intStruct();
            int nb_ = left_ - right_;
            return new IntStruct(nb_);
        }
        if (isLong(_cast)) {
            long left_ = _a.longStruct();
            long right_ = _b.longStruct();
            long nb_ = left_ - right_;
            return new LongStruct(nb_);
        }
        double left_ = _a.doubleStruct();
        double right_ = _b.doubleStruct();
        double nb_ = left_ - right_;
        if (isFloat(_cast)) {
            return new FloatStruct((float)nb_);
        }
        return new DoubleStruct(nb_);
    }

    public static NumberStruct calculateMult(NumberStruct _a, NumberStruct _b, byte _cast) {
        if (isIntOrLess(_cast)) {
            int left_ = _a.intStruct();
            int right_ = _b.intStruct();
            int nb_ = left_ * right_;
            return new IntStruct(nb_);
        }
        if (isLong(_cast)) {
            long left_ = _a.longStruct();
            long right_ = _b.longStruct();
            long nb_ = left_ * right_;
            return new LongStruct(nb_);
        }
        double left_ = _a.doubleStruct();
        double right_ = _b.doubleStruct();
        double nb_ = left_ * right_;
        if (isFloat(_cast)) {
            return new FloatStruct((float)nb_);
        }
        return new DoubleStruct(nb_);
    }

    public static Struct calculateDiv(NumberStruct _a, NumberStruct _b, byte _cast) {
        if (isIntOrLess(_cast)) {
            int left_ = _a.intStruct();
            int right_ = _b.intStruct();
            if (right_ == 0) {
                return NullStruct.NULL_VALUE;
            }
            int nb_ = left_ / right_;
            return new IntStruct(nb_);
        }
        if (isLong(_cast)) {
            long left_ = _a.longStruct();
            long right_ = _b.longStruct();
            if (right_ == 0) {
                return NullStruct.NULL_VALUE;
            }
            long nb_ = left_ / right_;
            return new LongStruct(nb_);
        }
        double left_ = _a.doubleStruct();
        double right_ = _b.doubleStruct();
        double nb_ = left_ / right_;
        if (isFloat(_cast)) {
            return new FloatStruct((float)nb_);
        }
        return new DoubleStruct(nb_);
    }

    public static Struct calculateMod(NumberStruct _a, NumberStruct _b, byte _cast) {
        if (isIntOrLess(_cast)) {
            int left_ = _a.intStruct();
            int right_ = _b.intStruct();
            if (right_ == 0) {
                return NullStruct.NULL_VALUE;
            }
            int nb_ = left_ % right_;
            return new IntStruct(nb_);
        }
        if (isLong(_cast)) {
            long left_ = _a.longStruct();
            long right_ = _b.longStruct();
            if (right_ == 0) {
                return NullStruct.NULL_VALUE;
            }
            long nb_ = left_ % right_;
            return new LongStruct(nb_);
        }
        double left_ = _a.doubleStruct();
        double right_ = _b.doubleStruct();
        double nb_ = left_ % right_;
        if (isFloat(_cast)) {
            return new FloatStruct((float)nb_);
        }
        return new DoubleStruct(nb_);
    }

    public static Struct calculateAnd(Struct _a, Struct _b, byte _cast) {
        if (_cast == PrimitiveTypes.BOOL_WRAP) {
            return convertToBoolean(_a).and(convertToBoolean(_b));
        }
        if (_cast <= PrimitiveTypes.INT_WRAP) {
            int left_ = convertToNumber(_a).intStruct();
            int right_ = convertToNumber(_b).intStruct();
            boolean[] bitsLeft_ = toBits(left_);
            boolean[] bitsRight_ = toBits(right_);
            int len_ = bitsLeft_.length;
            boolean[] bits_ = new boolean[len_];
            for (int i = 0; i < len_; i++) {
                bits_[i] = bitsLeft_[i] && bitsRight_[i];
            }
            int value_ = toInt(bits_);
            return new IntStruct(value_);
        }
        long left_ = convertToNumber(_a).longStruct();
        long right_ = convertToNumber(_b).longStruct();
        boolean[] bitsLeft_ = toBits(left_);
        boolean[] bitsRight_ = toBits(right_);
        int len_ = bitsLeft_.length;
        boolean[] bits_ = new boolean[len_];
        for (int i = 0; i < len_; i++) {
            bits_[i] = bitsLeft_[i] && bitsRight_[i];
        }
        long value_ = toLong(bits_);
        return new LongStruct(value_);
    }

    public static Struct calculateOr(Struct _a, Struct _b, byte _cast) {
        if (_cast == PrimitiveTypes.BOOL_WRAP) {
            return convertToBoolean(_a).or(convertToBoolean(_b));
        }
        if (_cast <= PrimitiveTypes.INT_WRAP) {
            int left_ = convertToNumber(_a).intStruct();
            int right_ = convertToNumber(_b).intStruct();
            boolean[] bitsLeft_ = toBits(left_);
            boolean[] bitsRight_ = toBits(right_);
            int len_ = bitsLeft_.length;
            boolean[] bits_ = new boolean[len_];
            for (int i = 0; i < len_; i++) {
                bits_[i] = bitsLeft_[i] || bitsRight_[i];
            }
            int value_ = toInt(bits_);
            return new IntStruct(value_);
        }
        long left_ = convertToNumber(_a).longStruct();
        long right_ = convertToNumber(_b).longStruct();
        boolean[] bitsLeft_ = toBits(left_);
        boolean[] bitsRight_ = toBits(right_);
        int len_ = bitsLeft_.length;
        boolean[] bits_ = new boolean[len_];
        for (int i = 0; i < len_; i++) {
            bits_[i] = bitsLeft_[i] || bitsRight_[i];
        }
        long value_ = toLong(bits_);
        return new LongStruct(value_);
    }

    public static Struct calculateXor(Struct _a, Struct _b, byte _cast) {
        if (_cast == PrimitiveTypes.BOOL_WRAP) {
            return BooleanStruct.of(!convertToBoolean(_a).sameReference(convertToBoolean(_b)));
        }
        if (_cast <= PrimitiveTypes.INT_WRAP) {
            int left_ = convertToNumber(_a).intStruct();
            int right_ = convertToNumber(_b).intStruct();
            boolean[] bitsLeft_ = toBits(left_);
            boolean[] bitsRight_ = toBits(right_);
            int len_ = bitsLeft_.length;
            boolean[] bits_ = new boolean[len_];
            for (int i = 0; i < len_; i++) {
                bits_[i] = bitsLeft_[i] != bitsRight_[i];
            }
            int value_ = toInt(bits_);
            return new IntStruct(value_);
        }
        long left_ = convertToNumber(_a).longStruct();
        long right_ = convertToNumber(_b).longStruct();
        boolean[] bitsLeft_ = toBits(left_);
        boolean[] bitsRight_ = toBits(right_);
        int len_ = bitsLeft_.length;
        boolean[] bits_ = new boolean[len_];
        for (int i = 0; i < len_; i++) {
            bits_[i] = bitsLeft_[i] != bitsRight_[i];
        }
        long value_ = toLong(bits_);
        return new LongStruct(value_);
    }

    public static NumberStruct calculateShiftLeft(NumberStruct _a, NumberStruct _b, byte _cast) {
        if (_cast <= PrimitiveTypes.INT_WRAP) {
            int left_ = _a.intStruct();
            int right_ = _b.intStruct();
            boolean[] bitsRight_ = toBits(right_);
            int value_ = toUnsignedInt(bitsRight_);
            int power_ = 1;
            for (int i = 0; i< value_; i++) {
                power_ *= 2;
            }
            return new IntStruct(left_*power_);
        }
        long left_ = _a.longStruct();
        long right_ = _b.longStruct();
        boolean[] bitsRight_ = toBits(right_);
        int value_ = toUnsignedLong(bitsRight_);
        long power_ = 1;
        for (int i = 0; i< value_; i++) {
            power_ *= 2;
        }
        return new LongStruct(left_*power_);
    }

    public static NumberStruct calculateShiftRight(NumberStruct _a, NumberStruct _b, byte _cast) {
        if (_cast <= PrimitiveTypes.INT_WRAP) {
            int left_ = _a.intStruct();
            int right_ = _b.intStruct();
            boolean[] bitsRight_ = toBits(right_);
            int value_ = toUnsignedInt(bitsRight_);
            int power_ = 1;
            for (int i = 0; i< value_; i++) {
                power_ *= 2;
            }
            return new IntStruct(NumberUtil.quot(left_, power_));
        }
        long left_ = _a.longStruct();
        long right_ = _b.longStruct();
        boolean[] bitsRight_ = toBits(right_);
        int value_ = toUnsignedLong(bitsRight_);
        long power_ = 1;
        for (int i = 0; i< value_; i++) {
            power_ *= 2;
        }
        return new LongStruct(NumberUtil.quot(left_, power_));
    }

    public static NumberStruct calculateBitShiftLeft(NumberStruct _a, NumberStruct _b, byte _cast) {
        if (_cast <= PrimitiveTypes.INT_WRAP) {
            int left_ = _a.intStruct();
            int right_ = _b.intStruct();
            boolean[] bitsRight_ = toBits(right_);
            int value_ = toUnsignedInt(bitsRight_);
            boolean[] bitsLeft_ = toBits(left_);
            int diff_ = 32 - value_;
            for (int i = 1; i < diff_; i++) {
                shift(value_, bitsLeft_, i);
            }
            for (int i = diff_; i < 32; i++) {
                bitsLeft_[i] = false;
            }
            return new IntStruct(toInt(bitsLeft_));
        }
        long left_ = _a.longStruct();
        long right_ = _b.longStruct();
        boolean[] bitsRight_ = toBits(right_);
        int value_ = toUnsignedLong(bitsRight_);
        boolean[] bitsLeft_ = toBits(left_);
        int diff_ = 64 - value_;
        for (int i = 1; i < diff_; i++) {
            shift(value_, bitsLeft_, i);
        }
        for (int i = diff_; i < 64; i++) {
            bitsLeft_[i] = false;
        }
        return new LongStruct(toLong(bitsLeft_));
    }

    private static void shift(int _value, boolean[] _bits, int _i) {
        _bits[_i] = _bits[_i + _value];
    }

    public static NumberStruct calculateBitShiftRight(NumberStruct _a, NumberStruct _b, byte _cast) {
        if (_cast <= PrimitiveTypes.INT_WRAP) {
            int left_ = _a.intStruct();
            int right_ = _b.intStruct();
            boolean[] bitsRight_ = toBits(right_);
            int value_ = toUnsignedInt(bitsRight_);
            boolean[] bitsLeft_ = toBits(left_);
            int diff_ = 32 - value_;
            for (int i = 0; i < diff_; i++) {
                int index_ = 31 - i;
                bitsLeft_[index_] = bitsLeft_[index_ - value_];
            }
            for (int i = diff_; i < 32; i++) {
                int index_ = 31 - i;
                bitsLeft_[index_] = false;
            }
            return new IntStruct(toInt(bitsLeft_));
        }
        long left_ = _a.longStruct();
        long right_ = _b.longStruct();
        boolean[] bitsRight_ = toBits(right_);
        int value_ = toUnsignedLong(bitsRight_);
        boolean[] bitsLeft_ = toBits(left_);
        int diff_ = 64 - value_;
        for (int i = 0; i < diff_; i++) {
            int index_ = 63 - i;
            bitsLeft_[index_] = bitsLeft_[index_ - value_];
        }
        for (int i = diff_; i < 64; i++) {
            int index_ = 63 - i;
            bitsLeft_[index_] = false;
        }
        return new LongStruct(toLong(bitsLeft_));
    }

    public static NumberStruct calculateRotateLeft(NumberStruct _a, NumberStruct _b, byte _cast) {
        if (_cast <= PrimitiveTypes.INT_WRAP) {
            int left_ = _a.intStruct();
            int right_ = _b.intStruct();
            boolean[] bitsRight_ = toBits(right_);
            int value_ = toUnsignedInt(bitsRight_);
            boolean[] bitsLeft_ = toBits(left_);
            int max_ = bitsLeft_.length - 1;
            for (int i = 0; i < value_; i++) {
                boolean firstBit_ = bitsLeft_[0];
                for (int j = 0; j < max_; j++) {
                    shift(bitsLeft_, j);
                }
                bitsLeft_[max_] = firstBit_;
            }
            return new IntStruct(toInt(bitsLeft_));
        }
        long left_ = _a.longStruct();
        long right_ = _b.longStruct();
        boolean[] bitsRight_ = toBits(right_);
        int value_ = toUnsignedLong(bitsRight_);
        boolean[] bitsLeft_ = toBits(left_);
        int max_ = bitsLeft_.length - 1;
        for (int i = 0; i < value_; i++) {
            boolean firstBit_ = bitsLeft_[0];
            for (int j = 0; j < max_; j++) {
                shift(bitsLeft_, j);
            }
            bitsLeft_[max_] = firstBit_;
        }
        return new LongStruct(toLong(bitsLeft_));
    }

    private static void shift(boolean[] _bits, int _j) {
        shift(1, _bits, _j);
    }

    public static NumberStruct calculateRotateRight(NumberStruct _a, NumberStruct _b, byte _cast) {
        if (_cast <= PrimitiveTypes.INT_WRAP) {
            int left_ = _a.intStruct();
            int right_ = _b.intStruct();
            boolean[] bitsRight_ = toBits(right_);
            int value_ = toUnsignedInt(bitsRight_);
            boolean[] bitsLeft_ = toBits(left_);
            int max_ = bitsLeft_.length - 1;
            for (int i = 0; i < value_; i++) {
                boolean firstBit_ = bitsLeft_[max_];
                for (int j = 0; j < max_; j++) {
                    int index_ = max_ - j;
                    bitsLeft_[index_] = bitsLeft_[index_ - 1];
                }
                bitsLeft_[0] = firstBit_;
            }
            return new IntStruct(toInt(bitsLeft_));
        }
        long left_ = _a.longStruct();
        long right_ = _b.longStruct();
        boolean[] bitsRight_ = toBits(right_);
        int value_ = toUnsignedLong(bitsRight_);
        boolean[] bitsLeft_ = toBits(left_);
        int max_ = bitsLeft_.length - 1;
        for (int i = 0; i < value_; i++) {
            boolean firstBit_ = bitsLeft_[max_];
            for (int j = 0; j < max_; j++) {
                int index_ = max_ - j;
                bitsLeft_[index_] = bitsLeft_[index_ - 1];
            }
            bitsLeft_[0] = firstBit_;
        }
        return new LongStruct(toLong(bitsLeft_));
    }

    public static ArrayStruct setElements(CustList<Struct> _args, String _cl) {
        int len_ = _args.size();
        ArrayStruct arr_ = new ArrayStruct(len_,_cl);
        for (int i = IndexConstants.FIRST_INDEX; i < len_; i++) {
            arr_.set(i,_args.get(i));
        }
        return arr_;
    }

    public static Replacement getReplacement(Struct[] _args) {
        Replacement rep_ = new Replacement();
        if (_args[0] instanceof CharSequenceStruct) {
            rep_.setOldString(getCharSeq(_args[0]).toStringInstance());
        }
        if (_args[1] instanceof CharSequenceStruct) {
            rep_.setNewString(getCharSeq(_args[1]).toStringInstance());
        }
        return rep_;
    }

    public static Struct isEmpty(CharSequenceStruct _inst) {
        return BooleanStruct.of(_inst.length() == 0);
    }

    public static boolean isInvalidIndex(int _ind, CharSequenceStruct _inst) {
        return _ind < 0 || _ind >= _inst.length();
    }

    public static boolean isIncorrectSub(int _begin, int _end, CharSequenceStruct _inst) {
        return _begin < 0 || _end > _inst.length() || _begin > _end;
    }
    public static int compareToIgnoreCase(String _instance, String _other) {
        int min_ = Math.min(_instance.length(), _other.length());
        for (int i = 0; i < min_; i++) {
            char cFirst_ = _instance.charAt(i);
            char cSecond_ = _other.charAt(i);
            cFirst_ = StringDataUtil.toLowerCase(cFirst_);
            cSecond_ = StringDataUtil.toLowerCase(cSecond_);
            if (cFirst_ != cSecond_) {
                return NumberUtil.compareLg(cFirst_, cSecond_);
            }
        }
        return NumberUtil.compareLg(_instance.length(), _other.length());
    }
    public static boolean equalsIgnoreCase(String _instance,String _other) {
        return _other.length() == _instance.length()
                && regionMatches(_instance,true, 0, _other, 0, _instance.length());
    }
    public static boolean regionMatches(String _instance,int _toffset,
                                        String _other, int _ooffset,
                                 int _len) {
        int to_ = _toffset;
        int po_ = _ooffset;
        int len_ = _len;
        if (outOfBounds(_instance, _toffset, _other, _ooffset, len_)) {
            return false;
        }
        while (len_ > 0) {
            if (_instance.charAt(to_) != _other.charAt(po_)) {
                return false;
            }
            len_--;
            to_++;
            po_++;
        }
        return true;
    }

    public static boolean regionMatches(String _instance,
                                        boolean _ignoreCase, int _toffset,
                                 String _other, int _ooffset, int _len) {
        if (!_ignoreCase) {
            return regionMatches(_instance,_toffset,_other,_ooffset,_len);
        }
        int len_ = _len;
        int to_ = _toffset;
        int po_ = _ooffset;
        if (outOfBounds(_instance, to_, _other, po_, len_)) {
            return false;
        }
        while (len_ > 0) {
            if (StringDataUtil.toLowerCase(_instance.charAt(to_)) != StringDataUtil.toLowerCase(_other.charAt(po_))) {
                return false;
            }
            len_--;
            to_++;
            po_++;
        }
        return true;
    }

    private static boolean outOfBounds(String _instance, int _toffset, String _other, int _ooffset, int _len) {
        return _ooffset < 0 || _toffset < 0
                || _toffset > (long)_instance.length() - _len
                || _ooffset > (long)_other.length() - _len;
    }

    public static Struct getStaticField(ClassField _clField, StringMap<StringMap<Struct>> _staticFields) {
        StringMap<Struct> map_ = getStaticFieldMap(_clField.getClassName(), _staticFields);
        if (map_.isEmpty()) {
            return null;
        }
        return map_.getVal(_clField.getFieldName());
    }

    public static StringMap<Struct> getStaticFieldMap(String _clField, StringMap<StringMap<Struct>> _map) {
        StringMap<Struct> map_ = _map.getVal(_clField);
        if (map_ == null) {
            map_ = new StringMap<Struct>();
        }
        return map_;
    }
    public static long randCode(Struct _boolean) {
        if (BooleanStruct.isTrue(_boolean)) {
            return 1;
        }
        return 0;
    }
    public static long randCode(double _number) {
        if (Double.isInfinite(_number)) {
            return Long.MAX_VALUE;
        }
        if (Double.isNaN(_number)) {
            return Long.MIN_VALUE;
        }
        if (_number >= 1.0) {
            long power_ = 0;
            double nb_ = _number;
            while (nb_ > 1.0) {
                power_++;
                nb_ /= 2.0;
            }
            return power_;
        }
        if (_number >= Double.MIN_VALUE) {
            long power_ = 0;
            double nb_ = _number;
            while (nb_ < 1.0) {
                power_++;
                nb_ *= 2.0;
            }
            return -power_;
        }
        if (_number <= -1.0) {
            long power_ = 0;
            double nb_ = _number;
            while (nb_ < -1.0) {
                power_++;
                nb_ /= 2.0;
            }
            return power_;
        }
        if (_number <= -Double.MIN_VALUE) {
            long power_ = 0;
            double nb_ = _number;
            while (nb_ > -1.0) {
                power_++;
                nb_ *= 2.0;
            }
            return -power_;
        }
        return 0;
    }
    public static long randCode(String _string) {
        long r_ = 0L;
        for(char c: StringUtil.nullToEmpty(_string).toCharArray()) {
            r_ = mergeRandCode(r_, c);
        }
        return r_;
    }
    public static long mergeRandCode(long _r, long _c) {
        return RATIO * _r + _c;
    }
}
