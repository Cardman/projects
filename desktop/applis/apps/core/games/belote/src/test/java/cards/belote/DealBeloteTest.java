package cards.belote;

import code.maths.montecarlo.DefaultGenerator;
import org.junit.Test;

import cards.belote.enumerations.CardBelote;
import cards.consts.MixCardsChoice;


public class DealBeloteTest extends EquallableBeloteUtil {

    @Test
    public void initDonne1Test(){
        HandBelote main_ = new HandBelote();
        main_.ajouter(CardBelote.DIAMOND_JACK);
        main_.ajouter(CardBelote.SPADE_10);
        main_.ajouter(CardBelote.SPADE_1);
        main_.ajouter(CardBelote.CLUB_8);
        main_.ajouter(CardBelote.DIAMOND_9);
        main_.ajouter(CardBelote.CLUB_KING);
        main_.ajouter(CardBelote.HEART_QUEEN);
        main_.ajouter(CardBelote.CLUB_9);
        main_.ajouter(CardBelote.HEART_8);
        main_.ajouter(CardBelote.CLUB_10);
        main_.ajouter(CardBelote.DIAMOND_10);
        main_.ajouter(CardBelote.HEART_JACK);
        main_.ajouter(CardBelote.CLUB_1);
        main_.ajouter(CardBelote.HEART_9);
        main_.ajouter(CardBelote.SPADE_KING);
        main_.ajouter(CardBelote.DIAMOND_7);
        main_.ajouter(CardBelote.DIAMOND_QUEEN);
        main_.ajouter(CardBelote.HEART_10);
        main_.ajouter(CardBelote.SPADE_9);
        main_.ajouter(CardBelote.HEART_1);
        main_.ajouter(CardBelote.HEART_KING);
        main_.ajouter(CardBelote.SPADE_JACK);
        main_.ajouter(CardBelote.SPADE_7);
        main_.ajouter(CardBelote.SPADE_QUEEN);
        main_.ajouter(CardBelote.SPADE_8);
        main_.ajouter(CardBelote.DIAMOND_1);
        main_.ajouter(CardBelote.DIAMOND_KING);
        main_.ajouter(CardBelote.CLUB_QUEEN);
        main_.ajouter(CardBelote.CLUB_JACK);
        main_.ajouter(CardBelote.DIAMOND_8);
        main_.ajouter(CardBelote.CLUB_7);
        main_.ajouter(CardBelote.HEART_7);
        DealBelote donne_ = deal(main_, 0, MixCardsChoice.NEVER);
        assertTrue(donne_.hand().contient(CardBelote.DIAMOND_QUEEN));
        assertTrue(donne_.hand().contient(CardBelote.HEART_10));
        assertTrue(donne_.hand().contient(CardBelote.SPADE_9));
        assertTrue(donne_.hand().contient(CardBelote.CLUB_JACK));
        assertTrue(donne_.hand().contient(CardBelote.DIAMOND_8));
        assertTrue(donne_.hand((byte)1).contient(CardBelote.HEART_1));
        assertTrue(donne_.hand((byte)1).contient(CardBelote.HEART_KING));
        assertTrue(donne_.hand((byte)1).contient(CardBelote.SPADE_JACK));
        assertTrue(donne_.hand((byte)1).contient(CardBelote.CLUB_7));
        assertTrue(donne_.hand((byte)1).contient(CardBelote.HEART_7));
        assertTrue(donne_.hand((byte)2).contient(CardBelote.SPADE_7));
        assertTrue(donne_.hand((byte)2).contient(CardBelote.SPADE_QUEEN));
        assertTrue(donne_.hand((byte)2).contient(CardBelote.SPADE_8));
        assertTrue(donne_.hand((byte)2).contient(CardBelote.DIAMOND_JACK));
        assertTrue(donne_.hand((byte)2).contient(CardBelote.SPADE_10));
        assertTrue(donne_.hand((byte)3).contient(CardBelote.DIAMOND_1));
        assertTrue(donne_.hand((byte)3).contient(CardBelote.DIAMOND_KING));
        assertTrue(donne_.hand((byte)3).contient(CardBelote.CLUB_QUEEN));
        assertTrue(donne_.hand((byte)3).contient(CardBelote.SPADE_1));
        assertTrue(donne_.hand((byte)3).contient(CardBelote.CLUB_8));
        assertTrue(donne_.derniereMain().contient(CardBelote.DIAMOND_9));
        assertTrue(donne_.derniereMain().contient(CardBelote.CLUB_KING));
        assertTrue(donne_.derniereMain().contient(CardBelote.HEART_QUEEN));
        assertTrue(donne_.derniereMain().contient(CardBelote.CLUB_9));
        assertTrue(donne_.derniereMain().contient(CardBelote.HEART_8));
        assertTrue(donne_.derniereMain().contient(CardBelote.CLUB_10));
        assertTrue(donne_.derniereMain().contient(CardBelote.DIAMOND_10));
        assertTrue(donne_.derniereMain().contient(CardBelote.HEART_JACK));
        assertTrue(donne_.derniereMain().contient(CardBelote.CLUB_1));
        assertTrue(donne_.derniereMain().contient(CardBelote.HEART_9));
        assertTrue(donne_.derniereMain().contient(CardBelote.SPADE_KING));
        assertTrue(donne_.derniereMain().contient(CardBelote.DIAMOND_7));
    }

    @Test
    public void initDonne2Test(){
        HandBelote main_ = new HandBelote();
        main_.ajouter(CardBelote.DIAMOND_JACK);
        main_.ajouter(CardBelote.SPADE_10);
        main_.ajouter(CardBelote.SPADE_1);
        main_.ajouter(CardBelote.CLUB_8);
        main_.ajouter(CardBelote.DIAMOND_9);
        main_.ajouter(CardBelote.CLUB_KING);
        main_.ajouter(CardBelote.HEART_QUEEN);
        main_.ajouter(CardBelote.CLUB_9);
        main_.ajouter(CardBelote.HEART_8);
        main_.ajouter(CardBelote.CLUB_10);
        main_.ajouter(CardBelote.DIAMOND_10);
        main_.ajouter(CardBelote.HEART_JACK);
        main_.ajouter(CardBelote.CLUB_1);
        main_.ajouter(CardBelote.HEART_9);
        main_.ajouter(CardBelote.SPADE_KING);
        main_.ajouter(CardBelote.DIAMOND_7);
        main_.ajouter(CardBelote.DIAMOND_QUEEN);
        main_.ajouter(CardBelote.HEART_10);
        main_.ajouter(CardBelote.SPADE_9);
        main_.ajouter(CardBelote.HEART_1);
        main_.ajouter(CardBelote.HEART_KING);
        main_.ajouter(CardBelote.SPADE_JACK);
        main_.ajouter(CardBelote.SPADE_7);
        main_.ajouter(CardBelote.SPADE_QUEEN);
        main_.ajouter(CardBelote.SPADE_8);
        main_.ajouter(CardBelote.DIAMOND_1);
        main_.ajouter(CardBelote.DIAMOND_KING);
        main_.ajouter(CardBelote.CLUB_QUEEN);
        main_.ajouter(CardBelote.CLUB_JACK);
        main_.ajouter(CardBelote.DIAMOND_8);
        main_.ajouter(CardBelote.CLUB_7);
        main_.ajouter(CardBelote.HEART_7);
        DealBelote donne_ = deal(main_, 0, MixCardsChoice.EACH_LAUNCHING);
        assertEq(12,donne_.derniereMain().total());
    }

    @Test
    public void initDonne3Test(){
        HandBelote main_ = new HandBelote();
        main_.ajouter(CardBelote.DIAMOND_JACK);
        main_.ajouter(CardBelote.SPADE_10);
        main_.ajouter(CardBelote.SPADE_1);
        main_.ajouter(CardBelote.CLUB_8);
        main_.ajouter(CardBelote.DIAMOND_9);
        main_.ajouter(CardBelote.CLUB_KING);
        main_.ajouter(CardBelote.HEART_QUEEN);
        main_.ajouter(CardBelote.CLUB_9);
        main_.ajouter(CardBelote.HEART_8);
        main_.ajouter(CardBelote.CLUB_10);
        main_.ajouter(CardBelote.DIAMOND_10);
        main_.ajouter(CardBelote.HEART_JACK);
        main_.ajouter(CardBelote.CLUB_1);
        main_.ajouter(CardBelote.HEART_9);
        main_.ajouter(CardBelote.SPADE_KING);
        main_.ajouter(CardBelote.DIAMOND_7);
        main_.ajouter(CardBelote.DIAMOND_QUEEN);
        main_.ajouter(CardBelote.HEART_10);
        main_.ajouter(CardBelote.SPADE_9);
        main_.ajouter(CardBelote.HEART_1);
        main_.ajouter(CardBelote.HEART_KING);
        main_.ajouter(CardBelote.SPADE_JACK);
        main_.ajouter(CardBelote.SPADE_7);
        main_.ajouter(CardBelote.SPADE_QUEEN);
        main_.ajouter(CardBelote.SPADE_8);
        main_.ajouter(CardBelote.DIAMOND_1);
        main_.ajouter(CardBelote.DIAMOND_KING);
        main_.ajouter(CardBelote.CLUB_QUEEN);
        main_.ajouter(CardBelote.CLUB_JACK);
        main_.ajouter(CardBelote.DIAMOND_8);
        main_.ajouter(CardBelote.CLUB_7);
        main_.ajouter(CardBelote.HEART_7);
        DealBelote donne_ = deal(main_, 0, MixCardsChoice.EACH_DEAL);
        assertEq(12,donne_.derniereMain().total());
    }
    @Test
    public void initDonne4Test(){
        HandBelote main_ = new HandBelote();
        main_.ajouter(CardBelote.DIAMOND_JACK);
        main_.ajouter(CardBelote.SPADE_10);
        main_.ajouter(CardBelote.SPADE_1);
        main_.ajouter(CardBelote.CLUB_8);
        main_.ajouter(CardBelote.DIAMOND_9);
        main_.ajouter(CardBelote.CLUB_KING);
        main_.ajouter(CardBelote.HEART_QUEEN);
        main_.ajouter(CardBelote.CLUB_9);
        main_.ajouter(CardBelote.HEART_8);
        main_.ajouter(CardBelote.CLUB_10);
        main_.ajouter(CardBelote.DIAMOND_10);
        main_.ajouter(CardBelote.HEART_JACK);
        main_.ajouter(CardBelote.CLUB_1);
        main_.ajouter(CardBelote.HEART_9);
        main_.ajouter(CardBelote.SPADE_KING);
        main_.ajouter(CardBelote.DIAMOND_7);
        main_.ajouter(CardBelote.DIAMOND_QUEEN);
        main_.ajouter(CardBelote.HEART_10);
        main_.ajouter(CardBelote.SPADE_9);
        main_.ajouter(CardBelote.HEART_1);
        main_.ajouter(CardBelote.HEART_KING);
        main_.ajouter(CardBelote.SPADE_JACK);
        main_.ajouter(CardBelote.SPADE_7);
        main_.ajouter(CardBelote.SPADE_QUEEN);
        main_.ajouter(CardBelote.SPADE_8);
        main_.ajouter(CardBelote.DIAMOND_1);
        main_.ajouter(CardBelote.DIAMOND_KING);
        main_.ajouter(CardBelote.CLUB_QUEEN);
        main_.ajouter(CardBelote.CLUB_JACK);
        main_.ajouter(CardBelote.DIAMOND_8);
        main_.ajouter(CardBelote.CLUB_7);
        main_.ajouter(CardBelote.HEART_7);
        DealBelote donne_ = deal(main_, 0, MixCardsChoice.ONCE_ONLY);
        assertEq(12,donne_.derniereMain().total());
    }

    @Test
    public void initDonne5Test(){
        HandBelote main_ = new HandBelote();
        main_.ajouter(CardBelote.DIAMOND_JACK);
        main_.ajouter(CardBelote.SPADE_10);
        main_.ajouter(CardBelote.SPADE_1);
        main_.ajouter(CardBelote.CLUB_8);
        main_.ajouter(CardBelote.DIAMOND_9);
        main_.ajouter(CardBelote.CLUB_KING);
        main_.ajouter(CardBelote.HEART_QUEEN);
        main_.ajouter(CardBelote.CLUB_9);
        main_.ajouter(CardBelote.HEART_8);
        main_.ajouter(CardBelote.CLUB_10);
        main_.ajouter(CardBelote.DIAMOND_10);
        main_.ajouter(CardBelote.HEART_JACK);
        main_.ajouter(CardBelote.CLUB_1);
        main_.ajouter(CardBelote.HEART_9);
        main_.ajouter(CardBelote.SPADE_KING);
        main_.ajouter(CardBelote.DIAMOND_7);
        main_.ajouter(CardBelote.DIAMOND_QUEEN);
        main_.ajouter(CardBelote.HEART_10);
        main_.ajouter(CardBelote.SPADE_9);
        main_.ajouter(CardBelote.HEART_1);
        main_.ajouter(CardBelote.HEART_KING);
        main_.ajouter(CardBelote.SPADE_JACK);
        main_.ajouter(CardBelote.SPADE_7);
        main_.ajouter(CardBelote.SPADE_QUEEN);
        main_.ajouter(CardBelote.SPADE_8);
        main_.ajouter(CardBelote.DIAMOND_1);
        main_.ajouter(CardBelote.DIAMOND_KING);
        main_.ajouter(CardBelote.CLUB_QUEEN);
        main_.ajouter(CardBelote.CLUB_JACK);
        main_.ajouter(CardBelote.DIAMOND_8);
        main_.ajouter(CardBelote.CLUB_7);
        main_.ajouter(CardBelote.HEART_7);
        DealBelote donne_ = deal(main_, 1, MixCardsChoice.ONCE_ONLY);
        assertEq(12,donne_.derniereMain().total());
    }

    @Test
    public void initDonne6Test(){
        HandBelote main_ = new HandBelote();
        main_.ajouter(CardBelote.DIAMOND_JACK);
        main_.ajouter(CardBelote.SPADE_10);
        main_.ajouter(CardBelote.SPADE_1);
        main_.ajouter(CardBelote.CLUB_8);
        main_.ajouter(CardBelote.DIAMOND_9);
        main_.ajouter(CardBelote.CLUB_KING);
        main_.ajouter(CardBelote.HEART_QUEEN);
        main_.ajouter(CardBelote.CLUB_9);
        main_.ajouter(CardBelote.HEART_8);
        main_.ajouter(CardBelote.CLUB_10);
        main_.ajouter(CardBelote.DIAMOND_10);
        main_.ajouter(CardBelote.HEART_JACK);
        main_.ajouter(CardBelote.CLUB_1);
        main_.ajouter(CardBelote.HEART_9);
        main_.ajouter(CardBelote.SPADE_KING);
        main_.ajouter(CardBelote.DIAMOND_7);
        main_.ajouter(CardBelote.DIAMOND_QUEEN);
        main_.ajouter(CardBelote.HEART_10);
        main_.ajouter(CardBelote.SPADE_9);
        main_.ajouter(CardBelote.HEART_1);
        main_.ajouter(CardBelote.HEART_KING);
        main_.ajouter(CardBelote.SPADE_JACK);
        main_.ajouter(CardBelote.SPADE_7);
        main_.ajouter(CardBelote.SPADE_QUEEN);
        main_.ajouter(CardBelote.SPADE_8);
        main_.ajouter(CardBelote.DIAMOND_1);
        main_.ajouter(CardBelote.DIAMOND_KING);
        main_.ajouter(CardBelote.CLUB_QUEEN);
        main_.ajouter(CardBelote.CLUB_JACK);
        main_.ajouter(CardBelote.DIAMOND_8);
        main_.ajouter(CardBelote.CLUB_7);
        main_.ajouter(CardBelote.HEART_7);
        DealBelote donne_ = deal(main_, 1, MixCardsChoice.EACH_LAUNCHING);
        assertEq(12,donne_.derniereMain().total());
    }
    @Test
    public void completerDonne1Test(){
        HandBelote main_ = new HandBelote();
        main_.ajouter(CardBelote.DIAMOND_JACK);
        main_.ajouter(CardBelote.SPADE_10);
        main_.ajouter(CardBelote.SPADE_1);
        main_.ajouter(CardBelote.CLUB_8);
        main_.ajouter(CardBelote.DIAMOND_9);
        main_.ajouter(CardBelote.CLUB_KING);
        main_.ajouter(CardBelote.HEART_QUEEN);
        main_.ajouter(CardBelote.CLUB_9);
        main_.ajouter(CardBelote.HEART_8);
        main_.ajouter(CardBelote.CLUB_10);
        main_.ajouter(CardBelote.DIAMOND_10);
        main_.ajouter(CardBelote.HEART_JACK);
        main_.ajouter(CardBelote.CLUB_1);
        main_.ajouter(CardBelote.HEART_9);
        main_.ajouter(CardBelote.SPADE_KING);
        main_.ajouter(CardBelote.DIAMOND_7);
        main_.ajouter(CardBelote.DIAMOND_QUEEN);
        main_.ajouter(CardBelote.HEART_10);
        main_.ajouter(CardBelote.SPADE_9);
        main_.ajouter(CardBelote.HEART_1);
        main_.ajouter(CardBelote.HEART_KING);
        main_.ajouter(CardBelote.SPADE_JACK);
        main_.ajouter(CardBelote.SPADE_7);
        main_.ajouter(CardBelote.SPADE_QUEEN);
        main_.ajouter(CardBelote.SPADE_8);
        main_.ajouter(CardBelote.DIAMOND_1);
        main_.ajouter(CardBelote.DIAMOND_KING);
        main_.ajouter(CardBelote.CLUB_QUEEN);
        main_.ajouter(CardBelote.CLUB_JACK);
        main_.ajouter(CardBelote.DIAMOND_8);
        main_.ajouter(CardBelote.CLUB_7);
        main_.ajouter(CardBelote.HEART_7);
        RulesBelote regles_=new RulesBelote();
        DealBelote donne_ = deal(0, MixCardsChoice.NEVER, regles_, main_);
        donne_.completerDonne((byte) 0,regles_);
        assertTrue(donne_.hand().contient(CardBelote.DIAMOND_QUEEN));
        assertTrue(donne_.hand().contient(CardBelote.HEART_10));
        assertTrue(donne_.hand().contient(CardBelote.SPADE_9));
        assertTrue(donne_.hand().contient(CardBelote.CLUB_JACK));
        assertTrue(donne_.hand().contient(CardBelote.DIAMOND_8));
        assertTrue(donne_.hand().contient(CardBelote.DIAMOND_9));
        assertTrue(donne_.hand().contient(CardBelote.CLUB_KING));
        assertTrue(donne_.hand().contient(CardBelote.HEART_QUEEN));
        assertTrue(donne_.hand((byte)1).contient(CardBelote.HEART_1));
        assertTrue(donne_.hand((byte)1).contient(CardBelote.HEART_KING));
        assertTrue(donne_.hand((byte)1).contient(CardBelote.SPADE_JACK));
        assertTrue(donne_.hand((byte)1).contient(CardBelote.CLUB_7));
        assertTrue(donne_.hand((byte)1).contient(CardBelote.HEART_7));
        assertTrue(donne_.hand((byte)1).contient(CardBelote.CLUB_9));
        assertTrue(donne_.hand((byte)1).contient(CardBelote.HEART_8));
        assertTrue(donne_.hand((byte)1).contient(CardBelote.CLUB_10));
        assertTrue(donne_.hand((byte)2).contient(CardBelote.SPADE_7));
        assertTrue(donne_.hand((byte)2).contient(CardBelote.SPADE_QUEEN));
        assertTrue(donne_.hand((byte)2).contient(CardBelote.SPADE_8));
        assertTrue(donne_.hand((byte)2).contient(CardBelote.DIAMOND_JACK));
        assertTrue(donne_.hand((byte)2).contient(CardBelote.SPADE_10));
        assertTrue(donne_.hand((byte)2).contient(CardBelote.DIAMOND_10));
        assertTrue(donne_.hand((byte)2).contient(CardBelote.HEART_JACK));
        assertTrue(donne_.hand((byte)2).contient(CardBelote.CLUB_1));
        assertTrue(donne_.hand((byte)3).contient(CardBelote.DIAMOND_1));
        assertTrue(donne_.hand((byte)3).contient(CardBelote.DIAMOND_KING));
        assertTrue(donne_.hand((byte)3).contient(CardBelote.CLUB_QUEEN));
        assertTrue(donne_.hand((byte)3).contient(CardBelote.SPADE_1));
        assertTrue(donne_.hand((byte)3).contient(CardBelote.CLUB_8));
        assertTrue(donne_.hand((byte)3).contient(CardBelote.HEART_9));
        assertTrue(donne_.hand((byte)3).contient(CardBelote.SPADE_KING));
        assertTrue(donne_.hand((byte)3).contient(CardBelote.DIAMOND_7));
        assertTrue(donne_.derniereMain().contient(CardBelote.DIAMOND_9));
        assertTrue(donne_.derniereMain().contient(CardBelote.CLUB_KING));
        assertTrue(donne_.derniereMain().contient(CardBelote.HEART_QUEEN));
        assertTrue(donne_.derniereMain().contient(CardBelote.CLUB_9));
        assertTrue(donne_.derniereMain().contient(CardBelote.HEART_8));
        assertTrue(donne_.derniereMain().contient(CardBelote.CLUB_10));
        assertTrue(donne_.derniereMain().contient(CardBelote.DIAMOND_10));
        assertTrue(donne_.derniereMain().contient(CardBelote.HEART_JACK));
        assertTrue(donne_.derniereMain().contient(CardBelote.CLUB_1));
        assertTrue(donne_.derniereMain().contient(CardBelote.HEART_9));
        assertTrue(donne_.derniereMain().contient(CardBelote.SPADE_KING));
        assertTrue(donne_.derniereMain().contient(CardBelote.DIAMOND_7));
        donne_ = deal(0, main_, regles_);
        donne_.completerDonne((byte) 1,regles_);
        assertTrue(donne_.hand().contient(CardBelote.DIAMOND_QUEEN));
        assertTrue(donne_.hand().contient(CardBelote.HEART_10));
        assertTrue(donne_.hand().contient(CardBelote.SPADE_9));
        assertTrue(donne_.hand().contient(CardBelote.CLUB_JACK));
        assertTrue(donne_.hand().contient(CardBelote.DIAMOND_8));

        assertTrue(donne_.hand().contient(CardBelote.CLUB_KING));
        assertTrue(donne_.hand().contient(CardBelote.HEART_QUEEN));
        assertTrue(donne_.hand().contient(CardBelote.CLUB_9));

        assertTrue(donne_.hand((byte)1).contient(CardBelote.HEART_1));
        assertTrue(donne_.hand((byte)1).contient(CardBelote.HEART_KING));
        assertTrue(donne_.hand((byte)1).contient(CardBelote.SPADE_JACK));
        assertTrue(donne_.hand((byte)1).contient(CardBelote.CLUB_7));
        assertTrue(donne_.hand((byte)1).contient(CardBelote.HEART_7));
        assertTrue(donne_.hand((byte)1).contient(CardBelote.DIAMOND_9));

        assertTrue(donne_.hand((byte)1).contient(CardBelote.HEART_8));
        assertTrue(donne_.hand((byte)1).contient(CardBelote.CLUB_10));

        assertTrue(donne_.hand((byte)2).contient(CardBelote.SPADE_7));
        assertTrue(donne_.hand((byte)2).contient(CardBelote.SPADE_QUEEN));
        assertTrue(donne_.hand((byte)2).contient(CardBelote.SPADE_8));
        assertTrue(donne_.hand((byte)2).contient(CardBelote.DIAMOND_JACK));
        assertTrue(donne_.hand((byte)2).contient(CardBelote.SPADE_10));
        assertTrue(donne_.hand((byte)2).contient(CardBelote.DIAMOND_10));
        assertTrue(donne_.hand((byte)2).contient(CardBelote.HEART_JACK));
        assertTrue(donne_.hand((byte)2).contient(CardBelote.CLUB_1));
        assertTrue(donne_.hand((byte)3).contient(CardBelote.DIAMOND_1));
        assertTrue(donne_.hand((byte)3).contient(CardBelote.DIAMOND_KING));
        assertTrue(donne_.hand((byte)3).contient(CardBelote.CLUB_QUEEN));
        assertTrue(donne_.hand((byte)3).contient(CardBelote.SPADE_1));
        assertTrue(donne_.hand((byte)3).contient(CardBelote.CLUB_8));
        assertTrue(donne_.hand((byte)3).contient(CardBelote.HEART_9));
        assertTrue(donne_.hand((byte)3).contient(CardBelote.SPADE_KING));
        assertTrue(donne_.hand((byte)3).contient(CardBelote.DIAMOND_7));
        assertTrue(donne_.derniereMain().contient(CardBelote.DIAMOND_9));
        assertTrue(donne_.derniereMain().contient(CardBelote.CLUB_KING));
        assertTrue(donne_.derniereMain().contient(CardBelote.HEART_QUEEN));
        assertTrue(donne_.derniereMain().contient(CardBelote.CLUB_9));
        assertTrue(donne_.derniereMain().contient(CardBelote.HEART_8));
        assertTrue(donne_.derniereMain().contient(CardBelote.CLUB_10));
        assertTrue(donne_.derniereMain().contient(CardBelote.DIAMOND_10));
        assertTrue(donne_.derniereMain().contient(CardBelote.HEART_JACK));
        assertTrue(donne_.derniereMain().contient(CardBelote.CLUB_1));
        assertTrue(donne_.derniereMain().contient(CardBelote.HEART_9));
        assertTrue(donne_.derniereMain().contient(CardBelote.SPADE_KING));
        assertTrue(donne_.derniereMain().contient(CardBelote.DIAMOND_7));

        donne_ = deal(0, main_, regles_);
        donne_.completerDonne((byte) 2,regles_);
        assertTrue(donne_.hand().contient(CardBelote.DIAMOND_QUEEN));
        assertTrue(donne_.hand().contient(CardBelote.HEART_10));
        assertTrue(donne_.hand().contient(CardBelote.SPADE_9));
        assertTrue(donne_.hand().contient(CardBelote.CLUB_JACK));
        assertTrue(donne_.hand().contient(CardBelote.DIAMOND_8));

        assertTrue(donne_.hand().contient(CardBelote.CLUB_KING));
        assertTrue(donne_.hand().contient(CardBelote.HEART_QUEEN));
        assertTrue(donne_.hand().contient(CardBelote.CLUB_9));

        assertTrue(donne_.hand((byte)1).contient(CardBelote.HEART_1));
        assertTrue(donne_.hand((byte)1).contient(CardBelote.HEART_KING));
        assertTrue(donne_.hand((byte)1).contient(CardBelote.SPADE_JACK));
        assertTrue(donne_.hand((byte)1).contient(CardBelote.CLUB_7));
        assertTrue(donne_.hand((byte)1).contient(CardBelote.HEART_7));

        assertTrue(donne_.hand((byte)1).contient(CardBelote.HEART_8));
        assertTrue(donne_.hand((byte)1).contient(CardBelote.CLUB_10));
        assertTrue(donne_.hand((byte)1).contient(CardBelote.DIAMOND_10));

        assertTrue(donne_.hand((byte)2).contient(CardBelote.SPADE_7));
        assertTrue(donne_.hand((byte)2).contient(CardBelote.SPADE_QUEEN));
        assertTrue(donne_.hand((byte)2).contient(CardBelote.SPADE_8));
        assertTrue(donne_.hand((byte)2).contient(CardBelote.DIAMOND_JACK));
        assertTrue(donne_.hand((byte)2).contient(CardBelote.SPADE_10));
        assertTrue(donne_.hand((byte)2).contient(CardBelote.DIAMOND_9));
        assertTrue(donne_.hand((byte)2).contient(CardBelote.HEART_JACK));
        assertTrue(donne_.hand((byte)2).contient(CardBelote.CLUB_1));
        assertTrue(donne_.hand((byte)3).contient(CardBelote.DIAMOND_1));
        assertTrue(donne_.hand((byte)3).contient(CardBelote.DIAMOND_KING));
        assertTrue(donne_.hand((byte)3).contient(CardBelote.CLUB_QUEEN));
        assertTrue(donne_.hand((byte)3).contient(CardBelote.SPADE_1));
        assertTrue(donne_.hand((byte)3).contient(CardBelote.CLUB_8));
        assertTrue(donne_.hand((byte)3).contient(CardBelote.HEART_9));
        assertTrue(donne_.hand((byte)3).contient(CardBelote.SPADE_KING));
        assertTrue(donne_.hand((byte)3).contient(CardBelote.DIAMOND_7));
        assertTrue(donne_.derniereMain().contient(CardBelote.DIAMOND_9));
        assertTrue(donne_.derniereMain().contient(CardBelote.CLUB_KING));
        assertTrue(donne_.derniereMain().contient(CardBelote.HEART_QUEEN));
        assertTrue(donne_.derniereMain().contient(CardBelote.CLUB_9));
        assertTrue(donne_.derniereMain().contient(CardBelote.HEART_8));
        assertTrue(donne_.derniereMain().contient(CardBelote.CLUB_10));
        assertTrue(donne_.derniereMain().contient(CardBelote.DIAMOND_10));
        assertTrue(donne_.derniereMain().contient(CardBelote.HEART_JACK));
        assertTrue(donne_.derniereMain().contient(CardBelote.CLUB_1));
        assertTrue(donne_.derniereMain().contient(CardBelote.HEART_9));
        assertTrue(donne_.derniereMain().contient(CardBelote.SPADE_KING));
        assertTrue(donne_.derniereMain().contient(CardBelote.DIAMOND_7));

        donne_ = deal(0, main_, regles_);
        donne_.completerDonne((byte) 3,regles_);
        assertTrue(donne_.hand().contient(CardBelote.DIAMOND_QUEEN));
        assertTrue(donne_.hand().contient(CardBelote.HEART_10));
        assertTrue(donne_.hand().contient(CardBelote.SPADE_9));
        assertTrue(donne_.hand().contient(CardBelote.CLUB_JACK));
        assertTrue(donne_.hand().contient(CardBelote.DIAMOND_8));

        assertTrue(donne_.hand().contient(CardBelote.CLUB_KING));
        assertTrue(donne_.hand().contient(CardBelote.HEART_QUEEN));
        assertTrue(donne_.hand().contient(CardBelote.CLUB_9));

        assertTrue(donne_.hand((byte)1).contient(CardBelote.HEART_1));
        assertTrue(donne_.hand((byte)1).contient(CardBelote.HEART_KING));
        assertTrue(donne_.hand((byte)1).contient(CardBelote.SPADE_JACK));
        assertTrue(donne_.hand((byte)1).contient(CardBelote.CLUB_7));
        assertTrue(donne_.hand((byte)1).contient(CardBelote.HEART_7));

        assertTrue(donne_.hand((byte)1).contient(CardBelote.HEART_8));
        assertTrue(donne_.hand((byte)1).contient(CardBelote.CLUB_10));
        assertTrue(donne_.hand((byte)1).contient(CardBelote.DIAMOND_10));

        assertTrue(donne_.hand((byte)2).contient(CardBelote.SPADE_7));
        assertTrue(donne_.hand((byte)2).contient(CardBelote.SPADE_QUEEN));
        assertTrue(donne_.hand((byte)2).contient(CardBelote.SPADE_8));
        assertTrue(donne_.hand((byte)2).contient(CardBelote.DIAMOND_JACK));
        assertTrue(donne_.hand((byte)2).contient(CardBelote.SPADE_10));
        assertTrue(donne_.hand((byte)2).contient(CardBelote.HEART_9));
        assertTrue(donne_.hand((byte)2).contient(CardBelote.HEART_JACK));
        assertTrue(donne_.hand((byte)2).contient(CardBelote.CLUB_1));
        assertTrue(donne_.hand((byte)3).contient(CardBelote.DIAMOND_1));
        assertTrue(donne_.hand((byte)3).contient(CardBelote.DIAMOND_KING));
        assertTrue(donne_.hand((byte)3).contient(CardBelote.CLUB_QUEEN));
        assertTrue(donne_.hand((byte)3).contient(CardBelote.SPADE_1));
        assertTrue(donne_.hand((byte)3).contient(CardBelote.CLUB_8));
        assertTrue(donne_.hand((byte)3).contient(CardBelote.DIAMOND_9));
        assertTrue(donne_.hand((byte)3).contient(CardBelote.SPADE_KING));
        assertTrue(donne_.hand((byte)3).contient(CardBelote.DIAMOND_7));
        assertTrue(donne_.derniereMain().contient(CardBelote.DIAMOND_9));
        assertTrue(donne_.derniereMain().contient(CardBelote.CLUB_KING));
        assertTrue(donne_.derniereMain().contient(CardBelote.HEART_QUEEN));
        assertTrue(donne_.derniereMain().contient(CardBelote.CLUB_9));
        assertTrue(donne_.derniereMain().contient(CardBelote.HEART_8));
        assertTrue(donne_.derniereMain().contient(CardBelote.CLUB_10));
        assertTrue(donne_.derniereMain().contient(CardBelote.DIAMOND_10));
        assertTrue(donne_.derniereMain().contient(CardBelote.HEART_JACK));
        assertTrue(donne_.derniereMain().contient(CardBelote.CLUB_1));
        assertTrue(donne_.derniereMain().contient(CardBelote.HEART_9));
        assertTrue(donne_.derniereMain().contient(CardBelote.SPADE_KING));
        assertTrue(donne_.derniereMain().contient(CardBelote.DIAMOND_7));
    }

    private DealBelote deal(HandBelote _main, int _nombreDeParties, MixCardsChoice _mix) {
        RulesBelote regles_=new RulesBelote();
        return deal(_nombreDeParties, _mix, regles_, _main);
    }

    private DealBelote deal(int _nombreDeParties, MixCardsChoice _mix, RulesBelote _regles, HandBelote _m) {
        _regles.getCommon().setMixedCards(_mix);
        return deal(_nombreDeParties, _m, _regles);
    }

    private DealBelote deal(int _nombreDeParties, HandBelote _m, RulesBelote _r) {
        DealBelote donne_ = new DealBelote(_nombreDeParties);
        donne_.setDealer((byte) 3);
        donne_.initDonne(_r,new DisplayingBelote(), DefaultGenerator.oneElt(), new HandBelote(_m));
        return donne_;
    }

}
