package code.formathtml.nat;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.fwd.Forwards;
import code.expressionlanguage.options.Options;
import code.formathtml.Configuration;
import code.formathtml.util.AbstractConfigurationLoader;
import code.formathtml.util.BeanLgNames;
import code.formathtml.util.DualAnalyzedContext;
import code.formathtml.util.DualConfigurationContext;
import code.sml.Document;

public final class NativeTestConfigurationLoader extends AbstractConfigurationLoader {
    private final BeanTestNatLgNames stds;

    public NativeTestConfigurationLoader(BeanTestNatLgNames _stds) {
        this.stds = _stds;
    }

    @Override
    public DualAnalyzedContext specificLoad(Configuration _configuration, String _lgCode, Document _document, AnalyzedPageEl _page, BeanLgNames _stds, DualConfigurationContext _context) {
        update(_configuration,_document, _context);
        Forwards forwards_ = stds.setupNative(null, _context);
        return new DualAnalyzedContext(forwards_,_page,_stds,_context);
    }
}
