package code.expressionlanguage.structs;

import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.opers.util.ClassField;
import code.util.ObjectMap;

public final class InnerCustStruct implements WithParentStruct {

    private final String className;

    private final ObjectMap<ClassField,Struct> fields;

    private Struct parent;

    private final String parentClassName;
    public InnerCustStruct(String _className,
            ObjectMap<ClassField,Struct> _fields, Struct _parent, String _parentClassName) {
        fields = _fields;
        className = _className;
        parent = _parent;
        parentClassName = _parentClassName;
    }

    @Override
    public String getParentClassName() {
        return parentClassName;
    }

    @Override
    public Struct getParent() {
        return parent;
    }

    @Override
    public void setParent(Struct _parent) {
        parent = _parent;
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
        fields.set(_classField,_value);
    }

    @Override
    public String getClassName(ExecutableCode _contextEl) {
        return className;
    }

    @Override
    public ObjectMap<ClassField,Struct> getFields() {
        return fields;
    }

}
