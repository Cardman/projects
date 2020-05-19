package code.expressionlanguage.structs;

import code.expressionlanguage.ContextEl;
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
    public boolean sameReference(Struct _other) {
        return this == _other;
    }

    @Override
    public EntryCust<ClassField, Struct> getEntryStruct(ClassField _classField) {
        return fields.getEntryByKey(_classField);
    }

    @Override
    public String getClassName(ContextEl _contextEl) {
        return className;
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

}
