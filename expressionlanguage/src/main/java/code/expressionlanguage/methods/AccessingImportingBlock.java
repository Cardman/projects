package code.expressionlanguage.methods;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.methods.util.TypeVar;
import code.util.CustList;
import code.util.StringList;

public interface AccessingImportingBlock extends AccessedBlock {
    boolean isTypeHidden(RootBlock _class, ContextEl _analyzable);

}
