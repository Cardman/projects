package code.expressionlanguage.methods;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.files.OffsetsBlock;
import code.util.EntryCust;
import code.util.IdMap;

public abstract class BracedBlock extends Block implements BracedBlockInt {

    private Block firstChild;

    BracedBlock(OffsetsBlock _offset) {
        super(_offset);
    }

    public final void appendChild(Block _child) {
        _child.setParent(this);
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
    public void abrupt(ContextEl _an, AnalyzingEl _anEl) {
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

    @Override
    public void checkTree(ContextEl _an, AnalyzingEl _anEl) {
    }

    public void abruptGroup(AnalyzingEl _anEl) {
    }

    public void exitStack(ContextEl _context){
    }
    @Override
    public final Block getFirstChild() {
        return firstChild;
    }

    public final void removeLocalVars(AbstractPageEl _ip) {
        for (Block s: Classes.getDirectChildren(this)) {
            if (s instanceof DeclareVariable) {
                for (String v: ((DeclareVariable)s).getVariableNames()) {
                    _ip.removeLocalVar(v);
                }
            }
        }
    }

    @Override
    public void reach(ContextEl _an, AnalyzingEl _anEl) {
        Block prev_ = getPreviousSibling();
        BracedBlock br_ = getParent();
        if (prev_ == null) {
            if (_anEl.isReachable(br_) && br_.accessibleCondition()) {
                _anEl.reach(this);
            } else {
                _anEl.unreach(this);
            }
        } else {
            super.reach(_an, _anEl);
        }
    }

    public void removeAllVars(AbstractPageEl _ip) {
        removeLocalVars(_ip);
    }

}
