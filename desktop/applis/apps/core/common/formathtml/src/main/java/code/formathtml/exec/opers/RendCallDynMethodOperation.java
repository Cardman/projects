package code.formathtml.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.opers.ExecMethodOperation;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.exec.opers.ExecInvokingOperation;
import code.expressionlanguage.fwd.opers.ExecArrContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.formathtml.exec.RendStackCall;
import code.util.CustList;
import code.util.IdMap;
import code.util.core.StringUtil;

public final class RendCallDynMethodOperation extends RendSettableCallFctOperation implements RendCalculableOperation {

    private final String fctName;
    public RendCallDynMethodOperation(ExecOperationContent _content, boolean _intermediateDottedOperation, String _fctName, ExecArrContent _arrContent) {
        super(_content, _intermediateDottedOperation, _arrContent);
        fctName = _fctName;
    }

    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, ContextEl _context, RendStackCall _rendStack) {
        Argument previous_ = getPreviousArg(this,_nodes, _rendStack);
        if (StringUtil.quickEq(fctName, _context.getStandards().getContent().getReflect().getAliasMetaInfo())) {
            Argument res_ = ExecInvokingOperation.getMetaInfo(previous_, _context, _rendStack.getStackCall());
            setSimpleArgument(res_, _nodes, _context, _rendStack);
            return;
        }
        if (StringUtil.quickEq(fctName, _context.getStandards().getContent().getReflect().getAliasInstance())) {
            Argument res_ = ExecInvokingOperation.getInstanceCall(previous_, _context, _rendStack.getStackCall());
            setSimpleArgument(res_, _nodes, _context, _rendStack);
            return;
        }
        ArgumentWrapper argres_ = RendDynOperationNode.processCall(ExecInvokingOperation.prepareCallDynNormal(previous_, fectchPosArgs(_nodes), _context, _rendStack.getStackCall()), _context, _rendStack);
        setSimpleArgument(argres_, _nodes, _context, _rendStack);
    }

    private ArgumentListCall fectchPosArgs(IdMap<RendDynOperationNode, ArgumentsPair> _nodes) {
        CustList<RendDynOperationNode> chidren_ = getChildrenNodes();
        RendDynOperationNode last_ = getLast(chidren_);
        if (last_ instanceof RendNamedArgumentOperation) {
            chidren_ = ((RendNamedArgumentOperation)last_).getChildrenNodes();
            last_ = getLast(chidren_);
        }
        if (last_ instanceof RendArgumentListInstancing) {
            chidren_ = ((RendArgumentListInstancing)last_).getChildrenNodes();
        }
        return ExecMethodOperation.listNamedArguments(buildInfos(_nodes, chidren_)).getArguments();
    }

    private static RendDynOperationNode getLast(CustList<RendDynOperationNode> _list) {
        if (_list.isEmpty()) {
            return null;
        }
        return _list.last();
    }
}
