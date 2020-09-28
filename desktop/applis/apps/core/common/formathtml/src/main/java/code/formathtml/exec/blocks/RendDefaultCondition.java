package code.formathtml.exec.blocks;

import code.formathtml.Configuration;
import code.formathtml.ImportingPage;
import code.formathtml.stacks.RendReadWrite;
import code.formathtml.stacks.RendSwitchBlockStack;

public final class RendDefaultCondition extends RendSwitchPartCondition implements RendWithEl {


    public RendDefaultCondition(int _offsetTrim, String importedClassName, String variableName) {
        super(_offsetTrim, importedClassName, variableName);
    }

    @Override
    public void removeAllVars(ImportingPage _ip) {
        super.removeAllVars(_ip);
        if (!getVariableName().isEmpty()) {
            _ip.getValueVars().removeKey(getVariableName());
        }
    }
    @Override
    public void processEl(Configuration _cont) {
        ImportingPage ip_ = _cont.getLastPage();
        RendReadWrite rw_ = ip_.getRendReadWrite();
        rw_.setRead(getFirstChild());
        ip_.getRendLastStack().setCurrentVisitedBlock(this);
    }

    @Override
    public void exitStack(Configuration _conf) {
        ImportingPage ip_ = _conf.getLastPage();
        RendReadWrite rw_ = ip_.getRendReadWrite();
        RendSwitchBlockStack if_ = (RendSwitchBlockStack) ip_.getRendLastStack();
        if (if_.getLastVisitedBlock() == this) {
            rw_.setRead(if_.getBlock());
        } else {
            rw_.setRead(getNextSibling());
        }
    }
}
