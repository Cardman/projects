package code.expressionlanguage.stds;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.DisplayedStrings;
import code.expressionlanguage.exec.ExecHelper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.annotation.ExportAnnotationUtil;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.structs.EnumerableStruct;
import code.expressionlanguage.structs.ErrorStruct;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;
import code.util.core.StringUtil;

public final class ApplyCoreMethodUtil {

    private ApplyCoreMethodUtil() {
    }

    public static ResultErrorStd invokeBase(ContextEl _cont, ClassMethodId _method, Struct _struct, Argument[] _args, StackCall _stackCall) {
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
