package code.expressionlanguage.opers.exec;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.calls.util.CustomFoundMethod;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.expressionlanguage.opers.CompoundAffectationOperation;
import code.expressionlanguage.opers.util.ClassMethodId;
import code.expressionlanguage.opers.util.MethodId;
import code.util.CustList;
import code.util.IdMap;

public final class ExecCompoundAffectationOperation extends ExecReflectableOpering implements CallExecSimpleOperation {

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
    public Argument calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes,
            ContextEl _conf) {
        CustList<ExecOperationNode> list_ = getChildrenNodes();
        ExecOperationNode right_ = list_.last();
        Argument rightArg_ = getArgument(_nodes,right_);
        if (classMethodId != null) {
            CustList<ExecOperationNode> chidren_ = new CustList<ExecOperationNode>();
            chidren_.add((ExecOperationNode) settable);
            chidren_.add(right_);
            CustList<Argument> arguments_ = new CustList<Argument>();
            arguments_.add(getArgument(_nodes,(ExecOperationNode) settable));
            arguments_.add(rightArg_);
            CustList<Argument> firstArgs_ = ExecInvokingOperation.listArguments(chidren_, -1, EMPTY_STRING, arguments_, _conf);
            String classNameFound_ = classMethodId.getClassName();
            MethodId id_ = classMethodId.getConstraints();
            _conf.getContextEl().setCallMethod(new CustomFoundMethod(Argument.createVoid(), classNameFound_, id_, firstArgs_));
            return Argument.createVoid();
        }
        Argument arg_ = settable.calculateCompoundSetting(_nodes, _conf, oper, rightArg_);
        setSimpleArgument(arg_, _conf, _nodes);
        return arg_;
    }

    @Override
    public Argument endCalculate(ContextEl _conf, IdMap<ExecOperationNode, ArgumentsPair> _nodes, Argument _right) {
        Argument arg_ = settable.endCalculate(_conf, _nodes, _right);
        setSimpleArgument(arg_, _conf, _nodes);
        return arg_;
    }

}
