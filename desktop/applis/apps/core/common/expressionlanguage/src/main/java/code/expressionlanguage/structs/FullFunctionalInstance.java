package code.expressionlanguage.structs;

import code.expressionlanguage.exec.ClassFieldStruct;
import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.util.CustList;

public final class FullFunctionalInstance extends AbstractFunctionalInstanceImpl {

    public FullFunctionalInstance(String _className, LambdaStruct _functional,
                                  CustList<ClassFieldStruct> _fields, ExecNamedFunctionBlock _named) {
        super(_className, _functional,_fields, _named);
    }
}
