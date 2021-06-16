package code.expressionlanguage.utilcompo;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.structs.WithoutParentIdStruct;
import code.threads.AbstractAtomicLong;

import java.util.concurrent.atomic.AtomicLong;

public final class AtomicLongStruct extends WithoutParentIdStruct {

    private final AbstractAtomicLong instance;
    private final String className;

    public AtomicLongStruct(AbstractAtomicLong _instance, String _className) {
        this.instance = _instance;
        this.className = _className;
    }
    @Override
    public String getClassName(ContextEl _contextEl) {
        return className;
    }

    public AbstractAtomicLong getInstance() {
        return instance;
    }
}
