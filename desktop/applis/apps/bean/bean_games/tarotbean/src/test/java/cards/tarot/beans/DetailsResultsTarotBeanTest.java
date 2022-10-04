package cards.tarot.beans;

import cards.consts.CoreResourcesAccess;
import cards.consts.GameType;
import cards.consts.Role;
import cards.tarot.*;
import cards.tarot.enumerations.*;
import code.maths.Rate;
import code.util.CustList;
import code.util.IdList;
import code.util.Longs;
import code.util.StringList;
import org.junit.Test;

public final class DetailsResultsTarotBeanTest extends BeanTarotCommonTs {

    private static final String FOLD = "fold";
    private static final String TAKE = "take";
    private static final String WITHOUT = "without";
    private static final String TAKER = "taker";
    private static final String CALLED = "called";
    private static final String DEFENDER = "defender";
    private static final String FOUR = "four";
    private static final String SUIT = "suit";
    private static final String CHAR = "char";
    private static final String LOW = "low";

    @Test
    public void rate1() {
        assertEq(0,callDetailsResultsTarotBeanRate(displaying(beanDetailResultsTarot(EN,resultsFive(game9(),0)))));
    }

    @Test
    public void rate2() {
        assertEq(1,callDetailsResultsTarotBeanRate(displaying(beanDetailResultsTarot(EN,results(game3(),0)))));
    }

    @Test
    public void differenceScoreTaker() {
        assertEq(55,callDetailsResultsTarotBeanDifferenceScoreTaker(displaying(beanDetailResultsTarot(EN,resultsFive(game4(),0)))));
    }

    @Test
    public void basePoints() {
        assertEq(25,callDetailsResultsTarotBeanBasePoints(displaying(beanDetailResultsTarot(EN,resultsFive(game4(),0)))));
    }

    @Test
    public void small() {
        assertEq("10",callDetailsResultsTarotBeanSmall(displaying(beanDetailResultsTarot(EN,resultsFive(game4(),0)))));
    }

    @Test
    public void plSmall() {
        assertEq("0",callDetailsResultsTarotBeanPlayerSmall(displaying(beanDetailResultsTarot(EN,resultsFive(game4(),0)))));
    }

    @Test
    public void multipliedTmp() {
        assertEq(360,callDetailsResultsTarotBeanMultipliedTmp(displaying(beanDetailResultsTarot(EN,resultsFive(game4(),0)))));
    }

    @Test
    public void sumPlayers() {
        assertEq(105,callDetailsResultsTarotBeanSumPlayers(displaying(beanDetailResultsTarot(EN,resultsFive(game4(),0)))));
    }

    @Test
    public void bonusAtt() {
        assertEq(400,callDetailsResultsTarotBeanAdditionnalBonusesAttack(displaying(beanDetailResultsTarot(EN,resultsFive(game4(),0)))));
    }

    @Test
    public void bonusDef() {
        assertEq(0,callDetailsResultsTarotBeanAdditionnalBonusesDefense(displaying(beanDetailResultsTarot(EN,resultsFive(game4(),0)))));
    }

    @Test
    public void bonusDiff() {
        assertEq(400,callDetailsResultsTarotBeanDiffAttackDefenseBonuses(displaying(beanDetailResultsTarot(EN,resultsFive(game4(),0)))));
    }

    @Test
    public void playerScores() {
        assertSizeEq(5,callDetailsResultsTarotBeanPlayersScores(displaying(beanDetailResultsTarot(EN,resultsFive(game4(),0)))));
    }

    @Test
    public void scoreNickname() {
        assertEq("0",callScoresPlayersNickname(elt(callDetailsResultsTarotBeanPlayersScores(displaying(beanDetailResultsTarot(EN,resultsFive(game4(),0)))),0)));
    }

    @Test
    public void scoreRate() {
        assertEq(Rate.newRate("2"),callScoresPlayersRate(elt(callDetailsResultsTarotBeanPlayersScores(displaying(beanDetailResultsTarot(EN,resultsFive(game4(),0)))),0)));
    }

    @Test
    public void scoreScore() {
        assertEq(1730,callScoresPlayersScore(elt(callDetailsResultsTarotBeanPlayersScores(displaying(beanDetailResultsTarot(EN,resultsFive(game4(),0)))),0)));
    }

    @Test
    public void scoreSum() {
        assertEq(Rate.newRate("2"),callScoresPlayersSum(elt(callDetailsResultsTarotBeanPlayersScores(displaying(beanDetailResultsTarot(EN,resultsFive(game4(),0)))),0)));
    }

    @Test
    public void bonusesPlayers() {
        assertSizeEq(5,callDetailsResultsTarotBeanBonuses(displaying(beanDetailResultsTarot(EN,resultsFive(game7(),0)))));
    }

    @Test
    public void bonusNickname() {
        assertEq("0",callBonusesPlayersNickname(elt(callDetailsResultsTarotBeanBonuses(displaying(beanDetailResultsTarot(EN,resultsFive(game7(),0)))),0)));
    }

    @Test
    public void bonusScore() {
        assertEq(200,callBonusesPlayersBonus(elt(callDetailsResultsTarotBeanBonuses(displaying(beanDetailResultsTarot(EN,resultsFive(game7(),0)))),0)));
    }

    @Test
    public void linesDeclaring1() {
        assertSizeEq(5,callDetailsResultsTarotBeanLinesDeclaring(displaying(beanDetailResultsTarot(EN,resultsFive(game7(),0)))));
    }
    @Test
    public void linesDeclaring2() {
        assertSizeEq(5,callDetailsResultsTarotBeanLinesDeclaring(displaying(beanDetailResultsTarot(EN,resultsFive(game4(),0)))));
    }

    @Test
    public void declNickname() {
        assertEq("0",callTarotSumDeclaringPlayerNickname(elt(callDetailsResultsTarotBeanLinesDeclaring(displaying(beanDetailResultsTarot(EN,resultsFive(game4(),0)))),0)));
    }

    @Test
    public void declSum() {
        assertEq(105,callTarotSumDeclaringPlayerSum(elt(callDetailsResultsTarotBeanLinesDeclaring(displaying(beanDetailResultsTarot(EN,resultsFive(game4(),0)))),0)));
    }

    @Test
    public void declStatus() {
        assertEq(TAKER,callTarotSumDeclaringPlayerStatus(elt(callDetailsResultsTarotBeanLinesDeclaring(displaying(beanDetailResultsTarot(EN,resultsFive(game4(),0)))),0)));
    }

    @Test
    public void declHand() {
        assertSizeEq(1,callTarotSumDeclaringPlayerHandfuls(elt(callDetailsResultsTarotBeanLinesDeclaring(displaying(beanDetailResultsTarot(EN,resultsFive(game4(),0)))),0)));
    }

    @Test
    public void handFirst1() {
        assertFirstEq(FOUR,elt(callTarotSumDeclaringPlayerHandfuls(elt(callDetailsResultsTarotBeanLinesDeclaring(displaying(beanDetailResultsTarot(EN,resultsFive(game4(),0)))),0)),0));
    }

    @Test
    public void handSecond1() {
        assertSecondEq(50,elt(callTarotSumDeclaringPlayerHandfuls(elt(callDetailsResultsTarotBeanLinesDeclaring(displaying(beanDetailResultsTarot(EN,resultsFive(game4(),0)))),0)),0));
    }

    @Test
    public void declMiseres() {
        assertSizeEq(3,callTarotSumDeclaringPlayerMiseres(elt(callDetailsResultsTarotBeanLinesDeclaring(displaying(beanDetailResultsTarot(EN,resultsFive(game4(),0)))),0)));
    }

    @Test
    public void misereFirst1() {
        assertFirstEq(CHAR,elt(callTarotSumDeclaringPlayerMiseres(elt(callDetailsResultsTarotBeanLinesDeclaring(displaying(beanDetailResultsTarot(EN,resultsFive(game4(),0)))),0)),0));
    }

    @Test
    public void misereSecond1() {
        assertSecondEq(5,elt(callTarotSumDeclaringPlayerMiseres(elt(callDetailsResultsTarotBeanLinesDeclaring(displaying(beanDetailResultsTarot(EN,resultsFive(game4(),0)))),0)),0));
    }

    @Test
    public void misereFirst2() {
        assertFirstEq(SUIT,elt(callTarotSumDeclaringPlayerMiseres(elt(callDetailsResultsTarotBeanLinesDeclaring(displaying(beanDetailResultsTarot(EN,resultsFive(game4(),0)))),0)),1));
    }

    @Test
    public void misereSecond2() {
        assertSecondEq(30,elt(callTarotSumDeclaringPlayerMiseres(elt(callDetailsResultsTarotBeanLinesDeclaring(displaying(beanDetailResultsTarot(EN,resultsFive(game4(),0)))),0)),1));
    }

    @Test
    public void misereFirst3() {
        assertFirstEq(LOW,elt(callTarotSumDeclaringPlayerMiseres(elt(callDetailsResultsTarotBeanLinesDeclaring(displaying(beanDetailResultsTarot(EN,resultsFive(game4(),0)))),0)),2));
    }

    @Test
    public void misereSecond3() {
        assertSecondEq(20,elt(callTarotSumDeclaringPlayerMiseres(elt(callDetailsResultsTarotBeanLinesDeclaring(displaying(beanDetailResultsTarot(EN,resultsFive(game4(),0)))),0)),2));
    }
    private static ResultsTarot results(GameTarot _g, int _user) {
        ResultsTarot res_ = new ResultsTarot();
        res_.setGame(_g);
        res_.getRes().setUser((byte) _user);
        res_.initialize(fourPseudos("0","1","2","3"), new CustList<Longs>());
//        res_.getRes().setGeneral(CoreResourcesAccess.key(Suit.SPADE)+SEP+ SPADE);
        res_.getRes().setGeneral("");
        res_.getRes().setSpecific(file(BidTarot.FOLD, FOLD)+RETURNE_LINE+file(BidTarot.TAKE,TAKE)+RETURNE_LINE+file(BidTarot.GUARD_WITHOUT, WITHOUT)+RETURNE_LINE);
//        res_.getRes().setGeneral(readCoreResource());
//        res_.getRes().setSpecific(readResource());
        return res_;
    }
    private static ResultsTarot resultsFive(GameTarot _g, int _user) {
        ResultsTarot res_ = new ResultsTarot();
        res_.setGame(_g);
        res_.getRes().setUser((byte) _user);
        res_.initialize(fivePseudos("0","1","2","3","4"), new CustList<Longs>());
//        res_.getRes().setGeneral(CoreResourcesAccess.key(Suit.SPADE)+SEP+ SPADE);
        res_.getRes().setGeneral(file(Role.TAKER, TAKER)+RETURNE_LINE+file(Role.CALLED_PLAYER, CALLED)+RETURNE_LINE+file(Role.DEFENDER, DEFENDER));
        res_.getRes().setSpecific(file(BidTarot.FOLD, FOLD)+RETURNE_LINE+file(BidTarot.TAKE,TAKE)+RETURNE_LINE+file(BidTarot.GUARD_WITHOUT, WITHOUT)+RETURNE_LINE+file(Handfuls.FOUR, FOUR)+RETURNE_LINE+file(Miseres.SUIT, SUIT)+RETURNE_LINE+file(Miseres.LOW_CARDS, LOW)+RETURNE_LINE+file(Miseres.CHARACTER, CHAR));
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

    private static StringList fivePseudos(String _one, String _two, String _three, String _four, String _five) {
        StringList ps_ = new StringList();
        ps_.add(_one);
        ps_.add(_two);
        ps_.add(_three);
        ps_.add(_four);
        ps_.add(_five);
        return ps_;
    }

    private static GameTarot game7() {
        RulesTarot rules_ = new RulesTarot((byte) 5);
        rules_.setMode(ModeTarot.ONE_FOR_ONE);
        rules_.setDealing(DealingTarot.DEAL_2_VS_3_CALL_KING);
        rules_.getMiseres().add(Miseres.SUIT);
        rules_.getMiseres().add(Miseres.LOW_CARDS);
        rules_.getMiseres().add(Miseres.CHARACTER);
        rules_.getAllowedHandfuls().put(Handfuls.FOUR,15);
        DealTarot deal_ = dealFivePlayers((byte) 4);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        game_.setEntameur(game_.playerAfter(deal_.getDealer()));
        game_.setPliEnCours(true);
        handful(game_,0,Handfuls.FOUR,fifteen(CardTarot.TRUMP_21,CardTarot.TRUMP_20,CardTarot.TRUMP_19,
                CardTarot.TRUMP_18,CardTarot.TRUMP_17,CardTarot.TRUMP_16,
                CardTarot.TRUMP_15,CardTarot.TRUMP_14,CardTarot.TRUMP_13,
                CardTarot.TRUMP_12,CardTarot.TRUMP_11,CardTarot.TRUMP_10,
                CardTarot.TRUMP_9,CardTarot.TRUMP_8,CardTarot.TRUMP_1));
        miseres(game_,0,three(Miseres.SUIT,Miseres.CHARACTER,Miseres.LOW_CARDS));
        playedCards(game_);
        //CheckerGameTarotWithRules.check(game_);
        return game_;
    }

    private static GameTarot game8() {
        RulesTarot rules_ = new RulesTarot((byte) 5);
        rules_.setMode(ModeTarot.MISERE);
        rules_.setDealing(DealingTarot.DEAL_2_VS_3_CALL_KING);
        DealTarot deal_ = dealFivePlayers((byte) 4);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        game_.setEntameur(game_.playerAfter(deal_.getDealer()));
        game_.setPliEnCours(true);
        playedCards(game_);
        //CheckerGameTarotWithRules.check(game_);
        return game_;
    }

    private static GameTarot game9() {
        RulesTarot rules_ = new RulesTarot((byte) 5);
        rules_.setMode(ModeTarot.NORMAL);
        rules_.setDealing(DealingTarot.DEAL_2_VS_3_CALL_KING);
        DealTarot deal_ = dealFivePlayers((byte) 4);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        bidsFive3(deal_, game_);
        //CheckerGameTarotWithRules.check(game_);
        return game_;
    }
    private static GameTarot game4() {
        RulesTarot rules_ = new RulesTarot((byte) 5);
        rules_.setMode(ModeTarot.NORMAL);
        rules_.setDealing(DealingTarot.DEAL_2_VS_3_CALL_KING);
        rules_.getMiseres().add(Miseres.SUIT);
        rules_.getMiseres().add(Miseres.LOW_CARDS);
        rules_.getMiseres().add(Miseres.CHARACTER);
        rules_.getAllowedHandfuls().put(Handfuls.FOUR,15);
        DealTarot deal_ = dealFivePlayers((byte) 4);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        bidsFive(deal_, game_);
        game_.initConfianceAppeleUtilisateur(single(CardTarot.HEART_KING));
        game_.gererChienInconnu();
        game_.ajouterChelemUtilisateur();
        game_.setPliEnCours(true);
        handful(game_,0,Handfuls.FOUR,fifteen(CardTarot.TRUMP_21,CardTarot.TRUMP_20,CardTarot.TRUMP_19,
                CardTarot.TRUMP_18,CardTarot.TRUMP_17,CardTarot.TRUMP_16,
                CardTarot.TRUMP_15,CardTarot.TRUMP_14,CardTarot.TRUMP_13,
                CardTarot.TRUMP_12,CardTarot.TRUMP_11,CardTarot.TRUMP_10,
                CardTarot.TRUMP_9,CardTarot.TRUMP_8,CardTarot.TRUMP_1));
        miseres(game_,0,three(Miseres.SUIT,Miseres.CHARACTER,Miseres.LOW_CARDS));
        playedCards(game_);
        //CheckerGameTarotWithRules.check(game_);
        return game_;
    }
    private static void playedCards(GameTarot _game) {
        play(_game,0, CardTarot.TRUMP_21);
        play(_game,1, CardTarot.EXCUSE);
        play(_game,2, CardTarot.HEART_4);
        play(_game,3, CardTarot.HEART_8);
        play(_game,4, CardTarot.SPADE_10);
        secPlayedCards(_game);
    }

//    private static void playedCards2(GameTarot _game) {
//        play(_game,2, CardTarot.HEART_4);
//        play(_game,3, CardTarot.HEART_8);
//        play(_game,4, CardTarot.SPADE_10);
//        play(_game,0, CardTarot.TRUMP_21);
//        play(_game,1, CardTarot.EXCUSE);
//        secPlayedCards(_game);
//    }

    private static void secPlayedCards(GameTarot _game) {
        tr(_game);
        play(_game,0, CardTarot.TRUMP_20);
        play(_game,1, CardTarot.TRUMP_2);
        play(_game,2, CardTarot.HEART_3);
        play(_game,3, CardTarot.HEART_7);
        play(_game,4, CardTarot.SPADE_9);
        tr(_game);
        play(_game,0, CardTarot.TRUMP_19);
        play(_game,1, CardTarot.TRUMP_3);
        play(_game,2, CardTarot.HEART_2);
        play(_game,3, CardTarot.HEART_6);
        play(_game,4, CardTarot.DIAMOND_10);
        tr(_game);
        play(_game,0, CardTarot.TRUMP_18);
        play(_game,1, CardTarot.TRUMP_4);
        play(_game,2, CardTarot.HEART_1);
        play(_game,3, CardTarot.HEART_5);
        play(_game,4, CardTarot.DIAMOND_9);
        tr(_game);
        play(_game,0, CardTarot.TRUMP_17);
        play(_game,1, CardTarot.TRUMP_5);
        play(_game,2, CardTarot.SPADE_4);
        play(_game,3, CardTarot.SPADE_8);
        play(_game,4, CardTarot.CLUB_10);
        tr(_game);
        play(_game,0, CardTarot.TRUMP_16);
        play(_game,1, CardTarot.TRUMP_6);
        play(_game,2, CardTarot.SPADE_3);
        play(_game,3, CardTarot.SPADE_7);
        play(_game,4, CardTarot.CLUB_9);
        tr(_game);
        play(_game,0, CardTarot.TRUMP_15);
        play(_game,1, CardTarot.TRUMP_7);
        play(_game,2, CardTarot.SPADE_2);
        play(_game,3, CardTarot.SPADE_6);
        play(_game,4, CardTarot.CLUB_8);
        tr(_game);
        play(_game,0, CardTarot.TRUMP_14);
        play(_game,1, CardTarot.HEART_KING);
        play(_game,2, CardTarot.SPADE_1);
        play(_game,3, CardTarot.SPADE_5);
        play(_game,4, CardTarot.CLUB_7);
        tr(_game);
        play(_game,0, CardTarot.TRUMP_13);
        play(_game,1, CardTarot.HEART_QUEEN);
        play(_game,2, CardTarot.DIAMOND_4);
        play(_game,3, CardTarot.DIAMOND_8);
        play(_game,4, CardTarot.DIAMOND_JACK);
        tr(_game);
        play(_game,0, CardTarot.TRUMP_12);
        play(_game,1, CardTarot.HEART_KNIGHT);
        play(_game,2, CardTarot.DIAMOND_3);
        play(_game,3, CardTarot.DIAMOND_7);
        play(_game,4, CardTarot.DIAMOND_KNIGHT);
        tr(_game);
        play(_game,0, CardTarot.TRUMP_11);
        play(_game,1, CardTarot.HEART_JACK);
        play(_game,2, CardTarot.DIAMOND_2);
        play(_game,3, CardTarot.DIAMOND_6);
        play(_game,4, CardTarot.DIAMOND_QUEEN);
        tr(_game);
        play(_game,0, CardTarot.TRUMP_10);
        play(_game,1, CardTarot.SPADE_QUEEN);
        play(_game,2, CardTarot.DIAMOND_1);
        play(_game,3, CardTarot.DIAMOND_5);
        play(_game,4, CardTarot.CLUB_JACK);
        tr(_game);
        play(_game,0, CardTarot.TRUMP_9);
        play(_game,1, CardTarot.SPADE_KNIGHT);
        play(_game,2, CardTarot.CLUB_3);
        play(_game,3, CardTarot.CLUB_6);
        play(_game,4, CardTarot.CLUB_KNIGHT);
        tr(_game);
        play(_game,0, CardTarot.TRUMP_8);
        play(_game,1, CardTarot.SPADE_JACK);
        play(_game,2, CardTarot.CLUB_2);
        play(_game,3, CardTarot.CLUB_5);
        play(_game,4, CardTarot.CLUB_QUEEN);
        tr(_game);
        play(_game,0, CardTarot.TRUMP_1);
        play(_game,1, CardTarot.DIAMOND_KING);
        play(_game,2, CardTarot.CLUB_1);
        play(_game,3, CardTarot.CLUB_4);
        play(_game,4, CardTarot.CLUB_KING);
        tr(_game);
    }
    private static void miseres(GameTarot _game, int _player, IdList<Miseres> _miseres) {
        _game.setAnnoncesMiseres((byte) _player,_miseres);
    }
    private static IdList<Miseres> three(Miseres _one, Miseres _two, Miseres _three) {
        IdList<Miseres> miseres_ = new IdList<Miseres>();
        miseres_.add(_one);
        miseres_.add(_two);
        miseres_.add(_three);
        return miseres_;
    }
    private static void handful(GameTarot _game,int _player, Handfuls _hf, HandTarot _cs) {
        _game.setAnnoncesPoignees((byte)_player, single(_hf));
        _game.ajouterPoignee(_cs, (byte) _player);
    }

    private static IdList<Handfuls> single(Handfuls _card) {
        IdList<Handfuls> h_ = new IdList<Handfuls>();
        h_.add(_card);
        return h_;
    }
    private static HandTarot single(CardTarot _card) {
        HandTarot h_ = new HandTarot();
        h_.ajouter(_card);
        return h_;
    }
    private static HandTarot fifteen(CardTarot _one, CardTarot _two, CardTarot _three,
                                     CardTarot _four, CardTarot _five, CardTarot _six,
                                     CardTarot _seven, CardTarot _eight, CardTarot _nine,
                                     CardTarot _ten, CardTarot _eleven, CardTarot _twelve,
                                     CardTarot _thirteen, CardTarot _fourteen, CardTarot _fifteen) {
        HandTarot h_ = new HandTarot();
        h_.ajouter(_one);
        h_.ajouter(_two);
        h_.ajouter(_three);
        h_.ajouter(_four);
        h_.ajouter(_five);
        h_.ajouter(_six);
        h_.ajouter(_seven);
        h_.ajouter(_eight);
        h_.ajouter(_nine);
        h_.ajouter(_ten);
        h_.ajouter(_eleven);
        h_.ajouter(_twelve);
        h_.ajouter(_thirteen);
        h_.ajouter(_fourteen);
        h_.ajouter(_fifteen);
        return h_;
    }

    private static void bidsFive(DealTarot _deal, GameTarot _game) {
        int first_ = _game.playerAfter(_deal.getDealer());
        _game.ajouterContrat(BidTarot.GUARD_WITHOUT, (byte) first_);
        first_ = _game.playerAfter((byte) first_);
        _game.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = _game.playerAfter((byte) first_);
        _game.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = _game.playerAfter((byte) first_);
        _game.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = _game.playerAfter((byte) first_);
        _game.ajouterContrat(BidTarot.FOLD, (byte) first_);
    }
//
//    private static void bidsFive2(DealTarot _deal, GameTarot _game) {
//        int first_ = _game.playerAfter(_deal.getDealer());
//        _game.ajouterContrat(BidTarot.FOLD, (byte) first_);
//        first_ = _game.playerAfter((byte) first_);
//        _game.ajouterContrat(BidTarot.FOLD, (byte) first_);
//        first_ = _game.playerAfter((byte) first_);
//        _game.ajouterContrat(BidTarot.GUARD_AGAINST, (byte) first_);
////        first_ = _game.playerAfter((byte) first_);
////        _game.ajouterContrat(BidTarot.FOLD, (byte) first_);
////        first_ = _game.playerAfter((byte) first_);
////        _game.ajouterContrat(BidTarot.FOLD, (byte) first_);
//    }

    private static void bidsFive3(DealTarot _deal, GameTarot _game) {
        int first_ = _game.playerAfter(_deal.getDealer());
        _game.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = _game.playerAfter((byte) first_);
        _game.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = _game.playerAfter((byte) first_);
        _game.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = _game.playerAfter((byte) first_);
        _game.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = _game.playerAfter((byte) first_);
        _game.ajouterContrat(BidTarot.FOLD, (byte) first_);
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

    private static void tr(GameTarot _game) {
        _game.ajouterPetitAuBoutPliEnCours();
        _game.setPliEnCours(true);
    }

    private static void play(GameTarot _game, int _nb, CardTarot _card) {
        _game.ajouterUneCarteDansPliEnCours((byte) _nb, _card);
    }

    private static DealTarot dealFivePlayers(byte _dealer) {
        CustList<HandTarot> hands_ = new CustList<HandTarot>();
        HandTarot hand_;
        hand_ = new HandTarot();
        hand_.ajouter(CardTarot.TRUMP_21);
        hand_.ajouter(CardTarot.TRUMP_20);
        hand_.ajouter(CardTarot.TRUMP_19);
        hand_.ajouter(CardTarot.TRUMP_18);
        hand_.ajouter(CardTarot.TRUMP_17);
        hand_.ajouter(CardTarot.TRUMP_16);
        hand_.ajouter(CardTarot.TRUMP_15);
        hand_.ajouter(CardTarot.TRUMP_14);
        hand_.ajouter(CardTarot.TRUMP_13);
        hand_.ajouter(CardTarot.TRUMP_12);
        hand_.ajouter(CardTarot.TRUMP_11);
        hand_.ajouter(CardTarot.TRUMP_10);
        hand_.ajouter(CardTarot.TRUMP_9);
        hand_.ajouter(CardTarot.TRUMP_8);
        hand_.ajouter(CardTarot.TRUMP_1);
        hands_.add(hand_);
        hand_ = new HandTarot();
        hand_.ajouter(CardTarot.TRUMP_7);
        hand_.ajouter(CardTarot.TRUMP_6);
        hand_.ajouter(CardTarot.TRUMP_5);
        hand_.ajouter(CardTarot.TRUMP_4);
        hand_.ajouter(CardTarot.TRUMP_3);
        hand_.ajouter(CardTarot.TRUMP_2);
        hand_.ajouter(CardTarot.EXCUSE);
        hand_.ajouter(CardTarot.HEART_KING);
        hand_.ajouter(CardTarot.HEART_QUEEN);
        hand_.ajouter(CardTarot.HEART_KNIGHT);
        hand_.ajouter(CardTarot.HEART_JACK);
        hand_.ajouter(CardTarot.SPADE_QUEEN);
        hand_.ajouter(CardTarot.SPADE_KNIGHT);
        hand_.ajouter(CardTarot.SPADE_JACK);
        hand_.ajouter(CardTarot.DIAMOND_KING);
        hands_.add(hand_);
        hand_ = new HandTarot();
        hand_.ajouter(CardTarot.HEART_4);
        hand_.ajouter(CardTarot.HEART_3);
        hand_.ajouter(CardTarot.HEART_2);
        hand_.ajouter(CardTarot.HEART_1);
        hand_.ajouter(CardTarot.SPADE_4);
        hand_.ajouter(CardTarot.SPADE_3);
        hand_.ajouter(CardTarot.SPADE_2);
        hand_.ajouter(CardTarot.SPADE_1);
        hand_.ajouter(CardTarot.DIAMOND_4);
        hand_.ajouter(CardTarot.DIAMOND_3);
        hand_.ajouter(CardTarot.DIAMOND_2);
        hand_.ajouter(CardTarot.DIAMOND_1);
        hand_.ajouter(CardTarot.CLUB_3);
        hand_.ajouter(CardTarot.CLUB_2);
        hand_.ajouter(CardTarot.CLUB_1);
        hands_.add(hand_);
        hand_ = new HandTarot();
        hand_.ajouter(CardTarot.HEART_8);
        hand_.ajouter(CardTarot.HEART_7);
        hand_.ajouter(CardTarot.HEART_6);
        hand_.ajouter(CardTarot.HEART_5);
        hand_.ajouter(CardTarot.SPADE_8);
        hand_.ajouter(CardTarot.SPADE_7);
        hand_.ajouter(CardTarot.SPADE_6);
        hand_.ajouter(CardTarot.SPADE_5);
        hand_.ajouter(CardTarot.DIAMOND_8);
        hand_.ajouter(CardTarot.DIAMOND_7);
        hand_.ajouter(CardTarot.DIAMOND_6);
        hand_.ajouter(CardTarot.DIAMOND_5);
        hand_.ajouter(CardTarot.CLUB_6);
        hand_.ajouter(CardTarot.CLUB_5);
        hand_.ajouter(CardTarot.CLUB_4);
        hands_.add(hand_);
        hand_ = new HandTarot();
        hand_.ajouter(CardTarot.SPADE_10);
        hand_.ajouter(CardTarot.SPADE_9);
        hand_.ajouter(CardTarot.DIAMOND_QUEEN);
        hand_.ajouter(CardTarot.DIAMOND_KNIGHT);
        hand_.ajouter(CardTarot.DIAMOND_JACK);
        hand_.ajouter(CardTarot.DIAMOND_10);
        hand_.ajouter(CardTarot.DIAMOND_9);
        hand_.ajouter(CardTarot.CLUB_KING);
        hand_.ajouter(CardTarot.CLUB_QUEEN);
        hand_.ajouter(CardTarot.CLUB_KNIGHT);
        hand_.ajouter(CardTarot.CLUB_JACK);
        hand_.ajouter(CardTarot.CLUB_10);
        hand_.ajouter(CardTarot.CLUB_9);
        hand_.ajouter(CardTarot.CLUB_8);
        hand_.ajouter(CardTarot.CLUB_7);
        hands_.add(hand_);
        hand_ = new HandTarot();
        hand_.ajouter(CardTarot.HEART_10);
        hand_.ajouter(CardTarot.HEART_9);
        hand_.ajouter(CardTarot.SPADE_KING);
        hands_.add(hand_);
        return new DealTarot(hands_,_dealer);
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
    private static String file(Role _b, String _value) {
        return CoreResourcesAccess.key(_b)+ SEP +_value;
    }
    private static String file(Handfuls _b, String _value) {
        return TarotResoucesAccess.key(_b)+ SEP +_value;
    }
    private static String file(BidTarot _b, String _value) {
        return TarotResoucesAccess.key(_b)+ SEP +_value;
    }

    private static String file(Miseres _m, String _value) {
        return TarotResoucesAccess.key(_m)+ SEP +_value;
    }

}
