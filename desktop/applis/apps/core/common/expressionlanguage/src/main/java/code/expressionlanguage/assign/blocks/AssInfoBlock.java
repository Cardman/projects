package code.expressionlanguage.assign.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.assign.util.AssignedVariablesBlock;

public interface AssInfoBlock extends AssBuildableElMethod {

    void setAssignmentBeforeAsLeaf(ContextEl _an, AssignedVariablesBlock _a, AssBlock _b);
    void setAssignmentAfterAsLeaf(ContextEl _an, AssignedVariablesBlock _a, AssBlock _b);
}
