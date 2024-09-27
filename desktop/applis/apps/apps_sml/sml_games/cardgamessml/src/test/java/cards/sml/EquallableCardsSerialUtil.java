package cards.sml;

import cards.consts.*;
import cards.belote.*;
import cards.belote.enumerations.*;
import cards.belote.sml.*;
import cards.president.*;
import cards.president.enumerations.*;
import cards.president.sml.*;
import cards.solitaire.AbsDealSolitaire;
import cards.solitaire.CardSolitaire;
import cards.solitaire.SolitaireType;
import cards.solitaire.sml.DocumentReaderSolitaireUtil;
import cards.solitaire.sml.DocumentWriterSolitaireUtil;
import cards.tarot.*;
import cards.tarot.enumerations.*;
import cards.tarot.sml.*;
import code.maths.*;
import code.sml.*;
import code.util.*;
import code.util.core.*;
import org.junit.Assert;

public abstract class EquallableCardsSerialUtil {
    public static CustList<GamePresident> saveResultPresident(CustList<GamePresident> _i) {
        FullDocument f_ = DocumentBuilder.newDocumentBuilder().newDocument();
        return DocumentReaderPresidentUtil.getListGamePresident(DocumentWriterPresidentUtil.setListGamePresident(_i,"_",f_));
    }
    public static TricksHandsPresident saveTricksHandsPresident(TricksHandsPresident _i) {
        FullDocument f_ = DocumentBuilder.newDocumentBuilder().newDocument();
        return DocumentReaderPresidentUtil.getTricksHandsPresident(DocumentWriterPresidentUtil.setTricksHandsPresident(_i,"_",f_));
    }
    public static DisplayingPresident saveDisplayingPresident(DisplayingPresident _i) {
        return DocumentReaderPresidentUtil.getDisplayingPresident(DocumentWriterPresidentUtil.setDisplayingPresident(_i));
    }
    public static RulesPresident saveRulesPresident(RulesPresident _i) {
        return DocumentReaderPresidentUtil.getRulesPresident(DocumentWriterPresidentUtil.setRulesPresident(_i));
    }
    public static HandPresident saveHandPresident(HandPresident _i) {
        return DocumentReaderPresidentUtil.getHandPresident(DocumentWriterPresidentUtil.setHandPresident(_i));
    }
    public static GamePresident saveGamePresident(GamePresident _i) {
        return DocumentReaderPresidentUtil.getGamePresident(DocumentBuilder.parseSax(DocumentWriterPresidentUtil.setGamePresident(_i)));
    }
    public static ResultsPresident saveResultPresident(ResultsPresident _i) {
        return DocumentReaderPresidentUtil.resultsPresident(DocumentBuilder.parseSax(DocumentWriterPresidentUtil.resultsPresident(_i)).getDocumentElement());
    }
    public static CustList<GameBelote> saveResultBelote(CustList<GameBelote> _i) {
        FullDocument f_ = DocumentBuilder.newDocumentBuilder().newDocument();
        return DocumentReaderBeloteUtil.getListGameBelote(DocumentWriterBeloteUtil.setListGameBelote(_i,"_",f_));
    }
    public static TricksHandsBelote saveTricksHandsBelote(TricksHandsBelote _i) {
        FullDocument f_ = DocumentBuilder.newDocumentBuilder().newDocument();
        return DocumentReaderBeloteUtil.getTricksHandsBelote(DocumentWriterBeloteUtil.setTricksHandsBelote(_i,"_",f_));
    }
    public static DisplayingBelote saveDisplayingBelote(DisplayingBelote _i) {
        return DocumentReaderBeloteUtil.getDisplayingBelote(DocumentWriterBeloteUtil.setDisplayingBelote(_i));
    }
    public static RulesBelote saveRulesBelote(RulesBelote _i) {
        return DocumentReaderBeloteUtil.getRulesBelote(DocumentWriterBeloteUtil.setRulesBelote(_i));
    }
    public static HandBelote saveHandBelote(HandBelote _i) {
        return DocumentReaderBeloteUtil.getHandBelote(DocumentWriterBeloteUtil.setHandBelote(_i));
    }
    public static GameBelote saveGameBelote(GameBelote _i) {
        return DocumentReaderBeloteUtil.getGameBelote(DocumentBuilder.parseSax(DocumentWriterBeloteUtil.setGameBelote(_i)));
    }
    public static ResultsBelote saveResultBelote(ResultsBelote _i) {
        return DocumentReaderBeloteUtil.resultsBelote(DocumentBuilder.parseSax(DocumentWriterBeloteUtil.resultsBelote(_i)).getDocumentElement());
    }

    public static CustList<GameTarot> saveResultTarot(CustList<GameTarot> _i) {
        FullDocument f_ = DocumentBuilder.newDocumentBuilder().newDocument();
        return DocumentReaderTarotUtil.getListGameTarot(DocumentWriterTarotUtil.setListGameTarot(_i,"_",f_));
    }
    public static TricksHandsTarot saveTricksHandsTarot(TricksHandsTarot _i) {
        FullDocument f_ = DocumentBuilder.newDocumentBuilder().newDocument();
        return DocumentReaderTarotUtil.getTricksHandsTarot(DocumentWriterTarotUtil.setTricksHandsTarot(_i,"_",f_));
    }
    public static DisplayingTarot saveDisplayingTarot(DisplayingTarot _i) {
        return DocumentReaderTarotUtil.getDisplayingTarot(DocumentWriterTarotUtil.setDisplayingTarot(_i));
    }
    public static RulesTarot saveRulesTarot(RulesTarot _i) {
        return DocumentReaderTarotUtil.getRulesTarot(DocumentWriterTarotUtil.setRulesTarot(_i));
    }
    public static HandTarot saveHandTarot(HandTarot _i) {
        return DocumentReaderTarotUtil.getHandTarot(DocumentWriterTarotUtil.setHandTarot(_i));
    }
    public static GameTarot saveGameTarot(GameTarot _i) {
        return DocumentReaderTarotUtil.getGameTarot(DocumentBuilder.parseSax(DocumentWriterTarotUtil.setGameTarot(_i)));
    }
    public static ResultsTarot saveResultTarot(ResultsTarot _i) {
        return DocumentReaderTarotUtil.resultsTarot(DocumentBuilder.parseSax(DocumentWriterTarotUtil.resultsTarot(_i)).getDocumentElement());
    }
    public static AbsDealSolitaire saveGameSolitaire(AbsDealSolitaire _i) {
        return DocumentReaderSolitaireUtil.getGameSolitaire(DocumentBuilder.parseSax(DocumentWriterSolitaireUtil.setGameSolitaire(_i)));
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
    public static void assertEq(CardSolitaire _expected, CardSolitaire _result) {
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
    public static void assertEq(Rate _expected, Rate _result) {
        Assert.assertEquals(_expected.toNumberString(), _result.toNumberString());
    }
    public static void assertEq(LgInt _expected, LgInt _result) {
        Assert.assertEquals(_expected.toNumberString(), _result.toNumberString());
    }
    public static void assertEq(SolitaireType _expected, SolitaireType _result) {
        Assert.assertSame(_expected, _result);
    }
    public static void assertNull(AbsDealSolitaire _ads) {
        Assert.assertNull(_ads);
    }
}
