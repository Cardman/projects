package cards.belote;

import code.util.CustList;
import org.junit.Test;

import cards.belote.enumerations.BidBelote;
import cards.belote.enumerations.CardBelote;
import cards.consts.GameType;
import cards.consts.MixCardsChoice;
import cards.consts.Suit;
import code.util.IdMap;

public class GameBeloteWithTrumpSuitTwoTest extends GameBeloteWithTrumpSuit {

    public GameBelote initialize() {
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
        main_.ajouter(CardBelote.CLUB_KING);
        main_.ajouter(CardBelote.HEART_QUEEN);
        main_.ajouter(CardBelote.CLUB_9);
        main_.ajouter(CardBelote.HEART_8);
        main_.ajouter(CardBelote.CLUB_10);
        main_.ajouter(CardBelote.DIAMOND_10);
        main_.ajouter(CardBelote.HEART_JACK);
        main_.ajouter(CardBelote.CLUB_1);
        main_.ajouter(CardBelote.HEART_9);
        main_.ajouter(CardBelote.SPADE_KING);
        main_.ajouter(CardBelote.DIAMOND_7);
        mains_.add(main_);
        DealBelote donne_ = new DealBelote(mains_,(byte)3);
        RulesBelote regles_=new RulesBelote();
        regles_.getCommon().setMixedCards(MixCardsChoice.NEVER);
        return new GameBelote(GameType.RANDOM,donne_,regles_);
    }

    /**Discarding a card while neither following nor trumping a suit*/
    @Test
    public void playableCards_WhileDiscarding1Test() {
        GameBelote game_ = initialize();
        biddingTrumpSuit(game_,BidBelote.SUIT,Suit.DIAMOND);
        game_.setPliEnCours();
        assertEq(0, game_.getEntameur());
        HandBelote hand_ = game_.getDistribution().hand(game_.getEntameur());
        assertTrue(hand_.contient(CardBelote.SPADE_9));
        game_.getDistribution().jouer(game_.getEntameur(),CardBelote.SPADE_9);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.SPADE_9);
        assertNotSame(game_.couleurAtout(), game_.getPliEnCours().couleurDemandee());
        byte player_ = game_.playerAfter(game_.getEntameur());
        //assertTrue(!game_.meme_equipe(game_.getEntameur(),player_));
        hand_ = game_.getDistribution().hand(player_);
        IdMap<Suit,HandBelote> suits_ = hand_.couleurs(game_.getBid());
        assertTrue(suits_.getVal(game_.couleurAtout()).estVide());
        HandBelote playableCards_ = game_.playableCards(suits_);
        assertEq(hand_.total(),playableCards_.total());
        assertTrue(playableCards_.contientCartes(hand_));
    }

    @Test
    public void playableCards_WhileDiscardingWhileTrumpSuit2Test() {
        GameBelote game_ = initialize();
        biddingTrumpSuit(game_,BidBelote.SUIT,Suit.DIAMOND);
        game_.setPliEnCours();
        assertEq(0, game_.getEntameur());
        HandBelote hand_ = game_.getDistribution().hand(game_.getEntameur());
        assertTrue(hand_.contient(CardBelote.DIAMOND_QUEEN));
        game_.getDistribution().jouer(game_.getEntameur(),CardBelote.DIAMOND_QUEEN);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.DIAMOND_QUEEN);
        assertEq(game_.couleurAtout(), game_.getPliEnCours().couleurDemandee());
        byte player_ = game_.playerAfter(game_.getEntameur());
        //assertTrue(!game_.meme_equipe(game_.getEntameur(),player_));
        hand_ = game_.getDistribution().hand(player_);
        IdMap<Suit,HandBelote> suits_ = hand_.couleurs(game_.getBid());
        assertTrue(suits_.getVal(game_.couleurAtout()).estVide());
        HandBelote playableCards_ = game_.playableCards(suits_);
        assertEq(hand_.total(),playableCards_.total());
        assertTrue(playableCards_.contientCartes(hand_));
    }
}
