package code.expressionlanguage.common;

import code.util.CustList;
import code.util.Ints;
import code.util.StringList;

public interface AnaGeneType extends AnaInheritedType {
    StringList getAllSuperTypes();
    Ints getTypeVarCounts();
    StringList getParamTypesValues();
    CustList<StringList> getBoundAll();
    StringList getAllGenericSuperTypes();
    String getPackageName();
    boolean withoutInstance();

    String getFullName();

    String getGenericString();
}
