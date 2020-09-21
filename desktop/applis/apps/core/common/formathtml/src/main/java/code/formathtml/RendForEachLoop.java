package code.formathtml;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.Argument;
import code.expressionlanguage.analyze.ManageTokens;
import code.expressionlanguage.analyze.TokenErrorMessage;
import code.expressionlanguage.analyze.inherits.AnaTemplates;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.analyze.types.AnaTypeUtil;
import code.expressionlanguage.analyze.variables.AnaLocalVariable;
import code.expressionlanguage.common.ConstType;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.ConditionReturn;
import code.expressionlanguage.analyze.variables.AnaLoopVariable;
import code.expressionlanguage.errors.custom.*;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.types.ExecClassArgumentMatching;
import code.expressionlanguage.exec.variables.LocalVariable;
import code.expressionlanguage.files.OffsetStringInfo;
import code.expressionlanguage.files.OffsetsBlock;
import code.expressionlanguage.analyze.inherits.Mapping;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.analyze.blocks.ImportForEachLoop;
import code.expressionlanguage.exec.opers.ExecInvokingOperation;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.options.IterableAnalysisResult;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.*;
import code.expressionlanguage.analyze.types.ResolvingImportTypes;
import code.expressionlanguage.exec.variables.LoopVariable;
import code.formathtml.exec.RendDynOperationNode;
import code.formathtml.stacks.RendLoopBlockStack;
import code.formathtml.util.AnalyzingDoc;
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

    RendForEachLoop(OffsetStringInfo _className, OffsetStringInfo _variable,
                    OffsetStringInfo _expression, OffsetStringInfo _classIndex, OffsetStringInfo _label, OffsetsBlock _offset, LgNames _stds) {
        super(_offset);
        className = _className.getInfo();
        classNameOffset = _className.getOffset();
        variableName = _variable.getInfo();
        variableNameOffset = _variable.getOffset();
        expression = _expression.getInfo();
        expressionOffset = _expression.getOffset();
        String classIndex_ = _classIndex.getInfo();
        if (classIndex_.isEmpty()) {
            classIndex_ = _stds.getAliasPrimInteger();
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
    public OperationNode buildEl(Configuration _cont, RendDocumentBlock _doc, AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
        importedClassIndexName = ResolvingImportTypes.resolveCorrectType(classIndexName, _page);
        if (!AnaTypeUtil.isIntOrderClass(new AnaClassArgumentMatching(importedClassIndexName), _page)) {
            Mapping mapping_ = new Mapping();
            mapping_.setArg(importedClassIndexName);
            mapping_.setParam(_page.getStandards().getAliasLong());
            FoundErrorInterpret cast_ = new FoundErrorInterpret();
            cast_.setFileName(_anaDoc.getFileName());
            cast_.setIndexFile(classIndexNameOffset);
            cast_.buildError(_page.getAnalysisMessages().getNotPrimitiveWrapper(),
                    importedClassIndexName);
            Configuration.addError(cast_, _anaDoc, _page);
        }
        TokenErrorMessage res_ = ManageTokens.partVar(_page).checkTokenVar(variableName, _page);
        if (res_.isError()) {
            okVar = false;
            FoundErrorInterpret b_ = new FoundErrorInterpret();
            b_.setFileName(_anaDoc.getFileName());
            b_.setIndexFile(variableNameOffset);
            b_.setBuiltError(res_.getMessage());
            Configuration.addError(b_, _anaDoc, _page);
        }
        _page.setGlobalOffset(classNameOffset);
        _page.setOffset(0);
        if (!toInfer(_page)) {
            importedClassName = ResolvingImportTypes.resolveCorrectType(className, _page);
        } else {
            importedClassName = EMPTY_STRING;
        }
        _page.setGlobalOffset(expressionOffset);
        _page.setOffset(0);
        _anaDoc.setAttribute(_cont.getRendKeyWords().getAttrList());
        opList = RenderExpUtil.getAnalyzedOperations(expression, 0, _anaDoc, _page);
        return _page.getCurrentRoot();
    }
    public void inferArrayClass(AnalyzingDoc _anaDoc, OperationNode _root, AnalyzedPageEl _page) {
        AnaClassArgumentMatching compo_ = StringExpUtil.getQuickComponentType(_root.getResultClass());
        if (toInfer(_page) && compo_.getNames().onlyOneElt()) {
            importedClassName = compo_.getName();
        } else {
            if (importedClassName.isEmpty()) {
                FoundErrorInterpret cast_ = new FoundErrorInterpret();
                cast_.setFileName(_anaDoc.getFileName());
                cast_.setIndexFile(expressionOffset);
                cast_.buildError(_page.getAnalysisMessages().getUnknownType(),
                        className.trim());
                Configuration.addError(cast_, _anaDoc, _page);
            } else {
                Mapping mapping_ = new Mapping();
                mapping_.setArg(compo_);
                mapping_.setParam(importedClassName);
                StringMap<StringList> vars_ = _page.getCurrentConstraints().getCurrentConstraints();
                mapping_.setMapping(vars_);
                if (!AnaTemplates.isCorrectOrNumbers(mapping_, _page)) {
                    FoundErrorInterpret cast_ = new FoundErrorInterpret();
                    cast_.setFileName(_anaDoc.getFileName());
                    cast_.setIndexFile(expressionOffset);
                    cast_.buildError(_page.getAnalysisMessages().getBadImplicitCast(),
                            StringList.join(compo_.getNames(),AND_ERR),
                            importedClassName);
                    Configuration.addError(cast_, _anaDoc, _page);
                }
            }
        }
    }
    @Override
    public void buildExpressionLanguage(Configuration _cont, RendDocumentBlock _doc, AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
        OperationNode root_ = buildEl(_cont, _doc, _anaDoc, _page);
        RendDynOperationNode el_ = opList.last();
        Argument arg_ = el_.getArgument();
        if (Argument.isNullValue(arg_)) {
            FoundErrorInterpret static_ = new FoundErrorInterpret();
            static_.setFileName(_anaDoc.getFileName());
            static_.setIndexFile(expressionOffset);
            static_.buildError(_page.getAnalysisMessages().getNullValue(),
                    _page.getStandards().getAliasNullPe());
            Configuration.addError(static_, _anaDoc, _page);
        } else if (root_.getResultClass().isArray()) {
            inferArrayClass(_anaDoc, root_, _page);
        } else {
            StringList names_ = el_.getResultClass().getNames();
            StringList out_ = getInferredIterable(names_, _page);
            checkIterableCandidates(out_, _anaDoc, _page);
        }
        if (!okVar) {
            return;
        }
        putVariable(_page);
    }
    public StringList getInferredIterable(StringList _types, AnalyzedPageEl _page) {
        IterableAnalysisResult it_ = _page.getStandards().getCustomType(_types,importedClassName, _page);
        return it_.getClassName();
    }
    public void checkIterableCandidates(StringList _types, AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
        if (_types.onlyOneElt()) {
            String type_ = _types.first();
            Mapping mapping_ = new Mapping();
            String paramArg_ = StringExpUtil.getAllTypes(type_).last();
            if (StringList.quickEq(paramArg_, Templates.SUB_TYPE)) {
                paramArg_ = _page.getStandards().getAliasObject();
            } else if (paramArg_.startsWith(Templates.SUB_TYPE)) {
                paramArg_ = paramArg_.substring(Templates.SUB_TYPE.length());
            } else if (paramArg_.startsWith(Templates.SUP_TYPE)){
                paramArg_ = _page.getStandards().getAliasObject();
            }
            if (toInfer(_page)) {
                importedClassName = paramArg_;
            } else {
                mapping_.setArg(paramArg_);
                mapping_.setParam(importedClassName);
                StringMap<StringList> vars_ = _page.getCurrentConstraints().getCurrentConstraints();
                mapping_.setMapping(vars_);
                if (!AnaTemplates.isCorrectOrNumbers(mapping_, _page)) {
                    FoundErrorInterpret cast_ = new FoundErrorInterpret();
                    cast_.setFileName(_anaDoc.getFileName());
                    cast_.setIndexFile(expressionOffset);
                    cast_.buildError(_page.getAnalysisMessages().getBadImplicitCast(),
                            paramArg_,
                            importedClassName);
                    Configuration.addError(cast_, _anaDoc, _page);
                }
            }
        } else {
            FoundErrorInterpret cast_ = new FoundErrorInterpret();
            cast_.setFileName(_anaDoc.getFileName());
            cast_.setIndexFile(expressionOffset);
            cast_.buildError(_page.getAnalysisMessages().getBadImplicitCast(),
                    _page.getStandards().getAliasObject(),
                    _page.getStandards().getAliasIterable());
            Configuration.addError(cast_, _anaDoc, _page);
        }
    }
    private boolean toInfer(AnalyzedPageEl _page) {
        KeyWords keyWords_ = _page.getKeyWords();
        String keyWordVar_ = keyWords_.getKeyWordVar();
        return StringList.quickEq(className.trim(), keyWordVar_) || className.trim().isEmpty();
    }
    public void putVariable(AnalyzedPageEl _page) {
        AnaLoopVariable lv_ = new AnaLoopVariable();
        lv_.setIndexClassName(importedClassIndexName);
        _page.getLoopsVars().put(variableName, lv_);
        AnaLocalVariable lInfo_ = new AnaLocalVariable();
        if (!importedClassName.isEmpty()) {
            lInfo_.setClassName(importedClassName);
        } else {
            lInfo_.setClassName(_page.getStandards().getAliasObject());
        }
        lInfo_.setConstType(ConstType.FIX_VAR);
        _page.getInfosVars().put(variableName, lInfo_);
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
        ContextEl context_ = _cont.getContext();
        if (context_.hasException()) {
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
            if (context_.hasException()) {
                return;
            }
        } else {
            if (its_ == NullStruct.NULL_VALUE) {
                String npe_ = context_.getStandards().getAliasNullPe();
                _cont.setException(new ErrorStruct(context_, npe_));
                return;
            }
            Argument arg_ = iterator(its_,_cont);
            if (context_.hasException()) {
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
        Struct struct_ = ExecClassArgumentMatching.defaultValue(importedClassName, context_);
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
        ContextEl context_ = _cont.getContext();
        String npe_ = context_.getStandards().getAliasNullPe();
        _cont.setException(new ErrorStruct(context_, npe_));
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
