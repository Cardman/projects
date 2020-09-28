package code.formathtml.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.opers.ExecInvokingOperation;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.fwd.opers.ExecStaticFctContent;
import code.formathtml.Configuration;
import code.formathtml.exec.AdvancedExiting;
import code.util.CustList;
import code.util.IdMap;
import code.util.StringList;

public final class RendStaticFctOperation extends RendInvokingOperation implements RendCalculableOperation,RendCallable {

    private ExecStaticFctContent staticFctContent;

    private ExecNamedFunctionBlock named;
    private ExecRootBlock rootBlock;
    public RendStaticFctOperation(ExecNamedFunctionBlock _named, ExecRootBlock _rootBlock, ExecOperationContent _content, boolean _intermediateDottedOperation, ExecStaticFctContent _staticFctContent) {
        super(_content, _intermediateDottedOperation);
        staticFctContent = _staticFctContent;
        named = _named;
        rootBlock = _rootBlock;
    }

    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf) {
        Argument previous_ = getPreviousArg(this,_nodes,_conf);
        Argument argres_ = processCall(this, this, previous_,_nodes, _conf, null);
        setSimpleArgument(argres_,_conf,_nodes);
    }

    @Override
    public Argument getArgument(Argument _previous, IdMap<RendDynOperationNode, ArgumentsPair> _all, Configuration _conf, Argument _right) {
        return getArgument(_all,_conf);
    }
    Argument getArgument(IdMap<RendDynOperationNode, ArgumentsPair> _all, Configuration _conf) {
        CustList<RendDynOperationNode> chidren_ = getChildrenNodes();
        int off_ = StringList.getFirstPrintableCharIndex(staticFctContent.getMethodName());
        setRelativeOffsetPossibleLastPage(getIndexInEl()+off_, _conf);
        CustList<Argument> firstArgs_;
        String lastType_ = staticFctContent.getLastType();
        int naturalVararg_ = staticFctContent.getNaturalVararg();
        String classNameFound_;
        Argument prev_ = new Argument();
        classNameFound_ = staticFctContent.getClassName();
        CustList<Argument> first_ = listNamedArguments(_all, chidren_).getArguments();
        firstArgs_ = listArguments(chidren_, naturalVararg_, lastType_, first_);
        if (AdvancedExiting.hasToExit(classNameFound_, _conf.getContext())) {
            return Argument.createVoid();
        }
        return ExecInvokingOperation.callPrepare(new AdvancedExiting(_conf),_conf.getContext(), classNameFound_,rootBlock, prev_, firstArgs_, null,named, staticFctContent.getKind(), "");
    }
}
