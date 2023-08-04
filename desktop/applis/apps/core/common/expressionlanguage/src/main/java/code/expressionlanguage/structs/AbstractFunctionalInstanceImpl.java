package code.expressionlanguage.structs;

import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.exec.ClassFieldStruct;
import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.util.CustList;

public abstract class AbstractFunctionalInstanceImpl extends AbsFieldableStruct implements AbstractFunctionalInstance {


    private final LambdaStruct functional;

    private final ExecNamedFunctionBlock named;
    protected AbstractFunctionalInstanceImpl(String _className, LambdaStruct _functional, CustList<ClassFieldStruct> _fields, ExecNamedFunctionBlock _named) {
        super(_className,_fields,NullStruct.NULL_VALUE,"",-1,"");
        functional = _functional;
        named = _named;
    }

    @Override
    public long randCode() {
        return NumParsers.randCode(getClassName());
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
