package code.expressionlanguage.analyze.instr;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.common.NumberInfos;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.options.*;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.NumberStruct;
import code.expressionlanguage.structs.Struct;
import code.maths.montecarlo.CustomSeedGene;
import code.util.CustList;
import code.util.core.StringUtil;

public final class ParsedArgument {

    private static final String UNEXPECTED_TYPE = "";

    private Struct object = NullStruct.NULL_VALUE;

    private String type = UNEXPECTED_TYPE;

    public static void buildCustom(Options _opt, KeyWords _keywords) {
        String seedElts_ = _opt.getSeedElts();
        if (seedElts_.trim().isEmpty()) {
            return;
        }
        CustList<NumberStruct> nbs_ = new CustList<NumberStruct>();
        for (String p: StringUtil.splitChars(seedElts_,',')){
            String trim_ = p.trim();
            int len_ = trim_.length();
            int n_ = StringExpUtil.nextPrintChar(0, len_, trim_);
            if (ElResolverCommon.isDigitOrDot(trim_,n_)) {
                NumberInfosOutput out_ = ElResolverCommon.processNb(_keywords, 0, trim_, StringExpUtil.nextCharIs(trim_, 0, len_, '.'));
                NumberInfos infos_ = out_.getInfos();
                infos_.setPositive(true);
                infos_.setSuffix(NumberInfos.WRAP_DOUBLE);
                Struct str_ = NumParsers.parseNb(infos_);
                if (str_ instanceof NumberStruct && ((NumberStruct)str_).doubleStruct() < 1) {
                    nbs_.add((NumberStruct) str_);
                }
            }
        }
        int size_ = nbs_.size();
        double[] arr_ = new double[size_];
        for (int i = 0; i < size_; i++) {
            arr_[i] = nbs_.get(i).doubleStruct();
        }
        CustomSeedGene s_ = new CustomSeedGene(arr_);
//        s_.setConverter(new AdvDoubleToStrConverter());
        _opt.setSeedGene(s_);
    }
    public static ParsedArgument parse(NumberInfos _infosNb, AnalyzedPageEl _context) {
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
        int suffix_ = _infosNb.getSuffix();
        Struct str_ = NumParsers.parseNb(_infosNb);
        ParsedArgument p_ = new ParsedArgument();
        p_.object = str_;
        if (str_ == NullStruct.NULL_VALUE) {
            return p_;
        }
        if (suffix_ == NumberInfos.WRAP_DOUBLE) {
            p_.type = doubleType_;
            return p_;
        }
        if (suffix_ == NumberInfos.PRIM_DOUBLE) {
            p_.type = doublePrimType_;
            return p_;
        }
        if (suffix_ == NumberInfos.WRAP_FLOAT) {
            p_.type = floatType_;
            return p_;
        }
        if (suffix_ == NumberInfos.PRIM_FLOAT) {
            p_.type = floatPrimType_;
            return p_;
        }
        if (suffix_ == NumberInfos.WRAP_LONG) {
            p_.type = longType_;
            return p_;
        }
        if (suffix_ == NumberInfos.PRIM_LONG) {
            p_.type = longPrimType_;
            return p_;
        }
        if (suffix_ == NumberInfos.WRAP_INT) {
            p_.type = intType_;
            return p_;
        }
        if (suffix_ == NumberInfos.PRIM_INT) {
            p_.type = intPrimType_;
            return p_;
        }
        if (suffix_ == NumberInfos.WRAP_CHAR) {
            p_.type = charType_;
            return p_;
        }
        if (suffix_ == NumberInfos.PRIM_CHAR) {
            p_.type = charPrimType_;
            return p_;
        }
        if (suffix_ == NumberInfos.WRAP_SHORT) {
            p_.type = shortType_;
            return p_;
        }
        if (suffix_ == NumberInfos.PRIM_SHORT) {
            p_.type = shortPrimType_;
            return p_;
        }
        if (suffix_ == NumberInfos.WRAP_BYTE) {
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
