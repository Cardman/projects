package cards.belote;
import static junitparams.JUnitParamsRunner.$;
import static cards.belote.EquallableBeloteUtil.assertEq;
import static org.junit.Assert.assertTrue;
import junitparams.Parameters;

import org.junit.Test;

import code.util.CustList;
import code.util.EnumList;
import code.util.EnumMap;
import code.util.EqList;
import cards.belote.enumerations.BidBelote;
import cards.belote.enumerations.CardBelote;
import cards.belote.enumerations.DealingBelote;
import cards.consts.GameType;
import cards.consts.MixCardsChoice;
import cards.consts.Suit;

@SuppressWarnings("static-method")
public class GameBeloteBiddingTest extends GameBeloteTest {

    static Object[] bidsWithoutTrumpSuit(){
        Object[] bids_ = new Object[3];
        EnumList<BidBelote> allowedBids_ = new EnumList<BidBelote>();
        allowedBids_.add(BidBelote.NO_TRUMP);
        bids_[0] = $(allowedBids_);
        allowedBids_ = new EnumList<BidBelote>();
        allowedBids_.add(BidBelote.ALL_TRUMP);
        bids_[1] = $(allowedBids_);
        allowedBids_ = new EnumList<BidBelote>();
        allowedBids_.add(BidBelote.NO_TRUMP);
        allowedBids_.add(BidBelote.ALL_TRUMP);
        bids_[2] = $(allowedBids_);
        return bids_;
    }

    static Object[] bidsWithoutTrumpSuitAllTrump(){
        Object[] bids_ = new Object[2];
        EnumList<BidBelote> allowedBids_ = new EnumList<BidBelote>();
        allowedBids_.add(BidBelote.ALL_TRUMP);
        bids_[0] = $(allowedBids_);
        allowedBids_ = new EnumList<BidBelote>();
        allowedBids_.add(BidBelote.NO_TRUMP);
        allowedBids_.add(BidBelote.ALL_TRUMP);
        bids_[1] = $(allowedBids_);
        return bids_;
    }

    static Object[] singleBidWithoutTrumpSuit(){
        Object[] bids_ = new Object[2];
        bids_[0] = $(BidBelote.NO_TRUMP);
        bids_[1] = $(BidBelote.ALL_TRUMP);
        return bids_;
    }

    static DealBelote initializeHands() {
        EqList<HandBelote> mains_ = new EqList<HandBelote>();
        HandBelote main_ = new HandBelote();
        main_.ajouter(CardBelote.DIAMOND_QUEEN);
        main_.ajouter(CardBelote.HEART_10);
        main_.ajouter(CardBelote.SPADE_9);
        main_.ajouter(CardBelote.CLUB_JACK);
        main_.ajouter(CardBelote.DIAMOND_8);
        mains_.add(main_);
        main_ = new HandBelote();
        main_.ajouter(CardBelote.HEART_1);
        main_.ajouter(CardBelote.HEART_KING);
        main_.ajouter(CardBelote.SPADE_JACK);
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
        return new DealBelote(mains_,(byte)3);

    }

    static RulesBelote initializeDefaultRules() {
        RulesBelote regles_=new RulesBelote();
        regles_.setCartesBattues(MixCardsChoice.NEVER);
        EnumMap<BidBelote,Boolean> contrats_ = new EnumMap<BidBelote,Boolean>();
        for (BidBelote b: regles_.getEncheresAutorisees().getKeys()) {
            contrats_.put(b, b.getToujoursPossibleAnnoncer());
        }
        if(!BidBelote.NO_TRUMP.getToujoursPossibleAnnoncer()) {
            contrats_.put(BidBelote.NO_TRUMP, false);
        }
        if(!BidBelote.ALL_TRUMP.getToujoursPossibleAnnoncer()) {
            contrats_.put(BidBelote.ALL_TRUMP, false);
        }
        regles_.setEncheresAutorisees(contrats_);
        return regles_;
    }

    static RulesBelote initializeRulesWithBids(EnumList<BidBelote> _bids) {
        RulesBelote regles_=new RulesBelote();
        regles_.setCartesBattues(MixCardsChoice.NEVER);
        EnumMap<BidBelote,Boolean> contrats_ = new EnumMap<BidBelote,Boolean>();
        for (BidBelote b: regles_.getEncheresAutorisees().getKeys()) {
            contrats_.put(b, b.getToujoursPossibleAnnoncer());
        }
        if(!BidBelote.NO_TRUMP.getToujoursPossibleAnnoncer()) {
            contrats_.put(BidBelote.NO_TRUMP, false);
        }
        if(!BidBelote.ALL_TRUMP.getToujoursPossibleAnnoncer()) {
            contrats_.put(BidBelote.ALL_TRUMP, false);
        }
        for (BidBelote b: _bids) {
            contrats_.put(b,true);
        }
        regles_.setEncheresAutorisees(contrats_);
        return regles_;
    }

    static RulesBelote initializeRulesWithBidPoints(boolean _addOverBid) {
        RulesBelote regles_=new RulesBelote();
        regles_.setCartesBattues(MixCardsChoice.NEVER);
        regles_.setRepartition(DealingBelote.COINCHE_2_VS_2);
        EnumMap<BidBelote,Boolean> contrats_ = new EnumMap<BidBelote,Boolean>();
        for (BidBelote b: regles_.getEncheresAutorisees().getKeys()) {
            contrats_.put(b, b.getToujoursPossibleAnnoncer());
        }
        if(!BidBelote.NO_TRUMP.getToujoursPossibleAnnoncer()) {
            contrats_.put(BidBelote.NO_TRUMP, false);
        }
        if(!BidBelote.ALL_TRUMP.getToujoursPossibleAnnoncer()) {
            contrats_.put(BidBelote.ALL_TRUMP, false);
        }
        if (_addOverBid) {
            for (BidBelote b: regles_.getEncheresAutorisees().getKeys()) {
                contrats_.put(b,true);
            }
        }
        regles_.setEncheresAutorisees(contrats_);
        return regles_;
    }

    @Test
    public void allowedBids_AtFirstRoundBidsInitialize1Test() {
        RulesBelote regles_=initializeDefaultRules();
        game = new GameBelote(GameType.RANDOM,initializeHands(),regles_);
        //game.resetNbPlisTotal();
        EqList<BidBeloteSuit> bids_ = game.allowedBids();
        EqList<BidBeloteSuit> expected_ = new EqList<BidBeloteSuit>();
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setEnchere(BidBelote.FOLD);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setEnchere(BidBelote.SUIT);
        b_.setCouleur(Suit.DIAMOND);
        expected_.add(b_);
        asserting(expected_,bids_);
    }
    @Test
    public void allowedBids_AtFirstRoundBidsInitializeWithoutTakingBid2Test() {
        RulesBelote regles_=initializeDefaultRules();
        game = new GameBelote(GameType.RANDOM,initializeHands(),regles_);
        //game.resetNbPlisTotal();
        byte player_ = game.playerAfter(game.getDistribution().getDonneur());
        assertTrue(game.keepBidding());
        BidBeloteSuit contratTmp_ = new BidBeloteSuit();
        contratTmp_.setEnchere(BidBelote.FOLD);
        game.ajouterContrat(contratTmp_,player_);
        player_ = game.playerAfter(player_);
        assertTrue(game.keepBidding());
        contratTmp_ = new BidBeloteSuit();
        contratTmp_.setEnchere(BidBelote.FOLD);
        game.ajouterContrat(contratTmp_,player_);
        player_ = game.playerAfter(player_);
        assertTrue(game.keepBidding());
        contratTmp_ = new BidBeloteSuit();
        contratTmp_.setEnchere(BidBelote.FOLD);
        game.ajouterContrat(contratTmp_,player_);
        player_ = game.playerAfter(player_);
        assertTrue(game.keepBidding());
        EqList<BidBeloteSuit> bids_ = game.allowedBids();
        EqList<BidBeloteSuit> expected_ = new EqList<BidBeloteSuit>();
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setEnchere(BidBelote.FOLD);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setEnchere(BidBelote.SUIT);
        b_.setCouleur(Suit.DIAMOND);
        expected_.add(b_);
        asserting(expected_,bids_);
    }
    @Test
    public void allowedBids_AtFirstRoundBidsInitializeByTakingBid3Test() {
        RulesBelote regles_=initializeDefaultRules();
        game = new GameBelote(GameType.RANDOM,initializeHands(),regles_);
        //game.resetNbPlisTotal();
        byte player_ = game.playerAfter(game.getDistribution().getDonneur());
        assertTrue(game.keepBidding());
        BidBeloteSuit contratTmp_ = new BidBeloteSuit();
        contratTmp_.setEnchere(BidBelote.FOLD);
        game.ajouterContrat(contratTmp_,player_);
        player_ = game.playerAfter(player_);
        assertTrue(game.keepBidding());
        contratTmp_ = new BidBeloteSuit();
        contratTmp_.setEnchere(BidBelote.FOLD);
        game.ajouterContrat(contratTmp_,player_);
        player_ = game.playerAfter(player_);
        assertTrue(game.keepBidding());
        contratTmp_ = new BidBeloteSuit();
        contratTmp_.setEnchere(BidBelote.SUIT);
        contratTmp_.setCouleur(Suit.DIAMOND);
        game.ajouterContrat(contratTmp_,player_);
        assertTrue(!game.keepBidding());
        //game.setContrat(contrat_tmp);
        EqList<BidBeloteSuit> bids_ = game.allowedBids();
        EqList<BidBeloteSuit> expected_ = new EqList<BidBeloteSuit>();
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setEnchere(BidBelote.FOLD);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setEnchere(BidBelote.SUIT);
        b_.setCouleur(Suit.DIAMOND);
        expected_.add(b_);
        asserting(expected_,bids_);
        assertTrue(expected_.get(0).estDemandable(game.getContrat()));
        assertTrue(!expected_.get(1).estDemandable(game.getContrat()));
    }
    @Test
    public void allowedBids_AtSecondRoundBidsInitialize4Test() {
        RulesBelote regles_=initializeDefaultRules();
        game = new GameBelote(GameType.RANDOM,initializeHands(),regles_);
        //game.resetNbPlisTotal();
        game.finEncherePremierTour();
        EqList<BidBeloteSuit> bids_ = game.allowedBids();
        EqList<BidBeloteSuit> expected_ = new EqList<BidBeloteSuit>();
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setEnchere(BidBelote.FOLD);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setEnchere(BidBelote.OTHER_SUIT);
        b_.setCouleur(Suit.HEART);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setEnchere(BidBelote.OTHER_SUIT);
        b_.setCouleur(Suit.SPADE);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setEnchere(BidBelote.OTHER_SUIT);
        b_.setCouleur(Suit.CLUB);
        expected_.add(b_);
        assertTrue(BidBeloteSuit.equalsSet(expected_,bids_));
        //max_contrat()
    }
    @Test
    public void allowedBids_AtSecondRoundBidsInitializeWithoutTakingBid5Test() {
        RulesBelote regles_=initializeDefaultRules();
        game = new GameBelote(GameType.RANDOM,initializeHands(),regles_);
        //game.resetNbPlisTotal();
        byte player_ = game.playerAfter(game.getDistribution().getDonneur());
        assertTrue(game.keepBidding());
        BidBeloteSuit contratTmp_ = new BidBeloteSuit();
        contratTmp_.setEnchere(BidBelote.FOLD);
        game.ajouterContrat(contratTmp_,player_);
        player_ = game.playerAfter(player_);
        assertTrue(game.keepBidding());
        contratTmp_ = new BidBeloteSuit();
        contratTmp_.setEnchere(BidBelote.FOLD);
        game.ajouterContrat(contratTmp_,player_);
        player_ = game.playerAfter(player_);
        assertTrue(game.keepBidding());
        contratTmp_ = new BidBeloteSuit();
        contratTmp_.setEnchere(BidBelote.FOLD);
        game.ajouterContrat(contratTmp_,player_);
        player_ = game.playerAfter(player_);
        assertTrue(game.keepBidding());
        contratTmp_ = new BidBeloteSuit();
        contratTmp_.setEnchere(BidBelote.FOLD);
        game.ajouterContrat(contratTmp_,player_);
        game.finEncherePremierTour();
        player_ = game.playerAfter(player_);
        assertTrue(game.keepBidding());
        contratTmp_ = new BidBeloteSuit();
        contratTmp_.setEnchere(BidBelote.FOLD);
        game.ajouterContrat(contratTmp_,player_);
        assertTrue(game.keepBidding());
        EqList<BidBeloteSuit> bids_ = game.allowedBids();
        EqList<BidBeloteSuit> expected_ = new EqList<BidBeloteSuit>();
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setEnchere(BidBelote.FOLD);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setEnchere(BidBelote.OTHER_SUIT);
        b_.setCouleur(Suit.HEART);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setEnchere(BidBelote.OTHER_SUIT);
        b_.setCouleur(Suit.SPADE);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setEnchere(BidBelote.OTHER_SUIT);
        b_.setCouleur(Suit.CLUB);
        expected_.add(b_);
        assertTrue(BidBeloteSuit.equalsSet(expected_,bids_));
    }
    @Test
    public void allowedBids_AtSecondRoundBidsInitializeByTakingBid6Test() {
        RulesBelote regles_=initializeDefaultRules();
        game = new GameBelote(GameType.RANDOM,initializeHands(),regles_);
        //game.resetNbPlisTotal();
        byte player_ = game.playerAfter(game.getDistribution().getDonneur());
        assertTrue(game.keepBidding());
        BidBeloteSuit contratTmp_ = new BidBeloteSuit();
        contratTmp_.setEnchere(BidBelote.FOLD);
        game.ajouterContrat(contratTmp_,player_);
        player_ = game.playerAfter(player_);
        assertTrue(game.keepBidding());
        contratTmp_ = new BidBeloteSuit();
        contratTmp_.setEnchere(BidBelote.FOLD);
        game.ajouterContrat(contratTmp_,player_);
        player_ = game.playerAfter(player_);
        assertTrue(game.keepBidding());
        contratTmp_ = new BidBeloteSuit();
        contratTmp_.setEnchere(BidBelote.FOLD);
        game.ajouterContrat(contratTmp_,player_);
        player_ = game.playerAfter(player_);
        assertTrue(game.keepBidding());
        contratTmp_ = new BidBeloteSuit();
        contratTmp_.setEnchere(BidBelote.FOLD);
        game.ajouterContrat(contratTmp_,player_);
        game.finEncherePremierTour();
        player_ = game.playerAfter(player_);
        assertTrue(game.keepBidding());
        contratTmp_ = new BidBeloteSuit();
        contratTmp_.setEnchere(BidBelote.FOLD);
        game.ajouterContrat(contratTmp_,player_);
        player_ = game.playerAfter(player_);
        assertTrue(game.keepBidding());
        contratTmp_ = new BidBeloteSuit();
        contratTmp_.setEnchere(BidBelote.OTHER_SUIT);
        contratTmp_.setCouleur(Suit.HEART);
        game.ajouterContrat(contratTmp_,player_);
        assertTrue(!game.keepBidding());
        //game.setContrat(contrat_tmp);
        EqList<BidBeloteSuit> bids_ = game.allowedBids();
        EqList<BidBeloteSuit> expected_ = new EqList<BidBeloteSuit>();
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setEnchere(BidBelote.FOLD);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setEnchere(BidBelote.OTHER_SUIT);
        b_.setCouleur(Suit.HEART);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setEnchere(BidBelote.OTHER_SUIT);
        b_.setCouleur(Suit.SPADE);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setEnchere(BidBelote.OTHER_SUIT);
        b_.setCouleur(Suit.CLUB);
        expected_.add(b_);
        asserting(expected_,bids_);
        assertTrue(expected_.get(0).estDemandable(game.getContrat()));
        assertTrue(!expected_.get(1).estDemandable(game.getContrat()));
        assertTrue(!expected_.get(2).estDemandable(game.getContrat()));
        assertTrue(!expected_.get(3).estDemandable(game.getContrat()));
    }
    @Test
    public void allowedBids_AtSecondRoundBidsInitializePassing7Test() {
        RulesBelote regles_=initializeDefaultRules();
        game = new GameBelote(GameType.RANDOM,initializeHands(),regles_);
        //game.resetNbPlisTotal();
        byte player_ = game.playerAfter(game.getDistribution().getDonneur());
        assertTrue(game.keepBidding());
        BidBeloteSuit contratTmp_ = new BidBeloteSuit();
        contratTmp_.setEnchere(BidBelote.FOLD);
        game.ajouterContrat(contratTmp_,player_);
        player_ = game.playerAfter(player_);
        assertTrue(game.keepBidding());
        contratTmp_ = new BidBeloteSuit();
        contratTmp_.setEnchere(BidBelote.FOLD);
        game.ajouterContrat(contratTmp_,player_);
        player_ = game.playerAfter(player_);
        assertTrue(game.keepBidding());
        contratTmp_ = new BidBeloteSuit();
        contratTmp_.setEnchere(BidBelote.FOLD);
        game.ajouterContrat(contratTmp_,player_);
        player_ = game.playerAfter(player_);
        assertTrue(game.keepBidding());
        contratTmp_ = new BidBeloteSuit();
        contratTmp_.setEnchere(BidBelote.FOLD);
        game.ajouterContrat(contratTmp_,player_);
        game.finEncherePremierTour();
        contratTmp_ = new BidBeloteSuit();
        contratTmp_.setEnchere(BidBelote.FOLD);
        game.ajouterContrat(contratTmp_,player_);
        player_ = game.playerAfter(player_);
        assertTrue(game.keepBidding());
        contratTmp_ = new BidBeloteSuit();
        contratTmp_.setEnchere(BidBelote.FOLD);
        game.ajouterContrat(contratTmp_,player_);
        player_ = game.playerAfter(player_);
        assertTrue(game.keepBidding());
        contratTmp_ = new BidBeloteSuit();
        contratTmp_.setEnchere(BidBelote.FOLD);
        game.ajouterContrat(contratTmp_,player_);
        player_ = game.playerAfter(player_);
        assertTrue(game.keepBidding());
        contratTmp_ = new BidBeloteSuit();
        contratTmp_.setEnchere(BidBelote.FOLD);
        game.ajouterContrat(contratTmp_,player_);
        assertTrue(!game.keepBidding());
    }
    @Test
    public void maximumBid_AtFirstRound1Test() {
        RulesBelote regles_=initializeDefaultRules();
        game = new GameBelote(GameType.RANDOM,initializeHands(),regles_);
        //game.resetNbPlisTotal();
        EqList<BidBeloteSuit> maximunBidsSuits_ = game.maximumBid();
        EqList<BidBeloteSuit> expected_ = new EqList<BidBeloteSuit>();
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setEnchere(BidBelote.SUIT);
        b_.setCouleur(Suit.DIAMOND);
        expected_.add(b_);
        asserting(expected_,maximunBidsSuits_);
    }
    @Test
    public void maximumBid_AtSecondRound2Test() {
        RulesBelote regles_=initializeDefaultRules();
        game = new GameBelote(GameType.RANDOM,initializeHands(),regles_);
        //game.resetNbPlisTotal();
        game.finEncherePremierTour();
        EqList<BidBeloteSuit> maximunBidsSuits_ = game.maximumBid();
        EqList<BidBeloteSuit> expected_ = new EqList<BidBeloteSuit>();
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setEnchere(BidBelote.OTHER_SUIT);
        b_.setCouleur(Suit.HEART);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setEnchere(BidBelote.OTHER_SUIT);
        b_.setCouleur(Suit.SPADE);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setEnchere(BidBelote.OTHER_SUIT);
        b_.setCouleur(Suit.CLUB);
        expected_.add(b_);
        assertTrue(BidBeloteSuit.equalsSet(expected_,maximunBidsSuits_));
    }

    @Parameters(method = "singleBidWithoutTrumpSuit",source =GameBeloteBiddingTest.class)
    @Test
    public void allowedBids_AtFirstRoundBidsInitialize8Test(BidBelote _bid) {
        RulesBelote regles_=initializeRulesWithBids(new EnumList<BidBelote>(_bid));
        game = new GameBelote(GameType.RANDOM,initializeHands(),regles_);
        //game.resetNbPlisTotal();
        EqList<BidBeloteSuit> bids_ = game.allowedBids();
        EqList<BidBeloteSuit> expected_ = new EqList<BidBeloteSuit>();
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setEnchere(BidBelote.FOLD);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setEnchere(BidBelote.SUIT);
        b_.setCouleur(Suit.DIAMOND);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setEnchere(_bid);
        expected_.add(b_);
        asserting(expected_,bids_);
    }

    @Test
    public void allowedBids_AtFirstRoundBidsInitialize9Test() {
        RulesBelote regles_=initializeRulesWithBids(new EnumList<BidBelote>(BidBelote.NO_TRUMP, BidBelote.ALL_TRUMP));
        game = new GameBelote(GameType.RANDOM,initializeHands(),regles_);
        //game.resetNbPlisTotal();
        EqList<BidBeloteSuit> bids_ = game.allowedBids();
        EqList<BidBeloteSuit> expected_ = new EqList<BidBeloteSuit>();
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setEnchere(BidBelote.FOLD);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setEnchere(BidBelote.SUIT);
        b_.setCouleur(Suit.DIAMOND);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setEnchere(BidBelote.NO_TRUMP);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setEnchere(BidBelote.ALL_TRUMP);
        expected_.add(b_);
        asserting(expected_,bids_);
    }
    @Parameters(method = "bidsWithoutTrumpSuit",source =GameBeloteBiddingTest.class)
    @Test
    public void allowedBids_AtFirstRoundBidsInitializeWithoutTakingBid10Test(EnumList<BidBelote> _bidsWithoutTrumpSuit) {
        RulesBelote regles_=initializeRulesWithBids(_bidsWithoutTrumpSuit);
        game = new GameBelote(GameType.RANDOM,initializeHands(),regles_);
        //game.resetNbPlisTotal();
        byte player_ = game.playerAfter(game.getDistribution().getDonneur());
        assertTrue(game.keepBidding());
        BidBeloteSuit contratTmp_ = new BidBeloteSuit();
        contratTmp_.setEnchere(BidBelote.FOLD);
        game.ajouterContrat(contratTmp_,player_);
        player_ = game.playerAfter(player_);
        assertTrue(game.keepBidding());
        contratTmp_ = new BidBeloteSuit();
        contratTmp_.setEnchere(BidBelote.FOLD);
        game.ajouterContrat(contratTmp_,player_);
        player_ = game.playerAfter(player_);
        assertTrue(game.keepBidding());
        contratTmp_ = new BidBeloteSuit();
        contratTmp_.setEnchere(BidBelote.FOLD);
        game.ajouterContrat(contratTmp_,player_);
        player_ = game.playerAfter(player_);
        assertTrue(game.keepBidding());
        EqList<BidBeloteSuit> bids_ = game.allowedBids();
        EqList<BidBeloteSuit> expected_ = new EqList<BidBeloteSuit>();
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setEnchere(BidBelote.FOLD);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setEnchere(BidBelote.SUIT);
        b_.setCouleur(Suit.DIAMOND);
        expected_.add(b_);
        expected_.addAllElts(toRealBid(_bidsWithoutTrumpSuit));
        asserting(expected_,bids_);
    }
    @Parameters(method = "bidsWithoutTrumpSuit",source =GameBeloteBiddingTest.class)
    @Test
    public void allowedBids_AtFirstRoundBidsInitializeByTakingBid11Test(EnumList<BidBelote> _bidsWithoutTrumpSuit) {
        RulesBelote regles_=initializeRulesWithBids(_bidsWithoutTrumpSuit);
        game = new GameBelote(GameType.RANDOM,initializeHands(),regles_);
        //game.resetNbPlisTotal();
        byte player_ = game.playerAfter(game.getDistribution().getDonneur());
        assertTrue(game.keepBidding());
        BidBeloteSuit contratTmp_ = new BidBeloteSuit();
        contratTmp_.setEnchere(BidBelote.FOLD);
        game.ajouterContrat(contratTmp_,player_);
        player_ = game.playerAfter(player_);
        assertTrue(game.keepBidding());
        contratTmp_ = new BidBeloteSuit();
        contratTmp_.setEnchere(BidBelote.FOLD);
        game.ajouterContrat(contratTmp_,player_);
        player_ = game.playerAfter(player_);
        assertTrue(game.keepBidding());
        contratTmp_ = new BidBeloteSuit();
        contratTmp_.setEnchere(BidBelote.SUIT);
        contratTmp_.setCouleur(Suit.DIAMOND);
        game.ajouterContrat(contratTmp_,player_);
        assertTrue(game.keepBidding());
        //game.setContrat(contrat_tmp);
        EqList<BidBeloteSuit> bids_ = game.allowedBids();
        EqList<BidBeloteSuit> expected_ = new EqList<BidBeloteSuit>();
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setEnchere(BidBelote.FOLD);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setEnchere(BidBelote.SUIT);
        b_.setCouleur(Suit.DIAMOND);
        expected_.add(b_);
        EqList<BidBeloteSuit> bidsGr_ = toRealBid(_bidsWithoutTrumpSuit);
        expected_.addAllElts(bidsGr_);
        asserting(expected_,bids_);
        assertTrue(expected_.get(0).estDemandable(game.getContrat()));
        assertTrue(!expected_.get(1).estDemandable(game.getContrat()));
        assertTrue(bidsGr_.first().estDemandable(game.getContrat()));
        assertTrue(bidsGr_.last().estDemandable(game.getContrat()));
    }
    @Parameters(method = "bidsWithoutTrumpSuit",source =GameBeloteBiddingTest.class)
    @Test
    public void allowedBids_AtSecondRoundBidsInitialize12Test(EnumList<BidBelote> _bidsWithoutTrumpSuit) {
        RulesBelote regles_=initializeRulesWithBids(_bidsWithoutTrumpSuit);
        game = new GameBelote(GameType.RANDOM,initializeHands(),regles_);
        //game.resetNbPlisTotal();
        game.finEncherePremierTour();
        EqList<BidBeloteSuit> bids_ = game.allowedBids();
        EqList<BidBeloteSuit> expected_ = new EqList<BidBeloteSuit>();
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setEnchere(BidBelote.FOLD);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setEnchere(BidBelote.OTHER_SUIT);
        b_.setCouleur(Suit.HEART);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setEnchere(BidBelote.OTHER_SUIT);
        b_.setCouleur(Suit.SPADE);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setEnchere(BidBelote.OTHER_SUIT);
        b_.setCouleur(Suit.CLUB);
        expected_.add(b_);
        expected_.addAllElts(toRealBid(_bidsWithoutTrumpSuit));
        assertTrue(BidBeloteSuit.equalsSet(expected_,bids_));
    }
    @Parameters(method = "bidsWithoutTrumpSuit",source =GameBeloteBiddingTest.class)
    @Test
    public void allowedBids_AtSecondRoundBidsInitializeWithoutTakingBid13Test(EnumList<BidBelote> _bidsWithoutTrumpSuit) {
        RulesBelote regles_=initializeRulesWithBids(_bidsWithoutTrumpSuit);
        game = new GameBelote(GameType.RANDOM,initializeHands(),regles_);
        //game.resetNbPlisTotal();
        byte player_ = game.playerAfter(game.getDistribution().getDonneur());
        assertTrue(game.keepBidding());
        BidBeloteSuit contratTmp_ = new BidBeloteSuit();
        contratTmp_.setEnchere(BidBelote.FOLD);
        game.ajouterContrat(contratTmp_,player_);
        player_ = game.playerAfter(player_);
        assertTrue(game.keepBidding());
        contratTmp_ = new BidBeloteSuit();
        contratTmp_.setEnchere(BidBelote.FOLD);
        game.ajouterContrat(contratTmp_,player_);
        player_ = game.playerAfter(player_);
        assertTrue(game.keepBidding());
        contratTmp_ = new BidBeloteSuit();
        contratTmp_.setEnchere(BidBelote.FOLD);
        game.ajouterContrat(contratTmp_,player_);
        player_ = game.playerAfter(player_);
        assertTrue(game.keepBidding());
        contratTmp_ = new BidBeloteSuit();
        contratTmp_.setEnchere(BidBelote.FOLD);
        game.ajouterContrat(contratTmp_,player_);
        game.finEncherePremierTour();
        player_ = game.playerAfter(player_);
        assertTrue(game.keepBidding());
        contratTmp_ = new BidBeloteSuit();
        contratTmp_.setEnchere(BidBelote.FOLD);
        game.ajouterContrat(contratTmp_,player_);
        assertTrue(game.keepBidding());
        EqList<BidBeloteSuit> bids_ = game.allowedBids();
        EqList<BidBeloteSuit> expected_ = new EqList<BidBeloteSuit>();
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setEnchere(BidBelote.FOLD);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setEnchere(BidBelote.OTHER_SUIT);
        b_.setCouleur(Suit.HEART);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setEnchere(BidBelote.OTHER_SUIT);
        b_.setCouleur(Suit.SPADE);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setEnchere(BidBelote.OTHER_SUIT);
        b_.setCouleur(Suit.CLUB);
        expected_.add(b_);
        expected_.addAllElts(toRealBid(_bidsWithoutTrumpSuit));
        assertTrue(BidBeloteSuit.equalsSet(expected_,bids_));
    }
    @Parameters(method = "bidsWithoutTrumpSuit",source =GameBeloteBiddingTest.class)
    @Test
    public void allowedBids_AtSecondRoundBidsInitializeByTakingBid14Test(EnumList<BidBelote> _bidsWithoutTrumpSuit) {
        RulesBelote regles_=initializeRulesWithBids(_bidsWithoutTrumpSuit);
        game = new GameBelote(GameType.RANDOM,initializeHands(),regles_);
        //game.resetNbPlisTotal();
        byte player_ = game.playerAfter(game.getDistribution().getDonneur());
        assertTrue(game.keepBidding());
        BidBeloteSuit contratTmp_ = new BidBeloteSuit();
        contratTmp_.setEnchere(BidBelote.FOLD);
        game.ajouterContrat(contratTmp_,player_);
        player_ = game.playerAfter(player_);
        assertTrue(game.keepBidding());
        contratTmp_ = new BidBeloteSuit();
        contratTmp_.setEnchere(BidBelote.FOLD);
        game.ajouterContrat(contratTmp_,player_);
        player_ = game.playerAfter(player_);
        assertTrue(game.keepBidding());
        contratTmp_ = new BidBeloteSuit();
        contratTmp_.setEnchere(BidBelote.FOLD);
        game.ajouterContrat(contratTmp_,player_);
        player_ = game.playerAfter(player_);
        assertTrue(game.keepBidding());
        contratTmp_ = new BidBeloteSuit();
        contratTmp_.setEnchere(BidBelote.FOLD);
        game.ajouterContrat(contratTmp_,player_);
        game.finEncherePremierTour();
        player_ = game.playerAfter(player_);
        assertTrue(game.keepBidding());
        contratTmp_ = new BidBeloteSuit();
        contratTmp_.setEnchere(BidBelote.FOLD);
        game.ajouterContrat(contratTmp_,player_);
        player_ = game.playerAfter(player_);
        assertTrue(game.keepBidding());
        contratTmp_ = new BidBeloteSuit();
        contratTmp_.setEnchere(BidBelote.OTHER_SUIT);
        contratTmp_.setCouleur(Suit.HEART);
        game.ajouterContrat(contratTmp_,player_);
        assertTrue(game.keepBidding());
        //game.setContrat(contrat_tmp);
        EqList<BidBeloteSuit> bids_ = game.allowedBids();
        EqList<BidBeloteSuit> expected_ = new EqList<BidBeloteSuit>();
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setEnchere(BidBelote.FOLD);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setEnchere(BidBelote.OTHER_SUIT);
        b_.setCouleur(Suit.HEART);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setEnchere(BidBelote.OTHER_SUIT);
        b_.setCouleur(Suit.SPADE);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setEnchere(BidBelote.OTHER_SUIT);
        b_.setCouleur(Suit.CLUB);
        expected_.add(b_);
        EqList<BidBeloteSuit> bidsGr_ = toRealBid(_bidsWithoutTrumpSuit);
        expected_.addAllElts(bidsGr_);
        asserting(expected_,bids_);
        assertTrue(expected_.get(0).estDemandable(game.getContrat()));
        assertTrue(!expected_.get(1).estDemandable(game.getContrat()));
        assertTrue(!expected_.get(2).estDemandable(game.getContrat()));
        assertTrue(!expected_.get(3).estDemandable(game.getContrat()));
        assertTrue(bidsGr_.first().estDemandable(game.getContrat()));
        assertTrue(bidsGr_.last().estDemandable(game.getContrat()));
    }
    @Parameters(method = "bidsWithoutTrumpSuit",source =GameBeloteBiddingTest.class)
    @Test
    public void allowedBids_AtSecondRoundBidsInitializePassing15Test(EnumList<BidBelote> _bidsWithoutTrumpSuit) {
        RulesBelote regles_=initializeRulesWithBids(_bidsWithoutTrumpSuit);
        game = new GameBelote(GameType.RANDOM,initializeHands(),regles_);
        //game.resetNbPlisTotal();
        byte player_ = game.playerAfter(game.getDistribution().getDonneur());
        assertTrue(game.keepBidding());
        BidBeloteSuit contratTmp_ = new BidBeloteSuit();
        contratTmp_.setEnchere(BidBelote.FOLD);
        game.ajouterContrat(contratTmp_,player_);
        player_ = game.playerAfter(player_);
        assertTrue(game.keepBidding());
        contratTmp_ = new BidBeloteSuit();
        contratTmp_.setEnchere(BidBelote.FOLD);
        game.ajouterContrat(contratTmp_,player_);
        player_ = game.playerAfter(player_);
        assertTrue(game.keepBidding());
        contratTmp_ = new BidBeloteSuit();
        contratTmp_.setEnchere(BidBelote.FOLD);
        game.ajouterContrat(contratTmp_,player_);
        player_ = game.playerAfter(player_);
        assertTrue(game.keepBidding());
        contratTmp_ = new BidBeloteSuit();
        contratTmp_.setEnchere(BidBelote.FOLD);
        game.ajouterContrat(contratTmp_,player_);
        game.finEncherePremierTour();
        contratTmp_ = new BidBeloteSuit();
        contratTmp_.setEnchere(BidBelote.FOLD);
        game.ajouterContrat(contratTmp_,player_);
        player_ = game.playerAfter(player_);
        assertTrue(game.keepBidding());
        contratTmp_ = new BidBeloteSuit();
        contratTmp_.setEnchere(BidBelote.FOLD);
        game.ajouterContrat(contratTmp_,player_);
        player_ = game.playerAfter(player_);
        assertTrue(game.keepBidding());
        contratTmp_ = new BidBeloteSuit();
        contratTmp_.setEnchere(BidBelote.FOLD);
        game.ajouterContrat(contratTmp_,player_);
        player_ = game.playerAfter(player_);
        assertTrue(game.keepBidding());
        contratTmp_ = new BidBeloteSuit();
        contratTmp_.setEnchere(BidBelote.FOLD);
        game.ajouterContrat(contratTmp_,player_);
        assertTrue(!game.keepBidding());
    }

    private static EqList<BidBeloteSuit> toRealBid(EnumList<BidBelote> _bids) {
        EqList<BidBeloteSuit> bids_ = new EqList<BidBeloteSuit>();
        for (BidBelote e: _bids) {
            BidBeloteSuit bid_ = new BidBeloteSuit();
            bid_.setEnchere(e);
            bids_.add(bid_);
        }
        return bids_;
    }

    @Test
    public void maximumBid_AtFirstRound3Test() {
        RulesBelote regles_=initializeRulesWithBids(new EnumList<BidBelote>(BidBelote.NO_TRUMP));
        game = new GameBelote(GameType.RANDOM,initializeHands(),regles_);
        //game.resetNbPlisTotal();
        EqList<BidBeloteSuit> maximunBidsSuits_ = game.maximumBid();
        BidBeloteSuit b_;
        b_ = new BidBeloteSuit();
        b_.setEnchere(BidBelote.NO_TRUMP);
        assertEq(1, maximunBidsSuits_.size());
        assertTrue(maximunBidsSuits_.containsObj(b_));
    }

    @Parameters(method = "bidsWithoutTrumpSuitAllTrump",source =GameBeloteBiddingTest.class)
    @Test
    public void maximumBid_AtFirstRound4Test(EnumList<BidBelote> _bidsWithoutTrumpSuit) {
        RulesBelote regles_=initializeRulesWithBids(_bidsWithoutTrumpSuit);
        game = new GameBelote(GameType.RANDOM,initializeHands(),regles_);
        //game.resetNbPlisTotal();
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setEnchere(BidBelote.ALL_TRUMP);
        EqList<BidBeloteSuit> maximunBidsSuits_ = game.maximumBid();
        assertEq(1, maximunBidsSuits_.size());
        assertTrue(maximunBidsSuits_.containsObj(b_));
    }

    @Test
    public void maximumBid_AtSecondRound5Test() {
        RulesBelote regles_=initializeRulesWithBids(new EnumList<BidBelote>(BidBelote.NO_TRUMP));
        game = new GameBelote(GameType.RANDOM,initializeHands(),regles_);
        //game.resetNbPlisTotal();
        game.finEncherePremierTour();
        EqList<BidBeloteSuit> maximunBidsSuits_ = game.maximumBid();
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setEnchere(BidBelote.NO_TRUMP);
        assertEq(1, maximunBidsSuits_.size());
        assertTrue(maximunBidsSuits_.containsObj(b_));
    }

    @Parameters(method = "bidsWithoutTrumpSuitAllTrump",source =GameBeloteBiddingTest.class)
    @Test
    public void maximumBid_AtSecondRound6Test(EnumList<BidBelote> _bidsWithoutTrumpSuit) {
        RulesBelote regles_=initializeRulesWithBids(_bidsWithoutTrumpSuit);
        game = new GameBelote(GameType.RANDOM,initializeHands(),regles_);
        //game.resetNbPlisTotal();
        game.finEncherePremierTour();
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setEnchere(BidBelote.ALL_TRUMP);
        EqList<BidBeloteSuit> maximunBidsSuits_ = game.maximumBid();
        assertEq(1, maximunBidsSuits_.size());
        assertTrue(maximunBidsSuits_.containsObj(b_));
    }

    @Test
    public void allowedBids16Test() {
        RulesBelote regles_=initializeRulesWithBidPoints(false);
        regles_.getEncheresAutorisees().put(BidBelote.NO_TRUMP, true);
        game = new GameBelote(GameType.RANDOM,initializeHands(),regles_);
        //game.resetNbPlisTotal();
        EqList<BidBeloteSuit> bids_ = game.allowedBids();
        EqList<BidBeloteSuit> expected_ = new EqList<BidBeloteSuit>();
        BidBeloteSuit b_;
        b_ = new BidBeloteSuit();
        b_.setEnchere(BidBelote.SUIT);
        b_.setCouleur(Suit.HEART);
        b_.setPoints(80);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setEnchere(BidBelote.SUIT);
        b_.setCouleur(Suit.SPADE);
        b_.setPoints(80);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setEnchere(BidBelote.SUIT);
        b_.setCouleur(Suit.DIAMOND);
        b_.setPoints(80);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setEnchere(BidBelote.SUIT);
        b_.setCouleur(Suit.CLUB);
        b_.setPoints(80);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setEnchere(BidBelote.SUIT);
        b_.setCouleur(Suit.HEART);
        b_.setPoints(90);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setEnchere(BidBelote.SUIT);
        b_.setCouleur(Suit.SPADE);
        b_.setPoints(90);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setEnchere(BidBelote.SUIT);
        b_.setCouleur(Suit.DIAMOND);
        b_.setPoints(90);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setEnchere(BidBelote.SUIT);
        b_.setCouleur(Suit.CLUB);
        b_.setPoints(90);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setEnchere(BidBelote.SUIT);
        b_.setCouleur(Suit.HEART);
        b_.setPoints(100);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setEnchere(BidBelote.SUIT);
        b_.setCouleur(Suit.SPADE);
        b_.setPoints(100);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setEnchere(BidBelote.SUIT);
        b_.setCouleur(Suit.DIAMOND);
        b_.setPoints(100);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setEnchere(BidBelote.SUIT);
        b_.setCouleur(Suit.CLUB);
        b_.setPoints(100);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setEnchere(BidBelote.SUIT);
        b_.setCouleur(Suit.HEART);
        b_.setPoints(110);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setEnchere(BidBelote.SUIT);
        b_.setCouleur(Suit.SPADE);
        b_.setPoints(110);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setEnchere(BidBelote.SUIT);
        b_.setCouleur(Suit.DIAMOND);
        b_.setPoints(110);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setEnchere(BidBelote.SUIT);
        b_.setCouleur(Suit.CLUB);
        b_.setPoints(110);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setEnchere(BidBelote.SUIT);
        b_.setCouleur(Suit.HEART);
        b_.setPoints(120);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setEnchere(BidBelote.SUIT);
        b_.setCouleur(Suit.SPADE);
        b_.setPoints(120);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setEnchere(BidBelote.SUIT);
        b_.setCouleur(Suit.DIAMOND);
        b_.setPoints(120);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setEnchere(BidBelote.SUIT);
        b_.setCouleur(Suit.CLUB);
        b_.setPoints(120);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setEnchere(BidBelote.SUIT);
        b_.setCouleur(Suit.HEART);
        b_.setPoints(130);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setEnchere(BidBelote.SUIT);
        b_.setCouleur(Suit.SPADE);
        b_.setPoints(130);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setEnchere(BidBelote.SUIT);
        b_.setCouleur(Suit.DIAMOND);
        b_.setPoints(130);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setEnchere(BidBelote.SUIT);
        b_.setCouleur(Suit.CLUB);
        b_.setPoints(130);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setEnchere(BidBelote.SUIT);
        b_.setCouleur(Suit.HEART);
        b_.setPoints(140);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setEnchere(BidBelote.SUIT);
        b_.setCouleur(Suit.SPADE);
        b_.setPoints(140);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setEnchere(BidBelote.SUIT);
        b_.setCouleur(Suit.DIAMOND);
        b_.setPoints(140);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setEnchere(BidBelote.SUIT);
        b_.setCouleur(Suit.CLUB);
        b_.setPoints(140);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setEnchere(BidBelote.SUIT);
        b_.setCouleur(Suit.HEART);
        b_.setPoints(150);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setEnchere(BidBelote.SUIT);
        b_.setCouleur(Suit.SPADE);
        b_.setPoints(150);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setEnchere(BidBelote.SUIT);
        b_.setCouleur(Suit.DIAMOND);
        b_.setPoints(150);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setEnchere(BidBelote.SUIT);
        b_.setCouleur(Suit.CLUB);
        b_.setPoints(150);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setEnchere(BidBelote.SUIT);
        b_.setCouleur(Suit.HEART);
        b_.setPoints(160);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setEnchere(BidBelote.SUIT);
        b_.setCouleur(Suit.SPADE);
        b_.setPoints(160);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setEnchere(BidBelote.SUIT);
        b_.setCouleur(Suit.DIAMOND);
        b_.setPoints(160);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setEnchere(BidBelote.SUIT);
        b_.setCouleur(Suit.CLUB);
        b_.setPoints(160);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setEnchere(BidBelote.SUIT);
        b_.setCouleur(Suit.HEART);
        b_.setPoints(162);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setEnchere(BidBelote.SUIT);
        b_.setCouleur(Suit.SPADE);
        b_.setPoints(162);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setEnchere(BidBelote.SUIT);
        b_.setCouleur(Suit.DIAMOND);
        b_.setPoints(162);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setEnchere(BidBelote.SUIT);
        b_.setCouleur(Suit.CLUB);
        b_.setPoints(162);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setEnchere(BidBelote.NO_TRUMP);
        b_.setCouleur(Suit.UNDEFINED);
        b_.setPoints(80);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setEnchere(BidBelote.NO_TRUMP);
        b_.setCouleur(Suit.UNDEFINED);
        b_.setPoints(90);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setEnchere(BidBelote.NO_TRUMP);
        b_.setCouleur(Suit.UNDEFINED);
        b_.setPoints(100);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setEnchere(BidBelote.NO_TRUMP);
        b_.setCouleur(Suit.UNDEFINED);
        b_.setPoints(110);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setEnchere(BidBelote.NO_TRUMP);
        b_.setCouleur(Suit.UNDEFINED);
        b_.setPoints(120);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setEnchere(BidBelote.NO_TRUMP);
        b_.setCouleur(Suit.UNDEFINED);
        b_.setPoints(130);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setEnchere(BidBelote.NO_TRUMP);
        b_.setCouleur(Suit.UNDEFINED);
        b_.setPoints(140);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setEnchere(BidBelote.NO_TRUMP);
        b_.setCouleur(Suit.UNDEFINED);
        b_.setPoints(150);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setEnchere(BidBelote.NO_TRUMP);
        b_.setCouleur(Suit.UNDEFINED);
        b_.setPoints(160);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setEnchere(BidBelote.NO_TRUMP);
        b_.setCouleur(Suit.UNDEFINED);
        b_.setPoints(162);
        expected_.add(b_);
        assertTrue(BidBeloteSuit.equalsSet(expected_,bids_));
    }

    @Test
    public void allowedBids17Test() {
        RulesBelote regles_=initializeRulesWithBidPoints(false);
        regles_.getEncheresAutorisees().put(BidBelote.NO_TRUMP, true);
        game = new GameBelote(GameType.RANDOM,initializeHands(),regles_);
        BidBeloteSuit contratTmp_ = new BidBeloteSuit();
        contratTmp_.setEnchere(BidBelote.SUIT);
        contratTmp_.setCouleur(Suit.DIAMOND);
        contratTmp_.setPoints(140);
        game.ajouterContrat(contratTmp_,game.playerAfter(game.getDistribution().getDonneur()));
        //game.resetNbPlisTotal();
        EqList<BidBeloteSuit> bids_ = game.allowedBids();
        EqList<BidBeloteSuit> expected_ = new EqList<BidBeloteSuit>();
        BidBeloteSuit b_;
        b_ = new BidBeloteSuit();
        b_.setEnchere(BidBelote.SUIT);
        b_.setCouleur(Suit.HEART);
        b_.setPoints(150);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setEnchere(BidBelote.SUIT);
        b_.setCouleur(Suit.SPADE);
        b_.setPoints(150);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setEnchere(BidBelote.SUIT);
        b_.setCouleur(Suit.DIAMOND);
        b_.setPoints(150);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setEnchere(BidBelote.SUIT);
        b_.setCouleur(Suit.CLUB);
        b_.setPoints(150);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setEnchere(BidBelote.SUIT);
        b_.setCouleur(Suit.HEART);
        b_.setPoints(160);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setEnchere(BidBelote.SUIT);
        b_.setCouleur(Suit.SPADE);
        b_.setPoints(160);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setEnchere(BidBelote.SUIT);
        b_.setCouleur(Suit.DIAMOND);
        b_.setPoints(160);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setEnchere(BidBelote.SUIT);
        b_.setCouleur(Suit.CLUB);
        b_.setPoints(160);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setEnchere(BidBelote.SUIT);
        b_.setCouleur(Suit.HEART);
        b_.setPoints(162);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setEnchere(BidBelote.SUIT);
        b_.setCouleur(Suit.SPADE);
        b_.setPoints(162);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setEnchere(BidBelote.SUIT);
        b_.setCouleur(Suit.DIAMOND);
        b_.setPoints(162);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setEnchere(BidBelote.SUIT);
        b_.setCouleur(Suit.CLUB);
        b_.setPoints(162);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setEnchere(BidBelote.NO_TRUMP);
        b_.setCouleur(Suit.UNDEFINED);
        b_.setPoints(150);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setEnchere(BidBelote.NO_TRUMP);
        b_.setCouleur(Suit.UNDEFINED);
        b_.setPoints(160);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setEnchere(BidBelote.NO_TRUMP);
        b_.setCouleur(Suit.UNDEFINED);
        b_.setPoints(162);
        expected_.add(b_);
        assertTrue(BidBeloteSuit.equalsSet(expected_,bids_));
    }

    //TODO change later
    private void asserting(EqList<BidBeloteSuit> _exp, EqList<BidBeloteSuit> _res) {
        assertEq(_exp.size(), _res.size());
        int s_ = _exp.size();
        for (int i = CustList.FIRST_INDEX; i < s_; i++) {
            assertEq(_exp.get(i), _res.get(i));
        }
    }
}
