package code.expressionlanguage.instr;

import code.expressionlanguage.common.NumberInfos;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;

public final class ParsedArgument {

    private static final String UNEXPECTED_TYPE = "";

    private Struct object = NullStruct.NULL_VALUE;

    private String type = UNEXPECTED_TYPE;

    public static ParsedArgument parse(NumberInfos _infosNb, LgNames _context) {
        String doubleType_ = _context.getAliasDouble();
        String doublePrimType_ = _context.getAliasPrimDouble();
        String floatType_ = _context.getAliasFloat();
        String floatPrimType_ = _context.getAliasPrimFloat();
        String longType_ = _context.getAliasLong();
        String longPrimType_ = _context.getAliasPrimLong();
        String intType_ = _context.getAliasInteger();
        String intPrimType_ = _context.getAliasPrimInteger();
        String charType_ = _context.getAliasCharacter();
        String charPrimType_ = _context.getAliasPrimChar();
        String shortType_ = _context.getAliasShort();
        String shortPrimType_ = _context.getAliasPrimShort();
        String byteType_ = _context.getAliasByte();
        String bytePrimType_ = _context.getAliasPrimByte();
        char suffix_ = _infosNb.getSuffix();
        Struct str_ = NumParsers.parseNb(_infosNb);
        ParsedArgument p_ = new ParsedArgument();
        p_.object = str_;
        if (str_ == NullStruct.NULL_VALUE) {
            return p_;
        }
        if (suffix_ == 'D') {
            p_.type = doubleType_;
            return p_;
        }
        if (suffix_ == 'd') {
            p_.type = doublePrimType_;
            return p_;
        }
        if (suffix_ == 'F') {
            p_.type = floatType_;
            return p_;
        }
        if (suffix_ == 'f') {
            p_.type = floatPrimType_;
            return p_;
        }
        if (suffix_ == 'L') {
            p_.type = longType_;
            return p_;
        }
        if (suffix_ == 'l') {
            p_.type = longPrimType_;
            return p_;
        }
        if (suffix_ == 'I') {
            p_.type = intType_;
            return p_;
        }
        if (suffix_ == 'i') {
            p_.type = intPrimType_;
            return p_;
        }
        if (suffix_ == 'C') {
            p_.type = charType_;
            return p_;
        }
        if (suffix_ == 'c') {
            p_.type = charPrimType_;
            return p_;
        }
        if (suffix_ == 'S') {
            p_.type = shortType_;
            return p_;
        }
        if (suffix_ == 's') {
            p_.type = shortPrimType_;
            return p_;
        }
        if (suffix_ == 'B') {
            p_.type = byteType_;
            return p_;
        }
        p_.type = bytePrimType_;
        return p_;
    }

    public Struct getStruct() {
        return object;
    }

    public String getType() {
        return type;
    }

}
