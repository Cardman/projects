package code.expressionlanguage.utilcompo;

import code.expressionlanguage.structs.Struct;
import code.threads.AbstractAtomicBoolean;

public interface AbsExecutorServiceStruct extends Struct {

    void shutdown();
    AbstractAtomicBoolean getStopped();
}
