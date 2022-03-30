package code.formathtml.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.opers.ExecStdFctOperation;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecArrContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.fwd.opers.ExecStdFctContent;
import code.formathtml.exec.RendStackCall;
import code.util.IdMap;
import code.util.core.StringUtil;

public final class RendStdFctOperation extends RendSettableCallFctOperation implements RendCalculableOperation {

    private final ExecStdFctContent stdFctContent;

    public RendStdFctOperation(ExecOperationContent _content, boolean _intermediateDottedOperation, ExecStdFctContent _stdFctContent, ExecArrContent _arrContent) {
        super(_content, _intermediateDottedOperation,_arrContent);
        stdFctContent = _stdFctContent;
    }

    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, ContextEl _context, RendStackCall _rendStack) {
        Argument previous_ = getPreviousArg(this, _nodes, _rendStack);
        int off_ = StringUtil.getFirstPrintableCharIndex(getMethodName());
        setRelOffsetPossibleLastPage(off_, _rendStack);
        ArgumentWrapper argres_ = RendDynOperationNode.processCall(ExecStdFctOperation.prep(_context,_rendStack.getStackCall(),previous_,buildInfos(_nodes), getStdFctContent()), _context, _rendStack);
        setSimpleArgument(argres_, _nodes, _context, _rendStack);
    }

    public ExecStdFctContent getStdFctContent() {
        return stdFctContent;
    }

    public String getMethodName() {
        return stdFctContent.getMethodName();
    }

}
