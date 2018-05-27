package code.expressionlanguage.stacks;
import code.expressionlanguage.AbstractPageEl;
import code.expressionlanguage.methods.BracedBlock;
import code.util.CustList;


public final class IfBlockStack extends IfStack implements BreakableBlockStack, RemovableVars {

    private final CustList<BracedBlock> blocks = new CustList<BracedBlock>();

    private BracedBlock block;

    private boolean finished;

    @Override
    public BracedBlock getBlock() {
        return block;
    }

    @Override
    public void setBlock(BracedBlock _block) {
        block = _block;
    }
    @Override
    public BracedBlock getLastBlock() {
        return lastVisitedBlock();
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
    public String getInfos() {
        // TODO Auto-generated method stub
        return null;
    }

    public boolean isFinished() {
        return finished;
    }

    @Override
    public void setFinished(boolean _finished) {
        finished = _finished;
    }

    @Override
    public BracedBlock getCurrentVisitedBlock() {
        return blocks.get(getVisitedBlock());
    }
}
