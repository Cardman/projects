package code.expressionlanguage.analyze;

import code.expressionlanguage.Argument;
import code.expressionlanguage.analyze.stds.AnaStdCaller;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.stds.*;
import code.expressionlanguage.structs.*;
import code.util.core.IndexConstants;
import code.util.core.NumberUtil;
import code.util.core.StringUtil;

public final class AnaApplyCoreMethodUtil {
    private AnaApplyCoreMethodUtil() {
    }

    public static Struct invokeAnalyzisStdMethod(StandardMethod _std,ClassMethodId _method, Struct _struct, AnalyzedPageEl _page, Argument... _args) {
        StdCaller caller_ = _std.getCaller();
        Struct[] args_ = getObjects(_args);
        if (caller_ instanceof AnaStdCaller) {
            return ((AnaStdCaller)caller_).call(_page,_struct,args_);
        }
        String type_ = _method.getClassName();
        String mathType_ = _page.getMathRef().getAliasMath();
        if (StringUtil.quickEq(type_, mathType_)) {
            return invokeAnalyzisMathStdMethod(_method, _page, _args);
        }
        return null;
    }

    public static Struct newAnalyzisInstanceStd(StandardConstructor _constructor, AnalyzedPageEl _page, Argument... _args) {
        Struct[] args_ = getObjects(_args);
        StdCaller stdCaller_ = StandardType.caller(_constructor, null);
        AnaStdCaller anaStdCaller_;
        if (stdCaller_ instanceof AnaStdCaller) {
            anaStdCaller_ = (AnaStdCaller)stdCaller_;
        } else {
            anaStdCaller_ = null;
        }
        if (anaStdCaller_ != null) {
            return anaStdCaller_.call(_page,NullStruct.NULL_VALUE,args_);
        }
        return null;
    }

    private static Struct[] getObjects(Argument... _args) {
        int len_ = _args.length;
        Struct[] classes_ = new Struct[len_];
        for (int i = IndexConstants.FIRST_INDEX; i < len_; i++) {
            classes_[i] = _args[i].getStruct();
        }
        return classes_;
    }

    public static AnaDisplayableStruct getAnaDisplayable(Struct _value) {
        if (_value instanceof NumberStruct) {
            return (AnaDisplayableStruct) _value;
        }
        if (_value instanceof ClassMetaInfo) {
            return (AnaDisplayableStruct) _value;
        }
        if (_value instanceof BooleanStruct) {
            return (AnaDisplayableStruct) _value;
        }
        if (_value instanceof NullStruct) {
            return (AnaDisplayableStruct) _value;
        }
        if (_value instanceof RangeStruct) {
            return (AnaDisplayableStruct) _value;
        }
        return NumParsers.getString(_value);
    }

    private static Struct invokeAnalyzisMathStdMethod(ClassMethodId _method, AnalyzedPageEl _page, Argument... _args) {
        Struct[] args_ = getObjects(_args);
        String name_ = _method.getConstraints().getName();
        AliasMathType am_ = _page.getMathRef();
        String aliasPrimLong_ = _page.getAliasPrimLong();
        String aliasPrimFloat_ = _page.getAliasPrimFloat();
        String aliasPrimDouble_ = _page.getAliasPrimDouble();
        if (StringUtil.quickEq(name_, am_.getAliasAbs())) {
            if (StringUtil.quickEq(_method.getConstraints().getParametersType(0), aliasPrimDouble_)) {
                return(new DoubleStruct(Math.abs(NumParsers.convertToNumber(args_[0]).doubleStruct())));
            }
            if (StringUtil.quickEq(_method.getConstraints().getParametersType(0), aliasPrimFloat_)) {
                return(new FloatStruct(Math.abs(NumParsers.convertToNumber(args_[0]).floatStruct())));
            }
            if (StringUtil.quickEq(_method.getConstraints().getParametersType(0), aliasPrimLong_)) {
                return(new LongStruct(Math.abs(NumParsers.convertToNumber(args_[0]).longStruct())));
            }
            return(new IntStruct(Math.abs(NumParsers.convertToNumber(args_[0]).intStruct())));
        }
        if (StringUtil.quickEq(name_, am_.getAliasMax())) {
            if (StringUtil.quickEq(_method.getConstraints().getParametersType(0), aliasPrimDouble_)) {
                return(new DoubleStruct(Math.max(NumParsers.convertToNumber(args_[0]).doubleStruct(),NumParsers.convertToNumber(args_[1]).doubleStruct())));
            }
            if (StringUtil.quickEq(_method.getConstraints().getParametersType(0), aliasPrimFloat_)) {
                return(new FloatStruct(Math.max(NumParsers.convertToNumber(args_[0]).floatStruct(),NumParsers.convertToNumber(args_[1]).floatStruct())));
            }
            if (StringUtil.quickEq(_method.getConstraints().getParametersType(0), aliasPrimLong_)) {
                return(new LongStruct(Math.max(NumParsers.convertToNumber(args_[0]).longStruct(),(NumParsers.convertToNumber(args_[1]).longStruct()))));
            }
            return(new IntStruct(Math.max(NumParsers.convertToNumber(args_[0]).intStruct(),NumParsers.convertToNumber(args_[1]).intStruct())));
        }
        if (StringUtil.quickEq(name_, am_.getAliasMin())) {
            if (StringUtil.quickEq(_method.getConstraints().getParametersType(0), aliasPrimDouble_)) {
                return(new DoubleStruct(Math.min(NumParsers.convertToNumber(args_[0]).doubleStruct(),NumParsers.convertToNumber(args_[1]).doubleStruct())));
            }
            if (StringUtil.quickEq(_method.getConstraints().getParametersType(0), aliasPrimFloat_)) {
                return(new FloatStruct(Math.min(NumParsers.convertToNumber(args_[0]).floatStruct(),NumParsers.convertToNumber(args_[1]).floatStruct())));
            }
            if (StringUtil.quickEq(_method.getConstraints().getParametersType(0), aliasPrimLong_)) {
                return(new LongStruct(Math.min(NumParsers.convertToNumber(args_[0]).longStruct(),NumParsers.convertToNumber(args_[1]).longStruct())));
            }
            return(new IntStruct(Math.min(NumParsers.convertToNumber(args_[0]).intStruct(),NumParsers.convertToNumber(args_[1]).intStruct())));
        }
        if (StringUtil.quickEq(name_, am_.getAliasMod())) {
            if (StringUtil.quickEq(_method.getConstraints().getParametersType(0), aliasPrimLong_)) {
                long num_ = NumParsers.convertToNumber(args_[0]).longStruct();
                long den_ = NumParsers.convertToNumber(args_[1]).longStruct();
                if (den_ == 0) {
                    return null;
                }
                return(new LongStruct(NumberUtil.mod(num_, den_)));
            }
            int num_ = NumParsers.convertToNumber(args_[0]).intStruct();
            int den_ = NumParsers.convertToNumber(args_[1]).intStruct();
            if (den_ == 0) {
                return null;
            }
            return(new IntStruct(NumberUtil.mod(num_, den_)));
        }
        if (StringUtil.quickEq(name_, am_.getAliasQuot())) {
            if (StringUtil.quickEq(_method.getConstraints().getParametersType(0), aliasPrimLong_)) {
                long num_ = NumParsers.convertToNumber(args_[0]).longStruct();
                long den_ = NumParsers.convertToNumber(args_[1]).longStruct();
                if (den_ == 0) {
                    return null;
                }
                return(new LongStruct(NumberUtil.quot(num_, den_)));
            }
            int num_ = NumParsers.convertToNumber(args_[0]).intStruct();
            int den_ = NumParsers.convertToNumber(args_[1]).intStruct();
            if (den_ == 0) {
                return null;
            }
            return(new IntStruct(NumberUtil.quot(num_, den_)));
        }
        if (StringUtil.quickEq(name_, am_.getAliasPlus())) {
            if (_method.getConstraints().getParametersTypesLength() == 1) {
                return(args_[0]);
            }
            return(NumParsers.calculateSum(NumParsers.convertToNumber(args_[0]), NumParsers.convertToNumber(args_[1]),
                    AnaClassArgumentMatching.getPrimitiveCast(_method.getConstraints().getParametersType(0), _page.getPrimTypes())));
        }
        if (StringUtil.quickEq(name_, am_.getAliasMinus())) {
            if (_method.getConstraints().getParametersTypesLength() != 1) {
                return (NumParsers.calculateDiff(NumParsers.convertToNumber(args_[0]), NumParsers.convertToNumber(args_[1]),
                        AnaClassArgumentMatching.getPrimitiveCast(_method.getConstraints().getParametersType(0), _page.getPrimTypes())));
            }
            NumberStruct b_ = NumParsers.convertToNumber(_args[0].getStruct());
            byte cast_ = AnaClassArgumentMatching.getPrimitiveCast(_method.getConstraints().getParametersType(0), _page.getPrimTypes());
            return NumParsers.opposite(b_,cast_);
        }
        if (StringUtil.quickEq(name_, am_.getAliasMult())) {
            return(NumParsers.calculateMult(NumParsers.convertToNumber(args_[0]), NumParsers.convertToNumber(args_[1]),
                    AnaClassArgumentMatching.getPrimitiveCast(_method.getConstraints().getParametersType(0), _page.getPrimTypes())));
        }
        if (StringUtil.quickEq(name_, am_.getAliasBinMod())) {
            Struct arg_ = NumParsers.calculateMod(NumParsers.convertToNumber(args_[0]), NumParsers.convertToNumber(args_[1]),
                    AnaClassArgumentMatching.getPrimitiveCast(_method.getConstraints().getParametersType(0), _page.getPrimTypes()));
            if (arg_ == NullStruct.NULL_VALUE) {
                return null;
            }
            return(arg_);
        }
        if (StringUtil.quickEq(name_, am_.getAliasBinQuot())) {
            Struct arg_ = NumParsers.calculateDiv(NumParsers.convertToNumber(args_[0]), NumParsers.convertToNumber(args_[1]),
                    AnaClassArgumentMatching.getPrimitiveCast(_method.getConstraints().getParametersType(0), _page.getPrimTypes()));
            if (arg_ == NullStruct.NULL_VALUE) {
                return null;
            }
            return(arg_);
        }
        if (StringUtil.quickEq(name_, am_.getAliasNegBin())) {
            byte cast_ = AnaClassArgumentMatching.getPrimitiveCast(_method.getConstraints().getParametersType(0), _page.getPrimTypes());
            return NumParsers.negBinNumber(NumParsers.convertToNumber(_args[0].getStruct()),cast_);
        }
        if (StringUtil.quickEq(name_, am_.getAliasNeg())) {
            return(NumParsers.convertToBoolean(_args[0].getStruct()).neg());
        }
        if (StringUtil.quickEq(name_, am_.getAliasAnd())) {
            return(NumParsers.calculateAnd(args_[0], args_[1], AnaClassArgumentMatching.getPrimitiveCast(_method.getConstraints().getParametersType(0), _page.getPrimTypes())));
        }
        if (StringUtil.quickEq(name_, am_.getAliasOr())) {
            return(NumParsers.calculateOr(args_[0], args_[1], AnaClassArgumentMatching.getPrimitiveCast(_method.getConstraints().getParametersType(0), _page.getPrimTypes())));
        }
        if (StringUtil.quickEq(name_, am_.getAliasXor())) {
            return(NumParsers.calculateXor(args_[0], args_[1], AnaClassArgumentMatching.getPrimitiveCast(_method.getConstraints().getParametersType(0), _page.getPrimTypes())));
        }
        if (StringUtil.quickEq(name_, am_.getAliasLt())) {
            return(NumParsers.quickCalculateLowerNb(args_[0], args_[1]));
        }
        if (StringUtil.quickEq(name_, am_.getAliasGt())) {
            return(NumParsers.quickCalculateGreaterNb(args_[0], args_[1]));
        }
        if (StringUtil.quickEq(name_, am_.getAliasLe())) {
            if (NumParsers.sameValue(args_[0],args_[1])) {
                return(BooleanStruct.of(true));
            }
            return(NumParsers.quickCalculateLowerNb(args_[0], args_[1]));
        }
        if (StringUtil.quickEq(name_, am_.getAliasGe())) {
            if (NumParsers.sameValue(args_[0],args_[1])) {
                return(BooleanStruct.of(true));
            }
            return(NumParsers.quickCalculateGreaterNb(args_[0], args_[1]));
        }
        if (StringUtil.quickEq(name_, am_.getAliasShiftLeft())) {
            return(NumParsers.calculateShiftLeft(NumParsers.convertToNumber(args_[0]), NumParsers.convertToNumber(args_[1]), AnaClassArgumentMatching.getPrimitiveCast(_method.getConstraints().getParametersType(0), _page.getPrimTypes())));
        }
        if (StringUtil.quickEq(name_, am_.getAliasShiftRight())) {
            return(NumParsers.calculateShiftRight(NumParsers.convertToNumber(args_[0]), NumParsers.convertToNumber(args_[1]),AnaClassArgumentMatching.getPrimitiveCast(_method.getConstraints().getParametersType(0), _page.getPrimTypes())));
        }
        if (StringUtil.quickEq(name_, am_.getAliasBitShiftLeft())) {
            return(NumParsers.calculateBitShiftLeft(NumParsers.convertToNumber(args_[0]), NumParsers.convertToNumber(args_[1]), AnaClassArgumentMatching.getPrimitiveCast(_method.getConstraints().getParametersType(0), _page.getPrimTypes())));
        }
        if (StringUtil.quickEq(name_, am_.getAliasBitShiftRight())) {
            return(NumParsers.calculateBitShiftRight(NumParsers.convertToNumber(args_[0]), NumParsers.convertToNumber(args_[1]), AnaClassArgumentMatching.getPrimitiveCast(_method.getConstraints().getParametersType(0), _page.getPrimTypes())));
        }
        if (StringUtil.quickEq(name_, am_.getAliasRotateLeft())) {
            return(NumParsers.calculateRotateLeft(NumParsers.convertToNumber(args_[0]), NumParsers.convertToNumber(args_[1]), AnaClassArgumentMatching.getPrimitiveCast(_method.getConstraints().getParametersType(0), _page.getPrimTypes())));
        }
        if (StringUtil.quickEq(name_, am_.getAliasRotateRight())) {
            return(NumParsers.calculateRotateRight(NumParsers.convertToNumber(args_[0]), NumParsers.convertToNumber(args_[1]), AnaClassArgumentMatching.getPrimitiveCast(_method.getConstraints().getParametersType(0), _page.getPrimTypes())));
        }
        return null;
    }

    public static String getString(Argument _value, AnalyzedPageEl _page) {
        Struct struct_ = _value.getStruct();
        if (struct_ instanceof ReplacementStruct) {
            return _page.getCharSeq().getAliasReplacement();
        }
        return getAnaDisplayable(struct_).getDisplayedString(_page).getInstance();
    }

    public static Argument localSumDiff(Argument _a, Argument _b, AnalyzedPageEl _page) {
        StringBuilder str_ = new StringBuilder();
        str_.append(getString(_a, _page));
        str_.append(getString(_b, _page));
        return new Argument(new StringStruct(str_.toString()));
    }

}
