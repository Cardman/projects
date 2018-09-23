package code.expressionlanguage.methods;
import code.expressionlanguage.AbstractPageEl;
import code.expressionlanguage.Analyzable;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.OffsetStringInfo;
import code.expressionlanguage.OffsetsBlock;
import code.expressionlanguage.ReadWrite;
import code.expressionlanguage.methods.util.EmptyTagName;
import code.expressionlanguage.methods.util.UnexpectedTagName;
import code.expressionlanguage.opers.ExpressionLanguage;
import code.expressionlanguage.opers.util.AssignedVariables;
import code.expressionlanguage.opers.util.AssignmentBefore;
import code.expressionlanguage.opers.util.SimpleAssignment;
import code.expressionlanguage.stacks.LoopBlockStack;
import code.util.EntryCust;
import code.util.IdMap;
import code.util.StringMap;

public final class DoBlock extends BracedStack implements Loop, IncrCurrentGroup {

    private String label;
    private int labelOffset;

    public DoBlock(ContextEl _importingPage, int _indexChild, BracedBlock _m, OffsetStringInfo _label, OffsetsBlock _offset) {
        super(_importingPage, _indexChild, _m, _offset);
        label = _label.getInfo();
        labelOffset = _label.getOffset();
    }

    @Override
    public String getLabel() {
        return label;
    }

    public int getLabelOffset() {
        return labelOffset;
    }

    @Override
    public void setAssignmentAfter(Analyzable _an, AnalyzingEl _anEl) {
        super.setAssignmentAfter(_an, _anEl);
        Block last_ = getFirstChild();
        if (last_ == null) {
            EmptyTagName un_ = new EmptyTagName();
            un_.setFileName(getFile().getFileName());
            un_.setRc(getRowCol(0, getOffset().getOffsetTrim()));
            _an.getClasses().addError(un_);
        }
        Block nextSibling_ = getNextSibling();
        if (nextSibling_ == null) {
            UnexpectedTagName un_ = new UnexpectedTagName();
            un_.setFileName(getFile().getFileName());
            un_.setRc(getRowCol(0, getOffset().getOffsetTrim()));
            _an.getClasses().addError(un_);
            return;
        }
        if (!(nextSibling_ instanceof DoWhileCondition)) {
            UnexpectedTagName un_ = new UnexpectedTagName();
            un_.setFileName(nextSibling_.getFile().getFileName());
            un_.setRc(nextSibling_.getRowCol(0, nextSibling_.getOffset().getOffsetTrim()));
            _an.getClasses().addError(un_);
        }
    }
    @Override
    public void setAssignmentBeforeNextSibling(Analyzable _an, AnalyzingEl _anEl) {
        Block last_ = getFirstChild();
        Block nextSibling_ = getNextSibling();
        AssignedVariables assBl_ = nextSibling_.buildNewAssignedVariable();
        IdMap<Block, AssignedVariables> id_ = _an.getAssignedVariables().getFinalVariables();
        if (last_ == null) {
            AssignedVariables parAss_ = id_.getVal(this);
            for (EntryCust<String, SimpleAssignment> e: parAss_.getFieldsRoot().entryList()) {
                AssignmentBefore ab_ = new AssignmentBefore();
                ab_.setAssignedBefore(true);
                ab_.setUnassignedBefore(true);
                assBl_.getFieldsRootBefore().put(e.getKey(), ab_);
            }
            for (StringMap<SimpleAssignment> s: parAss_.getVariablesRoot()) {
                StringMap<AssignmentBefore> sm_ = new StringMap<AssignmentBefore>();
                for (EntryCust<String, SimpleAssignment> e: s.entryList()) {
                    AssignmentBefore ab_ = new AssignmentBefore();
                    ab_.setAssignedBefore(true);
                    ab_.setUnassignedBefore(true);
                    sm_.put(e.getKey(), ab_);
                }
                assBl_.getVariablesRootBefore().add(sm_);
            }
            for (StringMap<SimpleAssignment> s: parAss_.getMutableLoopRoot()) {
                StringMap<AssignmentBefore> sm_ = new StringMap<AssignmentBefore>();
                for (EntryCust<String, SimpleAssignment> e: s.entryList()) {
                    AssignmentBefore ab_ = new AssignmentBefore();
                    ab_.setAssignedBefore(true);
                    ab_.setUnassignedBefore(true);
                    sm_.put(e.getKey(), ab_);
                }
                assBl_.getMutableLoopRootBefore().add(sm_);
            }
            id_.put(nextSibling_, assBl_);
            return;
        }
        assBl_.getFieldsRootBefore().putAllMap(buildAssListFieldBeforeNextSibling(_an, _anEl));
        assBl_.getVariablesRootBefore().addAllElts(buildAssListLocVarBeforeNextSibling(_an, _anEl));
        assBl_.getMutableLoopRootBefore().addAllElts(buildAssListMutableLoopBeforeNextSibling(_an, _anEl));
        id_.put(nextSibling_, assBl_);
    }
    

    @Override
    public void buildExpressionLanguage(ContextEl _cont) {
        buildEmptyEl(_cont);
    }

    @Override
    boolean canBeIncrementedNextGroup() {
        return false;
    }

    @Override
    boolean canBeIncrementedCurGroup() {
        return true;
    }

    @Override
    boolean canBeLastOfBlockGroup() {
        return false;
    }

    @Override
    public void processEl(ContextEl _cont) {
        AbstractPageEl ip_ = _cont.getLastPage();
        ReadWrite rw_ = ip_.getReadWrite();
        LoopBlockStack c_ = ip_.getLastLoopIfPossible();
        if (c_ != null && c_.getBlock() == this) {
            if (c_.isFinished()) {
                removeVarAndLoop(ip_);
                Block next_ = getNextSibling();
                rw_.setBlock(next_);
                next_.processBlock(_cont);
                return;
            }
            rw_.setBlock(getFirstChild());
            return;
        }
        LoopBlockStack l_ = new LoopBlockStack();
        l_.setBlock(this);
        ip_.addBlock(l_);
        rw_.setBlock(getFirstChild());
    }

    @Override
    public void exitStack(ContextEl _context) {
        processLastElementLoop(_context);
    }

    @Override
    public void processLastElementLoop(ContextEl _conf) {
        AbstractPageEl ip_ = _conf.getLastPage();
        ReadWrite rw_ = ip_.getReadWrite();
        rw_.setBlock(getNextSibling());
    }

    @Override
    public ExpressionLanguage getEl(ContextEl _context, boolean _native,
            int _indexProcess) {
        return null;
    }

}
