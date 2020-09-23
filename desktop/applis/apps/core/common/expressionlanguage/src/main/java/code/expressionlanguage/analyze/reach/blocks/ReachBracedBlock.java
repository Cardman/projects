package code.expressionlanguage.analyze.reach.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.blocks.*;
import code.util.CustList;
import code.util.EntryCust;
import code.util.IdMap;

public abstract class ReachBracedBlock extends ReachBlock {
    private ReachBlock firstChild;
    protected ReachBracedBlock(Block _info) {
        super(_info);
    }

    public final void appendChild(ReachBlock _child) {
        _child.setParent(this);
        if (firstChild == null) {
            firstChild = _child;
            return;
        }
        ReachBlock child_ = firstChild;
        while (true) {
            ReachBlock sibling_ = child_.getNextSibling();
            if (sibling_ == null) {
                _child.setPreviousSibling(child_);
                child_.setNextSibling(_child);
                return;
            }
            child_ = sibling_;
        }
    }

    public static CustList<ReachBlock> getDirectChildren(ReachBlock _element) {
        CustList<ReachBlock> list_ = new CustList<ReachBlock>();
        ReachBlock elt_ = _element.getFirstChild();
        while (elt_ != null) {
            list_.add(elt_);
            elt_ = elt_.getNextSibling();
        }
        return list_;
    }
    @Override
    public void abrupt(AnalyzingEl _anEl) {
        ReachBlock ch_ = getFirstChild();
        if (ch_ == null) {
            if (!_anEl.isReachable(this)) {
                _anEl.completeAbruptGroup(this);
            }
            return;
        }
        while (ch_.getNextSibling() != null) {
            ch_ = ch_.getNextSibling();
        }
        IdMap<ReachBreakBlock, ReachBreakableBlock> breakables_ = _anEl.getReachBreakables();
        boolean exist_ = false;
        for (EntryCust<ReachBreakBlock, ReachBreakableBlock> b: breakables_.entryList()) {
            if (b.getValue() == this) {
                if (_anEl.isReachable(b.getKey())) {
                    exist_ = true;
                }
            }
        }
        //parent breakable
        if (!_anEl.canCompleteNormallyGroup(ch_) && !exist_) {
            _anEl.completeAbrupt(this);
        }
    }

    protected CustList<ReachBlock> getConditionBlocks() {
        CustList<ReachBlock> group_ = new CustList<ReachBlock>();
        group_.add(this);
        ReachBlock p_ = getPreviousSibling();
        while (!(p_ instanceof ReachIfCondition)) {
            if (p_ == null) {
                break;
            }
            group_.add(p_);
            p_ = p_.getPreviousSibling();
        }
        if (p_ != null) {
            group_.add(p_);
        }
        return group_;
    }

    protected CustList<ReachBlock> getTryBlocks() {
        CustList<ReachBlock> group_ = new CustList<ReachBlock>();
        ReachBlock p_ = getPreviousSibling();
        while (!(p_ instanceof ReachTryEval)) {
            if (p_ == null) {
                break;
            }
            group_.add(p_);
            p_ = p_.getPreviousSibling();
        }
        if (p_ != null) {
            group_.add(p_);
        }
        return group_;
    }

    public void abruptGroup(AnalyzingEl _anEl) {
    }

    @Override
    public final ReachBlock getFirstChild() {
        return firstChild;
    }


    @Override
    public void reach(AnalyzingEl _anEl, AnalyzedPageEl _page) {
        ReachBlock prev_ = getPreviousSibling();
        ReachBracedBlock br_ = getParent();
        if (prev_ == null) {
            if (_anEl.isReachable(br_) && br_.accessibleCondition()) {
                _anEl.reach(this);
            } else {
                _anEl.unreach(this);
            }
        } else {
            super.reach(_anEl, _page);
        }
    }

}
