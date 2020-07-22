package code.expressionlanguage.structs;

import code.expressionlanguage.exec.blocks.ExecAnnotableParametersBlock;

public interface AnnotatedParamStruct extends AnnotatedStruct {
    ExecAnnotableParametersBlock getAnnotableBlockParam();
}
