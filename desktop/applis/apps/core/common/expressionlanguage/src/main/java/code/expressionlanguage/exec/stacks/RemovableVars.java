package code.expressionlanguage.exec.stacks;
import code.expressionlanguage.exec.blocks.ExecBracedBlock;

public interface RemovableVars {

    ExecBracedBlock getCurrentVisitedBlock();

    String getLabel();
}
