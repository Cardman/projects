package cards.gui;

import cards.belote.enumerations.*;
import cards.consts.MixCardsChoice;
import cards.consts.Suit;
import cards.facade.Games;
import cards.facade.enumerations.GameEnum;
import cards.gui.animations.PreparedPagesCards;
import cards.main.CardFactories;
import cards.president.enumerations.CardPresident;
import cards.president.enumerations.EqualtyPlaying;
import cards.tarot.enumerations.*;
import code.gui.*;
import code.gui.files.*;
import code.maths.LgInt;
import code.maths.Rate;
import code.maths.montecarlo.CustomSeedGene;
import code.mock.*;
import code.scripts.messages.cards.*;
import code.sml.util.TranslationsAppli;
import code.sml.util.TranslationsFile;
import code.sml.util.TranslationsLg;
import code.util.StringMap;
import code.util.core.BoolVal;
import org.junit.Assert;

public abstract class EquallableCardsGuiUtil {
    public static final int SAVE_WITHOUT_CLOSING = 0;
    public static final int SAVE_THEN_PLAY = 1;
    public static final int PLAY_WITHOUT_SAVING = 2;
    public static final int SAVE_THEN_CLOSE = 3;

    protected WindowCards frameRulesBelote() {
        MockProgramInfos pr_ = updateRulesBelote(build());
        return new WindowCards(new SampleNicknamesCrud(pr_),"en", pr_, new StringMap<StringMap<PreparedPagesCards>>(), new StringMap<StringMap<PreparedPagesCards>>(), new StringMap<StringMap<PreparedPagesCards>>());
    }

    protected WindowCards frameEditorBelote() {
        MockProgramInfos pr_ = updateEditorBelote(build());
        return new WindowCards(new SampleNicknamesCrud(pr_),"en", pr_, new StringMap<StringMap<PreparedPagesCards>>(), new StringMap<StringMap<PreparedPagesCards>>(), new StringMap<StringMap<PreparedPagesCards>>());
    }

    public WindowCards frameMiniBelote(String _h, String _t) {
        return frameMiniBelote(_h, _t, dbs(0.75));
    }
    public WindowCards frameMiniBelote(String _h, String _t, double[] _dbs) {
        WindowCards wc_ = frameEditorBeloteFiles(_h, _t,_dbs);
        wc_.getFrames().getFileCoreStream().newFile(_h).mkdirs();
        wc_.getFrames().getFileCoreStream().newFile(_t).mkdirs();
        buildMini(wc_);
        return wc_;
    }

    protected WindowCards frameEditorBeloteFiles(String _h, String _t) {
        return frameEditorBeloteFiles(_h, _t, dbs(0.75));
    }
    protected WindowCards frameEditorBeloteFiles(String _h, String _t, double[] _dbs) {
        MockProgramInfos pr_ = appendFileAppli(updateEditorBelote(build(_h, _t, _dbs)));
        return new WindowCards(new SampleNicknamesCrud(pr_),"en", pr_, new StringMap<StringMap<PreparedPagesCards>>(), new StringMap<StringMap<PreparedPagesCards>>(), new StringMap<StringMap<PreparedPagesCards>>());
    }

    public static MockProgramInfos updateRulesBelote(MockProgramInfos _pr) {
        appendMix(appendBelote(appendRulesBelote(baseEn(_pr),MessagesDialogBelote.en()),MessagesBelote.en()),MessagesCommonMix.en());
        appendMix(appendBelote(appendRulesBelote(baseFr(_pr),MessagesDialogBelote.fr()),MessagesBelote.fr()),MessagesCommonMix.fr());
        return _pr;
    }

    public static MockProgramInfos updateEditorBelote(MockProgramInfos _pr) {
        appendEditor(appendMix(appendBelote(appendRulesBelote(baseEn(_pr),MessagesDialogBelote.en()),MessagesBelote.en()),MessagesCommonMix.en()), MessagesEditorCards.en());
        appendEditor(appendMix(appendBelote(appendRulesBelote(baseFr(_pr),MessagesDialogBelote.fr()),MessagesBelote.fr()),MessagesCommonMix.en()),MessagesEditorCards.fr());
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

    protected WindowCards frameRulesPresident() {
        MockProgramInfos pr_ = updateRulesPresident(build());
        return new WindowCards(new SampleNicknamesCrud(pr_),"en", pr_, new StringMap<StringMap<PreparedPagesCards>>(), new StringMap<StringMap<PreparedPagesCards>>(), new StringMap<StringMap<PreparedPagesCards>>());
    }

    protected WindowCards frameEditorPresident() {
        MockProgramInfos pr_ = updateEditorPresident(build());
        return new WindowCards(new SampleNicknamesCrud(pr_),"en", pr_, new StringMap<StringMap<PreparedPagesCards>>(), new StringMap<StringMap<PreparedPagesCards>>(), new StringMap<StringMap<PreparedPagesCards>>());
    }

    public WindowCards frameMiniPresident(String _h, String _t) {
        return frameMiniPresident(_h, _t, dbs(0.75));
    }
    public WindowCards frameMiniPresident(String _h, String _t, double[] _dbs) {
        WindowCards wc_ = frameEditorPresidentFiles(_h, _t,_dbs);
        wc_.getFrames().getFileCoreStream().newFile(_h).mkdirs();
        wc_.getFrames().getFileCoreStream().newFile(_t).mkdirs();
        buildMini(wc_);
        return wc_;
    }

    protected WindowCards frameEditorPresidentFiles(String _h, String _t) {
        return frameEditorPresidentFiles(_h, _t, dbs(0.75));
    }
    protected WindowCards frameEditorPresidentFiles(String _h, String _t, double[] _dbs) {
        MockProgramInfos pr_ = appendFileAppli(updateEditorPresident(build(_h, _t, _dbs)));
        return new WindowCards(new SampleNicknamesCrud(pr_),"en", pr_, new StringMap<StringMap<PreparedPagesCards>>(), new StringMap<StringMap<PreparedPagesCards>>(), new StringMap<StringMap<PreparedPagesCards>>());
    }

    public static MockProgramInfos updateRulesPresident(MockProgramInfos _pr) {
        appendMix(appendPresident(appendRulesPresident(baseEn(_pr),MessagesDialogPresident.en()),MessagesPresident.en()),MessagesCommonMix.en());
        appendMix(appendPresident(appendRulesPresident(baseFr(_pr),MessagesDialogPresident.fr()),MessagesPresident.fr()),MessagesCommonMix.en());
        return _pr;
    }

    public static MockProgramInfos updateEditorPresident(MockProgramInfos _pr) {
        appendEditor(appendMix(appendPresident(appendRulesPresident(baseEn(_pr),MessagesDialogPresident.en()),MessagesPresident.en()),MessagesCommonMix.en()), MessagesEditorCards.en());
        appendEditor(appendMix(appendPresident(appendRulesPresident(baseFr(_pr),MessagesDialogPresident.fr()),MessagesPresident.fr()),MessagesCommonMix.en()),MessagesEditorCards.fr());
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
    protected WindowCards frameRulesTarot() {
        MockProgramInfos pr_ = updateRulesTarot(build());
        return new WindowCards(new SampleNicknamesCrud(pr_),"en", pr_, new StringMap<StringMap<PreparedPagesCards>>(), new StringMap<StringMap<PreparedPagesCards>>(), new StringMap<StringMap<PreparedPagesCards>>());
    }

    protected WindowCards frameEditorTarot() {
        MockProgramInfos pr_ = updateEditorTarot(build());
        return new WindowCards(new SampleNicknamesCrud(pr_),"en", pr_, new StringMap<StringMap<PreparedPagesCards>>(), new StringMap<StringMap<PreparedPagesCards>>(), new StringMap<StringMap<PreparedPagesCards>>());
    }

    public WindowCards frameMiniTarot(String _h, String _t) {
        return frameMiniTarot(_h, _t, dbs(0.75));
    }
    public WindowCards frameMiniTarot(String _h, String _t, double[] _dbs) {
        WindowCards wc_ = frameEditorTarotFiles(_h, _t,_dbs);
        wc_.getFrames().getFileCoreStream().newFile(_h).mkdirs();
        wc_.getFrames().getFileCoreStream().newFile(_t).mkdirs();
        buildMini(wc_);
        return wc_;
    }

    protected WindowCards frameEditorTarotFiles(String _h, String _t) {
        return frameEditorTarotFiles(_h, _t, dbs(0.75));
    }
    protected WindowCards frameEditorTarotFiles(String _h, String _t, double[] _dbs) {
        MockProgramInfos pr_ = appendFileAppli(updateEditorTarot(build(_h, _t, _dbs)));
        return new WindowCards(new SampleNicknamesCrud(pr_),"en", pr_, new StringMap<StringMap<PreparedPagesCards>>(), new StringMap<StringMap<PreparedPagesCards>>(), new StringMap<StringMap<PreparedPagesCards>>());
    }

    public static MockProgramInfos updateRulesTarot(MockProgramInfos _pr) {
        appendMix(appendTarot(appendRulesTarot(baseEn(_pr),MessagesDialogTarot.en()),MessagesTarot.en()),MessagesCommonMix.en());
        appendMix(appendTarot(appendRulesTarot(baseFr(_pr),MessagesDialogTarot.fr()),MessagesTarot.fr()),MessagesCommonMix.en());
        return _pr;
    }


    public static MockProgramInfos updateEditorTarot(MockProgramInfos _pr) {
        appendEditor(appendMix(appendTarot(appendRulesTarot(baseEn(_pr),MessagesDialogTarot.en()),MessagesTarot.en()),MessagesCommonMix.en()), MessagesEditorCards.en());
        appendEditor(appendMix(appendTarot(appendRulesTarot(baseFr(_pr),MessagesDialogTarot.fr()),MessagesTarot.fr()),MessagesCommonMix.en()),MessagesEditorCards.fr());
        return _pr;
    }

    private static TranslationsAppli baseFr(MockProgramInfos _pr) {
        return appendGamesNames(appendChTarot(Games.initAppliTr(lg(_pr, "fr")),MessagesChoiceTarot.fr()),MessagesGamesGames.fr());
    }
    private static TranslationsAppli baseEn(MockProgramInfos _pr) {
        return appendGamesNames(appendChTarot(Games.initAppliTr(lg(_pr, "en")),MessagesChoiceTarot.en()),MessagesGamesGames.en());
    }


    public static TranslationsAppli appendRulesTarot(TranslationsAppli _app, TranslationsFile _f) {
        _app.getMapping().addEntry(Games.DIALOG_TAROT,_f);
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
    public static TranslationsAppli appendEditor(TranslationsAppli _app, TranslationsFile _f) {
        _app.getMapping().addEntry(Games.EDITOR_CARDS,_f);
        return _app;
    }
    public static MockProgramInfos appendFileAppli(MockProgramInfos _pr) {
        StringMap<TranslationsFile> en_ = FileDialog.initAppliTr(_pr.getTranslations().getMapping().getVal("en")).getMapping();
        en_.addEntry(FileDialog.FILE_DIAL, MessagesFileDialog.en());
        en_.addEntry(FileSaveDialog.FILE_SAVE_DIAL, MessagesFileSaveDialog.en());
        en_.addEntry(FileTable.FILE_TAB, MessagesFileTable.en());
        StringMap<TranslationsFile> fr_ = FileDialog.initAppliTr(_pr.getTranslations().getMapping().getVal("fr")).getMapping();
        fr_.addEntry(FileDialog.FILE_DIAL, MessagesFileDialog.en());
        fr_.addEntry(FileSaveDialog.FILE_SAVE_DIAL, MessagesFileSaveDialog.en());
        fr_.addEntry(FileTable.FILE_TAB, MessagesFileTable.en());
        return _pr;
    }

    public static TranslationsAppli appendMix(TranslationsAppli _app, TranslationsFile _f) {
        _app.getMapping().addEntry(Games.COMMON_MIX,_f);
        return _app;
    }

    public static MockProgramInfos build() {
        return build("", "",dbs(0.75));
    }
    public static MockProgramInfos build(String _h, String _t, double[] _dbs) {
        MockProgramInfos pr_ = new MockProgramInfos(_h, _t, new MockEventListIncr(new CustomSeedGene(_dbs), new int[0], new String[0], new TextAnswerValue[]{new TextAnswerValue(GuiConstants.YES_OPTION, "file.txt")}), new MockFileSet(0, new long[1], new String[]{"/"}));
        pr_.setLanguage("en");
        return pr_;
    }
    public static void tryClick(AbsButton _m) {
        assertTrue(_m.isVisible());
        assertTrue(_m.isEnabled());
        _m.getActionListeners().get(0).action();
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

    public static void buildMini(WindowCards _wc) {
        CardFactories cf_ = new CardFactories(new MockBaseExecutorServiceParam<StringMap<StringMap<int[][]>>>());
        MiniCardsSampleGene mini_ = new MiniCardsSampleGene();
        cf_.submit(mini_,mini_,mini_);
        _wc.setTaskLoading(cf_.getTaskLoad());
        _wc.setTaskLoadingMiniDef(cf_.getTaskLoadMiniDef());
        _wc.setTaskLoadingMiniSel(cf_.getTaskLoadMiniSel());
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
