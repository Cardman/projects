package code.formathtml.exec;
import code.expressionlanguage.Analyzable;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.methods.ProcessMethod;
import code.expressionlanguage.opers.UnaryOperation;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.opers.util.ClassMethodId;
import code.expressionlanguage.opers.util.MethodId;
import code.expressionlanguage.structs.NumberStruct;
import code.util.CustList;
import code.util.StringList;

public final class ExecUnaryOperation extends ExecAbstractUnaryOperation {
    private ClassMethodId classMethodId;
    private String oper;

    public ExecUnaryOperation(UnaryOperation _u) {
        super(_u);
        oper = _u.getOper();
        classMethodId = _u.getClassMethodId();
    }

    @Override
    public void quickCalculate(Analyzable _conf) {
        if (classMethodId != null || !_conf.isOkNumOp()) {
            return;
        }
        CustList<ExecDynOperationNode> chidren_ = getChildrenNodes();
        Argument arg_ = chidren_.first().getArgument();
        Argument out_ = new Argument();
        if (arg_.isNull()) {
            return;
        }
        ClassArgumentMatching to_ = getResultClass();
        if (StringList.quickEq(oper, PLUS)) {
            out_.setStruct(NumberStruct.idNumber((NumberStruct) arg_.getStruct(), _conf, to_));
        } else {
            out_.setStruct(NumberStruct.opposite((NumberStruct) arg_.getStruct(), _conf, to_));
        }
        setSimpleArgumentAna(out_, _conf);
    }
    @Override
    public void calculate(ExecutableCode _conf) {
        CustList<ExecDynOperationNode> chidren_ = getChildrenNodes();
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
        Argument arg_ = chidren_.first().getArgument();
        Argument a_ = getArgument(_conf, arg_);
        if (_conf.getContextEl().hasException()) {
            return;
        }
        setSimpleArgument(a_, _conf);
    }

    Argument getArgument(ExecutableCode _conf,
            Argument _in) {
        Argument out_ = new Argument();
        setRelativeOffsetPossibleLastPage(getIndexInEl(), _conf);
        ClassArgumentMatching to_ = getResultClass();
        if (StringList.quickEq(oper, PLUS)) {
            out_.setStruct(NumberStruct.idNumber((NumberStruct) _in.getStruct(), _conf, to_));
        } else {
            out_.setStruct(NumberStruct.opposite((NumberStruct) _in.getStruct(), _conf, to_));
        }
        return out_;
    }
}
