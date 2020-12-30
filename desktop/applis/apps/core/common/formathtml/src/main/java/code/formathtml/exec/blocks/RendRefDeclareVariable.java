package code.formathtml.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.formathtml.Configuration;
import code.formathtml.ImportingPage;
import code.formathtml.exec.RendStackCall;
import code.formathtml.util.BeanLgNames;
import code.util.StringList;

public final class RendRefDeclareVariable extends RendAbstractDeclareVariable {
    public RendRefDeclareVariable(int _offsetTrim, StringList _variableNames) {
        super(_offsetTrim, _variableNames);
    }

    @Override
    public void removeLocalVars(ImportingPage _ip) {
        for (String v: getVariableNames()) {
            _ip.removeRefVar(v);
        }
    }

    @Override
    public void processEl(Configuration _cont, BeanLgNames _stds, ContextEl _ctx, StackCall _stack, RendStackCall _rendStack) {
        processBlock(_cont, _stds, _ctx, _stack, _rendStack);
    }
}
