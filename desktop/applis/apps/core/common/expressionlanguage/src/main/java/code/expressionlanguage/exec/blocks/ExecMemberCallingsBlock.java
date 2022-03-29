package code.expressionlanguage.exec.blocks;

import code.util.CustList;

public abstract class ExecMemberCallingsBlock extends ExecBracedBlock implements ExecAccessedFct{
    private final CustList<ExecRootBlock> reserved = new CustList<ExecRootBlock>();
    private final CustList<ExecRootBlock> anonymous = new CustList<ExecRootBlock>();
    private final CustList<ExecAnonymousFunctionBlock> anonymousLambda = new CustList<ExecAnonymousFunctionBlock>();
    private final CustList<ExecAbstractSwitchMethod> switchMethods = new CustList<ExecAbstractSwitchMethod>();

    public CustList<ExecRootBlock> getReserved() {
        return reserved;
    }

    public CustList<ExecRootBlock> getAnonymous() {
        return anonymous;
    }
    public CustList<ExecAnonymousFunctionBlock> getAnonymousLambda(){
        return anonymousLambda;
    }

    public CustList<ExecAbstractSwitchMethod> getSwitchMethods() {
        return switchMethods;
    }
}
