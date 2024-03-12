package cards.tarot;

import cards.tarot.enumerations.ChoiceTarot;
import code.maths.LgInt;
import code.maths.montecarlo.DefaultGenerator;
import code.util.CustList;
import org.junit.Test;

import cards.consts.MixCardsChoice;
import cards.tarot.enumerations.CardTarot;
import cards.tarot.enumerations.DealingTarot;


public final class DealTarotTest extends EquallableTarotUtil {

    @Test
    public void initDonne1Test(){
        HandTarot main_ = initPileTarot();
        DealTarot donne_ = deal(main_, MixCardsChoice.NEVER, 0);
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
        DealTarot donne_ = deal(main_, MixCardsChoice.ONCE_ONLY, 1);
        assertEq(24, donne_.hand().total());
    }

    @Test
    public void initDonne3Test() {
        HandTarot main_ = initPileTarot();
        DealTarot donne_ = deal(main_, MixCardsChoice.ONCE_ONLY, 0);
        assertEq(24, donne_.hand().total());
    }

    @Test
    public void initDonne4Test() {
        HandTarot main_ = initPileTarot();
        DealTarot donne_ = deal(main_, MixCardsChoice.EACH_LAUNCHING, 1);
        assertEq(24, donne_.hand().total());
    }

    @Test
    public void initDonne5Test() {
        HandTarot main_ = initPileTarot();
        DealTarot donne_ = deal(main_, MixCardsChoice.EACH_LAUNCHING, 0);
        assertEq(24, donne_.hand().total());
    }

    @Test
    public void initDonne6Test() {
        HandTarot main_ = initPileTarot();
        DealTarot donne_ = deal(main_, MixCardsChoice.EACH_DEAL, 0);
        assertEq(24, donne_.hand().total());
    }
    @Test
    public void chosenTrumps1Test(){
        byte minAtout_ = 9;
        byte maxAtout_ = 12;
        int nbCards_ = 12;
        CustList<LgInt> fonctionRepartition_ = fonctionRepartition(minAtout_, maxAtout_, nbCards_);
        assertEq(12, DealTarot.chosenTrumps(minAtout_,maxAtout_,fonctionRepartition_,new LgInt(489217092)));
    }
    @Test
    public void chosenTrumps2Test(){
        byte minAtout_ = 9;
        byte maxAtout_ = 12;
        int nbCards_ = 12;
        CustList<LgInt> fonctionRepartition_ = fonctionRepartition(minAtout_, maxAtout_, nbCards_);
        assertEq(12, DealTarot.chosenTrumps(minAtout_,maxAtout_,fonctionRepartition_,new LgInt(472757012+1)));
    }
    @Test
    public void chosenTrumps3Test(){
        byte minAtout_ = 9;
        byte maxAtout_ = 12;
        int nbCards_ = 12;
        CustList<LgInt> fonctionRepartition_ = fonctionRepartition(minAtout_, maxAtout_, nbCards_);
        assertEq(11, DealTarot.chosenTrumps(minAtout_,maxAtout_,fonctionRepartition_,new LgInt(472757012)));
    }
    @Test
    public void chosenTrumps4Test(){
        byte minAtout_ = 9;
        byte maxAtout_ = 12;
        int nbCards_ = 12;
        CustList<LgInt> fonctionRepartition_ = fonctionRepartition(minAtout_, maxAtout_, nbCards_);
        assertEq(11, DealTarot.chosenTrumps(minAtout_,maxAtout_,fonctionRepartition_,new LgInt(472404296+1)));
    }
    @Test
    public void chosenTrumps5Test(){
        byte minAtout_ = 9;
        byte maxAtout_ = 12;
        int nbCards_ = 12;
        CustList<LgInt> fonctionRepartition_ = fonctionRepartition(minAtout_, maxAtout_, nbCards_);
        assertEq(10, DealTarot.chosenTrumps(minAtout_,maxAtout_,fonctionRepartition_,new LgInt(472404296)));
    }
    @Test
    public void chosenTrumps6Test(){
        byte minAtout_ = 9;
        byte maxAtout_ = 12;
        int nbCards_ = 12;
        CustList<LgInt> fonctionRepartition_ = fonctionRepartition(minAtout_, maxAtout_, nbCards_);
        assertEq(10, DealTarot.chosenTrumps(minAtout_,maxAtout_,fonctionRepartition_,new LgInt(452652200+1)));
    }
    @Test
    public void chosenTrumps7Test(){
        byte minAtout_ = 9;
        byte maxAtout_ = 12;
        int nbCards_ = 12;
        CustList<LgInt> fonctionRepartition_ = fonctionRepartition(minAtout_, maxAtout_, nbCards_);
        assertEq(9, DealTarot.chosenTrumps(minAtout_,maxAtout_,fonctionRepartition_,new LgInt(452652200)));
    }
    @Test
    public void chosenTrumps8Test(){
        byte minAtout_ = 9;
        byte maxAtout_ = 12;
        int nbCards_ = 12;
        CustList<LgInt> fonctionRepartition_ = fonctionRepartition(minAtout_, maxAtout_, nbCards_);
        assertEq(9, DealTarot.chosenTrumps(minAtout_,maxAtout_,fonctionRepartition_,new LgInt(0)));
    }
    @Test
    public void chosenTrumps9Test(){
        byte minAtout_ = 9;
        byte maxAtout_ = 12;
        int nbCards_ = 12;
        CustList<LgInt> fonctionRepartition_ = fonctionRepartition(minAtout_, maxAtout_, nbCards_);
        assertEq(12, DealTarot.chosenTrumps(minAtout_,maxAtout_,fonctionRepartition_,new LgInt(489217092 + 1)));
    }
    @Test
    public void initDonneSpec1Test() {
        RulesTarot regles_ = new RulesTarot();
        regles_.setDealing(DealingTarot.DEAL_1_VS_2);
        DealTarot deal_ = new DealTarot(0);
        initHuntSmall(regles_, deal_);
        assertEq(24, deal_.hand().total());
    }
    @Test
    public void initDonneSpec2Test() {
        RulesTarot regles_ = new RulesTarot();
        regles_.setDealing(DealingTarot.DEAL_1_VS_3);
        DealTarot deal_ = new DealTarot(0);
        initHuntSmall(regles_, deal_);
        assertEq(18, deal_.hand().total());
    }
    @Test
    public void initDonneSpec3Test() {
        RulesTarot regles_ = new RulesTarot();
        regles_.setDealing(DealingTarot.DEAL_1_VS_4);
        DealTarot deal_ = new DealTarot(0);
        initHuntSmall(regles_, deal_);
        assertEq(14, deal_.hand().total());
    }
    @Test
    public void initDonneSpec4Test() {
        RulesTarot regles_ = new RulesTarot();
        regles_.setDealing(DealingTarot.DEAL_2_VS_3_CALL_KING);
        DealTarot deal_ = new DealTarot(0);
        initHuntSmall(regles_, deal_);
        assertEq(15, deal_.hand().total());
    }

    @Test
    public void initDonneSpec5Test() {
        RulesTarot regles_ = new RulesTarot();
        regles_.setDealing(DealingTarot.DEAL_2_VS_4_WITHOUT_CALL);
        DealTarot deal_ = new DealTarot(0);
        initHuntSmall(regles_, deal_);
        assertEq(12, deal_.hand().total());
    }
    @Test
    public void initDonneSpec6Test() {
        RulesTarot regles_ = new RulesTarot();
        regles_.setDealing(DealingTarot.DEAL_1_VS_2);
        DealTarot deal_ = new DealTarot(0);
        initLeadSmallBound(regles_, deal_);
        assertEq(24, deal_.hand().total());
    }

    @Test
    public void initDonneSpec7Test() {
        RulesTarot regles_ = new RulesTarot();
        regles_.setDealing(DealingTarot.DEAL_1_VS_3);
        DealTarot deal_ = new DealTarot(0);
        initLeadSmallBound(regles_, deal_);
        assertEq(18, deal_.hand().total());
    }
    @Test
    public void initDonneSpec8Test() {
        RulesTarot regles_ = new RulesTarot();
        regles_.setDealing(DealingTarot.DEAL_1_VS_4);
        DealTarot deal_ = new DealTarot(0);
        initLeadSmallBound(regles_, deal_);
        assertEq(14, deal_.hand().total());
    }
    @Test
    public void initDonneSpec9Test() {
        RulesTarot regles_ = new RulesTarot();
        regles_.setDealing(DealingTarot.DEAL_2_VS_3_CALL_KING);
        DealTarot deal_ = new DealTarot(0);
        initLeadSmallBound(regles_, deal_);
        assertEq(15, deal_.hand().total());
    }
    @Test
    public void initDonneSpec10Test() {
        RulesTarot regles_ = new RulesTarot();
        regles_.setDealing(DealingTarot.DEAL_2_VS_4_WITHOUT_CALL);
        DealTarot deal_ = new DealTarot(0);
        initLeadSmallBound(regles_, deal_);
        assertEq(12, deal_.hand().total());
    }
    @Test
    public void initDonneSpec11Test() {
        RulesTarot regles_ = new RulesTarot();
        regles_.setDealing(DealingTarot.DEAL_1_VS_2);
        DealTarot deal_ = new DealTarot(0);
        initSaveSmall(regles_, deal_);
        assertEq(24, deal_.hand().total());
    }

    @Test
    public void initDonneSpec12Test() {
        RulesTarot regles_ = new RulesTarot();
        regles_.setDealing(DealingTarot.DEAL_1_VS_3);
        DealTarot deal_ = new DealTarot(0);
        initSaveSmall(regles_, deal_);
        assertEq(18, deal_.hand().total());
    }
    @Test
    public void initDonneSpec13Test() {
        RulesTarot regles_ = new RulesTarot();
        regles_.setDealing(DealingTarot.DEAL_1_VS_4);
        DealTarot deal_ = new DealTarot(0);
        initSaveSmall(regles_, deal_);
        assertEq(14, deal_.hand().total());
    }
    @Test
    public void initDonneSpec14Test() {
        RulesTarot regles_ = new RulesTarot();
        regles_.setDealing(DealingTarot.DEAL_2_VS_3_CALL_KING);
        DealTarot deal_ = new DealTarot(0);
        initSaveSmall(regles_, deal_);
        assertEq(15, deal_.hand().total());
    }
    @Test
    public void initDonneSpec15Test() {
        RulesTarot regles_ = new RulesTarot();
        regles_.setDealing(DealingTarot.DEAL_2_VS_4_WITHOUT_CALL);
        DealTarot deal_ = new DealTarot(0);
        initSaveSmall(regles_, deal_);
        assertEq(12, deal_.hand().total());
    }

    private static void initHuntSmall(RulesTarot _regles, DealTarot _deal) {
        _deal.initDonne(ChoiceTarot.HUNT_SMALL,_regles, DefaultGenerator.oneElt());
    }

    private static void initLeadSmallBound(RulesTarot _regles, DealTarot _deal) {
        _deal.initDonne(ChoiceTarot.LEAD_SMALL_BOUND,_regles, DefaultGenerator.oneElt());
    }

    private static void initSaveSmall(RulesTarot _regles, DealTarot _deal) {
        _deal.initDonne(ChoiceTarot.SAVE_SMALL,_regles, DefaultGenerator.oneElt());
    }

    private static HandTarot initPileTarot() {
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

    private DealTarot deal(HandTarot _main, MixCardsChoice _m, int _nb) {
        DealTarot donne_ = new DealTarot(_nb);
        RulesTarot regles_ = new RulesTarot();
        regles_.setDealing(DealingTarot.DEAL_1_VS_2);
        regles_.getCommon().setMixedCards(_m);
        donne_.setDealer((byte) 1);
        initDonne(donne_,regles_,_main);
        return donne_;
    }

    private CustList<LgInt> fonctionRepartition(byte _minAtout, byte _maxAtout, int _nbCards) {
        return DealTarot.repartitionHunt(_nbCards, _minAtout, _maxAtout, _nbCards - _minAtout - 1L, 1L);
    }

}
