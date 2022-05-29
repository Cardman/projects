package code.expressionlanguage.analyze.blocks;

import code.expressionlanguage.Argument;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.files.OffsetStringInfo;
import code.expressionlanguage.analyze.instr.ElUtil;
import code.expressionlanguage.analyze.instr.OperationsSequence;
import code.expressionlanguage.analyze.opers.AffectationOperation;
import code.expressionlanguage.analyze.opers.Calculation;
import code.expressionlanguage.analyze.opers.ErrorPartOperation;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.analyze.opers.util.AnaTypeFct;
import code.expressionlanguage.analyze.syntax.ResultExpression;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.analyze.types.AnaResultPartType;
import code.expressionlanguage.analyze.types.ResolvingTypes;
import code.expressionlanguage.analyze.util.ClassMethodIdReturn;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.linkage.ExportCst;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.stds.PrimitiveTypes;
import code.util.StringList;
import code.util.core.StringUtil;

public final class ForMutableIterativeLoop extends AbstractForLoop implements
        Loop,WithConditionPart,AbsLoopDeclarator {

    private String importedClassName = EMPTY_STRING;

    private boolean alwaysTrue;
    private Argument argument;

    private final ManyLoopExpressionsContent manyLoopExpressionsContent;
    private AnaTypeFct functionImpl;
    private AnaTypeFct function;
    private int testOffset;
    private AnaResultPartType partOffsets = new AnaResultPartType();
    private String errInf = EMPTY_STRING;

    public ForMutableIterativeLoop(int _offset, OffsetStringInfo _label, ManyLoopExpressionsContent _ma) {
        super(_offset, _label);
        manyLoopExpressionsContent = _ma;
    }

    public int getClassNameOffset() {
        return manyLoopExpressionsContent.getClassNameOffset();
    }

    public int getClassIndexNameOffset() {
        return manyLoopExpressionsContent.getClassIndexNameOffset();
    }

    public int getInitOffset() {
        return manyLoopExpressionsContent.getInitOffset();
    }

    public int getExpressionOffset() {
        return manyLoopExpressionsContent.getExpressionOffset();
    }

    public int getStepOffset() {
        return manyLoopExpressionsContent.getStepOffset();
    }

    public String getClassIndexName() {
        return manyLoopExpressionsContent.getClassIndexName();
    }

    public String getClassName() {
        return manyLoopExpressionsContent.getClassName();
    }

    public String getInit() {
        return manyLoopExpressionsContent.getInit();
    }

    public String getExpression() {
        return manyLoopExpressionsContent.getExpression();
    }

    public String getStep() {
        return manyLoopExpressionsContent.getStep();
    }

    public boolean isFinalVariable() {
        return manyLoopExpressionsContent.isFinalVariable();
    }

    public int getFinalOffset() {
        return manyLoopExpressionsContent.getFinalOffset();
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
        _page.setSumOffset(manyLoopExpressionsContent.getResInit().getSumOffset());
        _page.zeroOffset();
        _page.setAcceptCommaInstr(true);
        _page.setForLoopPartState(ForLoopPart.INIT);
        if (!manyLoopExpressionsContent.getInit().trim().isEmpty()) {
            manyLoopExpressionsContent.getResInit().setRoot(ElUtil.getRootAnalyzedOperationsReadOnly(manyLoopExpressionsContent.getResInit(), Calculation.staticCalculation(static_), _page));
        }
        addVars(_page);
        _page.setLineDeclarator(null);
        _page.setSumOffset(manyLoopExpressionsContent.getResExp().getSumOffset());
        _page.zeroOffset();
        _page.setForLoopPartState(ForLoopPart.CONDITION);
        if (manyLoopExpressionsContent.getExpression().trim().isEmpty()) {
            alwaysTrue = true;
        } else {
            manyLoopExpressionsContent.getResExp().setRoot(ElUtil.getRootAnalyzedOperationsReadOnly(manyLoopExpressionsContent.getResExp(), Calculation.staticCalculation(static_), _page));
            checkBoolCondition(manyLoopExpressionsContent.getResExp().getRoot(), _page);
        }
        _page.setSumOffset(manyLoopExpressionsContent.getResStep().getSumOffset());
        _page.zeroOffset();
        _page.setForLoopPartState(ForLoopPart.STEP);
        _page.setAcceptCommaInstr(true);
        if (!manyLoopExpressionsContent.getStep().trim().isEmpty()) {
            manyLoopExpressionsContent.getResStep().setRoot(ElUtil.getRootAnalyzedOperationsReadOnly(manyLoopExpressionsContent.getResStep(), Calculation.staticCalculation(static_), _page));
        }
        _page.setAcceptCommaInstr(false);

    }

    private void addVars(AnalyzedPageEl _page) {
        if (_page.getLineDeclarator() != null) {
            StringList vars_ = _page.getVariablesNames();
            errInf = AffectationOperation.processInfer(importedClassName, _page);
            getVariableNames().addAllElts(vars_);
            if (manyLoopExpressionsContent.isRefVariable()) {
                checkOpers(manyLoopExpressionsContent.getResInit().getRoot(), _page);
            }
        }
        _page.setAcceptCommaInstr(false);
    }

    public static void checkOpers(OperationNode _root, AnalyzedPageEl _page) {
        Line.checkOpers(nullToErr(_root),_page);
    }
    public static OperationNode nullToErr(OperationNode _op) {
        if (_op == null) {
            OperationsSequence op_ = new OperationsSequence();
            return new ErrorPartOperation(0,0,null, op_);
        }
        return _op;
    }
    public String getErrInf() {
        return errInf;
    }

    private void processVariables(AnalyzedPageEl _page) {
        manyLoopExpressionsContent.resolveIndex(this,_page);
        _page.setSumOffset(manyLoopExpressionsContent.getClassNameOffset());
        _page.zeroOffset();
        if (!manyLoopExpressionsContent.getClassName().isEmpty()) {
            _page.setLineDeclarator(this);
            KeyWords keyWords_ = _page.getKeyWords();
            String keyWordVar_ = keyWords_.getKeyWordVar();
            if (StringUtil.quickEq(manyLoopExpressionsContent.getClassName().trim(), keyWordVar_)) {
                importedClassName = keyWordVar_;
            } else {
                partOffsets = ResolvingTypes.resolveCorrectType(manyLoopExpressionsContent.getClassName(), _page);
                importedClassName = partOffsets.getResult(_page);
            }
            _page.setCurrentVarSetting(importedClassName);
        }
    }
    private void checkBoolCondition(OperationNode _root, AnalyzedPageEl _page) {
        AnaClassArgumentMatching exp_ = _root.getResultClass();
        if (!exp_.isBoolType(_page)) {
            ClassMethodIdReturn res_ = OperationNode.tryGetDeclaredImplicitCast(_page.getAliasPrimBoolean(), exp_, _page);
            if (res_ != null) {
                exp_.implicitInfosCore(res_);
                functionImpl = res_.getParametrableContent().getPair();
            } else {
                ClassMethodIdReturn trueOp_ = OperationNode.fetchTrueOperator(exp_, _page);
                if (trueOp_ != null) {
                    exp_.implicitInfosTest(trueOp_);
                    function = trueOp_.getParametrableContent().getPair();
                } else {
                    FoundErrorInterpret un_ = new FoundErrorInterpret();
                    un_.setFile(getFile());
                    un_.setIndexFile(manyLoopExpressionsContent.getExpressionOffset());
                    //second ; char
                    un_.buildError(_page.getAnalysisMessages().getUnexpectedType(),
                            StringUtil.join(exp_.getNames(), ExportCst.JOIN_TYPES));
                    _page.addLocError(un_);
                    addErrorBlock(un_.getBuiltError());
                }
            }
        }
        exp_.setUnwrapObjectNb(PrimitiveTypes.BOOL_WRAP);
//        ElUtil.setImplicits(_exp, _page, _root);
    }

    @Override
    public boolean isRefVariable() {
        return manyLoopExpressionsContent.isRefVariable();
    }

    public String getImportedClassIndexName() {
        return manyLoopExpressionsContent.getImportedClassIndexName();
    }

    public StringList getVariableNames() {
        return manyLoopExpressionsContent.getVariableNames();
    }

    @Override
    public void removeAllVars(AnalyzedPageEl _ip) {
        super.removeAllVars(_ip);
        for (String v: manyLoopExpressionsContent.getVariableNames()) {
            _ip.getLoopsVars().removeKey(v);
            _ip.getInfosVars().removeKey(v);
        }
    }

    public Argument getArgument() {
        return argument;
    }

    public ResultExpression getResInit() {
        return manyLoopExpressionsContent.getResInit();
    }

    public OperationNode getRootInit() {
        return manyLoopExpressionsContent.getResInit().getRoot();
    }

    public ResultExpression getResExp() {
        return manyLoopExpressionsContent.getResExp();
    }

    public OperationNode getRootExp() {
        return manyLoopExpressionsContent.getResExp().getRoot();
    }

    public ResultExpression getResStep() {
        return manyLoopExpressionsContent.getResStep();
    }

    public OperationNode getRootStep() {
        return manyLoopExpressionsContent.getResStep().getRoot();
    }

    public String getImportedClassName() {
        return importedClassName;
    }

    public AnaResultPartType getPartOffsets() {
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

}
