package code.expressionlanguage.common;

import code.expressionlanguage.ContextEl;
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

    StringList getAllGenericSuperClasses(ContextEl _classes);

    StringList getAllSuperClasses();
    StringList getAllSuperTypes();

    StringList getDirectGenericSuperClasses(ContextEl _classes);

    /** Copy the list*/
    StringList getDirectSuperClasses(ContextEl _classes);

    boolean isFinalType();
    boolean isAbstractType();

    StringList getAllGenericInterfaces(ContextEl _classes);

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
    StringList getDirectGenericSuperTypes(ContextEl _classes);
}
