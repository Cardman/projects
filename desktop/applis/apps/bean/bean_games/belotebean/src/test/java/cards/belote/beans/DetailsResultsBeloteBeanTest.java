package cards.belote.beans;

import cards.belote.*;
import cards.belote.enumerations.BidBelote;
import cards.belote.enumerations.CardBelote;
import cards.belote.enumerations.DeclaresBelote;
import cards.consts.CoreResourcesAccess;
import cards.consts.GameType;
import cards.consts.Role;
import cards.consts.Suit;
import code.formathtml.Navigation;
import code.formathtml.analyze.blocks.AnaRendBlock;
import code.scripts.pages.cards.MessBelotePage;
import code.scripts.pages.cards.PagesBelotes;
import code.util.CustList;
import code.util.Longs;
import code.util.StringList;
import code.util.StringMap;
import code.util.consts.Constants;
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
        AnaRendBlock.adjust(other_);
        BeloteStandardsDetailResults stds_ = new BeloteStandardsDetailResults();
        Navigation nav_ = stds_.nav(Constants.getAvailableLanguages(),EN,new DetailsBeloteLoader(), PagesBelotes.buildDetails(),other_,other_,"");
        stds_.setDataBase(results(game1(), 0));
        stds_.initializeRendSessionDoc(nav_);
        assertEq("<html xmlns:c=\"javahtml\"><head><title>Results</title><link href=\"resources_cards/css/belote.css\" rel=\"stylesheet\" type=\"text/css\"/><style>h1 {\n" +
                "\tcolor: #0000FF;\n" +
                "}\n" +
                "</style></head><body><h1>Details of declaring</h1><ul><li>0's declaring (taker):<br/><ul><li>hundred : 100</li><li> : 20</li><li> : 10</li><li>Sum :130</li></ul></li><li>1's declaring (defender):No thing</li><li>2's declaring (partner):No thing</li><li>3's declaring (defender):No thing</li></ul></body></html>",nav_.getHtmlText());
    }
    private static ResultsBelote results(GameBelote _g, int _user) {
        ResultsBelote res_ = new ResultsBelote();
        res_.setGame(_g);
        res_.getRes().setUser((byte) _user);
        res_.initialize(fourPseudos("0","1","2","3"), new CustList<Longs>());
        res_.getRes().setGeneral(CoreResourcesAccess.key(Suit.SPADE)+SEP+ SPADE+RETURNE_LINE+CoreResourcesAccess.key(Role.TAKER)+SEP+ TAKER+RETURNE_LINE+CoreResourcesAccess.key(Role.CALLED_PLAYER)+SEP+ "partner"+RETURNE_LINE+CoreResourcesAccess.key(Role.DEFENDER)+SEP+ "defender");
        res_.getRes().setSpecific(file(DeclaresBelote.HUNDRED, HUNDRED));
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
        game_.ajouterContrat(new BidBeloteSuit(), game_.playerHavingToBid());
        game_.ajouterContrat(new BidBeloteSuit(), game_.playerHavingToBid());
        game_.ajouterContrat(new BidBeloteSuit(), game_.playerHavingToBid());
        game_.ajouterContrat(new BidBeloteSuit(), game_.playerHavingToBid());
        game_.finEncherePremierTour();
        game_.ajouterContrat(new BidBeloteSuit(), game_.playerHavingToBid());
        game_.ajouterContrat(new BidBeloteSuit(), game_.playerHavingToBid());
        game_.ajouterContrat(new BidBeloteSuit(), game_.playerHavingToBid());
        game_.ajouterContrat(new BidBeloteSuit(), game_.playerHavingToBid());
        return game_;
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
        game_.ajouterContrat(bid_, (byte) first_);
        game_.completerDonne();
        game_.setPliEnCours();
        play(game_, 0, CardBelote.SPADE_JACK);
        play(game_, 1, CardBelote.CLUB_KING);
        play(game_, 2, CardBelote.SPADE_8);
        play(game_, 3, CardBelote.CLUB_7);
        tr(game_);
        play(game_, 0, CardBelote.SPADE_9);
        play(game_, 1, CardBelote.CLUB_9);
        play(game_, 2, CardBelote.SPADE_7);
        play(game_, 3, CardBelote.HEART_7);
        tr(game_);
        play(game_, 0, CardBelote.SPADE_1);
        play(game_, 1, CardBelote.DIAMOND_9);
        play(game_, 2, CardBelote.DIAMOND_10);
        play(game_, 3, CardBelote.HEART_9);
        tr(game_);
        play(game_, 0, CardBelote.SPADE_10);
        play(game_, 1, CardBelote.HEART_8);
        play(game_, 2, CardBelote.DIAMOND_QUEEN);
        play(game_, 3, CardBelote.DIAMOND_JACK);
        tr(game_);
        play(game_, 0, CardBelote.DIAMOND_1);
        play(game_, 1, CardBelote.DIAMOND_8);
        play(game_, 2, CardBelote.DIAMOND_7);
        play(game_, 3, CardBelote.DIAMOND_KING);
        tr(game_);
        play(game_, 0, CardBelote.SPADE_QUEEN);
        play(game_, 1, CardBelote.CLUB_8);
        play(game_, 2, CardBelote.HEART_KING);
        play(game_, 3, CardBelote.HEART_JACK);
        tr(game_);
        play(game_, 0, CardBelote.HEART_1);
        play(game_, 1, CardBelote.HEART_10);
        play(game_, 2, CardBelote.CLUB_1);
        play(game_, 3, CardBelote.HEART_QUEEN);
        tr(game_);
        play(game_, 0, CardBelote.SPADE_KING);
        play(game_, 1, CardBelote.CLUB_JACK);
        play(game_, 2, CardBelote.CLUB_10);
        play(game_, 3, CardBelote.CLUB_QUEEN);
        tr(game_);
        return game_;
    }
    private static void tr(GameBelote _game) {
        _game.ajouterDixDeDerPliEnCours();
        _game.firstRound();
        _game.setPliEnCours();
    }

    private static void play(GameBelote _game, int _nb, CardBelote _card) {
        _game.tryDeclareBeloteRebelote((byte) _nb,_card);
        _game.premierTourAnnonce((byte) _nb);
        _game.getDistribution().jouer((byte) _nb, _card);
        _game.ajouterUneCarteDansPliEnCours(_card);
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
    private static String file(DeclaresBelote _b, String _value) {
        return BeloteBean.key(_b)+ SEP +_value;
    }
}
