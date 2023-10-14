package code.expressionlanguage.exec.stacks;

import code.expressionlanguage.exec.blocks.ExecBracedBlock;
import code.util.IdList;

public interface EnteredStack extends RemovableVars, MultipleBlockStack {
    ExecBracedBlock getBlock();
    IdList<ExecBracedBlock> getAllBlocks();
    boolean isEntered();
    void setEntered(boolean _entered);
}
