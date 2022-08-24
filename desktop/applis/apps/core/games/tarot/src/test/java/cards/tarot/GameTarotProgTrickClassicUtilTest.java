package cards.tarot;

import cards.consts.Suit;
import cards.tarot.enumerations.CardTarot;
import code.util.EnumList;
import code.util.IdMap;
import code.util.*;
import org.junit.Test;

public final class GameTarotProgTrickClassicUtilTest extends CommonGameTarot {
    @Test
    public void getCharacterSeq1Test() {
        HandTarot hand_ = new HandTarot();
        IdMap<Suit, HandTarot> played_ = new HandTarot().couleurs();
        CustList<HandTarot> chars_ = GameTarotProgTrickClassic.getCharacterSeq(hand_.eclaterEnCours(played_, Suit.HEART));
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
        IdMap<Suit, HandTarot> played_ = new HandTarot().couleurs();
        CustList<HandTarot> chars_ = GameTarotProgTrickClassic.getCharacterSeq(hand_.eclaterEnCours(played_, Suit.HEART));
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
        IdMap<Suit, HandTarot> played_ = new HandTarot().couleurs();
        CustList<HandTarot> chars_ = GameTarotProgTrickClassic.getLowCardSeq(hand_.eclaterEnCours(played_, Suit.HEART));
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
        IdMap<Suit, HandTarot> played_ = new HandTarot().couleurs();
        CustList<HandTarot> chars_ = GameTarotProgTrickClassic.getLowCardSeq(hand_.eclaterEnCours(played_, Suit.HEART));
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
        IdMap<Suit, HandTarot> played_ = new HandTarot().couleurs();
        assertSame(CardTarot.HEART_8, GameTarotProgTrickClassic.carteLaPlusPetite(hand_.eclaterEnCours(played_, Suit.HEART)));
    }
    @Test
    public void carteLaPlusPetite2Test() {
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.HEART_JACK);
        hand_.ajouter(CardTarot.HEART_KNIGHT);
        hand_.ajouter(CardTarot.HEART_KING);
        IdMap<Suit, HandTarot> played_ = new HandTarot().couleurs();
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
        IdMap<Suit, HandTarot> played_ = new HandTarot().couleurs();
        assertSame(CardTarot.HEART_8, GameTarotProgTrickClassic.weakestCard(hand_.eclaterEnCours(played_, Suit.HEART)));
    }
    @Test
    public void weakestCard2Test() {
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.HEART_JACK);
        hand_.ajouter(CardTarot.HEART_KNIGHT);
        hand_.ajouter(CardTarot.HEART_KING);
        IdMap<Suit, HandTarot> played_ = new HandTarot().couleurs();
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
        IdMap<Suit, HandTarot> played_ = new HandTarot().couleurs();
        assertSame(CardTarot.HEART_KNIGHT, GameTarotProgTrickClassic.jeuFigureHauteDePlusFaibleSuite(hand_.eclaterEnCours(played_, Suit.HEART)));
    }
    @Test
    public void jeuFigureHauteDePlusFaibleSuite2Test() {
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.HEART_7);
        hand_.ajouter(CardTarot.HEART_8);
        hand_.ajouter(CardTarot.HEART_10);
        IdMap<Suit, HandTarot> played_ = new HandTarot().couleurs();
        assertSame(CardTarot.HEART_8, GameTarotProgTrickClassic.jeuFigureHauteDePlusFaibleSuite(hand_.eclaterEnCours(played_, Suit.HEART)));
    }
    @Test
    public void atoutLePlusPetit1Test() {
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.TRUMP_7);
        hand_.ajouter(CardTarot.TRUMP_8);
        hand_.ajouter(CardTarot.TRUMP_10);
        IdMap<Suit, HandTarot> played_ = new HandTarot().couleurs();
        assertSame(CardTarot.TRUMP_8, GameTarotProgTrickClassic.atoutLePlusPetit(hand_.eclaterEnCours(played_, Suit.TRUMP)));
    }
    @Test
    public void atoutLePlusPetit2Test() {
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.TRUMP_1);
        hand_.ajouter(CardTarot.TRUMP_7);
        hand_.ajouter(CardTarot.TRUMP_8);
        hand_.ajouter(CardTarot.TRUMP_10);
        IdMap<Suit, HandTarot> played_ = new HandTarot().couleurs();
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
        IdMap<Suit, HandTarot> played_ = new HandTarot().couleurs();
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
        IdMap<Suit, HandTarot> played_ = new HandTarot().couleurs();
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
        IdMap<Suit, HandTarot> played_ = new HandTarot().couleurs();
        assertSame(CardTarot.EXCUSE, GameTarotProgTrickClassic.atoutLePlusPetit(hand_.eclaterEnCours(played_, Suit.TRUMP),true));
    }
    @Test
    public void atoutLePlusPetit6Test() {
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.TRUMP_1);
        hand_.ajouter(CardTarot.TRUMP_2);
        hand_.ajouter(CardTarot.TRUMP_3);
        IdMap<Suit, HandTarot> played_ = new HandTarot().couleurs();
        assertSame(CardTarot.TRUMP_3, GameTarotProgTrickClassic.atoutLePlusPetit(hand_.eclaterEnCours(played_, Suit.TRUMP)));
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
        IdMap<Suit, HandTarot> played_ = new HandTarot().couleurs();
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
        IdMap<Suit, HandTarot> played_ = new HandTarot().couleurs();
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
        IdMap<Suit, HandTarot> played_ = new HandTarot().couleurs();
        IdMap<Suit,HandTarot> cartesMaitresses_ = GameTarotCommon.cartesMaitresses(
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
        IdMap<Suit, HandTarot> played_ = new HandTarot().couleurs();
        IdMap<Suit,HandTarot> cartesMaitresses_ = GameTarotCommon.cartesMaitresses(
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
        IdMap<Suit, HandTarot> played_ = new HandTarot().couleurs();
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
        IdMap<Suit, HandTarot> played_ = new HandTarot().couleurs();
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
    public void jeuGrandeCarteDefausseMaitre3Test() {
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.HEART_JACK);
        hand_.ajouter(CardTarot.HEART_KNIGHT);
        hand_.ajouter(CardTarot.HEART_QUEEN);
        hand_.ajouter(CardTarot.HEART_KING);
        hand_.ajouter(CardTarot.DIAMOND_JACK);
        hand_.ajouter(CardTarot.DIAMOND_KNIGHT);
        hand_.ajouter(CardTarot.DIAMOND_QUEEN);
        hand_.ajouter(CardTarot.DIAMOND_KING);
        EnumList<Suit> s_ = new EnumList<Suit>();
        s_.add(Suit.HEART);
        s_.add(Suit.DIAMOND);
        assertSame(CardTarot.HEART_KING,GameTarotProgTrickClassic.jeuGrandeCarteDefausseMaitre(s_,hand_));
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
        IdMap<Suit, HandTarot> played_ = new HandTarot().couleurs();
        IdMap<Suit,HandTarot> cartesMaitresses_ = GameTarotCommon.cartesMaitresses(
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
        IdMap<Suit, HandTarot> played_ = new HandTarot().couleurs();
        IdMap<Suit,HandTarot> cartesMaitresses_ = GameTarotCommon.cartesMaitresses(
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
        IdMap<Suit, HandTarot> played_ = new HandTarot().couleurs();
        IdMap<Suit,HandTarot> cartesMaitresses_ = GameTarotCommon.cartesMaitresses(
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
        IdMap<Suit, HandTarot> played_ = new HandTarot().couleurs();
        IdMap<Suit,HandTarot> cartesMaitresses_ = GameTarotCommon.cartesMaitresses(
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
        IdMap<Suit, HandTarot> played_ = new HandTarot().couleurs();
        IdMap<Suit,HandTarot> cartesMaitresses_ = GameTarotCommon.cartesMaitresses(
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
        IdMap<Suit, HandTarot> played_ = pl_.couleurs();
        IdMap<Suit,HandTarot> cartesMaitresses_ = GameTarotCommon.cartesMaitresses(
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
        IdMap<Suit, HandTarot> played_ = pl_.couleurs();
        IdMap<Suit,HandTarot> cartesMaitresses_ = GameTarotCommon.cartesMaitresses(
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
        IdMap<Suit, HandTarot> played_ = pl_.couleurs();
        IdMap<Suit,HandTarot> cartesMaitresses_ = GameTarotCommon.cartesMaitresses(
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
        IdMap<Suit, HandTarot> played_ = new HandTarot().couleurs();
        IdMap<Suit,HandTarot> cartesMaitresses_ = GameTarotCommon.cartesMaitresses(
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
        IdMap<Suit, HandTarot> played_ = new HandTarot().couleurs();
        IdMap<Suit,HandTarot> cartesMaitresses_ = GameTarotCommon.cartesMaitresses(
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
        IdMap<Suit, HandTarot> played_ = pl_.couleurs();
        IdMap<Suit,HandTarot> cartesMaitresses_ = GameTarotCommon.cartesMaitresses(
                hand_.couleurs(), played_);
        cartesMaitresses_.getVal(Suit.HEART).removeCardIfPresent(CardTarot.HEART_1);
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
        IdMap<Suit, HandTarot> played_ = pl_.couleurs();
        IdMap<Suit,HandTarot> cartesMaitresses_ = GameTarotCommon.cartesMaitresses(
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
        IdMap<Suit, HandTarot> played_ = pl_.couleurs();
        IdMap<Suit,HandTarot> cartesMaitresses_ = GameTarotCommon.cartesMaitresses(
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
        IdMap<Suit, HandTarot> played_ = pl_.couleurs();
        IdMap<Suit,HandTarot> cartesMaitresses_ = GameTarotCommon.cartesMaitresses(
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
        IdMap<Suit, HandTarot> played_ = pl_.couleurs();
        IdMap<Suit,HandTarot> cartesMaitresses_ = GameTarotCommon.cartesMaitresses(
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
        IdMap<Suit, HandTarot> played_ = pl_.couleurs();
        IdMap<Suit,HandTarot> cartesMaitresses_ = GameTarotCommon.cartesMaitresses(
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
        IdMap<Suit, HandTarot> played_ = pl_.couleurs();
        IdMap<Suit,HandTarot> cartesMaitresses_ = GameTarotCommon.cartesMaitresses(
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
        IdMap<Suit, HandTarot> played_ = pl_.couleurs();
        IdMap<Suit,HandTarot> cartesMaitresses_ = GameTarotCommon.cartesMaitresses(
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
        IdMap<Suit, HandTarot> played_ = pl_.couleurs();
        IdMap<Suit,HandTarot> cartesMaitresses_ = GameTarotCommon.cartesMaitresses(
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
        IdMap<Suit, HandTarot> played_ = pl_.couleurs();
        IdMap<Suit,HandTarot> cartesMaitresses_ = GameTarotCommon.cartesMaitresses(
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
        IdMap<Suit, HandTarot> played_ = pl_.couleurs();
        IdMap<Suit,HandTarot> cartesMaitresses_ = GameTarotCommon.cartesMaitresses(
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
        IdMap<Suit, HandTarot> played_ = pl_.couleurs();
        IdMap<Suit,HandTarot> cartesMaitresses_ = GameTarotCommon.cartesMaitresses(
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
        IdMap<Suit, HandTarot> played_ = pl_.couleurs();
        IdMap<Suit,HandTarot> cartesMaitresses_ = GameTarotCommon.cartesMaitresses(
                hand_.couleurs(), played_);
        EnumList<Suit> s_ = new EnumList<Suit>();
        s_.add(Suit.HEART);
        s_.add(Suit.DIAMOND);
        s_.add(Suit.SPADE);
        s_.add(Suit.CLUB);
        cartesMaitresses_.getVal(Suit.HEART).removeCardIfPresent(CardTarot.HEART_1);
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
        IdMap<Suit, HandTarot> played_ = pl_.couleurs();
        IdMap<Suit,HandTarot> cartesMaitresses_ = GameTarotCommon.cartesMaitresses(
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
        IdMap<Suit, HandTarot> played_ = pl_.couleurs();
        IdMap<Suit,HandTarot> cartesMaitresses_ = GameTarotCommon.cartesMaitresses(
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
        IdMap<Suit, HandTarot> played_ = pl_.couleurs();
        IdMap<Suit,HandTarot> cartesMaitresses_ = GameTarotCommon.cartesMaitresses(
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
        IdMap<Suit, HandTarot> played_ = pl_.couleurs();
        IdMap<Suit,HandTarot> cartesMaitresses_ = GameTarotCommon.cartesMaitresses(
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
        IdMap<Suit, HandTarot> played_ = pl_.couleurs();
        IdMap<Suit,HandTarot> cartesMaitresses_ = GameTarotCommon.cartesMaitresses(
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
        IdMap<Suit, HandTarot> played_ = pl_.couleurs();
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
        IdMap<Suit, HandTarot> played_ = pl_.couleurs();
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
        IdMap<Suit, HandTarot> played_ = pl_.couleurs();
        IdMap<Suit, CustList<HandTarot>> possible_ = generate(5);
        IdMap<Suit, CustList<HandTarot>> sure_ = generate(5);
        CustList<HandTarot> suites_ = hand_.eclaterEnCours(played_, Suit.HEART);
        Bytes joueursNonJoue_ = new Bytes();
        CustList<HandTarot> cartesRelMaitres_ = GameTarotCommonPlaying.cartesRelativementMaitreEncours(
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
        IdMap<Suit, HandTarot> played_ = pl_.couleurs();
        IdMap<Suit, CustList<HandTarot>> possible_ = generate(5);
        IdMap<Suit, CustList<HandTarot>> sure_ = generate(5);
        CustList<HandTarot> suites_ = hand_.eclaterEnCours(played_, Suit.HEART);
        Bytes joueursNonJoue_ = new Bytes();
        joueursNonJoue_.add((byte) 1);
        CustList<HandTarot> cartesRelMaitres_ = GameTarotCommonPlaying.cartesRelativementMaitreEncours(
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
        IdMap<Suit, HandTarot> played_ = pl_.couleurs();
        IdMap<Suit, CustList<HandTarot>> possible_ = generate(5);
        addCard(possible_,1,CardTarot.HEART_QUEEN);
        IdMap<Suit, CustList<HandTarot>> sure_ = generate(5);
        CustList<HandTarot> suites_ = hand_.eclaterEnCours(played_, Suit.HEART);
        Bytes joueursNonJoue_ = new Bytes();
        joueursNonJoue_.add((byte) 1);
        CustList<HandTarot> cartesRelMaitres_ = GameTarotCommonPlaying.cartesRelativementMaitreEncours(
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
        IdMap<Suit, HandTarot> played_ = pl_.couleurs();
        IdMap<Suit, CustList<HandTarot>> possible_ = generate(5);
        addCard(possible_,1,CardTarot.HEART_10);
        IdMap<Suit, CustList<HandTarot>> sure_ = generate(5);
        CustList<HandTarot> suites_ = hand_.eclaterEnCours(played_, Suit.HEART);
        Bytes joueursNonJoue_ = new Bytes();
        joueursNonJoue_.add((byte) 1);
        CustList<HandTarot> cartesRelMaitres_ = GameTarotCommonPlaying.cartesRelativementMaitreEncours(
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
        IdMap<Suit, HandTarot> played_ = pl_.couleurs();
        IdMap<Suit, CustList<HandTarot>> possible_ = generate(5);
        addCard(possible_,1,CardTarot.HEART_KNIGHT);
        IdMap<Suit, CustList<HandTarot>> sure_ = generate(5);
        CustList<HandTarot> suites_ = hand_.eclaterEnCours(played_, Suit.HEART);
        Bytes joueursNonJoue_ = new Bytes();
        joueursNonJoue_.add((byte) 1);
        CustList<HandTarot> cartesRelMaitres_ = GameTarotCommonPlaying.cartesRelativementMaitreEncours(
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
        IdMap<Suit, HandTarot> played_ = pl_.couleurs();
        IdMap<Suit, CustList<HandTarot>> possible_ = generate(5);
        addCard(possible_,1,CardTarot.HEART_JACK);
        IdMap<Suit, CustList<HandTarot>> sure_ = generate(5);
        CustList<HandTarot> suites_ = hand_.eclaterEnCours(played_, Suit.HEART);
        Bytes joueursNonJoue_ = new Bytes();
        joueursNonJoue_.add((byte) 1);
        CustList<HandTarot> cartesRelMaitres_ = GameTarotCommonPlaying.cartesRelativementMaitreEncours(
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
        IdMap<Suit, HandTarot> played_ = pl_.couleurs();
        IdMap<Suit, CustList<HandTarot>> possible_ = generate(5);
        addCard(possible_,1,CardTarot.HEART_7);
        IdMap<Suit, CustList<HandTarot>> sure_ = generate(5);
        CustList<HandTarot> suites_ = hand_.eclaterEnCours(played_, Suit.HEART);
        Bytes joueursNonJoue_ = new Bytes();
        joueursNonJoue_.add((byte) 1);
        CustList<HandTarot> cartesRelMaitres_ = GameTarotCommonPlaying.cartesRelativementMaitreEncours(
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
        IdMap<Suit, HandTarot> played_ = pl_.couleurs();
        IdMap<Suit, CustList<HandTarot>> possible_ = generate(5);
        addCard(possible_,1,CardTarot.HEART_7);
        IdMap<Suit, CustList<HandTarot>> sure_ = generate(5);
        CustList<HandTarot> suites_ = hand_.eclaterEnCours(played_, Suit.HEART);
        Bytes joueursNonJoue_ = new Bytes();
        joueursNonJoue_.add((byte) 1);
        CustList<HandTarot> cartesRelMaitres_ = GameTarotCommonPlaying.cartesRelativementMaitreEncours(
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
        IdMap<Suit, HandTarot> played_ = pl_.couleurs();
        IdMap<Suit, CustList<HandTarot>> possible_ = generate(5);
        addCard(possible_,1,CardTarot.HEART_7);
        IdMap<Suit, CustList<HandTarot>> sure_ = generate(5);
        CustList<HandTarot> suites_ = hand_.eclaterEnCours(played_, Suit.HEART);
        Bytes joueursNonJoue_ = new Bytes();
        joueursNonJoue_.add((byte) 1);
        CustList<HandTarot> cartesRelMaitres_ = GameTarotCommonPlaying.cartesRelativementMaitreEncours(
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
        IdMap<Suit, HandTarot> played_ = pl_.couleurs();
        IdMap<Suit, CustList<HandTarot>> possible_ = generate(5);
        addCard(possible_,1,CardTarot.HEART_KING);
        IdMap<Suit, CustList<HandTarot>> sure_ = generate(5);
        CustList<HandTarot> suites_ = hand_.eclaterEnCours(played_, Suit.HEART);
        Bytes joueursNonJoue_ = new Bytes();
        joueursNonJoue_.add((byte) 1);
        CustList<HandTarot> cartesRelMaitres_ = GameTarotCommonPlaying.cartesRelativementMaitreEncours(
                suites_, possible_, joueursNonJoue_,
                Suit.HEART, Suit.HEART, sure_,
                CardTarot.HEART_QUEEN);
        assertSame(CardTarot.HEART_4,GameTarotProgTrickClassic.sauveQuiPeutFigure(possible_, suites_,cartesRelMaitres_,joueursNonJoue_,Suit.HEART));
    }
    @Test
    public void peutRamasserDemandeAtoutNonMaitre1Test() {
        IdMap<Suit, CustList<HandTarot>> possible_ = generate(5);
        IdMap<Suit, CustList<HandTarot>> sure_ = generate(5);
        addCard(possible_,1,CardTarot.TRUMP_21);
        addCard(sure_,1,CardTarot.TRUMP_21);
        addCard(possible_,2,CardTarot.TRUMP_11);
        addCard(sure_,2,CardTarot.TRUMP_11);
        addCard(possible_,0,CardTarot.TRUMP_15);
        addCard(sure_,0,CardTarot.TRUMP_15);
        Bytes played_ = new Bytes();
        played_.add((byte) 3);
        played_.add((byte) 4);
        Bytes notPlayed_ = new Bytes();
        notPlayed_.add((byte)1);
        notPlayed_.add((byte)2);
        assertTrue(!GameTarotProgTrickClassic.peutRamasserDemandeAtoutNonMaitre(possible_,sure_,(byte)0,notPlayed_,played_));
    }
    @Test
    public void peutRamasserDemandeAtoutNonMaitre2Test() {
        IdMap<Suit, CustList<HandTarot>> possible_ = generate(5);
        IdMap<Suit, CustList<HandTarot>> sure_ = generate(5);
        addCard(possible_,1,CardTarot.TRUMP_21);
        addCard(possible_,2,CardTarot.TRUMP_11);
        addCard(sure_,2,CardTarot.TRUMP_11);
        addCard(possible_,0,CardTarot.TRUMP_15);
        addCard(sure_,0,CardTarot.TRUMP_15);
        Bytes played_ = new Bytes();
        played_.add((byte) 3);
        played_.add((byte) 4);
        Bytes notPlayed_ = new Bytes();
        notPlayed_.add((byte)1);
        notPlayed_.add((byte)2);
        assertTrue(!GameTarotProgTrickClassic.peutRamasserDemandeAtoutNonMaitre(possible_,sure_,(byte)0,notPlayed_,played_));
    }
    @Test
    public void peutRamasserDemandeAtoutNonMaitre3Test() {
        IdMap<Suit, CustList<HandTarot>> possible_ = generate(5);
        IdMap<Suit, CustList<HandTarot>> sure_ = generate(5);
        addCard(possible_,1,CardTarot.TRUMP_21);
        addCard(possible_,2,CardTarot.TRUMP_11);
        addCard(sure_,2,CardTarot.TRUMP_11);
        addCard(possible_,0,CardTarot.TRUMP_15);
        addCard(sure_,0,CardTarot.TRUMP_15);
        addCard(possible_,3,CardTarot.TRUMP_16);
        Bytes played_ = new Bytes();
        played_.add((byte) 3);
        played_.add((byte) 4);
        Bytes notPlayed_ = new Bytes();
        notPlayed_.add((byte)1);
        notPlayed_.add((byte)2);
        assertTrue(!GameTarotProgTrickClassic.peutRamasserDemandeAtoutNonMaitre(possible_,sure_,(byte)0,notPlayed_,played_));
    }
    @Test
    public void peutRamasserDemandeAtoutNonMaitre4Test() {
        IdMap<Suit, CustList<HandTarot>> possible_ = generate(5);
        IdMap<Suit, CustList<HandTarot>> sure_ = generate(5);
        addCard(possible_,1,CardTarot.TRUMP_21);
        addCard(possible_,1,CardTarot.TRUMP_14);
        addCard(possible_,2,CardTarot.TRUMP_11);
        addCard(sure_,2,CardTarot.TRUMP_11);
        addCard(possible_,0,CardTarot.TRUMP_15);
        addCard(sure_,0,CardTarot.TRUMP_15);
        addCard(possible_,3,CardTarot.TRUMP_21);
        addCard(possible_,3,CardTarot.TRUMP_14);
        Bytes played_ = new Bytes();
        played_.add((byte) 3);
        played_.add((byte) 4);
        Bytes notPlayed_ = new Bytes();
        notPlayed_.add((byte)1);
        notPlayed_.add((byte)2);
        assertTrue(GameTarotProgTrickClassic.peutRamasserDemandeAtoutNonMaitre(possible_,sure_,(byte)0,notPlayed_,played_));
    }
    @Test
    public void peutRamasserDemandeAtoutNonMaitre5Test() {
        IdMap<Suit, CustList<HandTarot>> possible_ = generate(5);
        IdMap<Suit, CustList<HandTarot>> sure_ = generate(5);
        addCard(possible_,1,CardTarot.TRUMP_14);
        addCard(sure_,1,CardTarot.TRUMP_14);
        addCard(possible_,2,CardTarot.TRUMP_11);
        addCard(sure_,2,CardTarot.TRUMP_11);
        addCard(possible_,0,CardTarot.TRUMP_15);
        addCard(sure_,0,CardTarot.TRUMP_15);
        addCard(possible_,3,CardTarot.TRUMP_21);
        addCard(sure_,3,CardTarot.TRUMP_21);
        Bytes played_ = new Bytes();
        played_.add((byte) 3);
        played_.add((byte) 4);
        Bytes notPlayed_ = new Bytes();
        notPlayed_.add((byte)1);
        notPlayed_.add((byte)2);
        assertTrue(GameTarotProgTrickClassic.peutRamasserDemandeAtoutNonMaitre(possible_,sure_,(byte)0,notPlayed_,played_));
    }
    @Test
    public void peutRamasserDemandeAtoutNonMaitre6Test() {
        IdMap<Suit, CustList<HandTarot>> possible_ = generate(5);
        IdMap<Suit, CustList<HandTarot>> sure_ = generate(5);
        addCard(possible_,1,CardTarot.TRUMP_14);
        addCard(sure_,1,CardTarot.TRUMP_14);
        addCard(possible_,0,CardTarot.TRUMP_15);
        addCard(sure_,0,CardTarot.TRUMP_15);
        addCard(possible_,3,CardTarot.TRUMP_21);
        addCard(sure_,3,CardTarot.TRUMP_21);
        Bytes played_ = new Bytes();
        played_.add((byte) 3);
        played_.add((byte) 4);
        Bytes notPlayed_ = new Bytes();
        notPlayed_.add((byte)1);
        notPlayed_.add((byte)2);
        assertTrue(GameTarotProgTrickClassic.peutRamasserDemandeAtoutNonMaitre(possible_,sure_,(byte)0,notPlayed_,played_));
    }
    @Test
    public void peutSauverFigureAppele1Test() {
        IdMap<Suit, CustList<HandTarot>> possible_ = generate(5);
        addCard(possible_,1,CardTarot.TRUMP_14);
        Bytes played_ = new Bytes();
        played_.add((byte) 3);
        played_.add((byte) 4);
        Bytes notPlayed_ = new Bytes();
        notPlayed_.add((byte)1);
        notPlayed_.add((byte)2);
        assertTrue(!GameTarotProgTrickClassic.peutSauverFigureAppele(possible_,Suit.HEART,new HandTarot(),notPlayed_,true));
    }
    @Test
    public void peutSauverFigureAppele2Test() {
        IdMap<Suit, CustList<HandTarot>> possible_ = generate(5);
        addCard(possible_,1,CardTarot.TRUMP_14);
        addCard(possible_,1,CardTarot.HEART_JACK);
        Bytes played_ = new Bytes();
        played_.add((byte) 3);
        played_.add((byte) 4);
        Bytes notPlayed_ = new Bytes();
        notPlayed_.add((byte)1);
        notPlayed_.add((byte)2);
        assertTrue(!GameTarotProgTrickClassic.peutSauverFigureAppele(possible_,Suit.HEART,new HandTarot(),notPlayed_,true));
    }
    @Test
    public void peutSauverFigureAppele3Test() {
        IdMap<Suit, CustList<HandTarot>> possible_ = generate(5);
        addCard(possible_,1,CardTarot.TRUMP_14);
        addCard(possible_,1,CardTarot.HEART_JACK);
        Bytes played_ = new Bytes();
        played_.add((byte) 3);
        played_.add((byte) 4);
        Bytes notPlayed_ = new Bytes();
        notPlayed_.add((byte)1);
        notPlayed_.add((byte)2);
        HandTarot last_ = new HandTarot();
        last_.ajouter(CardTarot.HEART_7);
        assertTrue(!GameTarotProgTrickClassic.peutSauverFigureAppele(possible_,Suit.HEART, last_,notPlayed_,true));
    }
    @Test
    public void peutSauverFigureAppele4Test() {
        IdMap<Suit, CustList<HandTarot>> possible_ = generate(5);
        addCard(possible_,1,CardTarot.TRUMP_14);
        addCard(possible_,1,CardTarot.HEART_JACK);
        Bytes played_ = new Bytes();
        played_.add((byte) 3);
        played_.add((byte) 4);
        Bytes notPlayed_ = new Bytes();
        notPlayed_.add((byte)1);
        notPlayed_.add((byte)2);
        HandTarot last_ = new HandTarot();
        last_.ajouter(CardTarot.HEART_KING);
        assertTrue(GameTarotProgTrickClassic.peutSauverFigureAppele(possible_,Suit.HEART, last_,notPlayed_,true));
    }
    @Test
    public void peutSauverFigureAppele5Test() {
        IdMap<Suit, CustList<HandTarot>> possible_ = generate(5);
        addCard(possible_,1,CardTarot.TRUMP_14);
        addCard(possible_,1,CardTarot.HEART_10);
        Bytes played_ = new Bytes();
        played_.add((byte) 3);
        played_.add((byte) 4);
        Bytes notPlayed_ = new Bytes();
        notPlayed_.add((byte)1);
        notPlayed_.add((byte)2);
        HandTarot last_ = new HandTarot();
        last_.ajouter(CardTarot.HEART_9);
        assertTrue(GameTarotProgTrickClassic.peutSauverFigureAppele(possible_,Suit.HEART, last_,notPlayed_,true));
    }
    @Test
    public void peutSauverFigureAppele6Test() {
        IdMap<Suit, CustList<HandTarot>> possible_ = generate(5);
        Bytes played_ = new Bytes();
        played_.add((byte) 3);
        played_.add((byte) 4);
        Bytes notPlayed_ = new Bytes();
        notPlayed_.add((byte)1);
        notPlayed_.add((byte)2);
        HandTarot last_ = new HandTarot();
        assertTrue(GameTarotProgTrickClassic.peutSauverFigureAppele(possible_,Suit.HEART, last_,notPlayed_,true));
    }
    @Test
    public void peutSauverFigureAppele7Test() {
        IdMap<Suit, CustList<HandTarot>> possible_ = generate(5);
        Bytes played_ = new Bytes();
        played_.add((byte) 3);
        played_.add((byte) 4);
        Bytes notPlayed_ = new Bytes();
        notPlayed_.add((byte)1);
        notPlayed_.add((byte)2);
        HandTarot last_ = new HandTarot();
        assertTrue(!GameTarotProgTrickClassic.peutSauverFigureAppele(possible_,Suit.HEART, last_,notPlayed_,false));
    }
    @Test
    public void premiereSuitePlusLongueQueTotalCouleurDemandee1Test() {
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.HEART_4);
        hand_.ajouter(CardTarot.HEART_6);
        hand_.ajouter(CardTarot.HEART_8);
        hand_.ajouter(CardTarot.HEART_JACK);
        hand_.ajouter(CardTarot.HEART_KNIGHT);
        HandTarot pl_ = new HandTarot();
        pl_.ajouter(CardTarot.HEART_KING);
        pl_.ajouter(CardTarot.HEART_QUEEN);
        IdMap<Suit, HandTarot> played_ = pl_.couleurs();
        IdMap<Suit, CustList<HandTarot>> possible_ = generate(5);
        addCard(possible_,1,CardTarot.HEART_7);
        CustList<HandTarot> suites_ = hand_.eclaterEnCours(played_, Suit.HEART);
        Bytes joueursNonJoue_ = new Bytes();
        joueursNonJoue_.add((byte) 1);
        assertTrue(GameTarotProgTrickClassic.premiereSuitePlusLongueQueTotalCouleurDemandee(suites_,possible_,Suit.HEART,joueursNonJoue_));
    }
    @Test
    public void premiereSuitePlusLongueQueTotalCouleurDemandee2Test() {
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.HEART_4);
        hand_.ajouter(CardTarot.HEART_6);
        hand_.ajouter(CardTarot.HEART_8);
        hand_.ajouter(CardTarot.HEART_KNIGHT);
        HandTarot pl_ = new HandTarot();
        pl_.ajouter(CardTarot.HEART_QUEEN);
        pl_.ajouter(CardTarot.HEART_KING);
        IdMap<Suit, HandTarot> played_ = pl_.couleurs();
        IdMap<Suit, CustList<HandTarot>> possible_ = generate(5);
        addCard(possible_,1,CardTarot.HEART_JACK);
        addCard(possible_,1,CardTarot.HEART_10);
        CustList<HandTarot> suites_ = hand_.eclaterEnCours(played_, Suit.HEART);
        Bytes joueursNonJoue_ = new Bytes();
        joueursNonJoue_.add((byte) 1);
        assertTrue(!GameTarotProgTrickClassic.premiereSuitePlusLongueQueTotalCouleurDemandee(suites_,possible_,Suit.HEART,joueursNonJoue_));
    }
    @Test
    public void premiereSuitePlusLongueQueTotalCouleurDemandee3Test() {
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.HEART_4);
        hand_.ajouter(CardTarot.HEART_6);
        hand_.ajouter(CardTarot.HEART_8);
        HandTarot pl_ = new HandTarot();
        pl_.ajouter(CardTarot.HEART_QUEEN);
        pl_.ajouter(CardTarot.HEART_KING);
        IdMap<Suit, HandTarot> played_ = pl_.couleurs();
        IdMap<Suit, CustList<HandTarot>> possible_ = generate(5);
        CustList<HandTarot> suites_ = hand_.eclaterEnCours(played_, Suit.HEART);
        Bytes joueursNonJoue_ = new Bytes();
        joueursNonJoue_.add((byte) 1);
        assertTrue(!GameTarotProgTrickClassic.premiereSuitePlusLongueQueTotalCouleurDemandee(suites_,possible_,Suit.HEART,joueursNonJoue_));
    }
    @Test
    public void aucunePriseMainPossibleCouleur1Test() {
        IdMap<Suit, CustList<HandTarot>> possible_ = generate(5);
        addCard(possible_, 1, CardTarot.TRUMP_2);
        Bytes joueursNonJoue_ = new Bytes();
        joueursNonJoue_.add((byte) 1);
        assertTrue(!GameTarotProgTrickClassic.aucunePriseMainPossibleCouleur(possible_,Suit.HEART,CardTarot.HEART_QUEEN,joueursNonJoue_));
    }
    @Test
    public void aucunePriseMainPossibleCouleur2Test() {
        IdMap<Suit, CustList<HandTarot>> possible_ = generate(5);
        Bytes joueursNonJoue_ = new Bytes();
        joueursNonJoue_.add((byte) 1);
        assertTrue(GameTarotProgTrickClassic.aucunePriseMainPossibleCouleur(possible_,Suit.HEART,CardTarot.HEART_QUEEN,joueursNonJoue_));
    }
    @Test
    public void aucunePriseMainPossibleCouleur3Test() {
        IdMap<Suit, CustList<HandTarot>> possible_ = generate(5);
        addCard(possible_, 1, CardTarot.HEART_KNIGHT);
        Bytes joueursNonJoue_ = new Bytes();
        joueursNonJoue_.add((byte) 1);
        assertTrue(GameTarotProgTrickClassic.aucunePriseMainPossibleCouleur(possible_,Suit.HEART,CardTarot.HEART_QUEEN,joueursNonJoue_));
    }
    @Test
    public void aucunePriseMainPossibleCouleur4Test() {
        IdMap<Suit, CustList<HandTarot>> possible_ = generate(5);
        addCard(possible_, 1, CardTarot.HEART_KING);
        Bytes joueursNonJoue_ = new Bytes();
        joueursNonJoue_.add((byte) 1);
        assertTrue(!GameTarotProgTrickClassic.aucunePriseMainPossibleCouleur(possible_,Suit.HEART,CardTarot.HEART_QUEEN,joueursNonJoue_));
    }
    @Test
    public void aucunePriseMainPossibleParFigure1Test() {
        IdMap<Suit, CustList<HandTarot>> possible_ = generate(5);
        Bytes joueursNonJoue_ = new Bytes();
        joueursNonJoue_.add((byte) 1);
        assertTrue(GameTarotProgTrickClassic.aucunePriseMainPossibleParFigure(possible_,Suit.HEART,joueursNonJoue_));
    }
    @Test
    public void aucunePriseMainPossibleParFigure2Test() {
        IdMap<Suit, CustList<HandTarot>> possible_ = generate(5);
        addCard(possible_, 1, CardTarot.HEART_10);
        Bytes joueursNonJoue_ = new Bytes();
        joueursNonJoue_.add((byte) 1);
        assertTrue(GameTarotProgTrickClassic.aucunePriseMainPossibleParFigure(possible_,Suit.HEART,joueursNonJoue_));
    }
    @Test
    public void aucunePriseMainPossibleParFigure3Test() {
        IdMap<Suit, CustList<HandTarot>> possible_ = generate(5);
        addCard(possible_, 1, CardTarot.HEART_JACK);
        Bytes joueursNonJoue_ = new Bytes();
        joueursNonJoue_.add((byte) 1);
        assertTrue(!GameTarotProgTrickClassic.aucunePriseMainPossibleParFigure(possible_,Suit.HEART,joueursNonJoue_));
    }
    @Test
    public void aucunePriseMainPossibleParFigure4Test() {
        IdMap<Suit, CustList<HandTarot>> possible_ = generate(5);
        addCard(possible_, 1, CardTarot.TRUMP_2);
        Bytes joueursNonJoue_ = new Bytes();
        joueursNonJoue_.add((byte) 1);
        assertTrue(GameTarotProgTrickClassic.aucunePriseMainPossibleParFigure(possible_,Suit.HEART,joueursNonJoue_));
    }
    @Test
    public void joueurConfianceRamasseurProbaPli1Test() {
        IdMap<Suit, CustList<HandTarot>> possible_ = generate(5);
        Bytes joueursNonJoue_ = new Bytes();
        joueursNonJoue_.add((byte) 1);
        assertTrue(GameTarotProgTrickClassic.joueurConfianceRamasseurProbaPli(joueursNonJoue_,Suit.HEART,possible_,CardTarot.HEART_QUEEN));
    }
    @Test
    public void joueurConfianceRamasseurProbaPli2Test() {
        IdMap<Suit, CustList<HandTarot>> possible_ = generate(5);
        addCard(possible_,1,CardTarot.HEART_9);
        Bytes joueursNonJoue_ = new Bytes();
        joueursNonJoue_.add((byte) 1);
        assertTrue(GameTarotProgTrickClassic.joueurConfianceRamasseurProbaPli(joueursNonJoue_,Suit.HEART,possible_,CardTarot.HEART_QUEEN));
    }
    @Test
    public void joueurConfianceRamasseurProbaPli3Test() {
        IdMap<Suit, CustList<HandTarot>> possible_ = generate(5);
        addCard(possible_,1,CardTarot.HEART_KING);
        Bytes joueursNonJoue_ = new Bytes();
        joueursNonJoue_.add((byte) 1);
        assertTrue(!GameTarotProgTrickClassic.joueurConfianceRamasseurProbaPli(joueursNonJoue_,Suit.HEART,possible_,CardTarot.HEART_QUEEN));
    }
    @Test
    public void joueurConfianceRamasseurProbaPli4Test() {
        IdMap<Suit, CustList<HandTarot>> possible_ = generate(5);
        addCard(possible_,1,CardTarot.TRUMP_2);
        Bytes joueursNonJoue_ = new Bytes();
        joueursNonJoue_.add((byte) 1);
        assertTrue(!GameTarotProgTrickClassic.joueurConfianceRamasseurProbaPli(joueursNonJoue_,Suit.HEART,possible_,CardTarot.HEART_QUEEN));
    }
    @Test
    public void leadTrumps1Test() {
        IdMap<Suit, CustList<HandTarot>> possible_ = generate(5);
        Bytes joueursNonJoue_ = new Bytes();
        joueursNonJoue_.add((byte) 1);
        assertTrue(GameTarotProgTrickClassic.leadTrumps(possible_,CardTarot.TRUMP_12,joueursNonJoue_));
    }
    @Test
    public void leadTrumps2Test() {
        IdMap<Suit, CustList<HandTarot>> possible_ = generate(5);
        addCard(possible_,1,CardTarot.TRUMP_2);
        Bytes joueursNonJoue_ = new Bytes();
        joueursNonJoue_.add((byte) 1);
        assertTrue(GameTarotProgTrickClassic.leadTrumps(possible_,CardTarot.TRUMP_12,joueursNonJoue_));
    }
    @Test
    public void leadTrumps3Test() {
        IdMap<Suit, CustList<HandTarot>> possible_ = generate(5);
        addCard(possible_,1,CardTarot.TRUMP_21);
        Bytes joueursNonJoue_ = new Bytes();
        joueursNonJoue_.add((byte) 1);
        assertTrue(!GameTarotProgTrickClassic.leadTrumps(possible_,CardTarot.TRUMP_12,joueursNonJoue_));
    }
    @Test
    public void tryLeadTrick1Test(){
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.HEART_4);
        hand_.ajouter(CardTarot.HEART_6);
        hand_.ajouter(CardTarot.HEART_8);
        hand_.ajouter(CardTarot.HEART_JACK);
        hand_.ajouter(CardTarot.HEART_KNIGHT);
        HandTarot pl_ = new HandTarot();
        IdMap<Suit, HandTarot> played_ = pl_.couleurs();
        IdMap<Suit, CustList<HandTarot>> possible_ = generate(5);
        addCard(possible_,1,CardTarot.HEART_KING);
        IdMap<Suit, CustList<HandTarot>> sure_ = generate(5);
        CustList<HandTarot> suites_ = hand_.eclaterEnCours(played_, Suit.HEART);
        Bytes joueursNonJoue_ = new Bytes();
        joueursNonJoue_.add((byte) 1);
        IdMap<Suit, HandTarot> repartition_ = hand_.couleurs();
        IdMap<Suit,HandTarot> cartesMaitresses_ = GameTarotCommon.cartesMaitresses(
                repartition_, played_);
        CustList<HandTarot> cartesRelMaitres_ = GameTarotCommonPlaying.cartesRelativementMaitreEncours(
                suites_, possible_, joueursNonJoue_,
                Suit.HEART, Suit.HEART, sure_,
                CardTarot.HEART_QUEEN);
        assertSame(CardTarot.HEART_4,GameTarotProgTrickClassic.tryLeadTrick(cartesMaitresses_,Suit.HEART,hand_.couleur(Suit.HEART),suites_,cartesRelMaitres_));
    }
    @Test
    public void tryLeadTrick2Test(){
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.HEART_4);
        hand_.ajouter(CardTarot.HEART_6);
        hand_.ajouter(CardTarot.HEART_8);
        hand_.ajouter(CardTarot.HEART_JACK);
        hand_.ajouter(CardTarot.HEART_QUEEN);
        HandTarot pl_ = new HandTarot();
        IdMap<Suit, HandTarot> played_ = pl_.couleurs();
        IdMap<Suit, CustList<HandTarot>> possible_ = generate(5);
        IdMap<Suit, CustList<HandTarot>> sure_ = generate(5);
        CustList<HandTarot> suites_ = hand_.eclaterEnCours(played_, Suit.HEART);
        Bytes joueursNonJoue_ = new Bytes();
        joueursNonJoue_.add((byte) 1);
        IdMap<Suit, HandTarot> repartition_ = hand_.couleurs();
        IdMap<Suit,HandTarot> cartesMaitresses_ = GameTarotCommon.cartesMaitresses(
                repartition_, played_);
        CustList<HandTarot> cartesRelMaitres_ = GameTarotCommonPlaying.cartesRelativementMaitreEncours(
                suites_, possible_, joueursNonJoue_,
                Suit.HEART, Suit.HEART, sure_,
                CardTarot.HEART_KNIGHT);
        assertSame(CardTarot.HEART_QUEEN,GameTarotProgTrickClassic.tryLeadTrick(cartesMaitresses_,Suit.HEART,hand_.couleur(Suit.HEART),suites_,cartesRelMaitres_));
    }
    @Test
    public void tryLeadTrick3Test(){
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.HEART_4);
        hand_.ajouter(CardTarot.HEART_6);
        hand_.ajouter(CardTarot.HEART_8);
        hand_.ajouter(CardTarot.HEART_JACK);
        HandTarot pl_ = new HandTarot();
        pl_.ajouter(CardTarot.HEART_KING);
        pl_.ajouter(CardTarot.HEART_QUEEN);
        pl_.ajouter(CardTarot.HEART_KNIGHT);
        IdMap<Suit, HandTarot> played_ = pl_.couleurs();
        IdMap<Suit, CustList<HandTarot>> possible_ = generate(5);
        IdMap<Suit, CustList<HandTarot>> sure_ = generate(5);
        CustList<HandTarot> suites_ = hand_.eclaterEnCours(played_, Suit.HEART);
        Bytes joueursNonJoue_ = new Bytes();
        joueursNonJoue_.add((byte) 1);
        IdMap<Suit, HandTarot> repartition_ = hand_.couleurs();
        IdMap<Suit,HandTarot> cartesMaitresses_ = GameTarotCommon.cartesMaitresses(
                repartition_, played_);
        CustList<HandTarot> cartesRelMaitres_ = GameTarotCommonPlaying.cartesRelativementMaitreEncours(
                suites_, possible_, joueursNonJoue_,
                Suit.HEART, Suit.HEART, sure_,
                CardTarot.HEART_10);
        assertSame(CardTarot.HEART_JACK,GameTarotProgTrickClassic.tryLeadTrick(cartesMaitresses_,Suit.HEART,hand_.couleur(Suit.HEART),suites_,cartesRelMaitres_));
    }
    @Test
    public void tryPlayExcuse1Test() {
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.HEART_4);
        hand_.ajouter(CardTarot.HEART_6);
        hand_.ajouter(CardTarot.HEART_8);
        hand_.ajouter(CardTarot.HEART_JACK);
        assertSame(CardTarot.WHITE,GameTarotProgTrickClassic.tryPlayExcuse(true,hand_));
    }
    @Test
    public void tryPlayExcuse2Test() {
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.HEART_8);
        hand_.ajouter(CardTarot.HEART_JACK);
        assertSame(CardTarot.WHITE,GameTarotProgTrickClassic.tryPlayExcuse(true,hand_));
    }
    @Test
    public void tryPlayExcuse3Test() {
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.EXCUSE);
        hand_.ajouter(CardTarot.HEART_JACK);
        assertSame(CardTarot.EXCUSE,GameTarotProgTrickClassic.tryPlayExcuse(true,hand_));
    }
    @Test
    public void tryPlayExcuse4Test() {
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.EXCUSE);
        hand_.ajouter(CardTarot.HEART_10);
        hand_.ajouter(CardTarot.HEART_JACK);
        assertSame(CardTarot.WHITE,GameTarotProgTrickClassic.tryPlayExcuse(false,hand_));
    }
    @Test
    public void tryPlayExcuse5Test() {
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.EXCUSE);
        hand_.ajouter(CardTarot.HEART_JACK);
        assertSame(CardTarot.EXCUSE,GameTarotProgTrickClassic.tryPlayExcuse(false,hand_));
    }
    private static void addCard(IdMap<Suit, CustList<HandTarot>> _poss, int _p, CardTarot _c) {
        HandTarot h_ = _poss.getVal(_c.getId().getCouleur()).get(_p);
        if (h_.contient(_c)) {
            return;
        }
        h_.ajouter(_c);
        h_.trierParForceEnCours(_c.getId().getCouleur());
    }
    private static IdMap<Suit,CustList<HandTarot>> generate(int _nbPlayer) {
        IdMap<Suit,CustList<HandTarot>> e_ = new IdMap<Suit,CustList<HandTarot>>();
        EnumList<Suit> s_ = new EnumList<Suit>();
        s_.add(Suit.UNDEFINED);
        s_.add(Suit.TRUMP);
        s_.addAllElts(Suit.couleursOrdinaires());
        for (Suit s: s_) {
            CustList<HandTarot> l_ = new CustList<HandTarot>();
            for (int i = 0; i <= _nbPlayer; i++) {
                l_.add(new HandTarot());
            }
            e_.addEntry(s,l_);
        }
        return e_;
    }
}
