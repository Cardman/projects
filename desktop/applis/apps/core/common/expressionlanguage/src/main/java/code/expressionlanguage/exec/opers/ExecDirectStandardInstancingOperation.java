package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.opers.StandardInstancingOperation;
import code.expressionlanguage.exec.util.ArgumentList;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.functionid.ConstructorId;
import code.util.CustList;
import code.util.IdMap;
import code.util.StringList;

public final class ExecDirectStandardInstancingOperation extends
        ExecInvokingOperation {

    private String methodName;

    private ConstructorId constId;

    private String className;

    private int naturalVararg;

    private String lastType;
    public ExecDirectStandardInstancingOperation(StandardInstancingOperation _s) {
        super(_s);
        methodName = _s.getMethodName();
        constId = _s.getConstId();
        className = _s.getClassName();
        naturalVararg = _s.getNaturalVararg();
        lastType = _s.getLastType();
    }

    @Override
    public void calculate(IdMap<ExecOperationNode,ArgumentsPair> _nodes, ContextEl _conf) {
        Argument res_ = getArgument(_nodes, _conf);
        setSimpleArgument(res_, _conf, _nodes);
    }
    Argument getArgument(IdMap<ExecOperationNode, ArgumentsPair> _nodes,
                         ContextEl _conf) {
        int off_ = StringList.getFirstPrintableCharIndex(methodName);
        setRelativeOffsetPossibleLastPage(getIndexInEl()+off_, _conf);
        CustList<Argument> firstArgs_ = getArgs(_nodes);
        return instancePrepareStd(_conf, className, constId, firstArgs_);
    }

    private CustList<Argument> getArgs(IdMap<ExecOperationNode, ArgumentsPair> _nodes) {
        return fectchArgs(_nodes,lastType,naturalVararg);
    }

}
