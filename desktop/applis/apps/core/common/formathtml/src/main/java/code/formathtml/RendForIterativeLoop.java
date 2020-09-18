package code.formathtml;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.Argument;
import code.expressionlanguage.analyze.ManageTokens;
import code.expressionlanguage.analyze.TokenErrorMessage;
import code.expressionlanguage.analyze.inherits.AnaTemplates;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.analyze.types.AnaTypeUtil;
import code.expressionlanguage.analyze.variables.AnaLocalVariable;
import code.expressionlanguage.analyze.variables.AnaLoopVariable;
import code.expressionlanguage.common.ConstType;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.exec.variables.LocalVariable;
import code.expressionlanguage.files.OffsetBooleanInfo;
import code.expressionlanguage.files.OffsetStringInfo;
import code.expressionlanguage.files.OffsetsBlock;
import code.expressionlanguage.analyze.inherits.Mapping;
import code.expressionlanguage.inherits.ClassArgumentMatching;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.stds.PrimitiveTypes;
import code.expressionlanguage.structs.ErrorStruct;
import code.expressionlanguage.structs.LongStruct;
import code.expressionlanguage.analyze.types.ResolvingImportTypes;
import code.expressionlanguage.exec.variables.LoopVariable;
import code.expressionlanguage.structs.Struct;
import code.formathtml.exec.RendDynOperationNode;
import code.formathtml.stacks.RendLoopBlockStack;
import code.formathtml.stacks.RendReadWrite;
import code.formathtml.util.AnalyzingDoc;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;

public final class RendForIterativeLoop extends RendParentBlock implements RendLoop, RendReducableOperations {

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

    private final String init;
    private int initOffset;

    private final String expression;
    private int expressionOffset;

    private final String step;
    private int stepOffset;

    private final boolean eq;
    private int eqOffset;

    private CustList<RendDynOperationNode> opInit;

    private CustList<RendDynOperationNode> opExp;

    private CustList<RendDynOperationNode> opStep;
    RendForIterativeLoop(Configuration _importingPage,
                         OffsetStringInfo _className, OffsetStringInfo _variable,
                         OffsetStringInfo _from,
                         OffsetStringInfo _to, OffsetBooleanInfo _eq, OffsetStringInfo _step, OffsetStringInfo _classIndex, OffsetStringInfo _label, OffsetsBlock _offset) {
        super(_offset);
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
            classIndex_ = _importingPage.getStandards().getAliasPrimInteger();
        }
        classIndexName = classIndex_;
        classIndexNameOffset = _classIndex.getOffset();
        label = _label.getInfo();
        labelOffset = _label.getOffset();
    }

    public String getLabel() {
        return label;
    }

    @Override
    public String getRealLabel() {
        return getLabel();
    }

    public String getVariableName() {
        return variableName;
    }

    public boolean isEq() {
        return eq;
    }

    @Override
    public void buildExpressionLanguage(Configuration _cont, RendDocumentBlock _doc, AnalyzingDoc _anaDoc) {
        AnalyzedPageEl page_ = _cont.getContext().getAnalyzing();
        page_.setGlobalOffset(classIndexNameOffset);
        page_.setOffset(0);
        importedClassIndexName = ResolvingImportTypes.resolveCorrectType(_cont.getContext(),classIndexName);
        if (!AnaTypeUtil.isIntOrderClass(new AnaClassArgumentMatching(importedClassIndexName), _cont.getContext())) {
            Mapping mapping_ = new Mapping();
            mapping_.setArg(importedClassIndexName);
            mapping_.setParam(_cont.getStandards().getAliasLong());
            FoundErrorInterpret cast_ = new FoundErrorInterpret();
            cast_.setFileName(_anaDoc.getFileName());
            cast_.setIndexFile(classIndexNameOffset);
            cast_.buildError(_cont.getContext().getAnalyzing().getAnalysisMessages().getNotPrimitiveWrapper(),
                    importedClassIndexName);
            Configuration.addError(cast_, _anaDoc, _cont.getContext().getAnalyzing());
        }
        page_.setGlobalOffset(classNameOffset);
        page_.setOffset(0);
        importedClassName = ResolvingImportTypes.resolveCorrectType(_cont.getContext(),className);
        String cl_ = importedClassName;
        AnaClassArgumentMatching elementClass_ = new AnaClassArgumentMatching(cl_);
        if (!AnaTypeUtil.isIntOrderClass(elementClass_, _cont.getContext())) {
            FoundErrorInterpret cast_ = new FoundErrorInterpret();
            cast_.setFileName(_anaDoc.getFileName());
            cast_.setIndexFile(classNameOffset);
            cast_.buildError(_cont.getContext().getAnalyzing().getAnalysisMessages().getNotPrimitiveWrapper(),
                    importedClassName);
            Configuration.addError(cast_, _anaDoc, _cont.getContext().getAnalyzing());
        }
        page_.setGlobalOffset(variableNameOffset);
        page_.setOffset(0);
        TokenErrorMessage res_ = ManageTokens.partVar(page_).checkTokenVar(variableName, page_);
        if (res_.isError()) {
            FoundErrorInterpret b_ = new FoundErrorInterpret();
            b_.setFileName(_anaDoc.getFileName());
            b_.setIndexFile(variableNameOffset);
            b_.setBuiltError(res_.getMessage());
            Configuration.addError(b_, _anaDoc, _cont.getContext().getAnalyzing());
        }
        page_.setGlobalOffset(initOffset);
        page_.setOffset(0);
        _anaDoc.setAttribute(_cont.getRendKeyWords().getAttrFrom());
        opInit = RenderExpUtil.getAnalyzedOperations(init,initOffset,0, _cont, _anaDoc, _cont.getContext().getAnalyzing());
        RendDynOperationNode initEl_ = opInit.last();
        Mapping m_ = new Mapping();
        m_.setArg(page_.getCurrentRoot().getResultClass());
        m_.setParam(elementClass_);
        if (!AnaTemplates.isCorrectOrNumbers(m_,_cont.getContext())) {
            Mapping mapping_ = new Mapping();
            mapping_.setArg(page_.getCurrentRoot().getResultClass());
            mapping_.setParam(elementClass_);
            FoundErrorInterpret cast_ = new FoundErrorInterpret();
            cast_.setFileName(_anaDoc.getFileName());
            cast_.setIndexFile(initOffset);
            cast_.buildError(_cont.getContext().getAnalyzing().getAnalysisMessages().getBadImplicitCast(),
                    StringList.join(elementClass_.getNames(),AND_ERR),
                    StringList.join(initEl_.getResultClass().getNames(),AND_ERR));
            Configuration.addError(cast_, _anaDoc, _cont.getContext().getAnalyzing());
        }
        page_.setGlobalOffset(expressionOffset);
        page_.setOffset(0);
        _anaDoc.setAttribute(_cont.getRendKeyWords().getAttrTo());
        opExp = RenderExpUtil.getAnalyzedOperations(expression,expressionOffset,0, _cont, _anaDoc, _cont.getContext().getAnalyzing());
        RendDynOperationNode expressionEl_ = opExp.last();
        m_.setArg(page_.getCurrentRoot().getResultClass());
        m_.setParam(elementClass_);
        if (!AnaTemplates.isCorrectOrNumbers(m_,_cont.getContext())) {
            Mapping mapping_ = new Mapping();
            mapping_.setArg(page_.getCurrentRoot().getResultClass());
            mapping_.setParam(elementClass_);
            FoundErrorInterpret cast_ = new FoundErrorInterpret();
            cast_.setFileName(_anaDoc.getFileName());
            cast_.setIndexFile(expressionOffset);
            cast_.buildError(_cont.getContext().getAnalyzing().getAnalysisMessages().getBadImplicitCast(),
                    StringList.join(elementClass_.getNames(),AND_ERR),
                    StringList.join(expressionEl_.getResultClass().getNames(),AND_ERR));
            Configuration.addError(cast_, _anaDoc, _cont.getContext().getAnalyzing());
        }
        page_.setGlobalOffset(stepOffset);
        page_.setOffset(0);
        _anaDoc.setAttribute(_cont.getRendKeyWords().getAttrStep());
        opStep = RenderExpUtil.getAnalyzedOperations(step,stepOffset, 0,_cont, _anaDoc, _cont.getContext().getAnalyzing());
        RendDynOperationNode stepEl_ = opStep.last();
        m_.setArg(page_.getCurrentRoot().getResultClass());
        m_.setParam(elementClass_);
        if (!AnaTemplates.isCorrectOrNumbers(m_,_cont.getContext())) {
            Mapping mapping_ = new Mapping();
            mapping_.setArg(page_.getCurrentRoot().getResultClass());
            mapping_.setParam(elementClass_);
            FoundErrorInterpret cast_ = new FoundErrorInterpret();
            cast_.setFileName(_anaDoc.getFileName());
            cast_.setIndexFile(stepOffset);
            cast_.buildError(_cont.getContext().getAnalyzing().getAnalysisMessages().getBadImplicitCast(),
                    StringList.join(elementClass_.getNames(),AND_ERR),
                    StringList.join(stepEl_.getResultClass().getNames(),AND_ERR));
            Configuration.addError(cast_, _anaDoc, _cont.getContext().getAnalyzing());
        }
        if (!res_.isError()) {
            AnaLoopVariable lv_ = new AnaLoopVariable();
            lv_.setIndexClassName(importedClassIndexName);
            _cont.getContext().getAnalyzing().getLoopsVars().put(variableName, lv_);
            AnaLocalVariable lInfo_ = new AnaLocalVariable();
            lInfo_.setClassName(cl_);
            lInfo_.setConstType(ConstType.FIX_VAR);
            _cont.getContext().getAnalyzing().getInfosVars().put(variableName, lInfo_);
        }
    }

    @Override
    public void reduce(Configuration _context) {
        RendDynOperationNode i_ = opInit.last();
        opInit = RenderExpUtil.getReducedNodes(i_);
        RendDynOperationNode e_ = opExp.last();
        opExp = RenderExpUtil.getReducedNodes(e_);
        RendDynOperationNode s_ = opStep.last();
        opStep = RenderExpUtil.getReducedNodes(s_);
    }

    @Override
    public void processEl(Configuration _cont) {
        ImportingPage ip_ = _cont.getLastPage();
        RendLoopBlockStack c_ = ip_.getLastLoopIfPossible(this);
        if (c_ != null) {
            processBlockAndRemove(_cont);
            return;
        }
        processLoop(_cont);
        if (_cont.getContext().hasException()) {
            return;
        }
        c_ = (RendLoopBlockStack) ip_.getRendLastStack();
        if (c_.isFinished()) {
            processBlockAndRemove(_cont);
            return;
        }
        ip_.getRendReadWrite().setRead(getFirstChild());
    }

    void processLoop(Configuration _conf) {
        LgNames stds_ = _conf.getStandards();
        String null_ = stds_.getAliasNullPe();
        ImportingPage ip_ = _conf.getLastPage();
        StringMap<LoopVariable> varsLoop_ = ip_.getVars();
        String var_ = getVariableName();
        long nbMaxIterations_ = 0;
        long stepValue_ = 0;
        long fromValue_ = 0;

        boolean eq_ = isEq();
        ip_.setOffset(initOffset);
        ip_.setProcessingAttribute(_conf.getRendKeyWords().getAttrFrom());
        Argument argFrom_ = RenderExpUtil.calculateReuse(opInit,_conf);
        if (_conf.getContext().hasException()) {
            return;
        }
        if (argFrom_.isNull()) {
            _conf.setException(new ErrorStruct(_conf.getContext(),null_));
            return;
        }
        ip_.setOffset(expressionOffset);
        ip_.setProcessingAttribute(_conf.getRendKeyWords().getAttrTo());
        Argument argTo_ = RenderExpUtil.calculateReuse(opExp,_conf);
        if (_conf.getContext().hasException()) {
            return;
        }
        if (argTo_.isNull()) {
            _conf.setException(new ErrorStruct(_conf.getContext(),null_));
            return;
        }
        ip_.setOffset(stepOffset);
        ip_.setProcessingAttribute(_conf.getRendKeyWords().getAttrStep());
        Argument argStep_ = RenderExpUtil.calculateReuse(opStep,_conf);
        if (_conf.getContext().hasException()) {
            return;
        }
        if (argStep_.isNull()) {
            _conf.setException(new ErrorStruct(_conf.getContext(),null_));
            return;
        }
        fromValue_ = NumParsers.convertToInt(PrimitiveTypes.LONG_WRAP, NumParsers.convertToNumber(argFrom_.getStruct())).longStruct();
        long toValue_ = NumParsers.convertToInt(PrimitiveTypes.LONG_WRAP, NumParsers.convertToNumber(argTo_.getStruct())).longStruct();
        stepValue_ = NumParsers.convertToInt(PrimitiveTypes.LONG_WRAP, NumParsers.convertToNumber(argStep_.getStruct())).longStruct();
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
        long length_;
        boolean finished_ = false;
        length_ = nbMaxIterations_;
        if (length_ == CustList.SIZE_EMPTY) {
            finished_ = true;
        }
        RendLoopBlockStack l_ = new RendLoopBlockStack();
        l_.setFinished(finished_);
        l_.setBlock(this);
        l_.setCurrentVisitedBlock(this);
        l_.setMaxIteration(length_);
        l_.setStep(stepValue_);
        ip_.addBlock(l_);
        if (finished_) {
            return;
        }
        LoopVariable lv_ = new LoopVariable();
        lv_.setIndexClassName(importedClassIndexName);
        Struct struct_ = NumParsers.convertToInt(ClassArgumentMatching.getPrimitiveCast(importedClassName,_conf.getStandards()), new LongStruct(fromValue_));
        varsLoop_.put(var_, lv_);
        ip_.putValueVar(var_, LocalVariable.newLocalVariable(struct_,importedClassName));
    }

    @Override
    public void exitStack(Configuration _context) {
        processLastElementLoop(_context);
    }

    @Override
    public void removeAllVars(ImportingPage _ip) {
        super.removeAllVars(_ip);
        StringMap<LoopVariable> v_ = _ip.getVars();
        String var_ = getVariableName();
        v_.removeKey(var_);
        StringMap<LocalVariable> vInfo_ = _ip.getValueVars();
        vInfo_.removeKey(var_);
    }

    @Override
    public void removeAllVars(AnalyzedPageEl _ip) {
        super.removeAllVars(_ip);
        String var_ = getVariableName();
        _ip.getLoopsVars().removeKey(var_);
        _ip.getInfosVars().removeKey(var_);
    }
    @Override
    public void processLastElementLoop(Configuration _conf) {
        ImportingPage ip_ = _conf.getLastPage();
        RendReadWrite rw_ = ip_.getRendReadWrite();
        StringMap<LoopVariable> vars_ = ip_.getVars();
        StringMap<LocalVariable> varsInfos_ = ip_.getValueVars();
        RendLoopBlockStack l_ = (RendLoopBlockStack) ip_.getRendLastStack();
        RendBlock forLoopLoc_ = l_.getBlock();
        if (l_.hasNext()) {
            incrementLoop(_conf, l_, vars_,varsInfos_);
            rw_.setRead(forLoopLoc_.getFirstChild());
            return;
        }
        l_.setFinished(true);
    }

    public void incrementLoop(Configuration _conf, RendLoopBlockStack _l,
                              StringMap<LoopVariable> _vars, StringMap<LocalVariable> _varsInfos) {
        _l.setIndex(_l.getIndex() + 1);
        String var_ = getVariableName();
        LoopVariable lv_ = _vars.getVal(var_);
        LocalVariable lInfo_ = _varsInfos.getVal(var_);
        long o_ = NumParsers.convertToNumber(lInfo_.getStruct()).longStruct()+_l.getStep();
        lInfo_.setStruct(NumParsers.convertToInt(ClassArgumentMatching.getPrimitiveCast(importedClassName,_conf.getStandards()), new LongStruct(o_)));
        lv_.setIndex(lv_.getIndex() + 1);
    }
}
