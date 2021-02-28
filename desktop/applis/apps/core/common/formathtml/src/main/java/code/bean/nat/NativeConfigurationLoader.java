package code.bean.nat;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.formathtml.Configuration;
import code.formathtml.util.AbstractConfigurationLoader;
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
    public void specificLoad(Configuration _configuration, String _lgCode, Document _document, DualConfigurationContext _dual, AnalyzedPageEl _page) {
        if (init != null) {
            init.initConf(_configuration);
            init.initAna(_dual);
        } else {
            update(_configuration,_document,_dual);
        }
        _dual.setContext(stds.setupNative(_page));
    }
}
