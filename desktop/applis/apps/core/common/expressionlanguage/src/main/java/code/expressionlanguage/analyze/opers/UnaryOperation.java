package code.expressionlanguage.analyze.opers;
import code.expressionlanguage.Argument;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.opers.util.ClassMethodIdMemberIdTypeFct;
import code.expressionlanguage.analyze.opers.util.OperatorConverter;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.analyze.types.AnaTypeUtil;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.instr.OperationsSequence;
import code.expressionlanguage.linkage.ExportCst;
import code.expressionlanguage.stds.PrimitiveTypes;
import code.expressionlanguage.structs.ByteStruct;
import code.expressionlanguage.structs.ShortStruct;
import code.expressionlanguage.structs.Struct;
import code.util.core.StringUtil;

public final class UnaryOperation extends AbstractUnaryOperation implements SymbolOperation {
    private final ClassMethodIdMemberIdTypeFct fct = new ClassMethodIdMemberIdTypeFct();
    private final String oper;
    private int opOffset;
    private boolean okNum;

    public UnaryOperation(int _index,
            int _indexChild, MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
        oper = getOperators().firstValue().trim();
    }

    @Override
    public void analyzeUnary(AnalyzedPageEl _page) {
        okNum = true;
        OperationNode child_ = getFirstChild();
        AnaClassArgumentMatching clMatch_ = child_.getResultClass();
        opOffset = getOperators().firstKey();
        String oper_ = getOperators().firstValue();
        OperatorConverter clId_ = getUnaryOperatorOrMethod(this,child_, oper_, _page);
        if (clId_ != null) {
            fct.infos(clId_,_page);
            return;
        }
        AnaClassArgumentMatching cl_ = AnaTypeUtil.toPrimitive(clMatch_, _page);
        if (child_ instanceof ConstantOperation) {
            Argument arg_ = child_.getArgument();
            Struct instance_ = arg_.getStruct();
            if (instance_ instanceof ByteStruct) {
                clMatch_.setUnwrapObject(cl_, _page.getPrimitiveTypes());
                setResultClass(AnaClassArgumentMatching.copy(cl_, _page.getPrimitiveTypes()));
                return;
            }
            if (instance_ instanceof ShortStruct) {
                clMatch_.setUnwrapObject(cl_, _page.getPrimitiveTypes());
                setResultClass(AnaClassArgumentMatching.copy(cl_, _page.getPrimitiveTypes()));
                return;
            }
        }
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+opOffset, _page);
        if (!AnaTypeUtil.isPureNumberClass(clMatch_, _page)) {
            _page.setOkNumOp(false);
            String exp_ = _page.getAliasNumber();
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setIndexFile(_page);
            un_.setFile(_page.getCurrentFile());
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
        if (AnaTypeUtil.isIntOrderClass(cl_, _page)) {
            int res_ = AnaTypeUtil.getIntOrderClass(cl_, _page);
            int intOrder_ = AnaTypeUtil.getIntOrderClass(_page.getAliasPrimInteger(), _page);
            if (res_ < intOrder_) {
                cl_ = new AnaClassArgumentMatching(_page.getAliasPrimInteger(),PrimitiveTypes.INT_WRAP);
            }
        }
        clMatch_.setUnwrapObject(cl_, _page.getPrimitiveTypes());
        setResultClass(AnaClassArgumentMatching.copy(cl_, _page.getPrimitiveTypes()));
    }

    public ClassMethodIdMemberIdTypeFct getFct() {
        return fct;
    }

    public String getOper() {
        return oper;
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
