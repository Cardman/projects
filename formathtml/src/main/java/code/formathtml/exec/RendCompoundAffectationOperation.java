package code.formathtml.exec;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.methods.ProcessMethod;
import code.expressionlanguage.opers.CompoundAffectationOperation;
import code.expressionlanguage.opers.util.ClassMethodId;
import code.expressionlanguage.opers.util.MethodId;
import code.util.CustList;

public final class RendCompoundAffectationOperation extends RendMethodOperation implements RendCalculableOperation {

    private RendSettableElResult settable;
    private String oper;
    private ClassMethodId classMethodId;

    public RendCompoundAffectationOperation(CompoundAffectationOperation _c) {
        super(_c);
        oper = _c.getOper();
        classMethodId = _c.getClassMethodId();
    }

    public void setup() {
        settable = RendAffectationOperation.tryGetSettable(this);
    }

    @Override
    public void calculate(ExecutableCode _conf) {
        RendDynOperationNode right_ = getChildrenNodes().last();
        Argument rightArg_ = right_.getArgument();
        if (classMethodId != null) {
            CustList<RendDynOperationNode> chidren_ = new CustList<RendDynOperationNode>();
            chidren_.add((RendDynOperationNode) settable);
            chidren_.add(right_);
            CustList<Argument> arguments_ = new CustList<Argument>();
            arguments_.add(((RendDynOperationNode) settable).getArgument());
            arguments_.add(right_.getArgument());
            CustList<Argument> firstArgs_ = RendInvokingOperation.listArguments(chidren_, -1, EMPTY_STRING, arguments_, _conf);
            String classNameFound_ = classMethodId.getClassName();
            MethodId id_ = classMethodId.getConstraints();
            Argument res_;
            res_ = ProcessMethod.calculateArgument(Argument.createVoid(), classNameFound_, id_, firstArgs_, _conf.getContextEl());
            settable.endCalculate(_conf, res_);
            setSimpleArgument(res_, _conf);
            return;
        }
        settable.calculateCompoundSetting(_conf, oper, rightArg_);
        RendDynOperationNode op_ = (RendDynOperationNode)settable;
        setSimpleArgument(op_.getArgument(), _conf);
    }

}
