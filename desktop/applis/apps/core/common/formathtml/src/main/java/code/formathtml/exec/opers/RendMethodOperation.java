package code.formathtml.exec.opers;
import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.opers.ExecInvokingOperation;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.util.CustList;
import code.util.IdMap;

public abstract class RendMethodOperation extends RendDynOperationNode {

    private CustList<RendDynOperationNode> childrenNodes = new CustList<RendDynOperationNode>();

    public RendMethodOperation(ExecOperationContent _content) {
        super(_content);
    }

    public void checkParametersOperatorsFormatted(AbstractExiting _exit, ContextEl _conf, ExecTypeFunction _named,
                                                  IdMap<RendDynOperationNode, ArgumentsPair> _nodes, ExecFormattedRootBlock _formattedType, MethodAccessKind _kind, StackCall _stackCall) {
        CustList<Argument> arguments_ = getArguments(_nodes, this);
        ArgumentListCall l_ = new ArgumentListCall();
        l_.addAllArgs(arguments_);
        ExecInvokingOperation.checkParametersOperatorsFormatted(_exit, _conf, _named, l_ , _formattedType, _kind, _stackCall);
    }

    public final void appendChild(RendDynOperationNode _child) {
        _child.setParent(this);
        childrenNodes.add(_child);
    }

    public final CustList<RendDynOperationNode> getChildrenNodes() {
        return childrenNodes;
    }

    public final RendDynOperationNode getFirstChild() {
        return getFirstNode(this);
    }

}
