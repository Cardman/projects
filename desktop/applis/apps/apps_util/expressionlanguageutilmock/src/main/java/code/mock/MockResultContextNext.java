package code.mock;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.DefaultAliasGroups;
import code.expressionlanguage.analyze.blocks.ClassesUtil;
import code.expressionlanguage.analyze.errors.AnalysisMessages;
import code.expressionlanguage.analyze.files.CommentDelimiters;
import code.expressionlanguage.fwd.Forwards;
import code.expressionlanguage.options.ContextFactory;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.options.Options;
import code.expressionlanguage.options.ResultContext;
import code.expressionlanguage.stds.BuildableLgNames;
import code.expressionlanguage.stds.LgNamesContent;
import code.expressionlanguage.stds.ListLoggableLgNames;
import code.expressionlanguage.utilcompo.AbsAdvContextGenerator;
import code.expressionlanguage.utilcompo.AbsResultContextNext;
import code.expressionlanguage.utilcompo.StringViewReplaceAliases;
import code.sml.util.TranslationsFile;
import code.threads.AbstractAtomicBoolean;
import code.util.CustList;
import code.util.StringMap;

public final class MockResultContextNext implements AbsResultContextNext {
    private final String filter;
    private final StringMap<String> system;
    public MockResultContextNext(String _f) {
        this(_f,new StringMap<String>());
    }
    public MockResultContextNext(String _f, StringMap<String> _s) {
        filter = _f;
        system = _s;
    }

    @Override
    public ResultContext init(Options _opt) {
        KeyWords kwl_ = new KeyWords();
        AnalysisMessages mess_ = new AnalysisMessages();
        _opt.setReadOnly(true);
        MockLgNames stds_ = new MockLgNames();
        TranslationsFile en_ = new TranslationsFile();
        LgNamesContent.en(en_);
        StringViewReplaceAliases.en(en_);
        AnalyzedPageEl page_ = AnalyzedPageEl.setInnerAnalyzing();
        stds_.getContent().build(TranslationsFile.extractMap(en_),new StringMap<String>(), TranslationsFile.extractKeys(en_));
        stds_.getStrAlias().build(TranslationsFile.extractMap(en_),new StringMap<String>(), TranslationsFile.extractKeys(en_));
        TranslationsFile k_ = KeyWords.en();
        kwl_.build(TranslationsFile.extractMap(k_),new StringMap<String>(), TranslationsFile.extractKeys(k_));
        MockFileBuilder fileBuilder_ = new MockFileBuilder(stds_.getContent(), new DefaultAliasGroups(stds_.getContent()),stds_.getStrAlias());
        Forwards forwards_ = new Forwards(stds_, new ListLoggableLgNames(), fileBuilder_, _opt);
        page_.setLogErr(forwards_);
        ContextFactory.beforeBuild(forwards_,mess_,kwl_,new CustList<CommentDelimiters>(),_opt,stds_.getContent(),page_);
        ContextFactory.build(forwards_,kwl_,_opt,page_);
        ClassesUtil.buildCoreBracesBodies(page_);
        return new ResultContext(page_, forwards_, page_.getMessages());
    }

    @Override
    public ResultContext next(ResultContext _r, ResultContext _u) {
        BuildableLgNames lg_ = _r.getForwards().getGenerator();
        Forwards forwards_ = new Forwards(lg_, new ListLoggableLgNames(), _u.getForwards().getFileBuilder(), _u.getForwards().getOptions());
        return new ResultContext(_u.getPageEl(),forwards_,_u.getReportedMessages());
    }

    @Override
    public StringMap<String> files(ResultContext _r, StringMap<String> _files) {
        StringMap<String> v_ = new StringMap<String>();
        v_.addAllEntries(system);
        v_.putAllMap(_files);
        return v_;
    }

    @Override
    public AnalyzedPageEl nextAna(ResultContext _base, StringMap<String> _files) {
        return ResultContext.def(_base.getPageEl(), _files, filter);
    }

    @Override
    public ResultContext next(ResultContext _base, StringMap<String> _files) {
        StringMap<String> v_ = new StringMap<String>();
        v_.addAllEntries(system);
        v_.putAllMap(_files);
        return ResultContext.def(_base, _base.getForwards().getGenerator(), _base.getForwards().getLoggable(), v_, filter);
    }

    @Override
    public AbsAdvContextGenerator generate() {
        return generate(new MockAtomicBoolean());
    }

    @Override
    public AbsAdvContextGenerator generate(AbstractAtomicBoolean _at) {
        return new MockContextGenerator(_at);
    }

}
