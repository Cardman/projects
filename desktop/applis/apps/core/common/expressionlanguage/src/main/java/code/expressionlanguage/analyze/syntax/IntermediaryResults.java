package code.expressionlanguage.analyze.syntax;

import code.expressionlanguage.analyze.blocks.AnonymousFunctionBlock;
import code.expressionlanguage.analyze.blocks.AnonymousTypeBlock;
import code.expressionlanguage.analyze.blocks.SwitchMethodBlock;
import code.util.CustList;

public final class IntermediaryResults {
    private final CustList<SwitchMethodBlock> switchMethods = new CustList<SwitchMethodBlock>();
    private final CustList<AnonymousTypeBlock> anonymousTypes = new CustList<AnonymousTypeBlock>();
    private final CustList<AnonymousFunctionBlock> anonymousFunctions = new CustList<AnonymousFunctionBlock>();

    public CustList<SwitchMethodBlock> getSwitchMethods() {
        return switchMethods;
    }

    public CustList<AnonymousFunctionBlock> getAnonymousFunctions() {
        return anonymousFunctions;
    }

    public CustList<AnonymousTypeBlock> getAnonymousTypes() {
        return anonymousTypes;
    }
}
