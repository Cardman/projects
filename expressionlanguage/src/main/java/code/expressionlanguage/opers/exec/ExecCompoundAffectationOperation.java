package code.expressionlanguage.opers.exec;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.expressionlanguage.methods.util.TwoStepsArgumentsPair;
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

    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes,
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
            MethodId id_ = classMethodId.getConstraints();
            ExecInvokingOperation.checkParameters(_conf, "", id_, null, firstArgs_, 0,false,false,null,null);
            return;
        }
        Argument arg_ = settable.calculateCompoundSetting(_nodes, _conf, oper, rightArg_);
        setSimpleArgument(arg_, _conf, _nodes);
    }

    @Override
    public void endCalculate(ContextEl _conf, IdMap<ExecOperationNode, ArgumentsPair> _nodes, Argument _right) {
        ArgumentsPair pair_ = getArgumentPair(_nodes,this);
        if (pair_ instanceof TwoStepsArgumentsPair) {
            TwoStepsArgumentsPair s_ = (TwoStepsArgumentsPair) pair_;
            if (s_.isCalledIndexer()) {
                setSimpleArgument(_right, _conf, _nodes);
                return;
            }
            s_.setCalledIndexer(true);
        }
        Argument arg_ = settable.endCalculate(_conf, _nodes, _right);
        setSimpleArgument(arg_, _conf, _nodes);
    }

    @Override
    public ExecSettableElResult getSettable() {
        return settable;
    }
}
