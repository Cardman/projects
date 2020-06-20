package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.common.GeneClass;
import code.expressionlanguage.methods.UniqueRootedBlock;
import code.util.StringList;

public interface ExecUniqueRootedBlock extends GeneClass {

    String getImportedDirectGenericSuperClass();

    StringList getImportedDirectGenericSuperInterfaces();
    StringList getStaticInitImportedInterfaces();

    ExecFileBlock getFile();
}
