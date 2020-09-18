package code.expressionlanguage.analyze.opers;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.Argument;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.opers.util.OperatorConverter;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.analyze.types.AnaTypeUtil;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.instr.OperationsSequence;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.stds.PrimitiveTypes;
import code.expressionlanguage.structs.ByteStruct;
import code.expressionlanguage.structs.NumberStruct;
import code.expressionlanguage.structs.ShortStruct;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;
import code.util.StringList;

public final class UnaryOperation extends AbstractUnaryOperation implements SymbolOperation {
    private ClassMethodId classMethodId;
    private String oper;
    private int opOffset;
    private boolean okNum;
    private int rootNumber = -1;
    private int memberNumber = -1;

    public UnaryOperation(int _index,
            int _indexChild, MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
        oper = getOperations().getOperators().firstValue().trim();
    }

    @Override
    public void analyzeUnary(ContextEl _conf) {
        okNum = true;
        OperationNode child_ = getFirstChild();
        AnalyzedPageEl page_ = _conf.getAnalyzing();
        LgNames stds_ = page_.getStandards();
        AnaClassArgumentMatching clMatch_ = child_.getResultClass();
        opOffset = getOperations().getOperators().firstKey();
        String oper_ = getOperations().getOperators().firstValue();
        OperatorConverter clId_ = getUnaryOperatorOrMethod(this,child_, oper_, _conf);
        if (clId_ != null) {
            if (!AnaTypeUtil.isPrimitive(clId_.getSymbol().getClassName(),page_)) {
                classMethodId = clId_.getSymbol();
                rootNumber = clId_.getRootNumber();
                memberNumber = clId_.getMemberNumber();
            }
            return;
        }
        AnaClassArgumentMatching cl_ = AnaTypeUtil.toPrimitive(clMatch_, page_);
        if (child_ instanceof ConstantOperation) {
            Argument arg_ = child_.getArgument();
            Struct instance_ = arg_.getStruct();
            if (instance_ instanceof ByteStruct) {
                clMatch_.setUnwrapObject(cl_,page_.getStandards());
                setResultClass(AnaClassArgumentMatching.copy(cl_,page_.getStandards()));
                return;
            }
            if (instance_ instanceof ShortStruct) {
                clMatch_.setUnwrapObject(cl_,page_.getStandards());
                setResultClass(AnaClassArgumentMatching.copy(cl_,page_.getStandards()));
                return;
            }
        }
        setRelativeOffsetPossibleAnalyzable(getIndexInEl(), _conf);
        if (!AnaTypeUtil.isPureNumberClass(clMatch_,page_)) {
            page_.setOkNumOp(false);
            String exp_ = page_.getStandards().getAliasNumber();
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setIndexFile(page_.getLocalizer().getCurrentLocationIndex());
            un_.setFileName(page_.getLocalizer().getCurrentFileName());
            //oper
            un_.buildError(_conf.getAnalyzing().getAnalysisMessages().getUnexpectedOperandTypes(),
                    StringList.join(clMatch_.getNames(),"&"),
                    oper_);
            page_.getLocalizer().addError(un_);
            if (!MethodOperation.isEmptyError(getFirstChild())){
                getErrs().add(un_.getBuiltError());
            }
            AnaClassArgumentMatching arg_ = new AnaClassArgumentMatching(exp_);
            setResultClass(arg_);
            return;
        }
        if (AnaTypeUtil.isIntOrderClass(cl_, _conf)) {
            int res_ = AnaTypeUtil.getIntOrderClass(cl_, _conf);
            int intOrder_ = AnaTypeUtil.getIntOrderClass(stds_.getAliasPrimInteger(), _conf);
            if (res_ < intOrder_) {
                cl_ = new AnaClassArgumentMatching(stds_.getAliasPrimInteger(),PrimitiveTypes.INT_WRAP);
            }
        }
        clMatch_.setUnwrapObject(cl_,page_.getStandards());
        child_.quickCancel();
        setResultClass(AnaClassArgumentMatching.copy(cl_,page_.getStandards()));
    }

    @Override
    public void quickCalculate(ContextEl _conf) {
        tryGetArg(this,classMethodId,oper, _conf.getAnalyzing());
    }

    private static void tryGetArg(MethodOperation _par, ClassMethodId _m, String _oper, AnalyzedPageEl _page) {
        if (_m != null) {
            return;
        }
        CustList<OperationNode> chidren_ = _par.getChildrenNodes();
        Argument arg_ = chidren_.first().getArgument();
        Struct nb_ = arg_.getStruct();
        if (!(nb_ instanceof NumberStruct)) {
            return;
        }
        AnaClassArgumentMatching to_ = _par.getResultClass();
        Argument out_;
        if (StringList.quickEq(_oper, PLUS)) {
            out_ = new Argument(NumParsers.idNumber(NumParsers.convertToNumber(nb_), to_.getUnwrapObjectNb()));
        } else {
            out_ = new Argument(NumParsers.opposite(NumParsers.convertToNumber(nb_), to_.getUnwrapObjectNb()));
        }
        _par.setSimpleArgumentAna(out_, _page);
    }

    @Override
    public ClassMethodId getClassMethodId() {
        return classMethodId;
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

    @Override
    public int getMemberNumber() {
        return memberNumber;
    }

    @Override
    public int getRootNumber() {
        return rootNumber;
    }
}
