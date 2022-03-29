package code.formathtml.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.opers.ExecAnonymousInstancingOperation;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.exec.util.ExecOperationInfo;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecInstancingCustContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.formathtml.exec.RendStackCall;
import code.formathtml.util.BeanLgNames;
import code.util.CustList;
import code.util.IdMap;
import code.util.core.StringUtil;

public final class RendAnonymousInstancingOperation extends RendInvokingOperation implements RendCalculableOperation {

    private final ExecInstancingCustContent instancingCommonContent;

    private final ExecFormattedRootBlock formattedType;

    public RendAnonymousInstancingOperation(ExecOperationContent _content, boolean _intermediateDottedOperation, ExecInstancingCustContent _instancingCommonContent) {
        super(_content, _intermediateDottedOperation);
        instancingCommonContent = _instancingCommonContent;
        formattedType = _instancingCommonContent.getFormattedType();
    }

    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, BeanLgNames _advStandards, ContextEl _context, RendStackCall _rendStack) {
        Argument previous_ = getPreviousArg(this,_nodes, _rendStack);
        int off_ = StringUtil.getFirstPrintableCharIndex(instancingCommonContent.getMethodName());
        setRelOffsetPossibleLastPage(off_, _rendStack);
        CustList<ExecOperationInfo> infos_ = buildInfos(_nodes);
        Argument result_ = ExecAnonymousInstancingOperation.prep(_context, _rendStack.getStackCall(), previous_, StackCall.formatVarType(_rendStack, formattedType), infos_, instancingCommonContent);
        ArgumentWrapper argres_ = RendDynOperationNode.processCall(result_, _context, _rendStack);
        setSimpleArgument(argres_, _nodes, _context, _rendStack);
    }
}
