package code.expressionlanguage.adv;

import code.gui.AbsShortListTree;
import code.gui.AbstractMutableTreeNodeCore;
import code.util.Ints;

public final class TreeRenderPointBlockPairEvent implements AbsShortListTree {
    private final FramePoints frame;

    public TreeRenderPointBlockPairEvent(FramePoints _f) {
        this.frame = _f;
    }

    @Override
    public void valueChanged(AbstractMutableTreeNodeCore<String> _node) {
        Ints is_ = new Ints();
        TreeBreakPointBlockPairEvent.indexes(frame.getTree(),null,_node, is_);
        if (is_.size() < 2) {
            return;
        }
        frame.guiContentBuild(frame.getRenderList().getValue(is_.get(0)).get(is_.get(1)));
    }

}
