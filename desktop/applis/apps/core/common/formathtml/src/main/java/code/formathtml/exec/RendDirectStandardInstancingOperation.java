package code.formathtml.exec;

import code.expressionlanguage.Argument;
import code.expressionlanguage.analyze.opers.StandardInstancingOperation;
import code.expressionlanguage.exec.opers.ExecInvokingOperation;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.functionid.ConstructorId;
import code.formathtml.Configuration;
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
        CustList<RendDynOperationNode> chidren_ = getChildrenNodes();
        CustList<Argument> arguments_ = new CustList<Argument>();
        for (RendDynOperationNode o: filterInvoking(chidren_)) {
            arguments_.add(getArgument(_nodes,o));
        }
        Argument argres_ = getArgument(arguments_, _conf);
        setSimpleArgument(argres_,_conf,_nodes);
    }
    Argument getArgument(CustList<Argument> _arguments,
                         Configuration _conf) {
        CustList<RendDynOperationNode> chidren_ = getChildrenNodes();
        CustList<RendDynOperationNode> filter_ = filterInvoking(chidren_);
        int off_ = StringList.getFirstPrintableCharIndex(methodName);
        setRelativeOffsetPossibleLastPage(getIndexInEl()+off_, _conf);
        CustList<Argument> firstArgs_ = listArguments(filter_, naturalVararg, lastType, _arguments);
        return ExecInvokingOperation.instancePrepareStd(_conf.getContext(), className, constId, firstArgs_);
    }
}
