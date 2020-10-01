package code.formathtml;

import code.expressionlanguage.analyze.AbstractFileBuilder;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.DefaultConstantsCalculator;
import code.expressionlanguage.exec.ClassesCommon;
import code.expressionlanguage.analyze.files.CommentDelimiters;
import code.formathtml.structs.BeanInfo;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.DefaultInitializer;
import code.expressionlanguage.exec.DefaultLockingClass;
import code.expressionlanguage.analyze.errors.AnalysisMessages;
import code.expressionlanguage.options.ContextFactory;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.options.Options;
import code.formathtml.errors.RendAnalysisMessages;
import code.formathtml.errors.RendKeyWords;
import code.formathtml.exec.AdvancedFullStack;
import code.formathtml.util.BeanCustLgNames;
import code.formathtml.util.BeanLgNames;
import code.sml.Document;
import code.sml.Element;
import code.sml.ElementList;
import code.util.*;

public final class ReadConfiguration {

    private ReadConfiguration(){
    }
    public static AnalyzedPageEl load(Configuration _configuration, String _lgCode, Document _document, BeanLgNames _stds, RendAnalysisMessages _rend, AbstractFileBuilder _fileBuilder) {
        return _stds.load(_configuration,_lgCode,_document, _rend, _fileBuilder);
    }
    public static AnalyzedPageEl loadContext(Element _elt, String _lg, BeanCustLgNames _stds, Configuration _conf, RendAnalysisMessages _rend, AbstractFileBuilder _fileBuilder) {
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
        ClassesCommon com_ = new ClassesCommon();
        ContextEl context_ = ContextFactory.simpleBuild(stack_, lk_, di_, opt_, _stds, tab_, com_);
        AnalyzedPageEl page_ = AnalyzedPageEl.setInnerAnalyzing();
        ContextFactory.validateStds(a_, kw_, _stds, new CustList<CommentDelimiters>(), opt_, com_, new DefaultConstantsCalculator(_stds.getNbAlias()), _fileBuilder, _stds.getContent(), tab_, page_);
        _conf.setContext(context_);
        AnalysisMessages.validateMessageContents(_rend.allMessages(), page_);
        if (!page_.isEmptyMessageError()) {
            _conf.setContext(null);
            return page_;
        }
        StringMap<String> allTags_ = rkw_.allTags();
        rkw_.validateTagContents(allTags_, page_);
        rkw_.validateDuplicates(allTags_, page_);
        StringMap<String> allAttrs_ = rkw_.allAttrs();
        rkw_.validateAttrContents(allAttrs_, page_);
        rkw_.validateDuplicates(allAttrs_, page_);
        StringMap<String> allValues_ = rkw_.allValues();
        rkw_.validateValueContents(allValues_, page_);
        rkw_.validateDuplicates(allValues_, page_);
        StringMap<String> allStyleAttrs_ = rkw_.allStyleAttrs();
        rkw_.validateAttrContents(allStyleAttrs_, page_);
        rkw_.validateDuplicates(allStyleAttrs_, page_);
        StringMap<String> allSyleValues_ = rkw_.allStyleValues();
        rkw_.validateStyleValueContents(allSyleValues_, page_);
        rkw_.validateDuplicates(allSyleValues_, page_);
        StringMap<String> allStyleUnits_ = rkw_.allStyleUnits();
        rkw_.validateStyleUnitContents(allStyleUnits_, page_);
        rkw_.validateDuplicates(allStyleUnits_, page_);
        if (!page_.isEmptyStdError()) {
            _conf.setContext(null);
            return page_;
        }
        context_.setFullStack(new AdvancedFullStack(_conf));
        _conf.setContext(context_);
        return page_;
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
