package code.expressionlanguage.utilcompo;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.structs.WithoutParentIdStruct;
import code.threads.AbstractLock;

public final class StdStruct extends WithoutParentIdStruct implements Struct {

    private final Object instance;

    private final String className;
    
    private StdStruct(Object _instance, String _className) {
        instance = _instance;
        className = _className;
    }

    public static StdStruct newInstance(AtomicBoolean _instance, String _className) {
        return new StdStruct(_instance, _className);
    }
    public static StdStruct newInstance(AtomicInteger _instance, String _className) {
        return new StdStruct(_instance, _className);
    }
    public static StdStruct newInstance(AtomicLong _instance, String _className) {
        return new StdStruct(_instance, _className);
    }
    public static StdStruct newInstance(AbstractLock _instance, String _className) {
        return new StdStruct(_instance, _className);
    }

    @Override
    public String getClassName(ContextEl _contextEl) {
        return className;
    }
    public String getClassName() {
        return className;
    }

    public Object getInstance() {
        return instance;
    }

}
