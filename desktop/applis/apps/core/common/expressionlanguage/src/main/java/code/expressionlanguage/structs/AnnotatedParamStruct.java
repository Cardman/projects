package code.expressionlanguage.structs;

import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;

public interface AnnotatedParamStruct extends AnnotatedStruct {
    ExecNamedFunctionBlock getAnnotableBlockParam();
}
