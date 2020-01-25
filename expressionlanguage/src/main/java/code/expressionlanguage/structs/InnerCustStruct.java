package code.expressionlanguage.structs;

import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.opers.util.ClassField;
import code.util.EntryCust;
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
    public EntryCust<ClassField, Struct> getEntryStruct(ClassField _classField) {
        return fields.getEntryByKey(_classField);
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
