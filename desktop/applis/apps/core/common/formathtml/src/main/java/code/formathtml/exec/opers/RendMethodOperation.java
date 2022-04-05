package code.formathtml.exec.opers;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.inherits.ParamCheckerUtil;
import code.expressionlanguage.exec.opers.ExecMethodOperation;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.exec.util.ExecOperationInfo;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.fwd.opers.ExecStaticEltContent;
import code.formathtml.exec.RendStackCall;
import code.util.CustList;
import code.util.IdMap;

public abstract class RendMethodOperation extends RendDynOperationNode {

    private final CustList<RendDynOperationNode> childrenNodes = new CustList<RendDynOperationNode>();

    protected RendMethodOperation(ExecOperationContent _content) {
        super(_content);
    }

    public CustList<ExecOperationInfo> buildInfos(IdMap<RendDynOperationNode, ArgumentsPair> _all) {
        return buildInfos(_all,getChildrenNodes());
    }
    public static CustList<ExecOperationInfo> buildInfos(IdMap<RendDynOperationNode, ArgumentsPair> _all, CustList<RendDynOperationNode> _children) {
        CustList<ExecOperationInfo> infos_ = new CustList<ExecOperationInfo>();
        for (RendDynOperationNode c: _children) {
            int index_ = -1;
            boolean wr_ = false;
            if (c instanceof RendNamedArgumentOperation) {
                RendDynOperationNode ch_ = ((RendNamedArgumentOperation)c).getFirstChild();
                index_ = ((RendNamedArgumentOperation)c).getIndex();
                wr_ = ch_ instanceof RendWrappOperation;
            } else if (c instanceof RendWrappOperation) {
                wr_ = true;
            }
            ArgumentsPair calc_ = getArgumentPair(_all,c);
            infos_.add(new ExecOperationInfo(RendConstLeafOperation.isFilter(c), wr_,index_,calc_));
        }
        return infos_;
    }

    public void checkParametersOperatorsFormatted(AbstractExiting _exit, ContextEl _conf, ExecTypeFunction _named,
                                                  IdMap<RendDynOperationNode, ArgumentsPair> _nodes, ExecStaticEltContent _elt, RendStackCall _stackCall) {
        ArgumentListCall l_ = ExecMethodOperation.listNamedArguments(buildInfos(_nodes)).getArguments();
        ParamCheckerUtil.checkParametersOperatorsFormatted(_exit, _conf, _named, l_ , ExecFormattedRootBlock.formatType(_elt, _stackCall), _elt.getKind(), _stackCall.getStackCall());
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
