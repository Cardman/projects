package code.expressionlanguage.methods;

import code.util.Numbers;
import code.util.StringList;

public interface ImportingBlock {

    StringList getImports();
    Numbers<Integer> getImportsOffset();
}
