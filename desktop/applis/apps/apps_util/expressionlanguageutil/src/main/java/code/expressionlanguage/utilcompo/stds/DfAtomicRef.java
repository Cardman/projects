package code.expressionlanguage.utilcompo.stds;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.stds.DfInstancer;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.utilcompo.AtomicRefStruct;
import code.threads.AbstractAtomicRef;

public final class DfAtomicRef implements DfInstancer {
    private final String aliasAtomicRef;

    public DfAtomicRef(String _aliasAtomicBoolean) {
        this.aliasAtomicRef = _aliasAtomicBoolean;
    }

    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, ArgumentListCall _firstArgs, StackCall _stackCall) {
        AbstractAtomicRef<Struct> at_ = _cont.getCaller().newAtObj();
        AtomicRefStruct std_ = new AtomicRefStruct(at_, aliasAtomicRef);
        return new ArgumentWrapper(std_);
    }
}
