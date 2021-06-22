package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.instr.OperationsSequence;
import code.expressionlanguage.analyze.instr.PartOffset;
import code.expressionlanguage.analyze.opers.util.AnaTypeFct;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.analyze.util.ClassMethodIdReturn;
import code.expressionlanguage.fwd.opers.AnaArrContent;
import code.expressionlanguage.linkage.ExportCst;
import code.expressionlanguage.stds.PrimitiveTypes;
import code.util.CustList;
import code.util.StringList;
import code.util.core.IndexConstants;
import code.util.core.StringUtil;

public abstract class AbstractRefTernaryOperation extends MethodOperation implements SettableElResult {

    private int offsetLocal;
    private AnaTypeFct implFct;
    private AnaTypeFct testFct;
    private final StringList childrenErrors = new StringList();
    private final AnaArrContent arrContent;

    public AbstractRefTernaryOperation(int _index, int _indexChild, MethodOperation _m,
                                       OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
        arrContent = new AnaArrContent();
    }

    public final void setOffsetLocal(int _offsetLocal) {
        offsetLocal = _offsetLocal;
    }

    public final int getOffsetLocal() {
        return offsetLocal;
    }

    @Override
    public void setVariable(boolean _variable) {
        arrContent.setVariable(_variable);
    }

    @Override
    public final void analyze(AnalyzedPageEl _page) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+getOperations().getOperators().firstKey(), _page);
        OperationNode opOne_ = chidren_.first();
        AnaClassArgumentMatching clMatch_ = opOne_.getResultClass();
        if (!clMatch_.isBoolType(_page)) {
            ClassMethodIdReturn res_ = OperationNode.tryGetDeclaredImplicitCast(_page.getAliasPrimBoolean(), clMatch_, _page);
            if (res_ != null) {
                clMatch_.implicitInfosCore(res_);
                implFct = res_.getPair();
            } else {
                ClassMethodIdReturn trueOp_ = OperationNode.fetchTrueOperator(clMatch_, _page);
                if (trueOp_ != null) {
                    clMatch_.implicitInfosTest(trueOp_);
                    testFct = trueOp_.getPair();
                } else {
                    FoundErrorInterpret un_ = new FoundErrorInterpret();
                    un_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
                    un_.setFileName(_page.getLocalizer().getCurrentFileName());
                    //after first arg separator len
                    un_.buildError(_page.getAnalysisMessages().getUnexpectedType(),
                            StringUtil.join(clMatch_.getNames(),ExportCst.JOIN_TYPES));
                    _page.getLocalizer().addError(un_);
                    addErr(un_.getBuiltError());
                }
            }
        }
        StringList deep_ = getErrs();
        if (!deep_.isEmpty()) {
            int i_ = _page.getLocalizer().getCurrentLocationIndex();
            CustList<PartOffset> list_ = new CustList<PartOffset>();
            list_.add(new PartOffset(ExportCst.anchorErr(StringUtil.join(deep_,ExportCst.JOIN_ERR)),i_));
            list_.add(new PartOffset(ExportCst.END_ANCHOR,i_+1));
            getPartOffsetsChildren().add(list_);
        }
        opOne_.getResultClass().setUnwrapObjectNb(PrimitiveTypes.BOOL_WRAP);
        opOne_.getResultClass().setCheckOnlyNullPe(true);
        OperationNode opTwo_ = chidren_.get(IndexConstants.SECOND_INDEX);
        OperationNode opThree_ = chidren_.last();
        if (!(opTwo_ instanceof WrappOperation)) {
            FoundErrorInterpret varg_ = new FoundErrorInterpret();
            varg_.setFileName(_page.getLocalizer().getCurrentFileName());
            varg_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
            //key word len
            varg_.buildError(_page.getAnalysisMessages().getUnexpectedLeaf(),
                    _page.getKeyWords().getKeyWordThat());
            _page.getLocalizer().addError(varg_);
            childrenErrors.add(varg_.getBuiltError());
        }
        if (!(opThree_ instanceof WrappOperation)) {
            FoundErrorInterpret varg_ = new FoundErrorInterpret();
            varg_.setFileName(_page.getLocalizer().getCurrentFileName());
            varg_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
            //key word len
            varg_.buildError(_page.getAnalysisMessages().getUnexpectedLeaf(),
                    _page.getKeyWords().getKeyWordThat());
            _page.getLocalizer().addError(varg_);
            childrenErrors.add(varg_.getBuiltError());
        }
        AnaClassArgumentMatching clMatchTwo_ = opTwo_.getResultClass();
        AnaClassArgumentMatching clMatchThree_ = opThree_.getResultClass();
        if (!clMatchTwo_.matchClass(clMatchThree_)) {
            FoundErrorInterpret cast_ = new FoundErrorInterpret();
            cast_.setFileName(_page.getLocalizer().getCurrentFileName());
            int i_ = _page.getLocalizer().getCurrentLocationIndex();
            cast_.setIndexFile(i_);
            //character before
            cast_.buildError(_page.getAnalysisMessages().getBadImplicitCast(),
                    StringUtil.join(clMatchTwo_.getNames(),ExportCst.JOIN_TYPES),
                    StringUtil.join(clMatchThree_.getNames(),ExportCst.JOIN_TYPES));
            _page.getLocalizer().addError(cast_);
            childrenErrors.add(cast_.getBuiltError());
        }
        setResultClass(AnaClassArgumentMatching.copy(clMatchTwo_,_page.getPrimitiveTypes()));
    }

    public AnaTypeFct getImplFct() {
        return implFct;
    }

    public AnaTypeFct getTestFct() {
        return testFct;
    }

    public StringList getChildrenErrors() {
        return childrenErrors;
    }

    public AnaArrContent getArrContent() {
        return arrContent;
    }
}
