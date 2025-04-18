package cards.belote.beans;

import cards.belote.*;
import cards.belote.enumerations.*;
import cards.consts.*;
import code.util.CustList;
import code.util.Longs;
import code.util.StringList;
import code.util.StringMap;
import org.junit.Test;

public final class ResultsBeloteBeanTest extends BeanBeloteCommonTs {

    private static final String SPADE = "spade";

    @Test
    public void win1() {
        assertTrue(callResultsBeloteBeanWin(displayingGame(beanResultsBelote(EN,results(game1(),0)))));
    }

    @Test
    public void win2() {
        assertFalse(callResultsBeloteBeanWin(displayingGame(beanResultsBelote(EN,results(game1(),1)))));
    }
    @Test
    public void eq1() {
        assertTrue(callResultsBeloteBeanEquality(displayingGame(beanResultsBelote(EN,results(game3(),1)))));
    }
    @Test
    public void eq2() {
        assertFalse(callResultsBeloteBeanEquality(displayingGame(beanResultsBelote(EN,results(game1(),0)))));
    }

    @Test
    public void loose1() {
        assertFalse(callResultsBeloteBeanLoose(displayingGame(beanResultsBelote(EN,results(game1(),0)))));
    }

    @Test
    public void loose2() {
        assertTrue(callResultsBeloteBeanLoose(displayingGame(beanResultsBelote(EN,results(game1(),1)))));
    }

    @Test
    public void successfulBid1() {
        assertTrue(callResultsBeloteBeanSuccessfulBid(displayingGame(beanResultsBelote(EN,results(game1(),0)))));
    }

    @Test
    public void successfulBid2() {
        assertFalse(callResultsBeloteBeanSuccessfulBid(displayingGame(beanResultsBelote(EN,results(game4(),0)))));
    }
    @Test
    public void midBid1() {
        assertTrue(callResultsBeloteBeanMidBid(displayingGame(beanResultsBelote(EN,results(game3(),1)))));
    }
    @Test
    public void midBid2() {
        assertFalse(callResultsBeloteBeanMidBid(displayingGame(beanResultsBelote(EN,results(game1(),0)))));
    }

    @Test
    public void looseBid1() {
        assertTrue(callResultsBeloteBeanFailedBid(displayingGame(beanResultsBelote(EN,results(game4(),0)))));
    }

    @Test
    public void looseBid2() {
        assertFalse(callResultsBeloteBeanFailedBid(displayingGame(beanResultsBelote(EN,results(game1(),0)))));
    }

    @Test
    public void slam1() {
        assertTrue(callResultsBeloteBeanSlam(displayingGame(beanResultsBelote(EN,results(game1(),0)))));
    }

    @Test
    public void slam2() {
        assertFalse(callResultsBeloteBeanSlam(displayingGame(beanResultsBelote(EN,results(game2(),0)))));
    }

    @Test
    public void bidString1() {
        assertEq("",callResultsBeloteBeanBidString(displayingGame(beanResultsBelote(EN,results(game5(),0)))));
    }

    @Test
    public void bidString2() {
        assertEq(SPADE,callResultsBeloteBeanBidString(displayingGame(beanResultsBelote(EN,results(game1(),0)))));
    }

    @Test
    public void calledPlayersList() {
        StringList res_ = callResultsBeloteBeanCalledPlayersList(displayingGame(beanResultsBelote(EN, results(game1(), 0))));
        assertEq(1, res_.size());
        assertEq("2",res_.get(0));
    }

    @Test
    public void calledPlayersListNo() {
        assertEq(0, callResultsBeloteBeanCalledPlayersList(displayingGame(beanResultsBelote(EN, results(game6(), 0)))).size());
    }

    @Test
    public void takerNickname() {
        assertEq("0", callResultsBeloteBeanTakerNickname(displayingGame(beanResultsBelote(EN, results(game1(), 0)))));
    }

    @Test
    public void differenceScoreTaker() {
        assertEq(262, callResultsBeloteBeanDifferenceScoreTaker(displayingGame(beanResultsBelote(EN, results(game1(), 0)))));
    }

    @Test
    public void absoluteDiff() {
        assertEq(262, callResultsBeloteBeanAbsoluteDiff(displayingGame(beanResultsBelote(EN, results(game1(), 0)))));
    }

    @Test
    public void pointsAttaqueSansPrime() {
        assertEq(152, callResultsBeloteBeanPointsAttaqueSansPrime(displayingGame(beanResultsBelote(EN, results(game1(), 0)))));
    }

    @Test
    public void pointsAttaqueDefinitif() {
        assertEq(262, callResultsBeloteBeanPointsAttaqueDefinitif(displayingGame(beanResultsBelote(EN, results(game1(), 0)))));
    }

    @Test
    public void pointsAttaqueTemporaire() {
        assertEq(262, callResultsBeloteBeanPointsAttaqueTemporaire(displayingGame(beanResultsBelote(EN, results(game1(), 0)))));
    }

    @Test
    public void pointsDefenseDefinitif() {
        assertEq(0, callResultsBeloteBeanPointsDefenseDefinitif(displayingGame(beanResultsBelote(EN, results(game1(), 0)))));
    }

    @Test
    public void pointsDefenseSansPrime() {
        assertEq(0, callResultsBeloteBeanPointsDefenseSansPrime(displayingGame(beanResultsBelote(EN, results(game1(), 0)))));
    }

    @Test
    public void pointsDefenseTemporaire() {
        assertEq(0, callResultsBeloteBeanPointsDefenseTemporaire(displayingGame(beanResultsBelote(EN, results(game1(), 0)))));
    }

    @Test
    public void linesDeal() {
        CustList<LineDeal> res_ = callResultsBeloteBeanLinesDeal(displayingGame(beanResultsBelote(EN, results(game1(), 0))));
        assertEq(1, res_.size());
        assertEq(4, res_.get(0).getScores().size());
        assertEq(0,res_.get(0).getNumber());
        assertEq(262,res_.get(0).getScores().get(0));
        assertEq(0,res_.get(0).getScores().get(1));
        assertEq(262,res_.get(0).getScores().get(2));
        assertEq(0,res_.get(0).getScores().get(3));
    }

    @Test
    public void playGame1() {
        assertTrue(callBeloteBeanPlayGame(displayingGame(beanResultsBelote(EN, results(game1(), 0)))));
    }

    @Test
    public void playGame2() {
        assertFalse(callBeloteBeanPlayGame(displayingGame(beanResultsBelote(EN, results(game5(), 0)))));
    }

    @Test
    public void getNickNames() {
        StringList res_ = callBeloteBeanGetNicknames(displayingGame(beanResultsBelote(EN, results(game1(), 0))));
        assertEq(4, res_.size());
        assertEq("0",res_.get(0));
        assertEq("1",res_.get(1));
        assertEq("2",res_.get(2));
        assertEq("3",res_.get(3));
    }

    @Test
    public void getScores() {
        CustList<LineDeal> res_ = callBeloteBeanGetScores(displayingGame(beanResultsBelote(EN, results(game1(), 0))));
        assertEq(1, res_.size());
        assertEq(4, res_.get(0).getScores().size());
        assertEq(0,res_.get(0).getNumber());
        assertEq(262,res_.get(0).getScores().get(0));
        assertEq(0,res_.get(0).getScores().get(1));
        assertEq(262,res_.get(0).getScores().get(2));
        assertEq(0,res_.get(0).getScores().get(3));
    }

//    @Test
//    public void init() {
//        StringMap<String> other_ = MessBelotePage.ms();
////        NavigationCore.adjust(other_);
//        StringMap<TranslationsAppli> mes_ = new StringMap<TranslationsAppli>();
//        mes_.addEntry(EN,MessBelotePage.enBelote());
//        mes_.addEntry(FR,MessBelotePage.frBelote());
//        BeloteStandardsResults stds_ = new BeloteStandardsResults();
//        NatNavigation nav_ = stds_.nav(new StringList(EN,FR), new ResultsBeloteLoader(), PagesBelotes.build(),other_,mes_);
//        nav_.setLanguage(EN);
//        stds_.setDataBase(results(game1(), 0));
//        stds_.initializeRendSessionDoc(nav_);
//        assertFalse(nav_.getHtmlText().isEmpty());
////        assertEq("<html xmlns:c=\"javahtml\"><head><title>Results</title><link href=\""+BeloteScriptPages.CSS+"\" rel=\"stylesheet\" type=\"text/css\"/><style>h1 {\n" +
////                "\tcolor:blue;\n" +
////                "}\n" +
////                "</style></head><body><h1>1 Calculation of attack team's points</h1><ul><li>Number of necessary points in order that the taker wins:0</li><li>Number of points won in the attack team's tricks:262</li></ul><h1>2 Attack team</h1><ul><li>Taker:0</li><li>Taker's partners:<ul><li>2</li></ul></li><li>Bid:spade</li></ul><h1>3 Results</h1><p>Scored points by attack's team without bonuses:152</p><br/><p>Scored points by defense's team without bonuses:0</p><br/><p>Scored points by attack's team with bonuses:262</p><br/><p>Scored points by defense's team with bonuses:0</p><br/><p>Final scored points by attack's team:262</p><br/><p>Final scored points by defense's team:0</p><br/><p>You win.</p><br/><p>The bid spade is passed of 262 points.</p><br/><p>The attack's team has achieved the grand slam.</p><br/><br/><table border=\"1\"><caption>Scores</caption><thead><tr><td/><td>0</td><td>1</td><td>2</td><td>3</td></tr></thead><tbody><tr><td>0</td><td>262</td><td>0</td><td>262</td><td>0</td></tr></tbody></table><br/></body></html>",nav_.getHtmlText());
//    }
    private static ResultsBelote results(GameBelote _g, int _user) {
        ResultsBelote res_ = new ResultsBelote();
        res_.setGame(_g);
        res_.getRes().setUser(_user);
        res_.initialize(fourPseudos("0","1","2","3"), new CustList<Longs>());
        StringMap<String> s_ = new StringMap<String>();
        s_.addEntry(EnumCardsExporterUtil.SUITS+Suit.SPADE.getSuitSt(),SPADE);
        res_.getRes().setGeneral(s_);
        res_.getRes().setSpecific(new StringMap<String>());
//        res_.getRes().setGeneral(readCoreResource());
//        res_.getRes().setSpecific(readResource());
        return res_;
    }

    private static StringList fourPseudos(String _one, String _two, String _three, String _four) {
        StringList ps_ = new StringList();
        ps_.add(_one);
        ps_.add(_two);
        ps_.add(_three);
        ps_.add(_four);
        return ps_;
    }

    private static GameBelote game5() {
        RulesBelote rules_ = new RulesBelote();
        DealBelote deal_ = deal1Loose(3);
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        bid(game_);
        bid(game_);
        bid(game_);
        bid(game_);
//        game_.finEncherePremierTour();
        bid(game_);
        bid(game_);
        bid(game_);
        bid(game_);
        return game_;
    }

    private static void bid(GameBelote _g) {
        _g.ajouterContrat(new BidBeloteSuit());
    }

    private static GameBelote game4() {
        RulesBelote rules_ = new RulesBelote();
        DealBelote deal_ = deal1Loose(3);
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDealer());
        BidBeloteSuit bid_;
        bid_ = new BidBeloteSuit();
        bid_.setBid(BidBelote.SUIT);
        bid_.setSuit(Suit.SPADE);
        game_.ajouterContrat(bid_);
        game_.completerDonne();
        play(game_, CardBelote.SPADE_JACK);
        play(game_, CardBelote.SPADE_KING);
        play(game_, CardBelote.SPADE_QUEEN);
        play(game_, CardBelote.CLUB_7);
        tr(game_);
        play(game_, CardBelote.SPADE_9);
        play(game_, CardBelote.CLUB_9);
        play(game_, CardBelote.SPADE_8);
        play(game_, CardBelote.HEART_7);
        tr(game_);
        play(game_, CardBelote.SPADE_1);
        play(game_, CardBelote.DIAMOND_9);
        play(game_, CardBelote.SPADE_7);
        play(game_, CardBelote.HEART_9);
        tr(game_);
        play(game_, CardBelote.DIAMOND_KING);
        play(game_, CardBelote.DIAMOND_8);
        play(game_, CardBelote.DIAMOND_QUEEN);
        play(game_, CardBelote.DIAMOND_1);
        tr(game_);
        play(game_, CardBelote.DIAMOND_7);
        play(game_, CardBelote.DIAMOND_10);
        play(game_, CardBelote.HEART_8);
        play(game_, CardBelote.DIAMOND_JACK);
        tr(game_);
        play(game_, CardBelote.SPADE_10);
        play(game_, CardBelote.CLUB_8);
        play(game_, CardBelote.HEART_KING);
        play(game_, CardBelote.HEART_JACK);
        tr(game_);
        play(game_, CardBelote.HEART_10);
        play(game_, CardBelote.HEART_1);
        play(game_, CardBelote.CLUB_1);
        play(game_, CardBelote.HEART_QUEEN);
        tr(game_);
        play(game_, CardBelote.CLUB_JACK);
        play(game_, CardBelote.CLUB_QUEEN);
        play(game_, CardBelote.CLUB_10);
        play(game_, CardBelote.CLUB_KING);
        tr(game_);
        return game_;
    }
    private static GameBelote game3() {
        RulesBelote rules_ = new RulesBelote();
        DealBelote deal_ = deal1Eq(3);
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDealer());
        BidBeloteSuit bid_;
        bid_ = new BidBeloteSuit();
        bid_.setBid(BidBelote.SUIT);
        bid_.setSuit(Suit.SPADE);
        game_.ajouterContrat(bid_);
        game_.completerDonne();
        play(game_, CardBelote.SPADE_JACK);
        play(game_, CardBelote.SPADE_KING);
        play(game_, CardBelote.SPADE_QUEEN);
        play(game_, CardBelote.CLUB_7);
        tr(game_);
        play(game_, CardBelote.SPADE_9);
        play(game_, CardBelote.CLUB_9);
        play(game_, CardBelote.SPADE_8);
        play(game_, CardBelote.HEART_7);
        tr(game_);
        play(game_, CardBelote.SPADE_1);
        play(game_, CardBelote.DIAMOND_9);
        play(game_, CardBelote.SPADE_7);
        play(game_, CardBelote.HEART_9);
        tr(game_);
        play(game_, CardBelote.DIAMOND_KING);
        play(game_, CardBelote.DIAMOND_8);
        play(game_, CardBelote.DIAMOND_JACK);
        play(game_, CardBelote.DIAMOND_1);
        tr(game_);
        play(game_, CardBelote.DIAMOND_7);
        play(game_, CardBelote.DIAMOND_10);
        play(game_, CardBelote.HEART_8);
        play(game_, CardBelote.DIAMOND_QUEEN);
        tr(game_);
        play(game_, CardBelote.SPADE_10);
        play(game_, CardBelote.CLUB_8);
        play(game_, CardBelote.HEART_KING);
        play(game_, CardBelote.HEART_JACK);
        tr(game_);
        play(game_, CardBelote.HEART_10);
        play(game_, CardBelote.HEART_1);
        play(game_, CardBelote.CLUB_1);
        play(game_, CardBelote.HEART_QUEEN);
        tr(game_);
        play(game_, CardBelote.CLUB_JACK);
        play(game_, CardBelote.CLUB_QUEEN);
        play(game_, CardBelote.CLUB_10);
        play(game_, CardBelote.CLUB_KING);
        tr(game_);
        return game_;
    }

    private static GameBelote game2() {
        RulesBelote rules_ = new RulesBelote();
        DealBelote deal_ = deal1Win(3);
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDealer());
        BidBeloteSuit bid_;
        bid_ = new BidBeloteSuit();
        bid_.setBid(BidBelote.SUIT);
        bid_.setSuit(Suit.SPADE);
        game_.ajouterContrat(bid_);
        game_.completerDonne();
        play(game_, CardBelote.SPADE_JACK);
        play(game_, CardBelote.SPADE_KING);
        play(game_, CardBelote.SPADE_QUEEN);
        play(game_, CardBelote.CLUB_7);
        tr(game_);
        play(game_, CardBelote.SPADE_9);
        play(game_, CardBelote.CLUB_9);
        play(game_, CardBelote.SPADE_8);
        play(game_, CardBelote.HEART_7);
        tr(game_);
        play(game_, CardBelote.SPADE_1);
        play(game_, CardBelote.DIAMOND_9);
        play(game_, CardBelote.SPADE_7);
        play(game_, CardBelote.HEART_9);
        tr(game_);
        play(game_, CardBelote.SPADE_10);
        play(game_, CardBelote.HEART_8);
        play(game_, CardBelote.DIAMOND_QUEEN);
        play(game_, CardBelote.DIAMOND_JACK);
        tr(game_);
        play(game_, CardBelote.DIAMOND_1);
        play(game_, CardBelote.DIAMOND_8);
        play(game_, CardBelote.DIAMOND_7);
        play(game_, CardBelote.DIAMOND_KING);
        tr(game_);
        play(game_, CardBelote.DIAMOND_10);
        play(game_, CardBelote.CLUB_8);
        play(game_, CardBelote.HEART_KING);
        play(game_, CardBelote.HEART_JACK);
        tr(game_);
        play(game_, CardBelote.HEART_10);
        play(game_, CardBelote.HEART_1);
        play(game_, CardBelote.CLUB_1);
        play(game_, CardBelote.HEART_QUEEN);
        tr(game_);
        play(game_, CardBelote.CLUB_JACK);
        play(game_, CardBelote.CLUB_10);
        play(game_, CardBelote.CLUB_QUEEN);
        play(game_, CardBelote.CLUB_KING);
        tr(game_);
        return game_;
    }
    private static GameBelote game1() {
        RulesBelote rules_ = new RulesBelote();
        DealBelote deal_ = deal1Slam(3);
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDealer());
        BidBeloteSuit bid_;
        bid_ = new BidBeloteSuit();
        bid_.setBid(BidBelote.SUIT);
        bid_.setSuit(Suit.SPADE);
        game_.ajouterContrat(bid_);
        game_.completerDonne();
        play(game_, CardBelote.SPADE_JACK);
        play(game_, CardBelote.SPADE_KING);
        play(game_, CardBelote.SPADE_QUEEN);
        play(game_, CardBelote.CLUB_7);
        tr(game_);
        play(game_, CardBelote.SPADE_9);
        play(game_, CardBelote.CLUB_9);
        play(game_, CardBelote.SPADE_8);
        play(game_, CardBelote.HEART_7);
        tr(game_);
        play(game_, CardBelote.SPADE_1);
        play(game_, CardBelote.DIAMOND_9);
        play(game_, CardBelote.SPADE_7);
        play(game_, CardBelote.HEART_9);
        tr(game_);
        play(game_, CardBelote.SPADE_10);
        play(game_, CardBelote.HEART_8);
        play(game_, CardBelote.DIAMOND_QUEEN);
        play(game_, CardBelote.DIAMOND_JACK);
        tr(game_);
        play(game_, CardBelote.DIAMOND_1);
        play(game_, CardBelote.DIAMOND_8);
        play(game_, CardBelote.DIAMOND_7);
        play(game_, CardBelote.DIAMOND_KING);
        tr(game_);
        play(game_, CardBelote.DIAMOND_10);
        play(game_, CardBelote.CLUB_8);
        play(game_, CardBelote.HEART_KING);
        play(game_, CardBelote.HEART_JACK);
        tr(game_);
        play(game_, CardBelote.HEART_1);
        play(game_, CardBelote.HEART_10);
        play(game_, CardBelote.CLUB_1);
        play(game_, CardBelote.HEART_QUEEN);
        tr(game_);
        play(game_, CardBelote.CLUB_KING);
        play(game_, CardBelote.CLUB_JACK);
        play(game_, CardBelote.CLUB_10);
        play(game_, CardBelote.CLUB_QUEEN);
        tr(game_);
        return game_;
    }
    private static GameBelote game6() {
        RulesBelote rules_ = new RulesBelote();
        rules_.setDealing(DealingBelote.CLASSIC_1_VS_2);
        DealBelote deal_ = dealThreePlayers();
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        game_.playerAfter(deal_.getDealer());
        game_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        game_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        game_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        game_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        game_.ajouterContrat(bidSuit(Suit.HEART,0,BidBelote.OTHER_SUIT));
        game_.ajouterCartesUtilisateur();
        game_.ajouterUneCarteDansPliEnCoursPreneur(CardBelote.SPADE_KING);
        game_.ajouterUneCarteDansPliEnCoursPreneur(CardBelote.SPADE_QUEEN);
        game_.ajouterUneCarteDansPliEnCoursPreneur(CardBelote.CLUB_KING);
        game_.ajouterUneCarteDansPliEnCoursPreneur(CardBelote.CLUB_QUEEN);
        game_.ajouterUneCarteDansPliEnCoursPreneur(CardBelote.CLUB_9);
        game_.ajouterUneCarteDansPliEnCoursPreneur(CardBelote.CLUB_8);
        game_.ajouterUneCarteDansPliEnCoursPreneur(CardBelote.DIAMOND_7);
        game_.ajouterUneCarteDansPliEnCoursPreneur(CardBelote.DIAMOND_8);
        game_.ajouterChelemUtilisateur();
        game_.validateDiscard();
        play(game_, CardBelote.HEART_JACK);
        play(game_, CardBelote.HEART_7);
        play(game_, CardBelote.SPADE_7);
        tr(game_);
        play(game_, CardBelote.HEART_9);
        play(game_, CardBelote.HEART_8);
        play(game_, CardBelote.SPADE_8);
        tr(game_);
        play(game_, CardBelote.HEART_1);
        play(game_, CardBelote.HEART_QUEEN);
        play(game_, CardBelote.CLUB_JACK);
        tr(game_);
        play(game_, CardBelote.HEART_10);
        play(game_, CardBelote.HEART_KING);
        play(game_, CardBelote.SPADE_JACK);
        tr(game_);
        play(game_, CardBelote.DIAMOND_1);
        play(game_, CardBelote.CLUB_7);
        play(game_, CardBelote.DIAMOND_9);
        tr(game_);
        play(game_, CardBelote.DIAMOND_10);
        play(game_, CardBelote.SPADE_9);
        play(game_, CardBelote.DIAMOND_JACK);
        tr(game_);
        play(game_, CardBelote.SPADE_1);
        play(game_, CardBelote.SPADE_10);
        play(game_, CardBelote.DIAMOND_QUEEN);
        tr(game_);
        play(game_, CardBelote.CLUB_1);
        play(game_, CardBelote.CLUB_10);
        play(game_, CardBelote.DIAMOND_KING);
        tr(game_);
        return game_;
    }

    private static void tr(GameBelote _game) {
        _game.ajouterDixDeDerPliEnCours();
    }

    private static void play(GameBelote _game, CardBelote _card) {
        _game.ajouterUneCarteDansPliEnCoursJoue(_card);
    }

    private static DealBelote deal1Slam(int _dealer) {
        CustList<HandBelote> hands_ = new CustList<HandBelote>();
        HandBelote hand_;
        hand_ = new HandBelote();
        hand_.ajouter(CardBelote.DIAMOND_1);
        hand_.ajouter(CardBelote.SPADE_1);
        hand_.ajouter(CardBelote.SPADE_JACK);
        hand_.ajouter(CardBelote.CLUB_KING);
        hand_.ajouter(CardBelote.DIAMOND_10);
        hands_.add(hand_);
        hand_ = new HandBelote();
        hand_.ajouter(CardBelote.DIAMOND_9);
        hand_.ajouter(CardBelote.CLUB_9);
        hand_.ajouter(CardBelote.CLUB_JACK);
        hand_.ajouter(CardBelote.SPADE_KING);
        hand_.ajouter(CardBelote.HEART_10);
        hands_.add(hand_);
        hand_ = new HandBelote();
        hand_.ajouter(CardBelote.CLUB_1);
        hand_.ajouter(CardBelote.DIAMOND_QUEEN);
        hand_.ajouter(CardBelote.DIAMOND_7);
        hand_.ajouter(CardBelote.SPADE_QUEEN);
        hand_.ajouter(CardBelote.SPADE_7);
        hands_.add(hand_);
        hand_ = new HandBelote();
        hand_.ajouter(CardBelote.HEART_QUEEN);
        hand_.ajouter(CardBelote.HEART_9);
        hand_.ajouter(CardBelote.HEART_7);
        hand_.ajouter(CardBelote.CLUB_QUEEN);
        hand_.ajouter(CardBelote.CLUB_7);
        hands_.add(hand_);
        hand_ = new HandBelote();
        hand_.ajouter(CardBelote.SPADE_9);
        hand_.ajouter(CardBelote.SPADE_10);
        hand_.ajouter(CardBelote.HEART_1);
        hand_.ajouter(CardBelote.HEART_8);
        hand_.ajouter(CardBelote.DIAMOND_8);
        hand_.ajouter(CardBelote.CLUB_8);
        hand_.ajouter(CardBelote.SPADE_8);
        hand_.ajouter(CardBelote.CLUB_10);
        hand_.ajouter(CardBelote.HEART_KING);
        hand_.ajouter(CardBelote.DIAMOND_KING);
        hand_.ajouter(CardBelote.HEART_JACK);
        hand_.ajouter(CardBelote.DIAMOND_JACK);
        hands_.add(hand_);
        return new DealBelote(hands_,_dealer);
    }

    private static DealBelote deal1Eq(int _dealer) {
        CustList<HandBelote> hands_ = new CustList<HandBelote>();
        HandBelote hand_;
        hand_ = new HandBelote();
        hand_.ajouter(CardBelote.DIAMOND_KING);
        hand_.ajouter(CardBelote.SPADE_1);
        hand_.ajouter(CardBelote.SPADE_JACK);
        hand_.ajouter(CardBelote.CLUB_KING);
        hand_.ajouter(CardBelote.DIAMOND_10);
        hands_.add(hand_);
        hand_ = new HandBelote();
        hand_.ajouter(CardBelote.DIAMOND_9);
        hand_.ajouter(CardBelote.CLUB_9);
        hand_.ajouter(CardBelote.CLUB_JACK);
        hand_.ajouter(CardBelote.SPADE_KING);
        hand_.ajouter(CardBelote.HEART_1);
        hands_.add(hand_);
        hand_ = new HandBelote();
        hand_.ajouter(CardBelote.CLUB_1);
        hand_.ajouter(CardBelote.DIAMOND_QUEEN);
        hand_.ajouter(CardBelote.DIAMOND_JACK);
        hand_.ajouter(CardBelote.SPADE_QUEEN);
        hand_.ajouter(CardBelote.SPADE_7);
        hands_.add(hand_);
        hand_ = new HandBelote();
        hand_.ajouter(CardBelote.HEART_QUEEN);
        hand_.ajouter(CardBelote.HEART_9);
        hand_.ajouter(CardBelote.HEART_7);
        hand_.ajouter(CardBelote.CLUB_10);
        hand_.ajouter(CardBelote.CLUB_7);
        hands_.add(hand_);
        hand_ = new HandBelote();
        hand_.ajouter(CardBelote.SPADE_9);
        hand_.ajouter(CardBelote.SPADE_10);
        hand_.ajouter(CardBelote.HEART_10);
        hand_.ajouter(CardBelote.HEART_8);
        hand_.ajouter(CardBelote.DIAMOND_8);
        hand_.ajouter(CardBelote.CLUB_8);
        hand_.ajouter(CardBelote.SPADE_8);
        hand_.ajouter(CardBelote.CLUB_QUEEN);
        hand_.ajouter(CardBelote.HEART_KING);
        hand_.ajouter(CardBelote.DIAMOND_1);
        hand_.ajouter(CardBelote.HEART_JACK);
        hand_.ajouter(CardBelote.DIAMOND_7);
        hands_.add(hand_);
        return new DealBelote(hands_,_dealer);
    }

    private static DealBelote deal1Loose(int _dealer) {
        CustList<HandBelote> hands_ = new CustList<HandBelote>();
        HandBelote hand_;
        hand_ = new HandBelote();
        hand_.ajouter(CardBelote.DIAMOND_KING);
        hand_.ajouter(CardBelote.SPADE_1);
        hand_.ajouter(CardBelote.SPADE_JACK);
        hand_.ajouter(CardBelote.CLUB_KING);
        hand_.ajouter(CardBelote.DIAMOND_10);
        hands_.add(hand_);
        hand_ = new HandBelote();
        hand_.ajouter(CardBelote.DIAMOND_9);
        hand_.ajouter(CardBelote.CLUB_9);
        hand_.ajouter(CardBelote.CLUB_JACK);
        hand_.ajouter(CardBelote.SPADE_KING);
        hand_.ajouter(CardBelote.HEART_1);
        hands_.add(hand_);
        hand_ = new HandBelote();
        hand_.ajouter(CardBelote.CLUB_1);
        hand_.ajouter(CardBelote.DIAMOND_QUEEN);
        hand_.ajouter(CardBelote.DIAMOND_JACK);
        hand_.ajouter(CardBelote.SPADE_QUEEN);
        hand_.ajouter(CardBelote.SPADE_7);
        hands_.add(hand_);
        hand_ = new HandBelote();
        hand_.ajouter(CardBelote.HEART_QUEEN);
        hand_.ajouter(CardBelote.HEART_9);
        hand_.ajouter(CardBelote.HEART_7);
        hand_.ajouter(CardBelote.CLUB_10);
        hand_.ajouter(CardBelote.CLUB_7);
        hands_.add(hand_);
        hand_ = new HandBelote();
        hand_.ajouter(CardBelote.SPADE_9);
        hand_.ajouter(CardBelote.SPADE_10);
        hand_.ajouter(CardBelote.HEART_10);
        hand_.ajouter(CardBelote.HEART_8);
        hand_.ajouter(CardBelote.DIAMOND_8);
        hand_.ajouter(CardBelote.CLUB_8);
        hand_.ajouter(CardBelote.SPADE_8);
        hand_.ajouter(CardBelote.CLUB_QUEEN);
        hand_.ajouter(CardBelote.HEART_KING);
        hand_.ajouter(CardBelote.DIAMOND_1);
        hand_.ajouter(CardBelote.HEART_JACK);
        hand_.ajouter(CardBelote.DIAMOND_7);
        hands_.add(hand_);
        return new DealBelote(hands_,_dealer);
    }

    private static DealBelote deal1Win(int _dealer) {
        CustList<HandBelote> hands_ = new CustList<HandBelote>();
        HandBelote hand_;
        hand_ = new HandBelote();
        hand_.ajouter(CardBelote.DIAMOND_1);
        hand_.ajouter(CardBelote.SPADE_1);
        hand_.ajouter(CardBelote.SPADE_JACK);
        hand_.ajouter(CardBelote.CLUB_KING);
        hand_.ajouter(CardBelote.DIAMOND_10);
        hands_.add(hand_);
        hand_ = new HandBelote();
        hand_.ajouter(CardBelote.DIAMOND_9);
        hand_.ajouter(CardBelote.CLUB_9);
        hand_.ajouter(CardBelote.CLUB_JACK);
        hand_.ajouter(CardBelote.SPADE_KING);
        hand_.ajouter(CardBelote.HEART_1);
        hands_.add(hand_);
        hand_ = new HandBelote();
        hand_.ajouter(CardBelote.CLUB_1);
        hand_.ajouter(CardBelote.DIAMOND_QUEEN);
        hand_.ajouter(CardBelote.DIAMOND_7);
        hand_.ajouter(CardBelote.SPADE_QUEEN);
        hand_.ajouter(CardBelote.SPADE_7);
        hands_.add(hand_);
        hand_ = new HandBelote();
        hand_.ajouter(CardBelote.HEART_QUEEN);
        hand_.ajouter(CardBelote.HEART_9);
        hand_.ajouter(CardBelote.HEART_7);
        hand_.ajouter(CardBelote.CLUB_QUEEN);
        hand_.ajouter(CardBelote.CLUB_7);
        hands_.add(hand_);
        hand_ = new HandBelote();
        hand_.ajouter(CardBelote.SPADE_9);
        hand_.ajouter(CardBelote.SPADE_10);
        hand_.ajouter(CardBelote.HEART_10);
        hand_.ajouter(CardBelote.HEART_8);
        hand_.ajouter(CardBelote.DIAMOND_8);
        hand_.ajouter(CardBelote.CLUB_8);
        hand_.ajouter(CardBelote.SPADE_8);
        hand_.ajouter(CardBelote.CLUB_10);
        hand_.ajouter(CardBelote.HEART_KING);
        hand_.ajouter(CardBelote.DIAMOND_KING);
        hand_.ajouter(CardBelote.HEART_JACK);
        hand_.ajouter(CardBelote.DIAMOND_JACK);
        hands_.add(hand_);
        return new DealBelote(hands_,_dealer);
    }
    private static DealBelote dealThreePlayers() {
        DealBelote db_ = new DealBelote();
        db_.setDealer(1);
        db_.getDeal().add(create(CardBelote.HEART_JACK,CardBelote.HEART_9,CardBelote.SPADE_KING,CardBelote.SPADE_QUEEN,
                CardBelote.DIAMOND_10,CardBelote.DIAMOND_7,CardBelote.DIAMOND_8,CardBelote.HEART_10));
        db_.getDeal().add(create(CardBelote.HEART_KING,CardBelote.HEART_QUEEN,CardBelote.SPADE_10,
                CardBelote.CLUB_10,CardBelote.SPADE_9,CardBelote.CLUB_7,CardBelote.HEART_8,CardBelote.HEART_7));
        db_.getDeal().add(create(CardBelote.DIAMOND_KING,CardBelote.DIAMOND_QUEEN,CardBelote.DIAMOND_JACK,CardBelote.SPADE_JACK,
                CardBelote.CLUB_JACK,CardBelote.SPADE_8,CardBelote.SPADE_7,CardBelote.DIAMOND_9));
        db_.getDeal().add(create(CardBelote.CLUB_KING,CardBelote.CLUB_QUEEN,CardBelote.CLUB_9,CardBelote.CLUB_8,
                CardBelote.CLUB_1,CardBelote.HEART_1,CardBelote.DIAMOND_1,CardBelote.SPADE_1));
        return db_;
    }

    private static BidBeloteSuit bidSuit(Suit _suit, int _pts, BidBelote _bid) {
        BidBeloteSuit suit_ = new BidBeloteSuit();
        suit_.setSuit(_suit);
        suit_.setPoints(_pts);
        suit_.setBid(_bid);
        return suit_;
    }
    private static HandBelote create(CardBelote... _cards) {
        return HandBelote.create(_cards);
    }
}
