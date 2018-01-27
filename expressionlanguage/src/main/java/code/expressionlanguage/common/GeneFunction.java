package code.expressionlanguage.common;

import code.expressionlanguage.methods.AccessibleBlock;
import code.expressionlanguage.stds.LgNames;
import code.util.StringList;

public interface GeneFunction extends AccessibleBlock {

    String getReturnType(LgNames _stds);

    boolean isVarargs();

    String getName();
    StringList getParametersTypes();
}
