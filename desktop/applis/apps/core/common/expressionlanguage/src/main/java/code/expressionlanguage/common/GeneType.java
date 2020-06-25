package code.expressionlanguage.common;

import code.expressionlanguage.analyze.util.TypeVar;
import code.expressionlanguage.exec.util.ExecTypeVar;
import code.util.*;

public interface GeneType extends InheritedType {


    StringList getAllSuperTypes();


    boolean isStaticType();
    boolean withoutInstance();

    Ints getTypeVarCounts();
    CustList<StringList> getBoundAll();
    CustList<ExecTypeVar> getParamTypesMapValues();
    StringList getParamTypesValues();


    String getGenericString();

    String getPackageName();

    String getFullName();

    StringList getAllGenericSuperTypes();

}
