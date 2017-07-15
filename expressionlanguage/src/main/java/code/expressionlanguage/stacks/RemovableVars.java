package code.expressionlanguage.stacks;
import code.expressionlanguage.PageEl;
import code.expressionlanguage.methods.BracedBlock;

public interface RemovableVars {

    void removeVarAndLoop(PageEl _ip);

    BracedBlock getBlock();

    void setBlock(BracedBlock _block);
}
