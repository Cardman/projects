package code.expressionlanguage.analyze.blocks;

import code.expressionlanguage.analyze.util.MappingLocalType;
import code.util.StringList;
import code.util.StringMap;

public interface AccessedBlock extends ImportingBlock {
    StringList getFileImports();
    FileBlock getFile();
    StringList getAllReservedInners();
    StringMap<MappingLocalType> getRefMappings();

    int getCountsAnonFct();

    void setCountsAnonFct(int _i);

}
