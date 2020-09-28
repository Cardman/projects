package code.formathtml.exec.blocks;

import code.expressionlanguage.Argument;
import code.formathtml.Configuration;
import code.formathtml.ImportingPage;
import code.formathtml.stacks.RendReadWrite;
import code.formathtml.stacks.RendSwitchBlockStack;

public final class RendCaseCondition extends RendSwitchPartCondition implements RendWithEl {

    private String value;

    private Argument argument;

    public RendCaseCondition(int _offsetTrim, String importedClassName, String variableName, String value, Argument argument) {
        super(_offsetTrim, importedClassName, variableName);
        this.value = value;
        this.argument = argument;
    }

    public String getValue() {
        return value;
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

    public Argument getArgument() {
        return argument;
    }
}
