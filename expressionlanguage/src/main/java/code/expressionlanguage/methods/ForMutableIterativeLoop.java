package code.expressionlanguage.methods;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.AnalyzedPageEl;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.ElUtil;
import code.expressionlanguage.Mapping;
import code.expressionlanguage.OffsetStringInfo;
import code.expressionlanguage.OffsetsBlock;
import code.expressionlanguage.PrimitiveTypeUtil;
import code.expressionlanguage.methods.util.BadImplicitCast;
import code.expressionlanguage.opers.Calculation;
import code.expressionlanguage.opers.ExpressionLanguage;
import code.expressionlanguage.opers.OperationNode;
import code.expressionlanguage.opers.util.AssignedBooleanVariables;
import code.expressionlanguage.opers.util.AssignedVariables;
import code.expressionlanguage.opers.util.Assignment;
import code.expressionlanguage.opers.util.AssignmentBefore;
import code.expressionlanguage.opers.util.BooleanAssignment;
import code.expressionlanguage.opers.util.SimpleAssignment;
import code.expressionlanguage.stacks.LoopBlockStack;
import code.expressionlanguage.variables.LoopVariable;
import code.sml.Element;
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

    private final String init;
    private int initOffset;

    private final String expression;
    private int expressionOffset;

    private final String step;
    private int stepOffset;

    private CustList<OperationNode> opInit;

    private CustList<OperationNode> opExp;

    private CustList<OperationNode> opStep;
    public ForMutableIterativeLoop(Element _el, ContextEl _importingPage,
            int _indexChild, BracedBlock _m) {
        super(_el, _importingPage, _indexChild, _m);
        className = _el.getAttribute(ATTRIBUTE_CLASS);
        init = _el.getAttribute(ATTRIBUTE_INIT);
        expression = _el.getAttribute(ATTRIBUTE_EXPRESSION);
        step = _el.getAttribute(ATTRIBUTE_STEP);
        String classIndex_ = _el.getAttribute(ATTRIBUTE_CLASS_INDEX);
        if (classIndex_.isEmpty()) {
            classIndex_ = _importingPage.getStandards().getAliasPrimLong();
        }
        classIndexName = classIndex_;
        setAlwaysSkipped(true);
    }

    public ForMutableIterativeLoop(ContextEl _importingPage, int _indexChild,
            BracedBlock _m,
            OffsetStringInfo _className, OffsetStringInfo _variable,
            OffsetStringInfo _from,
            OffsetStringInfo _to, OffsetStringInfo _step, OffsetStringInfo _classIndex, OffsetStringInfo _label, OffsetsBlock _offset) {
        super(_importingPage, _indexChild, _m, _offset);
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
        setAlwaysSkipped(true);
    }

    @Override
    public void setAssignmentBeforeNextSibling(Analyzable _an, AnalyzingEl _anEl) {
        IdMap<Block, AssignedVariables> id_ = _an.getAssignedVariables().getFinalVariables();
        AssignedVariables prevAss_ = id_.getVal(this);
        Block nextSibling_ = getNextSibling();
        AssignedVariables assBl_ = nextSibling_.buildNewAssignedVariable();
        for (EntryCust<String, SimpleAssignment> e: prevAss_.getFieldsRoot().entryList()) {
            AssignmentBefore asBef_ = e.getValue().assignBefore();
            assBl_.getFieldsRootBefore().put(e.getKey(), asBef_);
        }
        for (StringMap<SimpleAssignment> s: prevAss_.getVariablesRoot()) {
            StringMap<AssignmentBefore> sm_ = new StringMap<AssignmentBefore>();
            for (EntryCust<String, SimpleAssignment> e: s.entryList()) {
                AssignmentBefore asBef_ = e.getValue().assignBefore();
                sm_.put(e.getKey(), asBef_);
            }
            assBl_.getVariablesRootBefore().add(sm_);
        }
        CustList<StringMap<SimpleAssignment>> mutable_ = prevAss_.getMutableLoopRoot();
        for (StringMap<SimpleAssignment> s: mutable_.sub(0, mutable_.size()-1)) {
            StringMap<AssignmentBefore> sm_ = new StringMap<AssignmentBefore>();
            for (EntryCust<String, SimpleAssignment> e: s.entryList()) {
                AssignmentBefore asBef_ = e.getValue().assignBefore();
                sm_.put(e.getKey(), asBef_);
            }
            assBl_.getMutableLoopRootBefore().add(sm_);
        }
        assBl_.getMutableLoopRootBefore().add(new StringMap<AssignmentBefore>());
        id_.put(nextSibling_, assBl_);
    }
    @Override
    public void processLastElementLoop(ContextEl _conf) {
        // TODO Auto-generated method stub

    }

    @Override
    public String getLabel() {
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
            importedClassName = _cont.resolveCorrectType(className);
            _cont.setMerged(true);
//            _cont.setFinalVariable(finalVariable);
            _cont.setCurrentVarSetting(importedClassName);
        } else {
            _cont.setMerged(false);
        }
        
        _cont.getVariablesNames().clear();
        page_.setGlobalOffset(initOffset);
        page_.setOffset(0);
        _cont.setRootAffect(false);
        opInit = ElUtil.getAnalyzedOperations(init, _cont, Calculation.staticCalculation(f_.isStaticContext()));
        if (_cont.isMerged()) {
            StringList vars_ = _cont.getVariablesNames();
            getVariableNames().addAllElts(vars_);
        }
        page_.setGlobalOffset(expressionOffset);
        page_.setOffset(0);
        opExp = ElUtil.getAnalyzedOperations(expression, _cont, Calculation.staticCalculation(f_.isStaticContext()));
        if (!opExp.isEmpty()) {
            buildConditions(_cont);
        } else {
            AssignedBooleanVariables res_ = (AssignedBooleanVariables) _cont.getAnalyzing().getAssignedVariables().getFinalVariables().getVal(this);
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
    public void setAssignmentAfter(Analyzable _an, AnalyzingEl _anEl) {
        _an.setMerged(false);
        Block firstChild_ = getFirstChild();
        FunctionBlock f_ = getFunction();
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
            AnalyzedPageEl page_ = _an.getAnalyzing();
            page_.setGlobalOffset(stepOffset);
            page_.setOffset(0);
            opStep = ElUtil.getAnalyzedOperations(step, (ContextEl) _an, Calculation.staticCalculation(f_.isStaticContext()));
            return;
        }
        AnalyzedPageEl page_ = _an.getAnalyzing();
        page_.setGlobalOffset(stepOffset);
        page_.setOffset(0);
        opStep = ElUtil.getAnalyzedOperations(step, (ContextEl) _an, Calculation.staticCalculation(f_.isStaticContext()));
        StringMap<SimpleAssignment> fieldsAfter_;
        fieldsAfter_= buildAssListFieldAfter(_an, _anEl);
        varsWhile_.getFieldsRoot().putAllMap(fieldsAfter_);
        CustList<StringMap<SimpleAssignment>> varsAfter_;
        CustList<StringMap<SimpleAssignment>> mutableAfter_;
        varsAfter_ = buildAssListLocVarAfter(_an, _anEl);
        varsWhile_.getVariablesRoot().clear();
        varsWhile_.getVariablesRoot().addAllElts(varsAfter_);
        mutableAfter_ = buildAssListMutableLoopAfter(_an, _anEl);
        varsWhile_.getMutableLoopRoot().clear();
        varsWhile_.getMutableLoopRoot().addAllElts(mutableAfter_);
    }
    @Override
    public void processEl(ContextEl _cont) {
        // TODO Auto-generated method stub

    }

    @Override
    public void exitStack(ContextEl _context) {
        // TODO Auto-generated method stub

    }

    @Override
    public void incrementLoop(ContextEl _conf, LoopBlockStack _l,
            StringMap<LoopVariable> _vars) {
        // TODO Auto-generated method stub

    }

    @Override
    public ExpressionLanguage getEl(ContextEl _context, boolean _native,
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
    public String getTagName() {
        return TAG_FOR;
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
