package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.calls.util.CustomFoundAnnotation;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecInstancingAnnotContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.util.*;
import code.util.core.StringUtil;

public final class ExecAnnotationInstanceArobaseOperation extends ExecInvokingOperation {

    private final ExecInstancingAnnotContent instancingAnnotContent;

    private final ExecRootBlock rootBlock;

    public ExecAnnotationInstanceArobaseOperation(
            ExecRootBlock _rootBlock, ExecOperationContent _opCont, boolean _intermediateDottedOperation, ExecInstancingAnnotContent _instancingAnnotContent) {
        super(_opCont, _intermediateDottedOperation);
        instancingAnnotContent = _instancingAnnotContent;
        rootBlock = _rootBlock;
    }

    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf, StackCall _stack) {
        CustList<Argument> arguments_ = getArguments(_nodes, this);
        int off_ = StringUtil.getFirstPrintableCharIndex(instancingAnnotContent.getMethodName());
        setRelOffsetPossibleLastPage(off_, _stack);
        Argument res_;
        if (!_conf.getExiting().hasToExit(_stack, rootBlock)) {
            _stack.setCallingState(new CustomFoundAnnotation(instancingAnnotContent.getFormattedType(), rootBlock, instancingAnnotContent.getFieldNames(), arguments_));
        }
        res_ = Argument.createVoid();
        setSimpleArgument(res_, _conf, _nodes, _stack);
    }

}
