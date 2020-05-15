package code.formathtml;

import code.bean.BeanInfo;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.calls.util.CallingState;
import code.expressionlanguage.methods.Classes;
import code.expressionlanguage.opers.util.ClassField;
import code.expressionlanguage.options.Options;
import code.expressionlanguage.structs.FieldableStruct;
import code.expressionlanguage.structs.Struct;
import code.formathtml.exec.RendDynOperationNode;
import code.formathtml.util.*;
import code.sml.Document;
import code.sml.DocumentBuilder;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;

import static org.junit.Assert.assertTrue;

public abstract class CommonRender {

    protected static void addImportingPage(Configuration _conf) {
        _conf.addPage(new ImportingPage());
        _conf.getContext().setAnalyzing();
        _conf.getAnalyzing().setProcessKeyWord(new AdvancedProcessKeyWord(_conf));
        _conf.getAnalyzing().setHiddenTypes(new AdvancedHiddenTypes(_conf));
        _conf.getAnalyzing().setCurrentGlobalBlock(new AdvancedCurrentGlobalBlock(_conf));
        _conf.getAnalyzing().setCurrentConstraints(new AdvancedCurrentConstraints());
        _conf.getAnalyzing().setAnnotationAnalysis(new AdvancedAnnotationAnalysis());
    }

    protected static void setLocale(String _locale, Configuration _conf) {
        _conf.setCurrentLanguage(_locale);
        _conf.getAnalyzingDoc().setLanguages(new StringList(_locale));
    }
    protected static Navigation newNavigation(Configuration _conf) {
        Navigation nav_ = new Navigation();
        nav_.setSession(_conf);
        ContextEl context_ = _conf.getContext();
        BeanLgNames standards_ = (BeanLgNames) context_.getStandards();
        nav_.getSession().setStandards(standards_);
        context_.setExecutingInstance(nav_.getSession());
        return nav_;
    }

    Configuration contextElFive() {
        return contextElFive(new StringMap<String>());
    }
    Configuration contextElFive(StringMap<String> _files) {
        Configuration conf_ =  EquallableExUtil.newConfiguration();
        conf_.setPrefix("c:");
        Options opt_ = new Options();
        
        ContextEl cont_ = InitializationLgNames.buildStdThree(opt_);
        conf_.setContext(cont_);
        cont_.setExecutingInstance(conf_);
        BeanLgNames standards_ = (BeanLgNames) cont_.getStandards();
        conf_.setStandards(standards_);
        Classes.validateWithoutInit(_files, cont_);
        assertTrue(cont_.isEmptyErrors());
        ((BeanCustLgNames)standards_).buildIterables(conf_);
        return conf_;
    }
    protected static RendDocumentBlock buildRendWithoutBean(String html_, Configuration conf_) {
        setLocale("en", conf_);
        Document doc_ = DocumentBuilder.parseSaxNotNullRowCol(html_).getDocument();
        RendDocumentBlock rendDocumentBlock_ = RendBlock.newRendDocumentBlock(conf_, "c:", doc_, html_);
        conf_.getRenders().put("page1.html",rendDocumentBlock_);
        conf_.getContext().setAnalyzing();
        conf_.getAnalyzing().setProcessKeyWord(new AdvancedProcessKeyWord(conf_));
        conf_.getAnalyzing().setHiddenTypes(new AdvancedHiddenTypes(conf_));
        conf_.getAnalyzing().setCurrentGlobalBlock(new AdvancedCurrentGlobalBlock(conf_));
        conf_.getAnalyzing().setCurrentConstraints(new AdvancedCurrentConstraints());
        conf_.getAnalyzing().setAnnotationAnalysis(new AdvancedAnnotationAnalysis());
        rendDocumentBlock_.buildFctInstructions(conf_);
        conf_.setDocument(doc_);
        tryInitStaticlyTypes(conf_);
        conf_.setDocument(doc_);
        return rendDocumentBlock_;
    }
    protected static RendDocumentBlock buildRendWithOneBean(String html_, Configuration context_) {
        setLocale("en", context_);
        Document doc_ = DocumentBuilder.parseSaxNotNullRowCol(html_).getDocument();
        CustList<RendDynOperationNode> ops_ = RenderExpUtil.getAnalyzedOperations("$new pkg.BeanOne()", 0, context_);
        BeanInfo b_ = new BeanInfo();
        b_.setClassName("pkg.BeanOne");
        b_.setExps(ops_);
        context_.getBeansInfos().addEntry("bean_one",b_);
        RendDocumentBlock rendDocumentBlock_ = RendBlock.newRendDocumentBlock(context_, "c:", doc_, html_);
        context_.getRenders().put("page1.html",rendDocumentBlock_);
        context_.getContext().setAnalyzing();
        context_.getAnalyzing().setProcessKeyWord(new AdvancedProcessKeyWord(context_));
        context_.getAnalyzing().setHiddenTypes(new AdvancedHiddenTypes(context_));
        context_.getAnalyzing().setCurrentGlobalBlock(new AdvancedCurrentGlobalBlock(context_));
        context_.getAnalyzing().setCurrentConstraints(new AdvancedCurrentConstraints());
        context_.getAnalyzing().setAnnotationAnalysis(new AdvancedAnnotationAnalysis());
        rendDocumentBlock_.buildFctInstructions(context_);
        tryInitStaticlyTypes(context_);
        addImportingPage(context_);
        Struct bean_ = RenderExpUtil.calculateReuse(ops_, context_).getStruct();
        context_.getBuiltBeans().addEntry("bean_one", bean_);
        context_.clearPages();
        context_.setDocument(doc_);
        return rendDocumentBlock_;
    }

    protected static RendDocumentBlock buildTwoPages(String html_, String htmlTwo_, Configuration conf_) {
        setLocale("en", conf_);
        Document doc_ = DocumentBuilder.parseSaxNotNullRowCol(html_).getDocument();
        Document secDoc_ = DocumentBuilder.parseSaxNotNullRowCol(htmlTwo_).getDocument();
        RendDocumentBlock rendDocumentBlock_ = RendBlock.newRendDocumentBlock(conf_, "c:", doc_, html_);
        RendDocumentBlock rendSecDocumentBlock_ = RendBlock.newRendDocumentBlock(conf_, "c:", secDoc_, htmlTwo_);
        conf_.getRenders().put("page1.html",rendDocumentBlock_);
        conf_.getRenders().put("page2.html",rendSecDocumentBlock_);
        rendDocumentBlock_.buildFctInstructions(conf_);
        rendSecDocumentBlock_.buildFctInstructions(conf_);
        tryInitStaticlyTypes(conf_);
        conf_.setDocument(doc_);
        return rendDocumentBlock_;
    }

    protected static RendDocumentBlock builtTwoPagesOne(String html_, String htmlTwo_, Configuration context_) {
        setLocale("en", context_);
        Document doc_ = DocumentBuilder.parseSaxNotNullRowCol(html_).getDocument();
        Document docSec_ = DocumentBuilder.parseSaxNotNullRowCol(htmlTwo_).getDocument();
        CustList<RendDynOperationNode> ops_ = RenderExpUtil.getAnalyzedOperations("$new pkg.BeanOne()", 0, context_);
        BeanInfo b_ = new BeanInfo();
        b_.setClassName("pkg.BeanOne");
        b_.setExps(ops_);
        context_.getBeansInfos().addEntry("bean_one",b_);
        RendDocumentBlock rendDocumentBlock_ = RendBlock.newRendDocumentBlock(context_, "c:", doc_, html_);
        RendDocumentBlock rendDocumentBlockSec_ = RendBlock.newRendDocumentBlock(context_, "c:", docSec_, htmlTwo_);
        context_.getRenders().put("page1.html",rendDocumentBlock_);
        context_.getRenders().put("page2.html",rendDocumentBlockSec_);
        context_.getContext().setAnalyzing();
        context_.getAnalyzing().setProcessKeyWord(new AdvancedProcessKeyWord(context_));
        context_.getAnalyzing().setHiddenTypes(new AdvancedHiddenTypes(context_));
        context_.getAnalyzing().setCurrentGlobalBlock(new AdvancedCurrentGlobalBlock(context_));
        context_.getAnalyzing().setCurrentConstraints(new AdvancedCurrentConstraints());
        context_.getAnalyzing().setAnnotationAnalysis(new AdvancedAnnotationAnalysis());
        rendDocumentBlock_.buildFctInstructions(context_);
        rendDocumentBlockSec_.buildFctInstructions(context_);
        tryInitStaticlyTypes(context_);
        addImportingPage(context_);
        Struct bean_ = RenderExpUtil.calculateReuse(ops_, context_).getStruct();
        context_.getBuiltBeans().addEntry("bean_one", bean_);
        context_.clearPages();
        context_.setDocument(doc_);
        return rendDocumentBlock_;
    }
    protected static RendDocumentBlock buildTwoPagesTwo(String html_, String htmlTwo_, Configuration context_) {
        setLocale("en", context_);
        Document doc_ = DocumentBuilder.parseSaxNotNullRowCol(html_).getDocument();
        Document docSec_ = DocumentBuilder.parseSaxNotNullRowCol(htmlTwo_).getDocument();
        CustList<RendDynOperationNode> ops_ = RenderExpUtil.getAnalyzedOperations("$new pkg.BeanOne()", 0, context_);
        BeanInfo b_ = new BeanInfo();
        b_.setExps(ops_);
        b_.setClassName("pkg.BeanOne");
        context_.getBeansInfos().addEntry("bean_one",b_);
        CustList<RendDynOperationNode> ops2_ = RenderExpUtil.getAnalyzedOperations("$new pkg.BeanTwo()", 0, context_);
        BeanInfo b_1 = new BeanInfo();
        b_1.setExps(ops2_);
        b_1.setClassName("pkg.BeanTwo");
        context_.getBeansInfos().addEntry("bean_two", b_1);
        RendDocumentBlock rendDocumentBlock_ = RendBlock.newRendDocumentBlock(context_, "c:", doc_, html_);
        RendDocumentBlock rendDocumentBlockSec_ = RendBlock.newRendDocumentBlock(context_, "c:", docSec_, htmlTwo_);
        context_.getRenders().put("page1.html",rendDocumentBlock_);
        context_.getRenders().put("page2.html",rendDocumentBlockSec_);
        context_.getContext().setAnalyzing();
        context_.getAnalyzing().setProcessKeyWord(new AdvancedProcessKeyWord(context_));
        context_.getAnalyzing().setHiddenTypes(new AdvancedHiddenTypes(context_));
        context_.getAnalyzing().setCurrentGlobalBlock(new AdvancedCurrentGlobalBlock(context_));
        context_.getAnalyzing().setCurrentConstraints(new AdvancedCurrentConstraints());
        context_.getAnalyzing().setAnnotationAnalysis(new AdvancedAnnotationAnalysis());
        rendDocumentBlock_.buildFctInstructions(context_);
        rendDocumentBlockSec_.buildFctInstructions(context_);
        tryInitStaticlyTypes(context_);
        addImportingPage(context_);
        Struct bean_ = RenderExpUtil.calculateReuse(ops_, context_).getStruct();
        context_.getBuiltBeans().addEntry("bean_one", bean_);
        bean_ = RenderExpUtil.calculateReuse(ops2_, context_).getStruct();
        context_.getBuiltBeans().addEntry("bean_two", bean_);
        context_.clearPages();
        context_.setDocument(doc_);
        return rendDocumentBlock_;
    }
    protected static RendDocumentBlock buildThree(String html_, String htmlTwo_, String htmlThree_, Configuration context_) {
        setLocale("en", context_);
        Document doc_ = DocumentBuilder.parseSaxNotNullRowCol(html_).getDocument();
        Document docSec_ = DocumentBuilder.parseSaxNotNullRowCol(htmlTwo_).getDocument();
        Document docThird_ = DocumentBuilder.parseSaxNotNullRowCol(htmlThree_).getDocument();
        CustList<RendDynOperationNode> ops_ = RenderExpUtil.getAnalyzedOperations("$new pkg.BeanOne()", 0, context_);
        BeanInfo b_ = new BeanInfo();
        b_.setExps(ops_);
        b_.setClassName("pkg.BeanOne");
        context_.getBeansInfos().addEntry("bean_one",b_);
        CustList<RendDynOperationNode> ops2_ = RenderExpUtil.getAnalyzedOperations("$new pkg.BeanTwo()", 0, context_);
        BeanInfo b_1 = new BeanInfo();
        b_1.setExps(ops2_);
        b_1.setClassName("pkg.BeanTwo");
        context_.getBeansInfos().addEntry("bean_two", b_1);
        CustList<RendDynOperationNode> ops3_ = RenderExpUtil.getAnalyzedOperations("$new pkg.BeanThree()", 0, context_);
        BeanInfo b_2 = new BeanInfo();
        b_2.setExps(ops3_);
        b_2.setClassName("pkg.BeanThree");
        context_.getBeansInfos().addEntry("bean_three", b_2);
        RendDocumentBlock rendDocumentBlock_ = RendBlock.newRendDocumentBlock(context_, "c:", doc_, html_);
        RendDocumentBlock rendDocumentBlockSec_ = RendBlock.newRendDocumentBlock(context_, "c:", docSec_, htmlTwo_);
        RendDocumentBlock rendDocumentBlockThird_ = RendBlock.newRendDocumentBlock(context_, "c:", docThird_, htmlThree_);
        context_.getRenders().put("page1.html",rendDocumentBlock_);
        context_.getRenders().put("page2.html",rendDocumentBlockSec_);
        context_.getRenders().put("page3.html",rendDocumentBlockThird_);
        context_.getContext().setAnalyzing();
        context_.getAnalyzing().setProcessKeyWord(new AdvancedProcessKeyWord(context_));
        context_.getAnalyzing().setHiddenTypes(new AdvancedHiddenTypes(context_));
        context_.getAnalyzing().setCurrentGlobalBlock(new AdvancedCurrentGlobalBlock(context_));
        context_.getAnalyzing().setCurrentConstraints(new AdvancedCurrentConstraints());
        context_.getAnalyzing().setAnnotationAnalysis(new AdvancedAnnotationAnalysis());
        rendDocumentBlock_.buildFctInstructions(context_);
        rendDocumentBlockSec_.buildFctInstructions(context_);
        rendDocumentBlockThird_.buildFctInstructions(context_);
        tryInitStaticlyTypes(context_);
        addImportingPage(context_);
        Struct bean_ = RenderExpUtil.calculateReuse(ops_, context_).getStruct();
        context_.getBuiltBeans().addEntry("bean_one", bean_);
        bean_ = RenderExpUtil.calculateReuse(ops2_, context_).getStruct();
        context_.getBuiltBeans().addEntry("bean_two", bean_);
        bean_ = RenderExpUtil.calculateReuse(ops3_, context_).getStruct();
        context_.getBuiltBeans().addEntry("bean_three", bean_);
        context_.clearPages();
        context_.setDocument(doc_);
        return rendDocumentBlock_;
    }

    protected static void tryInitStaticlyTypes(Configuration _context) {
        if (_context.isEmptyErrors()) {
            Classes.tryInitStaticlyTypes(_context.getContext());
            assertTrue(_context.isEmptyErrors());
        }
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
