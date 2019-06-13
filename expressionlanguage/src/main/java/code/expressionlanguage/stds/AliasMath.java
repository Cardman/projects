package code.expressionlanguage.stds;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.Argument;
import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.opers.util.ClassMethodId;
import code.expressionlanguage.opers.util.MethodId;
import code.expressionlanguage.opers.util.MethodModifier;
import code.expressionlanguage.structs.*;
import code.util.*;

public final class AliasMath {
    private String aliasAbs;
    private String aliasQuot;
    private String aliasMod;
    private String aliasMath;
    private String aliasBinQuot;
    private String aliasBinMod;
    private String aliasPlus;
    private String aliasMinus;
    private String aliasMult;
    private String aliasAnd;
    private String aliasOr;
    private String aliasXor;
    private String aliasNeg;
    private String aliasNegBin;
    private String aliasLt;
    private String aliasGt;
    private String aliasLe;
    private String aliasGe;
    private String aliasShiftLeft;
    private String aliasShiftRight;
    private String aliasBitShiftLeft;
    private String aliasBitShiftRight;
    private String aliasRotateLeft;
    private String aliasRotateRight;
    private String aliasRandom;
    public void build(LgNames _stds) {
        StringMap<StandardField> fields_;
        StringList params_;
        StandardMethod method_;
        CustList<StandardConstructor> constructors_;
        ObjectMap<MethodId, StandardMethod> methods_;
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        String aliasObject_ = _stds.getAliasObject();
        String aliasPrimInteger_ = _stds.getAliasPrimInteger();
        String aliasPrimLong_ = _stds.getAliasPrimLong();
        String aliasPrimFloat_ = _stds.getAliasPrimFloat();
        String aliasPrimDouble_ = _stds.getAliasPrimDouble();
        String aliasPrimBoolean_ = _stds.getAliasPrimBoolean();
        StandardClass std_ = new StandardClass(aliasMath, fields_, constructors_, methods_, aliasObject_, MethodModifier.ABSTRACT);
        params_ = new StringList(aliasPrimInteger_);
        method_ = new StandardMethod(aliasAbs, params_, aliasPrimInteger_, false, MethodModifier.STATIC,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimLong_);
        method_ = new StandardMethod(aliasAbs, params_, aliasPrimLong_, false, MethodModifier.STATIC,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimInteger_,aliasPrimInteger_);
        method_ = new StandardMethod(aliasQuot, params_, aliasPrimInteger_, false, MethodModifier.STATIC,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimLong_,aliasPrimLong_);
        method_ = new StandardMethod(aliasQuot, params_, aliasPrimLong_, false, MethodModifier.STATIC,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimInteger_,aliasPrimInteger_);
        method_ = new StandardMethod(aliasMod, params_, aliasPrimInteger_, false, MethodModifier.STATIC,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimLong_,aliasPrimLong_);
        method_ = new StandardMethod(aliasMod, params_, aliasPrimLong_, false, MethodModifier.STATIC,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimInteger_);
        method_ = new StandardMethod(aliasPlus, params_, aliasPrimInteger_, false, MethodModifier.STATIC,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimLong_);
        method_ = new StandardMethod(aliasPlus, params_, aliasPrimLong_, false, MethodModifier.STATIC,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimFloat_);
        method_ = new StandardMethod(aliasPlus, params_, aliasPrimFloat_, false, MethodModifier.STATIC,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimDouble_);
        method_ = new StandardMethod(aliasPlus, params_, aliasPrimDouble_, false, MethodModifier.STATIC,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimInteger_);
        method_ = new StandardMethod(aliasMinus, params_, aliasPrimInteger_, false, MethodModifier.STATIC,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimLong_);
        method_ = new StandardMethod(aliasMinus, params_, aliasPrimLong_, false, MethodModifier.STATIC,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimFloat_);
        method_ = new StandardMethod(aliasMinus, params_, aliasPrimFloat_, false, MethodModifier.STATIC,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimDouble_);
        method_ = new StandardMethod(aliasMinus, params_, aliasPrimDouble_, false, MethodModifier.STATIC,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimBoolean_);
        method_ = new StandardMethod(aliasNeg, params_, aliasPrimBoolean_, false, MethodModifier.STATIC,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimInteger_);
        method_ = new StandardMethod(aliasNegBin, params_, aliasPrimInteger_, false, MethodModifier.STATIC,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimLong_);
        method_ = new StandardMethod(aliasNegBin, params_, aliasPrimLong_, false, MethodModifier.STATIC,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimFloat_);
        method_ = new StandardMethod(aliasNegBin, params_, aliasPrimFloat_, false, MethodModifier.STATIC,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimDouble_);
        method_ = new StandardMethod(aliasNegBin, params_, aliasPrimDouble_, false, MethodModifier.STATIC,std_);
        methods_.put(method_.getId(), method_);
        _stds.getStandards().put(aliasMath, std_);
        params_ = new StringList(aliasPrimInteger_,aliasPrimInteger_);
        method_ = new StandardMethod(aliasPlus, params_, aliasPrimInteger_, false, MethodModifier.STATIC,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimLong_,aliasPrimLong_);
        method_ = new StandardMethod(aliasPlus, params_, aliasPrimLong_, false, MethodModifier.STATIC,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimFloat_,aliasPrimFloat_);
        method_ = new StandardMethod(aliasPlus, params_, aliasPrimFloat_, false, MethodModifier.STATIC,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimDouble_,aliasPrimDouble_);
        method_ = new StandardMethod(aliasPlus, params_, aliasPrimDouble_, false, MethodModifier.STATIC,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimInteger_,aliasPrimInteger_);
        method_ = new StandardMethod(aliasMinus, params_, aliasPrimInteger_, false, MethodModifier.STATIC,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimLong_,aliasPrimLong_);
        method_ = new StandardMethod(aliasMinus, params_, aliasPrimLong_, false, MethodModifier.STATIC,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimFloat_,aliasPrimFloat_);
        method_ = new StandardMethod(aliasMinus, params_, aliasPrimFloat_, false, MethodModifier.STATIC,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimDouble_,aliasPrimDouble_);
        method_ = new StandardMethod(aliasMinus, params_, aliasPrimDouble_, false, MethodModifier.STATIC,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimInteger_,aliasPrimInteger_);
        method_ = new StandardMethod(aliasMult, params_, aliasPrimInteger_, false, MethodModifier.STATIC,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimLong_,aliasPrimLong_);
        method_ = new StandardMethod(aliasMult, params_, aliasPrimLong_, false, MethodModifier.STATIC,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimFloat_,aliasPrimFloat_);
        method_ = new StandardMethod(aliasMult, params_, aliasPrimFloat_, false, MethodModifier.STATIC,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimDouble_,aliasPrimDouble_);
        method_ = new StandardMethod(aliasMult, params_, aliasPrimDouble_, false, MethodModifier.STATIC,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimInteger_,aliasPrimInteger_);
        method_ = new StandardMethod(aliasBinQuot, params_, aliasPrimInteger_, false, MethodModifier.STATIC,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimLong_,aliasPrimLong_);
        method_ = new StandardMethod(aliasBinQuot, params_, aliasPrimLong_, false, MethodModifier.STATIC,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimFloat_,aliasPrimFloat_);
        method_ = new StandardMethod(aliasBinQuot, params_, aliasPrimFloat_, false, MethodModifier.STATIC,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimDouble_,aliasPrimDouble_);
        method_ = new StandardMethod(aliasBinQuot, params_, aliasPrimDouble_, false, MethodModifier.STATIC,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimInteger_,aliasPrimInteger_);
        method_ = new StandardMethod(aliasBinMod, params_, aliasPrimInteger_, false, MethodModifier.STATIC,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimLong_,aliasPrimLong_);
        method_ = new StandardMethod(aliasBinMod, params_, aliasPrimLong_, false, MethodModifier.STATIC,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimFloat_,aliasPrimFloat_);
        method_ = new StandardMethod(aliasBinMod, params_, aliasPrimFloat_, false, MethodModifier.STATIC,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimDouble_,aliasPrimDouble_);
        method_ = new StandardMethod(aliasBinMod, params_, aliasPrimDouble_, false, MethodModifier.STATIC,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimInteger_,aliasPrimInteger_);
        method_ = new StandardMethod(aliasAnd, params_, aliasPrimInteger_, false, MethodModifier.STATIC,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimLong_,aliasPrimLong_);
        method_ = new StandardMethod(aliasAnd, params_, aliasPrimLong_, false, MethodModifier.STATIC,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimBoolean_,aliasPrimBoolean_);
        method_ = new StandardMethod(aliasAnd, params_, aliasPrimBoolean_, false, MethodModifier.STATIC,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimInteger_,aliasPrimInteger_);
        method_ = new StandardMethod(aliasOr, params_, aliasPrimInteger_, false, MethodModifier.STATIC,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimLong_,aliasPrimLong_);
        method_ = new StandardMethod(aliasOr, params_, aliasPrimLong_, false, MethodModifier.STATIC,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimBoolean_,aliasPrimBoolean_);
        method_ = new StandardMethod(aliasOr, params_, aliasPrimBoolean_, false, MethodModifier.STATIC,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimInteger_,aliasPrimInteger_);
        method_ = new StandardMethod(aliasXor, params_, aliasPrimInteger_, false, MethodModifier.STATIC,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimLong_,aliasPrimLong_);
        method_ = new StandardMethod(aliasXor, params_, aliasPrimLong_, false, MethodModifier.STATIC,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimBoolean_,aliasPrimBoolean_);
        method_ = new StandardMethod(aliasXor, params_, aliasPrimLong_, false, MethodModifier.STATIC,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimInteger_,aliasPrimInteger_);
        method_ = new StandardMethod(aliasShiftLeft, params_, aliasPrimInteger_, false, MethodModifier.STATIC,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimLong_,aliasPrimLong_);
        method_ = new StandardMethod(aliasShiftLeft, params_, aliasPrimLong_, false, MethodModifier.STATIC,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimInteger_,aliasPrimInteger_);
        method_ = new StandardMethod(aliasShiftRight, params_, aliasPrimInteger_, false, MethodModifier.STATIC,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimLong_,aliasPrimLong_);
        method_ = new StandardMethod(aliasShiftRight, params_, aliasPrimLong_, false, MethodModifier.STATIC,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimInteger_,aliasPrimInteger_);
        method_ = new StandardMethod(aliasBitShiftLeft, params_, aliasPrimInteger_, false, MethodModifier.STATIC,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimLong_,aliasPrimLong_);
        method_ = new StandardMethod(aliasBitShiftLeft, params_, aliasPrimLong_, false, MethodModifier.STATIC,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimInteger_,aliasPrimInteger_);
        method_ = new StandardMethod(aliasBitShiftRight, params_, aliasPrimInteger_, false, MethodModifier.STATIC,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimLong_,aliasPrimLong_);
        method_ = new StandardMethod(aliasBitShiftRight, params_, aliasPrimLong_, false, MethodModifier.STATIC,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimInteger_,aliasPrimInteger_);
        method_ = new StandardMethod(aliasRotateLeft, params_, aliasPrimInteger_, false, MethodModifier.STATIC,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimLong_,aliasPrimLong_);
        method_ = new StandardMethod(aliasRotateLeft, params_, aliasPrimLong_, false, MethodModifier.STATIC,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimInteger_,aliasPrimInteger_);
        method_ = new StandardMethod(aliasRotateRight, params_, aliasPrimInteger_, false, MethodModifier.STATIC,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimLong_,aliasPrimLong_);
        method_ = new StandardMethod(aliasRotateRight, params_, aliasPrimLong_, false, MethodModifier.STATIC,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimLong_,aliasPrimLong_);
        method_ = new StandardMethod(aliasLe, params_, aliasPrimBoolean_, false, MethodModifier.STATIC,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimLong_,aliasPrimLong_);
        method_ = new StandardMethod(aliasGe, params_, aliasPrimBoolean_, false, MethodModifier.STATIC,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimLong_,aliasPrimLong_);
        method_ = new StandardMethod(aliasLt, params_, aliasPrimBoolean_, false, MethodModifier.STATIC,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimLong_,aliasPrimLong_);
        method_ = new StandardMethod(aliasGt, params_, aliasPrimBoolean_, false, MethodModifier.STATIC,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimDouble_,aliasPrimDouble_);
        method_ = new StandardMethod(aliasLe, params_, aliasPrimBoolean_, false, MethodModifier.STATIC,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimDouble_,aliasPrimDouble_);
        method_ = new StandardMethod(aliasGe, params_, aliasPrimBoolean_, false, MethodModifier.STATIC,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimDouble_,aliasPrimDouble_);
        method_ = new StandardMethod(aliasLt, params_, aliasPrimBoolean_, false, MethodModifier.STATIC,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimDouble_,aliasPrimDouble_);
        method_ = new StandardMethod(aliasGt, params_, aliasPrimBoolean_, false, MethodModifier.STATIC,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimLong_);
        method_ = new StandardMethod(aliasRandom, params_, aliasPrimLong_, false, MethodModifier.STATIC,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasRandom, params_, aliasPrimDouble_, false, MethodModifier.STATIC,std_);
        methods_.put(method_.getId(), method_);
    }
    public static ResultErrorStd invokeStdMethod(Analyzable _cont, ClassMethodId _method, Argument... _args) {
        ResultErrorStd result_ = new ResultErrorStd();
        Struct[] args_ = getObjects(_args);
        String name_ = _method.getConstraints().getName();
        StringList paramList_ = _method.getConstraints().getParametersTypes();
        LgNames lgNames_ = _cont.getStandards();
        AliasMath am_ = lgNames_.getMathRef();
        String divZero_ = lgNames_.getAliasDivisionZero();
        String aliasPrimInteger_ = lgNames_.getAliasPrimInteger();
        String aliasPrimLong_ = lgNames_.getAliasPrimLong();
        String aliasPrimFloat_ = lgNames_.getAliasPrimFloat();
        if (StringList.quickEq(name_, lgNames_.getAliasAbs())) {
            if (StringList.quickEq(paramList_.first(), aliasPrimLong_)) {
                result_.setResult(new LongStruct(Math.abs(((NumberStruct) args_[0]).longStruct())));
            } else {
                result_.setResult(new IntStruct(Math.abs(((NumberStruct) args_[0]).intStruct())));
            }
        } else if (StringList.quickEq(name_, lgNames_.getAliasMod())) {
            if (StringList.quickEq(paramList_.first(), aliasPrimLong_)) {
                long num_ = ((NumberStruct) args_[0]).longStruct();
                long den_ = ((NumberStruct) args_[1]).longStruct();
                if (den_ == 0) {
                    result_.setError(divZero_);
                } else {
                    result_.setResult(new LongStruct(Numbers.mod(num_, den_)));
                }
            } else {
                int num_ = ((NumberStruct) args_[0]).intStruct();
                int den_ = ((NumberStruct) args_[1]).intStruct();
                if (den_ == 0) {
                    result_.setError(divZero_);
                } else {
                    result_.setResult(new IntStruct(Numbers.mod(num_, den_)));
                }
            }
        } else if (StringList.quickEq(name_, lgNames_.getAliasQuot())) {
            if (StringList.quickEq(paramList_.first(), aliasPrimLong_)) {
                long num_ = ((NumberStruct) args_[0]).longStruct();
                long den_ = ((NumberStruct) args_[1]).longStruct();
                if (den_ == 0) {
                    result_.setError(divZero_);
                } else {
                    result_.setResult(new LongStruct(Numbers.quot(num_, den_)));
                }
            } else {
                int num_ = ((NumberStruct) args_[0]).intStruct();
                int den_ = ((NumberStruct) args_[1]).intStruct();
                if (den_ == 0) {
                    result_.setError(divZero_);
                } else {
                    result_.setResult(new IntStruct(Numbers.quot(num_, den_)));
                }
            }
        } else if (StringList.quickEq(name_, am_.aliasPlus)) {
            if (paramList_.size() == 1) {
                result_.setResult(args_[0]);
            } else {
                ClassArgumentMatching clArg_ = new ClassArgumentMatching(paramList_.first());
                result_.setResult(NumberStruct.calculateSum((NumberStruct) args_[0],(NumberStruct) args_[1], _cont, clArg_));
            }
        } else if (StringList.quickEq(name_, am_.aliasMinus)) {
            if (paramList_.size() == 1) {
                NumberStruct b_ = (NumberStruct) _args[0].getStruct();
                ClassArgumentMatching clArg_ = new ClassArgumentMatching(paramList_.first());
                int order_ = PrimitiveTypeUtil.getOrderClass(clArg_, _cont);
                if (order_ == PrimitiveTypeUtil.getOrderClass(aliasPrimInteger_, _cont)) {
                    result_.setResult(new IntStruct(-b_.intStruct()));
                } else if (order_ == PrimitiveTypeUtil.getOrderClass(aliasPrimLong_, _cont)) {
                    result_.setResult(new LongStruct(-b_.longStruct()));
                } else if (order_ == PrimitiveTypeUtil.getOrderClass(aliasPrimFloat_, _cont)) {
                    result_.setResult(new FloatStruct(-b_.floatStruct()));
                } else {
                    result_.setResult(new DoubleStruct(-b_.doubleStruct()));
                }
            } else {
                ClassArgumentMatching clArg_ = new ClassArgumentMatching(paramList_.first());
                result_.setResult(NumberStruct.calculateDiff((NumberStruct) args_[0], (NumberStruct) args_[1], _cont, clArg_));
            }
        } else if (StringList.quickEq(name_, am_.aliasMult)) {
            ClassArgumentMatching clArg_ = new ClassArgumentMatching(paramList_.first());
            result_.setResult(NumberStruct.calculateMult((NumberStruct) args_[0], (NumberStruct) args_[1], _cont, clArg_));
        } else if (StringList.quickEq(name_, am_.aliasBinMod)) {
            ClassArgumentMatching clArg_ = new ClassArgumentMatching(paramList_.first());
            Struct arg_ = NumberStruct.calculateMod((NumberStruct) args_[0], (NumberStruct) args_[1], _cont, clArg_);
            if (arg_ == NullStruct.NULL_VALUE) {
                result_.setError(divZero_);
            } else {
                result_.setResult(arg_);
            }
        } else if (StringList.quickEq(name_, am_.aliasBinQuot)) {
            ClassArgumentMatching clArg_ = new ClassArgumentMatching(paramList_.first());
            Struct arg_ = NumberStruct.calculateDiv((NumberStruct) args_[0], (NumberStruct) args_[1], _cont, clArg_);
            if (arg_ == NullStruct.NULL_VALUE) {
                result_.setError(divZero_);
            } else {
                result_.setResult(arg_);
            }
        } else if (StringList.quickEq(name_, am_.aliasNegBin)) {
            ClassArgumentMatching res_ = new ClassArgumentMatching(paramList_.first());
            if (res_.matchClass(aliasPrimInteger_)) {
                int left_ = _args[0].getInt();
                boolean[] bits_ = NumParsers.toBits(left_);
                int len_ = bits_.length;
                for (int i = 0; i<len_; i++) {
                    bits_[i] = !bits_[i];
                }
                result_.setResult(new IntStruct(NumParsers.toInt(bits_)));
            } else {
                long left_ = _args[0].getLong();
                boolean[] bits_ = NumParsers.toBits(left_);
                int len_ = bits_.length;
                for (int i = 0; i<len_; i++) {
                    bits_[i] = !bits_[i];
                }
                result_.setResult(new LongStruct(NumParsers.toLong(bits_)));
            }
        } else if (StringList.quickEq(name_, am_.aliasNeg)) {
            Boolean left_ = ((BooleanStruct)_args[0].getStruct()).getInstance();
            result_.setResult(new BooleanStruct(!left_));
        } else if (StringList.quickEq(name_, am_.aliasAnd)) {
            ClassArgumentMatching clArg_ = new ClassArgumentMatching(paramList_.first());
            result_.setResult(NumberStruct.calculateAnd(args_[0], args_[1], _cont, clArg_));
        } else if (StringList.quickEq(name_, am_.aliasOr)) {
            ClassArgumentMatching clArg_ = new ClassArgumentMatching(paramList_.first());
            result_.setResult(NumberStruct.calculateOr(args_[0], args_[1], _cont, clArg_));
        } else if (StringList.quickEq(name_, am_.aliasXor)) {
            ClassArgumentMatching clArg_ = new ClassArgumentMatching(paramList_.first());
            result_.setResult(NumberStruct.calculateXor(args_[0], args_[1], _cont, clArg_));
        } else if (StringList.quickEq(name_, am_.aliasLt)) {
            result_.setResult(NumberStruct.quickCalculateLowerNb(args_[0], args_[1]));
        } else if (StringList.quickEq(name_, am_.aliasGt)) {
            result_.setResult(NumberStruct.quickCalculateGreaterNb(args_[0], args_[1]));
        } else if (StringList.quickEq(name_, am_.aliasLe)) {
            if (args_[0].sameReference(args_[1])) {
                result_.setResult(new BooleanStruct(true));
            } else {
                result_.setResult(NumberStruct.quickCalculateLowerNb(args_[0], args_[1]));
            }
        } else if (StringList.quickEq(name_, am_.aliasGe)) {
            if (args_[0].sameReference(args_[1])) {
                result_.setResult(new BooleanStruct(true));
            } else {
                result_.setResult(NumberStruct.quickCalculateGreaterNb(args_[0], args_[1]));
            }
        } else if (StringList.quickEq(name_, am_.aliasShiftLeft)) {
            ClassArgumentMatching clArg_ = new ClassArgumentMatching(paramList_.first());
            result_.setResult(NumberStruct.calculateShiftLeft((NumberStruct) args_[0], (NumberStruct) args_[1], _cont, clArg_));
        } else if (StringList.quickEq(name_, am_.aliasShiftRight)) {
            ClassArgumentMatching clArg_ = new ClassArgumentMatching(paramList_.first());
            result_.setResult(NumberStruct.calculateShiftRight((NumberStruct) args_[0], (NumberStruct) args_[1], _cont, clArg_));
        } else if (StringList.quickEq(name_, am_.aliasBitShiftLeft)) {
            ClassArgumentMatching clArg_ = new ClassArgumentMatching(paramList_.first());
            result_.setResult(NumberStruct.calculateBitShiftLeft((NumberStruct) args_[0], (NumberStruct) args_[1], _cont, clArg_));
        } else if (StringList.quickEq(name_, am_.aliasBitShiftRight)) {
            ClassArgumentMatching clArg_ = new ClassArgumentMatching(paramList_.first());
            result_.setResult(NumberStruct.calculateBitShiftRight((NumberStruct) args_[0], (NumberStruct) args_[1], _cont, clArg_));
        } else if (StringList.quickEq(name_, am_.aliasRotateLeft)) {
            ClassArgumentMatching clArg_ = new ClassArgumentMatching(paramList_.first());
            result_.setResult(NumberStruct.calculateRotateLeft((NumberStruct) args_[0], (NumberStruct) args_[1], _cont, clArg_));
        } else if (StringList.quickEq(name_, am_.aliasRotateRight)) {
            ClassArgumentMatching clArg_ = new ClassArgumentMatching(paramList_.first());
            result_.setResult(NumberStruct.calculateRotateRight((NumberStruct) args_[0], (NumberStruct) args_[1], _cont, clArg_));
        }
        return result_;
    }
    static Struct[] getObjects(Argument... _args) {
        int len_ = _args.length;
        Struct[] classes_ = new Struct[len_];
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            classes_[i] = _args[i].getStruct();
        }
        return classes_;
    }
    public String getAliasAbs() {
        return aliasAbs;
    }
    public void setAliasAbs(String _aliasAbs) {
        aliasAbs = _aliasAbs;
    }
    public String getAliasQuot() {
        return aliasQuot;
    }
    public void setAliasQuot(String _aliasQuot) {
        aliasQuot = _aliasQuot;
    }
    public String getAliasMod() {
        return aliasMod;
    }
    public void setAliasMod(String _aliasMod) {
        aliasMod = _aliasMod;
    }
    public String getAliasMath() {
        return aliasMath;
    }
    public void setAliasMath(String _aliasMath) {
        aliasMath = _aliasMath;
    }
    public String getAliasBinQuot() {
        return aliasBinQuot;
    }
    public void setAliasBinQuot(String _aliasBinQuot) {
        aliasBinQuot = _aliasBinQuot;
    }
    public String getAliasBinMod() {
        return aliasBinMod;
    }
    public void setAliasBinMod(String _aliasBinMod) {
        aliasBinMod = _aliasBinMod;
    }
    public String getAliasPlus() {
        return aliasPlus;
    }
    public void setAliasPlus(String _aliasPlus) {
        aliasPlus = _aliasPlus;
    }
    public String getAliasMinus() {
        return aliasMinus;
    }
    public void setAliasMinus(String _aliasMinus) {
        aliasMinus = _aliasMinus;
    }
    public String getAliasMult() {
        return aliasMult;
    }
    public void setAliasMult(String _aliasMult) {
        aliasMult = _aliasMult;
    }
    public String getAliasAnd() {
        return aliasAnd;
    }
    public void setAliasAnd(String _aliasAnd) {
        aliasAnd = _aliasAnd;
    }
    public String getAliasOr() {
        return aliasOr;
    }
    public void setAliasOr(String _aliasOr) {
        aliasOr = _aliasOr;
    }
    public String getAliasXor() {
        return aliasXor;
    }
    public void setAliasXor(String _aliasXor) {
        aliasXor = _aliasXor;
    }
    public String getAliasNegBin() {
        return aliasNegBin;
    }
    public void setAliasNegBin(String _aliasNegBin) {
        aliasNegBin = _aliasNegBin;
    }
    public String getAliasNeg() {
        return aliasNeg;
    }
    public void setAliasNeg(String _aliasNeg) {
        aliasNeg = _aliasNeg;
    }
    public String getAliasLt() {
        return aliasLt;
    }
    public void setAliasLt(String _aliasLt) {
        aliasLt = _aliasLt;
    }
    public String getAliasGt() {
        return aliasGt;
    }
    public void setAliasGt(String _aliasGt) {
        aliasGt = _aliasGt;
    }
    public String getAliasLe() {
        return aliasLe;
    }
    public void setAliasLe(String _aliasLe) {
        aliasLe = _aliasLe;
    }
    public String getAliasGe() {
        return aliasGe;
    }
    public void setAliasGe(String _aliasGe) {
        aliasGe = _aliasGe;
    }
    public String getAliasShiftLeft() {
        return aliasShiftLeft;
    }
    public void setAliasShiftLeft(String _aliasShiftLeft) {
        aliasShiftLeft = _aliasShiftLeft;
    }
    public String getAliasShiftRight() {
        return aliasShiftRight;
    }
    public void setAliasShiftRight(String _aliasShiftRight) {
        aliasShiftRight = _aliasShiftRight;
    }
    public String getAliasBitShiftLeft() {
        return aliasBitShiftLeft;
    }
    public void setAliasBitShiftLeft(String _aliasBitShiftLeft) {
        aliasBitShiftLeft = _aliasBitShiftLeft;
    }
    public String getAliasBitShiftRight() {
        return aliasBitShiftRight;
    }
    public void setAliasBitShiftRight(String _aliasBitShiftRight) {
        aliasBitShiftRight = _aliasBitShiftRight;
    }
    public String getAliasRotateLeft() {
        return aliasRotateLeft;
    }
    public void setAliasRotateLeft(String _aliasRotateLeft) {
        aliasRotateLeft = _aliasRotateLeft;
    }
    public String getAliasRotateRight() {
        return aliasRotateRight;
    }
    public void setAliasRotateRight(String _aliasRotateRight) {
        aliasRotateRight = _aliasRotateRight;
    }

    public void setAliasRandom(String _aliasRandom) {
        aliasRandom = _aliasRandom;
    }

}
