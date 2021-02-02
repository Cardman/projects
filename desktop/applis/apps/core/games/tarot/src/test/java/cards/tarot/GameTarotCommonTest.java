package cards.tarot;

import cards.consts.Suit;
import cards.tarot.enumerations.CardTarot;
import code.util.EnumMap;
import org.junit.Test;

public final class GameTarotCommonTest extends EquallableTarotUtil {
    @Test
    public void cartesMaitresses1Test() {
        HandTarot handSuit_ = new HandTarot();
        handSuit_.ajouter(CardTarot.HEART_KING);
        handSuit_.ajouter(CardTarot.HEART_QUEEN);
        handSuit_.ajouter(CardTarot.HEART_KNIGHT);
        handSuit_.ajouter(CardTarot.HEART_1);
        handSuit_.ajouter(CardTarot.SPADE_QUEEN);
        handSuit_.ajouter(CardTarot.SPADE_KNIGHT);
        handSuit_.ajouter(CardTarot.SPADE_JACK);
        handSuit_.ajouter(CardTarot.SPADE_10);
        handSuit_.ajouter(CardTarot.SPADE_9);
        handSuit_.ajouter(CardTarot.SPADE_8);
        handSuit_.ajouter(CardTarot.SPADE_1);
        handSuit_.ajouter(CardTarot.CLUB_KING);
        EnumMap<Suit, HandTarot> out_ = GameTarotCommon.cartesMaitresses(handSuit_.couleurs(), new HandTarot().couleurs());
        assertEq(4, out_.size());
        assertEq(3, out_.getVal(Suit.HEART).total());
        assertTrue(out_.getVal(Suit.HEART).contient(CardTarot.HEART_KING));
        assertTrue(out_.getVal(Suit.HEART).contient(CardTarot.HEART_QUEEN));
        assertTrue(out_.getVal(Suit.HEART).contient(CardTarot.HEART_KNIGHT));
        assertEq(0, out_.getVal(Suit.SPADE).total());
        assertEq(0, out_.getVal(Suit.DIAMOND).total());
        assertEq(1, out_.getVal(Suit.CLUB).total());
        assertTrue(out_.getVal(Suit.CLUB).contient(CardTarot.CLUB_KING));
    }
    @Test
    public void cartesMaitresses2Test() {
        HandTarot handSuit_ = new HandTarot();
        handSuit_.ajouter(CardTarot.HEART_KING);
        handSuit_.ajouter(CardTarot.HEART_QUEEN);
        handSuit_.ajouter(CardTarot.HEART_JACK);
        handSuit_.ajouter(CardTarot.HEART_1);
        handSuit_.ajouter(CardTarot.SPADE_QUEEN);
        handSuit_.ajouter(CardTarot.SPADE_KNIGHT);
        handSuit_.ajouter(CardTarot.SPADE_JACK);
        handSuit_.ajouter(CardTarot.SPADE_10);
        handSuit_.ajouter(CardTarot.SPADE_9);
        handSuit_.ajouter(CardTarot.SPADE_8);
        handSuit_.ajouter(CardTarot.SPADE_1);
        handSuit_.ajouter(CardTarot.CLUB_KING);
        HandTarot played_ = new HandTarot();
        played_.ajouter(CardTarot.HEART_KNIGHT);
        EnumMap<Suit, HandTarot> out_ = GameTarotCommon.cartesMaitresses(handSuit_.couleurs(), played_.couleurs());
        assertEq(4, out_.size());
        assertEq(3, out_.getVal(Suit.HEART).total());
        assertTrue(out_.getVal(Suit.HEART).contient(CardTarot.HEART_KING));
        assertTrue(out_.getVal(Suit.HEART).contient(CardTarot.HEART_QUEEN));
        assertTrue(out_.getVal(Suit.HEART).contient(CardTarot.HEART_JACK));
        assertEq(0, out_.getVal(Suit.SPADE).total());
        assertEq(0, out_.getVal(Suit.DIAMOND).total());
        assertEq(1, out_.getVal(Suit.CLUB).total());
        assertTrue(out_.getVal(Suit.CLUB).contient(CardTarot.CLUB_KING));
    }
    @Test
    public void cartesMaitresses3Test() {
        HandTarot handSuit_ = new HandTarot();
        handSuit_.ajouter(CardTarot.HEART_KING);
        handSuit_.ajouter(CardTarot.HEART_QUEEN);
        handSuit_.ajouter(CardTarot.HEART_JACK);
        handSuit_.ajouter(CardTarot.HEART_7);
        handSuit_.ajouter(CardTarot.SPADE_QUEEN);
        handSuit_.ajouter(CardTarot.SPADE_KNIGHT);
        handSuit_.ajouter(CardTarot.SPADE_JACK);
        handSuit_.ajouter(CardTarot.SPADE_10);
        handSuit_.ajouter(CardTarot.SPADE_9);
        handSuit_.ajouter(CardTarot.SPADE_8);
        handSuit_.ajouter(CardTarot.SPADE_1);
        handSuit_.ajouter(CardTarot.CLUB_KING);
        HandTarot played_ = new HandTarot();
        played_.ajouter(CardTarot.HEART_KNIGHT);
        played_.ajouter(CardTarot.HEART_10);
        played_.ajouter(CardTarot.HEART_9);
        played_.ajouter(CardTarot.HEART_6);
        played_.ajouter(CardTarot.HEART_5);
        played_.ajouter(CardTarot.HEART_4);
        played_.ajouter(CardTarot.HEART_3);
        played_.ajouter(CardTarot.HEART_2);
        played_.ajouter(CardTarot.HEART_1);
        EnumMap<Suit, HandTarot> out_ = GameTarotCommon.cartesMaitresses(handSuit_.couleurs(), played_.couleurs());
        assertEq(4, out_.size());
        assertEq(4, out_.getVal(Suit.HEART).total());
        assertTrue(out_.getVal(Suit.HEART).contient(CardTarot.HEART_KING));
        assertTrue(out_.getVal(Suit.HEART).contient(CardTarot.HEART_QUEEN));
        assertTrue(out_.getVal(Suit.HEART).contient(CardTarot.HEART_JACK));
        assertTrue(out_.getVal(Suit.HEART).contient(CardTarot.HEART_7));
        assertEq(0, out_.getVal(Suit.SPADE).total());
        assertEq(0, out_.getVal(Suit.DIAMOND).total());
        assertEq(1, out_.getVal(Suit.CLUB).total());
        assertTrue(out_.getVal(Suit.CLUB).contient(CardTarot.CLUB_KING));
    }
}
