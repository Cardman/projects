package code.expressionlanguage.analyze.blocks;

import code.util.StringList;

public interface AccessedBlock extends ImportingBlock {
    StringList getFileImports();

}
