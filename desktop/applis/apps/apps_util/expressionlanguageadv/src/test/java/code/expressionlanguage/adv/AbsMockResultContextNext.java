package code.expressionlanguage.adv;

import code.expressionlanguage.analyze.AbsAliasFileBuilder;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.DefAliasFileBuilder;
import code.expressionlanguage.analyze.DefaultAliasGroups;
import code.expressionlanguage.analyze.errors.AnalysisMessages;
import code.expressionlanguage.analyze.files.CommentDelimiters;
import code.expressionlanguage.analyze.instr.ParsedArgument;
import code.expressionlanguage.fwd.Forwards;
import code.expressionlanguage.gui.unit.UnitIssuer;
import code.expressionlanguage.guicompos.LgNamesGui;
import code.expressionlanguage.options.*;
import code.expressionlanguage.utilcompo.ExecutingOptions;
import code.expressionlanguage.utilcompo.FileInfos;
import code.expressionlanguage.utilimpl.CustContextFactory;
import code.expressionlanguage.utilimpl.LgNamesUtils;
import code.expressionlanguage.utilimpl.ManageOptions;
import code.gui.CdmFactory;
import code.gui.initialize.AbstractProgramInfos;
import code.mock.MockAliasFileBuilder;
import code.mock.MockFileBuilder;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;

public abstract class AbsMockResultContextNext extends AdvAbsResultContextNext {
    protected AbsMockResultContextNext(WindowCdmEditor _w, AbstractProgramInfos _frames, CdmFactory _progressingTests) {
        super(_w, _frames, _progressingTests);
    }
    @Override
    public ResultContext init(Options _opt) {
        ManageOptions man_ = getMainWindow().manage(getMainWindow().getSoftParams().getLines());
        FileInfos file_ = baseInit(man_, new UnitIssuer(getMainWindow().getStatusAnalyzeArea()));
        ExecutingOptions ex_ = man_.getEx();
        KeyWords kwl_ = new KeyWords();
        AnalysisMessages mess_ = new AnalysisMessages();
        _opt.setReadOnly(true);
        LgNamesGui stds_ = new LgNamesGui(file_, ex_.getInterceptor());
        CustContextFactory.preinit(_opt, ex_, mess_, kwl_, stds_);
        CustContextFactory.parts(ex_,stds_,new StringList());
        AnalyzedPageEl page_ = CustContextFactory.mapping(stds_);
        MockFileBuilder fileBuilder_ = new MockFileBuilder(new DefaultAliasGroups(stds_.getContent()));
        updateMockBuilders(stds_,page_,predef());
        Forwards forwards_ = CustContextFactory.fwd(_opt, stds_, fileBuilder_);
        page_.setLogErr(forwards_);
        ContextFactory.beforeBuild(forwards_,mess_,kwl_,new CustList<CommentDelimiters>(),_opt,stds_.getContent(),page_);
        StringMap<String> keyWords_ = kwl_.allKeyWords(page_.getMappingKeyWords());
        kwl_.validateKeyWordContents(keyWords_,page_);
        ParsedArgument.buildCustom(_opt, kwl_);
        build(stds_);
        ValidatorStandard.setupOverrides(page_);
        return new ResultContext(page_, forwards_);
    }

    public static void updateMockBuilders(LgNamesUtils _stds, AnalyzedPageEl _page, StringMap<String> _predef) {
        CustList<AbsAliasFileBuilder> builders_ = new CustList<AbsAliasFileBuilder>();
        builders_.add(new DefAliasFileBuilder());
        builders_.add(_stds.getExecContent().getCustAliases().getStringViewReplaceAliases());
        builders_.add(new MockAliasFileBuilder(_predef));
        CustList<AbsAliasFileBuilder> fbs_ = _page.getFileBuilders();
        fbs_.clear();
        fbs_.addAllElts(builders_);
    }
    protected abstract StringMap<String> predef();

    protected abstract void build(LgNamesGui _fwd);
}
