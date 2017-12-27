package code.expressionlanguage.opers.util;

import code.expressionlanguage.ContextEl;
import code.util.EntryCust;
import code.util.ObjectMap;
import code.util.StringList;

public final class CustStruct implements FieldableStruct {

    private final String className;

    private final ObjectMap<ClassField,Struct> fields;

    public CustStruct(String _className,
            ObjectMap<ClassField,Struct> _fields, Struct _parent) {
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

    @Override
    public boolean sameReference(Struct _other) {
        if (!(_other instanceof CustStruct)) {
            return false;
        }
        CustStruct other_ = (CustStruct) _other;
        if (!StringList.quickEq(className, other_.className)) {
            return false;
        }
        return getFields() == other_.getFields();
    }

    @Override
    public Struct getStruct(ClassField _classField) {
        return fields.getVal(_classField);
    }

    @Override
    public void setStruct(ClassField _classField, Struct _value) {
        for (EntryCust<ClassField, Struct> e: fields.entryList()) {
            if (e.getKey().eq(_classField)) {
                e.setValue(_value);
                return;
            }
        }
    }

    @Override
    public String getClassName(ContextEl _contextEl) {
        return className;
    }

    @Override
    public Object getInstance() {
        return null;
    }

    @Override
    public ObjectMap<ClassField,Struct> getFields() {
        return fields;
    }

    @Override
    public String getClassName() {
        return className;
    }
}
