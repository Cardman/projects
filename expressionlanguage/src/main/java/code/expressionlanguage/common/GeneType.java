package code.expressionlanguage.common;

import code.expressionlanguage.methods.util.TypeVar;
import code.expressionlanguage.opers.util.ClassMethodId;
import code.expressionlanguage.opers.util.MethodId;
import code.util.*;

public interface GeneType {


    StringList getAllSuperTypes();


    boolean isStaticType();
    boolean withoutInstance();

    Ints getTypeVarCounts();
    CustList<StringList> getBoundAll();
    CustList<TypeVar> getParamTypesMapValues();
    StringList getParamTypesValues();


    String getGenericString();

    String getPackageName();

    String getFullName();

    StringList getAllGenericSuperTypes();

}
