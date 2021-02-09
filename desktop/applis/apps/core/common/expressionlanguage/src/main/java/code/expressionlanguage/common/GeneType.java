package code.expressionlanguage.common;

import code.expressionlanguage.exec.util.ExecTypeVar;
import code.util.*;

public interface GeneType extends InheritedType {



    boolean withoutInstance();

    Ints getTypeVarCounts();
    CustList<StringList> getBoundAll();
    CustList<ExecTypeVar> getParamTypesMapValues();
    StringList getParamTypesValues();


    String getGenericString();

    String getFullName();

}
