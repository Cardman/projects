package code.formathtml;

import code.expressionlanguage.AnalyzedPageEl;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.methods.Classes;
import code.expressionlanguage.options.Options;
import code.expressionlanguage.variables.VariableSuffix;
import code.formathtml.util.BeanLgNames;
import code.util.StringMap;

import static org.junit.Assert.assertTrue;

public abstract class CommonRender {

    static void addImportingPage(Configuration _conf) {
        _conf.addPage(new ImportingPage(false));
        _conf.getContext().setAnalyzing(new AnalyzedPageEl());
    }
    Configuration contextEl() {
        StringMap<String> files_ = new StringMap<String>();
        return contextEl(files_);
    }
    Configuration contextEl(StringMap<String> _files) {
        Configuration conf_ =  EquallableExUtil.newConfiguration();
        Options opt_ = new Options();
        opt_.setEndLineSemiColumn(false);
        opt_.setSuffixVar(VariableSuffix.DISTINCT);
        ContextEl cont_ = InitializationLgNames.buildStdTwo(opt_);
        Classes.validateAll(_files, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        conf_.setContext(cont_);
        BeanLgNames standards_ = (BeanLgNames) cont_.getStandards();
        conf_.setStandards(standards_);
        standards_.buildIterables(conf_);
        return conf_;
    }
    Configuration contextElSec() {
        StringMap<String> files_ = new StringMap<String>();
        return contextElSec(files_);
    }
    Configuration contextElSec(StringMap<String> _files) {
        Configuration conf_ =  EquallableExUtil.newConfiguration();
        Options opt_ = new Options();
        opt_.setEndLineSemiColumn(false);
        opt_.setSuffixVar(VariableSuffix.DISTINCT);
        ContextEl cont_ = InitializationLgNames.buildStdOne(opt_);
        Classes.validateAll(_files, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        conf_.setContext(cont_);
        BeanLgNames standards_ = (BeanLgNames) cont_.getStandards();
        conf_.setStandards(standards_);
        standards_.buildIterables(conf_);
        return conf_;
    }
    Configuration contextElThird(StringMap<String> _files) {
        Configuration conf_ =  EquallableExUtil.newConfiguration();
        Options opt_ = new Options();
        opt_.setEndLineSemiColumn(false);
        opt_.setSuffixVar(VariableSuffix.DISTINCT);
        ContextEl cont_ = InitializationLgNames.buildStdThree(opt_);
        Classes.validateAll(_files, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        conf_.setContext(cont_);
        BeanLgNames standards_ = (BeanLgNames) cont_.getStandards();
        conf_.setStandards(standards_);
        standards_.buildIterables(conf_);
        return conf_;
    }
}
