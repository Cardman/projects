package code.expressionlanguage;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.ReentrantLock;

import code.expressionlanguage.opers.util.ClassField;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.util.ObjectMap;

public final class StdStruct implements Struct {

    private final Object instance;

    private final String className;
    
    private StdStruct(Object _instance, String _className) {
        instance = _instance;
        className = _className;
    }

    @Override
    public Struct getParent() {
        return NullStruct.NULL_VALUE;
    }
    public static StdStruct newInstance(Thread _instance, String _className) {
        return new StdStruct(_instance, _className);
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
    public static StdStruct newInstance(ReentrantLock _instance, String _className) {
        return new StdStruct(_instance, _className);
    }


    @Override
    public boolean isNull() {
        return false;
    }

    @Override
    public boolean sameReference(Struct _other) {
        if (!(_other instanceof StdStruct)) {
            return false;
        }
        StdStruct other_ = (StdStruct) _other;
        return getInstance() == other_.getInstance();
    }

    @Override
    public String getClassName(ExecutableCode _contextEl) {
        return className;
    }
    public String getClassName() {
        return className;
    }

    @Override
    public Object getInstance() {
        return instance;
    }

    @Override
    public ObjectMap<ClassField, Struct> getFields() {
        return null;
    }

    @Override
    public boolean isArray() {
        return className.startsWith(PrimitiveTypeUtil.ARR_CLASS);
    }
}
