package code.expressionlanguage.analyze.blocks;

import code.util.CustList;

public final class AnonymousElements {
    private final CustList<AnonymousTypeBlock> types = new CustList<AnonymousTypeBlock>();
    private final CustList<NamedCalledFunctionBlock> lambdas = new CustList<NamedCalledFunctionBlock>();
    private final CustList<SwitchMethodBlock> switches = new CustList<SwitchMethodBlock>();

    public CustList<AnonymousTypeBlock> getTypes() {
        return types;
    }

    public CustList<NamedCalledFunctionBlock> getLambdas() {
        return lambdas;
    }

    public CustList<SwitchMethodBlock> getSwitches() {
        return switches;
    }

}
