package code.expressionlanguage.structs;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.opers.util.ClassField;
import code.util.EntryCust;
import code.util.ObjectMap;

public final class FullFunctionalInstance implements AbstractFunctionalInstance,FieldableStruct {

    private final String className;

    private final ObjectMap<ClassField,Struct> fields;

    private final LambdaStruct functional;

    public FullFunctionalInstance(String _className, LambdaStruct _functional,
                                  ObjectMap<ClassField,Struct> _fields) {
        fields = _fields;
        functional = _functional;
        className = _className;
    }

    @Override
    public Struct getParent() {
        return NullStruct.NULL_VALUE;
    }

    @Override
    public boolean sameReference(Struct _other) {
        return this == _other;
    }

    @Override
    public String getClassName(ContextEl _contextEl) {
        return className;
    }

    @Override
    public LambdaStruct getFunctional() {
        return functional;
    }

    @Override
    public EntryCust<ClassField, Struct> getEntryStruct(ClassField _classField) {
        return fields.getEntryByKey(_classField);
    }

    @Override
    public ObjectMap<ClassField, Struct> getFields() {
        return fields;
    }
}
