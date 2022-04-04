package code.bean.nat.exec;

import code.bean.nat.exec.blocks.NatParentBlock;

public class NatIfStack implements NatAbstractStask {

    private NatParentBlock natBlock;
    private NatParentBlock natLastBlock;
    private NatParentBlock natCurentVisitedBlock;

    private boolean ent;

    public boolean isEntered() {
        return ent;
    }

    public void setEntered(boolean _entered) {
        ent = _entered;
    }

    public NatParentBlock getBlock() {
        return natBlock;
    }

    public void setBlock(NatParentBlock _block) {
        natBlock = _block;
    }

    public NatParentBlock getLastBlock() {
        return natLastBlock;
    }

    @Override
    public void setCurrentVisitedBlock(NatParentBlock _bl) {
        natCurentVisitedBlock = _bl;
    }

    public void setLastBlock(NatParentBlock _lastBlock) {
        natLastBlock = _lastBlock;
    }

    @Override
    public NatParentBlock getCurrentVisitedBlock() {
        return natCurentVisitedBlock;
    }
}
