package cards.tarot;
import static cards.tarot.EquallableTarotUtil.assertEq;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import cards.consts.Suit;
import cards.tarot.enumerations.CardTarot;
import cards.tarot.enumerations.DealingTarot;
import cards.tarot.enumerations.Handfuls;
import code.util.EnumList;
import code.util.EnumMap;
import code.util.EqList;


public class HandTarotTest {
    @Test
    public void pileBase1(){
        HandTarot main_ = HandTarot.pileBase();
        assertEq(CardTarot.values().length-1, main_.total());
        assertTrue(!main_.contient(CardTarot.WHITE));
    }
    @Test
    public void trierParForceEnCours1(){
        HandTarot main_ = new HandTarot();
        main_.ajouter(CardTarot.HEART_10);
        main_.ajouter(CardTarot.HEART_1);
        main_.ajouter(CardTarot.HEART_KING);
        HandTarot resAtt_ = new HandTarot();
        resAtt_.ajouter(CardTarot.HEART_KING);
        resAtt_.ajouter(CardTarot.HEART_10);
        resAtt_.ajouter(CardTarot.HEART_1);
        assertNotSame(resAtt_,main_);
        main_.trierParForceEnCours(Suit.HEART);
        assertEq(resAtt_, main_);
        main_ = new HandTarot();
        main_.ajouter(CardTarot.TRUMP_20);
        main_.ajouter(CardTarot.TRUMP_6);
        main_.ajouter(CardTarot.TRUMP_11);
        resAtt_ = new HandTarot();
        resAtt_.ajouter(CardTarot.TRUMP_20);
        resAtt_.ajouter(CardTarot.TRUMP_11);
        resAtt_.ajouter(CardTarot.TRUMP_6);
        main_.trierParForceEnCours(Suit.HEART);
        assertEq(resAtt_, main_);
        main_ = new HandTarot();
        main_.ajouter(CardTarot.TRUMP_20);
        main_.ajouter(CardTarot.TRUMP_6);
        main_.ajouter(CardTarot.TRUMP_11);
        main_.trierParForceEnCours(Suit.TRUMP);
        assertEq(resAtt_, main_);
        main_ = new HandTarot();
        main_.ajouter(CardTarot.HEART_10);
        main_.ajouter(CardTarot.HEART_1);
        main_.ajouter(CardTarot.HEART_KING);
        resAtt_ = new HandTarot();
        resAtt_.ajouter(CardTarot.HEART_1);
        resAtt_.ajouter(CardTarot.HEART_10);
        resAtt_.ajouter(CardTarot.HEART_KING);
        main_.trierParForceEcart(Suit.HEART);
        assertEq(resAtt_, main_);
        main_ = new HandTarot();
        main_.ajouter(CardTarot.HEART_10);
        main_.ajouter(CardTarot.HEART_1);
        main_.ajouter(CardTarot.HEART_KING);
        main_.ajouter(CardTarot.DIAMOND_JACK);
        main_.ajouter(CardTarot.DIAMOND_1);
        main_.ajouter(CardTarot.DIAMOND_QUEEN);
        resAtt_ = new HandTarot();
        resAtt_.ajouter(CardTarot.HEART_KING);
        resAtt_.ajouter(CardTarot.DIAMOND_QUEEN);
        resAtt_.ajouter(CardTarot.DIAMOND_JACK);
        resAtt_.ajouter(CardTarot.HEART_10);
        resAtt_.ajouter(CardTarot.DIAMOND_1);
        resAtt_.ajouter(CardTarot.HEART_1);
        main_.trierParForceEnCours(Suit.UNDEFINED);
        assertEq(resAtt_, main_);
        main_ = new HandTarot();
        main_.ajouter(CardTarot.HEART_10);
        main_.ajouter(CardTarot.HEART_1);
        main_.ajouter(CardTarot.HEART_KING);
        main_.ajouter(CardTarot.DIAMOND_JACK);
        main_.ajouter(CardTarot.DIAMOND_1);
        main_.ajouter(CardTarot.DIAMOND_QUEEN);
        resAtt_ = new HandTarot();
        resAtt_.ajouter(CardTarot.HEART_10);
        resAtt_.ajouter(CardTarot.HEART_1);
        resAtt_.ajouter(CardTarot.HEART_KING);
        resAtt_.ajouter(CardTarot.DIAMOND_JACK);
        resAtt_.ajouter(CardTarot.DIAMOND_1);
        resAtt_.ajouter(CardTarot.DIAMOND_QUEEN);
        main_.trierParForceEnCours(Suit.TRUMP);
        assertEq(resAtt_, main_);
    }
    @Test
    public void couleurComplete1(){
        Suit couleur_ = Suit.HEART;
        HandTarot main_ = HandTarot.couleurComplete(couleur_);
        HandTarot resAtt_ = new HandTarot();
        resAtt_.ajouter(CardTarot.HEART_KING);
        resAtt_.ajouter(CardTarot.HEART_QUEEN);
        resAtt_.ajouter(CardTarot.HEART_KNIGHT);
        resAtt_.ajouter(CardTarot.HEART_JACK);
        resAtt_.ajouter(CardTarot.HEART_10);
        resAtt_.ajouter(CardTarot.HEART_9);
        resAtt_.ajouter(CardTarot.HEART_8);
        resAtt_.ajouter(CardTarot.HEART_7);
        resAtt_.ajouter(CardTarot.HEART_6);
        resAtt_.ajouter(CardTarot.HEART_5);
        resAtt_.ajouter(CardTarot.HEART_4);
        resAtt_.ajouter(CardTarot.HEART_3);
        resAtt_.ajouter(CardTarot.HEART_2);
        resAtt_.ajouter(CardTarot.HEART_1);
        assertEq(resAtt_,main_);
    }
    @Test
    public void atoutsSansExcuse1(){
        HandTarot main_ = HandTarot.atoutsSansExcuse();
        HandTarot resAtt_ = new HandTarot();
        resAtt_.ajouter(CardTarot.TRUMP_21);
        resAtt_.ajouter(CardTarot.TRUMP_20);
        resAtt_.ajouter(CardTarot.TRUMP_19);
        resAtt_.ajouter(CardTarot.TRUMP_18);
        resAtt_.ajouter(CardTarot.TRUMP_17);
        resAtt_.ajouter(CardTarot.TRUMP_16);
        resAtt_.ajouter(CardTarot.TRUMP_15);
        resAtt_.ajouter(CardTarot.TRUMP_14);
        resAtt_.ajouter(CardTarot.TRUMP_13);
        resAtt_.ajouter(CardTarot.TRUMP_12);
        resAtt_.ajouter(CardTarot.TRUMP_11);
        resAtt_.ajouter(CardTarot.TRUMP_10);
        resAtt_.ajouter(CardTarot.TRUMP_9);
        resAtt_.ajouter(CardTarot.TRUMP_8);
        resAtt_.ajouter(CardTarot.TRUMP_7);
        resAtt_.ajouter(CardTarot.TRUMP_6);
        resAtt_.ajouter(CardTarot.TRUMP_5);
        resAtt_.ajouter(CardTarot.TRUMP_4);
        resAtt_.ajouter(CardTarot.TRUMP_3);
        resAtt_.ajouter(CardTarot.TRUMP_2);
        resAtt_.ajouter(CardTarot.TRUMP_1);
        assertEq(resAtt_,main_);
    }
    @Test
    public void figuresCouleurs1Test(){
        assertTrue(CardTarot.HEART_KING.isCharacter());
    }
    @Test
    public void figuresCouleurs2Test(){
        assertTrue(CardTarot.HEART_QUEEN.isCharacter());
    }
    @Test
    public void figuresCouleurs3Test(){
        assertTrue(CardTarot.HEART_KNIGHT.isCharacter());
    }
    @Test
    public void figuresCouleurs4Test(){
        assertTrue(CardTarot.HEART_JACK.isCharacter());
    }
    @Test
    public void figuresCouleurs5Test(){
        assertTrue(CardTarot.SPADE_KING.isCharacter());
    }
    @Test
    public void figuresCouleurs6Test(){
        assertTrue(CardTarot.SPADE_QUEEN.isCharacter());
    }
    @Test
    public void figuresCouleurs7Test(){
        assertTrue(CardTarot.SPADE_KNIGHT.isCharacter());
    }
    @Test
    public void figuresCouleurs8Test(){
        assertTrue(CardTarot.SPADE_JACK.isCharacter());
    }
    @Test
    public void figuresCouleurs9Test(){
        assertTrue(CardTarot.DIAMOND_KING.isCharacter());
    }
    @Test
    public void figuresCouleurs10Test(){
        assertTrue(CardTarot.DIAMOND_QUEEN.isCharacter());
    }
    @Test
    public void figuresCouleurs11Test(){
        assertTrue(CardTarot.DIAMOND_KNIGHT.isCharacter());
    }
    @Test
    public void figuresCouleurs12Test(){
        assertTrue(CardTarot.DIAMOND_JACK.isCharacter());
    }
    @Test
    public void figuresCouleurs13Test(){
        assertTrue(CardTarot.CLUB_KING.isCharacter());
    }
    @Test
    public void figuresCouleurs14Test(){
        assertTrue(CardTarot.CLUB_QUEEN.isCharacter());
    }
    @Test
    public void figuresCouleurs15Test(){
        assertTrue(CardTarot.CLUB_KNIGHT.isCharacter());
    }
    @Test
    public void figuresCouleurs16Test(){
        assertTrue(CardTarot.CLUB_JACK.isCharacter());
    }
    @Test
    public void figuresCouleurs17Test(){
        assertTrue(HandTarot.figuesCouleurs().contient(CardTarot.HEART_KING));
    }
    @Test
    public void figuresCouleurs18Test(){
        assertTrue(HandTarot.figuesCouleurs().contient(CardTarot.HEART_QUEEN));
    }
    @Test
    public void figuresCouleurs19Test(){
        assertTrue(HandTarot.figuesCouleurs().contient(CardTarot.HEART_KNIGHT));
    }
    @Test
    public void figuresCouleurs20Test(){
        assertTrue(HandTarot.figuesCouleurs().contient(CardTarot.HEART_JACK));
    }
    @Test
    public void figuresCouleurs21Test(){
        assertTrue(HandTarot.figuesCouleurs().contient(CardTarot.SPADE_KING));
    }
    @Test
    public void figuresCouleurs22Test(){
        assertTrue(HandTarot.figuesCouleurs().contient(CardTarot.SPADE_QUEEN));
    }
    @Test
    public void figuresCouleurs23Test(){
        assertTrue(HandTarot.figuesCouleurs().contient(CardTarot.SPADE_KNIGHT));
    }
    @Test
    public void figuresCouleurs24Test(){
        assertTrue(HandTarot.figuesCouleurs().contient(CardTarot.SPADE_JACK));
    }
    @Test
    public void figuresCouleurs25Test(){
        assertTrue(HandTarot.figuesCouleurs().contient(CardTarot.DIAMOND_KING));
    }
    @Test
    public void figuresCouleurs26Test(){
        assertTrue(HandTarot.figuesCouleurs().contient(CardTarot.DIAMOND_QUEEN));
    }
    @Test
    public void figuresCouleurs27Test(){
        assertTrue(HandTarot.figuesCouleurs().contient(CardTarot.DIAMOND_KNIGHT));
    }
    @Test
    public void figuresCouleurs28Test(){
        assertTrue(HandTarot.figuesCouleurs().contient(CardTarot.DIAMOND_JACK));
    }
    @Test
    public void figuresCouleurs29Test(){
        assertTrue(HandTarot.figuesCouleurs().contient(CardTarot.CLUB_KING));
    }
    @Test
    public void figuresCouleurs30Test(){
        assertTrue(HandTarot.figuesCouleurs().contient(CardTarot.CLUB_QUEEN));
    }
    @Test
    public void figuresCouleurs31Test(){
        assertTrue(HandTarot.figuesCouleurs().contient(CardTarot.CLUB_KNIGHT));
    }
    @Test
    public void figuresCouleurs32Test(){
        assertTrue(HandTarot.figuesCouleurs().contient(CardTarot.CLUB_JACK));
    }


    private HandTarot trier(){
        HandTarot main_ = new HandTarot();
        main_.ajouter(CardTarot.SPADE_KING);
        main_.ajouter(CardTarot.HEART_1);
        main_.ajouter(CardTarot.TRUMP_12);
        main_.ajouter(CardTarot.EXCUSE);
        main_.ajouter(CardTarot.SPADE_JACK);
        main_.ajouter(CardTarot.HEART_9);
        return main_;
    }
    @Test
    public void trier1(){
        HandTarot main_ = trier();
        EnumList<Suit> couleurs_ = new EnumList<Suit>();
        couleurs_.add(Suit.SPADE);
        couleurs_.add(Suit.HEART);
        couleurs_.add(Suit.TRUMP);
        main_.trier(couleurs_, true);
        HandTarot resAtt_ = new HandTarot();
        resAtt_.ajouter(CardTarot.SPADE_KING);
        resAtt_.ajouter(CardTarot.SPADE_JACK);
        resAtt_.ajouter(CardTarot.HEART_9);
        resAtt_.ajouter(CardTarot.HEART_1);
        resAtt_.ajouter(CardTarot.EXCUSE);
        resAtt_.ajouter(CardTarot.TRUMP_12);
        assertEq(resAtt_, main_);
        main_ = trier();
        main_.trier(couleurs_, false);
        resAtt_ = new HandTarot();
        resAtt_.ajouter(CardTarot.SPADE_JACK);
        resAtt_.ajouter(CardTarot.SPADE_KING);
        resAtt_.ajouter(CardTarot.HEART_1);
        resAtt_.ajouter(CardTarot.HEART_9);
        resAtt_.ajouter(CardTarot.TRUMP_12);
        resAtt_.ajouter(CardTarot.EXCUSE);
        assertEq(resAtt_, main_);
    }
    @Test
    public void couleur1(){
        HandTarot main_ = new HandTarot();
        main_.ajouter(CardTarot.SPADE_KING);
        main_.ajouter(CardTarot.SPADE_JACK);
        main_.ajouter(CardTarot.HEART_9);
        main_.ajouter(CardTarot.HEART_1);
        main_.ajouter(CardTarot.TRUMP_12);
        main_.ajouter(CardTarot.EXCUSE);
        HandTarot res_ = new HandTarot();
        res_.ajouter(CardTarot.SPADE_KING);
        res_.ajouter(CardTarot.SPADE_JACK);
        assertEq(res_, main_.couleur(Suit.SPADE));
        res_ = new HandTarot();
        res_.ajouter(CardTarot.HEART_9);
        res_.ajouter(CardTarot.HEART_1);
        assertEq(res_, main_.couleur(Suit.HEART));
        res_ = new HandTarot();
        assertEq(res_, main_.couleur(Suit.DIAMOND));
        assertEq(res_, main_.couleur(Suit.CLUB));
        res_.ajouter(CardTarot.TRUMP_12);
        assertEq(res_, main_.couleur(Suit.TRUMP));
        res_ = new HandTarot();
        res_.ajouter(CardTarot.EXCUSE);
        assertEq(res_, main_.couleur(Suit.UNDEFINED));
    }
    @Test
    public void couleurs1(){
        HandTarot main_ = new HandTarot();
        main_.ajouter(CardTarot.SPADE_KING);
        main_.ajouter(CardTarot.SPADE_JACK);
        main_.ajouter(CardTarot.HEART_9);
        main_.ajouter(CardTarot.HEART_1);
        main_.ajouter(CardTarot.TRUMP_12);
        main_.ajouter(CardTarot.EXCUSE);
        EnumMap<Suit,HandTarot> hash_ = new EnumMap<Suit,HandTarot>();
        HandTarot couleur_ = new HandTarot();
        couleur_.ajouter(CardTarot.SPADE_KING);
        couleur_.ajouter(CardTarot.SPADE_JACK);
        hash_.put(Suit.SPADE, couleur_);
        couleur_ = new HandTarot();
        couleur_.ajouter(CardTarot.HEART_9);
        couleur_.ajouter(CardTarot.HEART_1);
        hash_.put(Suit.HEART, couleur_);
        couleur_ = new HandTarot();
        couleur_.ajouter(CardTarot.TRUMP_12);
        hash_.put(Suit.TRUMP, couleur_);
        couleur_ = new HandTarot();
        couleur_.ajouter(CardTarot.EXCUSE);
        hash_.put(Suit.UNDEFINED, couleur_);
        couleur_ = new HandTarot();
        hash_.put(Suit.DIAMOND, couleur_);
        hash_.put(Suit.CLUB, couleur_);
        EnumMap<Suit,HandTarot> res_ = main_.couleurs();
        assertEq(hash_.getVal(Suit.HEART), res_.getVal(Suit.HEART));
        assertEq(hash_.getVal(Suit.DIAMOND), res_.getVal(Suit.DIAMOND));
        assertEq(hash_.getVal(Suit.SPADE), res_.getVal(Suit.SPADE));
        assertEq(hash_.getVal(Suit.CLUB), res_.getVal(Suit.CLUB));
        assertEq(hash_.getVal(Suit.TRUMP), res_.getVal(Suit.TRUMP));
        assertEq(hash_.getVal(CardTarot.EXCUSE.couleur()), res_.getVal(CardTarot.EXCUSE.couleur()));
    }
    @Test
    public void eclaterDebutPartie1(){
        HandTarot main_ = new HandTarot();
        main_.ajouter(CardTarot.HEART_KING);
        main_.ajouter(CardTarot.HEART_10);
        main_.ajouter(CardTarot.HEART_9);
        main_.ajouter(CardTarot.HEART_7);
        EqList<HandTarot> suites_ = new EqList<HandTarot>();
        HandTarot suite_ = new HandTarot();
        suite_.ajouter(CardTarot.HEART_KING);
        suites_.add(suite_);
        suite_ = new HandTarot();
        suite_.ajouter(CardTarot.HEART_10);
        suite_.ajouter(CardTarot.HEART_9);
        suites_.add(suite_);
        suite_ = new HandTarot();
        suite_.ajouter(CardTarot.HEART_7);
        suites_.add(suite_);
//        assertEq(suites_,main_.eclaterDebutPartie());
        EqList<HandTarot> res_ = main_.eclaterDebutPartie();
        assertEq(3, res_.size());
        assertEq(suites_.first(), res_.first());
        assertEq(suites_.get(1), res_.get(1));
        assertEq(suites_.last(), res_.last());
    }
    @Test
    public void eclaterDebutPartie2(){
        HandTarot main_ = new HandTarot();
        main_.ajouter(CardTarot.HEART_KING);
        main_.ajouter(CardTarot.HEART_10);
        main_.ajouter(CardTarot.HEART_9);
        main_.ajouter(CardTarot.HEART_7);
        EqList<HandTarot> suites_ = new EqList<HandTarot>();
        HandTarot suite_ = new HandTarot();
        suite_.ajouter(CardTarot.HEART_KING);
        suites_.add(suite_);
        suite_ = new HandTarot();
        suite_.ajouter(CardTarot.HEART_10);
        suite_.ajouter(CardTarot.HEART_9);
        suites_.add(suite_);
        suite_ = new HandTarot();
        suite_.ajouter(CardTarot.HEART_7);
        suites_.add(suite_);
        HandTarot cartesJouees_ = new HandTarot();
        cartesJouees_.ajouter(CardTarot.HEART_8);
        cartesJouees_.ajouter(CardTarot.HEART_JACK);
        suites_ = new EqList<HandTarot>();
        suite_ = new HandTarot();
        suite_.ajouter(CardTarot.HEART_KING);
        suites_.add(suite_);
        suite_ = new HandTarot();
        suite_.ajouter(CardTarot.HEART_10);
        suite_.ajouter(CardTarot.HEART_9);
        suite_.ajouter(CardTarot.HEART_7);
        suites_.add(suite_);
        main_ = new HandTarot();
        main_.ajouter(CardTarot.TRUMP_17);
        main_.ajouter(CardTarot.TRUMP_15);
        main_.ajouter(CardTarot.TRUMP_14);
        main_.ajouter(CardTarot.TRUMP_8);
        suites_ = new EqList<HandTarot>();
        suite_ = new HandTarot();
        suite_.ajouter(CardTarot.TRUMP_17);
        suites_.add(suite_);
        suite_ = new HandTarot();
        suite_.ajouter(CardTarot.TRUMP_15);
        suite_.ajouter(CardTarot.TRUMP_14);
        suites_.add(suite_);
        suite_ = new HandTarot();
        suite_.ajouter(CardTarot.TRUMP_8);
        suites_.add(suite_);
//        assertEq(suites_,main_.eclaterDebutPartie());
        EqList<HandTarot> res_ = main_.eclaterDebutPartie();
        assertEq(3, res_.size());
        assertEq(suites_.first(), res_.first());
        assertEq(suites_.get(1), res_.get(1));
        assertEq(suites_.last(), res_.last());
    }
    @Test
    public void eclaterEnCours1(){
        HandTarot main_ = new HandTarot();
        main_.ajouter(CardTarot.HEART_KING);
        main_.ajouter(CardTarot.HEART_10);
        main_.ajouter(CardTarot.HEART_9);
        main_.ajouter(CardTarot.HEART_7);
        EqList<HandTarot> suites_ = new EqList<HandTarot>();
        HandTarot suite_ = new HandTarot();
        suite_.ajouter(CardTarot.HEART_KING);
        suites_.add(suite_);
        suite_ = new HandTarot();
        suite_.ajouter(CardTarot.HEART_10);
        suite_.ajouter(CardTarot.HEART_9);
        suites_.add(suite_);
        suite_ = new HandTarot();
        suite_.ajouter(CardTarot.HEART_7);
        suites_.add(suite_);
        HandTarot cartesJouees_ = new HandTarot();
        cartesJouees_.ajouter(CardTarot.HEART_8);
        cartesJouees_.ajouter(CardTarot.HEART_JACK);
        suites_ = new EqList<HandTarot>();
        suite_ = new HandTarot();
        suite_.ajouter(CardTarot.HEART_KING);
        suites_.add(suite_);
        suite_ = new HandTarot();
        suite_.ajouter(CardTarot.HEART_10);
        suite_.ajouter(CardTarot.HEART_9);
        suite_.ajouter(CardTarot.HEART_7);
        suites_.add(suite_);
        EqList<HandTarot> res_ = main_.eclaterEnCours(cartesJouees_.couleurs(), Suit.HEART);
        assertEq(2, res_.size());
        assertEq(suites_.first(), res_.first());
        assertEq(suites_.last(), res_.last());
//        assertEq(suites_,main_.eclaterEnCours(cartesJouees_.couleurs(), Suit.HEART));
    }
    @Test
    public void eclaterEnCours2(){
        HandTarot main_ = new HandTarot();
        main_.ajouter(CardTarot.HEART_KING);
        main_.ajouter(CardTarot.HEART_10);
        main_.ajouter(CardTarot.HEART_9);
        main_.ajouter(CardTarot.HEART_7);
        EqList<HandTarot> suites_ = new EqList<HandTarot>();
        HandTarot suite_ = new HandTarot();
        suite_.ajouter(CardTarot.HEART_KING);
        suites_.add(suite_);
        suite_ = new HandTarot();
        suite_.ajouter(CardTarot.HEART_10);
        suite_.ajouter(CardTarot.HEART_9);
        suites_.add(suite_);
        suite_ = new HandTarot();
        suite_.ajouter(CardTarot.HEART_7);
        suites_.add(suite_);
        HandTarot cartesJouees_ = new HandTarot();
        cartesJouees_.ajouter(CardTarot.HEART_8);
        cartesJouees_.ajouter(CardTarot.HEART_JACK);
        suites_ = new EqList<HandTarot>();
        suite_ = new HandTarot();
        suite_.ajouter(CardTarot.HEART_KING);
        suites_.add(suite_);
        suite_ = new HandTarot();
        suite_.ajouter(CardTarot.HEART_10);
        suite_.ajouter(CardTarot.HEART_9);
        suite_.ajouter(CardTarot.HEART_7);
        suites_.add(suite_);
        main_ = new HandTarot();
        main_.ajouter(CardTarot.TRUMP_17);
        main_.ajouter(CardTarot.TRUMP_15);
        main_.ajouter(CardTarot.TRUMP_14);
        main_.ajouter(CardTarot.TRUMP_8);
        suites_ = new EqList<HandTarot>();
        suite_ = new HandTarot();
        suite_.ajouter(CardTarot.TRUMP_17);
        suites_.add(suite_);
        suite_ = new HandTarot();
        suite_.ajouter(CardTarot.TRUMP_15);
        suite_.ajouter(CardTarot.TRUMP_14);
        suites_.add(suite_);
        suite_ = new HandTarot();
        suite_.ajouter(CardTarot.TRUMP_8);
        suites_.add(suite_);
        cartesJouees_ = new HandTarot();
        cartesJouees_.ajouter(CardTarot.TRUMP_16);
        cartesJouees_.ajouter(CardTarot.TRUMP_9);
        suites_ = new EqList<HandTarot>();
        suite_ = new HandTarot();
        suite_.ajouter(CardTarot.TRUMP_17);
        suite_.ajouter(CardTarot.TRUMP_15);
        suite_.ajouter(CardTarot.TRUMP_14);
        suites_.add(suite_);
        suite_ = new HandTarot();
        suite_.ajouter(CardTarot.TRUMP_8);
        suites_.add(suite_);
        EqList<HandTarot> res_ = main_.eclaterEnCours(cartesJouees_.couleurs(), Suit.HEART);
        assertEq(2, res_.size());
        assertEq(suites_.first(), res_.first());
        assertEq(suites_.last(), res_.last());
        res_ = main_.eclaterEnCours(cartesJouees_.couleurs(), Suit.DIAMOND);
        assertEq(2, res_.size());
        assertEq(suites_.first(), res_.first());
        assertEq(suites_.last(), res_.last());
        res_ = main_.eclaterEnCours(cartesJouees_.couleurs(), Suit.SPADE);
        assertEq(2, res_.size());
        assertEq(suites_.first(), res_.first());
        assertEq(suites_.last(), res_.last());
        res_ = main_.eclaterEnCours(cartesJouees_.couleurs(), Suit.CLUB);
        assertEq(2, res_.size());
        assertEq(suites_.first(), res_.first());
        assertEq(suites_.last(), res_.last());
        res_ = main_.eclaterEnCours(cartesJouees_.couleurs(), Suit.TRUMP);
        assertEq(2, res_.size());
        assertEq(suites_.first(), res_.first());
        assertEq(suites_.last(), res_.last());
//        assertEq(suites_,main_.eclaterEnCours(cartesJouees_.couleurs(), Suit.HEART));
//        assertEq(suites_,main_.eclaterEnCours(cartesJouees_.couleurs(), Suit.DIAMOND));
//        assertEq(suites_,main_.eclaterEnCours(cartesJouees_.couleurs(), Suit.SPADE));
//        assertEq(suites_,main_.eclaterEnCours(cartesJouees_.couleurs(), Suit.CLUB));
//        assertEq(suites_,main_.eclaterEnCours(cartesJouees_.couleurs(), Suit.TRUMP));
    }
    @Test
    public void atoutsMaitres1Test() {
        HandTarot h_ = new HandTarot();
        HandTarot out_ = h_.atoutsMaitres(new HandTarot().couleurs());
        assertEq(0, out_.total());
    }
    @Test
    public void atoutsMaitres2Test() {
        HandTarot h_ = new HandTarot();
        h_.ajouter(CardTarot.TRUMP_21);
        h_.ajouter(CardTarot.TRUMP_15);
        h_.ajouter(CardTarot.TRUMP_10);
        h_.ajouter(CardTarot.TRUMP_6);
        h_.ajouter(CardTarot.TRUMP_3);
        h_.ajouter(CardTarot.TRUMP_1);
        HandTarot out_ = h_.atoutsMaitres(new HandTarot().couleurs());
        assertEq(1, out_.total());
        assertSame(CardTarot.TRUMP_21, out_.carte(0));
    }
    @Test
    public void atoutsMaitres3Test() {
        HandTarot h_ = new HandTarot();
        h_.ajouter(CardTarot.TRUMP_21);
        h_.ajouter(CardTarot.TRUMP_15);
        h_.ajouter(CardTarot.TRUMP_10);
        h_.ajouter(CardTarot.TRUMP_6);
        h_.ajouter(CardTarot.TRUMP_3);
        h_.ajouter(CardTarot.TRUMP_1);
        HandTarot out_ = h_.atoutsMaitres(new EnumMap<Suit, HandTarot>());
        assertEq(1, out_.total());
        assertSame(CardTarot.TRUMP_21, out_.carte(0));
    }
    @Test
    public void atoutsMaitres4Test() {
        HandTarot h_ = new HandTarot();
        h_.ajouter(CardTarot.TRUMP_20);
        h_.ajouter(CardTarot.TRUMP_15);
        h_.ajouter(CardTarot.TRUMP_10);
        h_.ajouter(CardTarot.TRUMP_6);
        h_.ajouter(CardTarot.TRUMP_3);
        h_.ajouter(CardTarot.TRUMP_1);
        HandTarot out_ = h_.atoutsMaitres(new HandTarot().couleurs());
        assertEq(0, out_.total());
    }
    @Test
    public void atoutsMaitres5Test() {
        HandTarot h_ = new HandTarot();
        h_.ajouter(CardTarot.TRUMP_20);
        h_.ajouter(CardTarot.TRUMP_15);
        h_.ajouter(CardTarot.TRUMP_10);
        h_.ajouter(CardTarot.TRUMP_6);
        h_.ajouter(CardTarot.TRUMP_3);
        h_.ajouter(CardTarot.TRUMP_1);
        HandTarot p_ = new HandTarot();
        p_.ajouter(CardTarot.TRUMP_21);
        HandTarot out_ = h_.atoutsMaitres(p_.couleurs());
        assertEq(1, out_.total());
        assertSame(CardTarot.TRUMP_20, out_.carte(0));
    }
    @Test
    public void atoutsMaitres6Test() {
        HandTarot h_ = new HandTarot();
        h_.ajouter(CardTarot.TRUMP_20);
        h_.ajouter(CardTarot.TRUMP_18);
        h_.ajouter(CardTarot.TRUMP_10);
        h_.ajouter(CardTarot.TRUMP_6);
        h_.ajouter(CardTarot.TRUMP_3);
        h_.ajouter(CardTarot.TRUMP_1);
        HandTarot p_ = new HandTarot();
        p_.ajouter(CardTarot.TRUMP_21);
        HandTarot out_ = h_.atoutsMaitres(p_.couleurs());
        assertEq(1, out_.total());
        assertSame(CardTarot.TRUMP_20, out_.carte(0));
    }
    @Test
    public void atoutsMaitres7Test() {
        HandTarot h_ = new HandTarot();
        h_.ajouter(CardTarot.TRUMP_20);
        h_.ajouter(CardTarot.TRUMP_18);
        h_.ajouter(CardTarot.TRUMP_10);
        h_.ajouter(CardTarot.TRUMP_6);
        h_.ajouter(CardTarot.TRUMP_3);
        h_.ajouter(CardTarot.TRUMP_1);
        HandTarot p_ = new HandTarot();
        p_.ajouter(CardTarot.TRUMP_21);
        p_.ajouter(CardTarot.TRUMP_19);
        HandTarot out_ = h_.atoutsMaitres(p_.couleurs());
        assertEq(2, out_.total());
        assertSame(CardTarot.TRUMP_20, out_.carte(0));
        assertSame(CardTarot.TRUMP_18, out_.carte(1));
    }
    @Test
    public void atoutsMaitres8Test() {
        HandTarot h_ = new HandTarot();
        h_.ajouter(CardTarot.TRUMP_20);
        h_.ajouter(CardTarot.TRUMP_18);
        h_.ajouter(CardTarot.TRUMP_10);
        h_.ajouter(CardTarot.TRUMP_6);
        h_.ajouter(CardTarot.TRUMP_3);
        h_.ajouter(CardTarot.TRUMP_1);
        HandTarot p_ = new HandTarot();
        p_.ajouter(CardTarot.TRUMP_21);
        p_.ajouter(CardTarot.TRUMP_19);
        p_.ajouter(CardTarot.TRUMP_17);
        p_.ajouter(CardTarot.TRUMP_16);
        p_.ajouter(CardTarot.TRUMP_15);
        p_.ajouter(CardTarot.TRUMP_14);
        p_.ajouter(CardTarot.TRUMP_13);
        p_.ajouter(CardTarot.TRUMP_12);
        p_.ajouter(CardTarot.TRUMP_11);
        p_.ajouter(CardTarot.TRUMP_9);
        p_.ajouter(CardTarot.TRUMP_8);
        p_.ajouter(CardTarot.TRUMP_7);
        HandTarot out_ = h_.atoutsMaitres(p_.couleurs());
        assertEq(6, out_.total());
        assertSame(CardTarot.TRUMP_20, out_.carte(0));
        assertSame(CardTarot.TRUMP_18, out_.carte(1));
        assertSame(CardTarot.TRUMP_10, out_.carte(2));
        assertSame(CardTarot.TRUMP_6, out_.carte(3));
        assertSame(CardTarot.TRUMP_3, out_.carte(4));
        assertSame(CardTarot.TRUMP_1, out_.carte(5));
    }
    @Test
    public void atoutsMaitres9Test() {
        HandTarot h_ = new HandTarot();
        h_.ajouter(CardTarot.TRUMP_20);
        h_.ajouter(CardTarot.TRUMP_18);
        h_.ajouter(CardTarot.TRUMP_10);
        h_.ajouter(CardTarot.TRUMP_6);
        h_.ajouter(CardTarot.TRUMP_3);
        h_.ajouter(CardTarot.TRUMP_1);
        HandTarot p_ = new HandTarot();
        p_.ajouter(CardTarot.TRUMP_21);
        p_.ajouter(CardTarot.TRUMP_19);
        p_.ajouter(CardTarot.TRUMP_17);
        p_.ajouter(CardTarot.TRUMP_16);
        p_.ajouter(CardTarot.TRUMP_15);
        p_.ajouter(CardTarot.TRUMP_14);
        p_.ajouter(CardTarot.TRUMP_13);
        p_.ajouter(CardTarot.TRUMP_12);
        p_.ajouter(CardTarot.TRUMP_11);
        p_.ajouter(CardTarot.TRUMP_9);
        p_.ajouter(CardTarot.TRUMP_8);
        p_.ajouter(CardTarot.TRUMP_7);
        p_.ajouter(CardTarot.TRUMP_5);
        p_.ajouter(CardTarot.TRUMP_4);
        p_.ajouter(CardTarot.TRUMP_2);
        HandTarot out_ = h_.atoutsMaitres(p_.couleurs());
        assertEq(6, out_.total());
        assertSame(CardTarot.TRUMP_20, out_.carte(0));
        assertSame(CardTarot.TRUMP_18, out_.carte(1));
        assertSame(CardTarot.TRUMP_10, out_.carte(2));
        assertSame(CardTarot.TRUMP_6, out_.carte(3));
        assertSame(CardTarot.TRUMP_3, out_.carte(4));
        assertSame(CardTarot.TRUMP_1, out_.carte(5));
    }
    @Test
    public void atoutsMaitres10Test() {
        HandTarot h_ = new HandTarot();
        h_.ajouter(CardTarot.TRUMP_20);
        h_.ajouter(CardTarot.TRUMP_18);
        h_.ajouter(CardTarot.TRUMP_10);
        h_.ajouter(CardTarot.TRUMP_6);
        h_.ajouter(CardTarot.TRUMP_1);
        HandTarot p_ = new HandTarot();
        p_.ajouter(CardTarot.TRUMP_21);
        p_.ajouter(CardTarot.TRUMP_19);
        p_.ajouter(CardTarot.TRUMP_17);
        p_.ajouter(CardTarot.TRUMP_16);
        p_.ajouter(CardTarot.TRUMP_15);
        p_.ajouter(CardTarot.TRUMP_14);
        p_.ajouter(CardTarot.TRUMP_13);
        p_.ajouter(CardTarot.TRUMP_12);
        p_.ajouter(CardTarot.TRUMP_11);
        p_.ajouter(CardTarot.TRUMP_9);
        p_.ajouter(CardTarot.TRUMP_8);
        p_.ajouter(CardTarot.TRUMP_7);
        HandTarot out_ = h_.atoutsMaitres(p_.couleurs());
        assertEq(5, out_.total());
        assertSame(CardTarot.TRUMP_20, out_.carte(0));
        assertSame(CardTarot.TRUMP_18, out_.carte(1));
        assertSame(CardTarot.TRUMP_10, out_.carte(2));
        assertSame(CardTarot.TRUMP_6, out_.carte(3));
        assertSame(CardTarot.TRUMP_1, out_.carte(4));
    }
    @Test
    public void getRepartitionsValides_repartitionsToutesValides1(){
        EnumList<DealingTarot> repartitionsValides_ = DealingTarot.getRepartitionsValides();
        assertTrue(!repartitionsValides_.isEmpty());
        assertEq(DealingTarot.values().length,repartitionsValides_.size());
    }
    @Test
    public void getDeclarableHandFuls_poigneesToutesValides1(){
        EnumList<Handfuls> poigneesValidesDefaut_ = Handfuls.getPoigneesValidesParDefaut();
        assertTrue(!poigneesValidesDefaut_.isEmpty());
        assertEq(Handfuls.getDeclarableHandFuls().size(),poigneesValidesDefaut_.size());
        assertTrue(Handfuls.getDeclarableHandFuls().containsAllObj(poigneesValidesDefaut_));
    }
}