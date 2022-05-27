package code.expressionlanguage.analyze.reach.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.blocks.AnalyzingEl;

public interface ReachMemberCallingsBlockSide {
    void setAssignmentAfterCallReadOnly(AnalyzingEl _anEl, AnalyzedPageEl _page);
}
