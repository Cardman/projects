package code.formathtml.exec;

import code.expressionlanguage.Argument;
import code.expressionlanguage.analyze.opers.*;
import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.exec.opers.ExecExplicitOperation;
import code.expressionlanguage.functionid.MethodId;
import code.formathtml.Configuration;
import code.formathtml.util.AdvancedExiting;
import code.util.CustList;
import code.util.IdMap;
import code.util.StringList;

public final class RendExplicitOperation extends RendAbstractUnaryOperation implements RendCallable {
    private String className;
    private String classNameOwner;
    private int offset;
    private ExecNamedFunctionBlock named;
    private ExecRootBlock rootBlock;
    public RendExplicitOperation(ExplicitOperation _a, ExecNamedFunctionBlock _named, ExecRootBlock _rootBlock) {
        super(_a);
        className = _a.getExplicitContent().getClassName();
        classNameOwner = _a.getExplicitContent().getClassNameOwner();
        offset = _a.getExplicitContent().getOffset();
        named = _named;
        rootBlock = _rootBlock;
    }

    public RendExplicitOperation(InvokingOperation _inv, AbstractCallFctOperation _a, ExecNamedFunctionBlock _named, ExecRootBlock _rootBlock) {
        super(_inv);
        className = _a.getClassMethodId().getClassName();
        classNameOwner = _a.getClassMethodId().getClassName();
        offset = StringList.getFirstPrintableCharIndex(_a.getCallFctContent().getMethodName());
        named = _named;
        rootBlock = _rootBlock;
    }
    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf) {
        setRelativeOffsetPossibleLastPage(getIndexInEl()+offset, _conf);
        Argument argres_ = processCall(this, this, Argument.createVoid(),_nodes, _conf, null);
        setSimpleArgument(argres_,_conf,_nodes);
    }

    @Override
    public Argument getArgument(Argument _previous, IdMap<RendDynOperationNode, ArgumentsPair> _all, Configuration _conf, Argument _right) {
        CustList<RendDynOperationNode> list_ = getChildrenNodes();
        CustList<Argument> first_ = RendInvokingOperation.listNamedArguments(_all, list_).getArguments();
        return ExecExplicitOperation.prepare(new AdvancedExiting(_conf),rootBlock,false,named,first_,className,classNameOwner,_conf.getPageEl(),_conf.getContext());
    }
}
