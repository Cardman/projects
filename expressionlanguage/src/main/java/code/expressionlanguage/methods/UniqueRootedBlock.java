package code.expressionlanguage.methods;

import code.expressionlanguage.ContextEl;
import code.util.StringList;

public interface UniqueRootedBlock extends RootedBlock {

    void validateConstructors(ContextEl _cont);

    String getSuperClass();

    StringList getDirectInterfaces();

    StringList getAllDirectInterfaces();

    StringList getAllSortedInterfaces();

    StringList getAllNeededSortedInterfaces();
}
