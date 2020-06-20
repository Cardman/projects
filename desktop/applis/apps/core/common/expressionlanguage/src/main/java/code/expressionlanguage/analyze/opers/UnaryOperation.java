package code.expressionlanguage.analyze.opers;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.Argument;
import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.expressionlanguage.instr.OperationsSequence;
import code.expressionlanguage.inherits.ClassArgumentMatching;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.stds.LgNames;
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

    public UnaryOperation(int _index,
            int _indexChild, MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
        oper = getOperations().getOperators().firstValue().trim();
    }

    @Override
    public void analyzeUnary(ContextEl _conf) {
        okNum = true;
        OperationNode child_ = getFirstChild();
        LgNames stds_ = _conf.getStandards();
        ClassArgumentMatching clMatch_ = child_.getResultClass();
        opOffset = getOperations().getOperators().firstKey();
        String oper_ = getOperations().getOperators().firstValue();
        ClassMethodId clId_ = getUnaryOperatorOrMethod(this,clMatch_, oper_, _conf);
        if (clId_ != null) {
            classMethodId = clId_;
            return;
        }
        ClassArgumentMatching cl_ = PrimitiveTypeUtil.toPrimitive(clMatch_, _conf);
        if (child_ instanceof ConstantOperation) {
            Argument arg_ = child_.getArgument();
            Struct instance_ = arg_.getStruct();
            if (instance_ instanceof ByteStruct) {
                clMatch_.setUnwrapObject(cl_);
                setResultClass(new ClassArgumentMatching(cl_));
                return;
            }
            if (instance_ instanceof ShortStruct) {
                clMatch_.setUnwrapObject(cl_);
                setResultClass(new ClassArgumentMatching(cl_));
                return;
            }
        }
        setRelativeOffsetPossibleAnalyzable(getIndexInEl(), _conf);
        if (!PrimitiveTypeUtil.isPureNumberClass(clMatch_,_conf)) {
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
            ClassArgumentMatching arg_ = new ClassArgumentMatching(exp_);
            setResultClass(arg_);
            return;
        }
        if (PrimitiveTypeUtil.isLessInt(cl_, _conf)) {
            cl_ = new ClassArgumentMatching(stds_.getAliasPrimInteger());
        }
        clMatch_.setUnwrapObject(cl_);
        child_.cancelArgument();
        setResultClass(new ClassArgumentMatching(cl_));
    }

    @Override
    public void quickCalculate(ContextEl _conf) {
        tryGetArg(this,classMethodId,oper,_conf);
    }

    public static void tryGetArg(MethodOperation _par, ClassMethodId _m,String _oper,ContextEl _conf) {
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
        ClassArgumentMatching to_ = _par.getResultClass();
        if (StringList.quickEq(_oper, PLUS)) {
            out_.setStruct(NumberStruct.idNumber(ClassArgumentMatching.convertToNumber(nb_), _conf, to_));
        } else {
            out_.setStruct(NumberStruct.opposite(ClassArgumentMatching.convertToNumber(nb_), _conf, to_));
        }
        _par.setSimpleArgumentAna(out_, _conf);
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
}
