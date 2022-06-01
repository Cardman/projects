package code.formathtml.analyze.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.files.OffsetStringInfo;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.analyze.syntax.ResultExpression;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.analyze.util.ClassMethodIdReturn;
import code.expressionlanguage.stds.PrimitiveTypes;
import code.formathtml.analyze.RenderAnalysis;
import code.formathtml.analyze.AnalyzingDoc;
import code.util.core.StringUtil;

public abstract class AnaRendCondition extends AnaRendParentBlock implements AnaRendBuildEl {

    private final String condition;

    private final int conditionOffset;

    private OperationNode root;
    private final ResultExpression resultExpression = new ResultExpression();

    AnaRendCondition(OffsetStringInfo _condition, int _offset) {
        super(_offset);
        condition = _condition.getInfo();
        conditionOffset = _condition.getOffset();
    }

    public void buildExpressionLanguage(AnaRendDocumentBlock _doc, AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
        buildConditions(_anaDoc, _page);
    }

    protected void buildConditions(AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
        _page.zeroOffset();
        _page.setSumOffset(resultExpression.getSumOffset());
        root = RenderAnalysis.getRootAnalyzedOperations(0, _anaDoc, _page,resultExpression);
        AnaClassArgumentMatching exp_ = root.getResultClass();
        tryConvert(_page, exp_, conditionOffset);
        exp_.setUnwrapObjectNb(PrimitiveTypes.BOOL_WRAP);
    }

    static void tryConvert(AnalyzedPageEl _page, AnaClassArgumentMatching _exp, int _off) {
        if (!_exp.isBoolType(_page)) {
            ClassMethodIdReturn res_ = OperationNode.tryGetDeclaredImplicitCast(_page.getAliasPrimBoolean(), _exp, _page);
            if (res_ != null) {
                _exp.implicitInfosCore(res_);
            } else {
                ClassMethodIdReturn trueOp_ = OperationNode.fetchTrueOperator(_exp, _page);
                if (trueOp_ != null) {
                    _exp.implicitInfosTest(trueOp_);
                } else {
                    FoundErrorInterpret un_ = new FoundErrorInterpret();
                    un_.setFile(_page.getCurrentFile());
                    un_.setIndexFile(_off);
                    un_.buildError(_page.getAnalysisMessages().getUnexpectedType(),
                            StringUtil.join(_exp.getNames(),AND_ERR));
                    AnalyzingDoc.addError(un_, _page);
                }
            }
        }
    }

    public String getCondition() {
        return condition;
    }

    public int getConditionOffset() {
        return conditionOffset;
    }

    public ResultExpression getRes() {
        return resultExpression;
    }

    public OperationNode getRoot() {
        return root;
    }
}
