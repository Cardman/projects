package code.expressionlanguage.analyze.blocks;


import code.util.CustList;

public interface UniqueRootedBlock {

    FileBlock getFile();

    CustList<RootBlock> getStaticInitImportedInterfaces();
}
