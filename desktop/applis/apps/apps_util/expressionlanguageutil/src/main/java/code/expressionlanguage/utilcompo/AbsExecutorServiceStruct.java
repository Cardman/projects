package code.expressionlanguage.utilcompo;

import code.expressionlanguage.structs.Struct;
import code.threads.AbstractShutdownExecutorService;

public interface AbsExecutorServiceStruct extends Struct {

    AbstractShutdownExecutorService getExecutorService();
}
