package code.expressionlanguage.stacks;
import code.expressionlanguage.calls.AbstractPageEl;
import code.expressionlanguage.methods.BracedBlock;

public final class SwitchBlockStack extends SwitchStack implements RemovableVars {

    private BracedBlock block;

    private BracedBlock curentVisitedBlock;

    private BracedBlock lastVisitedBlock;

    @Override
    public BracedBlock getBlock() {
        return block;
    }

    public void setBlock(BracedBlock _block) {
        block = _block;
    }

    public BracedBlock getCurentVisitedBlock() {
        return curentVisitedBlock;
    }

    public void setCurentVisitedBlock(BracedBlock _curentVisitedBlock) {
        curentVisitedBlock = _curentVisitedBlock;
    }

    @Override
    public void removeVarAndLoop(AbstractPageEl _ip) {
        BracedBlock cur_ = getCurentVisitedBlock();
        cur_.removeLocalVars(_ip);
        _ip.removeLastBlock();
    }

    @Override
    public BracedBlock getLastBlock() {
        return block;
    }

    @Override
    public BracedBlock getCurrentVisitedBlock() {
        return block;
    }

    public BracedBlock getLastVisitedBlock() {
        return lastVisitedBlock;
    }

    public void setLastVisitedBlock(BracedBlock _lastVisitedBlock) {
        lastVisitedBlock = _lastVisitedBlock;
    }

}
