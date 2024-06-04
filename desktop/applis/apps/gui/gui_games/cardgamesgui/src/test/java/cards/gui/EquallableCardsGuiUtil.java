package cards.gui;

import cards.belote.*;
import cards.belote.enumerations.*;
import cards.consts.MixCardsChoice;
import cards.consts.Suit;
import cards.facade.*;
import cards.facade.enumerations.GameEnum;
import cards.facade.sml.DocumentWriterCardsUnionUtil;
import cards.gui.containers.ContainerSingleImpl;
import cards.gui.dialogs.FileConst;
import cards.gui.dialogs.help.HelpIndexesTree;
import cards.gui.labels.HandfulLabel;
import cards.main.CardFactories;
import cards.main.CardNatLgNamesNavigation;
import cards.president.*;
import cards.president.enumerations.*;
import cards.tarot.enumerations.*;
import code.bean.nat.NatNavigation;
import code.bean.nat.analyze.NatConfigurationCore;
import code.gui.*;
import code.gui.files.*;
import code.gui.initialize.AbstractProgramInfos;
import code.maths.LgInt;
import code.maths.Rate;
import code.maths.montecarlo.CustomSeedGene;
import code.mock.*;
import code.scripts.confs.HelpScriptConfPages;
import code.scripts.messages.cards.*;
import code.sml.util.TranslationsAppli;
import code.sml.util.TranslationsFile;
import code.sml.util.TranslationsLg;
import code.stream.StreamTextFile;
import cards.tarot.*;
import code.threads.AbstractFutureParam;
import code.threads.AbstractThread;
import code.util.CustList;
import code.util.Ints;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.BoolVal;
import code.util.core.StringUtil;
import org.junit.Assert;

public abstract class EquallableCardsGuiUtil {
    public static final int SAVE_WITHOUT_CLOSING = 0;
    public static final int SAVE_THEN_PLAY = 1;
    public static final int PLAY_WITHOUT_SAVING = 2;
    public static final int SAVE_THEN_CLOSE = 3;
    public static final String FR = "fr";
    public static final String EN = "en";

    protected WindowCards frameRulesBelote() {
        MockProgramInfos pr_ = updateRulesBelote(build());
        return new WindowCards(stream(pr_), pr_);
    }

    protected WindowCards frameEditorBelote() {
        MockProgramInfos pr_ = updateEditorBelote(build());
        return new WindowCards(stream(pr_), pr_);
    }

    protected WindowCards frameSingleBelote(IntGameBelote _m) {
        IntArtCardGames ia_ = new IntArtCardGames();
        ia_.setBelote(_m);
        MockProgramInfos pr_ = updateSingleBelote(build());
        return new WindowCards(streamPseudoBelote(pr_), pr_, ia_);
    }

    protected WindowCards frameSingleBeloteSave(IntGameBelote _m) {
        IntArtCardGames ia_ = new IntArtCardGames();
        ia_.setBelote(_m);
        MockProgramInfos pr_ = updateSingleBeloteSave(build());
        return new WindowCards(streamPseudoBelote(pr_), pr_, ia_);
    }
    protected WindowCards frameSingleBeloteWithEnd(IntGameBelote _m) {
        return frameSingleBeloteWithEnd(_m,0);
    }
    protected WindowCards frameSingleBeloteWithEnd(IntGameBelote _m, int _i) {
        IntArtCardGames ia_ = new IntArtCardGames();
        ia_.setBelote(_m);
        MockProgramInfos pr_ = updateSingleBelote(build());
        CardFactories cf_ = new CardFactories(pr_,new MockBaseExecutorServiceParam<CardNatLgNamesNavigation>(),new MockBaseExecutorServiceParam<StringMap<HelpIndexesTree>>());
        String tempFolderSl_ = defStack(pr_);
        FacadeCards.changerNombreDeParties(GameEnum.BELOTE, _i,tempFolderSl_,pr_,1);
        WindowCards wc_ = new WindowCards(streamPseudoBelote(pr_), pr_, ia_);
        NatNavigation nav_ = nav();
        cf_.submitNav(FileConst.RESOURCES_HTML_FILES_RESULTS_BELOTE,new MockCallable<CardNatLgNamesNavigation>(new CardNatLgNamesNavigation(new BeloteStandardsSample(), nav_)));
        cf_.submitNav(FileConst.RESOURCES_HTML_FILES_DETAILS_RESULTS_BELOTE,new MockCallable<CardNatLgNamesNavigation>(new CardNatLgNamesNavigation(new BeloteStandardsSample(), nav_)));
        wc_.setPrepare(cf_.getTaskNav());
        return wc_;
    }
    protected WindowCards frameSingleBeloteWithEnd24(IntGameBelote _m) {
        return frameSingleBeloteWithEnd24(_m,0);
    }
    protected WindowCards frameSingleBeloteWithEnd24(IntGameBelote _m, int _i) {
        IntArtCardGames ia_ = new IntArtCardGames();
        ia_.setBelote(_m);
        MockProgramInfos pr_ = updateSingleBelote(build());
        CardFactories cf_ = new CardFactories(pr_,new MockBaseExecutorServiceParam<CardNatLgNamesNavigation>(),new MockBaseExecutorServiceParam<StringMap<HelpIndexesTree>>());
        String tempFolderSl_ = defStack(pr_);
        FacadeCards.changerNombreDeParties(GameEnum.BELOTE, _i,tempFolderSl_,pr_,2);
        WindowCards wc_ = new WindowCards(streamPseudoBelote(pr_), pr_, ia_);
        NatNavigation nav_ = nav();
        cf_.submitNav(FileConst.RESOURCES_HTML_FILES_RESULTS_BELOTE,new MockCallable<CardNatLgNamesNavigation>(new CardNatLgNamesNavigation(new BeloteStandardsSample(), nav_)));
        cf_.submitNav(FileConst.RESOURCES_HTML_FILES_DETAILS_RESULTS_BELOTE,new MockCallable<CardNatLgNamesNavigation>(new CardNatLgNamesNavigation(new BeloteStandardsSample(), nav_)));
        wc_.setPrepare(cf_.getTaskNav());
        return wc_;
    }
    protected WindowCards frameSingleBeloteWithEndModif(IntGameBelote _m, int _i) {
        IntArtCardGames ia_ = new IntArtCardGames();
        ia_.setBelote(_m);
        MockProgramInfos pr_ = updateSingleBelote(build());
        CardFactories cf_ = new CardFactories(pr_,new MockBaseExecutorServiceParam<CardNatLgNamesNavigation>(),new MockBaseExecutorServiceParam<StringMap<HelpIndexesTree>>());
        String tempFolderSl_ = defStack(pr_);
        FacadeCards.changerNombreDeParties(GameEnum.BELOTE, _i,tempFolderSl_,pr_,1);
        CardGamesStream str_ = streamPseudoBelote(pr_);
        SoftParams sp_ = new SoftParams();
        sp_.getLancement().add(GameEnum.BELOTE);
        StreamTextFile.saveTextFile(StringUtil.concat(WindowCards.getTempFolderSl(pr_), FacadeCards.PARAMS),DocumentWriterCardsUnionUtil.setSoftParams(sp_),pr_.getStreams());
        WindowCards wc_ = new WindowCards(str_, pr_, ia_);
        NatNavigation nav_ = nav();
        cf_.submitNav(FileConst.RESOURCES_HTML_FILES_RESULTS_BELOTE,new MockCallable<CardNatLgNamesNavigation>(new CardNatLgNamesNavigation(new BeloteStandardsSample(), nav_)));
        cf_.submitNav(FileConst.RESOURCES_HTML_FILES_DETAILS_RESULTS_BELOTE,new MockCallable<CardNatLgNamesNavigation>(new CardNatLgNamesNavigation(new BeloteStandardsSample(), nav_)));
        wc_.setPrepare(cf_.getTaskNav());
        return wc_;
    }
    protected WindowCards frameSimuBeloteWithEnd(IntGameBelote _m) {
        IntArtCardGames ia_ = new IntArtCardGames();
        ia_.setBelote(_m);
        MockProgramInfos pr_ = updateSimuBelote(build());
        CardFactories cf_ = new CardFactories(pr_,new MockBaseExecutorServiceParam<CardNatLgNamesNavigation>(),new MockBaseExecutorServiceParam<StringMap<HelpIndexesTree>>());
        WindowCards wc_ = new WindowCards(streamPseudoBelote(pr_), pr_, ia_);
        NatNavigation nav_ = nav();
        cf_.submitNav(FileConst.RESOURCES_HTML_FILES_RESULTS_BELOTE,new MockCallable<CardNatLgNamesNavigation>(new CardNatLgNamesNavigation(new BeloteStandardsSample(), nav_)));
        cf_.submitNav(FileConst.RESOURCES_HTML_FILES_DETAILS_RESULTS_BELOTE,new MockCallable<CardNatLgNamesNavigation>(new CardNatLgNamesNavigation(new BeloteStandardsSample(), nav_)));
        wc_.setPrepare(cf_.getTaskNav());
        return wc_;
    }
    protected WindowCards frameSinglePresidentWithEnd(IntGamePresident _m) {
        return frameSinglePresidentWithEnd(_m, 0);
    }
    protected WindowCards frameSinglePresidentWithEnd(IntGamePresident _m, int _i) {
        IntArtCardGames ia_ = new IntArtCardGames();
        ia_.setPresident(_m);
        MockProgramInfos pr_ = updateSinglePresident(build());
        CardFactories cf_ = new CardFactories(pr_,new MockBaseExecutorServiceParam<CardNatLgNamesNavigation>(),new MockBaseExecutorServiceParam<StringMap<HelpIndexesTree>>());
        String tempFolderSl_ = defStack(pr_);
        FacadeCards.changerNombreDeParties(GameEnum.PRESIDENT, _i,tempFolderSl_,pr_,1);
        WindowCards wc_ = new WindowCards(streamPseudoPresident(pr_), pr_, ia_);
        NatNavigation nav_ = nav();
        cf_.submitNav(FileConst.RESOURCES_HTML_FILES_RESULTS_PRESIDENT,new MockCallable<CardNatLgNamesNavigation>(new CardNatLgNamesNavigation(new PresidentStandardsSample(), nav_)));
        wc_.setPrepare(cf_.getTaskNav());
        return wc_;
    }

    protected WindowCards frameSimuPresidentWithEnd(IntGamePresident _m) {
        IntArtCardGames ia_ = new IntArtCardGames();
        ia_.setPresident(_m);
        MockProgramInfos pr_ = updateSimuPresident(build());
        CardFactories cf_ = new CardFactories(pr_,new MockBaseExecutorServiceParam<CardNatLgNamesNavigation>(),new MockBaseExecutorServiceParam<StringMap<HelpIndexesTree>>());
        WindowCards wc_ = new WindowCards(streamPseudoPresident(pr_), pr_, ia_);
        NatNavigation nav_ = nav();
        cf_.submitNav(FileConst.RESOURCES_HTML_FILES_RESULTS_PRESIDENT,new MockCallable<CardNatLgNamesNavigation>(new CardNatLgNamesNavigation(new PresidentStandardsSample(), nav_)));
        wc_.setPrepare(cf_.getTaskNav());
        return wc_;
    }
    protected WindowCards frameSingleTarot(IntGameTarot _m) {
        IntArtCardGames ia_ = new IntArtCardGames();
        ia_.setTarot(_m);
        MockProgramInfos pr_ = updateSingleTarot(build());
        return new WindowCards(streamPseudoTarot(pr_), pr_, ia_);
    }

    protected WindowCards frameSingleTarotWithEnd(IntGameTarot _m) {
        return frameSingleTarotWithEnd(_m,0);
    }
    protected WindowCards frameSingleTarotWithEnd(IntGameTarot _m, int _i) {
        IntArtCardGames ia_ = new IntArtCardGames();
        ia_.setTarot(_m);
        MockProgramInfos pr_ = updateSingleTarot(build());
        CardFactories cf_ = new CardFactories(pr_,new MockBaseExecutorServiceParam<CardNatLgNamesNavigation>(),new MockBaseExecutorServiceParam<StringMap<HelpIndexesTree>>());
        String tempFolderSl_ = defStack(pr_);
        FacadeCards.changerNombreDeParties(GameEnum.TAROT, _i,tempFolderSl_,pr_,0);
        WindowCards wc_ = new WindowCards(streamPseudoTarot(pr_), pr_, ia_);
        NatNavigation nav_ = nav();
        cf_.submitNav(FileConst.RESOURCES_HTML_FILES_RESULTS_TAROT,new MockCallable<CardNatLgNamesNavigation>(new CardNatLgNamesNavigation(new TarotStandardsSample(), nav_)));
        cf_.submitNav(FileConst.RESOURCES_HTML_FILES_DETAILS_RESULTS_TAROT,new MockCallable<CardNatLgNamesNavigation>(new CardNatLgNamesNavigation(new TarotStandardsSample(), nav_)));
        wc_.setPrepare(cf_.getTaskNav());
        return wc_;
    }
    protected WindowCards frameSimuTarotWithEnd(IntGameTarot _m) {
        IntArtCardGames ia_ = new IntArtCardGames();
        ia_.setTarot(_m);
        MockProgramInfos pr_ = updateSimuTarot(build());
        CardFactories cf_ = new CardFactories(pr_,new MockBaseExecutorServiceParam<CardNatLgNamesNavigation>(),new MockBaseExecutorServiceParam<StringMap<HelpIndexesTree>>());
        WindowCards wc_ = new WindowCards(streamPseudoTarot(pr_), pr_, ia_);
        NatNavigation nav_ = nav();
        cf_.submitNav(FileConst.RESOURCES_HTML_FILES_RESULTS_TAROT,new MockCallable<CardNatLgNamesNavigation>(new CardNatLgNamesNavigation(new TarotStandardsSample(), nav_)));
        cf_.submitNav(FileConst.RESOURCES_HTML_FILES_DETAILS_RESULTS_TAROT,new MockCallable<CardNatLgNamesNavigation>(new CardNatLgNamesNavigation(new TarotStandardsSample(), nav_)));
        wc_.setPrepare(cf_.getTaskNav());
        return wc_;
    }

    public NatNavigation nav() {
        NatNavigation nav_ = new NatNavigation();
        nav_.setSession(new NatConfigurationCore());
        return nav_;
    }

    private String defStack(MockProgramInfos _pr) {
        String tempFolderSl_ = WindowCards.getTempFolderSl(_pr);
        _pr.getFileCoreStream().newFile(StringUtil.concat("/"+FacadeCards.stack(tempFolderSl_), FacadeCards.DECK_FOLDER, StreamTextFile.SEPARATEUR)).mkdirs();
        StreamTextFile.saveTextFile("/"+FacadeCards.stack(tempFolderSl_),StringUtil.join(FacadeCards.defInfos(), "\n"), _pr.getStreams());
        return tempFolderSl_;
    }

    public WindowCards frameMiniBelote(String _h, String _t) {
        return frameMiniBelote(_h, _t, dbs(0.75));
    }
    public WindowCards frameMiniBelote(String _h, String _t, double[] _dbs) {
        WindowCards wc_ = frameEditorBeloteFiles(_h, _t,_dbs);
        wc_.getFrames().getFileCoreStream().newFile(_h).mkdirs();
        wc_.getFrames().getFileCoreStream().newFile(_t).mkdirs();
        return wc_;
    }

    protected WindowCards frameEditorBeloteFiles(String _h, String _t) {
        return frameEditorBeloteFiles(_h, _t, dbs(0.75));
    }
    protected WindowCards frameEditorBeloteFiles(String _h, String _t, double[] _dbs) {
        MockProgramInfos pr_ = appendFileAppli(updateEditorBelote(build(_h, _t, _dbs)));
        return new WindowCards(streamPseudoBelote(pr_), pr_);
    }

    public static MockProgramInfos updateRulesBelote(MockProgramInfos _pr) {
        appendMix(appendBelote(appendRulesBelote(baseEn(_pr),MessagesDialogBelote.en()),MessagesBelote.en()),MessagesCommonMix.en());
        appendMix(appendBelote(appendRulesBelote(baseFr(_pr),MessagesDialogBelote.fr()),MessagesBelote.fr()),MessagesCommonMix.fr());
        return _pr;
    }

    public static MockProgramInfos updateEditorBelote(MockProgramInfos _pr) {
        appendEditor(appendMix(appendBelote(appendRulesBelote(baseEn(_pr),MessagesDialogBelote.en()),MessagesBelote.en()),MessagesCommonMix.en()), MessagesEditorCards.en());
        appendEditor(appendMix(appendBelote(appendRulesBelote(baseFr(_pr),MessagesDialogBelote.fr()),MessagesBelote.fr()),MessagesCommonMix.en()),MessagesEditorCards.fr());
        miniImgs(_pr);
        return _pr;
    }

    public static MockProgramInfos updateSingleBelote(MockProgramInfos _pr) {
        appendMainGame(appendCards(appendCommon(appendMix(appendGameBelote(appendBelote(baseEn(_pr),MessagesBelote.en()),MessagesBelote.enGame()) ,MessagesCommonMix.en()),MessagesCommonFile.en()),MessagesCommonCards.en()),MessagesGuiCards.enGame());
        appendMainGame(appendCards(appendCommon(appendMix(appendGameBelote(appendBelote(baseFr(_pr),MessagesBelote.fr()),MessagesBelote.frGame()),MessagesCommonMix.en()),MessagesCommonFile.fr()),MessagesCommonCards.fr()),MessagesGuiCards.frGame());
        maxiImgs(_pr);
        return _pr;
    }

    public static MockProgramInfos updateSingleBeloteSave(MockProgramInfos _pr) {
        appendMainGame(appendCards(appendCommon(appendMix(appendGameBelote(appendBelote(baseEn(_pr),MessagesBelote.en()),MessagesBelote.enGame()) ,MessagesCommonMix.en()),MessagesCommonFile.en()),MessagesCommonCards.en()),MessagesGuiCards.enGame());
        appendMainGame(appendCards(appendCommon(appendMix(appendGameBelote(appendBelote(baseFr(_pr),MessagesBelote.fr()),MessagesBelote.frGame()),MessagesCommonMix.en()),MessagesCommonFile.fr()),MessagesCommonCards.fr()),MessagesGuiCards.frGame());
        maxiImgs(_pr);
        _pr.setLanguage(EN);
        updateBase(_pr.currentLg());
        return _pr;
    }

    public static void updateBase(TranslationsLg _en) {
        StringMap<TranslationsFile> en_ = FileFrame.initAppliTr(_en).getMapping();
        en_.addEntry(FileFrame.FILE_DIAL,MessagesFileDialog.en());
        en_.addEntry(FileFrame.CONFIRM,MessagesConfirmDialog.en());
        en_.addEntry(FolderOpenFrame.FOLDER_OPEN_DIAL,MessagesFolderOpenDialog.en());
        en_.addEntry(FileOpenFrame.FILE_OPEN_DIAL,MessagesFileOpenDialog.en());
        en_.addEntry(FileSaveFrame.FILE_SAVE_DIAL,MessagesFileSaveDialog.en());
        en_.addEntry(FileTable.FILE_TAB,MessagesFileTable.en());
    }

    public static MockProgramInfos updateSimuBelote(MockProgramInfos _pr) {
        appendSimuGame(appendMainGame(appendCards(appendCommon(appendMix(appendGameBelote(appendBelote(baseEn(_pr),MessagesBelote.en()),MessagesBelote.enGame()) ,MessagesCommonMix.en()),MessagesCommonFile.en()),MessagesCommonCards.en()),MessagesGuiCards.enGame()),MessagesGuiCards.enSimu());
        appendSimuGame(appendMainGame(appendCards(appendCommon(appendMix(appendGameBelote(appendBelote(baseFr(_pr),MessagesBelote.fr()),MessagesBelote.frGame()),MessagesCommonMix.en()),MessagesCommonFile.fr()),MessagesCommonCards.fr()),MessagesGuiCards.frGame()),MessagesGuiCards.frSimu());
        maxiImgs(_pr);
        return _pr;
    }

    public static TranslationsAppli appendRulesBelote(TranslationsAppli _app, TranslationsFile _f) {
        _app.getMapping().addEntry(Games.DIALOG_BELOTE,_f);
        return _app;
    }

    public static TranslationsAppli appendBelote(TranslationsAppli _app, TranslationsFile _f) {
        _app.getMapping().addEntry(Games.COMMON_BELOTE,_f);
        return _app;
    }

    public static TranslationsAppli appendGameBelote(TranslationsAppli _app, TranslationsFile _f) {
        _app.getMapping().addEntry(Games.GAME_BELOTE,_f);
        return _app;
    }

    protected WindowCards frameRulesPresident() {
        MockProgramInfos pr_ = updateRulesPresident(build());
        return new WindowCards(stream(pr_), pr_);
    }

    protected WindowCards frameEditorPresident() {
        MockProgramInfos pr_ = updateEditorPresident(build());
        return new WindowCards(stream(pr_), pr_);
    }

    protected WindowCards frameSinglePresident(IntGamePresident _m) {
        IntArtCardGames ia_ = new IntArtCardGames();
        ia_.setPresident(_m);
        MockProgramInfos pr_ = updateSinglePresident(build());
        return new WindowCards(streamPseudoPresident(pr_), pr_, ia_);
    }

    public WindowCards frameMiniPresident(String _h, String _t) {
        return frameMiniPresident(_h, _t, dbs(0.75));
    }
    public WindowCards frameMiniPresident(String _h, String _t, double[] _dbs) {
        WindowCards wc_ = frameEditorPresidentFiles(_h, _t,_dbs);
        wc_.getFrames().getFileCoreStream().newFile(_h).mkdirs();
        wc_.getFrames().getFileCoreStream().newFile(_t).mkdirs();
        return wc_;
    }

    protected WindowCards frameEditorPresidentFiles(String _h, String _t) {
        return frameEditorPresidentFiles(_h, _t, dbs(0.75));
    }
    protected WindowCards frameEditorPresidentFiles(String _h, String _t, double[] _dbs) {
        MockProgramInfos pr_ = appendFileAppli(updateEditorPresident(build(_h, _t, _dbs)));
        return new WindowCards(streamPseudoPresident(pr_), pr_);
    }

    public static MockProgramInfos updateRulesPresident(MockProgramInfos _pr) {
        appendMix(appendPresident(appendRulesPresident(baseEn(_pr),MessagesDialogPresident.en()),MessagesPresident.en()),MessagesCommonMix.en());
        appendMix(appendPresident(appendRulesPresident(baseFr(_pr),MessagesDialogPresident.fr()),MessagesPresident.fr()),MessagesCommonMix.en());
        return _pr;
    }

    public static MockProgramInfos updateEditorPresident(MockProgramInfos _pr) {
        appendEditor(appendMix(appendPresident(appendRulesPresident(baseEn(_pr),MessagesDialogPresident.en()),MessagesPresident.en()),MessagesCommonMix.en()), MessagesEditorCards.en());
        appendEditor(appendMix(appendPresident(appendRulesPresident(baseFr(_pr),MessagesDialogPresident.fr()),MessagesPresident.fr()),MessagesCommonMix.en()),MessagesEditorCards.fr());
        miniImgs(_pr);
        return _pr;
    }

    public static MockProgramInfos updateSinglePresident(MockProgramInfos _pr) {
        appendMainGame(appendCards(appendCommon(appendMix(appendGamePresident(appendPresident(baseEn(_pr),MessagesPresident.en()),MessagesPresident.enGame()) ,MessagesCommonMix.en()),MessagesCommonFile.en()),MessagesCommonCards.en()),MessagesGuiCards.enGame());
        appendMainGame(appendCards(appendCommon(appendMix(appendGamePresident(appendPresident(baseFr(_pr),MessagesPresident.fr()),MessagesPresident.frGame()),MessagesCommonMix.en()),MessagesCommonFile.fr()),MessagesCommonCards.fr()),MessagesGuiCards.frGame());
        maxiImgs(_pr);
        return _pr;
    }

    public static MockProgramInfos updateSimuPresident(MockProgramInfos _pr) {
        appendSimuGame(appendMainGame(appendCards(appendCommon(appendMix(appendGamePresident(appendPresident(baseEn(_pr),MessagesPresident.en()),MessagesPresident.enGame()) ,MessagesCommonMix.en()),MessagesCommonFile.en()),MessagesCommonCards.en()),MessagesGuiCards.enGame()),MessagesGuiCards.enSimu());
        appendSimuGame(appendMainGame(appendCards(appendCommon(appendMix(appendGamePresident(appendPresident(baseFr(_pr),MessagesPresident.fr()),MessagesPresident.frGame()),MessagesCommonMix.en()),MessagesCommonFile.fr()),MessagesCommonCards.fr()),MessagesGuiCards.frGame()),MessagesGuiCards.frSimu());
        maxiImgs(_pr);
        return _pr;
    }
    public static TranslationsAppli appendRulesPresident(TranslationsAppli _app, TranslationsFile _f) {
        _app.getMapping().addEntry(Games.DIALOG_PRESIDENT,_f);
        return _app;
    }

    public static TranslationsAppli appendPresident(TranslationsAppli _app, TranslationsFile _f) {
        _app.getMapping().addEntry(Games.COMMON_PRESIDENT,_f);
        return _app;
    }

    public static TranslationsAppli appendGamePresident(TranslationsAppli _app, TranslationsFile _f) {
        _app.getMapping().addEntry(Games.GAME_PRESIDENT,_f);
        return _app;
    }

    protected WindowCards frameRulesTarot() {
        MockProgramInfos pr_ = updateRulesTarot(build());
        return new WindowCards(stream(pr_), pr_);
    }

    protected WindowCards frameEditorTarot() {
        MockProgramInfos pr_ = updateEditorTarot(build());
        return new WindowCards(stream(pr_), pr_);
    }

    public WindowCards frameMiniTarot(String _h, String _t) {
        return frameMiniTarot(_h, _t, dbs(0.75));
    }
    public WindowCards frameMiniTarot(String _h, String _t, double[] _dbs) {
        WindowCards wc_ = frameEditorTarotFiles(_h, _t,_dbs);
        wc_.getFrames().getFileCoreStream().newFile(_h).mkdirs();
        wc_.getFrames().getFileCoreStream().newFile(_t).mkdirs();
        return wc_;
    }

    protected WindowCards frameEditorTarotFiles(String _h, String _t) {
        return frameEditorTarotFiles(_h, _t, dbs(0.75));
    }
    protected WindowCards frameEditorTarotFiles(String _h, String _t, double[] _dbs) {
        MockProgramInfos pr_ = appendFileAppli(updateEditorTarot(build(_h, _t, _dbs)));
        return new WindowCards(streamPseudoTarot(pr_), pr_);
    }
    protected WindowCards frameDialogNicknames(String _h, String _t) {
        MockProgramInfos pr_ = updateDialogNicknames(build(_h, _t, dbs(0.75)));
        return new WindowCards(streamPseudos(pr_), pr_);
    }

    private static CardGamesStream streamPseudos(AbstractProgramInfos _pr) {
        CardGamesStream str_ = stream(_pr);
        Nicknames n_ = new Nicknames();
        nicknamesBelote(n_);
        nicknamesPresident(n_);
        nicknamesTarot(n_);
        str_.getNicknamesCrud().value(n_);
        return str_;
    }

    protected WindowCards frameDialogSoft(String _h, String _t) {
        MockProgramInfos pr_ = updateDialogSoft(build(_h, _t, dbs(0.75)));
        return new WindowCards(stream(pr_), pr_);
    }

    protected WindowCards frameLanguage() {
        MockProgramInfos pr_ = updateDialogSoft(build("/__/", "/_/", dbs(0.75)));
        pr_.setLanguages(new StringList(EN,FR));
        return new WindowCards(stream(pr_), pr_,pr_.getCompoFactory().newMenuItem(),pr_.getCompoFactory().newMenuItem(),new IntArtCardGames());
    }

    protected WindowCards frameDialogDisplay(String _h, String _t) {
        MockProgramInfos pr_ = updateDialogDisplay(build(_h, _t, dbs(0.75)));
        return new WindowCards(stream(pr_), pr_);
    }

    protected static CardGamesStream stream(AbstractProgramInfos _pr) {
        CardGamesStream cs_ = new CardGamesStream(_pr,"_/");
        cs_.setNicknamesCrud(new SampleNicknamesCrud(_pr));
        cs_.setCardGamesCrud(new SampleCardGamesCrud(_pr));
        return cs_;
    }

    private static CardGamesStream streamPseudoBelote(AbstractProgramInfos _pr) {
        CardGamesStream str_ = stream(_pr);
        Nicknames n_ = new Nicknames();
        nicknamesBelote(n_);
        str_.getNicknamesCrud().value(n_);
        return str_;
    }

    private static void nicknamesBelote(Nicknames _b) {
        _b.getPseudosBelote().add("0");
        _b.getPseudosBelote().add("1");
        _b.getPseudosBelote().add("2");
    }

    private static CardGamesStream streamPseudoPresident(AbstractProgramInfos _pr) {
        CardGamesStream str_ = stream(_pr);
        Nicknames n_ = new Nicknames();
        nicknamesPresident(n_);
        str_.getNicknamesCrud().value(n_);
        return str_;
    }

    private static void nicknamesPresident(Nicknames _n) {
        _n.getPseudosPresident().add("0");
        _n.getPseudosPresident().add("1");
        _n.getPseudosPresident().add("2");
        _n.getPseudosPresident().add("3");
        _n.getPseudosPresident().add("4");
        _n.getPseudosPresident().add("5");
        _n.getPseudosPresident().add("6");
        _n.getPseudosPresident().add("7");
        _n.getPseudosPresident().add("8");
        _n.getPseudosPresident().add("9");
        _n.getPseudosPresident().add("10");
        _n.getPseudosPresident().add("11");
        _n.getPseudosPresident().add("12");
        _n.getPseudosPresident().add("13");
        _n.getPseudosPresident().add("14");
        _n.getPseudosPresident().add("15");
        _n.getPseudosPresident().add("16");
        _n.getPseudosPresident().add("17");
        _n.getPseudosPresident().add("18");
        _n.getPseudosPresident().add("19");
        _n.getPseudosPresident().add("20");
        _n.getPseudosPresident().add("21");
        _n.getPseudosPresident().add("22");
        _n.getPseudosPresident().add("23");
        _n.getPseudosPresident().add("24");
        _n.getPseudosPresident().add("25");
        _n.getPseudosPresident().add("26");
        _n.getPseudosPresident().add("27");
        _n.getPseudosPresident().add("28");
        _n.getPseudosPresident().add("29");
        _n.getPseudosPresident().add("30");
    }

    private static CardGamesStream streamPseudoTarot(AbstractProgramInfos _pr) {
        CardGamesStream str_ = stream(_pr);
        Nicknames n_ = new Nicknames();
        nicknamesTarot(n_);
        str_.getNicknamesCrud().value(n_);
        return str_;
    }

    private static void nicknamesTarot(Nicknames _n) {
        _n.getPseudosTarot().add("0");
        _n.getPseudosTarot().add("1");
        _n.getPseudosTarot().add("2");
        _n.getPseudosTarot().add("3");
        _n.getPseudosTarot().add("4");
    }

    protected WindowCards frameDialogGeneHelp(String _h, String _t) {
        MockProgramInfos pr_ = updateDialogGeneHelp(build(_h, _t, dbs(0.75)));
        CardFactories cf_ = new CardFactories(pr_,new MockBaseExecutorServiceParam<CardNatLgNamesNavigation>(),new MockBaseExecutorServiceParam<StringMap<HelpIndexesTree>>());
        cf_.submitHelp(pr_);
        AbstractFutureParam<StringMap<HelpIndexesTree>> helpTask_ = cf_.getHelpTask();
        helpTask_.attendreResultat();
        WindowCards wc_ = new WindowCards(stream(pr_), pr_, cf_.getGeneralHelp(),null,new IntArtCardGames());
        wc_.setHelpInitializerTask(helpTask_);
        return wc_;
    }
    public static MockProgramInfos updateRulesTarot(MockProgramInfos _pr) {
        appendMix(appendTarot(appendRulesTarot(baseEn(_pr),MessagesDialogTarot.en()),MessagesTarot.en()),MessagesCommonMix.en());
        appendMix(appendTarot(appendRulesTarot(baseFr(_pr),MessagesDialogTarot.fr()),MessagesTarot.fr()),MessagesCommonMix.en());
        return _pr;
    }


    public static MockProgramInfos updateEditorTarot(MockProgramInfos _pr) {
        appendEditor(appendMix(appendTarot(appendRulesTarot(baseEn(_pr),MessagesDialogTarot.en()),MessagesTarot.en()),MessagesCommonMix.en()), MessagesEditorCards.en());
        appendEditor(appendMix(appendTarot(appendRulesTarot(baseFr(_pr),MessagesDialogTarot.fr()),MessagesTarot.fr()),MessagesCommonMix.en()),MessagesEditorCards.fr());
        miniImgs(_pr);
        return _pr;
    }

    public static MockProgramInfos updateSingleTarot(MockProgramInfos _pr) {
        appendMainGame(appendCards(appendCommon(appendMix(appendGameTarot(appendTarot(baseEn(_pr),MessagesTarot.en()),MessagesTarot.enGame()) ,MessagesCommonMix.en()),MessagesCommonFile.en()),MessagesCommonCards.en()),MessagesGuiCards.enGame());
        appendMainGame(appendCards(appendCommon(appendMix(appendGameTarot(appendTarot(baseFr(_pr),MessagesTarot.fr()),MessagesTarot.frGame()),MessagesCommonMix.en()),MessagesCommonFile.fr()),MessagesCommonCards.fr()),MessagesGuiCards.frGame());
        maxiImgs(_pr);
        return _pr;
    }
    public static MockProgramInfos updateSimuTarot(MockProgramInfos _pr) {
        appendSimuGame(appendMainGame(appendCards(appendCommon(appendMix(appendGameTarot(appendTarot(baseEn(_pr),MessagesTarot.en()),MessagesTarot.enGame()) ,MessagesCommonMix.en()),MessagesCommonFile.en()),MessagesCommonCards.en()),MessagesGuiCards.enGame()),MessagesGuiCards.enSimu());
        appendSimuGame(appendMainGame(appendCards(appendCommon(appendMix(appendGameTarot(appendTarot(baseFr(_pr),MessagesTarot.fr()),MessagesTarot.frGame()),MessagesCommonMix.en()),MessagesCommonFile.fr()),MessagesCommonCards.fr()),MessagesGuiCards.frGame()),MessagesGuiCards.frSimu());
        maxiImgs(_pr);
        return _pr;
    }
    public void selectEventBelote(ScrollCustomGraphicList<CardBelote> _input, Ints _indices) {
        _input.select(_indices);
        _input.events();
    }
    public void selectEventPresident(ScrollCustomGraphicList<CardPresident> _input, Ints _indices) {
        _input.select(_indices);
        _input.events();
    }
    public void selectEventTarot(ScrollCustomGraphicList<CardTarot> _input, Ints _indices) {
        _input.select(_indices);
        _input.events();
    }
    private static void miniImgs(MockProgramInfos _pr) {
        StringMap<int[][]> mini_ = MiniCardsSampleGene.def();
        _pr.getTranslations().getMapping().getVal(EN).getMaxiCards().addAllEntries(mini_);
        _pr.getTranslations().getMapping().getVal(EN).getMiniCardsDef().addAllEntries(mini_);
        _pr.getTranslations().getMapping().getVal(EN).getMiniCardsSel().addAllEntries(mini_);
        _pr.getTranslations().getMapping().getVal(FR).getMaxiCards().addAllEntries(mini_);
        _pr.getTranslations().getMapping().getVal(FR).getMiniCardsDef().addAllEntries(mini_);
        _pr.getTranslations().getMapping().getVal(FR).getMiniCardsSel().addAllEntries(mini_);
    }
    private static void maxiImgs(MockProgramInfos _pr) {
        StringMap<int[][]> mini_ = MiniCardsSampleGene.def();
        _pr.getTranslations().getMapping().getVal(EN).getMaxiCards().addAllEntries(mini_);
        _pr.getTranslations().getMapping().getVal(EN).getMiniCardsDef().addAllEntries(mini_);
        _pr.getTranslations().getMapping().getVal(FR).getMaxiCards().addAllEntries(mini_);
        _pr.getTranslations().getMapping().getVal(FR).getMiniCardsDef().addAllEntries(mini_);
    }
    public static MockProgramInfos updateDialogNicknames(MockProgramInfos _pr) {
        appendDialogNicknames(baseEn(_pr),MessagesGuiCards.enNickname());
        appendDialogNicknames(baseFr(_pr),MessagesGuiCards.frNickname());
        return _pr;
    }

    public static MockProgramInfos updateDialogSoft(MockProgramInfos _pr) {
        appendDialogSoft(baseEn(_pr),MessagesGuiCards.enSoft());
        appendDialogSoft(baseFr(_pr),MessagesGuiCards.frSoft());
        return _pr;
    }

    public static MockProgramInfos updateDialogDisplay(MockProgramInfos _pr) {
        Games.appendCommonFile(appendDialogDisplay(baseEn(_pr),MessagesGuiCards.enDisplay()),MessagesCommonFile.en());
        Games.appendCommonFile(appendDialogDisplay(baseFr(_pr),MessagesGuiCards.frDisplay()),MessagesCommonFile.fr());
        return _pr;
    }

    public static MockProgramInfos updateDialogGeneHelp(MockProgramInfos _pr) {
        appendDialogHelp(baseEn(_pr),MessagesGuiCards.enHelp());
        appendDialogHelp(baseFr(_pr),MessagesGuiCards.frHelp());
        _pr.getTranslations().getMapping().getVal(EN).setTreeCards(HelpScriptConfPages.info(HelpScriptConfPages.en()));
        _pr.getTranslations().getMapping().getVal(FR).setTreeCards(HelpScriptConfPages.info(HelpScriptConfPages.fr()));
        return _pr;
    }
    private static TranslationsAppli baseFr(MockProgramInfos _pr) {
        return appendMenus(appendGamesNames(appendChTarot(Games.initAppliTr(lg(_pr, FR)),MessagesChoiceTarot.fr()),MessagesGamesGames.fr()),MessagesGuiCards.frMenu());
    }
    private static TranslationsAppli baseEn(MockProgramInfos _pr) {
        return appendMenus(appendGamesNames(appendChTarot(Games.initAppliTr(lg(_pr, EN)),MessagesChoiceTarot.en()),MessagesGamesGames.en()),MessagesGuiCards.enMenu());
    }

    public AbstractThread tryAnimate(ContainerSingleImpl _cont) {
        return tryAn(facto(_cont.getOwner()));
    }

    public MockThreadFactory facto(WindowCardsInt _owner) {
        return (MockThreadFactory) _owner.getFrames().getThreadFactory();
    }

    public CustList<AbstractThread> factory(WindowCardsInt _owner) {
        return facto(_owner).getAllThreads();
    }


    public CustList<AbstractThread> factory(ContainerSingleImpl _owner) {
        return factory(_owner.getOwner());
    }

    public static AbstractThread tryAn(MockThreadFactory _g) {
        assertEq(1, _g.getAllThreads().size());
        AbstractThread th_ = _g.getAllThreads().get(0);
        _g.getAllThreads().remove(0);
        th_.join();
        checkNoAnim(_g);
        return th_;
    }

    public static void checkNoAnim(MockThreadFactory _thFact) {
        assertEq(0, _thFact.getAllThreads().size());
    }


    public static TranslationsAppli appendRulesTarot(TranslationsAppli _app, TranslationsFile _f) {
        _app.getMapping().addEntry(Games.DIALOG_TAROT,_f);
        return _app;
    }

    public static TranslationsAppli appendGameTarot(TranslationsAppli _app, TranslationsFile _f) {
        _app.getMapping().addEntry(Games.GAME_TAROT,_f);
        return _app;
    }

    public static TranslationsAppli appendTarot(TranslationsAppli _app, TranslationsFile _f) {
        _app.getMapping().addEntry(Games.COMMON_TAROT,_f);
        return _app;
    }

    public static TranslationsAppli appendChTarot(TranslationsAppli _app, TranslationsFile _f) {
        _app.getMapping().addEntry(Games.CHOICE_TAROT,_f);
        return _app;
    }

    public static TranslationsAppli appendGamesNames(TranslationsAppli _app, TranslationsFile _f) {
        _app.getMapping().addEntry(Games.GAMES_NAMES,_f);
        return _app;
    }

    public static TranslationsAppli appendMenus(TranslationsAppli _app, TranslationsFile _f) {
        _app.getMapping().addEntry(Games.MENUS,_f);
        return _app;
    }
    public static TranslationsAppli appendEditor(TranslationsAppli _app, TranslationsFile _f) {
        _app.getMapping().addEntry(Games.EDITOR_CARDS,_f);
        return _app;
    }
    public static MockProgramInfos appendFileAppli(MockProgramInfos _pr) {
        StringMap<TranslationsFile> en_ = FileFrame.initAppliTr(_pr.getTranslations().getMapping().getVal(EN)).getMapping();
        en_.addEntry(FileFrame.FILE_DIAL, MessagesFileDialog.en());
        en_.addEntry(FileSaveFrame.FILE_SAVE_DIAL, MessagesFileSaveDialog.en());
        en_.addEntry(FileTable.FILE_TAB, MessagesFileTable.en());
        StringMap<TranslationsFile> fr_ = FileFrame.initAppliTr(_pr.getTranslations().getMapping().getVal(FR)).getMapping();
        fr_.addEntry(FileFrame.FILE_DIAL, MessagesFileDialog.en());
        fr_.addEntry(FileSaveFrame.FILE_SAVE_DIAL, MessagesFileSaveDialog.en());
        fr_.addEntry(FileTable.FILE_TAB, MessagesFileTable.en());
        return _pr;
    }

    public static TranslationsAppli appendMix(TranslationsAppli _app, TranslationsFile _f) {
        _app.getMapping().addEntry(Games.COMMON_MIX,_f);
        return _app;
    }

    public static TranslationsAppli appendCommon(TranslationsAppli _app, TranslationsFile _f) {
        _app.getMapping().addEntry(Games.COMMON_FILE,_f);
        return _app;
    }

    public static TranslationsAppli appendCards(TranslationsAppli _app, TranslationsFile _f) {
        _app.getMapping().addEntry(Games.COMMON_CARDS,_f);
        return _app;
    }

    public static TranslationsAppli appendMainGame(TranslationsAppli _app, TranslationsFile _f) {
        _app.getMapping().addEntry(Games.MAIN_GAME,_f);
        return _app;
    }

    public static TranslationsAppli appendSimuGame(TranslationsAppli _app, TranslationsFile _f) {
        _app.getMapping().addEntry(Games.SIMU_GAME,_f);
        return _app;
    }
    public static TranslationsAppli appendDialogNicknames(TranslationsAppli _app, TranslationsFile _f) {
        _app.getMapping().addEntry(Games.DIALOG_NICKNAME,_f);
        return _app;
    }

    public static TranslationsAppli appendDialogSoft(TranslationsAppli _app, TranslationsFile _f) {
        _app.getMapping().addEntry(Games.DIALOG_SOFT,_f);
        return _app;
    }

    public static TranslationsAppli appendDialogDisplay(TranslationsAppli _app, TranslationsFile _f) {
        _app.getMapping().addEntry(Games.DIALOG_DISPLAY,_f);
        return _app;
    }

    public static TranslationsAppli appendDialogHelp(TranslationsAppli _app, TranslationsFile _f) {
        _app.getMapping().addEntry(Games.DIALOG_HELP,_f);
        return _app;
    }
    public static MockProgramInfos build() {
        return build("", "",dbs(0.75));
    }
    public static MockProgramInfos build(String _h, String _t, double[] _dbs) {
        MockProgramInfos pr_ = MockProgramInfos.inst(_h, _t, new CustomSeedGene(_dbs), new MockFileSet(0, new long[1], new String[]{"/"}));
        pr_.setLanguage(EN);
        return pr_;
    }
    public static void tryClick(AbsButton _m) {
        assertTrue(_m.isVisible());
        assertTrue(_m.isEnabled());
        _m.getActionListeners().get(0).action();
    }

    public static void tryClickHandful(HandfulLabel _m) {
        tryClick(_m.getButton());
    }
    public static void tryClickCard(AbsCustComponent _compo) {
        assertEq(1,_compo.getMouseListenersRel().size());
        _compo.getMouseListenersRel().get(0).mouseReleased(null,null,null);
    }

    public static void tryCheck(AbsCustCheckBox _ch, boolean _v) {
        assertTrue(((MockCustComponent) _ch).isDeepAccessible());
        _ch.setSelected(_v);
    }

    public static void tryToggle(AbsCustCheckBox _ch) {
        assertTrue(((MockCustComponent) _ch).isDeepAccessible());
        ((MockCustCheckBox)_ch).toggle();
    }

    public static double[] dbs(double... _args) {
        return _args;
    }

    public void eventsCombo(ScrollCustomCombo _combo,int _i) {
        _combo.select(_i);
        _combo.events(null);
    }
    public static TranslationsLg lg(MockProgramInfos _pr, String _key) {
        return _pr.lg(_key);
    }
    public static void assertNull(AbsCustComponent _compo) {
        Assert.assertNull(_compo);
    }
    public static void assertNotNull(AbsCustComponent _compo) {
        Assert.assertNotNull(_compo);
    }
    public static void assertTrue(boolean _value) {
        Assert.assertTrue(_value);
    }

    public static void assertFalse(boolean _value) {
        Assert.assertFalse(_value);
    }
    public static void assertEq(long _expected, long _result) {
        Assert.assertEquals(_expected, _result);
    }

    public static void assertEq(String _expected, String _result) {
        Assert.assertEquals(_expected, _result);
    }

    public static void assertEq(DealingTarot _expected, DealingTarot _result) {
        Assert.assertSame(_expected, _result);
    }
    public static void assertEq(DealingBelote _expected, DealingBelote _result) {
        Assert.assertSame(_expected, _result);
    }
    public static void assertEq(Suit _expected, Suit _result) {
        Assert.assertSame(_expected, _result);
    }
    public static void assertEq(CardPresident _expected, CardPresident _result) {
        Assert.assertSame(_expected, _result);
    }
    public static void assertEq(EqualtyPlaying _expected, EqualtyPlaying _result) {
        Assert.assertSame(_expected, _result);
    }
    public static void assertEq(CardBelote _expected, CardBelote _result) {
        Assert.assertSame(_expected, _result);
    }
    public static void assertEq(CardTarot _expected, CardTarot _result) {
        Assert.assertSame(_expected, _result);
    }
    public static void assertEq(Handfuls _expected, Handfuls _result) {
        Assert.assertSame(_expected, _result);
    }
    public static void assertEq(MixCardsChoice _expected, MixCardsChoice _result) {
        Assert.assertSame(_expected, _result);
    }
    public static void assertEq(BeloteTrumpPartner _expected, BeloteTrumpPartner _result) {
        Assert.assertSame(_expected, _result);
    }
    public static void assertEq(DeclaresBelote _expected, DeclaresBelote _result) {
        Assert.assertSame(_expected, _result);
    }
    public static void assertEq(EndDealTarot _expected, EndDealTarot _result) {
        Assert.assertSame(_expected, _result);
    }
    public static void assertEq(ModeTarot _expected, ModeTarot _result) {
        Assert.assertSame(_expected, _result);
    }
    public static void assertEq(BidTarot _expected, BidTarot _result) {
        Assert.assertSame(_expected, _result);
    }
    public static void assertEq(BidBelote _expected, BidBelote _result) {
        Assert.assertSame(_expected, _result);
    }
    public static void assertEq(BoolVal _expected, BoolVal _result) {
        Assert.assertSame(_expected, _result);
    }
    public static void assertEq(GameEnum _expected, GameEnum _result) {
        Assert.assertSame(_expected,_result);
    }
    public static void assertEq(Rate _expected, Rate _result) {
        Assert.assertEquals(_expected.toNumberString(), _result.toNumberString());
    }
    public static void assertEq(LgInt _expected, LgInt _result) {
        Assert.assertEquals(_expected.toNumberString(), _result.toNumberString());
    }
}
