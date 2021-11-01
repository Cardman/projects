package code.expressionlanguage.analyze;

import code.expressionlanguage.Argument;
import code.expressionlanguage.analyze.stds.AnaStdCaller;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.common.*;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.stds.*;
import code.expressionlanguage.structs.*;
import code.maths.litteralcom.MathExpUtil;
import code.util.Replacement;
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
        Struct result_ = null;
        String type_ = _method.getClassName();
        String name_ = _method.getConstraints().getName();
        String mathType_ = _page.getMathRef().getAliasMath();
        String stringType_ = _page.getCharSeq().getAliasString();
        String replType_ = _page.getCharSeq().getAliasReplacement();
        if (StringUtil.quickEq(type_, _page.getCoreNames().getAliasResources())) {
            if (StringUtil.quickEq(name_, _page.getCoreNames().getAliasReadResourcesNamesLength())) {
                return ResourcesStruct.getResourceNamesLength(_page);
            }
            if (StringUtil.quickEq(name_, _page.getCoreNames().getAliasReadResourcesIndex())) {
                return ResourcesStruct.getResourceIndex(_page,args_[0]);
            }
            if (StringUtil.quickEq(name_, _page.getCoreNames().getAliasReadResources())) {
                result_ = ResourcesStruct.getResource(_page, NumParsers.getString(args_[0]));
            }
            return result_;
        }
        if (StringUtil.quickEq(type_, _page.getCoreNames().getAliasRange())) {
            RangeStruct rangeStruct_ = NumParsers.convertToRange(_struct);
            if (StringUtil.quickEq(name_, _page.getCoreNames().getAliasRangeLower())) {
                result_ = new IntStruct(rangeStruct_.getLower());
            } else if (StringUtil.quickEq(name_, _page.getCoreNames().getAliasRangeUnlimitedStep())) {
                result_ = rangeUnlimitStep(args_);
            } else if (StringUtil.quickEq(name_, _page.getCoreNames().getAliasRangeUpper())) {
                result_ = new IntStruct(rangeStruct_.getUpper());
            } else{
                result_ = BooleanStruct.of(rangeStruct_.isUnlimited());
            }
            return result_;
        }
        if (StringUtil.quickEq(type_, _page.getCoreNames().getAliasObjectsUtil())) {
            if (StringUtil.quickEq(name_, _page.getCoreNames().getAliasSameRef())) {
                result_= BooleanStruct.of(args_[0].sameReference(args_[1]));
                return result_;
            }
            if (StringUtil.quickEq(name_, _page.getCoreNames().getAliasGetParent())) {
                Struct arg_ = args_[0];
                result_ = arg_.getParent();
                return result_;
            }
        }
        if (StringUtil.quickEq(type_, replType_)) {
            return calculateReplacement(_method, _struct, _page);
        }
        if (StringUtil.quickEq(type_, stringType_)
                || StringUtil.quickEq(type_, _page.getCharSeq().getAliasCharSequence())) {
            result_ = invokeAnalyzisCharSequenceStdMethod(_method, _struct, _page, _args);
            return result_;
        }
        if (StringUtil.quickEq(type_, mathType_)) {
            return invokeAnalyzisMathStdMethod(_method, _page, _args);
        }
        return calculateNumber(_method, _struct, _page, args_);
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

    public static Struct range(Struct... _args) {
        if (_args.length == 3) {
            return rangeBoundsStep(_args[0], _args[1], _args[2]);
        }
        if (_args.length == 2) {
            return rangeBounds(_args[0], _args[1]);
        }
        return rangeUnlimit(_args[0]);
    }
    public static Struct rangeBoundsStep(Struct _min, Struct _max, Struct _step) {
        int lower_ = NumParsers.convertToNumber(_min).intStruct();
        if (lower_ < 0) {
            return null;
        }
        int upper_ = NumParsers.convertToNumber(_max).intStruct();
        if (upper_ < lower_) {
            return null;
        }
        int step_ = NumParsers.convertToNumber(_step).intStruct();
        if (step_ == 0) {
            return null;
        }
        return new RangeStruct(lower_, upper_, step_);
    }
    public static Struct rangeUnlimitStep(Struct... _args) {
        int lower_ = NumParsers.convertToNumber(_args[0]).intStruct();
        if (lower_ < 0) {
            return null;
        }
        int step_ = NumParsers.convertToNumber(_args[1]).intStruct();
        if (step_ == 0) {
            return null;
        }
        return new RangeStruct(lower_, -1, step_);
    }
    public static Struct rangeBounds(Struct _min, Struct _max) {
        int lower_ = NumParsers.convertToNumber(_min).intStruct();
        if (lower_ < 0) {
            return null;
        }
        int upper_ = NumParsers.convertToNumber(_max).intStruct();
        if (upper_ < lower_) {
            return null;
        }
        return new RangeStruct(lower_, upper_);
    }
    public static Struct rangeUnlimit(Struct _arg) {
        int lower_ = NumParsers.convertToNumber(_arg).intStruct();
        if (lower_ < 0) {
            return null;
        }
        return new RangeStruct(lower_);
    }
    private static Struct[] getObjects(Argument... _args) {
        int len_ = _args.length;
        Struct[] classes_ = new Struct[len_];
        for (int i = IndexConstants.FIRST_INDEX; i < len_; i++) {
            classes_[i] = _args[i].getStruct();
        }
        return classes_;
    }

    private static AnaDisplayableStruct getAnaDisplayable(Struct _value) {
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

    private static Struct calculateNumber(ClassMethodId _method, Struct _struct, AnalyzedPageEl _page, Struct... _args) {
        String type_ = _method.getClassName();
        String name_ = _method.getConstraints().getName();
        String booleanType_ = _page.getNbAlias().getAliasBoolean();
        String charType_ = _page.getNbAlias().getAliasCharacter();
        String nbType_ = _page.getNbAlias().getAliasNumber();
        String booleanPrimType_ = _page.getAliasPrimBoolean();
        if (StringUtil.quickEq(type_, booleanType_)) {
            if (StringUtil.quickEq(name_, _page.getNbAlias().getAliasBooleanValue())) {
                return _struct;
            }
            if (StringUtil.quickEq(name_, _page.getNbAlias().getAliasCompare())) {
                return NumParsers.cmpBool(_args[0],_args[1]);
            }
            if (StringUtil.quickEq(name_, _page.getNbAlias().getAliasCompareTo())) {
                return NumParsers.cmpBool(_struct,_args[0]);
            }
            if (StringUtil.quickEq(name_, _page.getNbAlias().getAliasEquals())) {
                return BooleanStruct.of(_struct.sameReference(_args[0]));
            }
            if (StringUtil.quickEq(name_, _page.getNbAlias().getAliasParseBoolean())) {
                StringStruct disp_ = NumParsers.getString(_args[0]);
                if (StringUtil.quickEq(disp_.getInstance(),_page.getDisplayedStrings().getTrueString())) {
                    return(BooleanStruct.of(true));
                }
                return(BooleanStruct.of(false));
            }
            if (StringUtil.quickEq(name_, _page.getNbAlias().getAliasToStringMethod())) {
                if (_method.getConstraints().getParametersTypesLength() > 0) {
                    return ((NumParsers.convertToBoolean(_args[0])).getDisplayedString(_page));
                }
                BooleanStruct instance_ = NumParsers.convertToBoolean(_struct);
                return (instance_.getDisplayedString(_page));
            }
            if (StringUtil.quickEq(_method.getConstraints().getParametersType(0), booleanPrimType_)) {
                return (_args[0]);
            }
            StringStruct disp_ = NumParsers.getString(_args[0]);
            if (StringUtil.quickEq(disp_.getInstance(), _page.getDisplayedStrings().getTrueString())) {
                return (BooleanStruct.of(true));
            }
            return (BooleanStruct.of(false));
        }
        if (StringUtil.quickEq(type_, charType_)) {
            if (_method.getConstraints().isStaticMethod()) {
                if (StringUtil.quickEq(name_, _page.getNbAlias().getAliasCompare())) {
                    char one_ = NumParsers.convertToChar(_args[0]).getChar();
                    char two_ = NumParsers.convertToChar(_args[1]).getChar();
                    return(new IntStruct(NumberUtil.compareLg(one_,two_)));
                }
                if (StringUtil.quickEq(name_, _page.getNbAlias().getAliasDigit())) {
                    char one_ = NumParsers.convertToChar(_args[0]).getChar();
                    int two_ = (NumParsers.convertToNumber(_args[1])).intStruct();
                    return(new IntStruct(StringDataUtil.digit(one_, two_)));
                }
                if (StringUtil.quickEq(name_, _page.getNbAlias().getAliasForDigit())) {
                    int one_ = (NumParsers.convertToNumber(_args[0])).intStruct();
                    int two_ = (NumParsers.convertToNumber(_args[1])).intStruct();
                    return(new CharStruct(StringDataUtil.forDigit(one_, two_)));
                }
                if (StringUtil.quickEq(name_, _page.getNbAlias().getAliasGetDirectionality())) {
                    char one_ = NumParsers.convertToChar(_args[0]).getChar();
                    return(new ByteStruct(StringDataUtil.getDirectionality(one_)));
                }
                if (StringUtil.quickEq(name_, _page.getNbAlias().getAliasGetCharType())) {
                    char one_ = NumParsers.convertToChar(_args[0]).getChar();
                    return(new IntStruct(StringDataUtil.getType(one_)));
                }
                if (StringUtil.quickEq(name_, _page.getNbAlias().getAliasIsDigit())) {
                    char one_ = NumParsers.convertToChar(_args[0]).getChar();
                    return(BooleanStruct.of(StringExpUtil.isDigit(one_)));
                }
                if (StringUtil.quickEq(name_, _page.getNbAlias().getAliasIsLetter())) {
                    char one_ = NumParsers.convertToChar(_args[0]).getChar();
                    return(BooleanStruct.of(StringDataLetterUtil.isLetter(one_)));
                }
                if (StringUtil.quickEq(name_, _page.getNbAlias().getAliasIsLetterOrDigit())) {
                    char one_ = NumParsers.convertToChar(_args[0]).getChar();
                    return(BooleanStruct.of(StringDataUtil.isLetterOrDigit(one_)));
                }
                if (StringUtil.quickEq(name_, _page.getNbAlias().getAliasIsLowerCase())) {
                    char one_ = NumParsers.convertToChar(_args[0]).getChar();
                    return(BooleanStruct.of(StringDataUtil.isLowerCase(one_)));
                }
                if (StringUtil.quickEq(name_, _page.getNbAlias().getAliasIsUpperCase())) {
                    char one_ = NumParsers.convertToChar(_args[0]).getChar();
                    return(BooleanStruct.of(StringDataUtil.isUpperCase(one_)));
                }
                if (StringUtil.quickEq(name_, _page.getNbAlias().getAliasIsSpace())) {
                    char one_ = NumParsers.convertToChar(_args[0]).getChar();
                    return(BooleanStruct.of(StringUtil.isWhitespace(one_)));
                }
                if (StringUtil.quickEq(name_, _page.getNbAlias().getAliasIsWhitespace())) {
                    char one_ = NumParsers.convertToChar(_args[0]).getChar();
                    return(BooleanStruct.of(StringUtil.isWhitespace(one_)));
                }
                if (StringUtil.quickEq(name_, _page.getNbAlias().getAliasIsWordChar())) {
                    char one_ = NumParsers.convertToChar(_args[0]).getChar();
                    return(BooleanStruct.of(MathExpUtil.isWordChar(one_)));
                }
                if (StringUtil.quickEq(name_, _page.getNbAlias().getAliasToLowerCaseChar())) {
                    char one_ = NumParsers.convertToChar(_args[0]).getChar();
                    return(new CharStruct(StringDataUtil.toLowerCase(one_)));
                }
                if (StringUtil.quickEq(name_, _page.getNbAlias().getAliasToUpperCaseChar())) {
                    char one_ = NumParsers.convertToChar(_args[0]).getChar();
                    return(new CharStruct(StringDataUtil.toUpperCase(one_)));
                }
                char one_ = NumParsers.convertToChar(_args[0]).getChar();
                return(new StringStruct(Character.toString(one_)));
            }
            if (StringUtil.quickEq(name_, _page.getNbAlias().getAliasCharValue())) {
                return _struct;
            }
            if (!(_args[0] instanceof CharStruct)) {
                return null;
            }
            CharStruct ch_ = NumParsers.convertToChar(_struct);
            char one_ = ch_.getChar();
            char two_ = ((CharStruct) _args[0]).getChar();
            return(new IntStruct(NumberUtil.compareLg(one_,two_)));
        }
        if (StringUtil.quickEq(type_, nbType_)) {
            if (StringUtil.quickEq(name_, _page.getNbAlias().getAliasCompare())) {
                if (!(_args[0] instanceof  NumberStruct)) {
                    return null;
                }
                if (!(_args[1] instanceof  NumberStruct)) {
                    return null;
                }
                return(new IntStruct(NumParsers.compareGene(NumParsers.convertToNumber(_args[0]), NumParsers.convertToNumber(_args[1]))));
            }
            if (StringUtil.quickEq(name_, _page.getNbAlias().getAliasCompareTo())) {
                NumberStruct instance_ = NumParsers.convertToNumber(_struct);
                if (!(_args[0] instanceof  NumberStruct)) {
                    return null;
                }
                return(new IntStruct(NumParsers.compareGene(instance_, NumParsers.convertToNumber(_args[0]))));
            }
            if (StringUtil.quickEq(name_, _page.getNbAlias().getAliasEquals())) {
                if (_method.getConstraints().getParametersTypesLength() > 1) {
                    return(BooleanStruct.of(NumParsers.sameValue(_args[0],_args[1])));
                }
                return(BooleanStruct.of(NumParsers.sameValue(_struct,_args[0])));
            }
            if (StringUtil.quickEq(name_, _page.getNbAlias().getAliasByteValue())) {
                NumberStruct instance_ = NumParsers.convertToNumber(_struct);
                return(new ByteStruct(instance_.byteStruct()));
            }
            if (StringUtil.quickEq(name_, _page.getNbAlias().getAliasShortValue())) {
                NumberStruct instance_ = NumParsers.convertToNumber(_struct);
                return(new ShortStruct(instance_.shortStruct()));
            }
            if (StringUtil.quickEq(name_, _page.getNbAlias().getAliasIntValue())) {
                NumberStruct instance_ = NumParsers.convertToNumber(_struct);
                return(new IntStruct(instance_.intStruct()));
            }
            if (StringUtil.quickEq(name_, _page.getNbAlias().getAliasLongValue())) {
                NumberStruct instance_ = NumParsers.convertToNumber(_struct);
                return(new LongStruct(instance_.longStruct()));
            }
            if (StringUtil.quickEq(name_, _page.getNbAlias().getAliasFloatValue())) {
                NumberStruct instance_ = NumParsers.convertToNumber(_struct);
                return(new FloatStruct(instance_.floatStruct()));
            }
            if (StringUtil.quickEq(name_, _page.getNbAlias().getAliasDoubleValue())) {
                NumberStruct instance_ = NumParsers.convertToNumber(_struct);
                return(new DoubleStruct(instance_.doubleStruct()));
            }
            if (_method.getConstraints().getParametersTypesLength() == 0) {
                NumberStruct instance_ = NumParsers.convertToNumber(_struct);
                return(instance_.getDisplayedString(_page));
            }
            AnaDisplayableStruct instance_ = getAnaDisplayable(_args[0]);
            return(instance_
                    .getDisplayedString(_page));
        }
        return null;
    }

    private static Struct invokeAnalyzisCharSequenceStdMethod(ClassMethodId _method, Struct _struct, AnalyzedPageEl _page, Argument... _args) {
        Struct[] args_ = getObjects(_args);
        String type_ = _method.getClassName();
        String stringType_ = _page.getCharSeq().getAliasString();
        if (StringUtil.quickEq(type_, stringType_)) {
            return calculateString(_method, _struct, _page, args_);
        }
        return calculateCharSeq(_method, _struct, _page, args_);
    }

    private static Struct calculateString(ClassMethodId _method, Struct _struct, AnalyzedPageEl _page, Struct... _args) {
        if (!_method.getConstraints().isStaticMethod()) {
            StringStruct str_ = NumParsers.getString(_struct);
            return calculateLocString(str_, _method, _page, _args);
        }
        String name_ = _method.getConstraints().getName();
        if (StringUtil.quickEq(name_, _page.getCharSeq().getAliasStringCompare())) {
            Struct arg_ = _args[0];
            if (!(arg_ instanceof StringStruct)) {
                return null;
            }
            StringStruct first_ = (StringStruct) arg_;
            return compareToString(first_,_args[1]);
        }
        int nbParams_ = _method.getConstraints().getParametersTypesLength();
        Struct arg_ = NumParsers.getArg(nbParams_, _args);
        if (NumParsers.isDisplay(nbParams_, arg_)) {
            return getAnaDisplayable(arg_).getDisplayedString(_page);
        }
        if (!(arg_ instanceof ArrayStruct)) {
            return null;
        }
        return tryGetCharArray(nbParams_, (ArrayStruct) arg_, _args);
    }

    private static Struct tryGetCharArray(int _list, ArrayStruct _arg, Struct[] _args) {
        int len_ = _arg.getLength();
        char[] arr_ = new char[len_];
        for (int i = 0; i < len_; i++) {
            arr_[i] = NumParsers.convertToChar(_arg.get(i)).getChar();
        }
        if (_list == 1) {
            return new StringStruct(String.valueOf(arr_));
        }
        int one_ = (NumParsers.convertToNumber(_args[0])).intStruct();
        int two_ = (NumParsers.convertToNumber(_args[1])).intStruct();
        if (NumParsers.koArray(arr_, one_, two_)) {
            return null;
        }
        return new StringStruct(String.valueOf(arr_,one_,two_));
    }

    private static Struct calculateLocString(StringStruct _str, ClassMethodId _method, AnalyzedPageEl _page, Struct... _args) {
        String name_ = _method.getConstraints().getName();
        String stringType_ = _page.getCharSeq().getAliasString();
        if (StringUtil.quickEq(name_, _page.getCharSeq().getAliasRegionMatches())) {
            return regionMatches(_str, NumParsers.convertToBoolean(_args[0]), NumParsers.convertToNumber(_args[1]), _args[2],
                    NumParsers.convertToNumber(_args[3]), NumParsers.convertToNumber(_args[4]));
        }
        if (StringUtil.quickEq(name_, _page.getCharSeq().getAliasReplaceString())) {
            if (StringUtil.quickEq(_method.getConstraints().getParametersType(0), stringType_)) {
                return replaceString(_str,_args[0], _args[1]);
            }
            return replace(_str, NumParsers.convertToChar(_args[0]), NumParsers.convertToChar(_args[1]));
        }
        if (StringUtil.quickEq(name_, _page.getCharSeq().getAliasReplaceMultiple())) {
            return replaceMultiple(_str,_args[0]);
        }
        String one_ = _str.getInstance();
        if (StringUtil.quickEq(name_, _page.getCharSeq().getAliasCompareToIgnoreCase())) {
            Struct two_ = _args[0];
            if (!(two_ instanceof StringStruct)) {
                return null;
            }
            StringStruct t_ = (StringStruct) two_;
            return new IntStruct(NumParsers.compareToIgnoreCase(one_,t_.getInstance()));
        }
        if (StringUtil.quickEq(name_, _page.getCharSeq().getAliasEqualsIgnoreCase())) {
            Struct two_ = _args[0];
            if (!(two_ instanceof StringStruct)) {
                return BooleanStruct.of(false);
            }
            StringStruct t_ = (StringStruct) two_;
            return BooleanStruct.of(NumParsers.equalsIgnoreCase(one_,t_.getInstance()));
        }
        if (StringUtil.quickEq(name_, _page.getCharSeq().getAliasToLowerCase())) {
            return new StringStruct(StringDataUtil.toLowerCase(one_));
        }
        return new StringStruct(StringDataUtil.toUpperCase(one_));
    }

    private static Struct compareToString(StringStruct _str, Struct _anotherString) {
        if (!(_anotherString instanceof StringStruct)) {
            return null;
        }
        StringStruct st_ = (StringStruct)_anotherString;
        return new IntStruct(StringUtil.compareStrings(_str.getInstance(),st_.getInstance()));
    }

    private static Struct regionMatches(StringStruct _str, BooleanStruct _case, NumberStruct _toffset, Struct _other, NumberStruct _ooffset,
                                        NumberStruct _len) {
        if (!(_other instanceof StringStruct)) {
            return null;
        }
        boolean case_ = BooleanStruct.isTrue(_case);
        StringStruct other_ = (StringStruct) _other;
        int comLen_ = _len.intStruct();
        int to_ = _toffset.intStruct();
        int po_ = _ooffset.intStruct();
        return BooleanStruct.of(NumParsers.regionMatches(_str.getInstance(),case_,to_, other_.getInstance(), po_, comLen_));
    }

    private static Struct replace(StringStruct _str, CharStruct _oldChar, CharStruct _newChar) {
        char oldChar_ = _oldChar.getChar();
        char newChar_ = _newChar.getChar();
        return new StringStruct(_str.getInstance().replace(oldChar_, newChar_));
    }

    private static Struct replaceString(StringStruct _str, Struct _oldChar, Struct _newChar) {
        String old_;
        old_ = NumParsers.getStringValue(_oldChar);
        String new_;
        new_ = NumParsers.getStringValue(_newChar);
        String out_ = StringUtil.replace(_str.getInstance(), old_, new_);
        return new StringStruct(out_);
    }

    private static Struct replaceMultiple(StringStruct _st, Struct _seps) {
        if (!(_seps instanceof ArrayStruct)) {
            return null;
        }
        ArrayStruct arrSep_ = (ArrayStruct) _seps;
        int lenSeps_ = arrSep_.getLength();
        Replacement[] seps_ = new Replacement[lenSeps_];
        for (int i = 0; i < lenSeps_; i++) {
            Struct curSep_ = arrSep_.get(i);
            if (!(curSep_ instanceof ReplacementStruct)) {
                return null;
            }
            seps_[i] = NumParsers.getReplacement(curSep_).getInstance();
            if (seps_[i].getNewString() == null) {
                return null;
            }
            if (seps_[i].getOldString() == null) {
                return null;
            }
        }
        return new StringStruct(StringUtil.replaceMult(_st.getInstance(), seps_));
    }

    private static Struct calculateCharSeq(ClassMethodId _method, Struct _struct, AnalyzedPageEl _page, Struct... _args) {
        if (!_method.getConstraints().isStaticMethod()) {
            return calculateLocCharSeq(NumParsers.getCharSeq(_struct), _method, _page, _args);
        }
        if (!(_args[0] instanceof CharSequenceStruct)) {
            return BooleanStruct.of(_args[1] == NullStruct.NULL_VALUE);
        }
        return BooleanStruct.of(NumParsers.sameEq(NumParsers.getCharSeq(_args[0]),_args[1]));
    }

    private static Struct calculateLocCharSeq(CharSequenceStruct _charSequence, ClassMethodId _method, AnalyzedPageEl _page, Struct... _args) {
        String name_ = _method.getConstraints().getName();
        if (StringUtil.quickEq(name_, _page.getCharSeq().getAliasLength())) {
            return new IntStruct(_charSequence.length());
        }
        if (StringUtil.quickEq(name_, _page.getCharSeq().getAliasIsEmpty())) {
            return NumParsers.isEmpty(_charSequence);
        }
        if (StringUtil.quickEq(name_, _page.getCharSeq().getAliasCharAt())) {
            return charAt(_charSequence, _args[0]);
        }
        if (StringUtil.quickEq(name_, _page.getCharSeq().getAliasCharSequenceCompareTo())) {
            return compareTo(_charSequence, _args[0]);
        }
        if (StringUtil.quickEq(name_, _page.getCharSeq().getAliasRegionMatches())) {
            return regionMatches(_charSequence, NumParsers.convertToNumber(_args[0]), _args[1], NumParsers.convertToNumber(_args[2]), NumParsers.convertToNumber(_args[3]));
        }
        if (StringUtil.quickEq(name_, _page.getCharSeq().getAliasStartsWith())) {
            if (_method.getConstraints().getParametersTypesLength() == 1) {
                return startsWith(_charSequence, _args[0]);
            }
            return startsWith(_charSequence, _args[0], NumParsers.convertToNumber(_args[1]));
        }
        if (StringUtil.quickEq(name_, _page.getCharSeq().getAliasEndsWith())) {
            return endsWith(_charSequence, _args[0]);
        }
        if (StringUtil.quickEq(name_, _page.getCharSeq().getAliasIndexOf())) {
            if (_method.getConstraints().getParametersTypesLength() == 1) {
                if (!(_args[0] instanceof NumberStruct)) {
                    return indexOfString(_charSequence, _args[0]);
                }
                return indexOf(_charSequence, _args[0]);
            }
            if (!(_args[0] instanceof NumberStruct)) {
                return indexOfString(_charSequence, _args[0], NumParsers.convertToNumber(_args[1]));
            }
            return indexOf(_charSequence, _args[0], _args[1]);
        }
        if (StringUtil.quickEq(name_, _page.getCharSeq().getAliasContains())) {
            return contains(_charSequence, _args[0]);
        }
        if (StringUtil.quickEq(name_, _page.getCharSeq().getAliasLastIndexOf())) {
            if (_method.getConstraints().getParametersTypesLength() == 1) {
                if (!(_args[0] instanceof NumberStruct)) {
                    return lastIndexOfString(_charSequence, _args[0]);
                }
                return lastIndexOf(_charSequence, _args[0]);
            }
            if (!(_args[0] instanceof NumberStruct)) {
                return lastIndexOfString(_charSequence, _args[0], NumParsers.convertToNumber(_args[1]));
            }
            return lastIndexOf(_charSequence, _args[0], _args[1]);
        }
        if (StringUtil.quickEq(name_, _page.getCharSeq().getAliasSubstring()) || StringUtil.quickEq(name_, _page.getCharSeq().getAliasSubSequence())) {
            if (_method.getConstraints().getParametersTypesLength() == 1) {
                return substring(_charSequence, NumParsers.convertToNumber(_args[0]));
            }
            return substring(_charSequence, NumParsers.convertToNumber(_args[0]), NumParsers.convertToNumber(_args[1]));
        }
        if (StringUtil.quickEq(name_, _page.getCharSeq().getAliasTrim())) {
            return trim(_charSequence);
        }
        if (StringUtil.quickEq(name_, _page.getCharSeq().getAliasFormat())) {
            return format(_charSequence, _args[0]);
        }
        if (StringUtil.quickEq(name_, _page.getCharSeq().getAliasCharSequenceToString())) {
            return _charSequence;
        }
        return null;
    }

    private static Struct charAt(CharSequenceStruct _charSequence, Struct _index) {
        NumberStruct nb_ = NumParsers.convertToNumber(_index);
        int ind_ = nb_.intStruct();
        if (NumParsers.isInvalidIndex(ind_, _charSequence)) {
            return null;
        }
        return new CharStruct(_charSequence.charAt(ind_));
    }

    private static Struct compareTo(CharSequenceStruct _charSequence, Struct _anotherString) {
        if (!(_anotherString instanceof CharSequenceStruct)) {
            return null;
        }
        CharSequenceStruct st_ = NumParsers.getCharSeq(_anotherString);
        return new IntStruct(StringUtil.compareStrings(_charSequence.toStringInstance(),st_.toStringInstance()));
    }

    private static Struct regionMatches(CharSequenceStruct _charSequence, NumberStruct _toffset, Struct _other, NumberStruct _ooffset,
                                        NumberStruct _len) {
        if (!(_other instanceof CharSequenceStruct)) {
            return null;
        }
        CharSequenceStruct other_ = NumParsers.getCharSeq(_other);
        int comLen_ = _len.intStruct();
        int to_ = _toffset.intStruct();
        int po_ = _ooffset.intStruct();
        return BooleanStruct.of(NumParsers.regionMatches(_charSequence.toStringInstance(),to_, other_.toStringInstance(), po_, comLen_));
    }

    private static Struct startsWith(CharSequenceStruct _charSequence, Struct _prefix) {
        return startsWith(_charSequence, _prefix,new IntStruct(0));
    }

    private static Struct endsWith(CharSequenceStruct _charSequence, Struct _suffix) {
        if (!(_suffix instanceof CharSequenceStruct)) {
            return null;
        }
        CharSequenceStruct suffix_ = NumParsers.getCharSeq(_suffix);
        return BooleanStruct.of(_charSequence.toStringInstance().endsWith(suffix_.toStringInstance()));
    }

    private static Struct startsWith(CharSequenceStruct _charSequence, Struct _prefix, NumberStruct _toffset) {
        if (!(_prefix instanceof CharSequenceStruct)) {
            return null;
        }
        CharSequenceStruct pref_ = NumParsers.getCharSeq(_prefix);
        int to_ = _toffset.intStruct();
        return BooleanStruct.of(_charSequence.toStringInstance().startsWith(pref_.toStringInstance(), to_));
    }

    private static Struct indexOf(CharSequenceStruct _charSequence, Struct _ch) {
        return indexOf(_charSequence, _ch,new IntStruct(0));
    }

    private static Struct indexOf(CharSequenceStruct _charSequence, Struct _ch, Struct _fromIndex) {
        NumberStruct ch_ = NumParsers.convertToNumber(_ch);
        int int_ = ch_.intStruct();
        NumberStruct index_ = NumParsers.convertToNumber(_fromIndex);
        int from_ = index_.intStruct();
        return new IntStruct(_charSequence.toStringInstance().indexOf(int_, from_));
    }

    private static Struct contains(CharSequenceStruct _charSequence, Struct _str) {
        if (!(_str instanceof CharSequenceStruct)) {
            return null;
        }
        CharSequenceStruct arg_ = NumParsers.getCharSeq(_str);
        return BooleanStruct.of(_charSequence.toStringInstance().contains(arg_.toStringInstance()));
    }

    private static Struct indexOfString(CharSequenceStruct _charSequence, Struct _str) {
        if (!(_str instanceof CharSequenceStruct)) {
            return null;
        }
        CharSequenceStruct str_ = NumParsers.getCharSeq(_str);
        return new IntStruct(_charSequence.toStringInstance().indexOf(str_.toStringInstance()));
    }

    private static Struct indexOfString(CharSequenceStruct _charSequence, Struct _str, NumberStruct _fromIndex) {
        int from_ = _fromIndex.intStruct();
        if (!(_str instanceof CharSequenceStruct)) {
            return null;
        }
        CharSequenceStruct str_ = NumParsers.getCharSeq(_str);
        return new IntStruct(_charSequence.toStringInstance().indexOf(str_.toStringInstance(), from_));
    }

    private static Struct lastIndexOf(CharSequenceStruct _charSequence, Struct _ch) {
        return lastIndexOf(_charSequence, _ch, new IntStruct(_charSequence.length()));
    }

    private static Struct lastIndexOf(CharSequenceStruct _charSequence, Struct _ch, Struct _fromIndex) {
        NumberStruct ch_ = NumParsers.convertToNumber(_ch);
        int int_ = ch_.intStruct();
        NumberStruct index_ = NumParsers.convertToNumber(_fromIndex);
        int from_ = index_.intStruct();
        return new IntStruct(_charSequence.toStringInstance().lastIndexOf(int_, from_));
    }

    private static Struct lastIndexOfString(CharSequenceStruct _charSequence, Struct _str) {
        return lastIndexOfString(_charSequence, _str,new IntStruct(_charSequence.length()));
    }

    private static Struct lastIndexOfString(CharSequenceStruct _charSequence, Struct _str, NumberStruct _fromIndex) {
        if (!(_str instanceof CharSequenceStruct)) {
            return null;
        }
        CharSequenceStruct str_ = NumParsers.getCharSeq(_str);
        int from_ = _fromIndex.intStruct();
        return new IntStruct(_charSequence.toStringInstance().lastIndexOf(str_.toStringInstance(), from_));
    }

    private static Struct substring(CharSequenceStruct _charSequence, NumberStruct _beginIndex) {
        return substring(_charSequence, _beginIndex, new IntStruct(_charSequence.length()));
    }

    private static Struct substring(CharSequenceStruct _charSequence, NumberStruct _beginIndex, NumberStruct _endIndex) {
        int begin_ = _beginIndex.intStruct();
        int end_ = _endIndex.intStruct();
        if (NumParsers.isIncorrectSub(begin_, end_, _charSequence)) {
            return null;
        }
        return new StringStruct(_charSequence.substring(begin_, end_));
    }

    private static Struct trim(CharSequenceStruct _charSequence) {
        return new StringStruct(_charSequence.toStringInstance().trim());
    }

    private static Struct format(CharSequenceStruct _charSequence, Struct _seps) {
        if (!(_seps instanceof ArrayStruct)) {
            return null;
        }
        ArrayStruct arrSep_ = (ArrayStruct) _seps;
        int lenSeps_ = arrSep_.getLength();
        String[] seps_ = new String[lenSeps_];
        for (int i = 0; i < lenSeps_; i++) {
            Struct curSep_ = arrSep_.get(i);
            if (!(curSep_ instanceof CharSequenceStruct)) {
                return null;
            }
            seps_[i] = NumParsers.getCharSeq(curSep_).toStringInstance();
        }
        return new StringStruct(StringUtil.simpleStringsFormat(_charSequence.toStringInstance(), seps_));
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

    private static Struct calculateReplacement(ClassMethodId _method, Struct _struct, AnalyzedPageEl _page) {
        String name_ = _method.getConstraints().getName();
        ReplacementStruct rp_ = NumParsers.getReplacement(_struct);
        if (StringUtil.quickEq(name_, _page.getCharSeq().getAliasGetNewString())) {
            String old_ = rp_.getInstance().getNewString();
            return Argument.wrapStr(old_);
        }
        String new_ = rp_.getInstance().getOldString();
        return Argument.wrapStr(new_);

    }

}
