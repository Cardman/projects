package code.formathtml.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.exec.opers.ExecInvokingOperation;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.fwd.opers.ExecStaticEltContent;
import code.formathtml.Configuration;
import code.formathtml.util.BeanLgNames;
import code.util.CustList;
import code.util.IdMap;

public final class RendCustNumericOperation extends RendNumericOperation implements RendCallable {

    private ExecStaticEltContent staticEltContent;
    private ExecNamedFunctionBlock named;
    private ExecRootBlock rootBlock;
    public RendCustNumericOperation(ExecNamedFunctionBlock _named, ExecRootBlock _rootBlock, ExecOperationContent _content, int _opOffset, ExecStaticEltContent _staticEltContent) {
        super(_content, _opOffset);
        staticEltContent = _staticEltContent;
        named = _named;
        rootBlock = _rootBlock;
    }

    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf, BeanLgNames _advStandards, ContextEl _context) {
        setRelativeOffsetPossibleLastPage(getIndexInEl()+getOpOffset(), _conf);
        Argument argres_ = processCall(this, this, Argument.createVoid(),_nodes, _conf, null, _advStandards, _context);
        setSimpleArgument(argres_,_conf,_nodes, _context);
    }

    @Override
    public Argument getArgument(Argument _previous, IdMap<RendDynOperationNode, ArgumentsPair> _all, Configuration _conf, Argument _right, BeanLgNames _advStandards, ContextEl _context) {
        CustList<RendDynOperationNode> list_ = getChildrenNodes();
        CustList<Argument> first_ = RendInvokingOperation.listNamedArguments(_all, list_).getArguments();
        ExecInvokingOperation.checkParametersOperators(_context.getExiting(),_context, rootBlock,named, first_, staticEltContent.getClassName());
        return Argument.createVoid();
    }
}
