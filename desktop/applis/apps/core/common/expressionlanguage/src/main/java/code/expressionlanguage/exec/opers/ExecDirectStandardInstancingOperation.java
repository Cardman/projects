package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecInstancingDirContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.util.IdMap;
import code.util.core.StringUtil;

public final class ExecDirectStandardInstancingOperation extends
        ExecInvokingOperation {

    private final ExecInstancingDirContent instancingCommonContent;

    public ExecDirectStandardInstancingOperation(ExecOperationContent _opCont, boolean _intermediateDottedOperation, ExecInstancingDirContent _instancingCommonContent) {
        super(_opCont, _intermediateDottedOperation);
        instancingCommonContent = _instancingCommonContent;
    }

    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf, StackCall _stack) {
        int off_ = StringUtil.getFirstPrintableCharIndex(instancingCommonContent.getMethodName());
        setRelOffsetPossibleLastPage(off_, _stack);
        Argument res_ = instancePrepareStd(_conf, instancingCommonContent.getConstructor(), instancingCommonContent.getConstId(), fectchArgs(instancingCommonContent.getLastType(), instancingCommonContent.getNaturalVararg(),null,_conf,_stack, buildInfos(_nodes)), _stack);
        setSimpleArgument(res_, _conf, _nodes, _stack);
    }

}
