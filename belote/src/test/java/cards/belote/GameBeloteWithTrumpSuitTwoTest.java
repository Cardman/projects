package cards.belote;
import static cards.belote.EquallableBeloteUtil.assertEq;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import cards.belote.enumerations.BidBelote;
import cards.belote.enumerations.CardBelote;
import cards.consts.GameType;
import cards.consts.MixCardsChoice;
import cards.consts.Suit;
import code.util.EnumMap;
import code.util.EqList;

public class GameBeloteWithTrumpSuitTwoTest extends GameBeloteTest{

    @Before
    public void initialize() {
        EqList<HandBelote> mains_ = new EqList<HandBelote>();
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
        regles_.setCartesBattues(MixCardsChoice.NEVER);
        game = new GameBelote(GameType.RANDOM,donne_,regles_);
        //game.resetNbPlisTotal();
    }

    /**Discarding a card while neither following nor trumping a suit*/
    @Test
    public void playableCards_WhileDiscarding1Test() {
        biddingTrumpSuit(BidBelote.SUIT,Suit.DIAMOND);
        game.setPliEnCours();
        assertEq(0,game.getEntameur());
        HandBelote hand_ = game.getDistribution().main(game.getEntameur());
        assertTrue(hand_.contient(CardBelote.SPADE_9));
        game.getDistribution().jouer(game.getEntameur(),CardBelote.SPADE_9);
        game.ajouterUneCarteDansPliEnCours(CardBelote.SPADE_9);
        assertNotEquals(game.couleurAtout(), game.getPliEnCours().couleurDemandee());
        byte player_ = game.playerAfter(game.getEntameur());
        //assertTrue(!game.meme_equipe(game.getEntameur(),player_));
        hand_ = game.getDistribution().main(player_);
        EnumMap<Suit,HandBelote> suits_ = hand_.couleurs(game.getContrat());
        assertTrue(suits_.getVal(game.couleurAtout()).estVide());
        HandBelote playableCards_ = game.playableCards(suits_);
        assertEq(hand_.total(),playableCards_.total());
        assertTrue(playableCards_.display(),playableCards_.contientCartes(hand_));
    }

    @Test
    public void playableCards_WhileDiscardingWhileTrumpSuit2Test() {
        biddingTrumpSuit(BidBelote.SUIT,Suit.DIAMOND);
        game.setPliEnCours();
        assertEq(0,game.getEntameur());
        HandBelote hand_ = game.getDistribution().main(game.getEntameur());
        assertTrue(hand_.contient(CardBelote.DIAMOND_QUEEN));
        game.getDistribution().jouer(game.getEntameur(),CardBelote.DIAMOND_QUEEN);
        game.ajouterUneCarteDansPliEnCours(CardBelote.DIAMOND_QUEEN);
        assertEq(game.couleurAtout(), game.getPliEnCours().couleurDemandee());
        byte player_ = game.playerAfter(game.getEntameur());
        //assertTrue(!game.meme_equipe(game.getEntameur(),player_));
        hand_ = game.getDistribution().main(player_);
        EnumMap<Suit,HandBelote> suits_ = hand_.couleurs(game.getContrat());
        assertTrue(suits_.getVal(game.couleurAtout()).estVide());
        HandBelote playableCards_ = game.playableCards(suits_);
        assertEq(hand_.total(),playableCards_.total());
        assertTrue(playableCards_.display(),playableCards_.contientCartes(hand_));
    }
}
