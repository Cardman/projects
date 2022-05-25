package code.formathtml.analyze.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.ManageTokens;
import code.expressionlanguage.analyze.TokenErrorMessage;
import code.expressionlanguage.analyze.blocks.OneLoopExpressionsContent;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.files.OffsetStringInfo;
import code.expressionlanguage.analyze.inherits.AnaInherits;
import code.expressionlanguage.analyze.inherits.Mapping;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.analyze.syntax.ResultExpression;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.analyze.types.AnaTypeUtil;
import code.expressionlanguage.analyze.types.ResolvingTypes;
import code.expressionlanguage.analyze.util.ClassMethodIdReturn;
import code.expressionlanguage.analyze.variables.AnaLocalVariable;
import code.expressionlanguage.analyze.variables.AnaLoopVariable;
import code.expressionlanguage.common.ConstType;
import code.formathtml.analyze.AnalyzingDoc;
import code.formathtml.analyze.RenderAnalysis;
import code.util.core.StringUtil;

public final class AnaRendForIterativeLoop extends AnaRendParentBlock implements AnaRendLoop {

    private final String label;
    private final int labelOffset;

    private String importedClassName;

    private final OneLoopExpressionsContent oneLoopExpressionsContent;

    AnaRendForIterativeLoop(OneLoopExpressionsContent _cont, OffsetStringInfo _classIndex, int _offset) {
        super(_offset);
        oneLoopExpressionsContent = _cont;
        label = _classIndex.getInfo();
        labelOffset = _classIndex.getOffset();
    }

    @Override
    public void buildExpressionLanguage(AnaRendDocumentBlock _doc, AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
        _page.setSumOffset(oneLoopExpressionsContent.getClassIndexNameOffset());
        _page.zeroOffset();
        oneLoopExpressionsContent.setImportedClassIndexName(ResolvingTypes.resolveCorrectType(oneLoopExpressionsContent.getClassIndexName(), _page).getResult(_page));
        if (!AnaTypeUtil.isIntOrderClass(new AnaClassArgumentMatching(oneLoopExpressionsContent.getImportedClassIndexName()), _page)) {
            FoundErrorInterpret cast_ = new FoundErrorInterpret();
            cast_.setFile(_page.getCurrentFile());
            cast_.setIndexFile(oneLoopExpressionsContent.getClassIndexNameOffset());
            cast_.buildError(_page.getAnalysisMessages().getNotPrimitiveWrapper(),
                    oneLoopExpressionsContent.getImportedClassIndexName());
            AnalyzingDoc.addError(cast_, _page);
        }
        _page.setSumOffset(oneLoopExpressionsContent.getClassNameOffset());
        _page.zeroOffset();
        importedClassName = ResolvingTypes.resolveCorrectType(oneLoopExpressionsContent.getClassName(), _page).getResult(_page);
        String cl_ = importedClassName;
        AnaClassArgumentMatching elementClass_ = new AnaClassArgumentMatching(cl_);
        if (!AnaTypeUtil.isIntOrderClass(elementClass_, _page)) {
            FoundErrorInterpret cast_ = new FoundErrorInterpret();
            cast_.setFile(_page.getCurrentFile());
            cast_.setIndexFile(oneLoopExpressionsContent.getClassNameOffset());
            cast_.buildError(_page.getAnalysisMessages().getNotPrimitiveWrapper(),
                    importedClassName);
            AnalyzingDoc.addError(cast_, _page);
        }
        _page.setSumOffset(oneLoopExpressionsContent.getVariableNameOffset());
        _page.zeroOffset();
        TokenErrorMessage res_ = ManageTokens.partVar(_page).checkTokenVar(oneLoopExpressionsContent.getVariableName(), _page);
        if (res_.isError()) {
            FoundErrorInterpret b_ = new FoundErrorInterpret();
            b_.setFile(_page.getCurrentFile());
            b_.setIndexFile(oneLoopExpressionsContent.getVariableNameOffset());
            b_.setBuiltError(res_.getMessage());
            AnalyzingDoc.addError(b_, _page);
        }
        _page.setSumOffset(oneLoopExpressionsContent.getResInit().getSumOffset());
        _page.zeroOffset();
        oneLoopExpressionsContent.getResInit().setRoot(RenderAnalysis.getRootAnalyzedOperations(0, _anaDoc, _page,oneLoopExpressionsContent.getResInit()));
        checkResult(_page, cl_, oneLoopExpressionsContent.getInitOffset(), getRootInit());
        _page.setSumOffset(oneLoopExpressionsContent.getResExp().getSumOffset());
        _page.zeroOffset();
        oneLoopExpressionsContent.getResExp().setRoot(RenderAnalysis.getRootAnalyzedOperations(0, _anaDoc, _page,oneLoopExpressionsContent.getResExp()));
        checkResult(_page, cl_, oneLoopExpressionsContent.getExpressionOffset(), getRootExp());
        _page.setSumOffset(oneLoopExpressionsContent.getResStep().getSumOffset());
        _page.zeroOffset();
        oneLoopExpressionsContent.getResStep().setRoot(RenderAnalysis.getRootAnalyzedOperations(0, _anaDoc, _page,oneLoopExpressionsContent.getResStep()));
        checkResult(_page, cl_, oneLoopExpressionsContent.getStepOffset(), getRootStep());
        if (!res_.isError()) {
            AnaLoopVariable lv_ = new AnaLoopVariable();
            lv_.setIndexClassName(oneLoopExpressionsContent.getImportedClassIndexName());
            _page.getLoopsVars().put(oneLoopExpressionsContent.getVariableName(), lv_);
            AnaLocalVariable lInfo_ = new AnaLocalVariable();
            lInfo_.setClassName(cl_);
            lInfo_.setConstType(ConstType.FIX_VAR);
            lInfo_.setFinalVariable(true);
            _page.getInfosVars().put(oneLoopExpressionsContent.getVariableName(), lInfo_);
        }
    }

    private static void checkResult(AnalyzedPageEl _page, String _result, int _offset, OperationNode _currentRoot) {
        Mapping m_ = new Mapping();
        AnaClassArgumentMatching resCl_ = _currentRoot.getResultClass();
        m_.setArg(resCl_);
        m_.setParam(_result);
        if (!AnaInherits.isCorrectOrNumbers(m_, _page)) {
            ClassMethodIdReturn res_ = OperationNode.tryGetDeclaredImplicitCast(_result, resCl_, _page);
            if (res_ != null) {
                resCl_.implicitInfosCore(res_);
            } else {
                FoundErrorInterpret cast_ = new FoundErrorInterpret();
                cast_.setFile(_page.getCurrentFile());
                cast_.setIndexFile(_offset);
                cast_.buildError(_page.getAnalysisMessages().getBadImplicitCast(),
                        _result,
                        StringUtil.join(_currentRoot.getResultClass().getNames(),AND_ERR));
                AnalyzingDoc.addError(cast_, _page);
            }
        }
    }

    @Override
    public void removeAllVars(AnalyzedPageEl _ip) {
        super.removeAllVars(_ip);
        String var_ = getVariableName();
        _ip.getLoopsVars().removeKey(var_);
        _ip.getInfosVars().removeKey(var_);
    }
    public String getVariableName() {
        return oneLoopExpressionsContent.getVariableName();
    }

    public boolean isEq() {
        return oneLoopExpressionsContent.isEq();
    }
    @Override
    public String getRealLabel() {
        return label;
    }

    @Override
    public int getRealLabelOffset() {
        return labelOffset;
    }

    public String getImportedClassName() {
        return importedClassName;
    }

    public String getImportedClassIndexName() {
        return oneLoopExpressionsContent.getImportedClassIndexName();
    }

    public ResultExpression getResInit() {
        return oneLoopExpressionsContent.getResInit();
    }

    public ResultExpression getResExp() {
        return oneLoopExpressionsContent.getResExp();
    }

    public ResultExpression getResStep() {
        return oneLoopExpressionsContent.getResStep();
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

    public int getInitOffset() {
        return oneLoopExpressionsContent.getInitOffset();
    }

    public int getStepOffset() {
        return oneLoopExpressionsContent.getStepOffset();
    }

    public int getExpressionOffset() {
        return oneLoopExpressionsContent.getExpressionOffset();
    }

    public OperationNode getRootExp() {
        return oneLoopExpressionsContent.getResExp().getRoot();
    }

    public OperationNode getRootInit() {
        return oneLoopExpressionsContent.getResInit().getRoot();
    }

    public OperationNode getRootStep() {
        return oneLoopExpressionsContent.getResStep().getRoot();
    }
}
