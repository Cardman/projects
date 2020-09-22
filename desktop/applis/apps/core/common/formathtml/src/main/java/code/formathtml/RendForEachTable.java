package code.formathtml;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.Argument;
import code.expressionlanguage.analyze.ManageTokens;
import code.expressionlanguage.analyze.TokenErrorMessage;
import code.expressionlanguage.analyze.inherits.AnaTemplates;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.analyze.types.AnaTypeUtil;
import code.expressionlanguage.analyze.variables.AnaLocalVariable;
import code.expressionlanguage.common.ConstType;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.ConditionReturn;
import code.expressionlanguage.analyze.variables.AnaLoopVariable;
import code.expressionlanguage.analyze.errors.custom.*;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.types.ExecClassArgumentMatching;
import code.expressionlanguage.exec.variables.LocalVariable;
import code.expressionlanguage.analyze.files.OffsetStringInfo;
import code.expressionlanguage.analyze.files.OffsetsBlock;
import code.expressionlanguage.analyze.inherits.Mapping;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.analyze.blocks.ImportForEachTable;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.ErrorStruct;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.analyze.types.ResolvingImportTypes;
import code.expressionlanguage.exec.variables.LoopVariable;
import code.formathtml.exec.RendDynOperationNode;
import code.formathtml.util.AnalyzingDoc;
import code.formathtml.util.BeanLgNames;
import code.formathtml.stacks.RendLoopBlockStack;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;

public final class RendForEachTable extends RendParentBlock implements RendLoop, RendWithEl,RendReducableOperations,ImportForEachTable {

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

    private CustList<RendDynOperationNode> opList;
    private boolean okVarFirst = true;
    private boolean okVarSecond = true;

    RendForEachTable(OffsetStringInfo _className, OffsetStringInfo _variable,
                     OffsetStringInfo _classNameSec, OffsetStringInfo _variableSec,
                     OffsetStringInfo _expression, OffsetStringInfo _classIndex, OffsetStringInfo _label, OffsetsBlock _offset, LgNames _stds) {
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
            classIndex_ = _stds.getAliasPrimInteger();
        }
        classIndexName = classIndex_;
        label = _label.getInfo();
        labelOffset = _label.getOffset();
        classIndexNameOffset = _classIndex.getOffset();
    }

    @Override
    public String getRealLabel() {
        return getLabel();
    }

    @Override
    public void buildExpressionLanguage(Configuration _cont, RendDocumentBlock _doc, AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
        buildEl(_cont,_doc, _anaDoc, _page);
        RendDynOperationNode el_ = opList.last();
        Argument arg_ = el_.getArgument();
        if (Argument.isNullValue(arg_)) {
            FoundErrorInterpret static_ = new FoundErrorInterpret();
            static_.setFileName(_anaDoc.getFileName());
            static_.setIndexFile(expressionOffset);
            static_.buildError(_page.getAnalysisMessages().getNullValue(),
                    _page.getStandards().getAliasNullPe());
            Configuration.addError(static_, _anaDoc, _page);
        } else {
            StringList names_ = el_.getResultClass().getNames();
            StringList out_ = getCustomType(names_, _cont, _page);
            checkIterableCandidates(out_, _cont, _anaDoc, _page);
        }
        putVariable(_anaDoc, _page);
    }

    @Override
    public void reduce(Configuration _context) {
        RendDynOperationNode r_ = opList.last();
        opList = RenderExpUtil.getReducedNodes(r_);
    }

    private StringList getCustomType(StringList _names, Configuration _context, AnalyzedPageEl _page) {
        BeanLgNames stds_ = _context.getAdvStandards();
        return stds_.getCustomTableType(_names, importedClassNameFirst,importedClassNameSecond, _page).getClassName();
    }

    public void buildEl(Configuration _cont, RendDocumentBlock _doc, AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
        AnalyzedPageEl page_ = _page;
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
        TokenErrorMessage resOne_ = ManageTokens.partVar(page_).checkTokenVar(variableNameFirst, page_);
        if (resOne_.isError()) {
            FoundErrorInterpret b_ = new FoundErrorInterpret();
            b_.setFileName(_anaDoc.getFileName());
            b_.setIndexFile(variableNameOffsetFirst);
            b_.setBuiltError(resOne_.getMessage());
            Configuration.addError(b_, _anaDoc, _page);
            okVarFirst = false;
        }
        TokenErrorMessage resTwo_ = ManageTokens.partVar(page_).checkTokenVar(variableNameSecond, page_);
        if (resTwo_.isError()) {
            FoundErrorInterpret b_ = new FoundErrorInterpret();
            b_.setFileName(_anaDoc.getFileName());
            b_.setIndexFile(variableNameOffsetSecond);
            b_.setBuiltError(resTwo_.getMessage());
            Configuration.addError(b_, _anaDoc, _page);
            okVarSecond = false;
        }
        page_.setGlobalOffset(classNameOffsetFirst);
        page_.setOffset(0);
        if (!toInferFirst(_page)) {
            importedClassNameFirst = ResolvingImportTypes.resolveCorrectType(classNameFirst, _page);
        } else {
            importedClassNameFirst = EMPTY_STRING;
        }
        page_.setGlobalOffset(classNameOffsetSecond);
        page_.setOffset(0);
        if (!toInferSecond(_page)) {
            importedClassNameSecond = ResolvingImportTypes.resolveCorrectType(classNameSecond, _page);
        } else {
            importedClassNameSecond = EMPTY_STRING;
        }
        page_.setGlobalOffset(expressionOffset);
        page_.setOffset(0);
        _anaDoc.setAttribute(_cont.getRendKeyWords().getAttrMap());
        opList = RenderExpUtil.getAnalyzedOperations(expression, 0, _anaDoc, _page);
    }

    public void checkIterableCandidates(StringList _types, Configuration _cont, AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
        if (_types.onlyOneElt()) {
            String type_ = _types.first();
            Mapping mapping_ = new Mapping();
            String paramArg_ = StringExpUtil.getAllTypes(type_).get(1);
            if (StringList.quickEq(paramArg_, Templates.SUB_TYPE)) {
                paramArg_ = _page.getStandards().getAliasObject();
            } else if (paramArg_.startsWith(Templates.SUB_TYPE)) {
                paramArg_ = paramArg_.substring(Templates.SUB_TYPE.length());
            } else if (paramArg_.startsWith(Templates.SUP_TYPE)) {
                paramArg_ = _page.getStandards().getAliasObject();
            }
            if (toInferFirst(_page)) {
                importedClassNameFirst = paramArg_;
            } else {
                mapping_.setArg(paramArg_);
                mapping_.setParam(importedClassNameFirst);
                StringMap<StringList> vars_ = _page.getCurrentConstraints().getCurrentConstraints();
                mapping_.setMapping(vars_);
                if (!AnaTemplates.isCorrectOrNumbers(mapping_, _page)) {
                    FoundErrorInterpret cast_ = new FoundErrorInterpret();
                    cast_.setFileName(_anaDoc.getFileName());
                    cast_.setIndexFile(expressionOffset);
                    cast_.buildError(_page.getAnalysisMessages().getBadImplicitCast(),
                            paramArg_,
                            importedClassNameFirst);
                    Configuration.addError(cast_, _anaDoc, _page);
                }
            }
            mapping_ = new Mapping();
            paramArg_ = StringExpUtil.getAllTypes(type_).last();
            if (StringList.quickEq(paramArg_, Templates.SUB_TYPE)) {
                paramArg_ = _page.getStandards().getAliasObject();
            } else if (paramArg_.startsWith(Templates.SUB_TYPE)) {
                paramArg_ = paramArg_.substring(Templates.SUB_TYPE.length());
            } else if (paramArg_.startsWith(Templates.SUP_TYPE)) {
                paramArg_ = _page.getStandards().getAliasObject();
            }
            if (toInferSecond(_page)) {
                importedClassNameSecond = paramArg_;
            } else {
                mapping_.setArg(paramArg_);
                mapping_.setParam(importedClassNameSecond);
                StringMap<StringList> vars_ = _page.getCurrentConstraints().getCurrentConstraints();
                mapping_.setMapping(vars_);
                if (!AnaTemplates.isCorrectOrNumbers(mapping_, _page)) {
                    FoundErrorInterpret cast_ = new FoundErrorInterpret();
                    cast_.setFileName(_anaDoc.getFileName());
                    cast_.setIndexFile(expressionOffset);
                    cast_.buildError(_page.getAnalysisMessages().getBadImplicitCast(),
                            paramArg_,
                            importedClassNameSecond);
                    Configuration.addError(cast_, _anaDoc, _page);
                }
            }
        } else {
            FoundErrorInterpret cast_ = new FoundErrorInterpret();
            cast_.setFileName(_anaDoc.getFileName());
            cast_.setIndexFile(expressionOffset);
            cast_.buildError(_page.getAnalysisMessages().getBadImplicitCast(),
                    _page.getStandards().getAliasObject(),
                    _page.getStandards().getAliasIterableTable());
            Configuration.addError(cast_, _anaDoc, _page);
        }
    }

    public void putVariable(AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
        if (okVarFirst && okVarSecond) {
            if (StringList.quickEq(variableNameFirst, variableNameSecond)) {
                FoundErrorInterpret d_ = new FoundErrorInterpret();
                d_.setFileName(_anaDoc.getFileName());
                d_.setIndexFile(variableNameOffsetSecond);
                d_.buildError(_page.getAnalysisMessages().getBadVariableName(),
                        variableNameFirst);
                Configuration.addError(d_, _anaDoc, _page);
                return;
            }
        }
        if (okVarFirst) {
            AnaLoopVariable lv_ = new AnaLoopVariable();
            lv_.setIndexClassName(importedClassIndexName);
            _page.getLoopsVars().put(variableNameFirst, lv_);
            AnaLocalVariable lInfo_ = new AnaLocalVariable();
            if (!importedClassNameFirst.isEmpty()) {
                lInfo_.setClassName(importedClassNameFirst);
            } else {
                lInfo_.setClassName(_page.getStandards().getAliasObject());
            }
            lInfo_.setConstType(ConstType.FIX_VAR);
            _page.getInfosVars().put(variableNameFirst, lInfo_);
        }
        if (okVarSecond) {
            AnaLoopVariable lv_ = new AnaLoopVariable();
            lv_.setIndexClassName(importedClassIndexName);
            AnaLocalVariable lInfo_ = new AnaLocalVariable();
            if (!importedClassNameSecond.isEmpty()) {
                lInfo_.setClassName(importedClassNameSecond);
            } else {
                lInfo_.setClassName(_page.getStandards().getAliasObject());
            }
            lInfo_.setConstType(ConstType.FIX_VAR);
            _page.getInfosVars().put(variableNameSecond, lInfo_);
        }
    }

    private boolean toInferFirst(AnalyzedPageEl _page) {
        KeyWords keyWords_ = _page.getKeyWords();
        String keyWordVar_ = keyWords_.getKeyWordVar();
        return StringList.quickEq(classNameFirst.trim(), keyWordVar_) || classNameFirst.trim().isEmpty();
    }

    private boolean toInferSecond(AnalyzedPageEl _page) {
        KeyWords keyWords_ = _page.getKeyWords();
        String keyWordVar_ = keyWords_.getKeyWordVar();
        return StringList.quickEq(classNameSecond.trim(), keyWordVar_) || classNameSecond.trim().isEmpty();
    }
    public String getLabel() {
        return label;
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
        Struct iterStr_;
        long length_ = CustList.INDEX_NOT_FOUND_ELT;
        Argument arg_ = iteratorMultTable(its_,_cont);
        if (_cont.getContext().hasException()) {
            return;
        }
        iterStr_ = arg_.getStruct();
        RendLoopBlockStack l_ = new RendLoopBlockStack();
        l_.setIndex(-1);
        l_.setFinished(false);
        l_.setBlock(this);
        l_.setCurrentVisitedBlock(this);
        l_.setStructIterator(iterStr_);
        l_.setMaxIteration(length_);
        l_.setContainer(its_);
        ip_.addBlock(l_);
        StringMap<LoopVariable> varsLoop_ = ip_.getVars();
        LoopVariable lv_ = new LoopVariable();
        lv_.setIndex(-1);
        Struct defFirst_ = ExecClassArgumentMatching.defaultValue(importedClassNameFirst, _cont.getContext());
        lv_.setIndexClassName(importedClassIndexName);
        varsLoop_.put(variableNameFirst, lv_);
        ip_.putValueVar(variableNameFirst, LocalVariable.newLocalVariable(defFirst_,importedClassNameFirst));
        lv_ = new LoopVariable();
        lv_.setIndex(-1);
        Struct defSecond_ = ExecClassArgumentMatching.defaultValue(importedClassNameSecond, _cont.getContext());
        lv_.setIndexClassName(importedClassIndexName);
        varsLoop_.put(variableNameSecond, lv_);
        ip_.putValueVar(variableNameSecond, LocalVariable.newLocalVariable(defSecond_,importedClassNameSecond));
        processLastElementLoop(_cont);
    }

    Struct processLoop(Configuration _conf) {
        ImportingPage ip_ = _conf.getLastPage();
        ip_.setOffset(expressionOffset);
        ip_.setProcessingAttribute(_conf.getRendKeyWords().getAttrMap());
        Argument arg_ = RenderExpUtil.calculateReuse(opList,_conf);
        ContextEl context_ = _conf.getContext();
        if (context_.hasException()) {
            return NullStruct.NULL_VALUE;
        }
        Struct ito_ = arg_.getStruct();
        if (ito_ == NullStruct.NULL_VALUE) {
            String npe_ = context_.getStandards().getAliasNullPe();
            _conf.setException(new ErrorStruct(context_, npe_));
        }
        return ito_;

    }

    @Override
    public void exitStack(Configuration _conf) {
        processLastElementLoop(_conf);
    }

    @Override
    public void removeAllVars(ImportingPage _ip) {
        super.removeAllVars(_ip);
        StringMap<LoopVariable> v_ = _ip.getVars();
        v_.removeKey(variableNameFirst);
        v_.removeKey(variableNameSecond);
        StringMap<LocalVariable> vInfo_ = _ip.getValueVars();
        vInfo_.removeKey(variableNameFirst);
        vInfo_.removeKey(variableNameSecond);
    }

    @Override
    public void removeAllVars(AnalyzedPageEl _ip) {
        super.removeAllVars(_ip);
        _ip.getInfosVars().removeKey(variableNameFirst);
        _ip.getLoopsVars().removeKey(variableNameFirst);
        _ip.getInfosVars().removeKey(variableNameSecond);
        _ip.getLoopsVars().removeKey(variableNameSecond);
    }
    @Override
    public void processLastElementLoop(Configuration _conf) {
        ImportingPage ip_ = _conf.getLastPage();
        StringMap<LoopVariable> vars_ = ip_.getVars();
        StringMap<LocalVariable> varsInfos_ = ip_.getValueVars();
        RendLoopBlockStack l_ = (RendLoopBlockStack) ip_.getRendLastStack();
        ConditionReturn has_ = iteratorHasNext(_conf);
        if (has_ == ConditionReturn.CALL_EX) {
            return;
        }
        boolean hasNext_ = has_ == ConditionReturn.YES;
        if (hasNext_) {
            incrementLoop(_conf, l_, vars_,varsInfos_);
        } else {
            l_.setFinished(true);
        }
    }
    public void incrementLoop(Configuration _conf, RendLoopBlockStack _l,
                              StringMap<LoopVariable> _vars, StringMap<LocalVariable> _varsInfos) {
        _l.setIndex(_l.getIndex() + 1);
        Struct iterator_ = _l.getStructIterator();
        ImportingPage call_ = _conf.getLastPage();
        Argument nextPair_ = nextPair(iterator_,_conf);
        if (_conf.getContext().hasException()) {
            return;
        }
        Struct value_ = nextPair_.getStruct();
        Argument arg_ = first(value_,_conf);
        if (_conf.getContext().hasException()) {
            return;
        }
        if (!ExecTemplates.checkQuick(importedClassNameFirst, arg_, _conf.getContext())) {
            return;
        }
        LoopVariable lv_ = _vars.getVal(variableNameFirst);
        LocalVariable lInfo_ = _varsInfos.getVal(variableNameFirst);
        lInfo_.setStruct(arg_.getStruct());
        lv_.setIndex(lv_.getIndex() + 1);
        arg_ = second(value_,_conf);
        if (_conf.getContext().hasException()) {
            return;
        }
        if (!ExecTemplates.checkQuick(importedClassNameSecond, arg_, _conf.getContext())) {
            return;
        }
        lv_ = _vars.getVal(variableNameSecond);
        lInfo_ = _varsInfos.getVal(variableNameSecond);
        lInfo_.setStruct(arg_.getStruct());
        lv_.setIndex(lv_.getIndex() + 1);
        call_.getRendReadWrite().setRead(getFirstChild());
    }
    private ConditionReturn iteratorHasNext(Configuration _conf) {
        ImportingPage ip_ = _conf.getLastPage();
        RendLoopBlockStack l_ = (RendLoopBlockStack) ip_.getRendLastStack();
        Struct strIter_ = l_.getStructIterator();
        Argument arg_ = hasNextPair(strIter_,_conf);
        if (_conf.getContext().hasException()) {
            return ConditionReturn.CALL_EX;
        }
        if (BooleanStruct.isTrue(arg_.getStruct())) {
            return ConditionReturn.YES;
        }
        return ConditionReturn.NO;
    }
}
