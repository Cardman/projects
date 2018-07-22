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
import code.sml.Element;
import code.util.CustList;
import code.util.EntryCust;
import code.util.IdMap;
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
            id_.put(nextSibling_, assBl_);
            return;
        }
        assBl_.getFieldsRootBefore().putAllMap(buildAssListFieldBeforeNextSibling(_an, _anEl));
        assBl_.getVariablesRootBefore().addAllElts(buildAssListLocVarBeforeNextSibling(_an, _anEl));
        id_.put(nextSibling_, assBl_);
    }
    protected StringMap<AssignmentBefore> buildAssListFieldBeforeNextSibling(Analyzable _an, AnalyzingEl _anEl) {
        Block last_ = getFirstChild();
        while (last_.getNextSibling() != null) {
            last_ = last_.getNextSibling();
        }
        CustList<ContinueBlock> continues_ = getContinuables(_anEl);
        IdMap<Block, AssignedVariables> id_;
        id_ = _an.getAssignedVariables().getFinalVariables();
        StringMap<SimpleAssignment> list_;
        list_ = id_.getVal(this).getFieldsRoot();
        int contLen_ = continues_.size();
        CustList<StringMap<AssignmentBefore>> breakAss_;
        breakAss_ = new CustList<StringMap<AssignmentBefore>>();
        for (int j = 0; j < contLen_; j++) {
            ContinueBlock br_ = continues_.get(j);
            AssignedVariables ass_ = id_.getVal(br_);
            StringMap<AssignmentBefore> vars_ = ass_.getFieldsRootBefore();
            breakAss_.add(vars_);
        }
        if (_anEl.canCompleteNormallyGroup(last_)) {
            AssignedVariables ass_ = id_.getVal(last_);
            StringMap<SimpleAssignment> v_ = ass_.getFieldsRoot();
            return beforeNextSibling(list_, v_, breakAss_);
        }
        return beforeNextSibling(list_, new StringMap<SimpleAssignment>(), breakAss_);
    }
    protected CustList<StringMap<AssignmentBefore>> buildAssListLocVarBeforeNextSibling(Analyzable _an, AnalyzingEl _anEl) {
        Block last_ = getFirstChild();
        while (last_.getNextSibling() != null) {
            last_ = last_.getNextSibling();
        }
        CustList<ContinueBlock> continues_ = getContinuables(_anEl);
        IdMap<Block, AssignedVariables> id_;
        id_ = _an.getAssignedVariables().getFinalVariables();
        CustList<StringMap<AssignmentBefore>> varsList_;
        varsList_ = new CustList<StringMap<AssignmentBefore>>();
        CustList<StringMap<SimpleAssignment>> list_;
        list_ = id_.getVal(this).getVariablesRoot();
        int contLen_ = continues_.size();
        int loopLen_ = list_.size();
        for (int i = 0; i < loopLen_; i++) {
            CustList<StringMap<AssignmentBefore>> breakAss_;
            breakAss_ = new CustList<StringMap<AssignmentBefore>>();
            for (int j = 0; j < contLen_; j++) {
                ContinueBlock br_ = continues_.get(j);
                AssignedVariables ass_ = id_.getVal(br_);
                CustList<StringMap<AssignmentBefore>> vars_ = ass_.getVariablesRootBefore();
                if (!vars_.isValidIndex(i)) {
                    continue;
                }
                breakAss_.add(vars_.get(i));
            }
            StringMap<SimpleAssignment> cond_ = list_.get(i);
            if (_anEl.canCompleteNormallyGroup(last_)) {
                AssignedVariables ass_ = id_.getVal(last_);
                CustList<StringMap<SimpleAssignment>> v_ = ass_.getVariablesRoot();
                if (v_.isValidIndex(i)) {
                    varsList_.add(beforeNextSibling(cond_, v_.get(i), breakAss_));
                }
            } else {
                varsList_.add(beforeNextSibling(cond_, new StringMap<SimpleAssignment>(), breakAss_));
            }
        }
        
        return varsList_;
    }
    private static StringMap<AssignmentBefore> beforeNextSibling(StringMap<SimpleAssignment> _loop, StringMap<SimpleAssignment> _last,
            CustList<StringMap<AssignmentBefore>> _continuable) {
        StringMap<AssignmentBefore> out_ = new StringMap<AssignmentBefore>();
        for (EntryCust<String, SimpleAssignment> e: _loop.entryList()) {
            String key_ = e.getKey();
            AssignmentBefore ab_ = new AssignmentBefore();
            boolean contAss_ = true;
            boolean contUnass_ = true;
            for (StringMap<AssignmentBefore> c: _continuable) {
                if (!c.contains(key_)) {
                    continue;
                }
                if (!c.getVal(key_).isAssignedBefore()) {
                    contAss_ = false;
                }
                if (!c.getVal(key_).isUnassignedBefore()) {
                    contUnass_ = false;
                }
            }
            if (_last.contains(key_)) {
                if (!_last.getVal(key_).isAssignedAfter()) {
                    contAss_ = false;
                }
                if (!_last.getVal(key_).isUnassignedAfter()) {
                    contUnass_ = false;
                }
            }
            if (contAss_) {
                ab_.setAssignedBefore(true);
            }
            if (contUnass_) {
                ab_.setUnassignedBefore(true);
            }
            out_.put(e.getKey(), ab_);
        }
        return out_;
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
