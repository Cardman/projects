package code.expressionlanguage.common;

import code.expressionlanguage.methods.AccessibleBlock;
import code.expressionlanguage.methods.util.TypeVar;
import code.expressionlanguage.opers.util.ClassMethodId;
import code.expressionlanguage.opers.util.MethodId;
import code.util.*;

public interface GeneType extends AccessibleBlock {

    StringList getAllSuperClasses();
    StringList getAllSuperTypes();
    GeneType getOuter();

    boolean isFinalType();
    boolean isStaticType();
    boolean isAbstractType();
    boolean withoutInstance();

    Ints getTypeVarCounts();
    CustList<StringList> getBoundAll();
    CustList<TypeVar> getParamTypesMapValues();


    String getGenericString();

    String getPackageName();

    ObjectMap<MethodId, EqList<ClassMethodId>> getAllOverridingMethods();

    String getFullName();

    StringList getAllGenericSuperTypes();
    StringList getAllInterfaces();
}
