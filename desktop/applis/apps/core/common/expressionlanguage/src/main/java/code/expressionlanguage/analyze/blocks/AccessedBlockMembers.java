package code.expressionlanguage.analyze.blocks;

import code.util.CustList;

public interface AccessedBlockMembers extends AccessedBlock{

    CustList<RootBlock> getLocalTypes();

    CustList<AnonymousTypeBlock> getAnonymousTypes();
}
