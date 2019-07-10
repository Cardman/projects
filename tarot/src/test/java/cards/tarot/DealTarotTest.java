package cards.tarot;
import static cards.tarot.EquallableTarotUtil.assertEq;
import static org.junit.Assert.assertTrue;

import cards.tarot.enumerations.ChoiceTarot;
import code.maths.LgInt;
import code.util.EqList;
import org.junit.Test;

import cards.consts.MixCardsChoice;
import cards.tarot.enumerations.CardTarot;
import cards.tarot.enumerations.DealingTarot;


public class DealTarotTest {

    private HandTarot initPileTarot() {
        HandTarot main_ = new HandTarot();
        main_.ajouter(CardTarot.EXCUSE);
        main_.ajouter(CardTarot.TRUMP_21);
        main_.ajouter(CardTarot.TRUMP_20);
        main_.ajouter(CardTarot.TRUMP_19);
        main_.ajouter(CardTarot.TRUMP_18);
        main_.ajouter(CardTarot.TRUMP_17);
        main_.ajouter(CardTarot.TRUMP_16);
        main_.ajouter(CardTarot.TRUMP_15);
        main_.ajouter(CardTarot.TRUMP_14);
        main_.ajouter(CardTarot.TRUMP_13);
        main_.ajouter(CardTarot.TRUMP_12);
        main_.ajouter(CardTarot.TRUMP_11);
        main_.ajouter(CardTarot.TRUMP_10);
        main_.ajouter(CardTarot.TRUMP_9);
        main_.ajouter(CardTarot.TRUMP_8);
        main_.ajouter(CardTarot.TRUMP_7);
        main_.ajouter(CardTarot.TRUMP_6);
        main_.ajouter(CardTarot.TRUMP_5);
        main_.ajouter(CardTarot.TRUMP_4);
        main_.ajouter(CardTarot.TRUMP_3);
        main_.ajouter(CardTarot.TRUMP_2);
        main_.ajouter(CardTarot.TRUMP_1);
        main_.ajouter(CardTarot.HEART_KING);
        main_.ajouter(CardTarot.HEART_QUEEN);
        main_.ajouter(CardTarot.HEART_KNIGHT);
        main_.ajouter(CardTarot.HEART_JACK);
        main_.ajouter(CardTarot.HEART_10);
        main_.ajouter(CardTarot.HEART_9);
        main_.ajouter(CardTarot.HEART_8);
        main_.ajouter(CardTarot.HEART_7);
        main_.ajouter(CardTarot.HEART_6);
        main_.ajouter(CardTarot.HEART_5);
        main_.ajouter(CardTarot.HEART_4);
        main_.ajouter(CardTarot.HEART_3);
        main_.ajouter(CardTarot.HEART_2);
        main_.ajouter(CardTarot.HEART_1);
        main_.ajouter(CardTarot.SPADE_KING);
        main_.ajouter(CardTarot.SPADE_QUEEN);
        main_.ajouter(CardTarot.SPADE_KNIGHT);
        main_.ajouter(CardTarot.SPADE_JACK);
        main_.ajouter(CardTarot.SPADE_10);
        main_.ajouter(CardTarot.SPADE_9);
        main_.ajouter(CardTarot.SPADE_8);
        main_.ajouter(CardTarot.SPADE_7);
        main_.ajouter(CardTarot.SPADE_6);
        main_.ajouter(CardTarot.SPADE_5);
        main_.ajouter(CardTarot.SPADE_4);
        main_.ajouter(CardTarot.SPADE_3);
        main_.ajouter(CardTarot.SPADE_2);
        main_.ajouter(CardTarot.SPADE_1);
        main_.ajouter(CardTarot.DIAMOND_KING);
        main_.ajouter(CardTarot.DIAMOND_QUEEN);
        main_.ajouter(CardTarot.DIAMOND_KNIGHT);
        main_.ajouter(CardTarot.DIAMOND_JACK);
        main_.ajouter(CardTarot.DIAMOND_10);
        main_.ajouter(CardTarot.DIAMOND_9);
        main_.ajouter(CardTarot.DIAMOND_8);
        main_.ajouter(CardTarot.DIAMOND_7);
        main_.ajouter(CardTarot.DIAMOND_6);
        main_.ajouter(CardTarot.DIAMOND_5);
        main_.ajouter(CardTarot.DIAMOND_4);
        main_.ajouter(CardTarot.DIAMOND_3);
        main_.ajouter(CardTarot.DIAMOND_2);
        main_.ajouter(CardTarot.DIAMOND_1);
        main_.ajouter(CardTarot.CLUB_KING);
        main_.ajouter(CardTarot.CLUB_QUEEN);
        main_.ajouter(CardTarot.CLUB_KNIGHT);
        main_.ajouter(CardTarot.CLUB_JACK);
        main_.ajouter(CardTarot.CLUB_10);
        main_.ajouter(CardTarot.CLUB_9);
        main_.ajouter(CardTarot.CLUB_8);
        main_.ajouter(CardTarot.CLUB_7);
        main_.ajouter(CardTarot.CLUB_6);
        main_.ajouter(CardTarot.CLUB_5);
        main_.ajouter(CardTarot.CLUB_4);
        main_.ajouter(CardTarot.CLUB_3);
        main_.ajouter(CardTarot.CLUB_2);
        main_.ajouter(CardTarot.CLUB_1);
        return main_;
    }

    @Test
    public void initDonne1Test(){
        HandTarot main_ = initPileTarot();
        DealTarot donne_ = new DealTarot(0, main_);
        RulesTarot regles_ = new RulesTarot();
        regles_.setRepartition(DealingTarot.DEAL_1_VS_2);
        regles_.setCartesBattues(MixCardsChoice.NEVER);
        donne_.setDealer((byte) 1);
        donne_.initDonne(regles_);
        assertTrue(donne_.hand().contient(CardTarot.SPADE_8));
        assertTrue(donne_.hand().contient(CardTarot.SPADE_7));
        assertTrue(donne_.hand().contient(CardTarot.SPADE_6));
        assertTrue(donne_.hand().contient(CardTarot.DIAMOND_KNIGHT));
        assertTrue(donne_.hand().contient(CardTarot.DIAMOND_JACK));
        assertTrue(donne_.hand().contient(CardTarot.DIAMOND_10));
        assertTrue(donne_.hand().contient(CardTarot.DIAMOND_2));
        assertTrue(donne_.hand().contient(CardTarot.DIAMOND_1));
        assertTrue(donne_.hand().contient(CardTarot.CLUB_KING));
        assertTrue(donne_.hand().contient(CardTarot.CLUB_6));
        assertTrue(donne_.hand().contient(CardTarot.CLUB_5));
        assertTrue(donne_.hand().contient(CardTarot.CLUB_4));
        assertTrue(donne_.hand().contient(CardTarot.TRUMP_18));
        assertTrue(donne_.hand().contient(CardTarot.TRUMP_17));
        assertTrue(donne_.hand().contient(CardTarot.TRUMP_16));
        assertTrue(donne_.hand().contient(CardTarot.TRUMP_9));
        assertTrue(donne_.hand().contient(CardTarot.TRUMP_8));
        assertTrue(donne_.hand().contient(CardTarot.TRUMP_7));
        assertTrue(donne_.hand().contient(CardTarot.HEART_QUEEN));
        assertTrue(donne_.hand().contient(CardTarot.HEART_KNIGHT));
        assertTrue(donne_.hand().contient(CardTarot.HEART_JACK));
        assertTrue(donne_.hand().contient(CardTarot.HEART_3));
        assertTrue(donne_.hand().contient(CardTarot.HEART_2));
        assertTrue(donne_.hand().contient(CardTarot.HEART_1));
        assertTrue(donne_.hand((byte)1).contient(CardTarot.SPADE_5));
        assertTrue(donne_.hand((byte)1).contient(CardTarot.SPADE_4));
        assertTrue(donne_.hand((byte)1).contient(CardTarot.SPADE_3));
        assertTrue(donne_.hand((byte)1).contient(CardTarot.DIAMOND_9));
        assertTrue(donne_.hand((byte)1).contient(CardTarot.DIAMOND_8));
        assertTrue(donne_.hand((byte)1).contient(CardTarot.DIAMOND_7));
        assertTrue(donne_.hand((byte)1).contient(CardTarot.CLUB_QUEEN));
        assertTrue(donne_.hand((byte)1).contient(CardTarot.CLUB_KNIGHT));
        assertTrue(donne_.hand((byte)1).contient(CardTarot.CLUB_JACK));
        assertTrue(donne_.hand((byte)1).contient(CardTarot.CLUB_3));
        assertTrue(donne_.hand((byte)1).contient(CardTarot.CLUB_2));
        assertTrue(donne_.hand((byte)1).contient(CardTarot.CLUB_1));
        assertTrue(donne_.hand((byte)1).contient(CardTarot.TRUMP_15));
        assertTrue(donne_.hand((byte)1).contient(CardTarot.TRUMP_14));
        assertTrue(donne_.hand((byte)1).contient(CardTarot.TRUMP_13));
        assertTrue(donne_.hand((byte)1).contient(CardTarot.TRUMP_5));
        assertTrue(donne_.hand((byte)1).contient(CardTarot.TRUMP_4));
        assertTrue(donne_.hand((byte)1).contient(CardTarot.TRUMP_3));
        assertTrue(donne_.hand((byte)1).contient(CardTarot.HEART_9));
        assertTrue(donne_.hand((byte)1).contient(CardTarot.HEART_8));
        assertTrue(donne_.hand((byte)1).contient(CardTarot.HEART_7));
        assertTrue(donne_.hand((byte)1).contient(CardTarot.SPADE_KING));
        assertTrue(donne_.hand((byte)1).contient(CardTarot.SPADE_QUEEN));
        assertTrue(donne_.hand((byte)1).contient(CardTarot.SPADE_KNIGHT));
        assertTrue(donne_.hand((byte)2).contient(CardTarot.SPADE_JACK));
        assertTrue(donne_.hand((byte)2).contient(CardTarot.SPADE_10));
        assertTrue(donne_.hand((byte)2).contient(CardTarot.SPADE_9));
        assertTrue(donne_.hand((byte)2).contient(CardTarot.SPADE_1));
        assertTrue(donne_.hand((byte)2).contient(CardTarot.DIAMOND_KING));
        assertTrue(donne_.hand((byte)2).contient(CardTarot.DIAMOND_QUEEN));
        assertTrue(donne_.hand((byte)2).contient(CardTarot.DIAMOND_5));
        assertTrue(donne_.hand((byte)2).contient(CardTarot.DIAMOND_4));
        assertTrue(donne_.hand((byte)2).contient(CardTarot.DIAMOND_3));
        assertTrue(donne_.hand((byte)2).contient(CardTarot.CLUB_10));
        assertTrue(donne_.hand((byte)2).contient(CardTarot.CLUB_9));
        assertTrue(donne_.hand((byte)2).contient(CardTarot.CLUB_8));
        assertTrue(donne_.hand((byte)2).contient(CardTarot.EXCUSE));
        assertTrue(donne_.hand((byte)2).contient(CardTarot.TRUMP_21));
        assertTrue(donne_.hand((byte)2).contient(CardTarot.TRUMP_20));
        assertTrue(donne_.hand((byte)2).contient(CardTarot.TRUMP_12));
        assertTrue(donne_.hand((byte)2).contient(CardTarot.TRUMP_11));
        assertTrue(donne_.hand((byte)2).contient(CardTarot.TRUMP_10));
        assertTrue(donne_.hand((byte)2).contient(CardTarot.TRUMP_2));
        assertTrue(donne_.hand((byte)2).contient(CardTarot.TRUMP_1));
        assertTrue(donne_.hand((byte)2).contient(CardTarot.HEART_KING));
        assertTrue(donne_.hand((byte)2).contient(CardTarot.HEART_6));
        assertTrue(donne_.hand((byte)2).contient(CardTarot.HEART_5));
        assertTrue(donne_.hand((byte)2).contient(CardTarot.HEART_4));
        assertTrue(donne_.derniereMain().contient(CardTarot.SPADE_2));
        assertTrue(donne_.derniereMain().contient(CardTarot.DIAMOND_6));
        assertTrue(donne_.derniereMain().contient(CardTarot.CLUB_7));
        assertTrue(donne_.derniereMain().contient(CardTarot.TRUMP_19));
        assertTrue(donne_.derniereMain().contient(CardTarot.TRUMP_6));
        assertTrue(donne_.derniereMain().contient(CardTarot.HEART_10));
    }

    @Test
    public void initDonne2Test() {
        HandTarot main_ = initPileTarot();
        DealTarot donne_ = new DealTarot(1, main_);
        RulesTarot regles_ = new RulesTarot();
        regles_.setRepartition(DealingTarot.DEAL_1_VS_2);
        regles_.setCartesBattues(MixCardsChoice.ONCE_ONLY);
        donne_.setDealer((byte) 1);
        donne_.initDonne(regles_);
        assertEq(24, donne_.hand().total());
    }

    @Test
    public void initDonne3Test() {
        HandTarot main_ = initPileTarot();
        DealTarot donne_ = new DealTarot(0, main_);
        RulesTarot regles_ = new RulesTarot();
        regles_.setRepartition(DealingTarot.DEAL_1_VS_2);
        regles_.setCartesBattues(MixCardsChoice.ONCE_ONLY);
        donne_.setDealer((byte) 1);
        donne_.initDonne(regles_);
        assertEq(24, donne_.hand().total());
    }

    @Test
    public void initDonne4Test() {
        HandTarot main_ = initPileTarot();
        DealTarot donne_ = new DealTarot(1, main_);
        RulesTarot regles_ = new RulesTarot();
        regles_.setRepartition(DealingTarot.DEAL_1_VS_2);
        regles_.setCartesBattues(MixCardsChoice.EACH_LAUNCHING);
        donne_.setDealer((byte) 1);
        donne_.initDonne(regles_);
        assertEq(24, donne_.hand().total());
    }

    @Test
    public void initDonne5Test() {
        HandTarot main_ = initPileTarot();
        DealTarot donne_ = new DealTarot(0, main_);
        RulesTarot regles_ = new RulesTarot();
        regles_.setRepartition(DealingTarot.DEAL_1_VS_2);
        regles_.setCartesBattues(MixCardsChoice.EACH_LAUNCHING);
        donne_.setDealer((byte) 1);
        donne_.initDonne(regles_);
        assertEq(24, donne_.hand().total());
    }

    @Test
    public void initDonne6Test() {
        HandTarot main_ = initPileTarot();
        DealTarot donne_ = new DealTarot(0, main_);
        RulesTarot regles_ = new RulesTarot();
        regles_.setRepartition(DealingTarot.DEAL_1_VS_2);
        regles_.setCartesBattues(MixCardsChoice.EACH_DEAL);
        donne_.setDealer((byte) 1);
        donne_.initDonne(regles_);
        assertEq(24, donne_.hand().total());
    }
    @Test
    public void chosenTrumps1Test(){
        byte minAtout_ = 9;
        byte maxAtout_ = 12;
        int nbCards_ = 12;
                EqList<LgInt> fonctionRepartition_ = new EqList<LgInt>();
        fonctionRepartition_.add(LgInt.multiply(LgInt.among(new LgInt(
                minAtout_), new LgInt(21)), LgInt.among(new LgInt(
                nbCards_ - minAtout_ - 1), new LgInt(56))));
        byte index_ = (byte) (minAtout_ + 1);
        for (byte evenement_ = index_; evenement_ <= maxAtout_; evenement_++) {
            fonctionRepartition_.add(LgInt.plus(
                    fonctionRepartition_.last(), LgInt
                            .multiply(LgInt.among(new LgInt(evenement_),
                                    new LgInt(21)), LgInt.among(
                                    new LgInt(nbCards_ - evenement_
                                            - 1), new LgInt(56)))));
        }
        assertEq(12, DealTarot.chosenTrumps(minAtout_,maxAtout_,fonctionRepartition_,new LgInt(489217092)));
    }
    @Test
    public void chosenTrumps2Test(){
        byte minAtout_ = 9;
        byte maxAtout_ = 12;
        int nbCards_ = 12;
        EqList<LgInt> fonctionRepartition_ = new EqList<LgInt>();
        fonctionRepartition_.add(LgInt.multiply(LgInt.among(new LgInt(
                minAtout_), new LgInt(21)), LgInt.among(new LgInt(
                nbCards_ - minAtout_ - 1), new LgInt(56))));
        byte index_ = (byte) (minAtout_ + 1);
        for (byte evenement_ = index_; evenement_ <= maxAtout_; evenement_++) {
            fonctionRepartition_.add(LgInt.plus(
                    fonctionRepartition_.last(), LgInt
                            .multiply(LgInt.among(new LgInt(evenement_),
                                    new LgInt(21)), LgInt.among(
                                    new LgInt(nbCards_ - evenement_
                                            - 1), new LgInt(56)))));
        }
        assertEq(12, DealTarot.chosenTrumps(minAtout_,maxAtout_,fonctionRepartition_,new LgInt(472757012+1)));
    }
    @Test
    public void chosenTrumps3Test(){
        byte minAtout_ = 9;
        byte maxAtout_ = 12;
        int nbCards_ = 12;
        EqList<LgInt> fonctionRepartition_ = new EqList<LgInt>();
        fonctionRepartition_.add(LgInt.multiply(LgInt.among(new LgInt(
                minAtout_), new LgInt(21)), LgInt.among(new LgInt(
                nbCards_ - minAtout_ - 1), new LgInt(56))));
        byte index_ = (byte) (minAtout_ + 1);
        for (byte evenement_ = index_; evenement_ <= maxAtout_; evenement_++) {
            fonctionRepartition_.add(LgInt.plus(
                    fonctionRepartition_.last(), LgInt
                            .multiply(LgInt.among(new LgInt(evenement_),
                                    new LgInt(21)), LgInt.among(
                                    new LgInt(nbCards_ - evenement_
                                            - 1), new LgInt(56)))));
        }
        assertEq(11, DealTarot.chosenTrumps(minAtout_,maxAtout_,fonctionRepartition_,new LgInt(472757012)));
    }
    @Test
    public void chosenTrumps4Test(){
        byte minAtout_ = 9;
        byte maxAtout_ = 12;
        int nbCards_ = 12;
        EqList<LgInt> fonctionRepartition_ = new EqList<LgInt>();
        fonctionRepartition_.add(LgInt.multiply(LgInt.among(new LgInt(
                minAtout_), new LgInt(21)), LgInt.among(new LgInt(
                nbCards_ - minAtout_ - 1), new LgInt(56))));
        byte index_ = (byte) (minAtout_ + 1);
        for (byte evenement_ = index_; evenement_ <= maxAtout_; evenement_++) {
            fonctionRepartition_.add(LgInt.plus(
                    fonctionRepartition_.last(), LgInt
                            .multiply(LgInt.among(new LgInt(evenement_),
                                    new LgInt(21)), LgInt.among(
                                    new LgInt(nbCards_ - evenement_
                                            - 1), new LgInt(56)))));
        }
        assertEq(11, DealTarot.chosenTrumps(minAtout_,maxAtout_,fonctionRepartition_,new LgInt(472404296+1)));
    }
    @Test
    public void chosenTrumps5Test(){
        byte minAtout_ = 9;
        byte maxAtout_ = 12;
        int nbCards_ = 12;
        EqList<LgInt> fonctionRepartition_ = new EqList<LgInt>();
        fonctionRepartition_.add(LgInt.multiply(LgInt.among(new LgInt(
                minAtout_), new LgInt(21)), LgInt.among(new LgInt(
                nbCards_ - minAtout_ - 1), new LgInt(56))));
        byte index_ = (byte) (minAtout_ + 1);
        for (byte evenement_ = index_; evenement_ <= maxAtout_; evenement_++) {
            fonctionRepartition_.add(LgInt.plus(
                    fonctionRepartition_.last(), LgInt
                            .multiply(LgInt.among(new LgInt(evenement_),
                                    new LgInt(21)), LgInt.among(
                                    new LgInt(nbCards_ - evenement_
                                            - 1), new LgInt(56)))));
        }
        assertEq(10, DealTarot.chosenTrumps(minAtout_,maxAtout_,fonctionRepartition_,new LgInt(472404296)));
    }
    @Test
    public void chosenTrumps6Test(){
        byte minAtout_ = 9;
        byte maxAtout_ = 12;
        int nbCards_ = 12;
        EqList<LgInt> fonctionRepartition_ = new EqList<LgInt>();
        fonctionRepartition_.add(LgInt.multiply(LgInt.among(new LgInt(
                minAtout_), new LgInt(21)), LgInt.among(new LgInt(
                nbCards_ - minAtout_ - 1), new LgInt(56))));
        byte index_ = (byte) (minAtout_ + 1);
        for (byte evenement_ = index_; evenement_ <= maxAtout_; evenement_++) {
            fonctionRepartition_.add(LgInt.plus(
                    fonctionRepartition_.last(), LgInt
                            .multiply(LgInt.among(new LgInt(evenement_),
                                    new LgInt(21)), LgInt.among(
                                    new LgInt(nbCards_ - evenement_
                                            - 1), new LgInt(56)))));
        }
        assertEq(10, DealTarot.chosenTrumps(minAtout_,maxAtout_,fonctionRepartition_,new LgInt(452652200+1)));
    }
    @Test
    public void chosenTrumps7Test(){
        byte minAtout_ = 9;
        byte maxAtout_ = 12;
        int nbCards_ = 12;
        EqList<LgInt> fonctionRepartition_ = new EqList<LgInt>();
        fonctionRepartition_.add(LgInt.multiply(LgInt.among(new LgInt(
                minAtout_), new LgInt(21)), LgInt.among(new LgInt(
                nbCards_ - minAtout_ - 1), new LgInt(56))));
        byte index_ = (byte) (minAtout_ + 1);
        for (byte evenement_ = index_; evenement_ <= maxAtout_; evenement_++) {
            fonctionRepartition_.add(LgInt.plus(
                    fonctionRepartition_.last(), LgInt
                            .multiply(LgInt.among(new LgInt(evenement_),
                                    new LgInt(21)), LgInt.among(
                                    new LgInt(nbCards_ - evenement_
                                            - 1), new LgInt(56)))));
        }
        assertEq(9, DealTarot.chosenTrumps(minAtout_,maxAtout_,fonctionRepartition_,new LgInt(452652200)));
    }
    @Test
    public void chosenTrumps8Test(){
        byte minAtout_ = 9;
        byte maxAtout_ = 12;
        int nbCards_ = 12;
        EqList<LgInt> fonctionRepartition_ = new EqList<LgInt>();
        fonctionRepartition_.add(LgInt.multiply(LgInt.among(new LgInt(
                minAtout_), new LgInt(21)), LgInt.among(new LgInt(
                nbCards_ - minAtout_ - 1), new LgInt(56))));
        byte index_ = (byte) (minAtout_ + 1);
        for (byte evenement_ = index_; evenement_ <= maxAtout_; evenement_++) {
            fonctionRepartition_.add(LgInt.plus(
                    fonctionRepartition_.last(), LgInt
                            .multiply(LgInt.among(new LgInt(evenement_),
                                    new LgInt(21)), LgInt.among(
                                    new LgInt(nbCards_ - evenement_
                                            - 1), new LgInt(56)))));
        }
        assertEq(9, DealTarot.chosenTrumps(minAtout_,maxAtout_,fonctionRepartition_,new LgInt(0)));
    }
    @Test
    public void chosenTrumps9Test(){
        byte minAtout_ = 9;
        byte maxAtout_ = 12;
        int nbCards_ = 12;
        EqList<LgInt> fonctionRepartition_ = new EqList<LgInt>();
        fonctionRepartition_.add(LgInt.multiply(LgInt.among(new LgInt(
                minAtout_), new LgInt(21)), LgInt.among(new LgInt(
                nbCards_ - minAtout_ - 1), new LgInt(56))));
        byte index_ = (byte) (minAtout_ + 1);
        for (byte evenement_ = index_; evenement_ <= maxAtout_; evenement_++) {
            fonctionRepartition_.add(LgInt.plus(
                    fonctionRepartition_.last(), LgInt
                            .multiply(LgInt.among(new LgInt(evenement_),
                                    new LgInt(21)), LgInt.among(
                                    new LgInt(nbCards_ - evenement_
                                            - 1), new LgInt(56)))));
        }
        assertEq(12, DealTarot.chosenTrumps(minAtout_,maxAtout_,fonctionRepartition_,new LgInt(489217092 + 1)));
    }
    @Test
    public void initDonneSpec1Test() {
        RulesTarot regles_ = new RulesTarot();
        regles_.setDealing(DealingTarot.DEAL_1_VS_2);
        DealTarot deal_ = new DealTarot(0);
        deal_.initDonne(ChoiceTarot.HUNT_SMALL,regles_);
        assertEq(24, deal_.hand().total());
    }
    @Test
    public void initDonneSpec2Test() {
        RulesTarot regles_ = new RulesTarot();
        regles_.setDealing(DealingTarot.DEAL_1_VS_3);
        DealTarot deal_ = new DealTarot(0);
        deal_.initDonne(ChoiceTarot.HUNT_SMALL,regles_);
        assertEq(18, deal_.hand().total());
    }
    @Test
    public void initDonneSpec3Test() {
        RulesTarot regles_ = new RulesTarot();
        regles_.setDealing(DealingTarot.DEAL_1_VS_4);
        DealTarot deal_ = new DealTarot(0);
        deal_.initDonne(ChoiceTarot.HUNT_SMALL,regles_);
        assertEq(14, deal_.hand().total());
    }
    @Test
    public void initDonneSpec4Test() {
        RulesTarot regles_ = new RulesTarot();
        regles_.setDealing(DealingTarot.DEAL_2_VS_3_CALL_KING);
        DealTarot deal_ = new DealTarot(0);
        deal_.initDonne(ChoiceTarot.HUNT_SMALL,regles_);
        assertEq(15, deal_.hand().total());
    }
    @Test
    public void initDonneSpec5Test() {
        RulesTarot regles_ = new RulesTarot();
        regles_.setDealing(DealingTarot.DEAL_2_VS_4_WITHOUT_CALL);
        DealTarot deal_ = new DealTarot(0);
        deal_.initDonne(ChoiceTarot.HUNT_SMALL,regles_);
        assertEq(12, deal_.hand().total());
    }
    @Test
    public void initDonneSpec6Test() {
        RulesTarot regles_ = new RulesTarot();
        regles_.setDealing(DealingTarot.DEAL_1_VS_2);
        DealTarot deal_ = new DealTarot(0);
        deal_.initDonne(ChoiceTarot.LEAD_SMALL_BOUND,regles_);
        assertEq(24, deal_.hand().total());
    }
    @Test
    public void initDonneSpec7Test() {
        RulesTarot regles_ = new RulesTarot();
        regles_.setDealing(DealingTarot.DEAL_1_VS_3);
        DealTarot deal_ = new DealTarot(0);
        deal_.initDonne(ChoiceTarot.LEAD_SMALL_BOUND,regles_);
        assertEq(18, deal_.hand().total());
    }
    @Test
    public void initDonneSpec8Test() {
        RulesTarot regles_ = new RulesTarot();
        regles_.setDealing(DealingTarot.DEAL_1_VS_4);
        DealTarot deal_ = new DealTarot(0);
        deal_.initDonne(ChoiceTarot.LEAD_SMALL_BOUND,regles_);
        assertEq(14, deal_.hand().total());
    }
    @Test
    public void initDonneSpec9Test() {
        RulesTarot regles_ = new RulesTarot();
        regles_.setDealing(DealingTarot.DEAL_2_VS_3_CALL_KING);
        DealTarot deal_ = new DealTarot(0);
        deal_.initDonne(ChoiceTarot.LEAD_SMALL_BOUND,regles_);
        assertEq(15, deal_.hand().total());
    }
    @Test
    public void initDonneSpec10Test() {
        RulesTarot regles_ = new RulesTarot();
        regles_.setDealing(DealingTarot.DEAL_2_VS_4_WITHOUT_CALL);
        DealTarot deal_ = new DealTarot(0);
        deal_.initDonne(ChoiceTarot.LEAD_SMALL_BOUND,regles_);
        assertEq(12, deal_.hand().total());
    }
    @Test
    public void initDonneSpec11Test() {
        RulesTarot regles_ = new RulesTarot();
        regles_.setDealing(DealingTarot.DEAL_1_VS_2);
        DealTarot deal_ = new DealTarot(0);
        deal_.initDonne(ChoiceTarot.SAVE_SMALL,regles_);
        assertEq(24, deal_.hand().total());
    }
    @Test
    public void initDonneSpec12Test() {
        RulesTarot regles_ = new RulesTarot();
        regles_.setDealing(DealingTarot.DEAL_1_VS_3);
        DealTarot deal_ = new DealTarot(0);
        deal_.initDonne(ChoiceTarot.SAVE_SMALL,regles_);
        assertEq(18, deal_.hand().total());
    }
    @Test
    public void initDonneSpec13Test() {
        RulesTarot regles_ = new RulesTarot();
        regles_.setDealing(DealingTarot.DEAL_1_VS_4);
        DealTarot deal_ = new DealTarot(0);
        deal_.initDonne(ChoiceTarot.SAVE_SMALL,regles_);
        assertEq(14, deal_.hand().total());
    }
    @Test
    public void initDonneSpec14Test() {
        RulesTarot regles_ = new RulesTarot();
        regles_.setDealing(DealingTarot.DEAL_2_VS_3_CALL_KING);
        DealTarot deal_ = new DealTarot(0);
        deal_.initDonne(ChoiceTarot.SAVE_SMALL,regles_);
        assertEq(15, deal_.hand().total());
    }
    @Test
    public void initDonneSpec15Test() {
        RulesTarot regles_ = new RulesTarot();
        regles_.setDealing(DealingTarot.DEAL_2_VS_4_WITHOUT_CALL);
        DealTarot deal_ = new DealTarot(0);
        deal_.initDonne(ChoiceTarot.SAVE_SMALL,regles_);
        assertEq(12, deal_.hand().total());
    }
}
