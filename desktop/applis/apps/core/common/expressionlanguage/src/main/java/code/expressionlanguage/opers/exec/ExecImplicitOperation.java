package code.expressionlanguage.opers.exec;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.DefaultExiting;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.expressionlanguage.opers.ImplicitOperation;
import code.expressionlanguage.opers.util.MethodId;
import code.util.CustList;
import code.util.IdMap;

public final class ExecImplicitOperation extends ExecAbstractUnaryOperation {
    private String className;
    private String classNameOwner;
    private int offset;
    private MethodId castOpId;
    public ExecImplicitOperation(ImplicitOperation _a) {
        super(_a);
        className = _a.getClassName();
        classNameOwner = _a.getClassNameOwner();
        offset = _a.getOffset();
        castOpId = _a.getCastOpId();
    }

    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf) {
        setRelativeOffsetPossibleLastPage(getIndexInEl()+offset, _conf);
        CustList<Argument> arguments_ = getArguments(_nodes, this);
        Argument argres_ =  ExecExplicitOperation.prepare(new DefaultExiting(_conf),false,castOpId,arguments_,className,classNameOwner,_conf.getLastPage(),_conf);
        setSimpleArgument(argres_, _conf, _nodes);
    }

}
