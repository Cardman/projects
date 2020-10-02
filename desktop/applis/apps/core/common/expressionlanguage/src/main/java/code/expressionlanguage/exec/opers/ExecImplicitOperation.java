package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.DefaultExiting;
import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecExplicitContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.util.CustList;
import code.util.IdMap;

public final class ExecImplicitOperation extends ExecAbstractUnaryOperation {
    private ExecExplicitContent explicitContent;
    private ExecNamedFunctionBlock named;
    private ExecRootBlock rootBlock;
    public ExecImplicitOperation(ExecNamedFunctionBlock _named, ExecRootBlock _rootBlock, ExecOperationContent _opCont, ExecExplicitContent _explicitContent) {
        super(_opCont);
        explicitContent = _explicitContent;
        named = _named;
        rootBlock = _rootBlock;
    }

    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf) {
        setRelativeOffsetPossibleLastPage(getIndexInEl()+ explicitContent.getOffset(), _conf);
        CustList<Argument> arguments_ = getArguments(_nodes, this);
        Argument argres_ =  ExecExplicitOperation.prepare(_conf.getExiting(),rootBlock,false,named,arguments_, explicitContent.getClassName(), explicitContent.getClassNameOwner(),_conf.getLastPage(),_conf);
        setSimpleArgument(argres_, _conf, _nodes);
    }

}
