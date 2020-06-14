package code.expressionlanguage.methods;

import code.expressionlanguage.ContextEl;

public interface AccessingImportingBlock extends AccessedBlock {
    boolean isTypeHidden(RootBlock _class, ContextEl _analyzable);

}
