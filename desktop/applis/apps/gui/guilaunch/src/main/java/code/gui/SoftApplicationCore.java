package code.gui;

import aiki.beans.*;
import aiki.facade.*;
import aiki.gui.threads.*;
import aiki.main.*;
import aiki.sml.*;
import cards.belote.beans.*;
import cards.gui.dialogs.*;
import cards.main.*;
import cards.president.beans.*;
import cards.tarot.beans.*;
import code.gui.files.*;
import code.gui.images.*;
import code.gui.initialize.*;
import code.maths.montecarlo.*;
import code.scripts.pages.aiki.*;
import code.scripts.pages.cards.*;
import code.sml.*;
import code.util.*;
import code.util.core.StringUtil;

public abstract class SoftApplicationCore {

    private final WithAppFactories factories;
    private final EnabledMenu languageMenu;

    protected SoftApplicationCore(WithAppFactories _frames) {
        factories = _frames;
        languageMenu = getFrames().getCompoFactory().newMenuItem();
    }

    public AppFactories getAppFactories() {
        return getFactories().getAppFactories();
    }

    protected void loadLaungage(String _dir, String[] _args, AbstractImage _icon) {
        String lg_ = prepareLanguage(_dir, _args, _icon);
        submitTasks();
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
        aiki(a_,lgs_,aikiMsg(),frs_.getDisplayLanguages(),frs_.getGenerator());
        CardFactories cf_ = factories_.getCardFactories();
        belote(cf_, lgs_, beloteMsg());
        president(cf_, lgs_, presidentMsg());
        tarot(cf_, lgs_, tarotMsg());
        cf_.submitHelp(frs_);
    }
    private static void aiki(AikiFactory _af, StringList _lgs, StringMap<String> _msg, StringMap<String> _dis, AbstractGenerator _gene) {
        _af.submit(new DefLoadingData(_gene, _lgs, _dis,new SexListImpl()));
        StringMap<String> builtOther_ = CssInit.ms();
        _af.submitNav(new DataWebInit(new PreparedRenderedPages(Resources.ACCESS_TO_DEFAULT_FILES, new DataGameInit(), PagesInit.build(), _msg, builtOther_, new PkData(), _lgs),_af.getGeneralHelp()));
        PreparedRenderedPages fight_ = new PreparedRenderedPages(Resources.ACCESS_TO_DEFAULT_FILES, new FightGameInit(), PagesInit.buildFight(), _msg, builtOther_, new PkFight(), _lgs);
        PreparedRenderedPages pk_ = new PreparedRenderedPages(Resources.ACCESS_TO_DEFAULT_FILES, new DetPkGameInit(), PagesInit.buildInd(), _msg, builtOther_, new PkInd(), _lgs);
        PreparedRenderedPages pkNet_ = new PreparedRenderedPages(Resources.ACCESS_TO_DEFAULT_FILES, new DetPkGameInit(), PagesInit.buildInd(), _msg, builtOther_, new PkInd(), _lgs);
        PreparedRenderedPages diff_ = new PreparedRenderedPages(Resources.ACCESS_TO_DEFAULT_FILES, new DiffGameInit(), PagesInit.buildDiff(), _msg, builtOther_, new PkDiff(), _lgs);
        PreparedRenderedPages prog_ = new PreparedRenderedPages(Resources.ACCESS_TO_DEFAULT_FILES, new ProgGameInit(), PagesInit.buildProg(), _msg, builtOther_, new PkProg(), _lgs);
        fight_.run();
        _af.setPreparedFightTask(new AikiNatLgNamesNavigation(fight_.getBeanNatLgNames(),fight_.getNavigation()));
        pk_.run();
        _af.setPreparedPkTask(new AikiNatLgNamesNavigation(pk_.getBeanNatLgNames(),pk_.getNavigation()));
        pkNet_.run();
        _af.setPreparedPkNetTask(new AikiNatLgNamesNavigation(pkNet_.getBeanNatLgNames(),pkNet_.getNavigation()));
        diff_.run();
        _af.setPreparedDiffTask(new AikiNatLgNamesNavigation(diff_.getBeanNatLgNames(),diff_.getNavigation()));
        prog_.run();
        _af.setPreparedProgTask(new AikiNatLgNamesNavigation(prog_.getBeanNatLgNames(),prog_.getNavigation()));
    }
    private static StringMap<String> aikiMsg() {
        StringMap<String> builtMessages_ = MessagesInit.ms();
        NavigationCore.adjust(builtMessages_);
        return builtMessages_;
    }

    private static void belote(CardFactories _cf, StringList _lgs, StringMap<String> _msg) {
        _cf.submitNav(FileConst.RESOURCES_HTML_FILES_RULES_BELOTE,new CallablePreparedPagesCards(new BeloteStandardsRules(), new RulesBeloteLoader(), PagesBelotes.buildRules(), _msg, _lgs));
        _cf.submitNav(FileConst.RESOURCES_HTML_FILES_RESULTS_BELOTE,new CallablePreparedPagesCards(new BeloteStandardsResults(), new ResultsBeloteLoader(), PagesBelotes.build(), _msg, _lgs));
        _cf.submitNav(FileConst.RESOURCES_HTML_FILES_DETAILS_RESULTS_BELOTE,new CallablePreparedPagesCards(new BeloteStandardsDetailResults(), new DetailsBeloteLoader(), PagesBelotes.buildDetails(), _msg, _lgs));
    }

    private static StringMap<String> beloteMsg() {
        StringMap<String> other_ = MessBelotePage.ms();
        NavigationCore.adjust(other_);
        return other_;
    }

    private static void president(CardFactories _cf, StringList _lgs, StringMap<String> _msg) {
        _cf.submitNav(FileConst.RESOURCES_HTML_FILES_RULES_PRESIDENT,new CallablePreparedPagesCards(new PresidentStandardsRules(), new RulesPresidentLoader(), PagesPresidents.buildRules(), _msg, _lgs));
        _cf.submitNav(FileConst.RESOURCES_HTML_FILES_RESULTS_PRESIDENT,new CallablePreparedPagesCards(new PresidentStandardsResults(), new ResultsPresidentLoader(), PagesPresidents.build(), _msg, _lgs));
    }

    private static StringMap<String> presidentMsg() {
        StringMap<String> other_ = MessPresidentPage.ms();
        NavigationCore.adjust(other_);
        return other_;
    }

    private static void tarot(CardFactories _cf, StringList _lgs, StringMap<String> _msg) {
        _cf.submitNav(FileConst.RESOURCES_HTML_FILES_RULES_TAROT,new CallablePreparedPagesCards(new TarotStandardsRules(), new RulesTarotLoader(), PagesTarots.buildRules(), _msg, _lgs));
        _cf.submitNav(FileConst.RESOURCES_HTML_FILES_RESULTS_TAROT,new CallablePreparedPagesCards(new TarotStandardsResults(), new ResultsTarotLoader(), PagesTarots.build(), _msg, _lgs));
        _cf.submitNav(FileConst.RESOURCES_HTML_FILES_DETAILS_RESULTS_TAROT,new CallablePreparedPagesCards(new TarotStandardsDetailResults(), new DetailsTarotLoader(), PagesTarots.buildDetails(), _msg, _lgs));
    }

    private static StringMap<String> tarotMsg() {
        StringMap<String> other_ = MessTarotPage.ms();
        NavigationCore.adjust(other_);
        return other_;
    }

    protected void launchFile(String[] _args, String _lg) {
        getFrames().setLanguage(_lg);
        launch(_lg, _args, languageMenu);
    }

    protected final String prepareLanguage(String _dir, String[] _args, AbstractImage _icon) {
        String language_ = FileDialog.loadLanguage(_dir,getFrames().getFileCoreStream(), getFrames().getStreams(), getFrames().getLanguages());
        if (language_.isEmpty()) {
            proponeLanguage(_dir, _args, _icon);
        }
        return language_;
    }

    private LanguageFrame proponeLanguage(String _dir, String[] _args, AbstractImage _icon) {
        return new LanguageFrame(_dir, _args, this, _icon);
    }

    protected StringList getFile(String[] _args) {
        StringList files_ = new StringList();
        if (_args.length > 0) {
            String fileName_ = getFrames().getFileCoreStream().newFile(_args[0]).getAbsolutePath();
            fileName_ = StringUtil.replaceBackSlash(fileName_);
            files_.add(fileName_);
        }
        return files_;
    }

    protected abstract void launch(String _language, String[] _args, EnabledMenu _lgMenu);

    public EnabledMenu getLanguageMenu() {
        return languageMenu;
    }

    public AbstractProgramInfos getFrames() {
        return getFactories().getProgramInfos();
    }

    public WithAppFactories getFactories() {
        return factories;
    }
}
