package code.formathtml.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.formathtml.Configuration;
import code.formathtml.ImportingPage;
import code.formathtml.stacks.RendReadWrite;
import code.formathtml.util.BeanLgNames;

public final class RendDefaultCondition extends RendSwitchPartCondition implements RendWithEl {


    public RendDefaultCondition(int _offsetTrim, String _importedClassName, String _variableName) {
        super(_offsetTrim, _importedClassName, _variableName);
    }

    @Override
    public void removeAllVars(ImportingPage _ip) {
        super.removeAllVars(_ip);
        if (!getVariableName().isEmpty()) {
            _ip.removeLocalVar(getVariableName());
        }
    }
    @Override
    public void processEl(Configuration _cont, BeanLgNames _stds, ContextEl _ctx) {
        ImportingPage ip_ = _cont.getLastPage();
        RendReadWrite rw_ = ip_.getRendReadWrite();
        rw_.setRead(getFirstChild());
        setVisited(ip_,this);
    }

}
