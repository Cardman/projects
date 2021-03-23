package code.expressionlanguage.exec.calls;

import code.expressionlanguage.exec.blocks.ExecBlock;
import code.util.CustList;

public abstract class AbstractInitPageEl extends AbstractPageEl {
    private int member;

    private final CustList<ExecBlock> visited;

    protected AbstractInitPageEl(CustList<ExecBlock> _visited) {
        this.visited = _visited;
    }

    public CustList<ExecBlock> getVisited() {
        return visited;
    }

    public int getMember() {
        return member;
    }

    public void setMember(int _member) {
        member = _member;
    }
}
