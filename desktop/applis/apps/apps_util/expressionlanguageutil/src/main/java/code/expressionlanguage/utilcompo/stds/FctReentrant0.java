package code.expressionlanguage.utilcompo.stds;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.stds.StdCaller;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.utilcompo.AbstractLockStruct;
import code.expressionlanguage.utilcompo.FileInfos;
import code.threads.AbstractLock;
import code.threads.LockFactory;

public final class FctReentrant0 implements StdCaller {
    private final FileInfos infos;
    private final String aliasReentrantLock;

    public FctReentrant0(FileInfos _infos, String _aliasReentrantLock) {
        this.infos = _infos;
        this.aliasReentrantLock = _aliasReentrantLock;
    }

    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        AbstractLock re_ = LockFactory.newLock(infos.getThreadFactory().newAtomicBoolean());
        AbstractLockStruct std_ = new AbstractLockStruct(re_, aliasReentrantLock);
        return new ArgumentWrapper(std_);
    }
}
