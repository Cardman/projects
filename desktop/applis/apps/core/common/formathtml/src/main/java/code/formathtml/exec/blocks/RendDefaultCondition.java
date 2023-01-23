package code.formathtml.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.formathtml.Configuration;
import code.formathtml.exec.ImportingPage;
import code.formathtml.exec.RendStackCall;
import code.formathtml.exec.stacks.DefRendReadWrite;
import code.formathtml.util.BeanLgNames;

public final class RendDefaultCondition extends RendParentBlock implements RendWithEl {
    private final String variableName;
    public RendDefaultCondition(){
        this("");
    }
    public RendDefaultCondition(String _varName){
        variableName = _varName;
    }

    @Override
    public void processEl(Configuration _cont, BeanLgNames _stds, ContextEl _ctx, RendStackCall _rendStack) {
        ImportingPage ip_ = _rendStack.getLastPage();
        DefRendReadWrite rw_ = ip_.getRendReadWrite();
        rw_.setRead(getFirstChild());
        setVisited(ip_,this);
    }

    @Override
    public void removeAllVars(ImportingPage _ip) {
        super.removeAllVars(_ip);
        if (!variableName.isEmpty()) {
            _ip.removeRefVar(variableName);
        }
    }
    public String getVariableName() {
        return variableName;
    }
}
