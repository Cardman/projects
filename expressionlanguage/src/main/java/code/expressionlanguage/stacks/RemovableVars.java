package code.expressionlanguage.stacks;
import code.expressionlanguage.calls.AbstractPageEl;
import code.expressionlanguage.methods.BracedBlock;

public interface RemovableVars {

    String getInfos();

    void removeVarAndLoop(AbstractPageEl _ip);

    BracedBlock getBlock();
    BracedBlock getCurrentVisitedBlock();
    BracedBlock getLastBlock();

    void setBlock(BracedBlock _block);
}
