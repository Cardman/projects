package code.expressionlanguage.assign.blocks;

import code.expressionlanguage.analyze.blocks.BreakBlock;
import code.expressionlanguage.exec.blocks.ExecBreakBlock;
import code.util.StringList;

public final class AssBreakBlock extends AssAbruptBlock {
    private String label;
    AssBreakBlock(boolean _completeNormally, boolean _completeNormallyGroup, BreakBlock _b) {
        super(_completeNormally,_completeNormallyGroup);
        label = _b.getLabel();
    }
    AssBracedBlock directAsc() {
        boolean childOfBreakable_ = false;
        AssBracedBlock b_ = getParent();
        while (b_ != null) {
            if (b_ instanceof AssBreakableBlock) {
                if (label.isEmpty()) {
                    if (b_ instanceof AssLoop || b_ instanceof AssSwitchBlock) {
                        childOfBreakable_ = true;
                        break;
                    }
                } else {
                    if (StringList.quickEq(label, ((AssBreakableBlock)b_).getRealLabel())){
                        childOfBreakable_ = true;
                        break;
                    }
                }
            }
            b_ = b_.getParent();
        }
        if (!childOfBreakable_) {
            return null;
        }
        return b_;
    }
}
