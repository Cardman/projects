package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.analyze.blocks.ImportingBlock;
import code.util.StringList;

public interface AccessedBlock extends ImportingBlock {
    StringList getFileImports();

}
