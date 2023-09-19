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
import code.expressionlanguage.stds.StandardNamedFunction;
import code.expressionlanguage.structs.NullStruct;
import code.formathtml.exec.RendStackCall;
import code.util.CustList;
import code.util.IdMap;

public final class RendCallDynMethodOperation extends RendSettableCallFctOperation implements RendCalculableOperation {

    private final StandardNamedFunction stdMethod;
    public RendCallDynMethodOperation(ExecOperationContent _content, boolean _intermediateDottedOperation, ExecArrContent _arrContent, StandardNamedFunction _std) {
        super(_content, _intermediateDottedOperation, _arrContent);
        stdMethod = _std;
    }

    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, ContextEl _context, RendStackCall _rendStack) {
        Argument previous_ = getPreviousArg(this,_nodes, _rendStack);
        if (stdMethod == _context.getStandards().getContent().getReflect().getFctTypeMeta()) {
            Argument res_ = ExecInvokingOperation.getMetaInfo(previous_, _context, _rendStack.getStackCall());
            setSimpleArgument(res_, _nodes, _context, _rendStack);
            return;
        }
        if (stdMethod == _context.getStandards().getContent().getReflect().getFctTypeInstance()) {
            Argument res_ = ExecInvokingOperation.getInstanceCall(previous_, _context, _rendStack.getStackCall());
            setSimpleArgument(res_, _nodes, _context, _rendStack);
            return;
        }
        ExecInvokingOperation.prepareCallDynNormal(previous_, fectchPosArgs(_nodes), _context, _rendStack.getStackCall());
        ArgumentWrapper argres_ = RendDynOperationNode.processCall(ArgumentListCall.toStr(NullStruct.NULL_VALUE), _context, _rendStack);
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
