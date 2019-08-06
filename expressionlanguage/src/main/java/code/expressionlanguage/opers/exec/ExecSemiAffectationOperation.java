package code.expressionlanguage.opers.exec;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.expressionlanguage.methods.util.TwoStepsArgumentsPair;
import code.expressionlanguage.opers.SemiAffectationOperation;
import code.expressionlanguage.opers.util.ClassMethodId;
import code.expressionlanguage.opers.util.MethodId;
import code.util.CustList;
import code.util.IdMap;

public final class ExecSemiAffectationOperation extends ExecAbstractUnaryOperation implements CallExecSimpleOperation {
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
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes,
                          ContextEl _conf) {
        if (classMethodId != null) {
            CustList<ExecOperationNode> chidren_ = new CustList<ExecOperationNode>();
            chidren_.add((ExecOperationNode) settable);
            CustList<Argument> arguments_ = new CustList<Argument>();
            arguments_.add(getArgument(_nodes,(ExecOperationNode) settable));
            CustList<Argument> firstArgs_ = ExecInvokingOperation.listArguments(chidren_, -1, EMPTY_STRING, arguments_, _conf);
            MethodId id_ = classMethodId.getConstraints();
            ExecInvokingOperation.checkParameters(_conf, "", id_, null, firstArgs_, false,false,null,null);
            return;
        }
        Argument arg_ = settable.calculateSemiSetting(_nodes, _conf, oper, post);
        setSimpleArgument(arg_, _conf, _nodes);
    }

    @Override
    public void endCalculate(ContextEl _conf,
                             IdMap<ExecOperationNode, ArgumentsPair> _nodes, Argument _right) {
        Argument stored_ = getArgument(_nodes,(ExecOperationNode) settable);
        ArgumentsPair pair_ = getArgumentPair(_nodes,this);
        if (pair_ instanceof TwoStepsArgumentsPair) {
            TwoStepsArgumentsPair s_ = (TwoStepsArgumentsPair) pair_;
            if (s_.isCalledIndexer()) {
                if (s_.isCalledToString()) {
                    setSimpleArgument(_right, _conf, _nodes);
                    return;
                }
                Argument out_ = ExecSemiAffectationOperation.getPrePost(post, stored_, _right);
                setSimpleArgument(out_, _conf, _nodes);
                return;
            }
            s_.setCalledIndexer(true);
        }
        if (pair_.isConvertToString()) {
            if (pair_.isCalledToString()) {
                setSimpleArgument(_right, _conf, _nodes);
                return;
            }
        }
        Argument arg_ = settable.endCalculate(_conf, _nodes, post, stored_, _right);
        setSimpleArgument(arg_, _conf, _nodes);
    }

    static Argument getPrePost(boolean _post, Argument _stored,Argument _right) {
        Argument a_ = _right;
        if (_post) {
            a_ = _stored;
        }
        return a_;
    }

    @Override
    public ExecSettableElResult getSettable() {
        return settable;
    }

}
