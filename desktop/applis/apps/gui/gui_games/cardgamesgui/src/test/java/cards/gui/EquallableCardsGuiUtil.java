package cards.gui;

import cards.belote.enumerations.BidBelote;
import cards.belote.enumerations.CardBelote;
import cards.belote.enumerations.DealingBelote;
import cards.belote.enumerations.DeclaresBelote;
import cards.consts.Suit;
import cards.facade.Games;
import cards.facade.enumerations.GameEnum;
import cards.gui.animations.PreparedPagesCards;
import cards.president.enumerations.CardPresident;
import cards.tarot.enumerations.BidTarot;
import cards.tarot.enumerations.CardTarot;
import cards.tarot.enumerations.DealingTarot;
import cards.tarot.enumerations.Handfuls;
import code.gui.*;
import code.maths.LgInt;
import code.maths.Rate;
import code.maths.montecarlo.CustomSeedGene;
import code.mock.MockCustComponent;
import code.mock.MockEventListIncr;
import code.mock.MockFileSet;
import code.mock.MockProgramInfos;
import code.scripts.messages.cards.MessagesDialogBelote;
import code.scripts.messages.cards.MessagesEditorCards;
import code.sml.util.TranslationsAppli;
import code.sml.util.TranslationsFile;
import code.sml.util.TranslationsLg;
import code.util.StringMap;
import code.util.core.BoolVal;
import org.junit.Assert;

public abstract class EquallableCardsGuiUtil {

    protected WindowCards frameRulesBelote() {
        return new WindowCards("en", updateRulesBelote(build()), new StringMap<StringMap<PreparedPagesCards>>(), new StringMap<StringMap<PreparedPagesCards>>(), new StringMap<StringMap<PreparedPagesCards>>());
    }

    protected WindowCards frameEditorBelote() {
        return new WindowCards("en", updateEditorBelote(build()), new StringMap<StringMap<PreparedPagesCards>>(), new StringMap<StringMap<PreparedPagesCards>>(), new StringMap<StringMap<PreparedPagesCards>>());
    }
    public static MockProgramInfos build() {
        return new MockProgramInfos("", "", new MockEventListIncr(new CustomSeedGene(dbs(0.75)), new int[0], new String[0], new TextAnswerValue[]{new TextAnswerValue(GuiConstants.YES_OPTION, "file.txt")}), new MockFileSet(0, new long[1], new String[]{"/"}));
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

    public static double[] dbs(double... _args) {
        return _args;
    }

    public static MockProgramInfos updateRulesBelote(MockProgramInfos _pr) {
        appendRules(Games.initAppliTr(lg(_pr, "en")),MessagesDialogBelote.en());
        appendRules(Games.initAppliTr(lg(_pr, "fr")),MessagesDialogBelote.fr());
        return _pr;
    }

    public static MockProgramInfos updateEditorBelote(MockProgramInfos _pr) {
        appendEditor(appendRules(Games.initAppliTr(lg(_pr, "en")),MessagesDialogBelote.en()), MessagesEditorCards.en());
        appendEditor(appendRules(Games.initAppliTr(lg(_pr, "fr")),MessagesDialogBelote.fr()),MessagesEditorCards.fr());
        return _pr;
    }
    public static TranslationsAppli appendRules(TranslationsAppli _app, TranslationsFile _f) {
        _app.getMapping().addEntry(Games.DIALOG_BELOTE,_f);
        return _app;
    }

    public static TranslationsAppli appendEditor(TranslationsAppli _app, TranslationsFile _f) {
        _app.getMapping().addEntry(Games.EDITOR_CARDS,_f);
        return _app;
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
    public static void assertEq(CardBelote _expected, CardBelote _result) {
        Assert.assertSame(_expected, _result);
    }
    public static void assertEq(CardTarot _expected, CardTarot _result) {
        Assert.assertSame(_expected, _result);
    }
    public static void assertEq(Handfuls _expected, Handfuls _result) {
        Assert.assertSame(_expected, _result);
    }
    public static void assertEq(DeclaresBelote _expected, DeclaresBelote _result) {
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
