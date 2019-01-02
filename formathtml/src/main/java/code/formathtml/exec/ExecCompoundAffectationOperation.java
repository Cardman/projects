package code.formathtml.exec;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.methods.ProcessMethod;
import code.expressionlanguage.opers.CompoundAffectationOperation;
import code.expressionlanguage.opers.util.ClassMethodId;
import code.expressionlanguage.opers.util.MethodId;
import code.util.CustList;

public final class ExecCompoundAffectationOperation extends ExecReflectableOpering {

    private ExecSettableElResult settable;
    private String oper;
    private ClassMethodId classMethodId;

    public ExecCompoundAffectationOperation(CompoundAffectationOperation _c) {
        super(_c);
        oper = _c.getOper();
        classMethodId = _c.getClassMethodId();
    }

    public void setup() {
        settable = ExecAffectationOperation.tryGetSettable(this);
    }
    public ExecSettableElResult getSettable() {
        return settable;
    }
    @Override
    public void calculate(ExecutableCode _conf) {
        ExecDynOperationNode right_ = getChildrenNodes().last();
        Argument rightArg_ = right_.getArgument();
        if (classMethodId != null) {
            CustList<ExecDynOperationNode> chidren_ = new CustList<ExecDynOperationNode>();
            chidren_.add((ExecDynOperationNode) settable);
            chidren_.add(right_);
            CustList<Argument> arguments_ = new CustList<Argument>();
            arguments_.add(((ExecDynOperationNode) settable).getArgument());
            arguments_.add(right_.getArgument());
            CustList<Argument> firstArgs_ = ExecInvokingOperation.listArguments(chidren_, -1, EMPTY_STRING, arguments_, _conf);
            String classNameFound_ = classMethodId.getClassName();
            MethodId id_ = classMethodId.getConstraints();
            Argument res_;
            res_ = ProcessMethod.calculateArgument(Argument.createVoid(), classNameFound_, id_, firstArgs_, _conf.getContextEl());
            settable.endCalculate(_conf, res_);
            setSimpleArgument(res_, _conf);
            return;
        }
        settable.calculateCompoundSetting(_conf, oper, rightArg_);
        ExecDynOperationNode op_ = (ExecDynOperationNode)settable;
        setSimpleArgument(op_.getArgument(), _conf);
    }

}
