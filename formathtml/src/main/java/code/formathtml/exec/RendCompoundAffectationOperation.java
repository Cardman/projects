package code.formathtml.exec;

import code.expressionlanguage.Argument;
import code.expressionlanguage.methods.ProcessMethod;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.expressionlanguage.opers.CompoundAffectationOperation;
import code.expressionlanguage.opers.exec.ExecInvokingOperation;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.opers.util.ClassMethodId;
import code.expressionlanguage.opers.util.MethodId;
import code.expressionlanguage.structs.NullStruct;
import code.formathtml.Configuration;
import code.formathtml.util.AdvancedExiting;
import code.util.CustList;
import code.util.IdMap;

public final class RendCompoundAffectationOperation extends RendMethodOperation implements RendCalculableOperation,RendCallable {

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
        RendDynOperationNode right_ = getChildrenNodes().last();
        Argument rightArg_ = getArgument(_nodes,right_);
        if (classMethodId != null) {
            CustList<RendDynOperationNode> chidren_ = new CustList<RendDynOperationNode>();
            chidren_.add((RendDynOperationNode) settable);
            chidren_.add(right_);
            CustList<Argument> arguments_ = new CustList<Argument>();
            arguments_.add(getArgument(_nodes,(RendDynOperationNode) settable));
            arguments_.add(getArgument(_nodes,right_));
            CustList<Argument> firstArgs_ = RendInvokingOperation.listArguments(chidren_, -1, EMPTY_STRING, arguments_, _conf);
            Argument res_;
            res_ =  processCall(this,this,_nodes,Argument.createVoid(),firstArgs_,_conf, null);
            setSimpleArgument(res_, _conf,_nodes);
            Argument arg_ = settable.endCalculate(_nodes, _conf, res_);
            setSimpleArgument(arg_, _conf,_nodes);
            return;
        }
        Argument arg_ = settable.calculateCompoundSetting(_nodes, _conf, oper, rightArg_);
        setSimpleArgument(arg_, _conf,_nodes);
    }

    public String getOper() {
        return oper;
    }

    @Override
    public Argument getArgument(Argument _previous, CustList<Argument> _arguments, Configuration _conf, Argument _right) {
        ExecInvokingOperation.checkParametersOperators(new AdvancedExiting(_conf),_conf.getContext(),classMethodId,_previous,_arguments);
        return Argument.createVoid();
    }
}
