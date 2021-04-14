package code.expressionlanguage.analyze.blocks;

import code.expressionlanguage.*;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.instr.OperationsSequence;
import code.expressionlanguage.analyze.opers.ErrorPartOperation;
import code.expressionlanguage.analyze.opers.util.AnaTypeFct;
import code.expressionlanguage.analyze.syntax.ResultExpression;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.analyze.types.AnaTypeUtil;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.files.OffsetBooleanInfo;
import code.expressionlanguage.analyze.files.OffsetStringInfo;
import code.expressionlanguage.analyze.types.ResolvingTypes;
import code.expressionlanguage.common.Delimiters;
import code.expressionlanguage.analyze.util.ClassMethodIdReturn;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.analyze.instr.ElUtil;
import code.expressionlanguage.analyze.instr.PartOffset;
import code.expressionlanguage.analyze.opers.AffectationOperation;
import code.expressionlanguage.analyze.opers.Calculation;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.stds.PrimitiveTypes;
import code.util.*;
import code.util.core.StringUtil;

public final class ForMutableIterativeLoop extends BracedBlock implements
        Loop {

    private final String label;
    private final int labelOffset;

    private final String className;
    private final int classNameOffset;

    private String importedClassName = EMPTY_STRING;

    private final String classIndexName;
    private String importedClassIndexName;
    private final int classIndexNameOffset;

    private final StringList variableNames = new StringList();

    private final boolean finalVariable;
    private final int finalOffset;

    private final String init;
    private final int initOffset;

    private final String expression;
    private final int expressionOffset;

    private final String step;
    private final int stepOffset;

    private boolean alwaysTrue;
    private Argument argument;

    private final ResultExpression resInit = new ResultExpression();
    private final ResultExpression resExp = new ResultExpression();
    private final ResultExpression resStep = new ResultExpression();

    private AnaTypeFct functionImpl;
    private AnaTypeFct function;
    private int testOffset;
    private final CustList<PartOffset> partOffsets = new CustList<PartOffset>();
    private String errInf = EMPTY_STRING;

    private int conditionNb;
    private final boolean refVariable;
    public ForMutableIterativeLoop(OffsetBooleanInfo _final,
                                   OffsetStringInfo _className,
                                   OffsetStringInfo _from,
                                   OffsetStringInfo _to, OffsetStringInfo _step, OffsetStringInfo _classIndex, OffsetStringInfo _label, int _offset, AnalyzedPageEl _page,boolean _refVariable) {
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
            classIndex_ = _page.getAliasPrimInteger();
        }
        classIndexName = classIndex_;
        classIndexNameOffset = _classIndex.getOffset();
        label = _label.getInfo();
        labelOffset = _label.getOffset();
        finalVariable = _final.isInfo();
        finalOffset = _final.getOffset();
        refVariable = _refVariable;
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
        MethodAccessKind static_ = f_.getStaticContext();
        _page.getVariablesNames().clear();
        _page.getVariablesNamesToInfer().clear();
        _page.setGlobalOffset(initOffset);
        _page.zeroOffset();
        _page.setAcceptCommaInstr(true);
        _page.setForLoopPartState(ForLoopPart.INIT);
        if (!init.trim().isEmpty()) {
            resInit.setRoot(ElUtil.getRootAnalyzedOperationsReadOnly(resInit, init, Calculation.staticCalculation(static_), _page));
        }
        addVars(_page);
        _page.setGlobalOffset(expressionOffset);
        _page.zeroOffset();
        _page.setForLoopPartState(ForLoopPart.CONDITION);
        if (expression.trim().isEmpty()) {
            alwaysTrue = true;
        } else {
            resExp.setRoot(ElUtil.getRootAnalyzedOperationsReadOnly(resExp, expression, Calculation.staticCalculation(static_), _page));
            checkBoolCondition(resExp.getRoot(), _page);
        }
        _page.setMerged(false);
        _page.setGlobalOffset(stepOffset);
        _page.zeroOffset();
        _page.setForLoopPartState(ForLoopPart.STEP);
        _page.setMerged(true);
        _page.setAcceptCommaInstr(true);
        if (!step.trim().isEmpty()) {
            resStep.setRoot(ElUtil.getRootAnalyzedOperationsReadOnly(resStep, step, Calculation.staticCalculation(static_), _page));
        }
        _page.setMerged(false);
        _page.setAcceptCommaInstr(false);

    }

    private void addVars(AnalyzedPageEl _page) {
        if (_page.isMerged()) {
            StringList vars_ = _page.getVariablesNames();
            errInf = AffectationOperation.processInfer(importedClassName, _page);
            getVariableNames().addAllElts(vars_);
            if (refVariable) {
                checkOpers(resInit.getRoot(), _page);
            }
        }
        _page.setMerged(false);
        _page.setRefVariable(false);
        _page.setAcceptCommaInstr(false);
    }

    public static void checkOpers(OperationNode _root, AnalyzedPageEl _page) {
        Line.checkOpers(nullToErr(_root),_page);
    }
    public static OperationNode nullToErr(OperationNode _op) {
        if (_op == null) {
            OperationsSequence op_ = new OperationsSequence();
            op_.setDelimiter(new Delimiters());
            return new ErrorPartOperation(0,0,null, op_);
        }
        return _op;
    }
    public String getErrInf() {
        return errInf;
    }

    private void processVariables(AnalyzedPageEl _page) {
        _page.setGlobalOffset(classIndexNameOffset);
        _page.zeroOffset();
        importedClassIndexName = ResolvingTypes.resolveCorrectType(classIndexName, _page);
        if (!AnaTypeUtil.isIntOrderClass(new AnaClassArgumentMatching(importedClassIndexName), _page)) {
            FoundErrorInterpret cast_ = new FoundErrorInterpret();
            cast_.setFileName(getFile().getFileName());
            cast_.setIndexFile(classIndexNameOffset);
            //classIndexName len
            cast_.buildError(_page.getAnalysisMessages().getNotPrimitiveWrapper(),
                    importedClassIndexName);
            _page.addLocError(cast_);
            addErrorBlock(cast_.getBuiltError());
        }
        _page.setGlobalOffset(classNameOffset);
        _page.zeroOffset();
        if (!className.isEmpty()) {
            KeyWords keyWords_ = _page.getKeyWords();
            String keyWordVar_ = keyWords_.getKeyWordVar();
            if (StringUtil.quickEq(className.trim(), keyWordVar_)) {
                importedClassName = keyWordVar_;
            } else {
                importedClassName = ResolvingTypes.resolveCorrectType(className, _page);
                partOffsets.addAllElts(_page.getCurrentParts());
            }
            _page.setMerged(true);
            _page.setRefVariable(refVariable);
            _page.setFinalVariable(finalVariable);
            _page.setCurrentVarSetting(importedClassName);
        } else {
            _page.setMerged(false);
            _page.setRefVariable(false);
        }
    }
    private void checkBoolCondition(OperationNode _root, AnalyzedPageEl _page) {
        AnaClassArgumentMatching exp_ = _root.getResultClass();
        if (!exp_.isBoolType(_page)) {
            ClassMethodIdReturn res_ = OperationNode.tryGetDeclaredImplicitCast(_page.getAliasPrimBoolean(), exp_, _page);
            if (res_.isFoundMethod()) {
                exp_.implicitInfosCore(res_);
                functionImpl = res_.getPair();
            } else {
                ClassMethodIdReturn trueOp_ = OperationNode.fetchTrueOperator(exp_, _page);
                if (trueOp_.isFoundMethod()) {
                    exp_.implicitInfosTest(trueOp_);
                    function = trueOp_.getPair();
                } else {
                    FoundErrorInterpret un_ = new FoundErrorInterpret();
                    un_.setFileName(getFile().getFileName());
                    un_.setIndexFile(expressionOffset);
                    //second ; char
                    un_.buildError(_page.getAnalysisMessages().getUnexpectedType(),
                            StringUtil.join(exp_.getNames(),"&"));
                    _page.addLocError(un_);
                    addErrorBlock(un_.getBuiltError());
                }
            }
        }
        exp_.setUnwrapObjectNb(PrimitiveTypes.BOOL_WRAP);
//        ElUtil.setImplicits(_exp, _page, _root);
    }

    public String getImportedClassIndexName() {
        return importedClassIndexName;
    }

    public StringList getVariableNames() {
        return variableNames;
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

    public ResultExpression getResInit() {
        return resInit;
    }

    public OperationNode getRootInit() {
        return resInit.getRoot();
    }

    public ResultExpression getResExp() {
        return resExp;
    }

    public OperationNode getRootExp() {
        return resExp.getRoot();
    }

    public ResultExpression getResStep() {
        return resStep;
    }

    public OperationNode getRootStep() {
        return resStep.getRoot();
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

    public AnaTypeFct getFunctionImpl() {
        return functionImpl;
    }

    public AnaTypeFct getFunction() {
        return function;
    }

    public void setArgument(Argument _arg) {
        this.argument = _arg;
    }

    public boolean isAlwaysTrue() {
        return alwaysTrue;
    }

    public void setAlwaysTrue(boolean _alwaysTrue) {
        this.alwaysTrue = _alwaysTrue;
    }

    public int getConditionNb() {
        return conditionNb;
    }

    public void setConditionNb(int _conditionNb) {
        conditionNb = _conditionNb;
    }

    public boolean isRefVariable() {
        return refVariable;
    }
}
