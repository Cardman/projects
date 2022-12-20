package code.expressionlanguage.utilcompo;

import code.expressionlanguage.WithoutParentIdClassStruct;
import code.threads.AbstractAtomicBoolean;

public final class AtomicBooleanStruct extends WithoutParentIdClassStruct {

    private final AbstractAtomicBoolean instance;

    public AtomicBooleanStruct(AbstractAtomicBoolean _instance, String _className) {
        super(_className);
        this.instance = _instance;
    }

    public AbstractAtomicBoolean getInstance() {
        return instance;
    }
}
