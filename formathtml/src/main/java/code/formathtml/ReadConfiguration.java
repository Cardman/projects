package code.formathtml;

import code.bean.BeanInfo;
import code.bean.validator.Validator;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.DefaultInitializer;
import code.expressionlanguage.DefaultLockingClass;
import code.expressionlanguage.errors.AnalysisMessages;
import code.expressionlanguage.errors.stds.StdWordError;
import code.expressionlanguage.options.ContextFactory;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.options.Options;
import code.formathtml.errors.RendAnalysisMessages;
import code.formathtml.errors.RendKeyWords;
import code.formathtml.util.BeanCustLgNames;
import code.formathtml.util.BeanLgNames;
import code.formathtml.util.BeanNatLgNames;
import code.sml.Document;
import code.sml.Element;
import code.sml.ElementList;
import code.util.*;

public final class ReadConfiguration {

    private ReadConfiguration(){
    }
    public static void load(Configuration _configuration, String _lgCode,Document _document) {
        BeanLgNames stds_ = _configuration.getAdvStandards();
        boolean found_ = false;
        for (Element c: _document.getDocumentElement().getChildElements()) {
            String fieldName_ = c.getAttribute("field");
            if (StringList.quickEq(fieldName_, "firstUrl")) {
                _configuration.setFirstUrl(c.getAttribute("value"));
                continue;
            }
            if (StringList.quickEq(fieldName_, "prefix")) {
                _configuration.setPrefix(c.getAttribute("value"));
                continue;
            }
            if (StringList.quickEq(fieldName_, "messagesFolder")) {
                _configuration.setMessagesFolder(c.getAttribute("value"));
                continue;
            }
            if (StringList.quickEq(fieldName_, "dataBaseClassName")) {
                _configuration.setDataBaseClassName(c.getAttribute("value"));
                continue;
            }
            if (stds_ instanceof BeanNatLgNames && StringList.quickEq(fieldName_, "validators")) {
                _configuration.setValidators(loadValidator(c, (BeanNatLgNames) stds_));
                continue;
            }
            if (StringList.quickEq(fieldName_, "lateValidators")) {
                _configuration.setLateValidators(loadStringMapString(c));
                continue;
            }
            if (StringList.quickEq(fieldName_, "beans")) {
                _configuration.setBeansInfos(loadBeans(c));
                continue;
            }
            if (StringList.quickEq(fieldName_, "properties")) {
                _configuration.setProperties(loadStringMapString(c));
                continue;
            }
            if (StringList.quickEq(fieldName_, "navigation")) {
                _configuration.setNavigation(loadStringMapStrings(c));
                continue;
            }
            if (StringList.quickEq(fieldName_, "tabWidth")) {
                _configuration.setTabWidth(Numbers.parseInt(c.getAttribute("value")));
                continue;
            }
            if (StringList.quickEq(fieldName_, "filesConfName")) {
                _configuration.setFilesConfName(c.getAttribute("value"));
                continue;
            }
            if (stds_ instanceof BeanCustLgNames &&StringList.quickEq(fieldName_, "context")) {
                found_ = true;
                loadContext(c, _lgCode, (BeanCustLgNames) stds_,_configuration);
                continue;
            }
            if (StringList.quickEq(fieldName_, "addedFiles")) {
                _configuration.setAddedFiles(getStringList(c));
                continue;
            }
            if (StringList.quickEq(fieldName_, "renderFiles")) {
                _configuration.setRenderFiles(getStringList(c));
            }
        }
        if (!found_ && stds_ instanceof BeanNatLgNames) {
            ((BeanNatLgNames)stds_).setupNative(_configuration);
        }
    }
    private static void loadContext(Element _elt, String _lg, BeanCustLgNames _stds, Configuration _conf) {
        DefaultLockingClass lk_ = new DefaultLockingClass();
        DefaultInitializer di_ = new DefaultInitializer();
        AnalysisMessages a_ = new AnalysisMessages();
        KeyWords kw_ = new KeyWords();
        Options opt_ = new Options();
        opt_.setReadOnly(true);
        for (Element c: _elt.getChildElements()) {
            String fieldName_ = c.getAttribute("field");
            if (StringList.quickEq(fieldName_, "options")) {
                opt_ = loadOptions(c);
            }
        }
        RendKeyWords rkw_ = _conf.getRendKeyWords();
        RendAnalysisMessages rMess_ = _conf.getRendAnalysisMessages();
        _stds.buildAliases(_elt,_lg, rkw_,kw_,rMess_,a_);
        ContextEl context_ = ContextFactory.build(-1,lk_, di_, opt_, a_, kw_, _stds,4);
        for (Element c: _elt.getChildElements()) {
            String fieldName_ = c.getAttribute("field");
            if (StringList.quickEq(fieldName_, "stackOverFlow")) {
                context_.setStackOverFlow(Numbers.parseInt(c.getAttribute("value")));
                continue;
            }
            if (StringList.quickEq(fieldName_, "tabWidth")) {
                context_.setTabWidth(Numbers.parseInt(c.getAttribute("value")));
            }
        }
        context_.setOptions(opt_);
        _conf.setContext(context_);
        AnalysisMessages.validateMessageContents(context_, rMess_.allMessages());
        if (!context_.getClasses().isEmptyMessageError()) {
            _conf.setContext(null);
            return;
        }
        StringMap<String> allTags_ = rkw_.allTags();
        rkw_.validateTagContents(_conf,allTags_);
        rkw_.validateDuplicates(_conf,allTags_);
        StringMap<String> allAttrs_ = rkw_.allAttrs();
        rkw_.validateAttrContents(_conf,allAttrs_);
        rkw_.validateDuplicates(_conf,allAttrs_);
        StringMap<String> allValues_ = rkw_.allValues();
        rkw_.validateValueContents(_conf,allValues_);
        rkw_.validateDuplicates(_conf,allValues_);
        StringMap<String> allStyleAttrs_ = rkw_.allStyleAttrs();
        rkw_.validateAttrContents(_conf,allStyleAttrs_);
        rkw_.validateDuplicates(_conf,allStyleAttrs_);
        StringMap<String> allSyleValues_ = rkw_.allStyleValues();
        rkw_.validateStyleValueContents(_conf,allSyleValues_);
        rkw_.validateDuplicates(_conf,allSyleValues_);
        StringMap<String> allStyleUnits_ = rkw_.allStyleUnits();
        rkw_.validateStyleUnitContents(_conf,allStyleUnits_);
        rkw_.validateDuplicates(_conf,allStyleUnits_);
        for (StdWordError s: _conf.getStdErrorDet()) {
            context_.getClasses().addStdError(s);
        }
        if (!context_.getClasses().isEmptyStdError()) {
            _conf.setContext(null);
            return;
        }
        context_.setExecutingInstance(_conf);
        _conf.setContext(context_);
    }
    private static Options loadOptions(Element _elt) {
        Options options_ = new Options();
        options_.setReadOnly(true);
        for (Element c: _elt.getChildElements()) {
            String fieldName_ = c.getAttribute("field");
            if (StringList.quickEq(fieldName_, "initializeStaticClassFirst")) {
                options_.setInitializeStaticClassFirst(StringList.quickEq(c.getAttribute("value"), "true"));
                continue;
            }
            if (StringList.quickEq(fieldName_, "classes")) {
                options_.getTypesInit().add(c.getAttribute("value"));
            }
        }
        return options_;
    }
    private static StringMap<BeanInfo> loadBeans(Element _elt) {
        StringMap<BeanInfo> beans_ = new StringMap<BeanInfo>();
        StringList keys_ = new StringList();
        CustList<BeanInfo> values_ = new CustList<BeanInfo>();
        for (Element c: _elt.getChildElements()) {
            if (c.hasAttribute("key")) {
                keys_.add(c.getAttribute("value"));
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
            String fieldName_ = c.getAttribute("field");
            if (StringList.quickEq(fieldName_, "scope")) {
                bean_.setScope(c.getAttribute("value"));
                continue;
            }
            if (StringList.quickEq(fieldName_, "className")) {
                bean_.setClassName(c.getAttribute("value"));
            }
        }
        return bean_;
    }
    private static StringMap<Validator> loadValidator(Element _elt, BeanNatLgNames _stds) {
        StringMap<Validator> validators_ = new StringMap<Validator>();
        int i_ = 0;
        String key_ = "";
        for (Element c: _elt.getChildElements()) {
            if (i_ % 2 == 0) {
                key_ = c.getAttribute("value");
            } else {
                validators_.put(key_, _stds.buildValidator(c));
            }
            i_++;
        }
        return validators_;
    }
    private static StringMap<StringMap<String>> loadStringMapStrings(Element _elt) {
        StringMap<StringMap<String>> navigation_;
        navigation_ = new StringMap<StringMap<String>>();
        StringList keys_ = new StringList();
        CustList<StringMap<String>> values_ = new CustList<StringMap<String>>();
        for (Element c: _elt.getChildElements()) {
            if (c.hasAttribute("key")) {
                keys_.add(c.getAttribute("value"));
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
    private static StringMap<String> loadStringMapString(Element _elt) {
        StringMap<String> map_ = new StringMap<String>();
        StringList keys_ = new StringList();
        StringList values_ = new StringList();
        for (Element c: _elt.getChildElements()) {
            if (c.hasAttribute("key")) {
                keys_.add(c.getAttribute("value"));
            } else {
                values_.add(c.getAttribute("value"));
            }
        }
        int len_ = keys_.size();
        for (int i = 0; i < len_; i++) {
            map_.put(keys_.get(i), values_.get(i));
        }
        return map_;
    }

    private static StringList getStringList(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        StringList list_ = new StringList(new CollCapacity(len_));
        for (Element c: childElements_) {
            list_.add(getString(c));
        }
        return list_;
    }
    static String getString(Element _elt) {
        return _elt.getAttribute("value");
    }
}
