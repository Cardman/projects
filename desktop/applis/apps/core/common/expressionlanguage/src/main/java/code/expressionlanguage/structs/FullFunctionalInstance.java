package code.expressionlanguage.structs;

import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.exec.ClassFieldStruct;
import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.util.CustList;

public final class FullFunctionalInstance extends AbstractFunctionalInstanceImpl implements FieldableStruct {

    private final CustList<ClassFieldStruct> fields;

    public FullFunctionalInstance(String _className, LambdaStruct _functional,
                                  CustList<ClassFieldStruct> _fields, ExecNamedFunctionBlock _named) {
        super(_className, _functional, _named);
        fields = _fields;
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
