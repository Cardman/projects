package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.DefaultExiting;
import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.util.CustList;
import code.util.IdMap;

public final class ExecCustNumericOperation extends ExecNumericOperation {

    private String className;
    private MethodAccessKind kind;
    private ExecNamedFunctionBlock named;
    private ExecRootBlock rootBlock;

    public ExecCustNumericOperation(ExecNamedFunctionBlock _named, ExecRootBlock _rootBlock, ExecOperationContent _opCont, int _opOffset, MethodAccessKind _kind, String _type) {
        super(_opCont, _opOffset);
        kind = _kind;
        className = _type;
        named = _named;
        rootBlock = _rootBlock;
    }
    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes,
                                ContextEl _conf) {
        CustList<ExecOperationNode> chidren_ = getChildrenNodes();
        setRelOffsetPossibleLastPage(getOpOffset(), _conf);
        CustList<Argument> arguments_ = getArguments(_nodes, this);
        CustList<Argument> firstArgs_ = ExecInvokingOperation.listArguments(chidren_, -1, EMPTY_STRING, arguments_);
        ExecInvokingOperation.checkParametersOperators(_conf.getExiting(),_conf, rootBlock, named, firstArgs_, className, kind);
    }

}
