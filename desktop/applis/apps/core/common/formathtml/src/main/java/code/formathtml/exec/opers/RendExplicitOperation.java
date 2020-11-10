package code.formathtml.exec.opers;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.exec.opers.ExecExplicitOperation;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.fwd.opers.ExecExplicitContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.formathtml.Configuration;
import code.formathtml.util.BeanLgNames;
import code.util.CustList;
import code.util.IdMap;

public final class RendExplicitOperation extends RendAbstractUnaryOperation {

    private ExecExplicitContent explicitContent;
    private ExecTypeFunction pair;
    public RendExplicitOperation(ExecTypeFunction _pair, ExecOperationContent _content, ExecExplicitContent _explicitContent) {
        super(_content);
        explicitContent = _explicitContent;
        pair = _pair;
    }

    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf, BeanLgNames _advStandards, ContextEl _context) {
        setRelativeOffsetPossibleLastPage(getIndexInEl()+ explicitContent.getOffset(), _conf);
        Argument argres_ = RendDynOperationNode.processCall(getArgument(_nodes, _context), _context);
        setSimpleArgument(argres_,_conf,_nodes, _context);
    }

    private Argument getArgument(IdMap<RendDynOperationNode, ArgumentsPair> _all, ContextEl _context) {
        CustList<RendDynOperationNode> list_ = getChildrenNodes();
        CustList<Argument> first_ = RendInvokingOperation.listNamedArguments(_all, list_).getArguments();
        return prepare(_context.getExiting(),pair,false,first_, explicitContent.getClassName(), explicitContent.getClassNameOwner(),_context);
    }

    public static Argument prepare(AbstractExiting _exit, ExecTypeFunction _rootBlock, boolean _direct, CustList<Argument> _arguments, String _className,
                                   String _classNameOwner, ContextEl _conf) {
        if (ExecExplicitOperation.direct(_direct,_rootBlock,_className)) {
            return ExecExplicitOperation.getArgument(_arguments, _className, _conf);
        }
        ExecExplicitOperation.checkCustomOper(_exit, _rootBlock, _arguments, _classNameOwner, _conf,null);
        return Argument.createVoid();
    }

}
