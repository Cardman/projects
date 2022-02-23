package code.expressionlanguage.exec.calls;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.blocks.ExecBlock;
import code.expressionlanguage.exec.blocks.ExecHelperBlocks;
import code.expressionlanguage.exec.blocks.ExecInitBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.calls.util.CustomFoundBlock;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.exec.variables.AbstractWrapper;
import code.util.CustList;
import code.util.IdMap;
import code.util.core.BoolVal;

public abstract class AbstractInitPageEl extends AbstractPageEl {
    private int member;

    private final CustList<ExecBlock> visited;

    protected AbstractInitPageEl(CustList<ExecBlock> _visited, ExecFormattedRootBlock _global) {
        super(_global);
        this.visited = _visited;
    }

    protected void block(StackCall _stack, IdMap<ExecInitBlock, BoolVal> _processedBlocks) {
        ExecBlock en_ = getBlock();
        if (en_ instanceof ExecInitBlock && _processedBlocks.getVal((ExecInitBlock) en_) == BoolVal.FALSE) {
            globalOffset(((ExecInitBlock) en_).getOffsetTrim());
            _processedBlocks.put((ExecInitBlock) en_, BoolVal.TRUE);
            CustomFoundBlock cust_ = new CustomFoundBlock(this, (ExecInitBlock) en_);
            _stack.setCallingState(cust_);
            return;
        }
        if (en_ != null) {
            ExecHelperBlocks.processMemberBlock(this);
            return;
        }
        setNullReadWrite();
    }
    public void blockRoot(ExecRootBlock _type) {
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
