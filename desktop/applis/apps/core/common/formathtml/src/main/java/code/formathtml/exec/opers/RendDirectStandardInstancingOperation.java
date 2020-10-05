package code.formathtml.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.opers.ExecInvokingOperation;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecInstancingCommonContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.formathtml.Configuration;
import code.formathtml.exec.RendArgumentList;
import code.formathtml.util.BeanLgNames;
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
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf, BeanLgNames _advStandards, ContextEl _context) {
        Argument argres_ = getArgument(_nodes, _conf, _context);
        setSimpleArgument(argres_,_conf,_nodes, _context);
    }
    Argument getArgument(IdMap<RendDynOperationNode, ArgumentsPair> _nodes,
                         Configuration _conf, ContextEl _context) {
        CustList<RendDynOperationNode> chidren_ = getChildrenNodes();
        int off_ = StringList.getFirstPrintableCharIndex(instancingCommonContent.getMethodName());
        setRelativeOffsetPossibleLastPage(getIndexInEl()+off_, _conf);
        RendArgumentList args_ = RendInvokingOperation.listNamedArguments(_nodes, chidren_);
        CustList<Argument> first_ = args_.getArguments();
        CustList<RendDynOperationNode> filter_ = args_.getFilter();
        CustList<Argument> firstArgs_ = listArguments(filter_, instancingCommonContent.getNaturalVararg(), instancingCommonContent.getLastType(), first_);
        return ExecInvokingOperation.instancePrepareStd(_context, instancingCommonContent.getClassName(), instancingCommonContent.getConstId(), firstArgs_);
    }
}
