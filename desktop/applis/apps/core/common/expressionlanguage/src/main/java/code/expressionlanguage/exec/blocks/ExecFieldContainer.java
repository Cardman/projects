package code.expressionlanguage.exec.blocks;

import code.util.CustList;

public final class ExecFieldContainer {
    private final CustList<ExecRootBlock> anonymous = new CustList<ExecRootBlock>();
    private final CustList<ExecAnonymousFunctionBlock> anonymousLambda = new CustList<ExecAnonymousFunctionBlock>();
    private final CustList<ExecAbstractSwitchMethod> switchMethods = new CustList<ExecAbstractSwitchMethod>();

    public CustList<ExecAbstractSwitchMethod> getSwitchMethods() {
        return switchMethods;
    }

    public CustList<ExecAnonymousFunctionBlock> getAnonymousLambda() {
        return anonymousLambda;
    }

    public CustList<ExecRootBlock> getAnonymous() {
        return anonymous;
    }
}
