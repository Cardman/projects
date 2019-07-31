package code.formathtml;

import code.bean.BeanInfo;
import code.bean.validator.Validator;
import code.expressionlanguage.AnalyzedPageEl;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.DefaultInitializer;
import code.expressionlanguage.DefaultLockingClass;
import code.expressionlanguage.options.ContextFactory;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.options.Options;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.variables.VariableSuffix;
import code.formathtml.util.BeanLgNames;
import code.formathtml.util.BeanNatLgNames;
import code.sml.Document;
import code.sml.Element;
import code.sml.ElementList;
import code.util.*;

public final class ReadConfiguration {

    private ReadConfiguration(){
    }
    public static void load(Configuration _configuration, Document _document) {
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
            if (StringList.quickEq(fieldName_, "validators")) {
                _configuration.setValidators(loadValidator(c, stds_));
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
            if (StringList.quickEq(fieldName_, "context")) {
                found_ = true;
                _configuration.setContext(loadContext(c, stds_));
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
            DefaultLockingClass lk_ = new DefaultLockingClass();
            DefaultInitializer di_ = new DefaultInitializer();
            KeyWords kw_ = new KeyWords();
            ContextEl context_ = ContextFactory.build(-1,lk_, di_, new Options(), kw_, stds_,4);
            context_.getOptions().setEndLineSemiColumn(false);
            context_.getOptions().setSuffixVar(VariableSuffix.DISTINCT);
            context_.setStandards(stds_);
            _configuration.setContext(context_);
            context_.setAnalyzing(new AnalyzedPageEl());
        }
    }
    static ContextEl loadContext(Element _elt, LgNames _stds) {
        DefaultLockingClass lk_ = new DefaultLockingClass();
        DefaultInitializer di_ = new DefaultInitializer();
        KeyWords kw_ = new KeyWords();
        ContextEl context_ = ContextFactory.build(-1,lk_, di_, new Options(), kw_, _stds,4);
        for (Element c: _elt.getChildElements()) {
            String fieldName_ = c.getAttribute("field");
            if (StringList.quickEq(fieldName_, "stackOverFlow")) {
                context_.setStackOverFlow(Numbers.parseInt(c.getAttribute("value")));
                continue;
            }
            if (StringList.quickEq(fieldName_, "tabWidth")) {
                context_.setTabWidth(Numbers.parseInt(c.getAttribute("value")));
                continue;
            }
            if (StringList.quickEq(fieldName_, "options")) {
                context_.setOptions(loadOptions(c));
                continue;
            }
        }
        return context_;
    }
    static Options loadOptions(Element _elt) {
        Options options_ = new Options();
        for (Element c: _elt.getChildElements()) {
            String fieldName_ = c.getAttribute("field");
            if (StringList.quickEq(fieldName_, "initializeStaticClassFirst")) {
                options_.setInitializeStaticClassFirst(StringList.quickEq(c.getAttribute("value"), "true"));
                continue;
            }
            if (StringList.quickEq(fieldName_, "suffixVar")) {
                options_.setSuffixVar(VariableSuffix.getVariableSuffixByName(c.getAttribute("value")));
                continue;
            }
        }
        return options_;
    }
    static StringMap<BeanInfo> loadBeans(Element _elt) {
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
    static BeanInfo loadBean(Element _elt) {
        BeanInfo bean_ = new BeanInfo();
        for (Element c: _elt.getChildElements()) {
            String fieldName_ = c.getAttribute("field");
            if (StringList.quickEq(fieldName_, "scope")) {
                bean_.setScope(c.getAttribute("value"));
                continue;
            }
            if (StringList.quickEq(fieldName_, "className")) {
                bean_.setClassName(c.getAttribute("value"));
                continue;
            }
        }
        return bean_;
    }
    static StringMap<Validator> loadValidator(Element _elt, BeanLgNames _stds) {
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
    static StringMap<StringMap<String>> loadStringMapStrings(Element _elt) {
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
    static StringMap<String> loadStringMapString(Element _elt) {
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

    static StringList getStringList(Element _elt) {
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
