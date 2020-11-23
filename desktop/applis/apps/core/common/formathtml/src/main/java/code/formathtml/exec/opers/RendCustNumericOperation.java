package code.formathtml.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.exec.opers.ExecInvokingOperation;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.fwd.opers.ExecStaticEltContent;
import code.formathtml.Configuration;
import code.formathtml.util.BeanLgNames;
import code.util.CustList;
import code.util.IdMap;

public final class RendCustNumericOperation extends RendNumericOperation {

    private final ExecTypeFunction pair;
    private ExecStaticEltContent staticEltContent;
    public RendCustNumericOperation(ExecNamedFunctionBlock _named, ExecRootBlock _rootBlock, ExecOperationContent _content, int _opOffset, ExecStaticEltContent _staticEltContent) {
        super(_content, _opOffset);
        staticEltContent = _staticEltContent;
        pair = new ExecTypeFunction(_rootBlock,_named);
    }

    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf, BeanLgNames _advStandards, ContextEl _context) {
        setRelativeOffsetPossibleLastPage(getIndexInEl()+getOpOffset(), _conf);
        Argument argres_ = RendDynOperationNode.processCall(getArgument(_nodes, _context), _context);
        setSimpleArgument(argres_,_conf,_nodes, _context);
    }

    private Argument getArgument(IdMap<RendDynOperationNode, ArgumentsPair> _all, ContextEl _context) {
        CustList<Argument> first_ = getArguments(_all,this);
        ExecInvokingOperation.checkParametersOperatorsFormatted(_context.getExiting(),_context, pair, first_, staticEltContent.getClassName(), staticEltContent.getKind());
        return Argument.createVoid();
    }
}
