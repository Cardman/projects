package code.expressionlanguage.methods;

import code.expressionlanguage.*;
import code.expressionlanguage.analyze.variables.AnaLoopVariable;
import code.expressionlanguage.exec.ConditionReturn;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.errors.custom.*;
import code.expressionlanguage.files.OffsetStringInfo;
import code.expressionlanguage.files.OffsetsBlock;
import code.expressionlanguage.inherits.Mapping;
import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.instr.ElUtil;
import code.expressionlanguage.instr.PartOffset;
import code.expressionlanguage.exec.coverage.AbstractCoverageResult;
import code.expressionlanguage.opers.Calculation;
import code.expressionlanguage.opers.ExpressionLanguage;
import code.expressionlanguage.exec.opers.ExecInvokingOperation;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.expressionlanguage.opers.util.*;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.exec.stacks.LoopBlockStack;
import code.expressionlanguage.stds.IterableAnalysisResult;
import code.expressionlanguage.structs.*;
import code.expressionlanguage.types.ResolvingImportTypes;
import code.expressionlanguage.exec.variables.LocalVariable;
import code.expressionlanguage.exec.variables.LoopVariable;
import code.util.*;

public final class ForEachLoop extends BracedStack implements ForLoop,ImportForEachLoop {

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

    private final String expression;

    private int expressionOffset;

    private CustList<ExecOperationNode> opList;

    private CustList<PartOffset> partOffsets = new CustList<PartOffset>();

    public ForEachLoop(ContextEl _importingPage,
                       OffsetStringInfo _className, OffsetStringInfo _variable,
                       OffsetStringInfo _expression, OffsetStringInfo _classIndex, OffsetStringInfo _label, OffsetsBlock _offset) {
        super(_offset);
        className = _className.getInfo();
        classNameOffset = _className.getOffset();
        variableName = _variable.getInfo();
        variableNameOffset = _variable.getOffset();
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
    public String getImportedClassName() {
        return importedClassName;
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
        if (_cont.getAnalyzing().containsVar(variableName)) {
            FoundErrorInterpret d_ = new FoundErrorInterpret();
            d_.setFileName(getFile().getFileName());
            d_.setIndexFile(variableNameOffset);
            //variable name len
            d_.buildError(_cont.getAnalysisMessages().getBadVariableName(),
                    variableName);
            _cont.addError(d_);
        }
        if (_cont.getAnalyzing().containsMutableLoopVar(variableName)) {
            FoundErrorInterpret d_ = new FoundErrorInterpret();
            d_.setFileName(getFile().getFileName());
            d_.setIndexFile(variableNameOffset);
            //variable name len
            d_.buildError(_cont.getAnalysisMessages().getBadVariableName(),
                    variableName);
            _cont.addError(d_);
        }
        if (!_cont.isValidSingleToken(variableName)) {
            FoundErrorInterpret b_ = new FoundErrorInterpret();
            b_.setFileName(getFile().getFileName());
            b_.setIndexFile(variableNameOffset);
            //variable name len
            b_.buildError(_cont.getAnalysisMessages().getBadVariableName(),
                    variableName);
            _cont.addError(b_);
        }
        AnalyzedPageEl page_ = _cont.getAnalyzing();
        page_.setGlobalOffset(classNameOffset);
        page_.setOffset(0);
        KeyWords keyWords_ = _cont.getKeyWords();
        String keyWordVar_ = keyWords_.getKeyWordVar();
        if (!StringList.quickEq(className.trim(), keyWordVar_)) {
            importedClassName = ResolvingImportTypes.resolveCorrectType(_cont,className);
            partOffsets.addAllElts(_cont.getCoverage().getCurrentParts());
        } else {
            importedClassName = "";
        }
        page_.setGlobalOffset(expressionOffset);
        page_.setOffset(0);
        MethodAccessKind static_ = f_.getStaticContext();
        _cont.getCoverage().putBlockOperationsLoops(_cont,this);
        return static_;
    }

    public void inferArrayClass(ContextEl _cont) {
        ExecOperationNode el_ = opList.last();
        ClassArgumentMatching compo_ = PrimitiveTypeUtil.getQuickComponentType(el_.getResultClass());
        KeyWords keyWords_ = _cont.getKeyWords();
        String keyWordVar_ = keyWords_.getKeyWordVar();
        if (StringList.quickEq(className.trim(), keyWordVar_) && compo_.getNames().onlyOneElt()) {
            importedClassName = compo_.getName();
        } else {
            Mapping mapping_ = new Mapping();
            if (importedClassName.isEmpty()) {
                mapping_.setArg("");
                mapping_.setParam("");
                FoundErrorInterpret cast_ = new FoundErrorInterpret();
                cast_.setFileName(getFile().getFileName());
                cast_.setIndexFile(expressionOffset);
                //separator char
                cast_.buildError(_cont.getAnalysisMessages().getUnknownType(),
                        className.trim());
                _cont.addError(cast_);
            } else {
                mapping_.setArg(compo_);
                mapping_.setParam(importedClassName);
                StringMap<StringList> vars_ = _cont.getAnalyzing().getCurrentConstraints().getCurrentConstraints();
                mapping_.setMapping(vars_);
                if (!Templates.isCorrectOrNumbers(mapping_, _cont)) {
                    FoundErrorInterpret cast_ = new FoundErrorInterpret();
                    cast_.setFileName(getFile().getFileName());
                    cast_.setIndexFile(expressionOffset);
                    //separator char
                    cast_.buildError(_cont.getAnalysisMessages().getBadImplicitCast(),
                            StringList.join(compo_.getNames(),"&"),
                            importedClassName);
                    _cont.addError(cast_);
                }
            }
        }
    }

    @Override
    public void buildExpressionLanguageReadOnly(ContextEl _cont) {
        MethodAccessKind static_ = processVarTypes(_cont);
        opList = ElUtil.getAnalyzedOperationsReadOnly(expression, _cont, Calculation.staticCalculation(static_));
        checkMatchs(_cont);
        processVariable(_cont);
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
        } else if (el_.getResultClass().isArray()) {
            inferArrayClass(_cont);
        } else {
            StringList names_ = el_.getResultClass().getNames();
            StringList out_ = getInferredIterable(names_, _cont);
            checkIterableCandidates(out_, _cont);
        }
    }

    public StringList getInferredIterable(StringList _types, ContextEl _cont) {
        IterableAnalysisResult it_ = _cont.getStandards().getCustomType(_types,"", _cont);
        return it_.getClassName();
    }

    public void checkIterableCandidates(StringList _types,ContextEl _cont) {
        if (_types.onlyOneElt()) {
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
                StringMap<StringList> vars_ = _cont.getAnalyzing().getCurrentConstraints().getCurrentConstraints();
                mapping_.setMapping(vars_);
                if (!Templates.isCorrectOrNumbers(mapping_, _cont)) {
                    FoundErrorInterpret cast_ = new FoundErrorInterpret();
                    cast_.setFileName(getFile().getFileName());
                    cast_.setIndexFile(expressionOffset);
                    //separator char
                    cast_.buildError(_cont.getAnalysisMessages().getBadImplicitCast(),
                            paramArg_,
                            importedClassName);
                    _cont.addError(cast_);
                }
            }
        } else {
            FoundErrorInterpret cast_ = new FoundErrorInterpret();
            cast_.setFileName(getFile().getFileName());
            cast_.setIndexFile(expressionOffset);
            //separator char
            cast_.buildError(_cont.getAnalysisMessages().getBadImplicitCast(),
                    _cont.getStandards().getAliasObject(),
                    _cont.getStandards().getAliasIterable());
            _cont.addError(cast_);
        }
    }

    private void processVariable(ContextEl _cont) {
        AnaLoopVariable lv_ = new AnaLoopVariable();
        if (!importedClassName.isEmpty()) {
            lv_.setClassName(importedClassName);
        } else {
            lv_.setClassName(_cont.getStandards().getAliasObject());
        }
        lv_.setIndexClassName(importedClassIndexName);
        _cont.getAnalyzing().putVar(variableName, lv_);
    }

    @Override
    public void abruptGroup(AnalyzingEl _anEl) {
        if (!_anEl.isReachable(this)) {
            _anEl.completeAbruptGroup(this);
        }
    }

    public String getIteratorVar(ContextEl _an) {
        return _an.getClasses().getIteratorVarCust();
    }

    public String getHasNextVar(ContextEl _an) {
        return _an.getClasses().getHasNextVarCust();
    }

    public String getNextVar(ContextEl _an) {
        return _an.getClasses().getNextVarCust();
    }
    public ExpressionLanguage getEqIterator(ContextEl _an) {
        return new ExpressionLanguage(_an.getClasses().getExpsIteratorCust());
    }

    public ExpressionLanguage getEqHasNext(ContextEl _an) {
        return new ExpressionLanguage(_an.getClasses().getExpsHasNextCust());
    }
    public ExpressionLanguage getEqNext(ContextEl _an) {
        return new ExpressionLanguage(_an.getClasses().getExpsNextCust());
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
        Struct iterStr_ = null;
        long length_ = CustList.INDEX_NOT_FOUND_ELT;
        boolean finished_ = false;
        ExecOperationNode el_ = opList.last();
        if (el_.getResultClass().isArray()) {
            length_ = getLength(its_,_cont);
            if (length_ == CustList.SIZE_EMPTY) {
                finished_ = true;
            }
            if (_cont.callsOrException()) {
                return;
            }
        } else {
            if (its_ == NullStruct.NULL_VALUE) {
                String npe_ = _cont.getStandards().getAliasNullPe();
                _cont.setException(new ErrorStruct(_cont, npe_));
                return;
            }
            String locName_ = getIteratorVar(_cont);
            LocalVariable locVar_ = LocalVariable.newLocalVariable(its_,_cont);
            ip_.getInternVars().put(locName_, locVar_);
            ExpressionLanguage dyn_ = ip_.getCurrentEl(_cont,this, CustList.SECOND_INDEX,CustList.SECOND_INDEX);
            Argument arg_ = ElUtil.tryToCalculate(_cont,dyn_,0);
            if (_cont.callsOrException()) {
                return;
            }
            iterStr_ = arg_.getStruct();
        }
        LoopBlockStack l_ = new LoopBlockStack();
        l_.setIndex(-1);
        l_.setFinished(finished_);
        l_.setBlock(this);
        l_.setCurrentVisitedBlock(this);
        l_.setStructIterator(iterStr_);
        l_.setMaxIteration(length_);
        ip_.addBlock(l_);
        ip_.clearCurrentEls();
        l_.setEvaluatingKeepLoop(true);
        String className_ = ip_.formatVarType(importedClassName, _cont);
        LoopVariable lv_ = LoopVariable.newLoopVariable(PrimitiveTypeUtil.defaultValue(className_,_cont),className_);
        lv_.setIndex(-1);
        lv_.setIndexClassName(importedClassIndexName);
        lv_.setContainer(its_);
        StringMap<LoopVariable> varsLoop_ = ip_.getVars();
        varsLoop_.put(variableName, lv_);
        if (iterStr_ != null) {
            iteratorHasNext(_cont);
            return;
        }
        incrOrFinish(_cont, finished_);
    }

    private int getLength(Struct _str,ContextEl _cont) {
        if (_str instanceof ArrayStruct) {
            return ((ArrayStruct)_str).getInstance().length;
        }
        String npe_ = _cont.getStandards().getAliasNullPe();
        _cont.setException(new ErrorStruct(_cont, npe_));
        return -1;
    }
    private void incrOrFinish(ContextEl _cont, boolean _finished) {
        AbstractPageEl ip_ = _cont.getLastPage();
        LoopBlockStack l_ = (LoopBlockStack) ip_.getLastStack();
        if (_finished) {
            ip_.clearCurrentEls();
            _cont.getCoverage().passLoop(_cont, new Argument(BooleanStruct.of(false)));
            l_.setEvaluatingKeepLoop(false);
            l_.setFinished(true);
            return;
        }
        _cont.getCoverage().passLoop(_cont, new Argument(BooleanStruct.of(true)));
        StringMap<LoopVariable> vars_ = ip_.getVars();
        incrementLoop(_cont, l_, vars_);
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
        if (StringList.quickEq(className.trim(), keyWordVar_)) {
            tag_ = "<b title=\""+ElUtil.transform(importedClassName)+"\">";
            _parts.add(new PartOffset(tag_,classNameOffset));
            tag_ = "</b>";
            _parts.add(new PartOffset(tag_,classNameOffset+ _cont.getKeyWords().getKeyWordFor().length()));
        } else {
            _parts.addAllElts(partOffsets);
        }
        tag_ = "<a name=\"m"+ variableNameOffset +"\">";
        _parts.add(new PartOffset(tag_,variableNameOffset));
        tag_ = "</a>";
        _parts.add(new PartOffset(tag_,variableNameOffset+variableName.length()));
        tag_ = "</span>";
        _parts.add(new PartOffset(tag_,variableNameOffset+ variableName.length()));
        off_ = expressionOffset;
        int offsetEndBlock_ = off_ + expression.length();
        ElUtil.buildCoverageReport(_cont,off_,this,opList,offsetEndBlock_,_parts);
        _cont.getCoverage().getLoopVars().put(variableName,variableNameOffset);
        refLabel(_parts,label,labelOffset);
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
        return arg_.getStruct();
        
    }

    @Override
    public void exitStack(ContextEl _context) {
        processLastElementLoop(_context);
    }

    @Override
    public void removeAllVars(AbstractPageEl _ip) {
        super.removeAllVars(_ip);
        StringMap<LoopVariable> v_ = _ip.getVars();
        v_.removeKey(variableName);
    }


    @Override
    public void processLastElementLoop(ContextEl _conf) {
        AbstractPageEl ip_ = _conf.getLastPage();
        LoopBlockStack l_ = (LoopBlockStack) ip_.getLastStack();
        l_.setEvaluatingKeepLoop(true);
        boolean hasNext_;
        if (l_.getStructIterator() != null) {
            ConditionReturn has_ = iteratorHasNext(_conf);
            if (has_ == ConditionReturn.CALL_EX) {
                return;
            }
            hasNext_ = has_ == ConditionReturn.YES;
        } else {
            hasNext_ = l_.hasNext();
        }
        incrOrFinish(_conf, !hasNext_);
    }

    private ConditionReturn iteratorHasNext(ContextEl _conf) {
        AbstractPageEl ip_ = _conf.getLastPage();
        LoopBlockStack l_ = (LoopBlockStack) ip_.getLastStack();
        Struct strIter_ = l_.getStructIterator();
        String locName_ = getHasNextVar(_conf);
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

    private void incrementLoop(ContextEl _conf, LoopBlockStack _l,
                               StringMap<LoopVariable> _vars) {
        _l.setIndex(_l.getIndex() + 1);
        AbstractPageEl abs_ = _conf.getLastPage();

        abs_.setGlobalOffset(variableNameOffset);
        abs_.setOffset(0);
        LoopVariable lv_ = _vars.getVal(variableName);
        Struct iterator_ = _l.getStructIterator();
        Struct element_ = NullStruct.NULL_VALUE;
        Argument arg_ = Argument.createVoid();
        ExecOperationNode el_ = opList.last();
        if (!el_.getResultClass().isArray()) {
            String locName_ = getNextVar(_conf);
            LocalVariable locVar_ = LocalVariable.newLocalVariable(iterator_,_conf);
            abs_.getInternVars().put(locName_, locVar_);
            ExpressionLanguage dyn_ = abs_.getCurrentEl(_conf,this, CustList.SECOND_INDEX, 3);
            arg_ = ElUtil.tryToCalculate(_conf,dyn_,0);
        } else {
            Struct container_ = lv_.getContainer();
            LongStruct lg_ = new LongStruct(_l.getIndex());
            element_ = ExecInvokingOperation.getElement(container_, lg_, _conf);
        }
        if (_conf.callsOrException()) {
            return;
        }
        abs_.clearCurrentEls();
        if (!el_.getResultClass().isArray()) {
            element_ = arg_.getStruct();
        } else {
            arg_ = new Argument(element_);
        }
        String className_ = abs_.formatVarType(importedClassName, _conf);
        if (!Templates.checkQuick(className_, arg_, _conf)) {
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

    public CustList<ExecOperationNode> getOpList() {
        return opList;
    }
}
