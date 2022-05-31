package code.expressionlanguage.analyze.assign.blocks;

import code.expressionlanguage.analyze.blocks.BreakBlock;
import code.util.core.StringUtil;

public final class AssBreakBlock extends AssAbruptBlock {
    private final String label;
    AssBreakBlock(boolean _completeNormally, boolean _completeNormallyGroup, BreakBlock _b) {
        super(_completeNormally,_completeNormallyGroup);
        label = _b.getLabel();
    }
    AssBracedBlock directAsc() {
        boolean childOfBreakable_ = false;
        AssBracedBlock b_ = getParent();
        while (b_ != null) {
            if (exit(b_)) {
                childOfBreakable_ = true;
                break;
            }
            b_ = b_.getParent();
        }
        if (!childOfBreakable_) {
            return null;
        }
        return b_;
    }
    private boolean exit(AssBracedBlock _b) {
        if (_b instanceof AssBreakableBlock) {
            if (label.isEmpty()) {
                return AssContinueBlock.isLoop(_b) || _b instanceof AssSwitchBlock;
            }
            return StringUtil.quickEq(label, ((AssBreakableBlock) _b).getRealLabel());
        }
        return false;
    }
}
