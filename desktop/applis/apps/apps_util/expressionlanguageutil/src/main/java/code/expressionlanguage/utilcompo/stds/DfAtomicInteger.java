package code.expressionlanguage.utilcompo.stds;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.stds.DfInstancer;
import code.expressionlanguage.utilcompo.AtomicIntegerStruct;
import code.expressionlanguage.utilcompo.FileInfos;
import code.threads.AbstractAtomicInteger;

public final class DfAtomicInteger implements DfInstancer {
    private final FileInfos infos;
    private final String aliasAtomicInteger;

    public DfAtomicInteger(FileInfos _infos, String _aliasAtomicInteger) {
        this.infos = _infos;
        this.aliasAtomicInteger = _aliasAtomicInteger;
    }

    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, ArgumentListCall _firstArgs, StackCall _stackCall) {
        AbstractAtomicInteger at_ = infos.getThreadFactory().newAtomicInteger();
        AtomicIntegerStruct std_ = new AtomicIntegerStruct(at_, aliasAtomicInteger);
        return new ArgumentWrapper(std_);
    }
}
