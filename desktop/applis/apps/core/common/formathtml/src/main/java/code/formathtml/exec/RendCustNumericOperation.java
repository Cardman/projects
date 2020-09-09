package code.formathtml.exec;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.analyze.opers.SymbolOperation;
import code.expressionlanguage.exec.opers.ExecInvokingOperation;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.formathtml.Configuration;
import code.formathtml.util.AdvancedExiting;
import code.util.CustList;
import code.util.IdMap;

public final class RendCustNumericOperation extends RendNumericOperation implements RendCallable {

    private MethodAccessKind kind;
    private String className;
    private ExecNamedFunctionBlock named;
    private ExecRootBlock rootBlock;
    public RendCustNumericOperation(SymbolOperation _n, ContextEl _context,OperationNode _op, ExecNamedFunctionBlock _named, ExecRootBlock _rootBlock) {
        super(_n,_op);
        kind = ExecOperationNode.getKind(_n.getClassMethodId());
        className = ExecOperationNode.getType(_context,_n.getClassMethodId());
        named = _named;
        rootBlock = _rootBlock;
    }

    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf) {
        setRelativeOffsetPossibleLastPage(getIndexInEl()+getOpOffset(), _conf);
        Argument argres_ = processCall(this, this, Argument.createVoid(),_nodes, _conf, null);
        setSimpleArgument(argres_,_conf,_nodes);
    }

    @Override
    public Argument getArgument(Argument _previous, IdMap<RendDynOperationNode, ArgumentsPair> _all, Configuration _conf, Argument _right) {
        CustList<RendDynOperationNode> list_ = getChildrenNodes();
        CustList<Argument> first_ = RendInvokingOperation.listNamedArguments(_all, list_).getArguments();
        ExecInvokingOperation.checkParametersOperators(new AdvancedExiting(_conf),_conf.getContext(), rootBlock,named, first_, className, kind);
        return Argument.createVoid();
    }
}
