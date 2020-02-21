package code.formathtml;

import code.bean.BeanInfo;
import code.expressionlanguage.AnalyzedPageEl;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.calls.util.CallingState;
import code.expressionlanguage.methods.Classes;
import code.expressionlanguage.opers.util.ClassField;
import code.expressionlanguage.options.Options;
import code.expressionlanguage.structs.FieldableStruct;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.variables.VariableSuffix;
import code.formathtml.util.BeanLgNames;
import code.util.StringList;
import code.util.StringMap;

import static org.junit.Assert.assertTrue;

public abstract class CommonRender {

    static void addImportingPage(Configuration _conf) {
        _conf.addPage(new ImportingPage());
        _conf.getContext().setAnalyzing(new AnalyzedPageEl());
    }

    static void setLocale(String _locale, Configuration _conf) {
        _conf.setCurrentLanguage(_locale);
        _conf.getAnalyzingDoc().setLanguages(new StringList(_locale));
    }
    static Navigation newNavigation(Configuration _conf) {
        Navigation nav_ = new Navigation();
        nav_.setSession(_conf);
        ContextEl context_ = _conf.getContext();
        BeanLgNames standards_ = (BeanLgNames) context_.getStandards();
        nav_.getSession().setStandards(standards_);
        context_.setExecutingInstance(nav_.getSession());
        return nav_;
    }
    Configuration contextElSec() {
        StringMap<String> files_ = new StringMap<String>();
        return contextElSec(files_);
    }
    Configuration contextElSec(StringMap<String> _files) {
        Configuration conf_ =  EquallableExUtil.newConfiguration();
        conf_.setPrefix("c:");
        Options opt_ = new Options();
        opt_.setEndLineSemiColumn(false);
        opt_.setSuffixVar(VariableSuffix.DISTINCT);
        ContextEl cont_ = InitializationLgNames.buildStdOne(opt_);
        Classes.validateAll(_files, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        conf_.setContext(cont_);
        BeanLgNames standards_ = (BeanLgNames) cont_.getStandards();
        conf_.setStandards(standards_);
        cont_.setExecutingInstance(conf_);
        standards_.buildIterables(conf_);
        return conf_;
    }
    Configuration contextElThird() {
        return contextElThird(new StringMap<String>());
    }
    Configuration contextElThird(StringMap<String> _files) {
        Configuration conf_ =  EquallableExUtil.newConfiguration();
        conf_.setPrefix("c:");
        Options opt_ = new Options();
        opt_.setEndLineSemiColumn(false);
        opt_.setSuffixVar(VariableSuffix.DISTINCT);
        ContextEl cont_ = InitializationLgNames.buildStdThree(opt_);
        Classes.validateAll(_files, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        conf_.setContext(cont_);
        BeanLgNames standards_ = (BeanLgNames) cont_.getStandards();
        conf_.setStandards(standards_);
        cont_.setExecutingInstance(conf_);
        standards_.buildIterables(conf_);
        return conf_;
    }
    Configuration contextElFourth() {
        return contextElFourth(new StringMap<String>());
    }
    Configuration contextElFourth(StringMap<String> _files) {
        Configuration conf_ =  EquallableExUtil.newConfiguration();
        conf_.setPrefix("c:");
        Options opt_ = new Options();
        opt_.setEndLineSemiColumn(false);
        opt_.setSuffixVar(VariableSuffix.DISTINCT);
        opt_.setSingleInnerParts(true);
        ContextEl cont_ = InitializationLgNames.buildStdThree(opt_);
        Classes.validateAll(_files, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        conf_.setContext(cont_);
        BeanLgNames standards_ = (BeanLgNames) cont_.getStandards();
        conf_.setStandards(standards_);
        cont_.setExecutingInstance(conf_);
        standards_.buildIterables(conf_);
        return conf_;
    }
    static void addBeanInfo(Configuration _conf, String _id,Struct _str) {
        BeanInfo b_ = new BeanInfo();
        b_.setClassName(_str.getClassName(_conf));
        _conf.getBeansInfos().addEntry(_id,b_);
        _conf.getBuiltBeans().addEntry(_id,_str);
    }
    protected static Struct getStruct(Struct _struct, ClassField _cl) {
        return ((FieldableStruct) _struct).getEntryStruct(_cl).getValue();
    }
    protected static void setStruct(Struct _struct, ClassField _cl, Struct _value) {
        ((FieldableStruct) _struct).getEntryStruct(_cl).setValue(_value);
    }
    protected static Struct getException(Configuration _cont) {
        CallingState str_ = _cont.getContext().getCallingState();
        if (str_ instanceof Struct) {
            return (Struct) str_;
        }
        return null;
    }
}
