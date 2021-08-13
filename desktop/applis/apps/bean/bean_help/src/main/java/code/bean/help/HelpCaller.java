package code.bean.help;

import code.bean.help.analyze.blocks.HelpAnaRendBlockHelp;
import code.bean.help.fwd.HelpRendForwardInfos;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.exec.InitPhase;
import code.formathtml.Configuration;
import code.formathtml.ImportingPage;
import code.formathtml.Navigation;
import code.formathtml.analyze.AnalyzingDoc;
import code.formathtml.analyze.blocks.AnaRendDocumentBlock;
import code.formathtml.exec.RendStackCall;
import code.formathtml.exec.blocks.RendBlock;
import code.formathtml.exec.blocks.RendDocumentBlock;
import code.formathtml.fwd.DefaultInputBuilder;
import code.formathtml.structs.BeanInfo;
import code.formathtml.util.DualConfigurationContext;
import code.sml.Document;
import code.sml.DocumentBuilder;
import code.sml.util.ResourcesMessagesUtil;
import code.util.StringList;
import code.util.StringMap;

public final class HelpCaller {
    private HelpCaller(){

    }

    public static String text(String _lg, String _realFilePath, String _uniq, StringMap<String> _ms, String _messagesFolder, StringMap<String> _properties) {
        return text(_lg, _realFilePath, _uniq,new StringList(), _ms, _messagesFolder, _properties);
    }
    public static String text(String _lg, String _realFilePath, String _uniq, StringList _add, StringMap<String> _ms, String _messagesFolder, StringMap<String> _properties) {
        Navigation navigation_= new Navigation();
        Configuration session_ = new Configuration();
        session_.setPrefix("c:");
        navigation_.setSession(session_);
        navigation_.setLanguage(_lg);
        navigation_.setLanguages(new StringList(_lg));
        DualConfigurationContext contextConf_ = new DualConfigurationContext();
        contextConf_.setMessagesFolder(_messagesFolder);
        contextConf_.setProperties(_properties);
        contextConf_.setAddedFiles(_add);
        HelpContextEl ctx_ = new HelpContextEl();
        RendStackCall rendStackCall_ = new RendStackCall(InitPhase.NOTHING, ctx_);
        return text(contextConf_, navigation_, _realFilePath, DocumentBuilder.parseSaxNotNullRowCol(_uniq).getDocument(), _ms,ctx_,rendStackCall_);
    }

    public static String text(DualConfigurationContext _contextConf, Navigation _navigation, String _realFilePath, Document _uniq, StringMap<String> _ms, HelpContextEl _ctx, RendStackCall _rendStackCall) {
        //        String realFilePath_ = navigation.getSession().getFirstUrl();
//        System.out.println(realFilePath2_);

//        DualAnalyzedContext du_ = new DualAnalyzedContext(null,page_,null,new DualConfigurationContext());
//        DualAnalyzedContext du_ = getNavigation().loadConfiguration("", null, null, nat_,document);
//        DualAnalyzedContext du_ = getNavigation().loadConfiguration("", null, DefaultFileBuilder.newInstance(getBeanNatLgNames().getContent()), nat_,document);
        StringMap<String> files_ = new StringMap<String>();
        Configuration session_ = _navigation.getSession();
        for (String a : _contextConf.getAddedFiles()) {
            files_.put(a, _ms.getVal(a));
//            files_.put(a, ResourceFiles.ressourceFichier(a));
        }
        for (String l : _navigation.getLanguages()) {
            for (String a : _contextConf.getProperties().values()) {
                String folder_ = _contextConf.getMessagesFolder();
                String fileName_ = ResourcesMessagesUtil.getPropertiesPath(folder_, l, a);
                files_.put(fileName_, _ms.getVal(fileName_));
            }
        }
//        StringMap<Document> docs_ = new StringMap<Document>();
//        Document doc_ = built.getVal(realFilePath_);
//        docs_.addEntry(realFilePath_,doc_);
//        files_.put(realFilePath_, ResourceFiles.ressourceFichier(realFilePath_));
        session_.setFirstUrl(_realFilePath);
        _navigation.setFiles(files_);
        AnalyzedPageEl page_ = AnalyzedPageEl.setInnerAnalyzing();
        Configuration conf_ = _navigation.getSession();
        AnalyzingDoc analyzingDoc_ = new AnalyzingDoc();
//        analyzingDoc_.setContent(beanNatLgNames);
        analyzingDoc_.setInputBuilder(new DefaultInputBuilder());
        StringMap<BeanInfo> beansInfos_ = new StringMap<BeanInfo>();
//        BeanNatCommonLgNames.initInstancesPattern(_navigation.getSession(), beansInfos_);
        conf_.getBeansInfos().addAllEntries(beansInfos_);
//        beanNatLgNames.preInitBeans(navigation.getSession());
//        analyzingDoc_.setRendAnalysisMessages(contextConf_.getAnalysisMessages());
        analyzingDoc_.setLanguages(_navigation.getLanguages());
        _navigation.getSession().setCurrentLanguage(_navigation.getLanguage());

        _navigation.getSession().getRenders().clear();
        _navigation.getSession().setFiles(_navigation.getFiles());
        analyzingDoc_.setup(_navigation.getSession(), _contextConf);
//        NatRenderAnalysis.setupInts(page_, analyzingDoc_);


//        StringMap<AnaRendDocumentBlock> d_ = new StringMap<AnaRendDocumentBlock>();
        String file_ = _uniq.export();
        AnaRendDocumentBlock anaDoc_ = HelpAnaRendBlockHelp.newRendDocumentBlock(analyzingDoc_.getPrefix(), _uniq, file_, _realFilePath, analyzingDoc_.getRendKeyWords());
//        d_.addEntry(link_,anaDoc_);
        anaDoc_.buildFctInstructions(analyzingDoc_, page_, beansInfos_);
//        for (EntryCust<String, Document> s: docs_.entryList()) {
//            String link_ = s.getKey();
//            String file_ = uniq_.export();
//            AnaRendDocumentBlock anaDoc_ = AnaRendBlockHelp.newRendDocumentBlock(analyzingDoc_.getPrefix(), uniq_, file_, link_, analyzingDoc_.getRendKeyWords(), null);
//            d_.addEntry(link_,anaDoc_);
//        }
//        for (AnaRendDocumentBlock v : d_.values()) {
//            v.buildFctInstructions(analyzingDoc_, page_, beansInfos_);
//        }
//        StringMap<AnaRendDocumentBlock> d_ = _nav.analyzedDocs(_docs,page_, this, analyzingDoc_, _dual.getContext());
        HelpRendForwardInfos.buildExec(analyzingDoc_, conf_, _realFilePath, anaDoc_);
        //        context = _du.getForwards().generate(_du.getContext().getOptions());
//        context = _du.getForwards().generate(_du.getContext().getOptions());
//        return null;
        /*

        */
        _rendStackCall.init();
        ImportingPage ip_ = new ImportingPage();
        _rendStackCall.addPage(ip_);
        String dest_ = _navigation.getSession().getFirstUrl();
        RendDocumentBlock rendDocumentBlock_ = _navigation.getSession().getRenders().getVal(dest_);
        _rendStackCall.clearPages();
        return RendBlock.getRes(rendDocumentBlock_, _navigation.getSession(), null, _ctx, _rendStackCall, dest_);
    }

}
