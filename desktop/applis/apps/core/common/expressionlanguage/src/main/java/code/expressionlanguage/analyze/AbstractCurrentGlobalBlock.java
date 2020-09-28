package code.expressionlanguage.analyze;

import code.expressionlanguage.analyze.blocks.AccessedBlock;
import code.expressionlanguage.analyze.blocks.ExecAccessingImportingBlock;

public interface AbstractCurrentGlobalBlock {

    AccessedBlock getCurrentGlobalBlockImporting();
    ExecAccessingImportingBlock getImportingAcces();
    AccessedBlock getCurrentGlobalBlock();
    AccessedBlock getCurrentGlobalBlock(AccessedBlock _bl);
}
