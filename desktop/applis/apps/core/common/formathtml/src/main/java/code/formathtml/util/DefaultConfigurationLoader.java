package code.formathtml.util;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.fwd.Forwards;
import code.expressionlanguage.options.Options;
import code.formathtml.Configuration;
import code.formathtml.ReadConfiguration;
import code.sml.Document;
import code.sml.Element;
import code.util.core.NumberUtil;
import code.util.core.StringUtil;

public final class DefaultConfigurationLoader extends AbstractConfigurationLoader {
    private final BeanCustLgNames stds;

    public DefaultConfigurationLoader(BeanCustLgNames _stds) {
        this.stds = _stds;
    }

    @Override
    public DualAnalyzedContext specificLoad(Configuration _configuration, String _lgCode, Document _document, AnalyzedPageEl _page, BeanLgNames _stds, DualConfigurationContext _context) {
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
        return new DualAnalyzedContext(forwards_,_page,_stds,_context);
    }
}
