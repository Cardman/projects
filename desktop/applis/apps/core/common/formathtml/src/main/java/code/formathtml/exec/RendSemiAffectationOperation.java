package code.formathtml.exec;

import code.expressionlanguage.Argument;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.expressionlanguage.opers.SemiAffectationOperation;
import code.expressionlanguage.opers.exec.ExecInvokingOperation;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.opers.util.ClassMethodId;
import code.expressionlanguage.structs.NullStruct;
import code.formathtml.Configuration;
import code.formathtml.util.AdvancedExiting;
import code.util.CustList;
import code.util.IdMap;

public final class RendSemiAffectationOperation extends RendAbstractUnaryOperation implements RendCallable {
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
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf) {
        if (((RendDynOperationNode) settable).getParent() instanceof RendSafeDotOperation) {
            RendDynOperationNode left_ = ((RendDynOperationNode) settable).getParent().getFirstChild();
            Argument leftArg_ = getArgument(_nodes,left_);
            if (leftArg_.isNull()) {
                leftArg_ = new Argument(ClassArgumentMatching.convert(_conf.getPageEl(),getResultClass(),NullStruct.NULL_VALUE,_conf.getContext()));
                setQuickConvertSimpleArgument(leftArg_, _conf, _nodes);
                return;
            }
        }
        if (classMethodId != null) {
            CustList<RendDynOperationNode> chidren_ = new CustList<RendDynOperationNode>();
            chidren_.add((RendDynOperationNode) settable);
            CustList<Argument> arguments_ = new CustList<Argument>();
            Argument stored_ = getArgument(_nodes,(RendDynOperationNode) settable);
            arguments_.add(stored_);
            CustList<Argument> firstArgs_ = RendInvokingOperation.listArguments(chidren_, -1, EMPTY_STRING, arguments_);
            Argument res_;
            res_ =  processCall(this,this, Argument.createVoid(),firstArgs_,_conf, null);
            setSimpleArgument(res_, _conf,_nodes);
            settable.endCalculate(_nodes,_conf, post, stored_, res_);
            res_ = getPrePost(post,stored_,res_);
            setSimpleArgument(res_, _conf,_nodes);
            return;
        }
        Argument stored_ = getArgument(_nodes,(RendDynOperationNode) settable);
        Argument arg_ = settable.calculateSemiSetting(_nodes, _conf, oper, post);
        Argument res_ = arg_;
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

    @Override
    public Argument getArgument(Argument _previous, CustList<Argument> _arguments, Configuration _conf, Argument _right) {
        ExecInvokingOperation.checkParametersOperators(new AdvancedExiting(_conf),_conf.getContext(),classMethodId,_previous,_arguments);
        return Argument.createVoid();
    }
}
