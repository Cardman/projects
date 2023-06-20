package code.expressionlanguage.adv;

import code.expressionlanguage.options.ResultContext;
import code.expressionlanguage.utilimpl.ManageOptions;
import code.util.StringMap;

public final class AnalyzingDebugEvent extends AbsAnalyzingDebugEvent {
    private final ResultContext resultContext;
    private final ManageOptions manageOptions;
    private final StringMap<String> srcFiles;

    public AnalyzingDebugEvent(ResultContext _b, AbsDebuggerGui _g, ManageOptions _man, StringMap<String> _s) {
        super(_g);
        this.resultContext = _b;
        this.manageOptions = _man;
        this.srcFiles = _s;
    }

    @Override
    protected ResultContext base() {
        return resultContext;
    }

    @Override
    protected ManageOptions manageOpt() {
        return manageOptions;
    }

    @Override
    protected StringMap<String> src() {
        return srcFiles;
    }
}
