package code.expressionlanguage.structs;

import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;

public interface AnnotatedParamStruct extends AnnotatedMemberStruct {
    ExecNamedFunctionBlock getAnnotableBlockParam();
}
