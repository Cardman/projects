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

    @Override
    public boolean isArray() {
        return false;
    }
}
