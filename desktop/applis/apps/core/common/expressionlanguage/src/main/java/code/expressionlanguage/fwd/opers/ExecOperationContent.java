package code.expressionlanguage.fwd.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.exec.types.ExecClassArgumentMatching;
import code.expressionlanguage.fwd.blocks.FetchMemberUtil;

public final class ExecOperationContent {
    private final int indexInEl;
    private final int indexChild;
    private final ExecClassArgumentMatching resultClass;
    private final Argument argument;
    private int order;
    public ExecOperationContent(AnaOperationContent _cont) {
        indexInEl = _cont.getIndexInEl();
        indexChild = _cont.getIndexChild();
        resultClass = FetchMemberUtil.toExec(_cont.getResultClass());
        argument = _cont.getArgument();
        order = _cont.getOrder();
    }
    public ExecOperationContent(int _indexChild, ExecClassArgumentMatching _res, int _order) {
        indexInEl = 0;
        indexChild = _indexChild;
        resultClass = _res;
        argument = null;
        order = _order;
    }

    public int getIndexInEl() {
        return indexInEl;
    }

    public int getIndexChild() {
        return indexChild;
    }

    public ExecClassArgumentMatching getResultClass() {
        return resultClass;
    }

    public Argument getArgument() {
        return argument;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int _order) {
        this.order = _order;
    }
}
