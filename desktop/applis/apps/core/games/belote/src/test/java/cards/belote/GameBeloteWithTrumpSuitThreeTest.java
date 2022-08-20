package cards.belote;

import code.util.CustList;
import org.junit.Test;

import cards.belote.enumerations.BeloteTrumpPartner;
import cards.belote.enumerations.BidBelote;
import cards.belote.enumerations.CardBelote;
import cards.consts.GameType;
import cards.consts.MixCardsChoice;
import cards.consts.Suit;
import code.util.IdMap;

public class GameBeloteWithTrumpSuitThreeTest extends GameBeloteWithTrumpSuit {

    static DealBelote initializeHands() {
        CustList<HandBelote> mains_ = new CustList<HandBelote>();
        HandBelote main_ = new HandBelote();
        main_.ajouter(CardBelote.DIAMOND_QUEEN);
        main_.ajouter(CardBelote.HEART_10);
        main_.ajouter(CardBelote.SPADE_9);
        main_.ajouter(CardBelote.SPADE_JACK);
        main_.ajouter(CardBelote.DIAMOND_8);
        mains_.add(main_);
        main_ = new HandBelote();
        main_.ajouter(CardBelote.HEART_1);
        main_.ajouter(CardBelote.HEART_KING);
        main_.ajouter(CardBelote.CLUB_JACK);
        main_.ajouter(CardBelote.CLUB_7);
        main_.ajouter(CardBelote.HEART_7);
        mains_.add(main_);
        main_ = new HandBelote();
        main_.ajouter(CardBelote.SPADE_7);
        main_.ajouter(CardBelote.SPADE_QUEEN);
        main_.ajouter(CardBelote.SPADE_8);
        main_.ajouter(CardBelote.DIAMOND_JACK);
        main_.ajouter(CardBelote.SPADE_10);
        mains_.add(main_);
        main_ = new HandBelote();
        main_.ajouter(CardBelote.DIAMOND_1);
        main_.ajouter(CardBelote.DIAMOND_KING);
        main_.ajouter(CardBelote.CLUB_QUEEN);
        main_.ajouter(CardBelote.SPADE_1);
        main_.ajouter(CardBelote.CLUB_8);
        mains_.add(main_);
        main_ = new HandBelote();
        main_.ajouter(CardBelote.DIAMOND_9);

        main_.ajouter(CardBelote.HEART_9);
        main_.ajouter(CardBelote.SPADE_KING);
        main_.ajouter(CardBelote.DIAMOND_7);

        main_.ajouter(CardBelote.CLUB_KING);
        main_.ajouter(CardBelote.HEART_QUEEN);

        main_.ajouter(CardBelote.CLUB_9);
        main_.ajouter(CardBelote.HEART_8);
        main_.ajouter(CardBelote.CLUB_10);

        main_.ajouter(CardBelote.DIAMOND_10);
        main_.ajouter(CardBelote.HEART_JACK);
        main_.ajouter(CardBelote.CLUB_1);
        mains_.add(main_);
        return new DealBelote(mains_,(byte)2);
    }
    @Test
    public void playableCards_WhileDiscarding1Test(){
        RulesBelote regles_=new RulesBelote();
        regles_.setGestionCoupePartenaire(BeloteTrumpPartner.UNDERTRUMP_ONLY);
        regles_.getCommon().setMixedCards(MixCardsChoice.NEVER);
        GameBelote game_ = new GameBelote(GameType.RANDOM,initializeHands(),regles_);
        //game_.resetNbPlisTotal();
        biddingTrumpSuit(game_,BidBelote.OTHER_SUIT,Suit.HEART);
        game_.setPliEnCours();
        assertEq(3, game_.getEntameur());
        HandBelote hand_ = game_.getDistribution().hand(game_.getEntameur());
        assertTrue(hand_.contient(CardBelote.DIAMOND_1));
        game_.getDistribution().jouer(game_.getEntameur(),CardBelote.DIAMOND_1);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.DIAMOND_1);
        assertNotSame(game_.couleurAtout(), game_.getPliEnCours().couleurDemandee());
        byte player_ = game_.playerAfter(game_.getEntameur());
        hand_ = game_.getDistribution().hand(player_);
        assertTrue(hand_.contient(CardBelote.DIAMOND_QUEEN));
        game_.getDistribution().jouer(player_,CardBelote.DIAMOND_QUEEN);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.DIAMOND_QUEEN);
        player_ = game_.playerAfter(player_);
        hand_ = game_.getDistribution().hand(player_);
        IdMap<Suit,HandBelote> suits_ = hand_.couleurs(game_.getBid());
        assertTrue(!suits_.getVal(game_.couleurAtout()).estVide());
        HandBelote playableCards_ = game_.playableCards(suits_);
        assertEq(hand_.total(),playableCards_.total());
        assertTrue(playableCards_.contientCartes(hand_));
    }
    @Test
    public void playableCards_WhileDiscarding2Test(){
        RulesBelote regles_=new RulesBelote();
        regles_.setGestionCoupePartenaire(BeloteTrumpPartner.NO_UNDERTRUMP_NO_OVERTRUMP);
        regles_.getCommon().setMixedCards(MixCardsChoice.NEVER);
        GameBelote game_ = new GameBelote(GameType.RANDOM,initializeHands(),regles_);
        //game_.resetNbPlisTotal();
        biddingTrumpSuit(game_,BidBelote.OTHER_SUIT,Suit.HEART);
        game_.setPliEnCours();
        assertEq(3, game_.getEntameur());
        HandBelote hand_ = game_.getDistribution().hand(game_.getEntameur());
        assertTrue(hand_.contient(CardBelote.DIAMOND_1));
        game_.getDistribution().jouer(game_.getEntameur(),CardBelote.DIAMOND_1);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.DIAMOND_1);
        assertNotSame(game_.couleurAtout(), game_.getPliEnCours().couleurDemandee());
        byte player_ = game_.playerAfter(game_.getEntameur());
        hand_ = game_.getDistribution().hand(player_);
        assertTrue(hand_.contient(CardBelote.DIAMOND_QUEEN));
        game_.getDistribution().jouer(player_,CardBelote.DIAMOND_QUEEN);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.DIAMOND_QUEEN);
        player_ = game_.playerAfter(player_);
        hand_ = game_.getDistribution().hand(player_);
        IdMap<Suit,HandBelote> suits_ = hand_.couleurs(game_.getBid());
        assertTrue(!suits_.getVal(game_.couleurAtout()).estVide());
        HandBelote playableCards_ = game_.playableCards(suits_);
        assertEq(hand_.total(),playableCards_.total());
        assertTrue(playableCards_.contientCartes(hand_));
    }
}