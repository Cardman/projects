package code.expressionlanguage.methods;
import code.expressionlanguage.AbstractPageEl;
import code.expressionlanguage.Analyzable;
import code.expressionlanguage.AnalyzedPageEl;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ConstType;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.CustomError;
import code.expressionlanguage.ElUtil;
import code.expressionlanguage.Mapping;
import code.expressionlanguage.OffsetBooleanInfo;
import code.expressionlanguage.OffsetStringInfo;
import code.expressionlanguage.OffsetsBlock;
import code.expressionlanguage.OperationsSequence;
import code.expressionlanguage.PrimitiveTypeUtil;
import code.expressionlanguage.ReadWrite;
import code.expressionlanguage.methods.util.BadImplicitCast;
import code.expressionlanguage.methods.util.BadVariableName;
import code.expressionlanguage.methods.util.DuplicateVariable;
import code.expressionlanguage.methods.util.UnexpectedOperationAffect;
import code.expressionlanguage.opers.AffectationOperation;
import code.expressionlanguage.opers.Calculation;
import code.expressionlanguage.opers.ExpressionLanguage;
import code.expressionlanguage.opers.OperationNode;
import code.expressionlanguage.opers.SettableAbstractFieldOperation;
import code.expressionlanguage.opers.SettableElResult;
import code.expressionlanguage.opers.VariableOperation;
import code.expressionlanguage.opers.util.AssignedBooleanVariables;
import code.expressionlanguage.opers.util.AssignedVariables;
import code.expressionlanguage.opers.util.Assignment;
import code.expressionlanguage.opers.util.AssignmentBefore;
import code.expressionlanguage.opers.util.BooleanAssignment;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.opers.util.ClassField;
import code.expressionlanguage.opers.util.FieldInfo;
import code.expressionlanguage.opers.util.SimpleAssignment;
import code.expressionlanguage.opers.util.StdStruct;
import code.expressionlanguage.stacks.LoopBlockStack;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.variables.LocalVariable;
import code.expressionlanguage.variables.LoopVariable;
import code.sml.Element;
import code.util.CustList;
import code.util.EntryCust;
import code.util.IdMap;
import code.util.ObjectMap;
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

    public ForIterativeLoop(Element _el, ContextEl _importingPage, int _indexChild,
            BracedBlock _m) {
        super(_el, _importingPage, _indexChild, _m);
        className = _el.getAttribute(ATTRIBUTE_CLASS);
        variableName = _el.getAttribute(ATTRIBUTE_VAR);
        init = _el.getAttribute(ATTRIBUTE_INIT);
        expression = _el.getAttribute(ATTRIBUTE_EXPRESSION);
        step = _el.getAttribute(ATTRIBUTE_STEP);
        eq = _el.hasAttribute(ATTRIBUTE_EQ);
        String classIndex_ = _el.getAttribute(ATTRIBUTE_CLASS_INDEX);
        if (classIndex_.isEmpty()) {
            classIndex_ = _importingPage.getStandards().getAliasPrimLong();
        }
        classIndexName = classIndex_;
        setAlwaysSkipped(true);
    }

    public ForIterativeLoop(ContextEl _importingPage, int _indexChild,
            BracedBlock _m,
            OffsetStringInfo _className, OffsetStringInfo _variable,
            OffsetStringInfo _from,
            OffsetStringInfo _to, OffsetBooleanInfo _eq, OffsetStringInfo _step, OffsetStringInfo _classIndex, OffsetStringInfo _label, OffsetsBlock _offset) {
        super(_importingPage, _indexChild, _m, _offset);
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
        setAlwaysSkipped(true);
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

    @Override
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

    @Override
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
            mapping_.setArg(cl_);
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
        if (!StringList.isWord(variableName)) {
            BadVariableName b_ = new BadVariableName();
            b_.setFileName(getFile().getFileName());
            b_.setRc(getRowCol(0, variableNameOffset));
            b_.setVarName(variableName);
            _cont.getClasses().addError(b_);
        }
        page_.setGlobalOffset(initOffset);
        page_.setOffset(0);
        _cont.setRootAffect(false);
        opInit = ElUtil.getAnalyzedOperations(init, _cont, Calculation.staticCalculation(f_.isStaticContext()));
        if (opInit.isEmpty()) {
            return;
        }
        OperationNode initEl_ = opInit.last();
        if (!PrimitiveTypeUtil.canBeUseAsArgument(cl_, initEl_.getResultClass().getName(), _cont)) {
            Mapping mapping_ = new Mapping();
            mapping_.setArg(initEl_.getResultClass().getName());
            mapping_.setParam(cl_);
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
        if (!PrimitiveTypeUtil.canBeUseAsArgument(cl_, expressionEl_.getResultClass().getName(), _cont)) {
            Mapping mapping_ = new Mapping();
            mapping_.setArg(expressionEl_.getResultClass().getName());
            mapping_.setParam(cl_);
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
        if (!PrimitiveTypeUtil.canBeUseAsArgument(cl_, stepEl_.getResultClass().getName(), _cont)) {
            Mapping mapping_ = new Mapping();
            mapping_.setArg(stepEl_.getResultClass().getName());
            mapping_.setParam(cl_);
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
        AssignedBooleanVariables res_ = (AssignedBooleanVariables) _cont.getAnalyzing().getAssignedVariables().getFinalVariables().getVal(this);
        for (EntryCust<ClassField,Assignment> e: res_.getFields().lastValue().entryList()) {
            BooleanAssignment ba_ = e.getValue().toBoolAssign().copy();
            res_.getFieldsRootAfter().put(e.getKey(), ba_);
        }
        for (StringMap<Assignment> s: res_.getVariables().lastValue()) {
            StringMap<BooleanAssignment> sm_;
            sm_ = new StringMap<BooleanAssignment>();
            for (EntryCust<String,Assignment> e: s.entryList()) {
                BooleanAssignment ba_ = e.getValue().toBoolAssign().copy();
                sm_.put(e.getKey(), ba_);
            }
            res_.getVariablesRootAfter().add(sm_);
        }
    }
    @Override
    public void setAssignmentBeforeChild(Analyzable _an, AnalyzingEl _anEl) {
        Block firstChild_ = getFirstChild();
        IdMap<Block, AssignedVariables> id_ = _an.getAssignedVariables().getFinalVariables();
        AssignedVariables parAss_ = id_.getVal(this);
        AssignedVariables assBl_ = firstChild_.buildNewAssignedVariable();
        for (EntryCust<ClassField, SimpleAssignment> e: parAss_.getFieldsRoot().entryList()) {
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
        id_.put(firstChild_, assBl_);
    }
    @Override
    public void defaultAssignmentBefore(Analyzable _an, OperationNode _root) {
        AssignedVariables vars_ = _an.getAssignedVariables().getFinalVariables().getVal(this);
        ObjectMap<ClassField,AssignmentBefore> fields_;
        CustList<StringMap<AssignmentBefore>> variables_;
        fields_ = new ObjectMap<ClassField,AssignmentBefore>();
        variables_ = new CustList<StringMap<AssignmentBefore>>();
        for (EntryCust<ClassField,AssignmentBefore> e: vars_.getFieldsRootBefore().entryList()) {
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
        Block last_ = firstChild_;
        while (last_.getNextSibling() != null) {
            last_ = last_.getNextSibling();
        }
        IdMap<Block, AssignedVariables> id_;
        id_ = _an.getAssignedVariables().getFinalVariables();
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
        ObjectMap<ClassField,AssignmentBefore> fieldsHypot_;
        fieldsHypot_ = makeHypothesisFields(_an);
        CustList<StringMap<AssignmentBefore>> varsHypot_;
        varsHypot_ = makeHypothesisVars(_an);
        for (EntryCust<ContinueBlock, Loop> e: _anEl.getContinuables().entryList()) {
            if (e.getValue() != this) {
                continue;
            }
            AssignedVariables vars_;
            vars_ = id_.getVal(e.getKey());
            for (EntryCust<ClassField,AssignmentBefore> f: vars_.getFieldsRootBefore().entryList()) {
                if (!f.getValue().isUnassignedBefore()) {
                    fieldsHypot_.getVal(f.getKey()).setUnassignedBefore(false);
                }
            }
            int index_ = 0;
            for (StringMap<AssignmentBefore> s: vars_.getVariablesRootBefore()) {
                if (index_ >= varsHypot_.size()) {
                    continue;
                }
                for (EntryCust<String,AssignmentBefore> f: s.entryList()) {
                    if (!f.getValue().isUnassignedBefore()) {
                        varsHypot_.get(index_).getVal(f.getKey()).setUnassignedBefore(false);
                    }
                }
                index_++;
            }
        }
        int index_ = 0;
        if (_anEl.canCompleteNormallyGroup(last_)) {
            AssignedVariables vars_;
            vars_ = id_.getVal(last_);
            for (EntryCust<ClassField,SimpleAssignment> f: vars_.getFieldsRoot().entryList()) {
                if (!f.getValue().isUnassignedAfter()) {
                    fieldsHypot_.getVal(f.getKey()).setUnassignedBefore(false);
                }
            }
            for (StringMap<SimpleAssignment> s: vars_.getVariablesRoot()) {
                if (index_ >= varsHypot_.size()) {
                    continue;
                }
                for (EntryCust<String,SimpleAssignment> f: s.entryList()) {
                    if (!f.getValue().isUnassignedAfter()) {
                        varsHypot_.get(index_).getVal(f.getKey()).setUnassignedBefore(false);
                    }
                }
                index_++;
            }
        }
        varsWhile_.getFieldsRootBefore().putAllMap(fieldsHypot_);
        varsWhile_.getVariablesRootBefore().clear();
        varsWhile_.getVariablesRootBefore().addAllElts(varsHypot_);
        processFinalFields(_an, _anEl, allDesc_, fieldsHypot_);
        processFinalVars(_an, _anEl, allDesc_, varsHypot_);
        ObjectMap<ClassField,SimpleAssignment> fieldsAfter_;
        fieldsAfter_ = new ObjectMap<ClassField,SimpleAssignment>();
        CustList<StringMap<SimpleAssignment>> varsAfter_;
        varsAfter_ = new CustList<StringMap<SimpleAssignment>>();
        for (EntryCust<ClassField,BooleanAssignment> e: varsWhile_.getFieldsRootAfter().entryList()) {
            BooleanAssignment ba_ = e.getValue();
            boolean ass_ = ba_.isAssignedAfterWhenFalse();
            boolean unass_ = ba_.isUnassignedAfterWhenFalse();
            if (!varsWhile_.getFieldsRootBefore().getVal(e.getKey()).isAssignedBefore()) {
                ass_ = false;
            }
            if (!varsWhile_.getFieldsRootBefore().getVal(e.getKey()).isUnassignedBefore()) {
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
            fieldsAfter_.put(key_, Assignment.assignClassic(ass_, unass_));
        }
        varsWhile_.getFieldsRoot().putAllMap(fieldsAfter_);
        index_ = 0;
        for (StringMap<BooleanAssignment> s: varsWhile_.getVariablesRootAfter()) {
            StringMap<SimpleAssignment> sm_ = new StringMap<SimpleAssignment>();
            for (EntryCust<String,BooleanAssignment> e: s.entryList()) {
                BooleanAssignment ba_ = e.getValue();
                boolean ass_ = ba_.isAssignedAfterWhenFalse();
                boolean unass_ = ba_.isUnassignedAfterWhenFalse();
                StringMap<AssignmentBefore> assThis_;
                assThis_ = varsWhile_.getVariablesRootBefore().get(index_);
                if (!assThis_.getVal(e.getKey()).isAssignedBefore()) {
                    ass_ = false;
                }
                if (!assThis_.getVal(e.getKey()).isUnassignedBefore()) {
                    unass_ = false;
                }
                for (EntryCust<BreakBlock, BreakableBlock> f: _anEl.getBreakables().entryList()) {
                    if (f.getValue() != this) {
                        continue;
                    }
                    CustList<StringMap<AssignmentBefore>> list_;
                    list_ = id_.getVal(f.getKey()).getVariablesRootBefore();
                    if (!list_.isValidIndex(index_)) {
                        continue;
                    }
                    StringMap<AssignmentBefore> assB_;
                    assB_ = list_.get(index_);
                    if (!assB_.contains(e.getKey())) {
                        continue;
                    }
                    if (!assB_.getVal(e.getKey()).isAssignedBefore()) {
                        ass_ = false;
                    }
                    if (!assB_.getVal(e.getKey()).isUnassignedBefore()) {
                        unass_ = false;
                    }
                }
                String key_ = e.getKey();
                sm_.put(key_, Assignment.assignClassic(ass_, unass_));
            }
            varsAfter_.add(sm_);
            index_++;
        }
        varsWhile_.getVariablesRoot().clear();
        varsWhile_.getVariablesRoot().addAllElts(varsAfter_);
    }
    private void processFinalFields(Analyzable _an,AnalyzingEl _anEl,
            IdMap<Block, AssignedVariables> _allDesc,
            ObjectMap<ClassField, AssignmentBefore> _fields) {
        AssignedVariables vars_;
        for (EntryCust<ClassField,AssignmentBefore> e: _fields.entryList()) {
            if (e.getValue().isUnassignedBefore()) {
                continue;
            }
            if (e.getValue().isAssignedBefore()) {
                continue;
            }
            ClassField key_ = e.getKey();
            FieldInfo meta_ = _an.getFieldInfo(key_);
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

    private void processFinalFields(Block _curBlock, Analyzable _an,AssignedVariables _vars, ClassField _field) {
        for (EntryCust<OperationNode, ObjectMap<ClassField,AssignmentBefore>> f: _vars.getFieldsBefore().entryList()) {
            if (!(f.getKey() instanceof AffectationOperation)) {
                continue;
            }
            AffectationOperation aff_ = (AffectationOperation) f.getKey();
            SettableElResult set_ = aff_.getSettable();
            if (!(set_ instanceof SettableAbstractFieldOperation)) {
                continue;
            }
            SettableAbstractFieldOperation cst_ = (SettableAbstractFieldOperation) set_;
            if (!cst_.matchFieldId(_field)) {
                continue;
            }
            cst_.setRelativeOffsetPossibleAnalyzable(cst_.getIndexInEl(), _an);
            UnexpectedOperationAffect un_ = new UnexpectedOperationAffect();
            un_.setFileName(_an.getCurrentFileName());
            un_.setRc(_curBlock.getRowCol(_an.getOffset(),_curBlock.getOffset().getOffsetTrim()));
            _an.getClasses().addError(un_);
        }
    }
    private void processFinalVars(Analyzable _an,AnalyzingEl _anEl,
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
    private void processFinalVars(Block _curBlock, Analyzable _an,AssignedVariables _vars, String _field) {
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
    public String getTagName() {
        return TAG_FOR;
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
        } else {
            ip_.getReadWrite().setBlock(getFirstChild());
            return;
        }
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
        ExpressionLanguage from_ = ip_.getCurrentEl(_conf,this, CustList.FIRST_INDEX, false, CustList.FIRST_INDEX);
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
        ExpressionLanguage to_ = ip_.getCurrentEl(_conf,this, CustList.SECOND_INDEX, false, CustList.SECOND_INDEX);
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
        ExpressionLanguage step_ = ip_.getCurrentEl(_conf,this, CustList.SECOND_INDEX + 1, false, CustList.SECOND_INDEX + 1);
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
    public void abruptGroup(Analyzable _an, AnalyzingEl _anEl) {
        if (!_anEl.isReachable(this)) {
            _anEl.completeAbrupt(this);
            _anEl.completeAbruptGroup(this);
        }
    }
}
