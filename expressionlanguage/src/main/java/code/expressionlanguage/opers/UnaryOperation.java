package code.expressionlanguage.opers;
import code.expressionlanguage.Analyzable;
import code.expressionlanguage.Argument;
import code.expressionlanguage.errors.custom.UnexpectedTypeOperationError;
import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.instr.OperationsSequence;
import code.expressionlanguage.opers.exec.Operable;
import code.expressionlanguage.opers.exec.ParentOperable;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.opers.util.ClassMethodId;
import code.expressionlanguage.opers.util.ClassMethodIdReturn;
import code.expressionlanguage.opers.util.MethodId;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.ByteStruct;
import code.expressionlanguage.structs.NumberStruct;
import code.expressionlanguage.structs.ShortStruct;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;
import code.util.*;
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
    public void analyzeUnary(Analyzable _conf) {
        okNum = true;
        OperationNode child_ = getFirstChild();
        LgNames stds_ = _conf.getStandards();
        ClassArgumentMatching clMatch_ = child_.getResultClass();
        opOffset = getOperations().getOperators().firstKey();
        String oper_ = getOperations().getOperators().firstValue();
        ClassMethodIdReturn cust_ = getOperator(_conf, oper_, clMatch_);
        if (cust_.isFoundMethod()) {
            setResultClass(new ClassArgumentMatching(cust_.getReturnType()));
            String foundClass_ = cust_.getRealClass();
            foundClass_ = Templates.getIdFromAllTypes(foundClass_);
            MethodId id_ = cust_.getRealId();
            classMethodId = new ClassMethodId(foundClass_, id_);
            MethodId realId_ = cust_.getRealId();
            CustList<ClassArgumentMatching> firstArgs_ = new CustList<ClassArgumentMatching>();
            firstArgs_.add(child_.getResultClass());
            CustList<OperationNode> children_ = new CustList<OperationNode>(child_);
            InvokingOperation.unwrapArgsFct(children_, realId_, -1, EMPTY_STRING, firstArgs_, _conf);
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
        int orderClass_ = PrimitiveTypeUtil.getOrderClass(clMatch_, _conf);
        if (orderClass_ == 0) {
            _conf.setOkNumOp(false);
            String exp_ = _conf.getStandards().getAliasNumber();
            UnexpectedTypeOperationError un_ = new UnexpectedTypeOperationError();
            un_.setIndexFile(_conf.getCurrentLocationIndex());
            un_.setFileName(_conf.getCurrentFileName());
            un_.setExpectedResult(exp_);
            un_.setOperands(clMatch_);
            _conf.addError(un_);
            ClassArgumentMatching arg_ = new ClassArgumentMatching(exp_);
            setResultClass(arg_);
            return;
        }
        int intOrder_ = PrimitiveTypeUtil.getOrderClass(stds_.getAliasPrimInteger(), _conf);
        if (PrimitiveTypeUtil.getOrderClass(cl_, _conf) < intOrder_) {
            cl_ = new ClassArgumentMatching(stds_.getAliasPrimInteger());
        }
        clMatch_.setUnwrapObject(cl_);
        child_.cancelArgument();
        setResultClass(new ClassArgumentMatching(cl_));
    }

    @Override
    public void quickCalculate(Analyzable _conf) {
        tryGetArg(this,classMethodId,oper,_conf);
    }

    public static void tryGetArg(ParentOperable _par, ClassMethodId _m,String _oper,Analyzable _conf) {
        if (_m != null) {
            return;
        }
        CustList<Operable> chidren_ = _par.getChildrenOperable();
        Argument arg_ = chidren_.first().getArgument();
        Argument out_ = new Argument();
        Struct nb_ = arg_.getStruct();
        if (!(nb_ instanceof NumberStruct)) {
            return;
        }
        ClassArgumentMatching to_ = _par.getResultClass();
        if (StringList.quickEq(_oper, PLUS)) {
            out_.setStruct(NumberStruct.idNumber((NumberStruct) nb_, _conf, to_));
        } else {
            out_.setStruct(NumberStruct.opposite((NumberStruct) nb_, _conf, to_));
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
