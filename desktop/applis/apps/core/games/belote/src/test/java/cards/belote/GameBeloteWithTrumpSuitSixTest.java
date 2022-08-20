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

public class GameBeloteWithTrumpSuitSixTest extends GameBeloteWithTrumpSuit {

    static DealBelote initializeHands() {
        CustList<HandBelote> mains_ = new CustList<HandBelote>();
        HandBelote main_ = new HandBelote();
        main_.ajouter(CardBelote.SPADE_1);
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
        main_.ajouter(CardBelote.DIAMOND_QUEEN);
        main_.ajouter(CardBelote.CLUB_8);
        mains_.add(main_);
        main_ = new HandBelote();
        main_.ajouter(CardBelote.DIAMOND_9);
        main_.ajouter(CardBelote.CLUB_9);
        main_.ajouter(CardBelote.HEART_8);
        main_.ajouter(CardBelote.DIAMOND_10);
        main_.ajouter(CardBelote.HEART_JACK);
        main_.ajouter(CardBelote.CLUB_1);
        main_.ajouter(CardBelote.HEART_9);
        main_.ajouter(CardBelote.SPADE_KING);
        main_.ajouter(CardBelote.DIAMOND_7);
        main_.ajouter(CardBelote.CLUB_KING);
        main_.ajouter(CardBelote.HEART_QUEEN);
        main_.ajouter(CardBelote.CLUB_10);
        mains_.add(main_);
        return new DealBelote(mains_,(byte)3);
    }
    @Test
    public void playableCards_WhileUnderTrumpingOnPartner1Test(){
        RulesBelote regles_=new RulesBelote();
        regles_.getCommon().setMixedCards(MixCardsChoice.NEVER);
        regles_.setGestionCoupePartenaire(BeloteTrumpPartner.UNDERTRUMP_ONLY);
        GameBelote game_ = new GameBelote(GameType.RANDOM,initializeHands(),regles_);
        //game_.resetNbPlisTotal();
        biddingTrumpSuit(game_,BidBelote.OTHER_SUIT,Suit.HEART);
        game_.setPliEnCours();
        assertEq(0, game_.getEntameur());
        HandBelote hand_ = game_.getDistribution().hand(game_.getEntameur());
        assertTrue(hand_.contient(CardBelote.SPADE_1));
        game_.getDistribution().jouer(game_.getEntameur(),CardBelote.SPADE_1);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.SPADE_1);
        assertNotSame(game_.couleurAtout(), game_.getPliEnCours().couleurDemandee());
        byte player_ = game_.playerAfter(game_.getEntameur());
        hand_ = game_.getDistribution().hand(player_);
        IdMap<Suit,HandBelote> suits_ = hand_.couleurs(game_.getBid());
        HandBelote trumps_ = suits_.getVal(game_.couleurAtout());
        HandBelote playableCards_ = game_.playableCards(suits_);
        assertEq(trumps_.total(),playableCards_.total());
        assertTrue(playableCards_.contientCartes(trumps_));
        assertTrue(hand_.contient(CardBelote.HEART_JACK));
        game_.getDistribution().jouer(player_,CardBelote.HEART_JACK);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.HEART_JACK);
        player_ = game_.playerAfter(player_);
//        assertTrue(game_.meme_equipe(player_, game_.getEntameur()));
        hand_ = game_.getDistribution().hand(player_);
        assertTrue(hand_.contient(CardBelote.SPADE_7));
        game_.getDistribution().jouer(player_,CardBelote.SPADE_7);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.SPADE_7);
        player_ = game_.playerAfter(player_);
        hand_ = game_.getDistribution().hand(player_);
        suits_ = hand_.couleurs(game_.getBid());
        trumps_ = suits_.getVal(game_.couleurAtout());
        playableCards_ = game_.playableCards(suits_);
        assertEq(trumps_.total(),playableCards_.total());
        assertTrue(playableCards_.contientCartes(trumps_));
    }
    @Test
    public void playableCards_WhileUnderTrumpingOnPartner2Test(){
        RulesBelote regles_=new RulesBelote();
        regles_.getCommon().setMixedCards(MixCardsChoice.NEVER);
        regles_.setGestionCoupePartenaire(BeloteTrumpPartner.UNDERTRUMP_OVERTRUMP);
        GameBelote game_ = new GameBelote(GameType.RANDOM,initializeHands(),regles_);
        //game_.resetNbPlisTotal();
        biddingTrumpSuit(game_,BidBelote.OTHER_SUIT,Suit.HEART);
        game_.setPliEnCours();
        assertEq(0, game_.getEntameur());
        HandBelote hand_ = game_.getDistribution().hand(game_.getEntameur());
        assertTrue(hand_.contient(CardBelote.SPADE_1));
        game_.getDistribution().jouer(game_.getEntameur(),CardBelote.SPADE_1);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.SPADE_1);
        assertNotSame(game_.couleurAtout(), game_.getPliEnCours().couleurDemandee());
        byte player_ = game_.playerAfter(game_.getEntameur());
        hand_ = game_.getDistribution().hand(player_);
        IdMap<Suit,HandBelote> suits_ = hand_.couleurs(game_.getBid());
        HandBelote trumps_ = suits_.getVal(game_.couleurAtout());
        HandBelote playableCards_ = game_.playableCards(suits_);
        assertEq(trumps_.total(),playableCards_.total());
        assertTrue(playableCards_.contientCartes(trumps_));
        assertTrue(hand_.contient(CardBelote.HEART_JACK));
        game_.getDistribution().jouer(player_,CardBelote.HEART_JACK);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.HEART_JACK);
        player_ = game_.playerAfter(player_);
//        assertTrue(game_.meme_equipe(player_, game_.getEntameur()));
        hand_ = game_.getDistribution().hand(player_);
        assertTrue(hand_.contient(CardBelote.SPADE_7));
        game_.getDistribution().jouer(player_,CardBelote.SPADE_7);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.SPADE_7);
        player_ = game_.playerAfter(player_);
        hand_ = game_.getDistribution().hand(player_);
        suits_ = hand_.couleurs(game_.getBid());
        trumps_ = suits_.getVal(game_.couleurAtout());
        playableCards_ = game_.playableCards(suits_);
        assertEq(trumps_.total(),playableCards_.total());
        assertTrue(playableCards_.contientCartes(trumps_));
    }
}