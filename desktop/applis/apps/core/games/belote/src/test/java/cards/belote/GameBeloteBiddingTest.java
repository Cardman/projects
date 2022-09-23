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
        assertEq(2, bids_.size());
        BidBeloteSuit b_ = toRealBid(BidBelote.FOLD);
        assertEq(b_, bids_.get(0));
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.SUIT);
        b_.setSuit(Suit.DIAMOND);
        assertEq(b_, bids_.get(1));
    }
    @Test
    public void allowedBids_AtFirstRoundBidsInitializeWithoutTakingBid1(){
        RulesBelote regles_=initializeDefaultRules();
        GameBelote game_ = new GameBelote(GameType.RANDOM,initializeHands(),regles_);
        //game_.resetNbPlisTotal();
        byte player_ = game_.playerAfter(game_.getDistribution().getDealer());
        assertTrue(game_.keepBidding());
        BidBeloteSuit contratTmp_ = toRealBid(BidBelote.FOLD);
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
        assertEq(2, bids_.size());
        BidBeloteSuit b_ = toRealBid(BidBelote.FOLD);
        assertEq(b_, bids_.get(0));
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.SUIT);
        b_.setSuit(Suit.DIAMOND);
        assertEq(b_, bids_.get(1));
    }
    @Test
    public void allowedBids_AtFirstRoundBidsInitializeByTakingBid1(){
        RulesBelote regles_=initializeDefaultRules();
        GameBelote game_ = new GameBelote(GameType.RANDOM,initializeHands(),regles_);
        //game_.resetNbPlisTotal();
        byte player_ = game_.playerAfter(game_.getDistribution().getDealer());
        assertTrue(game_.keepBidding());
        BidBeloteSuit contratTmp_ = toRealBid(BidBelote.FOLD);
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
        assertEq(2, bids_.size());
        BidBeloteSuit b_ = toRealBid(BidBelote.FOLD);
        assertEq(b_, bids_.get(0));
        assertTrue(b_.estDemandable(game_.getBid()));
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.SUIT);
        b_.setSuit(Suit.DIAMOND);
        assertEq(b_, bids_.get(1));
        assertTrue(!b_.estDemandable(game_.getBid()));
    }
    @Test
    public void allowedBids_AtSecondRoundBidsInitialize1(){
        RulesBelote regles_=initializeDefaultRules();
        GameBelote game_ = new GameBelote(GameType.RANDOM,initializeHands(),regles_);
        //game_.resetNbPlisTotal();
        game_.finEncherePremierTour();
        CustList<BidBeloteSuit> bids_ = game_.getGameBeloteBid().allowedBids();
        assertEq(4, bids_.size());
        BidBeloteSuit b_ = toRealBid(BidBelote.FOLD);
        assertEq(b_,bids_.get(0));
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.OTHER_SUIT);
        b_.setSuit(Suit.HEART);
        assertEq(b_,bids_.get(1));
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.OTHER_SUIT);
        b_.setSuit(Suit.SPADE);
        assertEq(b_,bids_.get(2));
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.OTHER_SUIT);
        b_.setSuit(Suit.CLUB);
        assertEq(b_,bids_.get(3));
        //max_contrat()
    }
    @Test
    public void allowedBids_AtSecondRoundBidsInitializeWithoutTakingBid1(){
        RulesBelote regles_=initializeDefaultRules();
        GameBelote game_ = new GameBelote(GameType.RANDOM,initializeHands(),regles_);
        //game_.resetNbPlisTotal();
        byte player_ = game_.playerAfter(game_.getDistribution().getDealer());
        assertTrue(game_.keepBidding());
        BidBeloteSuit contratTmp_ = toRealBid(BidBelote.FOLD);
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
        assertEq(4, bids_.size());
        BidBeloteSuit b_ = toRealBid(BidBelote.FOLD);
        assertEq(b_,bids_.get(0));
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.OTHER_SUIT);
        b_.setSuit(Suit.HEART);
        assertEq(b_,bids_.get(1));
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.OTHER_SUIT);
        b_.setSuit(Suit.SPADE);
        assertEq(b_,bids_.get(2));
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.OTHER_SUIT);
        b_.setSuit(Suit.CLUB);
        assertEq(b_,bids_.get(3));
    }
    @Test
    public void allowedBids_AtSecondRoundBidsInitializeByTakingBid1(){
        RulesBelote regles_=initializeDefaultRules();
        GameBelote game_ = new GameBelote(GameType.RANDOM,initializeHands(),regles_);
        //game_.resetNbPlisTotal();
        byte player_ = game_.playerAfter(game_.getDistribution().getDealer());
        assertTrue(game_.keepBidding());
        BidBeloteSuit contratTmp_ = toRealBid(BidBelote.FOLD);
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
        assertEq(4, bids_.size());
        BidBeloteSuit b_ = toRealBid(BidBelote.FOLD);
        assertEq(b_, bids_.get(0));
        assertTrue(b_.estDemandable(game_.getBid()));
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.OTHER_SUIT);
        b_.setSuit(Suit.HEART);
        assertEq(b_, bids_.get(1));
        assertTrue(!b_.estDemandable(game_.getBid()));
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.OTHER_SUIT);
        b_.setSuit(Suit.SPADE);
        assertEq(b_, bids_.get(2));
        assertTrue(!b_.estDemandable(game_.getBid()));
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.OTHER_SUIT);
        b_.setSuit(Suit.CLUB);
        assertEq(b_, bids_.get(3));
        assertTrue(!b_.estDemandable(game_.getBid()));
    }
    @Test
    public void allowedBids_AtSecondRoundBidsInitializePassing1(){
        RulesBelote regles_=initializeDefaultRules();
        GameBelote game_ = new GameBelote(GameType.RANDOM,initializeHands(),regles_);
        //game_.resetNbPlisTotal();
        byte player_ = game_.playerAfter(game_.getDistribution().getDealer());
        assertTrue(game_.keepBidding());
        BidBeloteSuit contratTmp_ = toRealBid(BidBelote.FOLD);
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
        BidBeloteSuit contratTmp_ = toRealBid(BidBelote.FOLD);
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
        BidBeloteSuit b_ = toRealBid(BidBelote.SUIT);
        b_.setSuit(Suit.DIAMOND);
        assertEq(1, maximunBidsSuits_.size());
        assertEq(b_, maximunBidsSuits_.get(0));
    }
    @Test
    public void maximumBid_AtSecondRound1(){
        RulesBelote regles_=initializeDefaultRules();
        GameBelote game_ = new GameBelote(GameType.RANDOM,initializeHands(),regles_);
        //game_.resetNbPlisTotal();
        game_.finEncherePremierTour();
        CustList<BidBeloteSuit> maximunBidsSuits_ = game_.maximumBid();
        assertEq(3, maximunBidsSuits_.size());
        BidBeloteSuit b_ = toRealBid(BidBelote.OTHER_SUIT);
        b_.setSuit(Suit.HEART);
        assertEq(b_,maximunBidsSuits_.get(0));
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.OTHER_SUIT);
        b_.setSuit(Suit.SPADE);
        assertEq(b_,maximunBidsSuits_.get(1));
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.OTHER_SUIT);
        b_.setSuit(Suit.CLUB);
        assertEq(b_,maximunBidsSuits_.get(2));
    }
    @Test
    public void allowedBids_AtFirstRoundBidsInitialize2Test(){
        RulesBelote regles_=initializeRulesWithBids(new EnumList<BidBelote>(BidBelote.NO_TRUMP));
        GameBelote game_ = new GameBelote(GameType.RANDOM,initializeHands(),regles_);
        //game_.resetNbPlisTotal();
        CustList<BidBeloteSuit> bids_ = game_.getGameBeloteBid().allowedBids();
        assertEq(3, bids_.size());
        BidBeloteSuit b_ = toRealBid(BidBelote.FOLD);
        assertEq(b_, bids_.get(0));
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.SUIT);
        b_.setSuit(Suit.DIAMOND);
        assertEq(b_, bids_.get(1));
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.NO_TRUMP);
        assertEq(b_, bids_.get(2));
    }
    @Test
    public void allowedBids_AtFirstRoundBidsInitialize3Test(){
        RulesBelote regles_=initializeRulesWithBids(new EnumList<BidBelote>(BidBelote.ALL_TRUMP));
        GameBelote game_ = new GameBelote(GameType.RANDOM,initializeHands(),regles_);
        //game_.resetNbPlisTotal();
        CustList<BidBeloteSuit> bids_ = game_.getGameBeloteBid().allowedBids();
        assertEq(3, bids_.size());
        BidBeloteSuit b_ = toRealBid(BidBelote.FOLD);
        assertEq(b_, bids_.get(0));
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.SUIT);
        b_.setSuit(Suit.DIAMOND);
        assertEq(b_, bids_.get(1));
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.ALL_TRUMP);
        assertEq(b_, bids_.get(2));
    }
    @Test
    public void allowedBids_AtFirstRoundBidsInitialize4(){
        RulesBelote regles_=initializeRulesWithBids(new EnumList<BidBelote>(BidBelote.NO_TRUMP, BidBelote.ALL_TRUMP));
        GameBelote game_ = new GameBelote(GameType.RANDOM,initializeHands(),regles_);
        //game_.resetNbPlisTotal();
        CustList<BidBeloteSuit> bids_ = game_.getGameBeloteBid().allowedBids();
        assertEq(4, bids_.size());
        BidBeloteSuit b_ = toRealBid(BidBelote.FOLD);
        assertEq(b_, bids_.get(0));
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.SUIT);
        b_.setSuit(Suit.DIAMOND);
        assertEq(b_, bids_.get(1));
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.NO_TRUMP);
        assertEq(b_, bids_.get(2));
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.ALL_TRUMP);
        assertEq(b_, bids_.get(3));
    }
    @Test
    public void allowedBids_AtFirstRoundBidsInitializeWithoutTakingBid2Test(){
        RulesBelote regles_=initializeRulesWithBids(new EnumList<BidBelote>(BidBelote.NO_TRUMP));
        GameBelote game_ = new GameBelote(GameType.RANDOM,initializeHands(),regles_);
        //game_.resetNbPlisTotal();
        byte player_ = game_.playerAfter(game_.getDistribution().getDealer());
        assertTrue(game_.keepBidding());
        BidBeloteSuit contratTmp_ = toRealBid(BidBelote.FOLD);
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
        assertEq(3, bids_.size());
        BidBeloteSuit b_ = toRealBid(BidBelote.FOLD);
        assertEq(b_, bids_.get(0));
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.SUIT);
        b_.setSuit(Suit.DIAMOND);
        assertEq(b_, bids_.get(1));
        assertEq(toRealBid(BidBelote.NO_TRUMP), bids_.get(2));
    }
    @Test
    public void allowedBids_AtFirstRoundBidsInitializeWithoutTakingBid3Test(){
        RulesBelote regles_=initializeRulesWithBids(new EnumList<BidBelote>(BidBelote.ALL_TRUMP));
        GameBelote game_ = new GameBelote(GameType.RANDOM,initializeHands(),regles_);
        //game_.resetNbPlisTotal();
        byte player_ = game_.playerAfter(game_.getDistribution().getDealer());
        assertTrue(game_.keepBidding());
        BidBeloteSuit contratTmp_ = toRealBid(BidBelote.FOLD);
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
        assertEq(3, bids_.size());
        BidBeloteSuit b_ = toRealBid(BidBelote.FOLD);
        assertEq(b_, bids_.get(0));
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.SUIT);
        b_.setSuit(Suit.DIAMOND);
        assertEq(b_, bids_.get(1));
        assertEq(toRealBid(BidBelote.ALL_TRUMP), bids_.get(2));
    }
    @Test
    public void allowedBids_AtFirstRoundBidsInitializeWithoutTakingBid4Test(){
        RulesBelote regles_=initializeRulesWithBids(new EnumList<BidBelote>(BidBelote.NO_TRUMP,BidBelote.ALL_TRUMP));
        GameBelote game_ = new GameBelote(GameType.RANDOM,initializeHands(),regles_);
        //game_.resetNbPlisTotal();
        byte player_ = game_.playerAfter(game_.getDistribution().getDealer());
        assertTrue(game_.keepBidding());
        BidBeloteSuit contratTmp_ = toRealBid(BidBelote.FOLD);
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
        assertEq(4, bids_.size());
        BidBeloteSuit b_ = toRealBid(BidBelote.FOLD);
        assertEq(b_, bids_.get(0));
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.SUIT);
        b_.setSuit(Suit.DIAMOND);
        assertEq(b_, bids_.get(1));
        assertEq(toRealBid(BidBelote.NO_TRUMP), bids_.get(2));
        assertEq(toRealBid(BidBelote.ALL_TRUMP), bids_.get(3));
    }
    @Test
    public void allowedBids_AtFirstRoundBidsInitializeByTakingBid2Test(){
        RulesBelote regles_=initializeRulesWithBids(new EnumList<BidBelote>(BidBelote.NO_TRUMP));
        GameBelote game_ = new GameBelote(GameType.RANDOM,initializeHands(),regles_);
        //game_.resetNbPlisTotal();
        byte player_ = game_.playerAfter(game_.getDistribution().getDealer());
        assertTrue(game_.keepBidding());
        BidBeloteSuit contratTmp_ = toRealBid(BidBelote.FOLD);
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
        assertEq(3, bids_.size());
        BidBeloteSuit b_ = toRealBid(BidBelote.FOLD);
        assertEq(b_, bids_.get(0));
        assertTrue(b_.estDemandable(game_.getBid()));
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.SUIT);
        b_.setSuit(Suit.DIAMOND);
        assertEq(b_, bids_.get(1));
        assertTrue(!b_.estDemandable(game_.getBid()));
        assertEq(toRealBid(BidBelote.NO_TRUMP), bids_.get(2));
        assertTrue(toRealBid(BidBelote.NO_TRUMP).estDemandable(game_.getBid()));
    }
    @Test
    public void allowedBids_AtFirstRoundBidsInitializeByTakingBid3Test(){
        RulesBelote regles_=initializeRulesWithBids(new EnumList<BidBelote>(BidBelote.ALL_TRUMP));
        GameBelote game_ = new GameBelote(GameType.RANDOM,initializeHands(),regles_);
        //game_.resetNbPlisTotal();
        byte player_ = game_.playerAfter(game_.getDistribution().getDealer());
        assertTrue(game_.keepBidding());
        BidBeloteSuit contratTmp_ = toRealBid(BidBelote.FOLD);
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
        assertEq(3, bids_.size());
        BidBeloteSuit b_ = toRealBid(BidBelote.FOLD);
        assertEq(b_, bids_.get(0));
        assertTrue(b_.estDemandable(game_.getBid()));
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.SUIT);
        b_.setSuit(Suit.DIAMOND);
        assertEq(b_, bids_.get(1));
        assertTrue(!b_.estDemandable(game_.getBid()));
        assertEq(toRealBid(BidBelote.ALL_TRUMP), bids_.get(2));
        assertTrue(toRealBid(BidBelote.ALL_TRUMP).estDemandable(game_.getBid()));
    }
    @Test
    public void allowedBids_AtFirstRoundBidsInitializeByTakingBid4Test(){
        RulesBelote regles_=initializeRulesWithBids(new EnumList<BidBelote>(BidBelote.NO_TRUMP,BidBelote.ALL_TRUMP));
        GameBelote game_ = new GameBelote(GameType.RANDOM,initializeHands(),regles_);
        //game_.resetNbPlisTotal();
        byte player_ = game_.playerAfter(game_.getDistribution().getDealer());
        assertTrue(game_.keepBidding());
        BidBeloteSuit contratTmp_ = toRealBid(BidBelote.FOLD);
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
        assertEq(4, bids_.size());
        BidBeloteSuit b_ = toRealBid(BidBelote.FOLD);
        assertTrue(b_.estDemandable(game_.getBid()));
        assertEq(b_, bids_.get(0));
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.SUIT);
        b_.setSuit(Suit.DIAMOND);
        assertTrue(!b_.estDemandable(game_.getBid()));
        assertEq(b_, bids_.get(1));
        assertEq(toRealBid(BidBelote.NO_TRUMP), bids_.get(2));
        assertEq(toRealBid(BidBelote.ALL_TRUMP), bids_.get(3));
        assertTrue(toRealBid(BidBelote.NO_TRUMP).estDemandable(game_.getBid()));
        assertTrue(toRealBid(BidBelote.ALL_TRUMP).estDemandable(game_.getBid()));
    }
    @Test
    public void allowedBids_AtSecondRoundBidsInitialize2Test(){
        RulesBelote regles_=initializeRulesWithBids(new EnumList<BidBelote>(BidBelote.NO_TRUMP));
        GameBelote game_ = new GameBelote(GameType.RANDOM,initializeHands(),regles_);
        //game_.resetNbPlisTotal();
        game_.finEncherePremierTour();
        CustList<BidBeloteSuit> bids_ = game_.getGameBeloteBid().allowedBids();
        assertEq(5, bids_.size());
        BidBeloteSuit b_ = toRealBid(BidBelote.FOLD);
        assertEq(b_, bids_.get(0));
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.OTHER_SUIT);
        b_.setSuit(Suit.HEART);
        assertEq(b_, bids_.get(1));
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.OTHER_SUIT);
        b_.setSuit(Suit.SPADE);
        assertEq(b_, bids_.get(2));
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.OTHER_SUIT);
        b_.setSuit(Suit.CLUB);
        assertEq(b_, bids_.get(3));
        assertEq(toRealBid(BidBelote.NO_TRUMP), bids_.get(4));
    }
    @Test
    public void allowedBids_AtSecondRoundBidsInitialize3Test(){
        RulesBelote regles_=initializeRulesWithBids(new EnumList<BidBelote>(BidBelote.ALL_TRUMP));
        GameBelote game_ = new GameBelote(GameType.RANDOM,initializeHands(),regles_);
        //game_.resetNbPlisTotal();
        game_.finEncherePremierTour();
        CustList<BidBeloteSuit> bids_ = game_.getGameBeloteBid().allowedBids();
        assertEq(5, bids_.size());
        BidBeloteSuit b_ = toRealBid(BidBelote.FOLD);
        assertEq(b_, bids_.get(0));
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.OTHER_SUIT);
        b_.setSuit(Suit.HEART);
        assertEq(b_, bids_.get(1));
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.OTHER_SUIT);
        b_.setSuit(Suit.SPADE);
        assertEq(b_, bids_.get(2));
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.OTHER_SUIT);
        b_.setSuit(Suit.CLUB);
        assertEq(b_, bids_.get(3));
        assertEq(toRealBid(BidBelote.ALL_TRUMP), bids_.get(4));
    }
    @Test
    public void allowedBids_AtSecondRoundBidsInitialize4Test(){
        RulesBelote regles_=initializeRulesWithBids(new EnumList<BidBelote>(BidBelote.NO_TRUMP,BidBelote.ALL_TRUMP));
        GameBelote game_ = new GameBelote(GameType.RANDOM,initializeHands(),regles_);
        //game_.resetNbPlisTotal();
        game_.finEncherePremierTour();
        CustList<BidBeloteSuit> bids_ = game_.getGameBeloteBid().allowedBids();
        assertEq(6, bids_.size());
        BidBeloteSuit b_ = toRealBid(BidBelote.FOLD);
        assertEq(b_, bids_.get(0));
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.OTHER_SUIT);
        b_.setSuit(Suit.HEART);
        assertEq(b_, bids_.get(1));
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.OTHER_SUIT);
        b_.setSuit(Suit.SPADE);
        assertEq(b_, bids_.get(2));
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.OTHER_SUIT);
        b_.setSuit(Suit.CLUB);
        assertEq(b_, bids_.get(3));
        assertEq(toRealBid(BidBelote.NO_TRUMP), bids_.get(4));
        assertEq(toRealBid(BidBelote.ALL_TRUMP), bids_.get(5));
    }
    @Test
    public void allowedBids_AtSecondRoundBidsInitializeWithoutTakingBid2Test(){
        RulesBelote regles_=initializeRulesWithBids(new EnumList<BidBelote>(BidBelote.NO_TRUMP));
        GameBelote game_ = new GameBelote(GameType.RANDOM,initializeHands(),regles_);
        //game_.resetNbPlisTotal();
        byte player_ = game_.playerAfter(game_.getDistribution().getDealer());
        assertTrue(game_.keepBidding());
        BidBeloteSuit contratTmp_ = toRealBid(BidBelote.FOLD);
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
        assertEq(5, bids_.size());
        BidBeloteSuit b_ = toRealBid(BidBelote.FOLD);
        assertEq(b_, bids_.get(0));
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.OTHER_SUIT);
        b_.setSuit(Suit.HEART);
        assertEq(b_, bids_.get(1));
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.OTHER_SUIT);
        b_.setSuit(Suit.SPADE);
        assertEq(b_, bids_.get(2));
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.OTHER_SUIT);
        b_.setSuit(Suit.CLUB);
        assertEq(b_, bids_.get(3));
        assertEq(toRealBid(BidBelote.NO_TRUMP), bids_.get(4));
    }
    @Test
    public void allowedBids_AtSecondRoundBidsInitializeWithoutTakingBid3Test(){
        RulesBelote regles_=initializeRulesWithBids(new EnumList<BidBelote>(BidBelote.ALL_TRUMP));
        GameBelote game_ = new GameBelote(GameType.RANDOM,initializeHands(),regles_);
        //game_.resetNbPlisTotal();
        byte player_ = game_.playerAfter(game_.getDistribution().getDealer());
        assertTrue(game_.keepBidding());
        BidBeloteSuit contratTmp_ = toRealBid(BidBelote.FOLD);
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
        assertEq(5, bids_.size());
        BidBeloteSuit b_ = toRealBid(BidBelote.FOLD);
        assertEq(b_, bids_.get(0));
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.OTHER_SUIT);
        b_.setSuit(Suit.HEART);
        assertEq(b_, bids_.get(1));
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.OTHER_SUIT);
        b_.setSuit(Suit.SPADE);
        assertEq(b_, bids_.get(2));
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.OTHER_SUIT);
        b_.setSuit(Suit.CLUB);
        assertEq(b_, bids_.get(3));
        assertEq(toRealBid(BidBelote.ALL_TRUMP), bids_.get(4));
    }

    @Test
    public void allowedBids_AtSecondRoundBidsInitializeWithoutTakingBid4Test(){
        RulesBelote regles_=initializeRulesWithBids(new EnumList<BidBelote>(BidBelote.NO_TRUMP,BidBelote.ALL_TRUMP));
        GameBelote game_ = new GameBelote(GameType.RANDOM,initializeHands(),regles_);
        //game_.resetNbPlisTotal();
        byte player_ = game_.playerAfter(game_.getDistribution().getDealer());
        assertTrue(game_.keepBidding());
        BidBeloteSuit contratTmp_ = toRealBid(BidBelote.FOLD);
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
        assertEq(6, bids_.size());
        BidBeloteSuit b_ = toRealBid(BidBelote.FOLD);
        assertEq(b_, bids_.get(0));
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.OTHER_SUIT);
        b_.setSuit(Suit.HEART);
        assertEq(b_, bids_.get(1));
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.OTHER_SUIT);
        b_.setSuit(Suit.SPADE);
        assertEq(b_, bids_.get(2));
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.OTHER_SUIT);
        b_.setSuit(Suit.CLUB);
        assertEq(b_, bids_.get(3));
        assertEq(toRealBid(BidBelote.NO_TRUMP), bids_.get(4));
        assertEq(toRealBid(BidBelote.ALL_TRUMP), bids_.get(5));
    }
    @Test
    public void allowedBids_AtSecondRoundBidsInitializeByTakingBid2Test(){
        RulesBelote regles_=initializeRulesWithBids(new EnumList<BidBelote>(BidBelote.NO_TRUMP));
        GameBelote game_ = new GameBelote(GameType.RANDOM,initializeHands(),regles_);
        //game_.resetNbPlisTotal();
        byte player_ = game_.playerAfter(game_.getDistribution().getDealer());
        assertTrue(game_.keepBidding());
        BidBeloteSuit contratTmp_ = toRealBid(BidBelote.FOLD);
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
        assertEq(5,bids_.size());
        BidBeloteSuit b_ = toRealBid(BidBelote.FOLD);
        assertEq(b_,bids_.get(0));
        assertTrue(b_.estDemandable(game_.getBid()));
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.OTHER_SUIT);
        b_.setSuit(Suit.HEART);
        assertEq(b_,bids_.get(1));
        assertTrue(!b_.estDemandable(game_.getBid()));
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.OTHER_SUIT);
        b_.setSuit(Suit.SPADE);
        assertEq(b_,bids_.get(2));
        assertTrue(!b_.estDemandable(game_.getBid()));
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.OTHER_SUIT);
        b_.setSuit(Suit.CLUB);
        assertEq(b_,bids_.get(3));
        assertTrue(!b_.estDemandable(game_.getBid()));
        assertEq(toRealBid(BidBelote.NO_TRUMP),bids_.get(4));
        assertTrue(toRealBid(BidBelote.NO_TRUMP).estDemandable(game_.getBid()));
    }
    @Test
    public void allowedBids_AtSecondRoundBidsInitializeByTakingBid3Test(){
        RulesBelote regles_=initializeRulesWithBids(new EnumList<BidBelote>(BidBelote.ALL_TRUMP));
        GameBelote game_ = new GameBelote(GameType.RANDOM,initializeHands(),regles_);
        //game_.resetNbPlisTotal();
        byte player_ = game_.playerAfter(game_.getDistribution().getDealer());
        assertTrue(game_.keepBidding());
        BidBeloteSuit contratTmp_ = toRealBid(BidBelote.FOLD);
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
        assertEq(5,bids_.size());
        BidBeloteSuit b_ = toRealBid(BidBelote.FOLD);
        assertEq(b_,bids_.get(0));
        assertTrue(b_.estDemandable(game_.getBid()));
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.OTHER_SUIT);
        b_.setSuit(Suit.HEART);
        assertEq(b_,bids_.get(1));
        assertTrue(!b_.estDemandable(game_.getBid()));
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.OTHER_SUIT);
        b_.setSuit(Suit.SPADE);
        assertEq(b_,bids_.get(2));
        assertTrue(!b_.estDemandable(game_.getBid()));
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.OTHER_SUIT);
        b_.setSuit(Suit.CLUB);
        assertEq(b_,bids_.get(3));
        assertTrue(!b_.estDemandable(game_.getBid()));
        assertEq(toRealBid(BidBelote.ALL_TRUMP),bids_.get(4));
        assertTrue(toRealBid(BidBelote.ALL_TRUMP).estDemandable(game_.getBid()));
    }
    @Test
    public void allowedBids_AtSecondRoundBidsInitializeByTakingBid4Test(){
        RulesBelote regles_=initializeRulesWithBids(new EnumList<BidBelote>(BidBelote.NO_TRUMP,BidBelote.ALL_TRUMP));
        GameBelote game_ = new GameBelote(GameType.RANDOM,initializeHands(),regles_);
        //game_.resetNbPlisTotal();
        byte player_ = game_.playerAfter(game_.getDistribution().getDealer());
        assertTrue(game_.keepBidding());
        BidBeloteSuit contratTmp_ = toRealBid(BidBelote.FOLD);
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
        assertEq(6, bids_.size());
        BidBeloteSuit b_ = toRealBid(BidBelote.FOLD);
        assertEq(b_,bids_.get(0));
        assertTrue(b_.estDemandable(game_.getBid()));
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.OTHER_SUIT);
        b_.setSuit(Suit.HEART);
        assertEq(b_,bids_.get(1));
        assertTrue(!b_.estDemandable(game_.getBid()));
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.OTHER_SUIT);
        b_.setSuit(Suit.SPADE);
        assertEq(b_,bids_.get(2));
        assertTrue(!b_.estDemandable(game_.getBid()));
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.OTHER_SUIT);
        b_.setSuit(Suit.CLUB);
        assertEq(b_,bids_.get(3));
        assertTrue(!b_.estDemandable(game_.getBid()));
        assertEq(toRealBid(BidBelote.NO_TRUMP),bids_.get(4));
        assertEq(toRealBid(BidBelote.ALL_TRUMP),bids_.get(5));
        assertTrue(toRealBid(BidBelote.NO_TRUMP).estDemandable(game_.getBid()));
        assertTrue(toRealBid(BidBelote.ALL_TRUMP).estDemandable(game_.getBid()));
    }
    @Test
    public void allowedBids_AtSecondRoundBidsInitializePassing2Test(){
        RulesBelote regles_=initializeRulesWithBids(new EnumList<BidBelote>(BidBelote.NO_TRUMP));
        GameBelote game_ = new GameBelote(GameType.RANDOM,initializeHands(),regles_);
        //game_.resetNbPlisTotal();
        byte player_ = game_.playerAfter(game_.getDistribution().getDealer());
        assertTrue(game_.keepBidding());
        BidBeloteSuit contratTmp_ = toRealBid(BidBelote.FOLD);
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
        BidBeloteSuit contratTmp_ = toRealBid(BidBelote.FOLD);
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
        BidBeloteSuit contratTmp_ = toRealBid(BidBelote.FOLD);
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
    public void maximumBid_AtFirstRound2(){
        RulesBelote regles_=initializeRulesWithBids(new EnumList<BidBelote>(BidBelote.NO_TRUMP));
        GameBelote game_ = new GameBelote(GameType.RANDOM,initializeHands(),regles_);
        //game_.resetNbPlisTotal();
        CustList<BidBeloteSuit> maximunBidsSuits_ = game_.maximumBid();
        BidBeloteSuit b_;
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.NO_TRUMP);
        assertEq(1, maximunBidsSuits_.size());
        assertEq(b_,maximunBidsSuits_.get(0));
    }

    @Test
    public void maximumBid_AtFirstRound3Test(){
        RulesBelote regles_=initializeRulesWithBids(new EnumList<BidBelote>(BidBelote.ALL_TRUMP));
        GameBelote game_ = new GameBelote(GameType.RANDOM,initializeHands(),regles_);
        //game_.resetNbPlisTotal();
        BidBeloteSuit b_ = toRealBid(BidBelote.ALL_TRUMP);
        CustList<BidBeloteSuit> maximunBidsSuits_ = game_.maximumBid();
        assertEq(1, maximunBidsSuits_.size());
        assertEq(b_,maximunBidsSuits_.get(0));
    }
    @Test
    public void maximumBid_AtFirstRound4Test(){
        RulesBelote regles_=initializeRulesWithBids(new EnumList<BidBelote>(BidBelote.NO_TRUMP,BidBelote.ALL_TRUMP));
        GameBelote game_ = new GameBelote(GameType.RANDOM,initializeHands(),regles_);
        //game_.resetNbPlisTotal();
        BidBeloteSuit b_ = toRealBid(BidBelote.ALL_TRUMP);
        CustList<BidBeloteSuit> maximunBidsSuits_ = game_.maximumBid();
        assertEq(1, maximunBidsSuits_.size());
        assertEq(b_,maximunBidsSuits_.get(0));
    }
    @Test
    public void maximumBid_AtSecondRound2(){
        RulesBelote regles_=initializeRulesWithBids(new EnumList<BidBelote>(BidBelote.NO_TRUMP));
        GameBelote game_ = new GameBelote(GameType.RANDOM,initializeHands(),regles_);
        //game_.resetNbPlisTotal();
        game_.finEncherePremierTour();
        CustList<BidBeloteSuit> maximunBidsSuits_ = game_.maximumBid();
        BidBeloteSuit b_ = toRealBid(BidBelote.NO_TRUMP);
        assertEq(1, maximunBidsSuits_.size());
        assertEq(b_,maximunBidsSuits_.get(0));
    }
    @Test
    public void maximumBid_AtSecondRound3Test(){
        RulesBelote regles_=initializeRulesWithBids(new EnumList<BidBelote>(BidBelote.ALL_TRUMP));
        GameBelote game_ = new GameBelote(GameType.RANDOM,initializeHands(),regles_);
        //game_.resetNbPlisTotal();
        game_.finEncherePremierTour();
        BidBeloteSuit b_ = toRealBid(BidBelote.ALL_TRUMP);
        CustList<BidBeloteSuit> maximunBidsSuits_ = game_.maximumBid();
        assertEq(1, maximunBidsSuits_.size());
        assertEq(b_,maximunBidsSuits_.get(0));
    }
    @Test
    public void maximumBid_AtSecondRound4Test(){
        RulesBelote regles_=initializeRulesWithBids(new EnumList<BidBelote>(BidBelote.NO_TRUMP,BidBelote.ALL_TRUMP));
        GameBelote game_ = new GameBelote(GameType.RANDOM,initializeHands(),regles_);
        //game_.resetNbPlisTotal();
        game_.finEncherePremierTour();
        BidBeloteSuit b_ = toRealBid(BidBelote.ALL_TRUMP);
        CustList<BidBeloteSuit> maximunBidsSuits_ = game_.maximumBid();
        assertEq(1, maximunBidsSuits_.size());
        assertEq(b_,maximunBidsSuits_.get(0));
    }
    @Test
    public void allowedBids1(){
        RulesBelote regles_=initializeRulesWithBidPoints(false);
        regles_.getAllowedBids().put(BidBelote.NO_TRUMP, BoolVal.TRUE);
        GameBelote game_ = new GameBelote(GameType.RANDOM,initializeHands(),regles_);
        //game_.resetNbPlisTotal();
        CustList<BidBeloteSuit> bids_ = game_.getGameBeloteBid().allowedBids();
        assertEq(50, bids_.size());
        BidBeloteSuit b_;
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.SUIT);
        b_.setSuit(Suit.HEART);
        b_.setPoints(80);
        assertEq(b_,bids_.get(0));
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.SUIT);
        b_.setSuit(Suit.SPADE);
        b_.setPoints(80);
        assertEq(b_,bids_.get(10));
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.SUIT);
        b_.setSuit(Suit.DIAMOND);
        b_.setPoints(80);
        assertEq(b_,bids_.get(20));
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.SUIT);
        b_.setSuit(Suit.CLUB);
        b_.setPoints(80);
        assertEq(b_,bids_.get(30));
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.SUIT);
        b_.setSuit(Suit.HEART);
        b_.setPoints(90);
        assertEq(b_,bids_.get(1));
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.SUIT);
        b_.setSuit(Suit.SPADE);
        b_.setPoints(90);
        assertEq(b_,bids_.get(11));
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.SUIT);
        b_.setSuit(Suit.DIAMOND);
        b_.setPoints(90);
        assertEq(b_,bids_.get(21));
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.SUIT);
        b_.setSuit(Suit.CLUB);
        b_.setPoints(90);
        assertEq(b_,bids_.get(31));
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.SUIT);
        b_.setSuit(Suit.HEART);
        b_.setPoints(100);
        assertEq(b_,bids_.get(2));
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.SUIT);
        b_.setSuit(Suit.SPADE);
        b_.setPoints(100);
        assertEq(b_,bids_.get(12));
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.SUIT);
        b_.setSuit(Suit.DIAMOND);
        b_.setPoints(100);
        assertEq(b_,bids_.get(22));
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.SUIT);
        b_.setSuit(Suit.CLUB);
        b_.setPoints(100);
        assertEq(b_,bids_.get(32));
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.SUIT);
        b_.setSuit(Suit.HEART);
        b_.setPoints(110);
        assertEq(b_,bids_.get(3));
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.SUIT);
        b_.setSuit(Suit.SPADE);
        b_.setPoints(110);
        assertEq(b_,bids_.get(13));
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.SUIT);
        b_.setSuit(Suit.DIAMOND);
        b_.setPoints(110);
        assertEq(b_,bids_.get(23));
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.SUIT);
        b_.setSuit(Suit.CLUB);
        b_.setPoints(110);
        assertEq(b_,bids_.get(33));
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.SUIT);
        b_.setSuit(Suit.HEART);
        b_.setPoints(120);
        assertEq(b_,bids_.get(4));
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.SUIT);
        b_.setSuit(Suit.SPADE);
        b_.setPoints(120);
        assertEq(b_,bids_.get(14));
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.SUIT);
        b_.setSuit(Suit.DIAMOND);
        b_.setPoints(120);
        assertEq(b_,bids_.get(24));
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.SUIT);
        b_.setSuit(Suit.CLUB);
        b_.setPoints(120);
        assertEq(b_,bids_.get(34));
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.SUIT);
        b_.setSuit(Suit.HEART);
        b_.setPoints(130);
        assertEq(b_,bids_.get(5));
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.SUIT);
        b_.setSuit(Suit.SPADE);
        b_.setPoints(130);
        assertEq(b_,bids_.get(15));
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.SUIT);
        b_.setSuit(Suit.DIAMOND);
        b_.setPoints(130);
        assertEq(b_,bids_.get(25));
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.SUIT);
        b_.setSuit(Suit.CLUB);
        b_.setPoints(130);
        assertEq(b_,bids_.get(35));
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.SUIT);
        b_.setSuit(Suit.HEART);
        b_.setPoints(140);
        assertEq(b_,bids_.get(6));
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.SUIT);
        b_.setSuit(Suit.SPADE);
        b_.setPoints(140);
        assertEq(b_,bids_.get(16));
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.SUIT);
        b_.setSuit(Suit.DIAMOND);
        b_.setPoints(140);
        assertEq(b_,bids_.get(26));
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.SUIT);
        b_.setSuit(Suit.CLUB);
        b_.setPoints(140);
        assertEq(b_,bids_.get(36));
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.SUIT);
        b_.setSuit(Suit.HEART);
        b_.setPoints(150);
        assertEq(b_,bids_.get(7));
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.SUIT);
        b_.setSuit(Suit.SPADE);
        b_.setPoints(150);
        assertEq(b_,bids_.get(17));
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.SUIT);
        b_.setSuit(Suit.DIAMOND);
        b_.setPoints(150);
        assertEq(b_,bids_.get(27));
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.SUIT);
        b_.setSuit(Suit.CLUB);
        b_.setPoints(150);
        assertEq(b_,bids_.get(37));
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.SUIT);
        b_.setSuit(Suit.HEART);
        b_.setPoints(160);
        assertEq(b_,bids_.get(8));
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.SUIT);
        b_.setSuit(Suit.SPADE);
        b_.setPoints(160);
        assertEq(b_,bids_.get(18));
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.SUIT);
        b_.setSuit(Suit.DIAMOND);
        b_.setPoints(160);
        assertEq(b_,bids_.get(28));
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.SUIT);
        b_.setSuit(Suit.CLUB);
        b_.setPoints(160);
        assertEq(b_,bids_.get(38));
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.SUIT);
        b_.setSuit(Suit.HEART);
        b_.setPoints(162);
        assertEq(b_,bids_.get(9));
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.SUIT);
        b_.setSuit(Suit.SPADE);
        b_.setPoints(162);
        assertEq(b_,bids_.get(19));
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.SUIT);
        b_.setSuit(Suit.DIAMOND);
        b_.setPoints(162);
        assertEq(b_,bids_.get(29));
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.SUIT);
        b_.setSuit(Suit.CLUB);
        b_.setPoints(162);
        assertEq(b_,bids_.get(39));
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.NO_TRUMP);
        b_.setSuit(Suit.UNDEFINED);
        b_.setPoints(80);
        assertEq(b_,bids_.get(40));
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.NO_TRUMP);
        b_.setSuit(Suit.UNDEFINED);
        b_.setPoints(90);
        assertEq(b_,bids_.get(41));
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.NO_TRUMP);
        b_.setSuit(Suit.UNDEFINED);
        b_.setPoints(100);
        assertEq(b_,bids_.get(42));
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.NO_TRUMP);
        b_.setSuit(Suit.UNDEFINED);
        b_.setPoints(110);
        assertEq(b_,bids_.get(43));
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.NO_TRUMP);
        b_.setSuit(Suit.UNDEFINED);
        b_.setPoints(120);
        assertEq(b_,bids_.get(44));
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.NO_TRUMP);
        b_.setSuit(Suit.UNDEFINED);
        b_.setPoints(130);
        assertEq(b_,bids_.get(45));
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.NO_TRUMP);
        b_.setSuit(Suit.UNDEFINED);
        b_.setPoints(140);
        assertEq(b_,bids_.get(46));
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.NO_TRUMP);
        b_.setSuit(Suit.UNDEFINED);
        b_.setPoints(150);
        assertEq(b_,bids_.get(47));
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.NO_TRUMP);
        b_.setSuit(Suit.UNDEFINED);
        b_.setPoints(160);
        assertEq(b_,bids_.get(48));
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.NO_TRUMP);
        b_.setSuit(Suit.UNDEFINED);
        b_.setPoints(162);
        assertEq(b_,bids_.get(49));
    }
    @Test
    public void allowedBids2(){
        RulesBelote regles_=initializeRulesWithBidPoints(false);
        regles_.getAllowedBids().put(BidBelote.NO_TRUMP,BoolVal.TRUE);
        GameBelote game_ = new GameBelote(GameType.RANDOM,initializeHands(),regles_);
        BidBeloteSuit contratTmp_ = toRealBid(BidBelote.SUIT);
        contratTmp_.setSuit(Suit.DIAMOND);
        contratTmp_.setPoints(140);
        game_.ajouterContrat(contratTmp_, game_.playerAfter(game_.getDistribution().getDealer()));
        //game_.resetNbPlisTotal();
        CustList<BidBeloteSuit> bids_ = game_.getGameBeloteBid().allowedBids();
        assertEq(15, bids_.size());
        BidBeloteSuit b_;
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.SUIT);
        b_.setSuit(Suit.HEART);
        b_.setPoints(150);
        assertEq(b_,bids_.get(0));
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.SUIT);
        b_.setSuit(Suit.SPADE);
        b_.setPoints(150);
        assertEq(b_,bids_.get(3));
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.SUIT);
        b_.setSuit(Suit.DIAMOND);
        b_.setPoints(150);
        assertEq(b_,bids_.get(6));
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.SUIT);
        b_.setSuit(Suit.CLUB);
        b_.setPoints(150);
        assertEq(b_,bids_.get(9));
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.SUIT);
        b_.setSuit(Suit.HEART);
        b_.setPoints(160);
        assertEq(b_,bids_.get(1));
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.SUIT);
        b_.setSuit(Suit.SPADE);
        b_.setPoints(160);
        assertEq(b_,bids_.get(4));
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.SUIT);
        b_.setSuit(Suit.DIAMOND);
        b_.setPoints(160);
        assertEq(b_,bids_.get(7));
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.SUIT);
        b_.setSuit(Suit.CLUB);
        b_.setPoints(160);
        assertEq(b_,bids_.get(10));
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.SUIT);
        b_.setSuit(Suit.HEART);
        b_.setPoints(162);
        assertEq(b_,bids_.get(2));
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.SUIT);
        b_.setSuit(Suit.SPADE);
        b_.setPoints(162);
        assertEq(b_,bids_.get(5));
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.SUIT);
        b_.setSuit(Suit.DIAMOND);
        b_.setPoints(162);
        assertEq(b_,bids_.get(8));
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.SUIT);
        b_.setSuit(Suit.CLUB);
        b_.setPoints(162);
        assertEq(b_,bids_.get(11));
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.NO_TRUMP);
        b_.setSuit(Suit.UNDEFINED);
        b_.setPoints(150);
        assertEq(b_,bids_.get(12));
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.NO_TRUMP);
        b_.setSuit(Suit.UNDEFINED);
        b_.setPoints(160);
        assertEq(b_,bids_.get(13));
        b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.NO_TRUMP);
        b_.setSuit(Suit.UNDEFINED);
        b_.setPoints(162);
        assertEq(b_,bids_.get(14));
    }


    private static BidBeloteSuit toRealBid(BidBelote _e) {
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setBid(_e);
        return bid_;
    }

}