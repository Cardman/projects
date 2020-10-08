package code.formathtml;

import code.expressionlanguage.analyze.AbstractFileBuilder;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.DefaultConstantsCalculator;
import code.expressionlanguage.analyze.files.CommentDelimiters;
import code.formathtml.structs.BeanInfo;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.errors.AnalysisMessages;
import code.expressionlanguage.options.ContextFactory;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.options.Options;
import code.formathtml.errors.RendAnalysisMessages;
import code.formathtml.errors.RendKeyWords;
import code.formathtml.util.BeanCustLgNames;
import code.formathtml.util.BeanLgNames;
import code.sml.Document;
import code.sml.Element;
import code.sml.ElementList;
import code.util.*;

public final class ReadConfiguration {

    private ReadConfiguration(){
    }

    public static ContextEl loadContext(Element _elt, String _lg, BeanCustLgNames _stds, Configuration _conf, RendAnalysisMessages _rend, AbstractFileBuilder _fileBuilder, AnalyzedPageEl _page) {
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
        _stds.buildAliases(_elt,_lg, rkw_,kw_, _rend,a_);
        int stack_ = -1;
        int tab_ = 4;
        for (Element c: _elt.getChildElements()) {
            String fieldName_ = c.getAttribute("field");
            if (StringList.quickEq(fieldName_, "stackOverFlow")) {
                stack_=(Numbers.parseInt(c.getAttribute("value")));
                continue;
            }
            if (StringList.quickEq(fieldName_, "tabWidth")) {
                tab_=(Numbers.parseInt(c.getAttribute("value")));
            }
        }
        ContextEl context_ = ContextFactory.simpleBuild(stack_, opt_, _stds, tab_);
        ContextFactory.validateStds(a_, kw_, _stds, new CustList<CommentDelimiters>(), opt_, context_.getClasses().getCommon(), new DefaultConstantsCalculator(_stds.getContent().getNbAlias()), _fileBuilder, _stds.getContent(), tab_, _page);
        AnalysisMessages.validateMessageContents(_rend.allMessages(), _page);
        if (!_page.isEmptyMessageError()) {
            return null;
        }
        StringMap<String> allTags_ = rkw_.allTags();
        rkw_.validateTagContents(allTags_, _page);
        rkw_.validateDuplicates(allTags_, _page);
        StringMap<String> allAttrs_ = rkw_.allAttrs();
        rkw_.validateAttrContents(allAttrs_, _page);
        rkw_.validateDuplicates(allAttrs_, _page);
        StringMap<String> allValues_ = rkw_.allValues();
        rkw_.validateValueContents(allValues_, _page);
        rkw_.validateDuplicates(allValues_, _page);
        StringMap<String> allStyleAttrs_ = rkw_.allStyleAttrs();
        rkw_.validateAttrContents(allStyleAttrs_, _page);
        rkw_.validateDuplicates(allStyleAttrs_, _page);
        StringMap<String> allSyleValues_ = rkw_.allStyleValues();
        rkw_.validateStyleValueContents(allSyleValues_, _page);
        rkw_.validateDuplicates(allSyleValues_, _page);
        StringMap<String> allStyleUnits_ = rkw_.allStyleUnits();
        rkw_.validateStyleUnitContents(allStyleUnits_, _page);
        rkw_.validateDuplicates(allStyleUnits_, _page);
        if (!_page.isEmptyStdError()) {
            return null;
        }
        return context_;
    }
    private static Options loadOptions(Element _elt) {
        Options options_ = new Options();
        options_.setReadOnly(true);
        for (Element c: _elt.getChildElements()) {
            String fieldName_ = c.getAttribute("field");
            if (StringList.quickEq(fieldName_, "classes")) {
                options_.getTypesInit().add(c.getAttribute("value"));
            }
        }
        return options_;
    }
    public static StringMap<BeanInfo> loadBeans(Element _elt) {
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

    public static StringMap<StringMap<String>> loadStringMapStrings(Element _elt) {
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
    public static StringMap<String> loadStringMapString(Element _elt) {
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
        return _elt.getAttribute("value");
    }
}
