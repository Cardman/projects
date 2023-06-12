package code.expressionlanguage.exec.variables;

import code.util.CustList;

public final class ViewPage {
    private final CustList<ViewVariable> vars;
    private final ViewInstance instance;

    public ViewPage(CustList<ViewVariable> _v, ViewInstance _i) {
        this.vars = _v;
        this.instance = _i;
    }

    public CustList<ViewVariable> getVars() {
        return vars;
    }

    public ViewInstance getInstance() {
        return instance;
    }
}
