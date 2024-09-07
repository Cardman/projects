package code.formathtml;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.files.CommentDelimiters;
import code.expressionlanguage.fwd.Forwards;
import code.expressionlanguage.options.AnalysisElementsBase;
import code.formathtml.structs.BeanInfo;
import code.expressionlanguage.analyze.errors.AnalysisMessages;
import code.expressionlanguage.options.ContextFactory;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.options.Options;
import code.formathtml.errors.RendKeyWords;
import code.formathtml.util.BeanCustLgNames;
import code.formathtml.util.DualConfigurationContext;
import code.sml.Element;
import code.sml.ElementList;
import code.util.*;
import code.util.core.NumberUtil;
import code.util.core.StringUtil;

public final class ReadConfiguration {

    public static final String FIELD = "0";
    public static final String VALUE = "1";
    public static final String KEY = "2";
    public static final String OPTIONS = "0";
    public static final String STACK_OVER_FLOW = "1";
    public static final String TAB_WIDTH = "2";
    public static final String CLASSES = "0";
    public static final String SCOPE = "0";
    public static final String CLASS_NAME = "1";
    public static final String CONTEXT = "0";
    public static final String LATE_VALIDATORS = "1";
    public static final String FILES_CONF_NAME = "3";
    public static final String FIRST_URL = "4";
    public static final String PREFIX = "5";
    public static final String MESSAGES_FOLDER = "6";
    public static final String BEANS = "7";
    public static final String PROPERTIES = "8";
    public static final String RESOURCES = "9";
    public static final String NAVIGATION = "10";
    public static final String ADDED_FILES = "11";
    public static final String RENDER_FILES = "12";
    public static final String LATE_REINIT = "13";

    private ReadConfiguration(){
    }

    public static boolean loadContext(Element _elt, String _lg, BeanCustLgNames _stds, Configuration _conf, AnalyzedPageEl _page, Forwards _forwards, DualConfigurationContext _context) {
        KeyWords kw_ = new KeyWords();
        AnalysisMessages a_ = new AnalysisMessages();
        RendKeyWords rkw_ = _conf.getRendKeyWords();
        _stds.buildAliases(_elt,_lg, rkw_,kw_, _context.getAnalysisMessages(),a_);
        _page.setLogErr(_forwards);
        AnalysisMessages.validateMessageContents(a_.allMessages(_stds.mappingMessages()), _page);
        AnalysisMessages.validateMessageContents(_context.getAnalysisMessages().allMessages(_stds.mappingRendMessages()), _page);
        if (!_page.isEmptyMessageError()) {
            return false;
        }
        kw_.initSupplDigits();
        _page.setMappingKeyWords(_stds.mappingKeywords());
        _page.setMappingAliases(_stds.mappingAliases());
        ContextFactory.validateStds(new AnalysisElementsBase(_forwards, a_, kw_, new CustList<CommentDelimiters>(), _forwards.getOptions(), _stds.getContent(), _page));
        return loadContext(_page, rkw_, _stds);
    }

    public static boolean loadContext(AnalyzedPageEl _page, RendKeyWords _rkw, BeanCustLgNames _std) {
        StringMap<String> allTags_ = _rkw.allTags(_std.mappingRendKeywords());
        _rkw.validateTagContents(allTags_, _page);
        _rkw.validateDuplicates(allTags_, _page);
        StringMap<String> mappingAttrs_ = _std.mappingAttrs();
        StringMap<String> allAttrs_ = _rkw.allAttrs(mappingAttrs_);
        _rkw.validateAttrContents(allAttrs_, _page, mappingAttrs_);
        _rkw.validateDuplicates(allAttrs_, _page);
        StringMap<String> allValues_ = _rkw.allValues(_std.mappingValues());
        _rkw.validateValueContents(allValues_, _page);
        _rkw.validateDuplicates(allValues_, _page);
        StringMap<String> allStyleAttrs_ = _rkw.allStyleAttrs(_std.mappingStyleAttrs());
        _rkw.validateAttrContents(allStyleAttrs_, _page, mappingAttrs_);
        _rkw.validateDuplicates(allStyleAttrs_, _page);
        StringMap<String> allSyleValues_ = _rkw.allStyleValues(_std.mappingStyleValues());
        _rkw.validateStyleValueContents(allSyleValues_, _page);
        _rkw.validateDuplicates(allSyleValues_, _page);
        StringMap<String> allStyleUnits_ = _rkw.allStyleUnits(_std.mappingStyleUnits());
        _rkw.validateStyleUnitContents(allStyleUnits_, _page);
        _rkw.validateDuplicates(allStyleUnits_, _page);
        return _page.isEmptyStdError();
    }

    public static Options getOptions(Element _elt, DualConfigurationContext _context) {
        Options opt_ = _context.getOptions();
        opt_.setReadOnly(true);
        for (Element c: _elt.getChildElements()) {
            String fieldName_ = c.getAttribute(FIELD);
            if (StringUtil.quickEq(fieldName_, OPTIONS)) {
                loadOptions(c, opt_);
            }
        }
        int stack_ = -1;
        int tab_ = 4;
        for (Element c: _elt.getChildElements()) {
            String fieldName_ = c.getAttribute(FIELD);
            if (StringUtil.quickEq(fieldName_, STACK_OVER_FLOW)) {
                stack_=(NumberUtil.parseInt(c.getAttribute(VALUE)));
                continue;
            }
            if (StringUtil.quickEq(fieldName_, TAB_WIDTH)) {
                tab_=(NumberUtil.parseInt(c.getAttribute(VALUE)));
            }
        }
        opt_.setStack(stack_);
        opt_.setTabWidth(tab_);
        return opt_;
    }

    private static void loadOptions(Element _elt, Options _opt) {
        _opt.setReadOnly(true);
        for (Element c: _elt.getChildElements()) {
            String fieldName_ = c.getAttribute(FIELD);
            if (StringUtil.quickEq(fieldName_, CLASSES)) {
                _opt.getTypesInit().add(c.getAttribute(VALUE));
            }
        }
    }
    public static StringMap<BeanInfo> loadBeans(Element _elt) {
        StringMap<BeanInfo> beans_ = new StringMap<BeanInfo>();
        StringList keys_ = new StringList();
        CustList<BeanInfo> values_ = new CustList<BeanInfo>();
        for (Element c: _elt.getChildElements()) {
            if (c.hasAttribute(KEY)) {
                keys_.add(c.getAttribute(VALUE));
            } else {
                values_.add(loadBean(c));
            }
        }
        int len_ = keys_.size();
        for (int i = 0; i < len_; i++) {
            beans_.put(keys_.get(i), values_.get(i));
        }
        return beans_;
    }
    private static BeanInfo loadBean(Element _elt) {
        BeanInfo bean_ = new BeanInfo();
        for (Element c: _elt.getChildElements()) {
            String fieldName_ = c.getAttribute(FIELD);
            if (StringUtil.quickEq(fieldName_, SCOPE)) {
                bean_.setScope(c.getAttribute(VALUE));
                continue;
            }
            if (StringUtil.quickEq(fieldName_, CLASS_NAME)) {
                bean_.setClassName(c.getAttribute(VALUE));
            }
        }
        return bean_;
    }

    public static StringMap<StringMap<String>> loadStringMapStrings(Element _elt) {
        StringMap<StringMap<String>> navigation_;
        navigation_ = new StringMap<StringMap<String>>();
        StringList keys_ = new StringList();
        CustList<StringMap<String>> values_ = new CustList<StringMap<String>>();
        for (Element c: _elt.getChildElements()) {
            if (c.hasAttribute(KEY)) {
                keys_.add(c.getAttribute(VALUE));
            } else {
                values_.add(loadStringMapString(c));
            }
        }
        int len_ = keys_.size();
        for (int i = 0; i < len_; i++) {
            navigation_.put(keys_.get(i), values_.get(i));
        }
        return navigation_;
    }
    public static StringMap<String> loadStringMapString(Element _elt) {
        StringMap<String> map_ = new StringMap<String>();
        StringList keys_ = new StringList();
        StringList values_ = new StringList();
        for (Element c: _elt.getChildElements()) {
            if (c.hasAttribute(KEY)) {
                keys_.add(c.getAttribute(VALUE));
            } else {
                values_.add(c.getAttribute(VALUE));
            }
        }
        int len_ = keys_.size();
        for (int i = 0; i < len_; i++) {
            map_.put(keys_.get(i), values_.get(i));
        }
        return map_;
    }

    public static StringList getStringList(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        StringList list_ = new StringList(new CollCapacity(len_));
        for (Element c: childElements_) {
            list_.add(getString(c));
        }
        return list_;
    }
    public static String getString(Element _elt) {
        return _elt.getAttribute(VALUE);
    }
}
