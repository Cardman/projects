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
        if (isDoubleSuffix(suffix_) || isFloatSuffix(suffix_)) {
            return processDotted(_infosNb, suffix_);
        }
        StringBuilder nbFormatted_ = _infosNb.getIntPart();
        String nb_ = StringUtil.removeChars(StringUtil.removeAllSpaces(nbFormatted_.toString()), '_');
        if (_infosNb.getBase() == 16) {
            return processSixteen(suffix_, nb_);
        }
        if (_infosNb.getBase() == 2) {
            return processTwo(suffix_, nb_);
        }
        if (_infosNb.getBase() == 8) {
            return processEight(suffix_, nb_);
        }
        return processTen(suffix_, nb_);
    }

    private static WithoutParentStruct processDotted(NumberInfos _infosNb, char _suffix) {
        DoubleInfo doubleInfo_ = NumParsers.parseDoubleOrInvalid(_infosNb);
        if (isDoubleSuffix(_suffix)) {
            return new DoubleStruct(doubleInfo_.getValue());
        }
        if (doubleInfo_.outOfRange(Float.MIN_VALUE,Float.MAX_VALUE)) {
            return NullStruct.NULL_VALUE;
        }
        return new FloatStruct((float) doubleInfo_.getValue());
    }

    private static WithoutParentStruct processSixteen(char _suffix, String _nb) {
        if (_nb.length() > 16) {
            return NullStruct.NULL_VALUE;
        }
        boolean[] bits_ = NumParsers.parseLongSixteenToBits(_nb);
        if (isLongSuffix(_suffix)) {
            long longValue_ = NumParsers.toLong(bits_);
            return new LongStruct(longValue_);
        }
        if (isIntSuffix(_suffix)) {
            return processBaseInt(_nb, bits_, 8);
        }
        if (isCharSuffix(_suffix)) {
            if (_nb.length() > 4) {
                return NullStruct.NULL_VALUE;
            }
            char int_ = NumParsers.parseCharSixteen(_nb);
            return new CharStruct(int_);
        }
        if (isShortSuffix(_suffix)) {
            return processBaseShort(_nb, bits_, 4);
        }
        if (_nb.length() > 2) {
            return NullStruct.NULL_VALUE;
        }
        byte int_ = NumParsers.extractByte(bits_);
        return new ByteStruct(int_);
    }

    private static WithoutParentStruct processTwo(char _suffix, String _nb) {
        if (_nb.length() > 64) {
            return NullStruct.NULL_VALUE;
        }
        boolean[] bits_ = NumParsers.parseLongBinaryToBits(_nb);
        if (isLongSuffix(_suffix)) {
            long longValue_ = NumParsers.toLong(bits_);
            return new LongStruct(longValue_);
        }
        if (isIntSuffix(_suffix)) {
            return processBaseInt(_nb, bits_, 32);
        }
        if (isCharSuffix(_suffix)) {
            return processBaseChar(_nb, bits_);
        }
        if (isShortSuffix(_suffix)) {
            return processBaseShort(_nb, bits_, 16);
        }
        if (_nb.length() > 8) {
            return NullStruct.NULL_VALUE;
        }
        byte int_ = NumParsers.extractByte(bits_);
        return new ByteStruct(int_);
    }

    private static WithoutParentStruct processBaseShort(String _nb, boolean[] _bits, int _max) {
        if (_nb.length() > _max) {
            return NullStruct.NULL_VALUE;
        }
        short int_ = NumParsers.extractShort(_bits);
        return new ShortStruct(int_);
    }

    private static WithoutParentStruct processBaseChar(String _nb, boolean[] _bits) {
        if (_nb.length() > 16) {
            return NullStruct.NULL_VALUE;
        }
        char int_ = (char) NumParsers.extractShort(_bits);
        return new CharStruct(int_);
    }

    private static WithoutParentStruct processBaseInt(String _nb, boolean[] _bits, int _max) {
        if (_nb.length() > _max) {
            return NullStruct.NULL_VALUE;
        }
        int int_ = NumParsers.extractInt(_bits);
        return new IntStruct(int_);
    }

    private static WithoutParentStruct processEight(char _suffix, String _nb) {
        if (isLongSuffix(_suffix)) {
            return processEightLong(_nb);
        }
        if (isCharSuffix(_suffix)) {
            return processEightChar(_nb);
        }
        LongInfo lg_ = NumParsers.parseLong(_nb, 8);
        if (!lg_.isValid()) {
            return NullStruct.NULL_VALUE;
        }
        long value_ = lg_.getValue();
        if (isIntSuffix(_suffix)) {
            return processEightInt(_nb, value_);
        }
        if (isShortSuffix(_suffix)) {
            return processEightShort(_nb, value_);
        }
        return processEightByte(_nb, value_);
    }

    private static WithoutParentStruct processEightByte(String _nb, long _value) {
        if (_nb.length() > 3) {
            return NullStruct.NULL_VALUE;
        }
        if (_nb.length() == 3 && notFourFirst(_nb)) {
            return NullStruct.NULL_VALUE;
        }
        long value_ = _value;
        if (value_ >= Byte.MAX_VALUE + 1L) {
            while (value_ >= 0) {
                value_ -= Byte.MAX_VALUE;
                value_--;
            }
        }
        return new ByteStruct((byte) value_);
    }

    private static WithoutParentStruct processEightShort(String _nb, long _value) {
        if (_nb.length() > 6) {
            return NullStruct.NULL_VALUE;
        }
        if (_nb.length() == 6 && notFourFirst(_nb)) {
            return NullStruct.NULL_VALUE;
        }
        long value_ = _value;
        if (value_ >= Short.MAX_VALUE + 1L) {
            while (value_ >= 0) {
                value_ -= Short.MAX_VALUE;
                value_--;
            }
        }
        return new ShortStruct((short) value_);
    }

    private static WithoutParentStruct processEightInt(String _nb, long _value) {
        if (_nb.length() > 11) {
            return NullStruct.NULL_VALUE;
        }
        if (_nb.length() == 11 && notFourFirst(_nb)) {
            return NullStruct.NULL_VALUE;
        }
        long value_ = _value;
        if (value_ >= Integer.MAX_VALUE + 1L) {
            while (value_ >= 0) {
                value_ -= Integer.MAX_VALUE;
                value_--;
            }
        }
        return new IntStruct((int) value_);
    }

    private static WithoutParentStruct processEightChar(String _nb) {
        if (_nb.length() > 6) {
            return NullStruct.NULL_VALUE;
        }
        if (_nb.length() == 6 && notTwoFirst(_nb)) {
            return NullStruct.NULL_VALUE;
        }
        LongInfo lg_ = NumParsers.parseLong(_nb, 8);
        if (!lg_.isValid()) {
            return NullStruct.NULL_VALUE;
        }
        return new CharStruct((char) lg_.getValue());
    }

    private static WithoutParentStruct processEightLong(String _nb) {
        if (_nb.length() > 22) {
            return NullStruct.NULL_VALUE;
        }
        int sub_ = 0;
        boolean rev_ = false;
        if (_nb.length() == 22) {
            if (notTwoFirst(_nb)) {
                return NullStruct.NULL_VALUE;
            }
            rev_ = _nb.charAt(0) == '1';
            sub_ = 1;
        }
        String subString_ = _nb.substring(sub_);
        LongInfo lg_ = NumParsers.parseLong(subString_, 8);
        if (!lg_.isValid()) {
            return NullStruct.NULL_VALUE;
        }
        boolean[] bitsOutTrunc_ = NumParsers.parseLongOctalToBits(subString_);
        long longValue_ = NumParsers.toLong(bitsOutTrunc_,rev_,0,63);
        return new LongStruct(longValue_);
    }

    private static boolean notFourFirst(String _nb) {
        return _nb.charAt(0) != '0' && _nb.charAt(0) != '1' && _nb.charAt(0) != '2' && _nb.charAt(0) != '3';
    }

    private static boolean notTwoFirst(String _nb) {
        return _nb.charAt(0) != '0' && _nb.charAt(0) != '1';
    }

    private static boolean isFloatSuffix(char _suffix) {
        return _suffix == 'F' || _suffix == 'f';
    }

    private static boolean isDoubleSuffix(char _suffix) {
        return _suffix == 'D' || _suffix == 'd';
    }

    private static WithoutParentStruct processTen(char _suffix, String _nb) {
        LongInfo longValue_ = NumParsers.parseLong(_nb, 10);
        if (!longValue_.isValid()) {
            String str_  = StringUtil.concat("-", _nb);
            LongInfo oppLongValue_ = NumParsers.parseLong(str_, 10);
            if (oppLongValue_.isValid() && isLongSuffix(_suffix)) {
                return new LongStruct(Long.MIN_VALUE);
            }
            return NullStruct.NULL_VALUE;
        }
        long value_ = longValue_.getValue();
        if (isLongSuffix(_suffix)) {
            return new LongStruct(value_);
        }
        if (isIntSuffix(_suffix)) {
            return processTenInt(value_);
        }
        if (isShortSuffix(_suffix)) {
            return processTenShort(value_);
        }
        if (isByteSuffix(_suffix)) {
            return processTenByte(value_);
        }
        return processTenChar(value_);
    }

    private static WithoutParentStruct processTenInt(long _value) {
        if (_value > Integer.MAX_VALUE) {
            if (_value == Integer.MAX_VALUE + 1L) {
                return new IntStruct(Integer.MIN_VALUE);
            }
            return NullStruct.NULL_VALUE;
        }
        return new IntStruct((int) _value);
    }

    private static WithoutParentStruct processTenShort(long _value) {
        if (_value > Short.MAX_VALUE) {
            if (_value == Short.MAX_VALUE + 1L) {
                return new ShortStruct(Short.MIN_VALUE);
            }
            return NullStruct.NULL_VALUE;
        }
        return new ShortStruct((short) _value);
    }

    private static WithoutParentStruct processTenByte(long _value) {
        if (_value > Byte.MAX_VALUE) {
            if (_value == Byte.MAX_VALUE + 1L) {
                return new ByteStruct(Byte.MIN_VALUE);
            }
            return NullStruct.NULL_VALUE;
        }
        return new ByteStruct((byte) _value);
    }

    private static WithoutParentStruct processTenChar(long _value) {
        if (_value > Character.MAX_VALUE) {
            return NullStruct.NULL_VALUE;
        }
        return new CharStruct((char) _value);
    }

    private static boolean isCharSuffix(char _suffix) {
        return _suffix == 'C' || _suffix == 'c';
    }

    private static boolean isByteSuffix(char _suffix) {
        return _suffix == 'B' || _suffix == 'b';
    }

    private static boolean isShortSuffix(char _suffix) {
        return _suffix == 'S' || _suffix == 's';
    }

    private static boolean isIntSuffix(char _suffix) {
        return _suffix == 'I' || _suffix == 'i';
    }

    private static boolean isLongSuffix(char _suffix) {
        return _suffix == 'L' || _suffix == 'l';
    }


    public static DoubleInfo parseDouble(NumberInfos _nb) {
        StringBuilder int_ = new StringBuilder(_nb.getIntPart());
        removeNbSep(int_);
        StringBuilder dec_ = new StringBuilder(_nb.getDecimalPart());
        removeNbSep(dec_);
        StringBuilder exp_ = new StringBuilder(_nb.getExponentialPart());
        removeNbSep(exp_);
        boolean positive_ = _nb.isPositive();
        LongInfo expNb_ = exponent(exp_);
        if (!expNb_.isValid()) {
            return bulldBoundNbArea(exp_, positive_);
        }
        long expNbLong_ = expNb_.getValue();
        StringBuilder nb_ = new StringBuilder(int_);
        nb_.append(dec_);
        if (_nb.getBase() == 16) {
            long longValue_ = parseLongSixteen(nb_.toString());
            return buildNb(dec_, expNbLong_, longValue_, 4L);
        }
        if (_nb.getBase() == 2) {
            long longValue_ = parseLongBase(nb_.toString(), 2);
            return buildNb(dec_, expNbLong_, longValue_, 1L);
        }
        if (_nb.getBase() == 8) {
            long longValue_ = parseLongBase(nb_.toString(), 8);
            return buildNb(dec_, expNbLong_, longValue_, 3L);
        }
        if (dec_.length() == 0) {
            return noDotTenBase(int_, positive_, expNbLong_);
        }
        if (expNbLong_ >= dec_.length()) {
            return exitDigitsFromDecPart(positive_, nb_, (int) expNbLong_ - dec_.length());
        }
        if (-expNbLong_ >= int_.length()) {
            return insertDot(int_, dec_, positive_, expNbLong_, nb_);
        }
        return shiftDot(int_, dec_, positive_, expNbLong_);
    }

    private static DoubleInfo shiftDot(StringBuilder _int, StringBuilder _dec, boolean _positive, long _expNbLong) {
        StringBuilder numberInt_ = new StringBuilder();
        StringBuilder numberDec_ = new StringBuilder();
        if (_expNbLong > 0) {
            //expNbLong_ < dec_.length() => dec_.length() > 0 => numberInt_.length() > 0
            //-expNbLong_ < int_.length()
            numberInt_.append(_int);
            numberInt_.append(_dec.substring(0, (int) _expNbLong));
            numberDec_.append(_dec.substring((int) _expNbLong));
        } else if (_expNbLong == 0) {
            //expNbLong_ < dec_.length() => 0 < dec_.length()
            //-expNbLong_ < int_.length() => 0 < int_.length() => numberInt_.length() > 0
            numberInt_.append(_int);
            numberDec_.append(_dec);
        } else {
            //expNbLong_ < 0
            int del_ = _int.length() +(int) _expNbLong;
            //-expNbLong_ < int_.length() => 0 < -expNbLong_ < int_.length() => 0 < int_.length()
            //-expNbLong_ < int_.length() => 0 < expNbLong_ + int_.length() => numberInt_.length() > 0
            numberInt_.append(_int.substring(0, del_));
            numberDec_.append(_int.substring(del_));
            numberDec_.append(_dec);
        }
        if (numberInt_.length() > MAX_DIGITS_DOUBLE) {
            return bigNb(_positive, numberInt_);
        }
        long longValue_ = parseQuickLongTen(numberInt_.toString());
        double value_ = (double) longValue_;
        int index_ = indexNotZero(numberDec_);
        StringBuilder decCopy_ = new StringBuilder(numberDec_.substring(index_));
        decCopy_.delete(Math.min(MAX_DIGITS_DOUBLE + 1, decCopy_.length()), decCopy_.length());
        if (decCopy_.length() == 0) {
            return buildNbSimple(_positive, longValue_, value_);
        }
        long decLongValue_ = parseQuickLongTen(decCopy_.toString());
        double decValue_ = (double) decLongValue_;
        double power_ = pow(numberDec_.length(),10.0);
        if (!_positive) {
            return new DoubleInfo(-value_ - decValue_ / power_);
        }
        return new DoubleInfo(value_ + decValue_ / power_);
    }

    private static DoubleInfo insertDot(StringBuilder _int, StringBuilder _dec, boolean _positive, long _expNbLong, StringBuilder _nb) {
        int index_ = indexNotZero(_nb);
        StringBuilder decCopy_ = new StringBuilder(_nb.substring(index_));
        if (decCopy_.length() == 0) {
            if (!_positive) {
                return new DoubleInfo(-0.0,true);
            }
            return new DoubleInfo(0.0,true);
        }
        long longValue_;
        int diff_;
        if (decCopy_.length() > MAX_DIGITS_DOUBLE) {
            longValue_ = parseQuickLongTen(decCopy_.substring(0, MAX_DIGITS_DOUBLE + 1));
            diff_ = (int) (-_expNbLong - _int.length() + MAX_DIGITS_DOUBLE + 1 + index_);
            //-expNbLong_ >= int_.length() => -expNbLong_ - int_.length() >= 0
            //-expNbLong_ >= int_.length() => -expNbLong_ - int_.length() + 1 > 0
            //-expNbLong_ >= int_.length() => -expNbLong_ - int_.length() + MAX_DIGITS_DOUBLE + 1 > MAX_DIGITS_DOUBLE > 0
        } else {
            longValue_ = parseQuickLongTen(decCopy_.toString());
            diff_ = (int) (-_expNbLong + _dec.length());
            //expNbLong_ < dec_.length() => 0 < dec_.length() - expNbLong_
        }
        double value_ = (double) longValue_;
        double power_ = pow(diff_,10.0);
        if (!_positive) {
            return new DoubleInfo(-value_ / power_);
        }
        return new DoubleInfo(value_ / power_);
    }

    private static DoubleInfo exitDigitsFromDecPart(boolean _positive, StringBuilder _nb, int _diff) {
        //try to get "double" as int
        StringBuilder number_ = new StringBuilder(_nb);
        for (long i = 0; i < _diff; i++) {
            number_.append("0");
        }
        if (number_.length() > MAX_DIGITS_DOUBLE) {
            return bigNb(_positive, number_);
        }
        long longValue_ = parseQuickLongTen(number_.toString());
        double value_ = (double) longValue_;
        return buildNbSimple(_positive, longValue_, value_);
    }

    private static DoubleInfo noDotTenBase(StringBuilder _int, boolean _positive, long _expNbLong) {
        if (_expNbLong == 0) {
            if (_int.length() > MAX_DIGITS_DOUBLE) {
                return bigNb(_positive, _int);
            }
            long longValue_ = parseQuickLongTen(_int.toString());
            double value_ = (double) longValue_;
            return buildNbSimple(_positive, longValue_, value_);
        }
        long longValue_;
        long expNbLong_;
        if (_int.length() > MAX_DIGITS_DOUBLE) {
            //MAX_DIGITS_DOUBLE >= 0 => MAX_DIGITS_DOUBLE + 1 > 0
            longValue_ = parseQuickLongTen(_int.substring(0, MAX_DIGITS_DOUBLE + 1));
            expNbLong_ = _expNbLong+_int.length() - MAX_DIGITS_DOUBLE - 1;
        } else {
            //dec_.length() + int_.toString() > 0 && dec_.length() == 0 => int_.toString() > 0
            longValue_ = parseQuickLongTen(_int.toString());
            expNbLong_ = _expNbLong;
        }
        double value_ = (double) longValue_;
        double power_ = pow(expNbLong_,10.0);
        if (!_positive) {
            return buildNb(-value_, expNbLong_, power_, longValue_);
        }
        return buildNb(value_, expNbLong_, power_, longValue_);
    }

    private static DoubleInfo bulldBoundNbArea(StringBuilder _exp, boolean _positive) {
        if (_positive) {
            if (StringExpUtil.startsWith(_exp,'-')) {
                return new DoubleInfo(0.0);
            }
            return new DoubleInfo(Double.POSITIVE_INFINITY);
        }
        if (StringExpUtil.startsWith(_exp,'-')) {
            return new DoubleInfo(-0.0);
        }
        return new DoubleInfo(Double.NEGATIVE_INFINITY);
    }

    private static LongInfo exponent(StringBuilder _exp) {
        LongInfo expNb_;
        if (StringExpUtil.startsWith(_exp,'+')) {
            expNb_ = parseLong(_exp.substring(1), 10);
        } else if (_exp.length() == 0) {
            expNb_ = new LongInfo(0);
        } else {
            expNb_ = parseLong(_exp.toString(), 10);
        }
        return expNb_;
    }

    private static DoubleInfo bigNb(boolean _positive, StringBuilder _number) {
        return new DoubleInfo(processBigNumbers(_number, _positive));
    }

    private static DoubleInfo buildNbSimple(boolean _positive, long _longValue, double _value) {
        boolean zero_ = _longValue == 0;
        if (!_positive) {
            return new DoubleInfo(-_value, zero_);
        }
        return new DoubleInfo(_value, zero_);
    }

    private static DoubleInfo buildNb(StringBuilder _dec, long _expNbLong, long _longValue, long _ra) {
        double parsed_ = (double) _longValue;
        long delta_ = delta(_dec, _expNbLong, _ra);
        double p_ = pow(delta_, 2.0);
        return buildNb(parsed_, delta_, p_, _longValue);
    }

    private static long delta(StringBuilder _dec, long _expNbLong, long _ra) {
        return _expNbLong - _ra * _dec.length();
    }

    private static double pow(long _delta, double _exp) {
        double p_ = 1.0;
        long absExpNbLong_ = Math.abs(_delta);
        for (int i = 0; i < absExpNbLong_; i++) {
            p_ *= _exp;
        }
        return p_;
    }

    private static DoubleInfo buildNb(double _parsed, long _delta, double _p, long _longValue) {
        return buildNb(_parsed, _delta, _p, _longValue == 0);
    }

    private static DoubleInfo buildNb(double _parsed, long _delta, double _p, boolean _zero) {
        if (_delta > 0) {
            return new DoubleInfo(_parsed * _p,_zero);
        }
        return new DoubleInfo(_parsed / _p,_zero);
    }

    private static int indexNotZero(StringBuilder _number) {
        int index_ = 0;
        while (index_ < _number.length()) {
            if (_number.charAt(index_) != '0') {
                break;
            }
            index_++;
        }
        return index_;
    }

    private static void removeNbSep(StringBuilder _part) {
        while (_part.indexOf("_") >= 0) {
            _part.deleteCharAt(_part.indexOf("_"));
        }
    }

    private static double processBigNumbers(StringBuilder _nb, boolean _positive) {
        double long_ = (double) parseQuickLongTen(_nb.substring(0, MAX_DIGITS_DOUBLE + 1));
        int logDec_ = _nb.length() - MAX_DIGITS_DOUBLE - 1;
        double power_ = pow(logDec_,10.0);
        double out_ = long_ * power_;
        if (_positive) {
            return out_;
        }
        return -out_;
    }

    //this long parser is very naive
    public static char parseCharSixteen(String _string) {
        return (char)parseLongSixteen(_string);
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
        StringBuilder str_ = init(_string, 16);
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
        StringBuilder str_ = init(_string, 21);
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
        StringBuilder str_ = init(_string, 64);
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

    private static StringBuilder init(String _string, int _nb) {
        StringBuilder str_;
        if (_string.length() < _nb) {
            str_ = new StringBuilder();
            int add_ = _nb - _string.length();
            for (int i = 0; i < add_; i++) {
                str_.append("0");
            }
            for (int i = add_; i < _nb; i++) {
                str_.append(_string.charAt(i - add_));
            }
        } else {
            str_ = new StringBuilder(_string);
        }
        return str_;
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
        if (_radix < Character.MIN_RADIX || _radix > Character.MAX_RADIX) {
            return new LongInfo();
        }
        int max_ = _string.length();
        if (max_ <= 0) {
            return new LongInfo();
        }
        boolean negative_ = _string.charAt(0) == '-';
        int i_ = first(negative_);
        if (i_ >= max_) {
            return new LongInfo();
        }
        int chEnt_ = _string.charAt(i_);
        int digEnt_ = dig(chEnt_,_radix);
        if (digEnt_ < 0) {
            return new LongInfo();
        }
        return buildAccLg(_string,_radix,i_,negative_,-digEnt_);
    }

    private static int first(boolean _negative) {
        int i_;
        if (_negative) {
            i_ = 1;
        } else {
            i_ = 0;
        }
        return i_;
    }

    private static LongInfo buildAccLg(String _string, int _radix, int _i, boolean _negative, long _result) {
        int max_ = _string.length();
        long result_ = _result;
        long limit_;
        if (_negative) {
            limit_ = Long.MIN_VALUE;
        } else {
            limit_ = -Long.MAX_VALUE;
        }
        long multmin_ = limit_ / _radix;
        int i_ = _i;
        i_++;
        while (i_ < max_) {
            // Accumulating negatively avoids surprises near MAX_VALUE
            int ch_ = _string.charAt(i_);
            int dig_ = dig(ch_,_radix);
            if (dig_ < 0 || result_ < multmin_) {
                return new LongInfo();
            }
            result_ *= _radix;
            if (result_ < limit_ + dig_) {
                return new LongInfo();
            }
            result_ -= dig_;
            i_++;
        }
        return buildLg(_negative, result_);
    }
    private static LongInfo buildLg(boolean _negative, long _result) {
        if (_negative) {
            return new LongInfo(_result);
        }
        return new LongInfo(-_result);
    }

    private static int dig(int _ch, int _radix) {
        int dig_ = dig(_ch);
        if (dig_ < 0) {
            return -1;
        }
        if (dig_ >= _radix) {
            return -1;
        }
        return dig_;
    }
    private static int dig(int _ch) {
        if (_ch >= '0' && _ch <= '9') {
            return _ch - '0';
        }
        if (_ch >= 'a' && _ch <= 'z') {
            return 10 + _ch - 'a';
        }
        if (_ch >= 'A' && _ch <= 'Z') {
            return 10 + _ch - 'A';
        }
        return -1;
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
        return buildIntDecExpPart(infos_,_nb,i_);
    }

    private static NumberInfos buildIntDecExpPart(NumberInfos _built, String _nb, int _i) {
        int len_ = _nb.length();
        int i_ = _i;
        StringBuilder intPart_ = new StringBuilder();
        _built.setIntPart(intPart_);
        StringBuilder decimalPart_ = new StringBuilder();
        _built.setDecimalPart(decimalPart_);
        StringBuilder exponentialPart_ = new StringBuilder();
        _built.setExponentialPart(exponentialPart_);
        while (i_ < len_) {
            char cur_ = _nb.charAt(i_);
            if (!StringExpUtil.isDigit(cur_)) {
                if (cur_ != DOT_VAR && isNotExpSymbol(cur_)) {
                    return null;
                }
                break;
            }
            intPart_.append(cur_);
            i_++;
        }
        return buildDecExpPart(_built,_nb,i_);
    }
    private static NumberInfos buildDecExpPart(NumberInfos _built, String _nb, int _i) {
        StringBuilder intPart_ = _built.getIntPart();
        StringBuilder decimalPart_ = _built.getDecimalPart();
        int len_ = _nb.length();
        int i_ = _i;
        if (StringExpUtil.nextCharIs(_nb,i_,len_,DOT_VAR)) {
            i_++;
            while (i_ < len_) {
                char cur_ = _nb.charAt(i_);
                if (!StringExpUtil.isDigit(cur_)) {
                    if (isNotExpSymbol(cur_)) {
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
            return _built;
        }
        return buildExpPart(_built,_nb,i_);
    }
    private static NumberInfos buildExpPart(NumberInfos _built, String _nb, int _i) {
        int len_ = _nb.length();
        StringBuilder exponentialPart_ = _built.getExponentialPart();
        int i_ = _i;
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
        return _built;
    }
    private static boolean isNotExpSymbol(char _cur) {
        return _cur != EXP && _cur != EXP_UPP;
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
            one_ = convertToNumber(_args[0]).doubleStruct();
        }
        return one_;
    }

    public static int getRadix(StringList _list, Struct[] _args) {
        int radix_ = DEFAULT_RADIX;
        if (_list.size() != 1) {
            radix_ = convertToNumber(_args[1]).intStruct();
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
        String useOp_ = usedOp(_op);
        BooleanStruct arg_;
        if (StringUtil.quickEq(useOp_, LOWER)) {
            arg_ = quickCalculateLowerNb(_one, _two);
        } else {
            arg_ = quickCalculateGreaterNb(_one, _two);
        }
        if (complement(_op)) {
            arg_ = arg_.neg();
        }
        return arg_;
    }

    public static BooleanStruct compareStr(String _op, Struct _one, Struct _two) {
        String useOp_ = usedOp(_op);
        BooleanStruct arg_;
        if (StringUtil.quickEq(useOp_, LOWER)) {
            arg_ = quickCalculateLowerStr(_one, _two);
        } else {
            arg_ = quickCalculateGreaterStr(_one, _two);
        }
        if (complement(_op)) {
            arg_ = arg_.neg();
        }
        return arg_;
    }

    private static String usedOp(String _op) {
        String useOp_ = _op;
        if (StringUtil.quickEq(_op, LOWER_EQ)) {
            useOp_ = GREATER;
        } else if (StringUtil.quickEq(_op, GREATER_EQ)) {
            useOp_ = LOWER;
        }
        return useOp_;
    }

    private static boolean complement(String _op) {
        return StringUtil.quickEq(_op, LOWER_EQ) || StringUtil.quickEq(_op, GREATER_EQ);
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
