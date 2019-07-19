package code.formathtml.util;
import code.expressionlanguage.stacks.IfStack;
import code.formathtml.ImportingPage;
import code.formathtml.RendBlock;
import code.formathtml.RendParentBlock;
import code.sml.Element;
import code.sml.Node;
import code.util.CustList;

public final class RendIfStack extends IfStack implements RendRemovableVars {

    private RendParentBlock block;
    private RendParentBlock lastBlock;
    private RendParentBlock curentVisitedBlock;

    @Override
    public RendParentBlock getBlock() {
        return block;
    }

    public void setBlock(RendParentBlock _block) {
        block = _block;
    }
    @Override
    public RendParentBlock getLastBlock() {
        return lastBlock;
    }
    public void setCurentVisitedBlock(RendParentBlock _curentVisitedBlock) {
        curentVisitedBlock = _curentVisitedBlock;
    }

    public void setLastBlock(RendParentBlock _lastBlock) {
        lastBlock = _lastBlock;
    }

    @Override
    public void removeVarAndLoop(ImportingPage _ip) {
        RendParentBlock cur_ = getCurrentVisitedBlock();
        cur_.removeLocalVars(_ip);
        _ip.removeLastBlock();
    }

    @Override
    public RendParentBlock getCurrentVisitedBlock() {
        return curentVisitedBlock;
    }}
