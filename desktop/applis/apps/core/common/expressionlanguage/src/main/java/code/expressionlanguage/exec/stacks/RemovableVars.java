package code.expressionlanguage.exec.stacks;
import code.expressionlanguage.exec.blocks.ExecBracedBlock;

public interface RemovableVars {

    ExecBracedBlock getBlock();
    void setCurrentVisitedBlock(ExecBracedBlock _bl);
    ExecBracedBlock getCurrentVisitedBlock();
    ExecBracedBlock getLastBlock();

    String getLabel();
}
