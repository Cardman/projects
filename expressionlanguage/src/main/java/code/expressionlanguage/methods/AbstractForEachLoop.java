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
import code.expressionlanguage.errors.custom.StaticAccessError;
import code.expressionlanguage.files.OffsetStringInfo;
import code.expressionlanguage.files.OffsetsBlock;
import code.expressionlanguage.inherits.Mapping;
import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.instr.ElUtil;
import code.expressionlanguage.methods.util.TypeVar;
import code.expressionlanguage.opers.Calculation;
import code.expressionlanguage.opers.ExpressionLanguage;
import code.expressionlanguage.opers.exec.ExecInvokingOperation;
import code.expressionlanguage.opers.exec.ExecOperationNode;
import code.expressionlanguage.opers.util.AssignedBooleanVariables;
import code.expressionlanguage.opers.util.AssignedVariables;
import code.expressionlanguage.opers.util.AssignmentBefore;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.opers.util.SimpleAssignment;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.stacks.LoopBlockStack;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.ArrayStruct;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.ErrorStruct;
import code.expressionlanguage.structs.LongStruct;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.variables.LocalVariable;
import code.expressionlanguage.variables.LoopVariable;
import code.util.CustList;
import code.util.EntryCust;
import code.util.IdMap;
import code.util.StringList;
import code.util.StringMap;

public abstract class AbstractForEachLoop extends BracedStack implements ForLoop {

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

    private CustList<ExecOperationNode> opList;

    protected AbstractForEachLoop(ContextEl _importingPage,
            BracedBlock _m,
            OffsetStringInfo _className, OffsetStringInfo _variable,
            OffsetStringInfo _expression, OffsetStringInfo _classIndex, OffsetStringInfo _label, OffsetsBlock _offset) {
        super(_importingPage, _m, _offset);
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

    public int getExpressionOffset() {
        return expressionOffset;
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

    public String getExpression() {
        return expression;
    }

    public ExpressionLanguage getEl() {
        return new ExpressionLanguage(opList);
    }

    @Override
    public void reduce(ContextEl _context) {
        ExecOperationNode r_ = opList.last();
        opList = ElUtil.getReducedNodes(r_);
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
    public String getImportedClassName() {
        return importedClassName;
    }
    public void buildEl(ContextEl _cont) {
        FunctionBlock f_ = getFunction();
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
        AnalyzedPageEl page_ = _cont.getAnalyzing();
        page_.setGlobalOffset(classNameOffset);
        page_.setOffset(0);
        KeyWords keyWords_ = _cont.getKeyWords();
        String keyWordVar_ = keyWords_.getKeyWordVar();
        if (!StringList.quickEq(className.trim(), keyWordVar_)) {
            importedClassName = _cont.resolveCorrectType(className);
        } else {
            importedClassName = "";
        }
        page_.setGlobalOffset(expressionOffset);
        page_.setOffset(0);
        boolean static_ = true;
        if (f_ != null) {
            static_ = f_.isStaticContext();
        }
        opList = ElUtil.getAnalyzedOperations(expression, _cont, Calculation.staticCalculation(static_));
    }
    public void inferArrayClass(ContextEl _cont) {
        FunctionBlock f_ = getFunction();
        if (f_ == null) {
            importedClassName = _cont.getStandards().getAliasObject();
            return;
        }
        AnalyzedPageEl page_ = _cont.getAnalyzing();
        ExecOperationNode el_ = opList.last();
        ClassArgumentMatching compo_ = PrimitiveTypeUtil.getQuickComponentType(el_.getResultClass());
        KeyWords keyWords_ = _cont.getKeyWords();
        String keyWordVar_ = keyWords_.getKeyWordVar();
        if (StringList.quickEq(className.trim(), keyWordVar_) && compo_.getNames().size() == 1) {
            importedClassName = compo_.getName();
        } else {
            Mapping mapping_ = new Mapping();
            if (importedClassName.isEmpty()) {
                BadImplicitCast cast_ = new BadImplicitCast();
                cast_.setMapping(mapping_);
                cast_.setFileName(getFile().getFileName());
                cast_.setIndexFile(expressionOffset);
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
                if (!Templates.isCorrectOrNumbers(mapping_, _cont)) {
                    BadImplicitCast cast_ = new BadImplicitCast();
                    cast_.setMapping(mapping_);
                    cast_.setFileName(getFile().getFileName());
                    cast_.setIndexFile(expressionOffset);
                    _cont.getClasses().addError(cast_);
                }
            }
        }
    }
    @Override
    public void buildExpressionLanguage(ContextEl _cont) {
        buildEl(_cont);
        ExecOperationNode el_ = opList.last();
        Argument arg_ = el_.getArgument();
        if (Argument.isNullValue(arg_)) {
            StaticAccessError static_ = new StaticAccessError();
            static_.setFileName(_cont.getCurrentFileName());
            static_.setIndexFile(_cont.getCurrentLocationIndex());
            _cont.getClasses().addError(static_);
        } else if (el_.getResultClass().isArray()) {
            inferArrayClass(_cont);
        } else {
            StringList names_ = el_.getResultClass().getNames();
            StringList out_ = getInferredIterable(names_, _cont);
            checkIterableCandidates(out_, _cont);
        }
        putVariable(_cont);
    }
    public abstract StringList getInferredIterable(StringList _types,ContextEl _cont);
    public void checkIterableCandidates(StringList _types,ContextEl _cont) {
        FunctionBlock f_ = getFunction();
        AnalyzedPageEl page_ = _cont.getAnalyzing();
        if (_types.size() == 1) {
            String type_ = _types.first();
            Mapping mapping_ = new Mapping();
            String paramArg_ = Templates.getAllTypes(type_).last();
            if (StringList.quickEq(paramArg_, Templates.SUB_TYPE)) {
                paramArg_ = _cont.getStandards().getAliasObject();
            } else if (paramArg_.startsWith(Templates.SUB_TYPE)) {
                paramArg_ = paramArg_.substring(Templates.SUB_TYPE.length());
            } else if (paramArg_.startsWith(Templates.SUP_TYPE)){
                paramArg_ = _cont.getStandards().getAliasObject();
            }
            KeyWords keyWords_ = _cont.getKeyWords();
            String keyWordVar_ = keyWords_.getKeyWordVar();
            if (StringList.quickEq(className.trim(), keyWordVar_)) {
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
                if (!Templates.isCorrectOrNumbers(mapping_, _cont)) {
                    BadImplicitCast cast_ = new BadImplicitCast();
                    cast_.setMapping(mapping_);
                    cast_.setFileName(getFile().getFileName());
                    cast_.setIndexFile(expressionOffset);
                    _cont.getClasses().addError(cast_);
                }
            }
        } else {
            if (_types.isEmpty()) {
                Mapping mapping_ = new Mapping();
                mapping_.setArg(_cont.getStandards().getAliasObject());
                mapping_.setParam(_cont.getStandards().getAliasIterable());
                BadImplicitCast cast_ = new BadImplicitCast();
                cast_.setMapping(mapping_);
                cast_.setFileName(getFile().getFileName());
                cast_.setIndexFile(expressionOffset);
                _cont.getClasses().addError(cast_);
            }
            String iterable_ = _cont.getStandards().getAliasIterable();
            for (String e: _types) {
                Mapping mapping_ = new Mapping();
                mapping_.setArg(e);
                mapping_.setParam(iterable_);
                BadImplicitCast cast_ = new BadImplicitCast();
                cast_.setMapping(mapping_);
                cast_.setFileName(getFile().getFileName());
                cast_.setIndexFile(expressionOffset);
                _cont.getClasses().addError(cast_);
            }
        }
    }
    public void putVariable(ContextEl _cont) {
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
    public void abruptGroup(AnalyzingEl _anEl) {
        if (!_anEl.isReachable(this)) {
            _anEl.completeAbruptGroup(this);
        }
    }

    public abstract String getIteratorVar(Analyzable _an);

    public abstract String getHasNextVar(Analyzable _an);

    public abstract String getNextVar(Analyzable _an);
    public abstract ExpressionLanguage getEqIterator(Analyzable _an);
    public abstract ExpressionLanguage getEqHasNext(Analyzable _an);
    public abstract ExpressionLanguage getEqNext(Analyzable _an);

    @Override
    public void processEl(ContextEl _cont) {
        AbstractPageEl ip_ = _cont.getLastPage();
        LoopBlockStack c_ = ip_.getLastLoopIfPossible();
        if (c_ != null && c_.getBlock() == this) {
            if (c_.isEvaluatingKeepLoop()) {
                processLastElementLoop(_cont);
                return;
            }
            removeVarAndLoop(ip_);
            processBlock(_cont);
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
        ExecOperationNode el_ = opList.last();
        if (el_.getResultClass().isArray()) {
            ArrayStruct arr_ = (ArrayStruct)its_;
            length_ = arr_.getInstance().length;
            if (length_ == CustList.SIZE_EMPTY) {
                finished_ = true;
            }
        } else {
            String locName_ = getIteratorVar(_cont);
            LocalVariable locVar_ = new LocalVariable();
            locVar_.setClassName(stds_.getStructClassName(its_, _cont));
            locVar_.setStruct(its_);
            _cont.getLastPage().getInternVars().put(locName_, locVar_);
            ExpressionLanguage dyn_ = _cont.getLastPage().getCurrentEl(_cont,this, CustList.SECOND_INDEX,CustList.SECOND_INDEX);
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
        LoopVariable lv_ = new LoopVariable();
        lv_.setIndex(-1);
        lv_.setClassName(importedClassName);
        lv_.setIndexClassName(classIndexName);
        lv_.setContainer(its_);
        StringMap<LoopVariable> varsLoop_ = ip_.getVars();
        varsLoop_.put(variableName, lv_);
        if (iterStr_ != null) {
            Boolean has_ = iteratorHasNext(_cont);
            if (has_ == null) {
                return;
            }
            finished_ = !has_;
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
    }

    Struct processLoop(ContextEl _conf) {
        AbstractPageEl ip_ = _conf.getLastPage();
        ip_.setGlobalOffset(expressionOffset);
        ip_.setOffset(0);
        ExpressionLanguage el_ = ip_.getCurrentEl(_conf, this, CustList.FIRST_INDEX, CustList.FIRST_INDEX);
        Argument arg_ = el_.calculateMember(_conf);
        if (_conf.callsOrException()) {
            return NullStruct.NULL_VALUE;
        }
        Struct ito_ = arg_.getStruct();
        if (ito_ == NullStruct.NULL_VALUE) {
            String npe_ = _conf.getStandards().getAliasNullPe();
            _conf.setException(new ErrorStruct(_conf, npe_));
        }
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
        v_.removeKey(variableName);
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
        } else {
            _conf.getLastPage().clearCurrentEls();
            l_.setFinished(true);
            l_.setEvaluatingKeepLoop(false);
        }
    }

    private Boolean iteratorHasNext(ContextEl _conf) {
        AbstractPageEl ip_ = _conf.getLastPage();
        LgNames stds_ = _conf.getStandards();
        LoopBlockStack l_ = (LoopBlockStack) ip_.getLastStack();
        Struct strIter_ = l_.getStructIterator();
        String locName_ = getHasNextVar(_conf);
        LocalVariable locVar_ = new LocalVariable();
        locVar_.setClassName(stds_.getStructClassName(strIter_, _conf));
        locVar_.setStruct(strIter_);
        _conf.getLastPage().getInternVars().put(locName_, locVar_);
        ExpressionLanguage dyn_ = _conf.getLastPage().getCurrentEl(_conf,this, CustList.FIRST_INDEX, 2);
        Argument arg_ = dyn_.calculateMember(_conf);
        if (_conf.callsOrException()) {
            return null;
        }
        return ((BooleanStruct) arg_.getStruct()).getInstance();
    }

    public void incrementLoop(ContextEl _conf, LoopBlockStack _l,
            StringMap<LoopVariable> _vars) {
        _l.setIndex(_l.getIndex() + 1);
        AbstractPageEl abs_ = _conf.getLastPage();

        abs_.setGlobalOffset(variableNameOffset);
        abs_.setOffset(0);
        LgNames stds_ = _conf.getStandards();
        LoopVariable lv_ = _vars.getVal(variableName);
        Struct iterator_ = _l.getStructIterator();
        Struct element_ = NullStruct.NULL_VALUE;
        Argument arg_ = Argument.createVoid();
        ExecOperationNode el_ = opList.last();
        if (!el_.getResultClass().isArray()) {
            String locName_ = getNextVar(_conf);
            LocalVariable locVar_ = new LocalVariable();
            locVar_.setClassName(stds_.getStructClassName(iterator_, _conf));
            locVar_.setStruct(iterator_);
            abs_.getInternVars().put(locName_, locVar_);
            ExpressionLanguage dyn_ = abs_.getCurrentEl(_conf,this, CustList.SECOND_INDEX, 3);
            arg_ = dyn_.calculateMember(_conf);
//            if (_conf.callsOrException()) {
//                return;
//            }
//            abs_.clearCurrentEls();
//            element_ = arg_.getStruct();
        } else {
            Struct container_ = lv_.getContainer();
            LongStruct lg_ = new LongStruct(_l.getIndex());
            element_ = ExecInvokingOperation.getElement(container_, lg_, _conf);
//            if (_conf.hasExceptionOrFailInit()) {
//                return;
//            }
        }
        if (_conf.callsOrException()) {
            return;
        }
        if (!el_.getResultClass().isArray()) {
            abs_.clearCurrentEls();
            element_ = arg_.getStruct();
        } else {
            arg_ = new Argument(element_);
        }
        String className_ = abs_.formatVarType(importedClassName, _conf);
//        Argument arg_ = new Argument(element_);
        if (!Templates.checkObject(className_, arg_, _conf)) {
            return;
        }
        lv_.setStruct(element_);
        lv_.setIndex(lv_.getIndex() + 1);
        _l.setEvaluatingKeepLoop(false);
        abs_.getReadWrite().setBlock(getFirstChild());
    }

    @Override
    public ExpressionLanguage getEl(ContextEl _context,
            int _indexProcess) {
        if (_indexProcess == 0) {
            return getEl();
        }
        if (_indexProcess == 1) {
            return getEqIterator(_context);
        }
        if (_indexProcess == 2) {
            return getEqHasNext(_context);
        }
        return getEqNext(_context);
    }
}
