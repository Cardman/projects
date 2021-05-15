package code.expressionlanguage.exec.opers;
import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ExecHelper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.types.ExecClassArgumentMatching;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.util.CustList;
import code.util.IdMap;

public abstract class ExecMethodOperation extends ExecOperationNode {

    private final CustList<ExecOperationNode> childrenNodes = new CustList<ExecOperationNode>();

    protected ExecMethodOperation(ExecOperationContent _m) {
        super(_m);
    }

    protected ExecMethodOperation(int _indexChild, ExecClassArgumentMatching _res, int _order) {
        super(_indexChild,_res,_order);
    }

    public void checkParametersOperators(AbstractExiting _exit, ContextEl _conf, ExecTypeFunction _named,
                                         IdMap<ExecOperationNode, ArgumentsPair> _nodes, ExecFormattedRootBlock _formattedType, MethodAccessKind _kind, StackCall _stackCall) {
        CustList<Argument> arguments_ = getArguments(_nodes, this);
        ArgumentListCall l_ = new ArgumentListCall();
        l_.addAllArgs(arguments_);
        ExecInvokingOperation.checkParametersOperatorsFormatted(_exit, _conf, _named, l_, ClassMethodId.formatType(_formattedType, _kind, _stackCall), _kind, _stackCall);
    }

    public final void appendChild(ExecOperationNode _child) {
        _child.setParent(this);
        childrenNodes.add(_child);
    }

    public final CustList<ExecOperationNode> getChildrenNodes() {
        return childrenNodes;
    }

    @Override
    public final ExecOperationNode getFirstChild() {
        return ExecHelper.getFirstNode(this);
    }

}
