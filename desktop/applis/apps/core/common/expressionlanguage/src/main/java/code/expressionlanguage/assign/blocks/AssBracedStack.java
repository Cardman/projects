package code.expressionlanguage.assign.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.assign.opers.*;
import code.expressionlanguage.assign.util.*;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.util.*;

public abstract class AssBracedStack extends AssBracedBlock {
    AssBracedStack(boolean _completeNormally, boolean _completeNormallyGroup) {
        super(_completeNormally,_completeNormallyGroup);
    }
    protected void buildConditions(ContextEl _cont, AssignedVariablesBlock _a) {
        AssignedBooleanVariables res_ = (AssignedBooleanVariables) _a.getFinalVariables().getVal(this);
        res_.getFieldsRootAfter().putAllMap(AssignmentsUtil.toBoolAssign(res_.getLastFieldsOrEmpty()));
        res_.getVariablesRootAfter().putAllMap(AssignmentsUtil.toBoolAssign(res_.getLastVariablesOrEmpty()));
    }
    protected void assignWhenFalse(boolean _add,ContextEl _an, AssignedVariablesBlock _anEl) {
        AssBlock firstChild_;
        IdMap<AssBlock, AssignedVariables> id_ = _anEl.getFinalVariables();
        AssignedVariables parAss_ = id_.getVal(this);
        AssBlock pr_ = getPreviousSibling();
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
        assBl_.getVariablesRootBefore().putAllMap(AssignmentsUtil.assignBoolWhenFalse(abv_.getVariablesRootAfter()));
        id_.put(firstChild_, assBl_);
    }
    protected void assignWhenTrue(ContextEl _an, AssignedVariablesBlock _anEl) {
        AssBlock firstChild_ = getFirstChild();
        IdMap<AssBlock, AssignedVariables> id_ = _anEl.getFinalVariables();
        AssignedVariables parAss_ = id_.getVal(this);
        AssignedVariables assBl_ = firstChild_.buildNewAssignedVariable();
        AssignedBooleanVariables abv_ = (AssignedBooleanVariables) parAss_;
        assBl_.getFieldsRootBefore().putAllMap(AssignmentsUtil.assignBoolWhenTrue(abv_.getFieldsRootAfter()));
        assBl_.getVariablesRootBefore().putAllMap(AssignmentsUtil.assignBoolWhenTrue(abv_.getVariablesRootAfter()));
        id_.put(firstChild_, assBl_);
    }
    protected void processFinalFields(ContextEl _an,
                                      IdMap<AssBlock, AssignedVariables> _allDesc,
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
            processFinalFields(this, false, _an, _root, key_);
            for (EntryCust<AssBlock, AssignedVariables> d: _allDesc.entryList()) {
                vars_ = d.getValue();
                AssBlock next_ = d.getKey();
                //next siblings of d
                processFinalFields(next_, true, _an, vars_, key_);
            }
        }
    }

    protected void processFinalVars(ContextEl _an, AssignedVariablesBlock _anEl,
                                    IdMap<AssBlock, AssignedVariables> _allDesc,
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
            if (!_anEl.isFinalLocalVar(key_)) {
                continue;
            }
            processFinalVars(this, false, _an, _root, key_);
            for (EntryCust<AssBlock, AssignedVariables> d: _allDesc.entryList()) {
                vars_ = d.getValue();
                AssBlock next_ = d.getKey();
                //next siblings of d
                processFinalVars(next_,true, _an, vars_, key_);
            }
        }

    }

    protected static void processFinalVars(AssBlock _curBlock, boolean _all, ContextEl _an, AssignedVariables _vars, String _field) {
        for (AssAffectationOperation f: _vars.getVariablesBefore(_curBlock,_all)) {
            AssOperationNode set_ = f.getSettableOp();
            if (!(set_ instanceof AssStdVariableOperation)) {
                continue;
            }
            AssStdVariableOperation cst_ = (AssStdVariableOperation) set_;
            String str_ = cst_.getVariableName();
            if (!StringList.quickEq(str_, _field)) {
                continue;
            }
            cst_.setRelativeOffsetPossibleAnalyzable(_an);
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFileName(_an.getAnalyzing().getLocalizer().getCurrentFileName());
            un_.setIndexFile(_an.getAnalyzing().getTraceIndex());
            un_.buildError(_an.getAnalyzing().getAnalysisMessages().getFinalField(),
                    str_);
            _an.getAnalyzing().addLocError(un_);
        }
    }

    protected static void processFinalFields(AssBlock _curBlock, boolean _all, ContextEl _an, AssignedVariables _vars, String _field) {
        for (AssAffectationOperation f: _vars.getFieldsBefore(_curBlock,_all)) {
            AssOperationNode set_ = f.getSettableOp();
            if (!(set_ instanceof AssSettableFieldOperation)) {
                continue;
            }
            AssSettableFieldOperation cst_ = (AssSettableFieldOperation) set_;
            if (!cst_.getFieldMetaInfo().isFinalField()) {
                continue;
            }
            String cl_ = StringExpUtil.getIdFromAllTypes(_an.getAnalyzing().getGlobalClass());
            ClassField key_ = new ClassField(cl_,_field);
            if (!cst_.matchFieldId(key_)) {
                continue;
            }
            cst_.setRelativeOffsetPossibleAnalyzable(_an);
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFileName(_an.getAnalyzing().getLocalizer().getCurrentFileName());
            un_.setIndexFile(_an.getAnalyzing().getTraceIndex());
            un_.buildError(_an.getAnalyzing().getAnalysisMessages().getFinalField(),
                    _field);
            _an.getAnalyzing().addLocError(un_);
        }
    }
    protected StringMap<AssignmentBefore> buildAssListFieldBeforeIncrPart(ContextEl _an, AssignedVariablesBlock _anEl) {
        AssBlock last_ = getFirstChild();
        while (last_.getNextSibling() != null) {
            last_ = last_.getNextSibling();
        }
        CustList<AssContinueBlock> continues_ = getContinuables();
        IdMap<AssBlock, AssignedVariables> id_;
        id_ = _anEl.getFinalVariables();
        StringMap<SimpleAssignment> list_;
        list_ = id_.getVal(this).getFieldsRoot();
        int contLen_ = continues_.size();
        CustList<StringMap<AssignmentBefore>> breakAss_;
        breakAss_ = new CustList<StringMap<AssignmentBefore>>();
        for (int j = 0; j < contLen_; j++) {
            AssContinueBlock br_ = continues_.get(j);
            AssignedVariables ass_ = id_.getVal(br_);
            StringMap<AssignmentBefore> vars_ = ass_.getFieldsRootBefore();
            breakAss_.add(vars_);
        }
        if (last_.isCompleteNormallyGroup()) {
            AssignedVariables ass_ = id_.getVal(last_);
            StringMap<SimpleAssignment> v_ = ass_.getFieldsRoot();
            return beforeIncrPart(list_, v_, breakAss_);
        }
        return beforeIncrPart(list_, new StringMap<SimpleAssignment>(), breakAss_);
    }
    protected StringMap<AssignmentBefore> buildAssListLocVarBeforeIncrPart(ContextEl _an, AssignedVariablesBlock _anEl) {
        AssBlock last_ = getFirstChild();
        while (last_.getNextSibling() != null) {
            last_ = last_.getNextSibling();
        }
        CustList<AssContinueBlock> continues_ = getContinuables();
        IdMap<AssBlock, AssignedVariables> id_;
        id_ = _anEl.getFinalVariables();
        StringMap<AssignmentBefore> varsList_;
        StringMap<SimpleAssignment> list_;
        list_ = id_.getVal(this).getVariablesRoot();
        int contLen_ = continues_.size();
        CustList<StringMap<AssignmentBefore>> breakAss_;
        breakAss_ = new CustList<StringMap<AssignmentBefore>>();
        for (int j = 0; j < contLen_; j++) {
            AssContinueBlock br_ = continues_.get(j);
            AssignedVariables ass_ = id_.getVal(br_);
            StringMap<AssignmentBefore> vars_ = ass_.getVariablesRootBefore();
            breakAss_.add(vars_);
        }
        if (last_.isCompleteNormallyGroup()) {
            AssignedVariables ass_ = id_.getVal(last_);
            StringMap<SimpleAssignment> v_ = ass_.getVariablesRoot();
            varsList_=beforeIncrPart(list_, v_, breakAss_);
        } else {
            varsList_=beforeIncrPart(list_, new StringMap<SimpleAssignment>(), breakAss_);
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
                for (EntryCust<String,AssignmentBefore> f: c.entryList()) {
                    if (!StringList.quickEq(f.getKey(),key_)) {
                        continue;
                    }
                    if (!f.getValue().isAssignedBefore()) {
                        contAss_ = false;
                    }
                    if (!f.getValue().isUnassignedBefore()) {
                        contUnass_ = false;
                    }
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
    protected StringMap<SimpleAssignment> buildAssVariablesAfterIf(boolean _useBool,
                                                                             CustList<AssBlock> _blocks,ContextEl _an, AssignedVariablesBlock _anEl) {

        IdMap<AssBlock, AssignedVariables> id_;
        id_ = _anEl.getFinalVariables();
        AssignedVariables idStdIf_;
        idStdIf_ = id_.getVal(this);
        StringMap<SimpleAssignment> list_;
        list_ = idStdIf_.getVariablesRoot();
        StringMap<BooleanAssignment> bool_;
        if (_useBool) {
            AssignedBooleanVariables idIf_;
            idIf_ = (AssignedBooleanVariables) id_.getVal(this);
            StringMap<BooleanAssignment> bools_;
            bools_ = idIf_.getVariablesRootAfter();
            bool_ = bools_;
        } else {
            bool_ = new StringMap<BooleanAssignment>();
        }
        CustList<StringMap<SimpleAssignment>> listBl_;
        listBl_ = new CustList<StringMap<SimpleAssignment>>();
        CustList<CustList<StringMap<AssignmentBefore>>> listBrs_;
        listBrs_ = new CustList<CustList<StringMap<AssignmentBefore>>>();
        for (AssBlock c: _blocks) {
            CustList<AssBreakBlock> breaks_ = ((AssBracedStack)c).getBreakables();
            CustList<StringMap<AssignmentBefore>> listBr_;
            listBr_ = new CustList<StringMap<AssignmentBefore>>();
            if (c.isCompleteNormally()) {
                StringMap<SimpleAssignment> map_;
                map_ = id_.getVal(c).getVariablesRoot();
                listBl_.add(map_);
            } else {
                listBl_.add(new StringMap<SimpleAssignment>());
            }
            for (AssBreakBlock b: breaks_) {
                StringMap<AssignmentBefore> map_;
                map_ = id_.getVal(b).getVariablesRootBefore();
                listBr_.add(map_);
            }
            listBrs_.add(listBr_);
        }
        return buildAssAfterIf(list_, bool_, listBl_, listBrs_);
    }

    protected StringMap<SimpleAssignment> buildAssFieldsAfterIf(boolean _useBool,
                                                                CustList<AssBlock> _blocks,ContextEl _an, AssignedVariablesBlock _anEl) {
        IdMap<AssBlock, AssignedVariables> id_;
        id_ = _anEl.getFinalVariables();
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
        for (AssBlock c: _blocks) {
            CustList<AssBreakBlock> breaks_ = ((AssBracedStack)c).getBreakables();
            CustList<StringMap<AssignmentBefore>> listBr_;
            listBr_ = new CustList<StringMap<AssignmentBefore>>();
            if (c.isCompleteNormally()) {
                listBl_.add(id_.getVal(c).getFieldsRoot());
            } else {
                listBl_.add(new StringMap<SimpleAssignment>());
            }
            for (AssBreakBlock b: breaks_) {
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
    protected StringMap<SimpleAssignment> buildAssVariablesAfterSwitch(
            boolean _default,
            AssBlock _last,
            ContextEl _an, AssignedVariablesBlock _anEl) {
        IdMap<AssBlock, AssignedVariables> id_;
        id_ = _anEl.getFinalVariables();
        StringMap<Assignment> list_;
        list_ = id_.getVal(this).getLastVariablesOrEmpty();
        StringMap<SimpleAssignment> last_;
        if (_last.isCompleteNormally()) {
            StringMap<SimpleAssignment> map_;
            map_ = id_.getVal(_last).getVariablesRoot();
            last_ = map_;
        } else {
            last_ = new StringMap<SimpleAssignment>();
        }
        CustList<AssBreakBlock> breaks_ = getBreakables();
        CustList<StringMap<AssignmentBefore>> listBr_;
        listBr_ = new CustList<StringMap<AssignmentBefore>>();
        for (AssBreakBlock b: breaks_) {
            StringMap<AssignmentBefore> map_;
            map_ = id_.getVal(b).getVariablesRootBefore();
            listBr_.add(map_);
        }
        return buildAssAfterSwitch(_default, list_, last_, listBr_);
    }

    protected StringMap<SimpleAssignment> buildAssFieldsAfterSwitch(
            boolean _default,
            AssBlock _last,
            ContextEl _an, AssignedVariablesBlock _anEl) {
        IdMap<AssBlock, AssignedVariables> id_;
        id_ = _anEl.getFinalVariables();
        StringMap<Assignment> list_;
        list_ = id_.getVal(this).getLastFieldsOrEmpty();
        StringMap<SimpleAssignment> last_;
        if (_last.isCompleteNormally()) {
            last_ = id_.getVal(_last).getFieldsRoot();
        } else {
            last_ = new StringMap<SimpleAssignment>();
        }
        CustList<AssBreakBlock> breaks_ = getBreakables();
        CustList<StringMap<AssignmentBefore>> listBr_;
        listBr_ = new CustList<StringMap<AssignmentBefore>>();
        for (AssBreakBlock b: breaks_) {
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
                for (EntryCust<String, AssignmentBefore> f: b.entryList()) {
                    if (!StringList.quickEq(f.getKey(),key_)) {
                        continue;
                    }
                    if (!f.getValue().isAssignedBefore()) {
                        assAfter_ = false;
                    }
                    if (!f.getValue().isUnassignedBefore()) {
                        unassAfter_ = false;
                    }
                }
            }
            out_.put(key_, Assignment.assignClassic(assAfter_, unassAfter_));
        }
        return out_;
    }
    protected StringMap<SimpleAssignment> buildAssListFieldAfterLoop(ContextEl _an, AssignedVariablesBlock _anEl) {
        CustList<AssBreakBlock> breaks_ = getBreakables();
        IdMap<AssBlock, AssignedVariables> id_;
        id_ = _anEl.getFinalVariables();
        StringMap<BooleanAssignment> list_;
        list_ = ((AssignedBooleanVariables) id_.getVal(this)).getFieldsRootAfter();
        int breakLen_ = breaks_.size();
        CustList<StringMap<AssignmentBefore>> breakAss_;
        breakAss_ = new CustList<StringMap<AssignmentBefore>>();
        for (int j = 0; j < breakLen_; j++) {
            AssBreakBlock br_ = breaks_.get(j);
            AssignedVariables ass_ = id_.getVal(br_);
            StringMap<AssignmentBefore> vars_ = ass_.getFieldsRootBefore();
            breakAss_.add(vars_);
        }
        return buildAssAfterLoop(list_, breakAss_);
    }
    protected StringMap<SimpleAssignment> buildAssListLocVarAfterLoop(ContextEl _an, AssignedVariablesBlock _anEl) {
        CustList<AssBreakBlock> breaks_ = getBreakables();
        IdMap<AssBlock, AssignedVariables> id_;
        id_ = _anEl.getFinalVariables();
        StringMap<BooleanAssignment> list_;
        list_ = ((AssignedBooleanVariables) id_.getVal(this)).getVariablesRootAfter();
        int breakLen_ = breaks_.size();
        CustList<StringMap<AssignmentBefore>> breakAss_;
        breakAss_ = new CustList<StringMap<AssignmentBefore>>();
        for (int j = 0; j < breakLen_; j++) {
            AssBreakBlock br_ = breaks_.get(j);
            AssignedVariables ass_ = id_.getVal(br_);
            StringMap<AssignmentBefore> vars_ = ass_.getVariablesRootBefore();
            breakAss_.add(vars_);
        }
        return buildAssAfterLoop(list_, breakAss_);
    }

    protected static StringMap<SimpleAssignment> buildAssAfterLoop(StringMap<BooleanAssignment> _loop, CustList<StringMap<AssignmentBefore>> _breakAss) {
        StringMap<SimpleAssignment> out_ = new StringMap<SimpleAssignment>();
        for (EntryCust<String,BooleanAssignment> e: _loop.entryList()) {
            BooleanAssignment ba_ = e.getValue();
            boolean ass_ = ba_.isAssignedAfterWhenFalse();
            boolean unass_ = ba_.isUnassignedAfterWhenFalse();
            String key_ = e.getKey();
            for (StringMap<AssignmentBefore> b: _breakAss) {
                for (EntryCust<String, AssignmentBefore> f: b.entryList()) {
                    if (!StringList.quickEq(f.getKey(),key_)) {
                        continue;
                    }
                    if (!f.getValue().isAssignedBefore()) {
                        ass_ = false;
                    }
                    if (!f.getValue().isUnassignedBefore()) {
                        unass_ = false;
                    }
                }
            }
            out_.put(key_, Assignment.assignClassic(ass_, unass_));
        }
        return out_;
    }

    protected StringMap<SimpleAssignment> buildAssVariablesAfterFinally(CustList<AssBlock> _blocks,ContextEl _an, AssignedVariablesBlock _anEl) {
        IdMap<AssBlock, AssignedVariables> id_;
        id_ = _anEl.getFinalVariables();
        StringMap<SimpleAssignment> list_;
        list_ = id_.getVal(this).getVariablesRoot();
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
        CustList<AssBreakBlock> breaks_ = getBreakables();
        CustList<StringMap<AssignmentBefore>> listBr_;
        CustList<StringMap<AssignmentBefore>> listBrFin_;
        listBrFin_ = new CustList<StringMap<AssignmentBefore>>();
        if (isCompleteNormally()) {
            StringMap<SimpleAssignment> map_;
            map_ = id_.getVal(this).getVariablesRoot();
            listBlFin_.add(map_);
        } else {
            listBlFin_.add(new StringMap<SimpleAssignment>());
        }
        for (AssBreakBlock b: breaks_) {
            StringMap<AssignmentBefore> map_;
            map_ = id_.getVal(b).getVariablesRootBefore();
            listBrFin_.add(map_);
        }
        listSingBrs_.add(listBrFin_);
        StringMap<SimpleAssignment> fin_ = buildAssAfterTry(list_, listBlFin_, listSingBrs_);

        for (AssBlock c: _blocks) {
            breaks_ = ((AssBracedStack)c).getBreakables();
            listBr_ = new CustList<StringMap<AssignmentBefore>>();
            if (c.isCompleteNormally()) {
                StringMap<SimpleAssignment> map_;
                map_ = id_.getVal(c).getVariablesRoot();
                listBl_.add(map_);
            } else {
                listBl_.add(new StringMap<SimpleAssignment>());
            }
            for (AssBreakBlock b: breaks_) {
                StringMap<AssignmentBefore> map_;
                map_ = id_.getVal(b).getVariablesRootBefore();
                listBr_.add(map_);
            }
            listBrs_.add(listBr_);
        }
        StringMap<SimpleAssignment> prev_ = buildAssAfterTry(list_, listBl_, listBrs_);
        buildAssFinally(out_, fin_, prev_);
        return out_;
    }

    private static void buildAssFinally(StringMap<SimpleAssignment> _out, StringMap<SimpleAssignment> _fin, StringMap<SimpleAssignment> _prev) {
        for (EntryCust<String, SimpleAssignment> f: _fin.entryList()) {
            if (!f.getValue().isAssignedAfter()) {
                if (_prev.getVal(f.getKey()).isAssignedAfter()) {
                    _out.put(f.getKey(), _prev.getVal(f.getKey()));
                } else {
                    _out.put(f.getKey(), f.getValue());
                }
            } else {
                _out.put(f.getKey(), f.getValue());
            }
        }
    }

    protected StringMap<SimpleAssignment> buildAssFieldsAfterFinally(CustList<AssBlock> _blocks,ContextEl _an, AssignedVariablesBlock _anEl) {
        IdMap<AssBlock, AssignedVariables> id_;
        id_ = _anEl.getFinalVariables();
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
        CustList<AssBreakBlock> breaks_ = getBreakables();
        CustList<StringMap<AssignmentBefore>> listBr_;
        CustList<StringMap<AssignmentBefore>> listBrFin_;
        listBrFin_ = new CustList<StringMap<AssignmentBefore>>();
        if (isCompleteNormally()) {
            listBlFin_.add(id_.getVal(this).getFieldsRoot());
        } else {
            listBlFin_.add(new StringMap<SimpleAssignment>());
        }
        for (AssBreakBlock b: breaks_) {
            listBrFin_.add(id_.getVal(b).getFieldsRootBefore());
        }
        listSingBrs_.add(listBrFin_);
        StringMap<SimpleAssignment> fin_ = buildAssAfterTry(list_, listBlFin_, listSingBrs_);
        for (AssBlock c: _blocks) {
            breaks_ = ((AssBracedStack)c).getBreakables();
            listBr_ = new CustList<StringMap<AssignmentBefore>>();
            if (c.isCompleteNormally()) {
                listBl_.add(id_.getVal(c).getFieldsRoot());
            } else {
                listBl_.add(new StringMap<SimpleAssignment>());
            }
            for (AssBreakBlock b: breaks_) {
                listBr_.add(id_.getVal(b).getFieldsRootBefore());
            }
            listBrs_.add(listBr_);
        }
        StringMap<SimpleAssignment> prev_ = buildAssAfterTry(list_, listBl_, listBrs_);
        StringMap<SimpleAssignment> out_;
        out_ = new StringMap<SimpleAssignment>();
        buildAssFinally(out_, fin_, prev_);
        return out_;
    }
    protected StringMap<SimpleAssignment> buildAssVariablesAfterTry(CustList<AssBlock> _blocks,ContextEl _an, AssignedVariablesBlock _anEl) {
        IdMap<AssBlock, AssignedVariables> id_;
        id_ = _anEl.getFinalVariables();
        CustList<StringMap<SimpleAssignment>> out_;
        out_ = new CustList<StringMap<SimpleAssignment>>();
        StringMap<SimpleAssignment> list_;
        list_ = id_.getVal(this).getVariablesRoot();
        int len_ = list_.size();
        CustList<StringMap<SimpleAssignment>> listBl_;
        listBl_ = new CustList<StringMap<SimpleAssignment>>();
        CustList<CustList<StringMap<AssignmentBefore>>> listBrs_;
        listBrs_ = new CustList<CustList<StringMap<AssignmentBefore>>>();
        for (AssBlock c: _blocks) {
            CustList<AssBreakBlock> breaks_ = ((AssBracedStack)c).getBreakables();
            CustList<StringMap<AssignmentBefore>> listBr_;
            listBr_ = new CustList<StringMap<AssignmentBefore>>();
            if (c.isCompleteNormally()) {
                StringMap<SimpleAssignment> map_;
                map_ = id_.getVal(c).getVariablesRoot();
                listBl_.add(map_);
            } else {
                listBl_.add(new StringMap<SimpleAssignment>());
            }
            for (AssBreakBlock b: breaks_) {
                StringMap<AssignmentBefore> map_;
                map_ = id_.getVal(b).getVariablesRootBefore();
                listBr_.add(map_);
            }
            listBrs_.add(listBr_);
        }
        return buildAssAfterTry(list_, listBl_, listBrs_);
    }

    protected StringMap<SimpleAssignment> buildAssFieldsAfterTry(CustList<AssBlock> _blocks,ContextEl _an, AssignedVariablesBlock _anEl) {
        IdMap<AssBlock, AssignedVariables> id_;
        id_ = _anEl.getFinalVariables();
        StringMap<SimpleAssignment> list_;
        list_ = id_.getVal(this).getFieldsRoot();
        CustList<StringMap<SimpleAssignment>> listBl_;
        listBl_ = new CustList<StringMap<SimpleAssignment>>();
        CustList<CustList<StringMap<AssignmentBefore>>> listBrs_;
        listBrs_ = new CustList<CustList<StringMap<AssignmentBefore>>>();
        for (AssBlock c: _blocks) {
            CustList<AssBreakBlock> breaks_ = ((AssBracedStack)c).getBreakables();
            CustList<StringMap<AssignmentBefore>> listBr_;
            listBr_ = new CustList<StringMap<AssignmentBefore>>();
            if (c.isCompleteNormally()) {
                listBl_.add(id_.getVal(c).getFieldsRoot());
            } else {
                listBl_.add(new StringMap<SimpleAssignment>());
            }
            for (AssBreakBlock b: breaks_) {
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
                    for (EntryCust<String, AssignmentBefore> f: b.entryList()) {
                        if (!StringList.quickEq(key_, f.getKey())) {
                            continue;
                        }
                        if (!f.getValue().isAssignedBefore()) {
                            assAfter_ = false;
                        }
                        if (!f.getValue().isUnassignedBefore()) {
                            unassAfter_ = false;
                        }
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
    protected static StringMap<AssignmentBefore> buildAssFieldsBefNextCatchFinally(AssTryEval _try, ContextEl _an, AssignedVariablesBlock _anEl,
                                                                                   CustList<AssCatchEval> _catchs) {
        CustList<AssAbruptBlock> abr_ = _try.getAbruptTry(_an, _anEl);
        CustList<StringMap<Assignment>> throws_ = new CustList<StringMap<Assignment>>();
        CustList<StringMap<AssignmentBefore>> others_ = new CustList<StringMap<AssignmentBefore>>();
        CustList<StringMap<SimpleAssignment>> catchs_ = new CustList<StringMap<SimpleAssignment>>();
        IdMap<AssBlock, AssignedVariables> id_;
        id_ = _anEl.getFinalVariables();
        for (AssAbruptBlock a: abr_) {
            if (a instanceof AssReturnMethod) {
                if (((AssReturnMethod)a).isEmpty()) {
                    others_.add(id_.getVal(a).getFieldsRootBefore());
                } else {
                    throws_.add(id_.getVal(a).getLastFieldsOrEmpty());
                }
            } else if (a instanceof AssThrowing) {
                throws_.add(id_.getVal(a).getLastFieldsOrEmpty());
            } else {
                others_.add(id_.getVal(a).getFieldsRootBefore());
            }
        }
        for (AssCatchEval c: _catchs) {
            for (AssAbruptBlock a: c.getAbruptTry(_an, _anEl)) {
                if (a instanceof AssReturnMethod) {
                    if (((AssReturnMethod)a).isEmpty()) {
                        others_.add(id_.getVal(a).getFieldsRootBefore());
                    } else {
                        throws_.add(id_.getVal(a).getLastFieldsOrEmpty());
                    }
                } else if (a instanceof AssThrowing) {
                    throws_.add(id_.getVal(a).getLastFieldsOrEmpty());
                } else {
                    others_.add(id_.getVal(a).getFieldsRootBefore());
                }
            }
        }
        for (AssCatchEval c: _catchs) {
            if (c.isCompleteNormally()) {
                catchs_.add(id_.getVal(c).getFieldsRoot());
            }
        }
        StringMap<SimpleAssignment> tryAfter_;
        if (_try.isCompleteNormally()) {
            tryAfter_ = id_.getVal(_try).getFieldsRoot();
        } else {
            tryAfter_ = new StringMap<SimpleAssignment>();
        }
        StringMap<AssignmentBefore> tryBefore_ = id_.getVal(_try).getFieldsRootBefore();
        return buildAssBefNextCatchFinally(tryAfter_, tryBefore_, throws_, others_, catchs_);
    }
    protected static StringMap<AssignmentBefore> buildAssVarsBefNextCatchFinally(AssTryEval _try, ContextEl _an, AssignedVariablesBlock _anEl,
                                                                                           CustList<AssCatchEval> _catchs) {
        CustList<AssAbruptBlock> abr_ = _try.getAbruptTry(_an, _anEl);
        CustList<StringMap<AssignmentBefore>> out_ = new CustList<StringMap<AssignmentBefore>>();
        IdMap<AssBlock, AssignedVariables> id_;
        id_ = _anEl.getFinalVariables();
        StringMap<SimpleAssignment> tryAfters_ = id_.getVal(_try).getVariablesRoot();
        StringMap<AssignmentBefore> tryBefores_ = id_.getVal(_try).getVariablesRootBefore();
        StringMap<SimpleAssignment> tryAfter_;
        if (_try.isCompleteNormally()) {
            tryAfter_ = tryAfters_;
        } else {
            tryAfter_ = new StringMap<SimpleAssignment>();
        }
        StringMap<AssignmentBefore> tryBefore_ = tryBefores_;
        CustList<StringMap<Assignment>> throws_ = new CustList<StringMap<Assignment>>();
        CustList<StringMap<AssignmentBefore>> others_ = new CustList<StringMap<AssignmentBefore>>();
        CustList<StringMap<SimpleAssignment>> catchs_ = new CustList<StringMap<SimpleAssignment>>();
        for (AssAbruptBlock a: abr_) {
            if (a instanceof AssReturnMethod) {
                if (((AssReturnMethod)a).isEmpty()) {
                    StringMap<AssignmentBefore> li_ = id_.getVal(a).getVariablesRootBefore();
                    others_.add(li_);
                } else {
                    StringMap<Assignment> li_ = id_.getVal(a).getLastVariablesOrEmpty();
                    throws_.add(li_);
                }
            } else if (a instanceof AssThrowing) {
                StringMap<Assignment> li_ = id_.getVal(a).getLastVariablesOrEmpty();
                throws_.add(li_);
            } else {
                StringMap<AssignmentBefore> li_ = id_.getVal(a).getVariablesRootBefore();
                others_.add(li_);
            }
        }
        for (AssCatchEval c: _catchs) {
            for (AssAbruptBlock a: c.getAbruptTry(_an, _anEl)) {
                if (a instanceof AssReturnMethod) {
                    if (((AssReturnMethod)a).isEmpty()) {
                        StringMap<AssignmentBefore> li_ = id_.getVal(a).getVariablesRootBefore();
                        others_.add(li_);
                    } else {
                        StringMap<Assignment> li_ = id_.getVal(a).getLastVariablesOrEmpty();
                        throws_.add(li_);
                    }
                } else if (a instanceof AssThrowing) {
                    StringMap<Assignment> li_ = id_.getVal(a).getLastVariablesOrEmpty();
                    throws_.add(li_);
                } else {
                    StringMap<AssignmentBefore> li_ = id_.getVal(a).getVariablesRootBefore();
                    others_.add(li_);
                }
            }
        }
        for (AssCatchEval c: _catchs) {
            if (c.isCompleteNormally()) {
                StringMap<SimpleAssignment> li_ = id_.getVal(c).getVariablesRoot();
                catchs_.add(li_);
            }
        }
        return buildAssBefNextCatchFinally(tryAfter_, tryBefore_, throws_, others_,catchs_);
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
                for (EntryCust<String,Assignment> f: t.entryList()) {
                    if (!StringList.quickEq(f.getKey(),key_)) {
                        continue;
                    }
                    if (!f.getValue().isUnassignedAfter()) {
                        unass_ = false;
                    }
                }
            }
            for (StringMap<AssignmentBefore> t: _others) {
                for (EntryCust<String,AssignmentBefore> f: t.entryList()) {
                    if (!StringList.quickEq(f.getKey(),key_)) {
                        continue;
                    }
                    if (!f.getValue().isUnassignedBefore()) {
                        unass_ = false;
                    }
                }
            }
            for (StringMap<SimpleAssignment> c: _catchs) {
                for (EntryCust<String,SimpleAssignment> f: c.entryList()) {
                    if (!StringList.quickEq(f.getKey(),key_)) {
                        continue;
                    }
                    if (!f.getValue().isUnassignedAfter()) {
                        unass_ = false;
                    }
                }
            }
            if (unass_) {
                ab_.setUnassignedBefore(true);
            }
            out_.put(e.getKey(), ab_);
        }
        return out_;
    }
    protected CustList<AssAbruptBlock> getAbruptTry(ContextEl _an, AssignedVariablesBlock _anEl) {
        IdMap<AssBlock, AssignedVariables> id_ = _anEl.getFinalVariables();
        IdList<AssBlock> inners_;
        inners_ = new IdList<AssBlock>();
        boolean add_ = false;
        for (EntryCust<AssBlock, AssignedVariables> e: id_.entryList()) {
            if (e.getKey() == this) {
                add_ = true;
            }
            if (add_) {
                inners_.add(e.getKey());
            }
        }
        CustList<AssAbruptBlock> stoppers_ = new CustList<AssAbruptBlock>();
        for (AssBlock f: inners_) {
            if (!(f instanceof AssAbruptBlock)) {
                continue;
            }
            if (f instanceof AssContinueBlock) {
                AssBracedBlock d_ = ((AssContinueBlock) f).directAsc();
                IdList<AssBracedBlock> pars_ = f.getParentsIntervalUntil(d_);
                if (!pars_.containsObj(this)) {
                    continue;
                }
            }
            if (f instanceof AssBreakBlock) {
                AssBracedBlock d_ = ((AssBreakBlock) f).directAsc();
                IdList<AssBracedBlock> pars_ = f.getParentsIntervalUntil(d_);
                if (!pars_.containsObj(this) && d_ != this) {
                    continue;
                }
            }
            stoppers_.add((AssAbruptBlock)f);
        }
        return stoppers_;
    }
    protected CustList<AssContinueBlock> getContinuables() {
        CustList<AssContinueBlock> breaks_ = new CustList<AssContinueBlock>();
        for (AssBlock b: getSelfDescendants()) {
            if (!(b instanceof AssContinueBlock)) {
                continue;
            }
            if (((AssContinueBlock)b).directAsc() == this){
                breaks_.add((AssContinueBlock)b);
            }
        }
        return breaks_;
    }
    protected CustList<AssBreakBlock> getBreakables() {
        CustList<AssBreakBlock> breaks_ = new CustList<AssBreakBlock>();
        for (AssBlock b: getSelfDescendants()) {
            if (!(b instanceof AssBreakBlock)) {
                continue;
            }
            if (((AssBreakBlock)b).directAsc() == this){
                breaks_.add((AssBreakBlock)b);
            }
        }
        return breaks_;
    }
    private CustList<AssBlock> getSelfDescendants() {
        CustList<AssBlock> l_ = new CustList<AssBlock>();
        if (getFirstChild() == null) {
            l_.add(this);
            return l_;
        }
        AssBlock r_ = this;
        AssBlock d_ = this;
        while (true) {
            AssBlock f_ = d_.getFirstChild();
            l_.add(d_);
            if (f_ != null) {
                d_ = f_;
                continue;
            }
            boolean stop_ = false;
            while (true) {
                AssBlock n_ = d_.getNextSibling();
                if (n_ != null) {
                    d_ = n_;
                    break;
                }
                AssBracedBlock par_ = d_.getParent();
                if (par_ == r_) {
                    stop_ = true;
                    break;
                }
                d_ = par_;
            }
            if (stop_) {
                break;
            }
        }
        return l_;
    }
}
