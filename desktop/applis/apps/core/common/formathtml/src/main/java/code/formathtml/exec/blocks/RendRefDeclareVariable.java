package code.formathtml.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.formathtml.Configuration;
import code.formathtml.ImportingPage;
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
    public void processEl(Configuration _cont, BeanLgNames _stds, ContextEl _ctx) {
        processBlock(_cont, _stds, _ctx);
    }
}
