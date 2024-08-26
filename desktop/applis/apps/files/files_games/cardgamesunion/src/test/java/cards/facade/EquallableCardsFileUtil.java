package cards.facade;

import cards.belote.enumerations.BidBelote;
import cards.belote.enumerations.CardBelote;
import cards.belote.enumerations.DealingBelote;
import cards.belote.enumerations.DeclaresBelote;
import cards.consts.Suit;
import cards.facade.enumerations.GameEnum;
import cards.facade.sml.DocumentReaderCardsUnionUtil;
import cards.facade.sml.DocumentWriterCardsUnionUtil;
import cards.president.enumerations.CardPresident;
import cards.tarot.enumerations.BidTarot;
import cards.tarot.enumerations.CardTarot;
import cards.tarot.enumerations.DealingTarot;
import cards.tarot.enumerations.Handfuls;
import code.maths.LgInt;
import code.maths.Rate;
import code.maths.montecarlo.CustomSeedGene;
import code.mock.MockFileSet;
import code.mock.MockProgramInfos;
import code.sml.DocumentBuilder;
import code.sml.util.TranslationsAppli;
import code.sml.util.TranslationsFile;
import code.sml.util.TranslationsLg;
import code.util.core.BoolVal;
import code.util.core.StringUtil;
import org.junit.Assert;

public abstract class EquallableCardsFileUtil {

    public static double[] dbs(double... _args) {
        return _args;
    }
    public static SoftParams save(SoftParams _s) {
        return DocumentReaderCardsUnionUtil.getSoftParams(DocumentWriterCardsUnionUtil.setSoftParams(_s));
    }
    public static Nicknames save(Nicknames _s) {
        return DocumentReaderCardsUnionUtil.getNicknames("","",DocumentWriterCardsUnionUtil.setNicknames(_s));
    }
    public static Games save(Games _s) {
        return DocumentReaderCardsUnionUtil.getGames(DocumentWriterCardsUnionUtil.setGames(_s,"", DocumentBuilder.newXmlDocument()));
    }

    public static MockProgramInfos pr(long _init,long..._incrs) {
        return prTmp("",_init,_incrs);
    }

    public static MockProgramInfos prTmp(String _tmp,long _init,long..._incrs) {
        return MockProgramInfos.inst("", _tmp, new CustomSeedGene(new double[]{0.75}), new MockFileSet(_init, _incrs, new String[]{"/"}));
    }

    public TranslationsLg nicknames() {
        return nicknames(prTmp("/_/",1, 2));
    }

    public TranslationsLg nicknames(MockProgramInfos _pr) {
        TranslationsLg lg_ = _pr.lg("");
        TranslationsAppli app_ = MessagesCardGames.initAppliTr(lg_);
        MessagesCardGames.appendNickNames(app_,new TranslationsFile());
        return lg_;
    }

    public static void update(MockProgramInfos _pr) {
        MessagesCardGames.enTr(MessagesCardGames.initAppliTr(lg(_pr, StringUtil.EN)));
        MessagesCardGames.frTr(MessagesCardGames.initAppliTr(lg(_pr,StringUtil.FR)));
    }
    public static TranslationsLg lg(MockProgramInfos _pr, String _key) {
        return _pr.lg(_key);
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
