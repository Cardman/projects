package cards.tarot;

import cards.consts.GameType;
import cards.tarot.enumerations.*;
import code.util.*;
import code.util.comparators.ComparatorBoolean;
import code.util.core.BoolVal;
import code.util.core.IndexConstants;
import org.junit.Test;

public final class EndTarotGameOtherTest extends CommonGameTarot {

    @Test
    public void setupPlayersWonTricksTest() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setDealing(DealingTarot.DEAL_1_VS_2);
        rules_.setMode(ModeTarot.ONE_FOR_ONE);
        rules_.setDiscardAfterCall(false);
        byte dealer_ = (byte) 2;
        EnumList<BidTarot> bids_ = new EnumList<BidTarot>();
        HandTarot last_ = new HandTarot();
        last_.ajouter(CardTarot.SPADE_KNIGHT);
        last_.ajouter(CardTarot.SPADE_JACK);
        last_.ajouter(CardTarot.HEART_JACK);
        last_.ajouter(CardTarot.SPADE_4);
        last_.ajouter(CardTarot.CLUB_6);
        last_.ajouter(CardTarot.DIAMOND_JACK);
        CustList<TrickTarot> trs_ = new CustList<TrickTarot>();
        TrickTarot dog_ = new TrickTarot((byte) 3,false);
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
        TrickTarot t_ =  newClassicTrick(trs_,rules_,dealer_);
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
        CustList<EnumList<Miseres>> m_ = new CustList<EnumList<Miseres>>();
        CustList<EnumList<Handfuls>> dh_ = new CustList<EnumList<Handfuls>>();
        CustList<HandTarot> h_ = new CustList<HandTarot>();
        for (int i = 0; i < 3; i++) {
            m_.add(new EnumList<Miseres>());
            h_.add(new HandTarot());
            dh_.add(new EnumList<Handfuls>());
        }
        EndTarotGame endTarotGame_ = newEndTarotGame(rules_, trs_, m_, dh_, h_, dealer_, bids_, new HandTarot(), last_, dSlam_, small_);
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
        assertEq(41,endTarotGame_.scoreNecessaireJoueur((byte) 0));
        assertEq(157,endTarotGame_.scoreJoueurPlisDouble((byte) 0));
    }
    @Test
    public void primeSupplementaire1Test() {
        RulesTarot r_ = new RulesTarot();
        r_.setMode(ModeTarot.ONE_FOR_ONE);
        r_.setDealing(DealingTarot.DEAL_1_VS_2);
        CustList<CustList<BoolVal>> conf_ = getConf(BidTarot.FOLD, r_, -1);
        Bytes called_ = new Bytes();
        GameTarotTeamsRelation g_ = new GameTarotTeamsRelation((byte)-1, called_,conf_,r_);
        CustList<HandTarot> wonCards_ = new CustList<HandTarot>();
        wonCards_.add(HandTarot.pileBase());
        wonCards_.add(new HandTarot());
        wonCards_.add(new HandTarot());
        assertEq(200, EndTarotGame.primeSupplementaire((byte)0,g_,wonCards_));
    }
    @Test
    public void primeSupplementaire2Test() {
        RulesTarot r_ = new RulesTarot();
        r_.setMode(ModeTarot.ONE_FOR_ONE);
        r_.setDealing(DealingTarot.DEAL_1_VS_2);
        CustList<CustList<BoolVal>> conf_ = getConf(BidTarot.FOLD, r_, -1);
        Bytes called_ = new Bytes();
        GameTarotTeamsRelation g_ = new GameTarotTeamsRelation((byte)-1, called_,conf_,r_);
        CustList<HandTarot> wonCards_ = new CustList<HandTarot>();
        wonCards_.add(HandTarot.pileBase());
        wonCards_.last().removeCardIfPresent(CardTarot.EXCUSE);
        wonCards_.add(new HandTarot());
        wonCards_.last().ajouter(CardTarot.EXCUSE);
        wonCards_.add(new HandTarot());
        assertEq(0, EndTarotGame.primeSupplementaire((byte)0,g_,wonCards_));
    }
    @Test
    public void differenceMax1Test() {
        assertEq(30, EndTarotGame.differenceMax((byte)50,(byte)10));
    }
    @Test
    public void differenceMax2Test() {
        assertEq(31, EndTarotGame.differenceMax((byte)51,(byte)10));
    }
    @Test
    public void positionsDifferenceTest() {
        Shorts diffs_ = new Shorts();
        diffs_.add((short)10);
        diffs_.add((short)0);
        diffs_.add((short)-10);
        diffs_.add((short)10);
        diffs_.add((short)0);
        diffs_.add((short)-10);
        Shorts pos_ = EndTarotGame.positionsDifference(diffs_);
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
        Shorts diffs_ = new Shorts();
        diffs_.add((short)1);
        diffs_.add((short)1);
        diffs_.add((short)5);
        diffs_.add((short)1);
        diffs_.add((short)1);
        diffs_.add((short)5);
        CustList<Shorts> gr_ = EndTarotGame.buildGroups(diffs_);
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
        Shorts diffs_ = new Shorts();
        diffs_.add((short)1);
        diffs_.add((short)2);
        diffs_.add((short)3);
        CustList<Shorts> gr_ = EndTarotGame.buildGroups(diffs_);
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
        Shorts diffs_ = new Shorts();
        diffs_.add((short)1);
        diffs_.add((short)2);
        diffs_.add((short)2);
        Shorts pos_ = EndTarotGame.changePositionsOne(wonCards_, diffs_, true);
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
        Shorts diffs_ = new Shorts();
        diffs_.add((short)1);
        diffs_.add((short)2);
        diffs_.add((short)2);
        Shorts pos_ = EndTarotGame.changePositionsOne(wonCards_,diffs_,true);
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
        Shorts diffs_ = new Shorts();
        diffs_.add((short)3);
        diffs_.add((short)1);
        diffs_.add((short)1);
        Shorts pos_ = EndTarotGame.changePositionsOne(wonCards_,diffs_,false);
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
        Shorts diffs_ = new Shorts();
        diffs_.add((short)3);
        diffs_.add((short)1);
        diffs_.add((short)1);
        Shorts pos_ = EndTarotGame.changePositionsOne(wonCards_,diffs_,false);
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
        Shorts diffs_ = new Shorts();
        diffs_.add((short)1);
        diffs_.add((short)2);
        diffs_.add((short)2);
        Shorts pos_ = EndTarotGame.changePositionsOne(wonCards_,diffs_,true);
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
        Shorts diffs_ = new Shorts();
        diffs_.add((short)1);
        diffs_.add((short)2);
        diffs_.add((short)2);
        Shorts pos_ = EndTarotGame.changePositionsOne(wonCards_,diffs_,true);
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
        Shorts diffs_ = new Shorts();
        diffs_.add((short)1);
        diffs_.add((short)2);
        diffs_.add((short)2);
        Shorts pos_ = EndTarotGame.changePositionsOne(wonCards_,diffs_,true);
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
        Shorts diffs_ = new Shorts();
        diffs_.add((short)1);
        diffs_.add((short)2);
        diffs_.add((short)2);
        Shorts pos_ = EndTarotGame.changePositionsOne(wonCards_,diffs_,true);
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
        Shorts diffs_ = new Shorts();
        diffs_.add((short)1);
        diffs_.add((short)2);
        diffs_.add((short)2);
        Shorts pos_ = EndTarotGame.changePositionsOne(wonCards_,diffs_,true);
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
        Shorts diffs_ = new Shorts();
        diffs_.add((short)1);
        diffs_.add((short)2);
        diffs_.add((short)2);
        Shorts pos_ = EndTarotGame.changePositionsOne(wonCards_,diffs_,true);
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
        Shorts diffs_ = new Shorts();
        diffs_.add((short)1);
        diffs_.add((short)2);
        diffs_.add((short)2);
        Shorts pos_ = EndTarotGame.changePositionsOne(wonCards_,diffs_,true);
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
        Shorts diffs_ = new Shorts();
        diffs_.add((short)1);
        diffs_.add((short)2);
        diffs_.add((short)2);
        Shorts pos_ = EndTarotGame.changePositionsOne(wonCards_,diffs_,true);
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
        Shorts diffs_ = new Shorts();
        diffs_.add((short)3);
        diffs_.add((short)1);
        diffs_.add((short)1);
        Shorts pos_ = EndTarotGame.changePositionsOne(wonCards_,diffs_,false);
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
        Shorts diffs_ = new Shorts();
        diffs_.add((short)3);
        diffs_.add((short)1);
        diffs_.add((short)1);
        Shorts pos_ = EndTarotGame.changePositionsOne(wonCards_,diffs_,false);
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
        Shorts diffs_ = new Shorts();
        diffs_.add((short)3);
        diffs_.add((short)1);
        diffs_.add((short)1);
        Shorts pos_ = EndTarotGame.changePositionsOne(wonCards_,diffs_,false);
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
        Shorts diffs_ = new Shorts();
        diffs_.add((short)3);
        diffs_.add((short)1);
        diffs_.add((short)1);
        Shorts pos_ = EndTarotGame.changePositionsOne(wonCards_,diffs_,false);
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
        Shorts diffs_ = new Shorts();
        diffs_.add((short)3);
        diffs_.add((short)1);
        diffs_.add((short)1);
        Shorts pos_ = EndTarotGame.changePositionsOne(wonCards_,diffs_,false);
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
        Shorts diffs_ = new Shorts();
        diffs_.add((short)3);
        diffs_.add((short)1);
        diffs_.add((short)1);
        Shorts pos_ = EndTarotGame.changePositionsOne(wonCards_,diffs_,false);
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
        Shorts diffs_ = new Shorts();
        diffs_.add((short)3);
        diffs_.add((short)1);
        diffs_.add((short)1);
        Shorts pos_ = EndTarotGame.changePositionsOne(wonCards_,diffs_,false);
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
        Shorts diffs_ = new Shorts();
        diffs_.add((short)3);
        diffs_.add((short)1);
        diffs_.add((short)1);
        Shorts pos_ = EndTarotGame.changePositionsOne(wonCards_,diffs_,false);
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
        Shorts diffs_ = new Shorts();
        diffs_.add((short)1);
        diffs_.add((short)2);
        diffs_.add((short)2);
        Shorts pos_ = EndTarotGame.changePositionsOne(wonCards_,diffs_,true);
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
        Shorts diffs_ = new Shorts();
        diffs_.add((short)1);
        diffs_.add((short)2);
        diffs_.add((short)2);
        Shorts pos_ = EndTarotGame.changePositionsOne(wonCards_,diffs_,true);
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
        Shorts diffs_ = new Shorts();
        diffs_.add((short)1);
        diffs_.add((short)2);
        diffs_.add((short)2);
        Shorts pos_ = EndTarotGame.changePositionsOne(wonCards_,diffs_,true);
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
        Shorts diffs_ = new Shorts();
        diffs_.add((short)3);
        diffs_.add((short)1);
        diffs_.add((short)1);
        Shorts pos_ = EndTarotGame.changePositionsOne(wonCards_,diffs_,false);
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
        Shorts diffs_ = new Shorts();
        diffs_.add((short)3);
        diffs_.add((short)1);
        diffs_.add((short)1);
        Shorts pos_ = EndTarotGame.changePositionsOne(wonCards_,diffs_,false);
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
        Shorts diffs_ = new Shorts();
        diffs_.add((short)3);
        diffs_.add((short)1);
        diffs_.add((short)1);
        Shorts pos_ = EndTarotGame.changePositionsOne(wonCards_,diffs_,false);
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
        Shorts diffs_ = new Shorts();
        diffs_.add((short)1);
        diffs_.add((short)2);
        diffs_.add((short)2);
        Shorts pos_ = EndTarotGame.changePositionsOne(wonCards_,diffs_,true);
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
        Shorts diffs_ = new Shorts();
        diffs_.add((short)1);
        diffs_.add((short)2);
        diffs_.add((short)2);
        Shorts pos_ = EndTarotGame.changePositionsOne(wonCards_,diffs_,true);
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
        Shorts diffs_ = new Shorts();
        diffs_.add((short)3);
        diffs_.add((short)1);
        diffs_.add((short)1);
        Shorts pos_ = EndTarotGame.changePositionsOne(wonCards_,diffs_,false);
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
        Shorts diffs_ = new Shorts();
        diffs_.add((short)3);
        diffs_.add((short)1);
        diffs_.add((short)1);
        Shorts pos_ = EndTarotGame.changePositionsOne(wonCards_,diffs_,false);
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
        Shorts pos_ = new Shorts();
        pos_.add((short)1);
        pos_.add((short)2);
        pos_.add((short)3);
        Shorts rates_ = EndTarotGame.coefficients(pos_, DealingTarot.DEAL_1_VS_2);
        assertEq(3,rates_.size());
        assertEq(1,rates_.get(0));
        assertEq(0,rates_.get(1));
        assertEq(-1,rates_.get(2));
    }
    @Test
    public void coefficients2Test() {
        Shorts pos_ = new Shorts();
        pos_.add((short)1);
        pos_.add((short)2);
        pos_.add((short)2);
        Shorts rates_ = EndTarotGame.coefficients(pos_,DealingTarot.DEAL_1_VS_2);
        assertEq(3,rates_.size());
        assertEq(2,rates_.get(0));
        assertEq(-1,rates_.get(1));
        assertEq(-1,rates_.get(2));
    }
    @Test
    public void coefficients3Test() {
        Shorts pos_ = new Shorts();
        pos_.add((short)1);
        pos_.add((short)2);
        pos_.add((short)3);
        pos_.add((short)4);
        Shorts rates_ = EndTarotGame.coefficients(pos_, DealingTarot.DEAL_1_VS_3);
        assertEq(4,rates_.size());
        assertEq(2,rates_.get(0));
        assertEq(1,rates_.get(1));
        assertEq(-1,rates_.get(2));
        assertEq(-2,rates_.get(3));
    }
    @Test
    public void coefficients4Test() {
        Shorts pos_ = new Shorts();
        pos_.add((short)1);
        pos_.add((short)2);
        pos_.add((short)3);
        pos_.add((short)3);
        Shorts rates_ = EndTarotGame.coefficients(pos_,DealingTarot.DEAL_1_VS_3);
        assertEq(4,rates_.size());
        assertEq(3,rates_.get(0));
        assertEq(1,rates_.get(1));
        assertEq(-2,rates_.get(2));
        assertEq(-2,rates_.get(3));
    }
    @Test
    public void coefficients5Test() {
        Shorts pos_ = new Shorts();
        pos_.add((short)1);
        pos_.add((short)2);
        pos_.add((short)2);
        pos_.add((short)2);
        Shorts rates_ = EndTarotGame.coefficients(pos_,DealingTarot.DEAL_1_VS_3);
        assertEq(4,rates_.size());
        assertEq(6,rates_.get(0));
        assertEq(-2,rates_.get(1));
        assertEq(-2,rates_.get(2));
        assertEq(-2,rates_.get(3));
    }
    @Test
    public void coefficients6Test() {
        Shorts pos_ = new Shorts();
        pos_.add((short)1);
        pos_.add((short)2);
        pos_.add((short)3);
        pos_.add((short)4);
        pos_.add((short)5);
        Shorts rates_ = EndTarotGame.coefficients(pos_, DealingTarot.DEAL_2_VS_3_CALL_KING);
        assertEq(5,rates_.size());
        assertEq(2,rates_.get(0));
        assertEq(1,rates_.get(1));
        assertEq(0,rates_.get(2));
        assertEq(-1,rates_.get(3));
        assertEq(-2,rates_.get(4));
    }
    @Test
    public void coefficients7Test() {
        Shorts pos_ = new Shorts();
        pos_.add((short)1);
        pos_.add((short)2);
        pos_.add((short)3);
        pos_.add((short)4);
        pos_.add((short)4);
        Shorts rates_ = EndTarotGame.coefficients(pos_, DealingTarot.DEAL_2_VS_3_CALL_KING);
        assertEq(5,rates_.size());
        assertEq(3,rates_.get(0));
        assertEq(1,rates_.get(1));
        assertEq(0,rates_.get(2));
        assertEq(-2,rates_.get(3));
        assertEq(-2,rates_.get(4));
    }
    @Test
    public void coefficients8Test() {
        Shorts pos_ = new Shorts();
        pos_.add((short)1);
        pos_.add((short)2);
        pos_.add((short)3);
        pos_.add((short)3);
        pos_.add((short)3);
        Shorts rates_ = EndTarotGame.coefficients(pos_, DealingTarot.DEAL_2_VS_3_CALL_KING);
        assertEq(5,rates_.size());
        assertEq(6,rates_.get(0));
        assertEq(0,rates_.get(1));
        assertEq(-2,rates_.get(2));
        assertEq(-2,rates_.get(3));
        assertEq(-2,rates_.get(4));
    }
    @Test
    public void coefficients9Test() {
        Shorts pos_ = new Shorts();
        pos_.add((short)1);
        pos_.add((short)2);
        pos_.add((short)2);
        pos_.add((short)2);
        pos_.add((short)2);
        Shorts rates_ = EndTarotGame.coefficients(pos_, DealingTarot.DEAL_2_VS_3_CALL_KING);
        assertEq(5,rates_.size());
        assertEq(8,rates_.get(0));
        assertEq(-2,rates_.get(1));
        assertEq(-2,rates_.get(2));
        assertEq(-2,rates_.get(3));
        assertEq(-2,rates_.get(4));
    }
    @Test
    public void coefficients10Test() {
        Shorts pos_ = new Shorts();
        pos_.add((short)1);
        pos_.add((short)2);
        pos_.add((short)3);
        pos_.add((short)4);
        pos_.add((short)5);
        pos_.add((short)6);
        Shorts rates_ = EndTarotGame.coefficients(pos_, DealingTarot.DEAL_2_VS_4_CALL_KING);
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
        Shorts pos_ = new Shorts();
        pos_.add((short)1);
        pos_.add((short)2);
        pos_.add((short)3);
        pos_.add((short)4);
        pos_.add((short)5);
        pos_.add((short)5);
        Shorts rates_ = EndTarotGame.coefficients(pos_, DealingTarot.DEAL_2_VS_4_CALL_KING);
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
        Shorts pos_ = new Shorts();
        pos_.add((short)1);
        pos_.add((short)2);
        pos_.add((short)3);
        pos_.add((short)4);
        pos_.add((short)4);
        pos_.add((short)4);
        Shorts rates_ = EndTarotGame.coefficients(pos_, DealingTarot.DEAL_2_VS_4_CALL_KING);
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
        Shorts pos_ = new Shorts();
        pos_.add((short)1);
        pos_.add((short)2);
        pos_.add((short)3);
        pos_.add((short)3);
        pos_.add((short)3);
        pos_.add((short)3);
        Shorts rates_ = EndTarotGame.coefficients(pos_, DealingTarot.DEAL_2_VS_4_CALL_KING);
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
        Shorts pos_ = new Shorts();
        pos_.add((short)1);
        pos_.add((short)2);
        pos_.add((short)2);
        pos_.add((short)2);
        pos_.add((short)2);
        pos_.add((short)2);
        Shorts rates_ = EndTarotGame.coefficients(pos_, DealingTarot.DEAL_2_VS_4_CALL_KING);
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
        Shorts pos_ = new Shorts();
        pos_.add((short)1);
        pos_.add((short)3);
        pos_.add((short)5);
        pos_.add((short)1);
        pos_.add((short)3);
        pos_.add((short)5);
        Shorts rates_ = EndTarotGame.coefficients(pos_, DealingTarot.DEAL_2_VS_4_WITHOUT_CALL);
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
        Shorts pos_ = new Shorts();
        pos_.add((short)1);
        pos_.add((short)3);
        pos_.add((short)3);
        pos_.add((short)1);
        pos_.add((short)3);
        pos_.add((short)3);
        Shorts rates_ = EndTarotGame.coefficients(pos_, DealingTarot.DEAL_2_VS_4_WITHOUT_CALL);
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
        Shorts pos_ = new Shorts();
        pos_.add((short)1);
        pos_.add((short)3);
        pos_.add((short)1);
        pos_.add((short)3);
        Shorts rates_ = EndTarotGame.coefficients(pos_, DealingTarot.DEAL_2_VS_2_WITHOUT_CALL);
        assertEq(4,rates_.size());
        assertEq(1,rates_.get(0));
        assertEq(-1,rates_.get(1));
        assertEq(1,rates_.get(2));
        assertEq(-1,rates_.get(3));
    }
    @Test
    public void coefficientsMisere1Test() {
        Shorts pos_ = new Shorts();
        pos_.add((short)1);
        pos_.add((short)2);
        pos_.add((short)3);
        Shorts rates_ = EndTarotGame.coefficientsMisere(pos_, DealingTarot.DEAL_1_VS_2);
        assertEq(3,rates_.size());
        assertEq(1,rates_.get(0));
        assertEq(0,rates_.get(1));
        assertEq(-1,rates_.get(2));
    }
    @Test
    public void coefficientsMisere2Test() {
        Shorts pos_ = new Shorts();
        pos_.add((short)1);
        pos_.add((short)1);
        pos_.add((short)3);
        Shorts rates_ = EndTarotGame.coefficientsMisere(pos_,DealingTarot.DEAL_1_VS_2);
        assertEq(3,rates_.size());
        assertEq(1,rates_.get(0));
        assertEq(1,rates_.get(1));
        assertEq(-2,rates_.get(2));
    }
    @Test
    public void coefficientsMisere3Test() {
        Shorts pos_ = new Shorts();
        pos_.add((short)1);
        pos_.add((short)2);
        pos_.add((short)3);
        pos_.add((short)4);
        Shorts rates_ = EndTarotGame.coefficientsMisere(pos_, DealingTarot.DEAL_1_VS_3);
        assertEq(4,rates_.size());
        assertEq(2,rates_.get(0));
        assertEq(1,rates_.get(1));
        assertEq(-1,rates_.get(2));
        assertEq(-2,rates_.get(3));
    }
    @Test
    public void coefficientsMisere4Test() {
        Shorts pos_ = new Shorts();
        pos_.add((short)1);
        pos_.add((short)1);
        pos_.add((short)3);
        pos_.add((short)4);
        Shorts rates_ = EndTarotGame.coefficientsMisere(pos_,DealingTarot.DEAL_1_VS_3);
        assertEq(4,rates_.size());
        assertEq(2,rates_.get(0));
        assertEq(2,rates_.get(1));
        assertEq(-1,rates_.get(2));
        assertEq(-3,rates_.get(3));
    }
    @Test
    public void coefficientsMisere5Test() {
        Shorts pos_ = new Shorts();
        pos_.add((short)1);
        pos_.add((short)1);
        pos_.add((short)1);
        pos_.add((short)4);
        Shorts rates_ = EndTarotGame.coefficientsMisere(pos_,DealingTarot.DEAL_1_VS_3);
        assertEq(4,rates_.size());
        assertEq(2,rates_.get(0));
        assertEq(2,rates_.get(1));
        assertEq(2,rates_.get(2));
        assertEq(-6,rates_.get(3));
    }
    @Test
    public void coefficientsMisere6Test() {
        Shorts pos_ = new Shorts();
        pos_.add((short)1);
        pos_.add((short)2);
        pos_.add((short)3);
        pos_.add((short)4);
        pos_.add((short)5);
        Shorts rates_ = EndTarotGame.coefficientsMisere(pos_, DealingTarot.DEAL_2_VS_3_CALL_KING);
        assertEq(5,rates_.size());
        assertEq(2,rates_.get(0));
        assertEq(1,rates_.get(1));
        assertEq(0,rates_.get(2));
        assertEq(-1,rates_.get(3));
        assertEq(-2,rates_.get(4));
    }
    @Test
    public void coefficientsMisere7Test() {
        Shorts pos_ = new Shorts();
        pos_.add((short)1);
        pos_.add((short)1);
        pos_.add((short)3);
        pos_.add((short)4);
        pos_.add((short)5);
        Shorts rates_ = EndTarotGame.coefficientsMisere(pos_, DealingTarot.DEAL_2_VS_3_CALL_KING);
        assertEq(5,rates_.size());
        assertEq(2,rates_.get(0));
        assertEq(2,rates_.get(1));
        assertEq(0,rates_.get(2));
        assertEq(-1,rates_.get(3));
        assertEq(-3,rates_.get(4));
    }
    @Test
    public void coefficientsMisere8Test() {
        Shorts pos_ = new Shorts();
        pos_.add((short)1);
        pos_.add((short)1);
        pos_.add((short)1);
        pos_.add((short)4);
        pos_.add((short)5);
        Shorts rates_ = EndTarotGame.coefficientsMisere(pos_, DealingTarot.DEAL_2_VS_3_CALL_KING);
        assertEq(5,rates_.size());
        assertEq(2,rates_.get(0));
        assertEq(2,rates_.get(1));
        assertEq(2,rates_.get(2));
        assertEq(0,rates_.get(3));
        assertEq(-6,rates_.get(4));
    }
    @Test
    public void coefficientsMisere9Test() {
        Shorts pos_ = new Shorts();
        pos_.add((short)1);
        pos_.add((short)1);
        pos_.add((short)1);
        pos_.add((short)1);
        pos_.add((short)5);
        Shorts rates_ = EndTarotGame.coefficientsMisere(pos_, DealingTarot.DEAL_2_VS_3_CALL_KING);
        assertEq(5,rates_.size());
        assertEq(2,rates_.get(0));
        assertEq(2,rates_.get(1));
        assertEq(2,rates_.get(2));
        assertEq(2,rates_.get(3));
        assertEq(-8,rates_.get(4));
    }
    @Test
    public void coefficientsMisere10Test() {
        Shorts pos_ = new Shorts();
        pos_.add((short)1);
        pos_.add((short)2);
        pos_.add((short)3);
        pos_.add((short)4);
        pos_.add((short)5);
        pos_.add((short)6);
        Shorts rates_ = EndTarotGame.coefficientsMisere(pos_, DealingTarot.DEAL_2_VS_4_CALL_KING);
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
        Shorts pos_ = new Shorts();
        pos_.add((short)1);
        pos_.add((short)1);
        pos_.add((short)3);
        pos_.add((short)4);
        pos_.add((short)5);
        pos_.add((short)6);
        Shorts rates_ = EndTarotGame.coefficientsMisere(pos_, DealingTarot.DEAL_2_VS_4_CALL_KING);
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
        Shorts pos_ = new Shorts();
        pos_.add((short)1);
        pos_.add((short)1);
        pos_.add((short)1);
        pos_.add((short)4);
        pos_.add((short)5);
        pos_.add((short)6);
        Shorts rates_ = EndTarotGame.coefficientsMisere(pos_, DealingTarot.DEAL_2_VS_4_CALL_KING);
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
        Shorts pos_ = new Shorts();
        pos_.add((short)1);
        pos_.add((short)1);
        pos_.add((short)1);
        pos_.add((short)1);
        pos_.add((short)5);
        pos_.add((short)6);
        Shorts rates_ = EndTarotGame.coefficientsMisere(pos_, DealingTarot.DEAL_2_VS_4_CALL_KING);
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
        Shorts pos_ = new Shorts();
        pos_.add((short)1);
        pos_.add((short)1);
        pos_.add((short)1);
        pos_.add((short)1);
        pos_.add((short)1);
        pos_.add((short)6);
        Shorts rates_ = EndTarotGame.coefficientsMisere(pos_, DealingTarot.DEAL_2_VS_4_CALL_KING);
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
        Shorts pos_ = new Shorts();
        pos_.add((short)1);
        pos_.add((short)3);
        pos_.add((short)5);
        pos_.add((short)1);
        pos_.add((short)3);
        pos_.add((short)5);
        Shorts rates_ = EndTarotGame.coefficientsMisere(pos_, DealingTarot.DEAL_2_VS_4_WITHOUT_CALL);
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
        Shorts pos_ = new Shorts();
        pos_.add((short)1);
        pos_.add((short)1);
        pos_.add((short)5);
        pos_.add((short)1);
        pos_.add((short)1);
        pos_.add((short)5);
        Shorts rates_ = EndTarotGame.coefficientsMisere(pos_, DealingTarot.DEAL_2_VS_4_WITHOUT_CALL);
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
        Shorts pos_ = new Shorts();
        pos_.add((short)1);
        pos_.add((short)3);
        pos_.add((short)1);
        pos_.add((short)3);
        Shorts rates_ = EndTarotGame.coefficientsMisere(pos_, DealingTarot.DEAL_2_VS_2_WITHOUT_CALL);
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
        CustList<CustList<BoolVal>> conf_ = getConf(BidTarot.FOLD, r_, -1);
        Bytes called_ = new Bytes();
        GameTarotTeamsRelation g_ = new GameTarotTeamsRelation((byte)-1, called_,conf_,r_);
        Shorts pos_ = new Shorts();
        pos_.add((short)1);
        pos_.add((short)2);
        pos_.add((short)3);
        Shorts rates_ = EndTarotGame.coefficients(pos_, DealingTarot.DEAL_1_VS_2);
        Shorts primes_ = new Shorts();
        primes_.add((short)200);
        primes_.add((short)0);
        primes_.add((short)0);
        CustList<EnumList<Handfuls>> handfuls_ = new CustList<EnumList<Handfuls>>();
        EnumList<Handfuls> handful_ = new EnumList<Handfuls>();
        handful_.add(Handfuls.ONE);
        handfuls_.add(handful_);
        handful_ = new EnumList<Handfuls>();
        handful_.add(Handfuls.TWO);
        handfuls_.add(handful_);
        handful_ = new EnumList<Handfuls>();
        handfuls_.add(handful_);
        CustList<EnumList<Miseres>> miseres_ = new CustList<EnumList<Miseres>>();
        EnumList<Miseres> misere_ = new EnumList<Miseres>();
        misere_.add(Miseres.LOW_CARDS);
        misere_.add(Miseres.SUIT);
        miseres_.add(misere_);
        misere_ = new EnumList<Miseres>();
        misere_.add(Miseres.TRUMP);
        misere_.add(Miseres.POINT);
        miseres_.add(misere_);
        misere_ = new EnumList<Miseres>();
        misere_.add(Miseres.CHARACTER);
        miseres_.add(misere_);
        CustList<BoolVal> small_ = new CustList<BoolVal>();
        small_.add(BoolVal.TRUE);
        small_.add(BoolVal.FALSE);
        small_.add(BoolVal.FALSE);
        Shorts scores_ = EndTarotGame.calculerScoresJoueurs(rates_, primes_, 100, g_, handfuls_, miseres_, small_);
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
        CustList<CustList<BoolVal>> conf_ = getConf(BidTarot.FOLD, r_, -1);
        Bytes called_ = new Bytes();
        GameTarotTeamsRelation g_ = new GameTarotTeamsRelation((byte)-1, called_,conf_,r_);
        Shorts pos_ = new Shorts();
        pos_.add((short)1);
        pos_.add((short)2);
        pos_.add((short)3);
        Shorts rates_ = EndTarotGame.coefficients(pos_, DealingTarot.DEAL_1_VS_2);
        Shorts primes_ = new Shorts();
        primes_.add((short)200);
        primes_.add((short)0);
        primes_.add((short)0);
        CustList<EnumList<Handfuls>> handfuls_ = new CustList<EnumList<Handfuls>>();
        EnumList<Handfuls> handful_ = new EnumList<Handfuls>();
        handful_.add(Handfuls.ONE);
        handfuls_.add(handful_);
        handful_ = new EnumList<Handfuls>();
        handful_.add(Handfuls.TWO);
        handfuls_.add(handful_);
        handful_ = new EnumList<Handfuls>();
        handfuls_.add(handful_);
        CustList<EnumList<Miseres>> miseres_ = new CustList<EnumList<Miseres>>();
        EnumList<Miseres> misere_ = new EnumList<Miseres>();
        misere_.add(Miseres.LOW_CARDS);
        misere_.add(Miseres.SUIT);
        miseres_.add(misere_);
        misere_ = new EnumList<Miseres>();
        misere_.add(Miseres.TRUMP);
        misere_.add(Miseres.POINT);
        miseres_.add(misere_);
        misere_ = new EnumList<Miseres>();
        misere_.add(Miseres.CHARACTER);
        miseres_.add(misere_);
        CustList<BoolVal> small_ = new CustList<BoolVal>();
        small_.add(BoolVal.FALSE);
        small_.add(BoolVal.TRUE);
        small_.add(BoolVal.FALSE);
        Shorts scores_ = EndTarotGame.calculerScoresJoueurs(rates_, primes_, 100, g_, handfuls_, miseres_, small_);
        assertEq(3, scores_.size());
        assertEq(2200, scores_.get(0));
        assertEq(-620, scores_.get(1));
        assertEq(-1580, scores_.get(2));
    }
    @Test
    public void calculerScoresJoueurs3Test() {
        Shorts pos_ = new Shorts();
        pos_.add((short)3);
        pos_.add((short)2);
        pos_.add((short)1);
        Shorts rates_ = EndTarotGame.coefficientsMisere(pos_, DealingTarot.DEAL_1_VS_2);
        Shorts scores_ = EndTarotGame.calculerScoresJoueurs(rates_, (byte)3, 100);
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
        byte dealer_ = (byte) 2;
        EnumList<BidTarot> bids_ = new EnumList<BidTarot>();
        HandTarot last_ = new HandTarot();
        last_.ajouter(CardTarot.SPADE_KNIGHT);
        last_.ajouter(CardTarot.SPADE_JACK);
        last_.ajouter(CardTarot.HEART_JACK);
        last_.ajouter(CardTarot.SPADE_4);
        last_.ajouter(CardTarot.CLUB_6);
        last_.ajouter(CardTarot.DIAMOND_JACK);
        CustList<TrickTarot> trs_ = new CustList<TrickTarot>();
        TrickTarot dog_ = new TrickTarot((byte) 3,false);
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
        TrickTarot t_ =  newClassicTrick(trs_,rules_,dealer_);
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
        CustList<EnumList<Miseres>> m_ = new CustList<EnumList<Miseres>>();
        CustList<EnumList<Handfuls>> dh_ = new CustList<EnumList<Handfuls>>();
        CustList<HandTarot> h_ = new CustList<HandTarot>();
        for (int i = 0; i < 3; i++) {
            m_.add(new EnumList<Miseres>());
            h_.add(new HandTarot());
            dh_.add(new EnumList<Handfuls>());
        }
        EndTarotGame endTarotGame_ = newEndTarotGame(rules_, trs_, m_, dh_, h_, dealer_, bids_, new HandTarot(), last_, dSlam_, small_);
        endTarotGame_.setupPlayersWonTricks();
        assertEq(3,endTarotGame_.calculHandfulsScorePlayer((byte) 0).size());
        assertEq(3,endTarotGame_.calculMiseresScorePlayer((byte)0).size());
        assertEq(3,endTarotGame_.calculSmallLastTurnScorePlayer((byte)0).size());
    }
    private static CustList<CustList<BoolVal>> getConf(BidTarot _b, RulesTarot _r, int _taker){
        CustList<CustList<BoolVal>> confidence_ = new CustList<CustList<BoolVal>>();
        ModeTarot mode_ = _r.getMode();
        boolean b_ = false;
        if (mode_ == ModeTarot.NORMAL) {
            b_ = true;
        } else if (mode_ == ModeTarot.NORMAL_WITH_ONE_FOR_ONE) {
            b_ = true;
        } else if (mode_ == ModeTarot.NORMAL_WITH_MISERE) {
            b_ = true;
        }
        byte nbPl_ = (byte) _r.getDealing().getId().getNombreJoueurs();
        for (int i = 0; i< nbPl_; i++) {
            CustList<BoolVal> c_ = new CustList<BoolVal>();
            for (int j = 0; j< nbPl_; j++) {
                c_.add(ComparatorBoolean.of(i == j));
            }
            confidence_.add(c_);
        }
        if (!b_ || !_b.isJouerDonne()) {
            for (byte i = IndexConstants.FIRST_INDEX; i < nbPl_; i++) {
                for (byte p: _r.getDealing().getAppelesDetermines(i)) {
                    confidence_.get(i).set(p,BoolVal.TRUE);
                }
                confidence_.get(i).set(i,BoolVal.TRUE);
            }
        } else if (_r.getDealing().getAppel() == CallingCard.DEFINED) {
            Bytes attaquants_= _r.getDealing().getAppelesDetermines((byte) _taker);
            attaquants_.add((byte) _taker);
            Bytes defenseurs_=GameTarotTeamsRelation.autresJoueurs(attaquants_, nbPl_);
            for(byte j1_:attaquants_) {
                for(byte j2_:attaquants_) {
                    if(j1_==j2_) {
                        continue;
                    }
                    confidence_.get(j1_).set(j2_,BoolVal.TRUE);
                }
            }
            for(byte j1_:defenseurs_) {
                for(byte j2_:defenseurs_) {
                    if(j1_==j2_) {
                        continue;
                    }
                    confidence_.get(j1_).set(j2_,BoolVal.TRUE);
                }
            }
        } else if (_r.getDealing().getAppel() == CallingCard.WITHOUT) {
            Bytes defenseurs_=new Bytes();
            for (byte joueur_ = IndexConstants.FIRST_INDEX; joueur_<nbPl_; joueur_++) {
                if(joueur_==_taker) {
                    continue;
                }
                defenseurs_.add(joueur_);
            }
            for(byte j1_:defenseurs_) {
                for(byte j2_:defenseurs_) {
                    if(j1_==j2_) {
                        continue;
                    }
                    confidence_.get(j1_).set(j2_,BoolVal.TRUE);
                }
            }
        }
        return confidence_;
    }
    private static EndTarotGame newEndTarotGame(RulesTarot _r, CustList<TrickTarot> _trs,
                                                CustList<EnumList<Miseres>> _m, CustList<EnumList<Handfuls>> _dh, CustList<HandTarot> _h, int _dealer,
                                                EnumList<BidTarot> _bids, HandTarot _calledCards, HandTarot _lastHand, CustList<BoolVal> _dSam, CustList<BoolVal> _small) {
        GameTarot g_ = newEndedGameTarot(_r, _trs, _m, _dh, _h, _dealer, _bids, _calledCards, _lastHand);
        return new EndTarotGame(g_.getTeamsRelation(),g_.getTricks(),g_.getDeclaresHandfuls(),g_.getDeclaresMiseres(),_dSam,_small);
    }
    private static GameTarot newEndedGameTarot(RulesTarot _r, CustList<TrickTarot> _trs,
                                               CustList<EnumList<Miseres>> _m, CustList<EnumList<Handfuls>> _dh, CustList<HandTarot> _h, int _dealer,
                                               EnumList<BidTarot> _bids, HandTarot _calledCards, HandTarot _lastHand) {
        CustList<HandTarot> deal_ = new CustList<HandTarot>();
        byte nbPl_ = (byte) _r.getDealing().getId().getNombreJoueurs();
        for (int i = 0; i < nbPl_; i++) {
            deal_.add(new HandTarot());
        }
        deal_.add(_lastHand);
        TrickTarot last_ = _trs.last();
        GameTarot g_ = new GameTarot(GameType.RANDOM,new DealTarot(deal_, (byte) _dealer),_r);
        g_.setProgressingTrick(new TrickTarot(new HandTarot(), last_.getStarter(), true));
        g_.setTricks(_trs);
        g_.setHandfuls(_h);
        g_.setDeclaresMiseres(_m);
        g_.setDeclaresHandfuls(_dh);
        g_.setBids(_bids);
        g_.setCalledCards(_calledCards);
        byte player_ = g_.playerAfter((byte) _dealer);
        int taker_ = getTaker(_r,_dealer,_bids);
        BidTarot bid_ = BidTarot.FOLD;
        for (BidTarot b: _bids) {
            if (b.strongerThan(bid_)) {
                bid_ = b;
            }
            player_ = g_.playerAfter(player_);
        }
        g_.setPreneur((byte) taker_);
        g_.setContrat(bid_);
        if (!g_.avecContrat() || !bid_.isJouerDonne()) {
            g_.initEquipeDetermineeSansPreneur();
        } else if (_r.getDealing().getAppel() == CallingCard.DEFINED) {
            g_.initEquipeDeterminee();
        } else if (_r.getDealing().getAppel() == CallingCard.WITHOUT) {
            g_.initDefense();
        }
        for (TrickTarot t: g_.getTricks()) {
            if (!t.getVuParToutJoueur()) {
                continue;
            }
            g_.retrieveCalledPlayers(t);
        }
        byte starter_ = last_.getEntameur();
        byte trickWinner_ = last_.getEntameur();
        g_.setStarter(starter_);
        g_.setTrickWinner(trickWinner_);
        return g_;
    }
}
