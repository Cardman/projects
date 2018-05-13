package code.expressionlanguage.methods;

import code.expressionlanguage.AbstractPageEl;
import code.expressionlanguage.Analyzable;
import code.expressionlanguage.AnalyzedPageEl;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ConstType;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.OffsetStringInfo;
import code.expressionlanguage.OffsetsBlock;
import code.expressionlanguage.OperationsSequence;
import code.expressionlanguage.ReadWrite;
import code.expressionlanguage.methods.util.EmptyTagName;
import code.expressionlanguage.methods.util.UnexpectedOperationAffect;
import code.expressionlanguage.opers.AffectationOperation;
import code.expressionlanguage.opers.OperationNode;
import code.expressionlanguage.opers.SemiAffectationOperation;
import code.expressionlanguage.opers.SettableAbstractFieldOperation;
import code.expressionlanguage.opers.SettableElResult;
import code.expressionlanguage.opers.VariableOperation;
import code.expressionlanguage.opers.util.AssignedBooleanVariables;
import code.expressionlanguage.opers.util.AssignedVariables;
import code.expressionlanguage.opers.util.Assignment;
import code.expressionlanguage.opers.util.AssignmentBefore;
import code.expressionlanguage.opers.util.BooleanAssignment;
import code.expressionlanguage.opers.util.ClassField;
import code.expressionlanguage.opers.util.ClassMetaInfo;
import code.expressionlanguage.opers.util.FieldMetaInfo;
import code.expressionlanguage.opers.util.SimpleAssignment;
import code.expressionlanguage.stacks.LoopBlockStack;
import code.expressionlanguage.variables.LocalVariable;
import code.util.CustList;
import code.util.EntryCust;
import code.util.IdMap;
import code.util.NatTreeMap;
import code.util.ObjectMap;
import code.util.StringList;
import code.util.StringMap;

public final class DoWhileCondition extends Condition implements IncrNextGroup {

    public DoWhileCondition(ContextEl _importingPage, int _indexChild,
            BracedBlock _m, OffsetStringInfo _condition, OffsetsBlock _offset) {
        super(_importingPage, _indexChild, _m, _condition, _offset);
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
        AnalyzedPageEl page_ = _cont.getAnalyzing();
        page_.setGlobalOffset(getOffset().getOffsetTrim());
        page_.setOffset(0);
        if (!(getPreviousSibling() instanceof DoBlock)) {
            EmptyTagName un_ = new EmptyTagName();
            un_.setFileName(getFile().getFileName());
            un_.setRc(getRowCol(0, getOffset().getOffsetTrim()));
            _cont.getClasses().getErrorsDet().add(un_);
        }
        if (getFirstChild() != null) {
            EmptyTagName un_ = new EmptyTagName();
            un_.setFileName(getFile().getFileName());
            un_.setRc(getRowCol(0, getOffset().getOffsetTrim()));
            _cont.getClasses().getErrorsDet().add(un_);
        }
    }

    @Override
    public String getTagName() {
        return TAG_WHILE;
    }

    @Override
    boolean canBeIncrementedNextGroup() {
        return true;
    }

    @Override
    boolean canBeIncrementedCurGroup() {
        return false;
    }

    @Override
    boolean canBeLastOfBlockGroup() {
        return true;
    }

    @Override
    public void exitStack(ContextEl _context) {
    }

    @Override
    public void processEl(ContextEl _cont) {
        AbstractPageEl ip_ = _cont.getLastPage();
        ReadWrite rw_ = ip_.getReadWrite();
        LoopBlockStack l_ = (LoopBlockStack) ip_.getLastStack();
        l_.setEvaluatingKeepLoop(true);
        rw_.setBlock(this);
        _cont.getLastPage().setGlobalOffset(getOffset().getOffsetTrim());
        _cont.getLastPage().setOffset(0);
        Boolean keep_ = evaluateCondition(_cont);
        if (keep_ == null) {
            return;
        }
        if (!keep_) {
            l_.setFinished(true);
        }
        l_.setEvaluatingKeepLoop(false);
        rw_.setBlock(getPreviousSibling());
    }

    @Override
    public void abruptGroup(Analyzable _an, AnalyzingEl _anEl) {
        Loop previous_ = (Loop) getPreviousSibling();
        boolean abr_ = true;
        Block last_ = getPreviousSibling().getFirstChild();
        while (last_.getNextSibling() != null) {
            last_ = last_.getNextSibling();
        }
        OperationNode op_ = getElCondition().getRoot();
        boolean proc_ = true;
        Argument arg_ = op_.getArgument();
        if (op_.getArgument() == null) {
            proc_ = false;
        } else if (!(arg_.getObject() instanceof Boolean)) {
            proc_ = false;
        } else if (!(Boolean)arg_.getObject()) {
            proc_ = false;
        }
        if (!proc_) {
            if (_anEl.canCompleteNormallyGroup(last_)) {
                abr_ = false;
            }
        }
        if (abr_) {
            if (!proc_) {
                IdMap<ContinueBlock, Loop> breakables_;
                breakables_ = _anEl.getContinuables();
                for (EntryCust<ContinueBlock, Loop> e: breakables_.entryList()) {
                    if (e.getValue() == previous_ && _anEl.isReachable(e.getKey())) {
                        abr_ = false;
                        break;
                    }
                }
            }
        }
        if (abr_) {
            IdMap<BreakBlock, BreakableBlock> breakables_;
            breakables_ = _anEl.getBreakables();
            for (EntryCust<BreakBlock, BreakableBlock> e: breakables_.entryList()) {
                if (e.getValue() == previous_ && _anEl.isReachable(e.getKey())) {
                    abr_ = false;
                    break;
                }
            }
        }
        if (abr_) {
            _anEl.completeAbrupt(this);
            _anEl.completeAbruptGroup(this);
        }
    }
    @Override
    public void setAssignmentAfter(Analyzable _an, AnalyzingEl _anEl) {
        super.setAssignmentAfter(_an, _anEl);
        IdMap<Block, AssignedVariables> id_;
        id_ = _an.getAssignedVariables().getFinalVariables();
        ObjectMap<ClassField,AssignmentBefore> fieldsHypot_;
        fieldsHypot_ = new ObjectMap<ClassField,AssignmentBefore>();
        CustList<StringMap<AssignmentBefore>> varsHypot_;
        varsHypot_ = new CustList<StringMap<AssignmentBefore>>();

        //by do block
        DoBlock dBlock_ = (DoBlock) getPreviousSibling();
        AssignedVariables varsDo_;
        varsDo_ = id_.getVal(dBlock_);
        for (EntryCust<ClassField,AssignmentBefore> e: varsDo_.getFieldsRootBefore().entryList()) {
            fieldsHypot_.put(e.getKey(), e.getValue().copy());
        }
        for (StringMap<AssignmentBefore> s: varsDo_.getVariablesRootBefore()) {
            StringMap<AssignmentBefore> sm_ = new StringMap<AssignmentBefore>();
            for (EntryCust<String,AssignmentBefore> e: s.entryList()) {
                sm_.put(e.getKey(), e.getValue().copy());
            }
            varsHypot_.add(sm_);
        }
        AssignedBooleanVariables varsWhile_;
        varsWhile_ = (AssignedBooleanVariables) id_.getVal(this);
        for (EntryCust<ClassField,BooleanAssignment> e: varsWhile_.getFieldsRootAfter().entryList()) {
            BooleanAssignment ba_ = e.getValue();
            if (!ba_.isUnassignedAfterWhenTrue()) {
                fieldsHypot_.getVal(e.getKey()).setUnassignedBefore(false);
            }
        }
        varsDo_.getFieldsRootBefore().putAllMap(fieldsHypot_);
        int index_ = 0;
        for (StringMap<BooleanAssignment> s: varsWhile_.getVariablesRootAfter()) {
            for (EntryCust<String,BooleanAssignment> e: s.entryList()) {
                BooleanAssignment ba_ = e.getValue();
                if (!ba_.isUnassignedAfterWhenTrue()) {
                    varsHypot_.get(index_).getVal(e.getKey()).setUnassignedBefore(false);
                }
            }
            index_++;
        }
        varsDo_.getVariablesRootBefore().clear();
        varsDo_.getVariablesRootBefore().addAllElts(varsHypot_);
        IdMap<Block, AssignedVariables> allDesc_ = new IdMap<Block, AssignedVariables>();
        boolean add_ = false;
        for (EntryCust<Block, AssignedVariables> e: id_.entryList()) {
            if (e.getKey() == dBlock_) {
                add_ = true;
            }
            if (add_) {
                allDesc_.put(e.getKey(), e.getValue());
            }
        }
        AssignedVariables vars_;
        for (EntryCust<ClassField,AssignmentBefore> e: fieldsHypot_.entryList()) {
            if (e.getValue().isUnassignedBefore()) {
                continue;
            }
            if (e.getValue().isAssignedBefore()) {
                continue;
            }
            ClassField key_ = e.getKey();
            ClassMetaInfo cl_ = _an.getClassMetaInfo(key_.getClassName());
            FieldMetaInfo fm_ = cl_.getFields().getVal(key_.getFieldName());
            if (!fm_.isFinalField()) {
                continue;
            }
            for (EntryCust<Block, AssignedVariables> d: allDesc_.entryList()) {
                vars_ = d.getValue();
                Block next_ = d.getKey();
                boolean take_ = takeContinue(next_, vars_, dBlock_, _anEl);
                if (!take_) {
                    continue;
                }
                //next siblings of d
                processFinalFields(next_, _an, vars_, key_);
            }
        }
        int indexDoWhile_ = 0;
        for (StringMap<AssignmentBefore> s: varsHypot_) {
            for (EntryCust<String,AssignmentBefore> e: s.entryList()) {
                if (e.getValue().isUnassignedBefore()) {
                    continue;
                }
                if (e.getValue().isAssignedBefore()) {
                    continue;
                }
                if (!_an.getLocalVariables().isValidIndex(indexDoWhile_)) {
                    continue;
                }
                String key_ = e.getKey();
                StringMap<LocalVariable> varLocs_;
                varLocs_ = _an.getLocalVariables().get(index_);
                LocalVariable varLoc_ = varLocs_.getVal(key_);
                if (!varLoc_.isFinalVariable()) {
                    continue;
                }
                for (EntryCust<Block, AssignedVariables> d: allDesc_.entryList()) {
                    vars_ = d.getValue();
                    Block next_ = d.getKey();
                    boolean take_ = takeContinue(next_, vars_, dBlock_, _anEl);
                    if (!take_) {
                        continue;
                    }
                    //next siblings of d
                    processFinalVars(next_, _an, vars_, key_);
                }
            }
            indexDoWhile_++;
        }

        ObjectMap<ClassField,SimpleAssignment> fieldsAfter_;
        fieldsAfter_ = new ObjectMap<ClassField,SimpleAssignment>();
        CustList<StringMap<SimpleAssignment>> varsAfter_;
        varsAfter_ = new CustList<StringMap<SimpleAssignment>>();
        for (EntryCust<ClassField,BooleanAssignment> e: varsWhile_.getFieldsRootAfter().entryList()) {
            BooleanAssignment ba_ = e.getValue();
            boolean ass_ = true;
            boolean unass_ = true;
            if (!ba_.isAssignedAfterWhenFalse()) {
                ass_ = false;
            }
            if (!ba_.isUnassignedAfterWhenFalse()) {
                unass_ = false;
            }
            for (EntryCust<BreakBlock, BreakableBlock> f: _anEl.getBreakables().entryList()) {
                if (f.getValue() != dBlock_) {
                    continue;
                }
                if (!id_.getVal(f.getKey()).getFieldsRootBefore().getVal(e.getKey()).isAssignedBefore()) {
                    ass_ = false;
                }
                if (!id_.getVal(f.getKey()).getFieldsRootBefore().getVal(e.getKey()).isUnassignedBefore()) {
                    unass_ = false;
                }
            }
            ClassField key_ = e.getKey();
            fieldsAfter_.put(key_, Assignment.assignClassic(ass_, unass_));
        }
        varsDo_.getFieldsRoot().putAllMap(fieldsAfter_);
        index_ = 0;
        for (StringMap<BooleanAssignment> s: varsWhile_.getVariablesRootAfter()) {
            StringMap<SimpleAssignment> sm_;
            sm_ = new StringMap<SimpleAssignment>();
            for (EntryCust<String,BooleanAssignment> e: s.entryList()) {
                BooleanAssignment ba_ = e.getValue();
                boolean ass_ = true;
                boolean unass_ = true;
                if (!ba_.isAssignedAfterWhenFalse()) {
                    ass_ = false;
                }
                if (!ba_.isUnassignedAfterWhenFalse()) {
                    unass_ = false;
                }
                for (EntryCust<BreakBlock, BreakableBlock> f: _anEl.getBreakables().entryList()) {
                    if (f.getValue() != dBlock_) {
                        continue;
                    }
                    StringMap<AssignmentBefore> set_;
                    set_ = id_.getVal(f.getKey()).getVariablesRootBefore().get(index_);
                    if (!set_.contains(e.getKey())) {
                        continue;
                    }
                    if (!set_.getVal(e.getKey()).isAssignedBefore()) {
                        ass_ = false;
                    }
                    if (!set_.getVal(e.getKey()).isUnassignedBefore()) {
                        unass_ = false;
                    }
                }
                String key_ = e.getKey();
                sm_.put(key_, Assignment.assignClassic(ass_, unass_));
            }
            index_++;
            varsAfter_.add(sm_);
        }
        varsWhile_.getVariablesRoot().clear();
        varsWhile_.getVariablesRoot().addAllElts(varsAfter_);
    
    }
    private void processFinalFields(Block _curBlock, Analyzable _an,AssignedVariables _vars, ClassField _field) {
        for (EntryCust<OperationNode, ObjectMap<ClassField,AssignmentBefore>> f: _vars.getFieldsBefore().entryList()) {
            if (!(f.getKey() instanceof AffectationOperation)) {
                if (!(f.getKey() instanceof SemiAffectationOperation)) {
                    continue;
                }
                //Error
                OperationNode cst_ = f.getKey();
                cst_.setRelativeOffsetPossibleAnalyzable(cst_.getIndexInEl(), _an);
                UnexpectedOperationAffect un_ = new UnexpectedOperationAffect();
                un_.setFileName(_an.getCurrentFileName());
                un_.setRc(_curBlock.getRowCol(_an.getOffset(),_curBlock.getOffset().getOffsetTrim()));
                _an.getClasses().getErrorsDet().add(un_);
                continue;
            }
            AffectationOperation aff_ = (AffectationOperation) f.getKey();
            SettableElResult set_ = aff_.getSettable();
            if (!(set_ instanceof SettableAbstractFieldOperation)) {
                continue;
            }
            SettableAbstractFieldOperation cst_ = (SettableAbstractFieldOperation) set_;
            if (!cst_.getFieldId().eq(_field)) {
                continue;
            }
            cst_.setRelativeOffsetPossibleAnalyzable(cst_.getIndexInEl(), _an);
            UnexpectedOperationAffect un_ = new UnexpectedOperationAffect();
            un_.setFileName(_an.getCurrentFileName());
            un_.setRc(_curBlock.getRowCol(_an.getOffset(),_curBlock.getOffset().getOffsetTrim()));
            _an.getClasses().getErrorsDet().add(un_);
        }
    }
    private void processFinalVars(Block _curBlock, Analyzable _an,AssignedVariables _vars, String _field) {
        for (EntryCust<OperationNode,CustList<StringMap<AssignmentBefore>>> f: _vars.getVariablesBefore().entryList()) {
            if (!(f.getKey() instanceof AffectationOperation)) {
                if (!(f.getKey() instanceof SemiAffectationOperation)) {
                    continue;
                }
                //Error
                OperationNode cst_ = f.getKey();
                cst_.setRelativeOffsetPossibleAnalyzable(cst_.getIndexInEl(), _an);
                UnexpectedOperationAffect un_ = new UnexpectedOperationAffect();
                un_.setFileName(_an.getCurrentFileName());
                un_.setRc(_curBlock.getRowCol(_an.getOffset(),_curBlock.getOffset().getOffsetTrim()));
                _an.getClasses().getErrorsDet().add(un_);
                continue;
            }
            AffectationOperation aff_ = (AffectationOperation) f.getKey();
            SettableElResult set_ = aff_.getSettable();
            if (!(set_ instanceof VariableOperation)) {
                continue;
            }
            VariableOperation cst_ = (VariableOperation) set_;
            OperationsSequence op_ = cst_.getOperations();
            if (op_.getConstType() != ConstType.LOC_VAR) {
                continue;
            }
            String originalStr_ = op_.getValues().getValue(CustList.FIRST_INDEX);
            String str_ = originalStr_.trim();
            if (!StringList.quickEq(str_, _field)) {
                continue;
            }
            cst_.setRelativeOffsetPossibleAnalyzable(cst_.getIndexInEl(), _an);
            UnexpectedOperationAffect un_ = new UnexpectedOperationAffect();
            un_.setFileName(_an.getCurrentFileName());
            un_.setRc(_curBlock.getRowCol(_an.getOffset(),_curBlock.getOffset().getOffsetTrim()));
            _an.getClasses().getErrorsDet().add(un_);
        }
    }
    private boolean takeContinue(Block _b,AssignedVariables _ass, Block _doBlock, AnalyzingEl _anEl) {
        Block next_ = _b;
        boolean take_ = false;
        while (next_ != null) {
            next_ = next_.getNextSibling();
        }
        if (next_ == null && _b.getParent() == _doBlock) {
            take_ = true;
        }
        return take_;
    }
}
