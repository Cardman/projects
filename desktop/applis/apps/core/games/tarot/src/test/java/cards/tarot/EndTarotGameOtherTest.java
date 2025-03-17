package cards.tarot;

import cards.consts.GameType;
import cards.tarot.enumerations.*;
import code.util.*;
import code.util.core.BoolVal;
import org.junit.Test;

public final class EndTarotGameOtherTest extends CommonGameTarot {

    @Test
    public void setupPlayersWonTricksTest() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setDealing(DealingTarot.DEAL_1_VS_2);
        rules_.setMode(ModeTarot.ONE_FOR_ONE);
        rules_.setDiscardAfterCall(false);
        int dealer_ = 2;
        IdList<BidTarot> bids_ = new IdList<BidTarot>();
        HandTarot last_ = new HandTarot();
        last_.ajouter(CardTarot.SPADE_KNIGHT);
        last_.ajouter(CardTarot.SPADE_JACK);
        last_.ajouter(CardTarot.HEART_JACK);
        last_.ajouter(CardTarot.SPADE_4);
        last_.ajouter(CardTarot.CLUB_6);
        last_.ajouter(CardTarot.DIAMOND_JACK);
        CustList<TrickTarot> trs_ = new CustList<TrickTarot>();
        TrickTarot dog_ = new TrickTarot(3);
        dog_.ajouter(CardTarot.HEART_JACK);
        dog_.ajouter(CardTarot.SPADE_4);
        dog_.ajouter(CardTarot.CLUB_6);
        dog_.ajouter(CardTarot.DIAMOND_JACK);
        dog_.ajouter(CardTarot.SPADE_KNIGHT);
        dog_.ajouter(CardTarot.SPADE_JACK);
        trs_.add(dog_);
        CustList<BoolVal> dSlam_ = new CustList<BoolVal>();
        dSlam_.add(BoolVal.FALSE);
        dSlam_.add(BoolVal.FALSE);
        dSlam_.add(BoolVal.FALSE);
        TrickTarot t_ =  newClassicTrickFirst(trs_,rules_,dealer_);
        t_.ajouter(CardTarot.TRUMP_1);
        t_.ajouter(CardTarot.CLUB_4);
        t_.ajouter(CardTarot.HEART_7);
        trs_.add(t_);
        t_ = newClassicTrick(trs_,rules_,dealer_);
        t_.ajouter(CardTarot.TRUMP_4);
        t_.ajouter(CardTarot.DIAMOND_7);
        t_.ajouter(CardTarot.HEART_1);
        trs_.add(t_);
        t_ = newClassicTrick(trs_,rules_,dealer_);
        t_.ajouter(CardTarot.TRUMP_10);
        t_.ajouter(CardTarot.DIAMOND_6);
        t_.ajouter(CardTarot.HEART_6);
        trs_.add(t_);
        t_ = newClassicTrick(trs_,rules_,dealer_);
        t_.ajouter(CardTarot.TRUMP_14);
        t_.ajouter(CardTarot.HEART_3);
        t_.ajouter(CardTarot.HEART_5);
        trs_.add(t_);
        t_ = newClassicTrick(trs_,rules_,dealer_);
        t_.ajouter(CardTarot.TRUMP_17);
        t_.ajouter(CardTarot.HEART_2);
        t_.ajouter(CardTarot.SPADE_9);
        trs_.add(t_);
        t_ = newClassicTrick(trs_,rules_,dealer_);
        t_.ajouter(CardTarot.TRUMP_21);
        t_.ajouter(CardTarot.SPADE_5);
        t_.ajouter(CardTarot.SPADE_7);
        trs_.add(t_);
        t_ = newClassicTrick(trs_,rules_,dealer_);
        t_.ajouter(CardTarot.TRUMP_11);
        t_.ajouter(CardTarot.CLUB_9);
        t_.ajouter(CardTarot.DIAMOND_10);
        trs_.add(t_);
        t_ = newClassicTrick(trs_,rules_,dealer_);
        t_.ajouter(CardTarot.TRUMP_18);
        t_.ajouter(CardTarot.CLUB_KNIGHT);
        t_.ajouter(CardTarot.CLUB_KING);
        trs_.add(t_);
        t_ = newClassicTrick(trs_,rules_,dealer_);
        t_.ajouter(CardTarot.TRUMP_3);
        t_.ajouter(CardTarot.DIAMOND_KNIGHT);
        t_.ajouter(CardTarot.CLUB_2);
        trs_.add(t_);
        t_ = newClassicTrick(trs_,rules_,dealer_);
        t_.ajouter(CardTarot.TRUMP_6);
        t_.ajouter(CardTarot.DIAMOND_1);
        t_.ajouter(CardTarot.CLUB_1);
        trs_.add(t_);
        t_ = newClassicTrick(trs_,rules_,dealer_);
        t_.ajouter(CardTarot.TRUMP_13);
        t_.ajouter(CardTarot.DIAMOND_2);
        t_.ajouter(CardTarot.HEART_QUEEN);
        trs_.add(t_);
        t_ = newClassicTrick(trs_,rules_,dealer_);
        t_.ajouter(CardTarot.TRUMP_20);
        t_.ajouter(CardTarot.HEART_4);
        t_.ajouter(CardTarot.CLUB_3);
        trs_.add(t_);
        t_ = newClassicTrick(trs_,rules_,dealer_);
        t_.ajouter(CardTarot.TRUMP_16);
        t_.ajouter(CardTarot.HEART_KNIGHT);
        t_.ajouter(CardTarot.CLUB_QUEEN);
        trs_.add(t_);
        t_ = newClassicTrick(trs_,rules_,dealer_);
        t_.ajouter(CardTarot.TRUMP_2);
        t_.ajouter(CardTarot.HEART_10);
        t_.ajouter(CardTarot.DIAMOND_8);
        trs_.add(t_);
        t_ = newClassicTrick(trs_,rules_,dealer_);
        t_.ajouter(CardTarot.TRUMP_8);
        t_.ajouter(CardTarot.SPADE_8);
        t_.ajouter(CardTarot.DIAMOND_4);
        trs_.add(t_);
        t_ = newClassicTrick(trs_,rules_,dealer_);
        t_.ajouter(CardTarot.TRUMP_12);
        t_.ajouter(CardTarot.SPADE_3);
        t_.ajouter(CardTarot.EXCUSE);
        trs_.add(t_);
        t_ = newClassicTrick(trs_,rules_,dealer_);
        t_.ajouter(CardTarot.TRUMP_5);
        t_.ajouter(CardTarot.CLUB_JACK);
        t_.ajouter(CardTarot.HEART_9);
        trs_.add(t_);
        t_ = newClassicTrick(trs_,rules_,dealer_);
        t_.ajouter(CardTarot.TRUMP_9);
        t_.ajouter(CardTarot.CLUB_8);
        t_.ajouter(CardTarot.SPADE_10);
        trs_.add(t_);
        t_ = newClassicTrick(trs_,rules_,dealer_);
        t_.ajouter(CardTarot.TRUMP_15);
        t_.ajouter(CardTarot.DIAMOND_5);
        t_.ajouter(CardTarot.CLUB_7);
        trs_.add(t_);
        t_ = newClassicTrick(trs_,rules_,dealer_);
        t_.ajouter(CardTarot.TRUMP_19);
        t_.ajouter(CardTarot.HEART_8);
        t_.ajouter(CardTarot.CLUB_5);
        trs_.add(t_);
        t_ = newClassicTrick(trs_,rules_,dealer_);
        t_.ajouter(CardTarot.TRUMP_7);
        t_.ajouter(CardTarot.SPADE_2);
        t_.ajouter(CardTarot.SPADE_6);
        trs_.add(t_);
        t_ = newClassicTrick(trs_,rules_,dealer_);
        t_.ajouter(CardTarot.HEART_KING);
        t_.ajouter(CardTarot.SPADE_1);
        t_.ajouter(CardTarot.CLUB_10);
        trs_.add(t_);
        t_ = newClassicTrick(trs_,rules_,dealer_);
        t_.ajouter(CardTarot.SPADE_KING);
        t_.ajouter(CardTarot.DIAMOND_9);
        t_.ajouter(CardTarot.DIAMOND_KING);
        trs_.add(t_);
        t_ = newClassicTrick(trs_,rules_,dealer_);
        t_.ajouter(CardTarot.SPADE_QUEEN);
        t_.ajouter(CardTarot.DIAMOND_3);
        t_.ajouter(CardTarot.DIAMOND_QUEEN);
        trs_.add(t_);
        CustList<BoolVal> small_ = new CustList<BoolVal>();
        small_.add(BoolVal.FALSE);
        small_.add(BoolVal.FALSE);
        small_.add(BoolVal.FALSE);
        EndTarotGame endTarotGame_ = newEndTarotGame(rules_, trs_, dealer_, bids_, last_, small_);
        endTarotGame_.setupPlayersWonTricks();
        Ints firstTrick_ = endTarotGame_.getFirstTrick();
        CustList<HandTarot> wonPlayersTeam_ = endTarotGame_.getWonPlayersTeam();
        assertEq(3,firstTrick_.size());
        assertEq(1,firstTrick_.get(0));
        assertEq(-1,firstTrick_.get(1));
        assertEq(-1,firstTrick_.get(2));
        assertEq(3,wonPlayersTeam_.size());
        assertEq(72,wonPlayersTeam_.get(0).total());
        assertEq(0,wonPlayersTeam_.get(1).total());
        assertEq(0,wonPlayersTeam_.get(2).total());
        assertEq(41,endTarotGame_.scoreNecessaireJoueur(0));
        assertEq(157,endTarotGame_.scoreJoueurPlisDouble(0));
    }
    @Test
    public void primeSupplementaire1Test() {
        RulesTarot r_ = new RulesTarot();
        r_.setMode(ModeTarot.ONE_FOR_ONE);
        r_.setDealing(DealingTarot.DEAL_1_VS_2);
        GameTarotTeamsRelation g_ = splitted(r_);
        CustList<HandTarot> wonCards_ = new CustList<HandTarot>();
        wonCards_.add(HandTarot.pileBase());
        wonCards_.add(new HandTarot());
        wonCards_.add(new HandTarot());
        assertEq(200, EndTarotGame.primeSupplementaire(0,g_,wonCards_));
    }
    @Test
    public void primeSupplementaire2Test() {
        RulesTarot r_ = new RulesTarot();
        r_.setMode(ModeTarot.ONE_FOR_ONE);
        r_.setDealing(DealingTarot.DEAL_1_VS_2);
        GameTarotTeamsRelation g_ = splitted(r_);
        CustList<HandTarot> wonCards_ = new CustList<HandTarot>();
        wonCards_.add(HandTarot.pileBase());
        wonCards_.last().removeCardIfPresent(CardTarot.EXCUSE);
        wonCards_.add(new HandTarot());
        wonCards_.last().ajouter(CardTarot.EXCUSE);
        wonCards_.add(new HandTarot());
        assertEq(0, EndTarotGame.primeSupplementaire(0,g_,wonCards_));
    }

    @Test
    public void differenceMax1Test() {
        assertEq(30, EndTarotGame.differenceMax(50,10));
    }
    @Test
    public void differenceMax2Test() {
        assertEq(31, EndTarotGame.differenceMax(51,10));
    }
    @Test
    public void positionsDifferenceTest() {
        Longs diffs_ = new Longs();
        diffs_.add(10L);
        diffs_.add(0L);
        diffs_.add(-10L);
        diffs_.add(10L);
        diffs_.add(0L);
        diffs_.add(-10L);
        Ints pos_ = EndTarotGame.positionsDifference(diffs_);
        assertEq(6, pos_.size());
        assertEq(1, pos_.get(0));
        assertEq(3, pos_.get(1));
        assertEq(5, pos_.get(2));
        assertEq(1, pos_.get(3));
        assertEq(3, pos_.get(4));
        assertEq(5, pos_.get(5));
    }
    @Test
    public void buildGroups1Test() {
        Ints diffs_ = new Ints();
        diffs_.add(1);
        diffs_.add(1);
        diffs_.add(5);
        diffs_.add(1);
        diffs_.add(1);
        diffs_.add(5);
        CustList<Ints> gr_ = EndTarotGame.buildGroups(diffs_);
        assertEq(2,gr_.size());
        assertEq(4,gr_.get(0).size());
        assertEq(0,gr_.get(0).get(0));
        assertEq(1,gr_.get(0).get(1));
        assertEq(3,gr_.get(0).get(2));
        assertEq(4,gr_.get(0).get(3));
        assertEq(2,gr_.get(1).size());
        assertEq(2,gr_.get(1).get(0));
        assertEq(5,gr_.get(1).get(1));
    }
    @Test
    public void buildGroups2Test() {
        Ints diffs_ = new Ints();
        diffs_.add(1);
        diffs_.add(2);
        diffs_.add(3);
        CustList<Ints> gr_ = EndTarotGame.buildGroups(diffs_);
        assertEq(0,gr_.size());
    }
    @Test
    public void changePositionsOne1Test() {
        CustList<HandTarot> wonCards_ = new CustList<HandTarot>();
        wonCards_.add(HandTarot.pileBase());
        wonCards_.last().removeCardIfPresent(CardTarot.EXCUSE);
        wonCards_.last().removeCardIfPresent(CardTarot.TRUMP_21);
        wonCards_.last().removeCardIfPresent(CardTarot.HEART_KING);
        wonCards_.last().removeCardIfPresent(CardTarot.TRUMP_1);
        wonCards_.add(new HandTarot());
        wonCards_.last().ajouter(CardTarot.EXCUSE);
        wonCards_.last().ajouter(CardTarot.TRUMP_1);
        wonCards_.add(new HandTarot());
        wonCards_.last().ajouter(CardTarot.TRUMP_21);
        wonCards_.last().ajouter(CardTarot.HEART_KING);
        Ints diffs_ = new Ints();
        diffs_.add(1);
        diffs_.add(2);
        diffs_.add(2);
        Ints pos_ = EndTarotGame.changePositionsOne(wonCards_, diffs_, true);
        assertEq(3,pos_.size());
        assertEq(1,pos_.get(0));
        assertEq(2,pos_.get(1));
        assertEq(3,pos_.get(2));
    }
    @Test
    public void changePositionsOne2Test() {
        CustList<HandTarot> wonCards_ = new CustList<HandTarot>();
        wonCards_.add(HandTarot.pileBase());
        wonCards_.last().removeCardIfPresent(CardTarot.EXCUSE);
        wonCards_.last().removeCardIfPresent(CardTarot.TRUMP_21);
        wonCards_.add(new HandTarot());
        wonCards_.last().ajouter(CardTarot.EXCUSE);
        wonCards_.add(new HandTarot());
        wonCards_.last().ajouter(CardTarot.TRUMP_21);
        Ints diffs_ = new Ints();
        diffs_.add(1);
        diffs_.add(2);
        diffs_.add(2);
        Ints pos_ = EndTarotGame.changePositionsOne(wonCards_,diffs_,true);
        assertEq(3,pos_.size());
        assertEq(1,pos_.get(0));
        assertEq(2,pos_.get(1));
        assertEq(2,pos_.get(2));
    }
    @Test
    public void changePositionsOne3Test() {
        CustList<HandTarot> wonCards_ = new CustList<HandTarot>();
        wonCards_.add(HandTarot.pileBase());
        wonCards_.last().removeCardIfPresent(CardTarot.EXCUSE);
        wonCards_.last().removeCardIfPresent(CardTarot.TRUMP_21);
        wonCards_.last().removeCardIfPresent(CardTarot.HEART_KING);
        wonCards_.last().removeCardIfPresent(CardTarot.TRUMP_1);
        wonCards_.add(new HandTarot());
        wonCards_.last().ajouter(CardTarot.EXCUSE);
        wonCards_.last().ajouter(CardTarot.TRUMP_1);
        wonCards_.add(new HandTarot());
        wonCards_.last().ajouter(CardTarot.TRUMP_21);
        wonCards_.last().ajouter(CardTarot.HEART_KING);
        Ints diffs_ = new Ints();
        diffs_.add(3);
        diffs_.add(1);
        diffs_.add(1);
        Ints pos_ = EndTarotGame.changePositionsOne(wonCards_,diffs_,false);
        assertEq(3,pos_.size());
        assertEq(3,pos_.get(0));
        assertEq(2,pos_.get(1));
        assertEq(1,pos_.get(2));
    }
    @Test
    public void changePositionsOne4Test() {
        CustList<HandTarot> wonCards_ = new CustList<HandTarot>();
        wonCards_.add(HandTarot.pileBase());
        wonCards_.last().removeCardIfPresent(CardTarot.EXCUSE);
        wonCards_.last().removeCardIfPresent(CardTarot.TRUMP_21);
        wonCards_.add(new HandTarot());
        wonCards_.last().ajouter(CardTarot.EXCUSE);
        wonCards_.add(new HandTarot());
        wonCards_.last().ajouter(CardTarot.TRUMP_21);
        Ints diffs_ = new Ints();
        diffs_.add(3);
        diffs_.add(1);
        diffs_.add(1);
        Ints pos_ = EndTarotGame.changePositionsOne(wonCards_,diffs_,false);
        assertEq(3,pos_.size());
        assertEq(3,pos_.get(0));
        assertEq(1,pos_.get(1));
        assertEq(1,pos_.get(2));
    }
    @Test
    public void changePositionsTwo1Test() {
        CustList<HandTarot> wonCards_ = new CustList<HandTarot>();
        wonCards_.add(HandTarot.pileBase());
        wonCards_.last().removeCardIfPresent(CardTarot.EXCUSE);
        wonCards_.last().removeCardIfPresent(CardTarot.TRUMP_21);
        wonCards_.add(new HandTarot());
        wonCards_.last().ajouter(CardTarot.EXCUSE);
        wonCards_.add(new HandTarot());
        wonCards_.last().ajouter(CardTarot.TRUMP_21);
        Ints diffs_ = new Ints();
        diffs_.add(1);
        diffs_.add(2);
        diffs_.add(2);
        Ints pos_ = EndTarotGame.changePositionsOne(wonCards_,diffs_,true);
        pos_ = EndTarotGame.changePositionsTwo(wonCards_,pos_,true);
        assertEq(3,pos_.size());
        assertEq(1,pos_.get(0));
        assertEq(3,pos_.get(1));
        assertEq(2,pos_.get(2));
    }
    @Test
    public void changePositionsTwo2Test() {
        CustList<HandTarot> wonCards_ = new CustList<HandTarot>();
        wonCards_.add(HandTarot.pileBase());
        wonCards_.last().removeCardIfPresent(CardTarot.EXCUSE);
        wonCards_.last().removeCardIfPresent(CardTarot.TRUMP_1);
        wonCards_.add(new HandTarot());
        wonCards_.last().ajouter(CardTarot.EXCUSE);
        wonCards_.add(new HandTarot());
        wonCards_.last().ajouter(CardTarot.TRUMP_1);
        Ints diffs_ = new Ints();
        diffs_.add(1);
        diffs_.add(2);
        diffs_.add(2);
        Ints pos_ = EndTarotGame.changePositionsOne(wonCards_,diffs_,true);
        pos_ = EndTarotGame.changePositionsTwo(wonCards_,pos_,true);
        assertEq(3,pos_.size());
        assertEq(1,pos_.get(0));
        assertEq(2,pos_.get(1));
        assertEq(3,pos_.get(2));
    }
    @Test
    public void changePositionsTwo3Test() {
        CustList<HandTarot> wonCards_ = new CustList<HandTarot>();
        wonCards_.add(HandTarot.pileBase());
        wonCards_.last().removeCardIfPresent(CardTarot.TRUMP_21);
        wonCards_.last().removeCardIfPresent(CardTarot.TRUMP_1);
        wonCards_.add(new HandTarot());
        wonCards_.last().ajouter(CardTarot.TRUMP_21);
        wonCards_.add(new HandTarot());
        wonCards_.last().ajouter(CardTarot.TRUMP_1);
        Ints diffs_ = new Ints();
        diffs_.add(1);
        diffs_.add(2);
        diffs_.add(2);
        Ints pos_ = EndTarotGame.changePositionsOne(wonCards_,diffs_,true);
        pos_ = EndTarotGame.changePositionsTwo(wonCards_,pos_,true);
        assertEq(3,pos_.size());
        assertEq(1,pos_.get(0));
        assertEq(2,pos_.get(1));
        assertEq(3,pos_.get(2));
    }
    @Test
    public void changePositionsTwo4Test() {
        CustList<HandTarot> wonCards_ = new CustList<HandTarot>();
        wonCards_.add(HandTarot.pileBase());
        wonCards_.last().removeCardIfPresent(CardTarot.EXCUSE);
        wonCards_.last().removeCardIfPresent(CardTarot.TRUMP_21);
        wonCards_.add(new HandTarot());
        wonCards_.last().ajouter(CardTarot.TRUMP_21);
        wonCards_.add(new HandTarot());
        wonCards_.last().ajouter(CardTarot.EXCUSE);
        Ints diffs_ = new Ints();
        diffs_.add(1);
        diffs_.add(2);
        diffs_.add(2);
        Ints pos_ = EndTarotGame.changePositionsOne(wonCards_,diffs_,true);
        pos_ = EndTarotGame.changePositionsTwo(wonCards_,pos_,true);
        assertEq(3,pos_.size());
        assertEq(1,pos_.get(0));
        assertEq(2,pos_.get(1));
        assertEq(3,pos_.get(2));
    }
    @Test
    public void changePositionsTwo5Test() {
        CustList<HandTarot> wonCards_ = new CustList<HandTarot>();
        wonCards_.add(HandTarot.pileBase());
        wonCards_.last().removeCardIfPresent(CardTarot.EXCUSE);
        wonCards_.last().removeCardIfPresent(CardTarot.TRUMP_1);
        wonCards_.add(new HandTarot());
        wonCards_.last().ajouter(CardTarot.TRUMP_1);
        wonCards_.add(new HandTarot());
        wonCards_.last().ajouter(CardTarot.EXCUSE);
        Ints diffs_ = new Ints();
        diffs_.add(1);
        diffs_.add(2);
        diffs_.add(2);
        Ints pos_ = EndTarotGame.changePositionsOne(wonCards_,diffs_,true);
        pos_ = EndTarotGame.changePositionsTwo(wonCards_,pos_,true);
        assertEq(3,pos_.size());
        assertEq(1,pos_.get(0));
        assertEq(3,pos_.get(1));
        assertEq(2,pos_.get(2));
    }
    @Test
    public void changePositionsTwo6Test() {
        CustList<HandTarot> wonCards_ = new CustList<HandTarot>();
        wonCards_.add(HandTarot.pileBase());
        wonCards_.last().removeCardIfPresent(CardTarot.TRUMP_21);
        wonCards_.last().removeCardIfPresent(CardTarot.TRUMP_1);
        wonCards_.add(new HandTarot());
        wonCards_.last().ajouter(CardTarot.TRUMP_1);
        wonCards_.add(new HandTarot());
        wonCards_.last().ajouter(CardTarot.TRUMP_21);
        Ints diffs_ = new Ints();
        diffs_.add(1);
        diffs_.add(2);
        diffs_.add(2);
        Ints pos_ = EndTarotGame.changePositionsOne(wonCards_,diffs_,true);
        pos_ = EndTarotGame.changePositionsTwo(wonCards_,pos_,true);
        assertEq(3,pos_.size());
        assertEq(1,pos_.get(0));
        assertEq(3,pos_.get(1));
        assertEq(2,pos_.get(2));
    }
    @Test
    public void changePositionsTwo7Test() {
        CustList<HandTarot> wonCards_ = new CustList<HandTarot>();
        wonCards_.add(HandTarot.pileBase());
        wonCards_.last().removeCardIfPresent(CardTarot.HEART_JACK);
        wonCards_.last().removeCardIfPresent(CardTarot.HEART_10);
        wonCards_.last().removeCardIfPresent(CardTarot.HEART_9);
        wonCards_.last().removeCardIfPresent(CardTarot.HEART_8);
        wonCards_.add(new HandTarot());
        wonCards_.last().ajouter(CardTarot.HEART_JACK);
        wonCards_.add(new HandTarot());
        wonCards_.last().ajouter(CardTarot.HEART_10);
        wonCards_.last().ajouter(CardTarot.HEART_9);
        wonCards_.last().ajouter(CardTarot.HEART_8);
        Ints diffs_ = new Ints();
        diffs_.add(1);
        diffs_.add(2);
        diffs_.add(2);
        Ints pos_ = EndTarotGame.changePositionsOne(wonCards_,diffs_,true);
        pos_ = EndTarotGame.changePositionsTwo(wonCards_,pos_,true);
        assertEq(3,pos_.size());
        assertEq(1,pos_.get(0));
        assertEq(2,pos_.get(1));
        assertEq(3,pos_.get(2));
    }
    @Test
    public void changePositionsTwo8Test() {
        CustList<HandTarot> wonCards_ = new CustList<HandTarot>();
        wonCards_.add(HandTarot.pileBase());
        wonCards_.last().removeCardIfPresent(CardTarot.HEART_JACK);
        wonCards_.last().removeCardIfPresent(CardTarot.HEART_10);
        wonCards_.last().removeCardIfPresent(CardTarot.HEART_9);
        wonCards_.last().removeCardIfPresent(CardTarot.HEART_8);
        wonCards_.add(new HandTarot());
        wonCards_.last().ajouter(CardTarot.HEART_10);
        wonCards_.last().ajouter(CardTarot.HEART_9);
        wonCards_.last().ajouter(CardTarot.HEART_8);
        wonCards_.add(new HandTarot());
        wonCards_.last().ajouter(CardTarot.HEART_JACK);
        Ints diffs_ = new Ints();
        diffs_.add(1);
        diffs_.add(2);
        diffs_.add(2);
        Ints pos_ = EndTarotGame.changePositionsOne(wonCards_,diffs_,true);
        pos_ = EndTarotGame.changePositionsTwo(wonCards_,pos_,true);
        assertEq(3,pos_.size());
        assertEq(1,pos_.get(0));
        assertEq(3,pos_.get(1));
        assertEq(2,pos_.get(2));
    }
    @Test
    public void changePositionsTwo9Test() {
        CustList<HandTarot> wonCards_ = new CustList<HandTarot>();
        wonCards_.add(HandTarot.pileBase());
        wonCards_.last().removeCardIfPresent(CardTarot.EXCUSE);
        wonCards_.last().removeCardIfPresent(CardTarot.TRUMP_21);
        wonCards_.add(new HandTarot());
        wonCards_.last().ajouter(CardTarot.EXCUSE);
        wonCards_.add(new HandTarot());
        wonCards_.last().ajouter(CardTarot.TRUMP_21);
        Ints diffs_ = new Ints();
        diffs_.add(3);
        diffs_.add(1);
        diffs_.add(1);
        Ints pos_ = EndTarotGame.changePositionsOne(wonCards_,diffs_,false);
        pos_ = EndTarotGame.changePositionsTwo(wonCards_,pos_,false);
        assertEq(3,pos_.size());
        assertEq(3,pos_.get(0));
        assertEq(1,pos_.get(1));
        assertEq(2,pos_.get(2));
    }
    @Test
    public void changePositionsTwo10Test() {
        CustList<HandTarot> wonCards_ = new CustList<HandTarot>();
        wonCards_.add(HandTarot.pileBase());
        wonCards_.last().removeCardIfPresent(CardTarot.EXCUSE);
        wonCards_.last().removeCardIfPresent(CardTarot.TRUMP_1);
        wonCards_.add(new HandTarot());
        wonCards_.last().ajouter(CardTarot.EXCUSE);
        wonCards_.add(new HandTarot());
        wonCards_.last().ajouter(CardTarot.TRUMP_1);
        Ints diffs_ = new Ints();
        diffs_.add(3);
        diffs_.add(1);
        diffs_.add(1);
        Ints pos_ = EndTarotGame.changePositionsOne(wonCards_,diffs_,false);
        pos_ = EndTarotGame.changePositionsTwo(wonCards_,pos_,false);
        assertEq(3,pos_.size());
        assertEq(3,pos_.get(0));
        assertEq(2,pos_.get(1));
        assertEq(1,pos_.get(2));
    }
    @Test
    public void changePositionsTwo11Test() {
        CustList<HandTarot> wonCards_ = new CustList<HandTarot>();
        wonCards_.add(HandTarot.pileBase());
        wonCards_.last().removeCardIfPresent(CardTarot.TRUMP_21);
        wonCards_.last().removeCardIfPresent(CardTarot.TRUMP_1);
        wonCards_.add(new HandTarot());
        wonCards_.last().ajouter(CardTarot.TRUMP_21);
        wonCards_.add(new HandTarot());
        wonCards_.last().ajouter(CardTarot.TRUMP_1);
        Ints diffs_ = new Ints();
        diffs_.add(3);
        diffs_.add(1);
        diffs_.add(1);
        Ints pos_ = EndTarotGame.changePositionsOne(wonCards_,diffs_,false);
        pos_ = EndTarotGame.changePositionsTwo(wonCards_,pos_,false);
        assertEq(3,pos_.size());
        assertEq(3,pos_.get(0));
        assertEq(2,pos_.get(1));
        assertEq(1,pos_.get(2));
    }
    @Test
    public void changePositionsTwo12Test() {
        CustList<HandTarot> wonCards_ = new CustList<HandTarot>();
        wonCards_.add(HandTarot.pileBase());
        wonCards_.last().removeCardIfPresent(CardTarot.EXCUSE);
        wonCards_.last().removeCardIfPresent(CardTarot.TRUMP_21);
        wonCards_.add(new HandTarot());
        wonCards_.last().ajouter(CardTarot.TRUMP_21);
        wonCards_.add(new HandTarot());
        wonCards_.last().ajouter(CardTarot.EXCUSE);
        Ints diffs_ = new Ints();
        diffs_.add(3);
        diffs_.add(1);
        diffs_.add(1);
        Ints pos_ = EndTarotGame.changePositionsOne(wonCards_,diffs_,false);
        pos_ = EndTarotGame.changePositionsTwo(wonCards_,pos_,false);
        assertEq(3,pos_.size());
        assertEq(3,pos_.get(0));
        assertEq(2,pos_.get(1));
        assertEq(1,pos_.get(2));
    }
    @Test
    public void changePositionsTwo13Test() {
        CustList<HandTarot> wonCards_ = new CustList<HandTarot>();
        wonCards_.add(HandTarot.pileBase());
        wonCards_.last().removeCardIfPresent(CardTarot.EXCUSE);
        wonCards_.last().removeCardIfPresent(CardTarot.TRUMP_1);
        wonCards_.add(new HandTarot());
        wonCards_.last().ajouter(CardTarot.TRUMP_1);
        wonCards_.add(new HandTarot());
        wonCards_.last().ajouter(CardTarot.EXCUSE);
        Ints diffs_ = new Ints();
        diffs_.add(3);
        diffs_.add(1);
        diffs_.add(1);
        Ints pos_ = EndTarotGame.changePositionsOne(wonCards_,diffs_,false);
        pos_ = EndTarotGame.changePositionsTwo(wonCards_,pos_,false);
        assertEq(3,pos_.size());
        assertEq(3,pos_.get(0));
        assertEq(1,pos_.get(1));
        assertEq(2,pos_.get(2));
    }
    @Test
    public void changePositionsTwo14Test() {
        CustList<HandTarot> wonCards_ = new CustList<HandTarot>();
        wonCards_.add(HandTarot.pileBase());
        wonCards_.last().removeCardIfPresent(CardTarot.TRUMP_21);
        wonCards_.last().removeCardIfPresent(CardTarot.TRUMP_1);
        wonCards_.add(new HandTarot());
        wonCards_.last().ajouter(CardTarot.TRUMP_1);
        wonCards_.add(new HandTarot());
        wonCards_.last().ajouter(CardTarot.TRUMP_21);
        Ints diffs_ = new Ints();
        diffs_.add(3);
        diffs_.add(1);
        diffs_.add(1);
        Ints pos_ = EndTarotGame.changePositionsOne(wonCards_,diffs_,false);
        pos_ = EndTarotGame.changePositionsTwo(wonCards_,pos_,false);
        assertEq(3,pos_.size());
        assertEq(3,pos_.get(0));
        assertEq(1,pos_.get(1));
        assertEq(2,pos_.get(2));
    }
    @Test
    public void changePositionsTwo15Test() {
        CustList<HandTarot> wonCards_ = new CustList<HandTarot>();
        wonCards_.add(HandTarot.pileBase());
        wonCards_.last().removeCardIfPresent(CardTarot.HEART_JACK);
        wonCards_.last().removeCardIfPresent(CardTarot.HEART_10);
        wonCards_.last().removeCardIfPresent(CardTarot.HEART_9);
        wonCards_.last().removeCardIfPresent(CardTarot.HEART_8);
        wonCards_.add(new HandTarot());
        wonCards_.last().ajouter(CardTarot.HEART_JACK);
        wonCards_.add(new HandTarot());
        wonCards_.last().ajouter(CardTarot.HEART_10);
        wonCards_.last().ajouter(CardTarot.HEART_9);
        wonCards_.last().ajouter(CardTarot.HEART_8);
        Ints diffs_ = new Ints();
        diffs_.add(3);
        diffs_.add(1);
        diffs_.add(1);
        Ints pos_ = EndTarotGame.changePositionsOne(wonCards_,diffs_,false);
        pos_ = EndTarotGame.changePositionsTwo(wonCards_,pos_,false);
        assertEq(3,pos_.size());
        assertEq(3,pos_.get(0));
        assertEq(2,pos_.get(1));
        assertEq(1,pos_.get(2));
    }
    @Test
    public void changePositionsTwo16Test() {
        CustList<HandTarot> wonCards_ = new CustList<HandTarot>();
        wonCards_.add(HandTarot.pileBase());
        wonCards_.last().removeCardIfPresent(CardTarot.HEART_JACK);
        wonCards_.last().removeCardIfPresent(CardTarot.HEART_10);
        wonCards_.last().removeCardIfPresent(CardTarot.HEART_9);
        wonCards_.last().removeCardIfPresent(CardTarot.HEART_8);
        wonCards_.add(new HandTarot());
        wonCards_.last().ajouter(CardTarot.HEART_10);
        wonCards_.last().ajouter(CardTarot.HEART_9);
        wonCards_.last().ajouter(CardTarot.HEART_8);
        wonCards_.add(new HandTarot());
        wonCards_.last().ajouter(CardTarot.HEART_JACK);
        Ints diffs_ = new Ints();
        diffs_.add(3);
        diffs_.add(1);
        diffs_.add(1);
        Ints pos_ = EndTarotGame.changePositionsOne(wonCards_,diffs_,false);
        pos_ = EndTarotGame.changePositionsTwo(wonCards_,pos_,false);
        assertEq(3,pos_.size());
        assertEq(3,pos_.get(0));
        assertEq(1,pos_.get(1));
        assertEq(2,pos_.get(2));
    }
    @Test
    public void changePositionsThree1Test() {
        CustList<HandTarot> wonCards_ = new CustList<HandTarot>();
        wonCards_.add(HandTarot.pileBase());
        wonCards_.last().removeCardIfPresent(CardTarot.HEART_JACK);
        wonCards_.last().removeCardIfPresent(CardTarot.HEART_10);
        wonCards_.last().removeCardIfPresent(CardTarot.HEART_9);
        wonCards_.last().removeCardIfPresent(CardTarot.HEART_KNIGHT);
        wonCards_.last().removeCardIfPresent(CardTarot.SPADE_KING);
        wonCards_.last().removeCardIfPresent(CardTarot.HEART_KING);
        wonCards_.add(new HandTarot());
        wonCards_.last().ajouter(CardTarot.SPADE_KING);
        wonCards_.last().ajouter(CardTarot.HEART_KNIGHT);
        wonCards_.add(new HandTarot());
        wonCards_.last().ajouter(CardTarot.HEART_KING);
        wonCards_.last().ajouter(CardTarot.HEART_JACK);
        wonCards_.last().ajouter(CardTarot.HEART_10);
        wonCards_.last().ajouter(CardTarot.HEART_9);
        Ints diffs_ = new Ints();
        diffs_.add(1);
        diffs_.add(2);
        diffs_.add(2);
        Ints pos_ = EndTarotGame.changePositionsOne(wonCards_,diffs_,true);
        pos_ = EndTarotGame.changePositionsTwo(wonCards_,pos_,true);
        pos_ = EndTarotGame.changePositionsThree(pos_,true,wonCards_);
        assertEq(3,pos_.size());
        assertEq(1,pos_.get(0));
        assertEq(2,pos_.get(1));
        assertEq(3,pos_.get(2));
    }
    @Test
    public void changePositionsThree2Test() {
        CustList<HandTarot> wonCards_ = new CustList<HandTarot>();
        wonCards_.add(HandTarot.pileBase());
        wonCards_.last().removeCardIfPresent(CardTarot.HEART_JACK);
        wonCards_.last().removeCardIfPresent(CardTarot.HEART_10);
        wonCards_.last().removeCardIfPresent(CardTarot.HEART_9);
        wonCards_.last().removeCardIfPresent(CardTarot.HEART_KNIGHT);
        wonCards_.last().removeCardIfPresent(CardTarot.SPADE_KING);
        wonCards_.last().removeCardIfPresent(CardTarot.HEART_KING);
        wonCards_.add(new HandTarot());
        wonCards_.last().ajouter(CardTarot.HEART_KING);
        wonCards_.last().ajouter(CardTarot.HEART_JACK);
        wonCards_.last().ajouter(CardTarot.HEART_10);
        wonCards_.last().ajouter(CardTarot.HEART_9);
        wonCards_.add(new HandTarot());
        wonCards_.last().ajouter(CardTarot.SPADE_KING);
        wonCards_.last().ajouter(CardTarot.HEART_KNIGHT);
        Ints diffs_ = new Ints();
        diffs_.add(1);
        diffs_.add(2);
        diffs_.add(2);
        Ints pos_ = EndTarotGame.changePositionsOne(wonCards_,diffs_,true);
        EndTarotGame.changePositionsTwo(wonCards_,pos_,true);
        pos_ = EndTarotGame.changePositionsThree(pos_,true,wonCards_);
        assertEq(3,pos_.size());
        assertEq(1,pos_.get(0));
        assertEq(3,pos_.get(1));
        assertEq(2,pos_.get(2));
    }
    @Test
    public void changePositionsThree3Test() {
        CustList<HandTarot> wonCards_ = new CustList<HandTarot>();
        wonCards_.add(HandTarot.pileBase());
        wonCards_.last().removeCardIfPresent(CardTarot.SPADE_KING);
        wonCards_.last().removeCardIfPresent(CardTarot.HEART_KING);
        wonCards_.add(new HandTarot());
        wonCards_.last().ajouter(CardTarot.HEART_KING);
        wonCards_.add(new HandTarot());
        wonCards_.last().ajouter(CardTarot.SPADE_KING);
        Ints diffs_ = new Ints();
        diffs_.add(1);
        diffs_.add(2);
        diffs_.add(2);
        Ints pos_ = EndTarotGame.changePositionsOne(wonCards_,diffs_,true);
        EndTarotGame.changePositionsTwo(wonCards_,pos_,true);
        pos_ = EndTarotGame.changePositionsThree(pos_,true,wonCards_);
        assertEq(3,pos_.size());
        assertEq(1,pos_.get(0));
        assertEq(2,pos_.get(1));
        assertEq(2,pos_.get(2));
    }
    @Test
    public void changePositionsThree4Test() {
        CustList<HandTarot> wonCards_ = new CustList<HandTarot>();
        wonCards_.add(HandTarot.pileBase());
        wonCards_.last().removeCardIfPresent(CardTarot.HEART_JACK);
        wonCards_.last().removeCardIfPresent(CardTarot.HEART_10);
        wonCards_.last().removeCardIfPresent(CardTarot.HEART_9);
        wonCards_.last().removeCardIfPresent(CardTarot.HEART_KNIGHT);
        wonCards_.last().removeCardIfPresent(CardTarot.SPADE_KING);
        wonCards_.last().removeCardIfPresent(CardTarot.HEART_KING);
        wonCards_.add(new HandTarot());
        wonCards_.last().ajouter(CardTarot.SPADE_KING);
        wonCards_.last().ajouter(CardTarot.HEART_KNIGHT);
        wonCards_.add(new HandTarot());
        wonCards_.last().ajouter(CardTarot.HEART_KING);
        wonCards_.last().ajouter(CardTarot.HEART_JACK);
        wonCards_.last().ajouter(CardTarot.HEART_10);
        wonCards_.last().ajouter(CardTarot.HEART_9);
        Ints diffs_ = new Ints();
        diffs_.add(3);
        diffs_.add(1);
        diffs_.add(1);
        Ints pos_ = EndTarotGame.changePositionsOne(wonCards_,diffs_,false);
        pos_ = EndTarotGame.changePositionsTwo(wonCards_,pos_,false);
        pos_ = EndTarotGame.changePositionsThree(pos_,false,wonCards_);
        assertEq(3,pos_.size());
        assertEq(3,pos_.get(0));
        assertEq(2,pos_.get(1));
        assertEq(1,pos_.get(2));
    }
    @Test
    public void changePositionsThree5Test() {
        CustList<HandTarot> wonCards_ = new CustList<HandTarot>();
        wonCards_.add(HandTarot.pileBase());
        wonCards_.last().removeCardIfPresent(CardTarot.HEART_JACK);
        wonCards_.last().removeCardIfPresent(CardTarot.HEART_10);
        wonCards_.last().removeCardIfPresent(CardTarot.HEART_9);
        wonCards_.last().removeCardIfPresent(CardTarot.HEART_KNIGHT);
        wonCards_.last().removeCardIfPresent(CardTarot.SPADE_KING);
        wonCards_.last().removeCardIfPresent(CardTarot.HEART_KING);
        wonCards_.add(new HandTarot());
        wonCards_.last().ajouter(CardTarot.HEART_KING);
        wonCards_.last().ajouter(CardTarot.HEART_JACK);
        wonCards_.last().ajouter(CardTarot.HEART_10);
        wonCards_.last().ajouter(CardTarot.HEART_9);
        wonCards_.add(new HandTarot());
        wonCards_.last().ajouter(CardTarot.SPADE_KING);
        wonCards_.last().ajouter(CardTarot.HEART_KNIGHT);
        Ints diffs_ = new Ints();
        diffs_.add(3);
        diffs_.add(1);
        diffs_.add(1);
        Ints pos_ = EndTarotGame.changePositionsOne(wonCards_,diffs_,false);
        EndTarotGame.changePositionsTwo(wonCards_,pos_,false);
        pos_ = EndTarotGame.changePositionsThree(pos_,false,wonCards_);
        assertEq(3,pos_.size());
        assertEq(3,pos_.get(0));
        assertEq(1,pos_.get(1));
        assertEq(2,pos_.get(2));
    }
    @Test
    public void changePositionsThree6Test() {
        CustList<HandTarot> wonCards_ = new CustList<HandTarot>();
        wonCards_.add(HandTarot.pileBase());
        wonCards_.last().removeCardIfPresent(CardTarot.SPADE_KING);
        wonCards_.last().removeCardIfPresent(CardTarot.HEART_KING);
        wonCards_.add(new HandTarot());
        wonCards_.last().ajouter(CardTarot.HEART_KING);
        wonCards_.add(new HandTarot());
        wonCards_.last().ajouter(CardTarot.SPADE_KING);
        Ints diffs_ = new Ints();
        diffs_.add(3);
        diffs_.add(1);
        diffs_.add(1);
        Ints pos_ = EndTarotGame.changePositionsOne(wonCards_,diffs_,false);
        EndTarotGame.changePositionsTwo(wonCards_,pos_,false);
        pos_ = EndTarotGame.changePositionsThree(pos_,false,wonCards_);
        assertEq(3,pos_.size());
        assertEq(3,pos_.get(0));
        assertEq(1,pos_.get(1));
        assertEq(1,pos_.get(2));
    }
    @Test
    public void changePositionsFour1Test() {
        CustList<HandTarot> wonCards_ = new CustList<HandTarot>();
        wonCards_.add(HandTarot.pileBase());
        wonCards_.last().removeCardIfPresent(CardTarot.SPADE_KING);
        wonCards_.last().removeCardIfPresent(CardTarot.HEART_KING);
        wonCards_.add(new HandTarot());
        wonCards_.last().ajouter(CardTarot.HEART_KING);
        wonCards_.add(new HandTarot());
        wonCards_.last().ajouter(CardTarot.SPADE_KING);
        Ints diffs_ = new Ints();
        diffs_.add(1);
        diffs_.add(2);
        diffs_.add(2);
        Ints pos_ = EndTarotGame.changePositionsOne(wonCards_,diffs_,true);
        EndTarotGame.changePositionsTwo(wonCards_,pos_,true);
        pos_ = EndTarotGame.changePositionsThree(pos_,true,wonCards_);
        Ints trs_ = new Ints();
        trs_.add(0);
        trs_.add(5);
        trs_.add(15);
        pos_ = EndTarotGame.changePositionsFour(pos_,true,trs_);
        assertEq(3,pos_.size());
        assertEq(1,pos_.get(0));
        assertEq(2,pos_.get(1));
        assertEq(3,pos_.get(2));
    }
    @Test
    public void changePositionsFour2Test() {
        CustList<HandTarot> wonCards_ = new CustList<HandTarot>();
        wonCards_.add(HandTarot.pileBase());
        wonCards_.last().removeCardIfPresent(CardTarot.SPADE_KING);
        wonCards_.last().removeCardIfPresent(CardTarot.HEART_KING);
        wonCards_.add(new HandTarot());
        wonCards_.last().ajouter(CardTarot.HEART_KING);
        wonCards_.add(new HandTarot());
        wonCards_.last().ajouter(CardTarot.SPADE_KING);
        Ints diffs_ = new Ints();
        diffs_.add(1);
        diffs_.add(2);
        diffs_.add(2);
        Ints pos_ = EndTarotGame.changePositionsOne(wonCards_,diffs_,true);
        EndTarotGame.changePositionsTwo(wonCards_,pos_,true);
        pos_ = EndTarotGame.changePositionsThree(pos_,true,wonCards_);
        Ints trs_ = new Ints();
        trs_.add(0);
        trs_.add(15);
        trs_.add(5);
        pos_ = EndTarotGame.changePositionsFour(pos_,true,trs_);
        assertEq(3,pos_.size());
        assertEq(1,pos_.get(0));
        assertEq(3,pos_.get(1));
        assertEq(2,pos_.get(2));
    }
    @Test
    public void changePositionsFour3Test() {
        CustList<HandTarot> wonCards_ = new CustList<HandTarot>();
        wonCards_.add(HandTarot.pileBase());
        wonCards_.last().removeCardIfPresent(CardTarot.SPADE_KING);
        wonCards_.last().removeCardIfPresent(CardTarot.HEART_KING);
        wonCards_.add(new HandTarot());
        wonCards_.last().ajouter(CardTarot.HEART_KING);
        wonCards_.add(new HandTarot());
        wonCards_.last().ajouter(CardTarot.SPADE_KING);
        Ints diffs_ = new Ints();
        diffs_.add(3);
        diffs_.add(1);
        diffs_.add(1);
        Ints pos_ = EndTarotGame.changePositionsOne(wonCards_,diffs_,false);
        EndTarotGame.changePositionsTwo(wonCards_,pos_,false);
        pos_ = EndTarotGame.changePositionsThree(pos_,false,wonCards_);
        Ints trs_ = new Ints();
        trs_.add(0);
        trs_.add(5);
        trs_.add(15);
        pos_ = EndTarotGame.changePositionsFour(pos_,false,trs_);
        assertEq(3,pos_.size());
        assertEq(3,pos_.get(0));
        assertEq(2,pos_.get(1));
        assertEq(1,pos_.get(2));
    }
    @Test
    public void changePositionsFour4Test() {
        CustList<HandTarot> wonCards_ = new CustList<HandTarot>();
        wonCards_.add(HandTarot.pileBase());
        wonCards_.last().removeCardIfPresent(CardTarot.SPADE_KING);
        wonCards_.last().removeCardIfPresent(CardTarot.HEART_KING);
        wonCards_.add(new HandTarot());
        wonCards_.last().ajouter(CardTarot.HEART_KING);
        wonCards_.add(new HandTarot());
        wonCards_.last().ajouter(CardTarot.SPADE_KING);
        Ints diffs_ = new Ints();
        diffs_.add(3);
        diffs_.add(1);
        diffs_.add(1);
        Ints pos_ = EndTarotGame.changePositionsOne(wonCards_,diffs_,false);
        EndTarotGame.changePositionsTwo(wonCards_,pos_,false);
        pos_ = EndTarotGame.changePositionsThree(pos_,false,wonCards_);
        Ints trs_ = new Ints();
        trs_.add(0);
        trs_.add(15);
        trs_.add(5);
        pos_ = EndTarotGame.changePositionsFour(pos_,false,trs_);
        assertEq(3,pos_.size());
        assertEq(3,pos_.get(0));
        assertEq(1,pos_.get(1));
        assertEq(2,pos_.get(2));
    }
    @Test
    public void coefficients1Test() {
        Ints pos_ = new Ints();
        pos_.add(1);
        pos_.add(2);
        pos_.add(3);
        Longs rates_ = EndTarotGame.coefficients(pos_, DealingTarot.DEAL_1_VS_2);
        assertEq(3,rates_.size());
        assertEq(1,rates_.get(0));
        assertEq(0,rates_.get(1));
        assertEq(-1,rates_.get(2));
    }
    @Test
    public void coefficients2Test() {
        Ints pos_ = new Ints();
        pos_.add(1);
        pos_.add(2);
        pos_.add(2);
        Longs rates_ = EndTarotGame.coefficients(pos_,DealingTarot.DEAL_1_VS_2);
        assertEq(3,rates_.size());
        assertEq(2,rates_.get(0));
        assertEq(-1,rates_.get(1));
        assertEq(-1,rates_.get(2));
    }
    @Test
    public void coefficients3Test() {
        Ints pos_ = new Ints();
        pos_.add(1);
        pos_.add(2);
        pos_.add(3);
        pos_.add(4);
        Longs rates_ = EndTarotGame.coefficients(pos_, DealingTarot.DEAL_1_VS_3);
        assertEq(4,rates_.size());
        assertEq(2,rates_.get(0));
        assertEq(1,rates_.get(1));
        assertEq(-1,rates_.get(2));
        assertEq(-2,rates_.get(3));
    }
    @Test
    public void coefficients4Test() {
        Ints pos_ = new Ints();
        pos_.add(1);
        pos_.add(2);
        pos_.add(3);
        pos_.add(3);
        Longs rates_ = EndTarotGame.coefficients(pos_,DealingTarot.DEAL_1_VS_3);
        assertEq(4,rates_.size());
        assertEq(3,rates_.get(0));
        assertEq(1,rates_.get(1));
        assertEq(-2,rates_.get(2));
        assertEq(-2,rates_.get(3));
    }
    @Test
    public void coefficients5Test() {
        Ints pos_ = new Ints();
        pos_.add(1);
        pos_.add(2);
        pos_.add(2);
        pos_.add(2);
        Longs rates_ = EndTarotGame.coefficients(pos_,DealingTarot.DEAL_1_VS_3);
        assertEq(4,rates_.size());
        assertEq(6,rates_.get(0));
        assertEq(-2,rates_.get(1));
        assertEq(-2,rates_.get(2));
        assertEq(-2,rates_.get(3));
    }
    @Test
    public void coefficients6Test() {
        Ints pos_ = new Ints();
        pos_.add(1);
        pos_.add(2);
        pos_.add(3);
        pos_.add(4);
        pos_.add(5);
        Longs rates_ = EndTarotGame.coefficients(pos_, DealingTarot.DEAL_2_VS_3_CALL_KING);
        assertEq(5,rates_.size());
        assertEq(2,rates_.get(0));
        assertEq(1,rates_.get(1));
        assertEq(0,rates_.get(2));
        assertEq(-1,rates_.get(3));
        assertEq(-2,rates_.get(4));
    }
    @Test
    public void coefficients7Test() {
        Ints pos_ = new Ints();
        pos_.add(1);
        pos_.add(2);
        pos_.add(3);
        pos_.add(4);
        pos_.add(4);
        Longs rates_ = EndTarotGame.coefficients(pos_, DealingTarot.DEAL_2_VS_3_CALL_KING);
        assertEq(5,rates_.size());
        assertEq(3,rates_.get(0));
        assertEq(1,rates_.get(1));
        assertEq(0,rates_.get(2));
        assertEq(-2,rates_.get(3));
        assertEq(-2,rates_.get(4));
    }
    @Test
    public void coefficients8Test() {
        Ints pos_ = new Ints();
        pos_.add(1);
        pos_.add(2);
        pos_.add(3);
        pos_.add(3);
        pos_.add(3);
        Longs rates_ = EndTarotGame.coefficients(pos_, DealingTarot.DEAL_2_VS_3_CALL_KING);
        assertEq(5,rates_.size());
        assertEq(6,rates_.get(0));
        assertEq(0,rates_.get(1));
        assertEq(-2,rates_.get(2));
        assertEq(-2,rates_.get(3));
        assertEq(-2,rates_.get(4));
    }
    @Test
    public void coefficients9Test() {
        Ints pos_ = new Ints();
        pos_.add(1);
        pos_.add(2);
        pos_.add(2);
        pos_.add(2);
        pos_.add(2);
        Longs rates_ = EndTarotGame.coefficients(pos_, DealingTarot.DEAL_2_VS_3_CALL_KING);
        assertEq(5,rates_.size());
        assertEq(8,rates_.get(0));
        assertEq(-2,rates_.get(1));
        assertEq(-2,rates_.get(2));
        assertEq(-2,rates_.get(3));
        assertEq(-2,rates_.get(4));
    }
    @Test
    public void coefficients10Test() {
        Ints pos_ = new Ints();
        pos_.add(1);
        pos_.add(2);
        pos_.add(3);
        pos_.add(4);
        pos_.add(5);
        pos_.add(6);
        Longs rates_ = EndTarotGame.coefficients(pos_, DealingTarot.DEAL_2_VS_4_CALL_KING);
        assertEq(6,rates_.size());
        assertEq(3,rates_.get(0));
        assertEq(2,rates_.get(1));
        assertEq(1,rates_.get(2));
        assertEq(-1,rates_.get(3));
        assertEq(-2,rates_.get(4));
        assertEq(-3,rates_.get(5));
    }
    @Test
    public void coefficients11Test() {
        Ints pos_ = new Ints();
        pos_.add(1);
        pos_.add(2);
        pos_.add(3);
        pos_.add(4);
        pos_.add(5);
        pos_.add(5);
        Longs rates_ = EndTarotGame.coefficients(pos_, DealingTarot.DEAL_2_VS_4_CALL_KING);
        assertEq(6,rates_.size());
        assertEq(3,rates_.get(0));
        assertEq(2,rates_.get(1));
        assertEq(1,rates_.get(2));
        assertEq(0,rates_.get(3));
        assertEq(-3,rates_.get(4));
        assertEq(-3,rates_.get(5));
    }
    @Test
    public void coefficients12Test() {
        Ints pos_ = new Ints();
        pos_.add(1);
        pos_.add(2);
        pos_.add(3);
        pos_.add(4);
        pos_.add(4);
        pos_.add(4);
        Longs rates_ = EndTarotGame.coefficients(pos_, DealingTarot.DEAL_2_VS_4_CALL_KING);
        assertEq(6,rates_.size());
        assertEq(4,rates_.get(0));
        assertEq(2,rates_.get(1));
        assertEq(0,rates_.get(2));
        assertEq(-2,rates_.get(3));
        assertEq(-2,rates_.get(4));
        assertEq(-2,rates_.get(5));
    }
    @Test
    public void coefficients13Test() {
        Ints pos_ = new Ints();
        pos_.add(1);
        pos_.add(2);
        pos_.add(3);
        pos_.add(3);
        pos_.add(3);
        pos_.add(3);
        Longs rates_ = EndTarotGame.coefficients(pos_, DealingTarot.DEAL_2_VS_4_CALL_KING);
        assertEq(6,rates_.size());
        assertEq(8,rates_.get(0));
        assertEq(0,rates_.get(1));
        assertEq(-2,rates_.get(2));
        assertEq(-2,rates_.get(3));
        assertEq(-2,rates_.get(4));
        assertEq(-2,rates_.get(5));
    }
    @Test
    public void coefficients14Test() {
        Ints pos_ = new Ints();
        pos_.add(1);
        pos_.add(2);
        pos_.add(2);
        pos_.add(2);
        pos_.add(2);
        pos_.add(2);
        Longs rates_ = EndTarotGame.coefficients(pos_, DealingTarot.DEAL_2_VS_4_CALL_KING);
        assertEq(6,rates_.size());
        assertEq(10,rates_.get(0));
        assertEq(-2,rates_.get(1));
        assertEq(-2,rates_.get(2));
        assertEq(-2,rates_.get(3));
        assertEq(-2,rates_.get(4));
        assertEq(-2,rates_.get(5));
    }
    @Test
    public void coefficients15Test() {
        Ints pos_ = new Ints();
        pos_.add(1);
        pos_.add(3);
        pos_.add(5);
        pos_.add(1);
        pos_.add(3);
        pos_.add(5);
        Longs rates_ = EndTarotGame.coefficients(pos_, DealingTarot.DEAL_2_VS_4_WITHOUT_CALL);
        assertEq(6,rates_.size());
        assertEq(1,rates_.get(0));
        assertEq(0,rates_.get(1));
        assertEq(-1,rates_.get(2));
        assertEq(1,rates_.get(3));
        assertEq(0,rates_.get(4));
        assertEq(-1,rates_.get(5));
    }
    @Test
    public void coefficients16Test() {
        Ints pos_ = new Ints();
        pos_.add(1);
        pos_.add(3);
        pos_.add(3);
        pos_.add(1);
        pos_.add(3);
        pos_.add(3);
        Longs rates_ = EndTarotGame.coefficients(pos_, DealingTarot.DEAL_2_VS_4_WITHOUT_CALL);
        assertEq(6,rates_.size());
        assertEq(2,rates_.get(0));
        assertEq(-1,rates_.get(1));
        assertEq(-1,rates_.get(2));
        assertEq(2,rates_.get(3));
        assertEq(-1,rates_.get(4));
        assertEq(-1,rates_.get(5));
    }
    @Test
    public void coefficients17Test() {
        Ints pos_ = new Ints();
        pos_.add(1);
        pos_.add(3);
        pos_.add(1);
        pos_.add(3);
        Longs rates_ = EndTarotGame.coefficients(pos_, DealingTarot.DEAL_2_VS_2_WITHOUT_CALL);
        assertEq(4,rates_.size());
        assertEq(1,rates_.get(0));
        assertEq(-1,rates_.get(1));
        assertEq(1,rates_.get(2));
        assertEq(-1,rates_.get(3));
    }
    @Test
    public void coefficientsMisere1Test() {
        Ints pos_ = new Ints();
        pos_.add(1);
        pos_.add(2);
        pos_.add(3);
        Longs rates_ = EndTarotGame.coefficientsMisere(pos_, DealingTarot.DEAL_1_VS_2);
        assertEq(3,rates_.size());
        assertEq(1,rates_.get(0));
        assertEq(0,rates_.get(1));
        assertEq(-1,rates_.get(2));
    }
    @Test
    public void coefficientsMisere2Test() {
        Ints pos_ = new Ints();
        pos_.add(1);
        pos_.add(1);
        pos_.add(3);
        Longs rates_ = EndTarotGame.coefficientsMisere(pos_,DealingTarot.DEAL_1_VS_2);
        assertEq(3,rates_.size());
        assertEq(1,rates_.get(0));
        assertEq(1,rates_.get(1));
        assertEq(-2,rates_.get(2));
    }
    @Test
    public void coefficientsMisere3Test() {
        Ints pos_ = new Ints();
        pos_.add(1);
        pos_.add(2);
        pos_.add(3);
        pos_.add(4);
        Longs rates_ = EndTarotGame.coefficientsMisere(pos_, DealingTarot.DEAL_1_VS_3);
        assertEq(4,rates_.size());
        assertEq(2,rates_.get(0));
        assertEq(1,rates_.get(1));
        assertEq(-1,rates_.get(2));
        assertEq(-2,rates_.get(3));
    }
    @Test
    public void coefficientsMisere4Test() {
        Ints pos_ = new Ints();
        pos_.add(1);
        pos_.add(1);
        pos_.add(3);
        pos_.add(4);
        Longs rates_ = EndTarotGame.coefficientsMisere(pos_,DealingTarot.DEAL_1_VS_3);
        assertEq(4,rates_.size());
        assertEq(2,rates_.get(0));
        assertEq(2,rates_.get(1));
        assertEq(-1,rates_.get(2));
        assertEq(-3,rates_.get(3));
    }
    @Test
    public void coefficientsMisere5Test() {
        Ints pos_ = new Ints();
        pos_.add(1);
        pos_.add(1);
        pos_.add(1);
        pos_.add(4);
        Longs rates_ = EndTarotGame.coefficientsMisere(pos_,DealingTarot.DEAL_1_VS_3);
        assertEq(4,rates_.size());
        assertEq(2,rates_.get(0));
        assertEq(2,rates_.get(1));
        assertEq(2,rates_.get(2));
        assertEq(-6,rates_.get(3));
    }
    @Test
    public void coefficientsMisere6Test() {
        Ints pos_ = new Ints();
        pos_.add(1);
        pos_.add(2);
        pos_.add(3);
        pos_.add(4);
        pos_.add(5);
        Longs rates_ = EndTarotGame.coefficientsMisere(pos_, DealingTarot.DEAL_2_VS_3_CALL_KING);
        assertEq(5,rates_.size());
        assertEq(2,rates_.get(0));
        assertEq(1,rates_.get(1));
        assertEq(0,rates_.get(2));
        assertEq(-1,rates_.get(3));
        assertEq(-2,rates_.get(4));
    }
    @Test
    public void coefficientsMisere7Test() {
        Ints pos_ = new Ints();
        pos_.add(1);
        pos_.add(1);
        pos_.add(3);
        pos_.add(4);
        pos_.add(5);
        Longs rates_ = EndTarotGame.coefficientsMisere(pos_, DealingTarot.DEAL_2_VS_3_CALL_KING);
        assertEq(5,rates_.size());
        assertEq(2,rates_.get(0));
        assertEq(2,rates_.get(1));
        assertEq(0,rates_.get(2));
        assertEq(-1,rates_.get(3));
        assertEq(-3,rates_.get(4));
    }
    @Test
    public void coefficientsMisere8Test() {
        Ints pos_ = new Ints();
        pos_.add(1);
        pos_.add(1);
        pos_.add(1);
        pos_.add(4);
        pos_.add(5);
        Longs rates_ = EndTarotGame.coefficientsMisere(pos_, DealingTarot.DEAL_2_VS_3_CALL_KING);
        assertEq(5,rates_.size());
        assertEq(2,rates_.get(0));
        assertEq(2,rates_.get(1));
        assertEq(2,rates_.get(2));
        assertEq(0,rates_.get(3));
        assertEq(-6,rates_.get(4));
    }
    @Test
    public void coefficientsMisere9Test() {
        Ints pos_ = new Ints();
        pos_.add(1);
        pos_.add(1);
        pos_.add(1);
        pos_.add(1);
        pos_.add(5);
        Longs rates_ = EndTarotGame.coefficientsMisere(pos_, DealingTarot.DEAL_2_VS_3_CALL_KING);
        assertEq(5,rates_.size());
        assertEq(2,rates_.get(0));
        assertEq(2,rates_.get(1));
        assertEq(2,rates_.get(2));
        assertEq(2,rates_.get(3));
        assertEq(-8,rates_.get(4));
    }
    @Test
    public void coefficientsMisere10Test() {
        Ints pos_ = new Ints();
        pos_.add(1);
        pos_.add(2);
        pos_.add(3);
        pos_.add(4);
        pos_.add(5);
        pos_.add(6);
        Longs rates_ = EndTarotGame.coefficientsMisere(pos_, DealingTarot.DEAL_2_VS_4_CALL_KING);
        assertEq(6,rates_.size());
        assertEq(3,rates_.get(0));
        assertEq(2,rates_.get(1));
        assertEq(1,rates_.get(2));
        assertEq(-1,rates_.get(3));
        assertEq(-2,rates_.get(4));
        assertEq(-3,rates_.get(5));
    }
    @Test
    public void coefficientsMisere11Test() {
        Ints pos_ = new Ints();
        pos_.add(1);
        pos_.add(1);
        pos_.add(3);
        pos_.add(4);
        pos_.add(5);
        pos_.add(6);
        Longs rates_ = EndTarotGame.coefficientsMisere(pos_, DealingTarot.DEAL_2_VS_4_CALL_KING);
        assertEq(6,rates_.size());
        assertEq(3,rates_.get(0));
        assertEq(3,rates_.get(1));
        assertEq(0,rates_.get(2));
        assertEq(-1,rates_.get(3));
        assertEq(-2,rates_.get(4));
        assertEq(-3,rates_.get(5));
    }
    @Test
    public void coefficientsMisere12Test() {
        Ints pos_ = new Ints();
        pos_.add(1);
        pos_.add(1);
        pos_.add(1);
        pos_.add(4);
        pos_.add(5);
        pos_.add(6);
        Longs rates_ = EndTarotGame.coefficientsMisere(pos_, DealingTarot.DEAL_2_VS_4_CALL_KING);
        assertEq(6,rates_.size());
        assertEq(2,rates_.get(0));
        assertEq(2,rates_.get(1));
        assertEq(2,rates_.get(2));
        assertEq(0,rates_.get(3));
        assertEq(-2,rates_.get(4));
        assertEq(-4,rates_.get(5));
    }
    @Test
    public void coefficientsMisere13Test() {
        Ints pos_ = new Ints();
        pos_.add(1);
        pos_.add(1);
        pos_.add(1);
        pos_.add(1);
        pos_.add(5);
        pos_.add(6);
        Longs rates_ = EndTarotGame.coefficientsMisere(pos_, DealingTarot.DEAL_2_VS_4_CALL_KING);
        assertEq(6,rates_.size());
        assertEq(2,rates_.get(0));
        assertEq(2,rates_.get(1));
        assertEq(2,rates_.get(2));
        assertEq(2,rates_.get(3));
        assertEq(0,rates_.get(4));
        assertEq(-8,rates_.get(5));
    }
    @Test
    public void coefficientsMisere14Test() {
        Ints pos_ = new Ints();
        pos_.add(1);
        pos_.add(1);
        pos_.add(1);
        pos_.add(1);
        pos_.add(1);
        pos_.add(6);
        Longs rates_ = EndTarotGame.coefficientsMisere(pos_, DealingTarot.DEAL_2_VS_4_CALL_KING);
        assertEq(6,rates_.size());
        assertEq(2,rates_.get(0));
        assertEq(2,rates_.get(1));
        assertEq(2,rates_.get(2));
        assertEq(2,rates_.get(3));
        assertEq(2,rates_.get(4));
        assertEq(-10,rates_.get(5));
    }
    @Test
    public void coefficientsMisere15Test() {
        Ints pos_ = new Ints();
        pos_.add(1);
        pos_.add(3);
        pos_.add(5);
        pos_.add(1);
        pos_.add(3);
        pos_.add(5);
        Longs rates_ = EndTarotGame.coefficientsMisere(pos_, DealingTarot.DEAL_2_VS_4_WITHOUT_CALL);
        assertEq(6,rates_.size());
        assertEq(1,rates_.get(0));
        assertEq(0,rates_.get(1));
        assertEq(-1,rates_.get(2));
        assertEq(1,rates_.get(3));
        assertEq(0,rates_.get(4));
        assertEq(-1,rates_.get(5));
    }
    @Test
    public void coefficientsMisere16Test() {
        Ints pos_ = new Ints();
        pos_.add(1);
        pos_.add(1);
        pos_.add(5);
        pos_.add(1);
        pos_.add(1);
        pos_.add(5);
        Longs rates_ = EndTarotGame.coefficientsMisere(pos_, DealingTarot.DEAL_2_VS_4_WITHOUT_CALL);
        assertEq(6,rates_.size());
        assertEq(1,rates_.get(0));
        assertEq(1,rates_.get(1));
        assertEq(-2,rates_.get(2));
        assertEq(1,rates_.get(3));
        assertEq(1,rates_.get(4));
        assertEq(-2,rates_.get(5));
    }
    @Test
    public void coefficientsMisere17Test() {
        Ints pos_ = new Ints();
        pos_.add(1);
        pos_.add(3);
        pos_.add(1);
        pos_.add(3);
        Longs rates_ = EndTarotGame.coefficientsMisere(pos_, DealingTarot.DEAL_2_VS_2_WITHOUT_CALL);
        assertEq(4,rates_.size());
        assertEq(1,rates_.get(0));
        assertEq(-1,rates_.get(1));
        assertEq(1,rates_.get(2));
        assertEq(-1,rates_.get(3));
    }
    @Test
    public void calculerScoresJoueurs1Test() {
        RulesTarot r_ = new RulesTarot();
        r_.setMode(ModeTarot.ONE_FOR_ONE);
        r_.setDealing(DealingTarot.DEAL_1_VS_2);
        GameTarotTeamsRelation g_ = splitted(r_);
        Ints pos_ = new Ints();
        pos_.add(1);
        pos_.add(2);
        pos_.add(3);
        Longs rates_ = EndTarotGame.coefficients(pos_, DealingTarot.DEAL_1_VS_2);
        Longs primes_ = new Longs();
        primes_.add(200L);
        primes_.add(0L);
        primes_.add(0L);
        CustList<IdList<Handfuls>> handfuls_ = new CustList<IdList<Handfuls>>();
        IdList<Handfuls> handful_ = new IdList<Handfuls>();
        handful_.add(Handfuls.ONE);
        handfuls_.add(handful_);
        handful_ = new IdList<Handfuls>();
        handful_.add(Handfuls.TWO);
        handfuls_.add(handful_);
        handful_ = new IdList<Handfuls>();
        handfuls_.add(handful_);
        CustList<IdList<Miseres>> miseres_ = new CustList<IdList<Miseres>>();
        IdList<Miseres> misere_ = new IdList<Miseres>();
        misere_.add(Miseres.LOW_CARDS);
        misere_.add(Miseres.SUIT);
        miseres_.add(misere_);
        misere_ = new IdList<Miseres>();
        misere_.add(Miseres.TRUMP);
        misere_.add(Miseres.POINT);
        miseres_.add(misere_);
        misere_ = new IdList<Miseres>();
        misere_.add(Miseres.CHARACTER);
        miseres_.add(misere_);
        CustList<BoolVal> small_ = new CustList<BoolVal>();
        small_.add(BoolVal.TRUE);
        small_.add(BoolVal.FALSE);
        small_.add(BoolVal.FALSE);
        Longs scores_ = EndTarotGame.calculerScoresJoueurs(rates_, primes_, 100, g_, handfuls_, miseres_, small_);
        assertEq(3, scores_.size());
        assertEq(2320, scores_.get(0));
        assertEq(-740, scores_.get(1));
        assertEq(-1580, scores_.get(2));
    }
    @Test
    public void calculerScoresJoueurs2Test() {
        RulesTarot r_ = new RulesTarot();
        r_.setMode(ModeTarot.ONE_FOR_ONE);
        r_.setDealing(DealingTarot.DEAL_1_VS_2);
        GameTarotTeamsRelation g_ = splitted(r_);
        Ints pos_ = new Ints();
        pos_.add(1);
        pos_.add(2);
        pos_.add(3);
        Longs rates_ = EndTarotGame.coefficients(pos_, DealingTarot.DEAL_1_VS_2);
        Longs primes_ = new Longs();
        primes_.add(200L);
        primes_.add(0L);
        primes_.add(0L);
        CustList<IdList<Handfuls>> handfuls_ = new CustList<IdList<Handfuls>>();
        IdList<Handfuls> handful_ = new IdList<Handfuls>();
        handful_.add(Handfuls.ONE);
        handfuls_.add(handful_);
        handful_ = new IdList<Handfuls>();
        handful_.add(Handfuls.TWO);
        handfuls_.add(handful_);
        handful_ = new IdList<Handfuls>();
        handfuls_.add(handful_);
        CustList<IdList<Miseres>> miseres_ = new CustList<IdList<Miseres>>();
        IdList<Miseres> misere_ = new IdList<Miseres>();
        misere_.add(Miseres.LOW_CARDS);
        misere_.add(Miseres.SUIT);
        miseres_.add(misere_);
        misere_ = new IdList<Miseres>();
        misere_.add(Miseres.TRUMP);
        misere_.add(Miseres.POINT);
        miseres_.add(misere_);
        misere_ = new IdList<Miseres>();
        misere_.add(Miseres.CHARACTER);
        miseres_.add(misere_);
        CustList<BoolVal> small_ = new CustList<BoolVal>();
        small_.add(BoolVal.FALSE);
        small_.add(BoolVal.TRUE);
        small_.add(BoolVal.FALSE);
        Longs scores_ = EndTarotGame.calculerScoresJoueurs(rates_, primes_, 100, g_, handfuls_, miseres_, small_);
        assertEq(3, scores_.size());
        assertEq(2200, scores_.get(0));
        assertEq(-620, scores_.get(1));
        assertEq(-1580, scores_.get(2));
    }
    @Test
    public void calculerScoresJoueurs3Test() {
        Ints pos_ = new Ints();
        pos_.add(3);
        pos_.add(2);
        pos_.add(1);
        Longs rates_ = EndTarotGame.coefficientsMisere(pos_, DealingTarot.DEAL_1_VS_2);
        Longs scores_ = EndTarotGame.calculerScoresJoueurs(rates_, 3, 100);
        assertEq(3, scores_.size());
        assertEq(-300, scores_.get(0));
        assertEq(0, scores_.get(1));
        assertEq(300, scores_.get(2));
    }

    @Test
    public void addonTest() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setDealing(DealingTarot.DEAL_1_VS_2);
        rules_.setMode(ModeTarot.ONE_FOR_ONE);
        rules_.setDiscardAfterCall(false);
        int dealer_ = 2;
        IdList<BidTarot> bids_ = new IdList<BidTarot>();
        HandTarot last_ = new HandTarot();
        last_.ajouter(CardTarot.SPADE_KNIGHT);
        last_.ajouter(CardTarot.SPADE_JACK);
        last_.ajouter(CardTarot.HEART_JACK);
        last_.ajouter(CardTarot.SPADE_4);
        last_.ajouter(CardTarot.CLUB_6);
        last_.ajouter(CardTarot.DIAMOND_JACK);
        CustList<TrickTarot> trs_ = new CustList<TrickTarot>();
        TrickTarot dog_ = new TrickTarot(3);
        dog_.ajouter(CardTarot.HEART_JACK);
        dog_.ajouter(CardTarot.SPADE_4);
        dog_.ajouter(CardTarot.CLUB_6);
        dog_.ajouter(CardTarot.DIAMOND_JACK);
        dog_.ajouter(CardTarot.SPADE_KNIGHT);
        dog_.ajouter(CardTarot.SPADE_JACK);
        trs_.add(dog_);
        CustList<BoolVal> dSlam_ = new CustList<BoolVal>();
        dSlam_.add(BoolVal.FALSE);
        dSlam_.add(BoolVal.FALSE);
        dSlam_.add(BoolVal.FALSE);
        TrickTarot t_ =  newClassicTrickFirst(trs_,rules_,dealer_);
        t_.ajouter(CardTarot.TRUMP_1);
        t_.ajouter(CardTarot.CLUB_4);
        t_.ajouter(CardTarot.HEART_7);
        trs_.add(t_);
        t_ = newClassicTrick(trs_,rules_,dealer_);
        t_.ajouter(CardTarot.TRUMP_4);
        t_.ajouter(CardTarot.DIAMOND_7);
        t_.ajouter(CardTarot.HEART_1);
        trs_.add(t_);
        t_ = newClassicTrick(trs_,rules_,dealer_);
        t_.ajouter(CardTarot.TRUMP_10);
        t_.ajouter(CardTarot.DIAMOND_6);
        t_.ajouter(CardTarot.HEART_6);
        trs_.add(t_);
        t_ = newClassicTrick(trs_,rules_,dealer_);
        t_.ajouter(CardTarot.TRUMP_14);
        t_.ajouter(CardTarot.HEART_3);
        t_.ajouter(CardTarot.HEART_5);
        trs_.add(t_);
        t_ = newClassicTrick(trs_,rules_,dealer_);
        t_.ajouter(CardTarot.TRUMP_17);
        t_.ajouter(CardTarot.HEART_2);
        t_.ajouter(CardTarot.SPADE_9);
        trs_.add(t_);
        t_ = newClassicTrick(trs_,rules_,dealer_);
        t_.ajouter(CardTarot.TRUMP_21);
        t_.ajouter(CardTarot.SPADE_5);
        t_.ajouter(CardTarot.SPADE_7);
        trs_.add(t_);
        t_ = newClassicTrick(trs_,rules_,dealer_);
        t_.ajouter(CardTarot.TRUMP_11);
        t_.ajouter(CardTarot.CLUB_9);
        t_.ajouter(CardTarot.DIAMOND_10);
        trs_.add(t_);
        t_ = newClassicTrick(trs_,rules_,dealer_);
        t_.ajouter(CardTarot.TRUMP_18);
        t_.ajouter(CardTarot.CLUB_KNIGHT);
        t_.ajouter(CardTarot.CLUB_KING);
        trs_.add(t_);
        t_ = newClassicTrick(trs_,rules_,dealer_);
        t_.ajouter(CardTarot.TRUMP_3);
        t_.ajouter(CardTarot.DIAMOND_KNIGHT);
        t_.ajouter(CardTarot.CLUB_2);
        trs_.add(t_);
        t_ = newClassicTrick(trs_,rules_,dealer_);
        t_.ajouter(CardTarot.TRUMP_6);
        t_.ajouter(CardTarot.DIAMOND_1);
        t_.ajouter(CardTarot.CLUB_1);
        trs_.add(t_);
        t_ = newClassicTrick(trs_,rules_,dealer_);
        t_.ajouter(CardTarot.TRUMP_13);
        t_.ajouter(CardTarot.DIAMOND_2);
        t_.ajouter(CardTarot.HEART_QUEEN);
        trs_.add(t_);
        t_ = newClassicTrick(trs_,rules_,dealer_);
        t_.ajouter(CardTarot.TRUMP_20);
        t_.ajouter(CardTarot.HEART_4);
        t_.ajouter(CardTarot.CLUB_3);
        trs_.add(t_);
        t_ = newClassicTrick(trs_,rules_,dealer_);
        t_.ajouter(CardTarot.TRUMP_16);
        t_.ajouter(CardTarot.HEART_KNIGHT);
        t_.ajouter(CardTarot.CLUB_QUEEN);
        trs_.add(t_);
        t_ = newClassicTrick(trs_,rules_,dealer_);
        t_.ajouter(CardTarot.TRUMP_2);
        t_.ajouter(CardTarot.HEART_10);
        t_.ajouter(CardTarot.DIAMOND_8);
        trs_.add(t_);
        t_ = newClassicTrick(trs_,rules_,dealer_);
        t_.ajouter(CardTarot.TRUMP_8);
        t_.ajouter(CardTarot.SPADE_8);
        t_.ajouter(CardTarot.DIAMOND_4);
        trs_.add(t_);
        t_ = newClassicTrick(trs_,rules_,dealer_);
        t_.ajouter(CardTarot.TRUMP_12);
        t_.ajouter(CardTarot.SPADE_3);
        t_.ajouter(CardTarot.EXCUSE);
        trs_.add(t_);
        t_ = newClassicTrick(trs_,rules_,dealer_);
        t_.ajouter(CardTarot.TRUMP_5);
        t_.ajouter(CardTarot.CLUB_JACK);
        t_.ajouter(CardTarot.HEART_9);
        trs_.add(t_);
        t_ = newClassicTrick(trs_,rules_,dealer_);
        t_.ajouter(CardTarot.TRUMP_9);
        t_.ajouter(CardTarot.CLUB_8);
        t_.ajouter(CardTarot.SPADE_10);
        trs_.add(t_);
        t_ = newClassicTrick(trs_,rules_,dealer_);
        t_.ajouter(CardTarot.TRUMP_15);
        t_.ajouter(CardTarot.DIAMOND_5);
        t_.ajouter(CardTarot.CLUB_7);
        trs_.add(t_);
        t_ = newClassicTrick(trs_,rules_,dealer_);
        t_.ajouter(CardTarot.TRUMP_19);
        t_.ajouter(CardTarot.HEART_8);
        t_.ajouter(CardTarot.CLUB_5);
        trs_.add(t_);
        t_ = newClassicTrick(trs_,rules_,dealer_);
        t_.ajouter(CardTarot.TRUMP_7);
        t_.ajouter(CardTarot.SPADE_2);
        t_.ajouter(CardTarot.SPADE_6);
        trs_.add(t_);
        t_ = newClassicTrick(trs_,rules_,dealer_);
        t_.ajouter(CardTarot.HEART_KING);
        t_.ajouter(CardTarot.SPADE_1);
        t_.ajouter(CardTarot.CLUB_10);
        trs_.add(t_);
        t_ = newClassicTrick(trs_,rules_,dealer_);
        t_.ajouter(CardTarot.SPADE_KING);
        t_.ajouter(CardTarot.DIAMOND_9);
        t_.ajouter(CardTarot.DIAMOND_KING);
        trs_.add(t_);
        t_ = newClassicTrick(trs_,rules_,dealer_);
        t_.ajouter(CardTarot.SPADE_QUEEN);
        t_.ajouter(CardTarot.DIAMOND_3);
        t_.ajouter(CardTarot.DIAMOND_QUEEN);
        trs_.add(t_);
        CustList<BoolVal> small_ = new CustList<BoolVal>();
        small_.add(BoolVal.FALSE);
        small_.add(BoolVal.FALSE);
        small_.add(BoolVal.FALSE);
        EndTarotGame endTarotGame_ = newEndTarotGame(rules_, trs_, dealer_, bids_, last_, small_);
        endTarotGame_.setupPlayersWonTricks();
        assertEq(3,endTarotGame_.calculHandfulsScorePlayer(0).size());
        assertEq(3,endTarotGame_.calculMiseresScorePlayer(0).size());
        assertEq(3,endTarotGame_.calculSmallLastTurnScorePlayer(0).size());
    }

    private GameTarotTeamsRelation splitted(RulesTarot _r) {
        CustList<CustList<BoolVal>> conf_ = getConf(BidTarot.FOLD, _r, -1);
        Ints called_ = new Ints();
        return new GameTarotTeamsRelation(-1, called_,conf_, _r);
    }

    private EndTarotGame newEndTarotGame(RulesTarot _rules, CustList<TrickTarot> _trs, int _dealer, IdList<BidTarot> _bids, HandTarot _last, CustList<BoolVal> _small) {
        GameTarotContent triplet_ = new GameTarotContent(3);
        return newEndTarotGame(_rules, _trs,triplet_.getDeclaresMiseres(),triplet_.getDeclaresHandfuls(),triplet_.getHandfuls(), _dealer, _bids, new HandTarot(), _last, _small);
    }

    private static CustList<CustList<BoolVal>> getConf(BidTarot _b, RulesTarot _r, int _taker){
        return getConfi(_b, _r, _taker);
    }
    private static EndTarotGame newEndTarotGame(RulesTarot _r, CustList<TrickTarot> _trs,
                                                CustList<IdList<Miseres>> _m, CustList<IdList<Handfuls>> _dh, CustList<HandTarot> _h, int _dealer,
                                                IdList<BidTarot> _bids, HandTarot _calledCards, HandTarot _lastHand, CustList<BoolVal> _small) {
        GameTarot g_ = newEndedGameTarot(_r, _trs, _m, _dh, _h, _dealer, _bids, _calledCards, _lastHand);
        return new EndTarotGame(g_.getTeamsRelation(g_.buildConfidence()),g_.getTricks(),g_.getDeclaresHandfuls(),g_.getDeclaresMiseres(), _small);
    }
    private static GameTarot newEndedGameTarot(RulesTarot _r, CustList<TrickTarot> _trs,
                                               CustList<IdList<Miseres>> _m, CustList<IdList<Handfuls>> _dh, CustList<HandTarot> _h, int _dealer,
                                               IdList<BidTarot> _bids, HandTarot _calledCards, HandTarot _lastHand) {
        CustList<HandTarot> deal_ = new CustList<HandTarot>();
        int nbPl_ = _r.getDealing().getId().getNombreJoueurs();
//        for (int i = 0; i < nbPl_; i++) {
//            deal_.add(new HandTarot());
//        }
        DealTarot.ajouterMainVides(deal_,nbPl_);
        deal_.add(_lastHand);
        TrickTarot last_ = _trs.last();
        GameTarot g_ = new GameTarot(GameType.RANDOM,new DealTarot(deal_, _dealer),_r);
        g_.setProgressingTrick(new TrickTarot(new HandTarot(), last_.getStarter()));
        g_.setTricks(_trs);
        g_.setHandfuls(_h);
        g_.setDeclaresMiseres(_m);
        g_.setDeclaresHandfuls(_dh);
        g_.setBids(_bids);
        g_.setCalledCards(_calledCards);

        g_.loadGame();

//        byte player_ = g_.playerAfter((byte) _dealer);
//        int taker_ = getTaker(_r,_dealer,_bids);
//        BidTarot bid_ = BidTarot.FOLD;
//        for (BidTarot b: _bids) {
//            if (b.strongerThan(bid_)) {
//                bid_ = b;
//            }
//            player_ = g_.playerAfter(player_);
//        }
//        g_.setPreneur((byte) taker_);
//        g_.setContrat(bid_);
//        if (!g_.avecContrat() || !bid_.isJouerDonne()) {
//            g_.initEquipeDetermineeSansPreneur();
//        } else if (_r.getDealing().getAppel() == CallingCard.DEFINED) {
//            g_.initEquipeDeterminee();
//        } else if (_r.getDealing().getAppel() == CallingCard.WITHOUT) {
//            g_.initDefense();
//        }
//        for (TrickTarot t: g_.getTricks()) {
//            if (!t.getVuParToutJoueur()) {
//                continue;
//            }
//            g_.retrieveCalledPlayers(t);
//        }
//        byte starter_ = last_.getEntameur();
//        byte trickWinner_ = last_.getEntameur();
//        g_.setStarter(starter_);
//        g_.setTrickWinner(trickWinner_);
        return g_;
    }
}
