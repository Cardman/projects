package cards.belote;

import cards.belote.enumerations.DealingBelote;
import cards.belote.enumerations.DeclaresBelote;
import code.maths.montecarlo.DefaultGenerator;
import code.util.CustList;
import org.junit.Test;

import cards.belote.enumerations.BidBelote;
import cards.belote.enumerations.CardBelote;
import cards.consts.GameType;
import cards.consts.MixCardsChoice;
import cards.consts.Suit;
import code.util.EnumMap;

public class GameBeloteWithTrumpSuitOneTest extends GameBeloteWithTrumpSuit {

    public GameBelote initialize() {
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
        DealBelote donne_ = new DealBelote(mains_,(byte)3);
        RulesBelote regles_=new RulesBelote();
        regles_.setCartesBattues(MixCardsChoice.NEVER);
        return new GameBelote(GameType.RANDOM,donne_,regles_);
    }
    @Test
    public void playableCards_BeginningTrick1(){
        GameBelote game_ = initialize();
        biddingTrumpSuit(game_,BidBelote.OTHER_SUIT,Suit.HEART);
        game_.setPliEnCours();
        HandBelote hand_ = game_.getDistribution().hand(game_.getEntameur());
        EnumMap<Suit,HandBelote> suits_ = hand_.couleurs(game_.getContrat());
        HandBelote playableCards_ = game_.playableCards(suits_);
        assertEq(hand_.total(), playableCards_.total());
        assertTrue(playableCards_.contientCartes(hand_));
    }
    @Test
    public void playableCards_WhileFollowingNoTrumpSuitTrick1(){
        GameBelote game_ = initialize();
        biddingTrumpSuit(game_,BidBelote.OTHER_SUIT,Suit.HEART);
        game_.setPliEnCours();
        assertEq(0, game_.getEntameur());
        HandBelote hand_ = game_.getDistribution().hand(game_.getEntameur());
        assertTrue(hand_.contient(CardBelote.SPADE_9));
        game_.getDistribution().jouer(game_.getEntameur(),CardBelote.SPADE_9);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.SPADE_9);
        assertNotSame(game_.couleurAtout(), game_.getPliEnCours().couleurDemandee());
        byte player_ = game_.playerAfter(game_.getEntameur());
        hand_ = game_.getDistribution().hand(player_);
        EnumMap<Suit,HandBelote> suits_ = hand_.couleurs(game_.getContrat());
        HandBelote playableCards_ = game_.playableCards(suits_);
        assertTrue(playableCards_.contient(CardBelote.SPADE_JACK));
        assertEq(1, playableCards_.total());
        assertEq(1, suits_.getVal(game_.getPliEnCours().couleurDemandee()).total());
    }
    @Test
    public void playableCards_WhileTrumpingTrickOverAFoe1Test(){
        GameBelote game_ = initialize();
        biddingTrumpSuit(game_,BidBelote.OTHER_SUIT,Suit.HEART);
        game_.setPliEnCours();
        assertEq(0, game_.getEntameur());
        HandBelote hand_ = game_.getDistribution().hand(game_.getEntameur());
        assertTrue(hand_.contient(CardBelote.DIAMOND_8));
        game_.getDistribution().jouer(game_.getEntameur(),CardBelote.DIAMOND_8);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.DIAMOND_8);
        assertNotSame(game_.couleurAtout(), game_.getPliEnCours().couleurDemandee());
        byte player_ = game_.playerAfter(game_.getEntameur());
        hand_ = game_.getDistribution().hand(player_);
        EnumMap<Suit,HandBelote> suits_ = hand_.couleurs(game_.getContrat());
        HandBelote playableCards_ = game_.playableCards(suits_);
        assertTrue(playableCards_.couleur(game_.getContrat(),Suit.SPADE).estVide());
    }
    @Test
    public void playableCards_WhileTrumpingTrickOverAFoe2Test(){
        GameBelote game_ = initialize();
        biddingTrumpSuit(game_,BidBelote.OTHER_SUIT,Suit.HEART);
        game_.setPliEnCours();
        assertEq(0, game_.getEntameur());
        HandBelote hand_ = game_.getDistribution().hand(game_.getEntameur());
        assertTrue(hand_.contient(CardBelote.DIAMOND_8));
        game_.getDistribution().jouer(game_.getEntameur(),CardBelote.DIAMOND_8);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.DIAMOND_8);
        assertNotSame(game_.couleurAtout(), game_.getPliEnCours().couleurDemandee());
        byte player_ = game_.playerAfter(game_.getEntameur());
        hand_ = game_.getDistribution().hand(player_);
        EnumMap<Suit,HandBelote> suits_ = hand_.couleurs(game_.getContrat());
        HandBelote playableCards_ = game_.playableCards(suits_);
        assertTrue(playableCards_.couleur(game_.getContrat(),Suit.CLUB).estVide());
    }
    @Test
    public void playableCards_WhileTrumpingTrickOverAFoe3Test(){
        GameBelote game_ = initialize();
        biddingTrumpSuit(game_,BidBelote.OTHER_SUIT,Suit.HEART);
        game_.setPliEnCours();
        assertEq(0, game_.getEntameur());
        HandBelote hand_ = game_.getDistribution().hand(game_.getEntameur());
        assertTrue(hand_.contient(CardBelote.DIAMOND_8));
        game_.getDistribution().jouer(game_.getEntameur(),CardBelote.DIAMOND_8);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.DIAMOND_8);
        assertNotSame(game_.couleurAtout(), game_.getPliEnCours().couleurDemandee());
        byte player_ = game_.playerAfter(game_.getEntameur());
        hand_ = game_.getDistribution().hand(player_);
        EnumMap<Suit,HandBelote> suits_ = hand_.couleurs(game_.getContrat());
        HandBelote playableCards_ = game_.playableCards(suits_);
        assertTrue(playableCards_.couleur(game_.getContrat(),Suit.DIAMOND).estVide());
    }
    @Test
    public void playableCards_WhileTrumpingTrickOverAFoe4(){
        GameBelote game_ = initialize();
        biddingTrumpSuit(game_,BidBelote.OTHER_SUIT,Suit.HEART);
        game_.setPliEnCours();
        assertEq(0, game_.getEntameur());
        HandBelote hand_ = game_.getDistribution().hand(game_.getEntameur());
        assertTrue(hand_.contient(CardBelote.DIAMOND_8));
        game_.getDistribution().jouer(game_.getEntameur(),CardBelote.DIAMOND_8);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.DIAMOND_8);
        assertNotSame(game_.couleurAtout(), game_.getPliEnCours().couleurDemandee());
        byte player_ = game_.playerAfter(game_.getEntameur());
        hand_ = game_.getDistribution().hand(player_);
        EnumMap<Suit,HandBelote> suits_ = hand_.couleurs(game_.getContrat());
        HandBelote playableCards_ = game_.playableCards(suits_);
        assertEq(suits_.getVal(Suit.HEART).total(), playableCards_.couleur(game_.getContrat(),Suit.HEART).total());
        assertTrue(!suits_.getVal(Suit.HEART).estVide());
    }
    @Test
    public void keepPlayingCurrentTrick_keepPlayingCards1(){
        GameBelote game_ = initialize();
        biddingTrumpSuit(game_,BidBelote.OTHER_SUIT,Suit.HEART);
        game_.setPliEnCours();
        assertTrue(game_.keepPlayingCurrentTrick());
        game_.getDistribution().jouer(game_.getEntameur(),CardBelote.DIAMOND_8);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.DIAMOND_8);
        assertTrue(game_.keepPlayingCurrentTrick());
        byte player_ = game_.playerAfter(game_.getEntameur());
        game_.getDistribution().jouer(player_,CardBelote.HEART_8);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.HEART_8);
        assertTrue(game_.keepPlayingCurrentTrick());
        player_ = game_.playerAfter(player_);
        game_.getDistribution().jouer(player_,CardBelote.DIAMOND_JACK);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.DIAMOND_JACK);
        assertTrue(game_.keepPlayingCurrentTrick());
        player_ = game_.playerAfter(player_);
        game_.getDistribution().jouer(player_,CardBelote.DIAMOND_KING);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.DIAMOND_KING);
        assertTrue(!game_.keepPlayingCurrentTrick());
        assertTrue(game_.keepPlayingCurrentGame());
    }
    @Test
    public void strategieJeuCarteUnique1Test(){
        GameBelote game_ = initialize();
        biddingTrumpSuit(game_,BidBelote.OTHER_SUIT,Suit.HEART);
        game_.setPliEnCours();
        CardBelote card_ = game_.strategieJeuCarteUnique();
        assertSame(CardBelote.DIAMOND_8,card_);
    }
    @Test
    public void strategieJeuCarteUnique2Test(){
        GameBelote game_ = initialize();
        biddingTrumpSuit(game_,BidBelote.OTHER_SUIT,Suit.HEART);
        game_.setPliEnCours();
        game_.ajouterUneCarteDansPliEnCours(game_.getEntameur(),CardBelote.DIAMOND_8);
        CardBelote card_ = game_.strategieJeuCarteUnique();
        assertSame(CardBelote.HEART_1,card_);
    }
    @Test
    public void tryDeclareBeloteRebelote1Test() {
        GameBelote game_ = initialize();
        biddingTrumpSuit(game_,BidBelote.OTHER_SUIT,Suit.HEART);
        game_.setPliEnCours();
        game_.tryDeclareBeloteRebelote(game_.getEntameur(),CardBelote.DIAMOND_1);
        assertEq(0,game_.getDeclaresBeloteRebelote().get(game_.getEntameur()).total());
    }
    @Test
    public void tryDeclareBeloteRebelote2Test() {
        GameBelote game_ = initialize();
        biddingTrumpSuit(game_,BidBelote.OTHER_SUIT,Suit.HEART);
        game_.setPliEnCours();
        game_.tryDeclareBeloteRebelote(game_.getEntameur(),CardBelote.HEART_KING);
        assertEq(0,game_.getDeclaresBeloteRebelote().get(game_.getEntameur()).total());
    }
    @Test
    public void tryDeclareBeloteRebelote3Test() {
        RulesBelote rules_ = new RulesBelote();
        rules_.getAnnoncesAutorisees().put(DeclaresBelote.THIRTY, true);
        DealBelote deal_ = deal2Classic((byte) 3);
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDealer());
        BidBeloteSuit bid_;
        bid_ = new BidBeloteSuit();
        bid_.setEnchere(BidBelote.SUIT);
        bid_.setCouleur(Suit.SPADE);
        game_.ajouterContrat(bid_, (byte) first_);
        game_.completerDonne();
        game_.setPliEnCours();
        game_.ajouterUneCarteDansPliEnCours((byte) 0, CardBelote.DIAMOND_1);
        game_.tryDeclareBeloteRebelote((byte)1,CardBelote.SPADE_KING);
        assertEq(1,game_.getDeclaresBeloteRebelote().get((byte)1).total());
    }

    @Test
    public void currentPlayerHasPlayed1Test() {
        RulesBelote rules_ = new RulesBelote();
        rules_.getAnnoncesAutorisees().put(DeclaresBelote.THIRTY, true);
        DealBelote deal_ = deal2Classic((byte) 3);
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDealer());
        BidBeloteSuit bid_;
        bid_ = new BidBeloteSuit();
        bid_.setEnchere(BidBelote.SUIT);
        bid_.setCouleur(Suit.SPADE);
        game_.ajouterContrat(bid_, (byte) first_);
        game_.completerDonne();
        game_.setPliEnCours();
        game_.ajouterUneCarteDansPliEnCours((byte) 0, CardBelote.DIAMOND_1);
        game_.setAnnoncesBeloteRebelote((byte) 1, CardBelote.SPADE_KING);
        game_.ajouterUneCarteDansPliEnCours((byte) 1, CardBelote.SPADE_KING);
        game_.ajouterUneCarteDansPliEnCours((byte) 2, CardBelote.DIAMOND_7);
        game_.ajouterUneCarteDansPliEnCours((byte) 3, CardBelote.DIAMOND_JACK);
        game_.ajouterDixDeDerPliEnCours();
        game_.annulerAnnonces();
        game_.setPliEnCours();
        game_.setAnnoncesBeloteRebelote((byte) 1, CardBelote.SPADE_QUEEN);
        game_.ajouterUneCarteDansPliEnCours((byte) 1, CardBelote.SPADE_QUEEN);
        game_.ajouterUneCarteDansPliEnCours((byte) 2, CardBelote.SPADE_10);
        game_.ajouterUneCarteDansPliEnCours((byte) 3, CardBelote.SPADE_1);
        assertTrue(!game_.currentPlayerHasPlayed((byte) 0));
        assertTrue(game_.currentPlayerHasPlayed((byte) 0));
    }

    @Test
    public void currentPlayerHasPlayed2Test() {
        RulesBelote rules_ = new RulesBelote();
        rules_.getAnnoncesAutorisees().put(DeclaresBelote.THIRTY, true);
        DealBelote deal_ = deal2Classic((byte) 3);
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDealer());
        BidBeloteSuit bid_;
        bid_ = new BidBeloteSuit();
        bid_.setEnchere(BidBelote.SUIT);
        bid_.setCouleur(Suit.SPADE);
        game_.ajouterContrat(bid_, (byte) first_);
        game_.completerDonne();
        game_.setPliEnCours();
        assertTrue(!game_.currentPlayerHasPlayed((byte) 0));
        assertTrue(game_.currentPlayerHasPlayed((byte) 0));
    }
    @Test
    public void playerHasAlreadyBidded1Test() {
        RulesBelote rules_ = new RulesBelote();
        rules_.getAnnoncesAutorisees().put(DeclaresBelote.THIRTY, true);
        DealBelote deal_ = deal2Classic((byte) 3);
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDealer());
        assertTrue(!game_.playerHasAlreadyBidded((byte) first_));
        assertTrue(game_.playerHasAlreadyBidded((byte) first_));
    }
    @Test
    public void playerHasAlreadyBidded2Test() {
        RulesBelote rules_ = new RulesBelote();
        rules_.getAnnoncesAutorisees().put(DeclaresBelote.THIRTY, true);
        DealBelote deal_ = deal2Classic((byte) 3);
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDealer());
        game_.ajouterContrat(new BidBeloteSuit(), (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(new BidBeloteSuit(), (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(new BidBeloteSuit(), (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        assertTrue(!game_.playerHasAlreadyBidded((byte) first_));
        assertTrue(game_.playerHasAlreadyBidded((byte) first_));
    }

    @Test
    public void playerHasAlreadyBidded3Test() {
        RulesBelote rules_ = new RulesBelote();
        rules_.setRepartition(DealingBelote.COINCHE_2_VS_2);
        rules_.getEncheresAutorisees().put(BidBelote.ALL_TRUMP, true);
        DealBelote deal_ = new DealBelote(0, HandBelote.pileBase());
        deal_.setDealer((byte) 0);
        initDonne(rules_, deal_);
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDealer());
        assertTrue(!game_.playerHasAlreadyBidded((byte) first_));
        assertTrue(game_.playerHasAlreadyBidded((byte) first_));
    }

    @Test
    public void playerHasAlreadyBidded4Test() {
        RulesBelote rules_ = new RulesBelote();
        rules_.setRepartition(DealingBelote.COINCHE_2_VS_2);
        rules_.getEncheresAutorisees().put(BidBelote.ALL_TRUMP, true);
        DealBelote deal_ = new DealBelote(0, HandBelote.pileBase());
        deal_.setDealer((byte) 0);
        initDonne(rules_, deal_);
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDealer());
        game_.ajouterContrat(new BidBeloteSuit(), (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        assertTrue(!game_.playerHasAlreadyBidded((byte) first_));
        assertTrue(game_.playerHasAlreadyBidded((byte) first_));
    }
    @Test
    public void completedDeal1Test() {
        RulesBelote rules_ = new RulesBelote();
        rules_.getAnnoncesAutorisees().put(DeclaresBelote.THIRTY, true);
        DealBelote deal_ = deal2Classic((byte) 3);
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDealer());
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(deal_.derniereMain().premiereCarte().getId().getCouleur());
        bid_.setEnchere(BidBelote.SUIT);
        game_.ajouterContrat(bid_, (byte) first_);
        assertTrue(!game_.completedDeal());
        assertTrue(game_.completedDeal());
    }

    @Test
    public void completedDeal2Test() {
        RulesBelote rules_ = new RulesBelote();
        rules_.setRepartition(DealingBelote.COINCHE_2_VS_2);
        rules_.getEncheresAutorisees().put(BidBelote.ALL_TRUMP, true);
        DealBelote deal_ = new DealBelote(0, HandBelote.pileBase());
        deal_.setDealer((byte) 0);
        initDonne(rules_, deal_);
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDealer());
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.HEART);
        bid_.setPoints(80);
        bid_.setBid(BidBelote.SUIT);
        game_.ajouterContrat(bid_, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(new BidBeloteSuit(), (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(new BidBeloteSuit(), (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(new BidBeloteSuit(), (byte) first_);
        assertTrue(!game_.completedDeal());
    }

    @Test
    public void completedDeal3Test() {
        RulesBelote rules_ = new RulesBelote();
        rules_.setRepartition(DealingBelote.COINCHE_2_VS_2);
        rules_.getEncheresAutorisees().put(BidBelote.ALL_TRUMP, true);
        DealBelote deal_ = new DealBelote(0, HandBelote.pileBase());
        deal_.setDealer((byte) 0);
        initDonne(rules_, deal_);
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDealer());
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.HEART);
        bid_.setPoints(162);
        bid_.setBid(BidBelote.SUIT);
        game_.ajouterContrat(bid_, (byte) first_);
        assertTrue(!game_.completedDeal());
    }

    private static void initDonne(RulesBelote _rules, DealBelote _deal) {
        _deal.initDonne(_rules, new DisplayingBelote(), new DefaultGenerator());
    }

    private static DealBelote deal2Classic(byte _dealer) {
        CustList<HandBelote> hands_ = new CustList<HandBelote>();
        HandBelote hand_;
        hand_ = new HandBelote();
        hand_.ajouter(CardBelote.DIAMOND_1);
        hand_.ajouter(CardBelote.DIAMOND_9);
        hand_.ajouter(CardBelote.SPADE_JACK);
        hand_.ajouter(CardBelote.CLUB_KING);
        hand_.ajouter(CardBelote.DIAMOND_10);
        hands_.add(hand_);
        hand_ = new HandBelote();
        hand_.ajouter(CardBelote.HEART_1);
        hand_.ajouter(CardBelote.HEART_9);
        hand_.ajouter(CardBelote.CLUB_JACK);
        hand_.ajouter(CardBelote.SPADE_KING);
        hand_.ajouter(CardBelote.SPADE_QUEEN);
        hands_.add(hand_);
        hand_ = new HandBelote();
        hand_.ajouter(CardBelote.CLUB_1);
        hand_.ajouter(CardBelote.DIAMOND_QUEEN);
        hand_.ajouter(CardBelote.DIAMOND_7);
        hand_.ajouter(CardBelote.SPADE_7);
        hand_.ajouter(CardBelote.HEART_10);
        hands_.add(hand_);
        hand_ = new HandBelote();
        hand_.ajouter(CardBelote.DIAMOND_JACK);
        hand_.ajouter(CardBelote.HEART_QUEEN);
        hand_.ajouter(CardBelote.HEART_7);
        hand_.ajouter(CardBelote.CLUB_QUEEN);
        hand_.ajouter(CardBelote.CLUB_7);
        hands_.add(hand_);
        hand_ = new HandBelote();
        hand_.ajouter(CardBelote.SPADE_9);
        hand_.ajouter(CardBelote.CLUB_9);
        hand_.ajouter(CardBelote.DIAMOND_8);
        hand_.ajouter(CardBelote.HEART_8);
        hand_.ajouter(CardBelote.CLUB_8);
        hand_.ajouter(CardBelote.SPADE_8);
        hand_.ajouter(CardBelote.SPADE_10);
        hand_.ajouter(CardBelote.CLUB_10);
        hand_.ajouter(CardBelote.HEART_KING);
        hand_.ajouter(CardBelote.DIAMOND_KING);
        hand_.ajouter(CardBelote.HEART_JACK);
        hand_.ajouter(CardBelote.SPADE_1);
        hands_.add(hand_);
        return new DealBelote(hands_,_dealer);
    }
}