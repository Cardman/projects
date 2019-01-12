package code.expressionlanguage.methods;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.methods.util.TypeVar;
import code.util.CustList;

public interface AccessingImportingBlock extends ImportingBlock {
    boolean isTypeHidden(String _class, Analyzable _analyzable);
    FileBlock getFile();

    CustList<TypeVar> getParamTypesMapValues();

}
