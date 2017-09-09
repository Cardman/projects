package code.expressionlanguage.methods;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.opers.util.FctConstraints;
import code.util.ObjectMap;
import code.util.StringList;

public interface UniqueRootedBlock {

    void validateConstructors(ContextEl _cont);

    String getSuperClass();

    StringList getDirectInterfaces();

    StringList getAllDirectInterfaces();

    StringList getAllSortedInterfaces();

    StringList getAllNeededSortedInterfaces();

    StringList getAllSuperClasses();

    StringList getAllSuperTypes();

    StringList getAllInterfaces();

    ObjectMap<FctConstraints, String> getDefaultMethods();
}
