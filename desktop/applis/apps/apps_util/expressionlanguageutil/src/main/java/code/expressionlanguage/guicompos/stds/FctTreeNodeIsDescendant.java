package code.expressionlanguage.guicompos.stds;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.guicompos.TreeNodeStruct;
import code.expressionlanguage.stds.StdCaller;
import code.expressionlanguage.structs.Struct;

public final class FctTreeNodeIsDescendant implements StdCaller {
    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        return new ArgumentWrapper(((TreeNodeStruct)_instance).isDescendantMethod(_firstArgs.getArgumentWrappers().get(0).getValue()));
    }
}
