package code.formathtml.util;

import code.expressionlanguage.analyze.AbstractFileBuilder;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.formathtml.Configuration;
import code.formathtml.ReadConfiguration;
import code.sml.Document;
import code.sml.Element;
import code.util.core.StringUtil;

public abstract class AbstractConfigurationLoader {

    public DualAnalyzedContext load(Configuration _configuration, String _lgCode, Document _document, AbstractFileBuilder _fileBuilder, AnalyzedPageEl _page, BeanLgNames _lgNames) {
        DualConfigurationContext d_ = new DualConfigurationContext();
        d_.setFileBuilder(_fileBuilder);
        return specificLoad(_configuration,_lgCode,_document, _page, _lgNames, d_);
    }

    protected void update(Configuration _configuration, Document _document, DualConfigurationContext _d) {
        for (Element c: _document.getDocumentElement().getChildElements()) {
            String fieldName_ = c.getAttribute("field");
            if (StringUtil.quickEq(fieldName_, "firstUrl")) {
                _configuration.setFirstUrl(c.getAttribute("value"));
                continue;
            }
            if (StringUtil.quickEq(fieldName_, "prefix")) {
                _configuration.setPrefix(c.getAttribute("value"));
                continue;
            }
            if (StringUtil.quickEq(fieldName_, "messagesFolder")) {
                _d.setMessagesFolder(c.getAttribute("value"));
                continue;
            }
            if (StringUtil.quickEq(fieldName_, "beans")) {
                _configuration.setBeansInfos(ReadConfiguration.loadBeans(c));
                continue;
            }
            if (StringUtil.quickEq(fieldName_, "properties")) {
                _d.setProperties(ReadConfiguration.loadStringMapString(c));
                continue;
            }
            if (StringUtil.quickEq(fieldName_, "resources")) {
                _d.setAddedResources(ReadConfiguration.getStringList(c));
                continue;
            }
            if (StringUtil.quickEq(fieldName_, "navigation")) {
                _configuration.setNavigation(ReadConfiguration.loadStringMapStrings(c));
                continue;
            }

            if (StringUtil.quickEq(fieldName_, "addedFiles")) {
                _d.setAddedFiles(ReadConfiguration.getStringList(c));
                continue;
            }
            if (StringUtil.quickEq(fieldName_, "renderFiles")) {
                _d.setRenderFiles(ReadConfiguration.getStringList(c));
            }
        }
    }

    public abstract DualAnalyzedContext specificLoad(Configuration _configuration, String _lgCode, Document _document, AnalyzedPageEl _page, BeanLgNames _stds, DualConfigurationContext _context);

}
