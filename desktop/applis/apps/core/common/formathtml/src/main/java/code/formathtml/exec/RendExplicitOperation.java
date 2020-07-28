package code.formathtml.exec;

import code.expressionlanguage.Argument;
import code.expressionlanguage.analyze.opers.ChoiceFctOperation;
import code.expressionlanguage.analyze.opers.FctOperation;
import code.expressionlanguage.analyze.opers.SuperFctOperation;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.analyze.opers.ExplicitOperation;
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
    private MethodId castOpId;
    public RendExplicitOperation(ExplicitOperation _a) {
        super(_a);
        className = _a.getClassName();
        classNameOwner = _a.getClassNameOwner();
        offset = _a.getOffset();
        castOpId = _a.getCastOpId();
    }
    public RendExplicitOperation(FctOperation _a) {
        super(_a);
        className = _a.getClassMethodId().getClassName();
        classNameOwner = _a.getClassMethodId().getClassName();
        offset = StringList.getFirstPrintableCharIndex(_a.getMethodName());
        castOpId = _a.getClassMethodId().getConstraints();
    }
    public RendExplicitOperation(SuperFctOperation _a) {
        super(_a);
        className = _a.getClassMethodId().getClassName();
        classNameOwner = _a.getClassMethodId().getClassName();
        offset = StringList.getFirstPrintableCharIndex(_a.getMethodName());
        castOpId = _a.getClassMethodId().getConstraints();
    }
    public RendExplicitOperation(ChoiceFctOperation _a) {
        super(_a);
        className = _a.getClassMethodId().getClassName();
        classNameOwner = _a.getClassMethodId().getClassName();
        offset = StringList.getFirstPrintableCharIndex(_a.getMethodName());
        castOpId = _a.getClassMethodId().getConstraints();
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
