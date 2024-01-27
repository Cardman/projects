package cards.main;

import cards.belote.beans.*;
import cards.facade.DefNicknamesCrud;
import cards.gui.WindowCards;
import cards.gui.animations.HelpInitializer;
import cards.gui.animations.PreparedPagesCards;
import cards.gui.dialogs.FileConst;
//import cards.gui.interfaces.ResultCardsServerInteractImpl;
import cards.president.beans.*;
import cards.tarot.beans.*;
import code.gui.TopLeftFrame;
import code.gui.files.FileDialog;
import code.gui.initialize.AbstractProgramInfos;
import code.scripts.pages.cards.*;
import code.sml.NavigationCore;
import code.threads.AbstractThread;
import code.util.EntryCust;
import code.util.StringList;
import code.util.StringMap;

/**This class thread is used by EDT (invokeLater of SwingUtilities),
Thread safe class*/
public final class LaunchingGame implements Runnable {

    private final AbstractProgramInfos list;
    private final StringList args;

    private final String language;

    private final TopLeftFrame topLeft;
    private final CardFactories taskLoadImgs;

    /**This class thread is used by EDT (invokeLater of SwingUtilities)*/
    public LaunchingGame(StringList _args, String _language, TopLeftFrame _topLeft, AbstractProgramInfos _list, CardFactories _imgs) {
        args = _args;
        language = _language;
        topLeft = _topLeft;
        list = _list;
        taskLoadImgs = _imgs;
    }

    @Override
    public void run() {
        StringList lgs_ = list.getLanguages();
        StringMap<PreparedPagesCards> belote_ = generateAnalyzedBelote(lgs_);
        StringMap<PreparedPagesCards> president_ = generateAnalyzedPresident(lgs_);
        StringMap<PreparedPagesCards> tarot_ = generateAnalyzedTarot(lgs_);
        WindowCards window_ = new WindowCards(new DefNicknamesCrud(list),language, list, belote_,president_,tarot_);
//        window_.setResultCardsServerInteract(new ResultCardsServerInteractImpl());
        FileDialog.setLocation(window_.getCommonFrame(), topLeft);
        window_.setImageIconFrame(WindowCards.getIcon(window_.getImageFactory()));
        window_.pack();
        window_.setVisible(true);
        HelpInitializer helpInitializerTask_ = new HelpInitializer(window_.getGeneralHelp(),taskLoadImgs.getTaskLoad(), lgs_);
        window_.setTaskLoading(taskLoadImgs.getTaskLoad());
        window_.setTaskLoadingMiniDef(taskLoadImgs.getTaskLoadMiniDef());
        window_.setTaskLoadingMiniSel(taskLoadImgs.getTaskLoadMiniSel());
        AbstractThread helpInitializerThread_ = window_.getThreadFactory().newThread(helpInitializerTask_);
        helpInitializerThread_.start();
        window_.setHelpInitializerTask(helpInitializerTask_);

        if (!args.isEmpty()) {
            window_.loadGameBegin(args.first());
        }
    }

    public static StringMap<PreparedPagesCards> generateAnalyzedBelote(StringList _lgs) {
        StringMap<PreparedPagesCards> belote_ = new StringMap<PreparedPagesCards>();
        StringMap<String> other_ = MessBelotePage.ms();
        NavigationCore.adjust(other_);
        belote_.addEntry(FileConst.RESOURCES_HTML_FILES_RULES_BELOTE,new PreparedPagesCards(new BeloteStandardsRules(), new RulesBeloteLoader(), PagesBelotes.buildRules(), other_, _lgs));
        belote_.addEntry(FileConst.RESOURCES_HTML_FILES_RESULTS_BELOTE,new PreparedPagesCards(new BeloteStandardsResults(), new ResultsBeloteLoader(), PagesBelotes.build(), other_, _lgs));
        belote_.addEntry(FileConst.RESOURCES_HTML_FILES_DETAILS_RESULTS_BELOTE,new PreparedPagesCards(new BeloteStandardsDetailResults(), new DetailsBeloteLoader(), PagesBelotes.buildDetails(), other_, _lgs));
        runAll(belote_);
        return belote_;
    }

    public static StringMap<PreparedPagesCards> generateAnalyzedPresident(StringList _lgs) {
        StringMap<PreparedPagesCards> president_ = new StringMap<PreparedPagesCards>();
        StringMap<String> other_ = MessPresidentPage.ms();
        NavigationCore.adjust(other_);
        president_.addEntry(FileConst.RESOURCES_HTML_FILES_RULES_PRESIDENT,new PreparedPagesCards(new PresidentStandardsRules(), new RulesPresidentLoader(), PagesPresidents.buildRules(), other_, _lgs));
        president_.addEntry(FileConst.RESOURCES_HTML_FILES_RESULTS_PRESIDENT,new PreparedPagesCards(new PresidentStandardsResults(), new ResultsPresidentLoader(), PagesPresidents.build(), other_, _lgs));
        runAll(president_);
        return president_;
    }

    public static StringMap<PreparedPagesCards> generateAnalyzedTarot(StringList _lgs) {
        StringMap<PreparedPagesCards> tarot_ = new StringMap<PreparedPagesCards>();
        StringMap<String> other_ = MessTarotPage.ms();
        NavigationCore.adjust(other_);
        tarot_.addEntry(FileConst.RESOURCES_HTML_FILES_RULES_TAROT,new PreparedPagesCards(new TarotStandardsRules(), new RulesTarotLoader(), PagesTarots.buildRules(), other_, _lgs));
        tarot_.addEntry(FileConst.RESOURCES_HTML_FILES_RESULTS_TAROT,new PreparedPagesCards(new TarotStandardsResults(), new ResultsTarotLoader(), PagesTarots.build(), other_, _lgs));
        tarot_.addEntry(FileConst.RESOURCES_HTML_FILES_DETAILS_RESULTS_TAROT,new PreparedPagesCards(new TarotStandardsDetailResults(), new DetailsTarotLoader(), PagesTarots.buildDetails(), other_, _lgs));
        runAll(tarot_);
        return tarot_;
    }

    private static void runAll(StringMap<PreparedPagesCards> _ls) {
        for (EntryCust<String,PreparedPagesCards> f: _ls.entryList()) {
            f.getValue().run();
        }
    }

}
