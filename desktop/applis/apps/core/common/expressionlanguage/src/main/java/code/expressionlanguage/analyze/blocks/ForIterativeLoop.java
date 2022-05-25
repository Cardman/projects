package code.expressionlanguage.analyze.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.ManageTokens;
import code.expressionlanguage.analyze.TokenErrorMessage;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.files.OffsetStringInfo;
import code.expressionlanguage.analyze.inherits.AnaInherits;
import code.expressionlanguage.analyze.inherits.Mapping;
import code.expressionlanguage.analyze.instr.ElUtil;
import code.expressionlanguage.analyze.opers.Calculation;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.analyze.syntax.ResultExpression;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.analyze.types.AnaTypeUtil;
import code.expressionlanguage.analyze.types.ResolvingTypes;
import code.expressionlanguage.analyze.util.ClassMethodIdReturn;
import code.expressionlanguage.analyze.variables.AnaLocalVariable;
import code.expressionlanguage.analyze.variables.AnaLoopVariable;
import code.expressionlanguage.common.ConstType;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.linkage.ExportCst;
import code.util.StringList;
import code.util.core.StringUtil;

public final class ForIterativeLoop extends AbstractForLoop {

    private String importedClassName;

    private final OneLoopExpressionsContent oneLoopExpressionsContent;
    private final StringList nameErrors = new StringList();

    public ForIterativeLoop(OneLoopExpressionsContent _content, int _offset, OffsetStringInfo _label) {
        super(_offset, _label);
        oneLoopExpressionsContent = _content;
    }

    public int getClassNameOffset() {
        return oneLoopExpressionsContent.getClassNameOffset();
    }

    public int getClassIndexNameOffset() {
        return oneLoopExpressionsContent.getClassIndexNameOffset();
    }

    public int getVariableNameOffset() {
        return oneLoopExpressionsContent.getVariableNameOffset();
    }

    public int getInitOffset() {
        return oneLoopExpressionsContent.getInitOffset();
    }

    public int getExpressionOffset() {
        return oneLoopExpressionsContent.getExpressionOffset();
    }

    public int getStepOffset() {
        return oneLoopExpressionsContent.getStepOffset();
    }

    public int getEqOffset() {
        return oneLoopExpressionsContent.getEqOffset();
    }

    public String getClassIndexName() {
        return oneLoopExpressionsContent.getClassIndexName();
    }

    public String getClassName() {
        return oneLoopExpressionsContent.getClassName();
    }

    public String getVariableName() {
        return oneLoopExpressionsContent.getVariableName();
    }

    public String getInit() {
        return oneLoopExpressionsContent.getInit();
    }

    public String getExpression() {
        return oneLoopExpressionsContent.getExpression();
    }

    public String getStep() {
        return oneLoopExpressionsContent.getStep();
    }

    public boolean isEq() {
        return oneLoopExpressionsContent.isEq();
    }

    public String getImportedClassName() {
        return importedClassName;
    }

    public String getImportedClassIndexName() {
        return oneLoopExpressionsContent.getImportedClassIndexName();
    }

    @Override
    public void buildExpressionLanguageReadOnly(AnalyzedPageEl _page) {
        boolean res_ = processVariableNames(_page);
        MemberCallingsBlock f_ = _page.getCurrentFct();
        String cl_ = importedClassName;
        _page.setSumOffset(oneLoopExpressionsContent.getResInit().getSumOffset());
        _page.zeroOffset();
        MethodAccessKind static_ = f_.getStaticContext();
        oneLoopExpressionsContent.getResInit().setRoot(ElUtil.getRootAnalyzedOperationsReadOnly(oneLoopExpressionsContent.getResInit(), Calculation.staticCalculation(static_), _page));
//        rootInit = _page.getCurrentRoot();
//        ExecOperationNode initEl_ = init_.last();
        checkType(cl_, oneLoopExpressionsContent.getInitOffset(), oneLoopExpressionsContent.getResInit().getRoot(), _page);
        _page.setSumOffset(oneLoopExpressionsContent.getResExp().getSumOffset());
        _page.zeroOffset();
        oneLoopExpressionsContent.getResExp().setRoot(ElUtil.getRootAnalyzedOperationsReadOnly(oneLoopExpressionsContent.getResExp(), Calculation.staticCalculation(static_), _page));
//        rootExp = _page.getCurrentRoot();
//        ExecOperationNode expressionEl_ = exp_.last();
        checkType(cl_, oneLoopExpressionsContent.getExpressionOffset(), oneLoopExpressionsContent.getResExp().getRoot(), _page);
        _page.setSumOffset(oneLoopExpressionsContent.getResStep().getSumOffset());
        _page.zeroOffset();
        oneLoopExpressionsContent.getResStep().setRoot(ElUtil.getRootAnalyzedOperationsReadOnly(oneLoopExpressionsContent.getResStep(), Calculation.staticCalculation(static_), _page));
//        rootStep = _page.getCurrentRoot();
//        ExecOperationNode stepEl_ = step_.last();
        checkType(cl_, oneLoopExpressionsContent.getStepOffset(), oneLoopExpressionsContent.getResStep().getRoot(), _page);
        if (res_) {
            AnaLoopVariable lv_ = new AnaLoopVariable();
            lv_.setRef(oneLoopExpressionsContent.getVariableNameOffset());
            lv_.setIndexClassName(oneLoopExpressionsContent.getImportedClassIndexName());
            _page.getLoopsVars().put(oneLoopExpressionsContent.getVariableName(), lv_);
            AnaLocalVariable lInfo_ = new AnaLocalVariable();
            lInfo_.setClassName(cl_);
            lInfo_.setRef(oneLoopExpressionsContent.getVariableNameOffset());
            lInfo_.setConstType(ConstType.FIX_VAR);
            lInfo_.setFinalVariable(true);
            _page.getInfosVars().put(oneLoopExpressionsContent.getVariableName(), lInfo_);
        }
//        _page.getCoverage().putBlockOperationsLoops(this);
//        ExecForIterativeLoop exec_ = new ExecForIterativeLoop(getOffset(),label, importedClassName,
//                importedClassIndexName,variableName,variableNameOffset, initOffset,
//                expressionOffset, stepOffset,eq,init_,exp_,step_);
//        exec_.setFile(_page.getBlockToWrite().getFile());
//        _page.getBlockToWrite().appendChild(exec_);
//        _page.getAnalysisAss().getMappingBracedMembers().put(this,exec_);
//        _page.getCoverage().putBlockOperations(exec_,this);
    }

    private void checkType(String _elementClass, int _offset, OperationNode _root, AnalyzedPageEl _page) {
        Mapping m_ = new Mapping();
        AnaClassArgumentMatching arg_ = _root.getResultClass();
        m_.setArg(arg_);
        m_.setParam(_elementClass);
        if (!AnaInherits.isCorrectOrNumbers(m_, _page)) {
            ClassMethodIdReturn res_ = OperationNode.tryGetDeclaredImplicitCast(_elementClass, arg_, _page);
            if (res_ != null) {
                arg_.implicitInfos(res_);
            } else {
                FoundErrorInterpret cast_ = new FoundErrorInterpret();
                cast_.setFile(getFile());
                cast_.setIndexFile(_offset);
                //char before expression
                cast_.buildError(_page.getAnalysisMessages().getBadImplicitCast(),
                        StringUtil.join(arg_.getNames(), ExportCst.JOIN_TYPES),
                        _elementClass);
                _page.addLocError(cast_);
                addErrorBlock(cast_.getBuiltError());
            }
        }
//        ElUtil.setImplicits(_stepEl, _page, _root);
    }

    private boolean processVariableNames(AnalyzedPageEl _page) {
        oneLoopExpressionsContent.resolveIndex(this,_page);
        _page.setSumOffset(oneLoopExpressionsContent.getClassNameOffset());
        _page.zeroOffset();
        importedClassName = ResolvingTypes.resolveCorrectType(oneLoopExpressionsContent.getClassName(), _page).getResult(_page);
        String cl_ = importedClassName;
        AnaClassArgumentMatching elementClass_ = new AnaClassArgumentMatching(cl_);
        if (!AnaTypeUtil.isIntOrderClass(elementClass_, _page)) {
            FoundErrorInterpret cast_ = new FoundErrorInterpret();
            cast_.setFile(getFile());
            cast_.setIndexFile(oneLoopExpressionsContent.getClassNameOffset());
            //className len
            cast_.buildError(_page.getAnalysisMessages().getNotPrimitiveWrapper(),
                    importedClassName);
            _page.addLocError(cast_);
            addErrorBlock(cast_.getBuiltError());
        }
        _page.setSumOffset(oneLoopExpressionsContent.getVariableNameOffset());
        _page.zeroOffset();
        TokenErrorMessage res_ = ManageTokens.partVar(_page).checkTokenVar(oneLoopExpressionsContent.getVariableName(), _page);
        if (res_.isError()) {
            FoundErrorInterpret b_ = new FoundErrorInterpret();
            b_.setFile(getFile());
            b_.setIndexFile(oneLoopExpressionsContent.getVariableNameOffset());
            //variable name len
            b_.setBuiltError(res_.getMessage());
            _page.addLocError(b_);
            nameErrors.add(b_.getBuiltError());
            return false;
        }
        return true;
    }


    @Override
    public void removeAllVars(AnalyzedPageEl _ip) {
        super.removeAllVars(_ip);
        _ip.getLoopsVars().removeKey(oneLoopExpressionsContent.getVariableName());
        _ip.getInfosVars().removeKey(oneLoopExpressionsContent.getVariableName());
    }

    public ResultExpression getResInit() {
        return oneLoopExpressionsContent.getResInit();
    }

    public OperationNode getRootInit() {
        return oneLoopExpressionsContent.getResInit().getRoot();
    }

    public ResultExpression getResExp() {
        return oneLoopExpressionsContent.getResExp();
    }

    public OperationNode getRootExp() {
        return oneLoopExpressionsContent.getResExp().getRoot();
    }

    public ResultExpression getResStep() {
        return oneLoopExpressionsContent.getResStep();
    }

    public OperationNode getRootStep() {
        return oneLoopExpressionsContent.getResStep().getRoot();
    }

    public StringList getNameErrors() {
        return nameErrors;
    }
}
