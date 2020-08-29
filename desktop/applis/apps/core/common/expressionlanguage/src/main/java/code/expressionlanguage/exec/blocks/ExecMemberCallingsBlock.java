package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.files.OffsetsBlock;
import code.util.CustList;

public abstract class ExecMemberCallingsBlock extends ExecBracedBlock {
    private CustList<ExecRootBlock> reserved = new CustList<ExecRootBlock>();
    private CustList<ExecRootBlock> anonymous = new CustList<ExecRootBlock>();
    private CustList<ExecAnonymousFunctionBlock> anonymousLambda = new CustList<ExecAnonymousFunctionBlock>();
    ExecMemberCallingsBlock(OffsetsBlock _offset) {
        super(_offset);
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
