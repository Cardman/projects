package code.renders.utilcompo;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.structs.WithoutParentIdStruct;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public final class DefaultStruct extends WithoutParentIdStruct {

    private final Object instance;

    private final String className;

    private DefaultStruct(Object _instance, String _className) {
        instance = _instance;
        className = _className;
    }

    public static DefaultStruct newInstance(AtomicBoolean _instance, String _className) {
        return new DefaultStruct(_instance, _className);
    }
    public static DefaultStruct newInstance(AtomicLong _instance, String _className) {
        return new DefaultStruct(_instance, _className);
    }
    public static DefaultStruct newInstance(AtomicInteger _instance, String _className) {
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
