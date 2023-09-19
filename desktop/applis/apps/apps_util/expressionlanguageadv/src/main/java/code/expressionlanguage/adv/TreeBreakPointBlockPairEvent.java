package code.expressionlanguage.adv;

import code.expressionlanguage.options.ResultContext;
import code.gui.AbsShortListTree;
import code.gui.AbstractMutableTreeNodeCore;
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
    public void valueChanged(AbstractMutableTreeNodeCore<String> _node) {
        Ints is_ = new Ints();
        AbstractMutableTreeNodeCore<String> ch_ = indexes(frameKeys,_node, is_);
        int index_ = TreeBreakPointBlockPairEvent.getNullableIndex(ch_);
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
        } else if (FramePointsTree.SORT_AP == index_) {
            frame.guiContentBuild(frameKeys.getArrList().getValue(is_.get(0)).get(is_.get(1)),resultContext);
        } else if (FramePointsTree.SORT_PP == index_) {
            frame.guiContentBuild(frameKeys.getParList().getValue(is_.get(0)).get(is_.get(1)),resultContext);
        } else if (FramePointsTree.SORT_OP == index_) {
            frame.guiContentBuild(frameKeys.getOperNatList().getValue(is_.get(0)),resultContext);
        }
    }

    public static int getNullableIndex(AbstractMutableTreeNodeCore<String> _c) {
        if (_c == null) {
            return -1;
        }
        return _c.getIndex();
    }
    static AbstractMutableTreeNodeCore<String> indexes(FramePointsTree _f, AbstractMutableTreeNodeCore<String> _node, Ints _is) {
        AbstractMutableTreeNodeCore<String> ch_ = _node;
        _f.getCreate().setEnabled(false);
        while (ch_ != null) {
            AbstractMutableTreeNodeCore<String> par_ = ch_.getParent();
            if (par_ == _f.getTree().getRoot()) {
                _f.getCreate().setEnabled(ch_ == _node);
                break;
            }
            _is.add(0,ch_.getIndex());
            ch_ = par_;
        }
        if (_is.isEmpty() || ch_ == null) {
            return null;
        }
        int index_ = ch_.getIndex();
        if (FramePointsTree.SORT_MP != index_ && FramePointsTree.SORT_OP != index_ && _is.size() < 2) {
            return null;
        }
        return ch_;
    }
}
