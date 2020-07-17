package code.formathtml;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.Argument;
import code.expressionlanguage.analyze.ManageTokens;
import code.expressionlanguage.analyze.TokenErrorMessage;
import code.expressionlanguage.analyze.inherits.AnaTemplates;
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
import code.expressionlanguage.analyze.blocks.ImportForEachTable;
import code.expressionlanguage.inherits.ClassArgumentMatching;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.ErrorStruct;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.analyze.types.ResolvingImportTypes;
import code.expressionlanguage.exec.variables.LoopVariable;
import code.formathtml.exec.RendDynOperationNode;
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

    RendForEachTable(Configuration _importingPage,
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
        return getLabel();
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
        } else {
            StringList names_ = el_.getResultClass().getNames();
            StringList out_ = getCustomType(names_, _cont);
            checkIterableCandidates(out_, _cont);
        }
        putVariable(_cont);
    }

    @Override
    public void reduce(Configuration _context) {
        RendDynOperationNode r_ = opList.last();
        opList = RenderExpUtil.getReducedNodes(r_);
    }

    private StringList getCustomType(StringList _names, Configuration _context) {
        BeanLgNames stds_ = _context.getAdvStandards();
        return stds_.getCustomTableType(_names,_context.getContext(),importedClassNameFirst,importedClassNameSecond).getClassName();
    }

    public void buildEl(Configuration _cont,RendDocumentBlock _doc) {
        importedClassIndexName = ResolvingImportTypes.resolveCorrectType(_cont.getContext(),classIndexName);
        if (!PrimitiveTypeUtil.isIntOrderClass(new ClassArgumentMatching(importedClassIndexName), _cont.getContext())) {
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
        TokenErrorMessage resOne_ = ManageTokens.partVar(_cont.getContext()).checkTokenVar(_cont.getContext(),variableNameFirst,false);
        if (resOne_.isError()) {
            FoundErrorInterpret b_ = new FoundErrorInterpret();
            b_.setFileName(_cont.getCurrentFileName());
            b_.setIndexFile(variableNameOffsetFirst);
            b_.setBuiltError(resOne_.getMessage());
            _cont.addError(b_);
            okVarFirst = false;
        }
        TokenErrorMessage resTwo_ = ManageTokens.partVar(_cont.getContext()).checkTokenVar(_cont.getContext(),variableNameSecond,false);
        if (resTwo_.isError()) {
            FoundErrorInterpret b_ = new FoundErrorInterpret();
            b_.setFileName(_cont.getCurrentFileName());
            b_.setIndexFile(variableNameOffsetSecond);
            b_.setBuiltError(resTwo_.getMessage());
            _cont.addError(b_);
            okVarSecond = false;
        }
        AnalyzedPageEl page_ = _cont.getAnalyzing();
        page_.setGlobalOffset(classNameOffsetFirst);
        page_.setOffset(0);
        if (!toInferFirst(_cont)) {
            importedClassNameFirst = ResolvingImportTypes.resolveCorrectType(_cont.getContext(),classNameFirst);
        } else {
            importedClassNameFirst = EMPTY_STRING;
        }
        page_.setGlobalOffset(classNameOffsetSecond);
        page_.setOffset(0);
        if (!toInferSecond(_cont)) {
            importedClassNameSecond = ResolvingImportTypes.resolveCorrectType(_cont.getContext(),classNameSecond);
        } else {
            importedClassNameSecond = EMPTY_STRING;
        }
        page_.setGlobalOffset(expressionOffset);
        page_.setOffset(0);
        _cont.getAnalyzingDoc().setAttribute(_cont.getRendKeyWords().getAttrMap());
        opList = RenderExpUtil.getAnalyzedOperations(expression,expressionOffset, 0, _cont);
    }

    public void checkIterableCandidates(StringList _types, Configuration _cont) {
        if (_types.onlyOneElt()) {
            String type_ = _types.first();
            Mapping mapping_ = new Mapping();
            String paramArg_ = StringExpUtil.getAllTypes(type_).get(1);
            if (StringList.quickEq(paramArg_, Templates.SUB_TYPE)) {
                paramArg_ = _cont.getStandards().getAliasObject();
            } else if (paramArg_.startsWith(Templates.SUB_TYPE)) {
                paramArg_ = paramArg_.substring(Templates.SUB_TYPE.length());
            } else if (paramArg_.startsWith(Templates.SUP_TYPE)) {
                paramArg_ = _cont.getStandards().getAliasObject();
            }
            if (toInferFirst(_cont)) {
                importedClassNameFirst = paramArg_;
            } else {
                mapping_.setArg(paramArg_);
                mapping_.setParam(importedClassNameFirst);
                StringMap<StringList> vars_ = _cont.getAnalyzing().getCurrentConstraints().getCurrentConstraints();
                mapping_.setMapping(vars_);
                if (!AnaTemplates.isCorrectOrNumbers(mapping_, _cont.getContext())) {
                    FoundErrorInterpret cast_ = new FoundErrorInterpret();
                    cast_.setFileName(_cont.getCurrentFileName());
                    cast_.setIndexFile(expressionOffset);
                    cast_.buildError(_cont.getContext().getAnalysisMessages().getBadImplicitCast(),
                            paramArg_,
                            importedClassNameFirst);
                    _cont.addError(cast_);
                }
            }
            mapping_ = new Mapping();
            paramArg_ = StringExpUtil.getAllTypes(type_).last();
            if (StringList.quickEq(paramArg_, Templates.SUB_TYPE)) {
                paramArg_ = _cont.getStandards().getAliasObject();
            } else if (paramArg_.startsWith(Templates.SUB_TYPE)) {
                paramArg_ = paramArg_.substring(Templates.SUB_TYPE.length());
            } else if (paramArg_.startsWith(Templates.SUP_TYPE)) {
                paramArg_ = _cont.getStandards().getAliasObject();
            }
            if (toInferSecond(_cont)) {
                importedClassNameSecond = paramArg_;
            } else {
                mapping_.setArg(paramArg_);
                mapping_.setParam(importedClassNameSecond);
                StringMap<StringList> vars_ = _cont.getAnalyzing().getCurrentConstraints().getCurrentConstraints();
                mapping_.setMapping(vars_);
                if (!AnaTemplates.isCorrectOrNumbers(mapping_, _cont.getContext())) {
                    FoundErrorInterpret cast_ = new FoundErrorInterpret();
                    cast_.setFileName(_cont.getCurrentFileName());
                    cast_.setIndexFile(expressionOffset);
                    cast_.buildError(_cont.getContext().getAnalysisMessages().getBadImplicitCast(),
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
            cast_.setFileName(_cont.getCurrentFileName());
            cast_.setIndexFile(expressionOffset);
            cast_.buildError(_cont.getContext().getAnalysisMessages().getBadImplicitCast(),
                    _cont.getStandards().getAliasObject(),
                    _cont.getStandards().getAliasIterableTable());
            _cont.addError(cast_);
        }
    }

    public void putVariable(Configuration _cont) {
        if (okVarFirst && okVarSecond) {
            if (StringList.quickEq(variableNameFirst, variableNameSecond)) {
                FoundErrorInterpret d_ = new FoundErrorInterpret();
                d_.setFileName(_cont.getCurrentFileName());
                d_.setIndexFile(variableNameOffsetSecond);
                d_.buildError(_cont.getContext().getAnalysisMessages().getBadVariableName(),
                        variableNameFirst);
                _cont.addError(d_);
                return;
            }
        }
        if (okVarFirst) {
            AnaLoopVariable lv_ = new AnaLoopVariable();
            lv_.setIndexClassName(importedClassIndexName);
            _cont.getAnalyzing().getLoopsVars().put(variableNameFirst, lv_);
            AnaLocalVariable lInfo_ = new AnaLocalVariable();
            if (!importedClassNameFirst.isEmpty()) {
                lInfo_.setClassName(importedClassNameFirst);
            } else {
                lInfo_.setClassName(_cont.getStandards().getAliasObject());
            }
            lInfo_.setConstType(ConstType.FIX_VAR);
            _cont.getAnalyzing().getInfosVars().put(variableNameFirst, lInfo_);
        }
        if (okVarSecond) {
            AnaLoopVariable lv_ = new AnaLoopVariable();
            lv_.setIndexClassName(importedClassIndexName);
            AnaLocalVariable lInfo_ = new AnaLocalVariable();
            if (!importedClassNameSecond.isEmpty()) {
                lInfo_.setClassName(importedClassNameSecond);
            } else {
                lInfo_.setClassName(_cont.getStandards().getAliasObject());
            }
            lInfo_.setConstType(ConstType.FIX_VAR);
            _cont.getAnalyzing().getInfosVars().put(variableNameSecond, lInfo_);
        }
    }

    private boolean toInferFirst(Configuration _cont) {
        KeyWords keyWords_ = _cont.getKeyWords();
        String keyWordVar_ = keyWords_.getKeyWordVar();
        return StringList.quickEq(classNameFirst.trim(), keyWordVar_) || classNameFirst.trim().isEmpty();
    }

    private boolean toInferSecond(Configuration _cont) {
        KeyWords keyWords_ = _cont.getKeyWords();
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
        Struct defFirst_ = PrimitiveTypeUtil.defaultValue(importedClassNameFirst, _cont.getContext());
        lv_.setIndexClassName(importedClassIndexName);
        varsLoop_.put(variableNameFirst, lv_);
        ip_.putValueVar(variableNameFirst, LocalVariable.newLocalVariable(defFirst_,importedClassNameFirst));
        lv_ = new LoopVariable();
        lv_.setIndex(-1);
        Struct defSecond_ = PrimitiveTypeUtil.defaultValue(importedClassNameSecond, _cont.getContext());
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
        if (_conf.getContext().hasException()) {
            return NullStruct.NULL_VALUE;
        }
        Struct ito_ = arg_.getStruct();
        if (ito_ == NullStruct.NULL_VALUE) {
            String npe_ = _conf.getStandards().getAliasNullPe();
            _conf.setException(new ErrorStruct(_conf.getContext(), npe_));
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
