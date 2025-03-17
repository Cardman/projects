package cards.tarot;

import cards.tarot.enumerations.DealingTarot;
import org.junit.Test;

import cards.consts.Suit;
import cards.tarot.enumerations.CardTarot;
import code.util.*;


public final class TrickTarotTest extends EquallableTarotUtil {
    @Test
    public void new_PliTarot_Test(){
        TrickTarot pli_ = new TrickTarot();
        assertTrue(pli_.estVide());
    }
    @Test
    public void new_PliTarot_byte_Boolean_1Test(){
        TrickTarot pli_ = new TrickTarot(0);
        assertTrue(pli_.estVide());
    }
    @Test
    public void new_PliTarot_byte_Boolean_2Test(){
        TrickTarot pli_ = new TrickTarot(1);
        assertTrue(pli_.estVide());
    }
    @Test
    public void new_PliTarot_byte_Boolean_3Test(){
        TrickTarot pli_ = new TrickTarot(2);
        assertTrue(pli_.estVide());
    }
    @Test
    public void new_PliTarot_byte_Boolean_4Test(){
        TrickTarot pli_ = new TrickTarot(3);
        assertTrue(pli_.estVide());
    }
    @Test
    public void new_PliTarot_MainTarot_byte_Boolean_1(){
        TrickTarot pli_ = new TrickTarot(new HandTarot(), 0);
        assertTrue(pli_.estVide());
        pli_ = new TrickTarot(new HandTarot(), 0);
        assertTrue(pli_.estVide());
        HandTarot main_ = new HandTarot();
        main_.ajouter(CardTarot.DIAMOND_1);
        pli_ = new TrickTarot(main_, 0);
        assertEq(1, pli_.total() );
        assertTrue(pli_.contient(CardTarot.DIAMOND_1));
    }
    @Test
    public void couleurDemandee1(){
        TrickTarot pli_ = new TrickTarot(0);
        //nombreTotal++;
        assertEq(Suit.UNDEFINED, pli_.couleurDemandee());
        pli_ = new TrickTarot(0);
        //nombreTotal++;
        pli_.getCartes().ajouter(CardTarot.DIAMOND_1);
        assertEq(Suit.DIAMOND, pli_.couleurDemandee());
        pli_ = new TrickTarot(0);
        //nombreTotal++;
        pli_.getCartes().ajouter(CardTarot.DIAMOND_1);
        pli_.getCartes().ajouter(CardTarot.SPADE_JACK);
        assertEq(Suit.DIAMOND, pli_.couleurDemandee());
        pli_ = new TrickTarot(0);
        //nombreTotal++;
        pli_.getCartes().ajouter(CardTarot.TRUMP_12);
        pli_.getCartes().ajouter(CardTarot.SPADE_JACK);
        assertEq(Suit.TRUMP, pli_.couleurDemandee());
        pli_ = new TrickTarot(0);
        //nombreTotal++;
        pli_.getCartes().ajouter(CardTarot.EXCUSE);
        pli_.getCartes().ajouter(CardTarot.SPADE_JACK);
        assertEq(Suit.SPADE, pli_.couleurDemandee());
        pli_ = new TrickTarot(0);
        //nombreTotal++;
        pli_.getCartes().ajouter(CardTarot.EXCUSE);
        pli_.getCartes().ajouter(CardTarot.TRUMP_20);
        assertEq(Suit.TRUMP, pli_.couleurDemandee());
        pli_ = new TrickTarot(0);
        //nombreTotal++;
        pli_.getCartes().ajouter(CardTarot.EXCUSE);
        assertEq(Suit.UNDEFINED, pli_.couleurDemandee());
    }
    @Test
    public void aJoue1Test(){
        TrickTarot pli_ = new TrickTarot(0);
        int nombreJoueurs_ = 4;
        assertTrue(!pli_.aJoue(0, nombreJoueurs_));
        assertTrue(!pli_.aJoue(1, nombreJoueurs_));
        assertTrue(!pli_.aJoue(2, nombreJoueurs_));
        assertTrue(!pli_.aJoue(3, nombreJoueurs_));
    }
    @Test
    public void aJoue2Test(){
        TrickTarot pli_ = new TrickTarot(1);
        int nombreJoueurs_ = 4;
        assertTrue(!pli_.aJoue(0, nombreJoueurs_));
        assertTrue(!pli_.aJoue(1, nombreJoueurs_));
        assertTrue(!pli_.aJoue(2, nombreJoueurs_));
        assertTrue(!pli_.aJoue(3, nombreJoueurs_));
    }
    @Test
    public void aJoue3Test(){
        TrickTarot pli_ = new TrickTarot(2);
        int nombreJoueurs_ = 4;
        assertTrue(!pli_.aJoue(0, nombreJoueurs_));
        assertTrue(!pli_.aJoue(1, nombreJoueurs_));
        assertTrue(!pli_.aJoue(2, nombreJoueurs_));
        assertTrue(!pli_.aJoue(3, nombreJoueurs_));
    }
    @Test
    public void aJoue4Test(){
        TrickTarot pli_ = new TrickTarot(3);
        int nombreJoueurs_ = 4;
        assertTrue(!pli_.aJoue(0, nombreJoueurs_));
        assertTrue(!pli_.aJoue(1, nombreJoueurs_));
        assertTrue(!pli_.aJoue(2, nombreJoueurs_));
        assertTrue(!pli_.aJoue(3, nombreJoueurs_));
    }
    @Test
    public void aJoue5Test(){
        TrickTarot pli_ = new TrickTarot(0);
        int nombreJoueurs_ = 4;
        pli_.getCartes().ajouter(CardTarot.DIAMOND_1);
        assertTrue(pli_.aJoue(0, nombreJoueurs_));
        assertTrue(!pli_.aJoue((0+1)%nombreJoueurs_, nombreJoueurs_));
        assertTrue(!pli_.aJoue((0+2)%nombreJoueurs_, nombreJoueurs_));
        assertTrue(!pli_.aJoue((0+3)%nombreJoueurs_, nombreJoueurs_));
    }
    @Test
    public void aJoue6Test(){
        TrickTarot pli_ = new TrickTarot(1);
        int nombreJoueurs_ = 4;
        pli_.getCartes().ajouter(CardTarot.DIAMOND_1);
        assertTrue(pli_.aJoue(1, nombreJoueurs_));
        assertTrue(!pli_.aJoue((1+1)%nombreJoueurs_, nombreJoueurs_));
        assertTrue(!pli_.aJoue((1+2)%nombreJoueurs_, nombreJoueurs_));
        assertTrue(!pli_.aJoue((1+3)%nombreJoueurs_, nombreJoueurs_));
    }
    @Test
    public void aJoue7Test(){
        TrickTarot pli_ = new TrickTarot(2);
        int nombreJoueurs_ = 4;
        pli_.getCartes().ajouter(CardTarot.DIAMOND_1);
        assertTrue(pli_.aJoue(2, nombreJoueurs_));
        assertTrue(!pli_.aJoue((2+1)%nombreJoueurs_, nombreJoueurs_));
        assertTrue(!pli_.aJoue((2+2)%nombreJoueurs_, nombreJoueurs_));
        assertTrue(!pli_.aJoue((2+3)%nombreJoueurs_, nombreJoueurs_));
    }
    @Test
    public void aJoue8Test(){
        TrickTarot pli_ = new TrickTarot(3);
        int nombreJoueurs_ = 4;
        pli_.getCartes().ajouter(CardTarot.DIAMOND_1);
        assertTrue(pli_.aJoue(3, nombreJoueurs_));
        assertTrue(!pli_.aJoue((3+1)%nombreJoueurs_, nombreJoueurs_));
        assertTrue(!pli_.aJoue((3+2)%nombreJoueurs_, nombreJoueurs_));
        assertTrue(!pli_.aJoue((3+3)%nombreJoueurs_, nombreJoueurs_));
    }
    @Test
    public void aJoue9Test(){
        TrickTarot pli_ = new TrickTarot(0);
        int nombreJoueurs_ = 4;
        pli_.getCartes().ajouter(CardTarot.DIAMOND_1);
        pli_.getCartes().ajouter(CardTarot.SPADE_JACK);
        assertTrue(pli_.aJoue(0, nombreJoueurs_));
        assertTrue(pli_.aJoue((0+1)%nombreJoueurs_, nombreJoueurs_));
        assertTrue(!pli_.aJoue((0+2)%nombreJoueurs_, nombreJoueurs_));
        assertTrue(!pli_.aJoue((0+3)%nombreJoueurs_, nombreJoueurs_));
    }
    @Test
    public void aJoue10Test(){
        TrickTarot pli_ = new TrickTarot(1);
        int nombreJoueurs_ = 4;
        pli_.getCartes().ajouter(CardTarot.DIAMOND_1);
        pli_.getCartes().ajouter(CardTarot.SPADE_JACK);
        assertTrue(pli_.aJoue(1, nombreJoueurs_));
        assertTrue(pli_.aJoue((1+1)%nombreJoueurs_, nombreJoueurs_));
        assertTrue(!pli_.aJoue((1+2)%nombreJoueurs_, nombreJoueurs_));
        assertTrue(!pli_.aJoue((1+3)%nombreJoueurs_, nombreJoueurs_));
    }
    @Test
    public void aJoue11Test(){
        TrickTarot pli_ = new TrickTarot(2);
        int nombreJoueurs_ = 4;
        pli_.getCartes().ajouter(CardTarot.DIAMOND_1);
        pli_.getCartes().ajouter(CardTarot.SPADE_JACK);
        assertTrue(pli_.aJoue(2, nombreJoueurs_));
        assertTrue(pli_.aJoue((2+1)%nombreJoueurs_, nombreJoueurs_));
        assertTrue(!pli_.aJoue((2+2)%nombreJoueurs_, nombreJoueurs_));
        assertTrue(!pli_.aJoue((2+3)%nombreJoueurs_, nombreJoueurs_));
    }
    @Test
    public void aJoue12Test(){
        TrickTarot pli_ = new TrickTarot(3);
        int nombreJoueurs_ = 4;
        pli_.getCartes().ajouter(CardTarot.DIAMOND_1);
        pli_.getCartes().ajouter(CardTarot.SPADE_JACK);
        assertTrue(pli_.aJoue(3, nombreJoueurs_));
        assertTrue(pli_.aJoue((3+1)%nombreJoueurs_, nombreJoueurs_));
        assertTrue(!pli_.aJoue((3+2)%nombreJoueurs_, nombreJoueurs_));
        assertTrue(!pli_.aJoue((3+3)%nombreJoueurs_, nombreJoueurs_));
    }
    @Test
    public void aJoue13Test(){
        TrickTarot pli_ = new TrickTarot(0);
        int nombreJoueurs_ = 4;
        pli_.getCartes().ajouter(CardTarot.DIAMOND_1);
        pli_.getCartes().ajouter(CardTarot.SPADE_JACK);
        pli_.getCartes().ajouter(CardTarot.HEART_KING);
        assertTrue(pli_.aJoue(0, nombreJoueurs_));
        assertTrue(pli_.aJoue((0+1)%nombreJoueurs_, nombreJoueurs_));
        assertTrue(pli_.aJoue((0+2)%nombreJoueurs_, nombreJoueurs_));
        assertTrue(!pli_.aJoue((0+3)%nombreJoueurs_, nombreJoueurs_));
    }
    @Test
    public void aJoue14Test(){
        TrickTarot pli_ = new TrickTarot(1);
        int nombreJoueurs_ = 4;
        pli_.getCartes().ajouter(CardTarot.DIAMOND_1);
        pli_.getCartes().ajouter(CardTarot.SPADE_JACK);
        pli_.getCartes().ajouter(CardTarot.HEART_KING);
        assertTrue(pli_.aJoue(1, nombreJoueurs_));
        assertTrue(pli_.aJoue((1+1)%nombreJoueurs_, nombreJoueurs_));
        assertTrue(pli_.aJoue((1+2)%nombreJoueurs_, nombreJoueurs_));
        assertTrue(!pli_.aJoue((1+3)%nombreJoueurs_, nombreJoueurs_));
    }
    @Test
    public void aJoue15Test(){
        TrickTarot pli_ = new TrickTarot(2);
        int nombreJoueurs_ = 4;
        pli_.getCartes().ajouter(CardTarot.DIAMOND_1);
        pli_.getCartes().ajouter(CardTarot.SPADE_JACK);
        pli_.getCartes().ajouter(CardTarot.HEART_KING);
        assertTrue(pli_.aJoue(2, nombreJoueurs_));
        assertTrue(pli_.aJoue((2+1)%nombreJoueurs_, nombreJoueurs_));
        assertTrue(pli_.aJoue((2+2)%nombreJoueurs_, nombreJoueurs_));
        assertTrue(!pli_.aJoue((2+3)%nombreJoueurs_, nombreJoueurs_));
    }
    @Test
    public void aJoue16Test(){
        TrickTarot pli_ = new TrickTarot(3);
        int nombreJoueurs_ = 4;
        pli_.getCartes().ajouter(CardTarot.DIAMOND_1);
        pli_.getCartes().ajouter(CardTarot.SPADE_JACK);
        pli_.getCartes().ajouter(CardTarot.HEART_KING);
        assertTrue(pli_.aJoue(3, nombreJoueurs_));
        assertTrue(pli_.aJoue((3+1)%nombreJoueurs_, nombreJoueurs_));
        assertTrue(pli_.aJoue((3+2)%nombreJoueurs_, nombreJoueurs_));
        assertTrue(!pli_.aJoue((3+3)%nombreJoueurs_, nombreJoueurs_));
    }
    @Test
    public void aJoue17Test(){
        TrickTarot pli_ = new TrickTarot(0);
        int nombreJoueurs_ = 4;
        pli_.getCartes().ajouter(CardTarot.DIAMOND_1);
        pli_.getCartes().ajouter(CardTarot.SPADE_JACK);
        pli_.getCartes().ajouter(CardTarot.HEART_KING);
        pli_.getCartes().ajouter(CardTarot.CLUB_7);
        assertTrue(pli_.aJoue(0, nombreJoueurs_));
        assertTrue(pli_.aJoue((0+1)%nombreJoueurs_, nombreJoueurs_));
        assertTrue(pli_.aJoue((0+2)%nombreJoueurs_, nombreJoueurs_));
        assertTrue(pli_.aJoue((0+3)%nombreJoueurs_, nombreJoueurs_));
    }
    @Test
    public void aJoue18Test(){
        TrickTarot pli_ = new TrickTarot(1);
        int nombreJoueurs_ = 4;
        pli_.getCartes().ajouter(CardTarot.DIAMOND_1);
        pli_.getCartes().ajouter(CardTarot.SPADE_JACK);
        pli_.getCartes().ajouter(CardTarot.HEART_KING);
        pli_.getCartes().ajouter(CardTarot.CLUB_7);
        assertTrue(pli_.aJoue(1, nombreJoueurs_));
        assertTrue(pli_.aJoue((1+1)%nombreJoueurs_, nombreJoueurs_));
        assertTrue(pli_.aJoue((1+2)%nombreJoueurs_, nombreJoueurs_));
        assertTrue(pli_.aJoue((1+3)%nombreJoueurs_, nombreJoueurs_));
    }
    @Test
    public void aJoue19Test(){
        TrickTarot pli_ = new TrickTarot(2);
        int nombreJoueurs_ = 4;
        pli_.getCartes().ajouter(CardTarot.DIAMOND_1);
        pli_.getCartes().ajouter(CardTarot.SPADE_JACK);
        pli_.getCartes().ajouter(CardTarot.HEART_KING);
        pli_.getCartes().ajouter(CardTarot.CLUB_7);
        assertTrue(pli_.aJoue(2, nombreJoueurs_));
        assertTrue(pli_.aJoue((2+1)%nombreJoueurs_, nombreJoueurs_));
        assertTrue(pli_.aJoue((2+2)%nombreJoueurs_, nombreJoueurs_));
        assertTrue(pli_.aJoue((2+3)%nombreJoueurs_, nombreJoueurs_));
    }
    @Test
    public void aJoue20Test(){
        TrickTarot pli_ = new TrickTarot(3);
        int nombreJoueurs_ = 4;
        pli_.getCartes().ajouter(CardTarot.DIAMOND_1);
        pli_.getCartes().ajouter(CardTarot.SPADE_JACK);
        pli_.getCartes().ajouter(CardTarot.HEART_KING);
        pli_.getCartes().ajouter(CardTarot.CLUB_7);
        assertTrue(pli_.aJoue(3, nombreJoueurs_));
        assertTrue(pli_.aJoue((3+1)%nombreJoueurs_, nombreJoueurs_));
        assertTrue(pli_.aJoue((3+2)%nombreJoueurs_, nombreJoueurs_));
        assertTrue(pli_.aJoue((3+3)%nombreJoueurs_, nombreJoueurs_));
    }
    @Test
    public void carteDuJoueur1(){
        TrickTarot pli_ = new TrickTarot(0);
        //nombreTotal++;
        int nombreJoueurs_ = 4;
        pli_.getCartes().ajouter(CardTarot.DIAMOND_1);
        assertEq(CardTarot.DIAMOND_1, pli_.carteDuJoueur(0, nombreJoueurs_));
        pli_.getCartes().ajouter(CardTarot.SPADE_JACK);
        assertEq(CardTarot.DIAMOND_1, pli_.carteDuJoueur(0, nombreJoueurs_));
        assertEq(CardTarot.SPADE_JACK, pli_.carteDuJoueur(1, nombreJoueurs_));
        pli_.getCartes().ajouter(CardTarot.HEART_KING);
        assertEq(CardTarot.DIAMOND_1, pli_.carteDuJoueur(0, nombreJoueurs_));
        assertEq(CardTarot.SPADE_JACK, pli_.carteDuJoueur(1, nombreJoueurs_));
        assertEq(CardTarot.HEART_KING, pli_.carteDuJoueur(2, nombreJoueurs_));
        pli_.getCartes().ajouter(CardTarot.CLUB_7);
        assertEq(CardTarot.DIAMOND_1, pli_.carteDuJoueur(0, nombreJoueurs_));
        assertEq(CardTarot.SPADE_JACK, pli_.carteDuJoueur(1, nombreJoueurs_));
        assertEq(CardTarot.HEART_KING, pli_.carteDuJoueur(2, nombreJoueurs_));
        assertEq(CardTarot.CLUB_7, pli_.carteDuJoueur(3, nombreJoueurs_));
        pli_ = new TrickTarot(1);
        pli_.getCartes().ajouter(CardTarot.DIAMOND_1);
        assertEq(CardTarot.DIAMOND_1, pli_.carteDuJoueur(1, nombreJoueurs_));
        pli_.getCartes().ajouter(CardTarot.SPADE_JACK);
        assertEq(CardTarot.DIAMOND_1, pli_.carteDuJoueur(1, nombreJoueurs_));
        assertEq(CardTarot.SPADE_JACK, pli_.carteDuJoueur(2, nombreJoueurs_));
        pli_.getCartes().ajouter(CardTarot.HEART_KING);
        assertEq(CardTarot.DIAMOND_1, pli_.carteDuJoueur(1, nombreJoueurs_));
        assertEq(CardTarot.SPADE_JACK, pli_.carteDuJoueur(2, nombreJoueurs_));
        assertEq(CardTarot.HEART_KING, pli_.carteDuJoueur(3, nombreJoueurs_));
        pli_.getCartes().ajouter(CardTarot.CLUB_7);
        assertEq(CardTarot.DIAMOND_1, pli_.carteDuJoueur(1, nombreJoueurs_));
        assertEq(CardTarot.SPADE_JACK, pli_.carteDuJoueur(2, nombreJoueurs_));
        assertEq(CardTarot.HEART_KING, pli_.carteDuJoueur(3, nombreJoueurs_));
        assertEq(CardTarot.CLUB_7, pli_.carteDuJoueur(0, nombreJoueurs_));
    }
    @Test
    public void joueurAyantJouePliEnCours1(){
        TrickTarot pli_ = new TrickTarot(0);
        //nombreTotal++;
        int nombreJoueurs_ = 4;
        assertEq(-1, pli_.joueurAyantJouePliEnCours(CardTarot.DIAMOND_1, nombreJoueurs_));
        pli_.getCartes().ajouter(CardTarot.DIAMOND_1);
        assertEq(0, pli_.joueurAyantJouePliEnCours(CardTarot.DIAMOND_1, nombreJoueurs_));
        pli_.getCartes().ajouter(CardTarot.SPADE_JACK);
        assertEq(0, pli_.joueurAyantJouePliEnCours(CardTarot.DIAMOND_1, nombreJoueurs_));
        assertEq(1, pli_.joueurAyantJouePliEnCours(CardTarot.SPADE_JACK, nombreJoueurs_));
        pli_.getCartes().ajouter(CardTarot.HEART_KING);
        assertEq(0, pli_.joueurAyantJouePliEnCours(CardTarot.DIAMOND_1, nombreJoueurs_));
        assertEq(1, pli_.joueurAyantJouePliEnCours(CardTarot.SPADE_JACK, nombreJoueurs_));
        assertEq(2, pli_.joueurAyantJouePliEnCours(CardTarot.HEART_KING, nombreJoueurs_));
        pli_.getCartes().ajouter(CardTarot.CLUB_7);
        assertEq(0, pli_.joueurAyantJouePliEnCours(CardTarot.DIAMOND_1, nombreJoueurs_));
        assertEq(1, pli_.joueurAyantJouePliEnCours(CardTarot.SPADE_JACK, nombreJoueurs_));
        assertEq(2, pli_.joueurAyantJouePliEnCours(CardTarot.HEART_KING, nombreJoueurs_));
        assertEq(3, pli_.joueurAyantJouePliEnCours(CardTarot.CLUB_7, nombreJoueurs_));
        pli_ = new TrickTarot(1);
        //nombreTotal++;
        assertEq(-1, pli_.joueurAyantJouePliEnCours(CardTarot.DIAMOND_1, nombreJoueurs_));
        pli_.getCartes().ajouter(CardTarot.DIAMOND_1);
        assertEq(1, pli_.joueurAyantJouePliEnCours(CardTarot.DIAMOND_1, nombreJoueurs_));
        pli_.getCartes().ajouter(CardTarot.SPADE_JACK);
        assertEq(1, pli_.joueurAyantJouePliEnCours(CardTarot.DIAMOND_1, nombreJoueurs_));
        assertEq(2, pli_.joueurAyantJouePliEnCours(CardTarot.SPADE_JACK, nombreJoueurs_));
        pli_.getCartes().ajouter(CardTarot.HEART_KING);
        assertEq(1, pli_.joueurAyantJouePliEnCours(CardTarot.DIAMOND_1, nombreJoueurs_));
        assertEq(2, pli_.joueurAyantJouePliEnCours(CardTarot.SPADE_JACK, nombreJoueurs_));
        assertEq(3, pli_.joueurAyantJouePliEnCours(CardTarot.HEART_KING, nombreJoueurs_));
        pli_.getCartes().ajouter(CardTarot.CLUB_7);
        assertEq(1, pli_.joueurAyantJouePliEnCours(CardTarot.DIAMOND_1, nombreJoueurs_));
        assertEq(2, pli_.joueurAyantJouePliEnCours(CardTarot.SPADE_JACK, nombreJoueurs_));
        assertEq(3, pli_.joueurAyantJouePliEnCours(CardTarot.HEART_KING, nombreJoueurs_));
        assertEq(0, pli_.joueurAyantJouePliEnCours(CardTarot.CLUB_7, nombreJoueurs_));
    }
    @Test
    public void joueursAyantJoueAvant1(){
        TrickTarot pli_ = new TrickTarot(0);
        //nombreTotal++;
        pli_.getCartes().ajouter(CardTarot.DIAMOND_1);
        Ints joueurs_ = pli_.joueursAyantJoueAvant(1, DealingTarot.DEAL_2_VS_2_WITHOUT_CALL);
        assertEq(1, joueurs_.size());
        assertTrue(joueurs_.containsObj(0));
        pli_.getCartes().ajouter(CardTarot.SPADE_JACK);
        pli_.getCartes().ajouter(CardTarot.HEART_KING);
        pli_.getCartes().ajouter(CardTarot.CLUB_7);
        assertEq(0, pli_.joueursAyantJoueAvant(0,DealingTarot.DEAL_2_VS_2_WITHOUT_CALL).size());
        joueurs_ = pli_.joueursAyantJoueAvant(1,DealingTarot.DEAL_2_VS_2_WITHOUT_CALL);
        assertEq(1, joueurs_.size());
        assertTrue(joueurs_.containsObj(0));
        joueurs_ = pli_.joueursAyantJoueAvant(2,DealingTarot.DEAL_2_VS_2_WITHOUT_CALL);
        assertEq(2, joueurs_.size());
        assertTrue(joueurs_.containsObj(0));
        assertTrue(joueurs_.containsObj(1));
        joueurs_ = pli_.joueursAyantJoueAvant(3,DealingTarot.DEAL_2_VS_2_WITHOUT_CALL);
        assertEq(3, joueurs_.size());
        assertTrue(joueurs_.containsObj(0));
        assertTrue(joueurs_.containsObj(1));
        assertTrue(joueurs_.containsObj(2));
        pli_ = new TrickTarot(1);
        //nombreTotal++;
        pli_.getCartes().ajouter(CardTarot.DIAMOND_1);
        pli_.getCartes().ajouter(CardTarot.SPADE_JACK);
        pli_.getCartes().ajouter(CardTarot.HEART_KING);
        pli_.getCartes().ajouter(CardTarot.CLUB_7);
        assertEq(0, pli_.joueursAyantJoueAvant(1,DealingTarot.DEAL_2_VS_2_WITHOUT_CALL).size());
        joueurs_ = pli_.joueursAyantJoueAvant(2,DealingTarot.DEAL_2_VS_2_WITHOUT_CALL);
        assertEq(1, joueurs_.size());
        assertTrue(joueurs_.containsObj(1));
        joueurs_ = pli_.joueursAyantJoueAvant(3,DealingTarot.DEAL_2_VS_2_WITHOUT_CALL);
        assertEq(2, joueurs_.size());
        assertTrue(joueurs_.containsObj(1));
        assertTrue(joueurs_.containsObj(2));
        joueurs_ = pli_.joueursAyantJoueAvant(0,DealingTarot.DEAL_2_VS_2_WITHOUT_CALL);
        assertEq(3, joueurs_.size());
        assertTrue(joueurs_.containsObj(1));
        assertTrue(joueurs_.containsObj(2));
        assertTrue(joueurs_.containsObj(3));
        pli_ = new TrickTarot(2);
        //nombreTotal++;
        pli_.getCartes().ajouter(CardTarot.DIAMOND_1);
        pli_.getCartes().ajouter(CardTarot.SPADE_JACK);
        pli_.getCartes().ajouter(CardTarot.HEART_KING);
        pli_.getCartes().ajouter(CardTarot.CLUB_7);
        assertEq(0, pli_.joueursAyantJoueAvant(2,DealingTarot.DEAL_2_VS_2_WITHOUT_CALL).size());
        joueurs_ = pli_.joueursAyantJoueAvant(3,DealingTarot.DEAL_2_VS_2_WITHOUT_CALL);
        assertEq(1, joueurs_.size());
        assertTrue(joueurs_.containsObj(2));
        joueurs_ = pli_.joueursAyantJoueAvant(0,DealingTarot.DEAL_2_VS_2_WITHOUT_CALL);
        assertEq(2, joueurs_.size());
        assertTrue(joueurs_.containsObj(2));
        assertTrue(joueurs_.containsObj(3));
        joueurs_ = pli_.joueursAyantJoueAvant(1,DealingTarot.DEAL_2_VS_2_WITHOUT_CALL);
        assertEq(3, joueurs_.size());
        assertTrue(joueurs_.containsObj(2));
        assertTrue(joueurs_.containsObj(3));
        assertTrue(joueurs_.containsObj(0));
        pli_ = new TrickTarot(3);
        //nombreTotal++;
        pli_.getCartes().ajouter(CardTarot.DIAMOND_1);
        pli_.getCartes().ajouter(CardTarot.SPADE_JACK);
        pli_.getCartes().ajouter(CardTarot.HEART_KING);
        pli_.getCartes().ajouter(CardTarot.CLUB_7);
        assertEq(0, pli_.joueursAyantJoueAvant(3,DealingTarot.DEAL_2_VS_2_WITHOUT_CALL).size());
        joueurs_ = pli_.joueursAyantJoueAvant(0,DealingTarot.DEAL_2_VS_2_WITHOUT_CALL);
        assertEq(1, joueurs_.size());
        assertTrue(joueurs_.containsObj(3));
        joueurs_ = pli_.joueursAyantJoueAvant(1,DealingTarot.DEAL_2_VS_2_WITHOUT_CALL);
        assertEq(2, joueurs_.size());
        assertTrue(joueurs_.containsObj(3));
        assertTrue(joueurs_.containsObj(0));
        joueurs_ = pli_.joueursAyantJoueAvant(2,DealingTarot.DEAL_2_VS_2_WITHOUT_CALL);
        assertEq(3, joueurs_.size());
        assertTrue(joueurs_.containsObj(3));
        assertTrue(joueurs_.containsObj(0));
        assertTrue(joueurs_.containsObj(1));
    }
    @Test
    public void cardsInTricks1() {
        TrickTarot pli_ = new TrickTarot(1);
        pli_.ajouter(CardTarot.HEART_1);
        pli_.ajouter(CardTarot.HEART_10);
        CustList<HandTarot> ls_ = pli_.completeCurrent(4, false);
        assertEq(4,ls_.size());
        assertEq(0,ls_.get(0).total());
        assertEq(0,ls_.get(1).total());
        assertEq(0,ls_.get(2).total());
        assertEq(0,ls_.get(3).total());
    }
    @Test
    public void cardsInTricks2() {
        TrickTarot pli_ = new TrickTarot(1);
        pli_.ajouter(CardTarot.HEART_1);
        pli_.ajouter(CardTarot.HEART_10);
        CustList<HandTarot> ls_ = pli_.completeCurrent(4, true);
        assertEq(4,ls_.size());
        assertEq(0,ls_.get(0).total());
        assertEq(1,ls_.get(1).total());
        assertEq(CardTarot.HEART_1,ls_.get(1).carte(0));
        assertEq(1,ls_.get(2).total());
        assertEq(CardTarot.HEART_10,ls_.get(2).carte(0));
        assertEq(0,ls_.get(3).total());
    }
    @Test
    public void getRamasseur1Test(){
        int nombreDeJoueurs_ = 4;
        int j_ = 1;
        int jTwo_ = 2;
        int jThree_ = 3;

        TrickTarot pli_ = new TrickTarot(0);
        pli_.getCartes().ajouter(CardTarot.DIAMOND_1);
        assertEq(0, pli_.getRamasseur(nombreDeJoueurs_));
        pli_.getCartes().ajouter(CardTarot.SPADE_7);
        assertEq(0, pli_.getRamasseur(nombreDeJoueurs_));
        pli_.getCartes().ajouter(CardTarot.DIAMOND_KING);
        assertEq(jTwo_, pli_.getRamasseur(nombreDeJoueurs_));
        pli_.getCartes().ajouter(CardTarot.DIAMOND_QUEEN);
        assertEq(jTwo_, pli_.getRamasseur(nombreDeJoueurs_));
        pli_ = new TrickTarot(0);
        pli_.getCartes().ajouter(CardTarot.DIAMOND_1);
        assertEq(0, pli_.getRamasseur(nombreDeJoueurs_));
        pli_.getCartes().ajouter(CardTarot.TRUMP_7);
        assertEq(j_, pli_.getRamasseur(nombreDeJoueurs_));
        pli_.getCartes().ajouter(CardTarot.DIAMOND_KING);
        assertEq(j_, pli_.getRamasseur(nombreDeJoueurs_));
        pli_.getCartes().ajouter(CardTarot.DIAMOND_QUEEN);
        assertEq(j_, pli_.getRamasseur(nombreDeJoueurs_));
        pli_ = new TrickTarot(0);
        pli_.getCartes().ajouter(CardTarot.DIAMOND_1);
        assertEq(0, pli_.getRamasseur(nombreDeJoueurs_));
        pli_.getCartes().ajouter(CardTarot.SPADE_7);
        assertEq(0, pli_.getRamasseur(nombreDeJoueurs_));
        pli_.getCartes().ajouter(CardTarot.HEART_KING);
        assertEq(0, pli_.getRamasseur(nombreDeJoueurs_));
        pli_.getCartes().ajouter(CardTarot.DIAMOND_QUEEN);
        assertEq(jThree_, pli_.getRamasseur(nombreDeJoueurs_));
        pli_ = new TrickTarot(0);
        pli_.getCartes().ajouter(CardTarot.DIAMOND_1);
        assertEq(0, pli_.getRamasseur(nombreDeJoueurs_));
        pli_.getCartes().ajouter(CardTarot.SPADE_7);
        assertEq(0, pli_.getRamasseur(nombreDeJoueurs_));
        pli_.getCartes().ajouter(CardTarot.HEART_KING);
        assertEq(0, pli_.getRamasseur(nombreDeJoueurs_));
        pli_.getCartes().ajouter(CardTarot.CLUB_QUEEN);
        assertEq(0, pli_.getRamasseur(nombreDeJoueurs_));
        pli_ = new TrickTarot(0);
        pli_.getCartes().ajouter(CardTarot.EXCUSE);
        assertEq(0, pli_.getRamasseur(nombreDeJoueurs_));
        pli_.getCartes().ajouter(CardTarot.SPADE_7);
        assertEq(j_, pli_.getRamasseur(nombreDeJoueurs_));
        pli_.getCartes().ajouter(CardTarot.HEART_KING);
        assertEq(j_, pli_.getRamasseur(nombreDeJoueurs_));
        pli_.getCartes().ajouter(CardTarot.CLUB_QUEEN);
        assertEq(j_, pli_.getRamasseur(nombreDeJoueurs_));
        pli_ = new TrickTarot(0);
        pli_.getCartes().ajouter(CardTarot.DIAMOND_1);
        assertEq(0, pli_.getRamasseur(nombreDeJoueurs_));
        pli_.getCartes().ajouter(CardTarot.SPADE_7);
        assertEq(0, pli_.getRamasseur(nombreDeJoueurs_));
        pli_.getCartes().ajouter(CardTarot.HEART_KING);
        assertEq(0, pli_.getRamasseur(nombreDeJoueurs_));
        pli_.getCartes().ajouter(CardTarot.EXCUSE);
        assertEq(0, pli_.getRamasseur(nombreDeJoueurs_));
        pli_ = new TrickTarot(0);
        pli_.getCartes().ajouter(CardTarot.TRUMP_10);
        assertEq(0, pli_.getRamasseur(nombreDeJoueurs_));
        pli_.getCartes().ajouter(CardTarot.SPADE_7);
        assertEq(0, pli_.getRamasseur(nombreDeJoueurs_));
        pli_.getCartes().ajouter(CardTarot.HEART_KING);
        assertEq(0, pli_.getRamasseur(nombreDeJoueurs_));
        pli_.getCartes().ajouter(CardTarot.EXCUSE);
        assertEq(0, pli_.getRamasseur(nombreDeJoueurs_));
        pli_ = new TrickTarot(0);
        pli_.getCartes().ajouter(CardTarot.TRUMP_10);
        assertEq(0, pli_.getRamasseur(nombreDeJoueurs_));
        pli_.getCartes().ajouter(CardTarot.SPADE_7);
        assertEq(0, pli_.getRamasseur(nombreDeJoueurs_));
        pli_.getCartes().ajouter(CardTarot.HEART_KING);
        assertEq(0, pli_.getRamasseur(nombreDeJoueurs_));
        pli_.getCartes().ajouter(CardTarot.TRUMP_4);
        assertEq(0, pli_.getRamasseur(nombreDeJoueurs_));
        pli_ = new TrickTarot(0);
        pli_.getCartes().ajouter(CardTarot.TRUMP_10);
        assertEq(0, pli_.getRamasseur(nombreDeJoueurs_));
        pli_.getCartes().ajouter(CardTarot.SPADE_7);
        assertEq(0, pli_.getRamasseur(nombreDeJoueurs_));
        pli_.getCartes().ajouter(CardTarot.HEART_KING);
        assertEq(0, pli_.getRamasseur(nombreDeJoueurs_));
        pli_.getCartes().ajouter(CardTarot.CLUB_QUEEN);
        assertEq(0, pli_.getRamasseur(nombreDeJoueurs_));
        pli_ = new TrickTarot(0);
        pli_.getCartes().ajouter(CardTarot.TRUMP_10);
        assertEq(0, pli_.getRamasseur(nombreDeJoueurs_));
        pli_.getCartes().ajouter(CardTarot.SPADE_7);
        assertEq(0, pli_.getRamasseur(nombreDeJoueurs_));
        pli_.getCartes().ajouter(CardTarot.TRUMP_14);
        assertEq(jTwo_, pli_.getRamasseur(nombreDeJoueurs_));
        pli_.getCartes().ajouter(CardTarot.EXCUSE);
        assertEq(jTwo_, pli_.getRamasseur(nombreDeJoueurs_));
        pli_ = new TrickTarot(0);
        pli_.getCartes().ajouter(CardTarot.TRUMP_10);
        assertEq(0, pli_.getRamasseur(nombreDeJoueurs_));
        pli_.getCartes().ajouter(CardTarot.TRUMP_16);
        assertEq(j_, pli_.getRamasseur(nombreDeJoueurs_));
        pli_.getCartes().ajouter(CardTarot.TRUMP_14);
        assertEq(j_, pli_.getRamasseur(nombreDeJoueurs_));
        pli_.getCartes().ajouter(CardTarot.EXCUSE);
        assertEq(j_, pli_.getRamasseur(nombreDeJoueurs_));
        pli_ = new TrickTarot(0);
        pli_.getCartes().ajouter(CardTarot.TRUMP_10);
        assertEq(0, pli_.getRamasseur(nombreDeJoueurs_));
        pli_.getCartes().ajouter(CardTarot.TRUMP_16);
        assertEq(j_, pli_.getRamasseur(nombreDeJoueurs_));
        pli_.getCartes().ajouter(CardTarot.TRUMP_14);
        assertEq(j_, pli_.getRamasseur(nombreDeJoueurs_));
        pli_.getCartes().ajouter(CardTarot.TRUMP_18);
        assertEq(jThree_, pli_.getRamasseur(nombreDeJoueurs_));
    }
    @Test
    public void getRamasseur2Test(){
        int nombreDeJoueurs_ = 4;
        int j_ = (1 + 1) % nombreDeJoueurs_;
        int jTwo_ = (1 + 2) % nombreDeJoueurs_;
        int jThree_ = (1 + 3) % nombreDeJoueurs_;

        TrickTarot pli_ = new TrickTarot(1);
        pli_.getCartes().ajouter(CardTarot.DIAMOND_1);
        assertEq(1, pli_.getRamasseur(nombreDeJoueurs_));
        pli_.getCartes().ajouter(CardTarot.SPADE_7);
        assertEq(1, pli_.getRamasseur(nombreDeJoueurs_));
        pli_.getCartes().ajouter(CardTarot.DIAMOND_KING);
        assertEq(jTwo_, pli_.getRamasseur(nombreDeJoueurs_));
        pli_.getCartes().ajouter(CardTarot.DIAMOND_QUEEN);
        assertEq(jTwo_, pli_.getRamasseur(nombreDeJoueurs_));
        pli_ = new TrickTarot(1);
        pli_.getCartes().ajouter(CardTarot.DIAMOND_1);
        assertEq(1, pli_.getRamasseur(nombreDeJoueurs_));
        pli_.getCartes().ajouter(CardTarot.TRUMP_7);
        assertEq(j_, pli_.getRamasseur(nombreDeJoueurs_));
        pli_.getCartes().ajouter(CardTarot.DIAMOND_KING);
        assertEq(j_, pli_.getRamasseur(nombreDeJoueurs_));
        pli_.getCartes().ajouter(CardTarot.DIAMOND_QUEEN);
        assertEq(j_, pli_.getRamasseur(nombreDeJoueurs_));
        pli_ = new TrickTarot(1);
        pli_.getCartes().ajouter(CardTarot.DIAMOND_1);
        assertEq(1, pli_.getRamasseur(nombreDeJoueurs_));
        pli_.getCartes().ajouter(CardTarot.SPADE_7);
        assertEq(1, pli_.getRamasseur(nombreDeJoueurs_));
        pli_.getCartes().ajouter(CardTarot.HEART_KING);
        assertEq(1, pli_.getRamasseur(nombreDeJoueurs_));
        pli_.getCartes().ajouter(CardTarot.DIAMOND_QUEEN);
        assertEq(jThree_, pli_.getRamasseur(nombreDeJoueurs_));
        pli_ = new TrickTarot(1);
        pli_.getCartes().ajouter(CardTarot.DIAMOND_1);
        assertEq(1, pli_.getRamasseur(nombreDeJoueurs_));
        pli_.getCartes().ajouter(CardTarot.SPADE_7);
        assertEq(1, pli_.getRamasseur(nombreDeJoueurs_));
        pli_.getCartes().ajouter(CardTarot.HEART_KING);
        assertEq(1, pli_.getRamasseur(nombreDeJoueurs_));
        pli_.getCartes().ajouter(CardTarot.CLUB_QUEEN);
        assertEq(1, pli_.getRamasseur(nombreDeJoueurs_));
        pli_ = new TrickTarot(1);
        pli_.getCartes().ajouter(CardTarot.EXCUSE);
        assertEq(1, pli_.getRamasseur(nombreDeJoueurs_));
        pli_.getCartes().ajouter(CardTarot.SPADE_7);
        assertEq(j_, pli_.getRamasseur(nombreDeJoueurs_));
        pli_.getCartes().ajouter(CardTarot.HEART_KING);
        assertEq(j_, pli_.getRamasseur(nombreDeJoueurs_));
        pli_.getCartes().ajouter(CardTarot.CLUB_QUEEN);
        assertEq(j_, pli_.getRamasseur(nombreDeJoueurs_));
        pli_ = new TrickTarot(1);
        pli_.getCartes().ajouter(CardTarot.DIAMOND_1);
        assertEq(1, pli_.getRamasseur(nombreDeJoueurs_));
        pli_.getCartes().ajouter(CardTarot.SPADE_7);
        assertEq(1, pli_.getRamasseur(nombreDeJoueurs_));
        pli_.getCartes().ajouter(CardTarot.HEART_KING);
        assertEq(1, pli_.getRamasseur(nombreDeJoueurs_));
        pli_.getCartes().ajouter(CardTarot.EXCUSE);
        assertEq(1, pli_.getRamasseur(nombreDeJoueurs_));
        pli_ = new TrickTarot(1);
        pli_.getCartes().ajouter(CardTarot.TRUMP_10);
        assertEq(1, pli_.getRamasseur(nombreDeJoueurs_));
        pli_.getCartes().ajouter(CardTarot.SPADE_7);
        assertEq(1, pli_.getRamasseur(nombreDeJoueurs_));
        pli_.getCartes().ajouter(CardTarot.HEART_KING);
        assertEq(1, pli_.getRamasseur(nombreDeJoueurs_));
        pli_.getCartes().ajouter(CardTarot.EXCUSE);
        assertEq(1, pli_.getRamasseur(nombreDeJoueurs_));
        pli_ = new TrickTarot(1);
        pli_.getCartes().ajouter(CardTarot.TRUMP_10);
        assertEq(1, pli_.getRamasseur(nombreDeJoueurs_));
        pli_.getCartes().ajouter(CardTarot.SPADE_7);
        assertEq(1, pli_.getRamasseur(nombreDeJoueurs_));
        pli_.getCartes().ajouter(CardTarot.HEART_KING);
        assertEq(1, pli_.getRamasseur(nombreDeJoueurs_));
        pli_.getCartes().ajouter(CardTarot.TRUMP_4);
        assertEq(1, pli_.getRamasseur(nombreDeJoueurs_));
        pli_ = new TrickTarot(1);
        pli_.getCartes().ajouter(CardTarot.TRUMP_10);
        assertEq(1, pli_.getRamasseur(nombreDeJoueurs_));
        pli_.getCartes().ajouter(CardTarot.SPADE_7);
        assertEq(1, pli_.getRamasseur(nombreDeJoueurs_));
        pli_.getCartes().ajouter(CardTarot.HEART_KING);
        assertEq(1, pli_.getRamasseur(nombreDeJoueurs_));
        pli_.getCartes().ajouter(CardTarot.CLUB_QUEEN);
        assertEq(1, pli_.getRamasseur(nombreDeJoueurs_));
        pli_ = new TrickTarot(1);
        pli_.getCartes().ajouter(CardTarot.TRUMP_10);
        assertEq(1, pli_.getRamasseur(nombreDeJoueurs_));
        pli_.getCartes().ajouter(CardTarot.SPADE_7);
        assertEq(1, pli_.getRamasseur(nombreDeJoueurs_));
        pli_.getCartes().ajouter(CardTarot.TRUMP_14);
        assertEq(jTwo_, pli_.getRamasseur(nombreDeJoueurs_));
        pli_.getCartes().ajouter(CardTarot.EXCUSE);
        assertEq(jTwo_, pli_.getRamasseur(nombreDeJoueurs_));
        pli_ = new TrickTarot(1);
        pli_.getCartes().ajouter(CardTarot.TRUMP_10);
        assertEq(1, pli_.getRamasseur(nombreDeJoueurs_));
        pli_.getCartes().ajouter(CardTarot.TRUMP_16);
        assertEq(j_, pli_.getRamasseur(nombreDeJoueurs_));
        pli_.getCartes().ajouter(CardTarot.TRUMP_14);
        assertEq(j_, pli_.getRamasseur(nombreDeJoueurs_));
        pli_.getCartes().ajouter(CardTarot.EXCUSE);
        assertEq(j_, pli_.getRamasseur(nombreDeJoueurs_));
        pli_ = new TrickTarot(1);
        pli_.getCartes().ajouter(CardTarot.TRUMP_10);
        assertEq(1, pli_.getRamasseur(nombreDeJoueurs_));
        pli_.getCartes().ajouter(CardTarot.TRUMP_16);
        assertEq(j_, pli_.getRamasseur(nombreDeJoueurs_));
        pli_.getCartes().ajouter(CardTarot.TRUMP_14);
        assertEq(j_, pli_.getRamasseur(nombreDeJoueurs_));
        pli_.getCartes().ajouter(CardTarot.TRUMP_18);
        assertEq(jThree_, pli_.getRamasseur(nombreDeJoueurs_));
    }
    @Test
    public void getRamasseur3Test(){
        int nombreDeJoueurs_ = 4;
        int j_ = (2 + 1) % nombreDeJoueurs_;
        int jTwo_ = (2 + 2) % nombreDeJoueurs_;
        int jThree_ = (2 + 3) % nombreDeJoueurs_;

        TrickTarot pli_ = new TrickTarot(2);
        pli_.getCartes().ajouter(CardTarot.DIAMOND_1);
        assertEq(2, pli_.getRamasseur(nombreDeJoueurs_));
        pli_.getCartes().ajouter(CardTarot.SPADE_7);
        assertEq(2, pli_.getRamasseur(nombreDeJoueurs_));
        pli_.getCartes().ajouter(CardTarot.DIAMOND_KING);
        assertEq(jTwo_, pli_.getRamasseur(nombreDeJoueurs_));
        pli_.getCartes().ajouter(CardTarot.DIAMOND_QUEEN);
        assertEq(jTwo_, pli_.getRamasseur(nombreDeJoueurs_));
        pli_ = new TrickTarot(2);
        pli_.getCartes().ajouter(CardTarot.DIAMOND_1);
        assertEq(2, pli_.getRamasseur(nombreDeJoueurs_));
        pli_.getCartes().ajouter(CardTarot.TRUMP_7);
        assertEq(j_, pli_.getRamasseur(nombreDeJoueurs_));
        pli_.getCartes().ajouter(CardTarot.DIAMOND_KING);
        assertEq(j_, pli_.getRamasseur(nombreDeJoueurs_));
        pli_.getCartes().ajouter(CardTarot.DIAMOND_QUEEN);
        assertEq(j_, pli_.getRamasseur(nombreDeJoueurs_));
        pli_ = new TrickTarot(2);
        pli_.getCartes().ajouter(CardTarot.DIAMOND_1);
        assertEq(2, pli_.getRamasseur(nombreDeJoueurs_));
        pli_.getCartes().ajouter(CardTarot.SPADE_7);
        assertEq(2, pli_.getRamasseur(nombreDeJoueurs_));
        pli_.getCartes().ajouter(CardTarot.HEART_KING);
        assertEq(2, pli_.getRamasseur(nombreDeJoueurs_));
        pli_.getCartes().ajouter(CardTarot.DIAMOND_QUEEN);
        assertEq(jThree_, pli_.getRamasseur(nombreDeJoueurs_));
        pli_ = new TrickTarot(2);
        pli_.getCartes().ajouter(CardTarot.DIAMOND_1);
        assertEq(2, pli_.getRamasseur(nombreDeJoueurs_));
        pli_.getCartes().ajouter(CardTarot.SPADE_7);
        assertEq(2, pli_.getRamasseur(nombreDeJoueurs_));
        pli_.getCartes().ajouter(CardTarot.HEART_KING);
        assertEq(2, pli_.getRamasseur(nombreDeJoueurs_));
        pli_.getCartes().ajouter(CardTarot.CLUB_QUEEN);
        assertEq(2, pli_.getRamasseur(nombreDeJoueurs_));
        pli_ = new TrickTarot(2);
        pli_.getCartes().ajouter(CardTarot.EXCUSE);
        assertEq(2, pli_.getRamasseur(nombreDeJoueurs_));
        pli_.getCartes().ajouter(CardTarot.SPADE_7);
        assertEq(j_, pli_.getRamasseur(nombreDeJoueurs_));
        pli_.getCartes().ajouter(CardTarot.HEART_KING);
        assertEq(j_, pli_.getRamasseur(nombreDeJoueurs_));
        pli_.getCartes().ajouter(CardTarot.CLUB_QUEEN);
        assertEq(j_, pli_.getRamasseur(nombreDeJoueurs_));
        pli_ = new TrickTarot(2);
        pli_.getCartes().ajouter(CardTarot.DIAMOND_1);
        assertEq(2, pli_.getRamasseur(nombreDeJoueurs_));
        pli_.getCartes().ajouter(CardTarot.SPADE_7);
        assertEq(2, pli_.getRamasseur(nombreDeJoueurs_));
        pli_.getCartes().ajouter(CardTarot.HEART_KING);
        assertEq(2, pli_.getRamasseur(nombreDeJoueurs_));
        pli_.getCartes().ajouter(CardTarot.EXCUSE);
        assertEq(2, pli_.getRamasseur(nombreDeJoueurs_));
        pli_ = new TrickTarot(2);
        pli_.getCartes().ajouter(CardTarot.TRUMP_10);
        assertEq(2, pli_.getRamasseur(nombreDeJoueurs_));
        pli_.getCartes().ajouter(CardTarot.SPADE_7);
        assertEq(2, pli_.getRamasseur(nombreDeJoueurs_));
        pli_.getCartes().ajouter(CardTarot.HEART_KING);
        assertEq(2, pli_.getRamasseur(nombreDeJoueurs_));
        pli_.getCartes().ajouter(CardTarot.EXCUSE);
        assertEq(2, pli_.getRamasseur(nombreDeJoueurs_));
        pli_ = new TrickTarot(2);
        pli_.getCartes().ajouter(CardTarot.TRUMP_10);
        assertEq(2, pli_.getRamasseur(nombreDeJoueurs_));
        pli_.getCartes().ajouter(CardTarot.SPADE_7);
        assertEq(2, pli_.getRamasseur(nombreDeJoueurs_));
        pli_.getCartes().ajouter(CardTarot.HEART_KING);
        assertEq(2, pli_.getRamasseur(nombreDeJoueurs_));
        pli_.getCartes().ajouter(CardTarot.TRUMP_4);
        assertEq(2, pli_.getRamasseur(nombreDeJoueurs_));
        pli_ = new TrickTarot(2);
        pli_.getCartes().ajouter(CardTarot.TRUMP_10);
        assertEq(2, pli_.getRamasseur(nombreDeJoueurs_));
        pli_.getCartes().ajouter(CardTarot.SPADE_7);
        assertEq(2, pli_.getRamasseur(nombreDeJoueurs_));
        pli_.getCartes().ajouter(CardTarot.HEART_KING);
        assertEq(2, pli_.getRamasseur(nombreDeJoueurs_));
        pli_.getCartes().ajouter(CardTarot.CLUB_QUEEN);
        assertEq(2, pli_.getRamasseur(nombreDeJoueurs_));
        pli_ = new TrickTarot(2);
        pli_.getCartes().ajouter(CardTarot.TRUMP_10);
        assertEq(2, pli_.getRamasseur(nombreDeJoueurs_));
        pli_.getCartes().ajouter(CardTarot.SPADE_7);
        assertEq(2, pli_.getRamasseur(nombreDeJoueurs_));
        pli_.getCartes().ajouter(CardTarot.TRUMP_14);
        assertEq(jTwo_, pli_.getRamasseur(nombreDeJoueurs_));
        pli_.getCartes().ajouter(CardTarot.EXCUSE);
        assertEq(jTwo_, pli_.getRamasseur(nombreDeJoueurs_));
        pli_ = new TrickTarot(2);
        pli_.getCartes().ajouter(CardTarot.TRUMP_10);
        assertEq(2, pli_.getRamasseur(nombreDeJoueurs_));
        pli_.getCartes().ajouter(CardTarot.TRUMP_16);
        assertEq(j_, pli_.getRamasseur(nombreDeJoueurs_));
        pli_.getCartes().ajouter(CardTarot.TRUMP_14);
        assertEq(j_, pli_.getRamasseur(nombreDeJoueurs_));
        pli_.getCartes().ajouter(CardTarot.EXCUSE);
        assertEq(j_, pli_.getRamasseur(nombreDeJoueurs_));
        pli_ = new TrickTarot(2);
        pli_.getCartes().ajouter(CardTarot.TRUMP_10);
        assertEq(2, pli_.getRamasseur(nombreDeJoueurs_));
        pli_.getCartes().ajouter(CardTarot.TRUMP_16);
        assertEq(j_, pli_.getRamasseur(nombreDeJoueurs_));
        pli_.getCartes().ajouter(CardTarot.TRUMP_14);
        assertEq(j_, pli_.getRamasseur(nombreDeJoueurs_));
        pli_.getCartes().ajouter(CardTarot.TRUMP_18);
        assertEq(jThree_, pli_.getRamasseur(nombreDeJoueurs_));
    }
    @Test
    public void getRamasseur4Test(){
        int nombreDeJoueurs_ = 4;
        int j_ = (3 + 1) % nombreDeJoueurs_;
        int jTwo_ = (3 + 2) % nombreDeJoueurs_;
        int jThree_ = (3 + 3) % nombreDeJoueurs_;

        TrickTarot pli_ = new TrickTarot(3);
        pli_.getCartes().ajouter(CardTarot.DIAMOND_1);
        assertEq(3, pli_.getRamasseur(nombreDeJoueurs_));
        pli_.getCartes().ajouter(CardTarot.SPADE_7);
        assertEq(3, pli_.getRamasseur(nombreDeJoueurs_));
        pli_.getCartes().ajouter(CardTarot.DIAMOND_KING);
        assertEq(jTwo_, pli_.getRamasseur(nombreDeJoueurs_));
        pli_.getCartes().ajouter(CardTarot.DIAMOND_QUEEN);
        assertEq(jTwo_, pli_.getRamasseur(nombreDeJoueurs_));
        pli_ = new TrickTarot(3);
        pli_.getCartes().ajouter(CardTarot.DIAMOND_1);
        assertEq(3, pli_.getRamasseur(nombreDeJoueurs_));
        pli_.getCartes().ajouter(CardTarot.TRUMP_7);
        assertEq(j_, pli_.getRamasseur(nombreDeJoueurs_));
        pli_.getCartes().ajouter(CardTarot.DIAMOND_KING);
        assertEq(j_, pli_.getRamasseur(nombreDeJoueurs_));
        pli_.getCartes().ajouter(CardTarot.DIAMOND_QUEEN);
        assertEq(j_, pli_.getRamasseur(nombreDeJoueurs_));
        pli_ = new TrickTarot(3);
        pli_.getCartes().ajouter(CardTarot.DIAMOND_1);
        assertEq(3, pli_.getRamasseur(nombreDeJoueurs_));
        pli_.getCartes().ajouter(CardTarot.SPADE_7);
        assertEq(3, pli_.getRamasseur(nombreDeJoueurs_));
        pli_.getCartes().ajouter(CardTarot.HEART_KING);
        assertEq(3, pli_.getRamasseur(nombreDeJoueurs_));
        pli_.getCartes().ajouter(CardTarot.DIAMOND_QUEEN);
        assertEq(jThree_, pli_.getRamasseur(nombreDeJoueurs_));
        pli_ = new TrickTarot(3);
        pli_.getCartes().ajouter(CardTarot.DIAMOND_1);
        assertEq(3, pli_.getRamasseur(nombreDeJoueurs_));
        pli_.getCartes().ajouter(CardTarot.SPADE_7);
        assertEq(3, pli_.getRamasseur(nombreDeJoueurs_));
        pli_.getCartes().ajouter(CardTarot.HEART_KING);
        assertEq(3, pli_.getRamasseur(nombreDeJoueurs_));
        pli_.getCartes().ajouter(CardTarot.CLUB_QUEEN);
        assertEq(3, pli_.getRamasseur(nombreDeJoueurs_));
        pli_ = new TrickTarot(3);
        pli_.getCartes().ajouter(CardTarot.EXCUSE);
        assertEq(3, pli_.getRamasseur(nombreDeJoueurs_));
        pli_.getCartes().ajouter(CardTarot.SPADE_7);
        assertEq(j_, pli_.getRamasseur(nombreDeJoueurs_));
        pli_.getCartes().ajouter(CardTarot.HEART_KING);
        assertEq(j_, pli_.getRamasseur(nombreDeJoueurs_));
        pli_.getCartes().ajouter(CardTarot.CLUB_QUEEN);
        assertEq(j_, pli_.getRamasseur(nombreDeJoueurs_));
        pli_ = new TrickTarot(3);
        pli_.getCartes().ajouter(CardTarot.DIAMOND_1);
        assertEq(3, pli_.getRamasseur(nombreDeJoueurs_));
        pli_.getCartes().ajouter(CardTarot.SPADE_7);
        assertEq(3, pli_.getRamasseur(nombreDeJoueurs_));
        pli_.getCartes().ajouter(CardTarot.HEART_KING);
        assertEq(3, pli_.getRamasseur(nombreDeJoueurs_));
        pli_.getCartes().ajouter(CardTarot.EXCUSE);
        assertEq(3, pli_.getRamasseur(nombreDeJoueurs_));
        pli_ = new TrickTarot(3);
        pli_.getCartes().ajouter(CardTarot.TRUMP_10);
        assertEq(3, pli_.getRamasseur(nombreDeJoueurs_));
        pli_.getCartes().ajouter(CardTarot.SPADE_7);
        assertEq(3, pli_.getRamasseur(nombreDeJoueurs_));
        pli_.getCartes().ajouter(CardTarot.HEART_KING);
        assertEq(3, pli_.getRamasseur(nombreDeJoueurs_));
        pli_.getCartes().ajouter(CardTarot.EXCUSE);
        assertEq(3, pli_.getRamasseur(nombreDeJoueurs_));
        pli_ = new TrickTarot(3);
        pli_.getCartes().ajouter(CardTarot.TRUMP_10);
        assertEq(3, pli_.getRamasseur(nombreDeJoueurs_));
        pli_.getCartes().ajouter(CardTarot.SPADE_7);
        assertEq(3, pli_.getRamasseur(nombreDeJoueurs_));
        pli_.getCartes().ajouter(CardTarot.HEART_KING);
        assertEq(3, pli_.getRamasseur(nombreDeJoueurs_));
        pli_.getCartes().ajouter(CardTarot.TRUMP_4);
        assertEq(3, pli_.getRamasseur(nombreDeJoueurs_));
        pli_ = new TrickTarot(3);
        pli_.getCartes().ajouter(CardTarot.TRUMP_10);
        assertEq(3, pli_.getRamasseur(nombreDeJoueurs_));
        pli_.getCartes().ajouter(CardTarot.SPADE_7);
        assertEq(3, pli_.getRamasseur(nombreDeJoueurs_));
        pli_.getCartes().ajouter(CardTarot.HEART_KING);
        assertEq(3, pli_.getRamasseur(nombreDeJoueurs_));
        pli_.getCartes().ajouter(CardTarot.CLUB_QUEEN);
        assertEq(3, pli_.getRamasseur(nombreDeJoueurs_));
        pli_ = new TrickTarot(3);
        pli_.getCartes().ajouter(CardTarot.TRUMP_10);
        assertEq(3, pli_.getRamasseur(nombreDeJoueurs_));
        pli_.getCartes().ajouter(CardTarot.SPADE_7);
        assertEq(3, pli_.getRamasseur(nombreDeJoueurs_));
        pli_.getCartes().ajouter(CardTarot.TRUMP_14);
        assertEq(jTwo_, pli_.getRamasseur(nombreDeJoueurs_));
        pli_.getCartes().ajouter(CardTarot.EXCUSE);
        assertEq(jTwo_, pli_.getRamasseur(nombreDeJoueurs_));
        pli_ = new TrickTarot(3);
        pli_.getCartes().ajouter(CardTarot.TRUMP_10);
        assertEq(3, pli_.getRamasseur(nombreDeJoueurs_));
        pli_.getCartes().ajouter(CardTarot.TRUMP_16);
        assertEq(j_, pli_.getRamasseur(nombreDeJoueurs_));
        pli_.getCartes().ajouter(CardTarot.TRUMP_14);
        assertEq(j_, pli_.getRamasseur(nombreDeJoueurs_));
        pli_.getCartes().ajouter(CardTarot.EXCUSE);
        assertEq(j_, pli_.getRamasseur(nombreDeJoueurs_));
        pli_ = new TrickTarot(3);
        pli_.getCartes().ajouter(CardTarot.TRUMP_10);
        assertEq(3, pli_.getRamasseur(nombreDeJoueurs_));
        pli_.getCartes().ajouter(CardTarot.TRUMP_16);
        assertEq(j_, pli_.getRamasseur(nombreDeJoueurs_));
        pli_.getCartes().ajouter(CardTarot.TRUMP_14);
        assertEq(j_, pli_.getRamasseur(nombreDeJoueurs_));
        pli_.getCartes().ajouter(CardTarot.TRUMP_18);
        assertEq(jThree_, pli_.getRamasseur(nombreDeJoueurs_));
    }
    @Test
    public void joueursCoupes1(){
        int nombreDeJoueurs_ = 4;

        TrickTarot pli_ = new TrickTarot(0);
        //nombreTotal++;
        Ints joueurs_ = pli_.joueursCoupes(nombreDeJoueurs_);
        assertEq(0,joueurs_.size());
        pli_.getCartes().ajouter(CardTarot.DIAMOND_1);
        joueurs_ = pli_.joueursCoupes(nombreDeJoueurs_);
        assertEq(0,joueurs_.size());
        pli_.getCartes().ajouter(CardTarot.SPADE_7);
        joueurs_ = pli_.joueursCoupes(nombreDeJoueurs_);
        assertEq(0,joueurs_.size());
        pli_.getCartes().ajouter(CardTarot.TRUMP_11);
        joueurs_ = pli_.joueursCoupes(nombreDeJoueurs_);
        int j_ = (0 + 2) % nombreDeJoueurs_;
        assertEq(1, joueurs_.size());
        assertTrue(joueurs_.containsObj(j_));
        pli_.getCartes().ajouter(CardTarot.DIAMOND_8);
        joueurs_ = pli_.joueursCoupes(nombreDeJoueurs_);
        assertEq(1, joueurs_.size());
        assertTrue(joueurs_.containsObj(j_));
        pli_ = new TrickTarot(1);
        //nombreTotal++;
        joueurs_ = pli_.joueursCoupes(nombreDeJoueurs_);
        assertEq(0,joueurs_.size());
        pli_.getCartes().ajouter(CardTarot.DIAMOND_1);
        joueurs_ = pli_.joueursCoupes(nombreDeJoueurs_);
        assertEq(0,joueurs_.size());
        pli_.getCartes().ajouter(CardTarot.SPADE_7);
        joueurs_ = pli_.joueursCoupes(nombreDeJoueurs_);
        assertEq(0,joueurs_.size());
        pli_.getCartes().ajouter(CardTarot.TRUMP_11);
        joueurs_ = pli_.joueursCoupes(nombreDeJoueurs_);
        j_ = (1 + 2) % nombreDeJoueurs_;
        assertEq(1, joueurs_.size());
        assertTrue(joueurs_.containsObj(j_));
        pli_.getCartes().ajouter(CardTarot.DIAMOND_8);
        joueurs_ = pli_.joueursCoupes(nombreDeJoueurs_);
        assertEq(1, joueurs_.size());
        assertTrue(joueurs_.containsObj(j_));
        pli_ = new TrickTarot(2);
        //nombreTotal++;
        joueurs_ = pli_.joueursCoupes(nombreDeJoueurs_);
        assertEq(0,joueurs_.size());
        pli_.getCartes().ajouter(CardTarot.DIAMOND_1);
        joueurs_ = pli_.joueursCoupes(nombreDeJoueurs_);
        assertEq(0,joueurs_.size());
        pli_.getCartes().ajouter(CardTarot.SPADE_7);
        joueurs_ = pli_.joueursCoupes(nombreDeJoueurs_);
        assertEq(0,joueurs_.size());
        pli_.getCartes().ajouter(CardTarot.TRUMP_11);
        joueurs_ = pli_.joueursCoupes(nombreDeJoueurs_);
        j_ = (2 + 2) % nombreDeJoueurs_;
        assertEq(1, joueurs_.size());
        assertTrue(joueurs_.containsObj(j_));
        pli_.getCartes().ajouter(CardTarot.DIAMOND_8);
        joueurs_ = pli_.joueursCoupes(nombreDeJoueurs_);
        assertEq(1, joueurs_.size());
        assertTrue(joueurs_.containsObj(j_));
        pli_ = new TrickTarot(3);
        //nombreTotal++;
        joueurs_ = pli_.joueursCoupes(nombreDeJoueurs_);
        assertEq(0,joueurs_.size());
        pli_.getCartes().ajouter(CardTarot.DIAMOND_1);
        joueurs_ = pli_.joueursCoupes(nombreDeJoueurs_);
        assertEq(0,joueurs_.size());
        pli_.getCartes().ajouter(CardTarot.SPADE_7);
        joueurs_ = pli_.joueursCoupes(nombreDeJoueurs_);
        assertEq(0,joueurs_.size());
        pli_.getCartes().ajouter(CardTarot.TRUMP_11);
        joueurs_ = pli_.joueursCoupes(nombreDeJoueurs_);
        j_ = (3 + 2) % nombreDeJoueurs_;
        assertEq(1, joueurs_.size());
        assertTrue(joueurs_.containsObj(j_));
        pli_.getCartes().ajouter(CardTarot.DIAMOND_8);
        joueurs_ = pli_.joueursCoupes(nombreDeJoueurs_);
        assertEq(1, joueurs_.size());
        assertTrue(joueurs_.containsObj(j_));
    }
    @Test
    public void joueursCoupes2(){
        int nombreDeJoueurs_ = 4;

        TrickTarot pli_ = new TrickTarot(0);
        //nombreTotal++;
        Ints joueurs_ = pli_.joueursCoupes(nombreDeJoueurs_);
        assertEq(0,joueurs_.size());
        pli_.getCartes().ajouter(CardTarot.TRUMP_14);
        joueurs_ = pli_.joueursCoupes(nombreDeJoueurs_);
        assertEq(0,joueurs_.size());
        pli_.getCartes().ajouter(CardTarot.SPADE_7);
        joueurs_ = pli_.joueursCoupes(nombreDeJoueurs_);
        int j_ = (0 + 1) % nombreDeJoueurs_;
        assertEq(1, joueurs_.size());
        assertTrue(joueurs_.containsObj(j_));
        pli_.getCartes().ajouter(CardTarot.TRUMP_11);
        joueurs_ = pli_.joueursCoupes(nombreDeJoueurs_);
        assertEq(1, joueurs_.size());
        assertTrue(joueurs_.containsObj(j_));
        pli_.getCartes().ajouter(CardTarot.DIAMOND_8);
        joueurs_ = pli_.joueursCoupes(nombreDeJoueurs_);
        int jTwo_ = (0 + 3) % nombreDeJoueurs_;
        assertEq(2, joueurs_.size());
        assertTrue(joueurs_.containsObj(j_));
        assertTrue(joueurs_.containsObj(jTwo_));
        pli_ = new TrickTarot(1);
        //nombreTotal++;
        joueurs_ = pli_.joueursCoupes(nombreDeJoueurs_);
        assertEq(0,joueurs_.size());
        pli_.getCartes().ajouter(CardTarot.TRUMP_14);
        joueurs_ = pli_.joueursCoupes(nombreDeJoueurs_);
        assertEq(0,joueurs_.size());
        pli_.getCartes().ajouter(CardTarot.SPADE_7);
        joueurs_ = pli_.joueursCoupes(nombreDeJoueurs_);
        j_ = (1 + 1) % nombreDeJoueurs_;
        assertEq(1, joueurs_.size());
        assertTrue(joueurs_.containsObj(j_));
        pli_.getCartes().ajouter(CardTarot.TRUMP_11);
        joueurs_ = pli_.joueursCoupes(nombreDeJoueurs_);
        assertEq(1, joueurs_.size());
        assertTrue(joueurs_.containsObj(j_));
        pli_.getCartes().ajouter(CardTarot.DIAMOND_8);
        joueurs_ = pli_.joueursCoupes(nombreDeJoueurs_);
        jTwo_ = (1 + 3) % nombreDeJoueurs_;
        assertEq(2, joueurs_.size());
        assertTrue(joueurs_.containsObj(j_));
        assertTrue(joueurs_.containsObj(jTwo_));
        pli_ = new TrickTarot(2);
        //nombreTotal++;
        joueurs_ = pli_.joueursCoupes(nombreDeJoueurs_);
        assertEq(0,joueurs_.size());
        pli_.getCartes().ajouter(CardTarot.TRUMP_14);
        joueurs_ = pli_.joueursCoupes(nombreDeJoueurs_);
        assertEq(0,joueurs_.size());
        pli_.getCartes().ajouter(CardTarot.SPADE_7);
        joueurs_ = pli_.joueursCoupes(nombreDeJoueurs_);
        j_ = (2 + 1) % nombreDeJoueurs_;
        assertEq(1, joueurs_.size());
        assertTrue(joueurs_.containsObj(j_));
        pli_.getCartes().ajouter(CardTarot.TRUMP_11);
        joueurs_ = pli_.joueursCoupes(nombreDeJoueurs_);
        assertEq(1, joueurs_.size());
        assertTrue(joueurs_.containsObj(j_));
        pli_.getCartes().ajouter(CardTarot.DIAMOND_8);
        joueurs_ = pli_.joueursCoupes(nombreDeJoueurs_);
        jTwo_ = (2 + 3) % nombreDeJoueurs_;
        assertEq(2, joueurs_.size());
        assertTrue(joueurs_.containsObj(j_));
        assertTrue(joueurs_.containsObj(jTwo_));
        pli_ = new TrickTarot(3);
        //nombreTotal++;
        joueurs_ = pli_.joueursCoupes(nombreDeJoueurs_);
        assertEq(0,joueurs_.size());
        pli_.getCartes().ajouter(CardTarot.TRUMP_14);
        joueurs_ = pli_.joueursCoupes(nombreDeJoueurs_);
        assertEq(0,joueurs_.size());
        pli_.getCartes().ajouter(CardTarot.SPADE_7);
        joueurs_ = pli_.joueursCoupes(nombreDeJoueurs_);
        j_ = (3 + 1) % nombreDeJoueurs_;
        assertEq(1, joueurs_.size());
        assertTrue(joueurs_.containsObj(j_));
        pli_.getCartes().ajouter(CardTarot.TRUMP_11);
        joueurs_ = pli_.joueursCoupes(nombreDeJoueurs_);
        assertEq(1, joueurs_.size());
        assertTrue(joueurs_.containsObj(j_));
        pli_.getCartes().ajouter(CardTarot.DIAMOND_8);
        joueurs_ = pli_.joueursCoupes(nombreDeJoueurs_);
        jTwo_ = (3 + 3) % nombreDeJoueurs_;
        assertEq(2, joueurs_.size());
        assertTrue(joueurs_.containsObj(j_));
        assertTrue(joueurs_.containsObj(jTwo_));
    }
    @Test
    public void joueursDefausses1(){
        int nombreDeJoueurs_ = 4;

        TrickTarot pli_ = new TrickTarot(0);
        //nombreTotal++;
        pli_.getCartes().ajouter(CardTarot.DIAMOND_1);
        pli_.getCartes().ajouter(CardTarot.SPADE_7);
        pli_.getCartes().ajouter(CardTarot.TRUMP_11);
        pli_.getCartes().ajouter(CardTarot.DIAMOND_8);
        Ints joueurs_ = pli_.joueursDefausses(nombreDeJoueurs_);
        int j_ = (0 + 1) % nombreDeJoueurs_;
        assertEq(1, joueurs_.size());
        assertTrue(joueurs_.containsObj(j_));
        pli_ = new TrickTarot(1);
        //nombreTotal++;
        pli_.getCartes().ajouter(CardTarot.DIAMOND_1);
        pli_.getCartes().ajouter(CardTarot.SPADE_7);
        pli_.getCartes().ajouter(CardTarot.TRUMP_11);
        pli_.getCartes().ajouter(CardTarot.DIAMOND_8);
        joueurs_ = pli_.joueursDefausses(nombreDeJoueurs_);
        j_ = (1 + 1) % nombreDeJoueurs_;
        assertEq(1, joueurs_.size());
        assertTrue(joueurs_.containsObj(j_));
        pli_ = new TrickTarot(2);
        //nombreTotal++;
        pli_.getCartes().ajouter(CardTarot.DIAMOND_1);
        pli_.getCartes().ajouter(CardTarot.SPADE_7);
        pli_.getCartes().ajouter(CardTarot.TRUMP_11);
        pli_.getCartes().ajouter(CardTarot.DIAMOND_8);
        joueurs_ = pli_.joueursDefausses(nombreDeJoueurs_);
        j_ = (2 + 1) % nombreDeJoueurs_;
        assertEq(1, joueurs_.size());
        assertTrue(joueurs_.containsObj(j_));
        pli_ = new TrickTarot(3);
        //nombreTotal++;
        pli_.getCartes().ajouter(CardTarot.DIAMOND_1);
        pli_.getCartes().ajouter(CardTarot.SPADE_7);
        pli_.getCartes().ajouter(CardTarot.TRUMP_11);
        pli_.getCartes().ajouter(CardTarot.DIAMOND_8);
        joueurs_ = pli_.joueursDefausses(nombreDeJoueurs_);
        j_ = (3 + 1) % nombreDeJoueurs_;
        assertEq(1, joueurs_.size());
        assertTrue(joueurs_.containsObj(j_));
    }
    @Test
    public void joueursDefausses2(){
        int nombreDeJoueurs_ = 4;
        TrickTarot pli_ = new TrickTarot(0);
        //nombreTotal++;
        pli_.getCartes().ajouter(CardTarot.TRUMP_14);
        pli_.getCartes().ajouter(CardTarot.SPADE_7);
        pli_.getCartes().ajouter(CardTarot.TRUMP_11);
        pli_.getCartes().ajouter(CardTarot.DIAMOND_8);
        Ints joueurs_ = pli_.joueursDefausses(nombreDeJoueurs_);
        int j_ = (0 + 1) % nombreDeJoueurs_;
        int jTwo_ = (0 + 3) % nombreDeJoueurs_;
        assertEq(2, joueurs_.size());
        assertTrue(joueurs_.containsObj(j_));
        assertTrue(joueurs_.containsObj(jTwo_));
        pli_ = new TrickTarot(1);
        //nombreTotal++;
        pli_.getCartes().ajouter(CardTarot.TRUMP_14);
        pli_.getCartes().ajouter(CardTarot.SPADE_7);
        pli_.getCartes().ajouter(CardTarot.TRUMP_11);
        pli_.getCartes().ajouter(CardTarot.DIAMOND_8);
        joueurs_ = pli_.joueursDefausses(nombreDeJoueurs_);
        j_ = (1 + 1) % nombreDeJoueurs_;
        jTwo_ = (1 + 3) % nombreDeJoueurs_;
        assertEq(2, joueurs_.size());
        assertTrue(joueurs_.containsObj(j_));
        assertTrue(joueurs_.containsObj(jTwo_));
        pli_ = new TrickTarot(2);
        //nombreTotal++;
        pli_.getCartes().ajouter(CardTarot.TRUMP_14);
        pli_.getCartes().ajouter(CardTarot.SPADE_7);
        pli_.getCartes().ajouter(CardTarot.TRUMP_11);
        pli_.getCartes().ajouter(CardTarot.DIAMOND_8);
        joueurs_ = pli_.joueursDefausses(nombreDeJoueurs_);
        j_ = (2 + 1) % nombreDeJoueurs_;
        jTwo_ = (2 + 3) % nombreDeJoueurs_;
        assertEq(2, joueurs_.size());
        assertTrue(joueurs_.containsObj(j_));
        assertTrue(joueurs_.containsObj(jTwo_));
        pli_ = new TrickTarot(3);
        //nombreTotal++;
        pli_.getCartes().ajouter(CardTarot.TRUMP_14);
        pli_.getCartes().ajouter(CardTarot.SPADE_7);
        pli_.getCartes().ajouter(CardTarot.TRUMP_11);
        pli_.getCartes().ajouter(CardTarot.DIAMOND_8);
        joueurs_ = pli_.joueursDefausses(nombreDeJoueurs_);
        j_ = (3 + 1) % nombreDeJoueurs_;
        jTwo_ = (3 + 3) % nombreDeJoueurs_;
        assertEq(2, joueurs_.size());
        assertTrue(joueurs_.containsObj(j_));
        assertTrue(joueurs_.containsObj(jTwo_));
    }
}