package code.formathtml.util;

import code.expressionlanguage.analyze.AnalyzedPageEl;

public final class DualAnalyzedContext {
    private final AnalyzedPageEl analyzed;
    private final BeanLgNames stds;
    private final DualConfigurationContext context;

    public DualAnalyzedContext(AnalyzedPageEl _analyzed, BeanLgNames _stds,DualConfigurationContext _context) {
        analyzed = _analyzed;
        stds = _stds;
        context = _context;
    }

    public DualConfigurationContext getContext() {
        return context;
    }

    public BeanLgNames getStds() {
        return stds;
    }

    public AnalyzedPageEl getAnalyzed() {
        return analyzed;
    }
}
