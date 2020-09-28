package code.formathtml.exec;

import code.expressionlanguage.Argument;
import code.expressionlanguage.exec.ClassCategory;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.exec.opers.ExecInvokingOperation;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.fwd.opers.ExecValuesContent;
import code.formathtml.Configuration;
import code.formathtml.util.AdvancedExiting;
import code.util.CustList;
import code.util.IdMap;

public final class RendEnumValueOfOperation extends RendAbstractUnaryOperation implements RendCallable {

    private ExecValuesContent valuesContent;

    public RendEnumValueOfOperation(ExecOperationContent _content, ExecValuesContent _valuesContent) {
        super(_content);
        valuesContent = _valuesContent;
    }


    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf) {
        Argument argres_ = processCall(this, this, Argument.createVoid(),_nodes, _conf, null);
        setSimpleArgument(argres_,_conf,_nodes);
    }

    Argument getCommonArgument(Argument _argument, Configuration _conf) {
        setRelativeOffsetPossibleLastPage(getIndexInEl()+ valuesContent.getArgOffset(), _conf);
        return ExecInvokingOperation.tryGetEnumValue(new AdvancedExiting(_conf),_conf.getContext(), valuesContent.getRootBlock(), ClassCategory.ENUM,_argument);
    }

    @Override
    public Argument getArgument(Argument _previous, IdMap<RendDynOperationNode, ArgumentsPair> _all, Configuration _conf, Argument _right) {
        CustList<RendDynOperationNode> list_ = getChildrenNodes();
        CustList<Argument> first_ = RendInvokingOperation.listNamedArguments(_all, list_).getArguments();
        return getCommonArgument(first_.first(),_conf);
    }
}
