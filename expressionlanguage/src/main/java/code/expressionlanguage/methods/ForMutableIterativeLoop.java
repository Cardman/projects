package code.expressionlanguage.methods;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.AnalyzedPageEl;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.ElUtil;
import code.expressionlanguage.Mapping;
import code.expressionlanguage.OffsetBooleanInfo;
import code.expressionlanguage.OffsetStringInfo;
import code.expressionlanguage.OffsetsBlock;
import code.expressionlanguage.PrimitiveTypeUtil;
import code.expressionlanguage.ReadWrite;
import code.expressionlanguage.calls.AbstractPageEl;
import code.expressionlanguage.methods.util.BadImplicitCast;
import code.expressionlanguage.methods.util.EmptyTagName;
import code.expressionlanguage.methods.util.UnexpectedTypeError;
import code.expressionlanguage.opers.Calculation;
import code.expressionlanguage.opers.ExpressionLanguage;
import code.expressionlanguage.opers.OperationNode;
import code.expressionlanguage.opers.util.AssignedBooleanVariables;
import code.expressionlanguage.opers.util.AssignedVariables;
import code.expressionlanguage.opers.util.Assignment;
import code.expressionlanguage.opers.util.AssignmentBefore;
import code.expressionlanguage.opers.util.BooleanAssignment;
import code.expressionlanguage.opers.util.SimpleAssignment;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.stacks.LoopBlockStack;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.variables.LoopVariable;
import code.util.CustList;
import code.util.EntryCust;
import code.util.IdMap;
import code.util.StringList;
import code.util.StringMap;

public final class ForMutableIterativeLoop extends BracedStack implements
        ForLoop, InitVariable {

    private String label;
    private int labelOffset;

    private final String className;
    private int classNameOffset;

    private String importedClassName = EMPTY_STRING;

    private final String classIndexName;
    private int classIndexNameOffset;

    private final StringList variableNames = new StringList();

    private boolean finalVariable;
    private int finalOffset;

    private final String init;
    private int initOffset;

    private final String expression;
    private int expressionOffset;

    private final String step;
    private int stepOffset;

    private CustList<OperationNode> opInit;

    private CustList<OperationNode> opExp;

    private CustList<OperationNode> opStep;

    public ForMutableIterativeLoop(ContextEl _importingPage,
            BracedBlock _m, OffsetBooleanInfo _final,
            OffsetStringInfo _className,
            OffsetStringInfo _from,
            OffsetStringInfo _to, OffsetStringInfo _step, OffsetStringInfo _classIndex, OffsetStringInfo _label, OffsetsBlock _offset) {
        super(_importingPage, _m, _offset);
        className = _className.getInfo();
        classNameOffset = _className.getOffset();
        init = _from.getInfo();
        initOffset = _from.getOffset();
        expression = _to.getInfo();
        expressionOffset = _to.getOffset();
        step = _step.getInfo();
        stepOffset = _step.getOffset();
        String classIndex_ = _classIndex.getInfo();
        if (classIndex_.isEmpty()) {
            classIndex_ = _importingPage.getStandards().getAliasPrimLong();
        }
        classIndexName = classIndex_;
        classIndexNameOffset = _classIndex.getOffset();
        label = _label.getInfo();
        labelOffset = _label.getOffset();
        finalVariable = _final.isInfo();
        finalOffset = _final.getOffset();
    }

    public String getLabel() {
        return label;
    }

    @Override
    public String getRealLabel() {
        return label;
    }

    public int getLabelOffset() {
        return labelOffset;
    }

    public int getClassNameOffset() {
        return classNameOffset;
    }

    public int getClassIndexNameOffset() {
        return classIndexNameOffset;
    }

    public int getInitOffset() {
        return initOffset;
    }

    public int getExpressionOffset() {
        return expressionOffset;
    }

    public int getStepOffset() {
        return stepOffset;
    }

    @Override
    public String getClassIndexName() {
        return classIndexName;
    }

    @Override
    public String getClassName() {
        return className;
    }

    public String getInit() {
        return init;
    }

    public String getExpression() {
        return expression;
    }

    public String getStep() {
        return step;
    }

    public boolean isFinalVariable() {
        return finalVariable;
    }

    public int getFinalOffset() {
        return finalOffset;
    }

    public void setImportedClassName(String _importedClassName) {
        importedClassName = _importedClassName;
    }
    public CustList<OperationNode> getOpInit() {
        return opInit;
    }

    public CustList<OperationNode> getOpExp() {
        return opExp;
    }

    public CustList<OperationNode> getOpStep() {
        return opStep;
    }

    public ExpressionLanguage getInitEl() {
        return new ExpressionLanguage(opInit);
    }

    public ExpressionLanguage getExpressionEl() {
        return new ExpressionLanguage(opExp);
    }

    public ExpressionLanguage getStepEl() {
        return new ExpressionLanguage(opStep);
    }
    @Override
    public void defaultAssignmentBefore(Analyzable _an, OperationNode _root) {
        AssignedVariables vars_ = _an.getAssignedVariables().getFinalVariables().getVal(this);
        StringMap<AssignmentBefore> fields_;
        CustList<StringMap<AssignmentBefore>> variables_;
        fields_ = new StringMap<AssignmentBefore>();
        variables_ = new CustList<StringMap<AssignmentBefore>>();
        CustList<StringMap<AssignmentBefore>> mutable_;
        mutable_ = new CustList<StringMap<AssignmentBefore>>();
        if (_an.getForLoopPartState() == ForLoopPart.INIT) {
            for (EntryCust<String,AssignmentBefore> e: vars_.getFieldsRootBefore().entryList()) {
                fields_.put(e.getKey(), e.getValue().copy());
            }
            for (StringMap<AssignmentBefore> s: vars_.getVariablesRootBefore()) {
                StringMap<AssignmentBefore> sm_ = new StringMap<AssignmentBefore>();
                for (EntryCust<String,AssignmentBefore> e: s.entryList()) {
                    sm_.put(e.getKey(), e.getValue().copy());
                }
                variables_.add(sm_);
            }
            for (StringMap<AssignmentBefore> s: vars_.getMutableLoopRootBefore()) {
                StringMap<AssignmentBefore> sm_ = new StringMap<AssignmentBefore>();
                for (EntryCust<String,AssignmentBefore> e: s.entryList()) {
                    sm_.put(e.getKey(), e.getValue().copy());
                }
                mutable_.add(sm_);
            }
        } else if (_an.getForLoopPartState() == ForLoopPart.CONDITION) {
            if (opInit.isEmpty()) {
                for (EntryCust<String,AssignmentBefore> e: vars_.getFieldsRootBefore().entryList()) {
                    fields_.put(e.getKey(), e.getValue().copy());
                }
                for (StringMap<AssignmentBefore> s: vars_.getVariablesRootBefore()) {
                    StringMap<AssignmentBefore> sm_ = new StringMap<AssignmentBefore>();
                    for (EntryCust<String,AssignmentBefore> e: s.entryList()) {
                        sm_.put(e.getKey(), e.getValue().copy());
                    }
                    variables_.add(sm_);
                }
                for (StringMap<AssignmentBefore> s: vars_.getMutableLoopRootBefore()) {
                    StringMap<AssignmentBefore> sm_ = new StringMap<AssignmentBefore>();
                    for (EntryCust<String,AssignmentBefore> e: s.entryList()) {
                        sm_.put(e.getKey(), e.getValue().copy());
                    }
                    mutable_.add(sm_);
                }
            } else {
                for (EntryCust<String,SimpleAssignment> e: vars_.getFieldsRoot().entryList()) {
                    fields_.put(e.getKey(), e.getValue().assignBefore());
                }
                for (StringMap<SimpleAssignment> s: vars_.getVariablesRoot()) {
                    StringMap<AssignmentBefore> sm_ = new StringMap<AssignmentBefore>();
                    for (EntryCust<String, SimpleAssignment> e: s.entryList()) {
                        sm_.put(e.getKey(), e.getValue().assignBefore());
                    }
                    variables_.add(sm_);
                }
                for (StringMap<SimpleAssignment> s: vars_.getMutableLoopRoot()) {
                    StringMap<AssignmentBefore> sm_ = new StringMap<AssignmentBefore>();
                    for (EntryCust<String, SimpleAssignment> e: s.entryList()) {
                        sm_.put(e.getKey(), e.getValue().assignBefore());
                    }
                    mutable_.add(sm_);
                }
            }
        } else {
            fields_ = buildAssListFieldBeforeIncrPart(_an, _an.getAnalysisAss());
            variables_ = buildAssListLocVarBeforeIncrPart(_an, _an.getAnalysisAss());
            mutable_ = buildAssListMutableLoopBeforeIncrPart(_an, _an.getAnalysisAss());
        }
        vars_.getFieldsBefore().put(_root, fields_);
        vars_.getVariablesBefore().put(_root, variables_);
        vars_.getMutableLoopBefore().put(_root, mutable_);
    }

    @Override
    public void setAssignmentBeforeChild(Analyzable _an, AnalyzingEl _anEl) {
        assignWhenTrue(_an, _anEl);
    }
    @Override
    protected AssignedBooleanVariables buildNewAssignedVariable() {
        return new AssignedBooleanVariables();
    }
    @Override
    public void buildExpressionLanguage(ContextEl _cont) {
        FunctionBlock f_ = getFunction();
        AnalyzedPageEl page_ = _cont.getAnalyzing();
        page_.setGlobalOffset(classIndexNameOffset);
        page_.setOffset(0);
        if (!PrimitiveTypeUtil.isPrimitiveOrWrapper(classIndexName, _cont)) {
            Mapping mapping_ = new Mapping();
            mapping_.setArg(classIndexName);
            mapping_.setParam(_cont.getStandards().getAliasLong());
            BadImplicitCast cast_ = new BadImplicitCast();
            cast_.setMapping(mapping_);
            cast_.setFileName(getFile().getFileName());
            cast_.setRc(getRowCol(0, classIndexNameOffset));
            _cont.getClasses().addError(cast_);
        }
        page_.setGlobalOffset(classNameOffset);
        page_.setOffset(0);
        if (!className.isEmpty()) {
            KeyWords keyWords_ = _cont.getKeyWords();
            String keyWordVar_ = keyWords_.getKeyWordVar();
            if (StringList.quickEq(className.trim(), keyWordVar_)) {
                importedClassName = keyWordVar_;
            } else {
                importedClassName = _cont.resolveCorrectType(className);
            }
            _cont.setMerged(true);
            _cont.setFinalVariable(finalVariable);
            _cont.setCurrentVarSetting(importedClassName);
        } else {
            _cont.setMerged(false);
        }
        
        _cont.getVariablesNames().clear();
        page_.setGlobalOffset(initOffset);
        page_.setOffset(0);
        _cont.setForLoopPartState(ForLoopPart.INIT);
        if (init.trim().isEmpty()) {
            opInit = new CustList<OperationNode>();
        } else {
            opInit = ElUtil.getAnalyzedOperations(init, _cont, Calculation.staticCalculation(f_.isStaticContext()));
        }
        if (_cont.isMerged()) {
            StringList vars_ = _cont.getVariablesNames();
            getVariableNames().addAllElts(vars_);
        }
        _cont.setMerged(false);
        page_.setGlobalOffset(expressionOffset);
        page_.setOffset(0);
        _cont.setForLoopPartState(ForLoopPart.CONDITION);
        if (expression.trim().isEmpty()) {
            opExp = new CustList<OperationNode>();
        } else {
            opExp = ElUtil.getAnalyzedOperations(expression, _cont, Calculation.staticCalculation(f_.isStaticContext()));
        }
        if (!opExp.isEmpty()) {
            OperationNode elCondition_ = opExp.last();
            LgNames stds_ = _cont.getStandards();
            if (!elCondition_.getResultClass().isBoolType(_cont)) {
                UnexpectedTypeError un_ = new UnexpectedTypeError();
                un_.setFileName(getFile().getFileName());
                un_.setRc(getRowCol(0, expressionOffset));
                un_.setType(opExp.last().getResultClass());
                _cont.getClasses().addError(un_);
            }
            elCondition_.getResultClass().setUnwrapObject(stds_.getAliasPrimBoolean());
            buildConditions(_cont);
        } else {
            AssignedBooleanVariables res_ = (AssignedBooleanVariables) _cont.getAnalyzing().getAssignedVariables().getFinalVariables().getVal(this);
            if (opInit.isEmpty()) {
                for (EntryCust<String,AssignmentBefore> e: res_.getFieldsRootBefore().entryList()) {
                    BooleanAssignment ba_ = new BooleanAssignment();
                    ba_.setAssignedAfterWhenFalse(true);
                    ba_.setUnassignedAfterWhenFalse(true);
                    ba_.setAssignedAfterWhenTrue(e.getValue().isAssignedBefore());
                    ba_.setUnassignedAfterWhenTrue(e.getValue().isUnassignedBefore());
                    res_.getFieldsRootAfter().put(e.getKey(), ba_);
                }
                for (StringMap<AssignmentBefore> s: res_.getVariablesRootBefore()) {
                    StringMap<BooleanAssignment> sm_;
                    sm_ = new StringMap<BooleanAssignment>();
                    for (EntryCust<String, AssignmentBefore> e: s.entryList()) {
                        BooleanAssignment ba_ = new BooleanAssignment();
                        ba_.setAssignedAfterWhenFalse(true);
                        ba_.setUnassignedAfterWhenFalse(true);
                        ba_.setAssignedAfterWhenTrue(e.getValue().isAssignedBefore());
                        ba_.setUnassignedAfterWhenTrue(e.getValue().isUnassignedBefore());
                        sm_.put(e.getKey(), ba_);
                    }
                    res_.getVariablesRootAfter().add(sm_);
                }
                for (StringMap<AssignmentBefore> s: res_.getMutableLoopRootBefore()) {
                    StringMap<BooleanAssignment> sm_;
                    sm_ = new StringMap<BooleanAssignment>();
                    for (EntryCust<String, AssignmentBefore> e: s.entryList()) {
                        BooleanAssignment ba_ = new BooleanAssignment();
                        ba_.setAssignedAfterWhenFalse(true);
                        ba_.setUnassignedAfterWhenFalse(true);
                        ba_.setAssignedAfterWhenTrue(e.getValue().isAssignedBefore());
                        ba_.setUnassignedAfterWhenTrue(e.getValue().isUnassignedBefore());
                        sm_.put(e.getKey(), ba_);
                    }
                    res_.getMutableLoopRootAfter().add(sm_);
                }
            } else {
                OperationNode prev_ = opInit.last();
                for (EntryCust<String,Assignment> e: res_.getFields().getVal(prev_).entryList()) {
                    BooleanAssignment ba_ = new BooleanAssignment();
                    ba_.setAssignedAfterWhenFalse(true);
                    ba_.setUnassignedAfterWhenFalse(true);
                    ba_.setAssignedAfterWhenTrue(e.getValue().isAssignedAfter());
                    ba_.setUnassignedAfterWhenTrue(e.getValue().isUnassignedAfter());
                    res_.getFieldsRootAfter().put(e.getKey(), ba_);
                }
                for (StringMap<Assignment> s: res_.getVariables().getVal(prev_)) {
                    StringMap<BooleanAssignment> sm_;
                    sm_ = new StringMap<BooleanAssignment>();
                    for (EntryCust<String, Assignment> e: s.entryList()) {
                        BooleanAssignment ba_ = new BooleanAssignment();
                        ba_.setAssignedAfterWhenFalse(true);
                        ba_.setUnassignedAfterWhenFalse(true);
                        ba_.setAssignedAfterWhenTrue(e.getValue().isAssignedAfter());
                        ba_.setUnassignedAfterWhenTrue(e.getValue().isUnassignedAfter());
                        sm_.put(e.getKey(), ba_);
                    }
                    res_.getVariablesRootAfter().add(sm_);
                }
                for (StringMap<Assignment> s: res_.getMutableLoop().getVal(prev_)) {
                    StringMap<BooleanAssignment> sm_;
                    sm_ = new StringMap<BooleanAssignment>();
                    for (EntryCust<String, Assignment> e: s.entryList()) {
                        BooleanAssignment ba_ = new BooleanAssignment();
                        ba_.setAssignedAfterWhenFalse(true);
                        ba_.setUnassignedAfterWhenFalse(true);
                        ba_.setAssignedAfterWhenTrue(e.getValue().isAssignedAfter());
                        ba_.setUnassignedAfterWhenTrue(e.getValue().isUnassignedAfter());
                        sm_.put(e.getKey(), ba_);
                    }
                    res_.getMutableLoopRootAfter().add(sm_);
                }
            }
        }
    }
    @Override
    public StringList getVariableNames() {
        return variableNames;
    }
    @Override
    protected void buildConditions(ContextEl _cont) {
        AssignedBooleanVariables res_ = (AssignedBooleanVariables) _cont.getAnalyzing().getAssignedVariables().getFinalVariables().getVal(this);
        for (EntryCust<String,Assignment> e: res_.getFields().getVal(opExp.last()).entryList()) {
            BooleanAssignment ba_ = e.getValue().toBoolAssign().copy();
            res_.getFieldsRootAfter().put(e.getKey(), ba_);
        }
        for (StringMap<Assignment> s: res_.getVariables().getVal(opExp.last())) {
            StringMap<BooleanAssignment> sm_;
            sm_ = new StringMap<BooleanAssignment>();
            for (EntryCust<String,Assignment> e: s.entryList()) {
                BooleanAssignment ba_ = e.getValue().toBoolAssign().copy();
                sm_.put(e.getKey(), ba_);
            }
            res_.getVariablesRootAfter().add(sm_);
        }
        for (StringMap<Assignment> s: res_.getMutableLoop().getVal(opExp.last())) {
            StringMap<BooleanAssignment> sm_;
            sm_ = new StringMap<BooleanAssignment>();
            for (EntryCust<String,Assignment> e: s.entryList()) {
                BooleanAssignment ba_ = e.getValue().toBoolAssign().copy();
                sm_.put(e.getKey(), ba_);
            }
            res_.getMutableLoopRootAfter().add(sm_);
        }
    }
    @Override
    public void defaultAssignmentAfter(Analyzable _an, OperationNode _root) {
        AssignedVariables vars_ = _an.getAssignedVariables().getFinalVariables().getVal(this);
        StringMap<Assignment> res_ = vars_.getFields().getVal(_root);
        for (EntryCust<String,Assignment> e: res_.entryList()) {
            vars_.getFieldsRoot().put(e.getKey(), e.getValue().assignClassic());
        }
        CustList<StringMap<Assignment>> varsRes_;
        varsRes_ = vars_.getVariables().getVal(_root);
        vars_.getVariablesRoot().clear();
        for (StringMap<Assignment> s: varsRes_) {
            StringMap<SimpleAssignment> sm_ = new StringMap<SimpleAssignment>();
            for (EntryCust<String, Assignment> e: s.entryList()) {
                sm_.put(e.getKey(), e.getValue().assignClassic());
            }
            vars_.getVariablesRoot().add(sm_);
        }
        CustList<StringMap<Assignment>> mutableRes_;
        mutableRes_ = vars_.getMutableLoop().getVal(_root);
        vars_.getMutableLoopRoot().clear();
        for (StringMap<Assignment> s: mutableRes_) {
            StringMap<SimpleAssignment> sm_ = new StringMap<SimpleAssignment>();
            for (EntryCust<String, Assignment> e: s.entryList()) {
                sm_.put(e.getKey(), e.getValue().assignClassic());
            }
            vars_.getMutableLoopRoot().add(sm_);
        }
    }
    @Override
    public void setAssignmentAfter(Analyzable _an, AnalyzingEl _anEl) {
        _an.setMerged(false);
        Block firstChild_ = getFirstChild();
        FunctionBlock f_ = getFunction();
        IdMap<Block, AssignedVariables> allDesc_ = new IdMap<Block, AssignedVariables>();
        boolean add_ = false;
        IdMap<Block, AssignedVariables> id_;
        id_ = _an.getAssignedVariables().getFinalVariables();
        AssignedBooleanVariables varsWhile_ = null;
        for (EntryCust<Block, AssignedVariables> e: id_.entryList()) {
            if (e.getKey() == this) {
                add_ = true;
                varsWhile_ = (AssignedBooleanVariables) e.getValue();
            } else if (add_) {
                allDesc_.put(e.getKey(), e.getValue());
            }
        }
        if (firstChild_ == null) {
            super.setAssignmentAfter(_an, _anEl);
            EmptyTagName un_ = new EmptyTagName();
            un_.setFileName(getFile().getFileName());
            un_.setRc(getRowCol(0, getOffset().getOffsetTrim()));
            _an.getClasses().addError(un_);
            return;
        }
        AnalyzedPageEl page_ = _an.getAnalyzing();
        page_.setGlobalOffset(stepOffset);
        page_.setOffset(0);
        _an.setForLoopPartState(ForLoopPart.STEP);
        _an.setMerged(true);
        _an.getLocalVariables().last().clear();
        if (step.trim().isEmpty()) {
            opStep = new CustList<OperationNode>();
        } else {
            opStep = ElUtil.getAnalyzedOperations(step, (ContextEl) _an, Calculation.staticCalculation(f_.isStaticContext()));
        }
        _an.setMerged(false);
        StringMap<AssignmentBefore> fieldsHypot_;
        CustList<StringMap<AssignmentBefore>> varsHypot_;
        CustList<StringMap<AssignmentBefore>> mutableHypot_;
        fieldsHypot_ = buildAssListFieldAfterInvalHypot(_an, _anEl);
        varsWhile_.getFieldsRootBefore().putAllMap(fieldsHypot_);
        varsHypot_ = buildAssListLocVarInvalHypot(_an, _anEl);
        varsWhile_.getVariablesRootBefore().clear();
        varsWhile_.getVariablesRootBefore().addAllElts(varsHypot_);
        mutableHypot_ = buildAssListMutableLoopInvalHypot(_an, _anEl);
        varsWhile_.getMutableLoopRootBefore().clear();
        varsWhile_.getMutableLoopRootBefore().addAllElts(mutableHypot_);
        processFinalFields(_an, _anEl, allDesc_, varsWhile_, fieldsHypot_);
        processFinalVars(_an, _anEl, allDesc_, varsWhile_, varsHypot_);
        processFinalMutableLoop(_an, _anEl, allDesc_, varsWhile_, mutableHypot_);
        StringMap<SimpleAssignment> fieldsAfter_;
        fieldsAfter_= buildAssListFieldAfterLoop(_an, _anEl);
        varsWhile_.getFieldsRoot().putAllMap(fieldsAfter_);
        CustList<StringMap<SimpleAssignment>> varsAfter_;
        CustList<StringMap<SimpleAssignment>> mutableAfter_;
        varsAfter_ = buildAssListLocVarAfterLoop(_an, _anEl);
        varsWhile_.getVariablesRoot().clear();
        varsWhile_.getVariablesRoot().addAllElts(varsAfter_);
        mutableAfter_ = buildAssListMutableLoopAfterLoop(_an, _anEl);
        varsWhile_.getMutableLoopRoot().clear();
        varsWhile_.getMutableLoopRoot().addAllElts(mutableAfter_);
    }
    protected StringMap<AssignmentBefore> buildAssListFieldAfterInvalHypot(Analyzable _an, AnalyzingEl _anEl) {
        Block last_ = getFirstChild();
        while (last_.getNextSibling() != null) {
            last_ = last_.getNextSibling();
        }
        CustList<ContinueBlock> continues_ = getContinuables(_anEl);
        IdMap<Block, AssignedVariables> id_;
        id_ = _an.getAssignedVariables().getFinalVariables();
        StringMap<AssignmentBefore> list_;
        list_ = makeHypothesisFields(_an);
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
            return invalidateHypothesis(list_, v_, breakAss_);
        }
        return invalidateHypothesis(list_, new StringMap<SimpleAssignment>(), breakAss_);
    }
    protected CustList<StringMap<AssignmentBefore>> buildAssListLocVarInvalHypot(Analyzable _an, AnalyzingEl _anEl) {
        Block last_ = getFirstChild();
        while (last_.getNextSibling() != null) {
            last_ = last_.getNextSibling();
        }
        CustList<ContinueBlock> continues_ = getContinuables(_anEl);
        IdMap<Block, AssignedVariables> id_;
        id_ = _an.getAssignedVariables().getFinalVariables();
        CustList<StringMap<AssignmentBefore>> varsList_;
        varsList_ = new CustList<StringMap<AssignmentBefore>>();
        CustList<StringMap<AssignmentBefore>> list_;
        list_ = makeHypothesisVars(_an);
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
            StringMap<AssignmentBefore> cond_ = list_.get(i);
            if (_anEl.canCompleteNormallyGroup(last_)) {
                AssignedVariables ass_ = id_.getVal(last_);
                CustList<StringMap<SimpleAssignment>> v_ = ass_.getVariablesRoot();
                if (v_.isValidIndex(i)) {
                    varsList_.add(invalidateHypothesis(cond_, v_.get(i), breakAss_));
                }
            } else {
                varsList_.add(invalidateHypothesis(cond_, new StringMap<SimpleAssignment>(), breakAss_));
            }
        }
        
        return varsList_;
    }
    protected CustList<StringMap<AssignmentBefore>> buildAssListMutableLoopInvalHypot(Analyzable _an, AnalyzingEl _anEl) {
        Block last_ = getFirstChild();
        while (last_.getNextSibling() != null) {
            last_ = last_.getNextSibling();
        }
        CustList<ContinueBlock> continues_ = getContinuables(_anEl);
        IdMap<Block, AssignedVariables> id_;
        id_ = _an.getAssignedVariables().getFinalVariables();
        CustList<StringMap<AssignmentBefore>> varsList_;
        varsList_ = new CustList<StringMap<AssignmentBefore>>();
        CustList<StringMap<AssignmentBefore>> list_;
        list_ = makeHypothesisMutableLoop(_an);
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
            StringMap<AssignmentBefore> cond_ = list_.get(i);
            if (_anEl.canCompleteNormallyGroup(last_)) {
                AssignedVariables ass_ = id_.getVal(last_);
                CustList<StringMap<SimpleAssignment>> v_ = ass_.getMutableLoopRoot();
                if (v_.isValidIndex(i)) {
                    varsList_.add(invalidateHypothesis(cond_, v_.get(i), breakAss_));
                }
            } else {
                varsList_.add(invalidateHypothesis(cond_, new StringMap<SimpleAssignment>(), breakAss_));
            }
        }
        
        return varsList_;
    }
    @Override
    protected StringMap<AssignmentBefore> makeHypothesisFields(Analyzable _an) {
        AssignedVariables vars_ = _an.getAssignedVariables().getFinalVariables().getVal(this);
        StringMap<AssignmentBefore> fields_;
        fields_ = new StringMap<AssignmentBefore>();
        if (opInit.isEmpty()) {
            for (EntryCust<String,AssignmentBefore> e: vars_.getFieldsRootBefore().entryList()) {
                AssignmentBefore ass_ = e.getValue();
                AssignmentBefore h_ = new AssignmentBefore();
                if (ass_.isAssignedBefore()) {
                    h_.setAssignedBefore(true);
                } else {
                    h_.setUnassignedBefore(true);
                }
                fields_.put(e.getKey(), h_);
            }
        } else {
            OperationNode prev_ = opInit.last();
            for (EntryCust<String,Assignment> e: vars_.getFields().getVal(prev_).entryList()) {
                Assignment ass_ = e.getValue();
                AssignmentBefore h_ = new AssignmentBefore();
                if (ass_.isAssignedAfter()) {
                    h_.setAssignedBefore(true);
                } else {
                    h_.setUnassignedBefore(true);
                }
                fields_.put(e.getKey(), h_);
            }
        }
        return fields_;
    }
    @Override
    protected CustList<StringMap<AssignmentBefore>> makeHypothesisVars(Analyzable _an) {
        AssignedVariables vars_ = _an.getAssignedVariables().getFinalVariables().getVal(this);
        CustList<StringMap<AssignmentBefore>> variables_;
        variables_ = new CustList<StringMap<AssignmentBefore>>();
        if (opInit.isEmpty()) {
            for (StringMap<AssignmentBefore> s: vars_.getVariablesRootBefore()) {
                StringMap<AssignmentBefore> sm_ = new StringMap<AssignmentBefore>();
                for (EntryCust<String,AssignmentBefore> e: s.entryList()) {
                    AssignmentBefore ass_ = e.getValue();
                    AssignmentBefore h_ = new AssignmentBefore();
                    if (ass_.isAssignedBefore()) {
                        h_.setAssignedBefore(true);
                    } else {
                        h_.setUnassignedBefore(true);
                    }
                    sm_.put(e.getKey(), h_);
                }
                variables_.add(sm_);
            }
        } else {
            OperationNode prev_ = opInit.last();
            for (StringMap<Assignment> s: vars_.getVariables().getVal(prev_)) {
                StringMap<AssignmentBefore> sm_ = new StringMap<AssignmentBefore>();
                for (EntryCust<String,Assignment> e: s.entryList()) {
                    Assignment ass_ = e.getValue();
                    AssignmentBefore h_ = new AssignmentBefore();
                    if (ass_.isAssignedAfter()) {
                        h_.setAssignedBefore(true);
                    } else {
                        h_.setUnassignedBefore(true);
                    }
                    sm_.put(e.getKey(), h_);
                }
                variables_.add(sm_);
            }
        }
        return variables_;
    }
    @Override
    protected CustList<StringMap<AssignmentBefore>> makeHypothesisMutableLoop(Analyzable _an) {
        AssignedVariables vars_ = _an.getAssignedVariables().getFinalVariables().getVal(this);
        CustList<StringMap<AssignmentBefore>> variables_;
        variables_ = new CustList<StringMap<AssignmentBefore>>();
        if (opInit.isEmpty()) {
            for (StringMap<AssignmentBefore> s: vars_.getMutableLoopRootBefore()) {
                StringMap<AssignmentBefore> sm_ = new StringMap<AssignmentBefore>();
                for (EntryCust<String,AssignmentBefore> e: s.entryList()) {
                    AssignmentBefore ass_ = e.getValue();
                    AssignmentBefore h_ = new AssignmentBefore();
                    if (ass_.isAssignedBefore()) {
                        h_.setAssignedBefore(true);
                    } else {
                        h_.setUnassignedBefore(true);
                    }
                    sm_.put(e.getKey(), h_);
                }
                variables_.add(sm_);
            }
        } else {
            OperationNode prev_ = opInit.last();
            for (StringMap<Assignment> s: vars_.getMutableLoop().getVal(prev_)) {
                StringMap<AssignmentBefore> sm_ = new StringMap<AssignmentBefore>();
                for (EntryCust<String,Assignment> e: s.entryList()) {
                    Assignment ass_ = e.getValue();
                    AssignmentBefore h_ = new AssignmentBefore();
                    if (ass_.isAssignedAfter()) {
                        h_.setAssignedBefore(true);
                    } else {
                        h_.setUnassignedBefore(true);
                    }
                    sm_.put(e.getKey(), h_);
                }
                variables_.add(sm_);
            }
        }
        return variables_;
    }
    private static StringMap<AssignmentBefore> invalidateHypothesis(StringMap<AssignmentBefore> _loop, StringMap<SimpleAssignment> _last,
            CustList<StringMap<AssignmentBefore>> _continuable) {
        StringMap<AssignmentBefore> out_ = new StringMap<AssignmentBefore>();
        for (EntryCust<String,AssignmentBefore> e: _loop.entryList()) {
            String key_ = e.getKey();
            AssignmentBefore ass_ = e.getValue().copy();
            for (StringMap<AssignmentBefore> c: _continuable) {
                if (!c.contains(key_)) {
                    continue;
                }
                if (!c.getVal(key_).isUnassignedBefore()) {
                    ass_.setUnassignedBefore(false);
                }
            }
            if (_last.contains(key_)) {
                if (!_last.getVal(key_).isUnassignedAfter()) {
                    ass_.setUnassignedBefore(false);
                }
            }
            out_.put(key_, ass_);
        }
        return out_;
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
                for (String v: variableNames) {
                    ip_.getVars().removeKey(v);
                }
                removeVarAndLoop(ip_);
                processBlock(_cont);
                return;
            }
            rw_.setBlock(getFirstChild());
            return;
        }
        ip_.setGlobalOffset(initOffset);
        ip_.setOffset(0);
        int index_ = 0;
        if (ip_.isEmptyEl()) {
            Struct struct_ = PrimitiveTypeUtil.defaultValue(importedClassName, _cont);
            for (String v: variableNames) {
                LoopVariable lv_ = new LoopVariable();
                lv_.setClassName(importedClassName);
                lv_.setStruct(struct_);
                ip_.getVars().put(v, lv_);
            }
        }
        if (!opInit.isEmpty()) {
            ExpressionLanguage from_ = ip_.getCurrentEl(_cont,this, CustList.FIRST_INDEX, CustList.FIRST_INDEX);
            from_.calculateMember(_cont);
            if (_cont.callsOrException()) {
                return;
            }
            index_++;
        }
        Boolean res_ = evaluateCondition(_cont, index_);
        if (res_ == null) {
            return;
        }
        LoopBlockStack l_ = new LoopBlockStack();
        l_.setBlock(this);
        l_.setFinished(!res_);
        ip_.addBlock(l_);
        c_ = (LoopBlockStack) ip_.getLastStack();
        if (c_.isFinished()) {
            for (String v: variableNames) {
                ip_.getVars().removeKey(v);
            }
            ip_.removeLastBlock();
            processBlock(_cont);
            return;
        }
        rw_.setBlock(getFirstChild());
    }

    private Boolean evaluateCondition(ContextEl _context, int _index) {
        AbstractPageEl last_ = _context.getLastPage();
        if (opExp.isEmpty()) {
            last_.clearCurrentEls();
            return true;
        }
        ExpressionLanguage exp_ = last_.getCurrentEl(_context,this, _index, CustList.SECOND_INDEX);
        last_.setOffset(0);
        last_.setGlobalOffset(expressionOffset);
        Argument arg_ = exp_.calculateMember(_context);
        if (_context.callsOrException()) {
            return null;
        }
        last_.clearCurrentEls();
        return (Boolean) arg_.getObject();
    }
    @Override
    public void exitStack(ContextEl _context) {
        processLastElementLoop(_context);
    }

    public void incrementLoop(ContextEl _conf, LoopBlockStack _l,
            StringMap<LoopVariable> _vars) {
        _l.setIndex(_l.getIndex() + 1);
        for (String v: variableNames) {
            LoopVariable lv_ = _vars.getVal(v);
            lv_.setIndex(lv_.getIndex() + 1);
        }
    }

    @Override
    public void processLastElementLoop(ContextEl _conf) {
        AbstractPageEl ip_ = _conf.getLastPage();
        ReadWrite rw_ = ip_.getReadWrite();
        LoopBlockStack l_ = (LoopBlockStack) ip_.getLastStack();
        l_.setEvaluatingKeepLoop(true);
        Block forLoopLoc_ = l_.getBlock();
        rw_.setBlock(forLoopLoc_);
        int index_ = 0;
        if (!opStep.isEmpty()) {
            ExpressionLanguage from_ = ip_.getCurrentEl(_conf,this, CustList.FIRST_INDEX, 2);
            from_.calculateMember(_conf);
            if (_conf.callsOrException()) {
                return;
            }
            index_++;
        }
        Boolean keep_ = evaluateCondition(_conf, index_);
        if (keep_ == null) {
            return;
        }
        if (!keep_) {
            l_.setFinished(true);
        }
        l_.setEvaluatingKeepLoop(false);
    }

    @Override
    public ExpressionLanguage getEl(ContextEl _context,
            int _indexProcess) {
        if (_indexProcess == 0) {
            return getInitEl();
        }
        if (_indexProcess == 1) {
            return getExpressionEl();
        }
        return getStepEl();
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
        return false;
    }
    @Override
    public boolean accessibleCondition() {
        if (opExp.isEmpty()) {
            return true;
        }
        OperationNode op_ = opExp.last();
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
        boolean proc_ = true;
        if (!opExp.isEmpty()) {
            OperationNode op_ = opExp.last();
            Argument arg_ = op_.getArgument();
            if (op_.getArgument() == null) {
                proc_ = false;
            } else if (!(arg_.getObject() instanceof Boolean)) {
                proc_ = false;
            } else if (!(Boolean)arg_.getObject()) {
                proc_ = false;
            }
        }
        if (_anEl.isReachable(this)) {
            if (!proc_) {
                abr_ = false;
            }
        }
        IdMap<BreakBlock, BreakableBlock> breakables_;
        breakables_ = _anEl.getBreakables();
        for (EntryCust<BreakBlock, BreakableBlock> e: breakables_.entryList()) {
            if (e.getValue() == this && _anEl.isReachable(e.getKey())) {
                abr_ = false;
                break;
            }
        }
        if (abr_) {
            _anEl.completeAbrupt(this);
            _anEl.completeAbruptGroup(this);
        }
    }

}
