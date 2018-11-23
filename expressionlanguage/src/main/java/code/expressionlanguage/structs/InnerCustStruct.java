package code.expressionlanguage.structs;

import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.opers.util.ClassField;
import code.util.EntryCust;
import code.util.ObjectMap;

public final class InnerCustStruct implements FieldableStruct {

    private final String className;

    private final ObjectMap<ClassField,Struct> fields;

    private final Struct parent;

    public InnerCustStruct(String _className,
            ObjectMap<ClassField,Struct> _fields, Struct _parent) {
        fields = _fields;
        className = _className;
        parent = _parent;
    }

    @Override
    public Struct getParent() {
        return parent;
    }

    @Override
    public boolean isNull() {
        return false;
    }

    @Override
    public boolean sameReference(Struct _other) {
        return this == _other;
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
    public String getClassName(ExecutableCode _contextEl) {
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
