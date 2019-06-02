package code.expressionlanguage.stacks;
import code.expressionlanguage.calls.AbstractPageEl;
import code.expressionlanguage.methods.BracedBlock;


public final class IfBlockStack extends IfStack implements RemovableVars {

    private BracedBlock block;
    private BracedBlock lastBlock;
    private BracedBlock curentVisitedBlock;

    @Override
    public BracedBlock getBlock() {
        return block;
    }

    public void setBlock(BracedBlock _block) {
        block = _block;
    }
    @Override
    public BracedBlock getLastBlock() {
        return lastBlock;
    }
    public void setCurentVisitedBlock(BracedBlock _curentVisitedBlock) {
        curentVisitedBlock = _curentVisitedBlock;
    }

    public void setLastBlock(BracedBlock _lastBlock) {
        lastBlock = _lastBlock;
    }

    @Override
    public void removeVarAndLoop(AbstractPageEl _ip) {
        BracedBlock cur_ = getCurrentVisitedBlock();
        cur_.removeLocalVars(_ip);
        _ip.removeLastBlock();
    }

    @Override
    public BracedBlock getCurrentVisitedBlock() {
        return curentVisitedBlock;
    }
}
