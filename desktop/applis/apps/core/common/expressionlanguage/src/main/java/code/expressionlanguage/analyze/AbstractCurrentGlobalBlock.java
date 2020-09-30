package code.expressionlanguage.analyze;

import code.expressionlanguage.analyze.blocks.AccessedBlock;
import code.expressionlanguage.analyze.blocks.AccessingImportingBlock;

public interface AbstractCurrentGlobalBlock {

    AccessedBlock getCurrentGlobalBlockImporting();
    AccessingImportingBlock getImportingAcces();
    AccessedBlock getCurrentGlobalBlock();
    AccessedBlock getCurrentGlobalBlock(AccessedBlock _bl);
}
