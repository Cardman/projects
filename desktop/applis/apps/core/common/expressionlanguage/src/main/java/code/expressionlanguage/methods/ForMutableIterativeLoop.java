package code.expressionlanguage.methods;

import code.expressionlanguage.*;
import code.expressionlanguage.exec.ConditionReturn;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.calls.util.ReadWrite;
import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.files.OffsetBooleanInfo;
import code.expressionlanguage.files.OffsetStringInfo;
import code.expressionlanguage.files.OffsetsBlock;
import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.expressionlanguage.instr.ElUtil;
import code.expressionlanguage.instr.PartOffset;
import code.expressionlanguage.exec.coverage.AbstractCoverageResult;
import code.expressionlanguage.opers.AffectationOperation;
import code.expressionlanguage.opers.Calculation;
import code.expressionlanguage.opers.ExpressionLanguage;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.expressionlanguage.opers.util.*;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.exec.stacks.LoopBlockStack;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.analyze.types.ResolvingImportTypes;
import code.expressionlanguage.exec.variables.LoopVariable;
import code.util.*;

public final class ForMutableIterativeLoop extends BracedStack implements
        ForLoop {

    private String label;
    private int labelOffset;

    private final String className;
    private int classNameOffset;

    private String importedClassName = EMPTY_STRING;

    private final String classIndexName;
    private String importedClassIndexName;
    private int classIndexNameOffset;

    private final StringList variableNames = new StringList();

    private boolean finalVariable;
    private int finalOffset;

    private final String init;
    private int initOffset;

    private final String expression;
    private int expressionOffset;

    private final String step;
    private int stepOffset;

    private CustList<ExecOperationNode> opInit;

    private CustList<ExecOperationNode> opExp;

    private CustList<ExecOperationNode> opStep;

    private CustList<PartOffset> partOffsets = new CustList<PartOffset>();

    public ForMutableIterativeLoop(ContextEl _importingPage,
                                   OffsetBooleanInfo _final,
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
        finalVariable = _final.isInfo();
        finalOffset = _final.getOffset();
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

    public int getInitOffset() {
        return initOffset;
    }

    public int getExpressionOffset() {
        return expressionOffset;
    }

    public int getStepOffset() {
        return stepOffset;
    }

    public String getClassIndexName() {
        return classIndexName;
    }

    public String getClassName() {
        return className;
    }

    public String getInit() {
        return init;
    }

    public String getExpression() {
        return expression;
    }

    public String getStep() {
        return step;
    }

    public boolean isFinalVariable() {
        return finalVariable;
    }

    public int getFinalOffset() {
        return finalOffset;
    }

    public void setImportedClassName(String _importedClassName) {
        importedClassName = _importedClassName;
    }

    private ExpressionLanguage getInitEl() {
        return new ExpressionLanguage(opInit);
    }

    private ExpressionLanguage getExpressionEl() {
        return new ExpressionLanguage(opExp);
    }

    private ExpressionLanguage getStepEl() {
        return new ExpressionLanguage(opStep);
    }

    @Override
    public void reduce(ContextEl _context) {
        if (!opInit.isEmpty()) {
            ExecOperationNode i_ = opInit.last();
            opInit = ElUtil.getReducedNodes(i_);
        }
        if (!opExp.isEmpty()) {
            ExecOperationNode e_ = opExp.last();
            opExp = ElUtil.getReducedNodes(e_);
        }
        if (!opStep.isEmpty()) {
            ExecOperationNode s_ = opStep.last();
            opStep = ElUtil.getReducedNodes(s_);
        }
    }

    @Override
    public void buildExpressionLanguageReadOnly(ContextEl _cont) {
        processVariables(_cont);
        FunctionBlock f_ = _cont.getAnalyzing().getCurrentFct();
        AnalyzedPageEl page_ = _cont.getAnalyzing();
        page_.setGlobalOffset(classNameOffset);
        page_.setOffset(0);
        MethodAccessKind static_ = f_.getStaticContext();
        page_.getVariablesNames().clear();
        page_.getVariablesNamesLoopToInfer().clear();
        page_.setGlobalOffset(initOffset);
        page_.setOffset(0);
        page_.setAcceptCommaInstr(true);
        page_.setForLoopPartState(ForLoopPart.INIT);
        if (init.trim().isEmpty()) {
            opInit = new CustList<ExecOperationNode>();
        } else {
            opInit = ElUtil.getAnalyzedOperationsReadOnly(init, _cont, Calculation.staticCalculation(static_));
        }
        addVars(_cont);
        page_.setGlobalOffset(expressionOffset);
        page_.setOffset(0);
        page_.setForLoopPartState(ForLoopPart.CONDITION);
        if (expression.trim().isEmpty()) {
            opExp = new CustList<ExecOperationNode>();
        } else {
            opExp = ElUtil.getAnalyzedOperationsReadOnly(expression, _cont, Calculation.staticCalculation(static_));
        }
        checkBoolCondition(_cont);
    }

    private void addVars(ContextEl _cont) {
        AnalyzedPageEl page_ = _cont.getAnalyzing();
        if (page_.isMerged()) {
            StringList vars_ = _cont.getAnalyzing().getVariablesNames();
            AffectationOperation.processInferLoop(_cont, importedClassName);
            getVariableNames().addAllElts(vars_);
        }
        page_.setMerged(false);
        page_.setAcceptCommaInstr(false);
    }

    private void processVariables(ContextEl _cont) {
        AnalyzedPageEl page_ = _cont.getAnalyzing();
        page_.setGlobalOffset(classIndexNameOffset);
        page_.setOffset(0);
        importedClassIndexName = ResolvingImportTypes.resolveCorrectType(_cont,classIndexName);
        if (!PrimitiveTypeUtil.isIntOrderClass(new ClassArgumentMatching(importedClassIndexName), _cont)) {
            FoundErrorInterpret cast_ = new FoundErrorInterpret();
            cast_.setFileName(getFile().getFileName());
            cast_.setIndexFile(classIndexNameOffset);
            //classIndexName len
            cast_.buildError(_cont.getAnalysisMessages().getNotPrimitiveWrapper(),
                    importedClassIndexName);
            _cont.addError(cast_);
        }
        page_.setGlobalOffset(classNameOffset);
        page_.setOffset(0);
        if (!className.isEmpty()) {
            KeyWords keyWords_ = _cont.getKeyWords();
            String keyWordVar_ = keyWords_.getKeyWordVar();
            if (StringList.quickEq(className.trim(), keyWordVar_)) {
                importedClassName = keyWordVar_;
            } else {
                importedClassName = ResolvingImportTypes.resolveCorrectType(_cont,className);
                partOffsets.addAllElts(_cont.getCoverage().getCurrentParts());
            }
            page_.setMerged(true);
            page_.setFinalVariable(finalVariable);
            page_.setCurrentVarSetting(importedClassName);
        } else {
            page_.setMerged(false);
        }
    }
    private void checkBoolCondition(ContextEl _cont) {
        if (!opExp.isEmpty()) {
            ExecOperationNode elCondition_ = opExp.last();
            LgNames stds_ = _cont.getStandards();
            if (!elCondition_.getResultClass().isBoolType(_cont)) {
                FoundErrorInterpret un_ = new FoundErrorInterpret();
                un_.setFileName(getFile().getFileName());
                un_.setIndexFile(expressionOffset);
                //second ; char
                un_.buildError(_cont.getAnalysisMessages().getUnexpectedType(),
                        StringList.join(elCondition_.getResultClass().getNames(),"&"));
                _cont.addError(un_);
            }
            elCondition_.getResultClass().setUnwrapObject(stds_.getAliasPrimBoolean());
        }
    }

    public String getImportedClassIndexName() {
        return importedClassIndexName;
    }

    public StringList getVariableNames() {
        return variableNames;
    }

    public void buildIncrementPartReadOnly(ContextEl _an) {
        FunctionBlock f_ = _an.getAnalyzing().getCurrentFct();
        AnalyzedPageEl page_ = _an.getAnalyzing();
        page_.setMerged(false);
        page_.setGlobalOffset(stepOffset);
        page_.setOffset(0);
        page_.setForLoopPartState(ForLoopPart.STEP);
        page_.setMerged(true);
        page_.setAcceptCommaInstr(true);
        page_.getLocalVars().last().clear();
        MethodAccessKind static_ = f_.getStaticContext();
        if (step.trim().isEmpty()) {
            opStep = new CustList<ExecOperationNode>();
        } else {
            opStep = ElUtil.getAnalyzedOperationsReadOnly(step, _an, Calculation.staticCalculation(static_));
        }
        page_.setMerged(false);
        page_.setAcceptCommaInstr(false);
    }


    @Override
    public void processReport(ContextEl _cont, CustList<PartOffset> _parts) {
        if (!opExp.isEmpty()) {
            AbstractCoverageResult result_ = _cont.getCoverage().getCovers().getVal(this).getVal(opExp.last());
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
            tag_ = "</span>";
            _parts.add(new PartOffset(tag_,off_+ _cont.getKeyWords().getKeyWordFor().length()));
        }
        KeyWords keyWords_ = _cont.getKeyWords();
        String keyWordVar_ = keyWords_.getKeyWordVar();
        if (StringList.quickEq(className.trim(), keyWordVar_)) {
            String tag_ = "<b title=\""+ElUtil.transform(importedClassName)+"\">";
            _parts.add(new PartOffset(tag_,classNameOffset));
            tag_ = "</b>";
            _parts.add(new PartOffset(tag_,classNameOffset+ _cont.getKeyWords().getKeyWordFor().length()));
        } else {
            _parts.addAllElts(partOffsets);
        }
        if (!opInit.isEmpty()) {
            _cont.getCoverage().setPossibleDeclareLoopVars(true);
            int off_ = initOffset;
            int offsetEndBlock_ = off_ + init.length();
            ElUtil.buildCoverageReport(_cont,off_,this,opInit,offsetEndBlock_,_parts);
            _cont.getCoverage().setPossibleDeclareLoopVars(false);
        }
        if (!opExp.isEmpty()) {
            int off_ = expressionOffset;
            int offsetEndBlock_ = off_ + expression.length();
            ElUtil.buildCoverageReport(_cont,off_,this,opExp,offsetEndBlock_,_parts);
        }
        if (!opStep.isEmpty()) {
            int off_ = stepOffset;
            int offsetEndBlock_ = off_ + step.length();
            ElUtil.buildCoverageReport(_cont,off_,this,opStep,offsetEndBlock_,_parts);
        }
        refLabel(_parts,label,labelOffset);
    }


    @Override
    public void processEl(ContextEl _cont) {
        AbstractPageEl ip_ = _cont.getLastPage();
        ReadWrite rw_ = ip_.getReadWrite();
        LoopBlockStack c_ = ip_.getLastLoopIfPossible(this);
        if (c_ != null) {
            ip_.processVisitedLoop(c_,this,this,_cont);
            return;
        }
        ip_.setGlobalOffset(initOffset);
        ip_.setOffset(0);
        int index_ = 0;
        if (ip_.isEmptyEl()) {
        	String formatted_ = ip_.formatVarType(importedClassName, _cont);
            Struct struct_ = PrimitiveTypeUtil.defaultValue(formatted_, _cont);
            for (String v: variableNames) {
                LoopVariable lv_ = LoopVariable.newLoopVariable(struct_,formatted_);
                lv_.setIndexClassName(importedClassIndexName);
                ip_.getVars().put(v, lv_);
            }
        }
        if (!opInit.isEmpty()) {
            ExpressionLanguage from_ = ip_.getCurrentEl(_cont,this, CustList.FIRST_INDEX, CustList.FIRST_INDEX);
            ElUtil.tryToCalculate(_cont,from_,0);
            if (_cont.callsOrException()) {
                return;
            }
            index_++;
        }
        ConditionReturn res_ = evaluateCondition(_cont, index_);
        if (res_ == ConditionReturn.CALL_EX) {
            return;
        }
        LoopBlockStack l_ = new LoopBlockStack();
        l_.setBlock(this);
        l_.setCurrentVisitedBlock(this);
        boolean finished_ = res_ == ConditionReturn.NO;
        l_.setFinished(finished_);
        ip_.addBlock(l_);
        if (finished_) {
            processBlockAndRemove(_cont);
            return;
        }
        rw_.setBlock(getFirstChild());
    }

    @Override
    public void removeAllVars(AbstractPageEl _ip) {
        super.removeAllVars(_ip);
        for (String v: variableNames) {
            _ip.getVars().removeKey(v);
        }
    }

    private ConditionReturn evaluateCondition(ContextEl _context, int _index) {
        AbstractPageEl last_ = _context.getLastPage();
        if (opExp.isEmpty()) {
            last_.clearCurrentEls();
            return ConditionReturn.YES;
        }
        ExpressionLanguage exp_ = last_.getCurrentEl(_context,this, _index, CustList.SECOND_INDEX);
        last_.setOffset(0);
        last_.setGlobalOffset(expressionOffset);
        Argument arg_ = ElUtil.tryToCalculate(_context,exp_,0);
        if (_context.callsOrException()) {
            return ConditionReturn.CALL_EX;
        }
        last_.clearCurrentEls();
        if (BooleanStruct.isTrue(arg_.getStruct())) {
            return ConditionReturn.YES;
        }
        return ConditionReturn.NO;
    }
    @Override
    public void exitStack(ContextEl _context) {
        processLastElementLoop(_context);
    }

    @Override
    public void processLastElementLoop(ContextEl _conf) {
        AbstractPageEl ip_ = _conf.getLastPage();
        LoopBlockStack l_ = (LoopBlockStack) ip_.getLastStack();
        l_.setEvaluatingKeepLoop(true);
        int index_ = 0;
        ip_.setOffset(0);
        ip_.setGlobalOffset(stepOffset);
        if (!opStep.isEmpty()) {
            ExpressionLanguage from_ = ip_.getCurrentEl(_conf,this, CustList.FIRST_INDEX, 2);
            ElUtil.tryToCalculate(_conf,from_,0);
            if (_conf.callsOrException()) {
                return;
            }
            index_++;
        }
        if (ip_.sizeEl() <= index_) {
            for (String v : variableNames) {
                LoopVariable lv_ = ip_.getVars().getVal(v);
                lv_.setIndex(lv_.getIndex() + 1);
            }
        }
        ConditionReturn keep_ = evaluateCondition(_conf, index_);
        if (keep_ == ConditionReturn.CALL_EX) {
            return;
        }
        if (keep_ == ConditionReturn.NO) {
            l_.setFinished(true);
        }
        l_.setEvaluatingKeepLoop(false);
    }

    @Override
    public ExpressionLanguage getEl(ContextEl _context,
            int _indexProcess) {
        if (_indexProcess == 0) {
            return getInitEl();
        }
        if (_indexProcess == 1) {
            return getExpressionEl();
        }
        return getStepEl();
    }

    @Override
    public boolean accessibleCondition() {
        if (opExp.isEmpty()) {
            return true;
        }
        ExecOperationNode op_ = opExp.last();
        Argument arg_ = op_.getArgument();
        return Argument.isNotFalseValue(arg_);
    }
    @Override
    public void abruptGroup(AnalyzingEl _anEl) {
        boolean abr_ = true;
        boolean proc_ = true;
        if (!opExp.isEmpty()) {
            ExecOperationNode op_ = opExp.last();
            Argument arg_ = op_.getArgument();
            proc_ = Argument.isTrueValue(arg_);
        }
        if (_anEl.isReachable(this)) {
            if (!proc_) {
                abr_ = false;
            }
        }
        IdMap<BreakBlock, BreakableBlock> breakables_;
        breakables_ = _anEl.getBreakables();
        for (EntryCust<BreakBlock, BreakableBlock> e: breakables_.entryList()) {
            if (e.getValue() == this && _anEl.isReachable(e.getKey())) {
                abr_ = false;
                break;
            }
        }
        if (abr_) {
            _anEl.completeAbruptGroup(this);
        }
    }

    public CustList<ExecOperationNode> getOpInit() {
        return opInit;
    }

    public CustList<ExecOperationNode> getOpStep() {
        return opStep;
    }

    public CustList<ExecOperationNode> getOpExp() {
        return opExp;
    }
}
