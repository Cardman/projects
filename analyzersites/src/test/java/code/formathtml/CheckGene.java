package code.formathtml;

import org.junit.Assert;
import org.junit.Test;

import cards.belote.beans.BeloteStandards;
import code.bean.Bean;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.CustElUtil;
import code.expressionlanguage.methods.Classes;
import code.expressionlanguage.opers.util.ClassMethodId;
import code.formathtml.util.BeanLgNames;
import code.sml.Document;
import code.sml.DocumentBuilder;
import code.sml.DocumentResult;
import code.stream.StreamTextFile;
import code.util.BooleanList;
import code.util.EntryCust;
import code.util.StringList;
import code.util.StringMap;

@SuppressWarnings("static-method")
public class CheckGene {

    @Test
    public void confPkTest() {
        String resPk = "resources_pk/rom/";
        String web = "C:/Users/cardman/git/pokemonbean/resources_pk/rom/web_fight/";
        String webtwo = "C:/Users/cardman/git/pokemonbean/";
        String conf = "faces.xml";
        test(conf, web, webtwo, resPk, null);
        web = "C:/Users/cardman/git/pokemonbean/resources_pk/rom/web/";
        test(conf, web, webtwo, resPk, null);
        web = "C:/Users/cardman/git/pokemonbean/resources_pk/rom/web_game/";
        test(conf, web, webtwo, resPk, null);
        web = "C:/Users/cardman/git/pokemonbean/resources_pk/rom/web_prog/";
        test(conf, web, webtwo, resPk, null);
        conf = "faces_pokemon.xml";
        web = "C:/Users/cardman/git/pokemonbean/resources_pk/rom/web_pk/";
        test(conf, web, webtwo, resPk, null);
        System.out.println();
        for (EntryCust<String, BooleanList> e: CustElUtil.GETTERS_SETTERS_FIELDS.entryList()) {
            if (e.getValue().size() != 1) {
                continue;
            }
            System.out.println(e.getKey());
        }
        System.out.println();
        for (EntryCust<String, BooleanList> e: CustElUtil.GETTERS_SETTERS_FIELDS.entryList()) {
            if (e.getValue().size() != 1) {
                continue;
            }
            if (e.getValue().first()) {
                continue;
            }
            System.out.println(e.getKey());
        }
        System.out.println();
        for (EntryCust<String, BooleanList> e: CustElUtil.GETTERS_SETTERS_FIELDS.entryList()) {
            if (e.getValue().size() != 1) {
                continue;
            }
            if (!e.getValue().first()) {
                continue;
            }
            System.out.println(e.getKey());
        }
        System.out.println();
        CustElUtil.CALLS.removeDuplicates();
        for (ClassMethodId e: CustElUtil.CALLS) {
            if (e.getClassName().endsWith(">")) {
                continue;
            }
//            String clName_ = e.getClassName();
//            String mName_ = e.getConstraints().getName();
//            try {
////                Class<?> cl_ = ConstClasses.classForNameNotInit(clName_);
////                EqList<ClassMethodId> homonymsMethods_ = new EqList<ClassMethodId>();
////                for (ClassMethodId f: CustElUtil.CALLS) {
////                    if (f.getClassName().endsWith(">")) {
////                        continue;
////                    }
////                    Class<?> cltwo_ = ConstClasses.classForNameNotInit(f.getClassName());
////                    if (!cltwo_.isAssignableFrom(cl_)) {
////                        continue;
////                    }
////                    if (cltwo_.isInterface()) {
////                        continue;
////                    }
////                    if (StringList.quickEq(f.getConstraints().getName(), mName_)) {
////                        homonymsMethods_.add(f);
////                    }
////                }
////                if (homonymsMethods_.size() > 1) {
////                    System.out.println(clName_+"."+e.getConstraints().getSignature());
////                    for (ClassMethodId c: homonymsMethods_) {
////                        System.out.println("\t"+c.getClassName()+"."+c.getConstraints().getSignature());
////                    }
////                }
//                Class<?> cl_ = ConstClasses.classForNameNotInit(clName_);
//                CustList<java.lang.reflect.Method> methods_ = new CustList<java.lang.reflect.Method>();
//                while (cl_ != null) {
//                    for (java.lang.reflect.Method m: cl_.getDeclaredMethods()) {
//                        if (Modifier.isAbstract(m.getModifiers())) {
//                            continue;
//                        }
//                        if (m.getAnnotation(Accessible.class) == null && !Modifier.isPublic(m.getModifiers())) {
//                            continue;
//                        }
//                        if (StringList.quickEq(m.getName(), mName_)) {
//                            methods_.add(m);
//                        }
//                    }
//                    cl_ = cl_.getSuperclass();
//                }
//                if (methods_.size() > 1) {
//                    System.out.println(clName_+"."+e.getConstraints().getSignature());
//                    for (java.lang.reflect.Method m: methods_) {
//                        System.out.println("\t"+m.toGenericString());
//                    }
//                }
//            } catch (Exception _0_) {
//            }
            System.out.println(e.getClassName()+"."+e.getConstraints().getSignature());
        }
        System.out.println();
        FormatHtmlLookFor.FIELDS_NAMES.removeDuplicates();
        for (String e: FormatHtmlLookFor.FIELDS_NAMES) {
            System.out.println(e);
        }
    }
    @Test
    public void confCardsTest() {
        String resPk;
        String web;
        String webtwo = "C:/Users/cardman/git/belotebean/";
        String conf;
        web = "C:/Users/cardman/git/belotebean/resources_cards/";
        resPk = "";
        conf = "conf/results_belote.xml";
        testOneFile(conf, web, webtwo, resPk, new BeloteStandards());
    }
    public static void init(Configuration _conf, boolean _cust) {
        _conf.setHtmlPage(new HtmlPage());
        _conf.setDocument(null);
        _conf.setCurrentUrl(_conf.getFirstUrl());
        if (_conf.getPrefix() == null) {
            _conf.setPrefix("");
        } else {
            _conf.setPrefix(StringList.concat(_conf.getPrefix(),":"));
        }
        if (_conf.getLateValidators() == null) {
            _conf.setLateValidators(new StringMap<String>());
        }
        if (_conf.getLateTranslators() == null) {
            _conf.setLateTranslators(new StringMap<String>());
        }
        _conf.getStandards().build();
        if (_cust) {
            _conf.getStandards().setContext(_conf.getContext());
            _conf.getContext().setStandards(_conf.getStandards());
            _conf.getStandards().setupOverrides(_conf.getContext());
        }
    }
//    private static void test(String conf, String web, String webtwo, String resPk) {
//        String contentConf_ = StreamTextFile.contentsOfFile(web+conf);
//        Configuration conf_ = (Configuration) SerializeXmlObject.fromXmlStringObject(contentConf_);
//        init(conf_, false);
//        Navigation nav = new Navigation();
//        nav.setSession(conf_);
//        conf_.setupClasses(new StringMap<String>());
//        for (EntryCust<String, Bean> e: conf_.getBeans().entryList()) {
//            conf_.getBuiltBeans().put(e.getKey(), conf_.newBean("fr", null, e.getValue(), true));
//        }
//        for (String f: StreamTextFile.allSortedFiles(web)) {
//            if (!f.endsWith(".html")) {
//                continue;
//            }
////            System.out.println("\t"+f);
//            String folder_ = f.substring(0, webtwo.length());
//            processOneFile(conf_, folder_, f.substring(webtwo.length()), conf, web, webtwo, resPk, conf_.getStandards());
////            String currentUrl_ = f.substring(webtwo.length());
////            String text_ = ExtractFromResources.loadPage(conf_, new StringMap<String>(), currentUrl_, resPk);
////            DocumentResult res_ = DocumentBuilder.parseSaxNotNullRowCol(text_);
////            Document doc_ = res_.getDocument();
////            if (doc_ == null) {
////                System.err.println(res_.getLocation());
////                continue;
////            }
////            conf_.setDocument(doc_);
////            conf_.addPage(new ImportingPage(true));
////            conf_.getLastPage().setPrefix(conf_.getPrefix());
////            String currentBeanName_ = doc_.getDocumentElement().getAttribute(conf_.getPrefix()+FormatHtml.BEAN_ATTRIBUTE);
////            conf_.getLastPage().setBeanName(currentBeanName_);
////            FormatHtmlLookFor.checkSyntax(conf_, false, doc_, text_, "fr", resPk);
////            FormatHtmlLookFor.checkSyntax(conf_, false, doc_, text_, "en", resPk);
////            conf_.removeLastPage();
//        }
//    }
    private static void testOneFile(String conf, String web, String webtwo, String resPk, BeanLgNames _stds) {
        String contentConf_ = StreamTextFile.contentsOfFile(web+conf);
        Configuration conf_ = new Configuration();
        conf_.setStandards(_stds);
        ReadConfiguration.load(conf_, DocumentBuilder.parseSax(contentConf_));
        ContextEl context_ = new ContextEl();
        context_.setClasses(new Classes());
        conf_.setContext(context_);
        init(conf_,true);
        Navigation nav = new Navigation();
        nav.setSession(conf_);
        conf_.setupClasses(new StringMap<String>());
        for (EntryCust<String, Bean> e: conf_.getBeans().entryList()) {
            conf_.getBuiltBeans().put(e.getKey(), conf_.newBean("fr", null, e.getValue(), true));
        }
        String currentUrl_ = conf_.getFirstUrl();
        processOneFile(conf_, "", currentUrl_, conf, web, webtwo, resPk, _stds);
    }
    private static void test(String conf, String web, String webtwo, String resPk, BeanLgNames _stds) {
        String contentConf_ = StreamTextFile.contentsOfFile(web+conf);
        Configuration conf_ = new Configuration();
        if (_stds == null) {
            conf_.setStandards(new BeanLgNames());
            ContextEl context_ = new ContextEl();
            DefaultInitialization.basicStandards(conf_.getStandards());
            context_.setStandards(conf_.getStandards());
            conf_.getStandards().setContext(context_);
            ReadConfiguration.load(conf_, DocumentBuilder.parseSax(contentConf_));
            conf_.getStandards().setContext(context_);
            conf_.setContext(context_);
            context_.setAccessValue(conf_.getAccessValue());
            context_.setStandards(conf_.getStandards());
            init(conf_,false);
        } else {
            conf_.setStandards(_stds);
            ContextEl context_ = new ContextEl();
            context_.setClasses(new Classes());
            conf_.setContext(context_);
            ReadConfiguration.load(conf_, DocumentBuilder.parseSax(contentConf_));
            init(conf_,true);
           }
        Navigation nav = new Navigation();
        nav.setSession(conf_);
        conf_.setupClasses(new StringMap<String>());
        for (EntryCust<String, Bean> e: conf_.getBeans().entryList()) {
            conf_.getBuiltBeans().put(e.getKey(), conf_.newBean("fr", null, e.getValue(), true));
        }
        for (String f: StreamTextFile.allSortedFiles(web)) {
            if (!f.endsWith(".html")) {
                continue;
            }
            String folder_ = f.substring(0, webtwo.length());
            processOneFile(conf_, folder_, f.substring(webtwo.length()), conf, web, webtwo, resPk, _stds);
        }
    }
    private static void processOneFile(Configuration conf_, String _folder,String _url, String conf, String web, String webtwo, String resPk, BeanLgNames _stds) {
        System.out.println("\t"+_folder+_url);
        String currentUrl_ = _url;
        String text_ = ExtractFromResources.loadPage(conf_, new StringMap<String>(), currentUrl_, resPk);
        DocumentResult res_ = DocumentBuilder.parseSaxNotNullRowCol(text_);
        Document doc_ = res_.getDocument();
        if (doc_ == null) {
            Assert.fail(res_.getLocation().display());
        }
        conf_.setDocument(doc_);
        conf_.addPage(new ImportingPage(true));
        conf_.getLastPage().setPrefix(conf_.getPrefix());
        String currentBeanName_ = doc_.getDocumentElement().getAttribute(conf_.getPrefix()+FormatHtml.BEAN_ATTRIBUTE);
        conf_.getLastPage().setBeanName(currentBeanName_);
        FormatHtmlLookFor.checkSyntax(conf_, _stds != null, doc_, text_, "fr", resPk);
        FormatHtmlLookFor.checkSyntax(conf_, _stds != null, doc_, text_, "en", resPk);
        conf_.removeLastPage();
    }
}
