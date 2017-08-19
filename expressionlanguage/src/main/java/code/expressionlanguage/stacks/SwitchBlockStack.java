package code.expressionlanguage.stacks;
import code.expressionlanguage.PageEl;
import code.expressionlanguage.methods.BracedBlock;
import code.util.CustList;

public final class SwitchBlockStack extends SwitchStack implements BreakableBlockStack, RemovableVars {

    private final CustList<BracedBlock> blocks = new CustList<BracedBlock>();

    public BracedBlock firstVisitedBlock() {
        return blocks.first();
    }
    
    public BracedBlock lastVisitedBlock() {
        return blocks.last();
    }

    public BracedBlock getCurentVisitedBlock() {
        return blocks.get(getVisitedBlock());
    }

    public CustList<BracedBlock> getBlocks() {
        return blocks;
    }

    @Override
    public void removeVarAndLoop(PageEl _ip) {
        BracedBlock cur_ = getCurentVisitedBlock();
        cur_.removeLocalVars(_ip);
        _ip.removeLastBlock();
    }
}
