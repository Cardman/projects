package code.expressionlanguage.analyze.opers;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.opers.util.AnaTypeFct;
import code.expressionlanguage.analyze.opers.util.ClassMethodIdMemberIdTypeFct;
import code.expressionlanguage.analyze.opers.util.MemberId;
import code.expressionlanguage.analyze.opers.util.OperatorConverter;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.analyze.types.AnaTypeUtil;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.instr.OperationsSequence;
import code.expressionlanguage.linkage.ExportCst;
import code.expressionlanguage.stds.PrimitiveTypes;
import code.util.core.StringUtil;

public final class UnaryBinOperation extends AbstractUnaryOperation implements SymbolOperation {

    private final ClassMethodIdMemberIdTypeFct fct = new ClassMethodIdMemberIdTypeFct();
    private int opOffset;
    private boolean okNum;

    public UnaryBinOperation(int _index,
            int _indexChild, MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
    }

    @Override
    public void analyzeUnary(AnalyzedPageEl _page) {
        okNum = true;
        OperationNode child_ = getFirstChild();
        AnaClassArgumentMatching clMatch_ = child_.getResultClass();
        opOffset = getOperations().getOperators().firstKey();
        String oper_ = getOperations().getOperators().firstValue();
        OperatorConverter clId_ = getUnaryOperatorOrMethod(this,child_, oper_, _page);
        if (clId_ != null) {
            fct.infos(clId_,_page);
            return;
        }
        int order_ = AnaTypeUtil.getIntOrderClass(clMatch_, _page);
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+opOffset, _page);
        if (order_ == 0) {
            _page.setOkNumOp(false);
            String exp_ = _page.getAliasNumber();
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
            un_.setFileName(_page.getLocalizer().getCurrentFileName());
            //oper
            un_.buildError(_page.getAnalysisMessages().getUnexpectedOperandTypes(),
                    StringUtil.join(clMatch_.getNames(), ExportCst.JOIN_TYPES),
                    oper_);
            _page.getLocalizer().addError(un_);
            if (!MethodOperation.isEmptyError(getFirstChild())){
                addErr(un_.getBuiltError());
            }
            AnaClassArgumentMatching arg_ = new AnaClassArgumentMatching(exp_);
            setResultClass(arg_);
            return;
        }
        AnaClassArgumentMatching cl_ = AnaTypeUtil.toPrimitive(clMatch_, _page);
        int intOrder_ = AnaTypeUtil.getIntOrderClass(_page.getAliasPrimInteger(), _page);
        if (order_ < intOrder_) {
            cl_ = new AnaClassArgumentMatching(_page.getAliasPrimInteger(),PrimitiveTypes.INT_WRAP);
        }
        clMatch_.setUnwrapObject(cl_, _page.getPrimitiveTypes());
        setResultClass(AnaClassArgumentMatching.copy(cl_, _page.getPrimitiveTypes()));
    }

    public ClassMethodIdMemberIdTypeFct getFct() {
        return fct;
    }

    @Override
    public int getOpOffset() {
        return opOffset;
    }

    @Override
    public boolean isOkNum() {
        return okNum;
    }

}
