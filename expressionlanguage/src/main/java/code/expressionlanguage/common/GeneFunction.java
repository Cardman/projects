package code.expressionlanguage.common;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.methods.AccessibleBlock;
import code.util.StringList;

public interface GeneFunction extends AccessibleBlock {

    String getReturnType(Analyzable _stds);

    boolean isVarargs();

    String getName();
    StringList getParametersTypes(Analyzable _stds);
}
