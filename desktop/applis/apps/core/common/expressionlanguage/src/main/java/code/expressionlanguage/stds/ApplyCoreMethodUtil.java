package code.expressionlanguage.stds;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.DisplayedStrings;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.exec.ExecHelper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.annotation.ExportAnnotationUtil;
import code.expressionlanguage.exec.calls.util.CustomFoundExc;
import code.expressionlanguage.exec.opers.ExecInvokingOperation;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.expressionlanguage.fcts.FctRange0;
import code.expressionlanguage.fcts.FctRange1;
import code.expressionlanguage.fcts.FctRange2;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.structs.*;
import code.util.CustList;
import code.util.core.StringUtil;

public final class ApplyCoreMethodUtil {

    private ApplyCoreMethodUtil() {
    }

    public static ResultErrorStd invokeBase(ContextEl _cont, ClassMethodId _method, Struct _struct, AbstractExiting _exit, Argument[] _args, StackCall _stackCall) {
        Struct[] args_ = ExecHelper.getObjects(_args);
        String type_ = _method.getClassName();
        LgNames lgNames_ = _cont.getStandards();
        String stringBuilderType_ = lgNames_.getContent().getCharSeq().getAliasStringBuilder();
        String mathType_ = lgNames_.getContent().getMathRef().getAliasMath();
        String stringType_ = lgNames_.getContent().getCharSeq().getAliasString();
        String replType_ = lgNames_.getContent().getCharSeq().getAliasReplacement();
        if (StringUtil.quickEq(type_, lgNames_.getContent().getCoreNames().getAliasResources())) {
            return processResources(_cont, _method, args_);
        }
        if (StringUtil.quickEq(type_, lgNames_.getContent().getCoreNames().getAliasObjectsUtil())) {
            return processObjectsUtil(_cont, _method, args_, _stackCall);
        }
        if (StringUtil.quickEq(type_, lgNames_.getContent().getCoreNames().getAliasRange())) {
            return processRange(_cont, _stackCall,_method,_struct,args_);
        }
        if (StringUtil.quickEq(type_, replType_)) {
            ResultErrorStd result_ = new ResultErrorStd();
            AliasCharSequenceType.calculate(_cont, result_, _method, _struct);
            return result_;
        }
        if (StringUtil.quickEq(type_, stringType_)
                || StringUtil.quickEq(type_, lgNames_.getContent().getCharSeq().getAliasCharSequence())) {
            return AliasCharSequenceType.invokeStdMethod(_cont, _method, _struct, _stackCall, _args);
        }
        if (StringUtil.quickEq(type_, lgNames_.getContent().getStackElt().getAliasStackTraceElement())) {
            return AliasStackTraceElementType.invokeMethod(_cont, _method, _struct, _stackCall);
        }
        if (StringUtil.quickEq(type_, lgNames_.getContent().getCoreNames().getAliasError())) {
            return processError(_cont, _method, _struct, args_, _stackCall);
        }
        if (StringUtil.quickEq(type_, mathType_)) {
            return AliasMathType.invokeStdMethod(_cont, _method, _stackCall, _args);
        }
        if (StringUtil.quickEq(type_, lgNames_.getContent().getNbAlias().getAliasBoolean())) {
            ResultErrorStd result_ = new ResultErrorStd();
            AliasNumberType.processBoolean(_cont, result_, _method, _struct, args_);
            return result_;
        }
        if (StringUtil.quickEq(type_, lgNames_.getContent().getNbAlias().getAliasCharacter())) {
            ResultErrorStd result_ = new ResultErrorStd();
            AliasNumberType.processCharacter(_cont, result_, _method, _struct, args_, _stackCall);
            return result_;
        }
        if (StringUtil.quickEq(type_, lgNames_.getContent().getNbAlias().getAliasNumber())) {
            ResultErrorStd result_ = new ResultErrorStd();
            AliasNumberType.processNumber(_cont, result_, _method, _struct, args_, _stackCall);
            return result_;
        }
        String stringUtil_ = lgNames_.getContent().getCoreNames().getAliasStringUtil();
        if (StringUtil.quickEq(type_, stringUtil_)) {
            ResultErrorStd result_ = new ResultErrorStd();
            Argument a_ = new Argument(args_[0]);
            a_ = ExecOperationNode.processString(a_,_cont, _stackCall);
            if (_stackCall.getCallingState() == null) {
                result_.setResult(a_.getStruct());
            }
            return result_;
        }
        if (StringUtil.quickEq(type_, stringBuilderType_)) {
            return AliasCharSequenceType.invokeMethod(_cont, _method, _struct, _stackCall, _args);
        }
        AliasReflection ref_ = lgNames_.getReflect();
        if (StringUtil.quickEq(type_, ref_.getAliasAnnotationType())) {
            ResultErrorStd result_ = new ResultErrorStd();
            DisplayedStrings dis_ = lgNames_.getDisplayedStrings();
            result_.setResult(new StringStruct(ExportAnnotationUtil.exportAnnotation(
                    dis_.getInfinity(),
                    dis_.getNan(),
                    dis_.getExponent(),args_[0])));
            return result_;
        }
        String aliasFct_ = lgNames_.getContent().getReflect().getAliasFct();
        if (StringUtil.quickEq(aliasFct_, type_)) {
            ResultErrorStd res_ = new ResultErrorStd();
            if (args_.length == 0) {
                if (StringUtil.quickEq(_method.getConstraints().getName(), _cont.getStandards().getContent().getReflect().getAliasMetaInfo())) {
                    res_.setResult(ExecInvokingOperation.getMetaInfo(new Argument(_struct), _cont, _stackCall).getStruct());
                    return res_;
                }
                res_.setResult(ExecInvokingOperation.getInstanceCall(new Argument(_struct), _cont, _stackCall).getStruct());
                return res_;
            }
            Argument instance_ = new Argument(args_[0]);
            Struct inst_ = instance_.getStruct();
            if (!(inst_ instanceof ArrayStruct)) {
                _stackCall.setCallingState(new CustomFoundExc(getNpe(_cont, _stackCall)));
                return res_;
            }
            ArrayStruct arr_ = (ArrayStruct) inst_;
            CustList<Argument> ar_ = arr_.listArgs();
            res_.setResult(ExecInvokingOperation.prepareCallDynReflect(new Argument(_struct), ar_, _cont, _stackCall).getStruct());
            return res_;
        }
        if (StringUtil.quickEq(type_, ref_.getAliasField())) {
            return AliasReflection.invokeFieldInfo(_cont, _method, _struct,args_, _stackCall);
        }
        if (StringUtil.quickEq(type_, ref_.getAliasMethod())) {
            return AliasReflection.invokeMethodInfo(_cont, _method, _struct, args_, _stackCall);
        }
        if (StringUtil.quickEq(type_, ref_.getAliasClassType())) {
            return AliasReflection.invokeClassInfo(_cont, _method, _struct, _exit, args_, _stackCall);
        }
        if (StringUtil.quickEq(type_, ref_.getAliasConstructor())) {
            return AliasReflection.invokeCtorInfo(_cont, _struct, _method,args_, _stackCall);
        }
        return lgNames_.getOtherResult(_stackCall, _cont, _struct, _method, args_);
    }

    public static Argument range(ContextEl _conf, StackCall _stack, Struct... _args) {
        if (_args.length == 3) {
            return FctRange2.rangeBoundsStep(_conf, _stack, _args[0], _args[1], _args[2]).getValue();
        }
        if (_args.length == 2) {
            return FctRange1.rangeBounds(_conf, _stack, _args[0], _args[1]).getValue();
        }
        return FctRange0.rangeUnlimit(_conf,_stack,_args[0]).getValue();
    }

    public static Argument rangeUnlimitStep(ContextEl _conf, StackCall _stack, Struct... _args) {
        int lower_ = NumParsers.convertToNumber(_args[0]).intStruct();
        if (lower_ < 0) {
            _stack.setCallingState(new CustomFoundExc(getBadIndex(_conf, getBeginMessage(lower_), _stack)));
            return Argument.createVoid();
        }
        int step_ = NumParsers.convertToNumber(_args[1]).intStruct();
        if (step_ == 0) {
            _stack.setCallingState(new CustomFoundExc(getDivideZero(_conf, _stack)));
            return Argument.createVoid();
        }
        return new Argument(new RangeStruct(lower_, -1,step_));
    }
    private static ErrorStruct getDivideZero(ContextEl _cont, StackCall _stackCall) {
        return new ErrorStruct(_cont, _cont.getStandards().getContent().getCoreNames().getAliasDivisionZero(), _stackCall);
    }

    private static ErrorStruct getBadIndex(ContextEl _context, String _message, StackCall _stackCall) {
        return new ErrorStruct(_context, _message, _context.getStandards().getContent().getCoreNames().getAliasBadIndex(), _stackCall);
    }

    private static String getBeginMessage(int _begin) {
        return StringUtil.concat(Long.toString(_begin), "<0");
    }

//    public static ResultErrorStd getOtherResultBase(ContextEl _cont, ClassMethodId _method, Struct[] _args, StackCall _stackCall) {
//        ResultErrorStd result_ = new ResultErrorStd();
//
//        String name_ = _method.getConstraints().getName();
//        LgNames lgNames_ = _cont.getStandards();
//        if (StringUtil.quickEq(name_, lgNames_.getContent().getCoreNames().getAliasName())) {
//            Struct str_ = _args[0];
//            if (!(str_ instanceof EnumerableStruct)) {
//                _stackCall.setCallingState(new CustomFoundExc(getNpe(_cont, _stackCall)));
//            } else {
//                EnumerableStruct en_ = (EnumerableStruct) str_;
//                result_.setResult(new StringStruct(en_.getName()));
//            }
//        } else {
//            Struct str_ = _args[0];
//            if (!(str_ instanceof EnumerableStruct)) {
//                _stackCall.setCallingState(new CustomFoundExc(getNpe(_cont, _stackCall)));
//            } else {
//                EnumerableStruct en_ = (EnumerableStruct) str_;
//                result_.setResult(new IntStruct(en_.getOrdinal()));
//            }
//        }
//        return result_;
//    }

    public static ErrorStruct getNpe(ContextEl _cont, StackCall _stackCall) {
        return new ErrorStruct(_cont, _cont.getStandards().getContent().getCoreNames().getAliasNullPe(), _stackCall);
    }

    private static ResultErrorStd processResources(ContextEl _cont, ClassMethodId _method, Struct[] _args) {
        ResultErrorStd result_ = new ResultErrorStd();
        LgNames lgNames_ = _cont.getStandards();
        String name_ = _method.getConstraints().getName();
        if (StringUtil.quickEq(name_, lgNames_.getContent().getCoreNames().getAliasReadResourcesNamesLength())) {
            result_.setResult(ResourcesStruct.getResourceNamesLength(_cont));
        } else if (StringUtil.quickEq(name_, lgNames_.getContent().getCoreNames().getAliasReadResourcesIndex())) {
            result_.setResult(ResourcesStruct.getResourceIndex(_cont,_args[0]));
        } else if (StringUtil.quickEq(name_, lgNames_.getContent().getCoreNames().getAliasReadResourcesNames())) {
            result_.setResult(ResourcesStruct.getResourceNames(_cont));
        } else {
            result_.setResult(ResourcesStruct.getResource(_cont, NumParsers.getString(_args[0])));
        }
        return result_;
    }

    private static ResultErrorStd processRange(ContextEl _cont,StackCall _stack, ClassMethodId _method, Struct _struct,Struct... _args) {
        ResultErrorStd result_ = new ResultErrorStd();
        LgNames lgNames_ = _cont.getStandards();
        String name_ = _method.getConstraints().getName();
        RangeStruct rangeStruct_ = NumParsers.convertToRange(_struct);
        if (StringUtil.quickEq(name_, lgNames_.getContent().getCoreNames().getAliasRangeLower())) {
            result_.setResult(new IntStruct(rangeStruct_.getLower()));
            return result_;
        }
        if (StringUtil.quickEq(name_, lgNames_.getContent().getCoreNames().getAliasRangeUpper())) {
            result_.setResult(new IntStruct(rangeStruct_.getUpper()));
            return result_;
        }
        if (StringUtil.quickEq(name_, lgNames_.getContent().getCoreNames().getAliasRangeUnlimitedStep())) {
            result_.setResult(rangeUnlimitStep(_cont,_stack,_args).getStruct());
            return result_;
        }
        result_.setResult(BooleanStruct.of(rangeStruct_.isUnlimited()));
        return result_;
    }
    private static ResultErrorStd processObjectsUtil(ContextEl _cont, ClassMethodId _method, Struct[] _args, StackCall _stackCall) {
        ResultErrorStd result_ = new ResultErrorStd();
        LgNames lgNames_ = _cont.getStandards();
        String name_ = _method.getConstraints().getName();
        if (StringUtil.quickEq(name_, lgNames_.getContent().getCoreNames().getAliasSameRef())) {
            result_.setResult(BooleanStruct.of(_args[0].sameReference(_args[1])));
            return result_;
        }
        if (StringUtil.quickEq(name_, lgNames_.getContent().getCoreNames().getAliasGetParent())) {
            Struct arg_ = _args[0];
            Struct par_ = arg_.getParent();
            _stackCall.getInitializingTypeInfos().addSensibleField(arg_, par_);
            result_.setResult(par_);
            return result_;
        }
        if (StringUtil.quickEq(name_, lgNames_.getContent().getCoreNames().getAliasGetFct())) {
            Struct arg_ = _args[0];
            if (arg_ instanceof AbstractFunctionalInstance) {
                Struct par_ = Argument.getNull(((AbstractFunctionalInstance)arg_).getFunctional());
                _stackCall.getInitializingTypeInfos().addSensibleField(arg_, par_);
                result_.setResult(par_);
                return result_;
            }
            result_.setResult(NullStruct.NULL_VALUE);
            return result_;
        }
        Struct inst_ = _args[0];
        if (!(inst_ instanceof WithParentStruct)) {
            result_.setResult(NullStruct.NULL_VALUE);
            return result_;
        }
        WithParentStruct i_ = (WithParentStruct) inst_;
        Struct par_ = _args[1];
        if (!StringUtil.quickEq(i_.getParentClassName(),par_.getClassName(_cont))) {
            result_.setResult(NullStruct.NULL_VALUE);
            return result_;
        }
        if (_stackCall.getInitializingTypeInfos().isContainedSensibleFields(i_)) {
            _stackCall.getInitializingTypeInfos().failInitEnums();
            return result_;
        }
        i_.setParent(par_);
        result_.setResult(NullStruct.NULL_VALUE);
        return result_;
    }

    private static ResultErrorStd processError(ContextEl _cont, ClassMethodId _method, Struct _struct, Struct[] _args, StackCall _stackCall) {
        LgNames lgNames_ = _cont.getStandards();
        ResultErrorStd result_ = new ResultErrorStd();
        String name_ = _method.getConstraints().getName();
        if (StringUtil.quickEq(name_, lgNames_.getContent().getCoreNames().getAliasErrorCurrentStack())) {
            ErroneousStruct err_;
            if (_args.length == 0) {
                err_ = getError(_struct,_cont, _stackCall);
                result_.setResult(err_.getStack());
                return result_;
            }
            err_ = getError(_args[0],_cont, _stackCall);
            result_.setResult(err_.getFullStack());
            return result_;
        }
        if (StringUtil.quickEq(name_, lgNames_.getContent().getCoreNames().getAliasGetMessage())) {
            ErroneousStruct err_ = getError(_struct,_cont, _stackCall);
            result_.setResult(err_.getMessage());
            return result_;
        }
        if (StringUtil.quickEq(name_, lgNames_.getContent().getCoreNames().getAliasGetCause())) {
            ErroneousStruct err_ = getError(_struct,_cont, _stackCall);
            result_.setResult(err_.getCause());
            return result_;
        }
        ErroneousStruct err_;
        if (_args.length == 0) {
            err_ = getError(_struct,_cont, _stackCall);
            result_.setResult(err_.getDisplayedString(_cont));
            return result_;
        }
        err_ = getError(_args[0],_cont, _stackCall);
        result_.setResult(new StringStruct(err_.getStringRep(_cont, err_.getFullStack())));
        return result_;
    }

    private static ErroneousStruct getError(Struct _err, ContextEl _cont, StackCall _stackCall) {
        if (_err instanceof ErroneousStruct) {
            return (ErroneousStruct) _err;
        }
        String null_ = _cont.getStandards().getContent().getCoreNames().getAliasNullPe();
        return new ErrorStruct(_cont,null_, _stackCall);
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
