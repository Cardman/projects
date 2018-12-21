package code.formathtml.exec;
import code.expressionlanguage.Analyzable;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.methods.ProcessMethod;
import code.expressionlanguage.opers.EqOperation;
import code.expressionlanguage.opers.util.ClassMethodId;
import code.expressionlanguage.opers.util.MethodId;
import code.expressionlanguage.structs.BooleanStruct;
import code.util.CustList;
import code.util.StringList;

public final class ExecEqOperation extends ExecReflectableOpering {

    private String oper;
    private ClassMethodId classMethodId;
    public ExecEqOperation(EqOperation _e) {
        super(_e);
        oper = _e.getOper();
        classMethodId = _e.getClassMethodId();
    }

    private static boolean calculateEq(Argument _a, Argument _b) {
        return _a.getStruct().sameReference(_b.getStruct());
    }

    @Override
    public void quickCalculate(Analyzable _conf) {
        if (classMethodId != null || !_conf.isOkNumOp()) {
            return;
        }
        CustList<ExecDynOperationNode> chidren_ = getChildrenNodes();
        Argument first_ = chidren_.first().getArgument();
        Argument second_ = chidren_.last().getArgument();
        boolean complement_ = false;
        String op_ = oper.trim();
        if (StringList.quickEq(op_, DIFF)) {
            complement_ = true;
        }
        boolean b_ = calculateEq(first_, second_);
        if (complement_) {
            b_ = !b_;
        }
        Argument arg_ = new Argument(new BooleanStruct(b_));
        setSimpleArgumentAna(arg_, _conf);
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
        Argument first_ = chidren_.first().getArgument();
        Argument second_ = chidren_.last().getArgument();
        boolean complement_ = false;
        String op_ = oper.trim();
        if (StringList.quickEq(op_, DIFF)) {
            complement_ = true;
        }
        boolean b_ = calculateEq(first_, second_);
        if (complement_) {
            b_ = !b_;
        }
        Argument arg_ = new Argument(new BooleanStruct(b_));
        setSimpleArgument(arg_, _conf);
    }

}
