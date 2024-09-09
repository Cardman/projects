package code.formathtml.util;

import code.expressionlanguage.analyze.AbstractFileBuilder;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.blocks.FileBlock;
import code.expressionlanguage.fwd.Forwards;
import code.expressionlanguage.options.Options;
import code.expressionlanguage.stds.LoggableLgNames;
import code.formathtml.Configuration;
import code.formathtml.ReadConfiguration;
import code.sml.Document;
import code.sml.Element;
import code.util.core.NumberUtil;
import code.util.core.StringUtil;

public final class DefaultConfigurationLoader {
    private final BeanCustLgNames stds;
    private final LoggableLgNames log;

    public DefaultConfigurationLoader(BeanCustLgNames _stds, LoggableLgNames _loggable) {
        this.stds = _stds;
        log = _loggable;
    }

    public DualAnalyzedContext load(Configuration _configuration, String _lgCode, Document _document, AbstractFileBuilder _fileBuilder, AnalyzedPageEl _page, FileBlock _file) {
        DualConfigurationContext d_ = new DualConfigurationContext();
        d_.setFileBuilder(_fileBuilder);
        return specificLoad(_configuration,_lgCode,_document, _page, d_, _file);
    }

    public DualAnalyzedContext specificLoad(Configuration _configuration, String _lgCode, Document _document, AnalyzedPageEl _page, DualConfigurationContext _context, FileBlock _file) {
        update(_configuration,_document, _context);

        boolean ok_ = false;
        for (Element c: _document.getDocumentElement().getChildElements()) {
            String fieldName_ = c.getAttribute(ReadConfiguration.FIELD);
            if (StringUtil.quickEq(fieldName_, ReadConfiguration.CONTEXT)) {
                ReadConfiguration.getOptions(c, _context);
            }
        }
        Options options_ = _context.getOptions();
        Forwards forwards_ = new Forwards(stds, log, _context.getFileBuilder(), options_);
        for (Element c: _document.getDocumentElement().getChildElements()) {
            if (specific(_configuration,_context,c)) {
                continue;
            }
            String fieldName_ = c.getAttribute(ReadConfiguration.FIELD);
            if (StringUtil.quickEq(fieldName_, ReadConfiguration.CONTEXT)) {
                ok_ = ReadConfiguration.loadContext(c, _lgCode, stds,_configuration, _page, forwards_, _context);
            }
        }
        _context.setKo(!ok_);
        return new DualAnalyzedContext(forwards_,_page,stds,_context, _file);
    }
    private static boolean specific(Configuration _configuration, DualConfigurationContext _context, Element _c) {
        String fieldName_ = _c.getAttribute(ReadConfiguration.FIELD);
        if (StringUtil.quickEq(fieldName_, ReadConfiguration.LATE_VALIDATORS)) {
            _context.setupLateValidators(ReadConfiguration.loadStringMapString(_c));
            _configuration.setLateValidators(_context.getLateValidators());
            return true;
        }
        if (StringUtil.quickEq(fieldName_, ReadConfiguration.LATE_REINIT)) {
            _context.setupLateReinit(ReadConfiguration.loadStringMapString(_c));
            _configuration.setLateReinits(_context.getLateReinit());
            return true;
        }
        if (StringUtil.quickEq(fieldName_, ReadConfiguration.TAB_WIDTH)) {
            _configuration.setTabWidth(NumberUtil.parseInt(_c.getAttribute(ReadConfiguration.VALUE)));
            return true;
        }
        if (StringUtil.quickEq(fieldName_, ReadConfiguration.FILES_CONF_NAME)) {
            _context.setFilesConfName(_c.getAttribute(ReadConfiguration.VALUE));
            return true;
        }
        if (StringUtil.quickEq(fieldName_, ReadConfiguration.INIT_DB)) {
            String att_ = _c.getAttribute(ReadConfiguration.VALUE);
            int last_ = att_.lastIndexOf('.');
            if (last_ > -1) {
                _context.setInitNameClass(att_.substring(0,last_));
                _context.setInitNameMethod(att_.substring(last_+1));
                return true;
            }
        }
        return false;
    }

    private void update(Configuration _configuration, Document _document, DualConfigurationContext _d) {
        for (Element c: _document.getDocumentElement().getChildElements()) {
            update(_configuration, _d, c);
        }
    }

    private void update(Configuration _configuration, DualConfigurationContext _d, Element _c) {
        String fieldName_ = _c.getAttribute(ReadConfiguration.FIELD);
        if (StringUtil.quickEq(fieldName_, ReadConfiguration.FIRST_URL)) {
            _configuration.setFirstUrl(_c.getAttribute(ReadConfiguration.VALUE));
            return;
        }
        if (StringUtil.quickEq(fieldName_, ReadConfiguration.PREFIX)) {
            _configuration.setPrefix(_c.getAttribute(ReadConfiguration.VALUE));
            return;
        }
        if (StringUtil.quickEq(fieldName_, ReadConfiguration.MESSAGES_FOLDER)) {
            _d.setMessagesFolder(_c.getAttribute(ReadConfiguration.VALUE));
            return;
        }
        if (StringUtil.quickEq(fieldName_, ReadConfiguration.BEANS)) {
            _configuration.setBeansInfos(ReadConfiguration.loadBeans(_c));
            return;
        }
        if (StringUtil.quickEq(fieldName_, ReadConfiguration.PROPERTIES)) {
            _d.setProperties(ReadConfiguration.loadStringMapString(_c));
            return;
        }
        if (StringUtil.quickEq(fieldName_, ReadConfiguration.RESOURCES)) {
            _d.setAddedResources(ReadConfiguration.getStringList(_c));
            return;
        }
        if (StringUtil.quickEq(fieldName_, ReadConfiguration.NAVIGATION)) {
            _configuration.setNavigation(ReadConfiguration.loadStringMapStrings(_c));
            return;
        }

        if (StringUtil.quickEq(fieldName_, ReadConfiguration.ADDED_FILES)) {
            _d.setAddedFiles(ReadConfiguration.getStringList(_c));
            return;
        }
        if (StringUtil.quickEq(fieldName_, ReadConfiguration.RENDER_FILES)) {
            _d.setRenderFiles(ReadConfiguration.getStringList(_c));
        }
    }

}
