package code.expressionlanguage.utilcompo;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.structs.WithoutParentIdStruct;
import code.threads.AbstractAtomicInteger;

import java.util.concurrent.atomic.AtomicInteger;

public final class AtomicIntegerStruct extends WithoutParentIdStruct {

    private final AbstractAtomicInteger instance;
    private final String className;

    public AtomicIntegerStruct(AbstractAtomicInteger _instance, String _className) {
        this.instance = _instance;
        this.className = _className;
    }
    @Override
    public String getClassName(ContextEl _contextEl) {
        return className;
    }

    public AbstractAtomicInteger getInstance() {
        return instance;
    }
}
