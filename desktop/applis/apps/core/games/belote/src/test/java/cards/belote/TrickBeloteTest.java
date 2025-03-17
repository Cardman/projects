package cards.belote;

import cards.belote.enumerations.DealingBelote;
import org.junit.Test;

import cards.belote.enumerations.BidBelote;
import cards.belote.enumerations.CardBelote;
import cards.consts.Suit;
import code.util.*;


public class TrickBeloteTest extends EquallableBeloteUtil {
    @Test
    public void new_PliBelote_byte_0Test(){
        TrickBelote pli_ = new TrickBelote();
        assertTrue(pli_.estVide());
    }
    @Test
    public void new_PliBelote_byte_1Test(){
        TrickBelote pli_ = new TrickBelote(0);
        assertTrue(pli_.estVide());
    }
    @Test
    public void new_PliBelote_byte_2Test(){
        TrickBelote pli_ = new TrickBelote(1);
        assertTrue(pli_.estVide());
    }
    @Test
    public void new_PliBelote_byte_3Test(){
        TrickBelote pli_ = new TrickBelote(2);
        assertTrue(pli_.estVide());
    }
    @Test
    public void new_PliBelote_byte_4Test(){
        TrickBelote pli_ = new TrickBelote(3);
        assertTrue(pli_.estVide());
    }
    @Test
    public void new_PliBelote_MainBelote_1(){
        TrickBelote pli_ = new TrickBelote(new HandBelote(), 0);
        assertTrue(pli_.estVide());
        pli_ = new TrickBelote(new HandBelote(), 0);
        assertTrue(pli_.estVide());
        HandBelote main_ = new HandBelote();
        main_.ajouter(CardBelote.DIAMOND_1);
        pli_ = new TrickBelote(main_, 0);
        assertEq(1, pli_.total() );
        assertTrue(pli_.contient(CardBelote.DIAMOND_1));
    }
    @Test
    public void couleurDemandee1(){
        TrickBelote pli_ = new TrickBelote(0);
        //nombreTotal++;
        assertEq(Suit.UNDEFINED, pli_.couleurDemandee());
        pli_ = new TrickBelote(0);
        //nombreTotal++;
        pli_.getCartes().ajouter(CardBelote.DIAMOND_1);
        assertEq(Suit.DIAMOND, pli_.couleurDemandee());
        pli_ = new TrickBelote(0);
        pli_.getCartes().ajouter(CardBelote.DIAMOND_1);
        pli_.getCartes().ajouter(CardBelote.SPADE_JACK);
        assertEq(Suit.DIAMOND, pli_.couleurDemandee());
    }
    @Test
    public void aJoue1Test(){
        TrickBelote pli_ = new TrickBelote(0);
        int nombreJoueurs_ = 4;
        assertTrue(!pli_.aJoue(0, nombreJoueurs_));
        assertTrue(!pli_.aJoue(1, nombreJoueurs_));
        assertTrue(!pli_.aJoue(2, nombreJoueurs_));
        assertTrue(!pli_.aJoue(3, nombreJoueurs_));
    }
    @Test
    public void aJoue2Test(){
        TrickBelote pli_ = new TrickBelote(1);
        int nombreJoueurs_ = 4;
        assertTrue(!pli_.aJoue(0, nombreJoueurs_));
        assertTrue(!pli_.aJoue(1, nombreJoueurs_));
        assertTrue(!pli_.aJoue(2, nombreJoueurs_));
        assertTrue(!pli_.aJoue(3, nombreJoueurs_));
    }
    @Test
    public void aJoue3Test(){
        TrickBelote pli_ = new TrickBelote(2);
        int nombreJoueurs_ = 4;
        assertTrue(!pli_.aJoue(0, nombreJoueurs_));
        assertTrue(!pli_.aJoue(1, nombreJoueurs_));
        assertTrue(!pli_.aJoue(2, nombreJoueurs_));
        assertTrue(!pli_.aJoue(3, nombreJoueurs_));
    }
    @Test
    public void aJoue4Test(){
        TrickBelote pli_ = new TrickBelote(3);
        int nombreJoueurs_ = 4;
        assertTrue(!pli_.aJoue(0, nombreJoueurs_));
        assertTrue(!pli_.aJoue(1, nombreJoueurs_));
        assertTrue(!pli_.aJoue(2, nombreJoueurs_));
        assertTrue(!pli_.aJoue(3, nombreJoueurs_));
    }
    @Test
    public void aJoue5Test(){
        TrickBelote pli_ = new TrickBelote(0);
        int nombreJoueurs_ = 4;
        pli_.getCartes().ajouter(CardBelote.DIAMOND_1);
        pli_.getCartes().ajouter(CardBelote.SPADE_JACK);
        pli_.getCartes().ajouter(CardBelote.HEART_KING);
        pli_.getCartes().ajouter(CardBelote.CLUB_7);
        assertTrue(pli_.aJoue(0, nombreJoueurs_));
        assertTrue(pli_.aJoue(1, nombreJoueurs_));
        assertTrue(pli_.aJoue(2, nombreJoueurs_));
        assertTrue(pli_.aJoue(3, nombreJoueurs_));
    }
    @Test
    public void aJoue6Test(){
        TrickBelote pli_ = new TrickBelote(1);
        int nombreJoueurs_ = 4;
        pli_.getCartes().ajouter(CardBelote.DIAMOND_1);
        pli_.getCartes().ajouter(CardBelote.SPADE_JACK);
        pli_.getCartes().ajouter(CardBelote.HEART_KING);
        pli_.getCartes().ajouter(CardBelote.CLUB_7);
        assertTrue(pli_.aJoue(0, nombreJoueurs_));
        assertTrue(pli_.aJoue(1, nombreJoueurs_));
        assertTrue(pli_.aJoue(2, nombreJoueurs_));
        assertTrue(pli_.aJoue(3, nombreJoueurs_));
    }
    @Test
    public void aJoue7Test(){
        TrickBelote pli_ = new TrickBelote(2);
        int nombreJoueurs_ = 4;
        pli_.getCartes().ajouter(CardBelote.DIAMOND_1);
        pli_.getCartes().ajouter(CardBelote.SPADE_JACK);
        pli_.getCartes().ajouter(CardBelote.HEART_KING);
        pli_.getCartes().ajouter(CardBelote.CLUB_7);
        assertTrue(pli_.aJoue(0, nombreJoueurs_));
        assertTrue(pli_.aJoue(1, nombreJoueurs_));
        assertTrue(pli_.aJoue(2, nombreJoueurs_));
        assertTrue(pli_.aJoue(3, nombreJoueurs_));
    }
    @Test
    public void aJoue8Test(){
        TrickBelote pli_ = new TrickBelote(3);
        int nombreJoueurs_ = 4;
        pli_.getCartes().ajouter(CardBelote.DIAMOND_1);
        pli_.getCartes().ajouter(CardBelote.SPADE_JACK);
        pli_.getCartes().ajouter(CardBelote.HEART_KING);
        pli_.getCartes().ajouter(CardBelote.CLUB_7);
        assertTrue(pli_.aJoue(0, nombreJoueurs_));
        assertTrue(pli_.aJoue(1, nombreJoueurs_));
        assertTrue(pli_.aJoue(2, nombreJoueurs_));
        assertTrue(pli_.aJoue(3, nombreJoueurs_));
    }
    @Test
    public void aJoue9Test(){
        TrickBelote pli_ = new TrickBelote(0);
        int nombreJoueurs_ = 4;
        pli_.getCartes().ajouter(CardBelote.DIAMOND_1);
        assertTrue(pli_.aJoue(0, nombreJoueurs_));
        assertTrue(!pli_.aJoue(1 %nombreJoueurs_, nombreJoueurs_));
        assertTrue(!pli_.aJoue(2 %nombreJoueurs_, nombreJoueurs_));
        assertTrue(!pli_.aJoue(3 %nombreJoueurs_, nombreJoueurs_));
    }
    @Test
    public void aJoue10Test(){
        TrickBelote pli_ = new TrickBelote(1);
        int nombreJoueurs_ = 4;
        pli_.getCartes().ajouter(CardBelote.DIAMOND_1);
        assertTrue(pli_.aJoue(1, nombreJoueurs_));
        assertTrue(!pli_.aJoue((1+1)%nombreJoueurs_, nombreJoueurs_));
        assertTrue(!pli_.aJoue((1+2)%nombreJoueurs_, nombreJoueurs_));
        assertTrue(!pli_.aJoue((1+3)%nombreJoueurs_, nombreJoueurs_));
    }
    @Test
    public void aJoue11Test(){
        TrickBelote pli_ = new TrickBelote(2);
        int nombreJoueurs_ = 4;
        pli_.getCartes().ajouter(CardBelote.DIAMOND_1);
        assertTrue(pli_.aJoue(2, nombreJoueurs_));
        assertTrue(!pli_.aJoue((2+1)%nombreJoueurs_, nombreJoueurs_));
        assertTrue(!pli_.aJoue((2+2)%nombreJoueurs_, nombreJoueurs_));
        assertTrue(!pli_.aJoue((2+3)%nombreJoueurs_, nombreJoueurs_));
    }
    @Test
    public void aJoue12Test(){
        TrickBelote pli_ = new TrickBelote(3);
        int nombreJoueurs_ = 4;
        pli_.getCartes().ajouter(CardBelote.DIAMOND_1);
        assertTrue(pli_.aJoue(3, nombreJoueurs_));
        assertTrue(!pli_.aJoue((3+1)%nombreJoueurs_, nombreJoueurs_));
        assertTrue(!pli_.aJoue((3+2)%nombreJoueurs_, nombreJoueurs_));
        assertTrue(!pli_.aJoue((3+3)%nombreJoueurs_, nombreJoueurs_));
    }
    @Test
    public void aJoue13Test(){
        TrickBelote pli_ = new TrickBelote(0);
        int nombreJoueurs_ = 4;
        pli_.getCartes().ajouter(CardBelote.DIAMOND_1);
        pli_.getCartes().ajouter(CardBelote.SPADE_JACK);
        assertTrue(pli_.aJoue(0, nombreJoueurs_));
        assertTrue(pli_.aJoue(1 %nombreJoueurs_, nombreJoueurs_));
        assertTrue(!pli_.aJoue(2 %nombreJoueurs_, nombreJoueurs_));
        assertTrue(!pli_.aJoue(3 %nombreJoueurs_, nombreJoueurs_));
    }
    @Test
    public void aJoue14Test(){
        TrickBelote pli_ = new TrickBelote(1);
        int nombreJoueurs_ = 4;
        pli_.getCartes().ajouter(CardBelote.DIAMOND_1);
        pli_.getCartes().ajouter(CardBelote.SPADE_JACK);
        assertTrue(pli_.aJoue(1, nombreJoueurs_));
        assertTrue(pli_.aJoue((1+1)%nombreJoueurs_, nombreJoueurs_));
        assertTrue(!pli_.aJoue((1+2)%nombreJoueurs_, nombreJoueurs_));
        assertTrue(!pli_.aJoue((1+3)%nombreJoueurs_, nombreJoueurs_));
    }
    @Test
    public void aJoue15Test(){
        TrickBelote pli_ = new TrickBelote(2);
        int nombreJoueurs_ = 4;
        pli_.getCartes().ajouter(CardBelote.DIAMOND_1);
        pli_.getCartes().ajouter(CardBelote.SPADE_JACK);
        assertTrue(pli_.aJoue(2, nombreJoueurs_));
        assertTrue(pli_.aJoue((2+1)%nombreJoueurs_, nombreJoueurs_));
        assertTrue(!pli_.aJoue((2+2)%nombreJoueurs_, nombreJoueurs_));
        assertTrue(!pli_.aJoue((2+3)%nombreJoueurs_, nombreJoueurs_));
    }
    @Test
    public void aJoue16Test(){
        TrickBelote pli_ = new TrickBelote(3);
        int nombreJoueurs_ = 4;
        pli_.getCartes().ajouter(CardBelote.DIAMOND_1);
        pli_.getCartes().ajouter(CardBelote.SPADE_JACK);
        assertTrue(pli_.aJoue(3, nombreJoueurs_));
        assertTrue(pli_.aJoue((3+1)%nombreJoueurs_, nombreJoueurs_));
        assertTrue(!pli_.aJoue((3+2)%nombreJoueurs_, nombreJoueurs_));
        assertTrue(!pli_.aJoue((3+3)%nombreJoueurs_, nombreJoueurs_));
    }
    @Test
    public void aJoue17Test(){
        TrickBelote pli_ = new TrickBelote(0);
        int nombreJoueurs_ = 4;
        pli_.getCartes().ajouter(CardBelote.DIAMOND_1);
        pli_.getCartes().ajouter(CardBelote.SPADE_JACK);
        pli_.getCartes().ajouter(CardBelote.HEART_KING);
        assertTrue(pli_.aJoue(0, nombreJoueurs_));
        assertTrue(pli_.aJoue(1 %nombreJoueurs_, nombreJoueurs_));
        assertTrue(pli_.aJoue(2 %nombreJoueurs_, nombreJoueurs_));
        assertTrue(!pli_.aJoue(3 %nombreJoueurs_, nombreJoueurs_));
    }
    @Test
    public void aJoue18Test(){
        TrickBelote pli_ = new TrickBelote(1);
        int nombreJoueurs_ = 4;
        pli_.getCartes().ajouter(CardBelote.DIAMOND_1);
        pli_.getCartes().ajouter(CardBelote.SPADE_JACK);
        pli_.getCartes().ajouter(CardBelote.HEART_KING);
        assertTrue(pli_.aJoue(1, nombreJoueurs_));
        assertTrue(pli_.aJoue((1+1)%nombreJoueurs_, nombreJoueurs_));
        assertTrue(pli_.aJoue((1+2)%nombreJoueurs_, nombreJoueurs_));
        assertTrue(!pli_.aJoue((1+3)%nombreJoueurs_, nombreJoueurs_));
    }
    @Test
    public void aJoue19Test(){
        TrickBelote pli_ = new TrickBelote(2);
        int nombreJoueurs_ = 4;
        pli_.getCartes().ajouter(CardBelote.DIAMOND_1);
        pli_.getCartes().ajouter(CardBelote.SPADE_JACK);
        pli_.getCartes().ajouter(CardBelote.HEART_KING);
        assertTrue(pli_.aJoue(2, nombreJoueurs_));
        assertTrue(pli_.aJoue((2+1)%nombreJoueurs_, nombreJoueurs_));
        assertTrue(pli_.aJoue((2+2)%nombreJoueurs_, nombreJoueurs_));
        assertTrue(!pli_.aJoue((2+3)%nombreJoueurs_, nombreJoueurs_));
    }
    @Test
    public void aJoue20Test(){
        TrickBelote pli_ = new TrickBelote(3);
        int nombreJoueurs_ = 4;
        pli_.getCartes().ajouter(CardBelote.DIAMOND_1);
        pli_.getCartes().ajouter(CardBelote.SPADE_JACK);
        pli_.getCartes().ajouter(CardBelote.HEART_KING);
        assertTrue(pli_.aJoue(3, nombreJoueurs_));
        assertTrue(pli_.aJoue((3+1)%nombreJoueurs_, nombreJoueurs_));
        assertTrue(pli_.aJoue((3+2)%nombreJoueurs_, nombreJoueurs_));
        assertTrue(!pli_.aJoue((3+3)%nombreJoueurs_, nombreJoueurs_));
    }
    @Test
    public void carteDuJoueur1(){
        TrickBelote pli_ = new TrickBelote(0);
        //nombreTotal++;
        int nombreJoueurs_ = 4;
        pli_.getCartes().ajouter(CardBelote.DIAMOND_1);
        assertEq(CardBelote.DIAMOND_1, pli_.carteDuJoueur(0, nombreJoueurs_));
        pli_.getCartes().ajouter(CardBelote.SPADE_JACK);
        assertEq(CardBelote.DIAMOND_1, pli_.carteDuJoueur(0, nombreJoueurs_));
        assertEq(CardBelote.SPADE_JACK, pli_.carteDuJoueur(1, nombreJoueurs_));
        pli_.getCartes().ajouter(CardBelote.HEART_KING);
        assertEq(CardBelote.DIAMOND_1, pli_.carteDuJoueur(0, nombreJoueurs_));
        assertEq(CardBelote.SPADE_JACK, pli_.carteDuJoueur(1, nombreJoueurs_));
        assertEq(CardBelote.HEART_KING, pli_.carteDuJoueur(2, nombreJoueurs_));
        pli_.getCartes().ajouter(CardBelote.CLUB_7);
        assertEq(CardBelote.DIAMOND_1, pli_.carteDuJoueur(0, nombreJoueurs_));
        assertEq(CardBelote.SPADE_JACK, pli_.carteDuJoueur(1, nombreJoueurs_));
        assertEq(CardBelote.HEART_KING, pli_.carteDuJoueur(2, nombreJoueurs_));
        assertEq(CardBelote.CLUB_7, pli_.carteDuJoueur(3, nombreJoueurs_));
        pli_ = new TrickBelote(1);
        pli_.getCartes().ajouter(CardBelote.DIAMOND_1);
        assertEq(CardBelote.DIAMOND_1, pli_.carteDuJoueur(1, nombreJoueurs_));
        pli_.getCartes().ajouter(CardBelote.SPADE_JACK);
        assertEq(CardBelote.DIAMOND_1, pli_.carteDuJoueur(1, nombreJoueurs_));
        assertEq(CardBelote.SPADE_JACK, pli_.carteDuJoueur(2, nombreJoueurs_));
        pli_.getCartes().ajouter(CardBelote.HEART_KING);
        assertEq(CardBelote.DIAMOND_1, pli_.carteDuJoueur(1, nombreJoueurs_));
        assertEq(CardBelote.SPADE_JACK, pli_.carteDuJoueur(2, nombreJoueurs_));
        assertEq(CardBelote.HEART_KING, pli_.carteDuJoueur(3, nombreJoueurs_));
        pli_.getCartes().ajouter(CardBelote.CLUB_7);
        assertEq(CardBelote.DIAMOND_1, pli_.carteDuJoueur(1, nombreJoueurs_));
        assertEq(CardBelote.SPADE_JACK, pli_.carteDuJoueur(2, nombreJoueurs_));
        assertEq(CardBelote.HEART_KING, pli_.carteDuJoueur(3, nombreJoueurs_));
        assertEq(CardBelote.CLUB_7, pli_.carteDuJoueur(0, nombreJoueurs_));
    }
    @Test
    public void carteDuJoueur2(){
        TrickBelote pli_ = new TrickBelote(1);
        pli_.getCartes().ajouter(CardBelote.DIAMOND_1);
        pli_.getCartes().ajouter(CardBelote.SPADE_JACK);
        pli_.getCartes().ajouter(CardBelote.HEART_KING);
        pli_.getCartes().ajouter(CardBelote.CLUB_7);
        assertEq(CardBelote.DIAMOND_1, pli_.carteDuJoueur(1));
        assertEq(CardBelote.SPADE_JACK, pli_.carteDuJoueur(2));
        assertEq(CardBelote.HEART_KING, pli_.carteDuJoueur(3));
        assertEq(CardBelote.CLUB_7, pli_.carteDuJoueur(0));
    }
    @Test
    public void joueurAyantJoue1(){
        TrickBelote pli_ = new TrickBelote(0);
        //nombreTotal++;
        int nombreJoueurs_ = 4;
        assertEq(-1, pli_.joueurAyantJoue(CardBelote.DIAMOND_1, nombreJoueurs_));
        pli_.getCartes().ajouter(CardBelote.DIAMOND_1);
        assertEq(0, pli_.joueurAyantJoue(CardBelote.DIAMOND_1, nombreJoueurs_));
        pli_.getCartes().ajouter(CardBelote.SPADE_JACK);
        assertEq(0, pli_.joueurAyantJoue(CardBelote.DIAMOND_1, nombreJoueurs_));
        assertEq(1, pli_.joueurAyantJoue(CardBelote.SPADE_JACK, nombreJoueurs_));
        pli_.getCartes().ajouter(CardBelote.HEART_KING);
        assertEq(0, pli_.joueurAyantJoue(CardBelote.DIAMOND_1, nombreJoueurs_));
        assertEq(1, pli_.joueurAyantJoue(CardBelote.SPADE_JACK, nombreJoueurs_));
        assertEq(2, pli_.joueurAyantJoue(CardBelote.HEART_KING, nombreJoueurs_));
        pli_.getCartes().ajouter(CardBelote.CLUB_7);
        assertEq(0, pli_.joueurAyantJoue(CardBelote.DIAMOND_1, nombreJoueurs_));
        assertEq(1, pli_.joueurAyantJoue(CardBelote.SPADE_JACK, nombreJoueurs_));
        assertEq(2, pli_.joueurAyantJoue(CardBelote.HEART_KING, nombreJoueurs_));
        assertEq(3, pli_.joueurAyantJoue(CardBelote.CLUB_7, nombreJoueurs_));
        pli_ = new TrickBelote(1);
        //nombreTotal++;
        assertEq(-1, pli_.joueurAyantJoue(CardBelote.DIAMOND_1, nombreJoueurs_));
        pli_.getCartes().ajouter(CardBelote.DIAMOND_1);
        assertEq(1, pli_.joueurAyantJoue(CardBelote.DIAMOND_1, nombreJoueurs_));
        pli_.getCartes().ajouter(CardBelote.SPADE_JACK);
        assertEq(1, pli_.joueurAyantJoue(CardBelote.DIAMOND_1, nombreJoueurs_));
        assertEq(2, pli_.joueurAyantJoue(CardBelote.SPADE_JACK, nombreJoueurs_));
        pli_.getCartes().ajouter(CardBelote.HEART_KING);
        assertEq(1, pli_.joueurAyantJoue(CardBelote.DIAMOND_1, nombreJoueurs_));
        assertEq(2, pli_.joueurAyantJoue(CardBelote.SPADE_JACK, nombreJoueurs_));
        assertEq(3, pli_.joueurAyantJoue(CardBelote.HEART_KING, nombreJoueurs_));
        pli_.getCartes().ajouter(CardBelote.CLUB_7);
        assertEq(1, pli_.joueurAyantJoue(CardBelote.DIAMOND_1, nombreJoueurs_));
        assertEq(2, pli_.joueurAyantJoue(CardBelote.SPADE_JACK, nombreJoueurs_));
        assertEq(3, pli_.joueurAyantJoue(CardBelote.HEART_KING, nombreJoueurs_));
        assertEq(0, pli_.joueurAyantJoue(CardBelote.CLUB_7, nombreJoueurs_));
    }
    @Test
    public void joueursAyantJoueAvant1(){
        TrickBelote pli_ = new TrickBelote(0);
        pli_.getCartes().ajouter(CardBelote.DIAMOND_1);
        pli_.getCartes().ajouter(CardBelote.SPADE_JACK);
        pli_.getCartes().ajouter(CardBelote.HEART_KING);
        pli_.getCartes().ajouter(CardBelote.CLUB_7);
        assertEq(0,pli_.joueursAyantJoueAvant(0,DealingBelote.CLASSIC_2_VS_2).size());
        Ints joueurs_;
        joueurs_ = pli_.joueursAyantJoueAvant(1,DealingBelote.CLASSIC_2_VS_2);
        assertEq(1, joueurs_.size());
        assertTrue(joueurs_.containsObj(0));
        joueurs_ = pli_.joueursAyantJoueAvant(2,DealingBelote.CLASSIC_2_VS_2);
        assertEq(2, joueurs_.size());
        assertTrue(joueurs_.containsObj(0));
        assertTrue(joueurs_.containsObj(1));
        joueurs_ = pli_.joueursAyantJoueAvant(3,DealingBelote.CLASSIC_2_VS_2);
        assertEq(3, joueurs_.size());
        assertTrue(joueurs_.containsObj(0));
        assertTrue(joueurs_.containsObj(1));
        assertTrue(joueurs_.containsObj(2));
        pli_ = new TrickBelote(1);
        pli_.getCartes().ajouter(CardBelote.DIAMOND_1);
        pli_.getCartes().ajouter(CardBelote.SPADE_JACK);
        pli_.getCartes().ajouter(CardBelote.HEART_KING);
        pli_.getCartes().ajouter(CardBelote.CLUB_7);
        assertEq(0,pli_.joueursAyantJoueAvant(1,DealingBelote.CLASSIC_2_VS_2).size());
        joueurs_ = pli_.joueursAyantJoueAvant(2,DealingBelote.CLASSIC_2_VS_2);
        assertEq(1, joueurs_.size());
        assertTrue(joueurs_.containsObj(1));
        joueurs_ = pli_.joueursAyantJoueAvant(3,DealingBelote.CLASSIC_2_VS_2);
        assertEq(2, joueurs_.size());
        assertTrue(joueurs_.containsObj(1));
        assertTrue(joueurs_.containsObj(2));
        joueurs_ = pli_.joueursAyantJoueAvant(0,DealingBelote.CLASSIC_2_VS_2);
        assertEq(3, joueurs_.size());
        assertTrue(joueurs_.containsObj(1));
        assertTrue(joueurs_.containsObj(2));
        assertTrue(joueurs_.containsObj(3));
        pli_ = new TrickBelote(2);
        pli_.getCartes().ajouter(CardBelote.DIAMOND_1);
        pli_.getCartes().ajouter(CardBelote.SPADE_JACK);
        pli_.getCartes().ajouter(CardBelote.HEART_KING);
        pli_.getCartes().ajouter(CardBelote.CLUB_7);
        assertEq(0,pli_.joueursAyantJoueAvant(2,DealingBelote.CLASSIC_2_VS_2).size());
        joueurs_ = pli_.joueursAyantJoueAvant(3,DealingBelote.CLASSIC_2_VS_2);
        assertEq(1, joueurs_.size());
        assertTrue(joueurs_.containsObj(2));
        joueurs_ = pli_.joueursAyantJoueAvant(0,DealingBelote.CLASSIC_2_VS_2);
        assertEq(2, joueurs_.size());
        assertTrue(joueurs_.containsObj(2));
        assertTrue(joueurs_.containsObj(3));
        joueurs_ = pli_.joueursAyantJoueAvant(1,DealingBelote.CLASSIC_2_VS_2);
        assertEq(3, joueurs_.size());
        assertTrue(joueurs_.containsObj(2));
        assertTrue(joueurs_.containsObj(3));
        assertTrue(joueurs_.containsObj(0));
        pli_ = new TrickBelote(3);
        pli_.getCartes().ajouter(CardBelote.DIAMOND_1);
        pli_.getCartes().ajouter(CardBelote.SPADE_JACK);
        pli_.getCartes().ajouter(CardBelote.HEART_KING);
        pli_.getCartes().ajouter(CardBelote.CLUB_7);
        assertEq(0,pli_.joueursAyantJoueAvant(3,DealingBelote.CLASSIC_2_VS_2).size());
        joueurs_ = pli_.joueursAyantJoueAvant(0,DealingBelote.CLASSIC_2_VS_2);
        assertEq(1, joueurs_.size());
        assertTrue(joueurs_.containsObj(3));
        joueurs_ = pli_.joueursAyantJoueAvant(1,DealingBelote.CLASSIC_2_VS_2);
        assertEq(2, joueurs_.size());
        assertTrue(joueurs_.containsObj(3));
        assertTrue(joueurs_.containsObj(0));
        joueurs_ = pli_.joueursAyantJoueAvant(2,DealingBelote.CLASSIC_2_VS_2);
        assertEq(3, joueurs_.size());
        assertTrue(joueurs_.containsObj(3));
        assertTrue(joueurs_.containsObj(0));
        assertTrue(joueurs_.containsObj(1));
    }
    @Test
    public void joueursAyantJoueAvant2(){
        TrickBelote pli_ = new TrickBelote(0);
        pli_.getCartes().ajouter(CardBelote.DIAMOND_1);
        Ints joueurs_;
        joueurs_ = pli_.joueursAyantJoueAvant(2,DealingBelote.CLASSIC_2_VS_2);
        assertEq(1, joueurs_.size());
        assertTrue(joueurs_.containsObj(0));
    }
    @Test
    public void joueursAyantJoueAvant3(){
        TrickBelote pli_ = new TrickBelote(0);
        Ints joueurs_;
        joueurs_ = pli_.joueursAyantJoueAvant(2,DealingBelote.CLASSIC_2_VS_2);
        assertEq(0, joueurs_.size());
    }
    @Test
    public void playersHavingPlayed1(){
        Ints joueurs_;
        TrickBelote pli_ = new TrickBelote(0);
        assertEq(0,pli_.playersHavingPlayed(4).size());
        pli_.getCartes().ajouter(CardBelote.DIAMOND_1);
        joueurs_ = pli_.playersHavingPlayed(4);
        assertEq(1, joueurs_.size());
        assertTrue(joueurs_.containsObj(0));
        pli_.getCartes().ajouter(CardBelote.SPADE_JACK);
        joueurs_ = pli_.playersHavingPlayed(4);
        assertEq(2, joueurs_.size());
        assertTrue(joueurs_.containsObj(0));
        assertTrue(joueurs_.containsObj(1));
        pli_.getCartes().ajouter(CardBelote.HEART_KING);
        joueurs_ = pli_.playersHavingPlayed(4);
        assertEq(3, joueurs_.size());
        assertTrue(joueurs_.containsObj(0));
        assertTrue(joueurs_.containsObj(1));
        assertTrue(joueurs_.containsObj(2));
        pli_.getCartes().ajouter(CardBelote.CLUB_7);
        joueurs_ = pli_.playersHavingPlayed(4);
        assertEq(4, joueurs_.size());
        pli_ = new TrickBelote(1);
        assertEq(0,pli_.playersHavingPlayed(4).size());
        pli_.getCartes().ajouter(CardBelote.DIAMOND_1);
        joueurs_ = pli_.playersHavingPlayed(4);
        assertEq(1, joueurs_.size());
        assertTrue(joueurs_.containsObj(1));
        pli_.getCartes().ajouter(CardBelote.SPADE_JACK);
        joueurs_ = pli_.playersHavingPlayed(4);
        assertEq(2, joueurs_.size());
        assertTrue(joueurs_.containsObj(1));
        assertTrue(joueurs_.containsObj(2));
        pli_.getCartes().ajouter(CardBelote.HEART_KING);
        joueurs_ = pli_.playersHavingPlayed(4);
        assertEq(3, joueurs_.size());
        assertTrue(joueurs_.containsObj(1));
        assertTrue(joueurs_.containsObj(2));
        assertTrue(joueurs_.containsObj(3));
        pli_.getCartes().ajouter(CardBelote.CLUB_7);
        joueurs_ = pli_.playersHavingPlayed(4);
        assertEq(4, joueurs_.size());
        pli_ = new TrickBelote(2);
        assertEq(0,pli_.playersHavingPlayed(4).size());
        pli_.getCartes().ajouter(CardBelote.DIAMOND_1);
        joueurs_ = pli_.playersHavingPlayed(4);
        assertEq(1, joueurs_.size());
        assertTrue(joueurs_.containsObj(2));
        pli_.getCartes().ajouter(CardBelote.SPADE_JACK);
        joueurs_ = pli_.playersHavingPlayed(4);
        assertEq(2, joueurs_.size());
        assertTrue(joueurs_.containsObj(2));
        assertTrue(joueurs_.containsObj(3));
        pli_.getCartes().ajouter(CardBelote.HEART_KING);
        joueurs_ = pli_.playersHavingPlayed(4);
        assertEq(3, joueurs_.size());
        assertTrue(joueurs_.containsObj(2));
        assertTrue(joueurs_.containsObj(3));
        assertTrue(joueurs_.containsObj(0));
        pli_.getCartes().ajouter(CardBelote.CLUB_7);
        joueurs_ = pli_.playersHavingPlayed(4);
        assertEq(4, joueurs_.size());
        pli_ = new TrickBelote(3);
        assertEq(0,pli_.playersHavingPlayed(4).size());
        pli_.getCartes().ajouter(CardBelote.DIAMOND_1);
        joueurs_ = pli_.playersHavingPlayed(4);
        assertEq(1, joueurs_.size());
        assertTrue(joueurs_.containsObj(3));
        pli_.getCartes().ajouter(CardBelote.SPADE_JACK);
        joueurs_ = pli_.playersHavingPlayed(4);
        assertEq(2, joueurs_.size());
        assertTrue(joueurs_.containsObj(3));
        assertTrue(joueurs_.containsObj(0));
        pli_.getCartes().ajouter(CardBelote.HEART_KING);
        joueurs_ = pli_.playersHavingPlayed(4);
        assertEq(3, joueurs_.size());
        assertTrue(joueurs_.containsObj(3));
        assertTrue(joueurs_.containsObj(0));
        assertTrue(joueurs_.containsObj(1));
        pli_.getCartes().ajouter(CardBelote.CLUB_7);
        joueurs_ = pli_.playersHavingPlayed(4);
        assertEq(4, joueurs_.size());
    }
    @Test
    public void cardsInTricks() {
        TrickBelote pli_ = new TrickBelote(1);
        pli_.ajouter(CardBelote.HEART_1);
        pli_.ajouter(CardBelote.HEART_10);
        CustList<HandBelote> ls_ = pli_.completeCurrent(4, true);
        assertEq(4,ls_.size());
        assertEq(0,ls_.get(0).total());
        assertEq(1,ls_.get(1).total());
        assertEq(CardBelote.HEART_1,ls_.get(1).carte(0));
        assertEq(1,ls_.get(2).total());
        assertEq(CardBelote.HEART_10,ls_.get(2).carte(0));
        assertEq(0,ls_.get(3).total());
    }
    @Test
    public void getRamasseurPliEnCours_BeloteToutAtout1Test(){
        BidBelote enchere_ = BidBelote.ALL_TRUMP;
        BidBeloteSuit enchereCouleur_ = new BidBeloteSuit();
        enchereCouleur_.setBid(enchere_);
        int nombreDeJoueurs_ = 4;
        TrickBelote pli_ = new TrickBelote(0);
        pli_.getCartes().ajouter(CardBelote.DIAMOND_1);
        assertEq(0, pli_.getRamasseurPliEnCours(nombreDeJoueurs_, enchereCouleur_));
        pli_.getCartes().ajouter(CardBelote.SPADE_7);
        assertEq(0, pli_.getRamasseurPliEnCours(nombreDeJoueurs_, enchereCouleur_));
        pli_.getCartes().ajouter(CardBelote.HEART_KING);
        assertEq(0, pli_.getRamasseurPliEnCours(nombreDeJoueurs_, enchereCouleur_));
        pli_.getCartes().ajouter(CardBelote.DIAMOND_JACK);
        int j_ = 3 % nombreDeJoueurs_;
        assertEq(j_, pli_.getRamasseurPliEnCours(nombreDeJoueurs_, enchereCouleur_));
    }
    @Test
    public void getRamasseurPliEnCours_BeloteToutAtout2Test(){
        BidBelote enchere_ = BidBelote.ALL_TRUMP;
        BidBeloteSuit enchereCouleur_ = new BidBeloteSuit();
        enchereCouleur_.setBid(enchere_);
        int nombreDeJoueurs_ = 4;
        TrickBelote pli_ = new TrickBelote(1);
        pli_.getCartes().ajouter(CardBelote.DIAMOND_1);
        assertEq(1, pli_.getRamasseurPliEnCours(nombreDeJoueurs_, enchereCouleur_));
        pli_.getCartes().ajouter(CardBelote.SPADE_7);
        assertEq(1, pli_.getRamasseurPliEnCours(nombreDeJoueurs_, enchereCouleur_));
        pli_.getCartes().ajouter(CardBelote.HEART_KING);
        assertEq(1, pli_.getRamasseurPliEnCours(nombreDeJoueurs_, enchereCouleur_));
        pli_.getCartes().ajouter(CardBelote.DIAMOND_JACK);
        int j_ = (1 + 3) % nombreDeJoueurs_;
        assertEq(j_, pli_.getRamasseurPliEnCours(nombreDeJoueurs_, enchereCouleur_));
    }
    @Test
    public void getRamasseurPliEnCours_BeloteToutAtout3Test(){
        BidBelote enchere_ = BidBelote.ALL_TRUMP;
        BidBeloteSuit enchereCouleur_ = new BidBeloteSuit();
        enchereCouleur_.setBid(enchere_);
        int nombreDeJoueurs_ = 4;
        TrickBelote pli_ = new TrickBelote(2);
        pli_.getCartes().ajouter(CardBelote.DIAMOND_1);
        assertEq(2, pli_.getRamasseurPliEnCours(nombreDeJoueurs_, enchereCouleur_));
        pli_.getCartes().ajouter(CardBelote.SPADE_7);
        assertEq(2, pli_.getRamasseurPliEnCours(nombreDeJoueurs_, enchereCouleur_));
        pli_.getCartes().ajouter(CardBelote.HEART_KING);
        assertEq(2, pli_.getRamasseurPliEnCours(nombreDeJoueurs_, enchereCouleur_));
        pli_.getCartes().ajouter(CardBelote.DIAMOND_JACK);
        int j_ = (2 + 3) % nombreDeJoueurs_;
        assertEq(j_, pli_.getRamasseurPliEnCours(nombreDeJoueurs_, enchereCouleur_));
    }
    @Test
    public void getRamasseurPliEnCours_BeloteToutAtout4Test(){
        BidBelote enchere_ = BidBelote.ALL_TRUMP;
        BidBeloteSuit enchereCouleur_ = new BidBeloteSuit();
        enchereCouleur_.setBid(enchere_);
        int nombreDeJoueurs_ = 4;
        TrickBelote pli_ = new TrickBelote(3);
        pli_.getCartes().ajouter(CardBelote.DIAMOND_1);
        assertEq(3, pli_.getRamasseurPliEnCours(nombreDeJoueurs_, enchereCouleur_));
        pli_.getCartes().ajouter(CardBelote.SPADE_7);
        assertEq(3, pli_.getRamasseurPliEnCours(nombreDeJoueurs_, enchereCouleur_));
        pli_.getCartes().ajouter(CardBelote.HEART_KING);
        assertEq(3, pli_.getRamasseurPliEnCours(nombreDeJoueurs_, enchereCouleur_));
        pli_.getCartes().ajouter(CardBelote.DIAMOND_JACK);
        int j_ = (3 + 3) % nombreDeJoueurs_;
        assertEq(j_, pli_.getRamasseurPliEnCours(nombreDeJoueurs_, enchereCouleur_));
    }
    @Test
    public void getRamasseurPliEnCours_BeloteSansAtout1Test(){
        BidBelote enchere_ = BidBelote.NO_TRUMP;
        BidBeloteSuit enchereCouleur_ = new BidBeloteSuit();
        enchereCouleur_.setBid(enchere_);
        int nombreDeJoueurs_ = 4;
        TrickBelote pli_ = new TrickBelote(0);
        pli_.getCartes().ajouter(CardBelote.DIAMOND_1);
        assertEq(0, pli_.getRamasseurPliEnCours(nombreDeJoueurs_, enchereCouleur_));
        pli_.getCartes().ajouter(CardBelote.SPADE_7);
        assertEq(0, pli_.getRamasseurPliEnCours(nombreDeJoueurs_, enchereCouleur_));
        pli_.getCartes().ajouter(CardBelote.HEART_KING);
        assertEq(0, pli_.getRamasseurPliEnCours(nombreDeJoueurs_, enchereCouleur_));
        pli_.getCartes().ajouter(CardBelote.DIAMOND_JACK);
        assertEq(0, pli_.getRamasseurPliEnCours(nombreDeJoueurs_, enchereCouleur_));
    }
    @Test
    public void getRamasseurPliEnCours_BeloteSansAtout2Test(){
        BidBelote enchere_ = BidBelote.NO_TRUMP;
        BidBeloteSuit enchereCouleur_ = new BidBeloteSuit();
        enchereCouleur_.setBid(enchere_);
        int nombreDeJoueurs_ = 4;
        TrickBelote pli_ = new TrickBelote(1);
        pli_.getCartes().ajouter(CardBelote.DIAMOND_1);
        assertEq(1, pli_.getRamasseurPliEnCours(nombreDeJoueurs_, enchereCouleur_));
        pli_.getCartes().ajouter(CardBelote.SPADE_7);
        assertEq(1, pli_.getRamasseurPliEnCours(nombreDeJoueurs_, enchereCouleur_));
        pli_.getCartes().ajouter(CardBelote.HEART_KING);
        assertEq(1, pli_.getRamasseurPliEnCours(nombreDeJoueurs_, enchereCouleur_));
        pli_.getCartes().ajouter(CardBelote.DIAMOND_JACK);
        assertEq(1, pli_.getRamasseurPliEnCours(nombreDeJoueurs_, enchereCouleur_));
    }
    @Test
    public void getRamasseurPliEnCours_BeloteSansAtout3Test(){
        BidBelote enchere_ = BidBelote.NO_TRUMP;
        BidBeloteSuit enchereCouleur_ = new BidBeloteSuit();
        enchereCouleur_.setBid(enchere_);
        int nombreDeJoueurs_ = 4;
        TrickBelote pli_ = new TrickBelote(2);
        pli_.getCartes().ajouter(CardBelote.DIAMOND_1);
        assertEq(2, pli_.getRamasseurPliEnCours(nombreDeJoueurs_, enchereCouleur_));
        pli_.getCartes().ajouter(CardBelote.SPADE_7);
        assertEq(2, pli_.getRamasseurPliEnCours(nombreDeJoueurs_, enchereCouleur_));
        pli_.getCartes().ajouter(CardBelote.HEART_KING);
        assertEq(2, pli_.getRamasseurPliEnCours(nombreDeJoueurs_, enchereCouleur_));
        pli_.getCartes().ajouter(CardBelote.DIAMOND_JACK);
        assertEq(2, pli_.getRamasseurPliEnCours(nombreDeJoueurs_, enchereCouleur_));
    }
    @Test
    public void getRamasseurPliEnCours_BeloteSansAtout4Test(){
        BidBelote enchere_ = BidBelote.NO_TRUMP;
        BidBeloteSuit enchereCouleur_ = new BidBeloteSuit();
        enchereCouleur_.setBid(enchere_);
        int nombreDeJoueurs_ = 4;
        TrickBelote pli_ = new TrickBelote(3);
        pli_.getCartes().ajouter(CardBelote.DIAMOND_1);
        assertEq(3, pli_.getRamasseurPliEnCours(nombreDeJoueurs_, enchereCouleur_));
        pli_.getCartes().ajouter(CardBelote.SPADE_7);
        assertEq(3, pli_.getRamasseurPliEnCours(nombreDeJoueurs_, enchereCouleur_));
        pli_.getCartes().ajouter(CardBelote.HEART_KING);
        assertEq(3, pli_.getRamasseurPliEnCours(nombreDeJoueurs_, enchereCouleur_));
        pli_.getCartes().ajouter(CardBelote.DIAMOND_JACK);
        assertEq(3, pli_.getRamasseurPliEnCours(nombreDeJoueurs_, enchereCouleur_));
    }
    @Test
    public void getRamasseurPliEnCours_BeloteCouleur1Test(){
        int nombreDeJoueurs_ = 4;
        BidBelote e = BidBelote.SUIT;
        BidBeloteSuit enchereCouleur_ = new BidBeloteSuit();
        enchereCouleur_.setBid(e);
        enchereCouleur_.setSuit(Suit.HEART);
        int jTwo_ = 2 % nombreDeJoueurs_;

        TrickBelote pli_ = new TrickBelote(0);
        pli_.getCartes().ajouter(CardBelote.DIAMOND_1);
        assertEq(0, pli_.getRamasseurPliEnCours(nombreDeJoueurs_, enchereCouleur_));
        pli_.getCartes().ajouter(CardBelote.SPADE_7);
        assertEq(0, pli_.getRamasseurPliEnCours(nombreDeJoueurs_, enchereCouleur_));
        pli_.getCartes().ajouter(CardBelote.HEART_KING);
        assertEq(jTwo_, pli_.getRamasseurPliEnCours(nombreDeJoueurs_, enchereCouleur_));
        pli_.getCartes().ajouter(CardBelote.DIAMOND_8);
        assertEq(jTwo_, pli_.getRamasseurPliEnCours(nombreDeJoueurs_, enchereCouleur_));
        pli_ = new TrickBelote(0);
        pli_.getCartes().ajouter(CardBelote.DIAMOND_1);
        assertEq(0, pli_.getRamasseurPliEnCours(nombreDeJoueurs_, enchereCouleur_));
        pli_.getCartes().ajouter(CardBelote.SPADE_7);
        assertEq(0, pli_.getRamasseurPliEnCours(nombreDeJoueurs_, enchereCouleur_));
        pli_.getCartes().ajouter(CardBelote.HEART_KING);
        assertEq(jTwo_, pli_.getRamasseurPliEnCours(nombreDeJoueurs_, enchereCouleur_));
        pli_.getCartes().ajouter(CardBelote.DIAMOND_JACK);
        assertEq(jTwo_, pli_.getRamasseurPliEnCours(nombreDeJoueurs_, enchereCouleur_));
    }
    @Test
    public void getRamasseurPliEnCours_BeloteCouleur2Test(){
        int nombreDeJoueurs_ = 4;
        BidBelote e = BidBelote.SUIT;
        BidBeloteSuit enchereCouleur_ = new BidBeloteSuit();
        enchereCouleur_.setBid(e);
        enchereCouleur_.setSuit(Suit.HEART);
        int jTwo_ = (1 + 2) % nombreDeJoueurs_;

        TrickBelote pli_ = new TrickBelote(1);
        pli_.getCartes().ajouter(CardBelote.DIAMOND_1);
        assertEq(1, pli_.getRamasseurPliEnCours(nombreDeJoueurs_, enchereCouleur_));
        pli_.getCartes().ajouter(CardBelote.SPADE_7);
        assertEq(1, pli_.getRamasseurPliEnCours(nombreDeJoueurs_, enchereCouleur_));
        pli_.getCartes().ajouter(CardBelote.HEART_KING);
        assertEq(jTwo_, pli_.getRamasseurPliEnCours(nombreDeJoueurs_, enchereCouleur_));
        pli_.getCartes().ajouter(CardBelote.DIAMOND_8);
        assertEq(jTwo_, pli_.getRamasseurPliEnCours(nombreDeJoueurs_, enchereCouleur_));
        pli_ = new TrickBelote(1);
        pli_.getCartes().ajouter(CardBelote.DIAMOND_1);
        assertEq(1, pli_.getRamasseurPliEnCours(nombreDeJoueurs_, enchereCouleur_));
        pli_.getCartes().ajouter(CardBelote.SPADE_7);
        assertEq(1, pli_.getRamasseurPliEnCours(nombreDeJoueurs_, enchereCouleur_));
        pli_.getCartes().ajouter(CardBelote.HEART_KING);
        assertEq(jTwo_, pli_.getRamasseurPliEnCours(nombreDeJoueurs_, enchereCouleur_));
        pli_.getCartes().ajouter(CardBelote.DIAMOND_JACK);
        assertEq(jTwo_, pli_.getRamasseurPliEnCours(nombreDeJoueurs_, enchereCouleur_));
    }
    @Test
    public void getRamasseurPliEnCours_BeloteCouleur3Test(){
        int nombreDeJoueurs_ = 4;
        BidBelote e = BidBelote.SUIT;
        BidBeloteSuit enchereCouleur_ = new BidBeloteSuit();
        enchereCouleur_.setBid(e);
        enchereCouleur_.setSuit(Suit.HEART);
        int jTwo_ = (2 + 2) % nombreDeJoueurs_;

        TrickBelote pli_ = new TrickBelote(2);
        pli_.getCartes().ajouter(CardBelote.DIAMOND_1);
        assertEq(2, pli_.getRamasseurPliEnCours(nombreDeJoueurs_, enchereCouleur_));
        pli_.getCartes().ajouter(CardBelote.SPADE_7);
        assertEq(2, pli_.getRamasseurPliEnCours(nombreDeJoueurs_, enchereCouleur_));
        pli_.getCartes().ajouter(CardBelote.HEART_KING);
        assertEq(jTwo_, pli_.getRamasseurPliEnCours(nombreDeJoueurs_, enchereCouleur_));
        pli_.getCartes().ajouter(CardBelote.DIAMOND_8);
        assertEq(jTwo_, pli_.getRamasseurPliEnCours(nombreDeJoueurs_, enchereCouleur_));
        pli_ = new TrickBelote(2);
        pli_.getCartes().ajouter(CardBelote.DIAMOND_1);
        assertEq(2, pli_.getRamasseurPliEnCours(nombreDeJoueurs_, enchereCouleur_));
        pli_.getCartes().ajouter(CardBelote.SPADE_7);
        assertEq(2, pli_.getRamasseurPliEnCours(nombreDeJoueurs_, enchereCouleur_));
        pli_.getCartes().ajouter(CardBelote.HEART_KING);
        assertEq(jTwo_, pli_.getRamasseurPliEnCours(nombreDeJoueurs_, enchereCouleur_));
        pli_.getCartes().ajouter(CardBelote.DIAMOND_JACK);
        assertEq(jTwo_, pli_.getRamasseurPliEnCours(nombreDeJoueurs_, enchereCouleur_));
    }
    @Test
    public void getRamasseurPliEnCours_BeloteCouleur4Test(){
        int nombreDeJoueurs_ = 4;
        BidBelote e = BidBelote.SUIT;
        BidBeloteSuit enchereCouleur_ = new BidBeloteSuit();
        enchereCouleur_.setBid(e);
        enchereCouleur_.setSuit(Suit.HEART);
        int jTwo_ = (3 + 2) % nombreDeJoueurs_;

        TrickBelote pli_ = new TrickBelote(3);
        pli_.getCartes().ajouter(CardBelote.DIAMOND_1);
        assertEq(3, pli_.getRamasseurPliEnCours(nombreDeJoueurs_, enchereCouleur_));
        pli_.getCartes().ajouter(CardBelote.SPADE_7);
        assertEq(3, pli_.getRamasseurPliEnCours(nombreDeJoueurs_, enchereCouleur_));
        pli_.getCartes().ajouter(CardBelote.HEART_KING);
        assertEq(jTwo_, pli_.getRamasseurPliEnCours(nombreDeJoueurs_, enchereCouleur_));
        pli_.getCartes().ajouter(CardBelote.DIAMOND_8);
        assertEq(jTwo_, pli_.getRamasseurPliEnCours(nombreDeJoueurs_, enchereCouleur_));
        pli_ = new TrickBelote(3);
        pli_.getCartes().ajouter(CardBelote.DIAMOND_1);
        assertEq(3, pli_.getRamasseurPliEnCours(nombreDeJoueurs_, enchereCouleur_));
        pli_.getCartes().ajouter(CardBelote.SPADE_7);
        assertEq(3, pli_.getRamasseurPliEnCours(nombreDeJoueurs_, enchereCouleur_));
        pli_.getCartes().ajouter(CardBelote.HEART_KING);
        assertEq(jTwo_, pli_.getRamasseurPliEnCours(nombreDeJoueurs_, enchereCouleur_));
        pli_.getCartes().ajouter(CardBelote.DIAMOND_JACK);
        assertEq(jTwo_, pli_.getRamasseurPliEnCours(nombreDeJoueurs_, enchereCouleur_));
    }
    @Test
    public void getRamasseurPliEnCours_BeloteCouleur5Test(){
        int nombreDeJoueurs_ = 4;
        BidBelote e = BidBelote.SUIT;
        BidBeloteSuit enchereCouleur_ = new BidBeloteSuit();
        enchereCouleur_.setBid(e);
        enchereCouleur_.setSuit(Suit.SPADE);
        int j_ = 1 % nombreDeJoueurs_;

        TrickBelote pli_ = new TrickBelote(0);
        pli_.getCartes().ajouter(CardBelote.DIAMOND_1);
        assertEq(0, pli_.getRamasseurPliEnCours(nombreDeJoueurs_, enchereCouleur_));
        pli_.getCartes().ajouter(CardBelote.SPADE_7);
        assertEq(j_, pli_.getRamasseurPliEnCours(nombreDeJoueurs_, enchereCouleur_));
        pli_.getCartes().ajouter(CardBelote.HEART_KING);
        assertEq(j_, pli_.getRamasseurPliEnCours(nombreDeJoueurs_, enchereCouleur_));
        pli_.getCartes().ajouter(CardBelote.DIAMOND_8);
        assertEq(j_, pli_.getRamasseurPliEnCours(nombreDeJoueurs_, enchereCouleur_));
        pli_ = new TrickBelote(0);
        pli_.getCartes().ajouter(CardBelote.DIAMOND_1);
        assertEq(0, pli_.getRamasseurPliEnCours(nombreDeJoueurs_, enchereCouleur_));
        pli_.getCartes().ajouter(CardBelote.SPADE_7);
        assertEq(j_, pli_.getRamasseurPliEnCours(nombreDeJoueurs_, enchereCouleur_));
        pli_.getCartes().ajouter(CardBelote.HEART_KING);
        assertEq(j_, pli_.getRamasseurPliEnCours(nombreDeJoueurs_, enchereCouleur_));
        pli_.getCartes().ajouter(CardBelote.DIAMOND_JACK);
        assertEq(j_, pli_.getRamasseurPliEnCours(nombreDeJoueurs_, enchereCouleur_));
    }
    @Test
    public void getRamasseurPliEnCours_BeloteCouleur6Test(){
        int nombreDeJoueurs_ = 4;
        BidBelote e = BidBelote.SUIT;
        BidBeloteSuit enchereCouleur_ = new BidBeloteSuit();
        enchereCouleur_.setBid(e);
        enchereCouleur_.setSuit(Suit.SPADE);
        int j_ = (1 + 1) % nombreDeJoueurs_;

        TrickBelote pli_ = new TrickBelote(1);
        pli_.getCartes().ajouter(CardBelote.DIAMOND_1);
        assertEq(1, pli_.getRamasseurPliEnCours(nombreDeJoueurs_, enchereCouleur_));
        pli_.getCartes().ajouter(CardBelote.SPADE_7);
        assertEq(j_, pli_.getRamasseurPliEnCours(nombreDeJoueurs_, enchereCouleur_));
        pli_.getCartes().ajouter(CardBelote.HEART_KING);
        assertEq(j_, pli_.getRamasseurPliEnCours(nombreDeJoueurs_, enchereCouleur_));
        pli_.getCartes().ajouter(CardBelote.DIAMOND_8);
        assertEq(j_, pli_.getRamasseurPliEnCours(nombreDeJoueurs_, enchereCouleur_));
        pli_ = new TrickBelote(1);
        pli_.getCartes().ajouter(CardBelote.DIAMOND_1);
        assertEq(1, pli_.getRamasseurPliEnCours(nombreDeJoueurs_, enchereCouleur_));
        pli_.getCartes().ajouter(CardBelote.SPADE_7);
        assertEq(j_, pli_.getRamasseurPliEnCours(nombreDeJoueurs_, enchereCouleur_));
        pli_.getCartes().ajouter(CardBelote.HEART_KING);
        assertEq(j_, pli_.getRamasseurPliEnCours(nombreDeJoueurs_, enchereCouleur_));
        pli_.getCartes().ajouter(CardBelote.DIAMOND_JACK);
        assertEq(j_, pli_.getRamasseurPliEnCours(nombreDeJoueurs_, enchereCouleur_));
    }
    @Test
    public void getRamasseurPliEnCours_BeloteCouleur7Test(){
        int nombreDeJoueurs_ = 4;
        BidBelote e = BidBelote.SUIT;
        BidBeloteSuit enchereCouleur_ = new BidBeloteSuit();
        enchereCouleur_.setBid(e);
        enchereCouleur_.setSuit(Suit.SPADE);
        int j_ = (2 + 1) % nombreDeJoueurs_;

        TrickBelote pli_ = new TrickBelote(2);
        pli_.getCartes().ajouter(CardBelote.DIAMOND_1);
        assertEq(2, pli_.getRamasseurPliEnCours(nombreDeJoueurs_, enchereCouleur_));
        pli_.getCartes().ajouter(CardBelote.SPADE_7);
        assertEq(j_, pli_.getRamasseurPliEnCours(nombreDeJoueurs_, enchereCouleur_));
        pli_.getCartes().ajouter(CardBelote.HEART_KING);
        assertEq(j_, pli_.getRamasseurPliEnCours(nombreDeJoueurs_, enchereCouleur_));
        pli_.getCartes().ajouter(CardBelote.DIAMOND_8);
        assertEq(j_, pli_.getRamasseurPliEnCours(nombreDeJoueurs_, enchereCouleur_));
        pli_ = new TrickBelote(2);
        pli_.getCartes().ajouter(CardBelote.DIAMOND_1);
        assertEq(2, pli_.getRamasseurPliEnCours(nombreDeJoueurs_, enchereCouleur_));
        pli_.getCartes().ajouter(CardBelote.SPADE_7);
        assertEq(j_, pli_.getRamasseurPliEnCours(nombreDeJoueurs_, enchereCouleur_));
        pli_.getCartes().ajouter(CardBelote.HEART_KING);
        assertEq(j_, pli_.getRamasseurPliEnCours(nombreDeJoueurs_, enchereCouleur_));
        pli_.getCartes().ajouter(CardBelote.DIAMOND_JACK);
        assertEq(j_, pli_.getRamasseurPliEnCours(nombreDeJoueurs_, enchereCouleur_));
    }
    @Test
    public void getRamasseurPliEnCours_BeloteCouleur8Test(){
        int nombreDeJoueurs_ = 4;
        BidBelote e = BidBelote.SUIT;
        BidBeloteSuit enchereCouleur_ = new BidBeloteSuit();
        enchereCouleur_.setBid(e);
        enchereCouleur_.setSuit(Suit.SPADE);
        int j_ = (3 + 1) % nombreDeJoueurs_;

        TrickBelote pli_ = new TrickBelote(3);
        pli_.getCartes().ajouter(CardBelote.DIAMOND_1);
        assertEq(3, pli_.getRamasseurPliEnCours(nombreDeJoueurs_, enchereCouleur_));
        pli_.getCartes().ajouter(CardBelote.SPADE_7);
        assertEq(j_, pli_.getRamasseurPliEnCours(nombreDeJoueurs_, enchereCouleur_));
        pli_.getCartes().ajouter(CardBelote.HEART_KING);
        assertEq(j_, pli_.getRamasseurPliEnCours(nombreDeJoueurs_, enchereCouleur_));
        pli_.getCartes().ajouter(CardBelote.DIAMOND_8);
        assertEq(j_, pli_.getRamasseurPliEnCours(nombreDeJoueurs_, enchereCouleur_));
        pli_ = new TrickBelote(3);
        pli_.getCartes().ajouter(CardBelote.DIAMOND_1);
        assertEq(3, pli_.getRamasseurPliEnCours(nombreDeJoueurs_, enchereCouleur_));
        pli_.getCartes().ajouter(CardBelote.SPADE_7);
        assertEq(j_, pli_.getRamasseurPliEnCours(nombreDeJoueurs_, enchereCouleur_));
        pli_.getCartes().ajouter(CardBelote.HEART_KING);
        assertEq(j_, pli_.getRamasseurPliEnCours(nombreDeJoueurs_, enchereCouleur_));
        pli_.getCartes().ajouter(CardBelote.DIAMOND_JACK);
        assertEq(j_, pli_.getRamasseurPliEnCours(nombreDeJoueurs_, enchereCouleur_));
    }
    @Test
    public void getRamasseurPliEnCours_BeloteCouleur9Test(){
        int nombreDeJoueurs_ = 4;
        BidBelote e = BidBelote.SUIT;
        BidBeloteSuit enchereCouleur_ = new BidBeloteSuit();
        enchereCouleur_.setBid(e);
        enchereCouleur_.setSuit(Suit.DIAMOND);
        int jThree_ = 3 % nombreDeJoueurs_;

        TrickBelote pli_ = new TrickBelote(0);
        pli_.getCartes().ajouter(CardBelote.DIAMOND_1);
        assertEq(0, pli_.getRamasseurPliEnCours(nombreDeJoueurs_, enchereCouleur_));
        pli_.getCartes().ajouter(CardBelote.SPADE_7);
        assertEq(0, pli_.getRamasseurPliEnCours(nombreDeJoueurs_, enchereCouleur_));
        pli_.getCartes().ajouter(CardBelote.HEART_KING);
        assertEq(0, pli_.getRamasseurPliEnCours(nombreDeJoueurs_, enchereCouleur_));
        pli_.getCartes().ajouter(CardBelote.DIAMOND_8);
        assertEq(0, pli_.getRamasseurPliEnCours(nombreDeJoueurs_, enchereCouleur_));
        pli_ = new TrickBelote(0);
        pli_.getCartes().ajouter(CardBelote.DIAMOND_1);
        assertEq(0, pli_.getRamasseurPliEnCours(nombreDeJoueurs_, enchereCouleur_));
        pli_.getCartes().ajouter(CardBelote.SPADE_7);
        assertEq(0, pli_.getRamasseurPliEnCours(nombreDeJoueurs_, enchereCouleur_));
        pli_.getCartes().ajouter(CardBelote.HEART_KING);
        assertEq(0, pli_.getRamasseurPliEnCours(nombreDeJoueurs_, enchereCouleur_));
        pli_.getCartes().ajouter(CardBelote.DIAMOND_JACK);
        assertEq(jThree_, pli_.getRamasseurPliEnCours(nombreDeJoueurs_, enchereCouleur_));
    }
    @Test
    public void getRamasseurPliEnCours_BeloteCouleur10Test(){
        int nombreDeJoueurs_ = 4;
        BidBelote e = BidBelote.SUIT;
        BidBeloteSuit enchereCouleur_ = new BidBeloteSuit();
        enchereCouleur_.setBid(e);
        enchereCouleur_.setSuit(Suit.DIAMOND);
        int jThree_ = (1 + 3) % nombreDeJoueurs_;

        TrickBelote pli_ = new TrickBelote(1);
        pli_.getCartes().ajouter(CardBelote.DIAMOND_1);
        assertEq(1, pli_.getRamasseurPliEnCours(nombreDeJoueurs_, enchereCouleur_));
        pli_.getCartes().ajouter(CardBelote.SPADE_7);
        assertEq(1, pli_.getRamasseurPliEnCours(nombreDeJoueurs_, enchereCouleur_));
        pli_.getCartes().ajouter(CardBelote.HEART_KING);
        assertEq(1, pli_.getRamasseurPliEnCours(nombreDeJoueurs_, enchereCouleur_));
        pli_.getCartes().ajouter(CardBelote.DIAMOND_8);
        assertEq(1, pli_.getRamasseurPliEnCours(nombreDeJoueurs_, enchereCouleur_));
        pli_ = new TrickBelote(1);
        pli_.getCartes().ajouter(CardBelote.DIAMOND_1);
        assertEq(1, pli_.getRamasseurPliEnCours(nombreDeJoueurs_, enchereCouleur_));
        pli_.getCartes().ajouter(CardBelote.SPADE_7);
        assertEq(1, pli_.getRamasseurPliEnCours(nombreDeJoueurs_, enchereCouleur_));
        pli_.getCartes().ajouter(CardBelote.HEART_KING);
        assertEq(1, pli_.getRamasseurPliEnCours(nombreDeJoueurs_, enchereCouleur_));
        pli_.getCartes().ajouter(CardBelote.DIAMOND_JACK);
        assertEq(jThree_, pli_.getRamasseurPliEnCours(nombreDeJoueurs_, enchereCouleur_));
    }
    @Test
    public void getRamasseurPliEnCours_BeloteCouleur11Test(){
        int nombreDeJoueurs_ = 4;
        BidBelote e = BidBelote.SUIT;
        BidBeloteSuit enchereCouleur_ = new BidBeloteSuit();
        enchereCouleur_.setBid(e);
        enchereCouleur_.setSuit(Suit.DIAMOND);
        int jThree_ = (2 + 3) % nombreDeJoueurs_;

        TrickBelote pli_ = new TrickBelote(2);
        pli_.getCartes().ajouter(CardBelote.DIAMOND_1);
        assertEq(2, pli_.getRamasseurPliEnCours(nombreDeJoueurs_, enchereCouleur_));
        pli_.getCartes().ajouter(CardBelote.SPADE_7);
        assertEq(2, pli_.getRamasseurPliEnCours(nombreDeJoueurs_, enchereCouleur_));
        pli_.getCartes().ajouter(CardBelote.HEART_KING);
        assertEq(2, pli_.getRamasseurPliEnCours(nombreDeJoueurs_, enchereCouleur_));
        pli_.getCartes().ajouter(CardBelote.DIAMOND_8);
        assertEq(2, pli_.getRamasseurPliEnCours(nombreDeJoueurs_, enchereCouleur_));
        pli_ = new TrickBelote(2);
        pli_.getCartes().ajouter(CardBelote.DIAMOND_1);
        assertEq(2, pli_.getRamasseurPliEnCours(nombreDeJoueurs_, enchereCouleur_));
        pli_.getCartes().ajouter(CardBelote.SPADE_7);
        assertEq(2, pli_.getRamasseurPliEnCours(nombreDeJoueurs_, enchereCouleur_));
        pli_.getCartes().ajouter(CardBelote.HEART_KING);
        assertEq(2, pli_.getRamasseurPliEnCours(nombreDeJoueurs_, enchereCouleur_));
        pli_.getCartes().ajouter(CardBelote.DIAMOND_JACK);
        assertEq(jThree_, pli_.getRamasseurPliEnCours(nombreDeJoueurs_, enchereCouleur_));
    }
    @Test
    public void getRamasseurPliEnCours_BeloteCouleur12Test(){
        int nombreDeJoueurs_ = 4;
        BidBelote e = BidBelote.SUIT;
        BidBeloteSuit enchereCouleur_ = new BidBeloteSuit();
        enchereCouleur_.setBid(e);
        enchereCouleur_.setSuit(Suit.DIAMOND);
        int jThree_ = (3 + 3) % nombreDeJoueurs_;

        TrickBelote pli_ = new TrickBelote(3);
        pli_.getCartes().ajouter(CardBelote.DIAMOND_1);
        assertEq(3, pli_.getRamasseurPliEnCours(nombreDeJoueurs_, enchereCouleur_));
        pli_.getCartes().ajouter(CardBelote.SPADE_7);
        assertEq(3, pli_.getRamasseurPliEnCours(nombreDeJoueurs_, enchereCouleur_));
        pli_.getCartes().ajouter(CardBelote.HEART_KING);
        assertEq(3, pli_.getRamasseurPliEnCours(nombreDeJoueurs_, enchereCouleur_));
        pli_.getCartes().ajouter(CardBelote.DIAMOND_8);
        assertEq(3, pli_.getRamasseurPliEnCours(nombreDeJoueurs_, enchereCouleur_));
        pli_ = new TrickBelote(3);
        pli_.getCartes().ajouter(CardBelote.DIAMOND_1);
        assertEq(3, pli_.getRamasseurPliEnCours(nombreDeJoueurs_, enchereCouleur_));
        pli_.getCartes().ajouter(CardBelote.SPADE_7);
        assertEq(3, pli_.getRamasseurPliEnCours(nombreDeJoueurs_, enchereCouleur_));
        pli_.getCartes().ajouter(CardBelote.HEART_KING);
        assertEq(3, pli_.getRamasseurPliEnCours(nombreDeJoueurs_, enchereCouleur_));
        pli_.getCartes().ajouter(CardBelote.DIAMOND_JACK);
        assertEq(jThree_, pli_.getRamasseurPliEnCours(nombreDeJoueurs_, enchereCouleur_));
    }
    @Test
    public void getRamasseurPliEnCours_BeloteCouleur13Test(){
        int nombreDeJoueurs_ = 4;
        BidBelote e = BidBelote.SUIT;
        BidBeloteSuit enchereCouleur_ = new BidBeloteSuit();
        enchereCouleur_.setBid(e);
        enchereCouleur_.setSuit(Suit.CLUB);

        TrickBelote pli_ = new TrickBelote(0);
        pli_.getCartes().ajouter(CardBelote.DIAMOND_1);
        assertEq(0, pli_.getRamasseurPliEnCours(nombreDeJoueurs_, enchereCouleur_));
        pli_.getCartes().ajouter(CardBelote.SPADE_7);
        assertEq(0, pli_.getRamasseurPliEnCours(nombreDeJoueurs_, enchereCouleur_));
        pli_.getCartes().ajouter(CardBelote.HEART_KING);
        assertEq(0, pli_.getRamasseurPliEnCours(nombreDeJoueurs_, enchereCouleur_));
        pli_.getCartes().ajouter(CardBelote.DIAMOND_8);
        assertEq(0, pli_.getRamasseurPliEnCours(nombreDeJoueurs_, enchereCouleur_));
        pli_ = new TrickBelote(0);
        pli_.getCartes().ajouter(CardBelote.DIAMOND_1);
        assertEq(0, pli_.getRamasseurPliEnCours(nombreDeJoueurs_, enchereCouleur_));
        pli_.getCartes().ajouter(CardBelote.SPADE_7);
        assertEq(0, pli_.getRamasseurPliEnCours(nombreDeJoueurs_, enchereCouleur_));
        pli_.getCartes().ajouter(CardBelote.HEART_KING);
        assertEq(0, pli_.getRamasseurPliEnCours(nombreDeJoueurs_, enchereCouleur_));
        pli_.getCartes().ajouter(CardBelote.DIAMOND_JACK);
        assertEq(0, pli_.getRamasseurPliEnCours(nombreDeJoueurs_, enchereCouleur_));
    }
    @Test
    public void getRamasseurPliEnCours_BeloteCouleur14Test(){
        int nombreDeJoueurs_ = 4;
        BidBelote e = BidBelote.SUIT;
        BidBeloteSuit enchereCouleur_ = new BidBeloteSuit();
        enchereCouleur_.setBid(e);
        enchereCouleur_.setSuit(Suit.CLUB);

        TrickBelote pli_ = new TrickBelote(1);
        pli_.getCartes().ajouter(CardBelote.DIAMOND_1);
        assertEq(1, pli_.getRamasseurPliEnCours(nombreDeJoueurs_, enchereCouleur_));
        pli_.getCartes().ajouter(CardBelote.SPADE_7);
        assertEq(1, pli_.getRamasseurPliEnCours(nombreDeJoueurs_, enchereCouleur_));
        pli_.getCartes().ajouter(CardBelote.HEART_KING);
        assertEq(1, pli_.getRamasseurPliEnCours(nombreDeJoueurs_, enchereCouleur_));
        pli_.getCartes().ajouter(CardBelote.DIAMOND_8);
        assertEq(1, pli_.getRamasseurPliEnCours(nombreDeJoueurs_, enchereCouleur_));
        pli_ = new TrickBelote(1);
        pli_.getCartes().ajouter(CardBelote.DIAMOND_1);
        assertEq(1, pli_.getRamasseurPliEnCours(nombreDeJoueurs_, enchereCouleur_));
        pli_.getCartes().ajouter(CardBelote.SPADE_7);
        assertEq(1, pli_.getRamasseurPliEnCours(nombreDeJoueurs_, enchereCouleur_));
        pli_.getCartes().ajouter(CardBelote.HEART_KING);
        assertEq(1, pli_.getRamasseurPliEnCours(nombreDeJoueurs_, enchereCouleur_));
        pli_.getCartes().ajouter(CardBelote.DIAMOND_JACK);
        assertEq(1, pli_.getRamasseurPliEnCours(nombreDeJoueurs_, enchereCouleur_));
    }
    @Test
    public void getRamasseurPliEnCours_BeloteCouleur15Test(){
        int nombreDeJoueurs_ = 4;
        BidBelote e = BidBelote.SUIT;
        BidBeloteSuit enchereCouleur_ = new BidBeloteSuit();
        enchereCouleur_.setBid(e);
        enchereCouleur_.setSuit(Suit.CLUB);

        TrickBelote pli_ = new TrickBelote(2);
        pli_.getCartes().ajouter(CardBelote.DIAMOND_1);
        assertEq(2, pli_.getRamasseurPliEnCours(nombreDeJoueurs_, enchereCouleur_));
        pli_.getCartes().ajouter(CardBelote.SPADE_7);
        assertEq(2, pli_.getRamasseurPliEnCours(nombreDeJoueurs_, enchereCouleur_));
        pli_.getCartes().ajouter(CardBelote.HEART_KING);
        assertEq(2, pli_.getRamasseurPliEnCours(nombreDeJoueurs_, enchereCouleur_));
        pli_.getCartes().ajouter(CardBelote.DIAMOND_8);
        assertEq(2, pli_.getRamasseurPliEnCours(nombreDeJoueurs_, enchereCouleur_));
        pli_ = new TrickBelote(2);
        pli_.getCartes().ajouter(CardBelote.DIAMOND_1);
        assertEq(2, pli_.getRamasseurPliEnCours(nombreDeJoueurs_, enchereCouleur_));
        pli_.getCartes().ajouter(CardBelote.SPADE_7);
        assertEq(2, pli_.getRamasseurPliEnCours(nombreDeJoueurs_, enchereCouleur_));
        pli_.getCartes().ajouter(CardBelote.HEART_KING);
        assertEq(2, pli_.getRamasseurPliEnCours(nombreDeJoueurs_, enchereCouleur_));
        pli_.getCartes().ajouter(CardBelote.DIAMOND_JACK);
        assertEq(2, pli_.getRamasseurPliEnCours(nombreDeJoueurs_, enchereCouleur_));
    }
    @Test
    public void getRamasseurPliEnCours_BeloteCouleur16Test(){
        int nombreDeJoueurs_ = 4;
        BidBelote e = BidBelote.SUIT;
        BidBeloteSuit enchereCouleur_ = new BidBeloteSuit();
        enchereCouleur_.setBid(e);
        enchereCouleur_.setSuit(Suit.CLUB);

        TrickBelote pli_ = new TrickBelote(3);
        pli_.getCartes().ajouter(CardBelote.DIAMOND_1);
        assertEq(3, pli_.getRamasseurPliEnCours(nombreDeJoueurs_, enchereCouleur_));
        pli_.getCartes().ajouter(CardBelote.SPADE_7);
        assertEq(3, pli_.getRamasseurPliEnCours(nombreDeJoueurs_, enchereCouleur_));
        pli_.getCartes().ajouter(CardBelote.HEART_KING);
        assertEq(3, pli_.getRamasseurPliEnCours(nombreDeJoueurs_, enchereCouleur_));
        pli_.getCartes().ajouter(CardBelote.DIAMOND_8);
        assertEq(3, pli_.getRamasseurPliEnCours(nombreDeJoueurs_, enchereCouleur_));
        pli_ = new TrickBelote(3);
        pli_.getCartes().ajouter(CardBelote.DIAMOND_1);
        assertEq(3, pli_.getRamasseurPliEnCours(nombreDeJoueurs_, enchereCouleur_));
        pli_.getCartes().ajouter(CardBelote.SPADE_7);
        assertEq(3, pli_.getRamasseurPliEnCours(nombreDeJoueurs_, enchereCouleur_));
        pli_.getCartes().ajouter(CardBelote.HEART_KING);
        assertEq(3, pli_.getRamasseurPliEnCours(nombreDeJoueurs_, enchereCouleur_));
        pli_.getCartes().ajouter(CardBelote.DIAMOND_JACK);
        assertEq(3, pli_.getRamasseurPliEnCours(nombreDeJoueurs_, enchereCouleur_));
    }
    @Test
    public void getRamasseur_BeloteToutAtout1Test(){
        BidBelote enchere_ = BidBelote.ALL_TRUMP;
        BidBeloteSuit enchereCouleur_ = new BidBeloteSuit();
        enchereCouleur_.setBid(enchere_);
        int nombreDeJoueurs_ = 4;
        TrickBelote pli_ = new TrickBelote(0);
        pli_.getCartes().ajouter(CardBelote.DIAMOND_1);
        pli_.getCartes().ajouter(CardBelote.SPADE_7);
        pli_.getCartes().ajouter(CardBelote.HEART_KING);
        pli_.getCartes().ajouter(CardBelote.DIAMOND_JACK);
        int j_ = 3 % nombreDeJoueurs_;
        assertEq(j_, pli_.getRamasseur(enchereCouleur_));
    }
    @Test
    public void getRamasseur_BeloteToutAtout2Test(){
        BidBelote enchere_ = BidBelote.ALL_TRUMP;
        BidBeloteSuit enchereCouleur_ = new BidBeloteSuit();
        enchereCouleur_.setBid(enchere_);
        int nombreDeJoueurs_ = 4;
        TrickBelote pli_ = new TrickBelote(1);
        pli_.getCartes().ajouter(CardBelote.DIAMOND_1);
        pli_.getCartes().ajouter(CardBelote.SPADE_7);
        pli_.getCartes().ajouter(CardBelote.HEART_KING);
        pli_.getCartes().ajouter(CardBelote.DIAMOND_JACK);
        int j_ = (1 + 3) % nombreDeJoueurs_;
        assertEq(j_, pli_.getRamasseur(enchereCouleur_));
    }
    @Test
    public void getRamasseur_BeloteToutAtout3Test(){
        BidBelote enchere_ = BidBelote.ALL_TRUMP;
        BidBeloteSuit enchereCouleur_ = new BidBeloteSuit();
        enchereCouleur_.setBid(enchere_);
        int nombreDeJoueurs_ = 4;
        TrickBelote pli_ = new TrickBelote(2);
        pli_.getCartes().ajouter(CardBelote.DIAMOND_1);
        pli_.getCartes().ajouter(CardBelote.SPADE_7);
        pli_.getCartes().ajouter(CardBelote.HEART_KING);
        pli_.getCartes().ajouter(CardBelote.DIAMOND_JACK);
        int j_ = (2 + 3) % nombreDeJoueurs_;
        assertEq(j_, pli_.getRamasseur(enchereCouleur_));
    }
    @Test
    public void getRamasseur_BeloteToutAtout4Test(){
        BidBelote enchere_ = BidBelote.ALL_TRUMP;
        BidBeloteSuit enchereCouleur_ = new BidBeloteSuit();
        enchereCouleur_.setBid(enchere_);
        int nombreDeJoueurs_ = 4;
        TrickBelote pli_ = new TrickBelote(3);
        pli_.getCartes().ajouter(CardBelote.DIAMOND_1);
        pli_.getCartes().ajouter(CardBelote.SPADE_7);
        pli_.getCartes().ajouter(CardBelote.HEART_KING);
        pli_.getCartes().ajouter(CardBelote.DIAMOND_JACK);
        int j_ = (3 + 3) % nombreDeJoueurs_;
        assertEq(j_, pli_.getRamasseur(enchereCouleur_));
    }
    @Test
    public void getRamasseur_BeloteSansAtout1Test(){
        BidBelote enchere_ = BidBelote.NO_TRUMP;
        BidBeloteSuit enchereCouleur_ = new BidBeloteSuit();
        enchereCouleur_.setBid(enchere_);
        TrickBelote pli_ = new TrickBelote(0);
        pli_.getCartes().ajouter(CardBelote.DIAMOND_1);
        pli_.getCartes().ajouter(CardBelote.SPADE_7);
        pli_.getCartes().ajouter(CardBelote.HEART_KING);
        pli_.getCartes().ajouter(CardBelote.DIAMOND_JACK);
        assertEq(0, pli_.getRamasseur(enchereCouleur_));
    }
    @Test
    public void getRamasseur_BeloteSansAtout2Test(){
        BidBelote enchere_ = BidBelote.NO_TRUMP;
        BidBeloteSuit enchereCouleur_ = new BidBeloteSuit();
        enchereCouleur_.setBid(enchere_);
        TrickBelote pli_ = new TrickBelote(1);
        pli_.getCartes().ajouter(CardBelote.DIAMOND_1);
        pli_.getCartes().ajouter(CardBelote.SPADE_7);
        pli_.getCartes().ajouter(CardBelote.HEART_KING);
        pli_.getCartes().ajouter(CardBelote.DIAMOND_JACK);
        assertEq(1, pli_.getRamasseur(enchereCouleur_));
    }
    @Test
    public void getRamasseur_BeloteSansAtout3Test(){
        BidBelote enchere_ = BidBelote.NO_TRUMP;
        BidBeloteSuit enchereCouleur_ = new BidBeloteSuit();
        enchereCouleur_.setBid(enchere_);
        TrickBelote pli_ = new TrickBelote(2);
        pli_.getCartes().ajouter(CardBelote.DIAMOND_1);
        pli_.getCartes().ajouter(CardBelote.SPADE_7);
        pli_.getCartes().ajouter(CardBelote.HEART_KING);
        pli_.getCartes().ajouter(CardBelote.DIAMOND_JACK);
        assertEq(2, pli_.getRamasseur(enchereCouleur_));
    }
    @Test
    public void getRamasseur_BeloteSansAtout4Test(){
        BidBelote enchere_ = BidBelote.NO_TRUMP;
        BidBeloteSuit enchereCouleur_ = new BidBeloteSuit();
        enchereCouleur_.setBid(enchere_);
        TrickBelote pli_ = new TrickBelote(3);
        pli_.getCartes().ajouter(CardBelote.DIAMOND_1);
        pli_.getCartes().ajouter(CardBelote.SPADE_7);
        pli_.getCartes().ajouter(CardBelote.HEART_KING);
        pli_.getCartes().ajouter(CardBelote.DIAMOND_JACK);
        assertEq(3, pli_.getRamasseur(enchereCouleur_));
    }
    @Test
    public void getRamasseur_BeloteCouleur1Test(){
        int nombreDeJoueurs_ = 4;
        BidBelote e = BidBelote.SUIT;
        BidBeloteSuit enchereCouleur_ = new BidBeloteSuit();
        enchereCouleur_.setBid(e);
        enchereCouleur_.setSuit(Suit.HEART);
        int jTwo_ = 2 % nombreDeJoueurs_;

        TrickBelote pli_ = new TrickBelote(0);
        pli_.getCartes().ajouter(CardBelote.DIAMOND_1);
        pli_.getCartes().ajouter(CardBelote.SPADE_7);
        pli_.getCartes().ajouter(CardBelote.HEART_KING);
        pli_.getCartes().ajouter(CardBelote.DIAMOND_8);
        assertEq(jTwo_, pli_.getRamasseur(enchereCouleur_));
        pli_ = new TrickBelote(0);
        pli_.getCartes().ajouter(CardBelote.DIAMOND_1);
        pli_.getCartes().ajouter(CardBelote.SPADE_7);
        pli_.getCartes().ajouter(CardBelote.HEART_KING);
        pli_.getCartes().ajouter(CardBelote.DIAMOND_JACK);
        assertEq(jTwo_, pli_.getRamasseur(enchereCouleur_));
    }
    @Test
    public void getRamasseur_BeloteCouleur2Test(){
        int nombreDeJoueurs_ = 4;
        BidBelote e = BidBelote.SUIT;
        BidBeloteSuit enchereCouleur_ = new BidBeloteSuit();
        enchereCouleur_.setBid(e);
        enchereCouleur_.setSuit(Suit.HEART);
        int jTwo_ = (1 + 2) % nombreDeJoueurs_;

        TrickBelote pli_ = new TrickBelote(1);
        pli_.getCartes().ajouter(CardBelote.DIAMOND_1);
        pli_.getCartes().ajouter(CardBelote.SPADE_7);
        pli_.getCartes().ajouter(CardBelote.HEART_KING);
        pli_.getCartes().ajouter(CardBelote.DIAMOND_8);
        assertEq(jTwo_, pli_.getRamasseur(enchereCouleur_));
        pli_ = new TrickBelote(1);
        pli_.getCartes().ajouter(CardBelote.DIAMOND_1);
        pli_.getCartes().ajouter(CardBelote.SPADE_7);
        pli_.getCartes().ajouter(CardBelote.HEART_KING);
        pli_.getCartes().ajouter(CardBelote.DIAMOND_JACK);
        assertEq(jTwo_, pli_.getRamasseur(enchereCouleur_));
    }
    @Test
    public void getRamasseur_BeloteCouleur3Test(){
        int nombreDeJoueurs_ = 4;
        BidBelote e = BidBelote.SUIT;
        BidBeloteSuit enchereCouleur_ = new BidBeloteSuit();
        enchereCouleur_.setBid(e);
        enchereCouleur_.setSuit(Suit.HEART);
        int jTwo_ = (2 + 2) % nombreDeJoueurs_;

        TrickBelote pli_ = new TrickBelote(2);
        pli_.getCartes().ajouter(CardBelote.DIAMOND_1);
        pli_.getCartes().ajouter(CardBelote.SPADE_7);
        pli_.getCartes().ajouter(CardBelote.HEART_KING);
        pli_.getCartes().ajouter(CardBelote.DIAMOND_8);
        assertEq(jTwo_, pli_.getRamasseur(enchereCouleur_));
        pli_ = new TrickBelote(2);
        pli_.getCartes().ajouter(CardBelote.DIAMOND_1);
        pli_.getCartes().ajouter(CardBelote.SPADE_7);
        pli_.getCartes().ajouter(CardBelote.HEART_KING);
        pli_.getCartes().ajouter(CardBelote.DIAMOND_JACK);
        assertEq(jTwo_, pli_.getRamasseur(enchereCouleur_));
    }
    @Test
    public void getRamasseur_BeloteCouleur4Test(){
        int nombreDeJoueurs_ = 4;
        BidBelote e = BidBelote.SUIT;
        BidBeloteSuit enchereCouleur_ = new BidBeloteSuit();
        enchereCouleur_.setBid(e);
        enchereCouleur_.setSuit(Suit.HEART);
        int jTwo_ = (3 + 2) % nombreDeJoueurs_;

        TrickBelote pli_ = new TrickBelote(3);
        pli_.getCartes().ajouter(CardBelote.DIAMOND_1);
        pli_.getCartes().ajouter(CardBelote.SPADE_7);
        pli_.getCartes().ajouter(CardBelote.HEART_KING);
        pli_.getCartes().ajouter(CardBelote.DIAMOND_8);
        assertEq(jTwo_, pli_.getRamasseur(enchereCouleur_));
        pli_ = new TrickBelote(3);
        pli_.getCartes().ajouter(CardBelote.DIAMOND_1);
        pli_.getCartes().ajouter(CardBelote.SPADE_7);
        pli_.getCartes().ajouter(CardBelote.HEART_KING);
        pli_.getCartes().ajouter(CardBelote.DIAMOND_JACK);
        assertEq(jTwo_, pli_.getRamasseur(enchereCouleur_));
    }
    @Test
    public void getRamasseur_BeloteCouleur5Test(){
        int nombreDeJoueurs_ = 4;
        BidBelote e = BidBelote.SUIT;
        BidBeloteSuit enchereCouleur_ = new BidBeloteSuit();
        enchereCouleur_.setBid(e);
        enchereCouleur_.setSuit(Suit.SPADE);
        int j_ = 1 % nombreDeJoueurs_;

        TrickBelote pli_ = new TrickBelote(0);
        pli_.getCartes().ajouter(CardBelote.DIAMOND_1);
        pli_.getCartes().ajouter(CardBelote.SPADE_7);
        pli_.getCartes().ajouter(CardBelote.HEART_KING);
        pli_.getCartes().ajouter(CardBelote.DIAMOND_8);
        assertEq(j_, pli_.getRamasseur(enchereCouleur_));
        pli_ = new TrickBelote(0);
        pli_.getCartes().ajouter(CardBelote.DIAMOND_1);
        pli_.getCartes().ajouter(CardBelote.SPADE_7);
        pli_.getCartes().ajouter(CardBelote.HEART_KING);
        pli_.getCartes().ajouter(CardBelote.DIAMOND_JACK);
        assertEq(j_, pli_.getRamasseur(enchereCouleur_));
    }
    @Test
    public void getRamasseur_BeloteCouleur6Test(){
        int nombreDeJoueurs_ = 4;
        BidBelote e = BidBelote.SUIT;
        BidBeloteSuit enchereCouleur_ = new BidBeloteSuit();
        enchereCouleur_.setBid(e);
        enchereCouleur_.setSuit(Suit.SPADE);
        int j_ = (1 + 1) % nombreDeJoueurs_;

        TrickBelote pli_ = new TrickBelote(1);
        pli_.getCartes().ajouter(CardBelote.DIAMOND_1);
        pli_.getCartes().ajouter(CardBelote.SPADE_7);
        pli_.getCartes().ajouter(CardBelote.HEART_KING);
        pli_.getCartes().ajouter(CardBelote.DIAMOND_8);
        assertEq(j_, pli_.getRamasseur(enchereCouleur_));
        pli_ = new TrickBelote(1);
        pli_.getCartes().ajouter(CardBelote.DIAMOND_1);
        pli_.getCartes().ajouter(CardBelote.SPADE_7);
        pli_.getCartes().ajouter(CardBelote.HEART_KING);
        pli_.getCartes().ajouter(CardBelote.DIAMOND_JACK);
        assertEq(j_, pli_.getRamasseur(enchereCouleur_));
    }
    @Test
    public void getRamasseur_BeloteCouleur7Test(){
        int nombreDeJoueurs_ = 4;
        BidBelote e = BidBelote.SUIT;
        BidBeloteSuit enchereCouleur_ = new BidBeloteSuit();
        enchereCouleur_.setBid(e);
        enchereCouleur_.setSuit(Suit.SPADE);
        int j_ = (2 + 1) % nombreDeJoueurs_;

        TrickBelote pli_ = new TrickBelote(2);
        pli_.getCartes().ajouter(CardBelote.DIAMOND_1);
        pli_.getCartes().ajouter(CardBelote.SPADE_7);
        pli_.getCartes().ajouter(CardBelote.HEART_KING);
        pli_.getCartes().ajouter(CardBelote.DIAMOND_8);
        assertEq(j_, pli_.getRamasseur(enchereCouleur_));
        pli_ = new TrickBelote(2);
        pli_.getCartes().ajouter(CardBelote.DIAMOND_1);
        pli_.getCartes().ajouter(CardBelote.SPADE_7);
        pli_.getCartes().ajouter(CardBelote.HEART_KING);
        pli_.getCartes().ajouter(CardBelote.DIAMOND_JACK);
        assertEq(j_, pli_.getRamasseur(enchereCouleur_));
    }
    @Test
    public void getRamasseur_BeloteCouleur8Test(){
        int nombreDeJoueurs_ = 4;
        BidBelote e = BidBelote.SUIT;
        BidBeloteSuit enchereCouleur_ = new BidBeloteSuit();
        enchereCouleur_.setBid(e);
        enchereCouleur_.setSuit(Suit.SPADE);
        int j_ = (3 + 1) % nombreDeJoueurs_;

        TrickBelote pli_ = new TrickBelote(3);
        pli_.getCartes().ajouter(CardBelote.DIAMOND_1);
        pli_.getCartes().ajouter(CardBelote.SPADE_7);
        pli_.getCartes().ajouter(CardBelote.HEART_KING);
        pli_.getCartes().ajouter(CardBelote.DIAMOND_8);
        assertEq(j_, pli_.getRamasseur(enchereCouleur_));
        pli_ = new TrickBelote(3);
        pli_.getCartes().ajouter(CardBelote.DIAMOND_1);
        pli_.getCartes().ajouter(CardBelote.SPADE_7);
        pli_.getCartes().ajouter(CardBelote.HEART_KING);
        pli_.getCartes().ajouter(CardBelote.DIAMOND_JACK);
        assertEq(j_, pli_.getRamasseur(enchereCouleur_));
    }
    @Test
    public void getRamasseur_BeloteCouleur9Test(){
        int nombreDeJoueurs_ = 4;
        BidBelote e = BidBelote.SUIT;
        BidBeloteSuit enchereCouleur_ = new BidBeloteSuit();
        enchereCouleur_.setBid(e);
        enchereCouleur_.setSuit(Suit.DIAMOND);
        int jThree_ = 3 % nombreDeJoueurs_;

        TrickBelote pli_ = new TrickBelote(0);
        pli_.getCartes().ajouter(CardBelote.DIAMOND_1);
        pli_.getCartes().ajouter(CardBelote.SPADE_7);
        pli_.getCartes().ajouter(CardBelote.HEART_KING);
        pli_.getCartes().ajouter(CardBelote.DIAMOND_8);
        assertEq(0, pli_.getRamasseur(enchereCouleur_));
        pli_ = new TrickBelote(0);
        pli_.getCartes().ajouter(CardBelote.DIAMOND_1);
        pli_.getCartes().ajouter(CardBelote.SPADE_7);
        pli_.getCartes().ajouter(CardBelote.HEART_KING);
        pli_.getCartes().ajouter(CardBelote.DIAMOND_JACK);
        assertEq(jThree_, pli_.getRamasseur(enchereCouleur_));
    }
    @Test
    public void getRamasseur_BeloteCouleur10Test(){
        int nombreDeJoueurs_ = 4;
        BidBelote e = BidBelote.SUIT;
        BidBeloteSuit enchereCouleur_ = new BidBeloteSuit();
        enchereCouleur_.setBid(e);
        enchereCouleur_.setSuit(Suit.DIAMOND);
        int jThree_ = (1 + 3) % nombreDeJoueurs_;

        TrickBelote pli_ = new TrickBelote(1);
        pli_.getCartes().ajouter(CardBelote.DIAMOND_1);
        pli_.getCartes().ajouter(CardBelote.SPADE_7);
        pli_.getCartes().ajouter(CardBelote.HEART_KING);
        pli_.getCartes().ajouter(CardBelote.DIAMOND_8);
        assertEq(1, pli_.getRamasseur(enchereCouleur_));
        pli_ = new TrickBelote(1);
        pli_.getCartes().ajouter(CardBelote.DIAMOND_1);
        pli_.getCartes().ajouter(CardBelote.SPADE_7);
        pli_.getCartes().ajouter(CardBelote.HEART_KING);
        pli_.getCartes().ajouter(CardBelote.DIAMOND_JACK);
        assertEq(jThree_, pli_.getRamasseur(enchereCouleur_));
    }
    @Test
    public void getRamasseur_BeloteCouleur11Test(){
        int nombreDeJoueurs_ = 4;
        BidBelote e = BidBelote.SUIT;
        BidBeloteSuit enchereCouleur_ = new BidBeloteSuit();
        enchereCouleur_.setBid(e);
        enchereCouleur_.setSuit(Suit.DIAMOND);
        int jThree_ = (2 + 3) % nombreDeJoueurs_;

        TrickBelote pli_ = new TrickBelote(2);
        pli_.getCartes().ajouter(CardBelote.DIAMOND_1);
        pli_.getCartes().ajouter(CardBelote.SPADE_7);
        pli_.getCartes().ajouter(CardBelote.HEART_KING);
        pli_.getCartes().ajouter(CardBelote.DIAMOND_8);
        assertEq(2, pli_.getRamasseur(enchereCouleur_));
        pli_ = new TrickBelote(2);
        pli_.getCartes().ajouter(CardBelote.DIAMOND_1);
        pli_.getCartes().ajouter(CardBelote.SPADE_7);
        pli_.getCartes().ajouter(CardBelote.HEART_KING);
        pli_.getCartes().ajouter(CardBelote.DIAMOND_JACK);
        assertEq(jThree_, pli_.getRamasseur(enchereCouleur_));
    }
    @Test
    public void getRamasseur_BeloteCouleur12Test(){
        int nombreDeJoueurs_ = 4;
        BidBelote e = BidBelote.SUIT;
        BidBeloteSuit enchereCouleur_ = new BidBeloteSuit();
        enchereCouleur_.setBid(e);
        enchereCouleur_.setSuit(Suit.DIAMOND);
        int jThree_ = (3 + 3) % nombreDeJoueurs_;

        TrickBelote pli_ = new TrickBelote(3);
        pli_.getCartes().ajouter(CardBelote.DIAMOND_1);
        pli_.getCartes().ajouter(CardBelote.SPADE_7);
        pli_.getCartes().ajouter(CardBelote.HEART_KING);
        pli_.getCartes().ajouter(CardBelote.DIAMOND_8);
        assertEq(3, pli_.getRamasseur(enchereCouleur_));
        pli_ = new TrickBelote(3);
        pli_.getCartes().ajouter(CardBelote.DIAMOND_1);
        pli_.getCartes().ajouter(CardBelote.SPADE_7);
        pli_.getCartes().ajouter(CardBelote.HEART_KING);
        pli_.getCartes().ajouter(CardBelote.DIAMOND_JACK);
        assertEq(jThree_, pli_.getRamasseur(enchereCouleur_));
    }
    @Test
    public void getRamasseur_BeloteCouleur13Test(){
        BidBelote e = BidBelote.SUIT;
        BidBeloteSuit enchereCouleur_ = new BidBeloteSuit();
        enchereCouleur_.setBid(e);
        enchereCouleur_.setSuit(Suit.CLUB);

        TrickBelote pli_ = new TrickBelote(0);
        pli_.getCartes().ajouter(CardBelote.DIAMOND_1);
        pli_.getCartes().ajouter(CardBelote.SPADE_7);
        pli_.getCartes().ajouter(CardBelote.HEART_KING);
        pli_.getCartes().ajouter(CardBelote.DIAMOND_8);
        assertEq(0, pli_.getRamasseur(enchereCouleur_));
        pli_ = new TrickBelote(0);
        pli_.getCartes().ajouter(CardBelote.DIAMOND_1);
        pli_.getCartes().ajouter(CardBelote.SPADE_7);
        pli_.getCartes().ajouter(CardBelote.HEART_KING);
        pli_.getCartes().ajouter(CardBelote.DIAMOND_JACK);
        assertEq(0, pli_.getRamasseur(enchereCouleur_));
    }
    @Test
    public void getRamasseur_BeloteCouleur14Test(){
        BidBelote e = BidBelote.SUIT;
        BidBeloteSuit enchereCouleur_ = new BidBeloteSuit();
        enchereCouleur_.setBid(e);
        enchereCouleur_.setSuit(Suit.CLUB);

        TrickBelote pli_ = new TrickBelote(1);
        pli_.getCartes().ajouter(CardBelote.DIAMOND_1);
        pli_.getCartes().ajouter(CardBelote.SPADE_7);
        pli_.getCartes().ajouter(CardBelote.HEART_KING);
        pli_.getCartes().ajouter(CardBelote.DIAMOND_8);
        assertEq(1, pli_.getRamasseur(enchereCouleur_));
        pli_ = new TrickBelote(1);
        pli_.getCartes().ajouter(CardBelote.DIAMOND_1);
        pli_.getCartes().ajouter(CardBelote.SPADE_7);
        pli_.getCartes().ajouter(CardBelote.HEART_KING);
        pli_.getCartes().ajouter(CardBelote.DIAMOND_JACK);
        assertEq(1, pli_.getRamasseur(enchereCouleur_));
    }
    @Test
    public void getRamasseur_BeloteCouleur15Test(){
        BidBelote e = BidBelote.SUIT;
        BidBeloteSuit enchereCouleur_ = new BidBeloteSuit();
        enchereCouleur_.setBid(e);
        enchereCouleur_.setSuit(Suit.CLUB);

        TrickBelote pli_ = new TrickBelote(2);
        pli_.getCartes().ajouter(CardBelote.DIAMOND_1);
        pli_.getCartes().ajouter(CardBelote.SPADE_7);
        pli_.getCartes().ajouter(CardBelote.HEART_KING);
        pli_.getCartes().ajouter(CardBelote.DIAMOND_8);
        assertEq(2, pli_.getRamasseur(enchereCouleur_));
        pli_ = new TrickBelote(2);
        pli_.getCartes().ajouter(CardBelote.DIAMOND_1);
        pli_.getCartes().ajouter(CardBelote.SPADE_7);
        pli_.getCartes().ajouter(CardBelote.HEART_KING);
        pli_.getCartes().ajouter(CardBelote.DIAMOND_JACK);
        assertEq(2, pli_.getRamasseur(enchereCouleur_));
    }
    @Test
    public void getRamasseur_BeloteCouleur16Test(){
        BidBelote e = BidBelote.SUIT;
        BidBeloteSuit enchereCouleur_ = new BidBeloteSuit();
        enchereCouleur_.setBid(e);
        enchereCouleur_.setSuit(Suit.CLUB);

        TrickBelote pli_ = new TrickBelote(3);
        pli_.getCartes().ajouter(CardBelote.DIAMOND_1);
        pli_.getCartes().ajouter(CardBelote.SPADE_7);
        pli_.getCartes().ajouter(CardBelote.HEART_KING);
        pli_.getCartes().ajouter(CardBelote.DIAMOND_8);
        assertEq(3, pli_.getRamasseur(enchereCouleur_));
        pli_ = new TrickBelote(3);
        pli_.getCartes().ajouter(CardBelote.DIAMOND_1);
        pli_.getCartes().ajouter(CardBelote.SPADE_7);
        pli_.getCartes().ajouter(CardBelote.HEART_KING);
        pli_.getCartes().ajouter(CardBelote.DIAMOND_JACK);
        assertEq(3, pli_.getRamasseur(enchereCouleur_));
    }
    @Test
    public void joueursCoupes1(){
        TrickBelote pli_ = new TrickBelote(0);
        pli_.getCartes().ajouter(CardBelote.DIAMOND_1);
        assertEq(0,pli_.joueursCoupes(Suit.UNDEFINED).size());
        pli_.getCartes().ajouter(CardBelote.SPADE_7);
        assertEq(0,pli_.joueursCoupes(Suit.UNDEFINED).size());
        pli_.getCartes().ajouter(CardBelote.HEART_KING);
        assertEq(0,pli_.joueursCoupes(Suit.UNDEFINED).size());
        pli_.getCartes().ajouter(CardBelote.DIAMOND_JACK);
        assertEq(0,pli_.joueursCoupes(Suit.UNDEFINED).size());
        pli_ = new TrickBelote(1);
        //nombreTotal++;
        pli_.getCartes().ajouter(CardBelote.DIAMOND_1);
        assertEq(0,pli_.joueursCoupes(Suit.UNDEFINED).size());
        pli_.getCartes().ajouter(CardBelote.SPADE_7);
        assertEq(0,pli_.joueursCoupes(Suit.UNDEFINED).size());
        pli_.getCartes().ajouter(CardBelote.HEART_KING);
        assertEq(0,pli_.joueursCoupes(Suit.UNDEFINED).size());
        pli_.getCartes().ajouter(CardBelote.DIAMOND_JACK);
        assertEq(0,pli_.joueursCoupes(Suit.UNDEFINED).size());
        pli_ = new TrickBelote(2);
        //nombreTotal++;
        pli_.getCartes().ajouter(CardBelote.DIAMOND_1);
        assertEq(0,pli_.joueursCoupes(Suit.UNDEFINED).size());
        pli_.getCartes().ajouter(CardBelote.SPADE_7);
        assertEq(0,pli_.joueursCoupes(Suit.UNDEFINED).size());
        pli_.getCartes().ajouter(CardBelote.HEART_KING);
        assertEq(0,pli_.joueursCoupes(Suit.UNDEFINED).size());
        pli_.getCartes().ajouter(CardBelote.DIAMOND_JACK);
        assertEq(0,pli_.joueursCoupes(Suit.UNDEFINED).size());
        pli_ = new TrickBelote(3);
        //nombreTotal++;
        pli_.getCartes().ajouter(CardBelote.DIAMOND_1);
        assertEq(0,pli_.joueursCoupes(Suit.UNDEFINED).size());
        pli_.getCartes().ajouter(CardBelote.SPADE_7);
        assertEq(0,pli_.joueursCoupes(Suit.UNDEFINED).size());
        pli_.getCartes().ajouter(CardBelote.HEART_KING);
        assertEq(0,pli_.joueursCoupes(Suit.UNDEFINED).size());
        pli_.getCartes().ajouter(CardBelote.DIAMOND_JACK);
        assertEq(0,pli_.joueursCoupes(Suit.UNDEFINED).size());
    }
    @Test
    public void joueursCoupes2(){
        int nombreDeJoueurs_ = 4;
        TrickBelote pli_ = new TrickBelote(0);
        //nombreTotal++;
        pli_.getCartes().ajouter(CardBelote.DIAMOND_1);
        pli_.getCartes().ajouter(CardBelote.SPADE_7);
        pli_.getCartes().ajouter(CardBelote.HEART_KING);
        pli_.getCartes().ajouter(CardBelote.DIAMOND_8);
        Ints joueurs_ = pli_.joueursCoupes(Suit.HEART);
        int j_ = 2 % nombreDeJoueurs_;
        assertEq(1, joueurs_.size());
        assertTrue(joueurs_.containsObj(j_));
        pli_ = new TrickBelote(1);
        //nombreTotal++;
        pli_.getCartes().ajouter(CardBelote.DIAMOND_1);
        pli_.getCartes().ajouter(CardBelote.SPADE_7);
        pli_.getCartes().ajouter(CardBelote.HEART_KING);
        pli_.getCartes().ajouter(CardBelote.DIAMOND_8);
        joueurs_ = pli_.joueursCoupes(Suit.HEART);
        j_ = (1 + 2) % nombreDeJoueurs_;
        assertEq(1, joueurs_.size());
        assertTrue(joueurs_.containsObj(j_));
        pli_ = new TrickBelote(2);
        //nombreTotal++;
        pli_.getCartes().ajouter(CardBelote.DIAMOND_1);
        pli_.getCartes().ajouter(CardBelote.SPADE_7);
        pli_.getCartes().ajouter(CardBelote.HEART_KING);
        pli_.getCartes().ajouter(CardBelote.DIAMOND_8);
        joueurs_ = pli_.joueursCoupes(Suit.HEART);
        j_ = (2 + 2) % nombreDeJoueurs_;
        assertEq(1, joueurs_.size());
        assertTrue(joueurs_.containsObj(j_));
        pli_ = new TrickBelote(3);
        //nombreTotal++;
        pli_.getCartes().ajouter(CardBelote.DIAMOND_1);
        pli_.getCartes().ajouter(CardBelote.SPADE_7);
        pli_.getCartes().ajouter(CardBelote.HEART_KING);
        pli_.getCartes().ajouter(CardBelote.DIAMOND_8);
        joueurs_ = pli_.joueursCoupes(Suit.HEART);
        j_ = (3 + 2) % nombreDeJoueurs_;
        assertEq(1, joueurs_.size());
        assertTrue(joueurs_.containsObj(j_));
    }
    @Test
    public void joueursDefausses1(){
        int nombreDeJoueurs_ = 4;
        TrickBelote pli_ = new TrickBelote(0);
        //nombreTotal++;
        pli_.getCartes().ajouter(CardBelote.DIAMOND_1);
        pli_.getCartes().ajouter(CardBelote.SPADE_7);
        pli_.getCartes().ajouter(CardBelote.HEART_KING);
        pli_.getCartes().ajouter(CardBelote.DIAMOND_JACK);
        Ints joueurs_ = pli_.joueursDefausses(Suit.UNDEFINED);
        int j_ = 1 % nombreDeJoueurs_;
        assertEq(2, joueurs_.size());
        assertTrue(joueurs_.containsObj(j_));
        j_ = (j_ + 1) % nombreDeJoueurs_;
        assertTrue(joueurs_.containsObj(j_));
        pli_ = new TrickBelote(1);
        //nombreTotal++;
        pli_.getCartes().ajouter(CardBelote.DIAMOND_1);
        pli_.getCartes().ajouter(CardBelote.SPADE_7);
        pli_.getCartes().ajouter(CardBelote.HEART_KING);
        pli_.getCartes().ajouter(CardBelote.DIAMOND_JACK);
        joueurs_ = pli_.joueursDefausses(Suit.UNDEFINED);
        j_ = (1 + 1) % nombreDeJoueurs_;
        assertEq(2, joueurs_.size());
        assertTrue(joueurs_.containsObj(j_));
        j_ = (j_ + 1) % nombreDeJoueurs_;
        assertTrue(joueurs_.containsObj(j_));
        pli_ = new TrickBelote(2);
        //nombreTotal++;
        pli_.getCartes().ajouter(CardBelote.DIAMOND_1);
        pli_.getCartes().ajouter(CardBelote.SPADE_7);
        pli_.getCartes().ajouter(CardBelote.HEART_KING);
        pli_.getCartes().ajouter(CardBelote.DIAMOND_JACK);
        joueurs_ = pli_.joueursDefausses(Suit.UNDEFINED);
        j_ = (2 + 1) % nombreDeJoueurs_;
        assertEq(2, joueurs_.size());
        assertTrue(joueurs_.containsObj(j_));
        j_ = (j_ + 1) % nombreDeJoueurs_;
        assertTrue(joueurs_.containsObj(j_));
        pli_ = new TrickBelote(3);
        //nombreTotal++;
        pli_.getCartes().ajouter(CardBelote.DIAMOND_1);
        pli_.getCartes().ajouter(CardBelote.SPADE_7);
        pli_.getCartes().ajouter(CardBelote.HEART_KING);
        pli_.getCartes().ajouter(CardBelote.DIAMOND_JACK);
        joueurs_ = pli_.joueursDefausses(Suit.UNDEFINED);
        j_ = (3 + 1) % nombreDeJoueurs_;
        assertEq(2, joueurs_.size());
        assertTrue(joueurs_.containsObj(j_));
        j_ = (j_ + 1) % nombreDeJoueurs_;
        assertTrue(joueurs_.containsObj(j_));
    }
    @Test
    public void joueursDefausses2(){
        int nombreDeJoueurs_ = 4;
        TrickBelote pli_ = new TrickBelote(0);
        pli_.getCartes().ajouter(CardBelote.DIAMOND_1);
        pli_.getCartes().ajouter(CardBelote.SPADE_7);
        pli_.getCartes().ajouter(CardBelote.HEART_KING);
        pli_.getCartes().ajouter(CardBelote.DIAMOND_8);
        Ints joueurs_ = pli_.joueursDefausses(Suit.HEART);
        int j_ = 1 % nombreDeJoueurs_;
        assertEq(1, joueurs_.size());
        assertTrue(joueurs_.containsObj(j_));
        pli_ = new TrickBelote(1);
        pli_.getCartes().ajouter(CardBelote.DIAMOND_1);
        pli_.getCartes().ajouter(CardBelote.SPADE_7);
        pli_.getCartes().ajouter(CardBelote.HEART_KING);
        pli_.getCartes().ajouter(CardBelote.DIAMOND_8);
        joueurs_ = pli_.joueursDefausses(Suit.HEART);
        j_ = (1 + 1) % nombreDeJoueurs_;
        assertEq(1, joueurs_.size());
        assertTrue(joueurs_.containsObj(j_));
        pli_ = new TrickBelote(2);
        pli_.getCartes().ajouter(CardBelote.DIAMOND_1);
        pli_.getCartes().ajouter(CardBelote.SPADE_7);
        pli_.getCartes().ajouter(CardBelote.HEART_KING);
        pli_.getCartes().ajouter(CardBelote.DIAMOND_8);
        joueurs_ = pli_.joueursDefausses(Suit.HEART);
        j_ = (2 + 1) % nombreDeJoueurs_;
        assertEq(1, joueurs_.size());
        assertTrue(joueurs_.containsObj(j_));
        pli_ = new TrickBelote(3);
        pli_.getCartes().ajouter(CardBelote.DIAMOND_1);
        pli_.getCartes().ajouter(CardBelote.SPADE_7);
        pli_.getCartes().ajouter(CardBelote.HEART_KING);
        pli_.getCartes().ajouter(CardBelote.DIAMOND_8);
        joueurs_ = pli_.joueursDefausses(Suit.HEART);
        j_ = (3 + 1) % nombreDeJoueurs_;
        assertEq(1, joueurs_.size());
        assertTrue(joueurs_.containsObj(j_));
    }
}