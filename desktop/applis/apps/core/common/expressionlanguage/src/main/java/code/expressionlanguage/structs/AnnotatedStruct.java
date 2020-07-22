package code.expressionlanguage.structs;

import code.expressionlanguage.exec.blocks.ExecAnnotableBlock;

public interface AnnotatedStruct extends DisplayableStruct {
    String getFileName();
    ExecAnnotableBlock getAnnotableBlock();
}
