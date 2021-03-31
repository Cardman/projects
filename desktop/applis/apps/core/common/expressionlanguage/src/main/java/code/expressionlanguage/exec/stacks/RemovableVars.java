package code.expressionlanguage.exec.stacks;
import code.expressionlanguage.exec.blocks.ExecBracedBlock;

public interface RemovableVars {

    void setCurrentVisitedBlock(ExecBracedBlock _bl);
    ExecBracedBlock getCurrentVisitedBlock();

    String getLabel();
}
