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
import code.expressionlanguage.opers.util.ClassField;
import code.expressionlanguage.opers.util.SimpleAssignment;
import code.expressionlanguage.stacks.LoopBlockStack;
import code.sml.Element;
import code.util.CustList;
import code.util.EntryCust;
import code.util.IdMap;
import code.util.ObjectMap;
import code.util.StringMap;

public final class DoBlock extends BracedStack implements Loop, IncrCurrentGroup {

    private String label;
    private int labelOffset;

    public DoBlock(Element _el, ContextEl _importingPage, int _indexChild,
            BracedBlock _m) {
        super(_el, _importingPage, _indexChild, _m);
    }

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
            _an.getClasses().getErrorsDet().add(un_);
        }
        Block nextSibling_ = getNextSibling();
        if (nextSibling_ == null) {
            UnexpectedTagName un_ = new UnexpectedTagName();
            un_.setFileName(getFile().getFileName());
            un_.setRc(getRowCol(0, getOffset().getOffsetTrim()));
            _an.getClasses().getErrorsDet().add(un_);
            return;
        }
        if (!(nextSibling_ instanceof DoWhileCondition)) {
            UnexpectedTagName un_ = new UnexpectedTagName();
            un_.setFileName(nextSibling_.getFile().getFileName());
            un_.setRc(nextSibling_.getRowCol(0, nextSibling_.getOffset().getOffsetTrim()));
            _an.getClasses().getErrorsDet().add(un_);
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
            for (EntryCust<ClassField, SimpleAssignment> e: parAss_.getFieldsRoot().entryList()) {
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
            id_.put(nextSibling_, assBl_);
            return;
        }
        while (last_.getNextSibling() != null) {
            last_ = last_.getNextSibling();
        }
        AssignedVariables parAss_ = id_.getVal(this);
        AssignedVariables parLast_ = id_.getVal(last_);
        for (EntryCust<ClassField, SimpleAssignment> e: parAss_.getFieldsRoot().entryList()) {
            AssignmentBefore ab_ = new AssignmentBefore();
            boolean contAss_ = true;
            boolean contUnass_ = true;
            for (EntryCust<ContinueBlock, Loop> c: _anEl.getContinuables().entryList()) {
                if (c.getValue() != this) {
                    continue;
                }
                if (!id_.getVal(c.getKey()).getFieldsRootBefore().getVal(e.getKey()).isAssignedBefore()) {
                    contAss_ = false;
                }
                if (!id_.getVal(c.getKey()).getFieldsRootBefore().getVal(e.getKey()).isUnassignedBefore()) {
                    contUnass_ = false;
                }
            }
            if (_anEl.canCompleteNormallyGroup(last_)) {
                SimpleAssignment ba_ = parLast_.getFieldsRoot().getVal(e.getKey());
                if (contAss_) {
                    contAss_ = ba_.isAssignedAfter();
                }
                if (contUnass_) {
                    contUnass_ = ba_.isUnassignedAfter();
                }
            }
            if (contAss_) {
                ab_.setAssignedBefore(true);
            }
            if (contUnass_) {
                ab_.setUnassignedBefore(true);
            }
            assBl_.getFieldsRootBefore().put(e.getKey(), ab_);
        }
        for (StringMap<SimpleAssignment> s: parAss_.getVariablesRoot()) {
            StringMap<AssignmentBefore> sm_ = new StringMap<AssignmentBefore>();
            int index_ = assBl_.getVariablesRootBefore().size();
            for (EntryCust<String, SimpleAssignment> e: s.entryList()) {
                AssignmentBefore ab_ = new AssignmentBefore();
                boolean contAss_ = true;
                boolean contUnass_ = true;
                for (EntryCust<ContinueBlock, Loop> c: _anEl.getContinuables().entryList()) {
                    if (c.getValue() != this) {
                        continue;
                    }
                    if (!id_.getVal(c.getKey()).getVariablesRootBefore().get(index_).getVal(e.getKey()).isAssignedBefore()) {
                        contAss_ = false;
                    }
                    if (!id_.getVal(c.getKey()).getVariablesRootBefore().get(index_).getVal(e.getKey()).isUnassignedBefore()) {
                        contUnass_ = false;
                    }
                }
                if (_anEl.canCompleteNormallyGroup(last_)) {
                    CustList<StringMap<SimpleAssignment>> vars_ = parLast_.getVariablesRoot();
                    if (vars_.isValidIndex(index_)) {
                        SimpleAssignment ba_ = vars_.get(index_).getVal(e.getKey());
                        if (contAss_) {
                            contAss_ = ba_.isAssignedAfter();
                        }
                        if (contUnass_) {
                            contUnass_ = ba_.isUnassignedAfter();
                        }
                    }
                }
                if (contAss_) {
                    ab_.setAssignedBefore(true);
                }
                if (contUnass_) {
                    ab_.setUnassignedBefore(true);
                }
                sm_.put(e.getKey(), ab_);
            }
            assBl_.getVariablesRootBefore().add(sm_);
        }
        id_.put(nextSibling_, assBl_);
    }
    @Override
    public void setAssignmentBeforeChild(Analyzable _an, AnalyzingEl _anEl) {
        Block firstChild_ = getFirstChild();
        IdMap<Block, AssignedVariables> id_ = _an.getAssignedVariables().getFinalVariables();
        AssignedVariables parAss_ = id_.getVal(this);
        AssignedVariables vars_ = firstChild_.buildNewAssignedVariable();
        ObjectMap<ClassField,AssignmentBefore> fields_;
        CustList<StringMap<AssignmentBefore>> variables_;
        fields_ = new ObjectMap<ClassField,AssignmentBefore>();
        variables_ = parAss_.getVariablesRootBefore();
        for (EntryCust<ClassField,AssignmentBefore> e: parAss_.getFieldsRootBefore().entryList()) {
            fields_.put(e.getKey(), e.getValue().copy());
        }
        vars_.getFieldsRootBefore().putAllMap(fields_);
        for (StringMap<AssignmentBefore> s: variables_) {
            StringMap<AssignmentBefore> sm_ = new StringMap<AssignmentBefore>();
            for (EntryCust<String,AssignmentBefore> e: s.entryList()) {
                sm_.put(e.getKey(), e.getValue().copy());
            }
            vars_.getVariablesRootBefore().add(sm_);
        }
        vars_.getVariablesRootBefore().add(new StringMap<AssignmentBefore>());
        id_.put(firstChild_, vars_);
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
    public String getTagName() {
        return TAG_DO;
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
