package code.expressionlanguage.utilcompo;

import code.expressionlanguage.WithoutParentIdClassStruct;
import code.threads.AbstractAtomicInteger;

public final class AtomicIntegerStruct extends WithoutParentIdClassStruct {

    private final AbstractAtomicInteger instance;

    public AtomicIntegerStruct(AbstractAtomicInteger _instance, String _className) {
        super(_className);
        this.instance = _instance;
    }

    public AbstractAtomicInteger getInstance() {
        return instance;
    }
}
