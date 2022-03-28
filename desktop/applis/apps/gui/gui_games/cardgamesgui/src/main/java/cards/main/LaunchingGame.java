package cards.main;
import cards.belote.beans.BeloteStandards;
import cards.belote.beans.DetailsBeloteLoader;
import cards.belote.beans.ResultsBeloteLoader;
import cards.belote.beans.RulesBeloteLoader;
import cards.gui.WindowCards;
import cards.gui.animations.HelpInitializer;
import cards.gui.animations.PreparedPagesCards;
import cards.gui.dialogs.FileConst;
import cards.president.beans.PresidentStandards;
import cards.president.beans.ResultsPresidentLoader;
import cards.president.beans.RulesPresidentLoader;
import cards.tarot.beans.DetailsTarotLoader;
import cards.tarot.beans.ResultsTarotLoader;
import cards.tarot.beans.RulesTarotLoader;
import cards.tarot.beans.TarotStandards;
import code.gui.SoftApplicationCore;
import code.gui.TopLeftFrame;
import code.gui.initialize.AbstractProgramInfos;
import code.scripts.pages.cards.*;
import code.sml.Document;
import code.threads.AbstractThread;
import code.util.EntryCust;
import code.util.StringList;
import code.util.StringMap;
import code.util.consts.Constants;

/**This class thread is used by EDT (invokeLater of SwingUtilities),
Thread safe class*/
public final class LaunchingGame implements Runnable {

    private final AbstractProgramInfos list;
    private final StringList args;

    private final String language;

    private final TopLeftFrame topLeft;
    private final CardFactories cardFactories;

    /**This class thread is used by EDT (invokeLater of SwingUtilities)*/
    public LaunchingGame(StringList _args, String _language, TopLeftFrame _topLeft, AbstractProgramInfos _list, CardFactories _cardFactories) {
        args = _args;
        language = _language;
        topLeft = _topLeft;
        list = _list;
        cardFactories = _cardFactories;
    }

    @Override
    public void run() {
        StringMap<StringMap<PreparedPagesCards>> belote_ = generateAnalyzedBelote(PagesBelotes.build());
        StringMap<StringMap<PreparedPagesCards>> president_ = generateAnalyzedPresident(PagesPresidents.build());
        StringMap<StringMap<PreparedPagesCards>> tarot_ = generateAnalyzedTarot(PagesTarots.build());
        WindowCards window_ = new WindowCards(language, list, belote_,president_,tarot_,cardFactories);

        SoftApplicationCore.setLocation(window_.getCommonFrame(), topLeft);
        window_.pack();
        window_.setVisible(true);
        HelpInitializer helpInitializerTask_ = new HelpInitializer(window_.getGeneralHelp());
        AbstractThread helpInitializerThread_ = window_.getThreadFactory().newThread(helpInitializerTask_);
        helpInitializerThread_.start();
        window_.setHelpInitializerTask(helpInitializerTask_);

        if (!args.isEmpty()) {
            window_.loadGameBegin(args.first());
        }
    }

    public static StringMap<StringMap<PreparedPagesCards>> generateAnalyzedBelote(StringMap<Document> _built) {
        StringMap<StringMap<PreparedPagesCards>> belote_ = new StringMap<StringMap<PreparedPagesCards>>();
        StringMap<PreparedPagesCards> rulesBelote_ = new StringMap<PreparedPagesCards>();
        StringMap<String> other_ = MessBelotePage.ms();
        for (String l: Constants.getAvailableLanguages()) {
            rulesBelote_.addEntry(l,new PreparedPagesCards(l,new BeloteStandards(), new RulesBeloteLoader(), _built, other_));
        }
        belote_.addEntry(FileConst.RESOURCES_HTML_FILES_RULES_BELOTE,rulesBelote_);
        StringMap<PreparedPagesCards> resultsBelote_ = new StringMap<PreparedPagesCards>();
        for (String l: Constants.getAvailableLanguages()) {
            resultsBelote_.addEntry(l,new PreparedPagesCards(l,new BeloteStandards(), new ResultsBeloteLoader(), _built, other_));
        }
        belote_.addEntry(FileConst.RESOURCES_HTML_FILES_RESULTS_BELOTE,resultsBelote_);
        StringMap<PreparedPagesCards> detResultsBelote_ = new StringMap<PreparedPagesCards>();
        for (String l: Constants.getAvailableLanguages()) {
            detResultsBelote_.addEntry(l,new PreparedPagesCards(l,new BeloteStandards(), new DetailsBeloteLoader(), _built, other_));
        }
        belote_.addEntry(FileConst.RESOURCES_HTML_FILES_DETAILS_RESULTS_BELOTE,detResultsBelote_);
        for (EntryCust<String,StringMap<PreparedPagesCards>> f: belote_.entryList()) {
            for (EntryCust<String,PreparedPagesCards> g: f.getValue().entryList()) {
                g.getValue().run();
            }
        }
        return belote_;
    }

    public static StringMap<StringMap<PreparedPagesCards>> generateAnalyzedPresident(StringMap<Document> _built) {
        StringMap<StringMap<PreparedPagesCards>> president_ = new StringMap<StringMap<PreparedPagesCards>>();
        StringMap<PreparedPagesCards> rulesPresident_ = new StringMap<PreparedPagesCards>();
        StringMap<String> other_ = MessPresidentPage.ms();
        for (String l: Constants.getAvailableLanguages()) {
            rulesPresident_.addEntry(l,new PreparedPagesCards(l,new PresidentStandards(), new RulesPresidentLoader(), _built, other_));
        }
        president_.addEntry(FileConst.RESOURCES_HTML_FILES_RULES_PRESIDENT,rulesPresident_);
        StringMap<PreparedPagesCards> resultsPresident_ = new StringMap<PreparedPagesCards>();
        for (String l: Constants.getAvailableLanguages()) {
            resultsPresident_.addEntry(l,new PreparedPagesCards(l,new PresidentStandards(), new ResultsPresidentLoader(), _built, other_));
        }
        president_.addEntry(FileConst.RESOURCES_HTML_FILES_RESULTS_PRESIDENT,resultsPresident_);
        for (EntryCust<String,StringMap<PreparedPagesCards>> f: president_.entryList()) {
            for (EntryCust<String,PreparedPagesCards> g: f.getValue().entryList()) {
                g.getValue().run();
            }
        }
        return president_;
    }

    public static StringMap<StringMap<PreparedPagesCards>> generateAnalyzedTarot(StringMap<Document> _built) {
        StringMap<StringMap<PreparedPagesCards>> tarot_ = new StringMap<StringMap<PreparedPagesCards>>();
        StringMap<PreparedPagesCards> rulesTarot_ = new StringMap<PreparedPagesCards>();
        StringMap<String> other_ = MessTarotPage.ms();
        for (String l: Constants.getAvailableLanguages()) {
            rulesTarot_.addEntry(l,new PreparedPagesCards(l,new TarotStandards(), new RulesTarotLoader(), _built, other_));
        }
        tarot_.addEntry(FileConst.RESOURCES_HTML_FILES_RULES_TAROT,rulesTarot_);
        StringMap<PreparedPagesCards> resultsTarot_ = new StringMap<PreparedPagesCards>();
        for (String l: Constants.getAvailableLanguages()) {
            resultsTarot_.addEntry(l,new PreparedPagesCards(l,new TarotStandards(), new ResultsTarotLoader(), _built, other_));
        }
        tarot_.addEntry(FileConst.RESOURCES_HTML_FILES_RESULTS_TAROT,resultsTarot_);
        StringMap<PreparedPagesCards> detResultsTarot_ = new StringMap<PreparedPagesCards>();
        for (String l: Constants.getAvailableLanguages()) {
            detResultsTarot_.addEntry(l,new PreparedPagesCards(l,new TarotStandards(), new DetailsTarotLoader(), _built, other_));
        }
        tarot_.addEntry(FileConst.RESOURCES_HTML_FILES_DETAILS_RESULTS_TAROT,detResultsTarot_);
        for (EntryCust<String,StringMap<PreparedPagesCards>> f: tarot_.entryList()) {
            for (EntryCust<String,PreparedPagesCards> g: f.getValue().entryList()) {
                g.getValue().run();
            }
        }
        return tarot_;
    }
}
