package code.expressionlanguage.methods;
import code.expressionlanguage.Analyzable;
import code.expressionlanguage.AnalyzedPageEl;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.OffsetStringInfo;
import code.expressionlanguage.OffsetsBlock;
import code.expressionlanguage.PageEl;
import code.expressionlanguage.PrimitiveTypeUtil;
import code.expressionlanguage.ReadWrite;
import code.expressionlanguage.methods.util.EmptyTagName;
import code.expressionlanguage.opers.AffectationOperation;
import code.expressionlanguage.opers.ConstantOperation;
import code.expressionlanguage.opers.OperationNode;
import code.expressionlanguage.opers.SemiAffectationOperation;
import code.expressionlanguage.opers.SettableElResult;
import code.expressionlanguage.opers.util.AssignedBooleanVariables;
import code.expressionlanguage.opers.util.AssignedVariables;
import code.expressionlanguage.opers.util.Assignment;
import code.expressionlanguage.opers.util.AssignmentBefore;
import code.expressionlanguage.opers.util.BooleanAssignment;
import code.expressionlanguage.opers.util.ClassField;
import code.expressionlanguage.opers.util.ClassMetaInfo;
import code.expressionlanguage.stacks.LoopBlockStack;
import code.sml.Element;
import code.util.CustList;
import code.util.EntryCust;
import code.util.IdMap;
import code.util.NatTreeMap;
import code.util.ObjectMap;
import code.util.StringList;
import code.util.StringMap;

public final class WhileCondition extends Condition implements Loop, IncrNextGroup {

    public WhileCondition(Element _el, ContextEl _importingPage, int _indexChild,
            BracedBlock _m) {
        super(_el, _importingPage, _indexChild, _m);
    }

    public WhileCondition(ContextEl _importingPage, int _indexChild,
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
        if (getFirstChild() == null && !(getPreviousSibling() instanceof DoBlock)) {
            EmptyTagName un_ = new EmptyTagName();
            un_.setFileName(getFile().getFileName());
            un_.setRc(getRowCol(0, getOffset().getOffsetTrim()));
            _cont.getClasses().getErrorsDet().add(un_);
        }
    }

    @Override
    public void defaultAssignmentBefore(Analyzable _an, OperationNode _root) {
        AssignedVariables vars_ = _an.getAssignedVariables().getFinalVariables().getVal(this);
        ObjectMap<ClassField,AssignmentBefore> fields_;
        CustList<StringMap<AssignmentBefore>> variables_;
        fields_ = new ObjectMap<ClassField,AssignmentBefore>();
        variables_ = new CustList<StringMap<AssignmentBefore>>();
        for (EntryCust<ClassField,AssignmentBefore> e: vars_.getFieldsRootBefore().entryList()) {
            AssignmentBefore ab_ = new AssignmentBefore();
            if (e.getValue().isAssignedBefore()) {
                ab_.setAssignedBefore(true);
            } else {
                ab_.setUnassignedBefore(true);
            }
            fields_.put(e.getKey(), ab_);
        }
        vars_.getFieldsBefore().put(_root, fields_);
        for (StringMap<AssignmentBefore> s: vars_.getVariablesRootBefore()) {
            StringMap<AssignmentBefore> sm_ = new StringMap<AssignmentBefore>();
            for (EntryCust<String,AssignmentBefore> e: s.entryList()) {
                AssignmentBefore ab_ = new AssignmentBefore();
                if (e.getValue().isAssignedBefore()) {
                    ab_.setAssignedBefore(true);
                } else {
                    ab_.setUnassignedBefore(true);
                }
                sm_.put(e.getKey(), ab_);
            }
            variables_.add(sm_);
        }
        vars_.getVariablesBefore().put(_root, variables_);
    }

    @Override
    public void setAssignmentAfter(Analyzable _an, AnalyzingEl _anEl) {
        // TODO Auto-generated method stub
        super.setAssignmentAfter(_an, _anEl);
        Block firstChild_ = getFirstChild();
        IdMap<Block, AssignedVariables> id_;
        id_ = _an.getAssignedVariables().getFinalVariables();
        if (firstChild_ == null) {
            //by do block
            DoBlock dBlock_ = (DoBlock) getPreviousSibling();
            AssignedVariables varsDo_;
            varsDo_ = id_.getVal(dBlock_);
            ObjectMap<ClassField,AssignmentBefore> fields_;
            fields_ = new ObjectMap<ClassField,AssignmentBefore>();
            for (EntryCust<ClassField,AssignmentBefore> e: varsDo_.getFieldsRootBefore().entryList()) {
                AssignmentBefore ab_ = new AssignmentBefore();
                if (e.getValue().isAssignedBefore()) {
                    ab_.setAssignedBefore(true);
                } else {
                    ab_.setUnassignedBefore(true);
                }
                fields_.put(e.getKey(), ab_);
            }
            AssignedBooleanVariables varsWhile_;
            varsWhile_ = (AssignedBooleanVariables) id_.getVal(this);
            for (EntryCust<ClassField,BooleanAssignment> e: varsWhile_.getFieldsRootAfter().entryList()) {
                BooleanAssignment ba_ = e.getValue();
                if (!ba_.isUnassignedAfterWhenTrue()) {
                    fields_.getVal(e.getKey()).setUnassignedBefore(false);
                }
            }
            varsDo_.getFieldsRootBefore().putAllMap(fields_);
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
            for (EntryCust<ClassField,AssignmentBefore> e: fields_.entryList()) {
                if (e.getValue().isUnassignedBefore()) {
                    continue;
                }
                if (e.getValue().isAssignedBefore()) {
                    continue;
                }
                for (EntryCust<Block, AssignedVariables> d: allDesc_.entryList()) {
                    vars_ = d.getValue();
                    Block next_ = d.getKey();
                    boolean take_ = false;
                    while (next_ != null) {
                        next_ = next_.getNextSibling();
                    }
                    if (next_ == null && d.getKey().getParent() == dBlock_) {
                        take_ = true;
                    }
                    if (!take_) {
                        continue;
                    }
                    //next siblings of d
                    processFinalVars(vars_);
                }
            }

            ObjectMap<ClassField,Assignment> fieldsAfter_;
            fieldsAfter_ = new ObjectMap<ClassField,Assignment>();
            String boolType_ = _an.getStandards().getAliasBoolean();
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
                String classNameDecl_ = key_.getClassName();
                ClassMetaInfo custClass_;
                custClass_ = _an.getClassMetaInfo(classNameDecl_);
                String type_ = custClass_.getFields().getVal(key_.getFieldName()).getType();
                boolean isBool_ = PrimitiveTypeUtil.canBeUseAsArgument(boolType_, type_, _an);
                fieldsAfter_.put(key_, Assignment.assign(isBool_, ass_, unass_));
            }
            varsDo_.getFieldsRoot().putAllMap(fieldsAfter_);
            return;
        }
        Block last_ = firstChild_;
        while (last_.getNextSibling() != null) {
            last_ = last_.getNextSibling();
        }
        IdMap<Block, AssignedVariables> allDesc_ = new IdMap<Block, AssignedVariables>();
        boolean add_ = false;
        for (EntryCust<Block, AssignedVariables> e: id_.entryList()) {
            if (e.getKey() == this) {
                add_ = true;
            }
            if (add_) {
                allDesc_.put(e.getKey(), e.getValue());
            }
        }
        AssignedBooleanVariables varsWhile_ = (AssignedBooleanVariables) allDesc_.firstValue();
        ObjectMap<ClassField,AssignmentBefore> fields_;
        fields_ = new ObjectMap<ClassField,AssignmentBefore>();
        for (EntryCust<ClassField,AssignmentBefore> e: varsWhile_.getFieldsRootBefore().entryList()) {
            AssignmentBefore ab_ = new AssignmentBefore();
            if (e.getValue().isAssignedBefore()) {
                ab_.setAssignedBefore(true);
            } else {
                ab_.setUnassignedBefore(true);
            }
            fields_.put(e.getKey(), ab_);
        }
        CustList<ContinueBlock> conts_ = new CustList<ContinueBlock>();
        for (EntryCust<ContinueBlock, Loop> e: _anEl.getContinuables().entryList()) {
            if (e.getValue() != this) {
                continue;
            }
            conts_.add(e.getKey());
            AssignedVariables vars_;
            vars_ = id_.getVal(e.getKey());
            for (EntryCust<ClassField,AssignmentBefore> f: vars_.getFieldsRootBefore().entryList()) {
                if (!f.getValue().isUnassignedBefore()) {
                    fields_.getVal(f.getKey()).setUnassignedBefore(false);
                }
            }
        }
        AssignedVariables vars_;
        vars_ = id_.getVal(last_);
        for (EntryCust<ClassField,Assignment> f: vars_.getFieldsRoot().entryList()) {
            if (!f.getValue().isUnassignedAfter()) {
                fields_.getVal(f.getKey()).setUnassignedBefore(false);
            }
        }
        varsWhile_.getFieldsRootBefore().putAllMap(fields_);
        processFinalVars(_anEl, allDesc_, fields_, conts_);
        ObjectMap<ClassField,Assignment> fieldsAfter_;
        fieldsAfter_ = new ObjectMap<ClassField,Assignment>();
        String boolType_ = _an.getStandards().getAliasBoolean();
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
            String classNameDecl_ = key_.getClassName();
            ClassMetaInfo custClass_;
            custClass_ = _an.getClassMetaInfo(classNameDecl_);
            String type_ = custClass_.getFields().getVal(key_.getFieldName()).getType();
            boolean isBool_ = PrimitiveTypeUtil.canBeUseAsArgument(boolType_, type_, _an);
            fieldsAfter_.put(key_, Assignment.assign(isBool_, ass_, unass_));
        }
        varsWhile_.getFieldsRoot().putAllMap(fieldsAfter_);
    }

    private void processFinalVars(AnalyzingEl _anEl,
            IdMap<Block, AssignedVariables> _allDesc,
            ObjectMap<ClassField, AssignmentBefore> _fields,
            CustList<ContinueBlock> _conts) {
        AssignedVariables vars_;
        for (EntryCust<ClassField,AssignmentBefore> e: _fields.entryList()) {
            if (e.getValue().isUnassignedBefore()) {
                continue;
            }
            if (e.getValue().isAssignedBefore()) {
                continue;
            }
            for (EntryCust<Block, AssignedVariables> d: _allDesc.entryList()) {
                vars_ = d.getValue();
                Block next_ = d.getKey();
                boolean take_ = false;
                while (next_ != null) {
                    if (next_ instanceof BracedBlock) {
                        BracedBlock possAnc_ = (BracedBlock) next_;
                        for (ContinueBlock c: _conts) {
                            if (_anEl.getContinuablesAncestors().getVal(c).getVal(this).containsObj(possAnc_)) {
                                take_ = true;
                                break;
                            }
                        }
                        if (take_) {
                            break;
                        }
                    }
                    if (next_ instanceof ContinueBlock) {
                        take_ = true;
                        break;
                    }
                    next_ = next_.getNextSibling();
                }
                if (next_ == null && d.getKey().getParent() == this) {
                    take_ = true;
                }
                if (!take_) {
                    continue;
                }
                //next siblings of d
                processFinalVars(vars_);
            }
        }
    }

    private void processFinalVars(AssignedVariables _vars) {
        for (EntryCust<OperationNode, ObjectMap<ClassField,AssignmentBefore>> f: _vars.getFieldsBefore().entryList()) {
            if (!(f.getKey() instanceof AffectationOperation)) {
                if (!(f.getKey() instanceof SemiAffectationOperation)) {
                    continue;
                }
                //Error
                continue;
            }
            AffectationOperation aff_ = (AffectationOperation) f.getKey();
            SettableElResult set_ = aff_.getSettable();
            if (!(set_ instanceof ConstantOperation)) {
                continue;
            }
            ConstantOperation cst_ = (ConstantOperation) set_;
            if (cst_.getFieldId() == null) {
                continue;
            }
            if (cst_.getFieldMetaInfo().isFinalField()) {
                //error
            }
        }
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
    public String getTagName() {
        return TAG_WHILE;
    }

    @Override
    public void processEl(ContextEl _cont) {
        PageEl ip_ = _cont.getLastPage();
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
        boolean res_ = evaluateCondition(_cont);
        if (_cont.callsOrException()) {
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
        PageEl ip_ = _conf.getLastPage();
        ReadWrite rw_ = ip_.getReadWrite();
        LoopBlockStack l_ = (LoopBlockStack) ip_.getLastStack();
        l_.setEvaluatingKeepLoop(true);
        Block forLoopLoc_ = l_.getBlock();
        rw_.setBlock(forLoopLoc_);
        if (!keepLoop(_conf)) {
            if (_conf.callsOrException()) {
                return;
            }
            l_.setFinished(true);
        }
        l_.setEvaluatingKeepLoop(false);
    }

    public boolean keepLoop(ContextEl _conf) {
        _conf.getLastPage().setGlobalOffset(getOffset().getOffsetTrim());
        _conf.getLastPage().setOffset(0);
        return evaluateCondition(_conf);
    }
    @Override
    public void abruptGroup(Analyzable _an, AnalyzingEl _anEl) {
        if (getFirstChild() == null) {
            return;
        }
        boolean abr_ = true;
        if (_anEl.isReachable(this)) {
            if (!StringList.quickEq(getCondition().trim(), TRUE_STRING)) {
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
