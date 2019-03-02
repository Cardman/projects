package code.expressionlanguage.methods;
import code.expressionlanguage.Analyzable;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.errors.custom.UnexpectedOperationAffect;
import code.expressionlanguage.files.OffsetsBlock;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.instr.ConstType;
import code.expressionlanguage.instr.OperationsSequence;
import code.expressionlanguage.opers.AffectationOperation;
import code.expressionlanguage.opers.MutableLoopVariableOperation;
import code.expressionlanguage.opers.SettableAbstractFieldOperation;
import code.expressionlanguage.opers.SettableElResult;
import code.expressionlanguage.opers.VariableOperation;
import code.expressionlanguage.opers.util.*;
import code.expressionlanguage.variables.LocalVariable;
import code.expressionlanguage.variables.LoopVariable;
import code.util.CustList;
import code.util.EntryCust;
import code.util.IdList;
import code.util.IdMap;
import code.util.StringList;
import code.util.StringMap;

public abstract class BracedStack extends BracedBlock {

    BracedStack(ContextEl _importingPage,
            BracedBlock _m, OffsetsBlock _offset) {
        super(_m, _offset);
    }
    
    protected void buildConditions(ContextEl _cont) {
        AssignedBooleanVariables res_ = (AssignedBooleanVariables) _cont.getAnalyzing().getAssignedVariables().getFinalVariables().getVal(this);
        res_.getFieldsRootAfter().putAllMap(AssignmentsUtil.toBoolAssign(res_.getLastFieldsOrEmpty()));
        res_.getVariablesRootAfter().addAllElts(AssignmentsUtil.toBoolAssign(res_.getLastVariablesOrEmpty()));
        res_.getMutableLoopRootAfter().addAllElts(AssignmentsUtil.toBoolAssign(res_.getLastMutableLoopOrEmpty()));
    }
    protected void assignWhenFalse(boolean _add,Analyzable _an, AnalyzingEl _anEl) {
        Block firstChild_;
        IdMap<Block, AssignedVariables> id_ = _an.getAssignedVariables().getFinalVariables();
        AssignedVariables parAss_ = id_.getVal(this);
        Block pr_ = getPreviousSibling();
        if (_add) {
            firstChild_ = getFirstChild();
            parAss_ = id_.getVal(pr_);
        } else {
            firstChild_ = getNextSibling();
        }
        AssignedVariables assBl_ = firstChild_.buildNewAssignedVariable();
        if (!(parAss_ instanceof AssignedBooleanVariables)) {
            super.setAssignmentBeforeChild(_an, _anEl);
            return;
        }
        AssignedBooleanVariables abv_ = (AssignedBooleanVariables) parAss_;
        assBl_.getFieldsRootBefore().putAllMap(AssignmentsUtil.assignBoolWhenFalse(abv_.getFieldsRootAfter()));
        assBl_.getVariablesRootBefore().addAllElts(AssignmentsUtil.assignBoolWhenFalse(abv_.getVariablesRootAfter()));
        assBl_.getMutableLoopRootBefore().addAllElts(AssignmentsUtil.assignBoolWhenFalse(abv_.getMutableLoopRootAfter()));
        if (_add) {
            assBl_.getVariablesRootBefore().add(new StringMap<AssignmentBefore>());
            assBl_.getMutableLoopRootBefore().add(new StringMap<AssignmentBefore>());
        }
        id_.put(firstChild_, assBl_);
    }
    protected void assignWhenTrue(Analyzable _an) {
        Block firstChild_ = getFirstChild();
        IdMap<Block, AssignedVariables> id_ = _an.getAssignedVariables().getFinalVariables();
        AssignedVariables parAss_ = id_.getVal(this);
        AssignedVariables assBl_ = firstChild_.buildNewAssignedVariable();
        AssignedBooleanVariables abv_ = (AssignedBooleanVariables) parAss_;
        assBl_.getFieldsRootBefore().putAllMap(AssignmentsUtil.assignBoolWhenTrue(abv_.getFieldsRootAfter()));
        assBl_.getVariablesRootBefore().addAllElts(AssignmentsUtil.assignBoolWhenTrue(abv_.getVariablesRootAfter()));
        assBl_.getMutableLoopRootBefore().addAllElts(AssignmentsUtil.assignBoolWhenTrue(abv_.getMutableLoopRootAfter()));
        assBl_.getVariablesRootBefore().add(new StringMap<AssignmentBefore>());
        assBl_.getMutableLoopRootBefore().add(new StringMap<AssignmentBefore>());
        id_.put(firstChild_, assBl_);
    }
    protected void processFinalFields(Analyzable _an,
                                      IdMap<Block, AssignedVariables> _allDesc,
                                      AssignedVariables _root,
                                      StringMap<AssignmentBefore> _fields) {
        AssignedVariables vars_;
        for (EntryCust<String,AssignmentBefore> e: _fields.entryList()) {
            if (e.getValue().isUnassignedBefore()) {
                continue;
            }
            if (e.getValue().isAssignedBefore()) {
                continue;
            }
            String key_ = e.getKey();
            String cl_ = Templates.getIdFromAllTypes(_an.getGlobalClass());
            ClassField id_ = new ClassField(cl_,key_);
            FieldInfo meta_ = _an.getFieldInfo(id_);
            if (!meta_.isFinalField()) {
                continue;
            }
            processFinalFields(this, false, _an, _root, key_);
            for (EntryCust<Block, AssignedVariables> d: _allDesc.entryList()) {
                vars_ = d.getValue();
                Block next_ = d.getKey();
                //next siblings of d
                processFinalFields(next_, true, _an, vars_, key_);
            }
        }
    }
    
    protected void processFinalVars(Analyzable _an,
                                    IdMap<Block, AssignedVariables> _allDesc,
                                    AssignedVariables _root,
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
                String key_ = e.getKey();
                LocalVariable varLoc_ = _an.getLocalVar(key_,index_);
                if (varLoc_ != null && !varLoc_.isFinalVariable()) {
                    continue;
                }
                processFinalVars(this, false, _an, _root, key_);
                for (EntryCust<Block, AssignedVariables> d: _allDesc.entryList()) {
                    vars_ = d.getValue();
                    Block next_ = d.getKey();
                    //next siblings of d
                    processFinalVars(next_,true, _an, vars_, key_);
                }
            }
            index_++;
        }
        
    }

    protected void processFinalMutableLoop(Analyzable _an,
                                           IdMap<Block, AssignedVariables> _allDesc,
                                           AssignedVariables _root,
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
                String key_ = e.getKey();
                LoopVariable varLoc_ = _an.getMutableLoopVar(key_,index_);
                if (varLoc_ != null && !varLoc_.isFinalVariable()) {
                    continue;
                }
                processFinalMutableLoop(this, false, _an, _root, key_);
                for (EntryCust<Block, AssignedVariables> d: _allDesc.entryList()) {
                    vars_ = d.getValue();
                    Block next_ = d.getKey();
                    //next siblings of d
                    processFinalMutableLoop(next_, true, _an, vars_, key_);
                }
            }
            index_++;
        }
        
    }
    protected static void processFinalVars(Block _curBlock, boolean _all, Analyzable _an,AssignedVariables _vars, String _field) {
        for (AffectationOperation f: _vars.getVariablesBefore(_curBlock,_all)) {
            SettableElResult set_ = f.getSettable();
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
            un_.setIndexFile(_an.getOffset()+_curBlock.getOffset().getOffsetTrim());
            _an.getClasses().addError(un_);
        }
    }
    protected static void processFinalMutableLoop(Block _curBlock, boolean _all, Analyzable _an,AssignedVariables _vars, String _field) {
        for (AffectationOperation f: _vars.getMutableLoopBefore(_curBlock,_all)) {
            SettableElResult set_ = f.getSettable();
            if (!(set_ instanceof MutableLoopVariableOperation)) {
                continue;
            }
            MutableLoopVariableOperation cst_ = (MutableLoopVariableOperation) set_;
            OperationsSequence op_ = cst_.getOperations();
            String originalStr_ = op_.getValues().getValue(CustList.FIRST_INDEX);
            String str_ = originalStr_.trim();
            if (!StringList.quickEq(str_, _field)) {
                continue;
            }
            cst_.setRelativeOffsetPossibleAnalyzable(cst_.getIndexInEl(), _an);
            UnexpectedOperationAffect un_ = new UnexpectedOperationAffect();
            un_.setFileName(_an.getCurrentFileName());
            un_.setIndexFile(_an.getOffset()+_curBlock.getOffset().getOffsetTrim());
            _an.getClasses().addError(un_);
        }
    }
    protected static void processFinalFields(Block _curBlock, boolean _all, Analyzable _an,AssignedVariables _vars, String _field) {
        for (AffectationOperation f: _vars.getFieldsBefore(_curBlock,_all)) {
            SettableElResult set_ = f.getSettable();
            if (!(set_ instanceof SettableAbstractFieldOperation)) {
                continue;
            }
            SettableAbstractFieldOperation cst_ = (SettableAbstractFieldOperation) set_;
            String cl_ = Templates.getIdFromAllTypes(_an.getGlobalClass());
            ClassField key_ = new ClassField(cl_,_field);
            if (!cst_.matchFieldId(key_)) {
                continue;
            }
            cst_.setRelativeOffsetPossibleAnalyzable(cst_.getIndexInEl(), _an);
            UnexpectedOperationAffect un_ = new UnexpectedOperationAffect();
            un_.setFileName(_an.getCurrentFileName());
            un_.setIndexFile(_an.getOffset()+_curBlock.getOffset().getOffsetTrim());
            _an.getClasses().addError(un_);
        }
    }
    protected StringMap<AssignmentBefore> buildAssListFieldBeforeIncrPart(Analyzable _an, AnalyzingEl _anEl) {
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
            return beforeIncrPart(list_, v_, breakAss_);
        }
        return beforeIncrPart(list_, new StringMap<SimpleAssignment>(), breakAss_);
    }
    protected CustList<StringMap<AssignmentBefore>> buildAssListLocVarBeforeIncrPart(Analyzable _an, AnalyzingEl _anEl) {
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
                    varsList_.add(beforeIncrPart(cond_, v_.get(i), breakAss_));
                }
            } else {
                varsList_.add(beforeIncrPart(cond_, new StringMap<SimpleAssignment>(), breakAss_));
            }
        }
        
        return varsList_;
    }
    protected CustList<StringMap<AssignmentBefore>> buildAssListMutableLoopBeforeIncrPart(Analyzable _an, AnalyzingEl _anEl) {
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
        list_ = id_.getVal(this).getMutableLoopRoot();
        int contLen_ = continues_.size();
        int loopLen_ = list_.size();
        for (int i = 0; i < loopLen_; i++) {
            CustList<StringMap<AssignmentBefore>> breakAss_;
            breakAss_ = new CustList<StringMap<AssignmentBefore>>();
            for (int j = 0; j < contLen_; j++) {
                ContinueBlock br_ = continues_.get(j);
                AssignedVariables ass_ = id_.getVal(br_);
                CustList<StringMap<AssignmentBefore>> vars_ = ass_.getMutableLoopRootBefore();
                if (!vars_.isValidIndex(i)) {
                    continue;
                }
                breakAss_.add(vars_.get(i));
            }
            StringMap<SimpleAssignment> cond_ = list_.get(i);
            if (_anEl.canCompleteNormallyGroup(last_)) {
                AssignedVariables ass_ = id_.getVal(last_);
                CustList<StringMap<SimpleAssignment>> v_ = ass_.getMutableLoopRoot();
                if (v_.isValidIndex(i)) {
                    varsList_.add(beforeIncrPart(cond_, v_.get(i), breakAss_));
                }
            } else {
                varsList_.add(beforeIncrPart(cond_, new StringMap<SimpleAssignment>(), breakAss_));
            }
        }
        
        return varsList_;
    }
    private static StringMap<AssignmentBefore> beforeIncrPart(StringMap<SimpleAssignment> _loop, StringMap<SimpleAssignment> _last,
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
    protected CustList<StringMap<SimpleAssignment>> buildAssVariablesAfterIf(boolean _useBool,
            CustList<Block> _blocks,Analyzable _an, AnalyzingEl _anEl) {
        CustList<StringMap<SimpleAssignment>> out_;
        out_ = new CustList<StringMap<SimpleAssignment>>();
        IdMap<Block, AssignedVariables> id_;
        id_ = _an.getAssignedVariables().getFinalVariables();
        AssignedVariables idStdIf_;
        idStdIf_ = id_.getVal(this);
        CustList<StringMap<SimpleAssignment>> list_;
        list_ = idStdIf_.getVariablesRoot();
        int len_ = list_.size();
        for (int i = 0; i < len_; i++) {
            StringMap<BooleanAssignment> bool_;
            if (_useBool) {
                AssignedBooleanVariables idIf_;
                idIf_ = (AssignedBooleanVariables) id_.getVal(this);
                CustList<StringMap<BooleanAssignment>> bools_;
                bools_ = idIf_.getVariablesRootAfter();
                if (bools_.isValidIndex(i)) {
                    bool_ = bools_.get(i);
                } else {
                    bool_ = new StringMap<BooleanAssignment>();
                }
            } else {
                bool_ = new StringMap<BooleanAssignment>();
            }
            CustList<StringMap<SimpleAssignment>> listBl_;
            listBl_ = new CustList<StringMap<SimpleAssignment>>();
            CustList<CustList<StringMap<AssignmentBefore>>> listBrs_;
            listBrs_ = new CustList<CustList<StringMap<AssignmentBefore>>>();
            for (Block c: _blocks) {
                CustList<BreakBlock> breaks_ = ((BracedStack)c).getBreakables(_anEl);
                CustList<StringMap<AssignmentBefore>> listBr_;
                listBr_ = new CustList<StringMap<AssignmentBefore>>();
                if (_anEl.canCompleteNormally(c)) {
                    CustList<StringMap<SimpleAssignment>> map_;
                    map_ = id_.getVal(c).getVariablesRoot();
                    if (map_.isValidIndex(i)) {
                        listBl_.add(map_.get(i));
                    } else {
                        listBl_.add(new StringMap<SimpleAssignment>());
                    }
                } else {
                    listBl_.add(new StringMap<SimpleAssignment>());
                }
                for (BreakBlock b: breaks_) {
                    CustList<StringMap<AssignmentBefore>> map_;
                    map_ = id_.getVal(b).getVariablesRootBefore();
                    if (map_.isValidIndex(i)) {
                        listBr_.add(map_.get(i));
                    } else {
                        listBr_.add(new StringMap<AssignmentBefore>());
                    }
                }
                listBrs_.add(listBr_);
            }
            StringMap<SimpleAssignment> if_ = list_.get(i);
            out_.add(buildAssAfterIf(if_, bool_, listBl_, listBrs_));
        }
        return out_;
    }
    protected CustList<StringMap<SimpleAssignment>> buildAssMutableLoopAfterIf(boolean _useBool,
            CustList<Block> _blocks,Analyzable _an, AnalyzingEl _anEl) {
        CustList<StringMap<SimpleAssignment>> out_;
        out_ = new CustList<StringMap<SimpleAssignment>>();
        IdMap<Block, AssignedVariables> id_;
        id_ = _an.getAssignedVariables().getFinalVariables();
        AssignedVariables idStdIf_;
        idStdIf_ = id_.getVal(this);
        CustList<StringMap<SimpleAssignment>> list_;
        list_ = idStdIf_.getMutableLoopRoot();
        int len_ = list_.size();
        for (int i = 0; i < len_; i++) {
            StringMap<BooleanAssignment> bool_;
            if (_useBool) {
                AssignedBooleanVariables idIf_;
                idIf_ = (AssignedBooleanVariables) id_.getVal(this);
                CustList<StringMap<BooleanAssignment>> bools_;
                bools_ = idIf_.getMutableLoopRootAfter();
                if (bools_.isValidIndex(i)) {
                    bool_ = bools_.get(i);
                } else {
                    bool_ = new StringMap<BooleanAssignment>();
                }
            } else {
                bool_ = new StringMap<BooleanAssignment>();
            }
            CustList<StringMap<SimpleAssignment>> listBl_;
            listBl_ = new CustList<StringMap<SimpleAssignment>>();
            CustList<CustList<StringMap<AssignmentBefore>>> listBrs_;
            listBrs_ = new CustList<CustList<StringMap<AssignmentBefore>>>();
            for (Block c: _blocks) {
                CustList<BreakBlock> breaks_ = ((BracedStack)c).getBreakables(_anEl);
                CustList<StringMap<AssignmentBefore>> listBr_;
                listBr_ = new CustList<StringMap<AssignmentBefore>>();
                if (_anEl.canCompleteNormally(c)) {
                    CustList<StringMap<SimpleAssignment>> map_;
                    map_ = id_.getVal(c).getMutableLoopRoot();
                    if (map_.isValidIndex(i)) {
                        listBl_.add(map_.get(i));
                    } else {
                        listBl_.add(new StringMap<SimpleAssignment>());
                    }
                } else {
                    listBl_.add(new StringMap<SimpleAssignment>());
                }
                for (BreakBlock b: breaks_) {
                    CustList<StringMap<AssignmentBefore>> map_;
                    map_ = id_.getVal(b).getMutableLoopRootBefore();
                    if (map_.isValidIndex(i)) {
                        listBr_.add(map_.get(i));
                    } else {
                        listBr_.add(new StringMap<AssignmentBefore>());
                    }
                }
                listBrs_.add(listBr_);
            }
            StringMap<SimpleAssignment> if_ = list_.get(i);
            out_.add(buildAssAfterIf(if_, bool_, listBl_, listBrs_));
        }
        return out_;
    }
    protected StringMap<SimpleAssignment> buildAssFieldsAfterIf(boolean _useBool,
            CustList<Block> _blocks,Analyzable _an, AnalyzingEl _anEl) {
        IdMap<Block, AssignedVariables> id_;
        id_ = _an.getAssignedVariables().getFinalVariables();
        AssignedVariables idStdIf_;
        idStdIf_ = id_.getVal(this);
        StringMap<SimpleAssignment> list_;
        list_ = idStdIf_.getFieldsRoot();
        StringMap<BooleanAssignment> bool_;
        if (_useBool) {
            AssignedBooleanVariables idIf_;
            idIf_ = (AssignedBooleanVariables) id_.getVal(this);
            bool_ = idIf_.getFieldsRootAfter();
        } else {
            bool_ = new StringMap<BooleanAssignment>();
        }
        CustList<StringMap<SimpleAssignment>> listBl_;
        listBl_ = new CustList<StringMap<SimpleAssignment>>();
        CustList<CustList<StringMap<AssignmentBefore>>> listBrs_;
        listBrs_ = new CustList<CustList<StringMap<AssignmentBefore>>>();
        for (Block c: _blocks) {
            CustList<BreakBlock> breaks_ = ((BracedStack)c).getBreakables(_anEl);
            CustList<StringMap<AssignmentBefore>> listBr_;
            listBr_ = new CustList<StringMap<AssignmentBefore>>();
            if (_anEl.canCompleteNormally(c)) {
                listBl_.add(id_.getVal(c).getFieldsRoot());
            } else {
                listBl_.add(new StringMap<SimpleAssignment>());
            }
            for (BreakBlock b: breaks_) {
                listBr_.add(id_.getVal(b).getFieldsRootBefore());
            }
            listBrs_.add(listBr_);
        }
        return buildAssAfterIf(list_, bool_, listBl_, listBrs_);
    }
    protected static StringMap<SimpleAssignment> buildAssAfterIf(StringMap<SimpleAssignment> _ifAfter,
            StringMap<BooleanAssignment> _afterCond,
            CustList<StringMap<SimpleAssignment>> _blocks,
            CustList<CustList<StringMap<AssignmentBefore>>> _breaks) {
        StringMap<SimpleAssignment> out_ = new StringMap<SimpleAssignment>();
        int len_ = _blocks.size();
        for (EntryCust<String, SimpleAssignment> e: _ifAfter.entryList()) {
            String key_ = e.getKey();
            boolean assAfterAll_ = true;
            boolean unassAfterAll_ = true;
            for (EntryCust<String, BooleanAssignment> f: _afterCond.entryList()) {
                if (!StringList.quickEq(key_, f.getKey())) {
                    continue;
                }
                if(!f.getValue().isAssignedAfterWhenFalse()) {
                    assAfterAll_ = false;
                }
                if(!f.getValue().isUnassignedAfterWhenFalse()) {
                    unassAfterAll_ = false;
                }
            }
            for (int i = 0; i < len_; i++) {
                boolean assAfter_ = true;
                boolean unassAfter_ = true;
                for (EntryCust<String, SimpleAssignment> f: _blocks.get(i).entryList()) {
                    if (!StringList.quickEq(key_, f.getKey())) {
                        continue;
                    }
                    if(!f.getValue().isAssignedAfter()) {
                        assAfter_ = false;
                    }
                    if(!f.getValue().isUnassignedAfter()) {
                        unassAfter_ = false;
                    }
                }
                for (StringMap<AssignmentBefore> b: _breaks.get(i)) {
                    if (!b.getVal(key_).isAssignedBefore()) {
                        assAfter_ = false;
                    }
                    if (!b.getVal(key_).isUnassignedBefore()) {
                        unassAfter_ = false;
                    }
                }
                if (!assAfter_) {
                    assAfterAll_ = false;
                }
                if (!unassAfter_) {
                    unassAfterAll_ = false;
                }
            }
            out_.put(key_, Assignment.assignClassic(assAfterAll_, unassAfterAll_));
        }
        return out_;
    }
    protected CustList<StringMap<SimpleAssignment>> buildAssVariablesAfterSwitch(
            boolean _default,
            Block _last,
            Analyzable _an, AnalyzingEl _anEl) {
        IdMap<Block, AssignedVariables> id_;
        id_ = _an.getAssignedVariables().getFinalVariables();
        CustList<StringMap<Assignment>> list_;
        list_ = id_.getVal(this).getLastVariablesOrEmpty();
        int len_ = list_.size();
        CustList<StringMap<SimpleAssignment>> out_;
        out_ = new CustList<StringMap<SimpleAssignment>>();
        for (int i = 0; i < len_; i++) {
            StringMap<Assignment> switch_ = list_.get(i);
            StringMap<SimpleAssignment> last_;
            if (_anEl.canCompleteNormally(_last)) {
                CustList<StringMap<SimpleAssignment>> map_;
                map_ = id_.getVal(_last).getVariablesRoot();
                if (map_.isValidIndex(i)) {
                    last_ = map_.get(i);
                } else {
                    last_ = new StringMap<SimpleAssignment>();
                }
            } else {
                last_ = new StringMap<SimpleAssignment>();
            }
            CustList<BreakBlock> breaks_ = getBreakables(_anEl);
            CustList<StringMap<AssignmentBefore>> listBr_;
            listBr_ = new CustList<StringMap<AssignmentBefore>>();
            for (BreakBlock b: breaks_) {
                CustList<StringMap<AssignmentBefore>> map_;
                map_ = id_.getVal(b).getVariablesRootBefore();
                if (map_.isValidIndex(i)) {
                    listBr_.add(map_.get(i));
                } else {
                    listBr_.add(new StringMap<AssignmentBefore>());
                }
            }
            out_.add(buildAssAfterSwitch(_default, switch_, last_, listBr_));
        }
        return out_;
    }
    protected CustList<StringMap<SimpleAssignment>> buildAssMutableLoopAfterSwitch(
            boolean _default,
            Block _last,
            Analyzable _an, AnalyzingEl _anEl) {
        IdMap<Block, AssignedVariables> id_;
        id_ = _an.getAssignedVariables().getFinalVariables();
        CustList<StringMap<Assignment>> list_;
        list_ = id_.getVal(this).getLastMutableLoopOrEmpty();
        int len_ = list_.size();
        CustList<StringMap<SimpleAssignment>> out_;
        out_ = new CustList<StringMap<SimpleAssignment>>();
        for (int i = 0; i < len_; i++) {
            StringMap<Assignment> switch_ = list_.get(i);
            StringMap<SimpleAssignment> last_;
            if (_anEl.canCompleteNormally(_last)) {
                CustList<StringMap<SimpleAssignment>> map_;
                map_ = id_.getVal(_last).getMutableLoopRoot();
                if (map_.isValidIndex(i)) {
                    last_ = map_.get(i);
                } else {
                    last_ = new StringMap<SimpleAssignment>();
                }
            } else {
                last_ = new StringMap<SimpleAssignment>();
            }
            CustList<BreakBlock> breaks_ = getBreakables(_anEl);
            CustList<StringMap<AssignmentBefore>> listBr_;
            listBr_ = new CustList<StringMap<AssignmentBefore>>();
            for (BreakBlock b: breaks_) {
                CustList<StringMap<AssignmentBefore>> map_;
                map_ = id_.getVal(b).getMutableLoopRootBefore();
                if (map_.isValidIndex(i)) {
                    listBr_.add(map_.get(i));
                } else {
                    listBr_.add(new StringMap<AssignmentBefore>());
                }
            }
            out_.add(buildAssAfterSwitch(_default, switch_, last_, listBr_));
        }
        return out_;
    }
    protected StringMap<SimpleAssignment> buildAssFieldsAfterSwitch(
            boolean _default,
            Block _last,
            Analyzable _an, AnalyzingEl _anEl) {
        IdMap<Block, AssignedVariables> id_;
        id_ = _an.getAssignedVariables().getFinalVariables();
        StringMap<Assignment> list_;
        list_ = id_.getVal(this).getLastFieldsOrEmpty();
        StringMap<SimpleAssignment> last_;
        if (_anEl.canCompleteNormally(_last)) {
            last_ = id_.getVal(_last).getFieldsRoot();
        } else {
            last_ = new StringMap<SimpleAssignment>();
        }
        CustList<BreakBlock> breaks_ = getBreakables(_anEl);
        CustList<StringMap<AssignmentBefore>> listBr_;
        listBr_ = new CustList<StringMap<AssignmentBefore>>();
        for (BreakBlock b: breaks_) {
            listBr_.add(id_.getVal(b).getFieldsRootBefore());
        }
        return buildAssAfterSwitch(_default,list_, last_, listBr_);
    }
    protected static StringMap<SimpleAssignment> buildAssAfterSwitch(boolean _default,
            StringMap<Assignment> _tryAfter,
            StringMap<SimpleAssignment> _last,
            CustList<StringMap<AssignmentBefore>> _breaks) {
        StringMap<SimpleAssignment> out_ = new StringMap<SimpleAssignment>();
        for (EntryCust<String, Assignment> e: _tryAfter.entryList()) {
            String key_ = e.getKey();
            boolean assAfter_ = true;
            boolean unassAfter_ = true;
            if (!(_default ||e.getValue().isAssignedAfter())){
                assAfter_ = false;
            }
            if (!(_default || e.getValue().isUnassignedAfter())){
                unassAfter_ = false;
            }
            for (EntryCust<String, SimpleAssignment> f: _last.entryList()) {
                if (!StringList.quickEq(key_, f.getKey())) {
                    continue;
                }
                if(!f.getValue().isAssignedAfter()) {
                    assAfter_ = false;
                }
                if(!f.getValue().isUnassignedAfter()) {
                    unassAfter_ = false;
                }
            }
            for (StringMap<AssignmentBefore> b: _breaks) {
                if (!b.getVal(key_).isAssignedBefore()) {
                    assAfter_ = false;
                }
                if (!b.getVal(key_).isUnassignedBefore()) {
                    unassAfter_ = false;
                }
            }
            out_.put(key_, Assignment.assignClassic(assAfter_, unassAfter_));
        }
        return out_;
    }
    protected StringMap<SimpleAssignment> buildAssListFieldAfterLoop(Analyzable _an, AnalyzingEl _anEl) {
        CustList<BreakBlock> breaks_ = getBreakables(_anEl);
        IdMap<Block, AssignedVariables> id_;
        id_ = _an.getAssignedVariables().getFinalVariables();
        StringMap<BooleanAssignment> list_;
        list_ = ((AssignedBooleanVariables) id_.getVal(this)).getFieldsRootAfter();
        int breakLen_ = breaks_.size();
        CustList<StringMap<AssignmentBefore>> breakAss_;
        breakAss_ = new CustList<StringMap<AssignmentBefore>>();
        for (int j = 0; j < breakLen_; j++) {
            BreakBlock br_ = breaks_.get(j);
            AssignedVariables ass_ = id_.getVal(br_);
            StringMap<AssignmentBefore> vars_ = ass_.getFieldsRootBefore();
            breakAss_.add(vars_);
        }
        return buildAssAfterLoop(list_, breakAss_);
    }
    protected CustList<StringMap<SimpleAssignment>> buildAssListLocVarAfterLoop(Analyzable _an, AnalyzingEl _anEl) {
        CustList<BreakBlock> breaks_ = getBreakables(_anEl);
        IdMap<Block, AssignedVariables> id_;
        id_ = _an.getAssignedVariables().getFinalVariables();
        CustList<StringMap<SimpleAssignment>> varsList_;
        varsList_ = new CustList<StringMap<SimpleAssignment>>();
        CustList<StringMap<BooleanAssignment>> list_;
        list_ = ((AssignedBooleanVariables) id_.getVal(this)).getVariablesRootAfter();
        int breakLen_ = breaks_.size();
        int loopLen_ = list_.size();
        for (int i = 0; i < loopLen_; i++) {
            CustList<StringMap<AssignmentBefore>> breakAss_;
            breakAss_ = new CustList<StringMap<AssignmentBefore>>();
            for (int j = 0; j < breakLen_; j++) {
                BreakBlock br_ = breaks_.get(j);
                AssignedVariables ass_ = id_.getVal(br_);
                CustList<StringMap<AssignmentBefore>> vars_ = ass_.getVariablesRootBefore();
                if (!vars_.isValidIndex(i)) {
                    continue;
                }
                breakAss_.add(vars_.get(i));
            }
            StringMap<BooleanAssignment> cond_ = list_.get(i);
            varsList_.add(buildAssAfterLoop(cond_, breakAss_));
        }
        return varsList_;
    }
    protected CustList<StringMap<SimpleAssignment>> buildAssListMutableLoopAfterLoop(Analyzable _an, AnalyzingEl _anEl) {
        CustList<BreakBlock> breaks_ = getBreakables(_anEl);
        IdMap<Block, AssignedVariables> id_;
        id_ = _an.getAssignedVariables().getFinalVariables();
        CustList<StringMap<SimpleAssignment>> varsList_;
        varsList_ = new CustList<StringMap<SimpleAssignment>>();
        CustList<StringMap<BooleanAssignment>> list_;
        list_ = ((AssignedBooleanVariables) id_.getVal(this)).getMutableLoopRootAfter();
        int breakLen_ = breaks_.size();
        int loopLen_ = list_.size();
        for (int i = 0; i < loopLen_; i++) {
            CustList<StringMap<AssignmentBefore>> breakAss_;
            breakAss_ = new CustList<StringMap<AssignmentBefore>>();
            for (int j = 0; j < breakLen_; j++) {
                BreakBlock br_ = breaks_.get(j);
                AssignedVariables ass_ = id_.getVal(br_);
                CustList<StringMap<AssignmentBefore>> vars_ = ass_.getMutableLoopRootBefore();
                if (!vars_.isValidIndex(i)) {
                    continue;
                }
                breakAss_.add(vars_.get(i));
            }
            StringMap<BooleanAssignment> cond_ = list_.get(i);
            varsList_.add(buildAssAfterLoop(cond_, breakAss_));
        }
        return varsList_;
    }
    protected static StringMap<SimpleAssignment> buildAssAfterLoop(StringMap<BooleanAssignment> _loop, CustList<StringMap<AssignmentBefore>> _breakAss) {
        StringMap<SimpleAssignment> out_ = new StringMap<SimpleAssignment>();
        for (EntryCust<String,BooleanAssignment> e: _loop.entryList()) {
            BooleanAssignment ba_ = e.getValue();
            boolean ass_ = ba_.isAssignedAfterWhenFalse();
            boolean unass_ = ba_.isUnassignedAfterWhenFalse();
            String key_ = e.getKey();
            for (StringMap<AssignmentBefore> b: _breakAss) {
                if (!b.contains(key_)) {
                    continue;
                }
                if (!b.getVal(key_).isAssignedBefore()) {
                    ass_ = false;
                }
                if (!b.getVal(key_).isUnassignedBefore()) {
                    unass_ = false;
                }
            }
            out_.put(key_, Assignment.assignClassic(ass_, unass_));
        }
        return out_;
    }

    protected CustList<StringMap<SimpleAssignment>> buildAssVariablesAfterFinally(CustList<Block> _blocks,Analyzable _an, AnalyzingEl _anEl) {
        IdMap<Block, AssignedVariables> id_;
        id_ = _an.getAssignedVariables().getFinalVariables();
        CustList<StringMap<SimpleAssignment>> list_;
        list_ = id_.getVal(this).getVariablesRoot();
        int len_ = list_.size();
        CustList<StringMap<SimpleAssignment>> outs_;
        outs_ = new CustList<StringMap<SimpleAssignment>>();
        for (int i = 0; i < len_; i++) {
            StringMap<SimpleAssignment> out_;
            out_ = new StringMap<SimpleAssignment>();
            CustList<StringMap<SimpleAssignment>> listBl_;
            listBl_ = new CustList<StringMap<SimpleAssignment>>();
            CustList<StringMap<SimpleAssignment>> listBlFin_;
            listBlFin_ = new CustList<StringMap<SimpleAssignment>>();
            CustList<CustList<StringMap<AssignmentBefore>>> listBrs_;
            listBrs_ = new CustList<CustList<StringMap<AssignmentBefore>>>();
            CustList<CustList<StringMap<AssignmentBefore>>> listSingBrs_;
            listSingBrs_ = new CustList<CustList<StringMap<AssignmentBefore>>>();
            CustList<BreakBlock> breaks_ = getBreakables(_anEl);
            CustList<StringMap<AssignmentBefore>> listBr_;
            listBr_ = new CustList<StringMap<AssignmentBefore>>();
            CustList<StringMap<AssignmentBefore>> listBrFin_;
            listBrFin_ = new CustList<StringMap<AssignmentBefore>>();
            if (_anEl.canCompleteNormally(this)) {
                CustList<StringMap<SimpleAssignment>> map_;
                map_ = id_.getVal(this).getVariablesRoot();
                if (map_.isValidIndex(i)) {
                    listBlFin_.add(map_.get(i));
                } else {
                    listBlFin_.add(new StringMap<SimpleAssignment>());
                }
            } else {
                listBlFin_.add(new StringMap<SimpleAssignment>());
            }
            for (BreakBlock b: breaks_) {
                CustList<StringMap<AssignmentBefore>> map_;
                map_ = id_.getVal(b).getVariablesRootBefore();
                if (map_.isValidIndex(i)) {
                    listBrFin_.add(map_.get(i));
                } else {
                    listBrFin_.add(new StringMap<AssignmentBefore>());
                }
            }
            listSingBrs_.add(listBrFin_);
            StringMap<SimpleAssignment> try_ = list_.get(i);
            StringMap<SimpleAssignment> fin_ = buildAssAfterTry(try_, listBlFin_, listSingBrs_);
            
            for (Block c: _blocks) {
                breaks_ = ((BracedStack)c).getBreakables(_anEl);
                listBr_ = new CustList<StringMap<AssignmentBefore>>();
                if (_anEl.canCompleteNormally(c)) {
                    CustList<StringMap<SimpleAssignment>> map_;
                    map_ = id_.getVal(c).getVariablesRoot();
                    if (map_.isValidIndex(i)) {
                        listBl_.add(map_.get(i));
                    } else {
                        listBl_.add(new StringMap<SimpleAssignment>());
                    }
                } else {
                    listBl_.add(new StringMap<SimpleAssignment>());
                }
                for (BreakBlock b: breaks_) {
                    CustList<StringMap<AssignmentBefore>> map_;
                    map_ = id_.getVal(b).getVariablesRootBefore();
                    if (map_.isValidIndex(i)) {
                        listBr_.add(map_.get(i));
                    } else {
                        listBr_.add(new StringMap<AssignmentBefore>());
                    }
                }
                listBrs_.add(listBr_);
            }
            StringMap<SimpleAssignment> prev_ = buildAssAfterTry(try_, listBl_, listBrs_);
            for (EntryCust<String, SimpleAssignment> f: fin_.entryList()) {
                if (!f.getValue().isAssignedAfter()) {
                    if (prev_.getVal(f.getKey()).isAssignedAfter()) {
                        out_.put(f.getKey(), prev_.getVal(f.getKey()));
                    } else {
                        out_.put(f.getKey(), f.getValue());
                    }
                } else {
                    out_.put(f.getKey(), f.getValue());
                }
            }
            outs_.add(out_);
        }
        return outs_;
    }
    protected CustList<StringMap<SimpleAssignment>> buildAssMutableLoopAfterFinally(CustList<Block> _blocks,Analyzable _an, AnalyzingEl _anEl) {
        IdMap<Block, AssignedVariables> id_;
        id_ = _an.getAssignedVariables().getFinalVariables();
        CustList<StringMap<SimpleAssignment>> list_;
        list_ = id_.getVal(this).getMutableLoopRoot();
        int len_ = list_.size();
        CustList<StringMap<SimpleAssignment>> outs_;
        outs_ = new CustList<StringMap<SimpleAssignment>>();
        for (int i = 0; i < len_; i++) {
            StringMap<SimpleAssignment> out_;
            out_ = new StringMap<SimpleAssignment>();
            CustList<StringMap<SimpleAssignment>> listBl_;
            listBl_ = new CustList<StringMap<SimpleAssignment>>();
            CustList<StringMap<SimpleAssignment>> listBlFin_;
            listBlFin_ = new CustList<StringMap<SimpleAssignment>>();
            CustList<CustList<StringMap<AssignmentBefore>>> listBrs_;
            listBrs_ = new CustList<CustList<StringMap<AssignmentBefore>>>();
            CustList<CustList<StringMap<AssignmentBefore>>> listSingBrs_;
            listSingBrs_ = new CustList<CustList<StringMap<AssignmentBefore>>>();
            CustList<BreakBlock> breaks_ = getBreakables(_anEl);
            CustList<StringMap<AssignmentBefore>> listBr_;
            listBr_ = new CustList<StringMap<AssignmentBefore>>();
            CustList<StringMap<AssignmentBefore>> listBrFin_;
            listBrFin_ = new CustList<StringMap<AssignmentBefore>>();
            if (_anEl.canCompleteNormally(this)) {
                CustList<StringMap<SimpleAssignment>> map_;
                map_ = id_.getVal(this).getMutableLoopRoot();
                if (map_.isValidIndex(i)) {
                    listBlFin_.add(map_.get(i));
                } else {
                    listBlFin_.add(new StringMap<SimpleAssignment>());
                }
            } else {
                listBlFin_.add(new StringMap<SimpleAssignment>());
            }
            for (BreakBlock b: breaks_) {
                CustList<StringMap<AssignmentBefore>> map_;
                map_ = id_.getVal(b).getMutableLoopRootBefore();
                if (map_.isValidIndex(i)) {
                    listBrFin_.add(map_.get(i));
                } else {
                    listBrFin_.add(new StringMap<AssignmentBefore>());
                }
            }
            listSingBrs_.add(listBrFin_);
            StringMap<SimpleAssignment> try_ = list_.get(i);
            StringMap<SimpleAssignment> fin_ = buildAssAfterTry(try_, listBlFin_, listSingBrs_);
            
            for (Block c: _blocks) {
                breaks_ = ((BracedStack)c).getBreakables(_anEl);
                listBr_ = new CustList<StringMap<AssignmentBefore>>();
                if (_anEl.canCompleteNormally(c)) {
                    CustList<StringMap<SimpleAssignment>> map_;
                    map_ = id_.getVal(c).getMutableLoopRoot();
                    if (map_.isValidIndex(i)) {
                        listBl_.add(map_.get(i));
                    } else {
                        listBl_.add(new StringMap<SimpleAssignment>());
                    }
                } else {
                    listBl_.add(new StringMap<SimpleAssignment>());
                }
                for (BreakBlock b: breaks_) {
                    CustList<StringMap<AssignmentBefore>> map_;
                    map_ = id_.getVal(b).getMutableLoopRootBefore();
                    if (map_.isValidIndex(i)) {
                        listBr_.add(map_.get(i));
                    } else {
                        listBr_.add(new StringMap<AssignmentBefore>());
                    }
                }
                listBrs_.add(listBr_);
            }
            StringMap<SimpleAssignment> prev_ = buildAssAfterTry(try_, listBl_, listBrs_);
            for (EntryCust<String, SimpleAssignment> f: fin_.entryList()) {
                if (!f.getValue().isAssignedAfter()) {
                    if (prev_.getVal(f.getKey()).isAssignedAfter()) {
                        out_.put(f.getKey(), prev_.getVal(f.getKey()));
                    } else {
                        out_.put(f.getKey(), f.getValue());
                    }
                } else {
                    out_.put(f.getKey(), f.getValue());
                }
            }
            outs_.add(out_);
        }
        return outs_;
    }
    protected StringMap<SimpleAssignment> buildAssFieldsAfterFinally(CustList<Block> _blocks,Analyzable _an, AnalyzingEl _anEl) {
        IdMap<Block, AssignedVariables> id_;
        id_ = _an.getAssignedVariables().getFinalVariables();
        StringMap<SimpleAssignment> list_;
        list_ = id_.getVal(this).getFieldsRoot();
        CustList<StringMap<SimpleAssignment>> listBl_;
        listBl_ = new CustList<StringMap<SimpleAssignment>>();
        CustList<StringMap<SimpleAssignment>> listBlFin_;
        listBlFin_ = new CustList<StringMap<SimpleAssignment>>();
        CustList<CustList<StringMap<AssignmentBefore>>> listBrs_;
        listBrs_ = new CustList<CustList<StringMap<AssignmentBefore>>>();
        CustList<CustList<StringMap<AssignmentBefore>>> listSingBrs_;
        listSingBrs_ = new CustList<CustList<StringMap<AssignmentBefore>>>();
        CustList<BreakBlock> breaks_ = getBreakables(_anEl);
        CustList<StringMap<AssignmentBefore>> listBr_;
        listBr_ = new CustList<StringMap<AssignmentBefore>>();
        CustList<StringMap<AssignmentBefore>> listBrFin_;
        listBrFin_ = new CustList<StringMap<AssignmentBefore>>();
        if (_anEl.canCompleteNormally(this)) {
            listBlFin_.add(id_.getVal(this).getFieldsRoot());
        } else {
            listBlFin_.add(new StringMap<SimpleAssignment>());
        }
        for (BreakBlock b: breaks_) {
            listBrFin_.add(id_.getVal(b).getFieldsRootBefore());
        }
        listSingBrs_.add(listBrFin_);
        StringMap<SimpleAssignment> fin_ = buildAssAfterTry(list_, listBlFin_, listSingBrs_);
        for (Block c: _blocks) {
            breaks_ = ((BracedStack)c).getBreakables(_anEl);
            listBr_ = new CustList<StringMap<AssignmentBefore>>();
            if (_anEl.canCompleteNormally(c)) {
                listBl_.add(id_.getVal(c).getFieldsRoot());
            } else {
                listBl_.add(new StringMap<SimpleAssignment>());
            }
            for (BreakBlock b: breaks_) {
                listBr_.add(id_.getVal(b).getFieldsRootBefore());
            }
            listBrs_.add(listBr_);
        }
        StringMap<SimpleAssignment> prev_ = buildAssAfterTry(list_, listBl_, listBrs_);
        StringMap<SimpleAssignment> out_;
        out_ = new StringMap<SimpleAssignment>();
        for (EntryCust<String, SimpleAssignment> f: fin_.entryList()) {
            if (!f.getValue().isAssignedAfter()) {
                if (prev_.getVal(f.getKey()).isAssignedAfter()) {
                    out_.put(f.getKey(), prev_.getVal(f.getKey()));
                } else {
                    out_.put(f.getKey(), f.getValue());
                }
            } else {
                out_.put(f.getKey(), f.getValue());
            }
        }
        return out_;
    }
    protected CustList<StringMap<SimpleAssignment>> buildAssVariablesAfterTry(CustList<Block> _blocks,Analyzable _an, AnalyzingEl _anEl) {
        IdMap<Block, AssignedVariables> id_;
        id_ = _an.getAssignedVariables().getFinalVariables();
        CustList<StringMap<SimpleAssignment>> out_;
        out_ = new CustList<StringMap<SimpleAssignment>>();
        CustList<StringMap<SimpleAssignment>> list_;
        list_ = id_.getVal(this).getVariablesRoot();
        int len_ = list_.size();
        for (int i = 0; i < len_; i++) {
            CustList<StringMap<SimpleAssignment>> listBl_;
            listBl_ = new CustList<StringMap<SimpleAssignment>>();
            CustList<CustList<StringMap<AssignmentBefore>>> listBrs_;
            listBrs_ = new CustList<CustList<StringMap<AssignmentBefore>>>();
            for (Block c: _blocks) {
                CustList<BreakBlock> breaks_ = ((BracedStack)c).getBreakables(_anEl);
                CustList<StringMap<AssignmentBefore>> listBr_;
                listBr_ = new CustList<StringMap<AssignmentBefore>>();
                if (_anEl.canCompleteNormally(c)) {
                    CustList<StringMap<SimpleAssignment>> map_;
                    map_ = id_.getVal(c).getVariablesRoot();
                    if (map_.isValidIndex(i)) {
                        listBl_.add(map_.get(i));
                    } else {
                        listBl_.add(new StringMap<SimpleAssignment>());
                    }
                } else {
                    listBl_.add(new StringMap<SimpleAssignment>());
                }
                for (BreakBlock b: breaks_) {
                    CustList<StringMap<AssignmentBefore>> map_;
                    map_ = id_.getVal(b).getVariablesRootBefore();
                    if (map_.isValidIndex(i)) {
                        listBr_.add(map_.get(i));
                    } else {
                        listBr_.add(new StringMap<AssignmentBefore>());
                    }
                }
                listBrs_.add(listBr_);
            }
            StringMap<SimpleAssignment> try_ = list_.get(i);
            out_.add(buildAssAfterTry(try_, listBl_, listBrs_));
        }
        return out_;
    }
    protected CustList<StringMap<SimpleAssignment>> buildAssMutableLoopAfterTry(CustList<Block> _blocks,Analyzable _an, AnalyzingEl _anEl) {
        IdMap<Block, AssignedVariables> id_;
        id_ = _an.getAssignedVariables().getFinalVariables();
        CustList<StringMap<SimpleAssignment>> out_;
        out_ = new CustList<StringMap<SimpleAssignment>>();
        CustList<StringMap<SimpleAssignment>> list_;
        list_ = id_.getVal(this).getMutableLoopRoot();
        int len_ = list_.size();
        for (int i = 0; i < len_; i++) {
            CustList<StringMap<SimpleAssignment>> listBl_;
            listBl_ = new CustList<StringMap<SimpleAssignment>>();
            CustList<CustList<StringMap<AssignmentBefore>>> listBrs_;
            listBrs_ = new CustList<CustList<StringMap<AssignmentBefore>>>();
            for (Block c: _blocks) {
                CustList<BreakBlock> breaks_ = ((BracedStack)c).getBreakables(_anEl);
                CustList<StringMap<AssignmentBefore>> listBr_;
                listBr_ = new CustList<StringMap<AssignmentBefore>>();
                if (_anEl.canCompleteNormally(c)) {
                    CustList<StringMap<SimpleAssignment>> map_;
                    map_ = id_.getVal(c).getMutableLoopRoot();
                    if (map_.isValidIndex(i)) {
                        listBl_.add(map_.get(i));
                    } else {
                        listBl_.add(new StringMap<SimpleAssignment>());
                    }
                } else {
                    listBl_.add(new StringMap<SimpleAssignment>());
                }
                for (BreakBlock b: breaks_) {
                    CustList<StringMap<AssignmentBefore>> map_;
                    map_ = id_.getVal(b).getMutableLoopRootBefore();
                    if (map_.isValidIndex(i)) {
                        listBr_.add(map_.get(i));
                    } else {
                        listBr_.add(new StringMap<AssignmentBefore>());
                    }
                }
                listBrs_.add(listBr_);
            }
            StringMap<SimpleAssignment> try_ = list_.get(i);
            out_.add(buildAssAfterTry(try_, listBl_, listBrs_));
        }
        return out_;
    }
    protected StringMap<SimpleAssignment> buildAssFieldsAfterTry(CustList<Block> _blocks,Analyzable _an, AnalyzingEl _anEl) {
        IdMap<Block, AssignedVariables> id_;
        id_ = _an.getAssignedVariables().getFinalVariables();
        StringMap<SimpleAssignment> list_;
        list_ = id_.getVal(this).getFieldsRoot();
        CustList<StringMap<SimpleAssignment>> listBl_;
        listBl_ = new CustList<StringMap<SimpleAssignment>>();
        CustList<CustList<StringMap<AssignmentBefore>>> listBrs_;
        listBrs_ = new CustList<CustList<StringMap<AssignmentBefore>>>();
        for (Block c: _blocks) {
            CustList<BreakBlock> breaks_ = ((BracedStack)c).getBreakables(_anEl);
            CustList<StringMap<AssignmentBefore>> listBr_;
            listBr_ = new CustList<StringMap<AssignmentBefore>>();
            if (_anEl.canCompleteNormally(c)) {
                listBl_.add(id_.getVal(c).getFieldsRoot());
            } else {
                listBl_.add(new StringMap<SimpleAssignment>());
            }
            for (BreakBlock b: breaks_) {
                listBr_.add(id_.getVal(b).getFieldsRootBefore());
            }
            listBrs_.add(listBr_);
        }
        return buildAssAfterTry(list_, listBl_, listBrs_);
    }
    protected static StringMap<SimpleAssignment> buildAssAfterTry(StringMap<SimpleAssignment> _tryAfter,
            CustList<StringMap<SimpleAssignment>> _blocks,
            CustList<CustList<StringMap<AssignmentBefore>>> _breaks) {
        StringMap<SimpleAssignment> out_ = new StringMap<SimpleAssignment>();
        int len_ = _blocks.size();
        for (EntryCust<String, SimpleAssignment> e: _tryAfter.entryList()) {
            String key_ = e.getKey();
            boolean assAfterAll_ = true;
            boolean unassAfterAll_ = true;
            for (int i = 0; i < len_; i++) {
                boolean assAfter_ = true;
                boolean unassAfter_ = true;
                for (EntryCust<String, SimpleAssignment> f: _blocks.get(i).entryList()) {
                    if (!StringList.quickEq(key_, f.getKey())) {
                        continue;
                    }
                    if(!f.getValue().isAssignedAfter()) {
                        assAfter_ = false;
                    }
                    if(!f.getValue().isUnassignedAfter()) {
                        unassAfter_ = false;
                    }
                }
                for (StringMap<AssignmentBefore> b: _breaks.get(i)) {
                    if (!b.getVal(key_).isAssignedBefore()) {
                        assAfter_ = false;
                    }
                    if (!b.getVal(key_).isUnassignedBefore()) {
                        unassAfter_ = false;
                    }
                }
                if (!assAfter_) {
                    assAfterAll_ = false;
                }
                if (!unassAfter_) {
                    unassAfterAll_ = false;
                }
            }
            out_.put(key_, Assignment.assignClassic(assAfterAll_, unassAfterAll_));
        }
        return out_;
    }
    protected static StringMap<AssignmentBefore> buildAssFieldsBefNextCatchFinally(TryEval _try, Analyzable _an, AnalyzingEl _anEl,
            CustList<AbstractCatchEval> _catchs) {
        CustList<AbruptBlock> abr_ = _try.getAbruptTry(_an, _anEl);
        CustList<StringMap<Assignment>> throws_ = new CustList<StringMap<Assignment>>();
        CustList<StringMap<AssignmentBefore>> others_ = new CustList<StringMap<AssignmentBefore>>();
        CustList<StringMap<SimpleAssignment>> catchs_ = new CustList<StringMap<SimpleAssignment>>();
        IdMap<Block, AssignedVariables> id_;
        id_ = _an.getAssignedVariables().getFinalVariables();
        for (AbruptBlock a: abr_) {
            if (a instanceof ReturnMehod) {
                if (((ReturnMehod)a).getOpRet() == null) {
                    others_.add(id_.getVal(a).getFieldsRootBefore());
                } else {
                    throws_.add(id_.getVal(a).getLastFieldsOrEmpty());
                }
            } else if (a instanceof Throwing) {
                throws_.add(id_.getVal(a).getLastFieldsOrEmpty());
            } else {
                others_.add(id_.getVal(a).getFieldsRootBefore());
            }
        }
        for (AbstractCatchEval c: _catchs) {
            for (AbruptBlock a: c.getAbruptTry(_an, _anEl)) {
                if (a instanceof ReturnMehod) {
                    if (((ReturnMehod)a).getOpRet() == null) {
                        others_.add(id_.getVal(a).getFieldsRootBefore());
                    } else {
                        throws_.add(id_.getVal(a).getLastFieldsOrEmpty());
                    }
                } else if (a instanceof Throwing) {
                    throws_.add(id_.getVal(a).getLastFieldsOrEmpty());
                } else {
                    others_.add(id_.getVal(a).getFieldsRootBefore());
                }
            }
        }
        for (AbstractCatchEval c: _catchs) {
            if (_anEl.canCompleteNormally(c)) {
                catchs_.add(id_.getVal(c).getFieldsRoot());
            }
        }
        StringMap<SimpleAssignment> tryAfter_;
        if (_anEl.canCompleteNormally(_try)) {
            tryAfter_ = id_.getVal(_try).getFieldsRoot();
        } else {
            tryAfter_ = new StringMap<SimpleAssignment>();
        }
        StringMap<AssignmentBefore> tryBefore_ = id_.getVal(_try).getFieldsRootBefore();
        return buildAssBefNextCatchFinally(tryAfter_, tryBefore_, throws_, others_, catchs_);
    }
    protected static CustList<StringMap<AssignmentBefore>> buildAssVarsBefNextCatchFinally(TryEval _try, Analyzable _an, AnalyzingEl _anEl,
            CustList<AbstractCatchEval> _catchs) {
        CustList<AbruptBlock> abr_ = _try.getAbruptTry(_an, _anEl);
        CustList<StringMap<AssignmentBefore>> out_ = new CustList<StringMap<AssignmentBefore>>();
        IdMap<Block, AssignedVariables> id_;
        id_ = _an.getAssignedVariables().getFinalVariables();
        CustList<StringMap<SimpleAssignment>> tryAfters_ = id_.getVal(_try).getVariablesRoot();
        CustList<StringMap<AssignmentBefore>> tryBefores_ = id_.getVal(_try).getVariablesRootBefore();
        int loopLen_ = tryAfters_.size();
        for (int i = 0; i < loopLen_; i++) {
            StringMap<SimpleAssignment> tryAfter_;
            if (_anEl.canCompleteNormally(_try)) {
                tryAfter_ = tryAfters_.get(i);
            } else {
                tryAfter_ = new StringMap<SimpleAssignment>();
            }
            StringMap<AssignmentBefore> tryBefore_ = tryBefores_.get(i);
            CustList<StringMap<Assignment>> throws_ = new CustList<StringMap<Assignment>>();
            CustList<StringMap<AssignmentBefore>> others_ = new CustList<StringMap<AssignmentBefore>>();
            CustList<StringMap<SimpleAssignment>> catchs_ = new CustList<StringMap<SimpleAssignment>>();
            for (AbruptBlock a: abr_) {
                if (a instanceof ReturnMehod) {
                    if (((ReturnMehod)a).getOpRet() == null) {
                        CustList<StringMap<AssignmentBefore>> li_ = id_.getVal(a).getVariablesRootBefore();
                        if (li_.isValidIndex(i)) {
                            others_.add(li_.get(i));
                        }
                    } else {
                        CustList<StringMap<Assignment>> li_ = id_.getVal(a).getLastVariablesOrEmpty();
                        if (li_.isValidIndex(i)) {
                            throws_.add(li_.get(i));
                        }
                    }
                } else if (a instanceof Throwing) {
                    CustList<StringMap<Assignment>> li_ = id_.getVal(a).getLastVariablesOrEmpty();
                    if (li_.isValidIndex(i)) {
                        throws_.add(li_.get(i));
                    }
                } else {
                    CustList<StringMap<AssignmentBefore>> li_ = id_.getVal(a).getVariablesRootBefore();
                    if (li_.isValidIndex(i)) {
                        others_.add(li_.get(i));
                    }
                }
            }
            for (AbstractCatchEval c: _catchs) {
                for (AbruptBlock a: c.getAbruptTry(_an, _anEl)) {
                    if (a instanceof ReturnMehod) {
                        if (((ReturnMehod)a).getOpRet() == null) {
                            CustList<StringMap<AssignmentBefore>> li_ = id_.getVal(a).getVariablesRootBefore();
                            if (li_.isValidIndex(i)) {
                                others_.add(li_.get(i));
                            }
                        } else {
                            CustList<StringMap<Assignment>> li_ = id_.getVal(a).getLastVariablesOrEmpty();
                            if (li_.isValidIndex(i)) {
                                throws_.add(li_.get(i));
                            }
                        }
                    } else if (a instanceof Throwing) {
                        CustList<StringMap<Assignment>> li_ = id_.getVal(a).getLastVariablesOrEmpty();
                        if (li_.isValidIndex(i)) {
                            throws_.add(li_.get(i));
                        }
                    } else {
                        CustList<StringMap<AssignmentBefore>> li_ = id_.getVal(a).getVariablesRootBefore();
                        if (li_.isValidIndex(i)) {
                            others_.add(li_.get(i));
                        }
                    }
                }
            }
            for (AbstractCatchEval c: _catchs) {
                if (_anEl.canCompleteNormally(c)) {
                    CustList<StringMap<SimpleAssignment>> li_ = id_.getVal(c).getVariablesRoot();
                    if (li_.isValidIndex(i)) {
                        catchs_.add(li_.get(i));
                    }
                }
            }
            out_.add(buildAssBefNextCatchFinally(tryAfter_, tryBefore_, throws_, others_,catchs_));
        }
        return out_;
    }
    protected static CustList<StringMap<AssignmentBefore>> buildAssMutableLoopBefNextCatchFinally(TryEval _try, Analyzable _an, AnalyzingEl _anEl,
            CustList<AbstractCatchEval> _catchs) {
        CustList<AbruptBlock> abr_ = _try.getAbruptTry(_an, _anEl);
        CustList<StringMap<AssignmentBefore>> out_ = new CustList<StringMap<AssignmentBefore>>();
        IdMap<Block, AssignedVariables> id_;
        id_ = _an.getAssignedVariables().getFinalVariables();
        CustList<StringMap<SimpleAssignment>> tryAfters_ = id_.getVal(_try).getMutableLoopRoot();
        CustList<StringMap<AssignmentBefore>> tryBefores_ = id_.getVal(_try).getMutableLoopRootBefore();
        int loopLen_ = tryAfters_.size();
        for (int i = 0; i < loopLen_; i++) {
            StringMap<SimpleAssignment> tryAfter_;
            if (_anEl.canCompleteNormally(_try)) {
                tryAfter_ = tryAfters_.get(i);
            } else {
                tryAfter_ = new StringMap<SimpleAssignment>();
            }
            StringMap<AssignmentBefore> tryBefore_ = tryBefores_.get(i);
            CustList<StringMap<Assignment>> throws_ = new CustList<StringMap<Assignment>>();
            CustList<StringMap<AssignmentBefore>> others_ = new CustList<StringMap<AssignmentBefore>>();
            CustList<StringMap<SimpleAssignment>> catchs_ = new CustList<StringMap<SimpleAssignment>>();
            for (AbruptBlock a: abr_) {
                if (a instanceof ReturnMehod) {
                    if (((ReturnMehod)a).getOpRet() == null) {
                        CustList<StringMap<AssignmentBefore>> li_ = id_.getVal(a).getMutableLoopRootBefore();
                        if (li_.isValidIndex(i)) {
                            others_.add(li_.get(i));
                        }
                    } else {
                        CustList<StringMap<Assignment>> li_ = id_.getVal(a).getLastMutableLoopOrEmpty();
                        if (li_.isValidIndex(i)) {
                            throws_.add(li_.get(i));
                        }
                    }
                } else if (a instanceof Throwing) {
                    CustList<StringMap<Assignment>> li_ = id_.getVal(a).getLastMutableLoopOrEmpty();
                    if (li_.isValidIndex(i)) {
                        throws_.add(li_.get(i));
                    }
                } else {
                    CustList<StringMap<AssignmentBefore>> li_ = id_.getVal(a).getMutableLoopRootBefore();
                    if (li_.isValidIndex(i)) {
                        others_.add(li_.get(i));
                    }
                }
            }
            for (AbstractCatchEval c: _catchs) {
                for (AbruptBlock a: c.getAbruptTry(_an, _anEl)) {
                    if (a instanceof ReturnMehod) {
                        if (((ReturnMehod)a).getOpRet() == null) {
                            CustList<StringMap<AssignmentBefore>> li_ = id_.getVal(a).getMutableLoopRootBefore();
                            if (li_.isValidIndex(i)) {
                                others_.add(li_.get(i));
                            }
                        } else {
                            CustList<StringMap<Assignment>> li_ = id_.getVal(a).getLastMutableLoopOrEmpty();
                            if (li_.isValidIndex(i)) {
                                throws_.add(li_.get(i));
                            }
                        }
                    } else if (a instanceof Throwing) {
                        CustList<StringMap<Assignment>> li_ = id_.getVal(a).getLastMutableLoopOrEmpty();
                        if (li_.isValidIndex(i)) {
                            throws_.add(li_.get(i));
                        }
                    } else {
                        CustList<StringMap<AssignmentBefore>> li_ = id_.getVal(a).getMutableLoopRootBefore();
                        if (li_.isValidIndex(i)) {
                            others_.add(li_.get(i));
                        }
                    }
                }
            }
            for (AbstractCatchEval c: _catchs) {
                if (_anEl.canCompleteNormally(c)) {
                    CustList<StringMap<SimpleAssignment>> li_ = id_.getVal(c).getMutableLoopRoot();
                    if (li_.isValidIndex(i)) {
                        catchs_.add(li_.get(i));
                    }
                }
            }
            out_.add(buildAssBefNextCatchFinally(tryAfter_, tryBefore_, throws_, others_,catchs_));
        }
        return out_;
    }
    protected static StringMap<AssignmentBefore> buildAssBefNextCatchFinally(StringMap<SimpleAssignment> _tryAfter,
            StringMap<AssignmentBefore> _tryBefore,CustList<StringMap<Assignment>> _throws,
            CustList<StringMap<AssignmentBefore>> _others, CustList<StringMap<SimpleAssignment>> _catchs) {
        StringMap<AssignmentBefore> out_ = new StringMap<AssignmentBefore>();
        for (EntryCust<String, AssignmentBefore> e: _tryBefore.entryList()) {
            AssignmentBefore ab_ = new AssignmentBefore();
            String key_ = e.getKey();
            if (e.getValue().isAssignedBefore()) {
                ab_.setAssignedBefore(true);
            }
            boolean unass_ = true;
            if (_tryAfter.contains(key_) && !_tryAfter.getVal(key_).isUnassignedAfter()) {
                unass_ = false;
            }
            for (StringMap<Assignment> t: _throws) {
                if (!t.contains(key_)) {
                    continue;
                }
                if (!t.getVal(key_).isUnassignedAfter()) {
                    unass_ = false;
                }
            }
            for (StringMap<AssignmentBefore> t: _others) {
                if (!t.contains(key_)) {
                    continue;
                }
                if (!t.getVal(key_).isUnassignedBefore()) {
                    unass_ = false;
                }
            }
            for (StringMap<SimpleAssignment> c: _catchs) {
                if (!c.contains(key_)) {
                    continue;
                }
                if (!c.getVal(key_).isUnassignedAfter()) {
                    unass_ = false;
                }
            }
            if (unass_) {
                ab_.setUnassignedBefore(true);
            }
            out_.put(e.getKey(), ab_);
        }
        return out_;
    }
    protected CustList<AbruptBlock> getAbruptTry(Analyzable _an, AnalyzingEl _anEl) {
        IdMap<Block, AssignedVariables> id_ = _an.getAssignedVariables().getFinalVariables();
        IdList<Block> inners_;
        inners_ = new IdList<Block>();
        boolean add_ = false;
        for (EntryCust<Block, AssignedVariables> e: id_.entryList()) {
            if (e.getKey() == this) {
                add_ = true;
            }
            if (add_) {
                inners_.add(e.getKey());
            }
        }
        CustList<AbruptBlock> stoppers_ = new CustList<AbruptBlock>();
        for (Block f: inners_) {
            if (!(f instanceof AbruptBlock)) {
                continue;
            }
            if (f instanceof ContinueBlock) {
                Loop lp_ = _anEl.getContinuables().getVal((ContinueBlock) f);
                if (!_anEl.getContinuablesAncestors().getVal((ContinueBlock) f).getVal(lp_).containsObj(this)) {
                    continue;
                }
            }
            if (f instanceof BreakBlock) {
                BreakableBlock lp_ = _anEl.getBreakables().getVal((BreakBlock) f);
                if (!_anEl.getBreakablesAncestors().getVal((BreakBlock) f).getVal(lp_).containsObj(this) && lp_ != this) {
                    continue;
                }
            }
            stoppers_.add((AbruptBlock)f);
        }
        return stoppers_;
    }
    protected CustList<ContinueBlock> getContinuables(AnalyzingEl _anEl) {
        CustList<ContinueBlock> breaks_ = new CustList<ContinueBlock>();
        for (EntryCust<ContinueBlock, Loop> f: _anEl.getContinuables().entryList()) {
            if (f.getValue() != this) {
                continue;
            }
            breaks_.add(f.getKey());
        }
        return breaks_;
    }
    protected CustList<BreakBlock> getBreakables(AnalyzingEl _anEl) {
        CustList<BreakBlock> breaks_ = new CustList<BreakBlock>();
        for (EntryCust<BreakBlock, BreakableBlock> f: _anEl.getBreakables().entryList()) {
            if (f.getValue() != this) {
                continue;
            }
            breaks_.add(f.getKey());
        }
        return breaks_;
    }
}
