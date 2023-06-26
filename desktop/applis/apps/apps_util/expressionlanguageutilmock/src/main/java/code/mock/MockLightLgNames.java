package code.mock;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.*;
import code.expressionlanguage.analyze.blocks.ClassesUtil;
import code.expressionlanguage.analyze.errors.AnalysisMessages;
import code.expressionlanguage.analyze.files.CommentDelimiters;
import code.expressionlanguage.exec.Classes;
import code.expressionlanguage.exec.CommonExecutionInfos;
import code.expressionlanguage.exec.DefaultInitializer;
import code.expressionlanguage.fwd.Forwards;
import code.expressionlanguage.fwd.blocks.ForwardInfos;
import code.expressionlanguage.options.ContextFactory;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.options.Options;
import code.expressionlanguage.options.ResultContext;
import code.expressionlanguage.stds.BuildableLgNames;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.stds.LgNamesContent;
import code.expressionlanguage.stds.ListLoggableLgNames;
import code.expressionlanguage.utilcompo.AbsPairRateLgIntType;
import code.maths.montecarlo.DefaultGenerator;
import code.sml.util.TranslationsFile;
import code.util.CustList;
import code.util.StringMap;

public final class MockLightLgNames extends LgNames implements BuildableLgNames, AbsPairRateLgIntType {

    private final MockPairRateLgIntType pair;
    public MockLightLgNames(MockPairRateLgIntType _r) {
        super(DefaultGenerator.oneElt());
        pair = _r;
    }

    @Override
    public String getAliasLgInt() {
        return pair.getAliasLgInt();
    }

    @Override
    public String getAliasRate() {
        return pair.getAliasRate();
    }

    @Override
    public void build() {
        buildBase();
    }

    @Override
    public CommonExecutionInfos newContextCommon(Options _opt, Forwards _options) {
        return commonExecutionInfos(new MockInterceptorStdCaller(),_opt,_options,new DefaultInitializer());
    }

    public static ResultContext resultContext(MockPairRateLgIntType _r,StringMap<String> _src, String _folder) {
        TranslationsFile en_ = new TranslationsFile();
        LgNamesContent.en(en_);
        MockLightLgNames m_ = new MockLightLgNames(_r);
        return resultContext(new Options(),m_,new DefaultFileBuilder(m_.getContent(), new DefaultAliasGroups(m_.getContent())),en_,_src, _folder, new DefSymbolFactory());
    }
    public static ResultContext resultContext(Options _o,LgNames _lg, DefaultFileBuilder _d, TranslationsFile _tr, StringMap<String> _src, String _folder, AbstractSymbolFactory _a) {
        AnalysisMessages mess_ = new AnalysisMessages();
        AnalyzedPageEl page_ = AnalyzedPageEl.setInnerAnalyzing();
        page_.setAbstractSymbolFactory(_a);
        _lg.getContent().build(TranslationsFile.extractMap(_tr),new StringMap<String>(), TranslationsFile.extractKeys(_tr));
        _o.setReadOnly(true);
        Forwards forwards_ = new Forwards(_lg, new ListLoggableLgNames(), _d, _o);
        page_.setLogErr(forwards_);
        TranslationsFile k_ = KeyWords.en();
        KeyWords kwl_ = new KeyWords();
        kwl_.build(TranslationsFile.extractMap(k_),new StringMap<String>(), TranslationsFile.extractKeys(k_));
        ContextFactory.beforeBuild(forwards_,mess_,kwl_,new CustList<CommentDelimiters>(),_o,_lg.getContent(),page_);
        ContextFactory.build(forwards_,kwl_,_o,page_);
        ClassesUtil.buildCoreBracesBodies(page_);
        ResultContext b_ = new ResultContext(page_, forwards_, page_.getMessages());
        ResultContext user_ = ResultContext.def(b_, _src, _folder);
        Forwards f_ = user_.getForwards();
        ForwardInfos.generalForward(user_);
        ContextEl ctx_ = new MockContextGenerator(new MockAtomicBoolean()).geneWith(f_);
        Classes.forwardAndClear(ctx_);
        user_.setContext(ctx_);
        return user_;
    }
}
