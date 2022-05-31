package code.expressionlanguage.analyze.assign.blocks;

import code.expressionlanguage.analyze.blocks.ContinueBlock;
import code.util.core.StringUtil;

public final class AssContinueBlock extends AssAbruptBlock {
    private final String label;
    AssContinueBlock(boolean _completeNormally, boolean _completeNormallyGroup, ContinueBlock _c) {
        super(_completeNormally,_completeNormallyGroup);
        label = _c.getLabel();
    }
    AssBracedBlock directAsc() {
        AssBracedBlock b_ = getParent();
        boolean childOfLoop_ = false;
        while (b_ != null) {
            if (isLoop(b_) && (label.isEmpty() || StringUtil.quickEq(label, ((AssBreakableBlock) b_).getRealLabel()))) {
                childOfLoop_ = true;
                break;
            }
            b_ = b_.getParent();
        }
        if (!childOfLoop_) {
            return null;
        }
        return b_;
    }
    static boolean isLoop(AssBracedBlock _b) {
        return _b instanceof AssDoBlock||_b instanceof AssForEach||_b instanceof AssForIterativeLoop||_b instanceof AssForMutableIterativeLoop||_b instanceof AssWhileCondition;
    }
}
