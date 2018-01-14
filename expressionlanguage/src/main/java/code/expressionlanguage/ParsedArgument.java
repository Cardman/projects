package code.expressionlanguage;

import code.expressionlanguage.opers.util.ByteStruct;
import code.expressionlanguage.opers.util.CharStruct;
import code.expressionlanguage.opers.util.DoubleStruct;
import code.expressionlanguage.opers.util.FloatStruct;
import code.expressionlanguage.opers.util.IntStruct;
import code.expressionlanguage.opers.util.LongStruct;
import code.expressionlanguage.opers.util.NullStruct;
import code.expressionlanguage.opers.util.ShortStruct;
import code.expressionlanguage.opers.util.Struct;
import code.expressionlanguage.stds.LgNames;
import code.util.StringList;

public final class ParsedArgument {

    private static final String UNEXPECTED_TYPE = "";

    private static final String DOUBLE_SUFFIX = "d";

    private static final String FLOAT_SUFFIX = "f";

    private static final String SHORT_SUFFIX = "s";

    private static final String LONG_SUFFIX = "l";

    private static final String BYTE_SUFFIX = "b";

    private static final String INT_SUFFIX = "i";

    private static final String CHAR_SUFFIX = "c";

    private Struct object = NullStruct.NULL_VALUE;

    private String type = UNEXPECTED_TYPE;

    public static ParsedArgument parse(NumberInfos _infosNb, ContextEl _context) {
        LgNames stds_ = _context.getStandards();
        String doubleType_ = stds_.getAliasDouble();
        String doublePrimType_ = stds_.getAliasPrimDouble();
        String floatType_ = stds_.getAliasFloat();
        String floatPrimType_ = stds_.getAliasPrimFloat();
        String longType_ = stds_.getAliasLong();
        String longPrimType_ = stds_.getAliasPrimLong();
        String intType_ = stds_.getAliasInteger();
        String intPrimType_ = stds_.getAliasPrimInteger();
        String charType_ = stds_.getAliasCharacter();
        String charPrimType_ = stds_.getAliasPrimChar();
        String shortType_ = stds_.getAliasShort();
        String shortPrimType_ = stds_.getAliasPrimShort();
        String byteType_ = stds_.getAliasByte();
        String bytePrimType_ = stds_.getAliasPrimByte();
        char suffix_ = _infosNb.getSuffix();
        ParsedArgument out_ = new ParsedArgument();
        if (suffix_ == 'D' || suffix_ == 'd' || suffix_ == 'F' || suffix_ == 'f') {
            StringBuilder nbFormatted_ = new StringBuilder();
            if (!_infosNb.isPositive()) {
                nbFormatted_.append("-");
            }
            nbFormatted_.append(_infosNb.getIntPart());
            if (_infosNb.isDotted()) {
                nbFormatted_.append(".");
                nbFormatted_.append(_infosNb.getDecimalPart());
            }
            if (_infosNb.getExponentialPart().length() > 0) {
                nbFormatted_.append("e");
                nbFormatted_.append(_infosNb.getExponentialPart());
            }
//            String nb_ = StringList.removeChars(StringList.removeAllSpaces(nbFormatted_.toString()), '_');
            if (suffix_ == 'd') {
                out_.type = doublePrimType_;
            } else if (suffix_ == 'D'){
                out_.type = doubleType_;
            } else if (suffix_ == 'f') {
                out_.type = floatPrimType_;
            } else {
                out_.type = floatType_;
            }
            if (suffix_ == 'D' || suffix_ == 'd') {
                out_.object = new DoubleStruct(LgNames.parseDouble(_infosNb));
            } else {
                double d_ = LgNames.parseDouble(_infosNb);
                if (!checkedDoubleBounds(d_, Float.MIN_VALUE, Float.MAX_VALUE)) {
                    return out_;
                }
                out_.object = new FloatStruct((float) d_);
            }
            return out_;
        }
        StringBuilder nbFormatted_ = new StringBuilder();
        if (!_infosNb.isPositive()) {
            nbFormatted_.append("-");
        }
        nbFormatted_.append(_infosNb.getIntPart());
        String nb_ = StringList.removeChars(StringList.removeAllSpaces(nbFormatted_.toString()), '_');
        Long longValue_ = LgNames.parseLongTen(nb_);
        if (suffix_ == 'L' || suffix_ == 'l') {
            if (suffix_ == 'l') {
                out_.type = longPrimType_;
            } else {
                out_.type = longType_;
            }
            out_.object = new LongStruct(longValue_.longValue());
            return out_;
        }
        if (suffix_ == 'I' || suffix_ == 'i') {
            if (!checkedLongBounds(longValue_.longValue(), Integer.MIN_VALUE, Integer.MAX_VALUE)) {
                return out_;
            }
            if (suffix_ == 'i') {
                out_.type = intPrimType_;
            } else {
                out_.type = intType_;
            }
            out_.object = new IntStruct(longValue_.intValue());
            return out_;
        }
        if (suffix_ == 'S' || suffix_ == 's') {
            if (!checkedLongBounds(longValue_.longValue(), Short.MIN_VALUE, Short.MAX_VALUE)) {
                return out_;
            }
            if (suffix_ == 's') {
                out_.type = shortPrimType_;
            } else {
                out_.type = shortType_;
            }
            out_.object = new ShortStruct(longValue_.shortValue());
            return out_;
        }
        if (suffix_ == 'B' || suffix_ == 'b') {
            if (!checkedLongBounds(longValue_.longValue(), Byte.MIN_VALUE, Byte.MAX_VALUE)) {
                return out_;
            }
            if (suffix_ == 'b') {
                out_.type = bytePrimType_;
            } else {
                out_.type = byteType_;
            }
            out_.object = new ByteStruct(longValue_.byteValue());
            return out_;
        }
        if (!checkedLongBounds(longValue_.longValue(), Character.MIN_VALUE, Character.MAX_VALUE)) {
            return out_;
        }
        if (suffix_ == 'c') {
            out_.type = charPrimType_;
        } else {
            out_.type = charType_;
        }
        out_.object = new CharStruct(Character.valueOf((char) longValue_.longValue()));
        return out_;
//        if (!or(LgNames.isValidDouble(nb_), longValue_ == null)) {
//            return new ParsedArgument();
//        }
//        if (StringList.quickEq(nb_, removeUnderscores(_nb))) {
//            if (longValue_ != null) {
//                out_.type = longType_;
//                out_.object = new LongStruct(longValue_);
//                return out_;
//            }
//            out_.type = doubleType_;
//            out_.object = new DoubleStruct(Double.parseDouble(nb_));
//            return out_;
//        }
//        String parts_ = StringList.splitInTwo(_nb, _nb.length() - 1).last();
//        boolean long_ = false;
//        Number value_;
//        if (longValue_ != null) {
//            long_ = true;
//            value_ = longValue_;
//        } else {
//            value_ = Double.parseDouble(nb_);
//        }
//        if (StringList.quickEq(StringList.toLowerCase(parts_), INT_SUFFIX) && long_) {
//            if (!checkedLongBounds(value_.longValue(), Integer.MIN_VALUE, Integer.MAX_VALUE)) {
//                return out_;
//            }
//            if (StringList.quickEq(parts_, INT_SUFFIX)) {
//                out_.type = intPrimType_;
//            } else {
//                out_.type = intType_;
//            }
//            out_.object = new IntStruct(value_.intValue());
//            return out_;
//        }
//        if (StringList.quickEq(StringList.toLowerCase(parts_), BYTE_SUFFIX) && long_) {
//            if (!checkedLongBounds(value_.longValue(), Byte.MIN_VALUE, Byte.MAX_VALUE)) {
//                return out_;
//            }
//            if (StringList.quickEq(parts_, BYTE_SUFFIX)) {
//                out_.type = bytePrimType_;
//            } else {
//                out_.type = byteType_;
//            }
//            out_.object = new ByteStruct(value_.byteValue());
//            return out_;
//        }
//        if (StringList.quickEq(StringList.toLowerCase(parts_), LONG_SUFFIX) && long_) {
//            if (StringList.quickEq(parts_, LONG_SUFFIX)) {
//                out_.type = longPrimType_;
//            } else {
//                out_.type = longType_;
//            }
//            out_.object = new LongStruct(value_.longValue());
//            return out_;
//        }
//        if (StringList.quickEq(StringList.toLowerCase(parts_), SHORT_SUFFIX) && long_) {
//            if (!checkedLongBounds(value_.longValue(), Short.MIN_VALUE, Short.MAX_VALUE)) {
//                return out_;
//            }
//            if (StringList.quickEq(parts_, SHORT_SUFFIX)) {
//                out_.type = shortPrimType_;
//            } else {
//                out_.type = shortType_;
//            }
//            out_.object = new ShortStruct(value_.shortValue());
//            return out_;
//        }
//        if (StringList.quickEq(StringList.toLowerCase(parts_), CHAR_SUFFIX) && long_) {
//            if (!checkedLongBounds(value_.longValue(), Character.MIN_VALUE, Character.MAX_VALUE)) {
//                return out_;
//            }
//            if (StringList.quickEq(parts_, CHAR_SUFFIX)) {
//                out_.type = charPrimType_;
//            } else {
//                out_.type = charType_;
//            }
//            out_.object = new CharStruct(Character.valueOf((char) value_.longValue()));
//            return out_;
//        }
//        if (StringList.quickEq(StringList.toLowerCase(parts_), FLOAT_SUFFIX)) {
//            if (!checkedDoubleBounds(value_.doubleValue(), Float.MIN_VALUE, Float.MAX_VALUE)) {
//                return out_;
//            }
//            if (StringList.quickEq(parts_, FLOAT_SUFFIX)) {
//                out_.type = floatPrimType_;
//            } else {
//                out_.type = floatType_;
//            }
//            out_.object = new FloatStruct(value_.floatValue());
//            return out_;
//        }
//        if (StringList.quickEq(StringList.toLowerCase(parts_), DOUBLE_SUFFIX)) {
//            if (StringList.quickEq(parts_, DOUBLE_SUFFIX)) {
//                out_.type = doublePrimType_;
//            } else {
//                out_.type = doubleType_;
//            }
//            out_.object = new DoubleStruct(value_.doubleValue());
//            return out_;
//        }
//        return out_;
    }

    static boolean checkedLongBounds(long _value, long _min, long _max) {
        if (_value < _min) {
            return false;
        }
        if (_value > _max) {
            return false;
        }
        return true;
    }


    static boolean checkedDoubleBounds(double _value, double _min, double _max) {
        if (_value < _min) {
            return false;
        }
        if (_value > _max) {
            return false;
        }
        return true;
    }

    static String extractFromSuffix(String _nb) {
        boolean sub_ = false;
        if (StringList.toLowerCase(_nb).endsWith(INT_SUFFIX)) {
            sub_ = true;
        }
        if (StringList.toLowerCase(_nb).endsWith(BYTE_SUFFIX)) {
            sub_ = true;
        }
        if (StringList.toLowerCase(_nb).endsWith(LONG_SUFFIX)) {
            sub_ = true;
        }
        if (StringList.toLowerCase(_nb).endsWith(SHORT_SUFFIX)) {
            sub_ = true;
        }
        if (StringList.toLowerCase(_nb).endsWith(FLOAT_SUFFIX)) {
            sub_ = true;
        }
        if (StringList.toLowerCase(_nb).endsWith(DOUBLE_SUFFIX)) {
            sub_ = true;
        }
        if (StringList.toLowerCase(_nb).endsWith(CHAR_SUFFIX)) {
            sub_ = true;
        }
        String nb_ = StringList.removeChars(_nb, '_');
        if (sub_) {
            return nb_.substring(0, nb_.length() - 1);
        }
        return nb_;
    }
    static String removeUnderscores(String _value) {
        return StringList.removeChars(_value, '_');
    }
    static boolean or(boolean _one, boolean _two) {
        return _one || _two;
    }

    public Struct getStruct() {
        return object;
    }

    public String getType() {
        return type;
    }

}
