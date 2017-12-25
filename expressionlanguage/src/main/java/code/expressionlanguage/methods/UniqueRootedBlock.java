package code.expressionlanguage.methods;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.opers.util.ClassMethodId;
import code.expressionlanguage.opers.util.MethodId;
import code.util.EqList;
import code.util.ObjectMap;
import code.util.StringList;

public interface UniqueRootedBlock {

    void validateConstructors(ContextEl _cont);

    String getGenericSuperClass(ContextEl _classes);

    String getSuperClass(ContextEl _classes);

    StringList getDirectGenericInterfaces(ContextEl _classes);

    StringList getDirectInterfaces(ContextEl _classes);
    StringList getAllGenericInterfaces(ContextEl _classes);

    StringList getAllSuperClasses(ContextEl _classes);

    StringList getAllSuperTypes();

    StringList getAllInterfaces();

    String getGenericString();

    ObjectMap<MethodId, EqList<ClassMethodId>> getAllOverridingMethods();
}
