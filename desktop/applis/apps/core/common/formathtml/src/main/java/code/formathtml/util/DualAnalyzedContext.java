package code.formathtml.util;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.blocks.FileBlock;
import code.expressionlanguage.fwd.Forwards;

public final class DualAnalyzedContext {
    private final AnalyzedPageEl analyzed;
    private final BeanCustLgNames stds;
    private final DualConfigurationContext context;
    private final Forwards forwards;
    private final FileBlock block;

    public DualAnalyzedContext(Forwards _forwards, AnalyzedPageEl _analyzed, BeanCustLgNames _stds, DualConfigurationContext _context, FileBlock _file) {
        forwards = _forwards;
        analyzed = _analyzed;
        stds = _stds;
        context = _context;
        block = _file;
    }

    public Forwards getForwards() {
        return forwards;
    }

    public DualConfigurationContext getContext() {
        return context;
    }

    public BeanCustLgNames getStds() {
        return stds;
    }

    public AnalyzedPageEl getAnalyzed() {
        return analyzed;
    }

    public FileBlock getBlock() {
        return block;
    }
}
