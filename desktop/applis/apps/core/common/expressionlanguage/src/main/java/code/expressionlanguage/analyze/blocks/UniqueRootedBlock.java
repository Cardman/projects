package code.expressionlanguage.analyze.blocks;


import code.util.StringList;

public interface UniqueRootedBlock {

    FileBlock getFile();

    String getImportedDirectGenericSuperClass();

    StringList getStaticInitImportedInterfaces();
}
