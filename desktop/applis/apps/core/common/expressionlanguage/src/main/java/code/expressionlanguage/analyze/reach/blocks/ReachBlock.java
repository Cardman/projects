package code.expressionlanguage.analyze.reach.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.blocks.*;
import code.expressionlanguage.analyze.files.OffsetsBlock;
import code.util.StringList;

public abstract class ReachBlock {
    private Block info;
    private ReachBlock previousSibling;
    private ReachBlock nextSibling;
    private ReachBracedBlock parent;
    protected ReachBlock(Block _info) {
        info = _info;
    }

    public static ReachBlock newReachBlock(Block _info) {
        if (_info instanceof EmptyInstruction) {
            return new ReachEmptyInstruction(_info);
        }
        if (_info instanceof DeclareVariable) {
            return new ReachDeclareVariable((DeclareVariable) _info);
        }
        if (_info instanceof Line) {
            return new ReachLine((Line) _info);
        }
        if (_info instanceof BreakBlock) {
            return new ReachBreakBlock((BreakBlock) _info);
        }
        if (_info instanceof ContinueBlock) {
            return new ReachContinueBlock((ContinueBlock) _info);
        }
        if (_info instanceof ReturnMethod) {
            return new ReachReturnMethod((ReturnMethod) _info);
        }
        if (_info instanceof Throwing) {
            return new ReachThrowing((Throwing) _info);
        }
        if (_info instanceof CaseCondition) {
            return new ReachCaseCondition((CaseCondition) _info);
        }
        if (_info instanceof DefaultCondition) {
            return new ReachDefaultCondition((DefaultCondition) _info);
        }
        if (_info instanceof SwitchBlock) {
            return new ReachSwitchBlock((SwitchBlock) _info);
        }
        if (_info instanceof IfCondition) {
            return new ReachIfCondition((IfCondition) _info);
        }
        if (_info instanceof ElseIfCondition) {
            return new ReachElseIfCondition((ElseIfCondition) _info);
        }
        if (_info instanceof ElseCondition) {
            return new ReachElseCondition((ElseCondition) _info);
        }
        if (_info instanceof TryEval) {
            return new ReachTryEval((TryEval) _info);
        }
        if (_info instanceof NullCatchEval) {
            return new ReachNullCatchEval((NullCatchEval) _info);
        }
        if (_info instanceof CatchEval) {
            return new ReachCatchEval((CatchEval) _info);
        }
        if (_info instanceof FinallyEval) {
            return new ReachFinallyEval((FinallyEval) _info);
        }
        if (_info instanceof DoBlock) {
            return new ReachDoBlock((DoBlock) _info);
        }
        if (_info instanceof DoWhileCondition) {
            return new ReachDoWhileCondition((DoWhileCondition) _info);
        }
        if (_info instanceof UnclassedBracedBlock) {
            return new ReachUnclassedBracedBlock(_info);
        }
        if (_info instanceof WhileCondition) {
            return new ReachWhileCondition((WhileCondition) _info);
        }
        if (_info instanceof ForIterativeLoop) {
            return new ReachForIterativeLoop((ForIterativeLoop) _info);
        }
        if (_info instanceof ForMutableIterativeLoop) {
            return new ReachForMutableIterativeLoop((ForMutableIterativeLoop) _info);
        }
        if (_info instanceof ForEachLoop) {
            return new ReachForEachLoop((ForEachLoop) _info);
        }
        if (_info instanceof ForEachTable) {
            return new ReachForEachTable((ForEachTable) _info);
        }
        return new ReachRootBlock(_info);
    }
    public static ReachMemberCallingsBlock newReachBlock(MemberCallingsBlock _info) {
        if (_info instanceof ConstructorBlock) {
            return new ReachConstructorBlock((ConstructorBlock) _info);
        }
        if (_info instanceof NamedFunctionBlock) {
            return new ReachStdNamedFunctionBlock((NamedFunctionBlock) _info);
        }
        return new ReachInitFunctionBlock(_info);
    }
    public void reach(AnalyzingEl _anEl, AnalyzedPageEl _page) {
        ReachBlock prev_ = getPreviousSibling();
        if (_anEl.canCompleteNormallyGroup(prev_)) {
            _anEl.reach(this);
        } else {
            _anEl.unreach(this);
        }
    }

    public boolean accessibleCondition() {
        return true;
    }

    public boolean accessibleForNext() {
        return true;
    }

    public abstract void abrupt(AnalyzingEl _anEl);
    public abstract ReachBlock getFirstChild();
    public Block getInfo() {
        return info;
    }
    public OffsetsBlock getOffset() {
        return info.getOffset();
    }
    public FileBlock getFile() {
        return info.getFile();
    }

    public StringList getErrorsBlock() {
        return info.getErrorsBlock();
    }

    public void setReachableError(boolean _reachableError) {
        info.setReachableError(_reachableError);
    }

    public ReachBracedBlock getParent() {
        return parent;
    }

    public void setParent(ReachBracedBlock parent) {
        this.parent = parent;
    }

    public ReachBlock getNextSibling() {
        return nextSibling;
    }

    public void setNextSibling(ReachBlock nextSibling) {
        this.nextSibling = nextSibling;
    }

    public ReachBlock getPreviousSibling() {
        return previousSibling;
    }

    public void setPreviousSibling(ReachBlock previousSibling) {
        this.previousSibling = previousSibling;
    }
}
