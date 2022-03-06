package code.formathtml;

import code.formathtml.util.DualAnalyzedContext;

public final class DualNavigationContext {
    private final Navigation navigation;
    private final DualAnalyzedContext dualAnalyzedContext;

    public DualNavigationContext(Navigation _nav, DualAnalyzedContext _dual) {
        this.navigation = _nav;
        this.dualAnalyzedContext = _dual;
    }

    public DualAnalyzedContext getDualAnalyzedContext() {
        return dualAnalyzedContext;
    }

    public Navigation getNavigation() {
        return navigation;
    }
}
