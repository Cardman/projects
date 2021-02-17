package code.expressionlanguage.utilcompo;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.structs.WithoutParentIdStruct;

import java.util.concurrent.atomic.AtomicBoolean;

public final class AtomicBooleanStruct extends WithoutParentIdStruct {

    private final AtomicBoolean instance;
    private final String className;

    public AtomicBooleanStruct(AtomicBoolean _instance, String _className) {
        this.instance = _instance;
        this.className = _className;
    }

    @Override
    public String getClassName(ContextEl _contextEl) {
        return className;
    }

    public AtomicBoolean getInstance() {
        return instance;
    }
}
