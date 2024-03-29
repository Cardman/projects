package code.expressionlanguage.adv;

import code.gui.AbsShortListTree;
import code.gui.AbstractMutableTreeNodeCore;
import code.util.Ints;

public final class TreeBreakPointBlockPairDepsEvent implements AbsShortListTree {
    private final DependantPointsForm frame;
    private final FramePointsTree frameKeys;

    public TreeBreakPointBlockPairDepsEvent(DependantPointsForm _f, FramePointsTree _k) {
        this.frame = _f;
        this.frameKeys = _k;
    }

    @Override
    public void valueChanged(AbstractMutableTreeNodeCore<String> _node) {
        Ints is_ = new Ints();
        AbstractMutableTreeNodeCore<String> ch_ = TreeBreakPointBlockPairEvent.indexes(frameKeys,_node, is_);
        int index_ = TreeBreakPointBlockPairEvent.getNullableIndex(ch_);
        if (FramePointsTree.SORT_BP == index_){
            frame.guiContentBuild(frameKeys.getBpList().getValue(is_.get(0)).get(is_.get(1)));
        } else if (FramePointsTree.SORT_WP == index_) {
            frame.guiContentBuild(frameKeys.getWpList().getValue(is_.get(0)).get(is_.get(1)));
        } else if (FramePointsTree.SORT_WP_ANNOT == index_) {
            frame.guiContentBuild(frameKeys.getWpListAnnot().getValue(is_.get(0)).get(is_.get(1)));
        } else if (FramePointsTree.SORT_EP == index_) {
            frame.guiContentBuild(frameKeys.getExcList().getValue(is_.get(0)).get(is_.get(1)));
        } else if (FramePointsTree.SORT_MP == index_) {
            frame.guiContentBuild(frameKeys.getMetList().getValue(is_.get(0)));
        } else if (FramePointsTree.SORT_SP == index_) {
            frame.guiContentBuild(frameKeys.getStdList().getValue(is_.get(0)).get(is_.get(1)));
        } else if (FramePointsTree.SORT_AP == index_) {
            frame.guiContentBuild(frameKeys.getArrList().getValue(is_.get(0)).get(is_.get(1)));
        } else if (FramePointsTree.SORT_PP == index_) {
            frame.guiContentBuild(frameKeys.getParList().getValue(is_.get(0)).get(is_.get(1)));
        } else if (FramePointsTree.SORT_OP == index_) {
            frame.guiContentBuildNotCompo(frameKeys.getOperNatList().getValue(is_.get(0)));
         } else if (FramePointsTree.SORT_CP == index_) {
            frame.guiContentBuild(frameKeys.getOperNatCompoList().getValue(is_.get(0)));
        } else if (FramePointsTree.SORT_TP == index_) {
            frame.guiContentBuild(frameKeys.getTpList().getValue(is_.get(0)).get(is_.get(1)));
        }
    }
}
