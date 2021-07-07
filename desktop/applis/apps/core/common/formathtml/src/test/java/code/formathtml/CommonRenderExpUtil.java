package code.formathtml;

import code.expressionlanguage.analyze.opers.OperationNode;
import code.formathtml.exec.opers.RendDynOperationNode;
import code.formathtml.fwd.RendForwardInfos;
import code.util.CustList;

public abstract class CommonRenderExpUtil extends CommonRender {
    protected static CustList<RendDynOperationNode> getExecutableNodes(AnalyzedTestConfiguration _an, CustList<OperationNode> _ops) {
        CustList<RendDynOperationNode> executableNodes_ = getQuickExecutableNodes(_an, _ops);
        _an.getForwards().generate(_an.getOpt());
        return executableNodes_;
    }
    protected static CustList<RendDynOperationNode> getQuickExecutableNodes(AnalyzedTestConfiguration _an, CustList<OperationNode> _ops) {
        OperationNode root_ = _ops.last();
        CustList<RendDynOperationNode> executableNodes_ = RendForwardInfos.getExecutableNodes(root_, _an.getForwards());
        return CommonRender.getReducedNodes(executableNodes_.last());
    }

}
