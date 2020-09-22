package code.expressionlanguage.analyze.assign.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.assign.util.AssignedVariablesBlock;

public interface AssInfoBlock extends AssBuildableElMethod {

    void setAssignmentBeforeAsLeaf(AssignedVariablesBlock _a, AssBlock _b);
    void setAssignmentAfterAsLeaf(AssignedVariablesBlock _a, AssBlock _b, AnalyzedPageEl _page);
}
