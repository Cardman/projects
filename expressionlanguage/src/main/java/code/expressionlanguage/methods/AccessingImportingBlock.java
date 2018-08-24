package code.expressionlanguage.methods;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.methods.util.TypeVar;
import code.util.CustList;

public interface AccessingImportingBlock extends ImportingBlock {
    boolean canAccessClass(String _class, Analyzable _analyzable);
    FileBlock getFile();
    boolean isAccessibleType(String _type, Analyzable _an);
    CustList<TypeVar> getParamTypesMapValues();

}
