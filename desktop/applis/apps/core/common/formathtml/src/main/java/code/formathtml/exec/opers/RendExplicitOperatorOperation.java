package code.formathtml.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.exec.opers.ExecInvokingOperation;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.fwd.opers.ExecArrContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.fwd.opers.ExecStaticFctContent;
import code.formathtml.Configuration;
import code.formathtml.util.BeanLgNames;
import code.util.IdMap;

public final class RendExplicitOperatorOperation extends RendSettableCallFctOperation implements RendCalculableOperation {

    private final ExecTypeFunction pair;
    private ExecStaticFctContent staticFctContent;
    private int offsetOper;
    public RendExplicitOperatorOperation(ExecOperationContent _content, boolean _intermediateDottedOperation, ExecStaticFctContent _staticFctContent, ExecNamedFunctionBlock _named, ExecRootBlock _rootBlock, int _offsetOper, ExecArrContent _arrContent) {
        super(_content, _intermediateDottedOperation, _arrContent);
        staticFctContent = _staticFctContent;
        pair = new ExecTypeFunction(_rootBlock,_named);
        offsetOper = _offsetOper;
    }

    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf, BeanLgNames _advStandards, ContextEl _context) {
        setRelativeOffsetPossibleLastPage(getIndexInEl()+offsetOper, _conf);
        ArgumentWrapper argres_ = RendDynOperationNode.processCall(getArgument(_nodes, _context), _context);
        setSimpleArgument(argres_,_conf,_nodes, _context);
    }

    private Argument getArgument(IdMap<RendDynOperationNode, ArgumentsPair> _all, ContextEl _context) {
        ExecInvokingOperation.checkParametersOperatorsFormatted(_context.getExiting(),_context, pair, fectchArgs(_all,staticFctContent.getLastType(),staticFctContent.getNaturalVararg()), staticFctContent.getClassName(), staticFctContent.getKind());
        return Argument.createVoid();
    }
}
