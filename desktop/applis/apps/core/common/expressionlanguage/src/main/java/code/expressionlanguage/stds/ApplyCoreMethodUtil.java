package code.expressionlanguage.stds;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.DisplayedStrings;
import code.expressionlanguage.exec.ExecHelper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.annotation.ExportAnnotationUtil;
import code.expressionlanguage.exec.calls.util.CustomFoundExc;
import code.expressionlanguage.exec.opers.ExecInvokingOperation;
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
