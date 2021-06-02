package code.expressionlanguage.exec.calls;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.blocks.ExecBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.exec.variables.AbstractWrapper;
import code.util.CustList;

public abstract class AbstractInitPageEl extends AbstractPageEl {
    private int member;

    private final CustList<ExecBlock> visited;

    protected AbstractInitPageEl(CustList<ExecBlock> _visited, ExecFormattedRootBlock _global) {
        super(_global);
        this.visited = _visited;
    }

    public void blockRoot(ExecRootBlock _type) {
        setBlockRootType(_type);
        setBlockRoot(_type);
    }
    public void receive(AbstractWrapper _wrap, Argument _argument, ContextEl _context, StackCall _stack) {
        basicReceive(_wrap, _argument,_context, _stack);
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
