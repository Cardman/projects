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
import code.expressionlanguage.opers.util.Assignment;
import code.expressionlanguage.opers.util.AssignmentBefore;
import code.expressionlanguage.opers.util.ClassField;
import code.expressionlanguage.opers.util.SimpleAssignment;
import code.expressionlanguage.stacks.TryBlockStack;
import code.sml.Element;
import code.util.CustList;
import code.util.EntryCust;
import code.util.IdMap;
import code.util.StringMap;

public final class TryEval extends BracedStack implements Eval, IncrCurrentGroup {


    private String label;
    private int labelOffset;

    public TryEval(Element _el, ContextEl _importingPage, int _indexChild,
            BracedBlock _m) {
        super(_el, _importingPage, _indexChild, _m);
    }

    public TryEval(ContextEl _importingPage, int _indexChild, BracedBlock _m, OffsetStringInfo _label, OffsetsBlock _offset) {
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
    public void buildExpressionLanguage(ContextEl _cont) {
        AssignedVariablesBlock glAss_ = _cont.getAssignedVariables();
        AssignedVariables ass_ = glAss_.getFinalVariables().getVal(this);
        for (EntryCust<ClassField,AssignmentBefore> e: ass_.getFieldsRootBefore().entryList()) {
            ClassField key_ = e.getKey();
            ass_.getFieldsRoot().put(key_, e.getValue().assignAfterClassic());
        }
        for (StringMap<AssignmentBefore> s: ass_.getVariablesRootBefore()) {
            StringMap<SimpleAssignment> vars_ = new StringMap<SimpleAssignment>();
            for (EntryCust<String,AssignmentBefore> e: s.entryList()) {
                vars_.put(e.getKey(), e.getValue().assignAfterClassic());
            }
            ass_.getVariablesRoot().add(vars_);
        }
    }
    @Override
    public void setAssignmentBeforeChild(Analyzable _an, AnalyzingEl _anEl) {
        Block firstChild_ = getFirstChild();
        IdMap<Block, AssignedVariables> id_ = _an.getAssignedVariables().getFinalVariables();
        AssignedVariables parAss_ = id_.getVal(this);
        AssignedVariables assBl_ = firstChild_.buildNewAssignedVariable();
        for (EntryCust<ClassField, SimpleAssignment> e: parAss_.getFieldsRoot().entryList()) {
            SimpleAssignment ba_ = e.getValue();
            assBl_.getFieldsRootBefore().put(e.getKey(), ba_.assignBefore());
        }
        for (StringMap<SimpleAssignment> s: parAss_.getVariablesRoot()) {
            StringMap<AssignmentBefore> sm_ = new StringMap<AssignmentBefore>();
            for (EntryCust<String, SimpleAssignment> e: s.entryList()) {
                SimpleAssignment ba_ = e.getValue();
                sm_.put(e.getKey(), ba_.assignBefore());
            }
            assBl_.getVariablesRootBefore().add(sm_);
        }
        assBl_.getVariablesRootBefore().add(new StringMap<AssignmentBefore>());
        id_.put(firstChild_, assBl_);
    }
    @Override
    public void setAssignmentBeforeNextSibling(Analyzable _an, AnalyzingEl _anEl) {
        IdMap<Block, AssignedVariables> id_ = _an.getAssignedVariables().getFinalVariables();
        Block nextSibling_ = getNextSibling();
        IdMap<Block, AssignedVariables> inners_;
        inners_ = new IdMap<Block, AssignedVariables>();
        boolean add_ = false;
        for (EntryCust<Block, AssignedVariables> e: id_.entryList()) {
            if (e.getKey() == this) {
                add_ = true;
            }
            if (add_) {
                inners_.put(e.getKey(), e.getValue());
            }
        }
        AssignedVariables parAss_ = inners_.firstValue();
        AssignedVariables assBl_ = nextSibling_.buildNewAssignedVariable();
        for (EntryCust<ClassField, SimpleAssignment> e: parAss_.getFieldsRoot().entryList()) {
            AssignmentBefore ab_ = new AssignmentBefore();
            if (parAss_.getFieldsRootBefore().getVal(e.getKey()).isAssignedBefore()) {
                ab_.setAssignedBefore(true);
            }
            boolean unass_ = e.getValue().isUnassignedAfter();
            for (EntryCust<Block, AssignedVariables> f: inners_.entryList()) {
                if (!(f.getKey() instanceof AbruptBlock)) {
                    continue;
                }
                if (f.getKey() instanceof ContinueBlock) {
                    Loop lp_ = _anEl.getContinuables().getVal((ContinueBlock) f.getKey());
                    if (!_anEl.getContinuablesAncestors().getVal((ContinueBlock) f.getKey()).getVal(lp_).containsObj(this)) {
                        continue;
                    }
                }
                if (f.getKey() instanceof BreakBlock) {
                    BreakableBlock lp_ = _anEl.getBreakables().getVal((BreakBlock) f.getKey());
                    if (!_anEl.getBreakablesAncestors().getVal((BreakBlock) f.getKey()).getVal(lp_).containsObj(this) && lp_ != this) {
                        continue;
                    }
                }
                if (f.getKey() instanceof Throwing) {
                    //throwing clause => test just after the root
                    AssignedVariables vars_ = _an.getAssignedVariables().getFinalVariables().getVal(f.getKey());
                    if (!vars_.getFields().lastValue().getVal(e.getKey()).isUnassignedAfter()) {
                        unass_ = false;
                        break;
                    }
                } else if (!f.getValue().getFieldsRootBefore().getVal(e.getKey()).isUnassignedBefore()) {
                    unass_ = false;
                    break;
                }
            }
            if (unass_) {
                ab_.setUnassignedBefore(true);
            }
            assBl_.getFieldsRootBefore().put(e.getKey(), ab_);
        }
        for (StringMap<SimpleAssignment> s: parAss_.getVariablesRoot()) {
            StringMap<AssignmentBefore> sm_ = new StringMap<AssignmentBefore>();
            int index_ = assBl_.getVariablesRootBefore().size();
            for (EntryCust<String, SimpleAssignment> e: s.entryList()) {
                AssignmentBefore ab_ = new AssignmentBefore();
                if (parAss_.getVariablesRootBefore().get(index_).getVal(e.getKey()).isAssignedBefore()) {
                    ab_.setAssignedBefore(true);
                }
                boolean unass_ = e.getValue().isUnassignedAfter();
                for (EntryCust<Block, AssignedVariables> f: inners_.entryList()) {
                    if (!(f.getKey() instanceof AbruptBlock)) {
                        continue;
                    }
                    if (f.getKey() instanceof ContinueBlock) {
                        Loop lp_ = _anEl.getContinuables().getVal((ContinueBlock) f.getKey());
                        if (!_anEl.getContinuablesAncestors().getVal((ContinueBlock) f.getKey()).getVal(lp_).containsObj(this)) {
                            continue;
                        }
                    }
                    if (f.getKey() instanceof BreakBlock) {
                        BreakableBlock lp_ = _anEl.getBreakables().getVal((BreakBlock) f.getKey());
                        if (!_anEl.getBreakablesAncestors().getVal((BreakBlock) f.getKey()).getVal(lp_).containsObj(this) && lp_ != this) {
                            continue;
                        }
                    }
                    if (f.getKey() instanceof Throwing) {
                        //throwing clause => test just after the root
                        AssignedVariables vars_ = _an.getAssignedVariables().getFinalVariables().getVal(f.getKey());
                        CustList<StringMap<Assignment>> list_ = vars_.getVariables().lastValue();
                        if (!list_.isValidIndex(index_)) {
                            continue;
                        }
                        if (!list_.get(index_).getVal(e.getKey()).isUnassignedAfter()) {
                            unass_ = false;
                            break;
                        }
                    } else {
                        CustList<StringMap<AssignmentBefore>> list_ = f.getValue().getVariablesRootBefore();
                        if (!list_.isValidIndex(index_)) {
                            continue;
                        }
                        if (!list_.get(index_).getVal(e.getKey()).isUnassignedBefore()) {
                            unass_ = false;
                            break;
                        }
                    }
                }
                if (unass_) {
                    ab_.setUnassignedBefore(true);
                }
                sm_.put(e.getKey(), ab_);
            }
            assBl_.getVariablesRootBefore().add(sm_);
        }
        id_.put(nextSibling_, assBl_);
    }
    @Override
    public final void setAssignmentAfter(Analyzable _an, AnalyzingEl _anEl) {
        super.setAssignmentAfter(_an, _anEl);
        Block ch_ = getFirstChild();
        if (ch_ == null) {
            EmptyTagName un_ = new EmptyTagName();
            un_.setFileName(getFile().getFileName());
            un_.setRc(getRowCol(0, getOffset().getOffsetTrim()));
            _an.getClasses().addError(un_);
            return;
        }
        Block nBlock_ = getNextSibling();
        if (!(nBlock_ instanceof AbstractCatchEval)) {
            if (!(nBlock_ instanceof FinallyEval)) {
                UnexpectedTagName un_ = new UnexpectedTagName();
                un_.setFileName(getFile().getFileName());
                un_.setRc(getRowCol(0, getOffset().getOffsetTrim()));
                _an.getClasses().addError(un_);
            }
        }
    }
    @Override
    boolean canBeIncrementedNextGroup() {
        return false;
    }

    @Override
    boolean canBeIncrementedCurGroup() {
        Block next_ = getNextSibling();
        return next_ instanceof AbstractCatchEval || next_ instanceof FinallyEval;
    }

    @Override
    boolean canBeLastOfBlockGroup() {
        return false;
    }

    @Override
    public String getTagName() {
        return TAG_TRY;
    }

    @Override
    public void processEl(ContextEl _cont) {
        AbstractPageEl ip_ = _cont.getLastPage();
        Block n_ = getNextSibling();
        TryBlockStack tryStack_ = new TryBlockStack();
        while (true) {
            if (!(n_ instanceof AbstractCatchEval) && !(n_ instanceof FinallyEval)) {
                break;
            }
            tryStack_.setLastBlock((BracedBlock)n_);
            n_ = n_.getNextSibling();
        }
        tryStack_.setCurrentBlock(this);
        tryStack_.setBlock(this);
        ip_.addBlock(tryStack_);
        ip_.getReadWrite().setBlock(getFirstChild());
    }

    @Override
    public void exitStack(ContextEl _context) {
        AbstractPageEl ip_ = _context.getLastPage();
        ReadWrite rw_ = ip_.getReadWrite();
        rw_.setBlock(getNextSibling());
    }

    @Override
    public ExpressionLanguage getEl(ContextEl _context, boolean _native,
            int _indexProcess) {
        return null;
    }

    @Override
    public void processToFinally(AbstractPageEl _ip, TryBlockStack _stack) {
        removeLocalVars(_ip);
        if (_stack.getLastBlock() instanceof FinallyEval) {
            _ip.clearCurrentEls();
            _ip.getReadWrite().setBlock(_stack.getLastBlock());
            _ip.setFinallyToProcess(true);
            return;
        }
        _ip.removeLastBlock();
    }
}
