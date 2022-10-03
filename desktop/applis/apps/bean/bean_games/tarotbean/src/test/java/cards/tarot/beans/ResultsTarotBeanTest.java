package cards.tarot.beans;

import cards.consts.GameType;
import cards.tarot.*;
import cards.tarot.enumerations.BidTarot;
import cards.tarot.enumerations.CardTarot;
import cards.tarot.enumerations.DealingTarot;
import cards.tarot.enumerations.ModeTarot;
import code.util.CustList;
import code.util.Longs;
import code.util.StringList;
import org.junit.Test;

public final class ResultsTarotBeanTest extends BeanTarotCommonTs {

    @Test
    public void eq1() {
        assertTrue(callResultsTarotBeanEquality(displaying(beanResultsTarot(EN,results(game1(),0)))));
    }
    @Test
    public void eq2() {
        assertFalse(callResultsTarotBeanEquality(displaying(beanResultsTarot(EN,results(game2(),0)))));
    }

    @Test
    public void loose1() {
        assertFalse(callResultsTarotBeanLoose(displaying(beanResultsTarot(EN,results(game1(),0)))));
    }
    @Test
    public void loose2() {
        assertTrue(callResultsTarotBeanLoose(displaying(beanResultsTarot(EN,results(game2(),0)))));
    }

    @Test
    public void win1() {
        assertFalse(callResultsTarotBeanWin(displaying(beanResultsTarot(EN,results(game2(),0)))));
    }
    @Test
    public void win2() {
        assertTrue(callResultsTarotBeanWin(displaying(beanResultsTarot(EN,results(game2(),1)))));
    }

    @Test
    public void failBid1() {
        assertFalse(callResultsTarotBeanFailedBid(displaying(beanResultsTarot(EN,results(game1(),0)))));
    }
    @Test
    public void failBid2() {
        assertTrue(callResultsTarotBeanFailedBid(displaying(beanResultsTarot(EN,results(game2(),0)))));
    }

    @Test
    public void midBid1() {
        assertTrue(callResultsTarotBeanMidBid(displaying(beanResultsTarot(EN,results(game1(),0)))));
    }
    @Test
    public void midBid2() {
        assertFalse(callResultsTarotBeanMidBid(displaying(beanResultsTarot(EN,results(game2(),0)))));
    }

    @Test
    public void sucBid1() {
        assertTrue(callResultsTarotBeanSuccessfulBid(displaying(beanResultsTarot(EN,results(game3(),0)))));
    }
    @Test
    public void sucBid2() {
        assertFalse(callResultsTarotBeanSuccessfulBid(displaying(beanResultsTarot(EN,results(game2(),0)))));
    }
    private static ResultsTarot results(GameTarot _g, int _user) {
        ResultsTarot res_ = new ResultsTarot();
        res_.setGame(_g);
        res_.getRes().setUser((byte) _user);
        res_.initialize(fourPseudos("0","1","2","3"), new CustList<Longs>());
//        res_.getRes().setGeneral(CoreResourcesAccess.key(Suit.SPADE)+SEP+ SPADE);
        res_.getRes().setGeneral("");
        res_.getRes().setSpecific("");
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

    private static GameTarot game3() {
        RulesTarot rules_ = new RulesTarot((byte) 4);
        rules_.setMode(ModeTarot.NORMAL);
        rules_.setDealing(DealingTarot.DEAL_1_VS_3);
        DealTarot deal_ = dealLoose((byte) 3);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDealer());
        game_.ajouterContrat(BidTarot.TAKE, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        game_.initDefense();

        game_.ajouterCartesUtilisateur();
        game_.ajouterUneCarteDansPliEnCours(game_.getPreneur(),CardTarot.HEART_QUEEN);
        game_.ajouterUneCarteDansPliEnCours(game_.getPreneur(),CardTarot.SPADE_QUEEN);
        game_.ajouterUneCarteDansPliEnCours(game_.getPreneur(),CardTarot.DIAMOND_QUEEN);
        game_.ajouterUneCarteDansPliEnCours(game_.getPreneur(),CardTarot.CLUB_QUEEN);
        game_.ajouterUneCarteDansPliEnCours(game_.getPreneur(),CardTarot.SPADE_KNIGHT);
        game_.ajouterUneCarteDansPliEnCours(game_.getPreneur(),CardTarot.DIAMOND_KNIGHT);
        game_.addCurTrick();
        game_.setPliEnCours(true);
        play(game_,0,CardTarot.TRUMP_21);
        play(game_,1,CardTarot.TRUMP_3);
        play(game_,2,CardTarot.TRUMP_11);
        play(game_,3,CardTarot.TRUMP_15);
        tr(game_);
        play(game_,0,CardTarot.HEART_KING);
        play(game_,1,CardTarot.HEART_3);
        play(game_,2,CardTarot.HEART_JACK);
        play(game_,3,CardTarot.HEART_KNIGHT);
        tr(game_);
        play(game_,0,CardTarot.HEART_1);
        play(game_,1,CardTarot.HEART_4);
        play(game_,2,CardTarot.HEART_5);
        play(game_,3,CardTarot.HEART_7);
        tr(game_);
        play(game_,3,CardTarot.CLUB_6);
        play(game_,0,CardTarot.TRUMP_1);
        play(game_,1,CardTarot.CLUB_1);
        play(game_,2,CardTarot.CLUB_2);
        tr(game_);
        play(game_,0,CardTarot.HEART_2);
        play(game_,1,CardTarot.TRUMP_4);
        play(game_,2,CardTarot.HEART_6);
        play(game_,3,CardTarot.HEART_8);
        tr(game_);
        play(game_,1,CardTarot.CLUB_9);
        play(game_,2,CardTarot.CLUB_3);
        play(game_,3,CardTarot.CLUB_7);
        play(game_,0,CardTarot.TRUMP_2);
        tr(game_);
        play(game_,0,CardTarot.HEART_9);
        play(game_,1,CardTarot.TRUMP_5);
        play(game_,2,CardTarot.TRUMP_12);
        play(game_,3,CardTarot.EXCUSE);
        tr(game_);
        play(game_,2,CardTarot.DIAMOND_KING);
        play(game_,3,CardTarot.DIAMOND_1);
        play(game_,0,CardTarot.DIAMOND_9);
        play(game_,1,CardTarot.DIAMOND_2);
        tr(game_);
        play(game_,2,CardTarot.DIAMOND_JACK);
        play(game_,3,CardTarot.DIAMOND_4);
        play(game_,0,CardTarot.DIAMOND_10);
        play(game_,1,CardTarot.DIAMOND_7);
        tr(game_);
        play(game_,2,CardTarot.DIAMOND_6);
        play(game_,3,CardTarot.DIAMOND_5);
        play(game_,0,CardTarot.SPADE_9);
        play(game_,1,CardTarot.DIAMOND_8);
        tr(game_);
        play(game_,1,CardTarot.CLUB_KING);
        play(game_,2,CardTarot.CLUB_KNIGHT);
        play(game_,3,CardTarot.CLUB_8);
        play(game_,0,CardTarot.SPADE_10);
        tr(game_);
        play(game_,1,CardTarot.CLUB_JACK);
        play(game_,2,CardTarot.CLUB_4);
        play(game_,3,CardTarot.CLUB_10);
        play(game_,0,CardTarot.HEART_10);
        tr(game_);
        play(game_,1,CardTarot.SPADE_KING);
        play(game_,2,CardTarot.SPADE_JACK);
        play(game_,3,CardTarot.SPADE_8);
        play(game_,0,CardTarot.SPADE_1);
        tr(game_);
        play(game_,1,CardTarot.TRUMP_6);
        play(game_,2,CardTarot.TRUMP_13);
        play(game_,3,CardTarot.TRUMP_16);
        play(game_,0,CardTarot.SPADE_2);
        tr(game_);
        play(game_,3,CardTarot.TRUMP_20);
        play(game_,0,CardTarot.SPADE_3);
        play(game_,1,CardTarot.TRUMP_7);
        play(game_,2,CardTarot.TRUMP_14);
        tr(game_);
        play(game_,3,CardTarot.TRUMP_19);
        play(game_,0,CardTarot.SPADE_4);
        play(game_,1,CardTarot.TRUMP_8);
        play(game_,2,CardTarot.SPADE_7);
        tr(game_);
        play(game_,3,CardTarot.TRUMP_18);
        play(game_,0,CardTarot.SPADE_5);
        play(game_,1,CardTarot.TRUMP_9);
        play(game_,2,CardTarot.CLUB_5);
        tr(game_);
        play(game_,3,CardTarot.TRUMP_17);
        play(game_,0,CardTarot.SPADE_6);
        play(game_,1,CardTarot.TRUMP_10);
        play(game_,2,CardTarot.DIAMOND_3);
        tr(game_);
        return game_;
    }
    private static GameTarot game2() {
        RulesTarot rules_ = new RulesTarot((byte) 4);
        rules_.setMode(ModeTarot.NORMAL);
        rules_.setDealing(DealingTarot.DEAL_1_VS_3);
        DealTarot deal_ = dealLoose((byte) 3);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDealer());
        game_.ajouterContrat(BidTarot.TAKE, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        game_.initDefense();

        game_.ajouterCartesUtilisateur();
        game_.ajouterUneCarteDansPliEnCours(game_.getPreneur(),CardTarot.HEART_QUEEN);
        game_.ajouterUneCarteDansPliEnCours(game_.getPreneur(),CardTarot.SPADE_QUEEN);
        game_.ajouterUneCarteDansPliEnCours(game_.getPreneur(),CardTarot.DIAMOND_QUEEN);
        game_.ajouterUneCarteDansPliEnCours(game_.getPreneur(),CardTarot.CLUB_QUEEN);
        game_.ajouterUneCarteDansPliEnCours(game_.getPreneur(),CardTarot.SPADE_KNIGHT);
        game_.ajouterUneCarteDansPliEnCours(game_.getPreneur(),CardTarot.DIAMOND_KNIGHT);
        game_.addCurTrick();
        game_.setPliEnCours(true);
        play(game_,0,CardTarot.TRUMP_21);
        play(game_,1,CardTarot.TRUMP_3);
        play(game_,2,CardTarot.TRUMP_11);
        play(game_,3,CardTarot.TRUMP_15);
        tr(game_);
        play(game_,0,CardTarot.HEART_KING);
        play(game_,1,CardTarot.HEART_3);
        play(game_,2,CardTarot.HEART_5);
        play(game_,3,CardTarot.HEART_7);
        tr(game_);
        play(game_,0,CardTarot.HEART_1);
        play(game_,1,CardTarot.HEART_4);
        play(game_,2,CardTarot.HEART_JACK);
        play(game_,3,CardTarot.HEART_KNIGHT);
        tr(game_);
        play(game_,3,CardTarot.CLUB_6);
        play(game_,0,CardTarot.TRUMP_1);
        play(game_,1,CardTarot.CLUB_1);
        play(game_,2,CardTarot.CLUB_2);
        tr(game_);
        play(game_,0,CardTarot.HEART_2);
        play(game_,1,CardTarot.TRUMP_4);
        play(game_,2,CardTarot.HEART_6);
        play(game_,3,CardTarot.HEART_8);
        tr(game_);
        play(game_,1,CardTarot.CLUB_9);
        play(game_,2,CardTarot.CLUB_3);
        play(game_,3,CardTarot.CLUB_7);
        play(game_,0,CardTarot.TRUMP_2);
        tr(game_);
        play(game_,0,CardTarot.HEART_9);
        play(game_,1,CardTarot.TRUMP_5);
        play(game_,2,CardTarot.TRUMP_12);
        play(game_,3,CardTarot.EXCUSE);
        tr(game_);
        play(game_,2,CardTarot.DIAMOND_KING);
        play(game_,3,CardTarot.DIAMOND_1);
        play(game_,0,CardTarot.DIAMOND_9);
        play(game_,1,CardTarot.DIAMOND_2);
        tr(game_);
        play(game_,2,CardTarot.DIAMOND_JACK);
        play(game_,3,CardTarot.DIAMOND_4);
        play(game_,0,CardTarot.DIAMOND_10);
        play(game_,1,CardTarot.DIAMOND_7);
        tr(game_);
        play(game_,2,CardTarot.DIAMOND_6);
        play(game_,3,CardTarot.DIAMOND_5);
        play(game_,0,CardTarot.SPADE_9);
        play(game_,1,CardTarot.DIAMOND_8);
        tr(game_);
        play(game_,1,CardTarot.CLUB_KING);
        play(game_,2,CardTarot.CLUB_KNIGHT);
        play(game_,3,CardTarot.CLUB_8);
        play(game_,0,CardTarot.SPADE_10);
        tr(game_);
        play(game_,1,CardTarot.CLUB_JACK);
        play(game_,2,CardTarot.CLUB_4);
        play(game_,3,CardTarot.CLUB_10);
        play(game_,0,CardTarot.HEART_10);
        tr(game_);
        play(game_,1,CardTarot.SPADE_KING);
        play(game_,2,CardTarot.SPADE_JACK);
        play(game_,3,CardTarot.SPADE_8);
        play(game_,0,CardTarot.SPADE_1);
        tr(game_);
        play(game_,1,CardTarot.TRUMP_6);
        play(game_,2,CardTarot.TRUMP_13);
        play(game_,3,CardTarot.TRUMP_16);
        play(game_,0,CardTarot.SPADE_2);
        tr(game_);
        play(game_,3,CardTarot.TRUMP_20);
        play(game_,0,CardTarot.SPADE_3);
        play(game_,1,CardTarot.TRUMP_7);
        play(game_,2,CardTarot.TRUMP_14);
        tr(game_);
        play(game_,3,CardTarot.TRUMP_19);
        play(game_,0,CardTarot.SPADE_4);
        play(game_,1,CardTarot.TRUMP_8);
        play(game_,2,CardTarot.SPADE_7);
        tr(game_);
        play(game_,3,CardTarot.TRUMP_18);
        play(game_,0,CardTarot.SPADE_5);
        play(game_,1,CardTarot.TRUMP_9);
        play(game_,2,CardTarot.CLUB_5);
        tr(game_);
        play(game_,3,CardTarot.TRUMP_17);
        play(game_,0,CardTarot.SPADE_6);
        play(game_,1,CardTarot.TRUMP_10);
        play(game_,2,CardTarot.DIAMOND_3);
        tr(game_);
        return game_;
    }
    private static GameTarot game1() {
        RulesTarot rules_ = new RulesTarot((byte) 4);
        rules_.setMode(ModeTarot.NORMAL);
        rules_.setDealing(DealingTarot.DEAL_1_VS_3);
        DealTarot deal_ = dealEq((byte) 3);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDealer());
        game_.ajouterContrat(BidTarot.TAKE, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        game_.initDefense();

        game_.ajouterCartesUtilisateur();
        game_.ajouterUneCarteDansPliEnCours(game_.getPreneur(),CardTarot.HEART_QUEEN);
        game_.ajouterUneCarteDansPliEnCours(game_.getPreneur(),CardTarot.SPADE_QUEEN);
        game_.ajouterUneCarteDansPliEnCours(game_.getPreneur(),CardTarot.DIAMOND_QUEEN);
        game_.ajouterUneCarteDansPliEnCours(game_.getPreneur(),CardTarot.CLUB_QUEEN);
        game_.ajouterUneCarteDansPliEnCours(game_.getPreneur(),CardTarot.SPADE_KNIGHT);
        game_.ajouterUneCarteDansPliEnCours(game_.getPreneur(),CardTarot.DIAMOND_KNIGHT);
        game_.addCurTrick();
        game_.setPliEnCours(true);
        play(game_,0,CardTarot.TRUMP_21);
        play(game_,1,CardTarot.TRUMP_3);
        play(game_,2,CardTarot.TRUMP_11);
        play(game_,3,CardTarot.TRUMP_15);
        tr(game_);
        play(game_,0,CardTarot.HEART_KING);
        play(game_,1,CardTarot.HEART_3);
        play(game_,2,CardTarot.HEART_KNIGHT);
        play(game_,3,CardTarot.HEART_7);
        tr(game_);
        play(game_,0,CardTarot.HEART_1);
        play(game_,1,CardTarot.HEART_4);
        play(game_,2,CardTarot.HEART_5);
        play(game_,3,CardTarot.HEART_JACK);
        tr(game_);
        play(game_,3,CardTarot.CLUB_6);
        play(game_,0,CardTarot.TRUMP_1);
        play(game_,1,CardTarot.CLUB_1);
        play(game_,2,CardTarot.CLUB_2);
        tr(game_);
        play(game_,0,CardTarot.HEART_2);
        play(game_,1,CardTarot.TRUMP_4);
        play(game_,2,CardTarot.HEART_6);
        play(game_,3,CardTarot.HEART_8);
        tr(game_);
        play(game_,1,CardTarot.CLUB_9);
        play(game_,2,CardTarot.CLUB_3);
        play(game_,3,CardTarot.CLUB_7);
        play(game_,0,CardTarot.TRUMP_2);
        tr(game_);
        play(game_,0,CardTarot.HEART_9);
        play(game_,1,CardTarot.TRUMP_5);
        play(game_,2,CardTarot.TRUMP_12);
        play(game_,3,CardTarot.EXCUSE);
        tr(game_);
        play(game_,2,CardTarot.DIAMOND_KING);
        play(game_,3,CardTarot.DIAMOND_1);
        play(game_,0,CardTarot.DIAMOND_9);
        play(game_,1,CardTarot.DIAMOND_2);
        tr(game_);
        play(game_,2,CardTarot.DIAMOND_JACK);
        play(game_,3,CardTarot.DIAMOND_4);
        play(game_,0,CardTarot.DIAMOND_10);
        play(game_,1,CardTarot.DIAMOND_7);
        tr(game_);
        play(game_,2,CardTarot.DIAMOND_6);
        play(game_,3,CardTarot.DIAMOND_5);
        play(game_,0,CardTarot.SPADE_9);
        play(game_,1,CardTarot.DIAMOND_8);
        tr(game_);
        play(game_,1,CardTarot.CLUB_KING);
        play(game_,2,CardTarot.CLUB_KNIGHT);
        play(game_,3,CardTarot.CLUB_8);
        play(game_,0,CardTarot.SPADE_10);
        tr(game_);
        play(game_,1,CardTarot.CLUB_JACK);
        play(game_,2,CardTarot.CLUB_4);
        play(game_,3,CardTarot.CLUB_10);
        play(game_,0,CardTarot.HEART_10);
        tr(game_);
        play(game_,1,CardTarot.SPADE_KING);
        play(game_,2,CardTarot.SPADE_JACK);
        play(game_,3,CardTarot.SPADE_8);
        play(game_,0,CardTarot.SPADE_1);
        tr(game_);
        play(game_,1,CardTarot.TRUMP_6);
        play(game_,2,CardTarot.TRUMP_13);
        play(game_,3,CardTarot.TRUMP_16);
        play(game_,0,CardTarot.SPADE_2);
        tr(game_);
        play(game_,3,CardTarot.TRUMP_20);
        play(game_,0,CardTarot.SPADE_3);
        play(game_,1,CardTarot.TRUMP_7);
        play(game_,2,CardTarot.TRUMP_14);
        tr(game_);
        play(game_,3,CardTarot.TRUMP_19);
        play(game_,0,CardTarot.SPADE_4);
        play(game_,1,CardTarot.TRUMP_8);
        play(game_,2,CardTarot.SPADE_7);
        tr(game_);
        play(game_,3,CardTarot.TRUMP_18);
        play(game_,0,CardTarot.SPADE_5);
        play(game_,1,CardTarot.TRUMP_9);
        play(game_,2,CardTarot.CLUB_5);
        tr(game_);
        play(game_,3,CardTarot.TRUMP_17);
        play(game_,0,CardTarot.SPADE_6);
        play(game_,1,CardTarot.TRUMP_10);
        play(game_,2,CardTarot.DIAMOND_3);
        tr(game_);
        return game_;
    }


    private static void tr(GameTarot _game) {
        _game.ajouterPetitAuBoutPliEnCours();
        _game.setPliEnCours(true);
    }

    private static void play(GameTarot _game, int _nb, CardTarot _card) {
        _game.ajouterUneCarteDansPliEnCours((byte) _nb, _card);
    }

    private static DealTarot dealLoose(byte _dealer) {
        CustList<HandTarot> hands_ = new CustList<HandTarot>();
        HandTarot hand_;
        hand_ = new HandTarot();
        hand_.ajouter(CardTarot.TRUMP_21);
        hand_.ajouter(CardTarot.TRUMP_2);
        hand_.ajouter(CardTarot.TRUMP_1);
        hand_.ajouter(CardTarot.HEART_KING);
        hand_.ajouter(CardTarot.HEART_QUEEN);
        hand_.ajouter(CardTarot.SPADE_QUEEN);
        hand_.ajouter(CardTarot.DIAMOND_QUEEN);
        hand_.ajouter(CardTarot.CLUB_QUEEN);
        hand_.ajouter(CardTarot.SPADE_KNIGHT);
        hand_.ajouter(CardTarot.DIAMOND_KNIGHT);
        hand_.ajouter(CardTarot.HEART_10);
        hand_.ajouter(CardTarot.HEART_9);
        hand_.ajouter(CardTarot.HEART_2);
        hand_.ajouter(CardTarot.HEART_1);
        hand_.ajouter(CardTarot.SPADE_10);
        hand_.ajouter(CardTarot.SPADE_9);
        hand_.ajouter(CardTarot.DIAMOND_10);
        hand_.ajouter(CardTarot.DIAMOND_9);
        hands_.add(hand_);
        hand_ = new HandTarot();
        hand_.ajouter(CardTarot.DIAMOND_8);
        hand_.ajouter(CardTarot.DIAMOND_7);
        hand_.ajouter(CardTarot.DIAMOND_2);
        hand_.ajouter(CardTarot.CLUB_9);
        hand_.ajouter(CardTarot.CLUB_JACK);
        hand_.ajouter(CardTarot.CLUB_KING);
        hand_.ajouter(CardTarot.CLUB_1);
        hand_.ajouter(CardTarot.SPADE_KING);
        hand_.ajouter(CardTarot.HEART_4);
        hand_.ajouter(CardTarot.HEART_3);
        hand_.ajouter(CardTarot.TRUMP_10);
        hand_.ajouter(CardTarot.TRUMP_9);
        hand_.ajouter(CardTarot.TRUMP_8);
        hand_.ajouter(CardTarot.TRUMP_7);
        hand_.ajouter(CardTarot.TRUMP_6);
        hand_.ajouter(CardTarot.TRUMP_5);
        hand_.ajouter(CardTarot.TRUMP_4);
        hand_.ajouter(CardTarot.TRUMP_3);
        hands_.add(hand_);
        hand_ = new HandTarot();
        hand_.ajouter(CardTarot.CLUB_KNIGHT);
        hand_.ajouter(CardTarot.CLUB_3);
        hand_.ajouter(CardTarot.CLUB_4);
        hand_.ajouter(CardTarot.CLUB_5);
        hand_.ajouter(CardTarot.CLUB_2);
        hand_.ajouter(CardTarot.DIAMOND_KING);
        hand_.ajouter(CardTarot.DIAMOND_JACK);
        hand_.ajouter(CardTarot.DIAMOND_6);
        hand_.ajouter(CardTarot.DIAMOND_3);
        hand_.ajouter(CardTarot.SPADE_JACK);
        hand_.ajouter(CardTarot.SPADE_7);
        hand_.ajouter(CardTarot.HEART_JACK);
        hand_.ajouter(CardTarot.HEART_5);
        hand_.ajouter(CardTarot.HEART_6);
        hand_.ajouter(CardTarot.TRUMP_11);
        hand_.ajouter(CardTarot.TRUMP_12);
        hand_.ajouter(CardTarot.TRUMP_13);
        hand_.ajouter(CardTarot.TRUMP_14);
        hands_.add(hand_);
        hand_ = new HandTarot();
        hand_.ajouter(CardTarot.EXCUSE);
        hand_.ajouter(CardTarot.HEART_KNIGHT);
        hand_.ajouter(CardTarot.HEART_8);
        hand_.ajouter(CardTarot.HEART_7);
        hand_.ajouter(CardTarot.CLUB_10);
        hand_.ajouter(CardTarot.CLUB_7);
        hand_.ajouter(CardTarot.CLUB_6);
        hand_.ajouter(CardTarot.CLUB_8);
        hand_.ajouter(CardTarot.SPADE_8);
        hand_.ajouter(CardTarot.DIAMOND_5);
        hand_.ajouter(CardTarot.DIAMOND_4);
        hand_.ajouter(CardTarot.DIAMOND_1);
        hand_.ajouter(CardTarot.TRUMP_15);
        hand_.ajouter(CardTarot.TRUMP_16);
        hand_.ajouter(CardTarot.TRUMP_17);
        hand_.ajouter(CardTarot.TRUMP_18);
        hand_.ajouter(CardTarot.TRUMP_19);
        hand_.ajouter(CardTarot.TRUMP_20);
        hands_.add(hand_);
        hand_ = new HandTarot();
        hand_.ajouter(CardTarot.SPADE_1);
        hand_.ajouter(CardTarot.SPADE_2);
        hand_.ajouter(CardTarot.SPADE_3);
        hand_.ajouter(CardTarot.SPADE_4);
        hand_.ajouter(CardTarot.SPADE_5);
        hand_.ajouter(CardTarot.SPADE_6);
        hands_.add(hand_);
        return new DealTarot(hands_,_dealer);
    }
    private static DealTarot dealEq(byte _dealer) {
        CustList<HandTarot> hands_ = new CustList<HandTarot>();
        HandTarot hand_;
        hand_ = new HandTarot();
        hand_.ajouter(CardTarot.TRUMP_21);
        hand_.ajouter(CardTarot.TRUMP_2);
        hand_.ajouter(CardTarot.TRUMP_1);
        hand_.ajouter(CardTarot.HEART_KING);
        hand_.ajouter(CardTarot.HEART_QUEEN);
        hand_.ajouter(CardTarot.SPADE_QUEEN);
        hand_.ajouter(CardTarot.DIAMOND_QUEEN);
        hand_.ajouter(CardTarot.CLUB_QUEEN);
        hand_.ajouter(CardTarot.SPADE_KNIGHT);
        hand_.ajouter(CardTarot.DIAMOND_KNIGHT);
        hand_.ajouter(CardTarot.HEART_10);
        hand_.ajouter(CardTarot.HEART_9);
        hand_.ajouter(CardTarot.HEART_2);
        hand_.ajouter(CardTarot.HEART_1);
        hand_.ajouter(CardTarot.SPADE_10);
        hand_.ajouter(CardTarot.SPADE_9);
        hand_.ajouter(CardTarot.DIAMOND_10);
        hand_.ajouter(CardTarot.DIAMOND_9);
        hands_.add(hand_);
        hand_ = new HandTarot();
        hand_.ajouter(CardTarot.DIAMOND_8);
        hand_.ajouter(CardTarot.DIAMOND_7);
        hand_.ajouter(CardTarot.DIAMOND_2);
        hand_.ajouter(CardTarot.CLUB_9);
        hand_.ajouter(CardTarot.CLUB_JACK);
        hand_.ajouter(CardTarot.CLUB_KING);
        hand_.ajouter(CardTarot.CLUB_1);
        hand_.ajouter(CardTarot.SPADE_KING);
        hand_.ajouter(CardTarot.HEART_4);
        hand_.ajouter(CardTarot.HEART_3);
        hand_.ajouter(CardTarot.TRUMP_10);
        hand_.ajouter(CardTarot.TRUMP_9);
        hand_.ajouter(CardTarot.TRUMP_8);
        hand_.ajouter(CardTarot.TRUMP_7);
        hand_.ajouter(CardTarot.TRUMP_6);
        hand_.ajouter(CardTarot.TRUMP_5);
        hand_.ajouter(CardTarot.TRUMP_4);
        hand_.ajouter(CardTarot.TRUMP_3);
        hands_.add(hand_);
        hand_ = new HandTarot();
        hand_.ajouter(CardTarot.CLUB_KNIGHT);
        hand_.ajouter(CardTarot.CLUB_3);
        hand_.ajouter(CardTarot.CLUB_4);
        hand_.ajouter(CardTarot.CLUB_5);
        hand_.ajouter(CardTarot.CLUB_2);
        hand_.ajouter(CardTarot.DIAMOND_KING);
        hand_.ajouter(CardTarot.DIAMOND_JACK);
        hand_.ajouter(CardTarot.DIAMOND_6);
        hand_.ajouter(CardTarot.DIAMOND_3);
        hand_.ajouter(CardTarot.SPADE_JACK);
        hand_.ajouter(CardTarot.SPADE_7);
        hand_.ajouter(CardTarot.HEART_KNIGHT);
        hand_.ajouter(CardTarot.HEART_5);
        hand_.ajouter(CardTarot.HEART_6);
        hand_.ajouter(CardTarot.TRUMP_11);
        hand_.ajouter(CardTarot.TRUMP_12);
        hand_.ajouter(CardTarot.TRUMP_13);
        hand_.ajouter(CardTarot.TRUMP_14);
        hands_.add(hand_);
        hand_ = new HandTarot();
        hand_.ajouter(CardTarot.EXCUSE);
        hand_.ajouter(CardTarot.HEART_JACK);
        hand_.ajouter(CardTarot.HEART_8);
        hand_.ajouter(CardTarot.HEART_7);
        hand_.ajouter(CardTarot.CLUB_10);
        hand_.ajouter(CardTarot.CLUB_7);
        hand_.ajouter(CardTarot.CLUB_6);
        hand_.ajouter(CardTarot.CLUB_8);
        hand_.ajouter(CardTarot.SPADE_8);
        hand_.ajouter(CardTarot.DIAMOND_5);
        hand_.ajouter(CardTarot.DIAMOND_4);
        hand_.ajouter(CardTarot.DIAMOND_1);
        hand_.ajouter(CardTarot.TRUMP_15);
        hand_.ajouter(CardTarot.TRUMP_16);
        hand_.ajouter(CardTarot.TRUMP_17);
        hand_.ajouter(CardTarot.TRUMP_18);
        hand_.ajouter(CardTarot.TRUMP_19);
        hand_.ajouter(CardTarot.TRUMP_20);
        hands_.add(hand_);
        hand_ = new HandTarot();
        hand_.ajouter(CardTarot.SPADE_1);
        hand_.ajouter(CardTarot.SPADE_2);
        hand_.ajouter(CardTarot.SPADE_3);
        hand_.ajouter(CardTarot.SPADE_4);
        hand_.ajouter(CardTarot.SPADE_5);
        hand_.ajouter(CardTarot.SPADE_6);
        hands_.add(hand_);
        return new DealTarot(hands_,_dealer);
    }

}
