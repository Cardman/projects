package code.formathtml.util;

import code.expressionlanguage.analyze.AbstractFileBuilder;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.formathtml.Configuration;
import code.formathtml.ReadConfiguration;
import code.sml.Document;
import code.sml.Element;
import code.util.core.StringUtil;

public abstract class AbstractConfigurationLoader {

    public DualConfigurationContext load(Configuration _configuration, String _lgCode, Document _document, AbstractFileBuilder _fileBuilder, AnalyzedPageEl _page) {
        DualConfigurationContext d_ = new DualConfigurationContext();
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
                d_.setMessagesFolder(c.getAttribute("value"));
                continue;
            }
            if (StringUtil.quickEq(fieldName_, "beans")) {
                _configuration.setBeansInfos(ReadConfiguration.loadBeans(c));
                continue;
            }
            if (StringUtil.quickEq(fieldName_, "properties")) {
                d_.setProperties(ReadConfiguration.loadStringMapString(c));
                continue;
            }
            if (StringUtil.quickEq(fieldName_, "resources")) {
                d_.setAddedResources(ReadConfiguration.getStringList(c));
                continue;
            }
            if (StringUtil.quickEq(fieldName_, "navigation")) {
                _configuration.setNavigation(ReadConfiguration.loadStringMapStrings(c));
                continue;
            }

            if (StringUtil.quickEq(fieldName_, "addedFiles")) {
                d_.setAddedFiles(ReadConfiguration.getStringList(c));
                continue;
            }
            if (StringUtil.quickEq(fieldName_, "renderFiles")) {
                d_.setRenderFiles(ReadConfiguration.getStringList(c));
            }
        }
        d_.setFileBuilder(_fileBuilder);
        specificLoad(_configuration,_lgCode,_document, d_, _page);
        return d_;
    }
    public abstract void specificLoad(Configuration _configuration, String _lgCode, Document _document, DualConfigurationContext _dual, AnalyzedPageEl _page);

}
