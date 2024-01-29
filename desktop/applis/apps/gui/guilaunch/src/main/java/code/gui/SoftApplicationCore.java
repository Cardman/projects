package code.gui;

import aiki.facade.SexListImpl;
import aiki.main.AikiFactory;
import aiki.sml.DefLoadingData;
import cards.belote.beans.*;
import cards.gui.dialogs.FileConst;
import cards.main.CallablePreparedPagesCards;
import cards.main.CardFactories;
import cards.president.beans.*;
import cards.tarot.beans.*;
import code.gui.files.FileDialog;
import code.gui.images.AbstractImage;
import code.gui.initialize.AbstractProgramInfos;
import code.scripts.pages.cards.*;
import code.sml.NavigationCore;
import code.util.StringList;
import code.util.StringMap;

public abstract class SoftApplicationCore {

    private final WithAppFactories factories;

    protected SoftApplicationCore(WithAppFactories _frames) {
        factories = _frames;
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
        a_.submit(new DefLoadingData(frs_.getGenerator(), lgs_, frs_.getDisplayLanguages(),new SexListImpl()));
        CardFactories cf_ = factories_.getCardFactories();
        belote(cf_, lgs_, beloteMsg());
        president(cf_, lgs_, presidentMsg());
        tarot(cf_, lgs_, tarotMsg());
        cf_.submitHelp(frs_);
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
        launch(_lg, _args);
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


    protected abstract void launch(String _language, String[] _args);

    public AbstractProgramInfos getFrames() {
        return getFactories().getProgramInfos();
    }

    public WithAppFactories getFactories() {
        return factories;
    }
}
