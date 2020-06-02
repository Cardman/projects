package code.expressionlanguage.structs;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.opers.util.ClassField;
import code.expressionlanguage.opers.util.ClassFieldStruct;
import code.util.CustList;

public final class InnerCustStruct implements WithParentStruct {

    private final String className;

    private final CustList<ClassFieldStruct> fields;

    private Struct parent;

    private final String parentClassName;
    public InnerCustStruct(String _className,
                           CustList<ClassFieldStruct> _fields, Struct _parent, String _parentClassName) {
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
    public ClassFieldStruct getEntryStruct(ClassField _classField) {
        return ClassFieldStruct.getPair(fields,_classField);
    }

    @Override
    public String getClassName(ContextEl _contextEl) {
        return className;
    }

    @Override
    public CustList<ClassFieldStruct> getFields() {
        return fields;
    }

}
