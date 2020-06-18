package code.expressionlanguage.types;

import code.expressionlanguage.exec.blocks.ExecAccessingImportingBlock;

public interface AbstractCurrentGlobalBlock {

    ExecAccessingImportingBlock getCurrentGlobalBlock();
    ExecAccessingImportingBlock getCurrentGlobalBlock(ExecAccessingImportingBlock _bl);
}
