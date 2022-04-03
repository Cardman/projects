package code.bean.nat.exec;

import code.formathtml.exec.blocks.RendParentBlock;

public class NatIfStack implements NatAbstractStask {

    private RendParentBlock natBlock;
    private RendParentBlock natLastBlock;
    private RendParentBlock natCurentVisitedBlock;

    private boolean ent;

    public boolean isEntered() {
        return ent;
    }

    public void setEntered(boolean _entered) {
        ent = _entered;
    }

    public RendParentBlock getBlock() {
        return natBlock;
    }

    public void setBlock(RendParentBlock _block) {
        natBlock = _block;
    }

    public RendParentBlock getLastBlock() {
        return natLastBlock;
    }

    @Override
    public void setCurrentVisitedBlock(RendParentBlock _bl) {
        natCurentVisitedBlock = _bl;
    }

    public void setLastBlock(RendParentBlock _lastBlock) {
        natLastBlock = _lastBlock;
    }

    @Override
    public RendParentBlock getCurrentVisitedBlock() {
        return natCurentVisitedBlock;
    }
}
