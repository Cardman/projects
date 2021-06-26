package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.common.GeneClass;
import code.util.CustList;

public interface ExecUniqueRootedBlock extends GeneClass {

    String getImportedDirectGenericSuperClass();

    ExecFileBlock getFile();

    CustList<ExecRootBlock> getStaticInitImportedInterfaces();
}
