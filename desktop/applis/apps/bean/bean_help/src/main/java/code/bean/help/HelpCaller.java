package code.bean.help;

import code.bean.help.analyze.blocks.HelpAnaRendBlockHelp;
import code.bean.help.fwd.HelpRendForwardInfos;
import code.bean.nat.NatDualConfigurationContext;
import code.bean.nat.analyze.NatAnalyzingDoc;
import code.bean.nat.analyze.blocks.AnaRendBlockHelp;
import code.bean.nat.analyze.blocks.NatAnaRendDocumentBlock;
import code.bean.nat.analyze.blocks.NatAnalyzedCode;
import code.bean.nat.exec.NatImportingPage;
import code.bean.nat.exec.NatRendStackCall;
import code.bean.nat.exec.blocks.NatDocumentBlock;
import code.bean.nat.exec.blocks.RendBlockHelp;
import code.expressionlanguage.analyze.util.AnaFormattedRootBlock;
import code.formathtml.Configuration;
import code.formathtml.Navigation;
import code.formathtml.structs.BeanInfo;
import code.sml.Document;
import code.sml.util.ResourcesMessagesUtil;
import code.util.StringMap;
import code.util.core.StringUtil;

public final class HelpCaller {
    private HelpCaller(){

    }

    public static Document text(NatDualConfigurationContext _contextConf, Navigation _navigation, String _realFilePath, Document _uniq, StringMap<String> _ms, String _language) {
        NatRendStackCall rendStackCall_ = new NatRendStackCall();
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
        NatAnalyzingDoc analyzingDoc_ = new NatAnalyzingDoc();
        StringMap<BeanInfo> beansInfos_ = new StringMap<BeanInfo>();
        conf_.getBeansInfos().addAllEntries(beansInfos_);
        analyzingDoc_.setLanguages(_navigation.getLanguages());
        _navigation.getSession().setCurrentLanguage(_language);

        _navigation.getSession().setFiles(_navigation.getFiles());
        analyzingDoc_.setup(_navigation.getSession(), _contextConf.getProperties(), _contextConf.getMessagesFolder());
        NatAnaRendDocumentBlock anaDoc_ = HelpAnaRendBlockHelp.newRendDocumentBlock(analyzingDoc_.getPrefix(), _uniq, analyzingDoc_.getRendKeyWords());
        buildFctInstructions(anaDoc_,analyzingDoc_, page_);
        NatDocumentBlock rendDocumentBlock_ = HelpRendForwardInfos.buildExec(analyzingDoc_, anaDoc_);
        rendStackCall_.init();
        NatImportingPage ip_ = new NatImportingPage();
        rendStackCall_.addPage(ip_);
        rendStackCall_.clearPages();
        rendStackCall_.getFormParts().initFormsSpec();
        String beanName_ = rendDocumentBlock_.getBeanName();
        RendBlockHelp.res(rendDocumentBlock_, _navigation.getSession(), rendStackCall_, beanName_, null);
        return rendStackCall_.getDocument();
    }

    public static void buildFctInstructions(NatAnaRendDocumentBlock _doc, NatAnalyzingDoc _anaDoc, NatAnalyzedCode _page) {
        _doc.setBeanName(_doc.getElt().getAttribute(StringUtil.concat(_anaDoc.getPrefix(),_anaDoc.getRendKeyWords().getAttrBean())));
        _page.setGlobalType(AnaFormattedRootBlock.defValue());
        AnaRendBlockHelp.loop(_doc, _anaDoc, _page);
    }
}
