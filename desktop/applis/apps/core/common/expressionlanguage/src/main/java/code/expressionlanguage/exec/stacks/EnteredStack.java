package code.expressionlanguage.exec.stacks;

import code.expressionlanguage.exec.blocks.ExecBracedBlock;

public interface EnteredStack extends RemovableVars, MultipleBlockStack {
    ExecBracedBlock getBlock();
    boolean isEntered();
    void setEntered(boolean _entered);
}
