package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.inherits.ParamCheckerUtil;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecInstancingAnnotContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.util.CustList;
import code.util.IdMap;
import code.util.core.StringUtil;

public final class ExecAnnotationInstanceArobaseOperation extends ExecInvokingOperation {

    private final ExecInstancingAnnotContent instancingAnnotContent;

    public ExecAnnotationInstanceArobaseOperation(
            ExecOperationContent _opCont, boolean _intermediateDottedOperation, ExecInstancingAnnotContent _instancingAnnotContent) {
        super(_opCont, _intermediateDottedOperation);
        instancingAnnotContent = _instancingAnnotContent;
    }

    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf, StackCall _stack) {
        CustList<Argument> arguments_ = getArguments(_nodes, this);
        int off_ = StringUtil.getFirstPrintableCharIndex(instancingAnnotContent.getMethodName());
        setRelOffsetPossibleLastPage(off_, _stack);
        ParamCheckerUtil.redirectAnnotation(_conf, _stack, arguments_, instancingAnnotContent);
        Argument res_ = Argument.createVoid();
        setSimpleArgument(res_, _conf, _nodes, _stack);
    }

}
