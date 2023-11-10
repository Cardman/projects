package code.expressionlanguage.utilcompo.stds;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.stds.DfInstancer;
import code.expressionlanguage.utilcompo.AtomicLongStruct;
import code.threads.AbstractAtomicLong;
import code.threads.AbstractThreadFactory;

public final class DfAtomicLong implements DfInstancer {
    private final AbstractThreadFactory infos;
    private final String aliasAtomicLong;

    public DfAtomicLong(AbstractThreadFactory _infos, String _aliasAtomicLong) {
        this.infos = _infos;
        this.aliasAtomicLong = _aliasAtomicLong;
    }

    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, ArgumentListCall _firstArgs, StackCall _stackCall) {
        AbstractAtomicLong at_ = infos.newAtomicLong();
        AtomicLongStruct std_ = new AtomicLongStruct(at_, aliasAtomicLong);
        return new ArgumentWrapper(std_);
    }
}
