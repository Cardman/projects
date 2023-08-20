package code.expressionlanguage.exec.variables;

import code.expressionlanguage.structs.StackTraceElementStruct;
import code.util.CustList;

public final class ViewPage {
    private final StackTraceElementStruct stackElt;
    private final CustList<ViewVariable> vars;
    private final ViewInstance instance;
    private final String fileName;
    private final int trace;

    public ViewPage(StackTraceElementStruct _st, CustList<ViewVariable> _v, ViewInstance _i, String _fileName, int _trace) {
        this.stackElt = _st;
        this.vars = _v;
        this.instance = _i;
        this.fileName = _fileName;
        this.trace = _trace;
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

    public String getFileName() {
        return fileName;
    }

    public int getTrace() {
        return trace;
    }
}
