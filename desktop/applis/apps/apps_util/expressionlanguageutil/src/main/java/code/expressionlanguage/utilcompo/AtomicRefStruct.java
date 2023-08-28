package code.expressionlanguage.utilcompo;

import code.expressionlanguage.WithoutParentIdClassStruct;
import code.expressionlanguage.structs.Struct;
import code.threads.AbstractAtomicRef;

public final class AtomicRefStruct extends WithoutParentIdClassStruct {

    private final AbstractAtomicRef<Struct> instance;

    public AtomicRefStruct(AbstractAtomicRef<Struct> _instance, String _className) {
        super(_className);
        this.instance = _instance;
    }

    public AbstractAtomicRef<Struct> getInstance() {
        return instance;
    }
}
