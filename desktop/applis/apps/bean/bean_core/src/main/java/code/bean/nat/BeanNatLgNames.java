package code.bean.nat;

import code.bean.nat.analyze.blocks.AnaRendBlockHelp;
import code.bean.nat.analyze.blocks.NatAnalyzedCode;
import code.bean.nat.fwd.AbstractNatBlockBuilder;
import code.bean.nat.fwd.NatRendForwardInfos;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.exec.opers.ExecCatOperation;
import code.expressionlanguage.functionid.ConstructorId;
import code.formathtml.analyze.AnalyzingDoc;
import code.formathtml.analyze.blocks.AnaRendDocumentBlock;
import code.formathtml.exec.RendStackCall;
import code.expressionlanguage.*;
import code.expressionlanguage.stds.*;
import code.expressionlanguage.structs.*;
import code.formathtml.*;
import code.formathtml.structs.BeanInfo;
import code.formathtml.util.DualConfigurationContext;
import code.sml.Document;
import code.util.*;

public abstract class BeanNatLgNames extends BeanNatCommonLgNames {
    private final NatAnalyzedCode natCode = NatAnalyzedCode.setInnerAnalyzing();
    public void setupAll(StringMap<Document> _docs, Navigation _nav, Configuration _conf, AbstractNatBlockBuilder _builder, DualConfigurationContext _context) {
        AnalyzingDoc analyzingDoc_ = new AnalyzingDoc();
        analyzingDoc_.setContent(this);
        StringMap<BeanInfo> beansInfos_ = new StringMap<BeanInfo>();
        initInstancesPattern(_nav.getSession(), beansInfos_);
        _conf.getBeansInfos().addAllEntries(beansInfos_);
        preInitBeans(_nav.getSession());
        analyzingDoc_.setRendAnalysisMessages(_context.getAnalysisMessages());
        analyzingDoc_.setLanguages(_nav.getLanguages());
        _nav.getSession().setCurrentLanguage(_nav.getLanguage());

        _nav.getSession().getRenders().clear();
        _nav.getSession().setFiles(_nav.getFiles());
        analyzingDoc_.setup(_nav.getSession(), _context);


        natCode.setStandards(getContent());
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
        NatRendForwardInfos.buildExec(analyzingDoc_, d_, _conf);
    }

    @Override
    public String processString(Argument _arg, ContextEl _ctx, RendStackCall _stack) {
        return processString(_arg, _ctx);
    }

    public static String processString(Argument _arg, ContextEl _ctx) {
        return ExecCatOperation.getDisplayable(_arg, _ctx).getInstance();
    }

    public abstract ResultErrorStd getOtherResultBean(ContextEl _cont,
                                             ConstructorId _method, Struct... _args);


    public abstract ResultErrorStd setOtherResult(ContextEl _cont, ClassField _classField, Struct _instance, Struct _val);

    public NatAnalyzedCode getNatCode() {
        return natCode;
    }
}
