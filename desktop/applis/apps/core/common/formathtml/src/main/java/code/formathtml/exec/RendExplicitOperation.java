package code.formathtml.exec;

import code.expressionlanguage.Argument;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.opers.ExplicitOperation;
import code.expressionlanguage.exec.opers.ExecExplicitOperation;
import code.expressionlanguage.opers.util.MethodId;
import code.formathtml.Configuration;
import code.formathtml.util.AdvancedExiting;
import code.util.CustList;
import code.util.IdMap;

public final class RendExplicitOperation extends RendAbstractUnaryOperation implements RendCallable {
    private String className;
    private String classNameOwner;
    private int offset;
    private MethodId castOpId;
    public RendExplicitOperation(ExplicitOperation _a) {
        super(_a);
        className = _a.getClassName();
        classNameOwner = _a.getClassNameOwner();
        offset = _a.getOffset();
        castOpId = _a.getCastOpId();
    }

    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf) {
        setRelativeOffsetPossibleLastPage(getIndexInEl()+offset, _conf);
        CustList<Argument> arguments_ = getArguments(_nodes, this);
        Argument argres_ = processCall(this, this, Argument.createVoid(), arguments_, _conf, null);
        setSimpleArgument(argres_,_conf,_nodes);
    }

    @Override
    public Argument getArgument(Argument _previous, CustList<Argument> _arguments, Configuration _conf, Argument _right) {
        return ExecExplicitOperation.prepare(new AdvancedExiting(_conf),false,castOpId,_arguments,className,classNameOwner,_conf.getPageEl(),_conf.getContext());
    }
}
