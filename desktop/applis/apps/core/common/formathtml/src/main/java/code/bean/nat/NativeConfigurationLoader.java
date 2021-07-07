package code.bean.nat;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.fwd.Forwards;
import code.formathtml.Configuration;
import code.formathtml.util.AbstractConfigurationLoader;
import code.formathtml.util.BeanLgNames;
import code.formathtml.util.DualAnalyzedContext;
import code.formathtml.util.DualConfigurationContext;
import code.sml.Document;

public final class NativeConfigurationLoader extends AbstractConfigurationLoader {
    private final BeanNatLgNames stds;
    private final AbstractNativeInit init;

    public NativeConfigurationLoader(BeanNatLgNames _stds, AbstractNativeInit _init) {
        this.stds = _stds;
        init = _init;
    }

    @Override
    public DualAnalyzedContext specificLoad(Configuration _configuration, String _lgCode, Document _document, AnalyzedPageEl _page, BeanLgNames _stds, DualConfigurationContext _context) {
        if (init != null) {
            init.initConf(_configuration);
            init.initAna(_context);
        } else {
            update(_configuration,_document, _context);
        }
        Forwards forwards_ = stds.setupNative(_page, _context);
        return new DualAnalyzedContext(forwards_,_page,_stds,_context);
    }
}
