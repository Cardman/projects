package code.formathtml;

import code.expressionlanguage.AnalyzedPageEl;
import code.expressionlanguage.Argument;
import code.expressionlanguage.errors.custom.BadImplicitCast;
import code.expressionlanguage.errors.custom.UnexpectedTypeError;
import code.expressionlanguage.files.OffsetStringInfo;
import code.expressionlanguage.files.OffsetsBlock;
import code.expressionlanguage.inherits.Mapping;
import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.expressionlanguage.methods.ForLoopPart;
import code.expressionlanguage.opers.AffectationOperation;
import code.expressionlanguage.opers.Calculation;
import code.expressionlanguage.opers.util.MethodAccessKind;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.variables.LoopVariable;
import code.formathtml.exec.RendDynOperationNode;
import code.formathtml.stacks.RendLoopBlockStack;
import code.formathtml.stacks.RendReadWrite;
import code.util.CustList;
import code.util.StringList;

public final class RendForMutableIterativeLoop extends RendParentBlock implements RendLoop,RendWithEl,RendReducableOperations {


    private String label;
    private int labelOffset;

    private final String className;
    private int classNameOffset;

    private String importedClassName = EMPTY_STRING;

    private final String classIndexName;
    private String importedClassIndexName;
    private int classIndexNameOffset;

    private final StringList variableNames = new StringList();

    private final String init;
    private int initOffset;

    private final String expression;
    private int expressionOffset;

    private final String step;
    private int stepOffset;

    private CustList<RendDynOperationNode> opInit;

    private CustList<RendDynOperationNode> opExp;

    private CustList<RendDynOperationNode> opStep;

    RendForMutableIterativeLoop(Configuration _importingPage,
                                OffsetStringInfo _className,
                                OffsetStringInfo _from,
                                OffsetStringInfo _to, OffsetStringInfo _step, OffsetStringInfo _classIndex, OffsetStringInfo _label, OffsetsBlock _offset) {
        super(_offset);
        className = _className.getInfo();
        classNameOffset = _className.getOffset();
        init = _from.getInfo();
        initOffset = _from.getOffset();
        expression = _to.getInfo();
        expressionOffset = _to.getOffset();
        step = _step.getInfo();
        stepOffset = _step.getOffset();
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

    public void setImportedClassName(String _importedClassName) {
        importedClassName = _importedClassName;
    }

    public StringList getVariableNames() {
        return variableNames;
    }

    @Override
    public void buildExpressionLanguage(Configuration _cont,RendDocumentBlock _doc) {
        AnalyzedPageEl page_ = _cont.getAnalyzing();
        page_.setGlobalOffset(classIndexNameOffset);
        page_.setOffset(0);
        importedClassIndexName = _cont.resolveCorrectType(classIndexName);
        if (!PrimitiveTypeUtil.isPrimitiveOrWrapper(importedClassIndexName, _cont)) {
            Mapping mapping_ = new Mapping();
            mapping_.setArg(importedClassIndexName);
            mapping_.setParam(_cont.getStandards().getAliasLong());
            BadImplicitCast cast_ = new BadImplicitCast();
            cast_.setMapping(mapping_);
            cast_.setFileName(_cont.getCurrentFileName());
            cast_.setIndexFile(classIndexNameOffset);
            _cont.getClasses().addError(cast_);
        }
        page_.setGlobalOffset(classNameOffset);
        page_.setOffset(0);
        if (!className.isEmpty()) {
            KeyWords keyWords_ = _cont.getKeyWords();
            String keyWordVar_ = keyWords_.getKeyWordVar();
            if (StringList.quickEq(className.trim(), keyWordVar_)) {
                importedClassName = keyWordVar_;
            } else {
                importedClassName = _cont.resolveCorrectType(className);
            }
            _cont.setMerged(true);
            _cont.setFinalVariable(false);
            _cont.setCurrentVarSetting(importedClassName);
        } else {
            _cont.setMerged(false);
        }
        MethodAccessKind static_ = _doc.getStaticContext();
        _cont.getVariablesNames().clear();
        _cont.getVariablesNamesLoopToInfer().clear();
        page_.setGlobalOffset(initOffset);
        page_.setOffset(0);
        _cont.getAnalyzingDoc().setAttribute(ATTRIBUTE_INIT);
        _cont.setForLoopPartState(ForLoopPart.INIT);
        _cont.setAcceptCommaInstr(true);
        if (init.trim().isEmpty()) {
            opInit = new CustList<RendDynOperationNode>();
        } else {
            opInit = RenderExpUtil.getAnalyzedOperations(init,0, _cont, Calculation.staticCalculation(static_));
        }
        if (_cont.isMerged()) {
            StringList vars_ = _cont.getVariablesNames();
            String t_ = inferOrObject(_cont,importedClassName);
            AffectationOperation.processInferLoop(_cont, t_);
            getVariableNames().addAllElts(vars_);
        }
        _cont.setMerged(false);
        _cont.setAcceptCommaInstr(false);
        page_.setGlobalOffset(expressionOffset);
        page_.setOffset(0);
        _cont.getAnalyzingDoc().setAttribute(ATTRIBUTE_CONDITION);
        _cont.setForLoopPartState(ForLoopPart.CONDITION);
        if (expression.trim().isEmpty()) {
            opExp = new CustList<RendDynOperationNode>();
        } else {
            opExp = RenderExpUtil.getAnalyzedOperations(expression, 0,_cont, Calculation.staticCalculation(static_));
        }
        if (!opExp.isEmpty()) {
            RendDynOperationNode elCondition_ = opExp.last();
            LgNames stds_ = _cont.getStandards();
            if (!elCondition_.getResultClass().isBoolType(_cont)) {
                UnexpectedTypeError un_ = new UnexpectedTypeError();
                un_.setFileName(_cont.getCurrentFileName());
                un_.setIndexFile(expressionOffset);
                un_.setType(opExp.last().getResultClass());
                _cont.getClasses().addError(un_);
            }
            elCondition_.getResultClass().setUnwrapObject(stds_.getAliasPrimBoolean());
        }
    }

    public void buildIncrementPart(Configuration _an,RendDocumentBlock _doc) {
        _an.setMerged(false);
        AnalyzedPageEl page_ = _an.getAnalyzing();
        page_.setGlobalOffset(stepOffset);
        page_.setOffset(0);
        _an.setForLoopPartState(ForLoopPart.STEP);
        _an.setMerged(true);
        _an.setAcceptCommaInstr(true);
        _an.getLocalVariables().last().clear();
        _an.getAnalyzingDoc().setAttribute(ATTRIBUTE_STEP);
        MethodAccessKind static_ = _doc.getStaticContext();
        if (step.trim().isEmpty()) {
            opStep = new CustList<RendDynOperationNode>();
        } else {
            opStep = RenderExpUtil.getAnalyzedOperations(step, 0, _an, Calculation.staticCalculation(static_));
        }
        _an.setMerged(false);
        _an.setAcceptCommaInstr(false);
    }
    @Override
    public void reduce(Configuration _context) {
        if (!opInit.isEmpty()) {
            RendDynOperationNode i_ = opInit.last();
            opInit = RenderExpUtil.getReducedNodes(i_);
        }
        if (!opExp.isEmpty()) {
            RendDynOperationNode e_ = opExp.last();
            opExp = RenderExpUtil.getReducedNodes(e_);
        }
        if (!opStep.isEmpty()) {
            RendDynOperationNode s_ = opStep.last();
            opStep = RenderExpUtil.getReducedNodes(s_);
        }
    }

    @Override
    public void processEl(Configuration _cont) {
        ImportingPage ip_ = _cont.getLastPage();
        RendReadWrite rw_ = ip_.getRendReadWrite();
        RendLoopBlockStack c_ = ip_.getLastLoopIfPossible(this);
        if (c_ != null) {
            removeAllVars(ip_);
            processBlockAndRemove(_cont);
            return;
        }
        ip_.setOffset(initOffset);
        ip_.setProcessingAttribute(ATTRIBUTE_INIT);
        Struct struct_ = PrimitiveTypeUtil.defaultValue(importedClassName, _cont);
        for (String v: variableNames) {
            LoopVariable lv_ = LoopVariable.newLoopVariable(struct_,importedClassName);
            ip_.getVars().put(v, lv_);
        }
        if (!opInit.isEmpty()) {
            RenderExpUtil.calculateReuse(opInit,_cont);
            if (_cont.getContext().hasExceptionOrFailInit()) {
                return;
            }
        }
        Boolean res_ = evaluateCondition(_cont);
        if (res_ == null) {
            return;
        }
        RendLoopBlockStack l_ = new RendLoopBlockStack();
        l_.setBlock(this);
        l_.setFinished(!res_);
        ip_.addBlock(l_);
        c_ = (RendLoopBlockStack) ip_.getRendLastStack();
        if (c_.isFinished()) {
            removeAllVars(ip_);
            processBlockAndRemove(_cont);
            return;
        }
        rw_.setRead(getFirstChild());
    }

    @Override
    public void removeAllVars(ImportingPage _ip) {
        super.removeAllVars(_ip);
        for (String v: variableNames) {
            _ip.getVars().removeKey(v);
        }
    }

    private Boolean evaluateCondition(Configuration _context) {
        ImportingPage last_ = _context.getLastPage();
        if (opExp.isEmpty()) {
            return true;
        }
        last_.setOffset(expressionOffset);
        last_.setProcessingAttribute(ATTRIBUTE_CONDITION);
        Argument arg_ = RenderExpUtil.calculateReuse(opExp,_context);
        if (_context.getContext().hasExceptionOrFailInit()) {
            return null;
        }
        return ((BooleanStruct)arg_.getStruct()).getInstance();
    }
    @Override
    public void exitStack(Configuration _conf) {
        processLastElementLoop(_conf);
    }

    @Override
    public void processLastElementLoop(Configuration _conf) {
        ImportingPage ip_ = _conf.getLastPage();
        RendReadWrite rw_ = ip_.getRendReadWrite();
        RendLoopBlockStack l_ = (RendLoopBlockStack) ip_.getRendLastStack();
        RendBlock forLoopLoc_ = l_.getBlock();
        ip_.setOffset(stepOffset);
        ip_.setProcessingAttribute(ATTRIBUTE_STEP);
        if (!opStep.isEmpty()) {
            RenderExpUtil.calculateReuse(opStep,_conf);
            if (_conf.getContext().hasExceptionOrFailInit()) {
                return;
            }
        }
        Boolean keep_ = evaluateCondition(_conf);
        if (keep_ == null) {
            return;
        }
        if (!keep_) {
            l_.setFinished(true);
        } else {
            rw_.setRead(forLoopLoc_.getFirstChild());
        }
    }
}
