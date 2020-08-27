package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.DefaultExiting;
import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.analyze.opers.ImplicitOperation;
import code.util.CustList;
import code.util.IdMap;

public final class ExecImplicitOperation extends ExecAbstractUnaryOperation {
    private String className;
    private String classNameOwner;
    private int offset;
    private ExecNamedFunctionBlock named;
    private ExecRootBlock rootBlock;
    public ExecImplicitOperation(ImplicitOperation _a, ExecNamedFunctionBlock _named, ExecRootBlock _rootBlock) {
        super(_a);
        className = _a.getClassName();
        classNameOwner = _a.getClassNameOwner();
        offset = _a.getOffset();
        named = _named;
        rootBlock = _rootBlock;
    }

    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf) {
        setRelativeOffsetPossibleLastPage(getIndexInEl()+offset, _conf);
        CustList<Argument> arguments_ = getArguments(_nodes, this);
        Argument argres_ =  ExecExplicitOperation.prepare(new DefaultExiting(_conf),rootBlock,false,named,arguments_,className,classNameOwner,_conf.getLastPage(),_conf);
        setSimpleArgument(argres_, _conf, _nodes);
    }

}
