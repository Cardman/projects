package code.expressionlanguage.methods;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.AnalyzedPageEl;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.calls.AbstractPageEl;
import code.expressionlanguage.calls.util.ReadWrite;
import code.expressionlanguage.errors.custom.BadImplicitCast;
import code.expressionlanguage.errors.custom.UnexpectedTypeError;
import code.expressionlanguage.files.OffsetBooleanInfo;
import code.expressionlanguage.files.OffsetStringInfo;
import code.expressionlanguage.files.OffsetsBlock;
import code.expressionlanguage.inherits.Mapping;
import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.expressionlanguage.instr.ElUtil;
import code.expressionlanguage.opers.Calculation;
import code.expressionlanguage.opers.ExpressionLanguage;
import code.expressionlanguage.opers.OperationNode;
import code.expressionlanguage.opers.exec.ExecOperationNode;
import code.expressionlanguage.opers.util.*;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.stacks.LoopBlockStack;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.BooleanStruct;
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

    private CustList<ExecOperationNode> opInit;

    private CustList<ExecOperationNode> opExp;

    private CustList<ExecOperationNode> opStep;

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

    public String getClassIndexName() {
        return classIndexName;
    }

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

    private ExpressionLanguage getInitEl() {
        return new ExpressionLanguage(opInit);
    }

    private ExpressionLanguage getExpressionEl() {
        return new ExpressionLanguage(opExp);
    }

    private ExpressionLanguage getStepEl() {
        return new ExpressionLanguage(opStep);
    }

    @Override
    public void reduce(ContextEl _context) {
        if (!opInit.isEmpty()) {
            ExecOperationNode i_ = opInit.last();
            opInit = ElUtil.getReducedNodes(i_);
        }
        if (!opExp.isEmpty()) {
            ExecOperationNode e_ = opExp.last();
            opExp = ElUtil.getReducedNodes(e_);
        }
        if (!opStep.isEmpty()) {
            ExecOperationNode s_ = opStep.last();
            opStep = ElUtil.getReducedNodes(s_);
        }
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
            fields_.putAllMap(AssignmentsUtil.copyBefore(vars_.getFieldsRootBefore()));
            variables_.addAllElts(AssignmentsUtil.copyBefore(vars_.getVariablesRootBefore()));
            mutable_.addAllElts(AssignmentsUtil.copyBefore(vars_.getMutableLoopRootBefore()));
        } else if (_an.getForLoopPartState() == ForLoopPart.CONDITION) {
            if (opInit.isEmpty()) {
                fields_.putAllMap(AssignmentsUtil.copyBefore(vars_.getFieldsRootBefore()));
                variables_.addAllElts(AssignmentsUtil.copyBefore(vars_.getVariablesRootBefore()));
                mutable_.addAllElts(AssignmentsUtil.copyBefore(vars_.getMutableLoopRootBefore()));
            } else {
                fields_.putAllMap(AssignmentsUtil.assignSimpleBefore(vars_.getFieldsRoot()));
                variables_.addAllElts(AssignmentsUtil.assignSimpleBefore(vars_.getVariablesRoot()));
                mutable_.addAllElts(AssignmentsUtil.assignSimpleBefore(vars_.getMutableLoopRoot()));
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
        assignWhenTrue(_an);
    }
    @Override
    protected AssignedBooleanVariables buildNewAssignedVariable() {
        return new AssignedBooleanLoopVariables();
    }
    @Override
    public void buildExpressionLanguage(ContextEl _cont) {
        FunctionBlock f_ = _cont.getAnalyzing().getCurrentFct();
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
            cast_.setIndexFile(classIndexNameOffset);
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
        boolean static_ = f_.isStaticContext();
        _cont.getVariablesNames().clear();
        page_.setGlobalOffset(initOffset);
        page_.setOffset(0);
        _cont.setForLoopPartState(ForLoopPart.INIT);
        if (init.trim().isEmpty()) {
            opInit = new CustList<ExecOperationNode>();
        } else {
            opInit = ElUtil.getAnalyzedOperations(init, _cont, Calculation.staticCalculation(static_));
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
            opExp = new CustList<ExecOperationNode>();
        } else {
            opExp = ElUtil.getAnalyzedOperations(expression, _cont, Calculation.staticCalculation(static_));
        }
        if (!opExp.isEmpty()) {
            ExecOperationNode elCondition_ = opExp.last();
            LgNames stds_ = _cont.getStandards();
            if (!elCondition_.getResultClass().isBoolType(_cont)) {
                UnexpectedTypeError un_ = new UnexpectedTypeError();
                un_.setFileName(getFile().getFileName());
                un_.setIndexFile(expressionOffset);
                un_.setType(opExp.last().getResultClass());
                _cont.getClasses().addError(un_);
            }
            elCondition_.getResultClass().setUnwrapObject(stds_.getAliasPrimBoolean());
            buildConditions(_cont);
        } else {
            AssignedBooleanVariables res_ = (AssignedBooleanVariables) _cont.getAnalyzing().getAssignedVariables().getFinalVariables().getVal(this);
            if (opInit.isEmpty()) {
                res_.getFieldsRootAfter().putAllMap(AssignmentsUtil.conditionBefore(res_.getFieldsRootBefore()));
                res_.getVariablesRootAfter().addAllElts(AssignmentsUtil.conditionBefore(res_.getVariablesRootBefore()));
                res_.getMutableLoopRootAfter().addAllElts(AssignmentsUtil.conditionBefore(res_.getMutableLoopRootBefore()));
            } else {
                res_.getFieldsRootAfter().putAllMap(AssignmentsUtil.conditionAfter(res_.getLastFieldsOrEmpty()));
                res_.getVariablesRootAfter().addAllElts(AssignmentsUtil.conditionAfter(res_.getLastVariablesOrEmpty()));
                res_.getMutableLoopRootAfter().addAllElts(AssignmentsUtil.conditionAfter(res_.getLastMutableLoopOrEmpty()));
            }
        }
    }
    @Override
    public StringList getVariableNames() {
        return variableNames;
    }

    @Override
    public void defaultAssignmentAfter(Analyzable _an, OperationNode _root) {
        AssignedVariables vars_ = _an.getAssignedVariables().getFinalVariables().getVal(this);
        if (_an.getForLoopPartState() == ForLoopPart.INIT) {
            AssignedBooleanLoopVariables loop_ = (AssignedBooleanLoopVariables) vars_;
            StringMap<Assignment> res_ = vars_.getLastFieldsOrEmpty();
            loop_.getFieldsRootAfterInit().putAllMap(res_);
            CustList<StringMap<Assignment>> varsRes_;
            varsRes_ = vars_.getLastVariablesOrEmpty();
            for (StringMap<Assignment> s: varsRes_) {
                loop_.getVariablesRootAfterInit().add(s);
            }
            CustList<StringMap<Assignment>> mutableRes_;
            mutableRes_ = vars_.getLastMutableLoopOrEmpty();
            for (StringMap<Assignment> s: mutableRes_) {
                loop_.getMutableLoopRootAfterInit().add(s);
            }
        }
        StringMap<Assignment> res_ = vars_.getLastFieldsOrEmpty();
        vars_.getFieldsRoot().putAllMap(AssignmentsUtil.assignClassic(res_));
        CustList<StringMap<Assignment>> varsRes_;
        varsRes_ = vars_.getLastVariablesOrEmpty();
        vars_.getVariablesRoot().clear();
        vars_.getVariablesRoot().addAllElts(AssignmentsUtil.assignClassic(varsRes_));
        CustList<StringMap<Assignment>> mutableRes_;
        mutableRes_ = vars_.getLastMutableLoopOrEmpty();
        vars_.getMutableLoopRoot().clear();
        vars_.getMutableLoopRoot().addAllElts(AssignmentsUtil.assignClassic(mutableRes_));
    }
    @Override
    public void setAssignmentAfter(Analyzable _an, AnalyzingEl _anEl) {
        _an.setMerged(false);
        FunctionBlock f_ = _an.getAnalyzing().getCurrentFct();
        IdMap<Block, AssignedVariables> allDesc_ = new IdMap<Block, AssignedVariables>();
        boolean add_ = false;
        IdMap<Block, AssignedVariables> id_;
        id_ = _an.getAssignedVariables().getFinalVariables();
        AssignedVariables varsWhile_ = null;
        for (EntryCust<Block, AssignedVariables> e: id_.entryList()) {
            if (e.getKey() == this) {
                add_ = true;
                varsWhile_ = e.getValue();
            } else if (add_) {
                allDesc_.put(e.getKey(), e.getValue());
            }
        }
        AnalyzedPageEl page_ = _an.getAnalyzing();
        page_.setGlobalOffset(stepOffset);
        page_.setOffset(0);
        _an.setForLoopPartState(ForLoopPart.STEP);
        _an.setMerged(true);
        _an.getLocalVariables().last().clear();
        boolean static_ = f_.isStaticContext();
        if (step.trim().isEmpty()) {
            opStep = new CustList<ExecOperationNode>();
        } else {
            opStep = ElUtil.getAnalyzedOperations(step, (ContextEl) _an, Calculation.staticCalculation(static_));
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
        processFinalFields(_an, allDesc_, varsWhile_, fieldsHypot_);
        processFinalVars(_an, allDesc_, varsWhile_, varsHypot_);
        processFinalMutableLoop(_an, allDesc_, varsWhile_, mutableHypot_);
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
    private StringMap<AssignmentBefore> buildAssListFieldAfterInvalHypot(Analyzable _an, AnalyzingEl _anEl) {
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
    private CustList<StringMap<AssignmentBefore>> buildAssListLocVarInvalHypot(Analyzable _an, AnalyzingEl _anEl) {
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
    private CustList<StringMap<AssignmentBefore>> buildAssListMutableLoopInvalHypot(Analyzable _an, AnalyzingEl _anEl) {
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
        AssignedBooleanLoopVariables vars_ = (AssignedBooleanLoopVariables) _an.getAssignedVariables().getFinalVariables().getVal(this);
        StringMap<AssignmentBefore> fields_;
        if (opInit.isEmpty()) {
            fields_ = AssignmentsUtil.getHypoAssignmentBefore(vars_.getFieldsRootBefore());
        } else {
            fields_ = AssignmentsUtil.getHypoAssignmentAfter(vars_.getFieldsRootAfterInit());
        }
        return fields_;
    }
    @Override
    protected CustList<StringMap<AssignmentBefore>> makeHypothesisVars(Analyzable _an) {
        AssignedBooleanLoopVariables vars_ = (AssignedBooleanLoopVariables) _an.getAssignedVariables().getFinalVariables().getVal(this);
        CustList<StringMap<AssignmentBefore>> variables_;
        variables_ = new CustList<StringMap<AssignmentBefore>>();
        if (opInit.isEmpty()) {
            for (StringMap<AssignmentBefore> s: vars_.getVariablesRootBefore()) {
                StringMap<AssignmentBefore> sm_ = AssignmentsUtil.getHypoAssignmentBefore(s);
                variables_.add(sm_);
            }
        } else {
            for (StringMap<Assignment> s: vars_.getVariablesRootAfterInit()) {
                StringMap<AssignmentBefore> sm_ = AssignmentsUtil.getHypoAssignmentAfter(s);
                variables_.add(sm_);
            }
        }
        return variables_;
    }

    @Override
    protected CustList<StringMap<AssignmentBefore>> makeHypothesisMutableLoop(Analyzable _an) {
        AssignedBooleanLoopVariables vars_ = (AssignedBooleanLoopVariables) _an.getAssignedVariables().getFinalVariables().getVal(this);
        CustList<StringMap<AssignmentBefore>> variables_;
        variables_ = new CustList<StringMap<AssignmentBefore>>();
        if (opInit.isEmpty()) {
            for (StringMap<AssignmentBefore> s: vars_.getMutableLoopRootBefore()) {
                StringMap<AssignmentBefore> sm_ = AssignmentsUtil.getHypoAssignmentBefore(s);
                variables_.add(sm_);
            }
        } else {
            for (StringMap<Assignment> s: vars_.getMutableLoopRootAfterInit()) {
                StringMap<AssignmentBefore> sm_ = AssignmentsUtil.getHypoAssignmentAfter(s);
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
        	String formatted_ = ip_.formatVarType(importedClassName, _cont);
            Struct struct_ = PrimitiveTypeUtil.defaultValue(formatted_, _cont);
            for (String v: variableNames) {
                LoopVariable lv_ = new LoopVariable();
                lv_.setClassName(formatted_);
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
        return ((BooleanStruct)arg_.getStruct()).getInstance();
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
    public boolean accessibleCondition() {
        if (opExp.isEmpty()) {
            return true;
        }
        ExecOperationNode op_ = opExp.last();
        boolean accessible_ = false;
        Argument arg_ = op_.getArgument();
        if (op_.getArgument() == null) {
            accessible_ = true;
        } else if (!(arg_.getStruct() instanceof BooleanStruct)) {
            accessible_ = true;
        } else if (((BooleanStruct)arg_.getStruct()).getInstance()) {
            accessible_ = true;
        }
        return accessible_;
    }
    @Override
    public void abruptGroup(AnalyzingEl _anEl) {
        boolean abr_ = true;
        boolean proc_ = true;
        if (!opExp.isEmpty()) {
            ExecOperationNode op_ = opExp.last();
            Argument arg_ = op_.getArgument();
            if (op_.getArgument() == null) {
                proc_ = false;
            } else if (!(arg_.getStruct() instanceof BooleanStruct)) {
                proc_ = false;
            } else if (!((BooleanStruct)arg_.getStruct()).getInstance()) {
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
            _anEl.completeAbruptGroup(this);
        }
    }

}
