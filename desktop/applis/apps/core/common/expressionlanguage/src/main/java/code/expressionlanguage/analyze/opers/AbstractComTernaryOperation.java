package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.InfoErrorDto;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.instr.OperationsSequence;
import code.expressionlanguage.analyze.opers.util.AnaTypeFct;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.analyze.util.ClassMethodIdReturn;
import code.expressionlanguage.linkage.ExportCst;
import code.expressionlanguage.stds.PrimitiveTypes;
import code.util.CustList;
import code.util.StringList;
import code.util.core.StringUtil;

public abstract class AbstractComTernaryOperation extends MethodOperation {

    private int offsetLocal;
    private AnaTypeFct implFct;
    private AnaTypeFct testFct;
    protected AbstractComTernaryOperation(int _index, int _indexChild, MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
    }

    protected void analyzeTernary(AnalyzedPageEl _page) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+ getOperators().firstKey(), _page);
        OperationNode opOne_ = chidren_.first();
        AnaClassArgumentMatching clMatch_ = opOne_.getResultClass();
        if (!clMatch_.isBoolType(_page)) {
            ClassMethodIdReturn res_ = OperationNode.tryGetDeclaredImplicitCast(_page.getAliasPrimBoolean(), clMatch_, _page);
            if (res_ != null) {
                clMatch_.implicitInfosCore(res_);
                implFct = res_.getParametrableContent().getPair();
            } else {
                ClassMethodIdReturn trueOp_ = OperationNode.fetchTrueOperator(clMatch_, _page);
                if (trueOp_ != null) {
                    clMatch_.implicitInfosTest(trueOp_);
                    testFct = trueOp_.getParametrableContent().getPair();
                } else {
                    FoundErrorInterpret un_ = new FoundErrorInterpret();
                    un_.setIndexFile(_page);
                    un_.setFile(_page.getCurrentFile());
                    //after first arg separator len
                    un_.buildError(_page.getAnalysisMessages().getUnexpectedType(),
                            StringUtil.join(clMatch_.getNames(), ExportCst.JOIN_TYPES));
                    _page.getLocalizer().addError(un_);
                    addErr(un_.getBuiltError());
                }
            }
        }
        StringList deep_ = getErrs();
        if (!deep_.isEmpty()) {
            getPartOffsetsChildren().add(new InfoErrorDto(StringUtil.join(deep_,ExportCst.JOIN_ERR),_page,1));
        }
        opOne_.getResultClass().setUnwrapObjectNb(PrimitiveTypes.BOOL_WRAP);
        opOne_.getResultClass().setCheckOnlyNullPe(true);
    }

    public final void setOffsetLocal(int _offsetLocal) {
        offsetLocal = _offsetLocal;
    }

    public final int getOffsetLocal() {
        return offsetLocal;
    }

    public AnaTypeFct getImplFct() {
        return implFct;
    }

    public AnaTypeFct getTestFct() {
        return testFct;
    }
}
