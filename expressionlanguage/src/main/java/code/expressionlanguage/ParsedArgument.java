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

    public static ParsedArgument parse(String _nb, ContextEl _context) {
        LgNames stds_ = _context.getStandards();
        if (_context.getClasses() != null) {
            
        }
        String nb_ = extractFromSuffix(_nb);
        Long longValue_ = LgNames.parseLongTen(nb_);
        if (!or(LgNames.isValidDouble(nb_), longValue_ == null)) {
            return new ParsedArgument();
        }
        ParsedArgument out_ = new ParsedArgument();
        if (StringList.quickEq(nb_, removeUnderscores(_nb))) {
            if (longValue_ != null) {
                out_.type = Long.class.getName();
                out_.object = new LongStruct(longValue_);
                return out_;
            }
            out_.type = Double.class.getName();
            out_.object = new DoubleStruct(Double.parseDouble(nb_));
            return out_;
        }
        String parts_ = StringList.splitInTwo(_nb, _nb.length() - 1).last();
        boolean long_ = false;
        Number value_;
        if (longValue_ != null) {
            long_ = true;
            value_ = longValue_;
        } else {
            value_ = Double.parseDouble(nb_);
        }
        if (StringList.quickEq(parts_.toLowerCase(), INT_SUFFIX) && long_) {
            if (!checkedLongBounds(value_.longValue(), Integer.MIN_VALUE, Integer.MAX_VALUE)) {
                return out_;
            }
            if (StringList.quickEq(parts_, INT_SUFFIX)) {
                out_.type = PrimitiveTypeUtil.PRIM_INT;
            } else {
                out_.type = Integer.class.getName();
            }
            out_.object = new IntStruct(value_.intValue());
            return out_;
        }
        if (StringList.quickEq(parts_.toLowerCase(), BYTE_SUFFIX) && long_) {
            if (!checkedLongBounds(value_.longValue(), Byte.MIN_VALUE, Byte.MAX_VALUE)) {
                return out_;
            }
            if (StringList.quickEq(parts_, BYTE_SUFFIX)) {
                out_.type = PrimitiveTypeUtil.PRIM_BYTE;
            } else {
                out_.type = Byte.class.getName();
            }
            out_.object = new ByteStruct(value_.byteValue());
            return out_;
        }
        if (StringList.quickEq(parts_.toLowerCase(), LONG_SUFFIX) && long_) {
            if (StringList.quickEq(parts_, LONG_SUFFIX)) {
                out_.type = PrimitiveTypeUtil.PRIM_LONG;
            } else {
                out_.type = Long.class.getName();
            }
            out_.object = new LongStruct(value_.longValue());
            return out_;
        }
        if (StringList.quickEq(parts_.toLowerCase(), SHORT_SUFFIX) && long_) {
            if (!checkedLongBounds(value_.longValue(), Short.MIN_VALUE, Short.MAX_VALUE)) {
                return out_;
            }
            if (StringList.quickEq(parts_, SHORT_SUFFIX)) {
                out_.type = PrimitiveTypeUtil.PRIM_SHORT;
            } else {
                out_.type = Short.class.getName();
            }
            out_.object = new ShortStruct(value_.shortValue());
            return out_;
        }
        if (StringList.quickEq(parts_.toLowerCase(), CHAR_SUFFIX) && long_) {
            if (!checkedLongBounds(value_.longValue(), Character.MIN_VALUE, Character.MAX_VALUE)) {
                return out_;
            }
            if (StringList.quickEq(parts_, CHAR_SUFFIX)) {
                out_.type = PrimitiveTypeUtil.PRIM_CHAR;
            } else {
                out_.type = Character.class.getName();
            }
            out_.object = new CharStruct(Character.valueOf((char) value_.longValue()));
            return out_;
        }
        if (StringList.quickEq(parts_.toLowerCase(), FLOAT_SUFFIX)) {
            if (!checkedDoubleBounds(value_.doubleValue(), Float.MIN_VALUE, Float.MAX_VALUE)) {
                return out_;
            }
            if (StringList.quickEq(parts_, FLOAT_SUFFIX)) {
                out_.type = PrimitiveTypeUtil.PRIM_FLOAT;
            } else {
                out_.type = Float.class.getName();
            }
            out_.object = new FloatStruct(value_.floatValue());
            return out_;
        }
        if (StringList.quickEq(parts_.toLowerCase(), DOUBLE_SUFFIX)) {
            if (StringList.quickEq(parts_, DOUBLE_SUFFIX)) {
                out_.type = PrimitiveTypeUtil.PRIM_DOUBLE;
            } else {
                out_.type = Double.class.getName();
            }
            out_.object = new DoubleStruct(value_.doubleValue());
            return out_;
        }
        return out_;
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
        if (_nb.toLowerCase().endsWith(INT_SUFFIX)) {
            sub_ = true;
        }
        if (_nb.toLowerCase().endsWith(BYTE_SUFFIX)) {
            sub_ = true;
        }
        if (_nb.toLowerCase().endsWith(LONG_SUFFIX)) {
            sub_ = true;
        }
        if (_nb.toLowerCase().endsWith(SHORT_SUFFIX)) {
            sub_ = true;
        }
        if (_nb.toLowerCase().endsWith(FLOAT_SUFFIX)) {
            sub_ = true;
        }
        if (_nb.toLowerCase().endsWith(DOUBLE_SUFFIX)) {
            sub_ = true;
        }
        if (_nb.toLowerCase().endsWith(CHAR_SUFFIX)) {
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
