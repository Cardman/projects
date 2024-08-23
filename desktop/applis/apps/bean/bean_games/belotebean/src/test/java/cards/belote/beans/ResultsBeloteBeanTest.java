package cards.belote.beans;

import cards.belote.*;
import cards.belote.enumerations.*;
import cards.consts.*;
import code.bean.nat.NatNavigation;
import code.bean.nat.*;
import code.scripts.pages.cards.MessBelotePage;
import code.scripts.pages.cards.PagesBelotes;
import code.sml.util.*;
import code.util.CustList;
import code.util.Longs;
import code.util.StringList;
import code.util.StringMap;
import org.junit.Test;

public final class ResultsBeloteBeanTest extends BeanBeloteCommonTs {

    private static final String SPADE = "spade";

    @Test
    public void win1() {
        assertTrue(callResultsBeloteBeanWin(displaying(beanResultsBelote(EN,results(game1(),0)))));
    }

    @Test
    public void win2() {
        assertFalse(callResultsBeloteBeanWin(displaying(beanResultsBelote(EN,results(game1(),1)))));
    }
    @Test
    public void eq1() {
        assertTrue(callResultsBeloteBeanEquality(displaying(beanResultsBelote(EN,results(game3(),1)))));
    }
    @Test
    public void eq2() {
        assertFalse(callResultsBeloteBeanEquality(displaying(beanResultsBelote(EN,results(game1(),0)))));
    }

    @Test
    public void loose1() {
        assertFalse(callResultsBeloteBeanLoose(displaying(beanResultsBelote(EN,results(game1(),0)))));
    }

    @Test
    public void loose2() {
        assertTrue(callResultsBeloteBeanLoose(displaying(beanResultsBelote(EN,results(game1(),1)))));
    }

    @Test
    public void successfulBid1() {
        assertTrue(callResultsBeloteBeanSuccessfulBid(displaying(beanResultsBelote(EN,results(game1(),0)))));
    }

    @Test
    public void successfulBid2() {
        assertFalse(callResultsBeloteBeanSuccessfulBid(displaying(beanResultsBelote(EN,results(game4(),0)))));
    }
    @Test
    public void midBid1() {
        assertTrue(callResultsBeloteBeanMidBid(displaying(beanResultsBelote(EN,results(game3(),1)))));
    }
    @Test
    public void midBid2() {
        assertFalse(callResultsBeloteBeanMidBid(displaying(beanResultsBelote(EN,results(game1(),0)))));
    }

    @Test
    public void looseBid1() {
        assertTrue(callResultsBeloteBeanFailedBid(displaying(beanResultsBelote(EN,results(game4(),0)))));
    }

    @Test
    public void looseBid2() {
        assertFalse(callResultsBeloteBeanFailedBid(displaying(beanResultsBelote(EN,results(game1(),0)))));
    }

    @Test
    public void slam1() {
        assertTrue(callResultsBeloteBeanSlam(displaying(beanResultsBelote(EN,results(game1(),0)))));
    }

    @Test
    public void slam2() {
        assertFalse(callResultsBeloteBeanSlam(displaying(beanResultsBelote(EN,results(game2(),0)))));
    }

    @Test
    public void bidString1() {
        assertEq("",callResultsBeloteBeanBidString(displaying(beanResultsBelote(EN,results(game5(),0)))));
    }

    @Test
    public void bidString2() {
        assertEq(SPADE,callResultsBeloteBeanBidString(displaying(beanResultsBelote(EN,results(game1(),0)))));
    }

    @Test
    public void calledPlayersList() {
        NaSt res_ = callResultsBeloteBeanCalledPlayersList(displaying(beanResultsBelote(EN, results(game1(), 0))));
        assertSizeEq(1, res_);
        assertEq("2", res_,0);
    }

    @Test
    public void takerNickname() {
        NaSt res_ = callResultsBeloteBeanTakerNickname(displaying(beanResultsBelote(EN, results(game1(), 0))));
        assertEq("0", res_);
    }

    @Test
    public void differenceScoreTaker() {
        NaSt res_ = callResultsBeloteBeanDifferenceScoreTaker(displaying(beanResultsBelote(EN, results(game1(), 0))));
        assertEq(262, res_);
    }

    @Test
    public void absoluteDiff() {
        NaSt res_ = callResultsBeloteBeanAbsoluteDiff(displaying(beanResultsBelote(EN, results(game1(), 0))));
        assertEq(262, res_);
    }

    @Test
    public void pointsAttaqueSansPrime() {
        NaSt res_ = callResultsBeloteBeanPointsAttaqueSansPrime(displaying(beanResultsBelote(EN, results(game1(), 0))));
        assertEq(152, res_);
    }

    @Test
    public void pointsAttaqueDefinitif() {
        NaSt res_ = callResultsBeloteBeanPointsAttaqueDefinitif(displaying(beanResultsBelote(EN, results(game1(), 0))));
        assertEq(262, res_);
    }

    @Test
    public void pointsAttaqueTemporaire() {
        NaSt res_ = callResultsBeloteBeanPointsAttaqueTemporaire(displaying(beanResultsBelote(EN, results(game1(), 0))));
        assertEq(262, res_);
    }

    @Test
    public void pointsDefenseDefinitif() {
        NaSt res_ = callResultsBeloteBeanPointsDefenseDefinitif(displaying(beanResultsBelote(EN, results(game1(), 0))));
        assertEq(0, res_);
    }

    @Test
    public void pointsDefenseSansPrime() {
        NaSt res_ = callResultsBeloteBeanPointsDefenseSansPrime(displaying(beanResultsBelote(EN, results(game1(), 0))));
        assertEq(0, res_);
    }

    @Test
    public void pointsDefenseTemporaire() {
        NaSt res_ = callResultsBeloteBeanPointsDefenseTemporaire(displaying(beanResultsBelote(EN, results(game1(), 0))));
        assertEq(0, res_);
    }

    @Test
    public void linesDeal() {
        NaSt res_ = callResultsBeloteBeanLinesDeal(displaying(beanResultsBelote(EN, results(game1(), 0))));
        assertSizeEq(1, res_);
        assertSizeEq(4, res_,0);
        assertNumberEq(0, res_,0);
        assertEq(262, res_,0,0);
        assertEq(0, res_,0,1);
        assertEq(262, res_,0,2);
        assertEq(0, res_,0,3);
    }

    @Test
    public void playGame1() {
        assertTrue(callBeloteBeanPlayGame(displaying(beanResultsBelote(EN, results(game1(), 0)))));
    }

    @Test
    public void playGame2() {
        assertFalse(callBeloteBeanPlayGame(displaying(beanResultsBelote(EN, results(game5(), 0)))));
    }

    @Test
    public void getNickNames() {
        NaSt res_ = callBeloteBeanGetNicknames(displaying(beanResultsBelote(EN, results(game1(), 0))));
        assertSizeEq(4, res_);
        assertEq("0", res_,0);
        assertEq("1", res_,1);
        assertEq("2", res_,2);
        assertEq("3", res_,3);
    }

    @Test
    public void getScores() {
        NaSt res_ = callBeloteBeanGetScores(displaying(beanResultsBelote(EN, results(game1(), 0))));
        assertSizeEq(1, res_);
        assertSizeLongsEq(4, res_,0);
        assertLongsEq(262, res_,0,0);
        assertLongsEq(0, res_,0,1);
        assertLongsEq(262, res_,0,2);
        assertLongsEq(0, res_,0,3);
    }

    @Test
    public void init() {
        StringMap<String> other_ = MessBelotePage.ms();
//        NavigationCore.adjust(other_);
        StringMap<TranslationsAppli> mes_ = new StringMap<TranslationsAppli>();
        mes_.addEntry(EN,MessBelotePage.enBelote());
        mes_.addEntry(FR,MessBelotePage.frBelote());
        BeloteStandardsResults stds_ = new BeloteStandardsResults();
        NatNavigation nav_ = stds_.nav(new StringList("en","fr"), new ResultsBeloteLoader(), PagesBelotes.build(),other_,mes_);
        nav_.setLanguage(EN);
        stds_.setDataBase(results(game1(), 0));
        stds_.initializeRendSessionDoc(nav_);
        assertEq("<html xmlns:c=\"javahtml\"><head><title>Results</title><link href=\"resources_cards/css/belote.css\" rel=\"stylesheet\" type=\"text/css\"/><style>h1 {\n" +
                "\tcolor:blue;\n" +
                "}\n" +
                "</style></head><body><h1>1 Calculation of attack team's points</h1><ul><li>Number of necessary points in order that the taker wins:0</li><li>Number of points won in the attack team's tricks:262</li></ul><h1>2 Attack team</h1><ul><li>Taker:0</li><li>Taker's partners:<ul><li>2</li></ul></li><li>Bid:spade</li></ul><h1>3 Results</h1><p>Scored points by attack's team without bonuses:152</p><br/><p>Scored points by defense's team without bonuses:0</p><br/><p>Scored points by attack's team with bonuses:262</p><br/><p>Scored points by defense's team with bonuses:0</p><br/><p>Final scored points by attack's team:262</p><br/><p>Final scored points by defense's team:0</p><br/><p>You win.</p><br/><p>The bid spade is passed of 262 points.</p><br/><p>The attack's team has achieved the grand slam.</p><br/><br/><table border=\"1\"><caption>Scores</caption><thead><tr><td/><td>0</td><td>1</td><td>2</td><td>3</td></tr></thead><tbody><tr><td>0</td><td>262</td><td>0</td><td>262</td><td>0</td></tr></tbody></table><br/></body></html>",nav_.getHtmlText());
    }
    private static ResultsBelote results(GameBelote _g, int _user) {
        ResultsBelote res_ = new ResultsBelote();
        res_.setGame(_g);
        res_.getRes().setUser((byte) _user);
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
        DealBelote deal_ = deal1Loose((byte) 3);
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
        DealBelote deal_ = deal1Loose((byte) 3);
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
        DealBelote deal_ = deal1Eq((byte) 3);
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
        DealBelote deal_ = deal1Win((byte) 3);
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
        DealBelote deal_ = deal1Slam((byte) 3);
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

    private static void tr(GameBelote _game) {
        _game.ajouterDixDeDerPliEnCours();
    }

    private static void play(GameBelote _game, CardBelote _card) {
        _game.ajouterUneCarteDansPliEnCoursJoue(_card);
    }

    private static DealBelote deal1Slam(byte _dealer) {
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

    private static DealBelote deal1Eq(byte _dealer) {
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

    private static DealBelote deal1Loose(byte _dealer) {
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

    private static DealBelote deal1Win(byte _dealer) {
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
}
