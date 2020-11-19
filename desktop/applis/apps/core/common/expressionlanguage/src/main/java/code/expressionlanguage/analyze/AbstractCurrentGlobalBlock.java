package code.expressionlanguage.analyze;

import code.expressionlanguage.analyze.blocks.AccessedBlock;
import code.expressionlanguage.analyze.blocks.AccessingImportingBlock;
import code.util.CustList;
import code.util.StringList;

public interface AbstractCurrentGlobalBlock {

    CustList<StringList> getCurrentGlobalBlockImportingTypes();
    AccessingImportingBlock getImportingAcces();
    AccessedBlock getCurrentGlobalBlock();
    AccessedBlock getCurrentGlobalBlock(AccessedBlock _bl);
}
