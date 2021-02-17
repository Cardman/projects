package code.expressionlanguage.utilcompo;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.structs.WithoutParentIdStruct;
import code.threads.AbstractLock;

public final class AbstractLockStruct extends WithoutParentIdStruct {

    private final AbstractLock instance;

    private final String className;

    public AbstractLockStruct(AbstractLock _instance, String _className) {
        instance = _instance;
        className = _className;
    }

    @Override
    public String getClassName(ContextEl _contextEl) {
        return className;
    }
    public String getClassName() {
        return className;
    }

    public AbstractLock getInstance() {
        return instance;
    }

}
