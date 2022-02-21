package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.fwd.opers.AnaArrayInstancingContent;

public interface WithArrayElementInstancing {
    AnaArrayInstancingContent getArrayInstancingContent();
    boolean isIntermediateDottedOperation();
}
