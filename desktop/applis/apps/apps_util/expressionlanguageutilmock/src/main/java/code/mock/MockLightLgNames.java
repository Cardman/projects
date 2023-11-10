package code.mock;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.*;
import code.expressionlanguage.analyze.errors.AnalysisMessages;
import code.expressionlanguage.analyze.files.CommentDelimiters;
import code.expressionlanguage.exec.Classes;
import code.expressionlanguage.exec.CommonExecutionInfos;
import code.expressionlanguage.exec.DefaultInitializer;
import code.expressionlanguage.fwd.Forwards;
import code.expressionlanguage.fwd.blocks.ForwardInfos;
import code.expressionlanguage.options.*;
import code.expressionlanguage.stds.AbstractInterceptorStdCaller;
import code.expressionlanguage.stds.BuildableLgNames;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.stds.ListLoggableLgNames;
import code.expressionlanguage.utilcompo.AbsPairRateLgIntType;
import code.maths.montecarlo.DefaultGenerator;
import code.sml.util.TranslationsFile;
import code.threads.ConcreteBoolean;
import code.util.CustList;
import code.util.StringMap;

public final class MockLightLgNames extends LgNames implements BuildableLgNames, AbsPairRateLgIntType {

    private String aliasLgInt = "";
    private String aliasRate = "";

    public MockLightLgNames() {
        super(DefaultGenerator.oneElt());
    }

    @Override
    public String getAliasLgInt() {
        return aliasLgInt;
    }

    public void setAliasLgInt(String _a) {
        this.aliasLgInt = _a;
    }

    @Override
    public String getAliasRate() {
        return aliasRate;
    }

    public void setAliasRate(String _a) {
        this.aliasRate = _a;
    }

    @Override
    public void build() {
        buildBase();
    }

    @Override
    public CommonExecutionInfos newContextCommon(Options _opt, Forwards _options) {
        return commonExecutionInfos(new MockInterceptorStdCaller(),_opt,_options,new DefaultInitializer());
    }

    @Override
    public AbstractInterceptorStdCaller interceptor() {
        return new MockInterceptorStdCaller();
    }
    public static ResultContext resultContext(Options _o,LgNames _lg, DefaultFileBuilder _d, TranslationsFile _tr, StringMap<String> _src, String _folder, AbstractSymbolFactory _a) {
        CustList<AbsAliasFileBuilder> bs_ = new CustList<AbsAliasFileBuilder>();
        bs_.add(new DefAliasFileBuilder());
        ResultContext b_ = resultContextCore(_o, _lg, _d, _tr, _a, bs_);
        ResultContext user_ = ResultContext.def(b_, _src, _folder);
        return fwd(user_);
    }

    public static ResultContext fwd(ResultContext _user) {
        Forwards f_ = _user.getForwards();
        ForwardInfos.generalForward(_user);
        ContextEl ctx_ = new MockContextGenerator(new ConcreteBoolean()).geneWith(f_);
        Classes.forwardAndClear(ctx_);
        _user.setContext(ctx_);
        return _user;
    }

    public static ResultContext resultContextCore(Options _o, LgNames _lg, AbstractFileBuilder _d, TranslationsFile _tr, AbstractSymbolFactory _a, CustList<AbsAliasFileBuilder> _builders) {
        AnalysisMessages mess_ = new AnalysisMessages();
        AnalyzedPageEl page_ = AnalyzedPageEl.setInnerAnalyzing();
        page_.setAbstractSymbolFactory(_a);
        page_.getFileBuilders().addAllElts(_builders);
        _lg.getContent().build(TranslationsFile.extractMap(_tr),new StringMap<String>(), TranslationsFile.extractKeys(_tr));
        _o.setReadOnly(true);
        Forwards forwards_ = new Forwards(_lg, new ListLoggableLgNames(), _d, _o);
        page_.setLogErr(forwards_);
        TranslationsFile k_ = KeyWords.en();
        KeyWords kwl_ = new KeyWords();
        kwl_.build(TranslationsFile.extractMap(k_),new StringMap<String>(), TranslationsFile.extractKeys(k_));
        ContextFactory.beforeBuild(forwards_,mess_,kwl_,new CustList<CommentDelimiters>(),_o,_lg.getContent(),page_);
        ContextFactory.build(forwards_,kwl_,_o,page_,new DefBuildLightResultContextNext());
        return new ResultContext(page_, forwards_);
    }
}
