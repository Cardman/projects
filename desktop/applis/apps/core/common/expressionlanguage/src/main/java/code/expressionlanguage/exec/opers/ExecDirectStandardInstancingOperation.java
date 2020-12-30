package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecInstancingCommonContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.util.CustList;
import code.util.IdMap;
import code.util.core.StringUtil;

public final class ExecDirectStandardInstancingOperation extends
        ExecInvokingOperation {

    private final ExecInstancingCommonContent instancingCommonContent;

    public ExecDirectStandardInstancingOperation(ExecOperationContent _opCont, boolean _intermediateDottedOperation, ExecInstancingCommonContent _instancingCommonContent) {
        super(_opCont, _intermediateDottedOperation);
        instancingCommonContent = _instancingCommonContent;
    }

    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf, StackCall _stack) {
        Argument res_ = getArgument(_nodes, _conf, _stack);
        setSimpleArgument(res_, _conf, _nodes, _stack);
    }
    Argument getArgument(IdMap<ExecOperationNode, ArgumentsPair> _nodes,
                         ContextEl _conf, StackCall _stackCall) {
        int off_ = StringUtil.getFirstPrintableCharIndex(instancingCommonContent.getMethodName());
        setRelOffsetPossibleLastPage(off_, _stackCall);
        return instancePrepareStd(_conf, instancingCommonContent.getClassName(), instancingCommonContent.getConstId(), getArgs(_nodes), _stackCall);
    }

    private CustList<Argument> getArgs(IdMap<ExecOperationNode, ArgumentsPair> _nodes) {
        return fectchArgs(_nodes, instancingCommonContent.getLastType(), instancingCommonContent.getNaturalVararg()).getArguments();
    }

}
