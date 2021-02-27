package code.bean.nat;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.formathtml.Configuration;
import code.formathtml.util.AbstractConfigurationLoader;
import code.formathtml.util.DualConfigurationContext;
import code.sml.Document;

public final class NativeConfigurationLoader extends AbstractConfigurationLoader {
    private final BeanNatLgNames stds;

    public NativeConfigurationLoader(BeanNatLgNames _stds) {
        this.stds = _stds;
    }

    @Override
    public void specificLoad(Configuration _configuration, String _lgCode, Document _document, DualConfigurationContext _dual, AnalyzedPageEl _page) {
        _dual.setContext(stds.setupNative(_page));
    }
}
