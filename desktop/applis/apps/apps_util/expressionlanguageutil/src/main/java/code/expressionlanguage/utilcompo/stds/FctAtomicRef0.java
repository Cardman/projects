package code.expressionlanguage.utilcompo.stds;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.stds.StdCaller;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.utilcompo.AtomicRefStruct;

public final class FctAtomicRef0 implements StdCaller {
    private final String aliasAtomicRef;

    public FctAtomicRef0(String _aliasAtomicRef) {
        this.aliasAtomicRef = _aliasAtomicRef;
    }

    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        AtomicRefStruct std_ = new AtomicRefStruct(_cont.getCaller().newAtObj(), aliasAtomicRef);
        return new ArgumentWrapper(std_);
    }
}
