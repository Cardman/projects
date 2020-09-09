package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.DefaultExiting;
import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.analyze.opers.SymbolOperation;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.util.CustList;
import code.util.IdMap;

public final class ExecCustNumericOperation extends ExecNumericOperation {

    private String className;
    private MethodAccessKind kind;
    private ExecNamedFunctionBlock named;
    private ExecRootBlock rootBlock;

    public ExecCustNumericOperation(SymbolOperation _n,ContextEl _context, OperationNode _op, ExecNamedFunctionBlock _named, ExecRootBlock _rootBlock) {
        super(_n,_op);
        kind = getKind(_n.getClassMethodId());
        className = getType(_context,_n.getClassMethodId());
        named = _named;
        rootBlock = _rootBlock;
    }
    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes,
                                ContextEl _conf) {
        CustList<ExecOperationNode> chidren_ = getChildrenNodes();
        setRelativeOffsetPossibleLastPage(getIndexInEl()+getOpOffset(), _conf);
        CustList<Argument> arguments_ = getArguments(_nodes, this);
        CustList<Argument> firstArgs_ = ExecInvokingOperation.listArguments(chidren_, -1, EMPTY_STRING, arguments_);
        ExecInvokingOperation.checkParametersOperators(new DefaultExiting(_conf),_conf, rootBlock, named, firstArgs_, className, kind);
    }

}
