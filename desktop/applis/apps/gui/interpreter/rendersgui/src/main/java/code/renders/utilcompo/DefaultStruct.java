package code.renders.utilcompo;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.structs.WithoutParentIdStruct;
import code.threads.AbstractAtomicBoolean;
import code.threads.AbstractAtomicInteger;
import code.threads.AbstractAtomicLong;

public final class DefaultStruct extends WithoutParentIdStruct {

    private final Object instance;

    private final String className;

    private DefaultStruct(Object _instance, String _className) {
        instance = _instance;
        className = _className;
    }

    public static DefaultStruct newInstance(AbstractAtomicBoolean _instance, String _className) {
        return new DefaultStruct(_instance, _className);
    }
    public static DefaultStruct newInstance(AbstractAtomicLong _instance, String _className) {
        return new DefaultStruct(_instance, _className);
    }
    public static DefaultStruct newInstance(AbstractAtomicInteger _instance, String _className) {
        return new DefaultStruct(_instance, _className);
    }
    @Override
    public String getClassName(ContextEl _contextEl) {
        return className;
    }

    public Object getInstance() {
        return instance;
    }

}
