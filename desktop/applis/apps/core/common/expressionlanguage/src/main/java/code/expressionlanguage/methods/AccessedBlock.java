package code.expressionlanguage.methods;

import code.util.StringList;

public interface AccessedBlock extends ImportingBlock {
    StringList getFileImports();

}
