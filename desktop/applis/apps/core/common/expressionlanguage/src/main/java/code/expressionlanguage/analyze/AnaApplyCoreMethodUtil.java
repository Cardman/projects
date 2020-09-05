package code.expressionlanguage.analyze;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.DoubleInfo;
import code.expressionlanguage.common.LongInfo;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.opers.ExecCatOperation;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.functionid.ConstructorId;
import code.expressionlanguage.inherits.ClassArgumentMatching;
import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.expressionlanguage.stds.*;
import code.expressionlanguage.structs.*;
import code.util.CustList;
import code.util.Numbers;
import code.util.Replacement;
import code.util.StringList;

public final class AnaApplyCoreMethodUtil {
    private AnaApplyCoreMethodUtil() {
    }

    public static Struct invokeAnalyzisStdMethod(ContextEl _cont, ClassMethodId _method, Struct _struct, Argument... _args) {
        Struct result_ = null;
        Struct[] args_ = getObjects(_args);
        String type_ = _method.getClassName();
        String name_ = _method.getConstraints().getName();
        LgNames lgNames_ = _cont.getStandards();
        String mathType_ = lgNames_.getAliasMath();
        String stringType_ = lgNames_.getAliasString();
        String replType_ = lgNames_.getAliasReplacement();
        if (StringList.quickEq(type_, lgNames_.getAliasResources())) {
            if (StringList.quickEq(name_, lgNames_.getAliasReadResourcesNamesLength())) {
                return ResourcesStruct.getResourceNamesLength(_cont);
            }
            if (StringList.quickEq(name_, lgNames_.getAliasReadResourcesIndex())) {
                return ResourcesStruct.getResourceIndex(_cont,args_[0]);
            }
            if (StringList.quickEq(name_, lgNames_.getAliasReadResources())) {
                result_ = ResourcesStruct.getResource(_cont, getString(args_[0]));
            }
            return result_;
        }
        if (StringList.quickEq(type_, lgNames_.getAliasObjectsUtil())) {
            if (StringList.quickEq(name_, lgNames_.getAliasSameRef())) {
                result_= BooleanStruct.of(args_[0].sameReference(args_[1]));
                return result_;
            }
            if (StringList.quickEq(name_, lgNames_.getAliasGetParent())) {
                Struct arg_ = args_[0];
                result_ = arg_.getParent();
                return result_;
            }
        }
        if (StringList.quickEq(type_, replType_)) {
            return ReplacementStruct.calculate(_cont, _method, _struct);
        }
        if (StringList.quickEq(type_, stringType_)
                || StringList.quickEq(type_, lgNames_.getAliasCharSequence())) {
            result_ = invokeAnalyzisCharSequenceStdMethod(_cont, _method, _struct, _args);
            return result_;
        }
        if (StringList.quickEq(type_, mathType_)) {
            return invokeAnalyzisMathStdMethod(_cont, _method, _args);
        }
        return calculateNumber(_cont, _method, _struct, args_);
    }

    public static Struct newAnalyzisInstanceStd(ContextEl _cont, ConstructorId _method, Argument... _args) {
        Struct[] args_ = getObjects(_args);
        String type_ = _method.getName();
        LgNames lgNames_ = _cont.getStandards();
        String booleanType_ = lgNames_.getAliasBoolean();
        String charType_ = lgNames_.getAliasCharacter();
        String stringType_ = lgNames_.getAliasString();
        String byteType_ = lgNames_.getAliasByte();
        String shortType_ = lgNames_.getAliasShort();
        String intType_ = lgNames_.getAliasInteger();
        String longType_ = lgNames_.getAliasLong();
        String floatType_ = lgNames_.getAliasFloat();
        String doubleType_ = lgNames_.getAliasDouble();
        String replType_ = lgNames_.getAliasReplacement();
        if (StringList.quickEq(type_, replType_)) {
            return ReplacementStruct.instantiate(args_);
        }
        if (StringList.quickEq(type_, stringType_)) {
            StringList list_ = _method.getParametersTypes();
            if (list_.size() == 0) {
                return new StringStruct("");
            }
            return null;
        }
        if (StringList.quickEq(type_, booleanType_)
                || StringList.quickEq(type_, charType_)
                || StringList.quickEq(type_, byteType_)
                || StringList.quickEq(type_, shortType_)
                || StringList.quickEq(type_, intType_)
                || StringList.quickEq(type_, longType_)
                || StringList.quickEq(type_, floatType_)
                || StringList.quickEq(type_, doubleType_)) {
            return instantiateNumber(_cont, _method, args_);
        }
        return null;
    }

    public static Struct[] getObjects(Argument... _args) {
        int len_ = _args.length;
        Struct[] classes_ = new Struct[len_];
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            classes_[i] = _args[i].getStruct();
        }
        return classes_;
    }

    public static StringStruct getString(Struct _previous) {
        if (_previous instanceof StringStruct) {
            return (StringStruct) _previous;
        }
        return new StringStruct("");
    }

    private static Struct invokeAnalyzisMathStdMethod(ContextEl _cont, ClassMethodId _method, Argument... _args) {
        Struct[] args_ = AliasMath.getObjects(_args);
        String name_ = _method.getConstraints().getName();
        StringList paramList_ = _method.getConstraints().getParametersTypes();
        LgNames lgNames_ = _cont.getStandards();
        AliasMath am_ = lgNames_.getMathRef();
        String aliasPrimInteger_ = lgNames_.getAliasPrimInteger();
        String aliasPrimLong_ = lgNames_.getAliasPrimLong();
        if (StringList.quickEq(name_, lgNames_.getAliasAbs())) {
            if (StringList.quickEq(paramList_.first(), aliasPrimLong_)) {
                return(new LongStruct(Math.abs(ClassArgumentMatching.convertToNumber(args_[0]).longStruct())));
            }
            return(new IntStruct(Math.abs(ClassArgumentMatching.convertToNumber(args_[0]).intStruct())));
        }
        if (StringList.quickEq(name_, lgNames_.getAliasMod())) {
            if (StringList.quickEq(paramList_.first(), aliasPrimLong_)) {
                long num_ = ClassArgumentMatching.convertToNumber(args_[0]).longStruct();
                long den_ = ClassArgumentMatching.convertToNumber(args_[1]).longStruct();
                if (den_ == 0) {
                    return null;
                }
                return(new LongStruct(Numbers.mod(num_, den_)));
            }
            int num_ = ClassArgumentMatching.convertToNumber(args_[0]).intStruct();
            int den_ = ClassArgumentMatching.convertToNumber(args_[1]).intStruct();
            if (den_ == 0) {
                return null;
            }
            return(new IntStruct(Numbers.mod(num_, den_)));
        }
        if (StringList.quickEq(name_, lgNames_.getAliasQuot())) {
            if (StringList.quickEq(paramList_.first(), aliasPrimLong_)) {
                long num_ = ClassArgumentMatching.convertToNumber(args_[0]).longStruct();
                long den_ = ClassArgumentMatching.convertToNumber(args_[1]).longStruct();
                if (den_ == 0) {
                    return null;
                }
                return(new LongStruct(Numbers.quot(num_, den_)));
            }
            int num_ = ClassArgumentMatching.convertToNumber(args_[0]).intStruct();
            int den_ = ClassArgumentMatching.convertToNumber(args_[1]).intStruct();
            if (den_ == 0) {
                return null;
            }
            return(new IntStruct(Numbers.quot(num_, den_)));
        }
        if (StringList.quickEq(name_, am_.getAliasPlus())) {
            if (paramList_.size() == 1) {
                return(args_[0]);
            }
            ClassArgumentMatching clArg_ = new ClassArgumentMatching(paramList_.first());
            return(AliasNumber.calculateSum(ClassArgumentMatching.convertToNumber(args_[0]),ClassArgumentMatching.convertToNumber(args_[1]), _cont, clArg_));
        }
        if (StringList.quickEq(name_, am_.getAliasMinus())) {
            if (paramList_.size() != 1) {
                ClassArgumentMatching clArg_ = new ClassArgumentMatching(paramList_.first());
                return (AliasNumber.calculateDiff(ClassArgumentMatching.convertToNumber(args_[0]), ClassArgumentMatching.convertToNumber(args_[1]), _cont, clArg_));
            }
            NumberStruct b_ = ClassArgumentMatching.convertToNumber(_args[0].getStruct());
            ClassArgumentMatching clArg_ = new ClassArgumentMatching(paramList_.first());
            if (PrimitiveTypeUtil.isInt(clArg_,_cont)) {
                return (new IntStruct(-b_.intStruct()));
            }
            if (PrimitiveTypeUtil.isLong(clArg_,_cont)) {
                return (new LongStruct(-b_.longStruct()));
            }
            if (PrimitiveTypeUtil.isFloat(clArg_,_cont)) {
                return (new FloatStruct(-b_.floatStruct()));
            }
            return (new DoubleStruct(-b_.doubleStruct()));
        }
        if (StringList.quickEq(name_, am_.getAliasMult())) {
            ClassArgumentMatching clArg_ = new ClassArgumentMatching(paramList_.first());
            return(AliasNumber.calculateMult(ClassArgumentMatching.convertToNumber(args_[0]), ClassArgumentMatching.convertToNumber(args_[1]), _cont, clArg_));
        }
        if (StringList.quickEq(name_, am_.getAliasBinMod())) {
            ClassArgumentMatching clArg_ = new ClassArgumentMatching(paramList_.first());
            Struct arg_ = AliasNumber.calculateMod(ClassArgumentMatching.convertToNumber(args_[0]), ClassArgumentMatching.convertToNumber(args_[1]), _cont, clArg_);
            if (arg_ == NullStruct.NULL_VALUE) {
                return null;
            }
            return(arg_);
        }
        if (StringList.quickEq(name_, am_.getAliasBinQuot())) {
            ClassArgumentMatching clArg_ = new ClassArgumentMatching(paramList_.first());
            Struct arg_ = AliasNumber.calculateDiv(ClassArgumentMatching.convertToNumber(args_[0]), ClassArgumentMatching.convertToNumber(args_[1]), _cont, clArg_);
            if (arg_ == NullStruct.NULL_VALUE) {
                return null;
            }
            return(arg_);
        }
        if (StringList.quickEq(name_, am_.getAliasNegBin())) {
            ClassArgumentMatching res_ = new ClassArgumentMatching(paramList_.first());
            if (res_.matchClass(aliasPrimInteger_)) {
                int left_ = ClassArgumentMatching.convertToNumber(_args[0].getStruct()).intStruct();
                boolean[] bits_ = NumParsers.toBits(left_);
                AliasMath.negateBits(bits_);
                return(new IntStruct(NumParsers.toInt(bits_)));
            }
            long left_ = ClassArgumentMatching.convertToNumber(_args[0].getStruct()).longStruct();
            boolean[] bits_ = NumParsers.toBits(left_);
            AliasMath.negateBits(bits_);
            return(new LongStruct(NumParsers.toLong(bits_)));
        }
        if (StringList.quickEq(name_, am_.getAliasNeg())) {
            return(ClassArgumentMatching.convertToBoolean(_args[0].getStruct()).neg());
        }
        if (StringList.quickEq(name_, am_.getAliasAnd())) {
            ClassArgumentMatching clArg_ = new ClassArgumentMatching(paramList_.first());
            return(AliasNumber.calculateAnd(args_[0], args_[1], _cont, clArg_));
        }
        if (StringList.quickEq(name_, am_.getAliasOr())) {
            ClassArgumentMatching clArg_ = new ClassArgumentMatching(paramList_.first());
            return(AliasNumber.calculateOr(args_[0], args_[1], _cont, clArg_));
        }
        if (StringList.quickEq(name_, am_.getAliasXor())) {
            ClassArgumentMatching clArg_ = new ClassArgumentMatching(paramList_.first());
            return(AliasNumber.calculateXor(args_[0], args_[1], _cont, clArg_));
        }
        if (StringList.quickEq(name_, am_.getAliasLt())) {
            return(AliasNumber.quickCalculateLowerNb(args_[0], args_[1]));
        }
        if (StringList.quickEq(name_, am_.getAliasGt())) {
            return(AliasNumber.quickCalculateGreaterNb(args_[0], args_[1]));
        }
        if (StringList.quickEq(name_, am_.getAliasLe())) {
            if (NumParsers.sameValue(args_[0],args_[1])) {
                return(BooleanStruct.of(true));
            }
            return(AliasNumber.quickCalculateLowerNb(args_[0], args_[1]));
        }
        if (StringList.quickEq(name_, am_.getAliasGe())) {
            if (NumParsers.sameValue(args_[0],args_[1])) {
                return(BooleanStruct.of(true));
            }
            return(AliasNumber.quickCalculateGreaterNb(args_[0], args_[1]));
        }
        if (StringList.quickEq(name_, am_.getAliasShiftLeft())) {
            ClassArgumentMatching clArg_ = new ClassArgumentMatching(paramList_.first());
            return(AliasNumber.calculateShiftLeft(ClassArgumentMatching.convertToNumber(args_[0]), ClassArgumentMatching.convertToNumber(args_[1]), _cont, clArg_));
        }
        if (StringList.quickEq(name_, am_.getAliasShiftRight())) {
            ClassArgumentMatching clArg_ = new ClassArgumentMatching(paramList_.first());
            return(AliasNumber.calculateShiftRight(ClassArgumentMatching.convertToNumber(args_[0]), ClassArgumentMatching.convertToNumber(args_[1]), _cont, clArg_));
        }
        if (StringList.quickEq(name_, am_.getAliasBitShiftLeft())) {
            ClassArgumentMatching clArg_ = new ClassArgumentMatching(paramList_.first());
            return(AliasNumber.calculateBitShiftLeft(ClassArgumentMatching.convertToNumber(args_[0]), ClassArgumentMatching.convertToNumber(args_[1]), _cont, clArg_));
        }
        if (StringList.quickEq(name_, am_.getAliasBitShiftRight())) {
            ClassArgumentMatching clArg_ = new ClassArgumentMatching(paramList_.first());
            return(AliasNumber.calculateBitShiftRight(ClassArgumentMatching.convertToNumber(args_[0]), ClassArgumentMatching.convertToNumber(args_[1]), _cont, clArg_));
        }
        if (StringList.quickEq(name_, am_.getAliasRotateLeft())) {
            ClassArgumentMatching clArg_ = new ClassArgumentMatching(paramList_.first());
            return(AliasNumber.calculateRotateLeft(ClassArgumentMatching.convertToNumber(args_[0]), ClassArgumentMatching.convertToNumber(args_[1]), _cont, clArg_));
        }
        if (StringList.quickEq(name_, am_.getAliasRotateRight())) {
            ClassArgumentMatching clArg_ = new ClassArgumentMatching(paramList_.first());
            return(AliasNumber.calculateRotateRight(ClassArgumentMatching.convertToNumber(args_[0]), ClassArgumentMatching.convertToNumber(args_[1]), _cont, clArg_));
        }
        return null;
    }

    public static Struct instantiateNumber(ContextEl _cont, ConstructorId _method, Struct... _args) {
        String type_ = _method.getName();
        StringList list_ = _method.getParametersTypes();
        LgNames lgNames_ = _cont.getStandards();
        String booleanType_ = lgNames_.getAliasBoolean();
        String charType_ = lgNames_.getAliasCharacter();
        String stringType_ = lgNames_.getAliasString();
        String byteType_ = lgNames_.getAliasByte();
        String shortType_ = lgNames_.getAliasShort();
        String intType_ = lgNames_.getAliasInteger();
        String longType_ = lgNames_.getAliasLong();
        String floatType_ = lgNames_.getAliasFloat();
        if (StringList.quickEq(type_, booleanType_)) {
            if (StringList.quickEq(list_.first(), stringType_)) {
                String one_ = NumParsers.getCharSeq(_args[0]).toStringInstance();
                if (StringList.quickEq(one_, lgNames_.getDisplayedStrings().getTrueString())) {
                    return(BooleanStruct.of(true));
                }
                return(BooleanStruct.of(false));
            }
            return(ClassArgumentMatching.convertToBoolean(_args[0]));
        }
        if (StringList.quickEq(type_, charType_)) {
            return(_args[0]);
        }
        if (StringList.quickEq(type_, byteType_)) {
            if (StringList.quickEq(list_.first(), stringType_)) {
                return parseByte(list_,_args,true);
            }
            byte one_ = (ClassArgumentMatching.convertToNumber(_args[0])).byteStruct();
            return(new ByteStruct(one_));
        }
        if (StringList.quickEq(type_, shortType_)) {
            if (StringList.quickEq(list_.first(), stringType_)) {
                return parseShort(list_,_args,true);
            }
            short one_ = (ClassArgumentMatching.convertToNumber(_args[0])).shortStruct();
            return(new ShortStruct(one_));
        }
        if (StringList.quickEq(type_, intType_)) {
            if (StringList.quickEq(list_.first(), stringType_)) {
                return parseInt(list_,_args,true);
            }
            int one_ = (ClassArgumentMatching.convertToNumber(_args[0])).intStruct();
            return(new IntStruct(one_));
        }
        if (StringList.quickEq(type_, longType_)) {
            if (StringList.quickEq(list_.first(), stringType_)) {
                return parseLong(list_,_args,true);
            }
            long one_ = (ClassArgumentMatching.convertToNumber(_args[0])).longStruct();
            return(new LongStruct(one_));
        }
        if (StringList.quickEq(type_, floatType_)) {
            if (StringList.quickEq(list_.first(), stringType_)) {
                return parseFloat(_args[0],true);
            }
            float one_ = (ClassArgumentMatching.convertToNumber(_args[0])).floatStruct();
            return (new FloatStruct(one_));
        }
        if (StringList.quickEq(list_.first(), stringType_)) {
            return parseDouble(_args[0],true);
        }
        double one_ = (ClassArgumentMatching.convertToNumber(_args[0])).doubleStruct();
        return(new DoubleStruct(one_));
    }

    private static Struct parseDouble(Struct _arg, boolean _exception) {
        if (!(_arg instanceof CharSequenceStruct)) {
            if (!_exception) {
                return(NullStruct.NULL_VALUE);
            }
            return null;
        }
        String one_ = NumParsers.getCharSeq(_arg).toStringInstance();
        DoubleInfo v_ = NumParsers.splitDouble(one_);
        if (!v_.isValid()) {
            if (!_exception) {
                return(NullStruct.NULL_VALUE);
            }
            return null;
        } else {
            return(new DoubleStruct(v_.getValue()));
        }
    }

    private static Struct parseFloat(Struct _arg, boolean _exception) {
        if (!(_arg instanceof CharSequenceStruct)) {
            if (!_exception) {
                return(NullStruct.NULL_VALUE);
            }
            return null;
        }
        String one_ = NumParsers.getCharSeq(_arg).toStringInstance();
        boolean valid_ = true;
        DoubleInfo v_ = NumParsers.splitDouble(one_);
        double d_ = 0.0;
        if (v_.outOfRange(Float.MIN_VALUE,Float.MAX_VALUE)) {
            valid_ = false;
        } else {
            d_ = v_.getValue();
        }
        if (!valid_) {
            if (!_exception) {
                return(NullStruct.NULL_VALUE);
            }
            return null;
        } else {
            return(new FloatStruct((float) d_));
        }
    }

    private static Struct parseLong(StringList _list, Struct[] _args, boolean _exception) {
        if (!(_args[0] instanceof CharSequenceStruct)) {
            if (!_exception) {
                return(NullStruct.NULL_VALUE);
            }
            return null;
        }
        String one_ = NumParsers.getCharSeq(_args[0]).toStringInstance();
        LongInfo lg_;
        int radix_ = NumParsers.getRadix(_list, _args);
        lg_ = NumParsers.parseLong(one_, radix_);
        if (!lg_.isValid()) {
            if (!_exception) {
                return(NullStruct.NULL_VALUE);
            }
            return null;
        } else {
            return(new LongStruct(lg_.getValue()));
        }
    }

    private static Struct parseInt(StringList _list, Struct[] _args, boolean _exception) {
        if (!(_args[0] instanceof CharSequenceStruct)) {
            if (!_exception) {
                return(NullStruct.NULL_VALUE);
            }
            return null;
        }
        String one_ = NumParsers.getCharSeq(_args[0]).toStringInstance();
        LongInfo lg_;
        int radix_ = NumParsers.getRadix(_list, _args);
        lg_ = NumParsers.parseLong(one_, radix_);
        if (lg_.outOfRange(Integer.MIN_VALUE,Integer.MAX_VALUE)) {
            if (!_exception) {
                return(NullStruct.NULL_VALUE);
            }
            return null;
        } else {
            return(new IntStruct((int) lg_.getValue()));
        }
    }

    private static Struct parseShort(StringList _list, Struct[] _args, boolean _exception) {
        if (!(_args[0] instanceof CharSequenceStruct)) {
            if (!_exception) {
                return(NullStruct.NULL_VALUE);
            }
            return null;
        }
        String one_ = NumParsers.getCharSeq(_args[0]).toStringInstance();
        LongInfo lg_;
        int radix_ = NumParsers.getRadix(_list, _args);
        lg_ = NumParsers.parseLong(one_, radix_);
        if (lg_.outOfRange(Short.MIN_VALUE,Short.MAX_VALUE)) {
            if (!_exception) {
                return(NullStruct.NULL_VALUE);
            }
            return null;
        } else {
            return(new ShortStruct((short) lg_.getValue()));
        }
    }

    private static Struct parseByte(StringList _list, Struct[] _args, boolean _exception) {
        if (!(_args[0] instanceof CharSequenceStruct)) {
            if (!_exception) {
                return(NullStruct.NULL_VALUE);
            }
            return null;
        }
        String one_ = NumParsers.getCharSeq(_args[0]).toStringInstance();
        LongInfo lg_;
        int radix_ = NumParsers.getRadix(_list, _args);
        lg_ = NumParsers.parseLong(one_, radix_);
        if (lg_.outOfRange(Byte.MIN_VALUE,Byte.MAX_VALUE)) {
            if (!_exception) {
                return(NullStruct.NULL_VALUE);
            }
            return null;
        } else {
            return(new ByteStruct((byte)lg_.getValue()));
        }
    }

    private static Struct calculateNumber(ContextEl _cont, ClassMethodId _method, Struct _struct, Struct... _args) {
        String type_ = _method.getClassName();
        String name_ = _method.getConstraints().getName();
        StringList list_ = _method.getConstraints().getParametersTypes();
        LgNames lgNames_ = _cont.getStandards();
        String booleanType_ = lgNames_.getAliasBoolean();
        String charType_ = lgNames_.getAliasCharacter();
        String byteType_ = lgNames_.getAliasByte();
        String shortType_ = lgNames_.getAliasShort();
        String intType_ = lgNames_.getAliasInteger();
        String longType_ = lgNames_.getAliasLong();
        String floatType_ = lgNames_.getAliasFloat();
        String doubleType_ = lgNames_.getAliasDouble();
        String nbType_ = lgNames_.getAliasNumber();
        String booleanPrimType_ = lgNames_.getAliasPrimBoolean();
        if (StringList.quickEq(type_, booleanType_)) {
            if (StringList.quickEq(name_, lgNames_.getAliasBooleanValue())) {
                return(ClassArgumentMatching.convertToBoolean(_struct));
            }
            if (StringList.quickEq(name_, lgNames_.getAliasCompare())) {
                return(NumParsers.cmpBool((ClassArgumentMatching.convertToBoolean(_args[0])),(ClassArgumentMatching.convertToBoolean(_args[1]))));
            }
            if (StringList.quickEq(name_, lgNames_.getAliasCompareTo())) {
                BooleanStruct instance_ = ClassArgumentMatching.convertToBoolean(_struct);
                return(NumParsers.cmpBool(instance_,(ClassArgumentMatching.convertToBoolean(_args[0]))));
            }
            if (StringList.quickEq(name_, lgNames_.getAliasEquals())) {
                BooleanStruct instance_ = ClassArgumentMatching.convertToBoolean(_struct);
                return(BooleanStruct.of(instance_.sameReference(_args[0])));
            }
            if (StringList.quickEq(name_, lgNames_.getAliasParseBoolean())) {
                StringStruct disp_ = ExecCatOperation.getDisplayable(new Argument(_args[0]),_cont).getDisplayedString(_cont);
                if (StringList.quickEq(disp_.getInstance(),lgNames_.getDisplayedStrings().getTrueString())) {
                    return(BooleanStruct.of(true));
                }
                return(BooleanStruct.of(false));
            }
            if (StringList.quickEq(name_, lgNames_.getAliasToStringMethod())) {
                if (!list_.isEmpty()) {
                    return ((ClassArgumentMatching.convertToBoolean(_args[0])).getDisplayedString(_cont));
                }
                BooleanStruct instance_ = ClassArgumentMatching.convertToBoolean(_struct);
                return (instance_.getDisplayedString(_cont));
            }
            if (StringList.quickEq(list_.first(), booleanPrimType_)) {
                return (_args[0]);
            }
            StringStruct disp_ = ExecCatOperation.getDisplayable(new Argument(_args[0]), _cont).getDisplayedString(_cont);
            if (StringList.quickEq(disp_.getInstance(), lgNames_.getDisplayedStrings().getTrueString())) {
                return (BooleanStruct.of(true));
            }
            return (BooleanStruct.of(false));
        }
        if (StringList.quickEq(type_, charType_)) {
            if (_method.getConstraints().isStaticMethod()) {
                if (StringList.quickEq(name_, lgNames_.getAliasCompare())) {
                    char one_ = ClassArgumentMatching.convertToChar(_args[0]).getChar();
                    char two_ = ClassArgumentMatching.convertToChar(_args[1]).getChar();
                    return(new IntStruct(Numbers.compareLg(one_,two_)));
                }
                if (StringList.quickEq(name_, lgNames_.getAliasDigit())) {
                    char one_ = ClassArgumentMatching.convertToChar(_args[0]).getChar();
                    Integer two_ = (ClassArgumentMatching.convertToNumber(_args[1])).intStruct();
                    return(new IntStruct(Character.digit(one_, two_)));
                }
                if (StringList.quickEq(name_, lgNames_.getAliasForDigit())) {
                    Integer one_ = (ClassArgumentMatching.convertToNumber(_args[0])).intStruct();
                    Integer two_ = (ClassArgumentMatching.convertToNumber(_args[1])).intStruct();
                    return(new CharStruct(Character.forDigit(one_, two_)));
                }
                if (StringList.quickEq(name_, lgNames_.getAliasGetDirectionality())) {
                    char one_ = ClassArgumentMatching.convertToChar(_args[0]).getChar();
                    return(new ByteStruct(Character.getDirectionality(one_)));
                }
                if (StringList.quickEq(name_, lgNames_.getAliasGetType())) {
                    char one_ = ClassArgumentMatching.convertToChar(_args[0]).getChar();
                    return(new IntStruct(Character.getType(one_)));
                }
                if (StringList.quickEq(name_, lgNames_.getAliasIsDigit())) {
                    char one_ = ClassArgumentMatching.convertToChar(_args[0]).getChar();
                    return(BooleanStruct.of(StringExpUtil.isDigit(one_)));
                }
                if (StringList.quickEq(name_, lgNames_.getAliasIsLetter())) {
                    char one_ = ClassArgumentMatching.convertToChar(_args[0]).getChar();
                    return(BooleanStruct.of(Character.isLetter(one_)));
                }
                if (StringList.quickEq(name_, lgNames_.getAliasIsLetterOrDigit())) {
                    char one_ = ClassArgumentMatching.convertToChar(_args[0]).getChar();
                    return(BooleanStruct.of(Character.isLetterOrDigit(one_)));
                }
                if (StringList.quickEq(name_, lgNames_.getAliasIsLowerCase())) {
                    char one_ = ClassArgumentMatching.convertToChar(_args[0]).getChar();
                    return(BooleanStruct.of(Character.isLowerCase(one_)));
                }
                if (StringList.quickEq(name_, lgNames_.getAliasIsUpperCase())) {
                    char one_ = ClassArgumentMatching.convertToChar(_args[0]).getChar();
                    return(BooleanStruct.of(Character.isUpperCase(one_)));
                }
                if (StringList.quickEq(name_, lgNames_.getAliasIsSpace())) {
                    char one_ = ClassArgumentMatching.convertToChar(_args[0]).getChar();
                    return(BooleanStruct.of(Character.isWhitespace(one_)));
                }
                if (StringList.quickEq(name_, lgNames_.getAliasIsWhitespace())) {
                    char one_ = ClassArgumentMatching.convertToChar(_args[0]).getChar();
                    return(BooleanStruct.of(Character.isWhitespace(one_)));
                }
                if (StringList.quickEq(name_, lgNames_.getAliasIsWordChar())) {
                    char one_ = ClassArgumentMatching.convertToChar(_args[0]).getChar();
                    return(BooleanStruct.of(StringList.isWordChar(one_)));
                }
                if (StringList.quickEq(name_, lgNames_.getAliasToLowerCaseChar())) {
                    char one_ = ClassArgumentMatching.convertToChar(_args[0]).getChar();
                    return(new CharStruct(Character.toLowerCase(one_)));
                }
                if (StringList.quickEq(name_, lgNames_.getAliasToUpperCaseChar())) {
                    char one_ = ClassArgumentMatching.convertToChar(_args[0]).getChar();
                    return(new CharStruct(Character.toUpperCase(one_)));
                }
                char one_ = ClassArgumentMatching.convertToChar(_args[0]).getChar();
                return(new StringStruct(Character.toString(one_)));
            }
            CharStruct ch_ = ClassArgumentMatching.convertToChar(_struct);
            if (StringList.quickEq(name_, lgNames_.getAliasCharValue())) {
                return(new CharStruct(ch_.getChar()));
            }
            if (!(_args[0] instanceof CharStruct)) {
                return null;
            }
            char one_ = ch_.getChar();
            char two_ = ((CharStruct) _args[0]).getChar();
            return(new IntStruct(Numbers.compareLg(one_,two_)));
        }
        if (StringList.quickEq(type_, byteType_)
                || StringList.quickEq(type_, shortType_)
                || StringList.quickEq(type_, intType_)
                || StringList.quickEq(type_, longType_)
                || StringList.quickEq(type_, floatType_)
                || StringList.quickEq(type_, doubleType_)) {
            if (StringList.quickEq(name_, lgNames_.getAliasCompare())) {
                return(new IntStruct(NumParsers.compare(ClassArgumentMatching.convertToNumber(_args[0]),ClassArgumentMatching.convertToNumber(_args[1]))));
            }
            if (StringList.quickEq(name_, lgNames_.getAliasCompareTo())) {
                NumberStruct instance_ = ClassArgumentMatching.convertToNumber(_struct);
                return(new IntStruct(NumParsers.compare(instance_,ClassArgumentMatching.convertToNumber(_args[0]))));
            }
            if (StringList.quickEq(type_, byteType_)) {
                if (StringList.quickEq(name_, lgNames_.getAliasToStringMethod())) {
                    byte one_ = (ClassArgumentMatching.convertToNumber(_args[0])).byteStruct();
                    return (new StringStruct(Integer.toString(one_)));
                }
                boolean exc_ = StringList.quickEq(name_, lgNames_.getAliasParseByte());
                return parseByte(list_, _args, exc_);
            }
            if (StringList.quickEq(type_, shortType_)) {
                if (StringList.quickEq(name_, lgNames_.getAliasToStringMethod())) {
                    short one_ = (ClassArgumentMatching.convertToNumber(_args[0])).shortStruct();
                    return (new StringStruct(Integer.toString(one_)));
                }
                boolean exc_ = StringList.quickEq(name_, lgNames_.getAliasParseShort());
                return parseShort(list_, _args, exc_);
            }
            if (StringList.quickEq(type_, intType_)) {
                if (StringList.quickEq(name_, lgNames_.getAliasToStringMethod())) {
                    int one_ = (ClassArgumentMatching.convertToNumber(_args[0])).intStruct();
                    return (new StringStruct(Integer.toString(one_)));
                }
                boolean exc_ = StringList.quickEq(name_, lgNames_.getAliasParseInt());
                return parseInt(list_, _args, exc_);
            }
            if (StringList.quickEq(type_, longType_)) {
                if (StringList.quickEq(name_, lgNames_.getAliasToStringMethod())) {
                    long one_ = (ClassArgumentMatching.convertToNumber(_args[0])).longStruct();
                    return (new StringStruct(Long.toString(one_)));
                }
                boolean exc_ = StringList.quickEq(name_, lgNames_.getAliasParseLong());
                return parseLong(list_, _args, exc_);
            }
            if (StringList.quickEq(type_, floatType_)) {
                if (StringList.quickEq(name_, lgNames_.getAliasIsNan())) {
                    float one_;
                    one_ = (ClassArgumentMatching.convertToNumber(_args[0])).floatStruct();
                    return (BooleanStruct.of(Float.isNaN(one_)));
                }
                if (StringList.quickEq(name_, lgNames_.getAliasIsInfinite())) {
                    float one_;
                    one_ = (ClassArgumentMatching.convertToNumber(_args[0])).floatStruct();
                    return (BooleanStruct.of(Float.isInfinite(one_)));
                }
                if (StringList.quickEq(name_, lgNames_.getAliasToStringMethod())) {
                    DisplayedStrings dis_ = _cont.getStandards().getDisplayedStrings();
                    NumberStruct nb_ = ClassArgumentMatching.convertToNumber(_args[0]);
                    return NumParsers.getFloatString(nb_,dis_.getInfinity(),
                            dis_.getNan(),
                            dis_.getExponent());
                }
                boolean exc_ = StringList.quickEq(name_, lgNames_.getAliasParseFloat());
                return parseFloat(_args[0], exc_);
            }
            if (StringList.quickEq(name_, lgNames_.getAliasIsNan())) {
                double one_ = NumParsers.asDouble(_struct, list_, _args);
                return (BooleanStruct.of(Double.isNaN(one_)));
            }
            if (StringList.quickEq(name_, lgNames_.getAliasIsInfinite())) {
                double one_ = NumParsers.asDouble(_struct, list_, _args);
                return (BooleanStruct.of(Double.isInfinite(one_)));
            }
            if (StringList.quickEq(name_, lgNames_.getAliasToStringMethod())) {
                DisplayedStrings dis_ = _cont.getStandards().getDisplayedStrings();
                NumberStruct nb_ = ClassArgumentMatching.convertToNumber(_args[0]);
                return NumParsers.getDoubleString(nb_,dis_.getInfinity(),
                        dis_.getNan(),
                        dis_.getExponent());
            }
            boolean exc_ = StringList.quickEq(name_, lgNames_.getAliasParseDouble());
            return parseDouble(_args[0], exc_);
        }
        if (StringList.quickEq(type_, nbType_)) {
            if (StringList.quickEq(name_, lgNames_.getAliasCompare())) {
                if (!(_args[0] instanceof  NumberStruct)) {
                    return null;
                }
                if (!(_args[1] instanceof  NumberStruct)) {
                    return null;
                }
                return(new IntStruct(NumParsers.compareGene(ClassArgumentMatching.convertToNumber(_args[0]), ClassArgumentMatching.convertToNumber(_args[1]))));
            }
            if (StringList.quickEq(name_, lgNames_.getAliasCompareTo())) {
                NumberStruct instance_ = ClassArgumentMatching.convertToNumber(_struct);
                if (!(_args[0] instanceof  NumberStruct)) {
                    return null;
                }
                return(new IntStruct(NumParsers.compareGene(instance_, ClassArgumentMatching.convertToNumber(_args[0]))));
            }
            if (StringList.quickEq(name_, lgNames_.getAliasEquals())) {
                if (list_.size() > 1) {
                    return(BooleanStruct.of(NumParsers.sameValue(_args[0],_args[1])));
                }
                return(BooleanStruct.of(NumParsers.sameValue(_struct,_args[0])));
            }
            if (StringList.quickEq(name_, lgNames_.getAliasByteValue())) {
                NumberStruct instance_ = ClassArgumentMatching.convertToNumber(_struct);
                return(new ByteStruct(instance_.byteStruct()));
            }
            if (StringList.quickEq(name_, lgNames_.getAliasShortValue())) {
                NumberStruct instance_ = ClassArgumentMatching.convertToNumber(_struct);
                return(new ShortStruct(instance_.shortStruct()));
            }
            if (StringList.quickEq(name_, lgNames_.getAliasIntValue())) {
                NumberStruct instance_ = ClassArgumentMatching.convertToNumber(_struct);
                return(new IntStruct(instance_.intStruct()));
            }
            if (StringList.quickEq(name_, lgNames_.getAliasLongValue())) {
                NumberStruct instance_ = ClassArgumentMatching.convertToNumber(_struct);
                return(new LongStruct(instance_.longStruct()));
            }
            if (StringList.quickEq(name_, lgNames_.getAliasFloatValue())) {
                NumberStruct instance_ = ClassArgumentMatching.convertToNumber(_struct);
                return(new FloatStruct(instance_.floatStruct()));
            }
            if (StringList.quickEq(name_, lgNames_.getAliasDoubleValue())) {
                NumberStruct instance_ = ClassArgumentMatching.convertToNumber(_struct);
                return(new DoubleStruct(instance_.doubleStruct()));
            }
            if (list_.isEmpty()) {
                NumberStruct instance_ = ClassArgumentMatching.convertToNumber(_struct);
                return(instance_.getDisplayedString(_cont));
            }
            return(ExecCatOperation.getDisplayable(new Argument(_args[0]),_cont)
                    .getDisplayedString(_cont));
        }
        return null;
    }

    public static Struct invokeAnalyzisCharSequenceStdMethod(ContextEl _cont, ClassMethodId _method, Struct _struct, Argument... _args) {
        Struct[] args_ = getObjects(_args);
        LgNames lgNames_ = _cont.getStandards();
        String type_ = _method.getClassName();
        String stringType_ = lgNames_.getAliasString();
        if (StringList.quickEq(type_, stringType_)) {
            return calculateString(_cont, _method, _struct, args_);
        }
        return calculateCharSeq(_cont, _method, _struct, args_);
    }

    public static Struct calculateString(ContextEl _cont, ClassMethodId _method, Struct _struct, Struct... _args) {
        if (!_method.getConstraints().isStaticMethod()) {
            StringStruct str_ = getString(_struct);
            return calculateLocString(str_,_cont, _method, _args);
        }
        String name_ = _method.getConstraints().getName();
        StringList list_ = _method.getConstraints().getParametersTypes();
        LgNames lgNames_ = _cont.getStandards();
        if (StringList.quickEq(name_, lgNames_.getAliasCompare())) {
            Struct arg_ = _args[0];
            if (!(arg_ instanceof StringStruct)) {
                return null;
            }
            StringStruct first_ = (StringStruct) arg_;
            return compareToString(first_,_args[1]);
        }
        Struct arg_ = NumParsers.getArg(list_, _args);
        if (NumParsers.isDisplay(list_, arg_)) {
            return ExecCatOperation.getDisplayable(new Argument(arg_), _cont).getDisplayedString(_cont);
        }
        if (!(arg_ instanceof ArrayStruct)) {
            return null;
        }
        return tryGetCharArray(list_, (ArrayStruct) arg_, _args);
    }

    private static Struct tryGetCharArray(StringList _list, ArrayStruct _arg, Struct[] _args) {
        Struct[] argArr_ = _arg.getInstance();
        int len_ = argArr_.length;
        char[] arr_ = new char[len_];
        for (int i = 0; i < len_; i++) {
            arr_[i] = ClassArgumentMatching.convertToChar(argArr_[i]).getChar();
        }
        if (_list.size() == 1) {
            return new StringStruct(String.valueOf(arr_));
        }
        int one_ = (ClassArgumentMatching.convertToNumber(_args[0])).intStruct();
        int two_ = (ClassArgumentMatching.convertToNumber(_args[1])).intStruct();
        if (NumParsers.okArray(arr_, one_, two_)) {
            return null;
        }
        return new StringStruct(String.valueOf(arr_,one_,two_));
    }

    private static Struct calculateLocString(StringStruct _str, ContextEl _cont, ClassMethodId _method, Struct... _args) {
        String name_ = _method.getConstraints().getName();
        StringList list_ = _method.getConstraints().getParametersTypes();
        LgNames lgNames_ = _cont.getStandards();
        String stringType_ = lgNames_.getAliasString();
        if (StringList.quickEq(name_, lgNames_.getAliasRegionMatches())) {
            return regionMatches(_str,ClassArgumentMatching.convertToBoolean(_args[0]),ClassArgumentMatching.convertToNumber(_args[1]), _args[2],
                    ClassArgumentMatching.convertToNumber(_args[3]), ClassArgumentMatching.convertToNumber(_args[4]));
        }
        if (StringList.quickEq(name_, lgNames_.getAliasReplace())) {
            if (StringList.quickEq(list_.first(), stringType_)) {
                return replaceString(_str,_args[0], _args[1]);
            }
            return replace(_str,ClassArgumentMatching.convertToChar(_args[0]), ClassArgumentMatching.convertToChar(_args[1]));
        }
        if (StringList.quickEq(name_, lgNames_.getAliasReplaceMultiple())) {
            return replaceMultiple(_str,_args[0]);
        }
        String one_ = _str.getInstance();
        if (StringList.quickEq(name_, lgNames_.getAliasCompareToIgnoreCase())) {
            Struct two_ = _args[0];
            if (!(two_ instanceof StringStruct)) {
                return null;
            }
            StringStruct t_ = (StringStruct) two_;
            return new IntStruct(one_.compareToIgnoreCase(t_.getInstance()));
        }
        if (StringList.quickEq(name_, lgNames_.getAliasEqualsIgnoreCase())) {
            Struct two_ = _args[0];
            if (!(two_ instanceof StringStruct)) {
                return BooleanStruct.of(false);
            }
            StringStruct t_ = (StringStruct) two_;
            return BooleanStruct.of(one_.equalsIgnoreCase(t_.getInstance()));
        }
        if (StringList.quickEq(name_, lgNames_.getAliasToLowerCase())) {
            return new StringStruct(StringList.toLowerCase(one_));
        }
        return new StringStruct(StringList.toUpperCase(one_));
    }

    private static Struct compareToString(StringStruct _str, Struct _anotherString) {
        if (!(_anotherString instanceof StringStruct)) {
            return null;
        }
        StringStruct st_ = (StringStruct)_anotherString;
        return new IntStruct(_str.getInstance().compareTo(st_.getInstance()));
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
        return BooleanStruct.of(_str.getInstance().regionMatches(case_,to_, other_.getInstance(), po_, comLen_));
    }

    private static Struct replace(StringStruct _str, CharStruct _oldChar, CharStruct _newChar) {
        char oldChar_ = _oldChar.getChar();
        char newChar_ = _newChar.getChar();
        return new StringStruct(_str.getInstance().replace(oldChar_, newChar_));
    }

    private static Struct replaceString(StringStruct _str, Struct _oldChar, Struct _newChar) {
        String old_;
        old_ = NumParsers.getString(_oldChar);
        String new_;
        new_ = NumParsers.getString(_newChar);
        String out_ = StringList.replace(_str.getInstance(), old_, new_);
        return new StringStruct(out_);
    }

    private static Struct replaceMultiple(StringStruct _st, Struct _seps) {
        if (!(_seps instanceof ArrayStruct)) {
            return null;
        }
        ArrayStruct arrSep_ = (ArrayStruct) _seps;
        Struct[] arrStructSep_ = arrSep_.getInstance();
        int lenSeps_ = arrStructSep_.length;
        Replacement[] seps_ = new Replacement[lenSeps_];
        for (int i = 0; i < lenSeps_; i++) {
            Struct curSep_ = arrStructSep_[i];
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
        return new StringStruct(StringList.replaceMult(_st.getInstance(), seps_));
    }

    public static Struct calculateCharSeq(ContextEl _cont, ClassMethodId _method, Struct _struct, Struct... _args) {
        if (!_method.getConstraints().isStaticMethod()) {
            return calculateLocCharSeq(NumParsers.getCharSeq(_struct), _cont, _method, _args);
        }
        if (!(_args[0] instanceof CharSequenceStruct)) {
            return BooleanStruct.of(_args[1] == NullStruct.NULL_VALUE);
        }
        return BooleanStruct.of(NumParsers.sameEq(NumParsers.getCharSeq(_args[0]),_args[1]));
    }

    private static Struct calculateLocCharSeq(CharSequenceStruct _charSequence, ContextEl _cont, ClassMethodId _method, Struct... _args) {
        String name_ = _method.getConstraints().getName();
        StringList list_ = _method.getConstraints().getParametersTypes();
        LgNames lgNames_ = _cont.getStandards();
        if (StringList.quickEq(name_, lgNames_.getAliasLength())) {
            return new IntStruct(_charSequence.length());
        }
        if (StringList.quickEq(name_, lgNames_.getAliasIsEmpty())) {
            return _charSequence.isEmpty();
        }
        if (StringList.quickEq(name_, lgNames_.getAliasCharAt())) {
            return charAt(_charSequence, _args[0]);
        }
        if (StringList.quickEq(name_, lgNames_.getAliasCompareTo())) {
            return compareTo(_charSequence, _args[0]);
        }
        if (StringList.quickEq(name_, lgNames_.getAliasRegionMatches())) {
            return regionMatches(_charSequence, ClassArgumentMatching.convertToNumber(_args[0]), _args[1], ClassArgumentMatching.convertToNumber(_args[2]), ClassArgumentMatching.convertToNumber(_args[3]));
        }
        if (StringList.quickEq(name_, lgNames_.getAliasStartsWith())) {
            if (list_.size() == 1) {
                return startsWith(_charSequence, _args[0]);
            }
            return startsWith(_charSequence, _args[0], ClassArgumentMatching.convertToNumber(_args[1]));
        }
        if (StringList.quickEq(name_, lgNames_.getAliasEndsWith())) {
            return endsWith(_charSequence, _args[0]);
        }
        if (StringList.quickEq(name_, lgNames_.getAliasIndexOf())) {
            if (list_.size() == 1) {
                if (!(_args[0] instanceof NumberStruct)) {
                    return indexOfString(_charSequence, _args[0]);
                }
                return indexOf(_charSequence, _args[0]);
            }
            if (!(_args[0] instanceof NumberStruct)) {
                return indexOfString(_charSequence, _args[0], ClassArgumentMatching.convertToNumber(_args[1]));
            }
            return indexOf(_charSequence, _args[0], _args[1]);
        }
        if (StringList.quickEq(name_, lgNames_.getAliasContains())) {
            return contains(_charSequence, _args[0]);
        }
        if (StringList.quickEq(name_, lgNames_.getAliasLastIndexOf())) {
            if (list_.size() == 1) {
                if (!(_args[0] instanceof NumberStruct)) {
                    return lastIndexOfString(_charSequence, _args[0]);
                }
                return lastIndexOf(_charSequence, _args[0]);
            }
            if (!(_args[0] instanceof NumberStruct)) {
                return lastIndexOfString(_charSequence, _args[0], ClassArgumentMatching.convertToNumber(_args[1]));
            }
            return lastIndexOf(_charSequence, _args[0], _args[1]);
        }
        if (StringList.quickEq(name_, lgNames_.getAliasSubstring()) || StringList.quickEq(name_, lgNames_.getAliasSubSequence())) {
            if (list_.size() == 1) {
                return substring(_charSequence, ClassArgumentMatching.convertToNumber(_args[0]));
            }
            return substring(_charSequence, ClassArgumentMatching.convertToNumber(_args[0]), ClassArgumentMatching.convertToNumber(_args[1]));
        }
        if (StringList.quickEq(name_, lgNames_.getAliasTrim())) {
            return trim(_charSequence);
        }
        if (StringList.quickEq(name_, lgNames_.getAliasFormat())) {
            return format(_charSequence, _args[0]);
        }
        if (StringList.quickEq(name_, lgNames_.getAliasToStringMethod())) {
            return _charSequence.getDisplayedString(_cont);
        }
        return null;
    }

    private static Struct charAt(CharSequenceStruct _charSequence, Struct _index) {
        NumberStruct nb_ = ClassArgumentMatching.convertToNumber(_index);
        int ind_ = nb_.intStruct();
        if (_charSequence.isValidIndex(ind_)) {
            return null;
        }
        return new CharStruct(_charSequence.charAt(ind_));
    }

    private static Struct compareTo(CharSequenceStruct _charSequence, Struct _anotherString) {
        if (!(_anotherString instanceof CharSequenceStruct)) {
            return null;
        }
        CharSequenceStruct st_ = NumParsers.getCharSeq(_anotherString);
        return new IntStruct(_charSequence.toStringInstance().compareTo(st_.toStringInstance()));
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
        return BooleanStruct.of(_charSequence.toStringInstance().regionMatches(to_, other_.toStringInstance(), po_, comLen_));
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
        NumberStruct ch_ = ClassArgumentMatching.convertToNumber(_ch);
        int int_ = ch_.intStruct();
        NumberStruct index_ = ClassArgumentMatching.convertToNumber(_fromIndex);
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
        NumberStruct ch_ = ClassArgumentMatching.convertToNumber(_ch);
        int int_ = ch_.intStruct();
        NumberStruct index_ = ClassArgumentMatching.convertToNumber(_fromIndex);
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
        if (_charSequence.isCorrectSub(begin_, end_)) {
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
        Struct[] arrStructSep_ = arrSep_.getInstance();
        int lenSeps_ = arrStructSep_.length;
        String[] seps_ = new String[lenSeps_];
        for (int i = 0; i < lenSeps_; i++) {
            Struct curSep_ = arrStructSep_[i];
            if (!(curSep_ instanceof CharSequenceStruct)) {
                return null;
            }
            seps_[i] = NumParsers.getCharSeq(curSep_).toStringInstance();
        }
        return new StringStruct(StringList.simpleStringsFormat(_charSequence.toStringInstance(), seps_));
    }
}
