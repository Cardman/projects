package code.expressionlanguage.methods;
import code.expressionlanguage.Analyzable;
import code.expressionlanguage.AnalyzedPageEl;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.OffsetsBlock;
import code.expressionlanguage.PageEl;
import code.expressionlanguage.PrimitiveTypeUtil;
import code.expressionlanguage.ReadWrite;
import code.expressionlanguage.methods.util.UnexpectedTagName;
import code.expressionlanguage.opers.ExpressionLanguage;
import code.expressionlanguage.opers.util.AssignedVariables;
import code.expressionlanguage.opers.util.Assignment;
import code.expressionlanguage.opers.util.AssignmentBefore;
import code.expressionlanguage.opers.util.ClassField;
import code.expressionlanguage.opers.util.ClassMetaInfo;
import code.expressionlanguage.stacks.TryBlockStack;
import code.expressionlanguage.variables.LocalVariable;
import code.sml.Element;
import code.util.CustList;
import code.util.EntryCust;
import code.util.IdMap;
import code.util.NatTreeMap;
import code.util.StringMap;

public final class TryEval extends BracedStack implements Eval, IncrCurrentGroup {

    public TryEval(Element _el, ContextEl _importingPage, int _indexChild,
            BracedBlock _m) {
        super(_el, _importingPage, _indexChild, _m);
    }

    public TryEval(ContextEl _importingPage, int _indexChild, BracedBlock _m, OffsetsBlock _offset) {
        super(_importingPage, _indexChild, _m, _offset);
    }

    @Override
    public NatTreeMap<String,String> getClassNames(ContextEl _context) {
        NatTreeMap<String,String> tr_ = new NatTreeMap<String,String>();
        return tr_;
    }

    @Override
    public NatTreeMap<Integer,String> getClassNamesOffsets(ContextEl _context) {
        NatTreeMap<Integer,String> tr_ = new NatTreeMap<Integer,String>();
        return tr_;
    }

    @Override
    public void checkBlocksTree(ContextEl _cont) {
        Block next_ = getNextSibling();
        boolean existCatch_ = false;
        while (next_ != null) {
            if (next_ instanceof FinallyEval) {
                existCatch_ = true;
                break;
            }
            existCatch_ = next_ instanceof CatchEval;
            break;
        }
        if (!existCatch_ || getFirstChild() == null) {
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
        AssignedVariablesBlock glAss_ = _cont.getAssignedVariables();
        AssignedVariables ass_ = glAss_.getFinalVariables().getVal(this);
        int index_ = 0;
        String boolStd_ = _cont.getStandards().getAliasBoolean();
        for (EntryCust<ClassField,AssignmentBefore> e: ass_.getFieldsRootBefore().entryList()) {
            ClassField key_ = e.getKey();
            String classNameDecl_ = key_.getClassName();
            ClassMetaInfo custClass_;
            custClass_ = _cont.getClassMetaInfo(classNameDecl_);
            String type_ = custClass_.getFields().getVal(key_.getFieldName()).getType();
            boolean isBool_ = PrimitiveTypeUtil.canBeUseAsArgument(boolStd_, type_, _cont);
            ass_.getFieldsRoot().put(key_, e.getValue().assignAfter(isBool_));
        }
        for (StringMap<AssignmentBefore> s: ass_.getVariablesRootBefore()) {
            StringMap<Assignment> vars_ = new StringMap<Assignment>();
            for (EntryCust<String,AssignmentBefore> e: s.entryList()) {
                String key_ = e.getKey();
                LocalVariable lc_ = _cont.getLocalVariables().get(index_).getVal(key_);
                String type_ = lc_.getClassName();
                boolean isBool_ = PrimitiveTypeUtil.canBeUseAsArgument(boolStd_, type_, _cont);
                vars_.put(e.getKey(), e.getValue().assignAfter(isBool_));
            }
            index_++;
            ass_.getVariablesRoot().add(vars_);
        }
    }
    @Override
    public void setAssignmentBeforeChild(Analyzable _an, AnalyzingEl _anEl) {
        Block firstChild_ = getFirstChild();
        IdMap<Block, AssignedVariables> id_ = _an.getAssignedVariables().getFinalVariables();
        AssignedVariables parAss_ = id_.getVal(this);
        AssignedVariables assBl_ = firstChild_.buildNewAssignedVariable();
        for (EntryCust<ClassField, Assignment> e: parAss_.getFieldsRoot().entryList()) {
            Assignment ba_ = e.getValue();
            assBl_.getFieldsRootBefore().put(e.getKey(), ba_.assignBefore());
        }
        for (StringMap<Assignment> s: parAss_.getVariablesRoot()) {
            StringMap<AssignmentBefore> sm_ = new StringMap<AssignmentBefore>();
            for (EntryCust<String, Assignment> e: s.entryList()) {
                Assignment ba_ = e.getValue();
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
        for (EntryCust<ClassField, Assignment> e: parAss_.getFieldsRoot().entryList()) {
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
        for (StringMap<Assignment> s: parAss_.getVariablesRoot()) {
            StringMap<AssignmentBefore> sm_ = new StringMap<AssignmentBefore>();
            int index_ = assBl_.getVariablesRootBefore().size();
            for (EntryCust<String, Assignment> e: s.entryList()) {
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
    boolean canBeIncrementedNextGroup() {
        return false;
    }

    @Override
    boolean canBeIncrementedCurGroup() {
        Block next_ = getNextSibling();
        return next_ instanceof CatchEval || next_ instanceof FinallyEval;
    }

    @Override
    boolean canBeLastOfBlockGroup() {
        return false;
    }

    @Override
    public void checkCallConstructor(ContextEl _cont) {
    }

    @Override
    public String getTagName() {
        return TAG_TRY;
    }

    @Override
    public void processEl(ContextEl _cont) {
        PageEl ip_ = _cont.getLastPage();
        Block n_ = getNextSibling();
        int index_ = getIndexGroup();
        TryBlockStack tryStack_ = new TryBlockStack();
        while (n_ != null) {
            if (n_.getIndexGroup() != index_) {
                break;
            }
            tryStack_.getCatchBlocks().add((BracedBlock)n_);
            n_ = n_.getNextSibling();
        }
        tryStack_.setBlock(this);
        ip_.addBlock(tryStack_);
        ip_.getReadWrite().setBlock(getFirstChild());
    }

    @Override
    public void exitStack(ContextEl _context) {
        PageEl ip_ = _context.getLastPage();
        ReadWrite rw_ = ip_.getReadWrite();
        rw_.setBlock(getNextSibling());
    }

    @Override
    public ExpressionLanguage getEl(ContextEl _context, boolean _native,
            int _indexProcess) {
        return null;
    }

    @Override
    public void processToFinally(PageEl _ip, TryBlockStack _stack) {
        removeLocalVars(_ip);
        if (_stack.getLastCatchBlock() instanceof FinallyEval) {
            _ip.clearCurrentEls();
            _ip.getReadWrite().setBlock(_stack.getLastCatchBlock());
            _ip.setFinallyToProcess(true);
            return;
        }
        _ip.removeLastBlock();
    }
}
