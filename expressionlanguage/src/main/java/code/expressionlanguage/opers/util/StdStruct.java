package code.expressionlanguage.opers.util;

import code.expressionlanguage.types.NativeTypeUtil;
import code.util.ObjectMap;

public final class StdStruct implements Struct {

    private final Object instance;

    private final String className;

    public StdStruct(Object _instance) {
        instance = _instance;
        className = NativeTypeUtil.getPrettyType(_instance.getClass());
    }

    public static Struct wrapStd(Object _element) {
        if (_element == null) {
            return NullStruct.NULL_VALUE;
        }
        if (_element instanceof Double) {
            return new DoubleStruct((Double) _element);
        }
        if (_element instanceof Float) {
            return new FloatStruct((Float) _element);
        }
        if (_element instanceof Long) {
            return new LongStruct((Long) _element);
        }
        if (_element instanceof Integer) {
            return new IntStruct((Integer) _element);
        }
        if (_element instanceof Character) {
            return new CharStruct((Character) _element);
        }
        if (_element instanceof Short) {
            return new ShortStruct((Short) _element);
        }
        if (_element instanceof Byte) {
            return new ByteStruct((Byte) _element);
        }
        if (_element instanceof Boolean) {
            return new BooleanStruct((Boolean) _element);
        }
        return new StdStruct(_element);
    }

    @Override
    public boolean isNull() {
        return false;
    }

    public boolean sameReference(Struct _other) {
        return getInstance() == _other.getInstance();
    }


    @Override
    public Boolean isJavaObject() {
        return true;
    }

    @Override
    public String getClassName() {
        return className;
    }

    @Override
    public String getRealClassName() {
        if (instance == null) {
            return null;
        }
        return instance.getClass().getName();
    }

    @Override
    public Object getInstance() {
        return instance;
    }

    @Override
    public ObjectMap<ClassField, Struct> getFields() {
        return null;
    }
}
