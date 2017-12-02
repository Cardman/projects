package code.expressionlanguage.methods;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.opers.util.ClassMethodId;
import code.expressionlanguage.opers.util.MethodId;
import code.util.EqList;
import code.util.ObjectMap;
import code.util.StringList;

public interface UniqueRootedBlock {

    void validateConstructors(ContextEl _cont);

    String getGenericSuperClass();

    String getSuperClass();

    StringList getDirectGenericInterfaces();

    StringList getDirectInterfaces();
    StringList getAllGenericInterfaces(Classes _classes);
    StringList getAllDirectInterfaces();

    StringList getAllSuperClasses();

    StringList getAllSuperTypes();

    StringList getAllInterfaces();

    String getGenericString();

    ObjectMap<MethodId, EqList<ClassMethodId>> getAllOverridingMethods();
}
