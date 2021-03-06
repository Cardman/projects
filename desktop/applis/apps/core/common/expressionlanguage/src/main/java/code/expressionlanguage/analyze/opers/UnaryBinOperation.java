package code.expressionlanguage.analyze.opers;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.opers.util.AnaTypeFct;
import code.expressionlanguage.analyze.opers.util.MemberId;
import code.expressionlanguage.analyze.opers.util.OperatorConverter;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.analyze.types.AnaTypeUtil;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.instr.OperationsSequence;
import code.expressionlanguage.stds.PrimitiveTypes;
import code.util.core.StringUtil;

public final class UnaryBinOperation extends AbstractUnaryOperation implements SymbolOperation {

    private String className="";
    private int opOffset;
    private boolean okNum;
    private MemberId memberId = new MemberId();
    private AnaTypeFct function;

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
            if (!AnaTypeUtil.isPrimitive(clId_.getSymbol().getClassName(), _page)) {
                memberId = clId_.getMemberId();
                function = clId_.getFunction();
                className = clId_.getSymbol().getClassName();
            }
            return;
        }
        int order_ = AnaTypeUtil.getIntOrderClass(clMatch_, _page);
        setRelativeOffsetPossibleAnalyzable(getIndexInEl(), _page);
        if (order_ == 0) {
            _page.setOkNumOp(false);
            String exp_ = _page.getAliasNumber();
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
            un_.setFileName(_page.getLocalizer().getCurrentFileName());
            //oper
            un_.buildError(_page.getAnalysisMessages().getUnexpectedOperandTypes(),
                    StringUtil.join(clMatch_.getNames(),"&"),
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

    @Override
    public String getClassName() {
        return className;
    }

    public AnaTypeFct getFunction() {
        return function;
    }

    @Override
    public int getOpOffset() {
        return opOffset;
    }

    @Override
    public boolean isOkNum() {
        return okNum;
    }

    public MemberId getMemberId() {
        return memberId;
    }

}
