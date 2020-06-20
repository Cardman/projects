package code.expressionlanguage.structs;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.ClassField;

import code.expressionlanguage.exec.ClassFieldStruct;
import code.util.CustList;

public final class CustStruct extends WithoutParentIdStruct implements FieldableStruct {

    private final String className;

    private final CustList<ClassFieldStruct> fields;

    public CustStruct(String _className,
                      CustList<ClassFieldStruct> _fields) {
        fields = _fields;
        className = _className;
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
