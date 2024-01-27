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
        return factories.getAppFactories();
    }

    protected void loadLaungage(String _dir, String[] _args, AbstractImage _icon) {
        String lg_ = prepareLanguage(_dir, _args, _icon);
        AppFactories factories_ = getAppFactories();
        AikiFactory a_ = factories_.getAikiFactory();
        a_.submit(new DefLoadingData(getFrames().getGenerator(), getFrames().getLanguages(), getFrames().getDisplayLanguages(),new SexListImpl()));
        CardFactories cf_ = factories_.getCardFactories();
        StringList lgs_ = getFrames().getLanguages();
        belote(cf_, lgs_);
        president(cf_, lgs_);
        tarot(cf_, lgs_);
        if (lg_.isEmpty()) {
            return;
        }
        launchFile(_args, lg_);
    }

    private void belote(CardFactories _cf, StringList _lgs) {
        StringMap<String> other_ = MessBelotePage.ms();
        NavigationCore.adjust(other_);
        _cf.submitNav(FileConst.RESOURCES_HTML_FILES_RULES_BELOTE,new CallablePreparedPagesCards(new BeloteStandardsRules(), new RulesBeloteLoader(), PagesBelotes.buildRules(), other_, _lgs));
        _cf.submitNav(FileConst.RESOURCES_HTML_FILES_RESULTS_BELOTE,new CallablePreparedPagesCards(new BeloteStandardsResults(), new ResultsBeloteLoader(), PagesBelotes.build(), other_, _lgs));
        _cf.submitNav(FileConst.RESOURCES_HTML_FILES_DETAILS_RESULTS_BELOTE,new CallablePreparedPagesCards(new BeloteStandardsDetailResults(), new DetailsBeloteLoader(), PagesBelotes.buildDetails(), other_, _lgs));
    }
    private void president(CardFactories _cf, StringList _lgs) {
        StringMap<String> other_ = MessPresidentPage.ms();
        NavigationCore.adjust(other_);
        _cf.submitNav(FileConst.RESOURCES_HTML_FILES_RULES_PRESIDENT,new CallablePreparedPagesCards(new PresidentStandardsRules(), new RulesPresidentLoader(), PagesPresidents.buildRules(), other_, _lgs));
        _cf.submitNav(FileConst.RESOURCES_HTML_FILES_RESULTS_PRESIDENT,new CallablePreparedPagesCards(new PresidentStandardsResults(), new ResultsPresidentLoader(), PagesPresidents.build(), other_, _lgs));
    }
    private void tarot(CardFactories _cf, StringList _lgs) {
        StringMap<String> other_ = MessTarotPage.ms();
        NavigationCore.adjust(other_);
        _cf.submitNav(FileConst.RESOURCES_HTML_FILES_RULES_TAROT,new CallablePreparedPagesCards(new TarotStandardsRules(), new RulesTarotLoader(), PagesTarots.buildRules(), other_, _lgs));
        _cf.submitNav(FileConst.RESOURCES_HTML_FILES_RESULTS_TAROT,new CallablePreparedPagesCards(new TarotStandardsResults(), new ResultsTarotLoader(), PagesTarots.build(), other_, _lgs));
        _cf.submitNav(FileConst.RESOURCES_HTML_FILES_DETAILS_RESULTS_TAROT,new CallablePreparedPagesCards(new TarotStandardsDetailResults(), new DetailsTarotLoader(), PagesTarots.buildDetails(), other_, _lgs));
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
        return factories.getProgramInfos();
    }

    public WithAppFactories getFactories() {
        return factories;
    }
}
