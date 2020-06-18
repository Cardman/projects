package code.expressionlanguage.methods;

import code.expressionlanguage.common.GeneClass;
import code.util.StringList;

public interface UniqueRootedBlock {

    String getImportedDirectGenericSuperClass();

    StringList getImportedDirectGenericSuperInterfaces();
    StringList getStaticInitImportedInterfaces();
    FileBlock getFile();
}
