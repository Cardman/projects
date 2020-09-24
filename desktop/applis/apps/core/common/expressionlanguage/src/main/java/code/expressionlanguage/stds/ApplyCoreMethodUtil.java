package code.expressionlanguage.stds;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.AnaApplyCoreMethodUtil;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.exec.annotation.ExportAnnotationUtil;
import code.expressionlanguage.exec.types.ExecClassArgumentMatching;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.functionid.ConstructorId;
import code.expressionlanguage.exec.opers.*;
import code.expressionlanguage.structs.*;
import code.util.*;

public final class ApplyCoreMethodUtil {

    private ApplyCoreMethodUtil() {
    }

    public static ResultErrorStd invokeBase(ContextEl _cont, ClassMethodId _method, Struct _struct, Argument[] _args) {
        Struct[] args_ = AnaApplyCoreMethodUtil.getObjects(_args);
        String type_ = _method.getClassName();
        LgNames lgNames_ = _cont.getStandards();
        String stringBuilderType_ = lgNames_.getAliasStringBuilder();
        String mathType_ = lgNames_.getAliasMath();
        String stringType_ = lgNames_.getAliasString();
        String replType_ = lgNames_.getAliasReplacement();
        if (StringList.quickEq(type_, lgNames_.getAliasResources())) {
            return processResources(_cont, _method, args_);
        }
        if (StringList.quickEq(type_, lgNames_.getAliasObjectsUtil())) {
            return processObjectsUtil(_cont, _method, args_);
        }
        if (StringList.quickEq(type_, replType_)) {
            ResultErrorStd result_ = new ResultErrorStd();
            ReplacementStruct.calculate(_cont, result_, _method, _struct);
            return result_;
        }
        if (StringList.quickEq(type_, stringType_)
                || StringList.quickEq(type_, lgNames_.getAliasCharSequence())) {
            return AliasCharSequence.invokeStdMethod(_cont, _method, _struct, _args);
        }
        if (StringList.quickEq(type_, lgNames_.getAliasStackTraceElement())) {
            return AliasStackTraceElement.invokeMethod(_cont, _method, _struct);
        }
        if (StringList.quickEq(type_, lgNames_.getAliasError())) {
            return processError(_cont, _method, _struct, args_);
        }
        if (StringList.quickEq(type_, mathType_)) {
            return AliasMath.invokeStdMethod(_cont, _method, _args);
        }
        if (StringList.quickEq(type_, lgNames_.getAliasBoolean())) {
            ResultErrorStd result_ = new ResultErrorStd();
            AliasNumber.processBoolean(_cont, result_, _method, _struct, args_);
            return result_;
        }
        if (StringList.quickEq(type_, lgNames_.getAliasCharacter())) {
            ResultErrorStd result_ = new ResultErrorStd();
            AliasNumber.processCharacter(_cont, result_, _method, _struct, args_);
            return result_;
        }
        if (StringList.quickEq(type_, lgNames_.getAliasByte())
                || StringList.quickEq(type_, lgNames_.getAliasShort())
                || StringList.quickEq(type_, lgNames_.getAliasInteger())
                || StringList.quickEq(type_, lgNames_.getAliasLong())
                || StringList.quickEq(type_, lgNames_.getAliasFloat())
                || StringList.quickEq(type_, lgNames_.getAliasDouble())) {
            ResultErrorStd result_ = new ResultErrorStd();
            AliasNumber.processNumbers(_cont, result_, _method, _struct, type_, args_);
            return result_;
        }
        if (StringList.quickEq(type_, lgNames_.getAliasNumber())) {
            ResultErrorStd result_ = new ResultErrorStd();
            AliasNumber.processNumber(_cont, result_, _method, _struct, args_);
            return result_;
        }
        String stringUtil_ = lgNames_.getAliasStringUtil();
        if (StringList.quickEq(type_, stringUtil_)) {
            ResultErrorStd result_ = new ResultErrorStd();
            Argument a_ = new Argument(args_[0]);
            a_ = ExecOperationNode.processString(a_,_cont);
            if (_cont.getCallingState() == null) {
                result_.setResult(a_.getStruct());
            }
            return result_;
        }
        if (StringList.quickEq(type_, stringBuilderType_)) {
            return AliasCharSequence.invokeMethod(_cont, _method, _struct, _args);
        }
        AliasReflection ref_ = lgNames_.getReflect();
        if (StringList.quickEq(type_, ref_.getAliasAnnotationType())) {
            ResultErrorStd result_ = new ResultErrorStd();
            DisplayedStrings dis_ = lgNames_.getDisplayedStrings();
            result_.setResult(new StringStruct(ExportAnnotationUtil.exportAnnotation(
                    dis_.getInfinity(),
                    dis_.getNan(),
                    dis_.getExponent(),args_[0])));
            return result_;
        }
        if (StringList.quickEq(type_, ref_.getAliasField())) {
            return AliasReflection.invokeFieldInfo(_cont, _method, _struct);
        }
        if (StringList.quickEq(type_, ref_.getAliasMethod())) {
            return AliasReflection.invokeMethodInfo(_cont, _method, _struct, args_);
        }
        if (StringList.quickEq(type_, ref_.getAliasClassType())) {
            return AliasReflection.invokeClassInfo(_cont, _method, _struct, args_);
        }
        if (StringList.quickEq(type_, ref_.getAliasConstructor())) {
            return AliasReflection.invokeCtorInfo(_cont, _struct, _method);
        }
        return lgNames_.getOtherResult(_cont, _struct, _method, args_);
    }

    public static ResultErrorStd instanceBase(ContextEl _cont, ConstructorId _method, Argument[] _args) {
        Struct[] args_ = AnaApplyCoreMethodUtil.getObjects(_args);
        String type_ = _method.getName();
        LgNames lgNames_ = _cont.getStandards();
        String stringBuilderType_ = lgNames_.getAliasStringBuilder();
        ResultErrorStd result_ = new ResultErrorStd();
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
            AliasCharSequence.instantiateString(lgNames_, result_, _method, _cont, args_);
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
        if (StringList.quickEq(type_, stringBuilderType_)) {
            AliasCharSequence.instantiateStringBuilder(_cont, result_, _method, args_);
            return result_;
        }
        result_ = lgNames_.getOtherResult(_cont, _method, args_);
        return result_;
    }

    public static ResultErrorStd getOtherResultBase(ContextEl _cont, ClassMethodId _method, Struct[] _args) {
        ResultErrorStd result_ = new ResultErrorStd();

        String name_ = _method.getConstraints().getName();
        LgNames lgNames_ = _cont.getStandards();
        if (StringList.quickEq(name_, lgNames_.getAliasName())) {
            Struct str_ = _args[0];
            if (!(str_ instanceof EnumerableStruct)) {
                _cont.setException(new ErrorStruct(_cont,lgNames_.getAliasNullPe()));
            } else {
                EnumerableStruct en_ = (EnumerableStruct) str_;
                result_.setResult(new StringStruct(en_.getName()));
            }
        } else {
            Struct str_ = _args[0];
            if (!(str_ instanceof EnumerableStruct)) {
                _cont.setException(new ErrorStruct(_cont,lgNames_.getAliasNullPe()));
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

    private static ResultErrorStd processResources(ContextEl _cont, ClassMethodId _method, Struct[] args_) {
        ResultErrorStd result_ = new ResultErrorStd();
        LgNames lgNames_ = _cont.getStandards();
        String name_ = _method.getConstraints().getName();
        if (StringList.quickEq(name_, lgNames_.getAliasReadResourcesNamesLength())) {
            result_.setResult(ResourcesStruct.getResourceNamesLength(_cont));
        } else if (StringList.quickEq(name_, lgNames_.getAliasReadResourcesIndex())) {
            result_.setResult(ResourcesStruct.getResourceIndex(_cont,args_[0]));
        } else if (StringList.quickEq(name_, lgNames_.getAliasReadResourcesNames())) {
            result_.setResult(ResourcesStruct.getResourceNames(_cont));
        } else {
            result_.setResult(ResourcesStruct.getResource(_cont, NumParsers.getString(args_[0])));
        }
        return result_;
    }

    private static ResultErrorStd processObjectsUtil(ContextEl _cont, ClassMethodId _method, Struct[] args_) {
        ResultErrorStd result_ = new ResultErrorStd();
        LgNames lgNames_ = _cont.getStandards();
        String name_ = _method.getConstraints().getName();
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

    private static ResultErrorStd processError(ContextEl _cont, ClassMethodId _method, Struct _struct, Struct[] args_) {
        LgNames lgNames_ = _cont.getStandards();
        ResultErrorStd result_ = new ResultErrorStd();
        String name_ = _method.getConstraints().getName();
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

    public static ResultErrorStd newInstance(ContextEl _cont, ConstructorId _method, Argument... _args) {
        LgNames lgNames_ = _cont.getStandards();
        return lgNames_.instance(_cont,_method,_args);
    }

    public static Struct defaultInstance(ContextEl _conf, String _id, CustList<Argument> _firstArgs) {
        LgNames stds_ = _conf.getStandards();
        Struct previous_ = NullStruct.NULL_VALUE;
        if (!_firstArgs.isEmpty()) {
            previous_ = _firstArgs.first().getStruct();
        }
        byte cast_ = ExecClassArgumentMatching.getPrimitiveWrapCast(_id, stds_);
        if (cast_ > 0) {
            return NumParsers.convertToNumber(cast_,previous_);
        }
        String aliasBoolean_ = stds_.getAliasBoolean();
        if (StringList.quickEq(aliasBoolean_, _id)) {
            return NumParsers.convertToBoolean(previous_);
        }
        String aliasString_ = stds_.getAliasString();
        String aliasStringBuilder_ = stds_.getAliasStringBuilder();
        if (StringList.quickEq(aliasString_, _id)) {
            return NumParsers.getString(previous_);
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

    public static StringStruct getStringOfObjectBase(ContextEl _cont, Struct _arg) {
        String str_;
        if (_arg instanceof EnumerableStruct) {
            str_ = ((EnumerableStruct)_arg).getName();
        } else {
            str_ =  Argument.getNull(_arg).getClassName(_cont);
        }
        return new StringStruct(str_);
    }

}
