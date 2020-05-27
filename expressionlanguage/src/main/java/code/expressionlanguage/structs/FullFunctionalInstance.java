package code.expressionlanguage.structs;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.opers.util.ClassField;
import code.expressionlanguage.opers.util.ClassFieldStruct;
import code.util.CustList;

public final class FullFunctionalInstance extends WithoutParentIdStruct implements AbstractFunctionalInstance,FieldableStruct {

    private final String className;

    private final CustList<ClassFieldStruct> fields;

    private final LambdaStruct functional;

    public FullFunctionalInstance(String _className, LambdaStruct _functional,
                                  CustList<ClassFieldStruct> _fields) {
        fields = _fields;
        functional = _functional;
        className = _className;
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
    public ClassFieldStruct getEntryStruct(ClassField _classField) {
        return ClassFieldStruct.getPair(fields,_classField);
    }

    @Override
    public CustList<ClassFieldStruct> getFields() {
        return fields;
    }
}
