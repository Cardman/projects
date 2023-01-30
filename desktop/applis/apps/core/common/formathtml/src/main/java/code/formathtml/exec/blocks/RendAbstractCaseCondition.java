package code.formathtml.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.formathtml.Configuration;
import code.formathtml.exec.ImportingPage;
import code.formathtml.exec.RendStackCall;
import code.formathtml.exec.opers.RendDynOperationNode;
import code.formathtml.exec.stacks.DefRendReadWrite;
import code.formathtml.util.BeanLgNames;
import code.util.CustList;

public abstract class RendAbstractCaseCondition extends RendParentBlock implements RendWithEl {

    private final RendOperationNodeListOff exp;

    protected RendAbstractCaseCondition(CustList<RendDynOperationNode> _list, int _offset) {
        exp = new RendOperationNodeListOff(_list, _offset);
    }

    @Override
    public void processEl(Configuration _cont, BeanLgNames _stds, ContextEl _ctx, RendStackCall _rendStack) {
        ImportingPage ip_ = _rendStack.getLastPage();
        DefRendReadWrite rw_ = ip_.getRendReadWrite();
        rw_.setRead(getFirstChild());
        setVisited(ip_,this);
    }

    public RendOperationNodeListOff getExp() {
        return exp;
    }
}
