package code.expressionlanguage.fcts;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.util.CustomFoundExc;
import code.expressionlanguage.exec.types.ExecPartTypeUtil;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.*;
import code.util.core.StringUtil;

public final class FctClassForName extends FctReflection {

    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        return forName(_exit, _cont, _firstArgs, _stackCall);
    }
    public static ArgumentWrapper forName(AbstractExiting _exit, ContextEl _cont, ArgumentListCall _firstArgs, StackCall _stackCall){
        LgNames lgNames_ = _cont.getStandards();
        Argument clArg_ = new Argument(_firstArgs.getArgumentWrappers().get(0).getValue().getStruct());
        Struct struct_ = clArg_.getStruct();
        if (!(struct_ instanceof StringStruct)) {
            _stackCall.setCallingState(new CustomFoundExc(getNpe(_cont, _stackCall)));
            return new ArgumentWrapper(NullStruct.NULL_VALUE);
        }
        String clDyn_ = ((StringStruct) struct_).getInstance();
        if (StringUtil.quickEq(clDyn_.trim(), _cont.getStandards().getContent().getCoreNames().getAliasVoid())) {
            return new ArgumentWrapper(ClassMetaInfo.getClassMetaInfo(_cont,clDyn_));
        }
        String res_ = ExecPartTypeUtil.correctClassPartsDynamic(clDyn_, _cont);
        if (res_.isEmpty()) {
            _stackCall.setCallingState(new CustomFoundExc(getClassIssue(_cont, clDyn_, lgNames_.getContent().getReflect().getAliasClassNotFoundError(), _stackCall)));
            return new ArgumentWrapper(NullStruct.NULL_VALUE);
        }
        if (BooleanStruct.isTrue(_firstArgs.getArgumentWrappers().last().getValue().getStruct()) && _exit.hasToExit(_stackCall, _cont.getClassBody(StringExpUtil.getIdFromAllTypes(res_)))) {
            return new ArgumentWrapper(NullStruct.NULL_VALUE);
        }
        return new ArgumentWrapper(ClassMetaInfo.getClassMetaInfo(_cont,res_));
    }

}
