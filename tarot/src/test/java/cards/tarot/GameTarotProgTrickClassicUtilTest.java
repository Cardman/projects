package cards.tarot;

import cards.consts.Suit;
import cards.tarot.enumerations.CardTarot;
import code.util.EnumList;
import code.util.EnumMap;
import code.util.EqList;
import code.util.Numbers;
import org.junit.Test;

import static cards.tarot.EquallableTarotUtil.assertEq;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

public class GameTarotProgTrickClassicUtilTest extends CommonGameTarot {
    @Test
    public void getCharacterSeq1Test() {
        HandTarot hand_ = new HandTarot();
        EnumMap<Suit, HandTarot> played_ = new HandTarot().couleurs();
        EqList<HandTarot> chars_ = GameTarotProgTrickClassic.getCharacterSeq(hand_.eclaterEnCours(played_, Suit.HEART));
        assertEq(0, chars_.size());
    }
    @Test
    public void getCharacterSeq2Test() {
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.HEART_7);
        hand_.ajouter(CardTarot.HEART_8);
        hand_.ajouter(CardTarot.HEART_10);
        hand_.ajouter(CardTarot.HEART_JACK);
        hand_.ajouter(CardTarot.HEART_QUEEN);
        hand_.ajouter(CardTarot.HEART_KING);
        EnumMap<Suit, HandTarot> played_ = new HandTarot().couleurs();
        EqList<HandTarot> chars_ = GameTarotProgTrickClassic.getCharacterSeq(hand_.eclaterEnCours(played_, Suit.HEART));
        assertEq(2, chars_.size());
        assertEq(2, chars_.get(0).total());
        assertSame(CardTarot.HEART_KING, chars_.get(0).carte(0));
        assertSame(CardTarot.HEART_QUEEN, chars_.get(0).carte(1));
        assertEq(1, chars_.get(1).total());
        assertSame(CardTarot.HEART_JACK, chars_.get(1).carte(0));
    }
    @Test
    public void getLowCardSeq1Test() {
        HandTarot hand_ = new HandTarot();
        EnumMap<Suit, HandTarot> played_ = new HandTarot().couleurs();
        EqList<HandTarot> chars_ = GameTarotProgTrickClassic.getLowCardSeq(hand_.eclaterEnCours(played_, Suit.HEART));
        assertEq(0, chars_.size());
    }
    @Test
    public void getLowCardSeq2Test() {
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.HEART_7);
        hand_.ajouter(CardTarot.HEART_8);
        hand_.ajouter(CardTarot.HEART_10);
        hand_.ajouter(CardTarot.HEART_JACK);
        hand_.ajouter(CardTarot.HEART_QUEEN);
        hand_.ajouter(CardTarot.HEART_KING);
        EnumMap<Suit, HandTarot> played_ = new HandTarot().couleurs();
        EqList<HandTarot> chars_ = GameTarotProgTrickClassic.getLowCardSeq(hand_.eclaterEnCours(played_, Suit.HEART));
        assertEq(2, chars_.size());
        assertEq(1, chars_.get(0).total());
        assertSame(CardTarot.HEART_10, chars_.get(0).carte(0));
        assertEq(2, chars_.get(1).total());
        assertSame(CardTarot.HEART_8, chars_.get(1).carte(0));
        assertSame(CardTarot.HEART_7, chars_.get(1).carte(1));
    }
    @Test
    public void carteLaPlusPetite1Test() {
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.HEART_7);
        hand_.ajouter(CardTarot.HEART_8);
        hand_.ajouter(CardTarot.HEART_10);
        hand_.ajouter(CardTarot.HEART_JACK);
        hand_.ajouter(CardTarot.HEART_QUEEN);
        hand_.ajouter(CardTarot.HEART_KING);
        EnumMap<Suit, HandTarot> played_ = new HandTarot().couleurs();
        assertSame(CardTarot.HEART_8, GameTarotProgTrickClassic.carteLaPlusPetite(hand_.eclaterEnCours(played_, Suit.HEART)));
    }
    @Test
    public void carteLaPlusPetite2Test() {
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.HEART_JACK);
        hand_.ajouter(CardTarot.HEART_KNIGHT);
        hand_.ajouter(CardTarot.HEART_KING);
        EnumMap<Suit, HandTarot> played_ = new HandTarot().couleurs();
        assertSame(CardTarot.HEART_JACK, GameTarotProgTrickClassic.carteLaPlusPetite(hand_.eclaterEnCours(played_, Suit.HEART)));
    }
    @Test
    public void weakestCard1Test() {
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.HEART_7);
        hand_.ajouter(CardTarot.HEART_8);
        hand_.ajouter(CardTarot.HEART_10);
        hand_.ajouter(CardTarot.HEART_JACK);
        hand_.ajouter(CardTarot.HEART_QUEEN);
        hand_.ajouter(CardTarot.HEART_KING);
        EnumMap<Suit, HandTarot> played_ = new HandTarot().couleurs();
        assertSame(CardTarot.HEART_8, GameTarotProgTrickClassic.weakestCard(hand_.eclaterEnCours(played_, Suit.HEART)));
    }
    @Test
    public void weakestCard2Test() {
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.HEART_JACK);
        hand_.ajouter(CardTarot.HEART_KNIGHT);
        hand_.ajouter(CardTarot.HEART_KING);
        EnumMap<Suit, HandTarot> played_ = new HandTarot().couleurs();
        assertSame(CardTarot.HEART_KNIGHT, GameTarotProgTrickClassic.weakestCard(hand_.eclaterEnCours(played_, Suit.HEART)));
    }
    @Test
    public void jeuFigureHauteDePlusFaibleSuite1Test() {
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.HEART_7);
        hand_.ajouter(CardTarot.HEART_8);
        hand_.ajouter(CardTarot.HEART_10);
        hand_.ajouter(CardTarot.HEART_JACK);
        hand_.ajouter(CardTarot.HEART_KNIGHT);
        hand_.ajouter(CardTarot.HEART_KING);
        EnumMap<Suit, HandTarot> played_ = new HandTarot().couleurs();
        assertSame(CardTarot.HEART_KNIGHT, GameTarotProgTrickClassic.jeuFigureHauteDePlusFaibleSuite(hand_.eclaterEnCours(played_, Suit.HEART)));
    }
    @Test
    public void jeuFigureHauteDePlusFaibleSuite2Test() {
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.HEART_7);
        hand_.ajouter(CardTarot.HEART_8);
        hand_.ajouter(CardTarot.HEART_10);
        EnumMap<Suit, HandTarot> played_ = new HandTarot().couleurs();
        assertSame(CardTarot.HEART_8, GameTarotProgTrickClassic.jeuFigureHauteDePlusFaibleSuite(hand_.eclaterEnCours(played_, Suit.HEART)));
    }
    @Test
    public void atoutLePlusPetit1Test() {
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.TRUMP_7);
        hand_.ajouter(CardTarot.TRUMP_8);
        hand_.ajouter(CardTarot.TRUMP_10);
        EnumMap<Suit, HandTarot> played_ = new HandTarot().couleurs();
        assertSame(CardTarot.TRUMP_8, GameTarotProgTrickClassic.atoutLePlusPetit(hand_.eclaterEnCours(played_, Suit.TRUMP)));
    }
    @Test
    public void atoutLePlusPetit2Test() {
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.TRUMP_1);
        hand_.ajouter(CardTarot.TRUMP_7);
        hand_.ajouter(CardTarot.TRUMP_8);
        hand_.ajouter(CardTarot.TRUMP_10);
        EnumMap<Suit, HandTarot> played_ = new HandTarot().couleurs();
        assertSame(CardTarot.TRUMP_8, GameTarotProgTrickClassic.atoutLePlusPetit(hand_.eclaterEnCours(played_, Suit.TRUMP)));
    }
    @Test
    public void atoutLePlusPetit3Test() {
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.TRUMP_1);
        hand_.ajouter(CardTarot.TRUMP_2);
        hand_.ajouter(CardTarot.TRUMP_3);
        hand_.ajouter(CardTarot.TRUMP_7);
        hand_.ajouter(CardTarot.TRUMP_8);
        hand_.ajouter(CardTarot.TRUMP_10);
        EnumMap<Suit, HandTarot> played_ = new HandTarot().couleurs();
        assertSame(CardTarot.TRUMP_3, GameTarotProgTrickClassic.atoutLePlusPetit(hand_.eclaterEnCours(played_, Suit.TRUMP)));
    }
    @Test
    public void atoutLePlusPetit4Test() {
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.TRUMP_1);
        hand_.ajouter(CardTarot.TRUMP_2);
        hand_.ajouter(CardTarot.TRUMP_3);
        hand_.ajouter(CardTarot.TRUMP_7);
        hand_.ajouter(CardTarot.TRUMP_8);
        hand_.ajouter(CardTarot.TRUMP_10);
        EnumMap<Suit, HandTarot> played_ = new HandTarot().couleurs();
        assertSame(CardTarot.TRUMP_3, GameTarotProgTrickClassic.atoutLePlusPetit(hand_.eclaterEnCours(played_, Suit.TRUMP),false));
    }
    @Test
    public void atoutLePlusPetit5Test() {
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.TRUMP_1);
        hand_.ajouter(CardTarot.TRUMP_2);
        hand_.ajouter(CardTarot.TRUMP_3);
        hand_.ajouter(CardTarot.TRUMP_7);
        hand_.ajouter(CardTarot.TRUMP_8);
        hand_.ajouter(CardTarot.TRUMP_10);
        hand_.ajouter(CardTarot.EXCUSE);
        EnumMap<Suit, HandTarot> played_ = new HandTarot().couleurs();
        assertSame(CardTarot.EXCUSE, GameTarotProgTrickClassic.atoutLePlusPetit(hand_.eclaterEnCours(played_, Suit.TRUMP),true));
    }
    @Test
    public void jouerPetiteCarteDefausse1Test() {
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.HEART_7);
        hand_.ajouter(CardTarot.HEART_8);
        hand_.ajouter(CardTarot.HEART_10);
        hand_.ajouter(CardTarot.HEART_JACK);
        hand_.ajouter(CardTarot.HEART_KNIGHT);
        hand_.ajouter(CardTarot.HEART_KING);
        EnumMap<Suit, HandTarot> played_ = new HandTarot().couleurs();
        EnumList<Suit> s_ = new EnumList<Suit>();
        s_.add(Suit.HEART);
        assertSame(CardTarot.HEART_8,GameTarotProgTrickClassic.jouerPetiteCarteDefausse(hand_.eclaterToutEnCours(played_),s_,hand_,played_));
    }
    @Test
    public void jouerPetiteCarteDefausse2Test() {
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.HEART_7);
        hand_.ajouter(CardTarot.HEART_8);
        hand_.ajouter(CardTarot.HEART_10);
        hand_.ajouter(CardTarot.HEART_JACK);
        hand_.ajouter(CardTarot.HEART_KNIGHT);
        hand_.ajouter(CardTarot.HEART_KING);
        hand_.ajouter(CardTarot.DIAMOND_1);
        EnumMap<Suit, HandTarot> played_ = new HandTarot().couleurs();
        EnumList<Suit> s_ = new EnumList<Suit>();
        s_.add(Suit.HEART);
        s_.add(Suit.DIAMOND);
        assertSame(CardTarot.DIAMOND_1,GameTarotProgTrickClassic.jouerPetiteCarteDefausse(hand_.eclaterToutEnCours(played_),s_,hand_,played_));
    }
    @Test
    public void jeuPetiteDefausseMaitre1Test() {
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.HEART_7);
        hand_.ajouter(CardTarot.HEART_8);
        hand_.ajouter(CardTarot.HEART_10);
        hand_.ajouter(CardTarot.HEART_JACK);
        hand_.ajouter(CardTarot.HEART_KNIGHT);
        hand_.ajouter(CardTarot.HEART_KING);
        EnumMap<Suit, HandTarot> played_ = new HandTarot().couleurs();
        EnumMap<Suit,HandTarot> cartesMaitresses_ = GameTarotCommon.cartesMaitresses(
                hand_.couleurs(), played_);
        EnumList<Suit> s_ = new EnumList<Suit>();
        s_.add(Suit.HEART);
        assertSame(CardTarot.HEART_8,GameTarotProgTrickClassic.jeuPetiteDefausseMaitre(hand_.eclaterToutEnCours(played_),cartesMaitresses_,hand_,s_));
    }
    @Test
    public void jeuPetiteDefausseMaitre2Test() {
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.HEART_7);
        hand_.ajouter(CardTarot.HEART_8);
        hand_.ajouter(CardTarot.HEART_10);
        hand_.ajouter(CardTarot.HEART_JACK);
        hand_.ajouter(CardTarot.HEART_KNIGHT);
        hand_.ajouter(CardTarot.HEART_KING);
        hand_.ajouter(CardTarot.DIAMOND_1);
        EnumMap<Suit, HandTarot> played_ = new HandTarot().couleurs();
        EnumMap<Suit,HandTarot> cartesMaitresses_ = GameTarotCommon.cartesMaitresses(
                hand_.couleurs(), played_);
        EnumList<Suit> s_ = new EnumList<Suit>();
        s_.add(Suit.HEART);
        s_.add(Suit.DIAMOND);
        assertSame(CardTarot.DIAMOND_1,GameTarotProgTrickClassic.jeuPetiteDefausseMaitre(hand_.eclaterToutEnCours(played_),cartesMaitresses_,hand_,s_));
    }
    @Test
    public void sauverFiguresDefausse1Test() {
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.HEART_7);
        hand_.ajouter(CardTarot.HEART_8);
        hand_.ajouter(CardTarot.HEART_10);
        hand_.ajouter(CardTarot.HEART_JACK);
        hand_.ajouter(CardTarot.HEART_KNIGHT);
        hand_.ajouter(CardTarot.HEART_KING);
        hand_.ajouter(CardTarot.DIAMOND_1);
        EnumMap<Suit, HandTarot> played_ = new HandTarot().couleurs();
        EnumList<Suit> s_ = new EnumList<Suit>();
        s_.add(Suit.HEART);
        s_.add(Suit.DIAMOND);
        assertSame(CardTarot.HEART_KING,GameTarotProgTrickClassic.sauverFiguresDefausse(s_,hand_,played_));
    }
    @Test
    public void sauverFiguresDefausse2Test() {
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.HEART_7);
        hand_.ajouter(CardTarot.HEART_8);
        hand_.ajouter(CardTarot.HEART_10);
        hand_.ajouter(CardTarot.DIAMOND_1);
        EnumMap<Suit, HandTarot> played_ = new HandTarot().couleurs();
        EnumList<Suit> s_ = new EnumList<Suit>();
        s_.add(Suit.HEART);
        s_.add(Suit.DIAMOND);
        assertSame(CardTarot.DIAMOND_1,GameTarotProgTrickClassic.sauverFiguresDefausse(s_,hand_,played_));
    }
    @Test
    public void jeuGrandeCarteDefausseMaitre1Test() {
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.HEART_7);
        hand_.ajouter(CardTarot.HEART_8);
        hand_.ajouter(CardTarot.HEART_10);
        hand_.ajouter(CardTarot.HEART_JACK);
        hand_.ajouter(CardTarot.HEART_KNIGHT);
        hand_.ajouter(CardTarot.HEART_KING);
        hand_.ajouter(CardTarot.DIAMOND_1);
        EnumList<Suit> s_ = new EnumList<Suit>();
        s_.add(Suit.HEART);
        s_.add(Suit.DIAMOND);
        assertSame(CardTarot.HEART_KING,GameTarotProgTrickClassic.jeuGrandeCarteDefausseMaitre(s_,hand_));
    }
    @Test
    public void jeuGrandeCarteDefausseMaitre2Test() {
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.HEART_7);
        hand_.ajouter(CardTarot.HEART_8);
        hand_.ajouter(CardTarot.HEART_10);
        hand_.ajouter(CardTarot.DIAMOND_1);
        EnumList<Suit> s_ = new EnumList<Suit>();
        s_.add(Suit.HEART);
        s_.add(Suit.DIAMOND);
        assertSame(CardTarot.HEART_7,GameTarotProgTrickClassic.jeuGrandeCarteDefausseMaitre(s_,hand_));
    }
    @Test
    public void leadingCard1Test() {
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.HEART_7);
        hand_.ajouter(CardTarot.HEART_8);
        hand_.ajouter(CardTarot.HEART_10);
        hand_.ajouter(CardTarot.HEART_JACK);
        hand_.ajouter(CardTarot.HEART_KNIGHT);
        hand_.ajouter(CardTarot.HEART_KING);
        hand_.ajouter(CardTarot.DIAMOND_1);
        EnumMap<Suit, HandTarot> played_ = new HandTarot().couleurs();
        EnumMap<Suit,HandTarot> cartesMaitresses_ = GameTarotCommon.cartesMaitresses(
                hand_.couleurs(), played_);
        EnumList<Suit> s_ = new EnumList<Suit>();
        s_.add(Suit.HEART);
        assertTrue(!GameTarotProgTrickClassic.leadingCard(cartesMaitresses_,s_,hand_.couleurs()));
    }
    @Test
    public void leadingCard2Test() {
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.HEART_7);
        hand_.ajouter(CardTarot.HEART_8);
        hand_.ajouter(CardTarot.HEART_9);
        hand_.ajouter(CardTarot.HEART_10);
        hand_.ajouter(CardTarot.HEART_JACK);
        hand_.ajouter(CardTarot.HEART_KNIGHT);
        hand_.ajouter(CardTarot.HEART_QUEEN);
        hand_.ajouter(CardTarot.HEART_KING);
        hand_.ajouter(CardTarot.DIAMOND_1);
        EnumMap<Suit, HandTarot> played_ = new HandTarot().couleurs();
        EnumMap<Suit,HandTarot> cartesMaitresses_ = GameTarotCommon.cartesMaitresses(
                hand_.couleurs(), played_);
        EnumList<Suit> s_ = new EnumList<Suit>();
        s_.add(Suit.HEART);
        assertTrue(GameTarotProgTrickClassic.leadingCard(cartesMaitresses_,s_,hand_.couleurs()));
    }
    @Test
    public void leadingCard3Test() {
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.HEART_7);
        hand_.ajouter(CardTarot.HEART_8);
        hand_.ajouter(CardTarot.HEART_10);
        hand_.ajouter(CardTarot.HEART_JACK);
        hand_.ajouter(CardTarot.HEART_KNIGHT);
        hand_.ajouter(CardTarot.HEART_QUEEN);
        hand_.ajouter(CardTarot.DIAMOND_KING);
        EnumMap<Suit, HandTarot> played_ = new HandTarot().couleurs();
        EnumMap<Suit,HandTarot> cartesMaitresses_ = GameTarotCommon.cartesMaitresses(
                hand_.couleurs(), played_);
        EnumList<Suit> s_ = new EnumList<Suit>();
        s_.add(Suit.DIAMOND);
        assertTrue(!GameTarotProgTrickClassic.leadingCard(cartesMaitresses_,s_,hand_.couleurs()));
    }
    @Test
    public void leadingCard4Test() {
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.HEART_7);
        hand_.ajouter(CardTarot.HEART_8);
        hand_.ajouter(CardTarot.HEART_10);
        hand_.ajouter(CardTarot.HEART_JACK);
        hand_.ajouter(CardTarot.HEART_KNIGHT);
        hand_.ajouter(CardTarot.HEART_QUEEN);
        hand_.ajouter(CardTarot.DIAMOND_KING);
        EnumMap<Suit, HandTarot> played_ = new HandTarot().couleurs();
        EnumMap<Suit,HandTarot> cartesMaitresses_ = GameTarotCommon.cartesMaitresses(
                hand_.couleurs(), played_);
        EnumList<Suit> s_ = new EnumList<Suit>();
        s_.add(Suit.SPADE);
        assertTrue(GameTarotProgTrickClassic.leadingCard(cartesMaitresses_,s_,hand_.couleurs()));
    }
    @Test
    public void discardCardPartner1Test() {
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.HEART_7);
        hand_.ajouter(CardTarot.HEART_8);
        hand_.ajouter(CardTarot.HEART_9);
        hand_.ajouter(CardTarot.HEART_10);
        hand_.ajouter(CardTarot.HEART_JACK);
        hand_.ajouter(CardTarot.HEART_KNIGHT);
        hand_.ajouter(CardTarot.HEART_QUEEN);
        hand_.ajouter(CardTarot.HEART_KING);
        hand_.ajouter(CardTarot.DIAMOND_1);
        EnumMap<Suit, HandTarot> played_ = new HandTarot().couleurs();
        EnumMap<Suit,HandTarot> cartesMaitresses_ = GameTarotCommon.cartesMaitresses(
                hand_.couleurs(), played_);
        EnumList<Suit> s_ = new EnumList<Suit>();
        s_.add(Suit.HEART);
        assertSame(CardTarot.HEART_KING,GameTarotProgTrickClassic.discardCardPartner(hand_.eclaterToutEnCours(played_),played_,hand_,cartesMaitresses_,s_,true));
    }
    @Test
    public void discardCardPartner2Test() {
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.HEART_7);
        hand_.ajouter(CardTarot.HEART_8);
        hand_.ajouter(CardTarot.HEART_9);
        hand_.ajouter(CardTarot.HEART_10);
        hand_.ajouter(CardTarot.DIAMOND_1);
        HandTarot pl_ = new HandTarot();
        pl_.ajouter(CardTarot.HEART_JACK);
        pl_.ajouter(CardTarot.HEART_KNIGHT);
        pl_.ajouter(CardTarot.HEART_QUEEN);
        pl_.ajouter(CardTarot.HEART_KING);
        EnumMap<Suit, HandTarot> played_ = pl_.couleurs();
        EnumMap<Suit,HandTarot> cartesMaitresses_ = GameTarotCommon.cartesMaitresses(
                hand_.couleurs(), played_);
        EnumList<Suit> s_ = new EnumList<Suit>();
        s_.add(Suit.HEART);
        assertSame(CardTarot.DIAMOND_1,GameTarotProgTrickClassic.discardCardPartner(hand_.eclaterToutEnCours(played_),played_,hand_,cartesMaitresses_,s_,true));
    }
    @Test
    public void discardCardPartner3Test() {
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.HEART_7);
        hand_.ajouter(CardTarot.HEART_8);
        hand_.ajouter(CardTarot.HEART_10);
        hand_.ajouter(CardTarot.HEART_JACK);
        hand_.ajouter(CardTarot.HEART_KNIGHT);
        hand_.ajouter(CardTarot.HEART_KING);
        hand_.ajouter(CardTarot.DIAMOND_1);
        HandTarot pl_ = new HandTarot();
        EnumMap<Suit, HandTarot> played_ = pl_.couleurs();
        EnumMap<Suit,HandTarot> cartesMaitresses_ = GameTarotCommon.cartesMaitresses(
                hand_.couleurs(), played_);
        EnumList<Suit> s_ = new EnumList<Suit>();
        s_.add(Suit.HEART);
        assertSame(CardTarot.HEART_KING,GameTarotProgTrickClassic.discardCardPartner(hand_.eclaterToutEnCours(played_),played_,hand_,cartesMaitresses_,s_,true));
    }
    @Test
    public void discardCardPartner4Test() {
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.HEART_7);
        hand_.ajouter(CardTarot.HEART_8);
        hand_.ajouter(CardTarot.HEART_10);
        hand_.ajouter(CardTarot.DIAMOND_1);
        HandTarot pl_ = new HandTarot();
        EnumMap<Suit, HandTarot> played_ = pl_.couleurs();
        EnumMap<Suit,HandTarot> cartesMaitresses_ = GameTarotCommon.cartesMaitresses(
                hand_.couleurs(), played_);
        EnumList<Suit> s_ = new EnumList<Suit>();
        s_.add(Suit.HEART);
        assertSame(CardTarot.DIAMOND_1,GameTarotProgTrickClassic.discardCardPartner(hand_.eclaterToutEnCours(played_),played_,hand_,cartesMaitresses_,s_,false));
    }
    @Test
    public void discardOptimPartner1Test() {
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.HEART_1);
        hand_.ajouter(CardTarot.HEART_9);
        hand_.ajouter(CardTarot.HEART_10);
        hand_.ajouter(CardTarot.HEART_JACK);
        hand_.ajouter(CardTarot.HEART_KNIGHT);
        hand_.ajouter(CardTarot.HEART_QUEEN);
        hand_.ajouter(CardTarot.HEART_KING);
        hand_.ajouter(CardTarot.DIAMOND_KING);
        EnumMap<Suit, HandTarot> played_ = new HandTarot().couleurs();
        EnumMap<Suit,HandTarot> cartesMaitresses_ = GameTarotCommon.cartesMaitresses(
                hand_.couleurs(), played_);
        assertSame(CardTarot.HEART_1,GameTarotProgTrickClassic.discardOptimPartner(hand_.eclaterToutEnCours(played_),played_,hand_,cartesMaitresses_));
    }
    @Test
    public void discardOptimPartner2Test() {
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.HEART_1);
        hand_.ajouter(CardTarot.HEART_8);
        hand_.ajouter(CardTarot.HEART_9);
        hand_.ajouter(CardTarot.HEART_10);
        hand_.ajouter(CardTarot.HEART_JACK);
        hand_.ajouter(CardTarot.HEART_KNIGHT);
        hand_.ajouter(CardTarot.HEART_QUEEN);
        hand_.ajouter(CardTarot.HEART_KING);
        hand_.ajouter(CardTarot.DIAMOND_KING);
        EnumMap<Suit, HandTarot> played_ = new HandTarot().couleurs();
        EnumMap<Suit,HandTarot> cartesMaitresses_ = GameTarotCommon.cartesMaitresses(
                hand_.couleurs(), played_);
        assertSame(CardTarot.HEART_KING,GameTarotProgTrickClassic.discardOptimPartner(hand_.eclaterToutEnCours(played_),played_,hand_,cartesMaitresses_));
    }
    @Test
    public void discardOptimPartner3Test() {
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.HEART_1);
        hand_.ajouter(CardTarot.HEART_8);
        hand_.ajouter(CardTarot.HEART_9);
        hand_.ajouter(CardTarot.HEART_10);
        hand_.ajouter(CardTarot.HEART_JACK);
        hand_.ajouter(CardTarot.HEART_KNIGHT);
        hand_.ajouter(CardTarot.HEART_QUEEN);
        hand_.ajouter(CardTarot.DIAMOND_KING);
        HandTarot pl_ = new HandTarot();
        pl_.ajouter(CardTarot.HEART_KING);
        EnumMap<Suit, HandTarot> played_ = pl_.couleurs();
        EnumMap<Suit,HandTarot> cartesMaitresses_ = GameTarotCommon.cartesMaitresses(
                hand_.couleurs(), played_);
        assertSame(CardTarot.HEART_1,GameTarotProgTrickClassic.discardOptimPartner(hand_.eclaterToutEnCours(played_),played_,hand_,cartesMaitresses_));
    }
    @Test
    public void discardOptimPartner4Test() {
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.HEART_7);
        hand_.ajouter(CardTarot.HEART_8);
        hand_.ajouter(CardTarot.HEART_9);
        hand_.ajouter(CardTarot.HEART_10);
        hand_.ajouter(CardTarot.DIAMOND_1);
        HandTarot pl_ = new HandTarot();
        pl_.ajouter(CardTarot.HEART_JACK);
        pl_.ajouter(CardTarot.HEART_KNIGHT);
        pl_.ajouter(CardTarot.HEART_QUEEN);
        pl_.ajouter(CardTarot.HEART_KING);
        EnumMap<Suit, HandTarot> played_ = pl_.couleurs();
        EnumMap<Suit,HandTarot> cartesMaitresses_ = GameTarotCommon.cartesMaitresses(
                hand_.couleurs(), played_);
        assertSame(CardTarot.DIAMOND_1,GameTarotProgTrickClassic.discardOptimPartner(hand_.eclaterToutEnCours(played_),played_,hand_,cartesMaitresses_));
    }
    @Test
    public void discardOptimPartner5Test() {
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.HEART_7);
        hand_.ajouter(CardTarot.HEART_8);
        hand_.ajouter(CardTarot.HEART_10);
        hand_.ajouter(CardTarot.HEART_JACK);
        hand_.ajouter(CardTarot.HEART_KNIGHT);
        hand_.ajouter(CardTarot.HEART_KING);
        hand_.ajouter(CardTarot.DIAMOND_1);
        HandTarot pl_ = new HandTarot();
        EnumMap<Suit, HandTarot> played_ = pl_.couleurs();
        EnumMap<Suit,HandTarot> cartesMaitresses_ = GameTarotCommon.cartesMaitresses(
                hand_.couleurs(), played_);
        assertSame(CardTarot.DIAMOND_1,GameTarotProgTrickClassic.discardOptimPartner(hand_.eclaterToutEnCours(played_),played_,hand_,cartesMaitresses_));
    }
    @Test
    public void discardOptimPartner6Test() {
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.HEART_7);
        hand_.ajouter(CardTarot.HEART_8);
        hand_.ajouter(CardTarot.HEART_10);
        hand_.ajouter(CardTarot.DIAMOND_1);
        HandTarot pl_ = new HandTarot();
        EnumMap<Suit, HandTarot> played_ = pl_.couleurs();
        EnumMap<Suit,HandTarot> cartesMaitresses_ = GameTarotCommon.cartesMaitresses(
                hand_.couleurs(), played_);
        assertSame(CardTarot.DIAMOND_1,GameTarotProgTrickClassic.discardOptimPartner(hand_.eclaterToutEnCours(played_),played_,hand_,cartesMaitresses_));
    }
    @Test
    public void discardOptimPartner7Test() {
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.HEART_7);
        hand_.ajouter(CardTarot.HEART_8);
        hand_.ajouter(CardTarot.HEART_9);
        hand_.ajouter(CardTarot.HEART_10);
        hand_.ajouter(CardTarot.DIAMOND_10);
        HandTarot pl_ = new HandTarot();
        pl_.ajouter(CardTarot.HEART_JACK);
        pl_.ajouter(CardTarot.HEART_KNIGHT);
        pl_.ajouter(CardTarot.HEART_QUEEN);
        pl_.ajouter(CardTarot.HEART_KING);
        pl_.ajouter(CardTarot.DIAMOND_JACK);
        pl_.ajouter(CardTarot.DIAMOND_KNIGHT);
        pl_.ajouter(CardTarot.DIAMOND_QUEEN);
        pl_.ajouter(CardTarot.DIAMOND_KING);
        EnumMap<Suit, HandTarot> played_ = pl_.couleurs();
        EnumMap<Suit,HandTarot> cartesMaitresses_ = GameTarotCommon.cartesMaitresses(
                hand_.couleurs(), played_);
        assertSame(CardTarot.HEART_10,GameTarotProgTrickClassic.discardOptimPartner(hand_.eclaterToutEnCours(played_),played_,hand_,cartesMaitresses_));
    }
    @Test
    public void defausseCouleurDemandeeSurPartenaire1Test() {
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.HEART_1);
        hand_.ajouter(CardTarot.HEART_7);
        hand_.ajouter(CardTarot.HEART_8);
        hand_.ajouter(CardTarot.HEART_9);
        hand_.ajouter(CardTarot.HEART_10);
        hand_.ajouter(CardTarot.HEART_JACK);
        hand_.ajouter(CardTarot.HEART_KNIGHT);
        hand_.ajouter(CardTarot.HEART_QUEEN);
        hand_.ajouter(CardTarot.HEART_KING);
        hand_.ajouter(CardTarot.DIAMOND_QUEEN);
        hand_.ajouter(CardTarot.DIAMOND_KING);
        hand_.ajouter(CardTarot.SPADE_KING);
        HandTarot pl_ = new HandTarot();
        pl_.ajouterCartes(HandTarot.couleurComplete(Suit.CLUB));
        pl_.ajouterCartes(HandTarot.couleurComplete(Suit.TRUMP));
        EnumMap<Suit, HandTarot> played_ = pl_.couleurs();
        EnumMap<Suit,HandTarot> cartesMaitresses_ = GameTarotCommon.cartesMaitresses(
                hand_.couleurs(), played_);
        EnumList<Suit> s_ = new EnumList<Suit>();
        s_.add(Suit.HEART);
        s_.add(Suit.DIAMOND);
        s_.add(Suit.SPADE);
        assertSame(CardTarot.HEART_KING,GameTarotProgTrickClassic.defausseCouleurDemandeeSurPartenaire(hand_.eclaterToutEnCours(played_),played_,hand_,cartesMaitresses_,s_,Suit.CLUB));
    }
    @Test
    public void defausseCouleurDemandeeSurPartenaire2Test() {
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.HEART_1);
        hand_.ajouter(CardTarot.HEART_9);
        hand_.ajouter(CardTarot.HEART_10);
        hand_.ajouter(CardTarot.HEART_JACK);
        hand_.ajouter(CardTarot.HEART_KNIGHT);
        hand_.ajouter(CardTarot.HEART_QUEEN);
        hand_.ajouter(CardTarot.HEART_KING);
        hand_.ajouter(CardTarot.DIAMOND_QUEEN);
        hand_.ajouter(CardTarot.DIAMOND_KING);
        hand_.ajouter(CardTarot.SPADE_KING);
        HandTarot pl_ = new HandTarot();
        pl_.ajouterCartes(HandTarot.couleurComplete(Suit.CLUB));
        pl_.ajouterCartes(HandTarot.couleurComplete(Suit.TRUMP));
        EnumMap<Suit, HandTarot> played_ = pl_.couleurs();
        EnumMap<Suit,HandTarot> cartesMaitresses_ = GameTarotCommon.cartesMaitresses(
                hand_.couleurs(), played_);
        EnumList<Suit> s_ = new EnumList<Suit>();
        s_.add(Suit.DIAMOND);
        s_.add(Suit.SPADE);
        assertSame(CardTarot.HEART_1,GameTarotProgTrickClassic.defausseCouleurDemandeeSurPartenaire(hand_.eclaterToutEnCours(played_),played_,hand_,cartesMaitresses_,s_,Suit.CLUB));
    }
    @Test
    public void defausseCouleurDemandeeSurPartenaire3Test() {
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.HEART_1);
        hand_.ajouter(CardTarot.HEART_8);
        hand_.ajouter(CardTarot.HEART_9);
        hand_.ajouter(CardTarot.HEART_10);
        hand_.ajouter(CardTarot.HEART_JACK);
        hand_.ajouter(CardTarot.HEART_KNIGHT);
        hand_.ajouter(CardTarot.HEART_QUEEN);
        hand_.ajouter(CardTarot.HEART_KING);
        hand_.ajouter(CardTarot.DIAMOND_QUEEN);
        hand_.ajouter(CardTarot.DIAMOND_KING);
        hand_.ajouter(CardTarot.SPADE_KING);
        hand_.ajouter(CardTarot.SPADE_QUEEN);
        HandTarot pl_ = new HandTarot();
        pl_.ajouterCartes(HandTarot.couleurComplete(Suit.CLUB));
        pl_.ajouterCartes(HandTarot.couleurComplete(Suit.TRUMP));
        EnumMap<Suit, HandTarot> played_ = pl_.couleurs();
        EnumMap<Suit,HandTarot> cartesMaitresses_ = GameTarotCommon.cartesMaitresses(
                hand_.couleurs(), played_);
        EnumList<Suit> s_ = new EnumList<Suit>();
        s_.add(Suit.DIAMOND);
        s_.add(Suit.SPADE);
        s_.add(Suit.HEART);
        assertSame(CardTarot.HEART_KING,GameTarotProgTrickClassic.defausseCouleurDemandeeSurPartenaire(hand_.eclaterToutEnCours(played_),played_,hand_,cartesMaitresses_,s_,Suit.CLUB));
    }
    @Test
    public void defausseCouleurDemandeeSurPartenaire4Test() {
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.HEART_7);
        hand_.ajouter(CardTarot.HEART_8);
        hand_.ajouter(CardTarot.HEART_9);
        hand_.ajouter(CardTarot.HEART_10);
        hand_.ajouter(CardTarot.DIAMOND_9);
        hand_.ajouter(CardTarot.DIAMOND_10);
        hand_.ajouter(CardTarot.SPADE_10);
        hand_.ajouter(CardTarot.SPADE_9);
        HandTarot pl_ = new HandTarot();
        pl_.ajouterCartes(HandTarot.couleurComplete(Suit.CLUB));
        pl_.ajouterCartes(HandTarot.couleurComplete(Suit.TRUMP));
        pl_.ajouterCartes(HandTarot.couleurComplete(Suit.HEART).figures());
        pl_.ajouterCartes(HandTarot.couleurComplete(Suit.DIAMOND).figures());
        pl_.ajouterCartes(HandTarot.couleurComplete(Suit.SPADE).figures());
        EnumMap<Suit, HandTarot> played_ = pl_.couleurs();
        EnumMap<Suit,HandTarot> cartesMaitresses_ = GameTarotCommon.cartesMaitresses(
                hand_.couleurs(), played_);
        EnumList<Suit> s_ = new EnumList<Suit>();
        s_.add(Suit.DIAMOND);
        s_.add(Suit.SPADE);
        s_.add(Suit.HEART);
        assertSame(CardTarot.HEART_10,GameTarotProgTrickClassic.defausseCouleurDemandeeSurPartenaire(hand_.eclaterToutEnCours(played_),played_,hand_,cartesMaitresses_,s_,Suit.CLUB));
    }
    @Test
    public void defausseCouleurDemandeeSurPartenaire5Test() {
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.HEART_7);
        hand_.ajouter(CardTarot.HEART_8);
        hand_.ajouter(CardTarot.HEART_9);
        hand_.ajouter(CardTarot.HEART_10);
        hand_.ajouter(CardTarot.DIAMOND_9);
        hand_.ajouter(CardTarot.DIAMOND_10);
        hand_.ajouter(CardTarot.SPADE_QUEEN);
        hand_.ajouter(CardTarot.SPADE_9);
        HandTarot pl_ = new HandTarot();
        pl_.ajouterCartes(HandTarot.couleurComplete(Suit.CLUB));
        pl_.ajouterCartes(HandTarot.couleurComplete(Suit.HEART).figures());
        pl_.ajouterCartes(HandTarot.couleurComplete(Suit.DIAMOND).figures());
        EnumMap<Suit, HandTarot> played_ = pl_.couleurs();
        EnumMap<Suit,HandTarot> cartesMaitresses_ = GameTarotCommon.cartesMaitresses(
                hand_.couleurs(), played_);
        EnumList<Suit> s_ = new EnumList<Suit>();
        s_.add(Suit.DIAMOND);
        s_.add(Suit.HEART);
        assertSame(CardTarot.SPADE_QUEEN,GameTarotProgTrickClassic.defausseCouleurDemandeeSurPartenaire(hand_.eclaterToutEnCours(played_),played_,hand_,cartesMaitresses_,s_,Suit.CLUB));
    }
    @Test
    public void defausseCouleurDemandeeSurPartenaire6Test() {
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.HEART_7);
        hand_.ajouter(CardTarot.HEART_8);
        hand_.ajouter(CardTarot.HEART_9);
        hand_.ajouter(CardTarot.HEART_10);
        hand_.ajouter(CardTarot.DIAMOND_9);
        hand_.ajouter(CardTarot.DIAMOND_10);
        hand_.ajouter(CardTarot.SPADE_QUEEN);
        hand_.ajouter(CardTarot.SPADE_9);
        HandTarot pl_ = new HandTarot();
        pl_.ajouterCartes(HandTarot.couleurComplete(Suit.TRUMP));
        pl_.ajouterCartes(HandTarot.couleurComplete(Suit.HEART).figures());
        pl_.ajouterCartes(HandTarot.couleurComplete(Suit.DIAMOND).figures());
        EnumMap<Suit, HandTarot> played_ = pl_.couleurs();
        EnumMap<Suit,HandTarot> cartesMaitresses_ = GameTarotCommon.cartesMaitresses(
                hand_.couleurs(), played_);
        EnumList<Suit> s_ = new EnumList<Suit>();
        s_.add(Suit.DIAMOND);
        s_.add(Suit.HEART);
        assertSame(CardTarot.SPADE_QUEEN,GameTarotProgTrickClassic.defausseCouleurDemandeeSurPartenaire(hand_.eclaterToutEnCours(played_),played_,hand_,cartesMaitresses_,s_,Suit.CLUB));
    }
    @Test
    public void defausseAtoutSurPartenaire1Test() {
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.HEART_1);
        hand_.ajouter(CardTarot.HEART_8);
        hand_.ajouter(CardTarot.HEART_9);
        hand_.ajouter(CardTarot.HEART_10);
        hand_.ajouter(CardTarot.HEART_JACK);
        hand_.ajouter(CardTarot.HEART_KNIGHT);
        hand_.ajouter(CardTarot.HEART_QUEEN);
        hand_.ajouter(CardTarot.HEART_KING);
        hand_.ajouter(CardTarot.DIAMOND_KNIGHT);
        hand_.ajouter(CardTarot.DIAMOND_QUEEN);
        hand_.ajouter(CardTarot.DIAMOND_KING);
        hand_.ajouter(CardTarot.SPADE_QUEEN);
        hand_.ajouter(CardTarot.SPADE_KING);
        hand_.ajouter(CardTarot.CLUB_KING);
        HandTarot pl_ = new HandTarot();
        pl_.ajouterCartes(HandTarot.couleurComplete(Suit.TRUMP));
        EnumMap<Suit, HandTarot> played_ = pl_.couleurs();
        EnumMap<Suit,HandTarot> cartesMaitresses_ = GameTarotCommon.cartesMaitresses(
                hand_.couleurs(), played_);
        EnumList<Suit> s_ = new EnumList<Suit>();
        s_.add(Suit.HEART);
        s_.add(Suit.DIAMOND);
        s_.add(Suit.SPADE);
        s_.add(Suit.CLUB);
        assertSame(CardTarot.HEART_KING,GameTarotProgTrickClassic.defausseAtoutSurPartenaire(hand_.eclaterToutEnCours(played_),played_,hand_,cartesMaitresses_,s_));
    }
    @Test
    public void defausseAtoutSurPartenaire2Test() {
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.HEART_1);
        hand_.ajouter(CardTarot.HEART_8);
        hand_.ajouter(CardTarot.HEART_9);
        hand_.ajouter(CardTarot.HEART_10);
        hand_.ajouter(CardTarot.HEART_JACK);
        hand_.ajouter(CardTarot.HEART_KNIGHT);
        hand_.ajouter(CardTarot.HEART_QUEEN);
        hand_.ajouter(CardTarot.DIAMOND_KNIGHT);
        hand_.ajouter(CardTarot.DIAMOND_QUEEN);
        hand_.ajouter(CardTarot.DIAMOND_KING);
        hand_.ajouter(CardTarot.SPADE_QUEEN);
        hand_.ajouter(CardTarot.SPADE_KING);
        hand_.ajouter(CardTarot.CLUB_KING);
        HandTarot pl_ = new HandTarot();
        pl_.ajouterCartes(HandTarot.couleurComplete(Suit.TRUMP));
        pl_.ajouter(CardTarot.HEART_KING);
        EnumMap<Suit, HandTarot> played_ = pl_.couleurs();
        EnumMap<Suit,HandTarot> cartesMaitresses_ = GameTarotCommon.cartesMaitresses(
                hand_.couleurs(), played_);
        EnumList<Suit> s_ = new EnumList<Suit>();
        s_.add(Suit.HEART);
        s_.add(Suit.DIAMOND);
        s_.add(Suit.SPADE);
        s_.add(Suit.CLUB);
        assertSame(CardTarot.HEART_1,GameTarotProgTrickClassic.defausseAtoutSurPartenaire(hand_.eclaterToutEnCours(played_),played_,hand_,cartesMaitresses_,s_));
    }
    @Test
    public void defausseAtoutSurPartenaire3Test() {
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.HEART_1);
        hand_.ajouter(CardTarot.HEART_8);
        hand_.ajouter(CardTarot.HEART_9);
        hand_.ajouter(CardTarot.HEART_10);
        hand_.ajouter(CardTarot.HEART_JACK);
        hand_.ajouter(CardTarot.HEART_KNIGHT);
        hand_.ajouter(CardTarot.HEART_QUEEN);
        hand_.ajouter(CardTarot.HEART_KING);
        hand_.ajouter(CardTarot.DIAMOND_KNIGHT);
        hand_.ajouter(CardTarot.DIAMOND_QUEEN);
        hand_.ajouter(CardTarot.DIAMOND_KING);
        hand_.ajouter(CardTarot.SPADE_QUEEN);
        hand_.ajouter(CardTarot.SPADE_KING);
        hand_.ajouter(CardTarot.CLUB_QUEEN);
        hand_.ajouter(CardTarot.CLUB_KING);
        HandTarot pl_ = new HandTarot();
        pl_.ajouterCartes(HandTarot.couleurComplete(Suit.TRUMP));
        EnumMap<Suit, HandTarot> played_ = pl_.couleurs();
        EnumMap<Suit,HandTarot> cartesMaitresses_ = GameTarotCommon.cartesMaitresses(
                hand_.couleurs(), played_);
        EnumList<Suit> s_ = new EnumList<Suit>();
        s_.add(Suit.HEART);
        s_.add(Suit.DIAMOND);
        s_.add(Suit.SPADE);
        s_.add(Suit.CLUB);
        assertSame(CardTarot.HEART_KING,GameTarotProgTrickClassic.defausseAtoutSurPartenaire(hand_.eclaterToutEnCours(played_),played_,hand_,cartesMaitresses_,s_));
    }
    @Test
    public void defausseAtoutSurPartenaire4Test() {
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.HEART_1);
        hand_.ajouter(CardTarot.HEART_8);
        hand_.ajouter(CardTarot.HEART_9);
        hand_.ajouter(CardTarot.HEART_10);
        hand_.ajouter(CardTarot.HEART_JACK);
        hand_.ajouter(CardTarot.HEART_KNIGHT);
        hand_.ajouter(CardTarot.HEART_QUEEN);
        hand_.ajouter(CardTarot.HEART_KING);
        hand_.ajouter(CardTarot.DIAMOND_KNIGHT);
        hand_.ajouter(CardTarot.DIAMOND_QUEEN);
        hand_.ajouter(CardTarot.DIAMOND_KING);
        hand_.ajouter(CardTarot.SPADE_QUEEN);
        hand_.ajouter(CardTarot.SPADE_KING);
        hand_.ajouter(CardTarot.CLUB_QUEEN);
        HandTarot pl_ = new HandTarot();
        pl_.ajouterCartes(HandTarot.couleurComplete(Suit.TRUMP));
        EnumMap<Suit, HandTarot> played_ = pl_.couleurs();
        EnumMap<Suit,HandTarot> cartesMaitresses_ = GameTarotCommon.cartesMaitresses(
                hand_.couleurs(), played_);
        EnumList<Suit> s_ = new EnumList<Suit>();
        s_.add(Suit.HEART);
        s_.add(Suit.DIAMOND);
        s_.add(Suit.SPADE);
        assertSame(CardTarot.CLUB_QUEEN,GameTarotProgTrickClassic.defausseAtoutSurPartenaire(hand_.eclaterToutEnCours(played_),played_,hand_,cartesMaitresses_,s_));
    }
    @Test
    public void defausseAtoutSurPartenaire5Test() {
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.HEART_1);
        hand_.ajouter(CardTarot.HEART_8);
        hand_.ajouter(CardTarot.HEART_9);
        hand_.ajouter(CardTarot.HEART_10);
        hand_.ajouter(CardTarot.DIAMOND_8);
        hand_.ajouter(CardTarot.DIAMOND_9);
        hand_.ajouter(CardTarot.DIAMOND_10);
        hand_.ajouter(CardTarot.SPADE_9);
        hand_.ajouter(CardTarot.SPADE_10);
        HandTarot pl_ = new HandTarot();
        pl_.ajouterCartes(HandTarot.couleurComplete(Suit.TRUMP));
        pl_.ajouterCartes(HandTarot.couleurComplete(Suit.CLUB));
        pl_.ajouterCartes(HandTarot.couleurComplete(Suit.HEART).figures());
        pl_.ajouterCartes(HandTarot.couleurComplete(Suit.SPADE).figures());
        pl_.ajouterCartes(HandTarot.couleurComplete(Suit.DIAMOND).figures());
        EnumMap<Suit, HandTarot> played_ = pl_.couleurs();
        EnumMap<Suit,HandTarot> cartesMaitresses_ = GameTarotCommon.cartesMaitresses(
                hand_.couleurs(), played_);
        EnumList<Suit> s_ = new EnumList<Suit>();
        s_.add(Suit.HEART);
        s_.add(Suit.DIAMOND);
        s_.add(Suit.SPADE);
        s_.add(Suit.CLUB);
        assertSame(CardTarot.HEART_1,GameTarotProgTrickClassic.defausseAtoutSurPartenaire(hand_.eclaterToutEnCours(played_),played_,hand_,cartesMaitresses_,s_));
    }
    @Test
    public void defausseAtoutSurPartenaire6Test() {
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.HEART_8);
        hand_.ajouter(CardTarot.HEART_9);
        hand_.ajouter(CardTarot.HEART_10);
        hand_.ajouter(CardTarot.DIAMOND_8);
        hand_.ajouter(CardTarot.DIAMOND_9);
        hand_.ajouter(CardTarot.DIAMOND_10);
        hand_.ajouter(CardTarot.SPADE_9);
        hand_.ajouter(CardTarot.SPADE_10);
        HandTarot pl_ = new HandTarot();
        pl_.ajouterCartes(HandTarot.couleurComplete(Suit.TRUMP));
        pl_.ajouterCartes(HandTarot.couleurComplete(Suit.CLUB));
        pl_.ajouterCartes(HandTarot.couleurComplete(Suit.HEART).figures());
        pl_.ajouterCartes(HandTarot.couleurComplete(Suit.SPADE).figures());
        pl_.ajouterCartes(HandTarot.couleurComplete(Suit.DIAMOND).figures());
        EnumMap<Suit, HandTarot> played_ = pl_.couleurs();
        EnumMap<Suit,HandTarot> cartesMaitresses_ = GameTarotCommon.cartesMaitresses(
                hand_.couleurs(), played_);
        EnumList<Suit> s_ = new EnumList<Suit>();
        s_.add(Suit.HEART);
        s_.add(Suit.DIAMOND);
        s_.add(Suit.SPADE);
        s_.add(Suit.CLUB);
        assertSame(CardTarot.HEART_10,GameTarotProgTrickClassic.defausseAtoutSurPartenaire(hand_.eclaterToutEnCours(played_),played_,hand_,cartesMaitresses_,s_));
    }
    @Test
    public void defausseAtoutSurPartenaire7Test() {
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.HEART_8);
        hand_.ajouter(CardTarot.HEART_9);
        hand_.ajouter(CardTarot.HEART_10);
        hand_.ajouter(CardTarot.DIAMOND_8);
        hand_.ajouter(CardTarot.DIAMOND_9);
        hand_.ajouter(CardTarot.DIAMOND_10);
        hand_.ajouter(CardTarot.SPADE_QUEEN);
        hand_.ajouter(CardTarot.SPADE_9);
        HandTarot pl_ = new HandTarot();
        pl_.ajouterCartes(HandTarot.couleurComplete(Suit.CLUB));
        pl_.ajouterCartes(HandTarot.couleurComplete(Suit.HEART).figures());
        pl_.ajouterCartes(HandTarot.couleurComplete(Suit.DIAMOND).figures());
        EnumMap<Suit, HandTarot> played_ = pl_.couleurs();
        EnumMap<Suit,HandTarot> cartesMaitresses_ = GameTarotCommon.cartesMaitresses(
                hand_.couleurs(), played_);
        EnumList<Suit> s_ = new EnumList<Suit>();
        s_.add(Suit.HEART);
        s_.add(Suit.DIAMOND);
        s_.add(Suit.CLUB);
        assertSame(CardTarot.SPADE_QUEEN,GameTarotProgTrickClassic.defausseAtoutSurPartenaire(hand_.eclaterToutEnCours(played_),played_,hand_,cartesMaitresses_,s_));
    }
    @Test
    public void discardFoe1Test() {
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.HEART_8);
        hand_.ajouter(CardTarot.HEART_9);
        hand_.ajouter(CardTarot.HEART_10);
        hand_.ajouter(CardTarot.DIAMOND_9);
        hand_.ajouter(CardTarot.DIAMOND_10);
        hand_.ajouter(CardTarot.SPADE_QUEEN);
        hand_.ajouter(CardTarot.SPADE_9);
        HandTarot pl_ = new HandTarot();
        pl_.ajouterCartes(HandTarot.couleurComplete(Suit.CLUB));
        EnumMap<Suit, HandTarot> played_ = pl_.couleurs();
        assertSame(CardTarot.DIAMOND_10,GameTarotProgTrickClassic.discardFoe(hand_.eclaterToutEnCours(played_),played_,hand_));
    }
    @Test
    public void discardFoe2Test() {
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.HEART_8);
        hand_.ajouter(CardTarot.HEART_9);
        hand_.ajouter(CardTarot.HEART_10);
        hand_.ajouter(CardTarot.HEART_KNIGHT);
        hand_.ajouter(CardTarot.DIAMOND_9);
        hand_.ajouter(CardTarot.DIAMOND_KING);
        hand_.ajouter(CardTarot.SPADE_QUEEN);
        hand_.ajouter(CardTarot.SPADE_9);
        HandTarot pl_ = new HandTarot();
        pl_.ajouterCartes(HandTarot.couleurComplete(Suit.CLUB));
        EnumMap<Suit, HandTarot> played_ = pl_.couleurs();
        assertSame(CardTarot.HEART_10,GameTarotProgTrickClassic.discardFoe(hand_.eclaterToutEnCours(played_),played_,hand_));
    }
    @Test
    public void sauveQuiPeutFigure1Test() {
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.HEART_8);
        hand_.ajouter(CardTarot.HEART_9);
        hand_.ajouter(CardTarot.HEART_10);
        hand_.ajouter(CardTarot.HEART_KNIGHT);
        hand_.ajouter(CardTarot.HEART_KING);
        HandTarot pl_ = new HandTarot();
        EnumMap<Suit, HandTarot> played_ = pl_.couleurs();
        EnumMap<Suit, EqList<HandTarot>> possible_ = generate(5);
        EnumMap<Suit, EqList<HandTarot>> sure_ = generate(5);
        EqList<HandTarot> suites_ = hand_.eclaterEnCours(played_, Suit.HEART);
        Numbers<Byte> joueursNonJoue_ = new Numbers<Byte>();
        EqList<HandTarot> cartesRelMaitres_ = GameTarotCommonPlaying.cartesRelativementMaitreEncours(
                suites_, possible_, joueursNonJoue_,
                Suit.HEART, Suit.HEART, sure_,
                CardTarot.HEART_JACK);
        assertSame(CardTarot.HEART_KNIGHT,GameTarotProgTrickClassic.sauveQuiPeutFigure(possible_, suites_,cartesRelMaitres_,joueursNonJoue_,Suit.HEART));
    }
    @Test
    public void sauveQuiPeutFigure2Test() {
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.HEART_8);
        hand_.ajouter(CardTarot.HEART_9);
        hand_.ajouter(CardTarot.HEART_10);
        hand_.ajouter(CardTarot.HEART_KNIGHT);
        hand_.ajouter(CardTarot.HEART_KING);
        HandTarot pl_ = new HandTarot();
        EnumMap<Suit, HandTarot> played_ = pl_.couleurs();
        EnumMap<Suit, EqList<HandTarot>> possible_ = generate(5);
        EnumMap<Suit, EqList<HandTarot>> sure_ = generate(5);
        EqList<HandTarot> suites_ = hand_.eclaterEnCours(played_, Suit.HEART);
        Numbers<Byte> joueursNonJoue_ = new Numbers<Byte>();
        joueursNonJoue_.add((byte) 1);
        EqList<HandTarot> cartesRelMaitres_ = GameTarotCommonPlaying.cartesRelativementMaitreEncours(
                suites_, possible_, joueursNonJoue_,
                Suit.HEART, Suit.HEART, sure_,
                CardTarot.HEART_JACK);
        assertSame(CardTarot.HEART_KNIGHT,GameTarotProgTrickClassic.sauveQuiPeutFigure(possible_, suites_,cartesRelMaitres_,joueursNonJoue_,Suit.HEART));
    }
    @Test
    public void sauveQuiPeutFigure3Test() {
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.HEART_8);
        hand_.ajouter(CardTarot.HEART_9);
        hand_.ajouter(CardTarot.HEART_10);
        hand_.ajouter(CardTarot.HEART_KNIGHT);
        hand_.ajouter(CardTarot.HEART_KING);
        HandTarot pl_ = new HandTarot();
        EnumMap<Suit, HandTarot> played_ = pl_.couleurs();
        EnumMap<Suit, EqList<HandTarot>> possible_ = generate(5);
        possible_.getVal(Suit.HEART).get(1).ajouter(CardTarot.HEART_QUEEN);
        EnumMap<Suit, EqList<HandTarot>> sure_ = generate(5);
        EqList<HandTarot> suites_ = hand_.eclaterEnCours(played_, Suit.HEART);
        Numbers<Byte> joueursNonJoue_ = new Numbers<Byte>();
        joueursNonJoue_.add((byte) 1);
        EqList<HandTarot> cartesRelMaitres_ = GameTarotCommonPlaying.cartesRelativementMaitreEncours(
                suites_, possible_, joueursNonJoue_,
                Suit.HEART, Suit.HEART, sure_,
                CardTarot.HEART_JACK);
        assertSame(CardTarot.HEART_KING,GameTarotProgTrickClassic.sauveQuiPeutFigure(possible_, suites_,cartesRelMaitres_,joueursNonJoue_,Suit.HEART));
    }
    @Test
    public void sauveQuiPeutFigure4Test() {
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.HEART_8);
        hand_.ajouter(CardTarot.HEART_9);
        hand_.ajouter(CardTarot.HEART_KNIGHT);
        hand_.ajouter(CardTarot.HEART_KING);
        HandTarot pl_ = new HandTarot();
        EnumMap<Suit, HandTarot> played_ = pl_.couleurs();
        EnumMap<Suit, EqList<HandTarot>> possible_ = generate(5);
        possible_.getVal(Suit.HEART).get(1).ajouter(CardTarot.HEART_10);
        EnumMap<Suit, EqList<HandTarot>> sure_ = generate(5);
        EqList<HandTarot> suites_ = hand_.eclaterEnCours(played_, Suit.HEART);
        Numbers<Byte> joueursNonJoue_ = new Numbers<Byte>();
        joueursNonJoue_.add((byte) 1);
        EqList<HandTarot> cartesRelMaitres_ = GameTarotCommonPlaying.cartesRelativementMaitreEncours(
                suites_, possible_, joueursNonJoue_,
                Suit.HEART, Suit.HEART, sure_,
                CardTarot.HEART_JACK);
        assertSame(CardTarot.HEART_KNIGHT,GameTarotProgTrickClassic.sauveQuiPeutFigure(possible_, suites_,cartesRelMaitres_,joueursNonJoue_,Suit.HEART));
    }
    @Test
    public void sauveQuiPeutFigure5Test() {
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.HEART_8);
        hand_.ajouter(CardTarot.HEART_9);
        hand_.ajouter(CardTarot.HEART_JACK);
        hand_.ajouter(CardTarot.HEART_QUEEN);
        hand_.ajouter(CardTarot.HEART_KING);
        HandTarot pl_ = new HandTarot();
        EnumMap<Suit, HandTarot> played_ = pl_.couleurs();
        EnumMap<Suit, EqList<HandTarot>> possible_ = generate(5);
        possible_.getVal(Suit.HEART).get(1).ajouter(CardTarot.HEART_KNIGHT);
        EnumMap<Suit, EqList<HandTarot>> sure_ = generate(5);
        EqList<HandTarot> suites_ = hand_.eclaterEnCours(played_, Suit.HEART);
        Numbers<Byte> joueursNonJoue_ = new Numbers<Byte>();
        joueursNonJoue_.add((byte) 1);
        EqList<HandTarot> cartesRelMaitres_ = GameTarotCommonPlaying.cartesRelativementMaitreEncours(
                suites_, possible_, joueursNonJoue_,
                Suit.HEART, Suit.HEART, sure_,
                CardTarot.HEART_10);
        assertSame(CardTarot.HEART_KING,GameTarotProgTrickClassic.sauveQuiPeutFigure(possible_, suites_,cartesRelMaitres_,joueursNonJoue_,Suit.HEART));
    }
    @Test
    public void sauveQuiPeutFigure6Test() {
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.HEART_8);
        hand_.ajouter(CardTarot.HEART_9);
        hand_.ajouter(CardTarot.HEART_KNIGHT);
        hand_.ajouter(CardTarot.HEART_QUEEN);
        hand_.ajouter(CardTarot.HEART_KING);
        HandTarot pl_ = new HandTarot();
        EnumMap<Suit, HandTarot> played_ = pl_.couleurs();
        EnumMap<Suit, EqList<HandTarot>> possible_ = generate(5);
        possible_.getVal(Suit.HEART).get(1).ajouter(CardTarot.HEART_JACK);
        EnumMap<Suit, EqList<HandTarot>> sure_ = generate(5);
        EqList<HandTarot> suites_ = hand_.eclaterEnCours(played_, Suit.HEART);
        Numbers<Byte> joueursNonJoue_ = new Numbers<Byte>();
        joueursNonJoue_.add((byte) 1);
        EqList<HandTarot> cartesRelMaitres_ = GameTarotCommonPlaying.cartesRelativementMaitreEncours(
                suites_, possible_, joueursNonJoue_,
                Suit.HEART, Suit.HEART, sure_,
                CardTarot.HEART_10);
        assertSame(CardTarot.HEART_KING,GameTarotProgTrickClassic.sauveQuiPeutFigure(possible_, suites_,cartesRelMaitres_,joueursNonJoue_,Suit.HEART));
    }
    @Test
    public void sauveQuiPeutFigure7Test() {
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.HEART_4);
        hand_.ajouter(CardTarot.HEART_6);
        hand_.ajouter(CardTarot.HEART_8);
        hand_.ajouter(CardTarot.HEART_9);
        HandTarot pl_ = new HandTarot();
        EnumMap<Suit, HandTarot> played_ = pl_.couleurs();
        EnumMap<Suit, EqList<HandTarot>> possible_ = generate(5);
        possible_.getVal(Suit.HEART).get(1).ajouter(CardTarot.HEART_7);
        EnumMap<Suit, EqList<HandTarot>> sure_ = generate(5);
        EqList<HandTarot> suites_ = hand_.eclaterEnCours(played_, Suit.HEART);
        Numbers<Byte> joueursNonJoue_ = new Numbers<Byte>();
        joueursNonJoue_.add((byte) 1);
        EqList<HandTarot> cartesRelMaitres_ = GameTarotCommonPlaying.cartesRelativementMaitreEncours(
                suites_, possible_, joueursNonJoue_,
                Suit.HEART, Suit.HEART, sure_,
                CardTarot.HEART_5);
        assertSame(CardTarot.HEART_9,GameTarotProgTrickClassic.sauveQuiPeutFigure(possible_, suites_,cartesRelMaitres_,joueursNonJoue_,Suit.HEART));
    }
    @Test
    public void sauveQuiPeutFigure8Test() {
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.HEART_4);
        hand_.ajouter(CardTarot.HEART_6);
        hand_.ajouter(CardTarot.HEART_8);
        hand_.ajouter(CardTarot.HEART_10);
        HandTarot pl_ = new HandTarot();
        EnumMap<Suit, HandTarot> played_ = pl_.couleurs();
        EnumMap<Suit, EqList<HandTarot>> possible_ = generate(5);
        possible_.getVal(Suit.HEART).get(1).ajouter(CardTarot.HEART_7);
        EnumMap<Suit, EqList<HandTarot>> sure_ = generate(5);
        EqList<HandTarot> suites_ = hand_.eclaterEnCours(played_, Suit.HEART);
        Numbers<Byte> joueursNonJoue_ = new Numbers<Byte>();
        joueursNonJoue_.add((byte) 1);
        EqList<HandTarot> cartesRelMaitres_ = GameTarotCommonPlaying.cartesRelativementMaitreEncours(
                suites_, possible_, joueursNonJoue_,
                Suit.HEART, Suit.HEART, sure_,
                CardTarot.HEART_5);
        assertSame(CardTarot.HEART_8,GameTarotProgTrickClassic.sauveQuiPeutFigure(possible_, suites_,cartesRelMaitres_,joueursNonJoue_,Suit.HEART));
    }
    @Test
    public void sauveQuiPeutFigure9Test() {
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.HEART_4);
        hand_.ajouter(CardTarot.HEART_6);
        hand_.ajouter(CardTarot.HEART_8);
        hand_.ajouter(CardTarot.HEART_JACK);
        hand_.ajouter(CardTarot.HEART_KNIGHT);
        HandTarot pl_ = new HandTarot();
        EnumMap<Suit, HandTarot> played_ = pl_.couleurs();
        EnumMap<Suit, EqList<HandTarot>> possible_ = generate(5);
        possible_.getVal(Suit.HEART).get(1).ajouter(CardTarot.HEART_7);
        EnumMap<Suit, EqList<HandTarot>> sure_ = generate(5);
        EqList<HandTarot> suites_ = hand_.eclaterEnCours(played_, Suit.HEART);
        Numbers<Byte> joueursNonJoue_ = new Numbers<Byte>();
        joueursNonJoue_.add((byte) 1);
        EqList<HandTarot> cartesRelMaitres_ = GameTarotCommonPlaying.cartesRelativementMaitreEncours(
                suites_, possible_, joueursNonJoue_,
                Suit.HEART, Suit.HEART, sure_,
                CardTarot.HEART_QUEEN);
        assertSame(CardTarot.HEART_KNIGHT,GameTarotProgTrickClassic.sauveQuiPeutFigure(possible_, suites_,cartesRelMaitres_,joueursNonJoue_,Suit.HEART));
    }
    @Test
    public void sauveQuiPeutFigure10Test() {
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.HEART_4);
        hand_.ajouter(CardTarot.HEART_6);
        hand_.ajouter(CardTarot.HEART_8);
        hand_.ajouter(CardTarot.HEART_JACK);
        hand_.ajouter(CardTarot.HEART_KNIGHT);
        HandTarot pl_ = new HandTarot();
        EnumMap<Suit, HandTarot> played_ = pl_.couleurs();
        EnumMap<Suit, EqList<HandTarot>> possible_ = generate(5);
        possible_.getVal(Suit.HEART).get(1).ajouter(CardTarot.HEART_KING);
        EnumMap<Suit, EqList<HandTarot>> sure_ = generate(5);
        EqList<HandTarot> suites_ = hand_.eclaterEnCours(played_, Suit.HEART);
        Numbers<Byte> joueursNonJoue_ = new Numbers<Byte>();
        joueursNonJoue_.add((byte) 1);
        EqList<HandTarot> cartesRelMaitres_ = GameTarotCommonPlaying.cartesRelativementMaitreEncours(
                suites_, possible_, joueursNonJoue_,
                Suit.HEART, Suit.HEART, sure_,
                CardTarot.HEART_QUEEN);
        assertSame(CardTarot.HEART_4,GameTarotProgTrickClassic.sauveQuiPeutFigure(possible_, suites_,cartesRelMaitres_,joueursNonJoue_,Suit.HEART));
    }
    private static EnumMap<Suit,EqList<HandTarot>> generate(int _nbPlayer) {
        EnumMap<Suit,EqList<HandTarot>> e_ = new EnumMap<Suit,EqList<HandTarot>>();
        EnumList<Suit> s_ = new EnumList<Suit>();
        s_.add(Suit.UNDEFINED);
        s_.add(Suit.TRUMP);
        s_.addAllElts(Suit.couleursOrdinaires());
        for (Suit s: s_) {
            EqList<HandTarot> l_ = new EqList<HandTarot>();
            for (int i = 0; i <= _nbPlayer; i++) {
                l_.add(new HandTarot());
            }
            e_.addEntry(s,l_);
        }
        return e_;
    }
}
