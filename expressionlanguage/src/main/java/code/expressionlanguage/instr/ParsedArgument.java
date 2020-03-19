package code.expressionlanguage.instr;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.stds.LongInfo;
import code.expressionlanguage.stds.NumParsers;
import code.expressionlanguage.structs.*;
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
            double d_ = NumParsers.parseDouble(_infosNb);
            if (suffix_ == 'D' || suffix_ == 'd') {
                if (suffix_ == 'd') {
                    out_.type = doublePrimType_;
                } else {
                    out_.type = doubleType_;
                }
                out_.object = new DoubleStruct(d_);
            } else {
                if (!checkedDoubleBounds(d_)) {
                    return out_;
                }
                if (suffix_ == 'f') {
                    out_.type = floatPrimType_;
                } else {
                    out_.type = floatType_;
                }
                out_.object = new FloatStruct((float) d_);
            }
            return out_;
        }
        StringBuilder nbFormatted_ = _infosNb.getIntPart();
        String nb_ = StringList.removeChars(StringList.removeAllSpaces(nbFormatted_.toString()), '_');
        if (_infosNb.getBase() == 16) {
            if (nb_.length() > 16) {
                return out_;
            }
            boolean[] bits_ = NumParsers.parseLongSixteenToBits(nb_);
            if (suffix_ == 'L' || suffix_ == 'l') {
                long longValue_ = NumParsers.toLong(bits_);
                if (suffix_ == 'l') {
                    out_.type = longPrimType_;
                } else {
                    out_.type = longType_;
                }
                out_.object = new LongStruct(longValue_);
                return out_;
            }
            if (suffix_ == 'I' || suffix_ == 'i') {
                if (nb_.length() > 8) {
                    return out_;
                }
                int int_ = NumParsers.extractInt(bits_);
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
                char int_ = NumParsers.parseCharSixteen(nb_);
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
                short int_ = NumParsers.extractShort(bits_);
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
            byte int_ = NumParsers.extractByte(bits_);
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
            boolean[] bits_ = NumParsers.parseLongBinaryToBits(nb_);
            if (suffix_ == 'L' || suffix_ == 'l') {
                long longValue_ = NumParsers.toLong(bits_);
                if (suffix_ == 'l') {
                    out_.type = longPrimType_;
                } else {
                    out_.type = longType_;
                }
                out_.object = new LongStruct(longValue_);
                return out_;
            }
            if (suffix_ == 'I' || suffix_ == 'i') {
                if (nb_.length() > 32) {
                    return out_;
                }
                int int_ = NumParsers.extractInt(bits_);
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
                char int_ = (char) NumParsers.extractShort(bits_);
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
                short int_ = NumParsers.extractShort(bits_);
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
            byte int_ = NumParsers.extractByte(bits_);
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
                boolean rev_ = false;
                if (nb_.length() == 22) {
                    if (nb_.charAt(0) != '0' && nb_.charAt(0) != '1') {
                        return out_;
                    }
                    rev_ = nb_.charAt(0) == '1';
                    sub_ = 1;
                }
                String subString_ = nb_.substring(sub_);
                LongInfo lg_ = NumParsers.parseLong(subString_, 8);
                if (!lg_.isValid()) {
                    return out_;
                }
                boolean[] bitsOutTrunc_ = NumParsers.parseLongOctalToBits(subString_);
                long longValue_ = NumParsers.toLong(bitsOutTrunc_,rev_,0,63);
                if (suffix_ == 'l') {
                    out_.type = longPrimType_;
                } else {
                    out_.type = longType_;
                }
                out_.object = new LongStruct(longValue_);
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
                LongInfo lg_ = NumParsers.parseLong(nb_, 8);
                if (!lg_.isValid()) {
                    return out_;
                }
                out_.type = charPrimType_;
                out_.object = new CharStruct((char) lg_.getValue());
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
            LongInfo lg_ = NumParsers.parseLong(nb_, 8);
            if (!lg_.isValid()) {
                return out_;
            }
            long value_ = lg_.getValue();
            if (suffix_ == 'I' || suffix_ == 'i') {
                if (value_ >= Integer.MAX_VALUE + 1L) {
                    while (value_ >= 0) {
                        value_ -= Integer.MAX_VALUE;
                        value_--;
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
                if (value_ >= Short.MAX_VALUE + 1L) {
                    while (value_ >= 0) {
                        value_ -= Short.MAX_VALUE;
                        value_--;
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
            if (value_ >= Byte.MAX_VALUE + 1L) {
                while (value_ >= 0) {
                    value_ -= Byte.MAX_VALUE;
                    value_--;
                }
            }
            if (suffix_ == 'b') {
                out_.type = bytePrimType_;
            } else {
                out_.type = byteType_;
            }
            out_.object = new ByteStruct((byte) value_);
            return out_;
        }
        LongInfo longValue_ = NumParsers.parseLongTen(nb_);
        if (!longValue_.isValid()) {
            String str_  = StringList.concat("-",nb_);
            LongInfo oppLongValue_ = NumParsers.parseLongTen(str_);
            if (oppLongValue_.isValid()) {
                if (suffix_ == 'L' || suffix_ == 'l') {
                    out_.object = new LongStruct(Long.MIN_VALUE);
                    if (suffix_ == 'l') {
                        out_.type = longPrimType_;
                    } else {
                        out_.type = longType_;
                    }
                }
            }
            return out_;
        }
        long value_ = longValue_.getValue();
        if (suffix_ == 'L' || suffix_ == 'l') {
            if (suffix_ == 'l') {
                out_.type = longPrimType_;
            } else {
                out_.type = longType_;
            }
            out_.object = new LongStruct(value_);
            return out_;
        }
        if (suffix_ == 'I' || suffix_ == 'i') {
            if (outOfBounds(value_, Integer.MAX_VALUE)) {
                if (value_ == Integer.MAX_VALUE + 1L) {
                    if (suffix_ == 'i') {
                        out_.type = longPrimType_;
                    } else {
                        out_.type = longType_;
                    }
                    out_.object = new LongStruct(Integer.MAX_VALUE + 1L);
                }
                return out_;
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
            if (outOfBounds(value_, Short.MAX_VALUE)) {
                if (value_ == Short.MAX_VALUE + 1L) {
                    if (suffix_ == 's') {
                        out_.type = intPrimType_;
                    } else {
                        out_.type = intType_;
                    }
                    out_.object = new IntStruct(Short.MAX_VALUE + 1);
                }
                return out_;
            }
            if (suffix_ == 's') {
                out_.type = shortPrimType_;
            } else {
                out_.type = shortType_;
            }
            out_.object = new ShortStruct((short) value_);
            return out_;
        }
        if (suffix_ == 'B' || suffix_ == 'b') {
            if (outOfBounds(value_, Byte.MAX_VALUE)) {
                if (value_ == Byte.MAX_VALUE + 1L) {
                    if (suffix_ == 'b') {
                        out_.type = shortPrimType_;
                    } else {
                        out_.type = shortType_;
                    }
                    out_.object = new ShortStruct((short) (Byte.MAX_VALUE + 1));
                }
                return out_;
            }
            if (suffix_ == 'b') {
                out_.type = bytePrimType_;
            } else {
                out_.type = byteType_;
            }
            out_.object = new ByteStruct((byte) value_);
            return out_;
        }
        if (outOfBounds(value_, Character.MAX_VALUE)) {
            return out_;
        }
        if (suffix_ == 'c') {
            out_.type = charPrimType_;
        } else {
            out_.type = charType_;
        }
        out_.object = new CharStruct((char) value_);
        return out_;
    }

    private static boolean outOfBounds(long _value, long _max) {
        return _value > _max;
    }


    private static boolean checkedDoubleBounds(double _value) {
        double value_ = Math.abs(_value);
        return value_ <= (double) Float.MAX_VALUE;
    }

    public Struct getStruct() {
        return object;
    }

    public String getType() {
        return type;
    }

}
