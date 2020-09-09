package code.expressionlanguage.analyze.blocks;

import code.expressionlanguage.*;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.types.AnaTypeUtil;
import code.expressionlanguage.exec.blocks.ExecForMutableIterativeLoop;
import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.files.OffsetBooleanInfo;
import code.expressionlanguage.files.OffsetStringInfo;
import code.expressionlanguage.files.OffsetsBlock;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.analyze.util.ClassMethodIdReturn;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.inherits.ClassArgumentMatching;
import code.expressionlanguage.instr.ElUtil;
import code.expressionlanguage.instr.PartOffset;
import code.expressionlanguage.analyze.opers.AffectationOperation;
import code.expressionlanguage.analyze.opers.Calculation;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.analyze.types.ResolvingImportTypes;
import code.util.*;

public final class ForMutableIterativeLoop extends BracedBlock implements
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

    private boolean alwaysTrue;
    private Argument argument;

    private OperationNode rootInit;
    private OperationNode rootExp;
    private OperationNode rootStep;

    private ClassMethodId test;
    private int testOffset;
    private CustList<PartOffset> partOffsets = new CustList<PartOffset>();
    private String errInf = EMPTY_STRING;

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

    @Override
    public void buildExpressionLanguageReadOnly(ContextEl _cont) {
        processVariables(_cont);
        MemberCallingsBlock f_ = _cont.getAnalyzing().getCurrentFct();
        AnalyzedPageEl page_ = _cont.getAnalyzing();
        page_.setGlobalOffset(classNameOffset);
        page_.setOffset(0);
        MethodAccessKind static_ = f_.getStaticContext();
        page_.getVariablesNames().clear();
        page_.getVariablesNamesToInfer().clear();
        page_.setGlobalOffset(initOffset);
        page_.setOffset(0);
        page_.setAcceptCommaInstr(true);
        page_.setForLoopPartState(ForLoopPart.INIT);
        CustList<ExecOperationNode> init_;
        if (init.trim().isEmpty()) {
            init_ = new CustList<ExecOperationNode>();
        } else {
            init_ = ElUtil.getAnalyzedOperationsReadOnly(init, _cont, Calculation.staticCalculation(static_));
            rootInit = page_.getCurrentRoot();
        }
        addVars(_cont);
        page_.setGlobalOffset(expressionOffset);
        page_.setOffset(0);
        page_.setForLoopPartState(ForLoopPart.CONDITION);
        CustList<ExecOperationNode> exp_;
        if (expression.trim().isEmpty()) {
            exp_ = new CustList<ExecOperationNode>();
            alwaysTrue = true;
        } else {
            exp_ = ElUtil.getAnalyzedOperationsReadOnly(expression, _cont, Calculation.staticCalculation(static_));
            ExecOperationNode l_ = exp_.last();
            argument = l_.getArgument();
            checkBoolCondition(_cont, l_);
            rootExp = page_.getCurrentRoot();
        }
        _cont.getCoverage().putBlockOperationsConditions(_cont,this);
        MemberCallingsBlock f_1 = _cont.getAnalyzing().getCurrentFct();
        page_.setMerged(false);
        page_.setGlobalOffset(stepOffset);
        page_.setOffset(0);
        page_.setForLoopPartState(ForLoopPart.STEP);
        page_.setMerged(true);
        page_.setAcceptCommaInstr(true);
        MethodAccessKind static_1 = f_1.getStaticContext();
        CustList<ExecOperationNode> step_;
        if (step.trim().isEmpty()) {
            step_ = new CustList<ExecOperationNode>();
        } else {
            step_ = ElUtil.getAnalyzedOperationsReadOnly(step, _cont, Calculation.staticCalculation(static_1));
            rootStep = page_.getCurrentRoot();
        }
        page_.setMerged(false);
        page_.setAcceptCommaInstr(false);
        ExecForMutableIterativeLoop exec_ = new ExecForMutableIterativeLoop(getOffset(),label, importedClassName, importedClassIndexName,
                variableNames, initOffset, expressionOffset, stepOffset,
                init_,exp_,step_);
        exec_.setFile(page_.getBlockToWrite().getFile());
        page_.getBlockToWrite().appendChild(exec_);
        page_.getAnalysisAss().getMappingBracedMembers().put(this,exec_);
        _cont.getCoverage().putBlockOperations(_cont, exec_,this);

    }

    private void addVars(ContextEl _cont) {
        AnalyzedPageEl page_ = _cont.getAnalyzing();
        if (page_.isMerged()) {
            StringList vars_ = _cont.getAnalyzing().getVariablesNames();
            errInf = AffectationOperation.processInferLoop(_cont, importedClassName);
            getVariableNames().addAllElts(vars_);
        }
        page_.setMerged(false);
        page_.setAcceptCommaInstr(false);
    }

    public String getErrInf() {
        return errInf;
    }

    private void processVariables(ContextEl _cont) {
        AnalyzedPageEl page_ = _cont.getAnalyzing();
        page_.setGlobalOffset(classIndexNameOffset);
        page_.setOffset(0);
        importedClassIndexName = ResolvingImportTypes.resolveCorrectType(_cont,classIndexName);
        if (!AnaTypeUtil.isIntOrderClass(new ClassArgumentMatching(importedClassIndexName), _cont)) {
            FoundErrorInterpret cast_ = new FoundErrorInterpret();
            cast_.setFileName(getFile().getFileName());
            cast_.setIndexFile(classIndexNameOffset);
            //classIndexName len
            cast_.buildError(_cont.getAnalysisMessages().getNotPrimitiveWrapper(),
                    importedClassIndexName);
            _cont.addError(cast_);
            setReachableError(true);
            getErrorsBlock().add(cast_.getBuiltError());
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
                partOffsets.addAllElts(_cont.getAnalyzing().getCurrentParts());
            }
            page_.setMerged(true);
            page_.setFinalVariable(finalVariable);
            page_.setCurrentVarSetting(importedClassName);
        } else {
            page_.setMerged(false);
        }
    }
    private void checkBoolCondition(ContextEl _cont, ExecOperationNode _exp) {
        ClassArgumentMatching exp_ = _exp.getResultClass();
        LgNames stds_ = _cont.getStandards();
        if (!exp_.isBoolType(_cont)) {
            ClassMethodIdReturn res_ = OperationNode.tryGetDeclaredImplicitCast(_cont, _cont.getStandards().getAliasPrimBoolean(), exp_);
            if (res_.isFoundMethod()) {
                ClassMethodId cl_ = new ClassMethodId(res_.getId().getClassName(),res_.getRealId());
                exp_.getImplicits().add(cl_);
                exp_.setRootNumber(res_.getRootNumber());
                exp_.setMemberNumber(res_.getMemberNumber());
            } else {
                ClassMethodIdReturn trueOp_ = OperationNode.fetchTrueOperator(_cont, exp_);
                if (trueOp_.isFoundMethod()) {
                    ClassMethodId cl_ = new ClassMethodId(trueOp_.getId().getClassName(),trueOp_.getRealId());
                    exp_.getImplicitsTest().add(cl_);
                    exp_.setRootNumberTest(trueOp_.getRootNumber());
                    exp_.setMemberNumberTest(trueOp_.getMemberNumber());
                    test = cl_;
                } else {
                    FoundErrorInterpret un_ = new FoundErrorInterpret();
                    un_.setFileName(getFile().getFileName());
                    un_.setIndexFile(expressionOffset);
                    //second ; char
                    un_.buildError(_cont.getAnalysisMessages().getUnexpectedType(),
                            StringList.join(exp_.getNames(),"&"));
                    _cont.addError(un_);
                    setReachableError(true);
                    getErrorsBlock().add(un_.getBuiltError());
                }
            }
        }
        exp_.setUnwrapObject(stds_.getAliasPrimBoolean());
        ElUtil.setImplicits(_exp,_cont);
    }

    public String getImportedClassIndexName() {
        return importedClassIndexName;
    }

    public StringList getVariableNames() {
        return variableNames;
    }

    @Override
    public boolean accessibleCondition() {
        if (alwaysTrue) {
            return true;
        }
        Argument arg_ = getArgument();
        return Argument.isNotFalseValue(arg_);
    }
    @Override
    public void abruptGroup(AnalyzingEl _anEl) {
        boolean abr_ = true;
        boolean proc_ = true;
        if (!alwaysTrue) {
            Argument arg_ = getArgument();
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

    @Override
    public void removeAllVars(AnalyzedPageEl _ip) {
        super.removeAllVars(_ip);
        for (String v: variableNames) {
            _ip.getLoopsVars().removeKey(v);
            _ip.getInfosVars().removeKey(v);
        }
    }

    public Argument getArgument() {
        return argument;
    }

    public OperationNode getRootInit() {
        return rootInit;
    }

    public OperationNode getRootExp() {
        return rootExp;
    }

    public OperationNode getRootStep() {
        return rootStep;
    }

    public String getImportedClassName() {
        return importedClassName;
    }

    public CustList<PartOffset> getPartOffsets() {
        return partOffsets;
    }

    public int getTestOffset() {
        return testOffset;
    }

    public void setTestOffset(int _testOffset) {
        testOffset = _testOffset;
    }

    public ClassMethodId getTest() {
        return test;
    }
}
