package code.formathtml.exec;

import code.expressionlanguage.Argument;
import code.expressionlanguage.exec.opers.ExecInvokingOperation;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecInstancingCommonContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.formathtml.Configuration;
import code.formathtml.util.RendArgumentList;
import code.util.CustList;
import code.util.IdMap;
import code.util.StringList;

public final class RendDirectStandardInstancingOperation extends RendInvokingOperation implements RendCalculableOperation {

    private ExecInstancingCommonContent instancingCommonContent;

    public RendDirectStandardInstancingOperation(ExecOperationContent _content, boolean _intermediateDottedOperation, ExecInstancingCommonContent _instancingCommonContent) {
        super(_content, _intermediateDottedOperation);
        instancingCommonContent = _instancingCommonContent;
    }
    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf) {
        Argument argres_ = getArgument(_nodes, _conf);
        setSimpleArgument(argres_,_conf,_nodes);
    }
    Argument getArgument(IdMap<RendDynOperationNode, ArgumentsPair> _nodes,
                         Configuration _conf) {
        CustList<RendDynOperationNode> chidren_ = getChildrenNodes();
        int off_ = StringList.getFirstPrintableCharIndex(instancingCommonContent.getMethodName());
        setRelativeOffsetPossibleLastPage(getIndexInEl()+off_, _conf);
        RendArgumentList args_ = RendInvokingOperation.listNamedArguments(_nodes, chidren_);
        CustList<Argument> first_ = args_.getArguments();
        CustList<RendDynOperationNode> filter_ = args_.getFilter();
        CustList<Argument> firstArgs_ = listArguments(filter_, instancingCommonContent.getNaturalVararg(), instancingCommonContent.getLastType(), first_);
        return ExecInvokingOperation.instancePrepareStd(_conf.getContext(), instancingCommonContent.getClassName(), instancingCommonContent.getConstId(), firstArgs_);
    }
}
