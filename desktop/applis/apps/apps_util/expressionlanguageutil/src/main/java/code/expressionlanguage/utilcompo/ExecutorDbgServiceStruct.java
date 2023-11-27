package code.expressionlanguage.utilcompo;

import code.threads.AbstractAtomicBoolean;

public final class ExecutorDbgServiceStruct extends AbsExecutorServiceImplStruct implements AbsExecutorServiceStruct {

    public ExecutorDbgServiceStruct(AbstractAtomicBoolean _shut) {
        super(_shut);
    }

    @Override
    public void shutdown() {
        getStopped().set(true);
    }

}
