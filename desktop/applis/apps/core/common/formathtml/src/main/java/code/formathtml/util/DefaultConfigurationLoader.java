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
    private static final String FIELD = "field";
    private static final String VALUE = "value";
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
            String fieldName_ = c.getAttribute(FIELD);
            if (StringUtil.quickEq(fieldName_, "context")) {
                ReadConfiguration.getOptions(c, _context);
            }
        }
        Options options_ = _context.getOptions();
        Forwards forwards_ = new Forwards(_stds, _context.getFileBuilder(), options_);
        for (Element c: _document.getDocumentElement().getChildElements()) {
            if (specific(_configuration,_context,c)) {
                continue;
            }
            String fieldName_ = c.getAttribute(FIELD);
            if (StringUtil.quickEq(fieldName_, "context")) {
                ok_ = ReadConfiguration.loadContext(c, _lgCode, stds,_configuration, _page, forwards_, _context);
            }
        }
        _context.setKo(!ok_);
        return new DualAnalyzedContext(forwards_,_page,_stds,_context, _file);
    }
    private static boolean specific(Configuration _configuration, DualConfigurationContext _context, Element _c) {
        String fieldName_ = _c.getAttribute(FIELD);
        if (StringUtil.quickEq(fieldName_, "lateValidators")) {
            _context.setupLateValidators(ReadConfiguration.loadStringMapString(_c));
            _configuration.setLateValidators(_context.getLateValidators());
            return true;
        }
        if (StringUtil.quickEq(fieldName_, "tabWidth")) {
            _configuration.setTabWidth(NumberUtil.parseInt(_c.getAttribute(VALUE)));
            return true;
        }
        if (StringUtil.quickEq(fieldName_, "filesConfName")) {
            _context.setFilesConfName(_c.getAttribute(VALUE));
            return true;
        }
        return false;
    }

    private void update(Configuration _configuration, Document _document, DualConfigurationContext _d) {
        for (Element c: _document.getDocumentElement().getChildElements()) {
            update(_configuration, _d, c);
        }
    }

    private void update(Configuration _configuration, DualConfigurationContext _d, Element _c) {
        String fieldName_ = _c.getAttribute(FIELD);
        if (StringUtil.quickEq(fieldName_, "firstUrl")) {
            _configuration.setFirstUrl(_c.getAttribute(VALUE));
            return;
        }
        if (StringUtil.quickEq(fieldName_, "prefix")) {
            _configuration.setPrefix(_c.getAttribute(VALUE));
            return;
        }
        if (StringUtil.quickEq(fieldName_, "messagesFolder")) {
            _d.setMessagesFolder(_c.getAttribute(VALUE));
            return;
        }
        if (StringUtil.quickEq(fieldName_, "beans")) {
            _configuration.setBeansInfos(ReadConfiguration.loadBeans(_c));
            return;
        }
        if (StringUtil.quickEq(fieldName_, "properties")) {
            _d.setProperties(ReadConfiguration.loadStringMapString(_c));
            return;
        }
        if (StringUtil.quickEq(fieldName_, "resources")) {
            _d.setAddedResources(ReadConfiguration.getStringList(_c));
            return;
        }
        if (StringUtil.quickEq(fieldName_, "navigation")) {
            _configuration.setNavigation(ReadConfiguration.loadStringMapStrings(_c));
            return;
        }

        if (StringUtil.quickEq(fieldName_, "addedFiles")) {
            _d.setAddedFiles(ReadConfiguration.getStringList(_c));
            return;
        }
        if (StringUtil.quickEq(fieldName_, "renderFiles")) {
            _d.setRenderFiles(ReadConfiguration.getStringList(_c));
        }
    }

}
