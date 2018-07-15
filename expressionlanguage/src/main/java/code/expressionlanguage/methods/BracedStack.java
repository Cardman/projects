package code.expressionlanguage.methods;
import code.expressionlanguage.Analyzable;
import code.expressionlanguage.ConstType;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.OffsetsBlock;
import code.expressionlanguage.OperationsSequence;
import code.expressionlanguage.Templates;
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
import code.expressionlanguage.opers.util.FieldInfo;
import code.expressionlanguage.opers.util.SimpleAssignment;
import code.expressionlanguage.variables.LocalVariable;
import code.sml.Element;
import code.util.CustList;
import code.util.EntryCust;
import code.util.IdList;
import code.util.IdMap;
import code.util.StringList;
import code.util.StringMap;

public abstract class BracedStack extends BracedBlock {

    BracedStack(Element _el, ContextEl _importingPage, int _indexChild,
            BracedBlock _m) {
        super(_el, _importingPage, _indexChild, _m);
    }

    BracedStack(ContextEl _importingPage, int _indexChild,
            BracedBlock _m, OffsetsBlock _offset) {
        super(_importingPage, _indexChild, _m, _offset);
    }
    protected void processFinalFields(Analyzable _an,AnalyzingEl _anEl,
            IdMap<Block, AssignedVariables> _allDesc,
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
            for (EntryCust<Block, AssignedVariables> d: _allDesc.entryList()) {
                vars_ = d.getValue();
                Block next_ = d.getKey();
                //next siblings of d
                processFinalFields(next_, _an, vars_, key_);
            }
        }
    }

    protected void processFinalVars(Analyzable _an,AnalyzingEl _anEl,
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
                String key_ = e.getKey();
                LocalVariable varLoc_ = _an.getLocalVar(key_,index_);
                if (varLoc_ != null && !varLoc_.isFinalVariable()) {
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
    protected void processFinalVars(Block _curBlock, Analyzable _an,AssignedVariables _vars, String _field) {
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
            _an.getClasses().addError(un_);
        }
    }
    protected void processFinalFields(Block _curBlock, Analyzable _an,AssignedVariables _vars, String _field) {
        for (EntryCust<OperationNode, StringMap<AssignmentBefore>> f: _vars.getFieldsBefore().entryList()) {
            if (!(f.getKey() instanceof AffectationOperation)) {
                continue;
            }
            AffectationOperation aff_ = (AffectationOperation) f.getKey();
            SettableElResult set_ = aff_.getSettable();
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
            un_.setRc(_curBlock.getRowCol(_an.getOffset(),_curBlock.getOffset().getOffsetTrim()));
            _an.getClasses().addError(un_);
        }
    }
    protected StringMap<SimpleAssignment> buildAssListFieldAfter(Analyzable _an, AnalyzingEl _anEl) {
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
        return buildAssAfter(list_, breakAss_);
    }
    protected CustList<StringMap<SimpleAssignment>> buildAssListLocVarAfter(Analyzable _an, AnalyzingEl _anEl) {
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
            varsList_.add(buildAssAfter(cond_, breakAss_));
        }
        return varsList_;
    }
    protected StringMap<SimpleAssignment> buildAssAfterTry(StringMap<SimpleAssignment> _tryAfter,
            CustList<StringMap<SimpleAssignment>> _blocks,
            CustList<StringMap<AssignmentBefore>> _breaks) {
        StringMap<SimpleAssignment> out_ = new StringMap<SimpleAssignment>();
        for (EntryCust<String, SimpleAssignment> e: _tryAfter.entryList()) {
            String key_ = e.getKey();
            boolean assAfter_ = true;
            boolean unassAfter_ = true;
            for (StringMap<SimpleAssignment> m: _blocks) {
                if (!m.getVal(key_).isAssignedAfter()) {
                    assAfter_ = false;
                }
                if (!m.getVal(key_).isUnassignedAfter()) {
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
    protected StringMap<AssignmentBefore> buildAssFieldsBefNextSibling(Analyzable _an, AnalyzingEl _anEl,
            CustList<AbstractCatchEval> _catchs) {
        CustList<AbruptBlock> abr_ = getAbruptTry(_an, _anEl);
        CustList<StringMap<Assignment>> throws_ = new CustList<StringMap<Assignment>>();
        CustList<StringMap<AssignmentBefore>> others_ = new CustList<StringMap<AssignmentBefore>>();
        CustList<StringMap<SimpleAssignment>> catchs_ = new CustList<StringMap<SimpleAssignment>>();
        IdMap<Block, AssignedVariables> id_;
        id_ = _an.getAssignedVariables().getFinalVariables();
        for (AbruptBlock a: abr_) {
            if (a instanceof Throwing) {
                throws_.add(id_.getVal(a).getFields().lastValue());
            } else {
                others_.add(id_.getVal(a).getFieldsRootBefore());
            }
        }
        for (AbstractCatchEval c: _catchs) {
            catchs_.add(id_.getVal(c).getFieldsRoot());
        }
        StringMap<SimpleAssignment> tryAfter_ = id_.getVal(this).getFieldsRoot();
        StringMap<AssignmentBefore> tryBefore_ = id_.getVal(this).getFieldsRootBefore();
        return buildAssBefNextSibling(tryAfter_, tryBefore_, throws_, others_, catchs_);
    }
    protected CustList<StringMap<AssignmentBefore>> buildAssVarsBefNextSibling(Analyzable _an, AnalyzingEl _anEl,
            CustList<AbstractCatchEval> _catchs) {
        CustList<AbruptBlock> abr_ = getAbruptTry(_an, _anEl);
        CustList<StringMap<AssignmentBefore>> out_ = new CustList<StringMap<AssignmentBefore>>();
        IdMap<Block, AssignedVariables> id_;
        id_ = _an.getAssignedVariables().getFinalVariables();
        CustList<StringMap<SimpleAssignment>> tryAfters_ = id_.getVal(this).getVariablesRoot();
        CustList<StringMap<AssignmentBefore>> tryBefores_ = id_.getVal(this).getVariablesRootBefore();
        int loopLen_ = tryAfters_.size();
        for (int i = 0; i < loopLen_; i++) {
            StringMap<SimpleAssignment> tryAfter_ = tryAfters_.get(i);
            StringMap<AssignmentBefore> tryBefore_ = tryBefores_.get(i);
            CustList<StringMap<Assignment>> throws_ = new CustList<StringMap<Assignment>>();
            CustList<StringMap<AssignmentBefore>> others_ = new CustList<StringMap<AssignmentBefore>>();
            CustList<StringMap<SimpleAssignment>> catchs_ = new CustList<StringMap<SimpleAssignment>>();
            for (AbruptBlock a: abr_) {
                if (a instanceof Throwing) {
                    CustList<StringMap<Assignment>> li_ = id_.getVal(a).getVariables().lastValue();
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
                CustList<StringMap<SimpleAssignment>> li_ = id_.getVal(c).getVariablesRoot();
                if (li_.isValidIndex(i)) {
                    catchs_.add(li_.get(i));
                }
            }
            out_.add(buildAssBefNextSibling(tryAfter_, tryBefore_, throws_, others_,catchs_));
        }
        return out_;
    }
    protected static StringMap<AssignmentBefore> buildAssBefNextSibling(StringMap<SimpleAssignment> _tryAfter,
            StringMap<AssignmentBefore> _tryBefore,CustList<StringMap<Assignment>> _throws,
            CustList<StringMap<AssignmentBefore>> _others, CustList<StringMap<SimpleAssignment>> _catchs) {
        StringMap<AssignmentBefore> out_ = new StringMap<AssignmentBefore>();
        for (EntryCust<String, SimpleAssignment> e: _tryAfter.entryList()) {
            AssignmentBefore ab_ = new AssignmentBefore();
            String key_ = e.getKey();
            if (_tryBefore.getVal(key_).isAssignedBefore()) {
                ab_.setAssignedBefore(true);
            }
            boolean unass_ = e.getValue().isUnassignedAfter();
            for (StringMap<Assignment> t: _throws) {
                if (!t.getVal(key_).isUnassignedAfter()) {
                    unass_ = false;
                }
            }
            for (StringMap<AssignmentBefore> t: _others) {
                if (!t.getVal(key_).isUnassignedBefore()) {
                    unass_ = false;
                }
            }
            for (StringMap<SimpleAssignment> c: _catchs) {
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
    protected static StringMap<SimpleAssignment> buildAssAfter(StringMap<BooleanAssignment> _loop, CustList<StringMap<AssignmentBefore>> _breakAss) {
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
}
