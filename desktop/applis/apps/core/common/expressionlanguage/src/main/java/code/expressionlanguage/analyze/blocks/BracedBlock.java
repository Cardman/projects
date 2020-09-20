package code.expressionlanguage.analyze.blocks;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.errors.custom.GraphicErrorList;
import code.expressionlanguage.files.OffsetsBlock;
import code.util.CustList;
import code.util.EntryCust;
import code.util.IdMap;

public abstract class BracedBlock extends Block {

    private Block firstChild;

    private GraphicErrorList globalErrorsPars = new GraphicErrorList();
    BracedBlock(OffsetsBlock _offset) {
        super(_offset);
    }

    public final void appendChild(Block _child) {
        _child.setParent(this);
        FileBlock file_ = getFile();
        if (file_ != null) {
            _child.setFile(file_);
        }
        if (firstChild == null) {
            firstChild = _child;
            return;
        }
        Block child_ = firstChild;
        while (true) {
            Block sibling_ = child_.getNextSibling();
            if (sibling_ == null) {
                _child.setPreviousSibling(child_);
                child_.setNextSibling(_child);
                return;
            }
            child_ = sibling_;
        }
    }

    @Override
    public void abrupt(AnalyzingEl _anEl) {
        Block ch_ = getFirstChild();
        if (ch_ == null) {
            if (!_anEl.isReachable(this)) {
                _anEl.completeAbruptGroup(this);
            }
            return;
        }
        while (ch_.getNextSibling() != null) {
            ch_ = ch_.getNextSibling();
        }
        IdMap<BreakBlock, BreakableBlock> breakables_ = _anEl.getBreakables();
        boolean exist_ = false;
        for (EntryCust<BreakBlock, BreakableBlock> b: breakables_.entryList()) {
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

    protected CustList<Block> getConditionBlocks() {
        CustList<Block> group_ = new CustList<Block>();
        group_.add(this);
        Block p_ = getPreviousSibling();
        while (!(p_ instanceof IfCondition)) {
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

    protected CustList<Block> getTryBlocks() {
        CustList<Block> group_ = new CustList<Block>();
        Block p_ = getPreviousSibling();
        while (!(p_ instanceof TryEval)) {
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
    @Override
    public void checkTree(AnalyzingEl _anEl, AnalyzedPageEl _page) {
    }

    public void abruptGroup(AnalyzingEl _anEl) {
    }

    @Override
    public final Block getFirstChild() {
        return firstChild;
    }


    @Override
    public void reach(AnalyzingEl _anEl, AnalyzedPageEl _page) {
        Block prev_ = getPreviousSibling();
        BracedBlock br_ = getParent();
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

    public void removeAllVars(AnalyzedPageEl _ip) {
        removeLocalVars(_ip);
    }

    public final void removeLocalVars(AnalyzedPageEl _ip) {
        for (Block s: ClassesUtil.getDirectChildren(this)) {
            if (s instanceof DeclareVariable) {
                for (String v: ((DeclareVariable)s).getVariableNames()) {
                    _ip.getInfosVars().removeKey(v);
                }
            }
        }
    }

    public GraphicErrorList getGlobalErrorsPars() {
        return globalErrorsPars;
    }
}
