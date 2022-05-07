package code.formathtml.util;

import code.expressionlanguage.analyze.AbstractFileBuilder;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.blocks.FileBlock;
import code.expressionlanguage.fwd.Forwards;
import code.expressionlanguage.options.Options;
import code.formathtml.Configuration;
import code.formathtml.ReadConfiguration;
import code.sml.Document;
import code.sml.Element;
import code.util.core.NumberUtil;
import code.util.core.StringUtil;

public final class DefaultConfigurationLoader {
    private final BeanCustLgNames stds;

    public DefaultConfigurationLoader(BeanCustLgNames _stds) {
        this.stds = _stds;
    }

    public DualAnalyzedContext load(Configuration _configuration, String _lgCode, Document _document, AbstractFileBuilder _fileBuilder, AnalyzedPageEl _page, BeanCustLgNames _lgNames, FileBlock _file) {
        DualConfigurationContext d_ = new DualConfigurationContext();
        d_.setFileBuilder(_fileBuilder);
        return specificLoad(_configuration,_lgCode,_document, _page, _lgNames, d_, _file);
    }

    public DualAnalyzedContext specificLoad(Configuration _configuration, String _lgCode, Document _document, AnalyzedPageEl _page, BeanCustLgNames _stds, DualConfigurationContext _context, FileBlock _file) {
        update(_configuration,_document, _context);

        boolean ok_ = false;
        for (Element c: _document.getDocumentElement().getChildElements()) {
            String fieldName_ = c.getAttribute("field");
            if (StringUtil.quickEq(fieldName_, "context")) {
                ReadConfiguration.getOptions(c, _context);
            }
        }
        Options options_ = _context.getOptions();
        Forwards forwards_ = new Forwards(_stds, _context.getFileBuilder(), options_);
        for (Element c: _document.getDocumentElement().getChildElements()) {
            String fieldName_ = c.getAttribute("field");
            if (StringUtil.quickEq(fieldName_, "lateValidators")) {
                _context.setupLateValidators(ReadConfiguration.loadStringMapString(c));
                _configuration.setLateValidators(_context.getLateValidators());
                continue;
            }
            if (StringUtil.quickEq(fieldName_, "tabWidth")) {
                _configuration.setTabWidth(NumberUtil.parseInt(c.getAttribute("value")));
                continue;
            }
            if (StringUtil.quickEq(fieldName_, "filesConfName")) {
                _context.setFilesConfName(c.getAttribute("value"));
                continue;
            }
            if (StringUtil.quickEq(fieldName_, "context")) {
                ok_ = ReadConfiguration.loadContext(c, _lgCode, stds,_configuration, _page, forwards_, _context);
            }
        }
        _context.setKo(!ok_);
        return new DualAnalyzedContext(forwards_,_page,_stds,_context, _file);
    }

    private void update(Configuration _configuration, Document _document, DualConfigurationContext _d) {
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

}
