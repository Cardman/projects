package code.expressionlanguage.analyze.assign.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.assign.opers.*;
import code.expressionlanguage.analyze.assign.util.*;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.util.*;
import code.util.core.StringUtil;

public abstract class AssBracedStack extends AssBracedBlock {
    AssBracedStack(boolean _completeNormally, boolean _completeNormallyGroup) {
        super(_completeNormally,_completeNormallyGroup);
    }
    protected void buildConditions(AssignedVariablesBlock _a) {
        AssignedBooleanVariables res_ = (AssignedBooleanVariables) _a.getFinalVariables().getVal(this);
        res_.getFieldsRootAfter().putAllMap(AssignmentsUtil.toBoolAssign(res_.getLastFieldsOrEmpty()));
        res_.getVariablesRootAfter().putAllMap(AssignmentsUtil.toBoolAssign(res_.getLastVariablesOrEmpty()));
    }
    protected void assignWhenFalse(boolean _add, AssignedVariablesBlock _anEl) {
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
            super.setAssignmentBeforeChild(_anEl);
            return;
        }
        AssignedBooleanVariables abv_ = (AssignedBooleanVariables) parAss_;
        assBl_.getFieldsRootBefore().putAllMap(AssignmentsUtil.assignBoolWhenFalse(abv_.getFieldsRootAfter()));
        assBl_.getVariablesRootBefore().putAllMap(AssignmentsUtil.assignBoolWhenFalse(abv_.getVariablesRootAfter()));
        id_.put(firstChild_, assBl_);
    }
    protected void assignWhenTrue(AssignedVariablesBlock _anEl) {
        AssBlock firstChild_ = getFirstChild();
        IdMap<AssBlock, AssignedVariables> id_ = _anEl.getFinalVariables();
        AssignedVariables parAss_ = id_.getVal(this);
        AssignedVariables assBl_ = firstChild_.buildNewAssignedVariable();
        AssignedBooleanVariables abv_ = (AssignedBooleanVariables) parAss_;
        assBl_.getFieldsRootBefore().putAllMap(AssignmentsUtil.assignBoolWhenTrue(abv_.getFieldsRootAfter()));
        assBl_.getVariablesRootBefore().putAllMap(AssignmentsUtil.assignBoolWhenTrue(abv_.getVariablesRootAfter()));
        id_.put(firstChild_, assBl_);
    }
    protected void processFinalFields(IdMap<AssBlock, AssignedVariables> _allDesc,
                                      AssignedVariables _root,
                                      StringMap<AssignmentBefore> _fields, AnalyzedPageEl _page) {
        AssignedVariables vars_;
        for (EntryCust<String,AssignmentBefore> e: _fields.entryList()) {
            if (e.getValue().isUnassignedBefore() || e.getValue().isAssignedBefore()) {
                continue;
            }
            String key_ = e.getKey();
            processFinalFields(this, false, _root, key_, _page);
            for (EntryCust<AssBlock, AssignedVariables> d: _allDesc.entryList()) {
                vars_ = d.getValue();
                AssBlock next_ = d.getKey();
                //next siblings of d
                processFinalFields(next_, true, vars_, key_, _page);
            }
        }
    }

    protected void processFinalVars(AssignedVariablesBlock _anEl,
                                    IdMap<AssBlock, AssignedVariables> _allDesc,
                                    AssignedVariables _root,
                                    StringMap<AssignmentBefore> _fields, AnalyzedPageEl _page) {
        AssignedVariables vars_;
        for (EntryCust<String,AssignmentBefore> e: _fields.entryList()) {
            if (e.getValue().isUnassignedBefore() || e.getValue().isAssignedBefore() || !_anEl.isFinalLocalVar(e.getKey())) {
                continue;
            }
            String key_ = e.getKey();
            processFinalVars(this, false, _root, key_, _page);
            for (EntryCust<AssBlock, AssignedVariables> d: _allDesc.entryList()) {
                vars_ = d.getValue();
                AssBlock next_ = d.getKey();
                //next siblings of d
                processFinalVars(next_,true, vars_, key_, _page);
            }
        }

    }

    protected static void processFinalVars(AssBlock _curBlock, boolean _all, AssignedVariables _vars, String _field, AnalyzedPageEl _page) {
        for (AssAffectationOperation f: _vars.getVariablesBefore(_curBlock,_all)) {
            AssOperationNode set_ = f.getSettableOp();
            if (!(set_ instanceof AssStdVariableOperation)) {
                continue;
            }
            AssStdVariableOperation cst_ = (AssStdVariableOperation) set_;
            String str_ = cst_.getVariableName();
            if (StringUtil.quickEq(str_, _field)) {
                cst_.setRelativeOffsetPossibleAnalyzable(_page);
                FoundErrorInterpret un_ = new FoundErrorInterpret();
                un_.setFile(_page.getCurrentFile());
                un_.setIndexFile(_page);
                un_.buildError(_page.getAnalysisMessages().getFinalField(),
                        str_);
                _page.addLocError(un_);
            }
        }
    }

    protected static void processFinalFields(AssBlock _curBlock, boolean _all, AssignedVariables _vars, String _field, AnalyzedPageEl _page) {
        for (AssAffectationOperation f: _vars.getFieldsBefore(_curBlock,_all)) {
            AssOperationNode set_ = f.getSettableOp();
            if (!(set_ instanceof AssSettableFieldOperation) || !((AssSettableFieldOperation) set_).getFieldMetaInfo().isFinalField()) {
                continue;
            }
            AssSettableFieldOperation cst_ = (AssSettableFieldOperation) set_;
            String cl_ = StringExpUtil.getIdFromAllTypes(_page.getGlobalClass());
            ClassField key_ = new ClassField(cl_,_field);
            if (cst_.matchFieldId(key_)) {
                cst_.setRelativeOffsetPossibleAnalyzable(_page);
                FoundErrorInterpret un_ = new FoundErrorInterpret();
                un_.setFile(_page.getCurrentFile());
                un_.setIndexFile(_page);
                un_.buildError(_page.getAnalysisMessages().getFinalField(),
                        _field);
                _page.addLocError(un_);
            }
        }
    }
    protected StringMap<AssignmentBefore> buildAssListFieldBeforeIncrPart(AssignedVariablesBlock _anEl) {
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
        CustList<StringMap<AssignmentBefore>> breakAss3_;
        breakAss3_ = new CustList<StringMap<AssignmentBefore>>();
        for (int j = 0; j < contLen_; j++) {
            AssContinueBlock br_ = continues_.get(j);
            AssignedVariables ass_ = id_.getVal(br_);
            StringMap<AssignmentBefore> vars_ = ass_.getFieldsRootBefore();
            breakAss3_.add(vars_);
        }
        if (last_.isCompleteNormallyGroup()) {
            AssignedVariables ass_ = id_.getVal(last_);
            StringMap<SimpleAssignment> v_ = ass_.getFieldsRoot();
            return beforeIncrPart(list_, v_, breakAss3_);
        }
        return beforeIncrPart(list_, new StringMap<SimpleAssignment>(), breakAss3_);
    }
    protected StringMap<AssignmentBefore> buildAssListLocVarBeforeIncrPart(AssignedVariablesBlock _anEl) {
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
        CustList<StringMap<AssignmentBefore>> breakAss2_;
        breakAss2_ = new CustList<StringMap<AssignmentBefore>>();
        for (int j = 0; j < contLen_; j++) {
            AssContinueBlock br_ = continues_.get(j);
            AssignedVariables ass_ = id_.getVal(br_);
            StringMap<AssignmentBefore> vars_ = ass_.getVariablesRootBefore();
            breakAss2_.add(vars_);
        }
        if (last_.isCompleteNormallyGroup()) {
            AssignedVariables ass_ = id_.getVal(last_);
            StringMap<SimpleAssignment> v_ = ass_.getVariablesRoot();
            varsList_=beforeIncrPart(list_, v_, breakAss2_);
        } else {
            varsList_=beforeIncrPart(list_, new StringMap<SimpleAssignment>(), breakAss2_);
        }

        return varsList_;
    }

    private static StringMap<AssignmentBefore> beforeIncrPart(StringMap<SimpleAssignment> _loop, StringMap<SimpleAssignment> _last,
                                                              CustList<StringMap<AssignmentBefore>> _continuable) {
        StringMap<AssignmentBefore> out_ = new StringMap<AssignmentBefore>();
        for (EntryCust<String, SimpleAssignment> e: _loop.entryList()) {
            extracted(_last, _continuable, out_, e);
        }
        return out_;
    }

    private static void extracted(StringMap<SimpleAssignment> _last, CustList<StringMap<AssignmentBefore>> _continuable, StringMap<AssignmentBefore> _out, EntryCust<String, SimpleAssignment> _e) {
        String key_ = _e.getKey();
        AssignmentBefore ab_ = new AssignmentBefore();
        boolean contAss_ = true;
        boolean contUnass_ = true;
        for (StringMap<AssignmentBefore> c: _continuable) {
            for (EntryCust<String,AssignmentBefore> f: c.entryList()) {
                if (!StringUtil.quickEq(f.getKey(),key_)) {
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
        extracted(_last, _out, _e, key_, ab_, contAss_, contUnass_);
    }

    private static void extracted(StringMap<SimpleAssignment> _last, StringMap<AssignmentBefore> _out, EntryCust<String, SimpleAssignment> _e, String _key, AssignmentBefore _ab, boolean _contAss, boolean _contUnass) {
        boolean contAss_ = _contAss;
        boolean contUnass_ = _contUnass;
        if (_last.contains(_key)) {
            if (!_last.getVal(_key).isAssignedAfter()) {
                contAss_ = false;
            }
            if (!_last.getVal(_key).isUnassignedAfter()) {
                contUnass_ = false;
            }
        }
        if (contAss_) {
            _ab.setAssignedBefore(true);
        }
        if (contUnass_) {
            _ab.setUnassignedBefore(true);
        }
        _out.put(_e.getKey(), _ab);
    }

    protected StringMap<SimpleAssignment> buildAssVariablesAfterIf(boolean _useBool,
                                                                   CustList<AssBlock> _blocks, AssignedVariablesBlock _anEl) {

        IdMap<AssBlock, AssignedVariables> id_;
        id_ = _anEl.getFinalVariables();
        AssignedVariables idStdIf_;
        idStdIf_ = id_.getVal(this);
        StringMap<SimpleAssignment> list_;
        list_ = idStdIf_.getVariablesRoot();
        StringMap<BooleanAssignment> bool4_;
        if (_useBool) {
            AssignedBooleanVariables idIf_;
            idIf_ = (AssignedBooleanVariables) id_.getVal(this);
            StringMap<BooleanAssignment> bools_;
            bools_ = idIf_.getVariablesRootAfter();
            bool4_ = bools_;
        } else {
            bool4_ = new StringMap<BooleanAssignment>();
        }
        CustList<StringMap<SimpleAssignment>> listBl1_;
        listBl1_ = new CustList<StringMap<SimpleAssignment>>();
        CustList<CustList<StringMap<AssignmentBefore>>> listBrs_;
        listBrs_ = new CustList<CustList<StringMap<AssignmentBefore>>>();
        for (AssBlock c: _blocks) {
            CustList<AssBreakBlock> breaks_ = ((AssBracedStack)c).getBreakables();
            CustList<StringMap<AssignmentBefore>> listBr_;
            listBr_ = new CustList<StringMap<AssignmentBefore>>();
            if (c.isCompleteNormally()) {
                StringMap<SimpleAssignment> map_;
                map_ = id_.getVal(c).getVariablesRoot();
                listBl1_.add(map_);
            } else {
                listBl1_.add(new StringMap<SimpleAssignment>());
            }
            for (AssBreakBlock b: breaks_) {
                StringMap<AssignmentBefore> map_;
                map_ = id_.getVal(b).getVariablesRootBefore();
                listBr_.add(map_);
            }
            listBrs_.add(listBr_);
        }
        return buildAssAfterIf(list_, bool4_, listBl1_, listBrs_);
    }

    protected StringMap<SimpleAssignment> buildAssFieldsAfterIf(boolean _useBool,
                                                                CustList<AssBlock> _blocks, AssignedVariablesBlock _anEl) {
        IdMap<AssBlock, AssignedVariables> id_;
        id_ = _anEl.getFinalVariables();
        AssignedVariables idStdIf_;
        idStdIf_ = id_.getVal(this);
        StringMap<SimpleAssignment> list_;
        list_ = idStdIf_.getFieldsRoot();
        StringMap<BooleanAssignment> bool5_;
        if (_useBool) {
            AssignedBooleanVariables idIf_;
            idIf_ = (AssignedBooleanVariables) id_.getVal(this);
            bool5_ = idIf_.getFieldsRootAfter();
        } else {
            bool5_ = new StringMap<BooleanAssignment>();
        }
        CustList<StringMap<SimpleAssignment>> listBl3_;
        listBl3_ = new CustList<StringMap<SimpleAssignment>>();
        CustList<CustList<StringMap<AssignmentBefore>>> listBrs_;
        listBrs_ = new CustList<CustList<StringMap<AssignmentBefore>>>();
        for (AssBlock c: _blocks) {
            CustList<AssBreakBlock> breaks_ = ((AssBracedStack)c).getBreakables();
            CustList<StringMap<AssignmentBefore>> listBr2_;
            listBr2_ = new CustList<StringMap<AssignmentBefore>>();
            if (c.isCompleteNormally()) {
                listBl3_.add(id_.getVal(c).getFieldsRoot());
            } else {
                listBl3_.add(new StringMap<SimpleAssignment>());
            }
            for (AssBreakBlock b: breaks_) {
                listBr2_.add(id_.getVal(b).getFieldsRootBefore());
            }
            listBrs_.add(listBr2_);
        }
        return buildAssAfterIf(list_, bool5_, listBl3_, listBrs_);
    }
    protected static StringMap<SimpleAssignment> buildAssAfterIf(StringMap<SimpleAssignment> _ifAfter,
                                                                 StringMap<BooleanAssignment> _afterCond,
                                                                 CustList<StringMap<SimpleAssignment>> _blocks,
                                                                 CustList<CustList<StringMap<AssignmentBefore>>> _breaks) {
        StringMap<SimpleAssignment> out_ = new StringMap<SimpleAssignment>();
        for (EntryCust<String, SimpleAssignment> e: _ifAfter.entryList()) {
            extracted(_afterCond, _blocks, _breaks, out_, e);
        }
        return out_;
    }

    private static void extracted(StringMap<BooleanAssignment> _afterCond, CustList<StringMap<SimpleAssignment>> _blocks, CustList<CustList<StringMap<AssignmentBefore>>> _breaks, StringMap<SimpleAssignment> _out, EntryCust<String, SimpleAssignment> _e) {
        int len_ = _blocks.size();
        String key_ = _e.getKey();
        boolean unassAfterAll_ = isUnassAfterAll(_afterCond, key_);
        boolean assAfterAll_ = isAssAfterAll(_afterCond, key_);
        for (int i = 0; i < len_; i++) {
            boolean assAfter_ = isAssAfter(_blocks, key_, i);
            boolean unassAfter10_ = isUnassAfter10(_blocks, key_, i);
            for (StringMap<AssignmentBefore> b: _breaks.get(i)) {
                if (!b.getVal(key_).isAssignedBefore()) {
                    assAfter_ = false;
                }
                if (!b.getVal(key_).isUnassignedBefore()) {
                    unassAfter10_ = false;
                }
            }
            if (!assAfter_) {
                assAfterAll_ = false;
            }
            if (!unassAfter10_) {
                unassAfterAll_ = false;
            }
        }
        _out.put(key_, Assignment.assignClassic(assAfterAll_, unassAfterAll_));
    }

    private static boolean isAssAfter(CustList<StringMap<SimpleAssignment>> _blocks, String _key, int _i) {
        boolean assAfter_ = true;
        for (EntryCust<String, SimpleAssignment> f: _blocks.get(_i).entryList()) {
            if (!StringUtil.quickEq(_key, f.getKey())) {
                continue;
            }
            if(!f.getValue().isAssignedAfter()) {
                assAfter_ = false;
            }
        }
        return assAfter_;
    }

    private static boolean isUnassAfter10(CustList<StringMap<SimpleAssignment>> _blocks, String _key, int _i) {
        boolean unassAfter10_ = true;
        for (EntryCust<String, SimpleAssignment> f: _blocks.get(_i).entryList()) {
            if (!StringUtil.quickEq(_key, f.getKey())) {
                continue;
            }
            if(!f.getValue().isUnassignedAfter()) {
                unassAfter10_ = false;
            }
        }
        return unassAfter10_;
    }

    private static boolean isAssAfterAll(StringMap<BooleanAssignment> _afterCond, String _key) {
        boolean assAfterAll_ = true;
        for (EntryCust<String, BooleanAssignment> f: _afterCond.entryList()) {
            if (!StringUtil.quickEq(_key, f.getKey())) {
                continue;
            }
            if(!f.getValue().isAssignedAfterWhenFalse()) {
                assAfterAll_ = false;
            }
        }
        return assAfterAll_;
    }

    private static boolean isUnassAfterAll(StringMap<BooleanAssignment> _afterCond, String _key) {
        boolean unassAfterAll_ = true;
        for (EntryCust<String, BooleanAssignment> f: _afterCond.entryList()) {
            if (!StringUtil.quickEq(_key, f.getKey())) {
                continue;
            }
            if(!f.getValue().isUnassignedAfterWhenFalse()) {
                unassAfterAll_ = false;
            }
        }
        return unassAfterAll_;
    }

    protected StringMap<SimpleAssignment> buildAssVariablesAfterSwitch(
            boolean _default,
            AssBlock _last,
            AssignedVariablesBlock _anEl) {
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
            AssignedVariablesBlock _anEl) {
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
            extracted(_default, _last, _breaks, out_, e);
        }
        return out_;
    }

    private static void extracted(boolean _default, StringMap<SimpleAssignment> _last, CustList<StringMap<AssignmentBefore>> _breaks, StringMap<SimpleAssignment> _out, EntryCust<String, Assignment> _e) {
        String key_ = _e.getKey();
        boolean assAfter_ = isAssAfter(_default, _last, _e, key_);
        boolean unassAfter_ = isUnassAfter(_default, _last, _e, key_);
        for (StringMap<AssignmentBefore> b: _breaks) {
            for (EntryCust<String, AssignmentBefore> f: b.entryList()) {
                if (!StringUtil.quickEq(f.getKey(),key_)) {
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
        _out.put(key_, Assignment.assignClassic(assAfter_, unassAfter_));
    }

    private static boolean isUnassAfter(boolean _default, StringMap<SimpleAssignment> _last, EntryCust<String, Assignment> _e, String _key) {
        boolean unassAfter_ = _default || _e.getValue().isUnassignedAfter();
        for (EntryCust<String, SimpleAssignment> f: _last.entryList()) {
            if (!StringUtil.quickEq(_key, f.getKey())) {
                continue;
            }
            if(!f.getValue().isUnassignedAfter()) {
                unassAfter_ = false;
            }
        }
        return unassAfter_;
    }

    private static boolean isAssAfter(boolean _default, StringMap<SimpleAssignment> _last, EntryCust<String, Assignment> _e, String _key) {
        boolean assAfter_ = _default || _e.getValue().isAssignedAfter();
        for (EntryCust<String, SimpleAssignment> f: _last.entryList()) {
            if (!StringUtil.quickEq(_key, f.getKey())) {
                continue;
            }
            if(!f.getValue().isAssignedAfter()) {
                assAfter_ = false;
            }
        }
        return assAfter_;
    }

    protected StringMap<SimpleAssignment> buildAssListFieldAfterLoop(AssignedVariablesBlock _anEl) {
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
    protected StringMap<SimpleAssignment> buildAssListLocVarAfterLoop(AssignedVariablesBlock _anEl) {
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
            extracted(_breakAss, out_, e);
        }
        return out_;
    }

    private static void extracted(CustList<StringMap<AssignmentBefore>> _breakAss, StringMap<SimpleAssignment> _out, EntryCust<String, BooleanAssignment> _e) {
        BooleanAssignment ba_ = _e.getValue();
        boolean ass_ = ba_.isAssignedAfterWhenFalse();
        boolean unass_ = ba_.isUnassignedAfterWhenFalse();
        String key_ = _e.getKey();
        for (StringMap<AssignmentBefore> b: _breakAss) {
            for (EntryCust<String, AssignmentBefore> f: b.entryList()) {
                if (!StringUtil.quickEq(f.getKey(),key_)) {
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
        _out.put(key_, Assignment.assignClassic(ass_, unass_));
    }

    protected StringMap<SimpleAssignment> buildAssVariablesAfterFinally(CustList<AssBlock> _blocks, AssignedVariablesBlock _anEl) {
        IdMap<AssBlock, AssignedVariables> id_;
        id_ = _anEl.getFinalVariables();
        StringMap<SimpleAssignment> list_;
        list_ = id_.getVal(this).getVariablesRoot();
        StringMap<SimpleAssignment> out_;
        out_ = new StringMap<SimpleAssignment>();
        CustList<StringMap<SimpleAssignment>> listBl8_;
        listBl8_ = new CustList<StringMap<SimpleAssignment>>();
        CustList<StringMap<SimpleAssignment>> listBlFin_;
        listBlFin_ = new CustList<StringMap<SimpleAssignment>>();
        CustList<CustList<StringMap<AssignmentBefore>>> listBrs9_;
        listBrs9_ = new CustList<CustList<StringMap<AssignmentBefore>>>();
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
                listBl8_.add(map_);
            } else {
                listBl8_.add(new StringMap<SimpleAssignment>());
            }
            for (AssBreakBlock b: breaks_) {
                StringMap<AssignmentBefore> map_;
                map_ = id_.getVal(b).getVariablesRootBefore();
                listBr_.add(map_);
            }
            listBrs9_.add(listBr_);
        }
        StringMap<SimpleAssignment> prev_ = buildAssAfterTry(list_, listBl8_, listBrs9_);
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

    protected StringMap<SimpleAssignment> buildAssFieldsAfterFinally(CustList<AssBlock> _blocks, AssignedVariablesBlock _anEl) {
        IdMap<AssBlock, AssignedVariables> id_;
        id_ = _anEl.getFinalVariables();
        StringMap<SimpleAssignment> list_;
        list_ = id_.getVal(this).getFieldsRoot();
        CustList<StringMap<SimpleAssignment>> listBl12_;
        listBl12_ = new CustList<StringMap<SimpleAssignment>>();
        CustList<StringMap<SimpleAssignment>> listBlFin_;
        listBlFin_ = new CustList<StringMap<SimpleAssignment>>();
        CustList<CustList<StringMap<AssignmentBefore>>> listBrs15_;
        listBrs15_ = new CustList<CustList<StringMap<AssignmentBefore>>>();
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
                listBl12_.add(id_.getVal(c).getFieldsRoot());
            } else {
                listBl12_.add(new StringMap<SimpleAssignment>());
            }
            for (AssBreakBlock b: breaks_) {
                listBr_.add(id_.getVal(b).getFieldsRootBefore());
            }
            listBrs15_.add(listBr_);
        }
        StringMap<SimpleAssignment> prev_ = buildAssAfterTry(list_, listBl12_, listBrs15_);
        StringMap<SimpleAssignment> out_;
        out_ = new StringMap<SimpleAssignment>();
        buildAssFinally(out_, fin_, prev_);
        return out_;
    }
    protected StringMap<SimpleAssignment> buildAssVariablesAfterTry(CustList<AssBlock> _blocks, AssignedVariablesBlock _anEl) {
        IdMap<AssBlock, AssignedVariables> id_;
        id_ = _anEl.getFinalVariables();
        StringMap<SimpleAssignment> list_;
        list_ = id_.getVal(this).getVariablesRoot();
        CustList<StringMap<SimpleAssignment>> listBl14_;
        listBl14_ = new CustList<StringMap<SimpleAssignment>>();
        CustList<CustList<StringMap<AssignmentBefore>>> listBrs_;
        listBrs_ = new CustList<CustList<StringMap<AssignmentBefore>>>();
        for (AssBlock c: _blocks) {
            CustList<AssBreakBlock> breaks_ = ((AssBracedStack)c).getBreakables();
            CustList<StringMap<AssignmentBefore>> listBr_;
            listBr_ = new CustList<StringMap<AssignmentBefore>>();
            if (c.isCompleteNormally()) {
                StringMap<SimpleAssignment> map_;
                map_ = id_.getVal(c).getVariablesRoot();
                listBl14_.add(map_);
            } else {
                listBl14_.add(new StringMap<SimpleAssignment>());
            }
            for (AssBreakBlock b: breaks_) {
                StringMap<AssignmentBefore> map_;
                map_ = id_.getVal(b).getVariablesRootBefore();
                listBr_.add(map_);
            }
            listBrs_.add(listBr_);
        }
        return buildAssAfterTry(list_, listBl14_, listBrs_);
    }

    protected StringMap<SimpleAssignment> buildAssFieldsAfterTry(CustList<AssBlock> _blocks, AssignedVariablesBlock _anEl) {
        IdMap<AssBlock, AssignedVariables> id_;
        id_ = _anEl.getFinalVariables();
        StringMap<SimpleAssignment> list_;
        list_ = id_.getVal(this).getFieldsRoot();
        CustList<StringMap<SimpleAssignment>> listBl16_;
        listBl16_ = new CustList<StringMap<SimpleAssignment>>();
        CustList<CustList<StringMap<AssignmentBefore>>> listBrs_;
        listBrs_ = new CustList<CustList<StringMap<AssignmentBefore>>>();
        for (AssBlock c: _blocks) {
            CustList<AssBreakBlock> breaks_ = ((AssBracedStack)c).getBreakables();
            CustList<StringMap<AssignmentBefore>> listBr_;
            listBr_ = new CustList<StringMap<AssignmentBefore>>();
            if (c.isCompleteNormally()) {
                listBl16_.add(id_.getVal(c).getFieldsRoot());
            } else {
                listBl16_.add(new StringMap<SimpleAssignment>());
            }
            for (AssBreakBlock b: breaks_) {
                listBr_.add(id_.getVal(b).getFieldsRootBefore());
            }
            listBrs_.add(listBr_);
        }
        return buildAssAfterTry(list_, listBl16_, listBrs_);
    }
    protected static StringMap<SimpleAssignment> buildAssAfterTry(StringMap<SimpleAssignment> _tryAfter,
                                                                  CustList<StringMap<SimpleAssignment>> _blocks,
                                                                  CustList<CustList<StringMap<AssignmentBefore>>> _breaks) {
        StringMap<SimpleAssignment> out_ = new StringMap<SimpleAssignment>();
        for (EntryCust<String, SimpleAssignment> e: _tryAfter.entryList()) {
            extracted(_blocks, _breaks, out_, e);
        }
        return out_;
    }

    private static void extracted(CustList<StringMap<SimpleAssignment>> _blocks, CustList<CustList<StringMap<AssignmentBefore>>> _breaks, StringMap<SimpleAssignment> _out, EntryCust<String, SimpleAssignment> _e) {
        int len_ = _blocks.size();
        String key_ = _e.getKey();
        boolean assAfterAll_ = true;
        boolean unassAfterAll_ = true;
        for (int i = 0; i < len_; i++) {
            boolean unassAfter_ = isUnassAfter2(_blocks, _breaks, key_, i);
            boolean assAfter11_ = isAssAfter11Sec(_blocks, _breaks, key_, i);
            if (!assAfter11_) {
                assAfterAll_ = false;
            }
            if (!unassAfter_) {
                unassAfterAll_ = false;
            }
        }
        _out.put(key_, Assignment.assignClassic(assAfterAll_, unassAfterAll_));
    }

    private static boolean isAssAfter11Sec(CustList<StringMap<SimpleAssignment>> _blocks, CustList<CustList<StringMap<AssignmentBefore>>> _breaks, String _key, int _i) {
        boolean assAfter11_ = isAssAfter11(_blocks, _key, _i);
        for (StringMap<AssignmentBefore> b: _breaks.get(_i)) {
            for (EntryCust<String, AssignmentBefore> f: b.entryList()) {
                if (!StringUtil.quickEq(_key, f.getKey())) {
                    continue;
                }
                if (!f.getValue().isAssignedBefore()) {
                    assAfter11_ = false;
                }
            }
        }
        return assAfter11_;
    }

    private static boolean isUnassAfter2(CustList<StringMap<SimpleAssignment>> _blocks, CustList<CustList<StringMap<AssignmentBefore>>> _breaks, String _key, int _i) {
        boolean unassAfter_ = isUnassAfter(_blocks, _key, _i);
        for (StringMap<AssignmentBefore> b: _breaks.get(_i)) {
            for (EntryCust<String, AssignmentBefore> f: b.entryList()) {
                if (!StringUtil.quickEq(_key, f.getKey())) {
                    continue;
                }
                if (!f.getValue().isUnassignedBefore()) {
                    unassAfter_ = false;
                }
            }
        }
        return unassAfter_;
    }

    private static boolean isUnassAfter(CustList<StringMap<SimpleAssignment>> _blocks, String _key, int _i) {
        boolean unassAfter_ = true;
        for (EntryCust<String, SimpleAssignment> f: _blocks.get(_i).entryList()) {
            if (!StringUtil.quickEq(_key, f.getKey())) {
                continue;
            }
            if(!f.getValue().isUnassignedAfter()) {
                unassAfter_ = false;
            }
        }
        return unassAfter_;
    }

    private static boolean isAssAfter11(CustList<StringMap<SimpleAssignment>> _blocks, String _key, int _i) {
        boolean assAfter11_ = true;
        for (EntryCust<String, SimpleAssignment> f: _blocks.get(_i).entryList()) {
            if (!StringUtil.quickEq(_key, f.getKey())) {
                continue;
            }
            if(!f.getValue().isAssignedAfter()) {
                assAfter11_ = false;
            }
        }
        return assAfter11_;
    }

    protected static StringMap<AssignmentBefore> buildAssFieldsBefNextCatchFinally(AssTryEval _try, AssignedVariablesBlock _anEl,
                                                                                   CustList<AssCatchEval> _catchs) {
        CustList<AssAbruptBlock> abr_ = _try.getAbruptTry(_anEl);
        CustList<StringMap<Assignment>> throws_ = new CustList<StringMap<Assignment>>();
        CustList<StringMap<AssignmentBefore>> others_ = new CustList<StringMap<AssignmentBefore>>();
        CustList<StringMap<SimpleAssignment>> catchs_ = new CustList<StringMap<SimpleAssignment>>();
        IdMap<AssBlock, AssignedVariables> id_;
        id_ = _anEl.getFinalVariables();
        for (AssAbruptBlock a: abr_) {
            extracted(a, others_, id_, throws_);
        }
        for (AssCatchEval c: _catchs) {
            for (AssAbruptBlock a: c.getAbruptTry(_anEl)) {
                extracted(a, others_, id_, throws_);
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

    private static void extracted(AssAbruptBlock _a, CustList<StringMap<AssignmentBefore>> _o, IdMap<AssBlock, AssignedVariables> _id, CustList<StringMap<Assignment>> _t) {
        if (_a instanceof AssReturnMethod) {
            if (((AssReturnMethod) _a).isEmpty()) {
                _o.add(_id.getVal(_a).getFieldsRootBefore());
            } else {
                _t.add(_id.getVal(_a).getLastFieldsOrEmpty());
            }
        } else if (_a instanceof AssThrowing) {
            _t.add(_id.getVal(_a).getLastFieldsOrEmpty());
        } else {
            _o.add(_id.getVal(_a).getFieldsRootBefore());
        }
    }

    protected static StringMap<AssignmentBefore> buildAssVarsBefNextCatchFinally(AssTryEval _try, AssignedVariablesBlock _anEl,
                                                                                 CustList<AssCatchEval> _catchs) {
        CustList<AssAbruptBlock> abr_ = _try.getAbruptTry(_anEl);
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
        CustList<StringMap<Assignment>> throws_ = new CustList<StringMap<Assignment>>();
        CustList<StringMap<AssignmentBefore>> others_ = new CustList<StringMap<AssignmentBefore>>();
        CustList<StringMap<SimpleAssignment>> catchs_ = new CustList<StringMap<SimpleAssignment>>();
        for (AssAbruptBlock a: abr_) {
            extracted(a, id_, others_, throws_);
        }
        for (AssCatchEval c: _catchs) {
            for (AssAbruptBlock a: c.getAbruptTry(_anEl)) {
                extracted(a, id_, others_, throws_);
            }
        }
        for (AssCatchEval c: _catchs) {
            if (c.isCompleteNormally()) {
                StringMap<SimpleAssignment> li_ = id_.getVal(c).getVariablesRoot();
                catchs_.add(li_);
            }
        }
        return buildAssBefNextCatchFinally(tryAfter_, tryBefores_, throws_, others_,catchs_);
    }

    private static void extracted(AssAbruptBlock _a, IdMap<AssBlock, AssignedVariables> _id, CustList<StringMap<AssignmentBefore>> _o, CustList<StringMap<Assignment>> _t) {
        if (_a instanceof AssReturnMethod) {
            if (((AssReturnMethod) _a).isEmpty()) {
                StringMap<AssignmentBefore> li_ = _id.getVal(_a).getVariablesRootBefore();
                _o.add(li_);
            } else {
                StringMap<Assignment> li_ = _id.getVal(_a).getLastVariablesOrEmpty();
                _t.add(li_);
            }
        } else if (_a instanceof AssThrowing) {
            StringMap<Assignment> li_ = _id.getVal(_a).getLastVariablesOrEmpty();
            _t.add(li_);
        } else {
            StringMap<AssignmentBefore> li_ = _id.getVal(_a).getVariablesRootBefore();
            _o.add(li_);
        }
    }

    protected static StringMap<AssignmentBefore> buildAssBefNextCatchFinally(StringMap<SimpleAssignment> _tryAfter,
                                                                             StringMap<AssignmentBefore> _tryBefore,CustList<StringMap<Assignment>> _throws,
                                                                             CustList<StringMap<AssignmentBefore>> _others, CustList<StringMap<SimpleAssignment>> _catchs) {
        StringMap<AssignmentBefore> out_ = new StringMap<AssignmentBefore>();
        for (EntryCust<String, AssignmentBefore> e: _tryBefore.entryList()) {
            extracted(_tryAfter, _throws, _others, _catchs, out_, e);
        }
        return out_;
    }

    private static void extracted(StringMap<SimpleAssignment> _tryAfter, CustList<StringMap<Assignment>> _throws, CustList<StringMap<AssignmentBefore>> _others, CustList<StringMap<SimpleAssignment>> _catchs, StringMap<AssignmentBefore> _out, EntryCust<String, AssignmentBefore> _e) {
        AssignmentBefore ab_ = new AssignmentBefore();
        String key_ = _e.getKey();
        if (_e.getValue().isAssignedBefore()) {
            ab_.setAssignedBefore(true);
        }
        boolean unass_ = isUnass2(_tryAfter, _throws, _others, key_);
        for (StringMap<SimpleAssignment> c: _catchs) {
            for (EntryCust<String,SimpleAssignment> f: c.entryList()) {
                if (!StringUtil.quickEq(f.getKey(),key_)) {
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
        _out.put(_e.getKey(), ab_);
    }

    private static boolean isUnass2(StringMap<SimpleAssignment> _tryAfter, CustList<StringMap<Assignment>> _throws, CustList<StringMap<AssignmentBefore>> _others, String _key) {
        boolean unass_ = isUnass(_tryAfter, _throws, _key);
        for (StringMap<AssignmentBefore> t: _others) {
            for (EntryCust<String,AssignmentBefore> f: t.entryList()) {
                if (!StringUtil.quickEq(f.getKey(), _key)) {
                    continue;
                }
                if (!f.getValue().isUnassignedBefore()) {
                    unass_ = false;
                }
            }
        }
        return unass_;
    }

    private static boolean isUnass(StringMap<SimpleAssignment> _tryAfter, CustList<StringMap<Assignment>> _throws, String _key) {
        boolean unass_ = !_tryAfter.contains(_key) || _tryAfter.getVal(_key).isUnassignedAfter();
        for (StringMap<Assignment> t: _throws) {
            for (EntryCust<String,Assignment> f: t.entryList()) {
                if (!StringUtil.quickEq(f.getKey(), _key)) {
                    continue;
                }
                if (!f.getValue().isUnassignedAfter()) {
                    unass_ = false;
                }
            }
        }
        return unass_;
    }

    protected CustList<AssAbruptBlock> getAbruptTry(AssignedVariablesBlock _anEl) {
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
            extracted(stoppers_, f);
        }
        return stoppers_;
    }

    private void extracted(CustList<AssAbruptBlock> _stoppers, AssBlock _f) {
        if (!(_f instanceof AssAbruptBlock)) {
            return;
        }
        if (_f instanceof AssContinueBlock) {
            AssBracedBlock d_ = ((AssContinueBlock) _f).directAsc();
            IdList<AssBracedBlock> pars_ = _f.getParentsIntervalUntil(d_);
            if (!pars_.containsObj(this)) {
                return;
            }
        }
        if (_f instanceof AssBreakBlock) {
            AssBracedBlock d_ = ((AssBreakBlock) _f).directAsc();
            IdList<AssBracedBlock> pars_ = _f.getParentsIntervalUntil(d_);
            if (!pars_.containsObj(this) && d_ != this) {
                return;
            }
        }
        _stoppers.add((AssAbruptBlock) _f);
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
        while (d_ != null) {
            AssBlock f_ = d_.getFirstChild();
            l_.add(d_);
            if (f_ != null) {
                d_ = f_;
                continue;
            }
            while (d_ != null) {
                AssBlock n_ = d_.getNextSibling();
                if (n_ != null) {
                    d_ = n_;
                    break;
                }
                AssBracedBlock par_ = d_.getParent();
                if (par_ == r_) {
                    d_ = null;
                } else {
                    d_ = par_;
                }
            }
        }
        return l_;
    }
}
