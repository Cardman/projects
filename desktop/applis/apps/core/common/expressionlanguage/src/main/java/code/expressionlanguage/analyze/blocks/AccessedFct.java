package code.expressionlanguage.analyze.blocks;

import code.util.CustList;

public interface AccessedFct {

    CustList<RootBlock> getReserved();

    CustList<AnonymousTypeBlock> getAnonymous();

    CustList<NamedCalledFunctionBlock> getAnonymousFct();

    CustList<SwitchMethodBlock> getSwitchMethods();
}
