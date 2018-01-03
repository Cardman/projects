package code.expressionlanguage.opers.util;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.CustomError;
import code.expressionlanguage.PrimitiveTypeUtil;
import code.expressionlanguage.types.NativeTypeUtil;
import code.util.ObjectMap;

public final class StdStruct implements Struct {

    private final Object instance;

    private final String className;

    public StdStruct(Object _instance) {
        instance = _instance;
        className = NativeTypeUtil.getPrettyType(_instance.getClass());
    }

    public StdStruct(Object _instance, String _className) {
        instance = _instance;
        className = _className;
    }

    public StdStruct(CustomError _instance, String _className) {
        instance = _instance;
        className = _className;
    }

    public static Struct defaultClass(String _element, ContextEl _context) {
        if (PrimitiveTypeUtil.isPrimitive(_element, _context)) {
            Object def_ = PrimitiveTypeUtil.defaultValue(_element, _context).getInstance();
            if (def_ instanceof Double) {
                return new DoubleStruct((Double) def_);
            }
            if (def_ instanceof Float) {
                return new FloatStruct((Float) def_);
            }
            if (def_ instanceof Long) {
                return new LongStruct((Long) def_);
            }
            if (def_ instanceof Integer) {
                return new IntStruct((Integer) def_);
            }
            if (def_ instanceof Character) {
                return new CharStruct((Character) def_);
            }
            if (def_ instanceof Short) {
                return new ShortStruct((Short) def_);
            }
            if (def_ instanceof Byte) {
                return new ByteStruct((Byte) def_);
            }
            if (def_ instanceof Boolean) {
                return new BooleanStruct((Boolean) def_);
            }
        }
        return NullStruct.NULL_VALUE;
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

    public static Struct wrapStd(Object _element, String _alias) {
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
        return new StdStruct(_element, _alias);
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
    public String getClassName(ContextEl _contextEl) {
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
}
