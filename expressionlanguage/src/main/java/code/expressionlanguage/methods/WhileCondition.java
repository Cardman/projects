package code.expressionlanguage.methods;
import code.expressionlanguage.AbstractPageEl;
import code.expressionlanguage.Analyzable;
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
import code.sml.Element;
import code.util.CustList;
import code.util.EntryCust;
import code.util.IdMap;
import code.util.ObjectMap;
import code.util.StringList;
import code.util.StringMap;

public final class WhileCondition extends Condition implements Loop, IncrNextGroup {

    private String label;
    private int labelOffset;

    public WhileCondition(Element _el, ContextEl _importingPage, int _indexChild,
            BracedBlock _m) {
        super(_el, _importingPage, _indexChild, _m);
    }

    public WhileCondition(ContextEl _importingPage, int _indexChild,
            BracedBlock _m, OffsetStringInfo _condition, OffsetStringInfo _label, OffsetsBlock _offset) {
        super(_importingPage, _indexChild, _m, _condition, _offset);
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
    public void setAssignmentBeforeChild(Analyzable _an, AnalyzingEl _anEl) {
        Block firstChild_ = getFirstChild();
        IdMap<Block, AssignedVariables> id_ = _an.getAssignedVariables().getFinalVariables();
        AssignedVariables parAss_ = id_.getVal(this);
        AssignedVariables assBl_ = firstChild_.buildNewAssignedVariable();
        AssignedBooleanVariables abv_ = (AssignedBooleanVariables) parAss_;
        for (EntryCust<ClassField, BooleanAssignment> e: abv_.getFieldsRootAfter().entryList()) {
            BooleanAssignment ba_ = e.getValue();
            AssignmentBefore ab_ = ba_.copyWhenTrue();
            assBl_.getFieldsRootBefore().put(e.getKey(), ab_);
        }
        for (StringMap<BooleanAssignment> s: abv_.getVariablesRootAfter()) {
            StringMap<AssignmentBefore> sm_ = new StringMap<AssignmentBefore>();
            for (EntryCust<String, BooleanAssignment> e: s.entryList()) {
                BooleanAssignment ba_ = e.getValue();
                AssignmentBefore ab_ = ba_.copyWhenTrue();
                sm_.put(e.getKey(), ab_);
            }
            assBl_.getVariablesRootBefore().add(sm_);
        }
        assBl_.getVariablesRootBefore().add(new StringMap<AssignmentBefore>());
        id_.put(firstChild_, assBl_);
    }
    @Override
    public void defaultAssignmentBefore(Analyzable _an, OperationNode _root) {
        AssignedVariables vars_ = _an.getAssignedVariables().getFinalVariables().getVal(this);
        ObjectMap<ClassField,AssignmentBefore> fields_;
        CustList<StringMap<AssignmentBefore>> variables_;
        fields_ = new ObjectMap<ClassField,AssignmentBefore>();
        variables_ = new CustList<StringMap<AssignmentBefore>>();
        for (EntryCust<ClassField,AssignmentBefore> e: vars_.getFieldsRootBefore().entryList()) {
            fields_.put(e.getKey(), e.getValue().copy());
        }
        vars_.getFieldsBefore().put(_root, fields_);
        for (StringMap<AssignmentBefore> s: vars_.getVariablesRootBefore()) {
            StringMap<AssignmentBefore> sm_ = new StringMap<AssignmentBefore>();
            for (EntryCust<String,AssignmentBefore> e: s.entryList()) {
                sm_.put(e.getKey(), e.getValue().copy());
            }
            variables_.add(sm_);
        }
        vars_.getVariablesBefore().put(_root, variables_);
    }

    @Override
    public void setAssignmentAfter(Analyzable _an, AnalyzingEl _anEl) {
        Block firstChild_ = getFirstChild();
        IdMap<Block, AssignedVariables> allDesc_ = new IdMap<Block, AssignedVariables>();
        boolean add_ = false;
        IdMap<Block, AssignedVariables> id_;
        id_ = _an.getAssignedVariables().getFinalVariables();
        for (EntryCust<Block, AssignedVariables> e: id_.entryList()) {
            if (e.getKey() == this) {
                add_ = true;
            }
            if (add_) {
                allDesc_.put(e.getKey(), e.getValue());
            }
        }
        AssignedBooleanVariables varsWhile_ = (AssignedBooleanVariables) allDesc_.firstValue();
        if (firstChild_ == null) {
            super.setAssignmentAfter(_an, _anEl);
            EmptyTagName un_ = new EmptyTagName();
            un_.setFileName(getFile().getFileName());
            un_.setRc(getRowCol(0, getOffset().getOffsetTrim()));
            _an.getClasses().getErrorsDet().add(un_);
            ObjectMap<ClassField,SimpleAssignment> fieldsAfter_;
            fieldsAfter_ = new ObjectMap<ClassField,SimpleAssignment>();
            for (EntryCust<ClassField,BooleanAssignment> e: varsWhile_.getFieldsRootAfter().entryList()) {
                BooleanAssignment ba_ = e.getValue();
                boolean ass_ = ba_.isAssignedAfterWhenFalse();
                boolean unass_ = ba_.isUnassignedAfterWhenFalse();
                for (EntryCust<BreakBlock, BreakableBlock> f: _anEl.getBreakables().entryList()) {
                    if (f.getValue() != this) {
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
            varsWhile_.getFieldsRoot().putAllMap(fieldsAfter_);
            CustList<StringMap<SimpleAssignment>> varsAfter_;
            varsAfter_ = new CustList<StringMap<SimpleAssignment>>();
            int index_ = 0;
            for (StringMap<BooleanAssignment> s: varsWhile_.getVariablesRootAfter()) {
                StringMap<SimpleAssignment> sm_ = new StringMap<SimpleAssignment>();
                for (EntryCust<String,BooleanAssignment> e: s.entryList()) {
                    BooleanAssignment ba_ = e.getValue();
                    boolean ass_ = ba_.isAssignedAfterWhenFalse();
                    boolean unass_ = ba_.isUnassignedAfterWhenFalse();
                    String key_ = e.getKey();
                    for (EntryCust<BreakBlock, BreakableBlock> f: _anEl.getBreakables().entryList()) {
                        if (f.getValue() != this) {
                            continue;
                        }
                        CustList<StringMap<AssignmentBefore>> list_;
                        list_ = id_.getVal(f.getKey()).getVariablesRootBefore();
                        StringMap<AssignmentBefore> sa_;
                        sa_ = list_.get(index_);
                        if (!sa_.contains(key_)) {
                            continue;
                        }
                        if (!sa_.getVal(key_).isAssignedBefore()) {
                            ass_ = false;
                        }
                        if (!sa_.getVal(key_).isUnassignedBefore()) {
                            unass_ = false;
                        }
                    }
                    sm_.put(key_, Assignment.assignClassic(ass_, unass_));
                }
                varsAfter_.add(sm_);
                index_++;
            }
            varsWhile_.getVariablesRoot().clear();
            varsWhile_.getVariablesRoot().addAllElts(varsAfter_);
            return;
        }
        ObjectMap<ClassField,AssignmentBefore> fieldsHypot_;
        fieldsHypot_ = makeHypothesisFields(_an);
        CustList<StringMap<AssignmentBefore>> varsHypot_;
        varsHypot_ = makeHypothesisVars(_an);
        Block last_ = firstChild_;
        while (last_.getNextSibling() != null) {
            last_ = last_.getNextSibling();
        }
        for (EntryCust<ContinueBlock, Loop> e: _anEl.getContinuables().entryList()) {
            if (e.getValue() != this) {
                continue;
            }
            AssignedVariables vars_;
            vars_ = id_.getVal(e.getKey());
            for (EntryCust<ClassField,AssignmentBefore> f: vars_.getFieldsRootBefore().entryList()) {
                if (!f.getValue().isUnassignedBefore()) {
                    fieldsHypot_.getVal(f.getKey()).setUnassignedBefore(false);
                }
            }
            int index_ = 0;
            for (StringMap<AssignmentBefore> s: vars_.getVariablesRootBefore()) {
                for (EntryCust<String,AssignmentBefore> f: s.entryList()) {
                    if (!f.getValue().isUnassignedBefore()) {
                        varsHypot_.get(index_).getVal(f.getKey()).setUnassignedBefore(false);
                    }
                }
                index_++;
            }
        }
        int index_ = 0;
        if (_anEl.canCompleteNormallyGroup(last_)) {
            AssignedVariables vars_;
            vars_ = id_.getVal(last_);
            for (EntryCust<ClassField,SimpleAssignment> f: vars_.getFieldsRoot().entryList()) {
                if (!f.getValue().isUnassignedAfter()) {
                    fieldsHypot_.getVal(f.getKey()).setUnassignedBefore(false);
                }
            }
            for (StringMap<SimpleAssignment> s: vars_.getVariablesRoot()) {
                if (index_ >= varsHypot_.size()) {
                    continue;
                }
                for (EntryCust<String,SimpleAssignment> f: s.entryList()) {
                    if (!f.getValue().isUnassignedAfter()) {
                        varsHypot_.get(index_).getVal(f.getKey()).setUnassignedBefore(false);
                    }
                }
                index_++;
            }
        }
        varsWhile_.getFieldsRootBefore().putAllMap(fieldsHypot_);
        varsWhile_.getVariablesRootBefore().clear();
        varsWhile_.getVariablesRootBefore().addAllElts(varsHypot_);
        processFinalFields(_an, _anEl, allDesc_, fieldsHypot_);
        processFinalVars(_an, _anEl, allDesc_, varsHypot_);
        ObjectMap<ClassField,SimpleAssignment> fieldsAfter_;
        fieldsAfter_ = new ObjectMap<ClassField,SimpleAssignment>();
        for (EntryCust<ClassField,BooleanAssignment> e: varsWhile_.getFieldsRootAfter().entryList()) {
            BooleanAssignment ba_ = e.getValue();
            boolean ass_ = ba_.isAssignedAfterWhenFalse();
            boolean unass_ = ba_.isUnassignedAfterWhenFalse();
            for (EntryCust<BreakBlock, BreakableBlock> f: _anEl.getBreakables().entryList()) {
                if (f.getValue() != this) {
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
        varsWhile_.getFieldsRoot().putAllMap(fieldsAfter_);
        CustList<StringMap<SimpleAssignment>> varsAfter_;
        varsAfter_ = new CustList<StringMap<SimpleAssignment>>();
        index_ = 0;
        for (StringMap<BooleanAssignment> s: varsWhile_.getVariablesRootAfter()) {
            StringMap<SimpleAssignment> sm_ = new StringMap<SimpleAssignment>();
            for (EntryCust<String,BooleanAssignment> e: s.entryList()) {
                BooleanAssignment ba_ = e.getValue();
                boolean ass_ = ba_.isAssignedAfterWhenFalse();
                boolean unass_ = ba_.isUnassignedAfterWhenFalse();
                String key_ = e.getKey();
                for (EntryCust<BreakBlock, BreakableBlock> f: _anEl.getBreakables().entryList()) {
                    if (f.getValue() != this) {
                        continue;
                    }
                    CustList<StringMap<AssignmentBefore>> list_;
                    list_ = id_.getVal(f.getKey()).getVariablesRootBefore();
                    StringMap<AssignmentBefore> sa_;
                    sa_ = list_.get(index_);
                    if (!sa_.contains(key_)) {
                        continue;
                    }
                    if (!sa_.getVal(key_).isAssignedBefore()) {
                        ass_ = false;
                    }
                    if (!sa_.getVal(key_).isUnassignedBefore()) {
                        unass_ = false;
                    }
                }
                sm_.put(key_, Assignment.assignClassic(ass_, unass_));
            }
            varsAfter_.add(sm_);
            index_++;
        }
        varsWhile_.getVariablesRoot().clear();
        varsWhile_.getVariablesRoot().addAllElts(varsAfter_);
    }

    private void processFinalFields(Analyzable _an,AnalyzingEl _anEl,
            IdMap<Block, AssignedVariables> _allDesc,
            ObjectMap<ClassField, AssignmentBefore> _fields) {
        AssignedVariables vars_;
        for (EntryCust<ClassField,AssignmentBefore> e: _fields.entryList()) {
            if (e.getValue().isUnassignedBefore()) {
                continue;
            }
            if (e.getValue().isAssignedBefore()) {
                continue;
            }
            ClassField key_ = e.getKey();
            ClassMetaInfo cl_ = _an.getClassMetaInfo(key_.getClassName());
            FieldMetaInfo fm_ = cl_.getFieldsInfos().getVal(key_.getFieldName());
            if (!fm_.isFinalField()) {
                continue;
            }
            for (EntryCust<Block, AssignedVariables> d: _allDesc.entryList()) {
                vars_ = d.getValue();
                Block next_ = d.getKey();
                //next siblings of d
                processFinalFields(next_, _an, vars_, key_);
            }
        }
    }

    private void processFinalFields(Block _curBlock, Analyzable _an,AssignedVariables _vars, ClassField _field) {
        for (EntryCust<OperationNode, ObjectMap<ClassField,AssignmentBefore>> f: _vars.getFieldsBefore().entryList()) {
            if (!(f.getKey() instanceof AffectationOperation)) {
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
    private void processFinalVars(Analyzable _an,AnalyzingEl _anEl,
            IdMap<Block, AssignedVariables> _allDesc,
            CustList<StringMap<AssignmentBefore>> _fields) {
        AssignedVariables vars_;
        int index_ = 0;
        for (StringMap<AssignmentBefore> s: _fields) {
            for (EntryCust<String,AssignmentBefore> e: s.entryList()) {
                if (e.getValue().isUnassignedBefore()) {
                    continue;
                }
                if (e.getValue().isAssignedBefore()) {
                    continue;
                }
                if (!_an.getLocalVariables().isValidIndex(index_)) {
                    continue;
                }
                String key_ = e.getKey();
                StringMap<LocalVariable> varLocs_;
                varLocs_ = _an.getLocalVariables().get(index_);
                LocalVariable varLoc_ = varLocs_.getVal(key_);
                if (!varLoc_.isFinalVariable()) {
                    continue;
                }
                for (EntryCust<Block, AssignedVariables> d: _allDesc.entryList()) {
                    vars_ = d.getValue();
                    Block next_ = d.getKey();
                    //next siblings of d
                    processFinalVars(next_, _an, vars_, key_);
                }
            }
            index_++;
        }
        
    }
    private void processFinalVars(Block _curBlock, Analyzable _an,AssignedVariables _vars, String _field) {
        for (EntryCust<OperationNode,CustList<StringMap<AssignmentBefore>>> f: _vars.getVariablesBefore().entryList()) {
            if (!(f.getKey() instanceof AffectationOperation)) {
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
    @Override
    boolean canBeIncrementedNextGroup() {
        return false;
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
    public String getTagName() {
        return TAG_WHILE;
    }

    @Override
    public void processEl(ContextEl _cont) {
        AbstractPageEl ip_ = _cont.getLastPage();
        ReadWrite rw_ = ip_.getReadWrite();
        LoopBlockStack c_ = ip_.getLastLoopIfPossible();
        if (c_ != null && c_.getBlock() == this) {
            if (c_.isEvaluatingKeepLoop()) {
                processLastElementLoop(_cont);
                return;
            }
            if (c_.isFinished()) {
                removeVarAndLoop(ip_);
                processBlock(_cont);
                return;
            }
            rw_.setBlock(getFirstChild());
            return;
        }
        Boolean res_ = evaluateCondition(_cont);
        if (res_ == null) {
            return;
        }
        LoopBlockStack l_ = new LoopBlockStack();
        l_.setBlock(this);
        l_.setFinished(!res_);
        ip_.addBlock(l_);
        c_ = (LoopBlockStack) ip_.getLastStack();
        if (c_.isFinished()) {
            ip_.removeLastBlock();
            processBlock(_cont);
            return;
        }
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
        LoopBlockStack l_ = (LoopBlockStack) ip_.getLastStack();
        l_.setEvaluatingKeepLoop(true);
        Block forLoopLoc_ = l_.getBlock();
        rw_.setBlock(forLoopLoc_);
        Boolean keep_ = keepLoop(_conf);
        if (keep_ == null) {
            return;
        }
        if (!keep_) {
            l_.setFinished(true);
        }
        l_.setEvaluatingKeepLoop(false);
    }

    public Boolean keepLoop(ContextEl _conf) {
        _conf.getLastPage().setGlobalOffset(getOffset().getOffsetTrim());
        _conf.getLastPage().setOffset(0);
        return evaluateCondition(_conf);
    }
    @Override
    public boolean accessibleCondition() {
        OperationNode op_ = getElCondition().getRoot();
        boolean accessible_ = false;
        Argument arg_ = op_.getArgument();
        if (op_.getArgument() == null) {
            accessible_ = true;
        } else if (!(arg_.getObject() instanceof Boolean)) {
            accessible_ = true;
        } else if ((Boolean)arg_.getObject()) {
            accessible_ = true;
        }
        return accessible_;
    }
    @Override
    public void abruptGroup(Analyzable _an, AnalyzingEl _anEl) {
        boolean abr_ = true;
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
        if (_anEl.isReachable(this)) {
            if (!proc_) {
                abr_ = false;
            }
        }
        if (abr_) {
            IdMap<BreakBlock, BreakableBlock> breakables_;
            breakables_ = _anEl.getBreakables();
            for (EntryCust<BreakBlock, BreakableBlock> e: breakables_.entryList()) {
                if (e.getValue() == this && _anEl.isReachable(e.getKey())) {
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
}
