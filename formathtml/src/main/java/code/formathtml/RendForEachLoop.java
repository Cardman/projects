package code.formathtml;

import code.expressionlanguage.AnalyzedPageEl;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ConditionReturn;
import code.expressionlanguage.errors.custom.*;
import code.expressionlanguage.files.OffsetStringInfo;
import code.expressionlanguage.files.OffsetsBlock;
import code.expressionlanguage.inherits.Mapping;
import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.methods.ImportForEachLoop;
import code.expressionlanguage.opers.Calculation;
import code.expressionlanguage.opers.exec.ExecInvokingOperation;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.opers.util.MethodAccessKind;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.stds.IterableAnalysisResult;
import code.expressionlanguage.structs.*;
import code.expressionlanguage.types.ResolvingImportTypes;
import code.expressionlanguage.variables.LoopVariable;
import code.formathtml.exec.RendDynOperationNode;
import code.formathtml.stacks.RendLoopBlockStack;
import code.formathtml.util.BeanCustLgNames;
import code.formathtml.util.BeanLgNames;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;

public final class RendForEachLoop extends RendParentBlock implements RendLoop, RendReducableOperations,ImportForEachLoop {

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

    private CustList<RendDynOperationNode> opList;

    RendForEachLoop(Configuration _importingPage,
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
        return getLabel();
    }

    @Override
    public void reduce(Configuration _context) {
        RendDynOperationNode r_ = opList.last();
        opList = RenderExpUtil.getReducedNodes(r_);
    }
    @Override
    public String getImportedClassName() {
        return importedClassName;
    }
    public void buildEl(Configuration _cont,RendDocumentBlock _doc) {
        importedClassIndexName = ResolvingImportTypes.resolveCorrectType(_cont,classIndexName);
        if (!PrimitiveTypeUtil.isPureNumberClass(new ClassArgumentMatching(importedClassIndexName), _cont)) {
            Mapping mapping_ = new Mapping();
            mapping_.setArg(importedClassIndexName);
            mapping_.setParam(_cont.getStandards().getAliasLong());
            FoundErrorInterpret cast_ = new FoundErrorInterpret();
            cast_.setFileName(_cont.getCurrentFileName());
            cast_.setIndexFile(classIndexNameOffset);
            cast_.buildError(_cont.getContext().getAnalysisMessages().getNotPrimitiveWrapper(),
                    importedClassIndexName);
            _cont.addError(cast_);
        }
        if (_cont.getAnalyzing().containsVar(variableName)) {
            FoundErrorInterpret d_ = new FoundErrorInterpret();
            d_.setFileName(_cont.getCurrentFileName());
            d_.setIndexFile(variableNameOffset);
            d_.buildError(_cont.getContext().getAnalysisMessages().getBadVariableName(),
                    variableName);
            _cont.addError(d_);
        }
        if (_cont.getAnalyzing().containsMutableLoopVar(variableName)) {
            FoundErrorInterpret d_ = new FoundErrorInterpret();
            d_.setFileName(_cont.getCurrentFileName());
            d_.setIndexFile(variableNameOffset);
            d_.buildError(_cont.getContext().getAnalysisMessages().getBadVariableName(),
                    variableName);
            _cont.addError(d_);
        }
        if (!_cont.isValidSingleToken(variableName)) {
            FoundErrorInterpret b_ = new FoundErrorInterpret();
            b_.setFileName(_cont.getCurrentFileName());
            b_.setIndexFile(variableNameOffset);
            b_.buildError(_cont.getContext().getAnalysisMessages().getBadVariableName(),
                    variableName);
            _cont.addError(b_);
        }
        AnalyzedPageEl page_ = _cont.getAnalyzing();
        page_.setGlobalOffset(classNameOffset);
        page_.setOffset(0);
        if (!toInfer(_cont)) {
            importedClassName = ResolvingImportTypes.resolveCorrectType(_cont,className);
        } else {
            importedClassName = EMPTY_STRING;
        }
        page_.setGlobalOffset(expressionOffset);
        page_.setOffset(0);
        _cont.getAnalyzingDoc().setAttribute(_cont.getRendKeyWords().getAttrList());
        opList = RenderExpUtil.getAnalyzedOperations(expression,expressionOffset,0, _cont);
    }
    public void inferArrayClass(Configuration _cont) {
        RendDynOperationNode el_ = opList.last();
        ClassArgumentMatching compo_ = PrimitiveTypeUtil.getQuickComponentType(el_.getResultClass());
        if (toInfer(_cont) && compo_.getNames().onlyOneElt()) {
            importedClassName = compo_.getName();
        } else {
            if (importedClassName.isEmpty()) {
                FoundErrorInterpret cast_ = new FoundErrorInterpret();
                cast_.setFileName(_cont.getCurrentFileName());
                cast_.setIndexFile(expressionOffset);
                cast_.buildError(_cont.getContext().getAnalysisMessages().getUnknownType(),
                        className.trim());
                _cont.addError(cast_);
            } else {
                Mapping mapping_ = new Mapping();
                mapping_.setArg(compo_);
                mapping_.setParam(importedClassName);
                StringMap<StringList> vars_ = _cont.getAnalyzing().getCurrentConstraints().getCurrentConstraints();
                mapping_.setMapping(vars_);
                if (!Templates.isCorrectOrNumbers(mapping_, _cont)) {
                    FoundErrorInterpret cast_ = new FoundErrorInterpret();
                    cast_.setFileName(_cont.getCurrentFileName());
                    cast_.setIndexFile(expressionOffset);
                    cast_.buildError(_cont.getContextEl().getAnalysisMessages().getBadImplicitCast(),
                            StringList.join(compo_.getNames(),AND_ERR),
                            importedClassName);
                    _cont.addError(cast_);
                }
            }
        }
    }
    @Override
    public void buildExpressionLanguage(Configuration _cont,RendDocumentBlock _doc) {
        buildEl(_cont,_doc);
        RendDynOperationNode el_ = opList.last();
        Argument arg_ = el_.getArgument();
        if (Argument.isNullValue(arg_)) {
            FoundErrorInterpret static_ = new FoundErrorInterpret();
            static_.setFileName(_cont.getCurrentFileName());
            static_.setIndexFile(expressionOffset);
            static_.buildError(_cont.getContext().getAnalysisMessages().getNullValue(),
                    _cont.getStandards().getAliasNullPe());
            _cont.addError(static_);
        } else if (el_.getResultClass().isArray()) {
            inferArrayClass(_cont);
        } else {
            StringList names_ = el_.getResultClass().getNames();
            StringList out_ = getInferredIterable(names_, _cont);
            checkIterableCandidates(out_, _cont);
        }
        putVariable(_cont);
    }
    public StringList getInferredIterable(StringList _types, Configuration _cont) {
        IterableAnalysisResult it_ = _cont.getStandards().getCustomType(_types,importedClassName, _cont.getContext());
        return it_.getClassName();
    }
    public void checkIterableCandidates(StringList _types,Configuration _cont) {
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
            if (toInfer(_cont)) {
                importedClassName = paramArg_;
            } else {
                mapping_.setArg(paramArg_);
                mapping_.setParam(importedClassName);
                StringMap<StringList> vars_ = _cont.getAnalyzing().getCurrentConstraints().getCurrentConstraints();
                mapping_.setMapping(vars_);
                if (!Templates.isCorrectOrNumbers(mapping_, _cont)) {
                    FoundErrorInterpret cast_ = new FoundErrorInterpret();
                    cast_.setFileName(_cont.getCurrentFileName());
                    cast_.setIndexFile(expressionOffset);
                    cast_.buildError(_cont.getContextEl().getAnalysisMessages().getBadImplicitCast(),
                            paramArg_,
                            importedClassName);
                    _cont.addError(cast_);
                }
            }
        } else {
            Mapping mapping_ = new Mapping();
            mapping_.setArg(_cont.getStandards().getAliasObject());
            mapping_.setParam(_cont.getStandards().getAliasIterable());
            FoundErrorInterpret cast_ = new FoundErrorInterpret();
            cast_.setFileName(_cont.getCurrentFileName());
            cast_.setIndexFile(expressionOffset);
            cast_.buildError(_cont.getContextEl().getAnalysisMessages().getBadImplicitCast(),
                    _cont.getStandards().getAliasObject(),
                    _cont.getStandards().getAliasIterable());
            _cont.addError(cast_);
        }
    }
    private boolean toInfer(Configuration _cont) {
        KeyWords keyWords_ = _cont.getKeyWords();
        String keyWordVar_ = keyWords_.getKeyWordVar();
        return StringList.quickEq(className.trim(), keyWordVar_) || className.trim().isEmpty();
    }
    public void putVariable(Configuration _cont) {
        LoopVariable lv_ = new LoopVariable();
        if (!importedClassName.isEmpty()) {
            lv_.setClassName(importedClassName);
        } else {
            lv_.setClassName(_cont.getStandards().getAliasObject());
        }
        lv_.setIndexClassName(importedClassIndexName);
        _cont.getAnalyzing().putVar(variableName, lv_);
    }

    @Override
    public void processEl(Configuration _cont) {
        ImportingPage ip_ = _cont.getLastPage();
        RendLoopBlockStack c_ = ip_.getLastLoopIfPossible(this);
        if (c_ != null) {
            processBlockAndRemove(_cont);
            return;
        }
        Struct its_ = processLoop(_cont);
        if (_cont.getContext().hasException()) {
            return;
        }
        Struct iterStr_ = null;
        long length_ = CustList.INDEX_NOT_FOUND_ELT;
        boolean finished_ = false;
        RendDynOperationNode el_ = opList.last();
        if (el_.getResultClass().isArray()) {
            length_ = getLength(its_,_cont);
            if (length_ == CustList.SIZE_EMPTY) {
                finished_ = true;
            }
            if (_cont.getContext().hasException()) {
                return;
            }
        } else {
            if (its_ == NullStruct.NULL_VALUE) {
                String npe_ = _cont.getStandards().getAliasNullPe();
                _cont.setException(new ErrorStruct(_cont, npe_));
                return;
            }
            Argument arg_ = iterator(its_,_cont);
            if (_cont.getContext().hasException()) {
                return;
            }
            iterStr_ = arg_.getStruct();
        }
        RendLoopBlockStack l_ = new RendLoopBlockStack();
        l_.setIndex(-1);
        l_.setFinished(finished_);
        l_.setBlock(this);
        l_.setCurrentVisitedBlock(this);
        l_.setStructIterator(iterStr_);
        l_.setMaxIteration(length_);
        ip_.addBlock(l_);
        LoopVariable lv_ = new LoopVariable();
        lv_.setIndex(-1);
        lv_.setClassName(importedClassName);
        lv_.setIndexClassName(importedClassIndexName);
        lv_.setContainer(its_);
        StringMap<LoopVariable> varsLoop_ = ip_.getVars();
        varsLoop_.put(variableName, lv_);
        processLastElementLoop(_cont);
    }

    private int getLength(Struct _str,Configuration _cont) {
        if (_str instanceof ArrayStruct) {
            return ((ArrayStruct)_str).getInstance().length;
        }
        String npe_ = _cont.getStandards().getAliasNullPe();
        _cont.setException(new ErrorStruct(_cont, npe_));
        return -1;
    }
    Struct processLoop(Configuration _conf) {
        ImportingPage ip_ = _conf.getLastPage();
        ip_.setOffset(expressionOffset);
        ip_.setProcessingAttribute(_conf.getRendKeyWords().getAttrList());
        Argument arg_ = RenderExpUtil.calculateReuse(opList,_conf);
        if (_conf.getContext().hasException()) {
            return NullStruct.NULL_VALUE;
        }
        return arg_.getStruct();

    }

    @Override
    public void exitStack(Configuration _context) {
        processLastElementLoop(_context);
    }

    @Override
    public void removeAllVars(ImportingPage _ip) {
        super.removeAllVars(_ip);
        StringMap<LoopVariable> v_ = _ip.getVars();
        v_.removeKey(variableName);
    }


    @Override
    public void processLastElementLoop(Configuration _conf) {
        ImportingPage ip_ = _conf.getLastPage();
        StringMap<LoopVariable> vars_ = ip_.getVars();
        RendLoopBlockStack l_ = (RendLoopBlockStack) ip_.getRendLastStack();
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

        if (hasNext_) {
            incrementLoop(_conf, l_, vars_);
        } else {
            l_.setFinished(true);
        }
    }

    private ConditionReturn iteratorHasNext(Configuration _conf) {
        ImportingPage ip_ = _conf.getLastPage();
        RendLoopBlockStack l_ = (RendLoopBlockStack) ip_.getRendLastStack();
        Struct strIter_ = l_.getStructIterator();
        Argument arg_ = hasNext(strIter_,_conf);
        if (_conf.getContext().hasException()) {
            return ConditionReturn.CALL_EX;
        }
        if (BooleanStruct.of(true).sameReference(arg_.getStruct())) {
            return ConditionReturn.YES;
        }
        return ConditionReturn.NO;
    }

    public void incrementLoop(Configuration _conf, RendLoopBlockStack _l,
                              StringMap<LoopVariable> _vars) {
        _l.setIndex(_l.getIndex() + 1);
        ImportingPage abs_ = _conf.getLastPage();

//        abs_.setGlobalOffset(variableNameOffset);
        LoopVariable lv_ = _vars.getVal(variableName);
        Struct iterator_ = _l.getStructIterator();
        Struct element_ = NullStruct.NULL_VALUE;
        Argument arg_ = Argument.createVoid();
        RendDynOperationNode el_ = opList.last();
        if (!el_.getResultClass().isArray()) {
            arg_ = next(iterator_,_conf);
        } else {
            Struct container_ = lv_.getContainer();
            LongStruct lg_ = new LongStruct(_l.getIndex());
            element_ = ExecInvokingOperation.getElement(container_, lg_, _conf);
        }
        if (_conf.getContext().hasException()) {
            return;
        }
        if (!el_.getResultClass().isArray()) {
            element_ = arg_.getStruct();
        } else {
            arg_ = new Argument(element_);
        }
        if (!Templates.checkObject(importedClassName, arg_, _conf)) {
            return;
        }
        lv_.setStruct(element_);
        lv_.setIndex(lv_.getIndex() + 1);
        abs_.getRendReadWrite().setRead(getFirstChild());
    }
}
