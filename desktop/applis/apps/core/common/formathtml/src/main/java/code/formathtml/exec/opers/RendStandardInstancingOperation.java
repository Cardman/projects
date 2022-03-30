package code.formathtml.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.opers.ExecStandardInstancingOperation;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.exec.util.ExecOperationInfo;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.*;
import code.formathtml.exec.RendStackCall;
import code.util.CustList;
import code.util.IdMap;
import code.util.core.StringUtil;

public final class RendStandardInstancingOperation extends RendInvokingOperation implements RendCalculableOperation {

    private final ExecInstancingCustContent instancingCommonContent;
    private final ExecInstancingStdContent instancingStdContent;

    private final ExecFormattedRootBlock formattedType;

    public RendStandardInstancingOperation(ExecOperationContent _content, boolean _intermediateDottedOperation, ExecInstancingCustContent _instancingCommonContent, ExecInstancingStdContent _instancingStdContent) {
        super(_content, _intermediateDottedOperation);
        instancingCommonContent = _instancingCommonContent;
        instancingStdContent = _instancingStdContent;
        formattedType = _instancingCommonContent.getFormattedType();
    }

    public RendStandardInstancingOperation(ExecOperationContent _content, ExecInstancingCustContent _instancingCommonContent, ExecInstancingStdContent _instancingStdContent) {
        super(_content,false);
        instancingCommonContent = _instancingCommonContent;
        instancingStdContent = _instancingStdContent;
        formattedType = _instancingCommonContent.getFormattedType();
    }
    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, ContextEl _context, RendStackCall _rendStack) {
        Argument previous_ = getPreviousArg(this,_nodes, _rendStack);
        int off_ = StringUtil.getFirstPrintableCharIndex(instancingCommonContent.getMethodName());
        setRelOffsetPossibleLastPage(off_, _rendStack);
        CustList<ExecOperationInfo> infos_ = buildInfos(_nodes);
        Argument result_ = ExecStandardInstancingOperation.prep(_context,_rendStack.getStackCall(),previous_, StackCall.formatVarType(_rendStack,formattedType),infos_,instancingCommonContent,instancingStdContent);
        ArgumentWrapper argres_ = RendDynOperationNode.processCall(result_, _context, _rendStack);
        setSimpleArgument(argres_, _nodes, _context, _rendStack);
    }

}
