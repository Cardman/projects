package code.formathtml.exec;

import code.expressionlanguage.Argument;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.opers.EnumValueOfOperation;
import code.expressionlanguage.exec.opers.ExecInvokingOperation;
import code.formathtml.Configuration;
import code.formathtml.util.AdvancedExiting;
import code.util.CustList;
import code.util.IdMap;

public final class RendEnumValueOfOperation extends RendAbstractUnaryOperation implements RendCallable {

    private String className;
    private int argOffset;

    public RendEnumValueOfOperation(EnumValueOfOperation _e) {
        super(_e);
        className = _e.getClassName();
        argOffset = _e.getArgOffset();
    }


    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf) {
        CustList<Argument> arguments_ = getArguments(_nodes,this);
        Argument argres_ = processCall(this, this, Argument.createVoid(), arguments_, _conf, null);
        setSimpleArgument(argres_,_conf,_nodes);
    }

    Argument getCommonArgument(Argument _argument, Configuration _conf) {
        setRelativeOffsetPossibleLastPage(getIndexInEl()+argOffset, _conf);
        return ExecInvokingOperation.getEnumValue(new AdvancedExiting(_conf),className, _argument, _conf.getContext());
    }

    @Override
    public Argument getArgument(Argument _previous, CustList<Argument> _arguments, Configuration _conf, Argument _right) {
        return getCommonArgument(_arguments.first(),_conf);
    }
}
