package code.expressionlanguage.structs;

import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;

public interface AnnotatedParamStruct extends AnnotatedMemberStruct {
    ExecTypeFunction getPair();
    ExecNamedFunctionBlock getAnnotableBlockParam();
}
