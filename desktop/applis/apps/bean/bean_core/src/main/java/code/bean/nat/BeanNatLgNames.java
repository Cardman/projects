package code.bean.nat;

import code.bean.nat.analyze.NatRenderAnalysis;
import code.bean.nat.analyze.blocks.AnaRendBlockHelp;
import code.bean.nat.fwd.NatRendForwardInfos;
import code.expressionlanguage.analyze.*;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.exec.*;
import code.expressionlanguage.exec.opers.ExecCatOperation;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.functionid.ConstructorId;
import code.formathtml.analyze.AnalyzingDoc;
import code.formathtml.analyze.blocks.AnaRendDocumentBlock;
import code.formathtml.exec.RendStackCall;
import code.formathtml.fwd.DefaultInputBuilder;
import code.expressionlanguage.*;
import code.expressionlanguage.stds.*;
import code.expressionlanguage.structs.*;
import code.formathtml.*;
import code.formathtml.structs.BeanInfo;
import code.formathtml.util.DualAnalyzedContext;
import code.sml.Document;
import code.util.*;

public abstract class BeanNatLgNames extends BeanNatCommonLgNames {

    public void setupAll(StringMap<Document> _docs, Navigation _nav, Configuration _conf, DualAnalyzedContext _dual) {
        AnalyzingDoc analyzingDoc_ = new AnalyzingDoc();
        analyzingDoc_.setContent(this);
        analyzingDoc_.setInputBuilder(new DefaultInputBuilder());
        AnalyzedPageEl page_ = _dual.getAnalyzed();
        StringMap<BeanInfo> beansInfos_ = new StringMap<BeanInfo>();
        initInstancesPattern(_nav.getSession(), beansInfos_);
        _conf.getBeansInfos().addAllEntries(beansInfos_);
        preInitBeans(_nav.getSession());
        analyzingDoc_.setRendAnalysisMessages(_dual.getContext().getAnalysisMessages());
        analyzingDoc_.setLanguages(_nav.getLanguages());
        _nav.getSession().setCurrentLanguage(_nav.getLanguage());

        _nav.getSession().getRenders().clear();
        _nav.getSession().setFiles(_nav.getFiles());
        analyzingDoc_.setup(_nav.getSession(), _dual.getContext());
        NatRenderAnalysis.setupInts(page_, analyzingDoc_);


        StringMap<AnaRendDocumentBlock> d_ = new StringMap<AnaRendDocumentBlock>();
        for (EntryCust<String, Document> s: _docs.entryList()) {
            String link_ = s.getKey();
            Document document_ = s.getValue();
            String file_ = document_.export();
            AnaRendDocumentBlock anaDoc_ = AnaRendBlockHelp.newRendDocumentBlock(analyzingDoc_.getPrefix(), document_, file_, link_, analyzingDoc_.getRendKeyWords(), this);
            d_.addEntry(link_,anaDoc_);
        }
        for (AnaRendDocumentBlock v : d_.values()) {
            v.buildFctInstructions(analyzingDoc_, page_, beansInfos_);
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

    @Override
    public ResultErrorStd getOtherResult(StackCall _stack, ContextEl _cont,
                                         ConstructorId _method, Struct... _args) {
        return getOtherResultBean(_cont, _method, _args);
    }

    public abstract ResultErrorStd getOtherResultBean(ContextEl _cont,
                                             ConstructorId _method, Struct... _args);

    public abstract ResultErrorStd getOtherResultBean(ContextEl _cont, Struct _instance,
                                             ClassMethodId _method, Struct... _args);

    public abstract ResultErrorStd setOtherResult(ContextEl _cont, ClassField _classField, Struct _instance, Struct _val);

}
