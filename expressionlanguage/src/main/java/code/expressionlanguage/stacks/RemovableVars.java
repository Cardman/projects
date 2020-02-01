package code.expressionlanguage.stacks;
import code.expressionlanguage.methods.BracedBlock;

public interface RemovableVars {

    BracedBlock getBlock();
    BracedBlock getCurrentVisitedBlock();
    BracedBlock getLastBlock();

}
