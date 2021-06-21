package code.expressionlanguage.analyze.blocks;


import code.util.CustList;
import code.util.StringList;

public interface UniqueRootedBlock {

    FileBlock getFile();

    String getImportedDirectGenericSuperClass();

    CustList<RootBlock> getStaticInitImportedInterfaces();
}
