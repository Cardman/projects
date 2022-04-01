package code.bean.nat;

import code.bean.nat.analyze.blocks.AnaRendBlockHelp;
import code.bean.nat.analyze.blocks.NatAnalyzedCode;
import code.bean.nat.fwd.AbstractNatBlockBuilder;
import code.bean.nat.fwd.NatRendForwardInfos;
import code.expressionlanguage.Argument;
import code.expressionlanguage.structs.NumberStruct;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;
import code.formathtml.Configuration;
import code.formathtml.Navigation;
import code.formathtml.analyze.AnalyzingDoc;
import code.formathtml.analyze.blocks.AnaRendDocumentBlock;
import code.formathtml.errors.RendAnalysisMessages;
import code.formathtml.structs.BeanInfo;
import code.sml.Document;
import code.util.EntryCust;
import code.util.StringMap;

public abstract class BeanNatLgNames extends BeanNatCommonLgNames {
    private final NatAnalyzedCode natCode = NatAnalyzedCode.setInnerAnalyzing();
    public void setupAll(StringMap<Document> _docs, Navigation _nav, Configuration _conf, AbstractNatBlockBuilder _builder, NatDualConfigurationContext _context) {
        setNavigation(_context.getNavigation());
        AnalyzingDoc analyzingDoc_ = new AnalyzingDoc();
        analyzingDoc_.setContent(this);
        StringMap<BeanInfo> beansInfos_ = new StringMap<BeanInfo>();
        initInstancesPattern(_nav.getSession(), beansInfos_);
        _conf.getBeansInfos().addAllEntries(beansInfos_);
        preInitBeans(_nav.getSession());
        analyzingDoc_.setRendAnalysisMessages(new RendAnalysisMessages());
        analyzingDoc_.setLanguages(_nav.getLanguages());
        _nav.getSession().setCurrentLanguage(_nav.getLanguage());

        getRenders().clear();
        _nav.getSession().setFiles(_nav.getFiles());
        analyzingDoc_.setup(_nav.getSession(), _context.getProperties(), _context.getMessagesFolder());


        natCode.setStds(this);
        StringMap<AnaRendDocumentBlock> d_ = new StringMap<AnaRendDocumentBlock>();
        for (EntryCust<String, Document> s: _docs.entryList()) {
            String link_ = s.getKey();
            Document document_ = s.getValue();
            String file_ = document_.export();
            AnaRendDocumentBlock anaDoc_ = AnaRendBlockHelp.newRendDocumentBlock(analyzingDoc_.getPrefix(), document_, file_, link_, analyzingDoc_.getRendKeyWords(), this, _builder);
            d_.addEntry(link_,anaDoc_);
        }
        for (AnaRendDocumentBlock v : d_.values()) {
            AnaRendBlockHelp.buildFctInstructions(v,analyzingDoc_, natCode, beansInfos_);
        }
//        StringMap<AnaRendDocumentBlock> d_ = _nav.analyzedDocs(_docs,page_, this, analyzingDoc_, _dual.getContext());
        NatRendForwardInfos.buildExec(analyzingDoc_, d_, getRenders());
    }

    public static String processString(Argument _arg) {
        Struct struct_ = _arg.getStruct();
        if (struct_ instanceof NumberStruct) {
            return Long.toString(((NumberStruct)struct_).longStruct());
        }
        if (struct_ instanceof StringStruct) {
            return ((StringStruct)struct_).getInstance();
        }
        if (struct_ instanceof NatDisplayableStruct) {
            return ((NatDisplayableStruct)struct_).getDisplayedString().getInstance();
        }
        return CST_NULL_STRING;
    }

    public NatAnalyzedCode getNatCode() {
        return natCode;
    }
}
