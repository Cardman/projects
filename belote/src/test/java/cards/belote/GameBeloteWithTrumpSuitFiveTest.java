package cards.belote;
import static cards.belote.EquallableBeloteUtil.assertEq;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import cards.belote.enumerations.BeloteTrumpPartner;
import cards.belote.enumerations.BidBelote;
import cards.belote.enumerations.CardBelote;
import cards.consts.GameType;
import cards.consts.MixCardsChoice;
import cards.consts.Suit;
import code.util.EnumMap;
import code.util.EqList;

public class GameBeloteWithTrumpSuitFiveTest extends GameBeloteTest {

    static DealBelote initializeHands() {
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
        return new DealBelote(mains_,(byte)2);
    }    @Test
    public void playableCards_WhileDiscardingInsteadOfTrumpingOnPartner1Test(){
        RulesBelote regles_=new RulesBelote();
        regles_.setCartesBattues(MixCardsChoice.NEVER);
        regles_.setGestionCoupePartenaire(BeloteTrumpPartner.OVERTRUMP_ONLY);
        game = new GameBelote(GameType.RANDOM,initializeHands(),regles_);
        //game.resetNbPlisTotal();
        biddingTrumpSuit(BidBelote.OTHER_SUIT,Suit.HEART);
        game.setPliEnCours();
        assertEq(3,game.getEntameur());
        HandBelote hand_ = game.getDistribution().main(game.getEntameur());
        assertTrue(hand_.contient(CardBelote.SPADE_1));
        game.getDistribution().jouer(game.getEntameur(),CardBelote.SPADE_1);
        game.ajouterUneCarteDansPliEnCours(CardBelote.SPADE_1);
        assertNotSame(game.couleurAtout(), game.getPliEnCours().couleurDemandee());
        byte player_ = game.playerAfter(game.getEntameur());
        hand_ = game.getDistribution().main(player_);
        assertTrue(hand_.contient(CardBelote.SPADE_9));
        game.getDistribution().jouer(player_,CardBelote.SPADE_9);
        game.ajouterUneCarteDansPliEnCours(CardBelote.SPADE_9);
        player_ = game.playerAfter(player_);
        assertTrue(game.memeEquipe(player_, game.getEntameur()));
        hand_ = game.getDistribution().main(player_);
        EnumMap<Suit,HandBelote> suits_ = hand_.couleurs(game.getContrat());
        HandBelote trumps_ = suits_.getVal(game.couleurAtout());
        HandBelote playableCards_ = game.playableCards(suits_);
        assertEq(trumps_.total(),playableCards_.total());
        assertTrue(playableCards_.display(),playableCards_.contientCartes(trumps_));
    }
    @Test
    public void playableCards_WhileDiscardingInsteadOfTrumpingOnPartner2Test(){
        RulesBelote regles_=new RulesBelote();
        regles_.setCartesBattues(MixCardsChoice.NEVER);
        regles_.setGestionCoupePartenaire(BeloteTrumpPartner.UNDERTRUMP_OVERTRUMP);
        game = new GameBelote(GameType.RANDOM,initializeHands(),regles_);
        //game.resetNbPlisTotal();
        biddingTrumpSuit(BidBelote.OTHER_SUIT,Suit.HEART);
        game.setPliEnCours();
        assertEq(3,game.getEntameur());
        HandBelote hand_ = game.getDistribution().main(game.getEntameur());
        assertTrue(hand_.contient(CardBelote.SPADE_1));
        game.getDistribution().jouer(game.getEntameur(),CardBelote.SPADE_1);
        game.ajouterUneCarteDansPliEnCours(CardBelote.SPADE_1);
        assertNotSame(game.couleurAtout(), game.getPliEnCours().couleurDemandee());
        byte player_ = game.playerAfter(game.getEntameur());
        hand_ = game.getDistribution().main(player_);
        assertTrue(hand_.contient(CardBelote.SPADE_9));
        game.getDistribution().jouer(player_,CardBelote.SPADE_9);
        game.ajouterUneCarteDansPliEnCours(CardBelote.SPADE_9);
        player_ = game.playerAfter(player_);
        assertTrue(game.memeEquipe(player_, game.getEntameur()));
        hand_ = game.getDistribution().main(player_);
        EnumMap<Suit,HandBelote> suits_ = hand_.couleurs(game.getContrat());
        HandBelote trumps_ = suits_.getVal(game.couleurAtout());
        HandBelote playableCards_ = game.playableCards(suits_);
        assertEq(trumps_.total(),playableCards_.total());
        assertTrue(playableCards_.display(),playableCards_.contientCartes(trumps_));
    }
}