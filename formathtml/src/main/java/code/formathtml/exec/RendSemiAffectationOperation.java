package code.formathtml.exec;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.methods.ProcessMethod;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.expressionlanguage.opers.SemiAffectationOperation;
import code.expressionlanguage.opers.util.ClassMethodId;
import code.expressionlanguage.opers.util.MethodId;
import code.formathtml.Configuration;
import code.util.CustList;
import code.util.IdMap;

public final class RendSemiAffectationOperation extends RendAbstractUnaryOperation {
    private RendSettableElResult settable;
    private boolean post;
    private String oper;
    private ClassMethodId classMethodId;

    public RendSemiAffectationOperation(SemiAffectationOperation _s) {
        super(_s);
        post = _s.isPost();
        oper = _s.getOper();
        classMethodId = _s.getClassMethodId();
    }

    public void setup() {
        settable = RendAffectationOperation.tryGetSettable(this);
    }
    @Override
    public void calculate(ExecutableCode _conf) {
        if (classMethodId != null) {
            CustList<RendDynOperationNode> chidren_ = new CustList<RendDynOperationNode>();
            chidren_.add((RendDynOperationNode) settable);
            CustList<Argument> arguments_ = new CustList<Argument>();
            Argument stored_ = ((RendDynOperationNode) settable).getArgument();
            arguments_.add(stored_);
            CustList<Argument> firstArgs_ = RendInvokingOperation.listArguments(chidren_, -1, EMPTY_STRING, arguments_, _conf);
            String classNameFound_ = classMethodId.getClassName();
            MethodId id_ = classMethodId.getConstraints();
            Argument res_;
            res_ = ProcessMethod.calculateArgument(Argument.createVoid(), classNameFound_, id_, firstArgs_, _conf.getContextEl(),null);
            settable.endCalculate(_conf, post, stored_, res_);
            res_ = getPrePost(post,stored_,res_);
            setSimpleArgument(res_, _conf);
            return;
        }
        Argument stored_ = ((RendDynOperationNode) settable).getArgument();
        settable.calculateSemiSetting(_conf, oper, post);
        RendDynOperationNode op_ = (RendDynOperationNode)settable;
        Argument res_ = op_.getArgument();
        res_ = getPrePost(post,stored_,res_);
        setSimpleArgument(res_, _conf);
    }

    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf) {
        if (classMethodId != null) {
            CustList<RendDynOperationNode> chidren_ = new CustList<RendDynOperationNode>();
            chidren_.add((RendDynOperationNode) settable);
            CustList<Argument> arguments_ = new CustList<Argument>();
            Argument stored_ = getArgument(_nodes,(RendDynOperationNode) settable);
            arguments_.add(stored_);
            CustList<Argument> firstArgs_ = RendInvokingOperation.listArguments(chidren_, -1, EMPTY_STRING, arguments_, _conf);
            String classNameFound_ = classMethodId.getClassName();
            MethodId id_ = classMethodId.getConstraints();
            Argument res_;
            res_ = ProcessMethod.calculateArgument(Argument.createVoid(), classNameFound_, id_, firstArgs_, _conf.getContextEl(),null);
            settable.endCalculate(_nodes,_conf, post, stored_, res_);
            res_ = getPrePost(post,stored_,res_);
            setSimpleArgument(res_, _conf,_nodes);
            return;
        }
        Argument stored_ = getArgument(_nodes,(RendDynOperationNode) settable);
        settable.calculateSemiSetting(_nodes,_conf, oper, post);
        RendDynOperationNode op_ = (RendDynOperationNode)settable;
        Argument res_ = getArgument(_nodes,op_);
        res_ = getPrePost(post,stored_,res_);
        setSimpleArgument(res_, _conf,_nodes);
    }

    static Argument getPrePost(boolean _post, Argument _stored, Argument _right) {
        Argument a_ = _right;
        if (_post) {
            a_ = _stored;
        }
        return a_;
    }
}
