package code.formathtml.util;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.formathtml.Configuration;
import code.formathtml.ReadConfiguration;
import code.sml.Document;
import code.sml.Element;
import code.util.Numbers;
import code.util.StringList;

public final class DefaultConfigurationLoader extends AbstractConfigurationLoader {
    private final BeanCustLgNames stds;

    public DefaultConfigurationLoader(BeanCustLgNames stds) {
        this.stds = stds;
    }

    @Override
    public void specificLoad(Configuration _configuration, String _lgCode, Document _document, DualConfigurationContext _dual, AnalyzedPageEl _page) {
        ContextEl page_ = null;
        for (Element c: _document.getDocumentElement().getChildElements()) {
            String fieldName_ = c.getAttribute("field");
            if (StringList.quickEq(fieldName_, "lateValidators")) {
                _dual.setupLateValidators(ReadConfiguration.loadStringMapString(c));
                _configuration.setLateValidators(_dual.getLateValidators());
                continue;
            }
            if (StringList.quickEq(fieldName_, "tabWidth")) {
                _configuration.setTabWidth(Numbers.parseInt(c.getAttribute("value")));
                continue;
            }
            if (StringList.quickEq(fieldName_, "filesConfName")) {
                _dual.setFilesConfName(c.getAttribute("value"));
                continue;
            }
            if (StringList.quickEq(fieldName_, "context")) {
                page_ = ReadConfiguration.loadContext(c, _lgCode, stds,_configuration, _dual.getAnalysisMessages(), _dual.getFileBuilder(), _page);
            }
        }
        _dual.setContext(page_);
    }
}
