package code.expressionlanguage.structs;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.exec.ClassFieldStruct;
import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.util.CustList;

public final class FullFunctionalInstance extends WithoutParentIdStruct implements AbstractFunctionalInstance,FieldableStruct {

    private final String className;

    private final CustList<ClassFieldStruct> fields;

    private final LambdaStruct functional;

    private final ExecNamedFunctionBlock named;
    public FullFunctionalInstance(String _className, LambdaStruct _functional,
                                  CustList<ClassFieldStruct> _fields, ExecNamedFunctionBlock _named) {
        fields = _fields;
        functional = _functional;
        className = _className;
        named = _named;
    }

    @Override
    public String getClassName(ContextEl _contextEl) {
        return className;
    }

    @Override
    public ExecNamedFunctionBlock getNamed() {
        return named;
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
