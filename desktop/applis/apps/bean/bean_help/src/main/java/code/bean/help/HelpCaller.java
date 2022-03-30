package code.bean.help;

import code.bean.help.analyze.blocks.HelpAnaRendBlockHelp;
import code.bean.help.fwd.HelpRendForwardInfos;
import code.bean.nat.NatDualConfigurationContext;
import code.bean.nat.analyze.blocks.AnaRendBlockHelp;
import code.bean.nat.analyze.blocks.NatAnalyzedCode;
import code.expressionlanguage.analyze.util.AnaFormattedRootBlock;
import code.expressionlanguage.exec.InitPhase;
import code.formathtml.Configuration;
import code.formathtml.ImportingPage;
import code.formathtml.Navigation;
import code.formathtml.analyze.AnalyzingDoc;
import code.formathtml.analyze.blocks.AnaRendDocumentBlock;
import code.formathtml.exec.RendStackCall;
import code.formathtml.exec.blocks.RendBlock;
import code.formathtml.exec.blocks.RendDocumentBlock;
import code.formathtml.structs.BeanInfo;
import code.sml.Document;
import code.sml.util.ResourcesMessagesUtil;
import code.util.StringMap;
import code.util.core.StringUtil;

public final class HelpCaller {
    private HelpCaller(){

    }

    public static Document text(NatDualConfigurationContext _contextConf, Navigation _navigation, String _realFilePath, Document _uniq, StringMap<String> _ms, String _language) {
        HelpContextEl ctx_ = new HelpContextEl();
        RendStackCall rendStackCall_ = new RendStackCall(InitPhase.NOTHING, ctx_);
        StringMap<String> files_ = new StringMap<String>();
        Configuration session_ = _navigation.getSession();
        for (String a : _contextConf.getAddedFiles()) {
            files_.put(a, _ms.getVal(a));
        }
        for (String l : _navigation.getLanguages()) {
            for (String a : _contextConf.getProperties().values()) {
                String folder_ = _contextConf.getMessagesFolder();
                String fileName_ = ResourcesMessagesUtil.getPropertiesPath(folder_, l, a);
                files_.put(fileName_, _ms.getVal(fileName_));
            }
        }
        session_.setFirstUrl(_realFilePath);
        _navigation.setFiles(files_);
        NatAnalyzedCode page_ = NatAnalyzedCode.setInnerAnalyzing();
        Configuration conf_ = _navigation.getSession();
        AnalyzingDoc analyzingDoc_ = new AnalyzingDoc();
        StringMap<BeanInfo> beansInfos_ = new StringMap<BeanInfo>();
        conf_.getBeansInfos().addAllEntries(beansInfos_);
        analyzingDoc_.setLanguages(_navigation.getLanguages());
        _navigation.getSession().setCurrentLanguage(_language);

        _navigation.getSession().setFiles(_navigation.getFiles());
        analyzingDoc_.setup(_navigation.getSession(), _contextConf.getProperties(), _contextConf.getMessagesFolder());
        String file_ = _uniq.export();
        AnaRendDocumentBlock anaDoc_ = HelpAnaRendBlockHelp.newRendDocumentBlock(analyzingDoc_.getPrefix(), _uniq, file_, _realFilePath, analyzingDoc_.getRendKeyWords());
        buildFctInstructions(anaDoc_,analyzingDoc_, page_);
        RendDocumentBlock rendDocumentBlock_ = HelpRendForwardInfos.buildExec(analyzingDoc_, anaDoc_);
        rendStackCall_.init();
        ImportingPage ip_ = new ImportingPage();
        rendStackCall_.addPage(ip_);
        rendStackCall_.clearPages();
        rendStackCall_.getFormParts().initForms();
        String beanName_ = rendDocumentBlock_.getBeanName();
        RendBlock.res(rendDocumentBlock_, _navigation.getSession(), null, ctx_, rendStackCall_, beanName_, null);
        return rendStackCall_.getDocument();
    }

    public static void buildFctInstructions(AnaRendDocumentBlock _doc, AnalyzingDoc _anaDoc, NatAnalyzedCode _page) {
        _doc.setBeanName(_doc.getElt().getAttribute(StringUtil.concat(_anaDoc.getPrefix(),_anaDoc.getRendKeyWords().getAttrBean())));
        _page.setGlobalType(AnaFormattedRootBlock.defValue());
        AnaRendBlockHelp.loop(_doc, _anaDoc, _page);
    }
}
