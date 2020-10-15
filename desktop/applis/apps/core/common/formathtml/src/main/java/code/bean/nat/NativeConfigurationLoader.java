package code.bean.nat;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.formathtml.Configuration;
import code.formathtml.util.AbstractConfigurationLoader;
import code.formathtml.util.DualConfigurationContext;
import code.sml.Document;
import code.sml.Element;
import code.util.core.StringUtil;

public final class NativeConfigurationLoader extends AbstractConfigurationLoader {
    private final BeanNatLgNames stds;

    public NativeConfigurationLoader(BeanNatLgNames _stds) {
        this.stds = _stds;
    }

    @Override
    public void specificLoad(Configuration _configuration, String _lgCode, Document _document, DualConfigurationContext _dual, AnalyzedPageEl _page) {
        for (Element c: _document.getDocumentElement().getChildElements()) {
            String fieldName_ = c.getAttribute("field");
            if (StringUtil.quickEq(fieldName_, "validators")) {
                stds.setValidators(stds.loadValidator(c));
            }
        }
        _dual.setContext(stds.setupNative(_page));
    }
}
