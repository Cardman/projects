package code.expressionlanguage.common;

import code.util.CustList;
import code.util.StringList;

public interface AnaGeneType extends AnaInheritedType,CommonGeneType {
    StringList getAllSuperTypes();
    StringList getParamTypesValues();
    CustList<StringList> getBoundAll();
    String getPackageName();
}
