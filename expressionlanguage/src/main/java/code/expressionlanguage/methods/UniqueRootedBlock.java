package code.expressionlanguage.methods;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.opers.util.FctConstraints;
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

    StringList getAllSortedInterfaces();

    StringList getAllNeededSortedInterfaces();

    StringList getAllSuperClasses();

    StringList getAllSuperTypes();

    StringList getAllInterfaces();

    ObjectMap<FctConstraints, String> getDefaultMethods();
}
