package code.expressionlanguage.utilcompo.stds;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.stds.StdCaller;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.utilcompo.AtomicIntegerStruct;
import code.threads.AbstractAtomicInteger;
import code.threads.AbstractThreadFactory;

public final class FctAtomicInteger0 implements StdCaller {
    private final AbstractThreadFactory infos;
    private final String aliasAtomicInteger;

    public FctAtomicInteger0(AbstractThreadFactory _infos, String _aliasAtomicInteger) {
        this.infos = _infos;
        this.aliasAtomicInteger = _aliasAtomicInteger;
    }

    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        AbstractAtomicInteger at_ = infos.newAtomicInteger();
        AtomicIntegerStruct std_ = new AtomicIntegerStruct(at_, aliasAtomicInteger);
        return new ArgumentWrapper(std_);
    }
}
