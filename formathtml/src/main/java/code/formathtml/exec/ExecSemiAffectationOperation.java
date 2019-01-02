package code.formathtml.exec;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.methods.ProcessMethod;
import code.expressionlanguage.opers.SemiAffectationOperation;
import code.expressionlanguage.opers.util.ClassMethodId;
import code.expressionlanguage.opers.util.MethodId;
import code.util.CustList;

public final class ExecSemiAffectationOperation extends ExecAbstractUnaryOperation  {
    private ExecSettableElResult settable;
    private boolean post;
    private String oper;
    private ClassMethodId classMethodId;

    public ExecSemiAffectationOperation(SemiAffectationOperation _s) {
        super(_s);
        post = _s.isPost();
        oper = _s.getOper();
        classMethodId = _s.getClassMethodId();
    }

    public void setup() {
        settable = ExecAffectationOperation.tryGetSettable(this);
    }
    @Override
    public void calculate(ExecutableCode _conf) {
        if (classMethodId != null) {
            CustList<ExecDynOperationNode> chidren_ = new CustList<ExecDynOperationNode>();
            chidren_.add((ExecDynOperationNode) settable);
            CustList<Argument> arguments_ = new CustList<Argument>();
            Argument stored_ = ((ExecDynOperationNode) settable).getArgument();
            arguments_.add(stored_);
            CustList<Argument> firstArgs_ = ExecInvokingOperation.listArguments(chidren_, -1, EMPTY_STRING, arguments_, _conf);
            String classNameFound_ = classMethodId.getClassName();
            MethodId id_ = classMethodId.getConstraints();
            Argument res_;
            res_ = ProcessMethod.calculateArgument(Argument.createVoid(), classNameFound_, id_, firstArgs_, _conf.getContextEl());
            settable.endCalculate(_conf, post, stored_, res_);
            setSimpleArgument(res_, _conf);
            return;
        }
        settable.calculateSemiSetting(_conf, oper, post);
        ExecDynOperationNode op_ = (ExecDynOperationNode)settable;
        setSimpleArgument(op_.getArgument(), _conf);
    }

    static Argument getPrePost(boolean _post, Argument _stored,Argument _right) {
        Argument a_ = _right;
        if (_post) {
            a_ = _stored;
        }
        return a_;
    }
}
