package code.expressionlanguage.analyze.opers;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.opers.util.OperatorConverter;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.analyze.types.AnaTypeUtil;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.instr.OperationsSequence;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.stds.PrimitiveTypes;
import code.util.*;

public final class UnaryBinOperation extends AbstractUnaryOperation implements SymbolOperation {
    private ClassMethodId classMethodId;
    private int opOffset;
    private boolean okNum;
    private int rootNumber = -1;
    private int memberNumber = -1;

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
                classMethodId = clId_.getSymbol();
                rootNumber = clId_.getRootNumber();
                memberNumber = clId_.getMemberNumber();
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
                    StringList.join(clMatch_.getNames(),"&"),
                    oper_);
            _page.getLocalizer().addError(un_);
            if (!MethodOperation.isEmptyError(getFirstChild())){
                getErrs().add(un_.getBuiltError());
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
    public ClassMethodId getClassMethodId() {
        return classMethodId;
    }
    @Override
    public int getOpOffset() {
        return opOffset;
    }

    @Override
    public boolean isOkNum() {
        return okNum;
    }

    @Override
    public int getMemberNumber() {
        return memberNumber;
    }

    @Override
    public int getRootNumber() {
        return rootNumber;
    }
}
