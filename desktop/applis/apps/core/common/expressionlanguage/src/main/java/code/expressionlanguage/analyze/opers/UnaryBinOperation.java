package code.expressionlanguage.analyze.opers;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.opers.util.OperatorConverter;
import code.expressionlanguage.analyze.types.AnaTypeUtil;
import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.expressionlanguage.instr.OperationsSequence;
import code.expressionlanguage.inherits.ClassArgumentMatching;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.stds.AliasNumber;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.NumberStruct;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;
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
    public void analyzeUnary(ContextEl _conf) {
        okNum = true;
        OperationNode child_ = getFirstChild();
        LgNames stds_ = _conf.getStandards();
        ClassArgumentMatching clMatch_ = child_.getResultClass();
        opOffset = getOperations().getOperators().firstKey();
        String oper_ = getOperations().getOperators().firstValue();
        OperatorConverter clId_ = getUnaryOperatorOrMethod(this,child_.getResultClass(), oper_, _conf);
        if (clId_ != null) {
            if (!PrimitiveTypeUtil.isPrimitive(clId_.getSymbol().getClassName(),_conf)) {
                classMethodId = clId_.getSymbol();
                rootNumber = clId_.getRootNumber();
                memberNumber = clId_.getMemberNumber();
            }
            return;
        }
        int order_ = AnaTypeUtil.getIntOrderClass(clMatch_, _conf);
        setRelativeOffsetPossibleAnalyzable(getIndexInEl(), _conf);
        if (order_ == 0) {
            _conf.getAnalyzing().setOkNumOp(false);
            String exp_ = _conf.getStandards().getAliasNumber();
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setIndexFile(_conf.getAnalyzing().getLocalizer().getCurrentLocationIndex());
            un_.setFileName(_conf.getAnalyzing().getLocalizer().getCurrentFileName());
            //oper
            un_.buildError(_conf.getAnalysisMessages().getUnexpectedOperandTypes(),
                    StringList.join(clMatch_.getNames(),"&"),
                    oper_);
            _conf.getAnalyzing().getLocalizer().addError(un_);
            if (!MethodOperation.isEmptyError(getFirstChild())){
                getErrs().add(un_.getBuiltError());
            }
            ClassArgumentMatching arg_ = new ClassArgumentMatching(exp_);
            setResultClass(arg_);
            return;
        }
        int intOrder_ = AnaTypeUtil.getIntOrderClass(stds_.getAliasPrimInteger(), _conf);
        ClassArgumentMatching cl_ = PrimitiveTypeUtil.toPrimitive(clMatch_, _conf);
        if (AnaTypeUtil.getIntOrderClass(cl_, _conf) < intOrder_) {
            cl_ = new ClassArgumentMatching(stds_.getAliasPrimInteger());
        }
        clMatch_.setUnwrapObject(cl_);
        child_.cancelArgument();
        setResultClass(ClassArgumentMatching.copy(cl_));
    }

    @Override
    public void quickCalculate(ContextEl _conf) {
        tryGetArg(this,classMethodId,_conf);
    }

    public static void tryGetArg(MethodOperation _par, ClassMethodId _m,ContextEl _conf) {
        if (_m != null) {
            return;
        }
        CustList<OperationNode> chidren_ = _par.getChildrenNodes();
        Argument arg_ = chidren_.first().getArgument();
        Argument out_ = new Argument();
        Struct nb_ = arg_.getStruct();
        if (!(nb_ instanceof NumberStruct)) {
            return;
        }
        ClassArgumentMatching res_ = _par.getResultClass();
        out_.setStruct(AliasNumber.negBinNumber(ClassArgumentMatching.convertToNumber(nb_), _conf, res_));
        _par.setSimpleArgumentAna(out_, _conf);
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
