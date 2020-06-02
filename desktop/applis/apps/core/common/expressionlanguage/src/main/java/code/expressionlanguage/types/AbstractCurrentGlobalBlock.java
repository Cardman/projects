package code.expressionlanguage.types;

import code.expressionlanguage.methods.AccessingImportingBlock;

public interface AbstractCurrentGlobalBlock {

    AccessingImportingBlock getCurrentGlobalBlock();
    AccessingImportingBlock getCurrentGlobalBlock(AccessingImportingBlock _bl);
}
