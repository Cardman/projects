package cards.belote.beans;

import cards.belote.*;
import cards.belote.enumerations.*;
import cards.consts.*;
import code.bean.nat.NatNavigation;
import code.scripts.pages.cards.MessBelotePage;
import code.scripts.pages.cards.PagesBelotes;
import code.sml.NavigationCore;
import code.sml.util.*;
import code.util.CustList;
import code.util.Longs;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.BoolVal;
import org.junit.Test;

public final class DetailsResultsBeloteBeanTest extends BeanBeloteCommonTs {

    private static final String SPADE = "spade";
    private static final String TAKER = "taker";
    private static final String HUNDRED = "hundred";
    @Test
    public void declaring1() {
        assertSizeEq(4, callDetailsResultsBeloteBeanDeclaring(displaying(beanDetailResultsBelote(EN, results(game1(), 0)))));
    }
    @Test
    public void declaring2() {
        assertSizeEq(0, callDetailsResultsBeloteBeanDeclaring(displaying(beanDetailResultsBelote(EN, results(game5(), 0)))));
    }

    @Test
    public void sum() {
        assertEq(130,callBeloteSumDeclaringPlayerSum(elt(callDetailsResultsBeloteBeanDeclaring(displaying(beanDetailResultsBelote(EN, results(game1(), 0)))),0)));
    }

    @Test
    public void nickname() {
        assertEq("0",callBeloteSumDeclaringPlayerNickname(elt(callDetailsResultsBeloteBeanDeclaring(displaying(beanDetailResultsBelote(EN, results(game1(), 0)))),0)));
    }

    @Test
    public void status() {
        assertEq(TAKER,callBeloteSumDeclaringPlayerStatut(elt(callDetailsResultsBeloteBeanDeclaring(displaying(beanDetailResultsBelote(EN, results(game1(), 0)))),0)));
    }

    @Test
    public void declaringValue() {
        assertSizeEq(3, callBeloteSumDeclaringPlayerDeclaring(elt(callDetailsResultsBeloteBeanDeclaring(displaying(beanDetailResultsBelote(EN, results(game1(), 0)))),0)));
    }

    @Test
    public void declaringStr() {
        assertEq(HUNDRED,callDeclaringPlayerValueDeclaring(elt( callBeloteSumDeclaringPlayerDeclaring(elt(callDetailsResultsBeloteBeanDeclaring(displaying(beanDetailResultsBelote(EN, results(game1(), 0)))),0)),0)));
    }

    @Test
    public void declaringVal1() {
        assertEq(100,callDeclaringPlayerValueValue(elt( callBeloteSumDeclaringPlayerDeclaring(elt(callDetailsResultsBeloteBeanDeclaring(displaying(beanDetailResultsBelote(EN, results(game1(), 0)))),0)),0)));
    }

    @Test
    public void declaringVal2() {
        assertEq(20,callDeclaringPlayerValueValue(elt( callBeloteSumDeclaringPlayerDeclaring(elt(callDetailsResultsBeloteBeanDeclaring(displaying(beanDetailResultsBelote(EN, results(game1(), 0)))),0)),1)));
    }

    @Test
    public void declaringVal3() {
        assertEq(10,callDeclaringPlayerValueValue(elt( callBeloteSumDeclaringPlayerDeclaring(elt(callDetailsResultsBeloteBeanDeclaring(displaying(beanDetailResultsBelote(EN, results(game1(), 0)))),0)),2)));
    }

    @Test
    public void init() {
        StringMap<String> other_ = MessBelotePage.ms();
        NavigationCore.adjust(other_);
        StringMap<TranslationsAppli> mes_ = new StringMap<TranslationsAppli>();
        mes_.addEntry(EN,MessBelotePage.enBelote());
        mes_.addEntry(FR,MessBelotePage.frBelote());
        BeloteStandardsDetailResults stds_ = new BeloteStandardsDetailResults();
        NatNavigation nav_ = stds_.nav(new StringList("en","fr"),EN,new DetailsBeloteLoader(), PagesBelotes.buildDetails(),other_,mes_,"");
        stds_.setDataBase(results(game1(), 0));
        stds_.initializeRendSessionDoc(nav_);
        assertEq("<html xmlns:c=\"javahtml\"><head><title>Results</title><link href=\"resources_cards/css/belote.css\" rel=\"stylesheet\" type=\"text/css\"/><style>h1 {\n" +
                "\tcolor:blue;\n" +
                "}\n" +
                "</style></head><body><h1>Details of declaring</h1><ul><li>0's declaring (taker):<br/><ul><li>hundred : 100</li><li> : 20</li><li> : 10</li><li>Sum :130</li></ul></li><li>1's declaring (defender):No thing</li><li>2's declaring (partner):No thing</li><li>3's declaring (defender):No thing</li></ul></body></html>",nav_.getHtmlText());
    }
    private static ResultsBelote results(GameBelote _g, int _user) {
        ResultsBelote res_ = new ResultsBelote();
        res_.setGame(_g);
        res_.getRes().setUser((byte) _user);
        res_.initialize(fourPseudos("0","1","2","3"), new CustList<Longs>());
        StringMap<String> s_ = new StringMap<String>();
        s_.addEntry(EnumCardsExporterUtil.SUITS +Suit.SPADE.getSuitSt(),SPADE);
        s_.addEntry(EnumCardsExporterUtil.ROLE +EnumCardsExporterUtil.fromRole(Role.TAKER),TAKER);
        s_.addEntry(EnumCardsExporterUtil.ROLE +EnumCardsExporterUtil.fromRole(Role.CALLED_PLAYER),"partner");
        s_.addEntry(EnumCardsExporterUtil.ROLE +EnumCardsExporterUtil.fromRole(Role.DEFENDER),"defender");
        res_.getRes().setGeneral(s_);
        StringMap<String> e_ = new StringMap<String>();
        e_.addEntry(BeloteCardsExporterUtil.DECLARE+BeloteCardsExporterUtil.fromDeclaresBelote(DeclaresBelote.HUNDRED),HUNDRED);
        res_.getRes().setSpecific(e_);
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

    private static GameBelote game1() {
        RulesBelote rules_ = new RulesBelote();
        rules_.getAllowedDeclares().put(DeclaresBelote.HUNDRED, BoolVal.TRUE);
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
        play(game_, CardBelote.CLUB_KING);
        play(game_, CardBelote.SPADE_8);
        play(game_, CardBelote.CLUB_7);
        tr(game_);
        play(game_, CardBelote.SPADE_9);
        play(game_, CardBelote.CLUB_9);
        play(game_, CardBelote.SPADE_7);
        play(game_, CardBelote.HEART_7);
        tr(game_);
        play(game_, CardBelote.SPADE_1);
        play(game_, CardBelote.DIAMOND_9);
        play(game_, CardBelote.DIAMOND_10);
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
        play(game_, CardBelote.SPADE_QUEEN);
        play(game_, CardBelote.CLUB_8);
        play(game_, CardBelote.HEART_KING);
        play(game_, CardBelote.HEART_JACK);
        tr(game_);
        play(game_, CardBelote.HEART_1);
        play(game_, CardBelote.HEART_10);
        play(game_, CardBelote.CLUB_1);
        play(game_, CardBelote.HEART_QUEEN);
        tr(game_);
        play(game_, CardBelote.SPADE_KING);
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
        _game.tryDeclareBeloteRebelote(_card);
        _game.premierTourAnnonce();
        _game.ajouterUneCarteDansPliEnCoursJoue(_card);
    }

    private static DealBelote deal1Slam(byte _dealer) {
        CustList<HandBelote> hands_ = new CustList<HandBelote>();
        HandBelote hand_;
        hand_ = new HandBelote();
        hand_.ajouter(CardBelote.DIAMOND_1);
        hand_.ajouter(CardBelote.SPADE_1);
        hand_.ajouter(CardBelote.SPADE_JACK);
        hand_.ajouter(CardBelote.SPADE_KING);
        hand_.ajouter(CardBelote.SPADE_QUEEN);
        hands_.add(hand_);
        hand_ = new HandBelote();
        hand_.ajouter(CardBelote.DIAMOND_9);
        hand_.ajouter(CardBelote.CLUB_9);
        hand_.ajouter(CardBelote.CLUB_JACK);
        hand_.ajouter(CardBelote.CLUB_KING);
        hand_.ajouter(CardBelote.HEART_10);
        hands_.add(hand_);
        hand_ = new HandBelote();
        hand_.ajouter(CardBelote.CLUB_1);
        hand_.ajouter(CardBelote.DIAMOND_QUEEN);
        hand_.ajouter(CardBelote.DIAMOND_7);
        hand_.ajouter(CardBelote.SPADE_8);
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
        hand_.ajouter(CardBelote.DIAMOND_10);
        hand_.ajouter(CardBelote.CLUB_10);
        hand_.ajouter(CardBelote.HEART_KING);
        hand_.ajouter(CardBelote.DIAMOND_KING);
        hand_.ajouter(CardBelote.HEART_JACK);
        hand_.ajouter(CardBelote.DIAMOND_JACK);
        hands_.add(hand_);
        return new DealBelote(hands_,_dealer);
    }
}
