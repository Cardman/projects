package code.expressionlanguage.methods;

import code.expressionlanguage.methods.util.TypeVar;
import code.util.CustList;
import code.util.StringList;

public interface AccessedBlock extends ImportingBlock {
    StringList getFileImports();
    CustList<TypeVar> getParamTypesMapValues();
}
