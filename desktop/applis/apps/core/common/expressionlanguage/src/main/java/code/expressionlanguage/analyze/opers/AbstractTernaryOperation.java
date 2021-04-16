package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.opers.util.AnaTypeFct;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.analyze.types.AnaTypeUtil;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.analyze.util.ClassMethodIdReturn;
import code.expressionlanguage.analyze.inherits.ResultTernary;
import code.expressionlanguage.analyze.instr.OperationsSequence;
import code.expressionlanguage.analyze.instr.PartOffset;
import code.expressionlanguage.linkage.ExportCst;
import code.expressionlanguage.linkage.LinkageUtil;
import code.expressionlanguage.stds.PrimitiveTypes;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.IndexConstants;
import code.util.core.StringUtil;

public abstract class AbstractTernaryOperation extends MethodOperation {

    private int offsetLocal;
    private AnaTypeFct implFct;
    private AnaTypeFct testFct;

    public AbstractTernaryOperation(int _index, int _indexChild, MethodOperation _m,
            OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
    }

    public final void setOffsetLocal(int _offsetLocal) {
        offsetLocal = _offsetLocal;
    }

    public final int getOffsetLocal() {
        return offsetLocal;
    }

    @Override
    public final void analyze(AnalyzedPageEl _page) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+getOperations().getOperators().firstKey(), _page);
        OperationNode opOne_ = chidren_.first();
        AnaClassArgumentMatching clMatch_ = opOne_.getResultClass();
        if (!clMatch_.isBoolType(_page)) {
            ClassMethodIdReturn res_ = OperationNode.tryGetDeclaredImplicitCast(_page.getAliasPrimBoolean(), clMatch_, _page);
            if (res_.isFoundMethod()) {
                clMatch_.implicitInfosCore(res_);
                implFct = res_.getPair();
            } else {
                ClassMethodIdReturn trueOp_ = OperationNode.fetchTrueOperator(clMatch_, _page);
                if (trueOp_.isFoundMethod()) {
                    clMatch_.implicitInfosTest(trueOp_);
                    testFct = trueOp_.getPair();
                } else {
                    FoundErrorInterpret un_ = new FoundErrorInterpret();
                    un_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
                    un_.setFileName(_page.getLocalizer().getCurrentFileName());
                    //after first arg separator len
                    un_.buildError(_page.getAnalysisMessages().getUnexpectedType(),
                            StringUtil.join(clMatch_.getNames(),"&"));
                    _page.getLocalizer().addError(un_);
                    addErr(un_.getBuiltError());
                }
            }
        }
        StringList deep_ = getErrs();
        if (!deep_.isEmpty()) {
            int i_ = _page.getLocalizer().getCurrentLocationIndex();
            CustList<PartOffset> list_ = new CustList<PartOffset>();
            list_.add(new PartOffset(ExportCst.anchorErr(StringUtil.join(deep_,"\n\n")),i_));
            list_.add(new PartOffset(ExportCst.END_ANCHOR,i_+1));
            getPartOffsetsChildren().add(list_);
        }
        opOne_.getResultClass().setUnwrapObjectNb(PrimitiveTypes.BOOL_WRAP);
        opOne_.getResultClass().setCheckOnlyNullPe(true);
        OperationNode opTwo_ = chidren_.get(IndexConstants.SECOND_INDEX);
        OperationNode opThree_ = chidren_.last();
        AnaClassArgumentMatching clMatchTwo_ = opTwo_.getResultClass();
        AnaClassArgumentMatching clMatchThree_ = opThree_.getResultClass();
        StringMap<StringList> vars_ = _page.getCurrentConstraints().getCurrentConstraints();
        OperationNode current_ = this;
        MethodOperation m_ = getParent();
        while (m_ != null) {
            if (!(m_ instanceof AbstractTernaryOperation)) {
                if (m_ instanceof IdOperation) {
                    current_ = current_.getParent();
                    m_ = m_.getParent();
                    continue;
                }
                break;
            }
            if (m_.getFirstChild() == current_) {
                break;
            }
            current_ = current_.getParent();
            m_ = m_.getParent();
        }
        String type_ = EMPTY_STRING;
        if (m_ instanceof CastOperation) {
            CastOperation c_ = (CastOperation) m_;
            type_ = c_.getClassName();
        }
        if (!type_.isEmpty()) {
            if (AnaTypeUtil.isPrimitive(type_, _page)) {
                opTwo_.getResultClass().setUnwrapObject(type_, _page.getPrimitiveTypes());
                opThree_.getResultClass().setUnwrapObject(type_, _page.getPrimitiveTypes());
            }
            setResultClass(new AnaClassArgumentMatching(type_));
            return;
        }
        StringList one_ = clMatchTwo_.getNames();
        StringList two_ = clMatchThree_.getNames();
        ResultTernary res_ = ResultTernary.getResultTernary(one_, null, two_, null, vars_, _page);
        if (res_.isUnwrapFirst()) {
            opTwo_.getResultClass().setUnwrapObjectNb(res_.getCastPrim());
        }
        if (res_.isUnwrapSecond()) {
            opThree_.getResultClass().setUnwrapObjectNb(res_.getCastPrim());
        }
        setResultClass(new AnaClassArgumentMatching(res_.getTypes()));
    }

    public AnaTypeFct getImplFct() {
        return implFct;
    }

    public AnaTypeFct getTestFct() {
        return testFct;
    }

}
