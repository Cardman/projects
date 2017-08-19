package code.expressionlanguage.methods;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.opers.util.FctConstraints;
import code.util.ObjectMap;
import code.util.StringList;

public interface UniqueRootedBlock extends RootedBlock {

    void validateConstructors(ContextEl _cont);

    String getSuperClass();

    StringList getDirectInterfaces();

    StringList getAllDirectInterfaces();

    StringList getAllInterfaces();

    StringList getAllSortedInterfaces();

    StringList getAllNeededSortedInterfaces();

    ObjectMap<FctConstraints, String> getDefaultMethods();
}
