package code.expressionlanguage.stds;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.structs.EnumerableStruct;
import code.expressionlanguage.structs.ErrorStruct;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;

public final class ApplyCoreMethodUtil {

    private ApplyCoreMethodUtil() {
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
