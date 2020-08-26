package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.opers.StandardInstancingOperation;
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
        CustList<ExecOperationNode> chidren_ = getChildrenNodes();
        CustList<Argument> arguments_ = filterInvoking(chidren_, _nodes);
        Argument res_ = getArgument(arguments_, _conf);
        setSimpleArgument(res_, _conf, _nodes);
    }
    Argument getArgument(CustList<Argument> _arguments,
                         ContextEl _conf) {
        CustList<ExecOperationNode> chidren_ = getChildrenNodes();
        CustList<ExecOperationNode> filter_ = filterInvoking(chidren_);
        int off_ = StringList.getFirstPrintableCharIndex(methodName);
        setRelativeOffsetPossibleLastPage(getIndexInEl()+off_, _conf);
        CustList<Argument> firstArgs_ = listArguments(filter_, naturalVararg, lastType, _arguments);
        return instancePrepareStd(_conf, className, constId, firstArgs_);
    }

}
