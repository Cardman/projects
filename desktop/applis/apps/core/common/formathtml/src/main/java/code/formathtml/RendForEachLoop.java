package code.formathtml;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.Argument;
import code.expressionlanguage.analyze.ManageTokens;
import code.expressionlanguage.analyze.TokenErrorMessage;
import code.expressionlanguage.analyze.inherits.AnaTemplates;
import code.expressionlanguage.analyze.types.AnaTypeUtil;
import code.expressionlanguage.analyze.variables.AnaLocalVariable;
import code.expressionlanguage.common.ConstType;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.ConditionReturn;
import code.expressionlanguage.analyze.variables.AnaLoopVariable;
import code.expressionlanguage.errors.custom.*;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.variables.LocalVariable;
import code.expressionlanguage.files.OffsetStringInfo;
import code.expressionlanguage.files.OffsetsBlock;
import code.expressionlanguage.analyze.inherits.Mapping;
import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.analyze.blocks.ImportForEachLoop;
import code.expressionlanguage.exec.opers.ExecInvokingOperation;
import code.expressionlanguage.inherits.ClassArgumentMatching;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.options.IterableAnalysisResult;
import code.expressionlanguage.structs.*;
import code.expressionlanguage.analyze.types.ResolvingImportTypes;
import code.expressionlanguage.exec.variables.LoopVariable;
import code.formathtml.exec.RendDynOperationNode;
import code.formathtml.stacks.RendLoopBlockStack;
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
    private boolean okVar = true;

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
        importedClassIndexName = ResolvingImportTypes.resolveCorrectType(_cont.getContext(),classIndexName);
        if (!AnaTypeUtil.isIntOrderClass(new ClassArgumentMatching(importedClassIndexName), _cont.getContext())) {
            Mapping mapping_ = new Mapping();
            mapping_.setArg(importedClassIndexName);
            mapping_.setParam(_cont.getStandards().getAliasLong());
            FoundErrorInterpret cast_ = new FoundErrorInterpret();
            cast_.setFileName(_cont.getAnalyzingDoc().getFileName());
            cast_.setIndexFile(classIndexNameOffset);
            cast_.buildError(_cont.getContext().getAnalyzing().getAnalysisMessages().getNotPrimitiveWrapper(),
                    importedClassIndexName);
            Configuration.addError(cast_, _cont.getAnalyzingDoc(), _cont.getContext().getAnalyzing());
        }
        AnalyzedPageEl page_ = _cont.getAnalyzing();
        TokenErrorMessage res_ = ManageTokens.partVar(page_).checkTokenVar(variableName, page_);
        if (res_.isError()) {
            okVar = false;
            FoundErrorInterpret b_ = new FoundErrorInterpret();
            b_.setFileName(_cont.getAnalyzingDoc().getFileName());
            b_.setIndexFile(variableNameOffset);
            b_.setBuiltError(res_.getMessage());
            Configuration.addError(b_, _cont.getAnalyzingDoc(), _cont.getContext().getAnalyzing());
        }
        page_.setGlobalOffset(classNameOffset);
        page_.setOffset(0);
        if (!toInfer(_cont)) {
            importedClassName = ResolvingImportTypes.resolveCorrectType(_cont.getContext(),className);
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
        ClassArgumentMatching compo_ = StringExpUtil.getQuickComponentType(el_.getResultClass());
        if (toInfer(_cont) && compo_.getNames().onlyOneElt()) {
            importedClassName = compo_.getName();
        } else {
            if (importedClassName.isEmpty()) {
                FoundErrorInterpret cast_ = new FoundErrorInterpret();
                cast_.setFileName(_cont.getAnalyzingDoc().getFileName());
                cast_.setIndexFile(expressionOffset);
                cast_.buildError(_cont.getContext().getAnalyzing().getAnalysisMessages().getUnknownType(),
                        className.trim());
                Configuration.addError(cast_, _cont.getAnalyzingDoc(), _cont.getContext().getAnalyzing());
            } else {
                Mapping mapping_ = new Mapping();
                mapping_.setArg(compo_);
                mapping_.setParam(importedClassName);
                StringMap<StringList> vars_ = _cont.getAnalyzing().getCurrentConstraints().getCurrentConstraints();
                mapping_.setMapping(vars_);
                if (!AnaTemplates.isCorrectOrNumbers(mapping_, _cont.getContext())) {
                    FoundErrorInterpret cast_ = new FoundErrorInterpret();
                    cast_.setFileName(_cont.getAnalyzingDoc().getFileName());
                    cast_.setIndexFile(expressionOffset);
                    cast_.buildError(_cont.getContext().getAnalyzing().getAnalysisMessages().getBadImplicitCast(),
                            StringList.join(compo_.getNames(),AND_ERR),
                            importedClassName);
                    Configuration.addError(cast_, _cont.getAnalyzingDoc(), _cont.getContext().getAnalyzing());
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
            static_.setFileName(_cont.getAnalyzingDoc().getFileName());
            static_.setIndexFile(expressionOffset);
            static_.buildError(_cont.getContext().getAnalyzing().getAnalysisMessages().getNullValue(),
                    _cont.getStandards().getAliasNullPe());
            Configuration.addError(static_, _cont.getAnalyzingDoc(), _cont.getContext().getAnalyzing());
        } else if (el_.getResultClass().isArray()) {
            inferArrayClass(_cont);
        } else {
            StringList names_ = el_.getResultClass().getNames();
            StringList out_ = getInferredIterable(names_, _cont);
            checkIterableCandidates(out_, _cont);
        }
        if (!okVar) {
            return;
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
            String paramArg_ = StringExpUtil.getAllTypes(type_).last();
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
                if (!AnaTemplates.isCorrectOrNumbers(mapping_, _cont.getContext())) {
                    FoundErrorInterpret cast_ = new FoundErrorInterpret();
                    cast_.setFileName(_cont.getAnalyzingDoc().getFileName());
                    cast_.setIndexFile(expressionOffset);
                    cast_.buildError(_cont.getContext().getAnalyzing().getAnalysisMessages().getBadImplicitCast(),
                            paramArg_,
                            importedClassName);
                    Configuration.addError(cast_, _cont.getAnalyzingDoc(), _cont.getContext().getAnalyzing());
                }
            }
        } else {
            Mapping mapping_ = new Mapping();
            mapping_.setArg(_cont.getStandards().getAliasObject());
            mapping_.setParam(_cont.getStandards().getAliasIterable());
            FoundErrorInterpret cast_ = new FoundErrorInterpret();
            cast_.setFileName(_cont.getAnalyzingDoc().getFileName());
            cast_.setIndexFile(expressionOffset);
            cast_.buildError(_cont.getContext().getAnalyzing().getAnalysisMessages().getBadImplicitCast(),
                    _cont.getStandards().getAliasObject(),
                    _cont.getStandards().getAliasIterable());
            Configuration.addError(cast_, _cont.getAnalyzingDoc(), _cont.getContext().getAnalyzing());
        }
    }
    private boolean toInfer(Configuration _cont) {
        KeyWords keyWords_ = _cont.getKeyWords();
        String keyWordVar_ = keyWords_.getKeyWordVar();
        return StringList.quickEq(className.trim(), keyWordVar_) || className.trim().isEmpty();
    }
    public void putVariable(Configuration _cont) {
        AnaLoopVariable lv_ = new AnaLoopVariable();
        lv_.setIndexClassName(importedClassIndexName);
        _cont.getAnalyzing().getLoopsVars().put(variableName, lv_);
        AnaLocalVariable lInfo_ = new AnaLocalVariable();
        if (!importedClassName.isEmpty()) {
            lInfo_.setClassName(importedClassName);
        } else {
            lInfo_.setClassName(_cont.getStandards().getAliasObject());
        }
        lInfo_.setConstType(ConstType.FIX_VAR);
        _cont.getAnalyzing().getInfosVars().put(variableName, lInfo_);
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
                _cont.setException(new ErrorStruct(_cont.getContext(), npe_));
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
        l_.setContainer(its_);
        ip_.addBlock(l_);
        LoopVariable lv_ = new LoopVariable();
        lv_.setIndex(-1);
        Struct struct_ = PrimitiveTypeUtil.defaultValue(importedClassName, _cont.getContext());
        lv_.setIndexClassName(importedClassIndexName);
        StringMap<LoopVariable> varsLoop_ = ip_.getVars();
        varsLoop_.put(variableName, lv_);
        ip_.putValueVar(variableName, LocalVariable.newLocalVariable(struct_,importedClassName));
        processLastElementLoop(_cont);
    }

    private int getLength(Struct _str,Configuration _cont) {
        if (_str instanceof ArrayStruct) {
            return ((ArrayStruct)_str).getInstance().length;
        }
        String npe_ = _cont.getStandards().getAliasNullPe();
        _cont.setException(new ErrorStruct(_cont.getContext(), npe_));
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
        StringMap<LocalVariable> vInfo_ = _ip.getValueVars();
        vInfo_.removeKey(variableName);
    }

    @Override
    public void removeAllVars(AnalyzedPageEl _ip) {
        super.removeAllVars(_ip);
        _ip.getInfosVars().removeKey(variableName);
        _ip.getLoopsVars().removeKey(variableName);
    }

    @Override
    public void processLastElementLoop(Configuration _conf) {
        ImportingPage ip_ = _conf.getLastPage();
        StringMap<LoopVariable> vars_ = ip_.getVars();
        StringMap<LocalVariable> varsInfos_ = ip_.getValueVars();
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
            incrementLoop(_conf, l_, vars_,varsInfos_);
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
        if (BooleanStruct.isTrue(arg_.getStruct())) {
            return ConditionReturn.YES;
        }
        return ConditionReturn.NO;
    }

    public void incrementLoop(Configuration _conf, RendLoopBlockStack _l,
                              StringMap<LoopVariable> _vars,
                              StringMap<LocalVariable> _varsInfos) {
        _l.setIndex(_l.getIndex() + 1);
        ImportingPage abs_ = _conf.getLastPage();

//        abs_.setGlobalOffset(variableNameOffset);
        LoopVariable lv_ = _vars.getVal(variableName);
        LocalVariable lInfo_ = _varsInfos.getVal(variableName);
        Struct iterator_ = _l.getStructIterator();
        Struct element_ = NullStruct.NULL_VALUE;
        Argument arg_ = Argument.createVoid();
        RendDynOperationNode el_ = opList.last();
        if (!el_.getResultClass().isArray()) {
            arg_ = next(iterator_,_conf);
        } else {
            Struct container_ = _l.getContainer();
            LongStruct lg_ = new LongStruct(_l.getIndex());
            element_ = ExecInvokingOperation.getElement(container_, lg_, _conf.getContext());
        }
        if (_conf.getContext().hasException()) {
            return;
        }
        if (!el_.getResultClass().isArray()) {
            element_ = arg_.getStruct();
        } else {
            arg_ = new Argument(element_);
        }
        if (!ExecTemplates.checkQuick(importedClassName, arg_, _conf.getContext())) {
            return;
        }
        lInfo_.setStruct(element_);
        lv_.setIndex(lv_.getIndex() + 1);
        abs_.getRendReadWrite().setRead(getFirstChild());
    }
}
