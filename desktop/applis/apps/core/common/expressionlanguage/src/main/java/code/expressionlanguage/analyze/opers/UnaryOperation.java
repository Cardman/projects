package code.expressionlanguage.analyze.opers;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.Argument;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.opers.util.OperatorConverter;
import code.expressionlanguage.analyze.types.AnaTypeUtil;
import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.instr.OperationsSequence;
import code.expressionlanguage.inherits.ClassArgumentMatching;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.stds.AliasNumber;
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
        ClassArgumentMatching clMatch_ = child_.getResultClass();
        opOffset = getOperations().getOperators().firstKey();
        String oper_ = getOperations().getOperators().firstValue();
        OperatorConverter clId_ = getUnaryOperatorOrMethod(this,clMatch_, oper_, _conf);
        if (clId_ != null) {
            if (!AnaTypeUtil.isPrimitive(clId_.getSymbol().getClassName(),page_)) {
                classMethodId = clId_.getSymbol();
                rootNumber = clId_.getRootNumber();
                memberNumber = clId_.getMemberNumber();
            }
            return;
        }
        ClassArgumentMatching cl_ = AnaTypeUtil.toPrimitive(clMatch_, page_);
        if (child_ instanceof ConstantOperation) {
            Argument arg_ = child_.getArgument();
            Struct instance_ = arg_.getStruct();
            if (instance_ instanceof ByteStruct) {
                clMatch_.setUnwrapObject(cl_);
                setResultClass(ClassArgumentMatching.copy(cl_));
                return;
            }
            if (instance_ instanceof ShortStruct) {
                clMatch_.setUnwrapObject(cl_);
                setResultClass(ClassArgumentMatching.copy(cl_));
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
            ClassArgumentMatching arg_ = new ClassArgumentMatching(exp_);
            setResultClass(arg_);
            return;
        }
        if (AnaTypeUtil.isLessInt(cl_, page_)) {
            cl_ = new ClassArgumentMatching(stds_.getAliasPrimInteger());
        }
        clMatch_.setUnwrapObject(cl_);
        child_.cancelArgument();
        setResultClass(ClassArgumentMatching.copy(cl_));
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
        Struct nb_ = arg_.getStruct();
        if (!(nb_ instanceof NumberStruct)) {
            return;
        }
        ClassArgumentMatching to_ = _par.getResultClass();
        LgNames stds_ = _conf.getAnalyzing().getStandards();
        Argument out_;
        if (StringList.quickEq(_oper, PLUS)) {
            out_ = new Argument(AliasNumber.idNumber(ClassArgumentMatching.convertToNumber(nb_), to_, stds_));
        } else {
            out_ = new Argument(AliasNumber.opposite(ClassArgumentMatching.convertToNumber(nb_), to_, stds_));
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

    @Override
    public int getMemberNumber() {
        return memberNumber;
    }

    @Override
    public int getRootNumber() {
        return rootNumber;
    }
}
