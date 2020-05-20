package code.renders.utilcompo;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.bean.RealInstanceStruct;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public final class DefaultStruct implements RealInstanceStruct {

    private final Object instance;

    private final String className;

    private DefaultStruct(Object _instance, String _className) {
        instance = _instance;
        className = _className;
    }

    @Override
    public Struct getParent() {
        return NullStruct.NULL_VALUE;
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
    public boolean sameReference(Struct _other) {
        return this == _other;
    }

    @Override
    public String getClassName(ContextEl _contextEl) {
        return className;
    }

    @Override
    public Object getInstance() {
        return instance;
    }

}
