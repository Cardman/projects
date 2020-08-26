package code.expressionlanguage.stds;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.AnaApplyCoreMethodUtil;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.exec.Classes;
import code.expressionlanguage.exec.ErrorType;
import code.expressionlanguage.exec.blocks.ExecBlock;
import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.expressionlanguage.exec.blocks.ExecOverridableBlock;
import code.expressionlanguage.exec.calls.util.CustomFoundMethod;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.util.ExecOverrideInfo;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.functionid.ConstructorId;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.inherits.ClassArgumentMatching;
import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.expressionlanguage.exec.opers.*;
import code.expressionlanguage.structs.*;
import code.maths.montecarlo.AbMonteCarlo;
import code.maths.montecarlo.AbstractGenerator;
import code.util.*;

public final class ApplyCoreMethodUtil {

    private ApplyCoreMethodUtil() {
    }

    public static ResultErrorStd invokeBase(ContextEl _cont, ClassMethodId _method, Struct _struct, Argument[] _args) {
        ResultErrorStd result_;
        Struct[] args_ = AnaApplyCoreMethodUtil.getObjects(_args);
        String type_ = _method.getClassName();
        LgNames lgNames_ = _cont.getStandards();
        String stringBuilderType_ = lgNames_.getAliasStringBuilder();
        result_ = invokeStdMethod(_cont, _method, _struct, _args);
        if (result_.getResult() != null) {
            return result_;
        }
        processError(_cont,result_);
        if (_cont.callsOrException()) {
            return result_;
        }
        if (StringList.quickEq(type_, lgNames_.getAliasObjectsUtil())) {
            Struct inst_ = args_[0];
            if (!(inst_ instanceof WithParentStruct)) {
                result_.setResult(NullStruct.NULL_VALUE);
                return result_;
            }
            WithParentStruct i_ = (WithParentStruct) inst_;
            Struct par_ = args_[1];
            if (!StringList.quickEq(i_.getParentClassName(),par_.getClassName(_cont))) {
                result_.setResult(NullStruct.NULL_VALUE);
                return result_;
            }
            if (_cont.getInitializingTypeInfos().isContainedSensibleFields(i_)) {
                _cont.getInitializingTypeInfos().failInitEnums();
                return result_;
            }
            i_.setParent(par_);
            result_.setResult(NullStruct.NULL_VALUE);
            return result_;
        }
        String mathType_ = lgNames_.getAliasMath();
        String stringUtil_ = lgNames_.getAliasStringUtil();
        if (StringList.quickEq(type_, stringUtil_)) {
            Argument a_ = new Argument(args_[0]);
            a_ = ExecOperationNode.processString(a_,_cont);
            if (_cont.getCallingState() == null) {
                result_.setResult(a_.getStruct());
            }
            return result_;
        }
        if (StringList.quickEq(type_, mathType_)) {
            if (_cont.getInitializingTypeInfos().isInitEnums()) {
                _cont.getInitializingTypeInfos().failInitEnums();
                return result_;
            }
            StringList paramList_ = _method.getConstraints().getParametersTypes();
            if (StringList.quickEq(_method.getConstraints().getName(), lgNames_.getAliasSeed())) {
                if (paramList_.isEmpty()) {
                    Struct seed_ = _cont.getSeed();
                    result_.setResult(seed_);
                    return result_;
                }
                _cont.setSeed(_args[0].getStruct());
                result_.setResult(NullStruct.NULL_VALUE);
                return result_;
            }
            if (paramList_.isEmpty()) {
                return random(_cont, result_);
            }
            return randomParam(_cont, result_, args_);
        }
        if (StringList.quickEq(type_, stringBuilderType_)) {
            result_ = AliasCharSequence.invokeMethod(_cont, _method, _struct, _args);
            processError(_cont, result_);
            return result_;
        }
        result_ = AliasReflection.invokeMethod(_cont, _method, _struct, _args);
        if (result_.getResult() != null) {
            return result_;
        }
        processError(_cont,result_);
        if (_cont.callsOrException()) {
            return result_;
        }
        result_ = lgNames_.getOtherResult(_cont, _struct, _method, args_);
        processError(_cont,result_);
        return result_;
    }

    private static ResultErrorStd random(ContextEl _cont, ResultErrorStd _result) {
        LgNames lgNames_ = _cont.getStandards();
        Struct seed_ = _cont.getSeed();
        Argument argSeed_ = new Argument(seed_);
        ExecNamedFunctionBlock named_ = null;
        CustList<Argument> argsToPass_ = new CustList<Argument>();
        String cl_ = "";
        if (seed_ != NullStruct.NULL_VALUE
                && ExecTemplates.safeObject(lgNames_.getAliasSeedDoubleGenerator(), argSeed_, _cont) == ErrorType.NOTHING) {
            String argClassName_ = seed_.getClassName(_cont);
            Classes classes_ = _cont.getClasses();
            ExecOverrideInfo polymorphMeth_ = ExecInvokingOperation.polymorph(_cont, seed_, classes_.getSeedDoubleGenerator(), classes_.getSeedDoublePick());
            named_ = polymorphMeth_.getOverridableBlock();
            String className_ = polymorphMeth_.getClassName();
            className_ = ExecTemplates.getOverridingFullTypeByBases(argClassName_, className_, _cont);
            cl_ = className_;
        }
        if (named_ instanceof ExecOverridableBlock) {
            ExecOverridableBlock meth_ = (ExecOverridableBlock)named_;
            if (seed_ instanceof AbstractFunctionalInstance && meth_.isAbstractMethod()) {
                Argument fct_ = new Argument(((AbstractFunctionalInstance)seed_).getFunctional());
                _result.setResult(ExecInvokingOperation.prepareCallDyn(fct_,argsToPass_,_cont).getStruct());
                return _result;
            }
            _cont.setCallingState(new CustomFoundMethod(argSeed_,cl_,meth_,argsToPass_,null));
            return _result;
        }
        AbstractGenerator generator_ = lgNames_.getGenerator();
        _result.setResult(new DoubleStruct(generator_.pick()));
        return _result;
    }

    private static ResultErrorStd randomParam(ContextEl _cont, ResultErrorStd _result, Struct[] _args) {
        LgNames lgNames_ = _cont.getStandards();
        Struct seed_ = _cont.getSeed();
        Argument argSeed_ = new Argument(seed_);
        ExecNamedFunctionBlock named_ = null;
        CustList<Argument> argsToPass_ = new CustList<Argument>();
        String cl_ = "";
        if (seed_ != NullStruct.NULL_VALUE
                && ExecTemplates.safeObject(lgNames_.getAliasSeedGenerator(), argSeed_, _cont) == ErrorType.NOTHING) {
            String argClassName_ = seed_.getClassName(_cont);
            Classes classes_ = _cont.getClasses();
            ExecOverrideInfo polymorphMeth_ = ExecInvokingOperation.polymorph(_cont, seed_, classes_.getSeedGenerator(), classes_.getSeedPick());
            named_ = polymorphMeth_.getOverridableBlock();
            String className_ = polymorphMeth_.getClassName();
            className_ = ExecTemplates.getOverridingFullTypeByBases(argClassName_, className_, _cont);
            cl_ = className_;
            argsToPass_.add(new Argument(_args[0]));
        }
        if (named_ instanceof ExecOverridableBlock) {
            ExecOverridableBlock meth_ = (ExecOverridableBlock)named_;
            if (seed_ instanceof AbstractFunctionalInstance && meth_.isAbstractMethod()) {
                Argument fct_ = new Argument(((AbstractFunctionalInstance)seed_).getFunctional());
                _result.setResult(ExecInvokingOperation.prepareCallDyn(fct_,argsToPass_,_cont).getStruct());
                return _result;
            }
            _cont.setCallingState(new CustomFoundMethod(argSeed_,cl_,meth_,argsToPass_,null));
            return _result;
        }
        AbstractGenerator generator_ = lgNames_.getGenerator();
        _result.setResult(new LongStruct(AbMonteCarlo.randomLong(ClassArgumentMatching.convertToNumber(_args[0]).longStruct(),generator_)));
        return _result;
    }

    public static ResultErrorStd instanceBase(ContextEl _cont, ConstructorId _method, Argument[] _args) {
        ResultErrorStd result_;
        Struct[] args_ = AnaApplyCoreMethodUtil.getObjects(_args);
        String type_ = _method.getName();
        LgNames lgNames_ = _cont.getStandards();
        String stringBuilderType_ = lgNames_.getAliasStringBuilder();
        result_ = newInstanceStd(_cont, _method, _args);
        if (result_.getResult() != null) {
            return result_;
        }
        if (result_.getError() != null) {
            processError(_cont, result_);
            return result_;
        }
        if (StringList.quickEq(type_, stringBuilderType_)) {
            AliasCharSequence.instantiateStringBuilder(_cont, result_, _method, args_);
            processError(_cont, result_);
            return result_;
        }
        result_ = lgNames_.getOtherResult(_cont, _method, args_);
        processError(_cont,result_);
        return result_;
    }

    public static void processError(ContextEl _cont, ResultErrorStd _result) {
        if (_result.getError() != null) {
            String errMessage_ = _result.getErrorMessage();
            if (errMessage_ != null) {
                _cont.setException(new ErrorStruct(_cont,errMessage_,_result.getError()));
            } else {
                _cont.setException(new ErrorStruct(_cont,_result.getError()));
            }
        }
    }

    public static ResultErrorStd getOtherResultBase(ContextEl _cont, ClassMethodId _method, Struct[] _args) {
        ResultErrorStd result_ = new ResultErrorStd();

        String name_ = _method.getConstraints().getName();
        LgNames lgNames_ = _cont.getStandards();
        if (StringList.quickEq(name_, lgNames_.getAliasName())) {
            Struct str_ = _args[0];
            if (!(str_ instanceof EnumerableStruct)) {
                result_.setError(lgNames_.getAliasNullPe());
            } else {
                EnumerableStruct en_ = (EnumerableStruct) str_;
                result_.setResult(new StringStruct(en_.getName()));
            }
        } else {
            Struct str_ = _args[0];
            if (!(str_ instanceof EnumerableStruct)) {
                result_.setError(lgNames_.getAliasNullPe());
            } else {
                EnumerableStruct en_ = (EnumerableStruct) str_;
                result_.setResult(new IntStruct(en_.getOrdinal()));
            }
        }
        return result_;
    }
    public static Struct defaultMeta(ContextEl _conf, String _id, CustList<Argument> _firstArgs) {
        LgNames stds_ = _conf.getStandards();
        String aliasField_ = stds_.getAliasField();
        String aliasMethod_ = stds_.getAliasMethod();
        String aliasConstructor_ = stds_.getAliasConstructor();
        Struct previous_ = NullStruct.NULL_VALUE;
        if (!_firstArgs.isEmpty()) {
            previous_ = _firstArgs.first().getStruct();
        }
        if (StringList.quickEq(_id,aliasMethod_)) {
            return NumParsers.getMethod(previous_);
        }
        if (StringList.quickEq(_id,aliasConstructor_)) {
            return NumParsers.getCtor(previous_);
        }
        if (StringList.quickEq(_id,aliasField_)) {
            return NumParsers.getField(previous_);
        }
        return NumParsers.getClass(previous_);
    }

    public static ResultErrorStd invokeStdMethod(ContextEl _cont, ClassMethodId _method, Struct _struct, Argument... _args) {
        ResultErrorStd result_ = new ResultErrorStd();
        Struct[] args_ = AnaApplyCoreMethodUtil.getObjects(_args);
        String type_ = _method.getClassName();
        String name_ = _method.getConstraints().getName();
        LgNames lgNames_ = _cont.getStandards();
        String mathType_ = lgNames_.getAliasMath();
        String stringType_ = lgNames_.getAliasString();
        String replType_ = lgNames_.getAliasReplacement();
        if (StringList.quickEq(type_, lgNames_.getAliasResources())) {
            if (StringList.quickEq(name_, lgNames_.getAliasReadResourcesNamesLength())) {
                result_.setResult(ResourcesStruct.getResourceNamesLength(_cont));
            } else if (StringList.quickEq(name_, lgNames_.getAliasReadResourcesIndex())) {
                result_.setResult(ResourcesStruct.getResourceIndex(_cont,args_[0]));
            } else if (StringList.quickEq(name_, lgNames_.getAliasReadResourcesNames())) {
                result_.setResult(ResourcesStruct.getResourceNames(_cont));
            } else {
                result_.setResult(ResourcesStruct.getResource(_cont, AnaApplyCoreMethodUtil.getString(args_[0])));
            }
            return result_;
        }
        if (StringList.quickEq(type_, lgNames_.getAliasObjectsUtil())) {
            if (StringList.quickEq(name_, lgNames_.getAliasSameRef())) {
                result_.setResult(BooleanStruct.of(args_[0].sameReference(args_[1])));
                return result_;
            }
            if (StringList.quickEq(name_, lgNames_.getAliasGetParent())) {
                Struct arg_ = args_[0];
                Struct par_ = arg_.getParent();
                _cont.getInitializingTypeInfos().addSensibleField(arg_, par_);
                result_.setResult(par_);
                return result_;
            }
        }
        if (StringList.quickEq(type_, replType_)) {
            ReplacementStruct.calculate(_cont, result_, _method, _struct);
            return result_;
        }
        if (StringList.quickEq(type_, stringType_)
                || StringList.quickEq(type_, lgNames_.getAliasCharSequence())) {
            result_ = AliasCharSequence.invokeStdMethod(_cont, _method, _struct, _args);
            return result_;
        }
        if (StringList.quickEq(type_, lgNames_.getAliasStackTraceElement())) {
            return AliasStackTraceElement.invokeMethod(_cont, _method, _struct);
        }
        if (StringList.quickEq(type_, lgNames_.getAliasError())) {
            if (StringList.quickEq(name_, lgNames_.getAliasCurrentStack())) {
                ErroneousStruct err_;
                if (args_.length == 0) {
                    err_ = getError(_struct,_cont);
                    result_.setResult(err_.getStack());
                    return result_;
                }
                err_ = getError(args_[0],_cont);
                result_.setResult(err_.getFullStack());
                return result_;
            }
            if (StringList.quickEq(name_, lgNames_.getAliasGetMessage())) {
                ErroneousStruct err_ = getError(_struct,_cont);
                result_.setResult(err_.getMessage());
                return result_;
            }
            if (StringList.quickEq(name_, lgNames_.getAliasGetCause())) {
                ErroneousStruct err_ = getError(_struct,_cont);
                result_.setResult(err_.getCause());
                return result_;
            }
            ErroneousStruct err_;
            if (args_.length == 0) {
                err_ = getError(_struct,_cont);
                result_.setResult(err_.getDisplayedString(_cont));
                return result_;
            }
            err_ = getError(args_[0],_cont);
            result_.setResult(new StringStruct(err_.getStringRep(_cont,err_.getFullStack().getInstance())));
            return result_;
        }
        if (StringList.quickEq(type_, mathType_)) {
            return AliasMath.invokeStdMethod(_cont, _method, _args);
        }
        AliasNumber.calculateNumber(_cont, result_, _method, _struct, args_);
        return result_;
    }
    public static ResultErrorStd newInstance(ContextEl _cont, ConstructorId _method, Argument... _args) {
        LgNames lgNames_ = _cont.getStandards();
        return lgNames_.instance(_cont,_method,_args);
    }

    public static ResultErrorStd newInstanceStd(ContextEl _cont, ConstructorId _method, Argument... _args) {
        ResultErrorStd result_ = new ResultErrorStd();
        Struct[] args_ = AnaApplyCoreMethodUtil.getObjects(_args);
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
            ReplacementStruct.instantiate(result_, args_);
            return result_;
        }
        if (StringList.quickEq(type_, stringType_)) {
            AliasCharSequence.instantiateString(lgNames_, result_, _method, args_);
            return result_;
        }
        if (StringList.quickEq(type_, booleanType_)
                || StringList.quickEq(type_, charType_)
                || StringList.quickEq(type_, byteType_)
                || StringList.quickEq(type_, shortType_)
                || StringList.quickEq(type_, intType_)
                || StringList.quickEq(type_, longType_)
                || StringList.quickEq(type_, floatType_)
                || StringList.quickEq(type_, doubleType_)) {
            AliasNumber.instantiateNumber(_cont, result_, _method, args_);
            return result_;
        }
        return result_;
    }

    public static Struct defaultInstance(ContextEl _conf, String _id, CustList<Argument> _firstArgs) {
        LgNames stds_ = _conf.getStandards();
        Struct previous_ = NullStruct.NULL_VALUE;
        if (!_firstArgs.isEmpty()) {
            previous_ = _firstArgs.first().getStruct();
        }
        if (PrimitiveTypeUtil.isPureNumberClass(new ClassArgumentMatching(_id),_conf)) {
            return PrimitiveTypeUtil.convertToNumber(new ClassArgumentMatching(_id),previous_,stds_);
        }
        String aliasBoolean_ = stds_.getAliasBoolean();
        if (StringList.quickEq(aliasBoolean_, _id)) {
            return ClassArgumentMatching.convertToBoolean(previous_);
        }
        String aliasString_ = stds_.getAliasString();
        String aliasStringBuilder_ = stds_.getAliasStringBuilder();
        if (StringList.quickEq(aliasString_, _id)) {
            return AnaApplyCoreMethodUtil.getString(previous_);
        }
        if (StringList.quickEq(aliasStringBuilder_, _id)) {
            return NumParsers.getStrBuilder(previous_);
        }
        String aliasRepl_ = stds_.getAliasReplacement();
        if (StringList.quickEq(aliasRepl_, _id)) {
            return NumParsers.getReplacement(previous_);
        }
        return stds_.defaultInstance(_conf,_id).getStruct();
    }

    private static ErroneousStruct getError(Struct _err, ContextEl _cont) {
        if (_err instanceof ErroneousStruct) {
            return (ErroneousStruct) _err;
        }
        String null_ = _cont.getStandards().getAliasNullPe();
        return new ErrorStruct(_cont,null_);
    }

    public static DisplayableStruct getStringOfObjectBase(ContextEl _cont, Struct _arg) {
        String str_;
        if (_arg instanceof EnumerableStruct) {
            str_ = ((EnumerableStruct)_arg).getName();
        } else {
            str_ =  Argument.getNull(_arg).getClassName(_cont);
        }
        return new StringStruct(str_);
    }

}
