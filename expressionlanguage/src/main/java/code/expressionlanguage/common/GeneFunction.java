package code.expressionlanguage.common;

import code.expressionlanguage.methods.AccessibleBlock;
import code.expressionlanguage.opers.util.Identifiable;
import code.util.StringList;

public interface GeneFunction extends AccessibleBlock {

    String getImportedReturnType();
    String getSignature();

    boolean isVarargs();

    String getName();
    Identifiable getId();
    StringList getImportedParametersTypes();
}
