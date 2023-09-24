package code.expressionlanguage.exec.variables;

import code.expressionlanguage.exec.blocks.ExecFileBlock;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.structs.StackTraceElementStruct;
import code.util.CustList;

public final class ViewPage {
    private final StackTraceElementStruct stackElt;
    private final CustList<ViewVariable> vars;
    private final ViewInstance instance;
    private final AbstractPageEl page;

    public ViewPage(StackTraceElementStruct _st, CustList<ViewVariable> _v, ViewInstance _i, AbstractPageEl _call) {
        this.stackElt = _st;
        this.vars = _v;
        this.instance = _i;
        this.page = _call;
    }

    public String getFileName() {
        return ExecFileBlock.name(getPage().getFile());
    }

    public int getTrace() {
        return getPage().getTraceIndex();
    }

    public StackTraceElementStruct getStackElt() {
        return stackElt;
    }

    public CustList<ViewVariable> getVars() {
        return vars;
    }

    public ViewInstance getInstance() {
        return instance;
    }

    public AbstractPageEl getPage() {
        return page;
    }

}
