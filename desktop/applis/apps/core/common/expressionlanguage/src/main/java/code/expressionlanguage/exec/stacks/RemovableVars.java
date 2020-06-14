package code.expressionlanguage.exec.stacks;
import code.expressionlanguage.methods.BracedBlock;

public interface RemovableVars {

    BracedBlock getBlock();
    void setCurrentVisitedBlock(BracedBlock _bl);
    BracedBlock getCurrentVisitedBlock();
    BracedBlock getLastBlock();

}
