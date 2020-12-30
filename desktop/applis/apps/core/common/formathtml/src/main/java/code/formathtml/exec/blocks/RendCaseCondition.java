package code.formathtml.exec.blocks;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.formathtml.Configuration;
import code.formathtml.ImportingPage;
import code.formathtml.exec.RendStackCall;
import code.formathtml.stacks.RendReadWrite;
import code.formathtml.util.BeanLgNames;

public final class RendCaseCondition extends RendSwitchPartCondition implements RendWithEl {

    private final String value;

    private final Argument argument;

    public RendCaseCondition(int _offsetTrim, String _importedClassName, String _variableName, String _value, Argument _argument) {
        super(_offsetTrim, _importedClassName, _variableName);
        this.value = _value;
        this.argument = _argument;
    }

    public String getValue() {
        return value;
    }

    @Override
    public void removeAllVars(ImportingPage _ip) {
        super.removeAllVars(_ip);
        if (!getVariableName().isEmpty()) {
            _ip.removeRefVar(getVariableName());
        }
    }

    @Override
    public void processEl(Configuration _cont, BeanLgNames _stds, ContextEl _ctx, StackCall _stack, RendStackCall _rendStack) {
        ImportingPage ip_ = _rendStack.getLastPage();
        RendReadWrite rw_ = ip_.getRendReadWrite();
        rw_.setRead(getFirstChild());
        setVisited(ip_,this);
    }

    public Argument getArgument() {
        return argument;
    }
}
