package code.expressionlanguage.methods;
import code.expressionlanguage.Analyzable;
import code.expressionlanguage.AnalyzedPageEl;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.calls.AbstractPageEl;
import code.expressionlanguage.calls.util.ReadWrite;
import code.expressionlanguage.errors.custom.BadImplicitCast;
import code.expressionlanguage.errors.custom.BadVariableName;
import code.expressionlanguage.errors.custom.DuplicateVariable;
import code.expressionlanguage.files.OffsetBooleanInfo;
import code.expressionlanguage.files.OffsetStringInfo;
import code.expressionlanguage.files.OffsetsBlock;
import code.expressionlanguage.inherits.Mapping;
import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.expressionlanguage.instr.ElUtil;
import code.expressionlanguage.instr.PartOffset;
import code.expressionlanguage.methods.util.AbstractCoverageResult;
import code.expressionlanguage.methods.util.AssignedVariablesDesc;
import code.expressionlanguage.opers.Calculation;
import code.expressionlanguage.opers.ExpressionLanguage;
import code.expressionlanguage.opers.OperationNode;
import code.expressionlanguage.opers.exec.ExecOperationNode;
import code.expressionlanguage.opers.util.*;
import code.expressionlanguage.stacks.LoopBlockStack;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.ErrorStruct;
import code.expressionlanguage.structs.LongStruct;
import code.expressionlanguage.structs.NumberStruct;
import code.expressionlanguage.variables.LoopVariable;
import code.util.*;

public final class ForIterativeLoop extends BracedStack implements ForLoop {

    private String label;
    private int labelOffset;

    private final String className;
    private String importedClassName;
    private int classNameOffset;

    private final String classIndexName;
    private String importedClassIndexName;
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

    private CustList<ExecOperationNode> opInit;

    private CustList<ExecOperationNode> opExp;

    private CustList<ExecOperationNode> opStep;

    public ForIterativeLoop(ContextEl _importingPage,
                            OffsetStringInfo _className, OffsetStringInfo _variable,
                            OffsetStringInfo _from,
                            OffsetStringInfo _to, OffsetBooleanInfo _eq, OffsetStringInfo _step, OffsetStringInfo _classIndex, OffsetStringInfo _label, OffsetsBlock _offset) {
        super(_offset);
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
            classIndex_ = _importingPage.getStandards().getAliasPrimInteger();
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

    @Override
    public int getRealLabelOffset() {
        return getLabelOffset();
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

    public String getClassIndexName() {
        return classIndexName;
    }

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
    public void reduce(ContextEl _context) {
        ExecOperationNode i_ = opInit.last();
        opInit = ElUtil.getReducedNodes(i_);
        ExecOperationNode e_ = opExp.last();
        opExp = ElUtil.getReducedNodes(e_);
        ExecOperationNode s_ = opStep.last();
        opStep = ElUtil.getReducedNodes(s_);
    }
    @Override
    public void buildExpressionLanguage(ContextEl _cont) {
        processVariableNames(_cont);
        FunctionBlock f_ = _cont.getAnalyzing().getCurrentFct();
        AnalyzedPageEl page_ = _cont.getAnalyzing();
        String cl_ = importedClassName;
        ClassArgumentMatching elementClass_ = new ClassArgumentMatching(cl_);
        page_.setGlobalOffset(initOffset);
        page_.setOffset(0);
        MethodAccessKind static_ = f_.getStaticContext();
        opInit = ElUtil.getAnalyzedOperations(init, _cont, Calculation.staticCalculation(static_));
        ExecOperationNode initEl_ = opInit.last();
        checkType(_cont, elementClass_, initEl_, initOffset);
        page_.setGlobalOffset(expressionOffset);
        page_.setOffset(0);
        opExp = ElUtil.getAnalyzedOperations(expression, _cont, Calculation.staticCalculation(static_));
        ExecOperationNode expressionEl_ = opExp.last();
        checkType(_cont, elementClass_, expressionEl_, expressionOffset);
        page_.setGlobalOffset(stepOffset);
        page_.setOffset(0);
        opStep = ElUtil.getAnalyzedOperations(step, _cont, Calculation.staticCalculation(static_));
        ExecOperationNode stepEl_ = opStep.last();
        checkType(_cont, elementClass_, stepEl_, stepOffset);
        LoopVariable lv_ = new LoopVariable();
        lv_.setClassName(cl_);
        lv_.setIndexClassName(importedClassIndexName);
        _cont.getAnalyzing().putVar(variableName, lv_);
        _cont.getCoverage().putBlockOperationsLoops(_cont,this);
        buildConditions(_cont);
    }

    @Override
    public void buildExpressionLanguageReadOnly(ContextEl _cont) {
        processVariableNames(_cont);
        FunctionBlock f_ = _cont.getAnalyzing().getCurrentFct();
        AnalyzedPageEl page_ = _cont.getAnalyzing();
        String cl_ = importedClassName;
        ClassArgumentMatching elementClass_ = new ClassArgumentMatching(cl_);
        page_.setGlobalOffset(initOffset);
        page_.setOffset(0);
        MethodAccessKind static_ = f_.getStaticContext();
        opInit = ElUtil.getAnalyzedOperationsReadOnly(init, _cont, Calculation.staticCalculation(static_));
        ExecOperationNode initEl_ = opInit.last();
        checkType(_cont, elementClass_, initEl_, initOffset);
        page_.setGlobalOffset(expressionOffset);
        page_.setOffset(0);
        opExp = ElUtil.getAnalyzedOperationsReadOnly(expression, _cont, Calculation.staticCalculation(static_));
        ExecOperationNode expressionEl_ = opExp.last();
        checkType(_cont, elementClass_, expressionEl_, expressionOffset);
        page_.setGlobalOffset(stepOffset);
        page_.setOffset(0);
        opStep = ElUtil.getAnalyzedOperationsReadOnly(step, _cont, Calculation.staticCalculation(static_));
        ExecOperationNode stepEl_ = opStep.last();
        checkType(_cont, elementClass_, stepEl_, stepOffset);
        LoopVariable lv_ = new LoopVariable();
        lv_.setClassName(cl_);
        lv_.setIndexClassName(importedClassIndexName);
        _cont.getAnalyzing().putVar(variableName, lv_);
        _cont.getCoverage().putBlockOperationsLoops(_cont,this);
    }

    private void checkType(ContextEl _cont, ClassArgumentMatching _elementClass, ExecOperationNode _stepEl, int stepOffset) {
        if (!PrimitiveTypeUtil.canBeUseAsArgument(_elementClass, _stepEl.getResultClass(), _cont)) {
            Mapping mapping_ = new Mapping();
            mapping_.setArg(_stepEl.getResultClass());
            mapping_.setParam(_elementClass);
            BadImplicitCast cast_ = new BadImplicitCast();
            cast_.setMapping(mapping_);
            cast_.setFileName(getFile().getFileName());
            cast_.setIndexFile(stepOffset);
            _cont.getClasses().addError(cast_);
        }
    }

    private void processVariableNames(ContextEl _cont) {
        AnalyzedPageEl page_ = _cont.getAnalyzing();
        page_.setGlobalOffset(classIndexNameOffset);
        page_.setOffset(0);
        importedClassIndexName = _cont.resolveCorrectType(classIndexName);
        if (!PrimitiveTypeUtil.isPrimitiveOrWrapper(importedClassIndexName, _cont)) {
            Mapping mapping_ = new Mapping();
            mapping_.setArg(importedClassIndexName);
            mapping_.setParam(_cont.getStandards().getAliasLong());
            BadImplicitCast cast_ = new BadImplicitCast();
            cast_.setMapping(mapping_);
            cast_.setFileName(getFile().getFileName());
            cast_.setIndexFile(classIndexNameOffset);
            _cont.getClasses().addError(cast_);
        }
        page_.setGlobalOffset(classNameOffset);
        page_.setOffset(0);
        importedClassName = _cont.resolveCorrectType(className);
        String cl_ = importedClassName;
        ClassArgumentMatching elementClass_ = new ClassArgumentMatching(cl_);
        if (!PrimitiveTypeUtil.isPureNumberClass(elementClass_, _cont)) {
            Mapping mapping_ = new Mapping();
            mapping_.setArg(elementClass_);
            mapping_.setParam(_cont.getStandards().getAliasLong());
            BadImplicitCast cast_ = new BadImplicitCast();
            cast_.setMapping(mapping_);
            cast_.setFileName(getFile().getFileName());
            cast_.setIndexFile(classNameOffset);
            _cont.getClasses().addError(cast_);
        }
        page_.setGlobalOffset(variableNameOffset);
        page_.setOffset(0);
        if (_cont.getAnalyzing().containsVar(variableName)) {
            DuplicateVariable d_ = new DuplicateVariable();
            d_.setId(variableName);
            d_.setFileName(getFile().getFileName());
            d_.setIndexFile(variableNameOffset);
            _cont.getClasses().addError(d_);
        }
        if (_cont.getAnalyzing().containsMutableLoopVar(variableName)) {
            DuplicateVariable d_ = new DuplicateVariable();
            d_.setId(variableName);
            d_.setFileName(getFile().getFileName());
            d_.setIndexFile(variableNameOffset);
            _cont.getClasses().addError(d_);
        }
        if (!_cont.isValidSingleToken(variableName)) {
            BadVariableName b_ = new BadVariableName();
            b_.setFileName(getFile().getFileName());
            b_.setIndexFile(variableNameOffset);
            b_.setVarName(variableName);
            _cont.getClasses().addError(b_);
        }
    }
    @Override
    public void setAssignmentBeforeChild(Analyzable _an, AnalyzingEl _anEl) {
        Block firstChild_ = getFirstChild();
        IdMap<Block, AssignedVariables> id_ = _an.getContextEl().getAssignedVariables().getFinalVariables();
        AssignedVariables parAss_ = id_.getVal(this);
        AssignedVariables assBl_ = firstChild_.buildNewAssignedVariable();
        assBl_.getFieldsRootBefore().putAllMap(AssignmentsUtil.assignSimpleBefore(parAss_.getFieldsRoot()));
        assBl_.getVariablesRootBefore().addAllElts(AssignmentsUtil.assignSimpleBefore(parAss_.getVariablesRoot()));
        assBl_.getVariablesRootBefore().add(new StringMap<AssignmentBefore>());
        assBl_.getMutableLoopRootBefore().addAllElts(AssignmentsUtil.assignSimpleBefore(parAss_.getMutableLoopRoot()));
        assBl_.getMutableLoopRootBefore().add(new StringMap<AssignmentBefore>());
        id_.put(firstChild_, assBl_);
    }
    @Override
    public void defaultAssignmentBefore(Analyzable _an, OperationNode _root) {
        AssignedVariables vars_ = _an.getContextEl().getAssignedVariables().getFinalVariables().getVal(this);
        CustList<StringMap<AssignmentBefore>> variables_;
        variables_ = new CustList<StringMap<AssignmentBefore>>();
        vars_.getFieldsBefore().put(_root, AssignmentsUtil.copyBefore(vars_.getFieldsRootBefore()));
        if (vars_.getVariablesRoot().isEmpty()) {
            variables_.addAllElts(AssignmentsUtil.copyBefore(vars_.getVariablesRootBefore()));
        } else {
            variables_.addAllElts(AssignmentsUtil.assignSimpleBefore(vars_.getVariablesRoot()));
        }
        vars_.getVariablesBefore().put(_root, variables_);
        CustList<StringMap<AssignmentBefore>> mutable_;
        mutable_ = new CustList<StringMap<AssignmentBefore>>();
        if (vars_.getMutableLoopRoot().isEmpty()) {
            mutable_.addAllElts(AssignmentsUtil.copyBefore(vars_.getMutableLoopRootBefore()));
        } else {
            mutable_.addAllElts(AssignmentsUtil.assignSimpleBefore(vars_.getMutableLoopRoot()));
        }
        vars_.getMutableLoopBefore().put(_root, mutable_);
    }
    @Override
    public void defaultAssignmentAfter(Analyzable _an, OperationNode _root) {
        AssignedVariables vars_ = _an.getContextEl().getAssignedVariables().getFinalVariables().getVal(this);
        StringMap<Assignment> res_ = vars_.getLastFieldsOrEmpty();
        vars_.getFieldsRoot().putAllMap(AssignmentsUtil.assignClassic(res_));
        CustList<StringMap<Assignment>> varsRes_;
        varsRes_ = vars_.getLastVariablesOrEmpty();
        if (vars_.getVariablesRoot().isEmpty()) {
            vars_.getVariablesRoot().addAllElts(AssignmentsUtil.assignClassic(varsRes_));
        } else {
            int index_ = 0;
            for (StringMap<Assignment> s: varsRes_) {
                vars_.getVariablesRoot().set(index_, AssignmentsUtil.assignClassic(s));
                index_++;
            }
        }
        CustList<StringMap<Assignment>> mutableRes_;
        mutableRes_ = vars_.getLastMutableLoopOrEmpty();
        if (vars_.getMutableLoopRoot().isEmpty()) {
            vars_.getMutableLoopRoot().addAllElts(AssignmentsUtil.assignClassic(mutableRes_));
        } else {
            int index_ = 0;
            for (StringMap<Assignment> s: mutableRes_) {
                vars_.getMutableLoopRoot().set(index_, AssignmentsUtil.assignClassic(s));
                index_++;
            }
        }
    }
    @Override
    protected AssignedVariables buildNewAssignedVariable() {
        return new AssignedBooleanVariables();
    }
    @Override
    public void setAssignmentAfter(Analyzable _an, AnalyzingEl _anEl) {
        AssignedVariablesDesc ass_ = new AssignedVariablesDesc(_an,this);
        AssignedVariables varsWhile_ = ass_.getVarsWhile();
        IdMap<Block, AssignedVariables> allDesc_ = ass_.getAllDesc();
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
        id_ = _an.getContextEl().getAssignedVariables().getFinalVariables();
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
        id_ = _an.getContextEl().getAssignedVariables().getFinalVariables();
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
                breakAss_.add(AssignmentsUtil.getOrEmptyBefore(vars_,i));
            }
            StringMap<AssignmentBefore> cond_ = list_.get(i);
            if (_anEl.canCompleteNormallyGroup(last_)) {
                AssignedVariables ass_ = id_.getVal(last_);
                CustList<StringMap<SimpleAssignment>> v_ = ass_.getVariablesRoot();
                varsList_.add(invalidateHypothesis(cond_, AssignmentsUtil.getOrEmptySimple(v_,i), breakAss_));
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
        id_ = _an.getContextEl().getAssignedVariables().getFinalVariables();
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
                breakAss_.add(AssignmentsUtil.getOrEmptyBefore(vars_,i));
            }
            StringMap<AssignmentBefore> cond_ = list_.get(i);
            if (_anEl.canCompleteNormallyGroup(last_)) {
                AssignedVariables ass_ = id_.getVal(last_);
                CustList<StringMap<SimpleAssignment>> v_ = ass_.getMutableLoopRoot();
                varsList_.add(invalidateHypothesis(cond_, AssignmentsUtil.getOrEmptySimple(v_,i), breakAss_));
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
                for (EntryCust<String,AssignmentBefore> f:c.entryList()) {
                    if (!StringList.quickEq(f.getKey(), key_)) {
                        continue;
                    }
                    if (!f.getValue().isUnassignedBefore()) {
                        ass_.setUnassignedBefore(false);
                    }
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
            _cont.getCoverage().passLoop(_cont, new Argument(new BooleanStruct(false)));
            ip_.removeLastBlock();
            processBlock(_cont);
            return;
        }
        _cont.getCoverage().passLoop(_cont, new Argument(new BooleanStruct(true)));
        ip_.getReadWrite().setBlock(getFirstChild());
    }
    @Override
    public void processReport(ContextEl _cont, CustList<PartOffset> _parts) {
        AbstractCoverageResult result_ = _cont.getCoverage().getCoverLoops().getVal(this);
        String tag_;
        if (result_.isFullCovered()) {
            tag_ = "<span class=\"f\">";
        } else if (result_.isPartialCovered()) {
            tag_ = "<span class=\"p\">";
        } else {
            tag_ = "<span class=\"n\">";
        }
        int off_ = getOffset().getOffsetTrim();
        _parts.add(new PartOffset(tag_,off_));
        tag_ = "</span>";
        _parts.add(new PartOffset(tag_,off_+ _cont.getKeyWords().getKeyWordIter().length()));
        tag_ = "<a name=\"m"+ variableNameOffset +"\">";
        _parts.add(new PartOffset(tag_,variableNameOffset));
        tag_ = "</a>";
        _parts.add(new PartOffset(tag_,variableNameOffset+variableName.length()));
        off_ = initOffset;
        int offsetEndBlock_ = off_ + init.length();
        ElUtil.buildCoverageReport(_cont,off_,this,opInit,offsetEndBlock_,_parts);
        off_ = expressionOffset;
        offsetEndBlock_ = off_ + expression.length();
        ElUtil.buildCoverageReport(_cont,off_,this,opExp,offsetEndBlock_,_parts);
        off_ = stepOffset;
        offsetEndBlock_ = off_ + step.length();
        ElUtil.buildCoverageReport(_cont,off_,this,opStep,offsetEndBlock_,_parts);
        _cont.getCoverage().getLoopVars().put(variableName,variableNameOffset);
        refLabel(_parts,label,labelOffset);
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

        boolean eq_ = isEq();
        ip_.setGlobalOffset(initOffset);
        ip_.setOffset(0);
        ExpressionLanguage from_ = ip_.getCurrentEl(_conf,this, CustList.FIRST_INDEX, CustList.FIRST_INDEX);
        Argument argFrom_ = from_.calculateMember(_conf);
        if (_conf.callsOrException()) {
            return;
        }
        if (argFrom_.isNull()) {
            _conf.setException(new ErrorStruct(_conf,null_));
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
            _conf.setException(new ErrorStruct(_conf,null_));
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
            _conf.setException(new ErrorStruct(_conf,null_));
            return;
        }
        ip_.clearCurrentEls();
        String prLong_ = stds_.getAliasPrimLong();
        fromValue_ = ((NumberStruct)PrimitiveTypeUtil.unwrapObject(prLong_, argFrom_.getStruct(), stds_)).longStruct();
        long toValue_ = ((NumberStruct)PrimitiveTypeUtil.unwrapObject(prLong_, argTo_.getStruct(), stds_)).longStruct();
        stepValue_ = ((NumberStruct)PrimitiveTypeUtil.unwrapObject(prLong_, argStep_.getStruct(), stds_)).longStruct();
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
        long length_;
        boolean finished_ = false;
        length_ = nbMaxIterations_;
        if (length_ == CustList.SIZE_EMPTY) {
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
        LoopVariable lv_ = new LoopVariable();
        lv_.setClassName(importedClassName);
        lv_.setIndexClassName(importedClassIndexName);
        lv_.setStruct(PrimitiveTypeUtil.unwrapObject(importedClassName, new LongStruct(fromValue_), stds_));
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
            _conf.getCoverage().passLoop(_conf, new Argument(new BooleanStruct(true)));
            return;
        }
        l_.setFinished(true);
        _conf.getCoverage().passLoop(_conf, new Argument(new BooleanStruct(false)));
    }

    public void incrementLoop(ContextEl _conf, LoopBlockStack _l,
            StringMap<LoopVariable> _vars) {
        _l.setIndex(_l.getIndex() + 1);
        _conf.getLastPage().setGlobalOffset(variableNameOffset);
        _conf.getLastPage().setOffset(0);
        String var_ = getVariableName();
        LoopVariable lv_ = _vars.getVal(var_);
        long o_ = ((NumberStruct) lv_.getStruct()).longStruct()+lv_.getStep();
        lv_.setStruct(PrimitiveTypeUtil.unwrapObject(importedClassName, new LongStruct(o_), _conf.getStandards()));
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
    public void abruptGroup(AnalyzingEl _anEl) {
        if (!_anEl.isReachable(this)) {
            _anEl.completeAbruptGroup(this);
        }
    }
}
