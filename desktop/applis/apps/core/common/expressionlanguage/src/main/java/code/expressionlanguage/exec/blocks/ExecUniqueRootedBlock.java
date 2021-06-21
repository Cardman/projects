package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.common.GeneClass;
import code.util.CustList;
import code.util.StringList;

public interface ExecUniqueRootedBlock extends GeneClass {

    String getImportedDirectGenericSuperClass();

    ExecFileBlock getFile();

    CustList<ExecRootBlock> getStaticInitImportedInterfaces();
}
