package code.expressionlanguage.structs;

import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;

public interface AbstractFunctionalInstance extends Struct {
    LambdaStruct getFunctional();
    ExecNamedFunctionBlock getNamed();
}
