package code.expressionlanguage.utilcompo.stds;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.stds.StdCaller;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.utilcompo.AtomicBooleanStruct;
import code.expressionlanguage.utilcompo.FileInfos;
import code.threads.AbstractAtomicBoolean;

public final class FctAtomicBoolean0 implements StdCaller {
    private final FileInfos infos;
    private final String aliasAtomicBoolean;

    public FctAtomicBoolean0(FileInfos _infos, String _aliasAtomicBoolean) {
        this.infos = _infos;
        this.aliasAtomicBoolean = _aliasAtomicBoolean;
    }

    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        AbstractAtomicBoolean at_ = infos.getThreadFactory().newAtomicBoolean();
        AtomicBooleanStruct std_ = new AtomicBooleanStruct(at_, aliasAtomicBoolean);
        return new ArgumentWrapper(std_);
    }
}
