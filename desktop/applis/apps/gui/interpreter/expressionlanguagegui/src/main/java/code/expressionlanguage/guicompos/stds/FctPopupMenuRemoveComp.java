package code.expressionlanguage.guicompos.stds;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.guicompos.PopupStruct;
import code.expressionlanguage.stds.StdCaller;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;

public final class FctPopupMenuRemoveComp implements StdCaller {
    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        PopupStruct inst_ = (PopupStruct) _instance;
        inst_.remove(_firstArgs.getArgumentWrappers().get(0).getValue().getStruct());
        return new ArgumentWrapper(NullStruct.NULL_VALUE);
    }
}
