package code.formathtml.exec;

import code.expressionlanguage.Argument;
import code.expressionlanguage.analyze.opers.StandardInstancingOperation;
import code.expressionlanguage.exec.opers.ExecInvokingOperation;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.functionid.ConstructorId;
import code.formathtml.Configuration;
import code.formathtml.util.RendArgumentList;
import code.util.CustList;
import code.util.IdMap;
import code.util.StringList;

public final class RendDirectStandardInstancingOperation extends RendInvokingOperation implements RendCalculableOperation {

    private String methodName;

    private ConstructorId constId;

    private String className;

    private int naturalVararg;

    private String lastType;
    public RendDirectStandardInstancingOperation(StandardInstancingOperation _s) {
        super(_s);
        methodName = _s.getMethodName();
        constId = _s.getConstId();
        className = _s.getClassName();
        naturalVararg = _s.getNaturalVararg();
        lastType = _s.getLastType();
    }
    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf) {
        Argument argres_ = getArgument(_nodes, _conf);
        setSimpleArgument(argres_,_conf,_nodes);
    }
    Argument getArgument(IdMap<RendDynOperationNode, ArgumentsPair> _nodes,
                         Configuration _conf) {
        CustList<RendDynOperationNode> chidren_ = getChildrenNodes();
        int off_ = StringList.getFirstPrintableCharIndex(methodName);
        setRelativeOffsetPossibleLastPage(getIndexInEl()+off_, _conf);
        RendArgumentList args_ = RendInvokingOperation.listNamedArguments(_nodes, chidren_);
        CustList<Argument> first_ = args_.getArguments();
        CustList<RendDynOperationNode> filter_ = args_.getFilter();
        CustList<Argument> firstArgs_ = listArguments(filter_, naturalVararg, lastType, first_);
        return ExecInvokingOperation.instancePrepareStd(_conf.getContext(), className, constId, firstArgs_);
    }
}
