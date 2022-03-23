package code.formathtml.nat;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.fwd.Forwards;
import code.formathtml.Configuration;
import code.formathtml.util.BeanLgNames;
import code.formathtml.util.DualAnalyzedContext;
import code.formathtml.util.DualConfigurationContext;
import code.sml.Document;

public final class NativeTestConfigurationLoader {
    private final BeanTestNatLgNames stds;

    public NativeTestConfigurationLoader(BeanTestNatLgNames _stds) {
        this.stds = _stds;
    }

    public DualAnalyzedContext specificLoad(Configuration _configuration, String _lgCode, Document _document, AnalyzedPageEl _page, BeanLgNames _stds, DualConfigurationContext _context) {
//        update(_configuration,_document, _context);
        Forwards forwards_ = stds.setupNative(null, _context);
        return new DualAnalyzedContext(forwards_,_page,null,_context);
    }
}
