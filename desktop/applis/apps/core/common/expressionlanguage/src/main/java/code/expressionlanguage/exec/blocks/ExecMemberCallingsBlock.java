package code.expressionlanguage.exec.blocks;

import code.util.CustList;

public abstract class ExecMemberCallingsBlock extends ExecBracedBlock {
    private CustList<ExecRootBlock> reserved = new CustList<ExecRootBlock>();
    private CustList<ExecRootBlock> anonymous = new CustList<ExecRootBlock>();
    private CustList<ExecAnonymousFunctionBlock> anonymousLambda = new CustList<ExecAnonymousFunctionBlock>();
    ExecMemberCallingsBlock(int _offsetTrim) {
        super(_offsetTrim);
    }

    public CustList<ExecRootBlock> getReserved() {
        return reserved;
    }

    public CustList<ExecRootBlock> getAnonymous() {
        return anonymous;
    }
    public CustList<ExecAnonymousFunctionBlock> getAnonymousLambda(){
        return anonymousLambda;
    }
}
