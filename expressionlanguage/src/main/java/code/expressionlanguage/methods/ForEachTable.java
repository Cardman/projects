package code.expressionlanguage.methods;

import code.expressionlanguage.*;
import code.expressionlanguage.calls.AbstractPageEl;
import code.expressionlanguage.errors.custom.*;
import code.expressionlanguage.files.OffsetStringInfo;
import code.expressionlanguage.files.OffsetsBlock;
import code.expressionlanguage.inherits.Mapping;
import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.instr.ElUtil;
import code.expressionlanguage.instr.PartOffset;
import code.expressionlanguage.methods.util.AbstractCoverageResult;
import code.expressionlanguage.methods.util.AssignedVariablesDesc;
import code.expressionlanguage.opers.Calculation;
import code.expressionlanguage.opers.ExpressionLanguage;
import code.expressionlanguage.opers.exec.ExecOperationNode;
import code.expressionlanguage.opers.util.*;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.stacks.LoopBlockStack;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.ErrorStruct;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.types.ResolvingImportTypes;
import code.expressionlanguage.variables.LocalVariable;
import code.expressionlanguage.variables.LoopVariable;
import code.util.*;

public final class ForEachTable extends BracedStack implements Loop, WithNotEmptyEl,ImportForEachTable {

    private String label;
    private int labelOffset;

    private final String classNameFirst;

    private String importedClassNameFirst;

    private int classNameOffsetFirst;

    private final String classNameSecond;

    private String importedClassNameSecond;

    private int classNameOffsetSecond;

    private final String classIndexName;
    private String importedClassIndexName;
    private int classIndexNameOffset;

    private final String variableNameFirst;

    private int variableNameOffsetFirst;

    private final String variableNameSecond;

    private int variableNameOffsetSecond;

    private final String expression;

    private int expressionOffset;

    private CustList<ExecOperationNode> opList;

    private CustList<PartOffset> partOffsetsFirst = new CustList<PartOffset>();

    private CustList<PartOffset> partOffsetsSecond = new CustList<PartOffset>();

    public ForEachTable(ContextEl _importingPage,
                        OffsetStringInfo _className, OffsetStringInfo _variable,
                        OffsetStringInfo _classNameSec, OffsetStringInfo _variableSec,
                        OffsetStringInfo _expression, OffsetStringInfo _classIndex, OffsetStringInfo _label, OffsetsBlock _offset) {
        super(_offset);
        classNameFirst = _className.getInfo();
        classNameOffsetFirst = _className.getOffset();
        variableNameFirst = _variable.getInfo();
        variableNameOffsetFirst = _variable.getOffset();
        classNameSecond = _classNameSec.getInfo();
        classNameOffsetSecond = _classNameSec.getOffset();
        variableNameSecond = _variableSec.getInfo();
        variableNameOffsetSecond = _variableSec.getOffset();
        expression = _expression.getInfo();
        expressionOffset = _expression.getOffset();
        String classIndex_ = _classIndex.getInfo();
        if (classIndex_.isEmpty()) {
            classIndex_ = _importingPage.getStandards().getAliasPrimInteger();
        }
        classIndexName = classIndex_;
        label = _label.getInfo();
        labelOffset = _label.getOffset();
        classIndexNameOffset = _classIndex.getOffset();
    }

    @Override
    public String getRealLabel() {
        return label;
    }

    @Override
    public int getRealLabelOffset() {
        return getLabelOffset();
    }

    public int getExpressionOffset() {
        return expressionOffset;
    }

    public int getLabelOffset() {
        return labelOffset;
    }

    public ExpressionLanguage getEl() {
        return new ExpressionLanguage(opList);
    }

    @Override
    public void setAssignmentBeforeChild(ContextEl _an, AnalyzingEl _anEl) {
        Block firstChild_ = getFirstChild();
        IdMap<Block, AssignedVariables> id_ = _an.getAssignedVariables().getFinalVariables();
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
    public void buildExpressionLanguage(ContextEl _cont) {
        MethodAccessKind static_ = processVarTypes(_cont);
        opList = ElUtil.getAnalyzedOperations(expression, _cont, Calculation.staticCalculation(static_));
        checkMatchs(_cont);
        putVariable(_cont);
    }

    @Override
    public void buildExpressionLanguageReadOnly(ContextEl _cont) {
        MethodAccessKind static_ = processVarTypes(_cont);
        opList = ElUtil.getAnalyzedOperationsReadOnly(expression, _cont, Calculation.staticCalculation(static_));
        checkMatchs(_cont);
        processVariables(_cont);
    }

    private void checkMatchs(ContextEl _cont) {
        ExecOperationNode el_ = opList.last();
        Argument arg_ = el_.getArgument();
        if (Argument.isNullValue(arg_)) {
            FoundErrorInterpret static_ = new FoundErrorInterpret();
            static_.setFileName(_cont.getCurrentFileName());
            static_.setIndexFile(_cont.getCurrentLocationIndex());
            //separator char
            static_.buildError(_cont.getAnalysisMessages().getNullValue(),
                    _cont.getStandards().getAliasNullPe());
            _cont.addError(static_);
        } else {
            StringList names_ = el_.getResultClass().getNames();
            StringList out_ = getCustomType(names_, _cont);
            checkIterableCandidates(out_, _cont);
        }
    }

    @Override
    public void reduce(ContextEl _context) {
        ExecOperationNode r_ = opList.last();
        opList = ElUtil.getReducedNodes(r_);
    }
    private StringList getCustomType(StringList _names, ContextEl _context) {
        LgNames stds_ = _context.getStandards();
        return stds_.getCustomTableType(_names,_context,"","").getClassName();
    }

    public String getClassIndexName() {
        return classIndexName;
    }

    private MethodAccessKind processVarTypes(ContextEl _cont) {
        FunctionBlock f_ = _cont.getAnalyzing().getCurrentFct();
        importedClassIndexName = ResolvingImportTypes.resolveCorrectType(_cont,classIndexName);
        if (!PrimitiveTypeUtil.isIntOrderClass(new ClassArgumentMatching(importedClassIndexName), _cont)) {
            Mapping mapping_ = new Mapping();
            mapping_.setArg(importedClassIndexName);
            mapping_.setParam(_cont.getStandards().getAliasLong());
            FoundErrorInterpret cast_ = new FoundErrorInterpret();
            cast_.setFileName(getFile().getFileName());
            cast_.setIndexFile(classIndexNameOffset);
            //classIndexName len
            cast_.buildError(_cont.getAnalysisMessages().getNotPrimitiveWrapper(),
                    importedClassIndexName);
            _cont.addError(cast_);
        }
        if (_cont.getAnalyzing().containsVar(variableNameFirst)) {
            FoundErrorInterpret d_ = new FoundErrorInterpret();
            d_.setFileName(getFile().getFileName());
            d_.setIndexFile(variableNameOffsetFirst);
            //first variable name
            d_.buildError(_cont.getAnalysisMessages().getBadVariableName(),
                    variableNameFirst);
            _cont.addError(d_);
        }
        if (_cont.getAnalyzing().containsMutableLoopVar(variableNameFirst)) {
            FoundErrorInterpret d_ = new FoundErrorInterpret();
            d_.setFileName(getFile().getFileName());
            d_.setIndexFile(variableNameOffsetFirst);
            //first variable name
            d_.buildError(_cont.getAnalysisMessages().getBadVariableName(),
                    variableNameFirst);
            _cont.addError(d_);
        }
        if (!_cont.isValidSingleToken(variableNameFirst)) {
            FoundErrorInterpret b_ = new FoundErrorInterpret();
            b_.setFileName(getFile().getFileName());
            b_.setIndexFile(variableNameOffsetFirst);
            //first variable name
            b_.buildError(_cont.getAnalysisMessages().getBadVariableName(),
                    variableNameFirst);
            _cont.addError(b_);
        }
        if (_cont.getAnalyzing().containsVar(variableNameSecond)) {
            FoundErrorInterpret d_ = new FoundErrorInterpret();
            d_.setFileName(getFile().getFileName());
            d_.setIndexFile(variableNameOffsetSecond);
            //second variable name
            d_.buildError(_cont.getAnalysisMessages().getBadVariableName(),
                    variableNameSecond);
            _cont.addError(d_);
        }
        if (_cont.getAnalyzing().containsMutableLoopVar(variableNameSecond)) {
            FoundErrorInterpret d_ = new FoundErrorInterpret();
            d_.setFileName(getFile().getFileName());
            d_.setIndexFile(variableNameOffsetSecond);
            //second variable name
            d_.buildError(_cont.getAnalysisMessages().getBadVariableName(),
                    variableNameSecond);
            _cont.addError(d_);
        }
        if (!_cont.isValidSingleToken(variableNameSecond)) {
            FoundErrorInterpret b_ = new FoundErrorInterpret();
            b_.setFileName(getFile().getFileName());
            b_.setIndexFile(variableNameOffsetSecond);
            //second variable name
            b_.buildError(_cont.getAnalysisMessages().getBadVariableName(),
                    variableNameSecond);
            _cont.addError(b_);
        }
        KeyWords keyWords_ = _cont.getKeyWords();
        String keyWordVar_ = keyWords_.getKeyWordVar();
        AnalyzedPageEl page_ = _cont.getAnalyzing();
        page_.setGlobalOffset(classNameOffsetFirst);
        page_.setOffset(0);
        if (!StringList.quickEq(classNameFirst.trim(), keyWordVar_)) {
            importedClassNameFirst = ResolvingImportTypes.resolveCorrectType(_cont,classNameFirst);
            partOffsetsFirst.addAllElts(_cont.getCoverage().getCurrentParts());
        } else {
            importedClassNameFirst = "";
        }
        page_.setGlobalOffset(classNameOffsetSecond);
        page_.setOffset(0);
        if (!StringList.quickEq(classNameSecond.trim(), keyWordVar_)) {
            importedClassNameSecond = ResolvingImportTypes.resolveCorrectType(_cont,classNameSecond);
            partOffsetsSecond.addAllElts(_cont.getCoverage().getCurrentParts());
        } else {
            importedClassNameSecond = "";
        }
        page_.setGlobalOffset(expressionOffset);
        page_.setOffset(0);
        MethodAccessKind static_ = f_.getStaticContext();
        _cont.getCoverage().putBlockOperationsLoops(_cont,this);
        return static_;
    }

    public void checkIterableCandidates(StringList _types,ContextEl _cont) {
        if (_types.onlyOneElt()) {
            KeyWords keyWords_ = _cont.getKeyWords();
            String keyWordVar_ = keyWords_.getKeyWordVar();
            String type_ = _types.first();
            Mapping mapping_ = new Mapping();
            String paramArg_ = Templates.getAllTypes(type_).get(1);
            if (StringList.quickEq(paramArg_, Templates.SUB_TYPE)) {
                paramArg_ = _cont.getStandards().getAliasObject();
            } else if (paramArg_.startsWith(Templates.SUB_TYPE)) {
                paramArg_ = paramArg_.substring(Templates.SUB_TYPE.length());
            } else if (paramArg_.startsWith(Templates.SUP_TYPE)){
                paramArg_ = _cont.getStandards().getAliasObject();
            }
            if (StringList.quickEq(classNameFirst.trim(), keyWordVar_)) {
                importedClassNameFirst = paramArg_;
            } else {
                mapping_.setArg(paramArg_);
                mapping_.setParam(importedClassNameFirst);
                StringMap<StringList> vars_ = _cont.getAnalyzing().getCurrentConstraints().getCurrentConstraints();
                mapping_.setMapping(vars_);
                if (!Templates.isCorrectOrNumbers(mapping_, _cont)) {
                    FoundErrorInterpret cast_ = new FoundErrorInterpret();
                    cast_.setFileName(getFile().getFileName());
                    cast_.setIndexFile(expressionOffset);
                    //separator char before expression
                    cast_.buildError(_cont.getAnalysisMessages().getBadImplicitCast(),
                            paramArg_,
                            importedClassNameFirst);
                    _cont.addError(cast_);
                }
            }
            mapping_ = new Mapping();
            paramArg_ = Templates.getAllTypes(type_).last();
            if (StringList.quickEq(paramArg_, Templates.SUB_TYPE)) {
                paramArg_ = _cont.getStandards().getAliasObject();
            } else if (paramArg_.startsWith(Templates.SUB_TYPE)) {
                paramArg_ = paramArg_.substring(Templates.SUB_TYPE.length());
            } else if (paramArg_.startsWith(Templates.SUP_TYPE)){
                paramArg_ = _cont.getStandards().getAliasObject();
            }
            if (StringList.quickEq(classNameSecond.trim(), keyWordVar_)) {
                importedClassNameSecond = paramArg_;
            } else {
                mapping_.setArg(paramArg_);
                mapping_.setParam(importedClassNameSecond);
                StringMap<StringList> vars_ = _cont.getAnalyzing().getCurrentConstraints().getCurrentConstraints();
                mapping_.setMapping(vars_);
                if (!Templates.isCorrectOrNumbers(mapping_, _cont)) {
                    FoundErrorInterpret cast_ = new FoundErrorInterpret();
                    cast_.setFileName(getFile().getFileName());
                    cast_.setIndexFile(expressionOffset);
                    //separator char before expression
                    cast_.buildError(_cont.getAnalysisMessages().getBadImplicitCast(),
                            paramArg_,
                            importedClassNameSecond);
                    _cont.addError(cast_);
                }
            }
        } else {
            Mapping mapping_ = new Mapping();
            mapping_.setArg(_cont.getStandards().getAliasObject());
            mapping_.setParam(_cont.getStandards().getAliasIterableTable());
            FoundErrorInterpret cast_ = new FoundErrorInterpret();
            cast_.setFileName(getFile().getFileName());
            cast_.setIndexFile(expressionOffset);
            //separator char before expression
            cast_.buildError(_cont.getAnalysisMessages().getBadImplicitCast(),
                    _cont.getStandards().getAliasObject(),
                    _cont.getStandards().getAliasIterableTable());
            _cont.addError(cast_);
        }
    }
    public void putVariable(ContextEl _cont) {
        processVariables(_cont);
        buildConditions(_cont);
    }

    private void processVariables(ContextEl _cont) {
        if (StringList.quickEq(variableNameFirst, variableNameSecond)) {
            FoundErrorInterpret d_ = new FoundErrorInterpret();
            d_.setFileName(getFile().getFileName());
            d_.setIndexFile(variableNameOffsetSecond);
            //second variable name len
            d_.buildError(_cont.getAnalysisMessages().getBadVariableName(),
                    variableNameFirst);
            _cont.addError(d_);
        }
        LoopVariable lv_ = new LoopVariable();
        if (!importedClassNameFirst.isEmpty()) {
            lv_.setClassName(importedClassNameFirst);
        } else {
            lv_.setClassName(_cont.getStandards().getAliasObject());
        }
        lv_.setIndexClassName(importedClassIndexName);
        _cont.getAnalyzing().putVar(variableNameFirst, lv_);
        lv_ = new LoopVariable();
        if (!importedClassNameSecond.isEmpty()) {
            lv_.setClassName(importedClassNameSecond);
        } else {
            lv_.setClassName(_cont.getStandards().getAliasObject());
        }
        lv_.setIndexClassName(importedClassIndexName);
        _cont.getAnalyzing().putVar(variableNameSecond, lv_);
    }

    @Override
    public void setAssignmentAfter(ContextEl _an, AnalyzingEl _anEl) {
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

    protected StringMap<AssignmentBefore> buildAssListFieldAfterInvalHypot(ContextEl _an, AnalyzingEl _anEl) {
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
    protected CustList<StringMap<AssignmentBefore>> buildAssListLocVarInvalHypot(ContextEl _an, AnalyzingEl _anEl) {
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
    protected CustList<StringMap<AssignmentBefore>> buildAssListMutableLoopInvalHypot(ContextEl _an, AnalyzingEl _anEl) {
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
    protected AssignedVariables buildNewAssignedVariable() {
        return new AssignedBooleanVariables();
    }

    public String getLabel() {
        return label;
    }

    public String getClassNameFirst() {
        return classNameFirst;
    }

    public String getVariableNameFirst() {
        return variableNameFirst;
    }

    public String getClassNameSecond() {
        return classNameSecond;
    }

    public String getVariableNameSecond() {
        return variableNameSecond;
    }

    public int getClassNameOffsetFirst() {
        return classNameOffsetFirst;
    }

    public int getClassNameOffsetSecond() {
        return classNameOffsetSecond;
    }

    public int getVariableNameOffsetFirst() {
        return variableNameOffsetFirst;
    }

    public int getVariableNameOffsetSecond() {
        return variableNameOffsetSecond;
    }

    public String getExpression() {
        return expression;
    }

    @Override
    public String getImportedClassNameFirst() {
        return importedClassNameFirst;
    }

    @Override
    public String getImportedClassNameSecond() {
        return importedClassNameSecond;
    }

    @Override
    public void abruptGroup(AnalyzingEl _anEl) {
        if (!_anEl.isReachable(this)) {
            _anEl.completeAbruptGroup(this);
        }
    }

    @Override
    public void processEl(ContextEl _cont) {
        AbstractPageEl ip_ = _cont.getLastPage();
        LoopBlockStack c_ = ip_.getLastLoopIfPossible(this);
        if (c_ != null) {
            ip_.processVisitedLoop(c_,this,this,_cont);
            return;
        }
        Struct its_ = processLoop(_cont);
        if (_cont.callsOrException()) {
            return;
        }
        Struct iterStr_;
        long length_ = CustList.INDEX_NOT_FOUND_ELT;
        Classes cls_ = _cont.getClasses();
        String locName_ = cls_.getIteratorTableVarCust();
        LocalVariable locVar_ = LocalVariable.newLocalVariable(its_,_cont);
        _cont.getLastPage().getInternVars().put(locName_, locVar_);
        ExpressionLanguage dyn_ = _cont.getLastPage().getCurrentEl(_cont,this, CustList.SECOND_INDEX,CustList.SECOND_INDEX);
        Argument arg_ = ElUtil.tryToCalculate(_cont,dyn_,0);
        if (_cont.callsOrException()) {
            return;
        }
        iterStr_ = arg_.getStruct();
        LoopBlockStack l_ = new LoopBlockStack();
        l_.setIndex(-1);
        l_.setFinished(false);
        l_.setBlock(this);
        l_.setCurrentVisitedBlock(this);
        l_.setStructIterator(iterStr_);
        l_.setMaxIteration(length_);
        ip_.addBlock(l_);
        ip_.clearCurrentEls();
        l_.setEvaluatingKeepLoop(true);
        StringMap<LoopVariable> varsLoop_ = ip_.getVars();
        String className_;
        className_ = ip_.formatVarType(importedClassNameFirst, _cont);
        LoopVariable lv_ = LoopVariable.newLoopVariable(PrimitiveTypeUtil.defaultValue(className_,_cont),className_);
        lv_.setIndex(-1);
        lv_.setIndexClassName(importedClassIndexName);
        lv_.setContainer(its_);
        varsLoop_.put(variableNameFirst, lv_);
        className_ = ip_.formatVarType(importedClassNameSecond, _cont);
        lv_ = LoopVariable.newLoopVariable(PrimitiveTypeUtil.defaultValue(className_,_cont),className_);
        lv_.setIndex(-1);
        lv_.setIndexClassName(importedClassIndexName);
        lv_.setContainer(its_);
        varsLoop_.put(variableNameSecond, lv_);
        iteratorHasNext(_cont);
    }
    Struct processLoop(ContextEl _conf) {
        AbstractPageEl ip_ = _conf.getLastPage();
        ip_.setGlobalOffset(expressionOffset);
        ip_.setOffset(0);
        ExpressionLanguage el_ = ip_.getCurrentEl(_conf, this, CustList.FIRST_INDEX, CustList.FIRST_INDEX);
        Argument arg_ = ElUtil.tryToCalculate(_conf,el_,0);
        if (_conf.callsOrException()) {
            return NullStruct.NULL_VALUE;
        }
        Struct ito_ = arg_.getStruct();
        if (ito_== NullStruct.NULL_VALUE) {
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
    public void removeAllVars(AbstractPageEl _ip) {
        super.removeAllVars(_ip);
        StringMap<LoopVariable> v_ = _ip.getVars();
        v_.removeKey(variableNameFirst);
        v_.removeKey(variableNameSecond);
    }

    @Override
    public void processLastElementLoop(ContextEl _conf) {
        AbstractPageEl ip_ = _conf.getLastPage();
        StringMap<LoopVariable> vars_ = ip_.getVars();
        LoopBlockStack l_ = (LoopBlockStack) ip_.getLastStack();
        l_.setEvaluatingKeepLoop(true);
        ConditionReturn has_ = iteratorHasNext(_conf);
        if (has_ == ConditionReturn.CALL_EX) {
            return;
        }
        boolean hasNext_ = has_ == ConditionReturn.YES;

        if (hasNext_) {
            _conf.getCoverage().passLoop(_conf, new Argument(BooleanStruct.of(true)));
            incrementLoop(_conf, l_, vars_);
        } else {
            _conf.getCoverage().passLoop(_conf, new Argument(BooleanStruct.of(false)));
            _conf.getLastPage().clearCurrentEls();
            l_.setFinished(true);
            l_.setEvaluatingKeepLoop(false);
        }
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
        KeyWords keyWords_ = _cont.getKeyWords();
        String keyWordVar_ = keyWords_.getKeyWordVar();
        if (StringList.quickEq(classNameFirst.trim(), keyWordVar_)) {
            tag_ = "<b title=\""+ElUtil.transform(importedClassNameFirst)+"\">";
            _parts.add(new PartOffset(tag_,classNameOffsetFirst));
            tag_ = "</b>";
            _parts.add(new PartOffset(tag_,classNameOffsetFirst+ _cont.getKeyWords().getKeyWordFor().length()));
        } else {
            _parts.addAllElts(partOffsetsFirst);
        }
        tag_ = "<a name=\"m"+ variableNameOffsetFirst +"\">";
        _parts.add(new PartOffset(tag_,variableNameOffsetFirst));
        tag_ = "</a>";
        _parts.add(new PartOffset(tag_,variableNameOffsetFirst+variableNameFirst.length()));
        if (StringList.quickEq(classNameSecond.trim(), keyWordVar_)) {
            tag_ = "<b title=\""+ElUtil.transform(importedClassNameSecond)+"\">";
            _parts.add(new PartOffset(tag_,classNameOffsetSecond));
            tag_ = "</b>";
            _parts.add(new PartOffset(tag_,classNameOffsetSecond+ _cont.getKeyWords().getKeyWordFor().length()));
        } else {
            _parts.addAllElts(partOffsetsSecond);
        }
        tag_ = "<a name=\"m"+ variableNameOffsetSecond +"\">";
        _parts.add(new PartOffset(tag_,variableNameOffsetSecond));
        tag_ = "</a>";
        _parts.add(new PartOffset(tag_,variableNameOffsetSecond+variableNameSecond.length()));
        tag_ = "</span>";
        _parts.add(new PartOffset(tag_,variableNameOffsetSecond+ variableNameSecond.length()));
        off_ = expressionOffset;
        int offsetEndBlock_ = off_ + expression.length();
        ElUtil.buildCoverageReport(_cont,off_,this,opList,offsetEndBlock_,_parts);
        _cont.getCoverage().getLoopVars().put(variableNameFirst,variableNameOffsetFirst);
        _cont.getCoverage().getLoopVars().put(variableNameSecond,variableNameOffsetSecond);
        refLabel(_parts,label,labelOffset);
    }

    private void incrementLoop(ContextEl _conf, LoopBlockStack _l,
                               StringMap<LoopVariable> _vars) {
        _l.setIndex(_l.getIndex() + 1);
        Classes cls_ = _conf.getClasses();
        Struct iterator_ = _l.getStructIterator();
        AbstractPageEl call_ = _conf.getLastPage();
        if (call_.sizeEl() < 2) {
            String locName_ = cls_.getNextPairVarCust();
            LocalVariable locVar_ = LocalVariable.newLocalVariable(iterator_,_conf);
            _conf.getLastPage().getInternVars().put(locName_, locVar_);
        }
        ExpressionLanguage nextEl_ = call_.getCurrentEl(_conf,this, CustList.SECOND_INDEX, 3);
        ElUtil.tryToCalculate(_conf,nextEl_,0);
        if (_conf.callsOrException()) {
            return;
        }
        String classNameFirst_ = _conf.getLastPage().formatVarType(importedClassNameFirst, _conf);
        if (call_.sizeEl() < 3) {
            String locName_ = cls_.getFirstVarCust();
            Struct value_ = call_.getValue(1).getStruct();
            LocalVariable locVar_ = LocalVariable.newLocalVariable(value_,_conf);
            _conf.getLastPage().getInternVars().put(locName_, locVar_);
        }
        ExpressionLanguage firstEl_ = call_.getCurrentEl(_conf,this, 2, 4);
        Argument arg_ = ElUtil.tryToCalculate(_conf,firstEl_,0);
        if (_conf.callsOrException()) {
            return;
        }
        if (!Templates.checkStrictObject(classNameFirst_, arg_, _conf)) {
            return;
        }
        String classNameSecond_ = _conf.getLastPage().formatVarType(importedClassNameSecond, _conf);
        if (call_.sizeEl() < 4) {
            LoopVariable lv_ = _vars.getVal(variableNameFirst);
            lv_.setStruct(arg_.getStruct());
            lv_.setIndex(lv_.getIndex() + 1);
            String locName_ = cls_.getSecondVarCust();
            Struct value_ = call_.getValue(1).getStruct();
            LocalVariable locVar_ = LocalVariable.newLocalVariable(value_,_conf);
            _conf.getLastPage().getInternVars().put(locName_, locVar_);
        }
        ExpressionLanguage secondEl_ = call_.getCurrentEl(_conf,this, 3, 5);
        arg_ = ElUtil.tryToCalculate(_conf,secondEl_,0);
        if (_conf.callsOrException()) {
            return;
        }
        if (!Templates.checkStrictObject(classNameSecond_, arg_, _conf)) {
            return;
        }
        LoopVariable lv_ = _vars.getVal(variableNameSecond);
        lv_.setStruct(arg_.getStruct());
        lv_.setIndex(lv_.getIndex() + 1);
        call_.clearCurrentEls();
        call_.getReadWrite().setBlock(getFirstChild());
        _l.setEvaluatingKeepLoop(false);
    }
    private ConditionReturn iteratorHasNext(ContextEl _conf) {
        AbstractPageEl ip_ = _conf.getLastPage();
        LoopBlockStack l_ = (LoopBlockStack) ip_.getLastStack();
        Struct strIter_ = l_.getStructIterator();
        Classes cls_ = _conf.getClasses();
        String locName_ = cls_.getHasNextPairVarCust();
        LocalVariable locVar_ = LocalVariable.newLocalVariable(strIter_,_conf);
        _conf.getLastPage().getInternVars().put(locName_, locVar_);
        ExpressionLanguage dyn_ = _conf.getLastPage().getCurrentEl(_conf,this, CustList.FIRST_INDEX, 2);
        Argument arg_ = ElUtil.tryToCalculate(_conf,dyn_,0);
        if (_conf.callsOrException()) {
            return ConditionReturn.CALL_EX;
        }
        if (BooleanStruct.isTrue(arg_.getStruct())) {
            return ConditionReturn.YES;
        }
        return ConditionReturn.NO;
    }
    @Override
    public ExpressionLanguage getEl(ContextEl _context, int _indexProcess) {
        if (_indexProcess == 0) {
            return getEl();
        }
        Classes cls_ = _context.getClasses();
        if (_indexProcess == 1) {
            return new ExpressionLanguage(cls_.getExpsIteratorTableCust());
        }
        if (_indexProcess == 2) {
            return new ExpressionLanguage(cls_.getExpsHasNextPairCust());
        }
        if (_indexProcess == 3) {
            return new ExpressionLanguage(cls_.getExpsNextPairCust());
        }
        if (_indexProcess == 4) {
            return new ExpressionLanguage(cls_.getExpsFirstCust());
        }
        return new ExpressionLanguage(cls_.getExpsSecondCust());
    }

}
