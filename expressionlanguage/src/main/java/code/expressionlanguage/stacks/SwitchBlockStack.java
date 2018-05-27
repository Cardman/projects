package code.expressionlanguage.stacks;
import code.expressionlanguage.AbstractPageEl;
import code.expressionlanguage.methods.BracedBlock;
import code.util.CustList;

public final class SwitchBlockStack extends SwitchStack implements BreakableBlockStack, RemovableVars {

    private final CustList<BracedBlock> blocks = new CustList<BracedBlock>();

    private BracedBlock block;

    @Override
    public BracedBlock getBlock() {
        return block;
    }

    @Override
    public void setBlock(BracedBlock _block) {
        block = _block;
    }
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
    public void removeVarAndLoop(AbstractPageEl _ip) {
        BracedBlock cur_ = getCurentVisitedBlock();
        cur_.removeLocalVars(_ip);
        _ip.removeLastBlock();
    }

    @Override
    public BracedBlock getLastBlock() {
        return getBlock();
    }

    @Override
    public BracedBlock getCurrentVisitedBlock() {
        return getBlock();
    }

    @Override
    public String getInfos() {
        // TODO Auto-generated method stub
        return null;
    }
}
