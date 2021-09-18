package code.formathtml.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.opers.ExecInvokingOperation;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecInstancingDirContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.formathtml.exec.RendStackCall;
import code.formathtml.util.BeanLgNames;
import code.util.IdMap;
import code.util.core.StringUtil;

public final class RendDirectStandardInstancingOperation extends RendInvokingOperation implements RendCalculableOperation {

    private final ExecInstancingDirContent instancingCommonContent;

    public RendDirectStandardInstancingOperation(ExecOperationContent _content, boolean _intermediateDottedOperation, ExecInstancingDirContent _instancingCommonContent) {
        super(_content, _intermediateDottedOperation);
        instancingCommonContent = _instancingCommonContent;
    }
    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, BeanLgNames _advStandards, ContextEl _context, RendStackCall _rendStack) {
        int off_ = StringUtil.getFirstPrintableCharIndex(instancingCommonContent.getMethodName());
        setRelOffsetPossibleLastPage(off_, _rendStack);
        Argument argres_ = ExecInvokingOperation.instancePrepareStd(_context, instancingCommonContent.getStandardType(), instancingCommonContent.getConstructor(), instancingCommonContent.getConstId(), ExecInvokingOperation.fectchArgs(instancingCommonContent.getLastType(), instancingCommonContent.getNaturalVararg(), null, _context, _rendStack.getStackCall(), buildInfos(_nodes)), _rendStack.getStackCall());
        setSimpleArgument(argres_, _nodes, _context, _rendStack);
    }
}
