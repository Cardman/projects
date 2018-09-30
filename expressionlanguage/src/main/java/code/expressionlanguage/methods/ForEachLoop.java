package code.expressionlanguage.methods;
import code.expressionlanguage.AbstractPageEl;
import code.expressionlanguage.Analyzable;
import code.expressionlanguage.AnalyzedPageEl;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.CustomError;
import code.expressionlanguage.ElUtil;
import code.expressionlanguage.Mapping;
import code.expressionlanguage.OffsetStringInfo;
import code.expressionlanguage.OffsetsBlock;
import code.expressionlanguage.PrimitiveTypeUtil;
import code.expressionlanguage.ReadWrite;
import code.expressionlanguage.Templates;
import code.expressionlanguage.common.TypeUtil;
import code.expressionlanguage.methods.util.BadImplicitCast;
import code.expressionlanguage.methods.util.BadVariableName;
import code.expressionlanguage.methods.util.DuplicateVariable;
import code.expressionlanguage.methods.util.EmptyTagName;
import code.expressionlanguage.methods.util.StaticAccessError;
import code.expressionlanguage.methods.util.TypeVar;
import code.expressionlanguage.opers.Calculation;
import code.expressionlanguage.opers.ExpressionLanguage;
import code.expressionlanguage.opers.OperationNode;
import code.expressionlanguage.opers.util.AssignedBooleanVariables;
import code.expressionlanguage.opers.util.AssignedVariables;
import code.expressionlanguage.opers.util.AssignmentBefore;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.opers.util.NullStruct;
import code.expressionlanguage.opers.util.SimpleAssignment;
import code.expressionlanguage.opers.util.StdStruct;
import code.expressionlanguage.opers.util.Struct;
import code.expressionlanguage.stacks.LoopBlockStack;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.variables.LocalVariable;
import code.expressionlanguage.variables.LoopVariable;
import code.util.CustList;
import code.util.EntryCust;
import code.util.IdMap;
import code.util.StringList;
import code.util.StringMap;

public final class ForEachLoop extends BracedStack implements ForLoop {

    private String label;
    private int labelOffset;

    private final String className;

    private String importedClassName;

    private int classNameOffset;

    private final String classIndexName;

    private int classIndexNameOffset;

    private final String variableName;

    private int variableNameOffset;

    private final String expression;

    private int expressionOffset;

    private CustList<OperationNode> opList;

    private Boolean nativeCmp;

    public ForEachLoop(ContextEl _importingPage, int _indexChild,
            BracedBlock _m,
            OffsetStringInfo _className, OffsetStringInfo _variable,
            OffsetStringInfo _expression, OffsetStringInfo _classIndex, OffsetStringInfo _label, OffsetsBlock _offset) {
        super(_importingPage, _indexChild, _m, _offset);
        className = _className.getInfo();
        classNameOffset = _className.getOffset();
        variableName = _variable.getInfo();
        variableNameOffset = _variable.getOffset();
        expression = _expression.getInfo();
        expressionOffset = _expression.getOffset();
        String classIndex_ = _classIndex.getInfo();
        if (classIndex_.isEmpty()) {
            classIndex_ = _importingPage.getStandards().getAliasPrimLong();
        }
        classIndexName = classIndex_;
        label = _label.getInfo();
        labelOffset = _label.getOffset();
        classIndexNameOffset = _classIndex.getOffset();
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

    public int getVariableNameOffset() {
        return variableNameOffset;
    }

    public int getExpressionOffset() {
        return expressionOffset;
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

    public String getExpression() {
        return expression;
    }

    public ExpressionLanguage getEl() {
        return new ExpressionLanguage(opList);
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
    public void buildExpressionLanguage(ContextEl _cont) {
        FunctionBlock f_ = getFunction();
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
        AnalyzedPageEl page_ = _cont.getAnalyzing();
        page_.setGlobalOffset(expressionOffset);
        page_.setOffset(0);
        _cont.setRootAffect(false);
        opList = ElUtil.getAnalyzedOperations(expression, _cont, Calculation.staticCalculation(f_.isStaticContext()));
        if (opList.isEmpty()) {
            return;
        }
        page_.setGlobalOffset(classNameOffset);
        page_.setOffset(0);
        if (!StringList.quickEq(className.trim(), TypeUtil.VAR_TYPE)) {
            importedClassName = _cont.resolveCorrectType(className);
        } else {
            importedClassName = "";
        }
        OperationNode el_ = opList.last();
        el_.getResultClass().setCheckOnlyNullPe(true);
        Argument arg_ = el_.getArgument();
        if (Argument.isNullValue(arg_)) {
            StaticAccessError static_ = new StaticAccessError();
            static_.setFileName(_cont.getCurrentFileName());
            static_.setRc(_cont.getCurrentLocation());
            _cont.getClasses().addError(static_);
        } else if (el_.getResultClass().isArray()) {
            ClassArgumentMatching compo_ = PrimitiveTypeUtil.getQuickComponentType(el_.getResultClass());
            if (StringList.quickEq(className.trim(), TypeUtil.VAR_TYPE) && compo_.getNames().size() == 1) {
                importedClassName = compo_.getName();
            } else {
                Mapping mapping_ = new Mapping();
                if (importedClassName.isEmpty()) {
                    BadImplicitCast cast_ = new BadImplicitCast();
                    cast_.setMapping(mapping_);
                    cast_.setFileName(getFile().getFileName());
                    cast_.setRc(getRowCol(0, expressionOffset));
                    _cont.getClasses().addError(cast_);
                } else {
                    mapping_.setArg(compo_);
                    mapping_.setParam(importedClassName);
                    StringMap<StringList> vars_ = new StringMap<StringList>();
                    if (!f_.isStaticContext()) {
                        String globalClass_ = page_.getGlobalClass();
                        String curClassBase_ = Templates.getIdFromAllTypes(globalClass_);
                        for (TypeVar t: _cont.getClasses().getClassBody(curClassBase_).getParamTypesMapValues()) {
                            vars_.put(t.getName(), t.getConstraints());
                        }
                    }
                    mapping_.setMapping(vars_);
                    if (!Templates.isGenericCorrect(mapping_, _cont)) {
                        BadImplicitCast cast_ = new BadImplicitCast();
                        cast_.setMapping(mapping_);
                        cast_.setFileName(getFile().getFileName());
                        cast_.setRc(getRowCol(0, expressionOffset));
                        _cont.getClasses().addError(cast_);
                    }
                }
            }
        } else {
            StringList names_ = el_.getResultClass().getNames();
            StringList out_ = new StringList();
            for (String f: names_) {
                String type_ = Templates.getIterableFullTypeByStds(f, _cont);
                String iterable_ = _cont.getStandards().getAliasIterable();
                if (type_ == null) {
                    type_ = Templates.getFullTypeByBases(f, iterable_, _cont);
                    nativeCmp = false;
                } else {
                    nativeCmp = true;
                }
                if (type_ != null) {
                    out_.add(type_);
                }
            }
            out_.removeDuplicates();
            if (out_.size() == 1) {
                String type_ = out_.first();
                Mapping mapping_ = new Mapping();
                String paramArg_ = Templates.getAllTypes(type_).last();
                if (StringList.quickEq(paramArg_, Templates.SUB_TYPE)) {
                    paramArg_ = _cont.getStandards().getAliasObject();
                } else if (paramArg_.startsWith(Templates.SUB_TYPE)) {
                    paramArg_ = paramArg_.substring(Templates.SUB_TYPE.length());
                } else if (paramArg_.startsWith(Templates.SUP_TYPE)){
                    paramArg_ = _cont.getStandards().getAliasObject();
                }
                if (StringList.quickEq(className.trim(), TypeUtil.VAR_TYPE)) {
                    importedClassName = paramArg_;
                } else {
                    mapping_.setArg(paramArg_);
                    mapping_.setParam(importedClassName);
                    StringMap<StringList> vars_ = new StringMap<StringList>();
                    if (!f_.isStaticContext()) {
                        String globalClass_ = page_.getGlobalClass();
                        String curClassBase_ = Templates.getIdFromAllTypes(globalClass_);
                        for (TypeVar t: _cont.getClasses().getClassBody(curClassBase_).getParamTypesMapValues()) {
                            vars_.put(t.getName(), t.getConstraints());
                        }
                    }
                    mapping_.setMapping(vars_);
                    if (!Templates.isGenericCorrect(mapping_, _cont)) {
                        BadImplicitCast cast_ = new BadImplicitCast();
                        cast_.setMapping(mapping_);
                        cast_.setFileName(getFile().getFileName());
                        cast_.setRc(getRowCol(0, expressionOffset));
                        _cont.getClasses().addError(cast_);
                    }
                }
            } else {
                String iterable_ = _cont.getStandards().getAliasIterable();
                for (String e: out_) {
                    Mapping mapping_ = new Mapping();
                    mapping_.setArg(e);
                    mapping_.setParam(iterable_);
                    BadImplicitCast cast_ = new BadImplicitCast();
                    cast_.setMapping(mapping_);
                    cast_.setFileName(getFile().getFileName());
                    cast_.setRc(getRowCol(0, expressionOffset));
                    _cont.getClasses().addError(cast_);
                }
            }
        }
        LoopVariable lv_ = new LoopVariable();
        if (!importedClassName.isEmpty()) {
            lv_.setClassName(importedClassName);
        } else {
            lv_.setClassName(_cont.getStandards().getAliasObject());
        }
        lv_.setIndexClassName(classIndexName);
        _cont.getAnalyzing().putVar(variableName, lv_);
        buildConditions(_cont);
    }

    @Override
    public void setAssignmentAfter(Analyzable _an, AnalyzingEl _anEl) {
        Block firstChild_ = getFirstChild();
        IdMap<Block, AssignedVariables> id_;
        id_ = _an.getAssignedVariables().getFinalVariables();
        IdMap<Block, AssignedVariables> allDesc_ = new IdMap<Block, AssignedVariables>();
        boolean add_ = false;
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
            StringMap<SimpleAssignment> fieldsAfter_;
            fieldsAfter_ = buildAssListFieldAfter(_an, _anEl);
            varsWhile_.getFieldsRoot().putAllMap(fieldsAfter_);
            CustList<StringMap<SimpleAssignment>> varsAfter_;
            varsAfter_ = buildAssListLocVarAfter(_an, _anEl);
            varsWhile_.getVariablesRoot().clear();
            varsWhile_.getVariablesRoot().addAllElts(varsAfter_);
            CustList<StringMap<SimpleAssignment>> mutableAfter_;
            mutableAfter_ = buildAssListMutableLoopAfter(_an, _anEl);
            varsWhile_.getMutableLoopRoot().clear();
            varsWhile_.getMutableLoopRoot().addAllElts(mutableAfter_);
            return;
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
        fieldsAfter_= buildAssListFieldAfter(_an, _anEl);
        varsWhile_.getFieldsRoot().putAllMap(fieldsAfter_);
        varsAfter_ = buildAssListLocVarAfter(_an, _anEl);
        varsWhile_.getVariablesRoot().clear();
        varsWhile_.getVariablesRoot().addAllElts(varsAfter_);
        mutableAfter_ = buildAssListMutableLoopAfter(_an, _anEl);
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
    protected AssignedBooleanVariables buildNewAssignedVariable() {
        return new AssignedBooleanVariables();
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
            if (c_.isEvaluatingKeepLoop()) {
                processLastElementLoop(_cont);
                return;
            }
            if (c_.isFinished()) {
                removeVarAndLoop(ip_);
                processBlock(_cont);
                return;
            }
            ip_.getReadWrite().setBlock(getFirstChild());
            return;
        }
        LgNames stds_ = _cont.getStandards();
        Struct its_ = processLoop(_cont);
        if (_cont.callsOrException()) {
            return;
        }
        Struct iterStr_ = null;
        long length_ = CustList.INDEX_NOT_FOUND_ELT;
        boolean finished_ = false;
        OperationNode el_ = opList.last();
        if (el_.getResultClass().isArray()) {
            length_ = LgNames.getLength(its_.getInstance());
            if (length_ == CustList.SIZE_EMPTY) {
                finished_ = true;
            }
        } else {
            boolean native_ = nativeCmp;
            String locName_ = _cont.getClasses().getIteratorVar(native_);
            LocalVariable locVar_ = new LocalVariable();
            locVar_.setClassName(stds_.getStructClassName(its_, _cont));
            locVar_.setStruct(its_);
            _cont.getLastPage().getInternVars().put(locName_, locVar_);
            ExpressionLanguage dyn_ = _cont.getLastPage().getCurrentEl(_cont,this, CustList.SECOND_INDEX, native_,CustList.SECOND_INDEX);
            Argument arg_ = dyn_.calculateMember(_cont);
            if (_cont.callsOrException()) {
                return;
            }
            _cont.getLastPage().clearCurrentEls();
            iterStr_ = arg_.getStruct();
        }
        LoopBlockStack l_ = new LoopBlockStack();
        l_.setIndex(-1);
        l_.setFinished(finished_);
        l_.setBlock(this);
        l_.setStructIterator(iterStr_);
        l_.setMaxIteration(length_);
        ip_.addBlock(l_);
        ip_.clearCurrentEls();
        l_.setEvaluatingKeepLoop(true);
        String indexClassName_;
        indexClassName_ = getClassIndexName();
        LoopVariable lv_ = new LoopVariable();
        lv_.setIndex(-1);
        lv_.setClassName(importedClassName);
        lv_.setIndexClassName(indexClassName_);
        lv_.setContainer(its_);
        StringMap<LoopVariable> varsLoop_ = ip_.getVars();
        String var_ = getVariableName();
        varsLoop_.put(var_, lv_);
        if (iterStr_ != null) {
            Boolean has_ = iteratorHasNext(_cont);
            if (has_ == null) {
                return;
            }
            finished_ = !has_;
            if (_cont.callsOrException()) {
                return;
            }
        }
        if (finished_) {
            removeVarAndLoop(ip_);
            _cont.getLastPage().clearCurrentEls();
            l_.setEvaluatingKeepLoop(false);
            processBlock(_cont);
            return;
        }
        StringMap<LoopVariable> vars_ = ip_.getVars();
        incrementLoop(_cont, l_, vars_);
        if (_cont.callsOrException()) {
            return;
        }
        l_.setEvaluatingKeepLoop(false);
        ip_.getReadWrite().setBlock(getFirstChild());
        return;
    }

    Struct processLoop(ContextEl _conf) {
        AbstractPageEl ip_ = _conf.getLastPage();
        ip_.setGlobalOffset(expressionOffset);
        ip_.setOffset(0);
        ExpressionLanguage el_ = ip_.getCurrentEl(_conf, this, CustList.FIRST_INDEX, false, CustList.FIRST_INDEX);
        Argument arg_ = el_.calculateMember(_conf);
        if (_conf.callsOrException()) {
            return NullStruct.NULL_VALUE;
        }
        Struct ito_ = arg_.getStruct();
        return ito_;
        
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
        l_.setEvaluatingKeepLoop(true);
        boolean hasNext_;
        if (l_.getStructIterator() != null) {
            Boolean has_ = iteratorHasNext(_conf);
            if (has_ == null) {
                return;
            }
            hasNext_ = has_;
        } else {
            hasNext_ = l_.hasNext();
        }
        
        if (hasNext_) {
            incrementLoop(_conf, l_, vars_);
            if (_conf.callsOrException()) {
                if (_conf.calls()) {
                    return;
                }
                l_.setEvaluatingKeepLoop(false);
                return;
            }
        } else {
            _conf.getLastPage().clearCurrentEls();
            l_.setFinished(true);
        }
        l_.setEvaluatingKeepLoop(false);
    }

    private Boolean iteratorHasNext(ContextEl _conf) {
        AbstractPageEl ip_ = _conf.getLastPage();
        LgNames stds_ = _conf.getStandards();
        LoopBlockStack l_ = (LoopBlockStack) ip_.getLastStack();
        Struct strIter_ = l_.getStructIterator();
        String locName_ = _conf.getClasses().getHasNextVar(nativeCmp);
        LocalVariable locVar_ = new LocalVariable();
        locVar_.setClassName(stds_.getStructClassName(strIter_, _conf));
        locVar_.setStruct(strIter_);
        _conf.getLastPage().getInternVars().put(locName_, locVar_);
        ExpressionLanguage dyn_ = _conf.getLastPage().getCurrentEl(_conf,this, CustList.FIRST_INDEX, nativeCmp, 2);
        Argument arg_ = dyn_.calculateMember(_conf);
        if (_conf.callsOrException()) {
            return null;
        }
        boolean hasNext_ = (Boolean) arg_.getObject();
        return hasNext_;
    }

    @Override
    public void incrementLoop(ContextEl _conf, LoopBlockStack _l,
            StringMap<LoopVariable> _vars) {
        _l.setIndex(_l.getIndex() + 1);

        _conf.getLastPage().setGlobalOffset(variableNameOffset);
        _conf.getLastPage().setOffset(0);
        String var_ = getVariableName();
        LgNames stds_ = _conf.getStandards();
        String null_ = stds_.getAliasNullPe();
        LoopVariable lv_ = _vars.getVal(var_);
        Struct iterator_ = _l.getStructIterator();
        Struct element_;
        OperationNode el_ = opList.last();
        if (!el_.getResultClass().isArray()) {
            boolean native_ = nativeCmp;
            String locName_ = _conf.getClasses().getNextVar(native_);
            LocalVariable locVar_ = new LocalVariable();
            locVar_.setClassName(stds_.getStructClassName(iterator_, _conf));
            locVar_.setStruct(iterator_);
            _conf.getLastPage().getInternVars().put(locName_, locVar_);
            ExpressionLanguage dyn_ = _conf.getLastPage().getCurrentEl(_conf,this, CustList.SECOND_INDEX, native_, 3);
            Argument arg_ = dyn_.calculateMember(_conf);
            if (_conf.callsOrException()) {
                return;
            }
            _conf.getLastPage().clearCurrentEls();
            element_ = arg_.getStruct();
        } else {
            element_ = LgNames.getElement(lv_.getContainer().getInstance(), (int) _l.getIndex(), _conf);
        }
        if (PrimitiveTypeUtil.primitiveTypeNullObject(importedClassName, element_, _conf)) {
            _conf.setException(new StdStruct(new CustomError(_conf.joinPages()),null_));
            return;
        }
        if (!element_.isNull()) {
            String className_ = _conf.getLastPage().formatVarType(importedClassName, _conf);
            String argCl_ = stds_.getStructClassName(element_, _conf);
            Mapping mapping_ = new Mapping();
            mapping_.setArg(argCl_);
            mapping_.setParam(className_);
            if (!Templates.isCorrect(mapping_, _conf)) {
                String cast_ = stds_.getAliasCast();
                _conf.setException(new StdStruct(new CustomError(_conf.joinPages()),cast_));
                return;
            }
        }
        lv_.setStruct(element_);
        lv_.setIndex(lv_.getIndex() + 1);
    }

    @Override
    public ExpressionLanguage getEl(ContextEl _context, boolean _native,
            int _indexProcess) {
        if (_indexProcess == 0) {
            return getEl();
        }
        if (_indexProcess == 1) {
            return _context.getClasses().getEqIterator(_native);
        }
        if (_indexProcess == 2) {
            return _context.getClasses().getEqHasNext(_native);
        }
        return _context.getClasses().getEqNext(_native);
    }

    @Override
    public void abruptGroup(Analyzable _an, AnalyzingEl _anEl) {
        if (!_anEl.isReachable(this)) {
            _anEl.completeAbrupt(this);
            _anEl.completeAbruptGroup(this);
        }
    }
}
