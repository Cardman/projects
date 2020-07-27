package code.expressionlanguage.common;

import code.util.CustList;
import code.util.StringList;

public interface AnaGeneType extends AnaInheritedType {
    StringList getAllSuperTypes();
    CustList<StringList> getBoundAll();
    StringList getAllGenericSuperTypes();
    boolean isStaticType();
    String getPackageName();
    boolean withoutInstance();

    String getFullName();

    String getGenericString();
}
