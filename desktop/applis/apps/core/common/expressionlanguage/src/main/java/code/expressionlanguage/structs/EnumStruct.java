package code.expressionlanguage.structs;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.opers.util.ClassField;
import code.expressionlanguage.opers.util.ClassFieldStruct;
import code.util.CustList;

public final class EnumStruct extends WithoutParentIdStruct implements FieldableStruct, EnumerableStruct {

    private final int ordinal;

    private final String name;

    private final String className;

    private final CustList<ClassFieldStruct> fields;

    public EnumStruct(String _className,
                      CustList<ClassFieldStruct> _fields,
            int _ordinal, String _name) {
        fields = _fields;
        className = _className;
        ordinal = _ordinal;
        name = _name;
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
    public int getOrdinal() {
        return ordinal;
    }

    @Override
    public String getName() {
        return name;
    }


    @Override
    public CustList<ClassFieldStruct> getFields() {
        return fields;
    }

}
