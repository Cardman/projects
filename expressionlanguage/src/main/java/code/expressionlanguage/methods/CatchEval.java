package code.expressionlanguage.methods;
import code.expressionlanguage.AbstractPageEl;
import code.expressionlanguage.Analyzable;
import code.expressionlanguage.AnalyzedPageEl;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.OffsetStringInfo;
import code.expressionlanguage.OffsetsBlock;
import code.expressionlanguage.ReadWrite;
import code.expressionlanguage.methods.util.DuplicateVariable;
import code.expressionlanguage.methods.util.UnexpectedTagName;
import code.expressionlanguage.opers.ExpressionLanguage;
import code.expressionlanguage.opers.util.AssignedVariables;
import code.expressionlanguage.opers.util.Assignment;
import code.expressionlanguage.opers.util.AssignmentBefore;
import code.expressionlanguage.opers.util.ClassField;
import code.expressionlanguage.opers.util.NullStruct;
import code.expressionlanguage.opers.util.SimpleAssignment;
import code.expressionlanguage.stacks.TryBlockStack;
import code.expressionlanguage.variables.LocalVariable;
import code.sml.Element;
import code.util.CustList;
import code.util.EntryCust;
import code.util.IdMap;
import code.util.NatTreeMap;
import code.util.ObjectMap;
import code.util.StringList;
import code.util.StringMap;

public final class CatchEval extends BracedStack implements Eval, IncrCurrentGroup, IncrNextGroup {

    private final String className;

    private int classNameOffset;

    private final String variableName;

    private int variableNameOffset;

    public CatchEval(Element _el, ContextEl _importingPage, int _indexChild,
            BracedBlock _m) {
        super(_el, _importingPage, _indexChild, _m);
        className = _el.getAttribute(ATTRIBUTE_CLASS);
        variableName = _el.getAttribute(ATTRIBUTE_VAR);
    }

    public CatchEval(ContextEl _importingPage, int _indexChild,
            BracedBlock _m, OffsetStringInfo _className, OffsetStringInfo _variable, OffsetsBlock _offset) {
        super(_importingPage, _indexChild, _m, _offset);
        className = _className.getInfo();
        classNameOffset = _className.getOffset();
        variableName = _variable.getInfo();
        variableNameOffset = _variable.getOffset();
    }

    public int getClassNameOffset() {
        return classNameOffset;
    }
    public int getVariableNameOffset() {
        return variableNameOffset;
    }
    public String getClassName() {
        return className;
    }

    public String getVariableName() {
        return variableName;
    }

    @Override
    public NatTreeMap<String,String> getClassNames(ContextEl _context) {
        NatTreeMap<String,String> tr_ = new NatTreeMap<String,String>();
        tr_.put(ATTRIBUTE_CLASS, className);
        return tr_;
    }

    @Override
    public NatTreeMap<Integer,String> getClassNamesOffsets(ContextEl _context) {
        NatTreeMap<Integer,String> tr_ = new NatTreeMap<Integer,String>();
        tr_.put(classNameOffset, className);
        return tr_;
    }
    @Override
    public void checkBlocksTree(ContextEl _cont) {
        Block next_ = getPreviousSibling();
        boolean existTry_ = false;
        while (next_ != null) {
            if (next_ instanceof TryEval) {
                existTry_ = true;
                break;
            }
            if (!(next_ instanceof CatchEval)) {
                break;
            }
            next_ = next_.getPreviousSibling();
        }
        if (!existTry_) {
            AnalyzedPageEl page_ = _cont.getAnalyzing();
            page_.setGlobalOffset(getOffset().getOffsetTrim());
            page_.setOffset(0);
            UnexpectedTagName un_ = new UnexpectedTagName();
            un_.setFileName(getFile().getFileName());
            un_.setRc(getRowCol(0, getOffset().getOffsetTrim()));
            _cont.getClasses().getErrorsDet().add(un_);
        }
    }

    @Override
    public void buildExpressionLanguage(ContextEl _cont) {
        AnalyzedPageEl page_ = _cont.getAnalyzing();
        page_.setGlobalOffset(classNameOffset);
        page_.setOffset(0);
        page_.setGlobalOffset(variableNameOffset);
        page_.setOffset(0);
        if (_cont.getAnalyzing().getCatchVars().contains(variableName)) {
            DuplicateVariable d_ = new DuplicateVariable();
            d_.setId(variableName);
            d_.setFileName(getFile().getFileName());
            d_.setRc(getRowCol(0, variableNameOffset));
            _cont.getClasses().getErrorsDet().add(d_);
            return;
        }
        if (getFirstChild() == null) {
            return;
        }
        LocalVariable lv_ = new LocalVariable();
        lv_.setClassName(className);
        _cont.getAnalyzing().getCatchVars().put(variableName, lv_);
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
    public void setAssignmentBeforeNextSibling(Analyzable _an, AnalyzingEl _anEl) {
        IdMap<Block, AssignedVariables> id_ = _an.getAssignedVariables().getFinalVariables();
        Block nextSibling_ = getNextSibling();
        boolean finClause_ = nextSibling_ instanceof FinallyEval;
        Block try_ = this;
        CustList<CatchEval> catch_ = new CustList<CatchEval>();
        while (try_ instanceof CatchEval) {
            catch_.add((CatchEval) try_);
            try_ = try_.getPreviousSibling();
        }
        IdMap<Block, AssignedVariables> inners_;
        inners_ = new IdMap<Block, AssignedVariables>();
        boolean add_ = false;
        for (EntryCust<Block, AssignedVariables> e: id_.entryList()) {
            if (e.getKey() == try_) {
                add_ = true;
            }
            if (e.getKey().getPreviousSibling() == try_) {
                break;
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
            boolean unass_ = true;
            if (!e.getValue().isUnassignedAfter()) {
                unass_ = false;
            }
            for (EntryCust<Block, AssignedVariables> f: inners_.entryList()) {
                if (!(f.getKey() instanceof AbruptBlock)) {
                    continue;
                }
                if (f.getKey() instanceof ContinueBlock) {
                    Loop lp_ = _anEl.getContinuables().getVal((ContinueBlock) f.getKey());
                    if (!_anEl.getContinuablesAncestors().getVal((ContinueBlock) f.getKey()).getVal(lp_).containsObj((BracedBlock) try_)) {
                        continue;
                    }
                }
                if (f.getKey() instanceof BreakBlock) {
                    BreakableBlock lp_ = _anEl.getBreakables().getVal((BreakBlock) f.getKey());
                    if (!_anEl.getBreakablesAncestors().getVal((BreakBlock) f.getKey()).getVal(lp_).containsObj((BracedBlock) try_)) {
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
            if (finClause_) {
                if (unass_) {
                    for (CatchEval c: catch_) {
                        AssignedVariables vars_ = _an.getAssignedVariables().getFinalVariables().getVal(c);
                        if (!vars_.getFieldsRoot().getVal(e.getKey()).isUnassignedAfter()) {
                            unass_ = false;
                            break;
                        }
                    }
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
                boolean unass_ = true;
                if (!e.getValue().isUnassignedAfter()) {
                    unass_ = false;
                }
                for (EntryCust<Block, AssignedVariables> f: inners_.entryList()) {
                    if (!(f.getKey() instanceof AbruptBlock)) {
                        continue;
                    }
                    if (f.getKey() instanceof ContinueBlock) {
                        Loop lp_ = _anEl.getContinuables().getVal((ContinueBlock) f.getKey());
                        if (!_anEl.getContinuablesAncestors().getVal((ContinueBlock) f.getKey()).getVal(lp_).containsObj((BracedBlock) try_)) {
                            continue;
                        }
                    }
                    if (f.getKey() instanceof BreakBlock) {
                        BreakableBlock lp_ = _anEl.getBreakables().getVal((BreakBlock) f.getKey());
                        if (!_anEl.getBreakablesAncestors().getVal((BreakBlock) f.getKey()).getVal(lp_).containsObj((BracedBlock) try_)) {
                            continue;
                        }
                    }
                    if (f.getKey() instanceof Throwing) {
                        //throwing clause => test just after the root
                        AssignedVariables vars_ = _an.getAssignedVariables().getFinalVariables().getVal(f.getKey());
                        if (!vars_.getVariables().lastValue().get(index_).getVal(e.getKey()).isUnassignedAfter()) {
                            unass_ = false;
                            break;
                        }
                    } else if (!f.getValue().getVariablesRootBefore().get(index_).getVal(e.getKey()).isUnassignedBefore()) {
                        unass_ = false;
                        break;
                    }
                }
                if (finClause_) {
                    if (unass_) {
                        for (CatchEval c: catch_) {
                            AssignedVariables vars_ = _an.getAssignedVariables().getFinalVariables().getVal(c);
                            if (!vars_.getVariablesRoot().get(index_).getVal(e.getKey()).isUnassignedAfter()) {
                                unass_ = false;
                                break;
                            }
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
    public void setAssignmentAfter(Analyzable _an, AnalyzingEl _anEl) {
        super.setAssignmentAfter(_an, _anEl);
        if (canBeIncrementedCurGroup()) {
            return;
        }
        CustList<Block> prev_ = new CustList<Block>();
        prev_.add(this);
        Block pBlock_ = getPreviousSibling();
        while (!(pBlock_ instanceof TryEval)) {
            prev_.add(pBlock_);
            pBlock_ = pBlock_.getPreviousSibling();
        }
        prev_.add(pBlock_);
        IdMap<Block, AssignedVariables> id_ = _an.getAssignedVariables().getFinalVariables();
        AssignedVariables ass_;
        ass_ = id_.getVal(this);
        AssignedVariables assTar_ = id_.getVal(this);
        ObjectMap<ClassField,SimpleAssignment> fields_ = ass_.getFieldsRoot();
        CustList<StringMap<SimpleAssignment>> vars_ = ass_.getVariablesRoot();
        ObjectMap<ClassField,SimpleAssignment> after_ = new ObjectMap<ClassField,SimpleAssignment>();
        CustList<StringMap<SimpleAssignment>> afterVars_ = new CustList<StringMap<SimpleAssignment>>();
        for (EntryCust<ClassField,SimpleAssignment> e: fields_.entryList()) {
            ClassField key_ = e.getKey();
            boolean assAfter_ = true;
            boolean unassAfter_ = true;
            for (Block p: prev_) {
                if (!_anEl.canCompleteNormally(p)) {
                    continue;
                }
                AssignedVariables assLoc_ = id_.getVal(p);
                ObjectMap<ClassField,SimpleAssignment> fieldsLoc_ = assLoc_.getFieldsRoot();
                if (!fieldsLoc_.getVal(key_).isAssignedAfter()) {
                    assAfter_ = false;
                    break;
                }
            }
            for (Block p: prev_) {
                if (!_anEl.canCompleteNormally(p)) {
                    continue;
                }
                AssignedVariables assLoc_ = id_.getVal(p);
                ObjectMap<ClassField,SimpleAssignment> fieldsLoc_ = assLoc_.getFieldsRoot();
                if (!fieldsLoc_.getVal(key_).isUnassignedAfter()) {
                    unassAfter_ = false;
                    break;
                }
            }
            after_.put(key_, Assignment.assignClassic(assAfter_, unassAfter_));
        }
        assTar_.getFieldsRoot().putAllMap(after_);
        for (StringMap<SimpleAssignment> s: vars_) {
            StringMap<SimpleAssignment> sm_ = new StringMap<SimpleAssignment>();
            int index_ = afterVars_.size();
            for (EntryCust<String,SimpleAssignment> e: s.entryList()) {
                String key_ = e.getKey();
                boolean assAfter_ = true;
                boolean unassAfter_ = true;
                for (Block p: prev_) {
                    if (!_anEl.canCompleteNormally(p)) {
                        continue;
                    }
                    AssignedVariables assLoc_ = id_.getVal(p);
                    StringMap<SimpleAssignment> fieldsLoc_ = assLoc_.getVariablesRoot().get(index_);
                    if (!fieldsLoc_.getVal(key_).isAssignedAfter()) {
                        assAfter_ = false;
                        break;
                    }
                }
                for (Block p: prev_) {
                    if (!_anEl.canCompleteNormally(p)) {
                        continue;
                    }
                    AssignedVariables assLoc_ = id_.getVal(p);
                    StringMap<SimpleAssignment> fieldsLoc_ = assLoc_.getVariablesRoot().get(index_);
                    if (!fieldsLoc_.getVal(key_).isUnassignedAfter()) {
                        unassAfter_ = false;
                        break;
                    }
                }
                sm_.put(key_, Assignment.assignClassic(assAfter_, unassAfter_));
            }
            afterVars_.add(sm_);
        }
        assTar_.getVariablesRoot().clear();
        assTar_.getVariablesRoot().addAllElts(afterVars_);
    }

    @Override
    boolean canBeIncrementedNextGroup() {
        return true;
    }

    @Override
    boolean canBeIncrementedCurGroup() {
        Block next_ = getNextSibling();
        return next_ instanceof CatchEval || next_ instanceof FinallyEval;
    }

    @Override
    boolean canBeLastOfBlockGroup() {
        return true;
    }

    @Override
    public String getTagName() {
        return TAG_CATCH;
    }

    @Override
    public void processEl(ContextEl _cont) {
        AbstractPageEl ip_ = _cont.getLastPage();
        ReadWrite rw_ = ip_.getReadWrite();
        TryBlockStack ts_ = (TryBlockStack) ip_.getLastStack();
        ts_.setException(NullStruct.NULL_VALUE);
        if (ts_.getLastCatchBlock() == this) {
            ip_.removeLastBlock();
            processBlock(_cont);
        } else {
            ts_.setVisitedCatch(getIndexInGroup()-1);
            rw_.setBlock(getNextSibling());
        }
    }

    @Override
    public void exitStack(ContextEl _context) {
        AbstractPageEl ip_ = _context.getLastPage();
        ReadWrite rw_ = ip_.getReadWrite();
        TryBlockStack tryStack_ = (TryBlockStack) ip_.getLastStack();
        CatchEval catch_ = (CatchEval) tryStack_.getCurrentCatchBlock();
        String var_ = catch_.getVariableName();
        StringMap<LocalVariable> vars_ = ip_.getCatchVars();
        vars_.removeKey(var_);
        rw_.setBlock(catch_);
    }

    @Override
    public ExpressionLanguage getEl(ContextEl _context, boolean _native,
            int _indexProcess) {
        return null;
    }

    @Override
    public void abruptGroup(Analyzable _an, AnalyzingEl _anEl) {
        if (canBeIncrementedCurGroup()) {
            return;
        }
        CustList<Block> group_ = new CustList<Block>();
        group_.add(this);
        Block p_ = getPreviousSibling();
        while (!(p_ instanceof TryEval)) {
            group_.add(p_);
            p_ = p_.getPreviousSibling();
        }
        group_.add(p_);
        boolean canCmpNormally_ = false;
        for (Block b: group_) {
            if (_anEl.canCompleteNormally(b)) {
                canCmpNormally_ = true;
                break;
            }
        }
        if (!canCmpNormally_) {
            for (Block b: group_) {
                _anEl.completeAbruptGroup(b);
            }
        }
    }

    @Override
    public void reach(Analyzable _an, AnalyzingEl _anEl) {
        StringList classes_ = new StringList();
        Block p_ = getPreviousSibling();
        while (!(p_ instanceof TryEval)) {
            classes_.add(((CatchEval)p_).getClassName());
            p_ = p_.getPreviousSibling();
        }
        String curClass_ = getClassName();
        _anEl.setArgMapping(curClass_);
        boolean reachCatch_ = true;
        for (String c: classes_) {
            _anEl.setParamMapping(c);
            if (_anEl.isCorrectMapping(_an)) {
                reachCatch_ = false;
                break;
            }
        }
        if (reachCatch_) {
            _anEl.reach(this);
        } else {
            _anEl.unreach(this);
        }
    }

    @Override
    public void processToFinally(AbstractPageEl _ip, TryBlockStack _stack) {
        String var_ = getVariableName();
        _ip.getCatchVars().removeKey(var_);
        removeLocalVars(_ip);
        BracedBlock br_ = _stack.getLastCatchBlock();
        if (br_ instanceof FinallyEval) {
            _ip.clearCurrentEls();
            _ip.getReadWrite().setBlock(br_);
            _ip.setFinallyToProcess(true);
            return;
        }
        _ip.removeLastBlock();
    }
}
