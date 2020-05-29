package code.formathtml.exec;

import code.expressionlanguage.Argument;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.expressionlanguage.opers.ExplicitOperatorOperation;
import code.expressionlanguage.opers.exec.ExecExplicitOperatorOperation;
import code.expressionlanguage.opers.exec.ExecInvokingOperation;
import code.expressionlanguage.opers.util.ClassMethodId;
import code.expressionlanguage.opers.util.MethodId;
import code.formathtml.Configuration;
import code.formathtml.util.AdvancedExiting;
import code.util.CustList;
import code.util.IdMap;

public final class RendExplicitOperatorOperation extends RendInvokingOperation implements RendCalculableOperation,RendCallable {

    private String lastType;

    private int naturalVararg;

    private ClassMethodId classMethodId;
    private int offsetOper;
    public RendExplicitOperatorOperation(ExplicitOperatorOperation _fct) {
        super(_fct);
        classMethodId = _fct.getClassMethodId();
        lastType = _fct.getLastType();
        naturalVararg = _fct.getNaturalVararg();
        offsetOper = _fct.getOffsetOper();
    }

    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf) {
        setRelativeOffsetPossibleLastPage(getIndexInEl()+offsetOper, _conf);
        CustList<Argument> arguments_ = getArguments(_nodes, this);
        Argument argres_ = processCall(this, this, _nodes, Argument.createVoid(), arguments_, _conf, null);
        setSimpleArgument(argres_,_conf,_nodes);
    }

    @Override
    public Argument getArgument(Argument _previous, CustList<Argument> _arguments, Configuration _conf, Argument _right) {
        CustList<RendDynOperationNode> chidren_ = getChildrenNodes();
        CustList<Argument> firstArgs_ = listArguments(chidren_, naturalVararg, lastType, _arguments, _conf);
        return ExecExplicitOperatorOperation.prepareExplicitOperator(new AdvancedExiting(_conf),_conf.getContext(),firstArgs_,_previous,classMethodId);
    }
}
