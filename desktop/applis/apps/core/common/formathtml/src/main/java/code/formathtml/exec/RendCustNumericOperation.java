package code.formathtml.exec;

import code.expressionlanguage.Argument;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.opers.OperationNode;
import code.expressionlanguage.opers.SymbolOperation;
import code.expressionlanguage.exec.opers.ExecInvokingOperation;
import code.expressionlanguage.opers.util.ClassMethodId;
import code.formathtml.Configuration;
import code.formathtml.util.AdvancedExiting;
import code.util.CustList;
import code.util.IdMap;

public final class RendCustNumericOperation extends RendNumericOperation implements RendCallable {

    private ClassMethodId classMethodId;
    public RendCustNumericOperation(SymbolOperation _n, OperationNode _op) {
        super(_n,_op);
        classMethodId = _n.getClassMethodId();
    }

    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf) {
        CustList<RendDynOperationNode> chidren_ = getChildrenNodes();
        setRelativeOffsetPossibleLastPage(getIndexInEl()+getOpOffset(), _conf);
        CustList<Argument> arguments_ = getArguments(_nodes,this);
        CustList<Argument> firstArgs_ = RendInvokingOperation.listArguments(chidren_, -1, EMPTY_STRING, arguments_);
        Argument argres_ = processCall(this, this, Argument.createVoid(), firstArgs_, _conf, null);
        setSimpleArgument(argres_,_conf,_nodes);
    }

    @Override
    public Argument getArgument(Argument _previous, CustList<Argument> _arguments, Configuration _conf, Argument _right) {
        ExecInvokingOperation.checkParametersOperators(new AdvancedExiting(_conf),_conf.getContext(),classMethodId,_previous,_arguments);
        return Argument.createVoid();
    }
}
