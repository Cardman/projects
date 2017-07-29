package code.expressionlanguage.methods;

import code.expressionlanguage.ContextEl;

public interface UniqueRootedBlock extends RootedBlock {

    void validateConstructors(ContextEl _cont);

    String getSuperClass();

}
