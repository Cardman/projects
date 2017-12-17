package code.expressionlanguage.opers.util;

import code.expressionlanguage.ContextEl;
import code.util.EntryCust;
import code.util.ObjectMap;

public final class CustStruct extends Struct {

    private final Object instance;

    private final String className;

    private final ObjectMap<ClassField,Struct> fields;

    public CustStruct(Object _instance, String _className) {
        this(_instance, _className, new ObjectMap<ClassField,Struct>());
    }

    public CustStruct(Object _instance, String _className, boolean _nativeObject) {
        this(_instance, _className, new ObjectMap<ClassField,Struct>());
    }

    public CustStruct(Object _instance, String _className,
            ObjectMap<ClassField,Struct> _fields) {
        this(_instance, _className, _fields, null);
    }

    public CustStruct(Object _instance, String _className,
            ObjectMap<ClassField,Struct> _fields, Struct _parent) {
        instance = _instance;
        fields = _fields;
        className = _className;
    }

    public static Struct wrapOrId(Object _element) {
        if (_element instanceof Struct) {
            return (Struct) _element;
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

    public Struct getStruct(ClassField _classField) {
        return fields.getVal(_classField);
    }

    public void setStruct(ClassField _classField, Struct _value) {
        for (EntryCust<ClassField, Struct> e: fields.entryList()) {
            if (e.getKey().eq(_classField)) {
                e.setValue(_value);
                return;
            }
        }
    }

    @Override
    public Boolean isJavaObject() {
        return false;
    }

    @Override
    public String getClassName(ContextEl _contextEl) {
        return className;
    }

    @Override
    public String getRealClassName(ContextEl _context) {
        return instance.getClass().getName();
    }

    @Override
    public Object getInstance() {
        return instance;
    }

    @Override
    public ObjectMap<ClassField,Struct> getFields() {
        return fields;
    }
}
