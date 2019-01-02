package code.formathtml.exec;
import code.expressionlanguage.Analyzable;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.methods.ProcessMethod;
import code.expressionlanguage.opers.CmpOperation;
import code.expressionlanguage.opers.util.ClassMethodId;
import code.expressionlanguage.opers.util.MethodId;
import code.util.CustList;

public class ExecAbstractCmpOperation extends ExecReflectableOpering {

    private boolean stringCompare;
    private ClassMethodId classMethodId;
    private String op;

    public ExecAbstractCmpOperation(CmpOperation _a) {
        super(_a);
        stringCompare = _a.isStringCompare();
        classMethodId = _a.getClassMethodId();
        op = _a.getOp();
    }

    public final boolean isStringCompare() {
        return stringCompare;
    }

    @Override
    public final void quickCalculate(Analyzable _conf) {
        CmpOperation.tryGetResult(_conf, op, classMethodId, stringCompare, this);
    }

    @Override
    public void calculate(ExecutableCode _conf) {
        if (classMethodId != null) {
            CustList<ExecDynOperationNode> chidren_ = getChildrenNodes();
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
        CustList<ExecDynOperationNode> chidren_ = getChildrenNodes();
        ExecDynOperationNode opOne_ = chidren_.first();
        ExecDynOperationNode opTwo_ = chidren_.last();
        Argument first_ = opOne_.getArgument();
        Argument second_ = opTwo_.getArgument();
        Argument arg_ = calculateCommon(first_, second_);
        setSimpleArgument(arg_, _conf);
    }
    private Argument calculateCommon(Argument _one, Argument _two) {
        String op_ = getOp().trim();
        if (stringCompare) {
            return CmpOperation.calculateCommonStr(_one, _two, op_);
        }
        return CmpOperation.calculateCommonNb(_one, _two, op_);
    }

    public String getOp() {
        return op;
    }

}
