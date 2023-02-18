package code.expressionlanguage.structs;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.exec.ClassFieldStruct;
import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.util.CustList;

public abstract class AbstractFunctionalInstanceImpl extends WithoutParentIdStruct implements AbstractFunctionalInstance, FieldableStruct {

    private final CustList<ClassFieldStruct> fields;

    private final String className;

    private final LambdaStruct functional;

    private final ExecNamedFunctionBlock named;
    protected AbstractFunctionalInstanceImpl(String _className, LambdaStruct _functional, CustList<ClassFieldStruct> _fields, ExecNamedFunctionBlock _named) {
        className = _className;
        functional = _functional;
        fields = _fields;
        named = _named;
    }

    @Override
    public ClassFieldStruct getEntryStruct(ClassField _classField) {
        return ClassFieldStruct.getPair(fields,_classField);
    }

    @Override
    public CustList<ClassFieldStruct> getFields() {
        return fields;
    }

    @Override
    public String getClassName(ContextEl _contextEl) {
        return getClassName();
    }

    @Override
    public long randCode() {
        return NumParsers.randCode(className);
    }

    public String getClassName() {
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
}
