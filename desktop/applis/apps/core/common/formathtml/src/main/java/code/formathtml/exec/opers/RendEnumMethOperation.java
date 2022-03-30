package code.formathtml.exec.opers;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.opers.ExecInvokingOperation;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecArrContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.fwd.opers.ExecStaticFctCommonContent;
import code.formathtml.exec.RendStackCall;
import code.util.IdMap;
import code.util.core.StringUtil;

public final class RendEnumMethOperation extends RendSettableCallFctOperation implements RendCalculableOperation {

    private final ExecStaticFctCommonContent staticFctContent;

    private final ExecRootBlock type;

    public RendEnumMethOperation(ExecOperationContent _content, boolean _intermediateDottedOperation, ExecStaticFctCommonContent _staticFctContent, ExecArrContent _arrContent, ExecRootBlock _type) {
        super(_content, _intermediateDottedOperation, _arrContent);
        staticFctContent = _staticFctContent;
        type = _type;
    }

    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, ContextEl _context, RendStackCall _rendStack) {
        int off_ = StringUtil.getFirstPrintableCharIndex(staticFctContent.getMethodName());
        setRelOffsetPossibleLastPage(off_, _rendStack);
        String lastType_ = staticFctContent.getLastType();
        int naturalVararg_ = staticFctContent.getNaturalVararg();
        ArgumentWrapper argres_ = RendDynOperationNode.processCall(ExecInvokingOperation.processEnums(_context.getExiting(), _context, ExecInvokingOperation.fectchArgs(lastType_, naturalVararg_, null, _context, _rendStack.getStackCall(), buildInfos(_nodes)), _rendStack.getStackCall(), type), _context, _rendStack);
        setSimpleArgument(argres_, _nodes, _context, _rendStack);
    }
}
