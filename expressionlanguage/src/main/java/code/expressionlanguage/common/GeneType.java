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
import code.util.StringMap;

public interface GeneType extends AccessibleBlock {

    StringList getAllGenericSuperClasses(Analyzable _classes);

    StringList getAllSuperClasses();
    StringList getAllSuperTypes();

    StringList getDirectGenericSuperClasses(Analyzable _classes);

    /** Copy the list*/
    StringList getDirectSuperClasses(Analyzable _classes);

    boolean isFinalType();
    boolean isAbstractType();

    StringList getAllGenericInterfaces(Analyzable _classes);

    StringMap<TypeVar> getParamTypesMap();

    CustList<TypeVar> getParamTypes();
    StringList getAllInterfaces();

    boolean mustImplement();
    String getGenericString();

    String getFullDefinition();

    String getName();

    String getPackageName();

    ObjectMap<MethodId, EqList<ClassMethodId>> getAllOverridingMethods();

    String getFullName();
    StringList getDirectGenericSuperTypes(Analyzable _classes);
}
