package code.expressionlanguage.common;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.methods.AccessibleBlock;
import code.expressionlanguage.methods.util.TypeVar;
import code.expressionlanguage.opers.util.ClassMethodId;
import code.expressionlanguage.opers.util.MethodId;
import code.util.CustList;
import code.util.EqList;
import code.util.ObjectMap;
import code.util.StringList;

public interface GeneType extends AccessibleBlock {

    StringList getAllSuperClasses();
    StringList getAllSuperTypes();
    GeneType getOuter();

    boolean isFinalType();
    boolean isStaticType();
    boolean isAbstractType();
    boolean withoutInstance();

    CustList<TypeVar> getParamTypesMapValues();

    CustList<TypeVar> getParamTypes();

    boolean mustImplement();
    String getGenericString();

    String getPackageName();

    ObjectMap<MethodId, EqList<ClassMethodId>> getAllOverridingMethods();

    String getFullName();

    StringList getAllGenericSuperTypes();
}
