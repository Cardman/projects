package code.expressionlanguage.common;

import code.expressionlanguage.methods.AccessibleBlock;
import code.expressionlanguage.opers.util.Identifiable;

public interface GeneFunction extends AccessibleBlock {

    String getImportedReturnType();

    boolean isVarargs();

    String getName();
}
