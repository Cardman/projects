package code.expressionlanguage.adv;

import code.expressionlanguage.options.ResultContext;
import code.gui.AbsShortListTree;
import code.gui.AbstractMutableTreeNodeCore;
import code.gui.MutableTreeNodeCoreUtil;
import code.util.Ints;

public final class TreeBreakPointBlockPairEvent implements AbsShortListTree {
    private final FramePoints frame;
    private final FramePointsTree frameKeys;
    private final ResultContext resultContext;

    public TreeBreakPointBlockPairEvent(FramePoints _f, FramePointsTree _k, ResultContext _r) {
        this.frame = _f;
        this.frameKeys = _k;
        this.resultContext = _r;
    }

    @Override
    public void valueChanged(AbstractMutableTreeNodeCore _node) {
        Ints is_ = new Ints();
        AbstractMutableTreeNodeCore ch_ = indexes(frameKeys,_node, is_);
        int index_ = MutableTreeNodeCoreUtil.getNullableIndex(ch_);
        if (FramePointsTree.SORT_BP == index_){
            frame.guiContentBuild(frameKeys.getBpList().getValue(is_.get(0)).get(is_.get(1)),resultContext);
        } else if (FramePointsTree.SORT_WP == index_) {
            frame.guiContentBuild(frameKeys.getWpList().getValue(is_.get(0)).get(is_.get(1)),resultContext);
        } else if (FramePointsTree.SORT_WP_ANNOT == index_) {
            frame.guiContentBuild(frameKeys.getWpListAnnot().getValue(is_.get(0)).get(is_.get(1)),resultContext);
        } else if (FramePointsTree.SORT_EP == index_) {
            frame.guiContentBuild(frameKeys.getExcList().getValue(is_.get(0)).get(is_.get(1)),resultContext);
        } else if (FramePointsTree.SORT_MP == index_) {
            frame.guiContentBuild(frameKeys.getMetList().getValue(is_.get(0)),resultContext);
        } else if (FramePointsTree.SORT_SP == index_) {
            frame.guiContentBuild(frameKeys.getStdList().getValue(is_.get(0)).get(is_.get(1)),resultContext);
        }
    }
    static AbstractMutableTreeNodeCore indexes(FramePointsTree _f, AbstractMutableTreeNodeCore _node, Ints _is) {
        AbstractMutableTreeNodeCore ch_ = _node;
        _f.getCreate().setEnabled(false);
        while (ch_ != null) {
            AbstractMutableTreeNodeCore par_ = ch_.getParent();
            if (par_ == _f.getTree().getRoot()) {
                _f.getCreate().setEnabled(ch_ == _node);
                break;
            }
            _is.add(0,MutableTreeNodeCoreUtil.getNullableIndex(ch_));
            ch_ = par_;
        }
        if (_is.isEmpty()) {
            return null;
        }
        int index_ = MutableTreeNodeCoreUtil.getNullableIndex(ch_);
        if (FramePointsTree.SORT_MP != index_ && _is.size() < 2) {
            return null;
        }
        return ch_;
    }
}
