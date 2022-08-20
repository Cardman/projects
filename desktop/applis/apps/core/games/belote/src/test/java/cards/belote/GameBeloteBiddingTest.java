package cards.belote;

import code.util.comparators.ComparatorBoolean;
import code.util.core.BoolVal;
import code.util.core.IndexConstants;
import org.junit.Test;

import cards.belote.enumerations.BidBelote;
import cards.belote.enumerations.CardBelote;
import cards.belote.enumerations.DealingBelote;
import cards.consts.GameType;
import cards.consts.MixCardsChoice;
import cards.consts.Suit;
import code.util.CustList;
import code.util.EnumList;
import code.util.IdMap;


public class GameBeloteBiddingTest extends GameBeloteWithTrumpSuit {


    static DealBelote initializeHands() {
        CustList<HandBelote> mains_ = new CustList<HandBelote>();
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
        regles_.getCommon().setMixedCards(MixCardsChoice.NEVER);
        IdMap<BidBelote,BoolVal> contrats_ = new IdMap<BidBelote,BoolVal>();
        for (BidBelote b: regles_.getAllowedBids().getKeys()) {
            contrats_.put(b, ComparatorBoolean.of(b.getToujoursPossibleAnnoncer()));
        }
        if(!BidBelote.NO_TRUMP.getToujoursPossibleAnnoncer()) {
            contrats_.put(BidBelote.NO_TRUMP,BoolVal.FALSE);
        }
        if(!BidBelote.ALL_TRUMP.getToujoursPossibleAnnoncer()) {
            contrats_.put(BidBelote.ALL_TRUMP,BoolVal.FALSE);
        }
        regles_.setAllowedBids(contrats_);
        return regles_;
    }

    static RulesBelote initializeRulesWithBids(EnumList<BidBelote> _bids) {
        RulesBelote regles_=new RulesBelote();
        regles_.getCommon().setMixedCards(MixCardsChoice.NEVER);
        IdMap<BidBelote,BoolVal> contrats_ = new IdMap<BidBelote,BoolVal>();
        for (BidBelote b: regles_.getAllowedBids().getKeys()) {
            contrats_.put(b, ComparatorBoolean.of(b.getToujoursPossibleAnnoncer()));
        }
        if(!BidBelote.NO_TRUMP.getToujoursPossibleAnnoncer()) {
            contrats_.put(BidBelote.NO_TRUMP,BoolVal.FALSE);
        }
        if(!BidBelote.ALL_TRUMP.getToujoursPossibleAnnoncer()) {
            contrats_.put(BidBelote.ALL_TRUMP,BoolVal.FALSE);
        }
        for (BidBelote b: _bids) {
            contrats_.put(b,BoolVal.TRUE);
        }
        regles_.setAllowedBids(contrats_);
        return regles_;
    }

    static RulesBelote initializeRulesWithBidPoints(boolean _addOverBid) {
        RulesBelote regles_=new RulesBelote();
        regles_.getCommon().setMixedCards(MixCardsChoice.NEVER);
        regles_.setDealing(DealingBelote.COINCHE_2_VS_2);
        IdMap<BidBelote,BoolVal> contrats_ = new IdMap<BidBelote,BoolVal>();
        for (BidBelote b: regles_.getAllowedBids().getKeys()) {
            contrats_.put(b, ComparatorBoolean.of(b.getToujoursPossibleAnnoncer()));
        }
        if(!BidBelote.NO_TRUMP.getToujoursPossibleAnnoncer()) {
            contrats_.put(BidBelote.NO_TRUMP,BoolVal.FALSE);
        }
        if(!BidBelote.ALL_TRUMP.getToujoursPossibleAnnoncer()) {
            contrats_.put(BidBelote.ALL_TRUMP,BoolVal.FALSE);
        }
        if (_addOverBid) {
            for (BidBelote b: regles_.getAllowedBids().getKeys()) {
                contrats_.put(b,BoolVal.TRUE);
            }
        }
        regles_.setAllowedBids(contrats_);
        return regles_;
    }
    @Test
    public void allowedBids_AtFirstRoundBidsInitialize1(){
        RulesBelote regles_=initializeDefaultRules();
        GameBelote game_ = new GameBelote(GameType.RANDOM,initializeHands(),regles_);
        //game_.resetNbPlisTotal();
        CustList<BidBeloteSuit> bids_ = game_.getGameBeloteBid().allowedBids();
        CustList<BidBeloteSuit> expected_ = new CustList<BidBeloteSuit>();
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.FOLD);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.SUIT);
        b_.setSuit(Suit.DIAMOND);
        expected_.add(b_);
        asserting(expected_,bids_);
    }
    @Test
    public void allowedBids_AtFirstRoundBidsInitializeWithoutTakingBid1(){
        RulesBelote regles_=initializeDefaultRules();
        GameBelote game_ = new GameBelote(GameType.RANDOM,initializeHands(),regles_);
        //game_.resetNbPlisTotal();
        byte player_ = game_.playerAfter(game_.getDistribution().getDealer());
        assertTrue(game_.keepBidding());
        BidBeloteSuit contratTmp_ = new BidBeloteSuit();
        contratTmp_.setBid(BidBelote.FOLD);
        game_.ajouterContrat(contratTmp_,player_);
        player_ = game_.playerAfter(player_);
        assertTrue(game_.keepBidding());
        contratTmp_ = new BidBeloteSuit();
        contratTmp_.setBid(BidBelote.FOLD);
        game_.ajouterContrat(contratTmp_,player_);
        player_ = game_.playerAfter(player_);
        assertTrue(game_.keepBidding());
        contratTmp_ = new BidBeloteSuit();
        contratTmp_.setBid(BidBelote.FOLD);
        game_.ajouterContrat(contratTmp_,player_);
        player_ = game_.playerAfter(player_);
        assertTrue(game_.keepBidding());
        CustList<BidBeloteSuit> bids_ = game_.getGameBeloteBid().allowedBids();
        CustList<BidBeloteSuit> expected_ = new CustList<BidBeloteSuit>();
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.FOLD);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.SUIT);
        b_.setSuit(Suit.DIAMOND);
        expected_.add(b_);
        asserting(expected_,bids_);
    }
    @Test
    public void allowedBids_AtFirstRoundBidsInitializeByTakingBid1(){
        RulesBelote regles_=initializeDefaultRules();
        GameBelote game_ = new GameBelote(GameType.RANDOM,initializeHands(),regles_);
        //game_.resetNbPlisTotal();
        byte player_ = game_.playerAfter(game_.getDistribution().getDealer());
        assertTrue(game_.keepBidding());
        BidBeloteSuit contratTmp_ = new BidBeloteSuit();
        contratTmp_.setBid(BidBelote.FOLD);
        game_.ajouterContrat(contratTmp_,player_);
        player_ = game_.playerAfter(player_);
        assertTrue(game_.keepBidding());
        contratTmp_ = new BidBeloteSuit();
        contratTmp_.setBid(BidBelote.FOLD);
        game_.ajouterContrat(contratTmp_,player_);
        player_ = game_.playerAfter(player_);
        assertTrue(game_.keepBidding());
        contratTmp_ = new BidBeloteSuit();
        contratTmp_.setBid(BidBelote.SUIT);
        contratTmp_.setSuit(Suit.DIAMOND);
        game_.ajouterContrat(contratTmp_,player_);
        assertTrue(!game_.keepBidding());
        //game_.setContrat(contrat_tmp);
        CustList<BidBeloteSuit> bids_ = game_.getGameBeloteBid().allowedBids();
        CustList<BidBeloteSuit> expected_ = new CustList<BidBeloteSuit>();
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.FOLD);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.SUIT);
        b_.setSuit(Suit.DIAMOND);
        expected_.add(b_);
        asserting(expected_,bids_);
        assertTrue(expected_.get(0).estDemandable(game_.getBid()));
        assertTrue(!expected_.get(1).estDemandable(game_.getBid()));
    }
    @Test
    public void allowedBids_AtSecondRoundBidsInitialize1(){
        RulesBelote regles_=initializeDefaultRules();
        GameBelote game_ = new GameBelote(GameType.RANDOM,initializeHands(),regles_);
        //game_.resetNbPlisTotal();
        game_.finEncherePremierTour();
        CustList<BidBeloteSuit> bids_ = game_.getGameBeloteBid().allowedBids();
        CustList<BidBeloteSuit> expected_ = new CustList<BidBeloteSuit>();
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.FOLD);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.OTHER_SUIT);
        b_.setSuit(Suit.HEART);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.OTHER_SUIT);
        b_.setSuit(Suit.SPADE);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.OTHER_SUIT);
        b_.setSuit(Suit.CLUB);
        expected_.add(b_);
        assertEqSet(bids_, expected_);
        //max_contrat()
    }
    @Test
    public void allowedBids_AtSecondRoundBidsInitializeWithoutTakingBid1(){
        RulesBelote regles_=initializeDefaultRules();
        GameBelote game_ = new GameBelote(GameType.RANDOM,initializeHands(),regles_);
        //game_.resetNbPlisTotal();
        byte player_ = game_.playerAfter(game_.getDistribution().getDealer());
        assertTrue(game_.keepBidding());
        BidBeloteSuit contratTmp_ = new BidBeloteSuit();
        contratTmp_.setBid(BidBelote.FOLD);
        game_.ajouterContrat(contratTmp_,player_);
        player_ = game_.playerAfter(player_);
        assertTrue(game_.keepBidding());
        contratTmp_ = new BidBeloteSuit();
        contratTmp_.setBid(BidBelote.FOLD);
        game_.ajouterContrat(contratTmp_,player_);
        player_ = game_.playerAfter(player_);
        assertTrue(game_.keepBidding());
        contratTmp_ = new BidBeloteSuit();
        contratTmp_.setBid(BidBelote.FOLD);
        game_.ajouterContrat(contratTmp_,player_);
        player_ = game_.playerAfter(player_);
        assertTrue(game_.keepBidding());
        contratTmp_ = new BidBeloteSuit();
        contratTmp_.setBid(BidBelote.FOLD);
        game_.ajouterContrat(contratTmp_,player_);
        game_.finEncherePremierTour();
        player_ = game_.playerAfter(player_);
        assertTrue(game_.keepBidding());
        contratTmp_ = new BidBeloteSuit();
        contratTmp_.setBid(BidBelote.FOLD);
        game_.ajouterContrat(contratTmp_,player_);
        assertTrue(game_.keepBidding());
        CustList<BidBeloteSuit> bids_ = game_.getGameBeloteBid().allowedBids();
        CustList<BidBeloteSuit> expected_ = new CustList<BidBeloteSuit>();
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.FOLD);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.OTHER_SUIT);
        b_.setSuit(Suit.HEART);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.OTHER_SUIT);
        b_.setSuit(Suit.SPADE);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.OTHER_SUIT);
        b_.setSuit(Suit.CLUB);
        expected_.add(b_);
        assertEqSet(bids_, expected_);
    }
    @Test
    public void allowedBids_AtSecondRoundBidsInitializeByTakingBid1(){
        RulesBelote regles_=initializeDefaultRules();
        GameBelote game_ = new GameBelote(GameType.RANDOM,initializeHands(),regles_);
        //game_.resetNbPlisTotal();
        byte player_ = game_.playerAfter(game_.getDistribution().getDealer());
        assertTrue(game_.keepBidding());
        BidBeloteSuit contratTmp_ = new BidBeloteSuit();
        contratTmp_.setBid(BidBelote.FOLD);
        game_.ajouterContrat(contratTmp_,player_);
        player_ = game_.playerAfter(player_);
        assertTrue(game_.keepBidding());
        contratTmp_ = new BidBeloteSuit();
        contratTmp_.setBid(BidBelote.FOLD);
        game_.ajouterContrat(contratTmp_,player_);
        player_ = game_.playerAfter(player_);
        assertTrue(game_.keepBidding());
        contratTmp_ = new BidBeloteSuit();
        contratTmp_.setBid(BidBelote.FOLD);
        game_.ajouterContrat(contratTmp_,player_);
        player_ = game_.playerAfter(player_);
        assertTrue(game_.keepBidding());
        contratTmp_ = new BidBeloteSuit();
        contratTmp_.setBid(BidBelote.FOLD);
        game_.ajouterContrat(contratTmp_,player_);
        game_.finEncherePremierTour();
        player_ = game_.playerAfter(player_);
        assertTrue(game_.keepBidding());
        contratTmp_ = new BidBeloteSuit();
        contratTmp_.setBid(BidBelote.FOLD);
        game_.ajouterContrat(contratTmp_,player_);
        player_ = game_.playerAfter(player_);
        assertTrue(game_.keepBidding());
        contratTmp_ = new BidBeloteSuit();
        contratTmp_.setBid(BidBelote.OTHER_SUIT);
        contratTmp_.setSuit(Suit.HEART);
        game_.ajouterContrat(contratTmp_,player_);
        assertTrue(!game_.keepBidding());
        //game_.setContrat(contrat_tmp);
        CustList<BidBeloteSuit> bids_ = game_.getGameBeloteBid().allowedBids();
        CustList<BidBeloteSuit> expected_ = new CustList<BidBeloteSuit>();
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.FOLD);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.OTHER_SUIT);
        b_.setSuit(Suit.HEART);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.OTHER_SUIT);
        b_.setSuit(Suit.SPADE);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.OTHER_SUIT);
        b_.setSuit(Suit.CLUB);
        expected_.add(b_);
        asserting(expected_,bids_);
        assertTrue(expected_.get(0).estDemandable(game_.getBid()));
        assertTrue(!expected_.get(1).estDemandable(game_.getBid()));
        assertTrue(!expected_.get(2).estDemandable(game_.getBid()));
        assertTrue(!expected_.get(3).estDemandable(game_.getBid()));
    }
    @Test
    public void allowedBids_AtSecondRoundBidsInitializePassing1(){
        RulesBelote regles_=initializeDefaultRules();
        GameBelote game_ = new GameBelote(GameType.RANDOM,initializeHands(),regles_);
        //game_.resetNbPlisTotal();
        byte player_ = game_.playerAfter(game_.getDistribution().getDealer());
        assertTrue(game_.keepBidding());
        BidBeloteSuit contratTmp_ = new BidBeloteSuit();
        contratTmp_.setBid(BidBelote.FOLD);
        game_.ajouterContrat(contratTmp_,player_);
        player_ = game_.playerAfter(player_);
        assertTrue(game_.keepBidding());
        contratTmp_ = new BidBeloteSuit();
        contratTmp_.setBid(BidBelote.FOLD);
        game_.ajouterContrat(contratTmp_,player_);
        player_ = game_.playerAfter(player_);
        assertTrue(game_.keepBidding());
        contratTmp_ = new BidBeloteSuit();
        contratTmp_.setBid(BidBelote.FOLD);
        game_.ajouterContrat(contratTmp_,player_);
        player_ = game_.playerAfter(player_);
        assertTrue(game_.keepBidding());
        contratTmp_ = new BidBeloteSuit();
        contratTmp_.setBid(BidBelote.FOLD);
        game_.ajouterContrat(contratTmp_,player_);
        player_ = game_.playerAfter(player_);
        game_.finEncherePremierTour();
        contratTmp_ = new BidBeloteSuit();
        contratTmp_.setBid(BidBelote.FOLD);
        game_.ajouterContrat(contratTmp_,player_);
        player_ = game_.playerAfter(player_);
        assertTrue(game_.keepBidding());
        contratTmp_ = new BidBeloteSuit();
        contratTmp_.setBid(BidBelote.FOLD);
        game_.ajouterContrat(contratTmp_,player_);
        player_ = game_.playerAfter(player_);
        assertTrue(game_.keepBidding());
        contratTmp_ = new BidBeloteSuit();
        contratTmp_.setBid(BidBelote.FOLD);
        game_.ajouterContrat(contratTmp_,player_);
        player_ = game_.playerAfter(player_);
        assertTrue(game_.keepBidding());
        contratTmp_ = new BidBeloteSuit();
        contratTmp_.setBid(BidBelote.FOLD);
        game_.ajouterContrat(contratTmp_,player_);
        assertTrue(!game_.keepBidding());
    }
    @Test
    public void allowedBids_AtSecondRoundBidsInitializePassingDealAll(){
        RulesBelote regles_=initializeRulesWithBidPoints(false);
        GameBelote game_ = new GameBelote(GameType.RANDOM,initializeHands(),regles_);
        //game_.resetNbPlisTotal();
        byte player_ = game_.playerAfter(game_.getDistribution().getDealer());
        assertTrue(game_.keepBidding());
        BidBeloteSuit contratTmp_ = new BidBeloteSuit();
        contratTmp_.setBid(BidBelote.FOLD);
        game_.ajouterContrat(contratTmp_,player_);
        player_ = game_.playerAfter(player_);
        assertTrue(game_.keepBidding());
        contratTmp_ = new BidBeloteSuit();
        contratTmp_.setBid(BidBelote.FOLD);
        game_.ajouterContrat(contratTmp_,player_);
        player_ = game_.playerAfter(player_);
        assertTrue(game_.keepBidding());
        contratTmp_ = new BidBeloteSuit();
        contratTmp_.setBid(BidBelote.FOLD);
        game_.ajouterContrat(contratTmp_,player_);
        player_ = game_.playerAfter(player_);
        assertTrue(game_.keepBidding());
        contratTmp_ = new BidBeloteSuit();
        contratTmp_.setBid(BidBelote.FOLD);
        game_.ajouterContrat(contratTmp_,player_);
        assertTrue(!game_.keepBidding());
    }
    @Test
    public void maximumBid_AtFirstRound1(){
        RulesBelote regles_=initializeDefaultRules();
        GameBelote game_ = new GameBelote(GameType.RANDOM,initializeHands(),regles_);
        //game_.resetNbPlisTotal();
        CustList<BidBeloteSuit> maximunBidsSuits_ = game_.maximumBid();
        CustList<BidBeloteSuit> expected_ = new CustList<BidBeloteSuit>();
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.SUIT);
        b_.setSuit(Suit.DIAMOND);
        expected_.add(b_);
        asserting(expected_,maximunBidsSuits_);
    }
    @Test
    public void maximumBid_AtSecondRound1(){
        RulesBelote regles_=initializeDefaultRules();
        GameBelote game_ = new GameBelote(GameType.RANDOM,initializeHands(),regles_);
        //game_.resetNbPlisTotal();
        game_.finEncherePremierTour();
        CustList<BidBeloteSuit> maximunBidsSuits_ = game_.maximumBid();
        CustList<BidBeloteSuit> expected_ = new CustList<BidBeloteSuit>();
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.OTHER_SUIT);
        b_.setSuit(Suit.HEART);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.OTHER_SUIT);
        b_.setSuit(Suit.SPADE);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.OTHER_SUIT);
        b_.setSuit(Suit.CLUB);
        expected_.add(b_);
        assertEqSet(maximunBidsSuits_, expected_);
    }
    @Test
    public void allowedBids_AtFirstRoundBidsInitialize2Test(){
        RulesBelote regles_=initializeRulesWithBids(new EnumList<BidBelote>(BidBelote.NO_TRUMP));
        GameBelote game_ = new GameBelote(GameType.RANDOM,initializeHands(),regles_);
        //game_.resetNbPlisTotal();
        CustList<BidBeloteSuit> bids_ = game_.getGameBeloteBid().allowedBids();
        CustList<BidBeloteSuit> expected_ = new CustList<BidBeloteSuit>();
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.FOLD);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.SUIT);
        b_.setSuit(Suit.DIAMOND);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.NO_TRUMP);
        expected_.add(b_);
        asserting(expected_,bids_);
    }
    @Test
    public void allowedBids_AtFirstRoundBidsInitialize3Test(){
        RulesBelote regles_=initializeRulesWithBids(new EnumList<BidBelote>(BidBelote.ALL_TRUMP));
        GameBelote game_ = new GameBelote(GameType.RANDOM,initializeHands(),regles_);
        //game_.resetNbPlisTotal();
        CustList<BidBeloteSuit> bids_ = game_.getGameBeloteBid().allowedBids();
        CustList<BidBeloteSuit> expected_ = new CustList<BidBeloteSuit>();
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.FOLD);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.SUIT);
        b_.setSuit(Suit.DIAMOND);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.ALL_TRUMP);
        expected_.add(b_);
        asserting(expected_,bids_);
    }
    @Test
    public void allowedBids_AtFirstRoundBidsInitialize4(){
        RulesBelote regles_=initializeRulesWithBids(new EnumList<BidBelote>(BidBelote.NO_TRUMP, BidBelote.ALL_TRUMP));
        GameBelote game_ = new GameBelote(GameType.RANDOM,initializeHands(),regles_);
        //game_.resetNbPlisTotal();
        CustList<BidBeloteSuit> bids_ = game_.getGameBeloteBid().allowedBids();
        CustList<BidBeloteSuit> expected_ = new CustList<BidBeloteSuit>();
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.FOLD);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.SUIT);
        b_.setSuit(Suit.DIAMOND);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.NO_TRUMP);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.ALL_TRUMP);
        expected_.add(b_);
        asserting(expected_,bids_);
    }
    @Test
    public void allowedBids_AtFirstRoundBidsInitializeWithoutTakingBid2Test(){
        RulesBelote regles_=initializeRulesWithBids(new EnumList<BidBelote>(BidBelote.NO_TRUMP));
        GameBelote game_ = new GameBelote(GameType.RANDOM,initializeHands(),regles_);
        //game_.resetNbPlisTotal();
        byte player_ = game_.playerAfter(game_.getDistribution().getDealer());
        assertTrue(game_.keepBidding());
        BidBeloteSuit contratTmp_ = new BidBeloteSuit();
        contratTmp_.setBid(BidBelote.FOLD);
        game_.ajouterContrat(contratTmp_,player_);
        player_ = game_.playerAfter(player_);
        assertTrue(game_.keepBidding());
        contratTmp_ = new BidBeloteSuit();
        contratTmp_.setBid(BidBelote.FOLD);
        game_.ajouterContrat(contratTmp_,player_);
        player_ = game_.playerAfter(player_);
        assertTrue(game_.keepBidding());
        contratTmp_ = new BidBeloteSuit();
        contratTmp_.setBid(BidBelote.FOLD);
        game_.ajouterContrat(contratTmp_,player_);
        player_ = game_.playerAfter(player_);
        assertTrue(game_.keepBidding());
        CustList<BidBeloteSuit> bids_ = game_.getGameBeloteBid().allowedBids();
        CustList<BidBeloteSuit> expected_ = new CustList<BidBeloteSuit>();
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.FOLD);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.SUIT);
        b_.setSuit(Suit.DIAMOND);
        expected_.add(b_);
        expected_.addAllElts(toRealBid(new EnumList<BidBelote>(BidBelote.NO_TRUMP)));
        asserting(expected_,bids_);
    }
    @Test
    public void allowedBids_AtFirstRoundBidsInitializeWithoutTakingBid3Test(){
        RulesBelote regles_=initializeRulesWithBids(new EnumList<BidBelote>(BidBelote.ALL_TRUMP));
        GameBelote game_ = new GameBelote(GameType.RANDOM,initializeHands(),regles_);
        //game_.resetNbPlisTotal();
        byte player_ = game_.playerAfter(game_.getDistribution().getDealer());
        assertTrue(game_.keepBidding());
        BidBeloteSuit contratTmp_ = new BidBeloteSuit();
        contratTmp_.setBid(BidBelote.FOLD);
        game_.ajouterContrat(contratTmp_,player_);
        player_ = game_.playerAfter(player_);
        assertTrue(game_.keepBidding());
        contratTmp_ = new BidBeloteSuit();
        contratTmp_.setBid(BidBelote.FOLD);
        game_.ajouterContrat(contratTmp_,player_);
        player_ = game_.playerAfter(player_);
        assertTrue(game_.keepBidding());
        contratTmp_ = new BidBeloteSuit();
        contratTmp_.setBid(BidBelote.FOLD);
        game_.ajouterContrat(contratTmp_,player_);
        player_ = game_.playerAfter(player_);
        assertTrue(game_.keepBidding());
        CustList<BidBeloteSuit> bids_ = game_.getGameBeloteBid().allowedBids();
        CustList<BidBeloteSuit> expected_ = new CustList<BidBeloteSuit>();
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.FOLD);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.SUIT);
        b_.setSuit(Suit.DIAMOND);
        expected_.add(b_);
        expected_.addAllElts(toRealBid(new EnumList<BidBelote>(BidBelote.ALL_TRUMP)));
        asserting(expected_,bids_);
    }
    @Test
    public void allowedBids_AtFirstRoundBidsInitializeWithoutTakingBid4Test(){
        RulesBelote regles_=initializeRulesWithBids(new EnumList<BidBelote>(BidBelote.NO_TRUMP,BidBelote.ALL_TRUMP));
        GameBelote game_ = new GameBelote(GameType.RANDOM,initializeHands(),regles_);
        //game_.resetNbPlisTotal();
        byte player_ = game_.playerAfter(game_.getDistribution().getDealer());
        assertTrue(game_.keepBidding());
        BidBeloteSuit contratTmp_ = new BidBeloteSuit();
        contratTmp_.setBid(BidBelote.FOLD);
        game_.ajouterContrat(contratTmp_,player_);
        player_ = game_.playerAfter(player_);
        assertTrue(game_.keepBidding());
        contratTmp_ = new BidBeloteSuit();
        contratTmp_.setBid(BidBelote.FOLD);
        game_.ajouterContrat(contratTmp_,player_);
        player_ = game_.playerAfter(player_);
        assertTrue(game_.keepBidding());
        contratTmp_ = new BidBeloteSuit();
        contratTmp_.setBid(BidBelote.FOLD);
        game_.ajouterContrat(contratTmp_,player_);
        player_ = game_.playerAfter(player_);
        assertTrue(game_.keepBidding());
        CustList<BidBeloteSuit> bids_ = game_.getGameBeloteBid().allowedBids();
        CustList<BidBeloteSuit> expected_ = new CustList<BidBeloteSuit>();
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.FOLD);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.SUIT);
        b_.setSuit(Suit.DIAMOND);
        expected_.add(b_);
        expected_.addAllElts(toRealBid(new EnumList<BidBelote>(BidBelote.NO_TRUMP,BidBelote.ALL_TRUMP)));
        asserting(expected_,bids_);
    }
    @Test
    public void allowedBids_AtFirstRoundBidsInitializeByTakingBid2Test(){
        RulesBelote regles_=initializeRulesWithBids(new EnumList<BidBelote>(BidBelote.NO_TRUMP));
        GameBelote game_ = new GameBelote(GameType.RANDOM,initializeHands(),regles_);
        //game_.resetNbPlisTotal();
        byte player_ = game_.playerAfter(game_.getDistribution().getDealer());
        assertTrue(game_.keepBidding());
        BidBeloteSuit contratTmp_ = new BidBeloteSuit();
        contratTmp_.setBid(BidBelote.FOLD);
        game_.ajouterContrat(contratTmp_,player_);
        player_ = game_.playerAfter(player_);
        assertTrue(game_.keepBidding());
        contratTmp_ = new BidBeloteSuit();
        contratTmp_.setBid(BidBelote.FOLD);
        game_.ajouterContrat(contratTmp_,player_);
        player_ = game_.playerAfter(player_);
        assertTrue(game_.keepBidding());
        contratTmp_ = new BidBeloteSuit();
        contratTmp_.setBid(BidBelote.SUIT);
        contratTmp_.setSuit(Suit.DIAMOND);
        game_.ajouterContrat(contratTmp_,player_);
        assertTrue(game_.keepBidding());
        //game_.setContrat(contrat_tmp);
        CustList<BidBeloteSuit> bids_ = game_.getGameBeloteBid().allowedBids();
        CustList<BidBeloteSuit> expected_ = new CustList<BidBeloteSuit>();
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.FOLD);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.SUIT);
        b_.setSuit(Suit.DIAMOND);
        expected_.add(b_);
        CustList<BidBeloteSuit> bidsGr_ = toRealBid(new EnumList<BidBelote>(BidBelote.NO_TRUMP));
        expected_.addAllElts(bidsGr_);
        asserting(expected_,bids_);
        assertTrue(expected_.get(0).estDemandable(game_.getBid()));
        assertTrue(!expected_.get(1).estDemandable(game_.getBid()));
        assertTrue(bidsGr_.first().estDemandable(game_.getBid()));
        assertTrue(bidsGr_.last().estDemandable(game_.getBid()));
    }
    @Test
    public void allowedBids_AtFirstRoundBidsInitializeByTakingBid3Test(){
        RulesBelote regles_=initializeRulesWithBids(new EnumList<BidBelote>(BidBelote.ALL_TRUMP));
        GameBelote game_ = new GameBelote(GameType.RANDOM,initializeHands(),regles_);
        //game_.resetNbPlisTotal();
        byte player_ = game_.playerAfter(game_.getDistribution().getDealer());
        assertTrue(game_.keepBidding());
        BidBeloteSuit contratTmp_ = new BidBeloteSuit();
        contratTmp_.setBid(BidBelote.FOLD);
        game_.ajouterContrat(contratTmp_,player_);
        player_ = game_.playerAfter(player_);
        assertTrue(game_.keepBidding());
        contratTmp_ = new BidBeloteSuit();
        contratTmp_.setBid(BidBelote.FOLD);
        game_.ajouterContrat(contratTmp_,player_);
        player_ = game_.playerAfter(player_);
        assertTrue(game_.keepBidding());
        contratTmp_ = new BidBeloteSuit();
        contratTmp_.setBid(BidBelote.SUIT);
        contratTmp_.setSuit(Suit.DIAMOND);
        game_.ajouterContrat(contratTmp_,player_);
        assertTrue(game_.keepBidding());
        //game_.setContrat(contrat_tmp);
        CustList<BidBeloteSuit> bids_ = game_.getGameBeloteBid().allowedBids();
        CustList<BidBeloteSuit> expected_ = new CustList<BidBeloteSuit>();
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.FOLD);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.SUIT);
        b_.setSuit(Suit.DIAMOND);
        expected_.add(b_);
        CustList<BidBeloteSuit> bidsGr_ = toRealBid(new EnumList<BidBelote>(BidBelote.ALL_TRUMP));
        expected_.addAllElts(bidsGr_);
        asserting(expected_,bids_);
        assertTrue(expected_.get(0).estDemandable(game_.getBid()));
        assertTrue(!expected_.get(1).estDemandable(game_.getBid()));
        assertTrue(bidsGr_.first().estDemandable(game_.getBid()));
        assertTrue(bidsGr_.last().estDemandable(game_.getBid()));
    }
    @Test
    public void allowedBids_AtFirstRoundBidsInitializeByTakingBid4Test(){
        RulesBelote regles_=initializeRulesWithBids(new EnumList<BidBelote>(BidBelote.NO_TRUMP,BidBelote.ALL_TRUMP));
        GameBelote game_ = new GameBelote(GameType.RANDOM,initializeHands(),regles_);
        //game_.resetNbPlisTotal();
        byte player_ = game_.playerAfter(game_.getDistribution().getDealer());
        assertTrue(game_.keepBidding());
        BidBeloteSuit contratTmp_ = new BidBeloteSuit();
        contratTmp_.setBid(BidBelote.FOLD);
        game_.ajouterContrat(contratTmp_,player_);
        player_ = game_.playerAfter(player_);
        assertTrue(game_.keepBidding());
        contratTmp_ = new BidBeloteSuit();
        contratTmp_.setBid(BidBelote.FOLD);
        game_.ajouterContrat(contratTmp_,player_);
        player_ = game_.playerAfter(player_);
        assertTrue(game_.keepBidding());
        contratTmp_ = new BidBeloteSuit();
        contratTmp_.setBid(BidBelote.SUIT);
        contratTmp_.setSuit(Suit.DIAMOND);
        game_.ajouterContrat(contratTmp_,player_);
        assertTrue(game_.keepBidding());
        //game_.setContrat(contrat_tmp);
        CustList<BidBeloteSuit> bids_ = game_.getGameBeloteBid().allowedBids();
        CustList<BidBeloteSuit> expected_ = new CustList<BidBeloteSuit>();
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.FOLD);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.SUIT);
        b_.setSuit(Suit.DIAMOND);
        expected_.add(b_);
        CustList<BidBeloteSuit> bidsGr_ = toRealBid(new EnumList<BidBelote>(BidBelote.NO_TRUMP,BidBelote.ALL_TRUMP));
        expected_.addAllElts(bidsGr_);
        asserting(expected_,bids_);
        assertTrue(expected_.get(0).estDemandable(game_.getBid()));
        assertTrue(!expected_.get(1).estDemandable(game_.getBid()));
        assertTrue(bidsGr_.first().estDemandable(game_.getBid()));
        assertTrue(bidsGr_.last().estDemandable(game_.getBid()));
    }
    @Test
    public void allowedBids_AtSecondRoundBidsInitialize2Test(){
        RulesBelote regles_=initializeRulesWithBids(new EnumList<BidBelote>(BidBelote.NO_TRUMP));
        GameBelote game_ = new GameBelote(GameType.RANDOM,initializeHands(),regles_);
        //game_.resetNbPlisTotal();
        game_.finEncherePremierTour();
        CustList<BidBeloteSuit> bids_ = game_.getGameBeloteBid().allowedBids();
        CustList<BidBeloteSuit> expected_ = new CustList<BidBeloteSuit>();
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.FOLD);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.OTHER_SUIT);
        b_.setSuit(Suit.HEART);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.OTHER_SUIT);
        b_.setSuit(Suit.SPADE);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.OTHER_SUIT);
        b_.setSuit(Suit.CLUB);
        expected_.add(b_);
        expected_.addAllElts(toRealBid(new EnumList<BidBelote>(BidBelote.NO_TRUMP)));
        assertEqSet(bids_, expected_);
    }
    @Test
    public void allowedBids_AtSecondRoundBidsInitialize3Test(){
        RulesBelote regles_=initializeRulesWithBids(new EnumList<BidBelote>(BidBelote.ALL_TRUMP));
        GameBelote game_ = new GameBelote(GameType.RANDOM,initializeHands(),regles_);
        //game_.resetNbPlisTotal();
        game_.finEncherePremierTour();
        CustList<BidBeloteSuit> bids_ = game_.getGameBeloteBid().allowedBids();
        CustList<BidBeloteSuit> expected_ = new CustList<BidBeloteSuit>();
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.FOLD);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.OTHER_SUIT);
        b_.setSuit(Suit.HEART);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.OTHER_SUIT);
        b_.setSuit(Suit.SPADE);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.OTHER_SUIT);
        b_.setSuit(Suit.CLUB);
        expected_.add(b_);
        expected_.addAllElts(toRealBid(new EnumList<BidBelote>(BidBelote.ALL_TRUMP)));
        assertEqSet(bids_, expected_);
    }
    @Test
    public void allowedBids_AtSecondRoundBidsInitialize4Test(){
        RulesBelote regles_=initializeRulesWithBids(new EnumList<BidBelote>(BidBelote.NO_TRUMP,BidBelote.ALL_TRUMP));
        GameBelote game_ = new GameBelote(GameType.RANDOM,initializeHands(),regles_);
        //game_.resetNbPlisTotal();
        game_.finEncherePremierTour();
        CustList<BidBeloteSuit> bids_ = game_.getGameBeloteBid().allowedBids();
        CustList<BidBeloteSuit> expected_ = new CustList<BidBeloteSuit>();
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.FOLD);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.OTHER_SUIT);
        b_.setSuit(Suit.HEART);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.OTHER_SUIT);
        b_.setSuit(Suit.SPADE);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.OTHER_SUIT);
        b_.setSuit(Suit.CLUB);
        expected_.add(b_);
        expected_.addAllElts(toRealBid(new EnumList<BidBelote>(BidBelote.NO_TRUMP,BidBelote.ALL_TRUMP)));
        assertEqSet(bids_, expected_);
    }
    @Test
    public void allowedBids_AtSecondRoundBidsInitializeWithoutTakingBid2Test(){
        RulesBelote regles_=initializeRulesWithBids(new EnumList<BidBelote>(BidBelote.NO_TRUMP));
        GameBelote game_ = new GameBelote(GameType.RANDOM,initializeHands(),regles_);
        //game_.resetNbPlisTotal();
        byte player_ = game_.playerAfter(game_.getDistribution().getDealer());
        assertTrue(game_.keepBidding());
        BidBeloteSuit contratTmp_ = new BidBeloteSuit();
        contratTmp_.setBid(BidBelote.FOLD);
        game_.ajouterContrat(contratTmp_,player_);
        player_ = game_.playerAfter(player_);
        assertTrue(game_.keepBidding());
        contratTmp_ = new BidBeloteSuit();
        contratTmp_.setBid(BidBelote.FOLD);
        game_.ajouterContrat(contratTmp_,player_);
        player_ = game_.playerAfter(player_);
        assertTrue(game_.keepBidding());
        contratTmp_ = new BidBeloteSuit();
        contratTmp_.setBid(BidBelote.FOLD);
        game_.ajouterContrat(contratTmp_,player_);
        player_ = game_.playerAfter(player_);
        assertTrue(game_.keepBidding());
        contratTmp_ = new BidBeloteSuit();
        contratTmp_.setBid(BidBelote.FOLD);
        game_.ajouterContrat(contratTmp_,player_);
        game_.finEncherePremierTour();
        player_ = game_.playerAfter(player_);
        assertTrue(game_.keepBidding());
        contratTmp_ = new BidBeloteSuit();
        contratTmp_.setBid(BidBelote.FOLD);
        game_.ajouterContrat(contratTmp_,player_);
        assertTrue(game_.keepBidding());
        CustList<BidBeloteSuit> bids_ = game_.getGameBeloteBid().allowedBids();
        CustList<BidBeloteSuit> expected_ = new CustList<BidBeloteSuit>();
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.FOLD);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.OTHER_SUIT);
        b_.setSuit(Suit.HEART);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.OTHER_SUIT);
        b_.setSuit(Suit.SPADE);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.OTHER_SUIT);
        b_.setSuit(Suit.CLUB);
        expected_.add(b_);
        expected_.addAllElts(toRealBid(new EnumList<BidBelote>(BidBelote.NO_TRUMP)));
        assertEqSet(bids_, expected_);
    }
    @Test
    public void allowedBids_AtSecondRoundBidsInitializeWithoutTakingBid3Test(){
        RulesBelote regles_=initializeRulesWithBids(new EnumList<BidBelote>(BidBelote.ALL_TRUMP));
        GameBelote game_ = new GameBelote(GameType.RANDOM,initializeHands(),regles_);
        //game_.resetNbPlisTotal();
        byte player_ = game_.playerAfter(game_.getDistribution().getDealer());
        assertTrue(game_.keepBidding());
        BidBeloteSuit contratTmp_ = new BidBeloteSuit();
        contratTmp_.setBid(BidBelote.FOLD);
        game_.ajouterContrat(contratTmp_,player_);
        player_ = game_.playerAfter(player_);
        assertTrue(game_.keepBidding());
        contratTmp_ = new BidBeloteSuit();
        contratTmp_.setBid(BidBelote.FOLD);
        game_.ajouterContrat(contratTmp_,player_);
        player_ = game_.playerAfter(player_);
        assertTrue(game_.keepBidding());
        contratTmp_ = new BidBeloteSuit();
        contratTmp_.setBid(BidBelote.FOLD);
        game_.ajouterContrat(contratTmp_,player_);
        player_ = game_.playerAfter(player_);
        assertTrue(game_.keepBidding());
        contratTmp_ = new BidBeloteSuit();
        contratTmp_.setBid(BidBelote.FOLD);
        game_.ajouterContrat(contratTmp_,player_);
        game_.finEncherePremierTour();
        player_ = game_.playerAfter(player_);
        assertTrue(game_.keepBidding());
        contratTmp_ = new BidBeloteSuit();
        contratTmp_.setBid(BidBelote.FOLD);
        game_.ajouterContrat(contratTmp_,player_);
        assertTrue(game_.keepBidding());
        CustList<BidBeloteSuit> bids_ = game_.getGameBeloteBid().allowedBids();
        CustList<BidBeloteSuit> expected_ = new CustList<BidBeloteSuit>();
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.FOLD);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.OTHER_SUIT);
        b_.setSuit(Suit.HEART);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.OTHER_SUIT);
        b_.setSuit(Suit.SPADE);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.OTHER_SUIT);
        b_.setSuit(Suit.CLUB);
        expected_.add(b_);
        expected_.addAllElts(toRealBid(new EnumList<BidBelote>(BidBelote.ALL_TRUMP)));
        assertEqSet(bids_, expected_);
    }

    @Test
    public void allowedBids_AtSecondRoundBidsInitializeWithoutTakingBid4Test(){
        RulesBelote regles_=initializeRulesWithBids(new EnumList<BidBelote>(BidBelote.NO_TRUMP,BidBelote.ALL_TRUMP));
        GameBelote game_ = new GameBelote(GameType.RANDOM,initializeHands(),regles_);
        //game_.resetNbPlisTotal();
        byte player_ = game_.playerAfter(game_.getDistribution().getDealer());
        assertTrue(game_.keepBidding());
        BidBeloteSuit contratTmp_ = new BidBeloteSuit();
        contratTmp_.setBid(BidBelote.FOLD);
        game_.ajouterContrat(contratTmp_,player_);
        player_ = game_.playerAfter(player_);
        assertTrue(game_.keepBidding());
        contratTmp_ = new BidBeloteSuit();
        contratTmp_.setBid(BidBelote.FOLD);
        game_.ajouterContrat(contratTmp_,player_);
        player_ = game_.playerAfter(player_);
        assertTrue(game_.keepBidding());
        contratTmp_ = new BidBeloteSuit();
        contratTmp_.setBid(BidBelote.FOLD);
        game_.ajouterContrat(contratTmp_,player_);
        player_ = game_.playerAfter(player_);
        assertTrue(game_.keepBidding());
        contratTmp_ = new BidBeloteSuit();
        contratTmp_.setBid(BidBelote.FOLD);
        game_.ajouterContrat(contratTmp_,player_);
        game_.finEncherePremierTour();
        player_ = game_.playerAfter(player_);
        assertTrue(game_.keepBidding());
        contratTmp_ = new BidBeloteSuit();
        contratTmp_.setBid(BidBelote.FOLD);
        game_.ajouterContrat(contratTmp_,player_);
        assertTrue(game_.keepBidding());
        CustList<BidBeloteSuit> bids_ = game_.getGameBeloteBid().allowedBids();
        CustList<BidBeloteSuit> expected_ = new CustList<BidBeloteSuit>();
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.FOLD);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.OTHER_SUIT);
        b_.setSuit(Suit.HEART);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.OTHER_SUIT);
        b_.setSuit(Suit.SPADE);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.OTHER_SUIT);
        b_.setSuit(Suit.CLUB);
        expected_.add(b_);
        expected_.addAllElts(toRealBid(new EnumList<BidBelote>(BidBelote.NO_TRUMP,BidBelote.ALL_TRUMP)));
        assertEqSet(bids_, expected_);
    }
    @Test
    public void allowedBids_AtSecondRoundBidsInitializeByTakingBid2Test(){
        RulesBelote regles_=initializeRulesWithBids(new EnumList<BidBelote>(BidBelote.NO_TRUMP));
        GameBelote game_ = new GameBelote(GameType.RANDOM,initializeHands(),regles_);
        //game_.resetNbPlisTotal();
        byte player_ = game_.playerAfter(game_.getDistribution().getDealer());
        assertTrue(game_.keepBidding());
        BidBeloteSuit contratTmp_ = new BidBeloteSuit();
        contratTmp_.setBid(BidBelote.FOLD);
        game_.ajouterContrat(contratTmp_,player_);
        player_ = game_.playerAfter(player_);
        assertTrue(game_.keepBidding());
        contratTmp_ = new BidBeloteSuit();
        contratTmp_.setBid(BidBelote.FOLD);
        game_.ajouterContrat(contratTmp_,player_);
        player_ = game_.playerAfter(player_);
        assertTrue(game_.keepBidding());
        contratTmp_ = new BidBeloteSuit();
        contratTmp_.setBid(BidBelote.FOLD);
        game_.ajouterContrat(contratTmp_,player_);
        player_ = game_.playerAfter(player_);
        assertTrue(game_.keepBidding());
        contratTmp_ = new BidBeloteSuit();
        contratTmp_.setBid(BidBelote.FOLD);
        game_.ajouterContrat(contratTmp_,player_);
        game_.finEncherePremierTour();
        player_ = game_.playerAfter(player_);
        assertTrue(game_.keepBidding());
        contratTmp_ = new BidBeloteSuit();
        contratTmp_.setBid(BidBelote.FOLD);
        game_.ajouterContrat(contratTmp_,player_);
        player_ = game_.playerAfter(player_);
        assertTrue(game_.keepBidding());
        contratTmp_ = new BidBeloteSuit();
        contratTmp_.setBid(BidBelote.OTHER_SUIT);
        contratTmp_.setSuit(Suit.HEART);
        game_.ajouterContrat(contratTmp_,player_);
        assertTrue(game_.keepBidding());
        //game_.setContrat(contrat_tmp);
        CustList<BidBeloteSuit> bids_ = game_.getGameBeloteBid().allowedBids();
        CustList<BidBeloteSuit> expected_ = new CustList<BidBeloteSuit>();
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.FOLD);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.OTHER_SUIT);
        b_.setSuit(Suit.HEART);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.OTHER_SUIT);
        b_.setSuit(Suit.SPADE);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.OTHER_SUIT);
        b_.setSuit(Suit.CLUB);
        expected_.add(b_);
        CustList<BidBeloteSuit> bidsGr_ = toRealBid(new EnumList<BidBelote>(BidBelote.NO_TRUMP));
        expected_.addAllElts(bidsGr_);
        asserting(expected_,bids_);
        assertTrue(expected_.get(0).estDemandable(game_.getBid()));
        assertTrue(!expected_.get(1).estDemandable(game_.getBid()));
        assertTrue(!expected_.get(2).estDemandable(game_.getBid()));
        assertTrue(!expected_.get(3).estDemandable(game_.getBid()));
        assertTrue(bidsGr_.first().estDemandable(game_.getBid()));
        assertTrue(bidsGr_.last().estDemandable(game_.getBid()));
    }
    @Test
    public void allowedBids_AtSecondRoundBidsInitializeByTakingBid3Test(){
        RulesBelote regles_=initializeRulesWithBids(new EnumList<BidBelote>(BidBelote.ALL_TRUMP));
        GameBelote game_ = new GameBelote(GameType.RANDOM,initializeHands(),regles_);
        //game_.resetNbPlisTotal();
        byte player_ = game_.playerAfter(game_.getDistribution().getDealer());
        assertTrue(game_.keepBidding());
        BidBeloteSuit contratTmp_ = new BidBeloteSuit();
        contratTmp_.setBid(BidBelote.FOLD);
        game_.ajouterContrat(contratTmp_,player_);
        player_ = game_.playerAfter(player_);
        assertTrue(game_.keepBidding());
        contratTmp_ = new BidBeloteSuit();
        contratTmp_.setBid(BidBelote.FOLD);
        game_.ajouterContrat(contratTmp_,player_);
        player_ = game_.playerAfter(player_);
        assertTrue(game_.keepBidding());
        contratTmp_ = new BidBeloteSuit();
        contratTmp_.setBid(BidBelote.FOLD);
        game_.ajouterContrat(contratTmp_,player_);
        player_ = game_.playerAfter(player_);
        assertTrue(game_.keepBidding());
        contratTmp_ = new BidBeloteSuit();
        contratTmp_.setBid(BidBelote.FOLD);
        game_.ajouterContrat(contratTmp_,player_);
        game_.finEncherePremierTour();
        player_ = game_.playerAfter(player_);
        assertTrue(game_.keepBidding());
        contratTmp_ = new BidBeloteSuit();
        contratTmp_.setBid(BidBelote.FOLD);
        game_.ajouterContrat(contratTmp_,player_);
        player_ = game_.playerAfter(player_);
        assertTrue(game_.keepBidding());
        contratTmp_ = new BidBeloteSuit();
        contratTmp_.setBid(BidBelote.OTHER_SUIT);
        contratTmp_.setSuit(Suit.HEART);
        game_.ajouterContrat(contratTmp_,player_);
        assertTrue(game_.keepBidding());
        //game_.setContrat(contrat_tmp);
        CustList<BidBeloteSuit> bids_ = game_.getGameBeloteBid().allowedBids();
        CustList<BidBeloteSuit> expected_ = new CustList<BidBeloteSuit>();
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.FOLD);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.OTHER_SUIT);
        b_.setSuit(Suit.HEART);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.OTHER_SUIT);
        b_.setSuit(Suit.SPADE);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.OTHER_SUIT);
        b_.setSuit(Suit.CLUB);
        expected_.add(b_);
        CustList<BidBeloteSuit> bidsGr_ = toRealBid(new EnumList<BidBelote>(BidBelote.ALL_TRUMP));
        expected_.addAllElts(bidsGr_);
        asserting(expected_,bids_);
        assertTrue(expected_.get(0).estDemandable(game_.getBid()));
        assertTrue(!expected_.get(1).estDemandable(game_.getBid()));
        assertTrue(!expected_.get(2).estDemandable(game_.getBid()));
        assertTrue(!expected_.get(3).estDemandable(game_.getBid()));
        assertTrue(bidsGr_.first().estDemandable(game_.getBid()));
        assertTrue(bidsGr_.last().estDemandable(game_.getBid()));
    }
    @Test
    public void allowedBids_AtSecondRoundBidsInitializeByTakingBid4Test(){
        RulesBelote regles_=initializeRulesWithBids(new EnumList<BidBelote>(BidBelote.NO_TRUMP,BidBelote.ALL_TRUMP));
        GameBelote game_ = new GameBelote(GameType.RANDOM,initializeHands(),regles_);
        //game_.resetNbPlisTotal();
        byte player_ = game_.playerAfter(game_.getDistribution().getDealer());
        assertTrue(game_.keepBidding());
        BidBeloteSuit contratTmp_ = new BidBeloteSuit();
        contratTmp_.setBid(BidBelote.FOLD);
        game_.ajouterContrat(contratTmp_,player_);
        player_ = game_.playerAfter(player_);
        assertTrue(game_.keepBidding());
        contratTmp_ = new BidBeloteSuit();
        contratTmp_.setBid(BidBelote.FOLD);
        game_.ajouterContrat(contratTmp_,player_);
        player_ = game_.playerAfter(player_);
        assertTrue(game_.keepBidding());
        contratTmp_ = new BidBeloteSuit();
        contratTmp_.setBid(BidBelote.FOLD);
        game_.ajouterContrat(contratTmp_,player_);
        player_ = game_.playerAfter(player_);
        assertTrue(game_.keepBidding());
        contratTmp_ = new BidBeloteSuit();
        contratTmp_.setBid(BidBelote.FOLD);
        game_.ajouterContrat(contratTmp_,player_);
        game_.finEncherePremierTour();
        player_ = game_.playerAfter(player_);
        assertTrue(game_.keepBidding());
        contratTmp_ = new BidBeloteSuit();
        contratTmp_.setBid(BidBelote.FOLD);
        game_.ajouterContrat(contratTmp_,player_);
        player_ = game_.playerAfter(player_);
        assertTrue(game_.keepBidding());
        contratTmp_ = new BidBeloteSuit();
        contratTmp_.setBid(BidBelote.OTHER_SUIT);
        contratTmp_.setSuit(Suit.HEART);
        game_.ajouterContrat(contratTmp_,player_);
        assertTrue(game_.keepBidding());
        //game_.setContrat(contrat_tmp);
        CustList<BidBeloteSuit> bids_ = game_.getGameBeloteBid().allowedBids();
        CustList<BidBeloteSuit> expected_ = new CustList<BidBeloteSuit>();
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.FOLD);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.OTHER_SUIT);
        b_.setSuit(Suit.HEART);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.OTHER_SUIT);
        b_.setSuit(Suit.SPADE);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.OTHER_SUIT);
        b_.setSuit(Suit.CLUB);
        expected_.add(b_);
        CustList<BidBeloteSuit> bidsGr_ = toRealBid(new EnumList<BidBelote>(BidBelote.NO_TRUMP,BidBelote.ALL_TRUMP));
        expected_.addAllElts(bidsGr_);
        asserting(expected_,bids_);
        assertTrue(expected_.get(0).estDemandable(game_.getBid()));
        assertTrue(!expected_.get(1).estDemandable(game_.getBid()));
        assertTrue(!expected_.get(2).estDemandable(game_.getBid()));
        assertTrue(!expected_.get(3).estDemandable(game_.getBid()));
        assertTrue(bidsGr_.first().estDemandable(game_.getBid()));
        assertTrue(bidsGr_.last().estDemandable(game_.getBid()));
    }
    @Test
    public void allowedBids_AtSecondRoundBidsInitializePassing2Test(){
        RulesBelote regles_=initializeRulesWithBids(new EnumList<BidBelote>(BidBelote.NO_TRUMP));
        GameBelote game_ = new GameBelote(GameType.RANDOM,initializeHands(),regles_);
        //game_.resetNbPlisTotal();
        byte player_ = game_.playerAfter(game_.getDistribution().getDealer());
        assertTrue(game_.keepBidding());
        BidBeloteSuit contratTmp_ = new BidBeloteSuit();
        contratTmp_.setBid(BidBelote.FOLD);
        game_.ajouterContrat(contratTmp_,player_);
        player_ = game_.playerAfter(player_);
        assertTrue(game_.keepBidding());
        contratTmp_ = new BidBeloteSuit();
        contratTmp_.setBid(BidBelote.FOLD);
        game_.ajouterContrat(contratTmp_,player_);
        player_ = game_.playerAfter(player_);
        assertTrue(game_.keepBidding());
        contratTmp_ = new BidBeloteSuit();
        contratTmp_.setBid(BidBelote.FOLD);
        game_.ajouterContrat(contratTmp_,player_);
        player_ = game_.playerAfter(player_);
        assertTrue(game_.keepBidding());
        contratTmp_ = new BidBeloteSuit();
        contratTmp_.setBid(BidBelote.FOLD);
        game_.ajouterContrat(contratTmp_,player_);
        player_ = game_.playerAfter(player_);
        game_.finEncherePremierTour();
        contratTmp_ = new BidBeloteSuit();
        contratTmp_.setBid(BidBelote.FOLD);
        game_.ajouterContrat(contratTmp_,player_);
        player_ = game_.playerAfter(player_);
        assertTrue(game_.keepBidding());
        contratTmp_ = new BidBeloteSuit();
        contratTmp_.setBid(BidBelote.FOLD);
        game_.ajouterContrat(contratTmp_,player_);
        player_ = game_.playerAfter(player_);
        assertTrue(game_.keepBidding());
        contratTmp_ = new BidBeloteSuit();
        contratTmp_.setBid(BidBelote.FOLD);
        game_.ajouterContrat(contratTmp_,player_);
        player_ = game_.playerAfter(player_);
        assertTrue(game_.keepBidding());
        contratTmp_ = new BidBeloteSuit();
        contratTmp_.setBid(BidBelote.FOLD);
        game_.ajouterContrat(contratTmp_,player_);
        assertTrue(!game_.keepBidding());
    }
    @Test
    public void allowedBids_AtSecondRoundBidsInitializePassing3Test(){
        RulesBelote regles_=initializeRulesWithBids(new EnumList<BidBelote>(BidBelote.ALL_TRUMP));
        GameBelote game_ = new GameBelote(GameType.RANDOM,initializeHands(),regles_);
        //game_.resetNbPlisTotal();
        byte player_ = game_.playerAfter(game_.getDistribution().getDealer());
        assertTrue(game_.keepBidding());
        BidBeloteSuit contratTmp_ = new BidBeloteSuit();
        contratTmp_.setBid(BidBelote.FOLD);
        game_.ajouterContrat(contratTmp_,player_);
        player_ = game_.playerAfter(player_);
        assertTrue(game_.keepBidding());
        contratTmp_ = new BidBeloteSuit();
        contratTmp_.setBid(BidBelote.FOLD);
        game_.ajouterContrat(contratTmp_,player_);
        player_ = game_.playerAfter(player_);
        assertTrue(game_.keepBidding());
        contratTmp_ = new BidBeloteSuit();
        contratTmp_.setBid(BidBelote.FOLD);
        game_.ajouterContrat(contratTmp_,player_);
        player_ = game_.playerAfter(player_);
        assertTrue(game_.keepBidding());
        contratTmp_ = new BidBeloteSuit();
        contratTmp_.setBid(BidBelote.FOLD);
        game_.ajouterContrat(contratTmp_,player_);
        player_ = game_.playerAfter(player_);
        game_.finEncherePremierTour();
        contratTmp_ = new BidBeloteSuit();
        contratTmp_.setBid(BidBelote.FOLD);
        game_.ajouterContrat(contratTmp_,player_);
        player_ = game_.playerAfter(player_);
        assertTrue(game_.keepBidding());
        contratTmp_ = new BidBeloteSuit();
        contratTmp_.setBid(BidBelote.FOLD);
        game_.ajouterContrat(contratTmp_,player_);
        player_ = game_.playerAfter(player_);
        assertTrue(game_.keepBidding());
        contratTmp_ = new BidBeloteSuit();
        contratTmp_.setBid(BidBelote.FOLD);
        game_.ajouterContrat(contratTmp_,player_);
        player_ = game_.playerAfter(player_);
        assertTrue(game_.keepBidding());
        contratTmp_ = new BidBeloteSuit();
        contratTmp_.setBid(BidBelote.FOLD);
        game_.ajouterContrat(contratTmp_,player_);
        assertTrue(!game_.keepBidding());
    }
    @Test
    public void allowedBids_AtSecondRoundBidsInitializePassing4Test(){
        RulesBelote regles_=initializeRulesWithBids(new EnumList<BidBelote>(BidBelote.NO_TRUMP,BidBelote.ALL_TRUMP));
        GameBelote game_ = new GameBelote(GameType.RANDOM,initializeHands(),regles_);
        //game_.resetNbPlisTotal();
        byte player_ = game_.playerAfter(game_.getDistribution().getDealer());
        assertTrue(game_.keepBidding());
        BidBeloteSuit contratTmp_ = new BidBeloteSuit();
        contratTmp_.setBid(BidBelote.FOLD);
        game_.ajouterContrat(contratTmp_,player_);
        player_ = game_.playerAfter(player_);
        assertTrue(game_.keepBidding());
        contratTmp_ = new BidBeloteSuit();
        contratTmp_.setBid(BidBelote.FOLD);
        game_.ajouterContrat(contratTmp_,player_);
        player_ = game_.playerAfter(player_);
        assertTrue(game_.keepBidding());
        contratTmp_ = new BidBeloteSuit();
        contratTmp_.setBid(BidBelote.FOLD);
        game_.ajouterContrat(contratTmp_,player_);
        player_ = game_.playerAfter(player_);
        assertTrue(game_.keepBidding());
        contratTmp_ = new BidBeloteSuit();
        contratTmp_.setBid(BidBelote.FOLD);
        game_.ajouterContrat(contratTmp_,player_);
        player_ = game_.playerAfter(player_);
        game_.finEncherePremierTour();
        contratTmp_ = new BidBeloteSuit();
        contratTmp_.setBid(BidBelote.FOLD);
        game_.ajouterContrat(contratTmp_,player_);
        player_ = game_.playerAfter(player_);
        assertTrue(game_.keepBidding());
        contratTmp_ = new BidBeloteSuit();
        contratTmp_.setBid(BidBelote.FOLD);
        game_.ajouterContrat(contratTmp_,player_);
        player_ = game_.playerAfter(player_);
        assertTrue(game_.keepBidding());
        contratTmp_ = new BidBeloteSuit();
        contratTmp_.setBid(BidBelote.FOLD);
        game_.ajouterContrat(contratTmp_,player_);
        player_ = game_.playerAfter(player_);
        assertTrue(game_.keepBidding());
        contratTmp_ = new BidBeloteSuit();
        contratTmp_.setBid(BidBelote.FOLD);
        game_.ajouterContrat(contratTmp_,player_);
        assertTrue(!game_.keepBidding());
    }


    private static CustList<BidBeloteSuit> toRealBid(EnumList<BidBelote> _bids) {
        CustList<BidBeloteSuit> bids_ = new CustList<BidBeloteSuit>();
        for (BidBelote e: _bids) {
            BidBeloteSuit bid_ = new BidBeloteSuit();
            bid_.setBid(e);
            bids_.add(bid_);
        }
        return bids_;
    }
    @Test
    public void maximumBid_AtFirstRound2(){
        RulesBelote regles_=initializeRulesWithBids(new EnumList<BidBelote>(BidBelote.NO_TRUMP));
        GameBelote game_ = new GameBelote(GameType.RANDOM,initializeHands(),regles_);
        //game_.resetNbPlisTotal();
        CustList<BidBeloteSuit> maximunBidsSuits_ = game_.maximumBid();
        BidBeloteSuit b_;
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.NO_TRUMP);
        assertEq(1, maximunBidsSuits_.size());
        assertTrue(containsBid(maximunBidsSuits_, b_));
    }

    @Test
    public void maximumBid_AtFirstRound3Test(){
        RulesBelote regles_=initializeRulesWithBids(new EnumList<BidBelote>(BidBelote.ALL_TRUMP));
        GameBelote game_ = new GameBelote(GameType.RANDOM,initializeHands(),regles_);
        //game_.resetNbPlisTotal();
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.ALL_TRUMP);
        CustList<BidBeloteSuit> maximunBidsSuits_ = game_.maximumBid();
        assertEq(1, maximunBidsSuits_.size());
        assertTrue(containsBid(maximunBidsSuits_, b_));
    }
    @Test
    public void maximumBid_AtFirstRound4Test(){
        RulesBelote regles_=initializeRulesWithBids(new EnumList<BidBelote>(BidBelote.NO_TRUMP,BidBelote.ALL_TRUMP));
        GameBelote game_ = new GameBelote(GameType.RANDOM,initializeHands(),regles_);
        //game_.resetNbPlisTotal();
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.ALL_TRUMP);
        CustList<BidBeloteSuit> maximunBidsSuits_ = game_.maximumBid();
        assertEq(1, maximunBidsSuits_.size());
        assertTrue(containsBid(maximunBidsSuits_, b_));
    }
    @Test
    public void maximumBid_AtSecondRound2(){
        RulesBelote regles_=initializeRulesWithBids(new EnumList<BidBelote>(BidBelote.NO_TRUMP));
        GameBelote game_ = new GameBelote(GameType.RANDOM,initializeHands(),regles_);
        //game_.resetNbPlisTotal();
        game_.finEncherePremierTour();
        CustList<BidBeloteSuit> maximunBidsSuits_ = game_.maximumBid();
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.NO_TRUMP);
        assertEq(1, maximunBidsSuits_.size());
        assertTrue(containsBid(maximunBidsSuits_, b_));
    }
    @Test
    public void maximumBid_AtSecondRound3Test(){
        RulesBelote regles_=initializeRulesWithBids(new EnumList<BidBelote>(BidBelote.ALL_TRUMP));
        GameBelote game_ = new GameBelote(GameType.RANDOM,initializeHands(),regles_);
        //game_.resetNbPlisTotal();
        game_.finEncherePremierTour();
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.ALL_TRUMP);
        CustList<BidBeloteSuit> maximunBidsSuits_ = game_.maximumBid();
        assertEq(1, maximunBidsSuits_.size());
        assertTrue(containsBid(maximunBidsSuits_, b_));
    }
    @Test
    public void maximumBid_AtSecondRound4Test(){
        RulesBelote regles_=initializeRulesWithBids(new EnumList<BidBelote>(BidBelote.NO_TRUMP,BidBelote.ALL_TRUMP));
        GameBelote game_ = new GameBelote(GameType.RANDOM,initializeHands(),regles_);
        //game_.resetNbPlisTotal();
        game_.finEncherePremierTour();
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.ALL_TRUMP);
        CustList<BidBeloteSuit> maximunBidsSuits_ = game_.maximumBid();
        assertEq(1, maximunBidsSuits_.size());
        assertTrue(containsBid(maximunBidsSuits_, b_));
    }
    @Test
    public void allowedBids1(){
        RulesBelote regles_=initializeRulesWithBidPoints(false);
        regles_.getAllowedBids().put(BidBelote.NO_TRUMP, BoolVal.TRUE);
        GameBelote game_ = new GameBelote(GameType.RANDOM,initializeHands(),regles_);
        //game_.resetNbPlisTotal();
        CustList<BidBeloteSuit> bids_ = game_.getGameBeloteBid().allowedBids();
        CustList<BidBeloteSuit> expected_ = new CustList<BidBeloteSuit>();
        BidBeloteSuit b_;
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.SUIT);
        b_.setSuit(Suit.HEART);
        b_.setPoints(80);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.SUIT);
        b_.setSuit(Suit.SPADE);
        b_.setPoints(80);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.SUIT);
        b_.setSuit(Suit.DIAMOND);
        b_.setPoints(80);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.SUIT);
        b_.setSuit(Suit.CLUB);
        b_.setPoints(80);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.SUIT);
        b_.setSuit(Suit.HEART);
        b_.setPoints(90);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.SUIT);
        b_.setSuit(Suit.SPADE);
        b_.setPoints(90);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.SUIT);
        b_.setSuit(Suit.DIAMOND);
        b_.setPoints(90);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.SUIT);
        b_.setSuit(Suit.CLUB);
        b_.setPoints(90);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.SUIT);
        b_.setSuit(Suit.HEART);
        b_.setPoints(100);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.SUIT);
        b_.setSuit(Suit.SPADE);
        b_.setPoints(100);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.SUIT);
        b_.setSuit(Suit.DIAMOND);
        b_.setPoints(100);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.SUIT);
        b_.setSuit(Suit.CLUB);
        b_.setPoints(100);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.SUIT);
        b_.setSuit(Suit.HEART);
        b_.setPoints(110);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.SUIT);
        b_.setSuit(Suit.SPADE);
        b_.setPoints(110);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.SUIT);
        b_.setSuit(Suit.DIAMOND);
        b_.setPoints(110);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.SUIT);
        b_.setSuit(Suit.CLUB);
        b_.setPoints(110);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.SUIT);
        b_.setSuit(Suit.HEART);
        b_.setPoints(120);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.SUIT);
        b_.setSuit(Suit.SPADE);
        b_.setPoints(120);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.SUIT);
        b_.setSuit(Suit.DIAMOND);
        b_.setPoints(120);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.SUIT);
        b_.setSuit(Suit.CLUB);
        b_.setPoints(120);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.SUIT);
        b_.setSuit(Suit.HEART);
        b_.setPoints(130);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.SUIT);
        b_.setSuit(Suit.SPADE);
        b_.setPoints(130);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.SUIT);
        b_.setSuit(Suit.DIAMOND);
        b_.setPoints(130);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.SUIT);
        b_.setSuit(Suit.CLUB);
        b_.setPoints(130);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.SUIT);
        b_.setSuit(Suit.HEART);
        b_.setPoints(140);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.SUIT);
        b_.setSuit(Suit.SPADE);
        b_.setPoints(140);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.SUIT);
        b_.setSuit(Suit.DIAMOND);
        b_.setPoints(140);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.SUIT);
        b_.setSuit(Suit.CLUB);
        b_.setPoints(140);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.SUIT);
        b_.setSuit(Suit.HEART);
        b_.setPoints(150);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.SUIT);
        b_.setSuit(Suit.SPADE);
        b_.setPoints(150);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.SUIT);
        b_.setSuit(Suit.DIAMOND);
        b_.setPoints(150);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.SUIT);
        b_.setSuit(Suit.CLUB);
        b_.setPoints(150);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.SUIT);
        b_.setSuit(Suit.HEART);
        b_.setPoints(160);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.SUIT);
        b_.setSuit(Suit.SPADE);
        b_.setPoints(160);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.SUIT);
        b_.setSuit(Suit.DIAMOND);
        b_.setPoints(160);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.SUIT);
        b_.setSuit(Suit.CLUB);
        b_.setPoints(160);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.SUIT);
        b_.setSuit(Suit.HEART);
        b_.setPoints(162);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.SUIT);
        b_.setSuit(Suit.SPADE);
        b_.setPoints(162);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.SUIT);
        b_.setSuit(Suit.DIAMOND);
        b_.setPoints(162);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.SUIT);
        b_.setSuit(Suit.CLUB);
        b_.setPoints(162);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.NO_TRUMP);
        b_.setSuit(Suit.UNDEFINED);
        b_.setPoints(80);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.NO_TRUMP);
        b_.setSuit(Suit.UNDEFINED);
        b_.setPoints(90);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.NO_TRUMP);
        b_.setSuit(Suit.UNDEFINED);
        b_.setPoints(100);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.NO_TRUMP);
        b_.setSuit(Suit.UNDEFINED);
        b_.setPoints(110);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.NO_TRUMP);
        b_.setSuit(Suit.UNDEFINED);
        b_.setPoints(120);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.NO_TRUMP);
        b_.setSuit(Suit.UNDEFINED);
        b_.setPoints(130);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.NO_TRUMP);
        b_.setSuit(Suit.UNDEFINED);
        b_.setPoints(140);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.NO_TRUMP);
        b_.setSuit(Suit.UNDEFINED);
        b_.setPoints(150);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.NO_TRUMP);
        b_.setSuit(Suit.UNDEFINED);
        b_.setPoints(160);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.NO_TRUMP);
        b_.setSuit(Suit.UNDEFINED);
        b_.setPoints(162);
        expected_.add(b_);
        assertEqSet(bids_, expected_);
    }
    @Test
    public void allowedBids2(){
        RulesBelote regles_=initializeRulesWithBidPoints(false);
        regles_.getAllowedBids().put(BidBelote.NO_TRUMP,BoolVal.TRUE);
        GameBelote game_ = new GameBelote(GameType.RANDOM,initializeHands(),regles_);
        BidBeloteSuit contratTmp_ = new BidBeloteSuit();
        contratTmp_.setBid(BidBelote.SUIT);
        contratTmp_.setSuit(Suit.DIAMOND);
        contratTmp_.setPoints(140);
        game_.ajouterContrat(contratTmp_, game_.playerAfter(game_.getDistribution().getDealer()));
        //game_.resetNbPlisTotal();
        CustList<BidBeloteSuit> bids_ = game_.getGameBeloteBid().allowedBids();
        CustList<BidBeloteSuit> expected_ = new CustList<BidBeloteSuit>();
        BidBeloteSuit b_;
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.SUIT);
        b_.setSuit(Suit.HEART);
        b_.setPoints(150);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.SUIT);
        b_.setSuit(Suit.SPADE);
        b_.setPoints(150);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.SUIT);
        b_.setSuit(Suit.DIAMOND);
        b_.setPoints(150);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.SUIT);
        b_.setSuit(Suit.CLUB);
        b_.setPoints(150);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.SUIT);
        b_.setSuit(Suit.HEART);
        b_.setPoints(160);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.SUIT);
        b_.setSuit(Suit.SPADE);
        b_.setPoints(160);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.SUIT);
        b_.setSuit(Suit.DIAMOND);
        b_.setPoints(160);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.SUIT);
        b_.setSuit(Suit.CLUB);
        b_.setPoints(160);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.SUIT);
        b_.setSuit(Suit.HEART);
        b_.setPoints(162);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.SUIT);
        b_.setSuit(Suit.SPADE);
        b_.setPoints(162);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.SUIT);
        b_.setSuit(Suit.DIAMOND);
        b_.setPoints(162);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.SUIT);
        b_.setSuit(Suit.CLUB);
        b_.setPoints(162);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.NO_TRUMP);
        b_.setSuit(Suit.UNDEFINED);
        b_.setPoints(150);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.NO_TRUMP);
        b_.setSuit(Suit.UNDEFINED);
        b_.setPoints(160);
        expected_.add(b_);
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.NO_TRUMP);
        b_.setSuit(Suit.UNDEFINED);
        b_.setPoints(162);
        expected_.add(b_);
        assertEqSet(bids_, expected_);
    }

    static boolean containsBid(CustList<BidBeloteSuit> _bids, BidBeloteSuit _b) {
        for (BidBeloteSuit b: _bids) {
            if (_b.eq(b)) {
                return true;
            }
        }
        return false;
    }

    private void asserting(CustList<BidBeloteSuit> _exp, CustList<BidBeloteSuit> _res) {
        assertEq(_exp.size(), _res.size());
        int s_ = _exp.size();
        for (int i = IndexConstants.FIRST_INDEX; i < s_; i++) {
            assertEq(_exp.get(i), _res.get(i));
        }
    }}