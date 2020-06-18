package code.expressionlanguage.methods;

import code.util.StringList;

public interface UniqueRootedBlock {

    String getImportedDirectGenericSuperClass();

    StringList getImportedDirectGenericSuperInterfaces();

    FileBlock getFile();
}
