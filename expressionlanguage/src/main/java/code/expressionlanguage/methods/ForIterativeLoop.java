package code.expressionlanguage.methods;
import code.expressionlanguage.AbstractPageEl;
import code.expressionlanguage.Analyzable;
import code.expressionlanguage.AnalyzedPageEl;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.CustomError;
import code.expressionlanguage.ElUtil;
import code.expressionlanguage.Mapping;
import code.expressionlanguage.OffsetBooleanInfo;
import code.expressionlanguage.OffsetStringInfo;
import code.expressionlanguage.OffsetsBlock;
import code.expressionlanguage.PrimitiveTypeUtil;
import code.expressionlanguage.ReadWrite;
import code.expressionlanguage.VariableSuffix;
import code.expressionlanguage.methods.util.BadImplicitCast;
import code.expressionlanguage.methods.util.BadVariableName;
import code.expressionlanguage.methods.util.DuplicateVariable;
import code.expressionlanguage.opers.Calculation;
import code.expressionlanguage.opers.ExpressionLanguage;
import code.expressionlanguage.opers.OperationNode;
import code.expressionlanguage.opers.util.AssignedBooleanVariables;
import code.expressionlanguage.opers.util.AssignedVariables;
import code.expressionlanguage.opers.util.Assignment;
import code.expressionlanguage.opers.util.AssignmentBefore;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.opers.util.SimpleAssignment;
import code.expressionlanguage.options.Options;
import code.expressionlanguage.stacks.LoopBlockStack;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.StdStruct;
import code.expressionlanguage.variables.LoopVariable;
import code.util.CustList;
import code.util.EntryCust;
import code.util.IdMap;
import code.util.StringList;
import code.util.StringMap;

public final class ForIterativeLoop extends BracedStack implements ForLoop {

    private String label;
    private int labelOffset;

    private final String className;
    private int classNameOffset;

    private final String classIndexName;
    private int classIndexNameOffset;

    private final String variableName;
    private int variableNameOffset;

    private final String init;
    private int initOffset;

    private final String expression;
    private int expressionOffset;

    private final String step;
    private int stepOffset;

    private final boolean eq;
    private int eqOffset;

    private CustList<OperationNode> opInit;

    private CustList<OperationNode> opExp;

    private CustList<OperationNode> opStep;

    public ForIterativeLoop(ContextEl _importingPage,
            BracedBlock _m,
            OffsetStringInfo _className, OffsetStringInfo _variable,
            OffsetStringInfo _from,
            OffsetStringInfo _to, OffsetBooleanInfo _eq, OffsetStringInfo _step, OffsetStringInfo _classIndex, OffsetStringInfo _label, OffsetsBlock _offset) {
        super(_importingPage, _m, _offset);
        className = _className.getInfo();
        classNameOffset = _className.getOffset();
        variableName = _variable.getInfo();
        variableNameOffset = _variable.getOffset();
        init = _from.getInfo();
        initOffset = _from.getOffset();
        expression = _to.getInfo();
        expressionOffset = _to.getOffset();
        step = _step.getInfo();
        stepOffset = _step.getOffset();
        eq = _eq.isInfo();
        eqOffset = _eq.getOffset();
        String classIndex_ = _classIndex.getInfo();
        if (classIndex_.isEmpty()) {
            classIndex_ = _importingPage.getStandards().getAliasPrimLong();
        }
        classIndexName = classIndex_;
        classIndexNameOffset = _classIndex.getOffset();
        label = _label.getInfo();
        labelOffset = _label.getOffset();
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

    public int getVariableNameOffset() {
        return variableNameOffset;
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

    public int getEqOffset() {
        return eqOffset;
    }

    @Override
    public String getClassIndexName() {
        return classIndexName;
    }

    @Override
    public String getClassName() {
        return className;
    }

    public String getVariableName() {
        return variableName;
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

    public boolean isEq() {
        return eq;
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
        String cl_ = className.trim();
        ClassArgumentMatching elementClass_ = new ClassArgumentMatching(cl_);
        if (!PrimitiveTypeUtil.isPureNumberClass(elementClass_, _cont)) {
            Mapping mapping_ = new Mapping();
            mapping_.setArg(elementClass_);
            mapping_.setParam(_cont.getStandards().getAliasLong());
            BadImplicitCast cast_ = new BadImplicitCast();
            cast_.setMapping(mapping_);
            cast_.setFileName(getFile().getFileName());
            cast_.setRc(getRowCol(0, classNameOffset));
            _cont.getClasses().addError(cast_);
        }
        page_.setGlobalOffset(variableNameOffset);
        page_.setOffset(0);
        if (_cont.getAnalyzing().containsVar(variableName)) {
            DuplicateVariable d_ = new DuplicateVariable();
            d_.setId(variableName);
            d_.setFileName(getFile().getFileName());
            d_.setRc(getRowCol(0, variableNameOffset));
            _cont.getClasses().addError(d_);
        }
        if (_cont.getAnalyzing().containsMutableLoopVar(variableName)) {
            DuplicateVariable d_ = new DuplicateVariable();
            d_.setId(variableName);
            d_.setFileName(getFile().getFileName());
            d_.setRc(getRowCol(0, variableNameOffset));
            _cont.getClasses().addError(d_);
        }
        if (!StringList.isWord(variableName)) {
            BadVariableName b_ = new BadVariableName();
            b_.setFileName(getFile().getFileName());
            b_.setRc(getRowCol(0, variableNameOffset));
            b_.setVarName(variableName);
            _cont.getClasses().addError(b_);
        }
        if (_cont.getKeyWords().isKeyWordNotVar(variableName)) {
            BadVariableName b_ = new BadVariableName();
            b_.setFileName(getFile().getFileName());
            b_.setRc(getRowCol(0, variableNameOffset));
            b_.setVarName(variableName);
            _cont.getClasses().addError(b_);
        }
        if (PrimitiveTypeUtil.isPrimitive(variableName, _cont)) {
            BadVariableName b_ = new BadVariableName();
            b_.setFileName(getFile().getFileName());
            b_.setRc(getRowCol(0, variableNameOffset));
            b_.setVarName(variableName);
            _cont.getClasses().addError(b_);
        }
        if (StringList.quickEq(variableName, _cont.getStandards().getAliasVoid())) {
            BadVariableName b_ = new BadVariableName();
            b_.setFileName(getFile().getFileName());
            b_.setRc(getRowCol(0, variableNameOffset));
            b_.setVarName(variableName);
            _cont.getClasses().addError(b_);
        }
        Options opt_ = _cont.getOptions();
        if (opt_.getSuffixVar() == VariableSuffix.NONE) {
            if (!variableName.isEmpty() && ContextEl.isDigit(variableName.charAt(0))) {
                BadVariableName b_ = new BadVariableName();
                b_.setFileName(getFile().getFileName());
                b_.setRc(getRowCol(0, variableNameOffset));
                b_.setVarName(variableName);
                _cont.getClasses().addError(b_);
            }
        }
        if (opt_.getSuffixVar() != VariableSuffix.DISTINCT) {
            if (_cont.getAnalyzing().containsLocalVar(variableName)) {
                DuplicateVariable d_ = new DuplicateVariable();
                d_.setId(variableName);
                d_.setFileName(getFile().getFileName());
                d_.setRc(getRowCol(0, variableNameOffset));
                _cont.getClasses().addError(d_);
                return;
            }
            if (_cont.getAnalyzing().containsCatchVar(variableName)) {
                DuplicateVariable d_ = new DuplicateVariable();
                d_.setId(variableName);
                d_.setFileName(getFile().getFileName());
                d_.setRc(getRowCol(0, variableNameOffset));
                _cont.getClasses().addError(d_);
                return;
            }
            if (_cont.getParameters().contains(variableName)) {
                DuplicateVariable d_ = new DuplicateVariable();
                d_.setId(variableName);
                d_.setFileName(getFile().getFileName());
                d_.setRc(getRowCol(0, variableNameOffset));
                _cont.getClasses().addError(d_);
                return;
            }
        }
        page_.setGlobalOffset(initOffset);
        page_.setOffset(0);
        opInit = ElUtil.getAnalyzedOperations(init, _cont, Calculation.staticCalculation(f_.isStaticContext()));
        if (opInit.isEmpty()) {
            return;
        }
        OperationNode initEl_ = opInit.last();
        if (!PrimitiveTypeUtil.canBeUseAsArgument(false, elementClass_, initEl_.getResultClass(), _cont)) {
            Mapping mapping_ = new Mapping();
            mapping_.setArg(initEl_.getResultClass());
            mapping_.setParam(elementClass_);
            BadImplicitCast cast_ = new BadImplicitCast();
            cast_.setMapping(mapping_);
            cast_.setFileName(getFile().getFileName());
            cast_.setRc(getRowCol(0, initOffset));
            _cont.getClasses().addError(cast_);
        }
        page_.setGlobalOffset(expressionOffset);
        page_.setOffset(0);
        opExp = ElUtil.getAnalyzedOperations(expression, _cont, Calculation.staticCalculation(f_.isStaticContext()));
        if (opExp.isEmpty()) {
            return;
        }
        OperationNode expressionEl_ = opExp.last();
        if (!PrimitiveTypeUtil.canBeUseAsArgument(false, elementClass_, expressionEl_.getResultClass(), _cont)) {
            Mapping mapping_ = new Mapping();
            mapping_.setArg(expressionEl_.getResultClass());
            mapping_.setParam(elementClass_);
            BadImplicitCast cast_ = new BadImplicitCast();
            cast_.setMapping(mapping_);
            cast_.setFileName(getFile().getFileName());
            cast_.setRc(getRowCol(0, expressionOffset));
            _cont.getClasses().addError(cast_);
        }
        page_.setGlobalOffset(stepOffset);
        page_.setOffset(0);
        opStep = ElUtil.getAnalyzedOperations(step, _cont, Calculation.staticCalculation(f_.isStaticContext()));
        if (opStep.isEmpty()) {
            return;
        }
        OperationNode stepEl_ = opStep.last();
        if (!PrimitiveTypeUtil.canBeUseAsArgument(false, elementClass_, stepEl_.getResultClass(), _cont)) {
            Mapping mapping_ = new Mapping();
            mapping_.setArg(stepEl_.getResultClass());
            mapping_.setParam(elementClass_);
            BadImplicitCast cast_ = new BadImplicitCast();
            cast_.setMapping(mapping_);
            cast_.setFileName(getFile().getFileName());
            cast_.setRc(getRowCol(0, stepOffset));
            _cont.getClasses().addError(cast_);
        }
        if (getFirstChild() != null) {
            LoopVariable lv_ = new LoopVariable();
            lv_.setClassName(cl_);
            lv_.setIndexClassName(classIndexName);
            _cont.getAnalyzing().putVar(variableName, lv_);
        }
        buildConditions(_cont);
    }
    @Override
    public void setAssignmentBeforeChild(Analyzable _an, AnalyzingEl _anEl) {
        Block firstChild_ = getFirstChild();
        IdMap<Block, AssignedVariables> id_ = _an.getAssignedVariables().getFinalVariables();
        AssignedVariables parAss_ = id_.getVal(this);
        AssignedVariables assBl_ = firstChild_.buildNewAssignedVariable();
        for (EntryCust<String, SimpleAssignment> e: parAss_.getFieldsRoot().entryList()) {
            SimpleAssignment ba_ = e.getValue();
            assBl_.getFieldsRootBefore().put(e.getKey(), ba_.assignBefore());
        }
        for (StringMap<SimpleAssignment> s: parAss_.getVariablesRoot()) {
            StringMap<AssignmentBefore> sm_ = new StringMap<AssignmentBefore>();
            for (EntryCust<String, SimpleAssignment> e: s.entryList()) {
                SimpleAssignment ba_ = e.getValue();
                sm_.put(e.getKey(), ba_.assignBefore());
            }
            assBl_.getVariablesRootBefore().add(sm_);
        }
        assBl_.getVariablesRootBefore().add(new StringMap<AssignmentBefore>());
        for (StringMap<SimpleAssignment> s: parAss_.getMutableLoopRoot()) {
            StringMap<AssignmentBefore> sm_ = new StringMap<AssignmentBefore>();
            for (EntryCust<String, SimpleAssignment> e: s.entryList()) {
                SimpleAssignment ba_ = e.getValue();
                sm_.put(e.getKey(), ba_.assignBefore());
            }
            assBl_.getMutableLoopRootBefore().add(sm_);
        }
        assBl_.getMutableLoopRootBefore().add(new StringMap<AssignmentBefore>());
        id_.put(firstChild_, assBl_);
    }
    @Override
    public void defaultAssignmentBefore(Analyzable _an, OperationNode _root) {
        AssignedVariables vars_ = _an.getAssignedVariables().getFinalVariables().getVal(this);
        StringMap<AssignmentBefore> fields_;
        CustList<StringMap<AssignmentBefore>> variables_;
        fields_ = new StringMap<AssignmentBefore>();
        variables_ = new CustList<StringMap<AssignmentBefore>>();
        for (EntryCust<String,AssignmentBefore> e: vars_.getFieldsRootBefore().entryList()) {
            fields_.put(e.getKey(), e.getValue().copy());
        }
        vars_.getFieldsBefore().put(_root, fields_);
        if (vars_.getVariablesRoot().isEmpty()) {
            for (StringMap<AssignmentBefore> s: vars_.getVariablesRootBefore()) {
                StringMap<AssignmentBefore> sm_ = new StringMap<AssignmentBefore>();
                for (EntryCust<String,AssignmentBefore> e: s.entryList()) {
                    sm_.put(e.getKey(), e.getValue().copy());
                }
                variables_.add(sm_);
            }
        } else {
            for (StringMap<SimpleAssignment> s: vars_.getVariablesRoot()) {
                StringMap<AssignmentBefore> sm_ = new StringMap<AssignmentBefore>();
                for (EntryCust<String, SimpleAssignment> e: s.entryList()) {
                    sm_.put(e.getKey(), e.getValue().assignBefore());
                }
                variables_.add(sm_);
            }
        }
        vars_.getVariablesBefore().put(_root, variables_);
        CustList<StringMap<AssignmentBefore>> mutable_;
        mutable_ = new CustList<StringMap<AssignmentBefore>>();
        if (vars_.getMutableLoopRoot().isEmpty()) {
            for (StringMap<AssignmentBefore> s: vars_.getMutableLoopRootBefore()) {
                StringMap<AssignmentBefore> sm_ = new StringMap<AssignmentBefore>();
                for (EntryCust<String,AssignmentBefore> e: s.entryList()) {
                    sm_.put(e.getKey(), e.getValue().copy());
                }
                mutable_.add(sm_);
            }
        } else {
            for (StringMap<SimpleAssignment> s: vars_.getMutableLoopRoot()) {
                StringMap<AssignmentBefore> sm_ = new StringMap<AssignmentBefore>();
                for (EntryCust<String, SimpleAssignment> e: s.entryList()) {
                    sm_.put(e.getKey(), e.getValue().assignBefore());
                }
                mutable_.add(sm_);
            }
        }
        vars_.getMutableLoopBefore().put(_root, mutable_);
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
        if (vars_.getVariablesRoot().isEmpty()) {
            for (StringMap<Assignment> s: varsRes_) {
                StringMap<SimpleAssignment> sm_ = new StringMap<SimpleAssignment>();
                for (EntryCust<String, Assignment> e: s.entryList()) {
                    sm_.put(e.getKey(), e.getValue().assignClassic());
                }
                vars_.getVariablesRoot().add(sm_);
            }
        } else {
            int index_ = 0;
            for (StringMap<Assignment> s: varsRes_) {
                StringMap<SimpleAssignment> sm_ = new StringMap<SimpleAssignment>();
                for (EntryCust<String, Assignment> e: s.entryList()) {
                    sm_.put(e.getKey(), e.getValue().assignClassic());
                }
                vars_.getVariablesRoot().set(index_, sm_);
                index_++;
            }
        }
        CustList<StringMap<Assignment>> mutableRes_;
        mutableRes_ = vars_.getMutableLoop().getVal(_root);
        if (vars_.getMutableLoopRoot().isEmpty()) {
            for (StringMap<Assignment> s: mutableRes_) {
                StringMap<SimpleAssignment> sm_ = new StringMap<SimpleAssignment>();
                for (EntryCust<String, Assignment> e: s.entryList()) {
                    sm_.put(e.getKey(), e.getValue().assignClassic());
                }
                vars_.getMutableLoopRoot().add(sm_);
            }
        } else {
            int index_ = 0;
            for (StringMap<Assignment> s: mutableRes_) {
                StringMap<SimpleAssignment> sm_ = new StringMap<SimpleAssignment>();
                for (EntryCust<String, Assignment> e: s.entryList()) {
                    sm_.put(e.getKey(), e.getValue().assignClassic());
                }
                vars_.getMutableLoopRoot().set(index_, sm_);
                index_++;
            }
        }
    }
    @Override
    protected AssignedBooleanVariables buildNewAssignedVariable() {
        return new AssignedBooleanVariables();
    }
    @Override
    public void setAssignmentAfter(Analyzable _an, AnalyzingEl _anEl) {
        Block firstChild_ = getFirstChild();
        if (firstChild_ == null) {
            super.setAssignmentAfter(_an, _anEl);
            return;
        }
        IdMap<Block, AssignedVariables> id_;
        id_ = _an.getAssignedVariables().getFinalVariables();
        IdMap<Block, AssignedVariables> allDesc_ = new IdMap<Block, AssignedVariables>();
        AssignedBooleanVariables varsWhile_ = null;
        boolean add_ = false;
        for (EntryCust<Block, AssignedVariables> e: id_.entryList()) {
            if (e.getKey() == this) {
                varsWhile_ = (AssignedBooleanVariables) e.getValue();
                add_ = true;
            } else if (add_) {
                allDesc_.put(e.getKey(), e.getValue());
            }
        }
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
        CustList<StringMap<SimpleAssignment>> varsAfter_;
        CustList<StringMap<SimpleAssignment>> mutableAfter_;
        fieldsAfter_= buildAssListFieldAfterLoop(_an, _anEl);
        varsWhile_.getFieldsRoot().putAllMap(fieldsAfter_);
        varsAfter_ = buildAssListLocVarAfterLoop(_an, _anEl);
        varsWhile_.getVariablesRoot().clear();
        varsWhile_.getVariablesRoot().addAllElts(varsAfter_);
        mutableAfter_ = buildAssListMutableLoopAfterLoop(_an, _anEl);
        varsWhile_.getMutableLoopRoot().clear();
        varsWhile_.getMutableLoopRoot().addAllElts(mutableAfter_);
    }
    protected StringMap<AssignmentBefore> buildAssListFieldAfterInvalHypot(Analyzable _an, AnalyzingEl _anEl) {
        Block first_ = getFirstChild();
        Block last_ = first_;
        while (last_.getNextSibling() != null) {
            last_ = last_.getNextSibling();
        }
        CustList<ContinueBlock> continues_ = getContinuables(_anEl);
        IdMap<Block, AssignedVariables> id_;
        id_ = _an.getAssignedVariables().getFinalVariables();
        StringMap<AssignmentBefore> list_;
        list_ = first_.makeHypothesisFields(_an);
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
        Block first_ = getFirstChild();
        Block last_ = first_;
        while (last_.getNextSibling() != null) {
            last_ = last_.getNextSibling();
        }
        CustList<ContinueBlock> continues_ = getContinuables(_anEl);
        IdMap<Block, AssignedVariables> id_;
        id_ = _an.getAssignedVariables().getFinalVariables();
        CustList<StringMap<AssignmentBefore>> varsList_;
        varsList_ = new CustList<StringMap<AssignmentBefore>>();
        CustList<StringMap<AssignmentBefore>> list_;
        list_ = first_.makeHypothesisVars(_an);
        list_ = list_.mid(0, list_.size() - 1);
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
        Block first_ = getFirstChild();
        Block last_ = first_;
        while (last_.getNextSibling() != null) {
            last_ = last_.getNextSibling();
        }
        CustList<ContinueBlock> continues_ = getContinuables(_anEl);
        IdMap<Block, AssignedVariables> id_;
        id_ = _an.getAssignedVariables().getFinalVariables();
        CustList<StringMap<AssignmentBefore>> varsList_;
        varsList_ = new CustList<StringMap<AssignmentBefore>>();
        CustList<StringMap<AssignmentBefore>> list_;
        list_ = first_.makeHypothesisMutableLoop(_an);
        list_ = list_.mid(0, list_.size() - 1);
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
    public void processEl(ContextEl _cont) {
        AbstractPageEl ip_ = _cont.getLastPage();
        LoopBlockStack c_ = ip_.getLastLoopIfPossible();
        if (c_ != null && c_.getBlock() == this) {
            if (c_.isFinished()) {
                removeVarAndLoop(ip_);
                processBlock(_cont);
                return;
            }
            ip_.getReadWrite().setBlock(getFirstChild());
            return;
        }
        processLoop(_cont);
        if (_cont.callsOrException()) {
            return;
        }
        c_ = (LoopBlockStack) ip_.getLastStack();
        if (c_.isFinished()) {
            ip_.removeLastBlock();
            processBlock(_cont);
            return;
        }
        ip_.getReadWrite().setBlock(getFirstChild());
        return;
    }

    void processLoop(ContextEl _conf) {
        LgNames stds_ = _conf.getStandards();
        String null_ = stds_.getAliasNullPe();
        AbstractPageEl ip_ = _conf.getLastPage();
        StringMap<LoopVariable> varsLoop_ = ip_.getVars();
        String var_ = getVariableName();
        long nbMaxIterations_ = 0;
        long stepValue_ = 0;
        long fromValue_ = 0;
        Object realFromValue_ = 0;

        boolean eq_ = isEq();
        ip_.setGlobalOffset(initOffset);
        ip_.setOffset(0);
        ExpressionLanguage from_ = ip_.getCurrentEl(_conf,this, CustList.FIRST_INDEX, CustList.FIRST_INDEX);
        Argument argFrom_ = from_.calculateMember(_conf);
        if (_conf.callsOrException()) {
            return;
        }
        if (argFrom_.isNull()) {
            _conf.setException(new StdStruct(new CustomError(_conf.joinPages()),null_));
            return;
        }
        ip_.setGlobalOffset(expressionOffset);
        ip_.setOffset(0);
        ExpressionLanguage to_ = ip_.getCurrentEl(_conf,this, CustList.SECOND_INDEX, CustList.SECOND_INDEX);
        Argument argTo_ = to_.calculateMember(_conf);
        if (_conf.callsOrException()) {
            return;
        }
        if (argTo_.isNull()) {
            _conf.setException(new StdStruct(new CustomError(StringList.concat(RETURN_LINE,_conf.joinPages())),null_));
            return;
        }
        ip_.setGlobalOffset(stepOffset);
        ip_.setOffset(0);
        ExpressionLanguage step_ = ip_.getCurrentEl(_conf,this, CustList.SECOND_INDEX + 1, CustList.SECOND_INDEX + 1);
        Argument argStep_ = step_.calculateMember(_conf);
        if (_conf.callsOrException()) {
            return;
        }
        if (argStep_.isNull()) {
            _conf.setException(new StdStruct(new CustomError(StringList.concat(RETURN_LINE,_conf.joinPages())),null_));
            return;
        }
        realFromValue_ = argFrom_.getObject();
        ip_.setCurrentBlock(null);
        ip_.clearCurrentEls();
        fromValue_ = (Long)PrimitiveTypeUtil.convert(stds_.getAliasPrimLong(), realFromValue_, _conf).getInstance();
        long toValue_ = (Long)PrimitiveTypeUtil.convert(stds_.getAliasPrimLong(), argTo_.getObject(), _conf).getInstance();
        stepValue_ = (Long)PrimitiveTypeUtil.convert(stds_.getAliasPrimLong(), argStep_.getObject(), _conf).getInstance();
        if (stepValue_ > 0) {
            if (fromValue_ > toValue_) {
                stepValue_ = -stepValue_;
            }
        } else if (stepValue_ < 0) {
            if (fromValue_ < toValue_) {
                stepValue_ = -stepValue_;
            }
        }
        if (stepValue_ > 0) {
            long copyFrom_ = fromValue_;
            while (true) {
                if (copyFrom_ >= toValue_ && !eq_) {
                    break;
                }
                if (copyFrom_ > toValue_) {
                    break;
                }
                nbMaxIterations_++;
                copyFrom_ += stepValue_;
            }
        } else if (stepValue_ < 0) {
            long copyFrom_ = fromValue_;
            while (true) {
                if (copyFrom_ <= toValue_ && !eq_) {
                    break;
                }
                if (copyFrom_ < toValue_) {
                    break;
                }
                nbMaxIterations_++;
                copyFrom_ += stepValue_;
            }
        }
        long length_ = CustList.INDEX_NOT_FOUND_ELT;
        boolean finished_ = false;
        length_ = nbMaxIterations_;
        if (length_ == CustList.SIZE_EMPTY) {
            finished_ = true;
        }
        if (getFirstChild() == null) {
            finished_ = true;
        }
        LoopBlockStack l_ = new LoopBlockStack();
        l_.setFinished(finished_);
        l_.setBlock(this);
        l_.setMaxIteration(length_);
        ip_.addBlock(l_);
        if (finished_) {
            return;
        }
        Object int_ = realFromValue_;
        String indexClassName_;
        indexClassName_ = getClassIndexName();
        String className_;
        LoopVariable lv_ = new LoopVariable();
        className_ = getClassName();
        lv_.setClassName(className_);
        lv_.setIndexClassName(indexClassName_);
        lv_.setElement((Number)PrimitiveTypeUtil.convert(className_, int_, _conf).getInstance());
        lv_.setStep(stepValue_);
        varsLoop_.put(var_, lv_);
    }

    @Override
    public void exitStack(ContextEl _context) {
        processLastElementLoop(_context);
    }

    @Override
    public void removeVarAndLoop(AbstractPageEl _ip) {
        super.removeVarAndLoop(_ip);
        StringMap<LoopVariable> v_ = _ip.getVars();
        String var_ = getVariableName();
        v_.removeKey(var_);
    }

    @Override
    public void processLastElementLoop(ContextEl _conf) {
        AbstractPageEl ip_ = _conf.getLastPage();
        ReadWrite rw_ = ip_.getReadWrite();
        StringMap<LoopVariable> vars_ = ip_.getVars();
        LoopBlockStack l_ = (LoopBlockStack) ip_.getLastStack();
        Block forLoopLoc_ = l_.getBlock();
        rw_.setBlock(forLoopLoc_);
        if (l_.hasNext()) {
            incrementLoop(_conf, l_, vars_);
            return;
        }
        l_.setFinished(true);
    }

    @Override
    public void incrementLoop(ContextEl _conf, LoopBlockStack _l,
            StringMap<LoopVariable> _vars) {
        _l.setIndex(_l.getIndex() + 1);
        _conf.getLastPage().setGlobalOffset(variableNameOffset);
        _conf.getLastPage().setOffset(0);
        String var_ = getVariableName();
        LoopVariable lv_ = _vars.getVal(var_);
        Number element_ = (Number) lv_.getStruct().getInstance();
        Number o_ = element_.longValue()+lv_.getStep();
        o_ = (Number) PrimitiveTypeUtil.convert(className, o_, _conf).getInstance();
        lv_.setElement(o_);
        lv_.setIndex(lv_.getIndex() + 1);
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
    public void abruptGroup(Analyzable _an, AnalyzingEl _anEl) {
        if (!_anEl.isReachable(this)) {
            _anEl.completeAbrupt(this);
            _anEl.completeAbruptGroup(this);
        }
    }
}
