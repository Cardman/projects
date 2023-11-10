package code.mock;

import code.expressionlanguage.analyze.*;
import code.expressionlanguage.exec.AbsStackStopper;
import code.expressionlanguage.exec.DefStackStopper;
import code.expressionlanguage.fwd.Forwards;
import code.expressionlanguage.options.Options;
import code.expressionlanguage.options.ResultContext;
import code.expressionlanguage.stds.LgNamesContent;
import code.expressionlanguage.utilcompo.AbsAdvContextGenerator;
import code.expressionlanguage.utilcompo.AbsResultContextNext;
import code.expressionlanguage.utilcompo.StringViewReplaceAliases;
import code.sml.util.TranslationsFile;
import code.threads.AbstractAtomicBoolean;
import code.threads.ConcreteBoolean;
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
    public ResultContext init(Options _opt, boolean _light) {
        _opt.setReadOnly(true);
        MockLgNames stds_ = new MockLgNames();
        TranslationsFile en_ = new TranslationsFile();
        LgNamesContent.en(en_);
        StringViewReplaceAliases.en(en_);
        stds_.getStrAlias().build(TranslationsFile.extractMap(en_),new StringMap<String>(), TranslationsFile.extractKeys(en_));
        AbstractFileBuilder fileBuilder_ = new MockFileBuilder(new DefaultAliasGroups(stds_.getContent()));
        CustList<AbsAliasFileBuilder> bs_ = new CustList<AbsAliasFileBuilder>();
        bs_.add(new DefAliasFileBuilder());
        bs_.add(stds_.getStrAlias());
        return MockLightLgNames.resultContextCore(_opt,stds_,fileBuilder_,en_,new DefSymbolFactory(), bs_);
    }

    @Override
    public ResultContext next(ResultContext _r, ResultContext _u) {
        Forwards forwards_ = new Forwards(_u.getForwards(),new DefStackStopper());
        forwards_.getClasses().getCommon().setStaticFields(_u.getPageEl().getStaticFields());
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
    public ResultContext next(ResultContext _base, StringMap<String> _files, AbsStackStopper _s) {
        StringMap<String> v_ = new StringMap<String>();
        v_.addAllEntries(system);
        v_.putAllMap(_files);
        return ResultContext.def(_base, v_, filter, _s);
    }

    @Override
    public AbsAdvContextGenerator generate() {
        return generateAdv(new ConcreteBoolean());
    }

    @Override
    public void generate(ResultContext _r) {
        ResultContext.fwdWithoutCheck(_r,generate());
    }

    @Override
    public AbsAdvContextGenerator generateAdv(AbstractAtomicBoolean _at) {
        return new MockContextGenerator(_at);
    }
}
