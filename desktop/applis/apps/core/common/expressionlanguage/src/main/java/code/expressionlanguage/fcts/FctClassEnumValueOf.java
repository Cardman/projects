package code.expressionlanguage.fcts;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.ClassCategory;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.opers.ExecInvokingOperation;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.structs.ClassMetaInfo;
import code.expressionlanguage.structs.Struct;

public final class FctClassEnumValueOf extends FctReflection {
    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        ClassMetaInfo instanceClass_ = (ClassMetaInfo) _instance;
        ExecRootBlock r_ = instanceClass_.getFormatted().getRootBlock();
        ClassCategory category_ = instanceClass_.getCategory();
        Struct arg_ = _firstArgs.getArgumentWrappers().get(0).getValue().getStruct();
        Argument clArg_ = new Argument(arg_);
        return new ArgumentWrapper(ExecInvokingOperation.tryGetEnumValue(_exit, _cont, r_, category_, clArg_, _stackCall).getStruct());
    }
}
