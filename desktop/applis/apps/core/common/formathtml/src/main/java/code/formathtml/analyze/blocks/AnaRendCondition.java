package code.formathtml.analyze.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.files.OffsetStringInfo;
import code.expressionlanguage.analyze.files.OffsetsBlock;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.analyze.util.ClassMethodIdReturn;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.stds.PrimitiveTypes;
import code.formathtml.analyze.RenderAnalysis;
import code.formathtml.analyze.AnalyzingDoc;
import code.util.core.StringUtil;

public abstract class AnaRendCondition extends AnaRendParentBlock {

    private String condition;

    private int conditionOffset;

    private OperationNode root;
    AnaRendCondition(OffsetStringInfo _condition, OffsetsBlock _offset) {
        super(_offset);
        condition = _condition.getInfo();
        conditionOffset = _condition.getOffset();
    }

    @Override
    public void buildExpressionLanguage(AnaRendDocumentBlock _doc, AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
        buildConditions(_anaDoc, _page);
    }

    protected void buildConditions(AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
        _page.setGlobalOffset(conditionOffset);
        _page.setOffset(0);
        _anaDoc.setAttribute(_anaDoc.getRendKeyWords().getAttrCondition());
        root = RenderAnalysis.getRootAnalyzedOperations(condition, 0, _anaDoc, _page);
        AnaClassArgumentMatching exp_ = root.getResultClass();
        if (!exp_.isBoolType(_page)) {
            ClassMethodIdReturn res_ = OperationNode.tryGetDeclaredImplicitCast(_page.getAliasPrimBoolean(), exp_, _page);
            if (res_.isFoundMethod()) {
                ClassMethodId cl_ = new ClassMethodId(res_.getId().getClassName(),res_.getRealId());
                exp_.getImplicits().add(cl_);
                exp_.setMemberId(res_.getMemberId());
            } else {
                ClassMethodIdReturn trueOp_ = OperationNode.fetchTrueOperator(exp_, _page);
                if (trueOp_.isFoundMethod()) {
                    ClassMethodId cl_ = new ClassMethodId(trueOp_.getId().getClassName(),trueOp_.getRealId());
                    exp_.getImplicitsTest().add(cl_);
                    exp_.setMemberIdTest(trueOp_.getMemberId());
                } else {
                    FoundErrorInterpret un_ = new FoundErrorInterpret();
                    un_.setFileName(_anaDoc.getFileName());
                    un_.setIndexFile(conditionOffset);
                    un_.buildError(_page.getAnalysisMessages().getUnexpectedType(),
                            StringUtil.join(exp_.getNames(),AND_ERR));
                    AnalyzingDoc.addError(un_, _anaDoc, _page);
                }
            }
        }
        exp_.setUnwrapObjectNb(PrimitiveTypes.BOOL_WRAP);
    }

    public int getConditionOffset() {
        return conditionOffset;
    }

    public OperationNode getRoot() {
        return root;
    }
}
