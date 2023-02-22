package code.expressionlanguage.guicompos.stds;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.guicompos.EnabledActionStruct;
import code.expressionlanguage.stds.StdCaller;
import code.expressionlanguage.structs.Struct;
import code.gui.events.AbsAdvActionListener;
import code.gui.initialize.AbsCompoFactory;

public final class FctActionWrap implements StdCaller {
    private final String className;
    private final AbsCompoFactory compoFactory;
    public FctActionWrap(String _cl, AbsCompoFactory _comp) {
        className = _cl;
        compoFactory = _comp;
    }
    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        Struct str_ = ArgumentListCall.toStr(_firstArgs.getArgumentWrappers().get(0).getValue());
        if (str_ instanceof AbsAdvActionListener) {
            return new ArgumentWrapper(new EnabledActionStruct(className,str_,compoFactory.wrap((AbsAdvActionListener)str_)));
        }
        return new ArgumentWrapper(null);
    }
}
