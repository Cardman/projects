package code.expressionlanguage.structs;

import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;

public final class FunctionalInstance extends AbstractFunctionalInstanceImpl {

    public FunctionalInstance(String _className, LambdaStruct _functional, ExecNamedFunctionBlock _named) {
        super(_className, _functional, _named);
    }

}
