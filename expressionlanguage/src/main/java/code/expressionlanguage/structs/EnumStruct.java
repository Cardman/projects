package code.expressionlanguage.structs;

import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.opers.util.ClassField;
import code.util.EntryCust;
import code.util.ObjectMap;

public final class EnumStruct implements FieldableStruct, EnumerableStruct {

    private final int ordinal;

    private final String name;

    private final String className;

    private final ObjectMap<ClassField,Struct> fields;

    public EnumStruct(String _className,
            ObjectMap<ClassField,Struct> _fields,
            int _ordinal, String _name) {
        fields = _fields;
        className = _className;
        ordinal = _ordinal;
        name = _name;
    }

    @Override
    public Struct getParent() {
        return NullStruct.NULL_VALUE;
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
    public int getOrdinal() {
        return ordinal;
    }

    @Override
    public String getName() {
        return name;
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
