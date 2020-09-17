package code.formathtml;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.Argument;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.analyze.types.AnaTypeUtil;
import code.expressionlanguage.exec.ConditionReturn;
import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.exec.variables.LocalVariable;
import code.expressionlanguage.files.OffsetStringInfo;
import code.expressionlanguage.files.OffsetsBlock;
import code.expressionlanguage.analyze.inherits.Mapping;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.analyze.util.ClassMethodIdReturn;
import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.expressionlanguage.analyze.blocks.ForLoopPart;
import code.expressionlanguage.analyze.opers.AffectationOperation;
import code.expressionlanguage.inherits.ClassArgumentMatching;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.analyze.types.ResolvingImportTypes;
import code.expressionlanguage.exec.variables.LoopVariable;
import code.formathtml.exec.RendDynOperationNode;
import code.formathtml.stacks.RendLoopBlockStack;
import code.formathtml.stacks.RendReadWrite;
import code.formathtml.util.AnalyzingDoc;
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
    public void buildExpressionLanguage(Configuration _cont, RendDocumentBlock _doc, AnalyzingDoc _anaDoc) {
        AnalyzedPageEl page_ = _cont.getContext().getAnalyzing();
        page_.setGlobalOffset(classIndexNameOffset);
        page_.setOffset(0);
        importedClassIndexName = ResolvingImportTypes.resolveCorrectType(_cont.getContext(),classIndexName);
        if (!AnaTypeUtil.isIntOrderClass(new ClassArgumentMatching(importedClassIndexName), _cont.getContext())) {
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
        if (!className.isEmpty()) {
            KeyWords keyWords_ = _cont.getContext().getAnalyzing().getKeyWords();
            String keyWordVar_ = keyWords_.getKeyWordVar();
            if (StringList.quickEq(className.trim(), keyWordVar_)) {
                importedClassName = keyWordVar_;
            } else {
                importedClassName = ResolvingImportTypes.resolveCorrectType(_cont.getContext(),className);
            }
            page_.setMerged(true);
            page_.setFinalVariable(false);
            page_.setCurrentVarSetting(importedClassName);
        } else {
            page_.setMerged(false);
        }
        page_.getVariablesNames().clear();
        page_.getVariablesNamesToInfer().clear();
        page_.setGlobalOffset(initOffset);
        page_.setOffset(0);
        _anaDoc.setAttribute(_cont.getRendKeyWords().getAttrInit());
        page_.setForLoopPartState(ForLoopPart.INIT);
        page_.setAcceptCommaInstr(true);
        if (init.trim().isEmpty()) {
            opInit = new CustList<RendDynOperationNode>();
        } else {
            opInit = RenderExpUtil.getAnalyzedOperations(init,initOffset,0, _cont, _anaDoc, _cont.getContext().getAnalyzing());
        }
        if (page_.isMerged()) {
            StringList vars_ = page_.getVariablesNames();
            String t_ = inferOrObject(_cont,importedClassName);
            AffectationOperation.processInferLoop(_cont.getContext(), t_);
            getVariableNames().addAllElts(vars_);
        }
        page_.setMerged(false);
        page_.setAcceptCommaInstr(false);
        page_.setGlobalOffset(expressionOffset);
        page_.setOffset(0);
        _anaDoc.setAttribute(_cont.getRendKeyWords().getAttrCondition());
        page_.setForLoopPartState(ForLoopPart.CONDITION);
        if (expression.trim().isEmpty()) {
            opExp = new CustList<RendDynOperationNode>();
        } else {
            opExp = RenderExpUtil.getAnalyzedOperations(expression,expressionOffset, 0,_cont, _anaDoc, _cont.getContext().getAnalyzing());
        }
        if (!opExp.isEmpty()) {
            RendDynOperationNode elCondition_ = opExp.last();
            LgNames stds_ = page_.getStandards();
            ClassArgumentMatching exp_ = elCondition_.getResultClass();
            if (!exp_.isBoolType(page_)) {
                ClassMethodIdReturn res_ = OperationNode.tryGetDeclaredImplicitCast(_cont.getContext(), _cont.getStandards().getAliasPrimBoolean(), exp_);
                if (res_.isFoundMethod()) {
                    ClassMethodId cl_ = new ClassMethodId(res_.getId().getClassName(),res_.getRealId());
                    exp_.getImplicits().add(cl_);
                    exp_.setRootNumber(res_.getRootNumber());
                    exp_.setMemberNumber(res_.getMemberNumber());
                } else {
                    ClassMethodIdReturn trueOp_ = OperationNode.fetchTrueOperator(_cont.getContext(), exp_);
                    if (trueOp_.isFoundMethod()) {
                        ClassMethodId cl_ = new ClassMethodId(trueOp_.getId().getClassName(),trueOp_.getRealId());
                        exp_.getImplicitsTest().add(cl_);
                        exp_.setRootNumberTest(trueOp_.getRootNumber());
                        exp_.setMemberNumberTest(trueOp_.getMemberNumber());
                    } else {
                        FoundErrorInterpret un_ = new FoundErrorInterpret();
                        un_.setFileName(_anaDoc.getFileName());
                        un_.setIndexFile(expressionOffset);
                        un_.buildError(_cont.getContext().getAnalyzing().getAnalysisMessages().getUnexpectedType(),
                                StringList.join(exp_.getNames(),AND_ERR));
                        Configuration.addError(un_, _anaDoc, _cont.getContext().getAnalyzing());
                    }
                }
            }
            exp_.setUnwrapObject(stds_.getAliasPrimBoolean());
            RenderExpUtil.setImplicits(elCondition_, _cont.getContext().getAnalyzing());
        }
        buildIncrementPart(_cont,_doc, _anaDoc);
    }

    public String getImportedClassIndexName() {
        return importedClassIndexName;
    }

    public void buildIncrementPart(Configuration _an, RendDocumentBlock _doc, AnalyzingDoc _anaDoc) {
        AnalyzedPageEl page_ = _an.getContext().getAnalyzing();
        page_.setMerged(false);
        page_.setGlobalOffset(stepOffset);
        page_.setOffset(0);
        page_.setForLoopPartState(ForLoopPart.STEP);
        page_.setMerged(true);
        page_.setAcceptCommaInstr(true);
        _anaDoc.setAttribute(_an.getRendKeyWords().getAttrStep());
        if (step.trim().isEmpty()) {
            opStep = new CustList<RendDynOperationNode>();
        } else {
            opStep = RenderExpUtil.getAnalyzedOperations(step,stepOffset, 0, _an, _anaDoc, _an.getContext().getAnalyzing());
        }
        page_.setMerged(false);
        page_.setAcceptCommaInstr(false);
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
            processBlockAndRemove(_cont);
            return;
        }
        ip_.setOffset(initOffset);
        ip_.setProcessingAttribute(_cont.getRendKeyWords().getAttrInit());
        Struct struct_ = PrimitiveTypeUtil.defaultValue(importedClassName, _cont.getContext());
        for (String v: variableNames) {
            LoopVariable lv_ = new LoopVariable();
            lv_.setIndexClassName(importedClassIndexName);
            ip_.getVars().put(v, lv_);
            ip_.putValueVar(v, LocalVariable.newLocalVariable(struct_,importedClassName));
        }
        if (!opInit.isEmpty()) {
            RenderExpUtil.calculateReuse(opInit,_cont);
            if (_cont.getContext().hasException()) {
                return;
            }
        }
        ConditionReturn res_ = evaluateCondition(_cont);
        if (res_ == ConditionReturn.CALL_EX) {
            return;
        }
        RendLoopBlockStack l_ = new RendLoopBlockStack();
        l_.setBlock(this);
        l_.setCurrentVisitedBlock(this);
        l_.setFinished(res_ == ConditionReturn.NO);
        ip_.addBlock(l_);
        c_ = (RendLoopBlockStack) ip_.getRendLastStack();
        if (c_.isFinished()) {
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
        for (String v: variableNames) {
            _ip.getValueVars().removeKey(v);
        }
    }

    @Override
    public void removeAllVars(AnalyzedPageEl _ip) {
        super.removeAllVars(_ip);
        for (String v: variableNames) {
            _ip.getLoopsVars().removeKey(v);
            _ip.getInfosVars().removeKey(v);
        }
    }

    private ConditionReturn evaluateCondition(Configuration _context) {
        ImportingPage last_ = _context.getLastPage();
        if (opExp.isEmpty()) {
            return ConditionReturn.YES;
        }
        last_.setOffset(expressionOffset);
        last_.setProcessingAttribute(_context.getRendKeyWords().getAttrCondition());
        Argument arg_ = RenderExpUtil.calculateReuse(opExp,_context);
        if (_context.getContext().hasException()) {
            return ConditionReturn.CALL_EX;
        }
        if (BooleanStruct.isTrue(arg_.getStruct())) {
            return ConditionReturn.YES;
        }
        return ConditionReturn.NO;
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
        ip_.setProcessingAttribute(_conf.getRendKeyWords().getAttrStep());
        if (!opStep.isEmpty()) {
            RenderExpUtil.calculateReuse(opStep,_conf);
            if (_conf.getContext().hasException()) {
                return;
            }
        }
        for (String v: variableNames) {
            LoopVariable lv_ = ip_.getVars().getVal(v);
            lv_.setIndex(lv_.getIndex()+1);
        }
        ConditionReturn keep_ = evaluateCondition(_conf);
        if (keep_ == ConditionReturn.CALL_EX) {
            return;
        }
        if (keep_ == ConditionReturn.NO) {
            l_.setFinished(true);
        } else {
            rw_.setRead(forLoopLoc_.getFirstChild());
        }
    }
}
