package code.expressionlanguage.exec.stacks;

import code.expressionlanguage.exec.blocks.ExecBracedBlock;

public interface ConditionBlockStack extends MultipleBlockStack {
    ExecBracedBlock getBlock();
}
