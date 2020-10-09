package code.expressionlanguage.analyze.assign.blocks;

import code.expressionlanguage.analyze.blocks.ContinueBlock;
import code.util.core.StringUtil;

public final class AssContinueBlock extends AssAbruptBlock {
    private String label;
    AssContinueBlock(boolean _completeNormally, boolean _completeNormallyGroup, ContinueBlock _c) {
        super(_completeNormally,_completeNormallyGroup);
        label = _c.getLabel();
    }
    AssBracedBlock directAsc() {
        AssBracedBlock b_ = getParent();
        boolean childOfLoop_ = false;
        while (b_ != null) {
            if (b_ instanceof AssLoop) {
                if (label.isEmpty()) {
                    childOfLoop_ = true;
                    break;
                }
                if (StringUtil.quickEq(label, ((AssBreakableBlock)b_).getRealLabel())){
                    childOfLoop_ = true;
                    break;
                }
            }
            b_ = b_.getParent();
        }
        if (!childOfLoop_) {
            return null;
        }
        return b_;
    }
}
