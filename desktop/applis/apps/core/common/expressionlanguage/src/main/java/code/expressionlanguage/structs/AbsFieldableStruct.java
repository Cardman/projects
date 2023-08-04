package code.expressionlanguage.structs;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.exec.ClassFieldStruct;
import code.util.CustList;

public abstract class AbsFieldableStruct implements WithParentStruct, EnumerableStruct {

    private final String className;

    private final CustList<ClassFieldStruct> fields;

    private Struct parent;

    private final String parentClassName;
    private final int ordinal;

    private final String name;
    protected AbsFieldableStruct(String _className,
                           CustList<ClassFieldStruct> _fields, Struct _parent, String _parentClassName,
                                 int _ordinal, String _name) {
        fields = _fields;
        className = _className;
        parent = _parent;
        parentClassName = _parentClassName;
        ordinal = _ordinal;
        name = _name;
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
    public long randCode() {
        return 1;
    }
    @Override
    public ClassFieldStruct getEntryStruct(ClassField _classField) {
        return ClassFieldStruct.getPair(fields,_classField);
    }

    @Override
    public String getClassName(ContextEl _contextEl) {
        return getClassName();
    }

    public String getClassName() {
        return className;
    }

    @Override
    public CustList<ClassFieldStruct> getFields() {
        return fields;
    }

    @Override
    public int getOrdinal() {
        return ordinal;
    }

    @Override
    public String getName() {
        return name;
    }

}
