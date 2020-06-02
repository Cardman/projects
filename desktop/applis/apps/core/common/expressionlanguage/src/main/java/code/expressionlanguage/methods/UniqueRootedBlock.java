package code.expressionlanguage.methods;

import code.expressionlanguage.common.GeneClass;
import code.util.StringList;

public interface UniqueRootedBlock extends GeneClass {

    String getImportedDirectGenericSuperClass();

    StringList getImportedDirectGenericSuperInterfaces();
    StringList getStaticInitImportedInterfaces();
    FileBlock getFile();
}
