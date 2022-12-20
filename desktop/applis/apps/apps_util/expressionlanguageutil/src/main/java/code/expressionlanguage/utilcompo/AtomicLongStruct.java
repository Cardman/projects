package code.expressionlanguage.utilcompo;

import code.expressionlanguage.WithoutParentIdClassStruct;
import code.threads.AbstractAtomicLong;

public final class AtomicLongStruct extends WithoutParentIdClassStruct {

    private final AbstractAtomicLong instance;

    public AtomicLongStruct(AbstractAtomicLong _instance, String _className) {
        super(_className);
        this.instance = _instance;
    }

    public AbstractAtomicLong getInstance() {
        return instance;
    }
}
