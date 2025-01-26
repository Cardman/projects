package applications.code.gui;

import aiki.beans.*;
import aiki.db.*;
import aiki.facade.*;
import aiki.gui.threads.*;
import aiki.main.*;
import aiki.sml.*;
import applications.code.gui.initialize.LoadLanguageUtil;
import cards.belote.beans.*;
import cards.gui.dialogs.*;
import cards.main.*;
import cards.president.beans.*;
import cards.tarot.beans.*;
import code.gui.AbsButton;
import code.gui.EnabledMenu;
import code.gui.InterpretedFile;
import code.gui.LanguagesButtonsPair;
import code.gui.document.MessagesPkBean;
import code.gui.files.*;
import code.gui.images.*;
import code.gui.initialize.*;
import code.maths.montecarlo.*;
import code.scripts.pages.aiki.*;
import code.scripts.pages.cards.*;
import code.sml.util.*;
import code.util.*;

public abstract class SoftApplicationCore {

    private final WithAppFactories factories;
//    private final EnabledMenu languageMenu;
    private LanguageFrame languageFrame;

    protected SoftApplicationCore(WithAppFactories _frames) {
        factories = _frames;
//        languageMenu = getFrames().getCompoFactory().newMenuItem();
    }

    public AppFactories getAppFactories() {
        return getFactories().getAppFactories();
    }
    public void loadLanguage(String[] _args) {
        LoadLanguageUtil.loadLaungage(this, factories.getAppFactories().getTmpFolder(), _args);
    }
    protected void loadLaungage(String _dir, String[] _args, AbstractImage _icon) {
        submitTasks();
        String lg_ = prepareLanguage(_dir, _args, _icon);
        if (lg_.isEmpty()) {
            return;
        }
        launchFile(_args, lg_);
    }

    private void submitTasks() {
        AppFactories factories_ = getAppFactories();
        AikiFactory a_ = factories_.getAikiFactory();
        AbstractProgramInfos frs_ = getFrames();
        StringList lgs_ = new StringList(frs_.getTranslations().getMapping().getKeys());
        aiki(a_,lgs_,frs_.getTranslations(),frs_.getDisplayLanguages(),frs_.getGenerator());
        CardFactories cf_ = factories_.getCardFactories();
        belote(cf_, lgs_, frs_.getTranslations().byAppl(MessBelotePage.APP_BEAN));
        president(cf_, lgs_, frs_.getTranslations().byAppl(MessPresidentPage.APP_BEAN));
        tarot(cf_, lgs_, frs_.getTranslations().byAppl(MessTarotPage.APP_BEAN));
        cf_.submitHelp();
    }
    private static void aiki(AikiFactory _af, StringList _lgs, Translations _msg, StringMap<String> _dis, AbstractGenerator _gene) {
        _af.submit(new DefLoadingData(_gene, _lgs, _dis,new SexListImpl(), GamesPk.baseEncode(_msg), _msg.byAppl(MessagesDataBaseConstants.SC_APP)));
        StringMap<String> builtOther_ = CssInit.ms();
        _af.submitNavData(new DataWebInit(new PreparedRenderedPages(new DataGameInit(), PagesInit.build(), _msg.byAppl(MessagesInit.APP_BEAN_DATA), builtOther_, new PkData(), _lgs),_af.getGeneralHelp()));
        _af.submitNavFight(new DataWebInit(new PreparedRenderedPages(new FightGameInit(), PagesInit.buildFight(), _msg.byAppl(MessagesPkBean.APP_BEAN_FIGHT), builtOther_, new PkFight(), _lgs),null));
        StringMap<TranslationsAppli> stds_ = _msg.byAppl(MessagesInit.APP_BEAN);
//        _af.submitNavPkTask(new DataWebInit(new PreparedRenderedPages(new DetPkGameInit(), PagesInit.buildInd(), stds_, builtOther_, new PkInd(), _lgs),null));
//        _af.submitNavPkNetTask(new DataWebInit(new PreparedRenderedPages(new DetPkGameInit(), PagesInit.buildInd(), stds_, builtOther_, new PkInd(), _lgs),null));
        _af.submitNavDiffTask(new DataWebInit(new PreparedRenderedPages(new DiffGameInit(), PagesInit.buildDiff(), stds_, builtOther_, new PkDiff(), _lgs),null));
        _af.submitNavProgTask(new DataWebInit(new PreparedRenderedPages(new ProgGameInit(), PagesInit.buildProg(), stds_, builtOther_, new PkProg(), _lgs),null));
    }

    private static void belote(CardFactories _cf, StringList _lgs, StringMap<TranslationsAppli> _msg) {
        StringMap<String> other_ = MessBelotePage.ms();
        _cf.submitNav(FrameGeneralHelp.RESOURCES_HTML_FILES_RULES_BELOTE,new CallablePreparedPagesCards(new BeloteStandardsRules(), new RulesBeloteLoader(), PagesBelotes.buildRules(), _msg, other_, _lgs));
        _cf.submitNav(FrameGeneralHelp.RESOURCES_HTML_FILES_RESULTS_BELOTE,new CallablePreparedPagesCards(new BeloteStandardsResults(), new ResultsBeloteLoader(), PagesBelotes.build(), _msg, other_, _lgs));
        _cf.submitNav(FrameGeneralHelp.RESOURCES_HTML_FILES_DETAILS_RESULTS_BELOTE,new CallablePreparedPagesCards(new BeloteStandardsDetailResults(), new DetailsBeloteLoader(), PagesBelotes.buildDetails(), _msg, other_, _lgs));
    }

    private static void president(CardFactories _cf, StringList _lgs, StringMap<TranslationsAppli> _msg) {
        StringMap<String> other_ = MessPresidentPage.ms();
        _cf.submitNav(FrameGeneralHelp.RESOURCES_HTML_FILES_RULES_PRESIDENT,new CallablePreparedPagesCards(new PresidentStandardsRules(), new RulesPresidentLoader(), PagesPresidents.buildRules(), _msg, other_, _lgs));
        _cf.submitNav(FrameGeneralHelp.RESOURCES_HTML_FILES_RESULTS_PRESIDENT,new CallablePreparedPagesCards(new PresidentStandardsResults(), new ResultsPresidentLoader(), PagesPresidents.build(), _msg, other_, _lgs));
    }

    private static void tarot(CardFactories _cf, StringList _lgs, StringMap<TranslationsAppli> _msg) {
        StringMap<String> other_ = MessTarotPage.ms();
        _cf.submitNav(FrameGeneralHelp.RESOURCES_HTML_FILES_RULES_TAROT,new CallablePreparedPagesCards(new TarotStandardsRules(), new RulesTarotLoader(), PagesTarots.buildRules(), _msg, other_, _lgs));
        _cf.submitNav(FrameGeneralHelp.RESOURCES_HTML_FILES_RESULTS_TAROT,new CallablePreparedPagesCards(new TarotStandardsResults(), new ResultsTarotLoader(), PagesTarots.build(), _msg, other_, _lgs));
        _cf.submitNav(FrameGeneralHelp.RESOURCES_HTML_FILES_DETAILS_RESULTS_TAROT,new CallablePreparedPagesCards(new TarotStandardsDetailResults(), new DetailsTarotLoader(), PagesTarots.buildDetails(), _msg, other_, _lgs));
    }

    protected void launchFile(String[] _args, String _lg) {
        getFrames().setLanguage(_lg);
        factories.getAppFactories().getCdmFactory().getProgramInfos().setLanguage(_lg);
        launch(_lg, new InterpretedFile(getFrames(),_args), null, null, new LanguagesButtonsPair(null,null,null));
    }

    protected final String prepareLanguage(String _dir, String[] _args, AbstractImage _icon) {
        String language_ = FileDialog.loadLanguage(_dir,getFrames().getFileCoreStream(), getFrames().getStreams(), getFrames().getLanguages());
        if (language_.isEmpty()) {
            languageFrame = proponeLanguage(_dir, _args, _icon);
        }
        return language_;
    }

    private LanguageFrame proponeLanguage(String _dir, String[] _args, AbstractImage _icon) {
        return new LanguageFrame(_dir, _args, this, _icon);
    }

//    protected StringList getFile(String[] _args) {
//        StringList files_ = new StringList();
//        if (_args.length > 0) {
//            String fileName_ = getFrames().getFileCoreStream().newFile(_args[0]).getAbsolutePath();
//            fileName_ = StringUtil.replaceBackSlash(fileName_);
//            files_.add(fileName_);
//        }
//        return files_;
//    }

    protected abstract void launch(String _language, InterpretedFile _args, EnabledMenu _lgMenu, AbsButton _main, LanguagesButtonsPair _pair);

    public LanguageFrame getLanguageFrame() {
        return languageFrame;
    }

//    public EnabledMenu getLanguageMenu() {
//        return languageMenu;
//    }

    public AbstractProgramInfos getFrames() {
        return getFactories().getProgramInfos();
    }

    public WithAppFactories getFactories() {
        return factories;
    }
}
