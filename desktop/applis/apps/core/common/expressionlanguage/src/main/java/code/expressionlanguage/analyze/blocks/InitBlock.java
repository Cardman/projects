package code.expressionlanguage.analyze.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.files.OffsetsBlock;

public abstract class InitBlock extends MemberCallingsBlock {

    public InitBlock(OffsetsBlock _offset) {
        super(_offset);
    }

    @Override
    public void setAssignmentAfterCallReadOnly(ContextEl _an, AnalyzingEl _anEl) {
    }

}
