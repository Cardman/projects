package code.expressionlanguage.instr;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.ByteStruct;
import code.expressionlanguage.structs.CharStruct;
import code.expressionlanguage.structs.DoubleStruct;
import code.expressionlanguage.structs.FloatStruct;
import code.expressionlanguage.structs.IntStruct;
import code.expressionlanguage.structs.LongStruct;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.ShortStruct;
import code.expressionlanguage.structs.Struct;
import code.util.StringList;

public final class ParsedArgument {

    private static final String UNEXPECTED_TYPE = "";

    private Struct object = NullStruct.NULL_VALUE;

    private String type = UNEXPECTED_TYPE;

    public static ParsedArgument parse(NumberInfos _infosNb, Analyzable _context) {
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
        Long longValue_;
        if (_infosNb.getBase() == 16) {
            if (nb_.length() > 16) {
                return out_;
            }
            boolean[] bits_ = LgNames.parseLongSixteenToBits(nb_);
            if (suffix_ == 'L' || suffix_ == 'l') {
                longValue_ = LgNames.toLong(bits_);
                if (suffix_ == 'l') {
                    out_.type = longPrimType_;
                } else {
                    out_.type = longType_;
                }
                out_.object = new LongStruct(longValue_.longValue());
                return out_;
            }
            if (suffix_ == 'I' || suffix_ == 'i') {
                if (nb_.length() > 8) {
                    return out_;
                }
                int int_ = LgNames.extractInt(bits_);
                if (suffix_ == 'i') {
                    out_.type = intPrimType_;
                } else {
                    out_.type = intType_;
                }
                out_.object = new IntStruct(int_);
                return out_;
            }
            if (suffix_ == 'C' || suffix_ == 'c') {
                if (nb_.length() > 4) {
                    return out_;
                }
                char int_ = LgNames.parseCharSixteen(nb_);
                if (suffix_ == 'c') {
                    out_.type = charPrimType_;
                } else {
                    out_.type = charType_;
                }
                out_.object = new CharStruct(int_);
                return out_;
            }
            if (suffix_ == 'S' || suffix_ == 's') {
                if (nb_.length() > 4) {
                    return out_;
                }
                short int_ = LgNames.extractShort(bits_);
                if (suffix_ == 's') {
                    out_.type = shortPrimType_;
                } else {
                    out_.type = shortType_;
                }
                out_.object = new ShortStruct(int_);
                return out_;
            }
            if (nb_.length() > 2) {
                return out_;
            }
            byte int_ = LgNames.extractByte(bits_);
            if (suffix_ == 'b') {
                out_.type = bytePrimType_;
            } else {
                out_.type = byteType_;
            }
            out_.object = new ByteStruct(int_);
            return out_;
        }
        if (_infosNb.getBase() == 2) {
            if (nb_.length() > 64) {
                return out_;
            }
            boolean[] bits_ = LgNames.parseLongBinaryToBits(nb_);
            longValue_ = LgNames.toLong(bits_);
            if (suffix_ == 'L' || suffix_ == 'l') {
                longValue_ = LgNames.toLong(bits_);
                if (suffix_ == 'l') {
                    out_.type = longPrimType_;
                } else {
                    out_.type = longType_;
                }
                out_.object = new LongStruct(longValue_.longValue());
                return out_;
            }
            if (suffix_ == 'I' || suffix_ == 'i') {
                if (nb_.length() > 32) {
                    return out_;
                }
                int int_ = LgNames.extractInt(bits_);
                if (suffix_ == 'i') {
                    out_.type = intPrimType_;
                } else {
                    out_.type = intType_;
                }
                out_.object = new IntStruct(int_);
                return out_;
            }
            if (suffix_ == 'C' || suffix_ == 'c') {
                if (nb_.length() > 16) {
                    return out_;
                }
                char int_ = (char) LgNames.extractShort(bits_);
                if (suffix_ == 'c') {
                    out_.type = charPrimType_;
                } else {
                    out_.type = charType_;
                }
                out_.object = new CharStruct(int_);
                return out_;
            }
            if (suffix_ == 'S' || suffix_ == 's') {
                if (nb_.length() > 16) {
                    return out_;
                }
                short int_ = LgNames.extractShort(bits_);
                if (suffix_ == 's') {
                    out_.type = shortPrimType_;
                } else {
                    out_.type = shortType_;
                }
                out_.object = new ShortStruct(int_);
                return out_;
            }
            if (nb_.length() > 8) {
                return out_;
            }
            byte int_ = LgNames.extractByte(bits_);
            if (suffix_ == 'b') {
                out_.type = bytePrimType_;
            } else {
                out_.type = byteType_;
            }
            out_.object = new ByteStruct(int_);
            return out_;
        }
        if (_infosNb.getBase() == 8) {
            if (suffix_ == 'L' || suffix_ == 'l') {
                if (nb_.length() > 22) {
                    return out_;
                }
                int sub_ = 0;
                boolean[] bits_ = new boolean[64];
                if (nb_.length() == 22) {
                    if (nb_.charAt(0) != '0' && nb_.charAt(0) != '1') {
                        return out_;
                    }
                    bits_[sub_] = nb_.charAt(0) == '1';
                    sub_ = 1;
                }
                String subString_ = nb_.substring(sub_);
                boolean[] bitsOutTrunc_ = LgNames.parseLongOctalToBits(subString_);
                for (int i = 1; i < 64; i++) {
                    bits_[i] = bitsOutTrunc_[i-1];
                }
                longValue_ = LgNames.toLong(bits_);
                if (suffix_ == 'l') {
                    out_.type = longPrimType_;
                } else {
                    out_.type = longType_;
                }
                out_.object = new LongStruct(longValue_.longValue());
                return out_;
            }
            if (suffix_ == 'C' || suffix_ == 'c') {
                if (nb_.length() > 6) {
                    return out_;
                }
                if (nb_.length() == 6) {
                    if (nb_.charAt(0) != '0' && nb_.charAt(0) != '1') {
                        return out_;
                    }
                }
                Long lg_ = LgNames.parseLong(nb_, 8);
                if (lg_ == null) {
                    return out_;
                }
                out_.type = charPrimType_;
                out_.object = new CharStruct((char) lg_.longValue());
                return out_;
            }
            if (suffix_ == 'I' || suffix_ == 'i') {
                if (nb_.length() > 11) {
                    return out_;
                }
                if (nb_.length() == 11) {
                    if (nb_.charAt(0) != '0' && nb_.charAt(0) != '1' && nb_.charAt(0) != '2' && nb_.charAt(0) != '3') {
                        return out_;
                    }
                }
            } else if (suffix_ == 'S' || suffix_ == 's') {
                if (nb_.length() > 6) {
                    return out_;
                }
                if (nb_.length() == 6) {
                    if (nb_.charAt(0) != '0' && nb_.charAt(0) != '1' && nb_.charAt(0) != '2' && nb_.charAt(0) != '3') {
                        return out_;
                    }
                }
            } else {
                if (nb_.length() > 3) {
                    return out_;
                }
                if (nb_.length() == 3) {
                    if (nb_.charAt(0) != '0' && nb_.charAt(0) != '1' && nb_.charAt(0) != '2' && nb_.charAt(0) != '3') {
                        return out_;
                    }
                }
            }
            Long lg_ = LgNames.parseLong(nb_, 8);
            if (lg_ == null) {
                return out_;
            }
            long value_ = lg_;
            if (suffix_ == 'I' || suffix_ == 'i') {
                if (value_ >= Integer.MAX_VALUE + 1l) {
                    while (value_ >= 0) {
                        value_ -= Integer.MAX_VALUE;
                        value_ --;
                    }
                }
                if (suffix_ == 'i') {
                    out_.type = intPrimType_;
                } else {
                    out_.type = intType_;
                }
                out_.object = new IntStruct((int) value_);
                return out_;
            }
            if (suffix_ == 'S' || suffix_ == 's') {
                if (value_ >= Short.MAX_VALUE + 1l) {
                    while (value_ >= 0) {
                        value_ -= Short.MAX_VALUE;
                        value_ --;
                    }
                }
                if (suffix_ == 's') {
                    out_.type = shortPrimType_;
                } else {
                    out_.type = shortType_;
                }
                out_.object = new ShortStruct((short) value_);
                return out_;
            }
            if (value_ >= Byte.MAX_VALUE + 1l) {
                while (value_ >= 0) {
                    value_ -= Byte.MAX_VALUE;
                    value_ --;
                }
            }
            longValue_ = value_;
            if (suffix_ == 'b') {
                out_.type = bytePrimType_;
            } else {
                out_.type = byteType_;
            }
            out_.object = new ByteStruct(longValue_.byteValue());
            return out_;
        }
        longValue_ = LgNames.parseLongTen(nb_);
        if (longValue_ == null) {
            return out_;
        }
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
        out_.object = new CharStruct((char) longValue_.longValue());
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
        double value_ = Math.abs(_value);
        if (value_ < _min) {
            return false;
        }
        if (value_ > _max) {
            return false;
        }
        return true;
    }

    public Struct getStruct() {
        return object;
    }

    public String getType() {
        return type;
    }

}
