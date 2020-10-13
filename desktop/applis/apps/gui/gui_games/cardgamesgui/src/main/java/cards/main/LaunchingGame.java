package cards.main;
import cards.belote.beans.BeloteStandards;
import cards.facade.enumerations.GameEnum;
import cards.gui.MainWindow;
import cards.gui.animations.HelpInitializer;
import cards.gui.animations.PreparedPagesCards;
import cards.gui.dialogs.FileConst;
import cards.president.beans.PresidentStandards;
import cards.tarot.beans.TarotStandards;
import code.gui.CustComponent;
import code.gui.GroupFrame;
import code.gui.SoftApplicationCore;
import code.gui.TopLeftFrame;
import code.util.CustList;
import code.util.EntryCust;
import code.util.IdMap;
import code.util.StringMap;
import code.util.consts.Constants;

/**This class thread is used by EDT (invokeLater of SwingUtilities),
Thread safe class*/
public final class LaunchingGame implements Runnable {

    private final CustList<GroupFrame> list;
    private StringMap<Object> args;

    private String language;

    private TopLeftFrame topLeft;

    /**This class thread is used by EDT (invokeLater of SwingUtilities)*/
    public LaunchingGame(StringMap<Object> _args, String _language, TopLeftFrame _topLeft, CustList<GroupFrame> _list) {
        args = _args;
        language = _language;
        topLeft = _topLeft;
        list = _list;
    }

    @Override
    public void run() {
        IdMap<GameEnum, StringMap<StringMap<PreparedPagesCards>>> prepared_ = generateAnalyzed();
        MainWindow window_ = new MainWindow(language, list, prepared_);

        SoftApplicationCore.setLocation(window_, topLeft);
        window_.pack();
        window_.setVisible(true);
        HelpInitializer helpInitializerTask_ = new HelpInitializer();
        Thread helpInitializerThread_ = CustComponent.newThread(helpInitializerTask_);
        helpInitializerThread_.start();
        window_.setHelpInitializerTask(helpInitializerTask_);
        window_.setHelpInitializerThread(helpInitializerThread_);

        if (!args.isEmpty()) {
            window_.loadGameBegin(args.getKeys().first(), args.values().first());
        }
    }

    public static IdMap<GameEnum, StringMap<StringMap<PreparedPagesCards>>> generateAnalyzed() {
        IdMap<GameEnum, StringMap<StringMap<PreparedPagesCards>>> prepared_ = new IdMap<GameEnum, StringMap<StringMap<PreparedPagesCards>>>();
        StringMap<StringMap<PreparedPagesCards>> belote_ = new StringMap<StringMap<PreparedPagesCards>>();
        StringMap<PreparedPagesCards> rulesBelote_ = new StringMap<PreparedPagesCards>();
        for (String l: Constants.getAvailableLanguages()) {
            rulesBelote_.addEntry(l,new PreparedPagesCards(FileConst.RESOURCES_HTML_FILES_RULES_BELOTE,l,new BeloteStandards()));
        }
        belote_.addEntry(FileConst.RESOURCES_HTML_FILES_RULES_BELOTE,rulesBelote_);
        StringMap<PreparedPagesCards> resultsBelote_ = new StringMap<PreparedPagesCards>();
        for (String l: Constants.getAvailableLanguages()) {
            resultsBelote_.addEntry(l,new PreparedPagesCards(FileConst.RESOURCES_HTML_FILES_RESULTS_BELOTE,l,new BeloteStandards()));
        }
        belote_.addEntry(FileConst.RESOURCES_HTML_FILES_RESULTS_BELOTE,resultsBelote_);
        StringMap<PreparedPagesCards> detResultsBelote_ = new StringMap<PreparedPagesCards>();
        for (String l: Constants.getAvailableLanguages()) {
            detResultsBelote_.addEntry(l,new PreparedPagesCards(FileConst.RESOURCES_HTML_FILES_DETAILS_RESULTS_BELOTE,l,new BeloteStandards()));
        }
        belote_.addEntry(FileConst.RESOURCES_HTML_FILES_DETAILS_RESULTS_BELOTE,detResultsBelote_);
        prepared_.addEntry(GameEnum.BELOTE,belote_);
        StringMap<StringMap<PreparedPagesCards>> president_ = new StringMap<StringMap<PreparedPagesCards>>();
        StringMap<PreparedPagesCards> rulesPresident_ = new StringMap<PreparedPagesCards>();
        for (String l: Constants.getAvailableLanguages()) {
            rulesPresident_.addEntry(l,new PreparedPagesCards(FileConst.RESOURCES_HTML_FILES_RULES_PRESIDENT,l,new PresidentStandards()));
        }
        president_.addEntry(FileConst.RESOURCES_HTML_FILES_RULES_PRESIDENT,rulesPresident_);
        StringMap<PreparedPagesCards> resultsPresident_ = new StringMap<PreparedPagesCards>();
        for (String l: Constants.getAvailableLanguages()) {
            resultsPresident_.addEntry(l,new PreparedPagesCards(FileConst.RESOURCES_HTML_FILES_RESULTS_PRESIDENT,l,new PresidentStandards()));
        }
        president_.addEntry(FileConst.RESOURCES_HTML_FILES_RESULTS_PRESIDENT,resultsPresident_);
        prepared_.addEntry(GameEnum.PRESIDENT,president_);
        StringMap<StringMap<PreparedPagesCards>> tarot_ = new StringMap<StringMap<PreparedPagesCards>>();
        StringMap<PreparedPagesCards> rulesTarot_ = new StringMap<PreparedPagesCards>();
        for (String l: Constants.getAvailableLanguages()) {
            rulesTarot_.addEntry(l,new PreparedPagesCards(FileConst.RESOURCES_HTML_FILES_RULES_TAROT,l,new TarotStandards()));
        }
        tarot_.addEntry(FileConst.RESOURCES_HTML_FILES_RULES_TAROT,rulesTarot_);
        StringMap<PreparedPagesCards> resultsTarot_ = new StringMap<PreparedPagesCards>();
        for (String l: Constants.getAvailableLanguages()) {
            resultsTarot_.addEntry(l,new PreparedPagesCards(FileConst.RESOURCES_HTML_FILES_RESULTS_TAROT,l,new TarotStandards()));
        }
        tarot_.addEntry(FileConst.RESOURCES_HTML_FILES_RESULTS_TAROT,resultsTarot_);
        StringMap<PreparedPagesCards> detResultsTarot_ = new StringMap<PreparedPagesCards>();
        for (String l: Constants.getAvailableLanguages()) {
            detResultsTarot_.addEntry(l,new PreparedPagesCards(FileConst.RESOURCES_HTML_FILES_DETAILS_RESULTS_TAROT,l,new TarotStandards()));
        }
        tarot_.addEntry(FileConst.RESOURCES_HTML_FILES_DETAILS_RESULTS_TAROT,detResultsTarot_);
        prepared_.addEntry(GameEnum.TAROT,tarot_);
        for (EntryCust<GameEnum, StringMap<StringMap<PreparedPagesCards>>> e: prepared_.entryList()) {
            for (EntryCust<String,StringMap<PreparedPagesCards>> f: e.getValue().entryList()) {
                for (EntryCust<String,PreparedPagesCards> g: f.getValue().entryList()) {
                    g.getValue().run();
                }
            }
        }
        return prepared_;
    }
}
