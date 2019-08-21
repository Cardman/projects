package code.expressionlanguage.methods;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.methods.util.TypeVar;
import code.util.CustList;
import code.util.StringList;

public interface AccessingImportingBlock extends AccessedBlock {
    boolean isTypeHidden(String _class, Analyzable _analyzable);

}
