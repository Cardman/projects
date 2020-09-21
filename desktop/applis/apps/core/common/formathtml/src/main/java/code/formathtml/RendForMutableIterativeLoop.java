package code.formathtml;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.Argument;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.analyze.types.AnaTypeUtil;
import code.expressionlanguage.exec.ConditionReturn;
import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.exec.types.ExecClassArgumentMatching;
import code.expressionlanguage.exec.variables.LocalVariable;
import code.expressionlanguage.files.OffsetStringInfo;
import code.expressionlanguage.files.OffsetsBlock;
import code.expressionlanguage.analyze.inherits.Mapping;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.analyze.util.ClassMethodIdReturn;
import code.expressionlanguage.analyze.blocks.ForLoopPart;
import code.expressionlanguage.analyze.opers.AffectationOperation;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.stds.PrimitiveTypes;
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

    RendForMutableIterativeLoop(OffsetStringInfo _className,
                                OffsetStringInfo _from,
                                OffsetStringInfo _to, OffsetStringInfo _step, OffsetStringInfo _classIndex, OffsetStringInfo _label, OffsetsBlock _offset, LgNames _stds) {
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
            classIndex_ = _stds.getAliasPrimInteger();
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
    public void buildExpressionLanguage(Configuration _cont, RendDocumentBlock _doc, AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
        _page.setGlobalOffset(classIndexNameOffset);
        _page.setOffset(0);
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
        _page.setGlobalOffset(classNameOffset);
        _page.setOffset(0);
        if (!className.isEmpty()) {
            KeyWords keyWords_ = _page.getKeyWords();
            String keyWordVar_ = keyWords_.getKeyWordVar();
            if (StringList.quickEq(className.trim(), keyWordVar_)) {
                importedClassName = keyWordVar_;
            } else {
                importedClassName = ResolvingImportTypes.resolveCorrectType(className, _page);
            }
            _page.setMerged(true);
            _page.setFinalVariable(false);
            _page.setCurrentVarSetting(importedClassName);
        } else {
            _page.setMerged(false);
        }
        _page.getVariablesNames().clear();
        _page.getVariablesNamesToInfer().clear();
        _page.setGlobalOffset(initOffset);
        _page.setOffset(0);
        _anaDoc.setAttribute(_cont.getRendKeyWords().getAttrInit());
        _page.setForLoopPartState(ForLoopPart.INIT);
        _page.setAcceptCommaInstr(true);
        if (init.trim().isEmpty()) {
            opInit = new CustList<RendDynOperationNode>();
        } else {
            opInit = RenderExpUtil.getAnalyzedOperations(init, 0, _anaDoc, _page);
        }
        if (_page.isMerged()) {
            StringList vars_ = _page.getVariablesNames();
            String t_ = inferOrObject(importedClassName, _page);
            AffectationOperation.processInferLoop(t_, _page);
            getVariableNames().addAllElts(vars_);
        }
        _page.setMerged(false);
        _page.setAcceptCommaInstr(false);
        _page.setGlobalOffset(expressionOffset);
        _page.setOffset(0);
        _anaDoc.setAttribute(_cont.getRendKeyWords().getAttrCondition());
        _page.setForLoopPartState(ForLoopPart.CONDITION);
        if (expression.trim().isEmpty()) {
            opExp = new CustList<RendDynOperationNode>();
        } else {
            opExp = RenderExpUtil.getAnalyzedOperations(expression, 0, _anaDoc, _page);
        }
        if (!opExp.isEmpty()) {
            RendDynOperationNode elCondition_ = opExp.last();
            OperationNode root_ = _page.getCurrentRoot();
            AnaClassArgumentMatching exp_ = root_.getResultClass();
            if (!exp_.isBoolType(_page)) {
                ClassMethodIdReturn res_ = OperationNode.tryGetDeclaredImplicitCast(_page.getStandards().getAliasPrimBoolean(), exp_, _page);
                if (res_.isFoundMethod()) {
                    ClassMethodId cl_ = new ClassMethodId(res_.getId().getClassName(),res_.getRealId());
                    exp_.getImplicits().add(cl_);
                    exp_.setRootNumber(res_.getRootNumber());
                    exp_.setMemberNumber(res_.getMemberNumber());
                } else {
                    ClassMethodIdReturn trueOp_ = OperationNode.fetchTrueOperator(exp_, _page);
                    if (trueOp_.isFoundMethod()) {
                        ClassMethodId cl_ = new ClassMethodId(trueOp_.getId().getClassName(),trueOp_.getRealId());
                        exp_.getImplicitsTest().add(cl_);
                        exp_.setRootNumberTest(trueOp_.getRootNumber());
                        exp_.setMemberNumberTest(trueOp_.getMemberNumber());
                    } else {
                        FoundErrorInterpret un_ = new FoundErrorInterpret();
                        un_.setFileName(_anaDoc.getFileName());
                        un_.setIndexFile(expressionOffset);
                        un_.buildError(_page.getAnalysisMessages().getUnexpectedType(),
                                StringList.join(exp_.getNames(),AND_ERR));
                        Configuration.addError(un_, _anaDoc, _page);
                    }
                }
            }
            exp_.setUnwrapObjectNb(PrimitiveTypes.BOOL_WRAP);
            RenderExpUtil.setImplicits(elCondition_, _page, root_);
        }
        buildIncrementPart(_cont,_doc, _anaDoc, _page);
    }

    public String getImportedClassIndexName() {
        return importedClassIndexName;
    }

    public void buildIncrementPart(Configuration _an, RendDocumentBlock _doc, AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
        AnalyzedPageEl page_ = _page;
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
            opStep = RenderExpUtil.getAnalyzedOperations(step, 0, _anaDoc, _page);
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
        Struct struct_ = ExecClassArgumentMatching.defaultValue(importedClassName, _cont.getContext());
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
