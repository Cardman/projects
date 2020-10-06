package code.formathtml.exec.opers;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.exec.opers.ExecExplicitOperation;
import code.expressionlanguage.fwd.opers.ExecExplicitContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.formathtml.Configuration;
import code.formathtml.util.BeanLgNames;
import code.util.CustList;
import code.util.IdMap;

public final class RendExplicitOperation extends RendAbstractUnaryOperation implements RendCallable {

    private ExecExplicitContent explicitContent;
    private ExecNamedFunctionBlock named;
    private ExecRootBlock rootBlock;
    public RendExplicitOperation(ExecNamedFunctionBlock _named, ExecRootBlock _rootBlock, ExecOperationContent _content, ExecExplicitContent _explicitContent) {
        super(_content);
        explicitContent = _explicitContent;
        named = _named;
        rootBlock = _rootBlock;
    }

    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf, BeanLgNames _advStandards, ContextEl _context) {
        setRelativeOffsetPossibleLastPage(getIndexInEl()+ explicitContent.getOffset(), _conf);
        Argument argres_ = processCall(this, this, Argument.createVoid(),_nodes, _conf, null, _advStandards, _context);
        setSimpleArgument(argres_,_conf,_nodes, _context);
    }

    @Override
    public Argument getArgument(Argument _previous, IdMap<RendDynOperationNode, ArgumentsPair> _all, Configuration _conf, Argument _right, BeanLgNames _advStandards, ContextEl _context) {
        CustList<RendDynOperationNode> list_ = getChildrenNodes();
        CustList<Argument> first_ = RendInvokingOperation.listNamedArguments(_all, list_).getArguments();
        return prepare(_context.getExiting(),rootBlock,false,named,first_, explicitContent.getClassName(), explicitContent.getClassNameOwner(),_context);
    }
    public static Argument prepare(AbstractExiting _exit, ExecRootBlock _rootBlock, boolean _direct, ExecNamedFunctionBlock _castOpId, CustList<Argument> _arguments, String _className,
                                   String _classNameOwner, ContextEl _conf) {
        if (ExecExplicitOperation.direct(_direct,_castOpId,_className)) {
            return ExecExplicitOperation.getArgument(_arguments, _className, _conf);
        }
        ExecExplicitOperation.checkCustomOper(_exit, _rootBlock, _castOpId, _arguments, _classNameOwner, _conf,null);
        return Argument.createVoid();
    }

}
