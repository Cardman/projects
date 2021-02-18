package code.expressionlanguage.analyze.syntax;

import code.expressionlanguage.analyze.blocks.AnonymousTypeBlock;
import code.expressionlanguage.analyze.blocks.NamedCalledFunctionBlock;
import code.expressionlanguage.analyze.blocks.SwitchMethodBlock;
import code.util.CustList;

public final class IntermediaryResults {
    private final CustList<SwitchMethodBlock> switchMethods = new CustList<SwitchMethodBlock>();
    private final CustList<AnonymousTypeBlock> anonymousTypes = new CustList<AnonymousTypeBlock>();
    private final CustList<NamedCalledFunctionBlock> anonymousFunctions = new CustList<NamedCalledFunctionBlock>();

    public CustList<SwitchMethodBlock> getSwitchMethods() {
        return switchMethods;
    }

    public CustList<NamedCalledFunctionBlock> getAnonymousFunctions() {
        return anonymousFunctions;
    }

    public CustList<AnonymousTypeBlock> getAnonymousTypes() {
        return anonymousTypes;
    }
}
