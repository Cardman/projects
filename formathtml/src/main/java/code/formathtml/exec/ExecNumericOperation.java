package code.formathtml.exec;
import code.expressionlanguage.Analyzable;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.errors.custom.UnexpectedTypeOperationError;
import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.expressionlanguage.methods.Block;
import code.expressionlanguage.methods.ProcessMethod;
import code.expressionlanguage.opers.NumericOperation;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.opers.util.ClassMethodId;
import code.expressionlanguage.opers.util.MethodId;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.stds.ResultErrorStd;
import code.expressionlanguage.structs.ErrorStruct;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.NumberStruct;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;
import code.util.StringList;

public abstract class ExecNumericOperation extends ExecReflectableOpering {
    private ClassMethodId classMethodId;
    private String op;
    private int opOffset;

    public ExecNumericOperation(NumericOperation _n) {
        super(_n);
        classMethodId = _n.getClassMethodId();
        op = _n.getOp();
        opOffset = _n.getOpOffset();
    }

    static Argument calculateAffect(Argument _left,ExecutableCode _conf, Argument _right, String _op, boolean _catString, ClassArgumentMatching _arg) {
        ResultErrorStd res_= new ResultErrorStd();
        NumberStruct.calculateOperator(_conf, res_, _arg, _op, _catString, _left.getStruct(), _right.getStruct());
        return new Argument(res_.getResult());
    }
    static Argument calculateIncrDecr(Argument _left,ExecutableCode _conf, String _op, ClassArgumentMatching _arg) {
        Argument o_;
        if (StringList.quickEq(_op, Block.INCR)) {
            o_ = new Argument(ExecAddOperation.addOne((NumberStruct) _left.getStruct(), _conf, _arg));
        } else {
            o_ = new Argument(ExecAddOperation.removeOne((NumberStruct) _left.getStruct(), _conf, _arg));
        }
        return o_;
    }

    static Argument calculateDivEx(Argument _a, ExecutableCode _cont, Argument _b,ClassArgumentMatching _order) {
        LgNames stds_ = _cont.getStandards();
        String div_;
        div_ = stds_.getAliasDivisionZero();
        Struct res_ = NumberStruct.calculateDiv((NumberStruct)_a.getStruct(),(NumberStruct) _b.getStruct(), _cont, _order);
        if (res_ == NullStruct.NULL_VALUE) {
            _cont.setException(new ErrorStruct(_cont,div_));
        }
        return new Argument(res_);
    }
    static Argument calculateModEx(Argument _a, ExecutableCode _cont, Argument _b,ClassArgumentMatching _order) {
        LgNames stds_ = _cont.getStandards();
        String div_;
        div_ = stds_.getAliasDivisionZero();
        Struct res_ = NumberStruct.calculateMod((NumberStruct)_a.getStruct(),(NumberStruct) _b.getStruct(), _cont, _order);
        if (res_ == NullStruct.NULL_VALUE) {
            _cont.setException(new ErrorStruct(_cont,div_));
        }
        return new Argument(res_);
    }

    static ClassArgumentMatching getResultClass(ClassArgumentMatching _a, Analyzable _cont, ClassArgumentMatching _b) {
        int oa_ = PrimitiveTypeUtil.getOrderClass(_a, _cont);
        String exp_ = _cont.getStandards().getAliasNumber();
        boolean ok_ = true;
        if (oa_ == 0) {
            UnexpectedTypeOperationError un_ = new UnexpectedTypeOperationError();
            un_.setIndexFile(_cont.getCurrentLocationIndex());
            un_.setFileName(_cont.getCurrentFileName());
            un_.setExpectedResult(exp_);
            un_.setOperands(_a);
            _cont.getClasses().addError(un_);
            _cont.setOkNumOp(false);
            ok_ = false;
        }
        int ob_ = PrimitiveTypeUtil.getOrderClass(_b, _cont);
        if (ob_ == 0) {
            UnexpectedTypeOperationError un_ = new UnexpectedTypeOperationError();
            un_.setIndexFile(_cont.getCurrentLocationIndex());
            un_.setFileName(_cont.getCurrentFileName());
            un_.setExpectedResult(exp_);
            un_.setOperands(_b);
            _cont.getClasses().addError(un_);
            _cont.setOkNumOp(false);
            ok_ = false;
        }
        if (!ok_) {
            return new ClassArgumentMatching(exp_);
        }
        return getQuickResultClass(_a, oa_, _cont, _b, ob_);
    }

    static ClassArgumentMatching getQuickResultClass(ClassArgumentMatching _a, int _oa, Analyzable _cont, ClassArgumentMatching _b, int _ob) {
        ClassArgumentMatching arg_;
        int max_ = Math.max(_oa, _ob);
        if (_oa > _ob) {
            arg_ = _a;
        } else {
            arg_ = _b;
        }
        LgNames stds_ = _cont.getStandards();
        int intOrder_ = PrimitiveTypeUtil.getOrderClass(stds_.getAliasPrimInteger(), _cont);
        if (max_ < intOrder_) {
            arg_ = new ClassArgumentMatching(stds_.getAliasPrimInteger());
        }
        return PrimitiveTypeUtil.toPrimitive(arg_, true, _cont);
    }
    abstract Argument calculateOper(Argument _a, String _op, Argument _b, ExecutableCode _cont);
    abstract Argument calculateOperAna(Argument _a, String _op, Argument _b, Analyzable _an);

    @Override
    public void quickCalculate(Analyzable _conf) {
        if (classMethodId != null || !_conf.isOkNumOp()) {
            return;
        }
        CustList<ExecDynOperationNode> chidren_ = getChildrenNodes();
        Argument a_ = chidren_.first().getArgument();
        Argument c_ = chidren_.last().getArgument();
        Argument r_;
        r_ = calculateOperAna(a_, op, c_, _conf);
        if (r_.isNull()) {
            return;
        }
        a_ = r_;
        setSimpleArgumentAna(a_, _conf);
    }
    @Override
    public final void calculate(ExecutableCode _conf) {
        CustList<ExecDynOperationNode> chidren_ = getChildrenNodes();
        Argument a_ = chidren_.first().getArgument();
        Argument c_ = chidren_.last().getArgument();
        setRelativeOffsetPossibleLastPage(getIndexInEl()+opOffset, _conf);
        if (classMethodId != null) {
            CustList<Argument> arguments_ = new CustList<Argument>();
            for (ExecDynOperationNode o: chidren_) {
                arguments_.add(o.getArgument());
            }
            CustList<Argument> firstArgs_ = ExecInvokingOperation.listArguments(chidren_, -1, EMPTY_STRING, arguments_, _conf);
            String classNameFound_ = classMethodId.getClassName();
            MethodId id_ = classMethodId.getConstraints();
            Argument res_;
            res_ = ProcessMethod.calculateArgument(Argument.createVoid(), classNameFound_, id_, firstArgs_, _conf.getContextEl());
            setSimpleArgument(res_, _conf);
            return;
        }
        Argument r_;
        r_ = calculateOper(a_, op, c_, _conf);
        if (_conf.getContextEl().hasException()) {
            return;
        }
        a_ = r_;
        setSimpleArgument(a_, _conf);
    }
}
