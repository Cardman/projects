package code.formathtml.stacks;
import code.expressionlanguage.stacks.SwitchStack;
import code.formathtml.ImportingPage;
import code.formathtml.RendParentBlock;

public final class RendSwitchBlockStack extends SwitchStack implements RendRemovableVars {

    private RendParentBlock block;

    private RendParentBlock curentVisitedBlock;

    private RendParentBlock lastVisitedBlock;

    @Override
    public RendParentBlock getBlock() {
        return block;
    }

    public void setBlock(RendParentBlock _block) {
        block = _block;
    }

    public RendParentBlock getCurentVisitedBlock() {
        return curentVisitedBlock;
    }

    public void setCurentVisitedBlock(RendParentBlock _curentVisitedBlock) {
        curentVisitedBlock = _curentVisitedBlock;
    }

    @Override
    public void removeVarAndLoop(ImportingPage _ip) {
        RendParentBlock cur_ = getCurentVisitedBlock();
        cur_.removeLocalVars(_ip);
        _ip.removeRendLastBlock();
    }

    @Override
    public RendParentBlock getLastBlock() {
        return block;
    }

    @Override
    public RendParentBlock getCurrentVisitedBlock() {
        return block;
    }

    public RendParentBlock getLastVisitedBlock() {
        return lastVisitedBlock;
    }

    public void setLastVisitedBlock(RendParentBlock _lastVisitedBlock) {
        lastVisitedBlock = _lastVisitedBlock;
    }

}
