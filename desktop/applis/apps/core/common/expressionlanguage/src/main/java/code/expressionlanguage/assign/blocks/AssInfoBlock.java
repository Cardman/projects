package code.expressionlanguage.assign.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.assign.util.AssignedVariablesBlock;

public interface AssInfoBlock extends AssBuildableElMethod {

    void setAssignmentBeforeAsLeaf(AssignedVariablesBlock _a, AssBlock _b);
    void setAssignmentAfterAsLeaf(AssignedVariablesBlock _a, AssBlock _b, AnalyzedPageEl _page);
}
