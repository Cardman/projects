package code.expressionlanguage.analyze.blocks;

import code.expressionlanguage.*;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.analyze.types.AnaTypeUtil;
import code.expressionlanguage.exec.blocks.ExecForMutableIterativeLoop;
import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.files.OffsetBooleanInfo;
import code.expressionlanguage.files.OffsetStringInfo;
import code.expressionlanguage.files.OffsetsBlock;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.analyze.util.ClassMethodIdReturn;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.instr.ElUtil;
import code.expressionlanguage.instr.PartOffset;
import code.expressionlanguage.analyze.opers.AffectationOperation;
import code.expressionlanguage.analyze.opers.Calculation;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.analyze.types.ResolvingImportTypes;
import code.expressionlanguage.stds.PrimitiveTypes;
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

    public ForMutableIterativeLoop(OffsetBooleanInfo _final,
                                   OffsetStringInfo _className,
                                   OffsetStringInfo _from,
                                   OffsetStringInfo _to, OffsetStringInfo _step, OffsetStringInfo _classIndex, OffsetStringInfo _label, OffsetsBlock _offset, AnalyzedPageEl _page) {
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
            classIndex_ = _page.getStandards().getAliasPrimInteger();
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
    public void buildExpressionLanguageReadOnly(AnalyzedPageEl _page) {
        processVariables(_page);
        MemberCallingsBlock f_ = _page.getCurrentFct();
        _page.setGlobalOffset(classNameOffset);
        _page.setOffset(0);
        MethodAccessKind static_ = f_.getStaticContext();
        _page.getVariablesNames().clear();
        _page.getVariablesNamesToInfer().clear();
        _page.setGlobalOffset(initOffset);
        _page.setOffset(0);
        _page.setAcceptCommaInstr(true);
        _page.setForLoopPartState(ForLoopPart.INIT);
        CustList<ExecOperationNode> init_;
        if (init.trim().isEmpty()) {
            init_ = new CustList<ExecOperationNode>();
        } else {
            init_ = ElUtil.getAnalyzedOperationsReadOnly(init, Calculation.staticCalculation(static_), _page);
            rootInit = _page.getCurrentRoot();
        }
        addVars(_page);
        _page.setGlobalOffset(expressionOffset);
        _page.setOffset(0);
        _page.setForLoopPartState(ForLoopPart.CONDITION);
        CustList<ExecOperationNode> exp_;
        if (expression.trim().isEmpty()) {
            exp_ = new CustList<ExecOperationNode>();
            alwaysTrue = true;
        } else {
            exp_ = ElUtil.getAnalyzedOperationsReadOnly(expression, Calculation.staticCalculation(static_), _page);
            ExecOperationNode l_ = exp_.last();
            argument = l_.getArgument();
            checkBoolCondition(l_, _page.getCurrentRoot(), _page);
            rootExp = _page.getCurrentRoot();
        }
        _page.getCoverage().putBlockOperationsConditions(this);
        MemberCallingsBlock f_1 = _page.getCurrentFct();
        _page.setMerged(false);
        _page.setGlobalOffset(stepOffset);
        _page.setOffset(0);
        _page.setForLoopPartState(ForLoopPart.STEP);
        _page.setMerged(true);
        _page.setAcceptCommaInstr(true);
        MethodAccessKind static_1 = f_1.getStaticContext();
        CustList<ExecOperationNode> step_;
        if (step.trim().isEmpty()) {
            step_ = new CustList<ExecOperationNode>();
        } else {
            step_ = ElUtil.getAnalyzedOperationsReadOnly(step, Calculation.staticCalculation(static_1), _page);
            rootStep = _page.getCurrentRoot();
        }
        _page.setMerged(false);
        _page.setAcceptCommaInstr(false);
        ExecForMutableIterativeLoop exec_ = new ExecForMutableIterativeLoop(getOffset(),label, importedClassName, importedClassIndexName,
                variableNames, initOffset, expressionOffset, stepOffset,
                init_,exp_,step_);
        exec_.setFile(_page.getBlockToWrite().getFile());
        _page.getBlockToWrite().appendChild(exec_);
        _page.getAnalysisAss().getMappingBracedMembers().put(this,exec_);
        _page.getCoverage().putBlockOperations(exec_,this);

    }

    private void addVars(AnalyzedPageEl _page) {
        if (_page.isMerged()) {
            StringList vars_ = _page.getVariablesNames();
            errInf = AffectationOperation.processInferLoop(importedClassName, _page);
            getVariableNames().addAllElts(vars_);
        }
        _page.setMerged(false);
        _page.setAcceptCommaInstr(false);
    }

    public String getErrInf() {
        return errInf;
    }

    private void processVariables(AnalyzedPageEl _page) {
        AnalyzedPageEl page_ = _page;
        page_.setGlobalOffset(classIndexNameOffset);
        page_.setOffset(0);
        importedClassIndexName = ResolvingImportTypes.resolveCorrectType(classIndexName, _page);
        if (!AnaTypeUtil.isIntOrderClass(new AnaClassArgumentMatching(importedClassIndexName), _page)) {
            FoundErrorInterpret cast_ = new FoundErrorInterpret();
            cast_.setFileName(getFile().getFileName());
            cast_.setIndexFile(classIndexNameOffset);
            //classIndexName len
            cast_.buildError(_page.getAnalysisMessages().getNotPrimitiveWrapper(),
                    importedClassIndexName);
            _page.addLocError(cast_);
            setReachableError(true);
            getErrorsBlock().add(cast_.getBuiltError());
        }
        page_.setGlobalOffset(classNameOffset);
        page_.setOffset(0);
        if (!className.isEmpty()) {
            KeyWords keyWords_ = _page.getKeyWords();
            String keyWordVar_ = keyWords_.getKeyWordVar();
            if (StringList.quickEq(className.trim(), keyWordVar_)) {
                importedClassName = keyWordVar_;
            } else {
                importedClassName = ResolvingImportTypes.resolveCorrectType(className, _page);
                partOffsets.addAllElts(_page.getCurrentParts());
            }
            page_.setMerged(true);
            page_.setFinalVariable(finalVariable);
            page_.setCurrentVarSetting(importedClassName);
        } else {
            page_.setMerged(false);
        }
    }
    private void checkBoolCondition(ExecOperationNode _exp, OperationNode _root, AnalyzedPageEl _page) {
        AnaClassArgumentMatching exp_ = _root.getResultClass();
        AnalyzedPageEl page_ = _page;
        LgNames stds_ = page_.getStandards();
        if (!exp_.isBoolType(page_)) {
            ClassMethodIdReturn res_ = OperationNode.tryGetDeclaredImplicitCast(page_.getStandards().getAliasPrimBoolean(), exp_, _page);
            if (res_.isFoundMethod()) {
                ClassMethodId cl_ = new ClassMethodId(res_.getId().getClassName(),res_.getRealId());
                exp_.getImplicits().add(cl_);
                exp_.setRootNumber(res_.getRootNumber());
                exp_.setMemberNumber(res_.getMemberNumber());
//                _exp.getResultClass().getImplicits().add(cl_);
//                _exp.getResultClass().setRootNumber(res_.getRootNumber());
//                _exp.getResultClass().setMemberNumber(res_.getMemberNumber());
            } else {
                ClassMethodIdReturn trueOp_ = OperationNode.fetchTrueOperator(exp_, _page);
                if (trueOp_.isFoundMethod()) {
                    ClassMethodId cl_ = new ClassMethodId(trueOp_.getId().getClassName(),trueOp_.getRealId());
                    exp_.getImplicitsTest().add(cl_);
                    exp_.setRootNumberTest(trueOp_.getRootNumber());
                    exp_.setMemberNumberTest(trueOp_.getMemberNumber());
//                    _exp.getResultClass().getImplicitsTest().add(cl_);
//                    _exp.getResultClass().setRootNumberTest(trueOp_.getRootNumber());
//                    _exp.getResultClass().setMemberNumberTest(trueOp_.getMemberNumber());
                    test = cl_;
                } else {
                    FoundErrorInterpret un_ = new FoundErrorInterpret();
                    un_.setFileName(getFile().getFileName());
                    un_.setIndexFile(expressionOffset);
                    //second ; char
                    un_.buildError(_page.getAnalysisMessages().getUnexpectedType(),
                            StringList.join(exp_.getNames(),"&"));
                    _page.addLocError(un_);
                    setReachableError(true);
                    getErrorsBlock().add(un_.getBuiltError());
                }
            }
        }
        exp_.setUnwrapObjectNb(PrimitiveTypes.BOOL_WRAP);
        ElUtil.setImplicits(_exp, _page, _root);
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
