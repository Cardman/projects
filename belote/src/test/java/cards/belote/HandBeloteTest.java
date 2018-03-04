package cards.belote;
import static cards.belote.EquallableBeloteUtil.assertEq;
import static junitparams.JUnitParamsRunner.$;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import org.junit.Test;
import org.junit.runner.RunWith;

import cards.belote.enumerations.BidBelote;
import cards.belote.enumerations.CardBelote;
import cards.belote.enumerations.DealingBelote;
import cards.belote.enumerations.DeclaresBelote;
import cards.consts.Order;
import cards.consts.Suit;
import code.util.CustList;
import code.util.EnumList;
import code.util.EnumMap;
import code.util.EqList;

@RunWith(JUnitParamsRunner.class)
@SuppressWarnings("static-method")
public class HandBeloteTest {

    Object[] monotonieTri(){
        return $($(true),$(false));
    }
    @Test
    public void pileBase1Test(){
        HandBelote main_ = HandBelote.pileBase();
        assertEq(CardBelote.values().length-1, main_.total());
        assertTrue(!main_.contient(CardBelote.WHITE));
    }

    @Test
    public void trierUnicolore1Test(){
        HandBelote main_ = new HandBelote(Order.SUIT);
        main_.ajouter(CardBelote.HEART_10);
        main_.ajouter(CardBelote.HEART_1);
        main_.ajouter(CardBelote.HEART_KING);
        HandBelote resAtt_ = new HandBelote(Order.SUIT);
        resAtt_.ajouter(CardBelote.HEART_1);
        resAtt_.ajouter(CardBelote.HEART_10);
        resAtt_.ajouter(CardBelote.HEART_KING);
        assertNotEquals(resAtt_, main_);
        main_.trierUnicolore(true);
        assertEq(resAtt_, main_);
        resAtt_ = new HandBelote(Order.SUIT);
        resAtt_.ajouter(CardBelote.HEART_KING);
        resAtt_.ajouter(CardBelote.HEART_10);
        resAtt_.ajouter(CardBelote.HEART_1);
        main_.trierUnicolore(false);
        assertEq(resAtt_, main_);
        main_ = new HandBelote(Order.TRUMP);
        main_.ajouter(CardBelote.HEART_10);
        main_.ajouter(CardBelote.HEART_9);
        main_.ajouter(CardBelote.HEART_1);
        resAtt_ = new HandBelote(Order.TRUMP);
        resAtt_.ajouter(CardBelote.HEART_9);
        resAtt_.ajouter(CardBelote.HEART_1);
        resAtt_.ajouter(CardBelote.HEART_10);
        main_.trierUnicolore(true);
        assertEq(resAtt_, main_);
        resAtt_ = new HandBelote(Order.TRUMP);
        resAtt_.ajouter(CardBelote.HEART_10);
        resAtt_.ajouter(CardBelote.HEART_1);
        resAtt_.ajouter(CardBelote.HEART_9);
        main_.trierUnicolore(false);
        assertEq(resAtt_, main_);
    }

    @Test
    public void couleurComplete1Test(){
        Suit couleur_ = Suit.HEART;
        HandBelote main_ = HandBelote.couleurComplete(couleur_,Order.SUIT);
        HandBelote resAtt_ = new HandBelote();
        resAtt_.ajouter(CardBelote.HEART_1);
        resAtt_.ajouter(CardBelote.HEART_10);
        resAtt_.ajouter(CardBelote.HEART_KING);
        resAtt_.ajouter(CardBelote.HEART_QUEEN);
        resAtt_.ajouter(CardBelote.HEART_JACK);
        resAtt_.ajouter(CardBelote.HEART_9);
        resAtt_.ajouter(CardBelote.HEART_8);
        resAtt_.ajouter(CardBelote.HEART_7);
        assertEq(resAtt_,main_);
        main_ = HandBelote.couleurComplete(couleur_,Order.TRUMP);
        resAtt_ = new HandBelote();
        resAtt_.ajouter(CardBelote.HEART_JACK);
        resAtt_.ajouter(CardBelote.HEART_9);
        resAtt_.ajouter(CardBelote.HEART_1);
        resAtt_.ajouter(CardBelote.HEART_10);
        resAtt_.ajouter(CardBelote.HEART_KING);
        resAtt_.ajouter(CardBelote.HEART_QUEEN);
        resAtt_.ajouter(CardBelote.HEART_8);
        resAtt_.ajouter(CardBelote.HEART_7);
        assertEq(resAtt_,main_);
    }

    private HandBelote trier(){
        HandBelote main_ = new HandBelote();
        main_.ajouter(CardBelote.SPADE_KING);
        main_.ajouter(CardBelote.HEART_1);
        main_.ajouter(CardBelote.SPADE_JACK);
        main_.ajouter(CardBelote.HEART_9);
        return main_;
    }
    @Test
    public void trier1Test(){
        HandBelote main_ = trier();
        EnumList<Suit> couleurs_ = new EnumList<Suit>();
        couleurs_.add(Suit.SPADE);
        couleurs_.add(Suit.HEART);
        Suit couleurAtout_ = Suit.SPADE;
        main_.trier(couleurs_, true,couleurAtout_);
        HandBelote resAtt_ = new HandBelote();
        resAtt_.ajouter(CardBelote.SPADE_JACK);
        resAtt_.ajouter(CardBelote.SPADE_KING);
        resAtt_.ajouter(CardBelote.HEART_1);
        resAtt_.ajouter(CardBelote.HEART_9);
        assertEq(resAtt_, main_);
        couleurAtout_ = Suit.HEART;
        main_ = trier();
        main_.trier(couleurs_, true,couleurAtout_);
        resAtt_ = new HandBelote();
        resAtt_.ajouter(CardBelote.SPADE_KING);
        resAtt_.ajouter(CardBelote.SPADE_JACK);
        resAtt_.ajouter(CardBelote.HEART_9);
        resAtt_.ajouter(CardBelote.HEART_1);
        assertEq(resAtt_, main_);
        couleurAtout_ = Suit.SPADE;
        main_ = trier();
        main_.trier(couleurs_, false,couleurAtout_);
        resAtt_ = new HandBelote();
        resAtt_.ajouter(CardBelote.SPADE_KING);
        resAtt_.ajouter(CardBelote.SPADE_JACK);
        resAtt_.ajouter(CardBelote.HEART_9);
        resAtt_.ajouter(CardBelote.HEART_1);
        assertEq(resAtt_, main_);
        couleurAtout_ = Suit.HEART;
        main_ = trier();
        main_.trier(couleurs_, false,couleurAtout_);
        resAtt_ = new HandBelote();
        resAtt_.ajouter(CardBelote.SPADE_JACK);
        resAtt_.ajouter(CardBelote.SPADE_KING);
        resAtt_.ajouter(CardBelote.HEART_1);
        resAtt_.ajouter(CardBelote.HEART_9);
        assertEq(resAtt_, main_);
        main_ = trier();
        main_.trier(couleurs_, true,Order.SUIT);
        resAtt_ = new HandBelote();
        resAtt_.ajouter(CardBelote.SPADE_KING);
        resAtt_.ajouter(CardBelote.SPADE_JACK);
        resAtt_.ajouter(CardBelote.HEART_1);
        resAtt_.ajouter(CardBelote.HEART_9);
        assertEq(resAtt_, main_);
        main_ = trier();
        main_.trier(couleurs_, true,Order.TRUMP);
        resAtt_ = new HandBelote();
        resAtt_.ajouter(CardBelote.SPADE_JACK);
        resAtt_.ajouter(CardBelote.SPADE_KING);
        resAtt_.ajouter(CardBelote.HEART_9);
        resAtt_.ajouter(CardBelote.HEART_1);
        assertEq(resAtt_, main_);
        main_ = trier();
        main_.trier(couleurs_, false,Order.TRUMP);
        resAtt_ = new HandBelote();
        resAtt_.ajouter(CardBelote.SPADE_KING);
        resAtt_.ajouter(CardBelote.SPADE_JACK);
        resAtt_.ajouter(CardBelote.HEART_1);
        resAtt_.ajouter(CardBelote.HEART_9);
        assertEq(resAtt_, main_);
        main_ = trier();
        main_.trier(couleurs_, false,Order.SUIT);
        resAtt_ = new HandBelote();
        resAtt_.ajouter(CardBelote.SPADE_JACK);
        resAtt_.ajouter(CardBelote.SPADE_KING);
        resAtt_.ajouter(CardBelote.HEART_9);
        resAtt_.ajouter(CardBelote.HEART_1);
        assertEq(resAtt_, main_);
    }
    private HandBelote couleurs(){
        HandBelote main_ = new HandBelote();
        main_.ajouter(CardBelote.DIAMOND_1);
        main_.ajouter(CardBelote.DIAMOND_9);
        main_.ajouter(CardBelote.HEART_10);
        main_.ajouter(CardBelote.HEART_JACK);
        main_.ajouter(CardBelote.SPADE_KING);
        main_.ajouter(CardBelote.CLUB_7);
        return main_;
    }
    private HandBelote initMainAvecCouleurVideCouleursTest(){
        HandBelote main_ = new HandBelote();
        main_.ajouter(CardBelote.DIAMOND_1);
        main_.ajouter(CardBelote.DIAMOND_9);
        main_.ajouter(CardBelote.HEART_10);
        main_.ajouter(CardBelote.HEART_JACK);
        main_.ajouter(CardBelote.SPADE_KING);
        return main_;
    }
    @Test
    public void couleurs1Test(){
        HandBelote main_ = new HandBelote();
        EnumMap<Suit,HandBelote> hash_ = new EnumMap<Suit,HandBelote>();
        BidBeloteSuit enchereCouleur_ = new BidBeloteSuit();
        enchereCouleur_.setEnchere(BidBelote.SUIT);
        HandBelote couleur_ = new HandBelote();
//        couleur_.ajouter(CarteBelote.NeufCarreau);
//        couleur_.ajouter(CarteBelote.AsCarreau);
//        hash_.put(Couleur.Carreau, couleur_);
//        couleur_ = new MainBelote();
//        couleur_.ajouter(CarteBelote.ValetCoeur);
//        couleur_.ajouter(CarteBelote.DixCoeur);
//        hash_.put(Couleur.Coeur, couleur_);
//        couleur_ = new MainBelote();
//        couleur_.ajouter(CarteBelote.RoiPique);
//        hash_.put(Couleur.Pique, couleur_);
//        couleur_ = new MainBelote();
//        couleur_.ajouter(CarteBelote.SeptTrefle);
//        hash_.put(Couleur.Trefle, couleur_);
//        assertEq(hash_, main_.couleurs(enchereCouleur_));
//        enchereCouleur_.setEnchere(EncheresBelote.Autre_couleur);
//        assertEq(hash_, main_.couleurs(enchereCouleur_));
//        enchereCouleur_.setEnchere(EncheresBelote.Tout_atout);
//        assertEq(hash_, main_.couleurs(enchereCouleur_));
//        couleur_ = new MainBelote();
//        couleur_.ajouter(CarteBelote.AsCarreau);
//        couleur_.ajouter(CarteBelote.NeufCarreau);
//        hash_.put(Couleur.Carreau, couleur_);
//        couleur_ = new MainBelote();
//        couleur_.ajouter(CarteBelote.DixCoeur);
//        couleur_.ajouter(CarteBelote.ValetCoeur);
//        hash_.put(Couleur.Coeur, couleur_);
//        couleur_ = new MainBelote();
//        couleur_.ajouter(CarteBelote.RoiPique);
//        hash_.put(Couleur.Pique, couleur_);
//        couleur_ = new MainBelote();
//        couleur_.ajouter(CarteBelote.SeptTrefle);
//        hash_.put(Couleur.Trefle, couleur_);
//        enchereCouleur_.setEnchere(EncheresBelote.Sans_atout);
//        assertEq(hash_, main_.couleurs(enchereCouleur_));
//        main_ = initMainAvecCouleurVideCouleursTest();
//        couleur_ = new MainBelote();
//        couleur_.ajouter(CarteBelote.NeufCarreau);
//        couleur_.ajouter(CarteBelote.AsCarreau);
//        hash_.put(Couleur.Carreau, couleur_);
//        couleur_ = new MainBelote();
//        couleur_.ajouter(CarteBelote.ValetCoeur);
//        couleur_.ajouter(CarteBelote.DixCoeur);
//        hash_.put(Couleur.Coeur, couleur_);
//        couleur_ = new MainBelote();
//        couleur_.ajouter(CarteBelote.RoiPique);
//        hash_.put(Couleur.Pique, couleur_);
//        couleur_ = new MainBelote();
//        hash_.put(Couleur.Trefle, couleur_);
//        enchereCouleur_.setEnchere(EncheresBelote.Couleur);
//        assertEq(hash_, main_.couleurs(enchereCouleur_));
//        enchereCouleur_.setEnchere(EncheresBelote.Autre_couleur);
//        assertEq(hash_, main_.couleurs(enchereCouleur_));
//        enchereCouleur_.setEnchere(EncheresBelote.Tout_atout);
//        assertEq(hash_, main_.couleurs(enchereCouleur_));
//        couleur_ = new MainBelote();
//        couleur_.ajouter(CarteBelote.AsCarreau);
//        couleur_.ajouter(CarteBelote.NeufCarreau);
//        hash_.put(Couleur.Carreau, couleur_);
//        couleur_ = new MainBelote();
//        couleur_.ajouter(CarteBelote.DixCoeur);
//        couleur_.ajouter(CarteBelote.ValetCoeur);
//        hash_.put(Couleur.Coeur, couleur_);
//        couleur_ = new MainBelote();
//        couleur_.ajouter(CarteBelote.RoiPique);
//        hash_.put(Couleur.Pique, couleur_);
//        couleur_ = new MainBelote();
//        hash_.put(Couleur.Trefle, couleur_);
//        enchereCouleur_.setEnchere(EncheresBelote.Sans_atout);
//        assertEq(hash_, main_.couleurs(enchereCouleur_));
        main_ = couleurs();
        Suit couleurAtout_ = Suit.DIAMOND;
        enchereCouleur_.setCouleur(couleurAtout_);
        couleur_ = new HandBelote();
        couleur_.ajouter(CardBelote.DIAMOND_9);
        couleur_.ajouter(CardBelote.DIAMOND_1);
        hash_.put(Suit.DIAMOND, couleur_);
        couleur_ = new HandBelote();
        couleur_.ajouter(CardBelote.HEART_10);
        couleur_.ajouter(CardBelote.HEART_JACK);
        hash_.put(Suit.HEART, couleur_);
        couleur_ = new HandBelote();
        couleur_.ajouter(CardBelote.SPADE_KING);
        hash_.put(Suit.SPADE, couleur_);
        couleur_ = new HandBelote();
        couleur_.ajouter(CardBelote.CLUB_7);
        hash_.put(Suit.CLUB, couleur_);
        enchereCouleur_.setEnchere(BidBelote.SUIT);
        EnumMap<Suit,HandBelote> res_ = main_.couleurs(enchereCouleur_);
        assertEq(hash_.getVal(Suit.HEART), res_.getVal(Suit.HEART));
        assertEq(hash_.getVal(Suit.SPADE), res_.getVal(Suit.SPADE));
        assertEq(hash_.getVal(Suit.DIAMOND), res_.getVal(Suit.DIAMOND));
        assertEq(hash_.getVal(Suit.CLUB), res_.getVal(Suit.CLUB));
        enchereCouleur_.setEnchere(BidBelote.OTHER_SUIT);
        res_ = main_.couleurs(enchereCouleur_);
        assertEq(hash_.getVal(Suit.HEART), res_.getVal(Suit.HEART));
        assertEq(hash_.getVal(Suit.SPADE), res_.getVal(Suit.SPADE));
        assertEq(hash_.getVal(Suit.DIAMOND), res_.getVal(Suit.DIAMOND));
        assertEq(hash_.getVal(Suit.CLUB), res_.getVal(Suit.CLUB));
        couleurAtout_ = Suit.HEART;
        couleur_ = new HandBelote();
        couleur_.ajouter(CardBelote.DIAMOND_1);
        couleur_.ajouter(CardBelote.DIAMOND_9);
        hash_.put(Suit.DIAMOND, couleur_);
        couleur_ = new HandBelote();
        couleur_.ajouter(CardBelote.HEART_JACK);
        couleur_.ajouter(CardBelote.HEART_10);
        hash_.put(Suit.HEART, couleur_);
        couleur_ = new HandBelote();
        couleur_.ajouter(CardBelote.SPADE_KING);
        hash_.put(Suit.SPADE, couleur_);
        couleur_ = new HandBelote();
        couleur_.ajouter(CardBelote.CLUB_7);
        hash_.put(Suit.CLUB, couleur_);
        enchereCouleur_.setCouleur(couleurAtout_);
        enchereCouleur_.setEnchere(BidBelote.SUIT);
        res_ = main_.couleurs(enchereCouleur_);
        assertEq(hash_.getVal(Suit.HEART), res_.getVal(Suit.HEART));
        assertEq(hash_.getVal(Suit.SPADE), res_.getVal(Suit.SPADE));
        assertEq(hash_.getVal(Suit.DIAMOND), res_.getVal(Suit.DIAMOND));
        assertEq(hash_.getVal(Suit.CLUB), res_.getVal(Suit.CLUB));
        enchereCouleur_.setEnchere(BidBelote.OTHER_SUIT);
        res_ = main_.couleurs(enchereCouleur_);
        assertEq(hash_.getVal(Suit.HEART), res_.getVal(Suit.HEART));
        assertEq(hash_.getVal(Suit.SPADE), res_.getVal(Suit.SPADE));
        assertEq(hash_.getVal(Suit.DIAMOND), res_.getVal(Suit.DIAMOND));
        assertEq(hash_.getVal(Suit.CLUB), res_.getVal(Suit.CLUB));
        main_ = initMainAvecCouleurVideCouleursTest();
        couleurAtout_ = Suit.DIAMOND;
        couleur_ = new HandBelote();
        couleur_.ajouter(CardBelote.DIAMOND_9);
        couleur_.ajouter(CardBelote.DIAMOND_1);
        hash_.put(Suit.DIAMOND, couleur_);
        couleur_ = new HandBelote();
        couleur_.ajouter(CardBelote.HEART_10);
        couleur_.ajouter(CardBelote.HEART_JACK);
        hash_.put(Suit.HEART, couleur_);
        couleur_ = new HandBelote();
        couleur_.ajouter(CardBelote.SPADE_KING);
        hash_.put(Suit.SPADE, couleur_);
        couleur_ = new HandBelote();
        hash_.put(Suit.CLUB, couleur_);
        enchereCouleur_.setCouleur(couleurAtout_);
        enchereCouleur_.setEnchere(BidBelote.SUIT);
        res_ = main_.couleurs(enchereCouleur_);
        assertEq(hash_.getVal(Suit.HEART), res_.getVal(Suit.HEART));
        assertEq(hash_.getVal(Suit.SPADE), res_.getVal(Suit.SPADE));
        assertEq(hash_.getVal(Suit.DIAMOND), res_.getVal(Suit.DIAMOND));
        assertEq(hash_.getVal(Suit.CLUB), res_.getVal(Suit.CLUB));
        enchereCouleur_.setEnchere(BidBelote.OTHER_SUIT);
        res_ = main_.couleurs(enchereCouleur_);
        assertEq(hash_.getVal(Suit.HEART), res_.getVal(Suit.HEART));
        assertEq(hash_.getVal(Suit.SPADE), res_.getVal(Suit.SPADE));
        assertEq(hash_.getVal(Suit.DIAMOND), res_.getVal(Suit.DIAMOND));
        assertEq(hash_.getVal(Suit.CLUB), res_.getVal(Suit.CLUB));
        couleurAtout_ = Suit.HEART;
        couleur_ = new HandBelote();
        couleur_.ajouter(CardBelote.DIAMOND_1);
        couleur_.ajouter(CardBelote.DIAMOND_9);
        hash_.put(Suit.DIAMOND, couleur_);
        couleur_ = new HandBelote();
        couleur_.ajouter(CardBelote.HEART_JACK);
        couleur_.ajouter(CardBelote.HEART_10);
        hash_.put(Suit.HEART, couleur_);
        couleur_ = new HandBelote();
        couleur_.ajouter(CardBelote.SPADE_KING);
        hash_.put(Suit.SPADE, couleur_);
        couleur_ = new HandBelote();
        hash_.put(Suit.CLUB, couleur_);
        enchereCouleur_.setCouleur(couleurAtout_);
        enchereCouleur_.setEnchere(BidBelote.SUIT);
        res_ = main_.couleurs(enchereCouleur_);
        assertEq(hash_.getVal(Suit.HEART), res_.getVal(Suit.HEART));
        assertEq(hash_.getVal(Suit.SPADE), res_.getVal(Suit.SPADE));
        assertEq(hash_.getVal(Suit.DIAMOND), res_.getVal(Suit.DIAMOND));
        assertEq(hash_.getVal(Suit.CLUB), res_.getVal(Suit.CLUB));
        enchereCouleur_.setEnchere(BidBelote.OTHER_SUIT);
        res_ = main_.couleurs(enchereCouleur_);
        assertEq(hash_.getVal(Suit.HEART), res_.getVal(Suit.HEART));
        assertEq(hash_.getVal(Suit.SPADE), res_.getVal(Suit.SPADE));
        assertEq(hash_.getVal(Suit.DIAMOND), res_.getVal(Suit.DIAMOND));
        assertEq(hash_.getVal(Suit.CLUB), res_.getVal(Suit.CLUB));
        main_ = couleurs();
        couleurAtout_ = Suit.UNDEFINED;
        couleur_ = new HandBelote();
        couleur_.ajouter(CardBelote.DIAMOND_1);
        couleur_.ajouter(CardBelote.DIAMOND_9);
        hash_.put(Suit.DIAMOND, couleur_);
        couleur_ = new HandBelote();
        couleur_.ajouter(CardBelote.HEART_10);
        couleur_.ajouter(CardBelote.HEART_JACK);
        hash_.put(Suit.HEART, couleur_);
        couleur_ = new HandBelote();
        couleur_.ajouter(CardBelote.SPADE_KING);
        hash_.put(Suit.SPADE, couleur_);
        couleur_ = new HandBelote();
        couleur_.ajouter(CardBelote.CLUB_7);
        hash_.put(Suit.CLUB, couleur_);
        enchereCouleur_.setCouleur(couleurAtout_);
        enchereCouleur_.setEnchere(BidBelote.NO_TRUMP);
        res_ = main_.couleurs(enchereCouleur_);
        assertEq(hash_.getVal(Suit.HEART), res_.getVal(Suit.HEART));
        assertEq(hash_.getVal(Suit.SPADE), res_.getVal(Suit.SPADE));
        assertEq(hash_.getVal(Suit.DIAMOND), res_.getVal(Suit.DIAMOND));
        assertEq(hash_.getVal(Suit.CLUB), res_.getVal(Suit.CLUB));
        couleur_ = new HandBelote();
        couleur_.ajouter(CardBelote.DIAMOND_9);
        couleur_.ajouter(CardBelote.DIAMOND_1);
        hash_.put(Suit.DIAMOND, couleur_);
        couleur_ = new HandBelote();
        couleur_.ajouter(CardBelote.HEART_JACK);
        couleur_.ajouter(CardBelote.HEART_10);
        hash_.put(Suit.HEART, couleur_);
        couleur_ = new HandBelote();
        couleur_.ajouter(CardBelote.SPADE_KING);
        hash_.put(Suit.SPADE, couleur_);
        couleur_ = new HandBelote();
        couleur_.ajouter(CardBelote.CLUB_7);
        hash_.put(Suit.CLUB, couleur_);
        enchereCouleur_.setCouleur(couleurAtout_);
        enchereCouleur_.setEnchere(BidBelote.ALL_TRUMP);
        res_ = main_.couleurs(enchereCouleur_);
        assertEq(hash_.getVal(Suit.HEART), res_.getVal(Suit.HEART));
        assertEq(hash_.getVal(Suit.SPADE), res_.getVal(Suit.SPADE));
        assertEq(hash_.getVal(Suit.DIAMOND), res_.getVal(Suit.DIAMOND));
        assertEq(hash_.getVal(Suit.CLUB), res_.getVal(Suit.CLUB));
        main_ = initMainAvecCouleurVideCouleursTest();
        couleurAtout_ = Suit.UNDEFINED;
        couleur_ = new HandBelote();
        couleur_.ajouter(CardBelote.DIAMOND_1);
        couleur_.ajouter(CardBelote.DIAMOND_9);
        hash_.put(Suit.DIAMOND, couleur_);
        couleur_ = new HandBelote();
        couleur_.ajouter(CardBelote.HEART_10);
        couleur_.ajouter(CardBelote.HEART_JACK);
        hash_.put(Suit.HEART, couleur_);
        couleur_ = new HandBelote();
        couleur_.ajouter(CardBelote.SPADE_KING);
        hash_.put(Suit.SPADE, couleur_);
        couleur_ = new HandBelote();
        hash_.put(Suit.CLUB, couleur_);
        enchereCouleur_.setCouleur(couleurAtout_);
        enchereCouleur_.setEnchere(BidBelote.NO_TRUMP);
        res_ = main_.couleurs(enchereCouleur_);
        assertEq(hash_.getVal(Suit.HEART), res_.getVal(Suit.HEART));
        assertEq(hash_.getVal(Suit.SPADE), res_.getVal(Suit.SPADE));
        assertEq(hash_.getVal(Suit.DIAMOND), res_.getVal(Suit.DIAMOND));
        assertEq(hash_.getVal(Suit.CLUB), res_.getVal(Suit.CLUB));
        couleur_ = new HandBelote();
        couleur_.ajouter(CardBelote.DIAMOND_9);
        couleur_.ajouter(CardBelote.DIAMOND_1);
        hash_.put(Suit.DIAMOND, couleur_);
        couleur_ = new HandBelote();
        couleur_.ajouter(CardBelote.HEART_JACK);
        couleur_.ajouter(CardBelote.HEART_10);
        hash_.put(Suit.HEART, couleur_);
        couleur_ = new HandBelote();
        couleur_.ajouter(CardBelote.SPADE_KING);
        hash_.put(Suit.SPADE, couleur_);
        couleur_ = new HandBelote();
        hash_.put(Suit.CLUB, couleur_);
        enchereCouleur_.setCouleur(couleurAtout_);
        enchereCouleur_.setEnchere(BidBelote.ALL_TRUMP);
        res_ = main_.couleurs(enchereCouleur_);
        assertEq(hash_.getVal(Suit.HEART), res_.getVal(Suit.HEART));
        assertEq(hash_.getVal(Suit.SPADE), res_.getVal(Suit.SPADE));
        assertEq(hash_.getVal(Suit.DIAMOND), res_.getVal(Suit.DIAMOND));
        assertEq(hash_.getVal(Suit.CLUB), res_.getVal(Suit.CLUB));
    }

    Object[] bidsSuits() {
        return $($(BidBelote.SUIT, Suit.HEART),$(BidBelote.SUIT,Suit.SPADE),
                $(BidBelote.SUIT,Suit.DIAMOND),$(BidBelote.SUIT,Suit.CLUB),
                $(BidBelote.OTHER_SUIT,Suit.HEART),$(BidBelote.OTHER_SUIT,Suit.SPADE),
                $(BidBelote.OTHER_SUIT,Suit.DIAMOND),$(BidBelote.OTHER_SUIT,Suit.CLUB));
    }

    @Parameters(method="bidsSuits")
    @Test
    public void couleurs2Test(BidBelote _bid, Suit _suit){
        HandBelote main_ = new HandBelote();
//        Map<Suit, HandBelote> hash_;
//        hash_ = new Map<>();
//        hash_.put(Suit.HEART, new HandBelote());
//        hash_.put(Suit.SPADE, new HandBelote());
//        hash_.put(Suit.DIAMOND, new HandBelote());
//        hash_.put(Suit.CLUB, new HandBelote());
        BidBeloteSuit enchereCouleur_ = new BidBeloteSuit();
        enchereCouleur_.setEnchere(_bid);
        enchereCouleur_.setCouleur(_suit);
        EnumMap<Suit,HandBelote> res_ = main_.couleurs(enchereCouleur_);
        assertEq(4,res_.size());
        assertEq(0,res_.getVal(Suit.HEART).total());
        assertEq(0,res_.getVal(Suit.SPADE).total());
        assertEq(0,res_.getVal(Suit.DIAMOND).total());
        assertEq(0,res_.getVal(Suit.CLUB).total());
    }

    Object[] bids() {
        return $($(BidBelote.NO_TRUMP),$(BidBelote.ALL_TRUMP));
    }

    @Parameters(method="bids")
    @Test
    public void couleurs3Test(BidBelote _bid){
        HandBelote main_ = new HandBelote();
//        Map<Suit, HandBelote> hash_;
//        hash_ = new Map<>();
//        hash_.put(Suit.HEART, new HandBelote());
//        hash_.put(Suit.SPADE, new HandBelote());
//        hash_.put(Suit.DIAMOND, new HandBelote());
//        hash_.put(Suit.CLUB, new HandBelote());
        BidBeloteSuit enchereCouleur_ = new BidBeloteSuit();
        enchereCouleur_.setEnchere(_bid);
        EnumMap<Suit,HandBelote> res_ = main_.couleurs(enchereCouleur_);
        assertEq(4,res_.size());
        assertEq(0,res_.getVal(Suit.HEART).total());
        assertEq(0,res_.getVal(Suit.SPADE).total());
        assertEq(0,res_.getVal(Suit.DIAMOND).total());
        assertEq(0,res_.getVal(Suit.CLUB).total());
    }

    @Test
    public void nombreDePlisMinAssures1Test(){
        HandBelote main_ = new HandBelote();
        main_.ajouter(CardBelote.HEART_JACK);
        main_.ajouter(CardBelote.HEART_1);
        main_.ajouter(CardBelote.HEART_10);
        assertEq(2, main_.nombreDePlisMinAssures(Order.TRUMP));
        main_ = new HandBelote();
        main_.ajouter(CardBelote.HEART_9);
        main_.ajouter(CardBelote.HEART_1);
        main_.ajouter(CardBelote.HEART_10);
        assertEq(2, main_.nombreDePlisMinAssures(Order.TRUMP));
        main_ = new HandBelote();
        main_.ajouter(CardBelote.HEART_9);
        main_.ajouter(CardBelote.HEART_1);
        main_.ajouter(CardBelote.HEART_KING);
        assertEq(1, main_.nombreDePlisMinAssures(Order.TRUMP));
        main_ = new HandBelote();
        main_.ajouter(CardBelote.HEART_9);
        main_.ajouter(CardBelote.HEART_1);
        main_.ajouter(CardBelote.HEART_KING);
        main_.ajouter(CardBelote.HEART_QUEEN);
        main_.ajouter(CardBelote.HEART_8);
        assertEq(3, main_.nombreDePlisMinAssures(Order.TRUMP));
        main_ = new HandBelote();
        main_.ajouter(CardBelote.HEART_9);
        main_.ajouter(CardBelote.HEART_1);
        main_.ajouter(CardBelote.HEART_KING);
        main_.ajouter(CardBelote.HEART_QUEEN);
        assertEq(2, main_.nombreDePlisMinAssures(Order.TRUMP));
        main_ = new HandBelote();
        main_.ajouter(CardBelote.HEART_JACK);
        main_.ajouter(CardBelote.HEART_1);
        main_.ajouter(CardBelote.HEART_10);
        main_.ajouter(CardBelote.HEART_KING);
        main_.ajouter(CardBelote.HEART_QUEEN);
        main_.ajouter(CardBelote.HEART_8);
        main_.ajouter(CardBelote.HEART_7);
        assertEq(6, main_.nombreDePlisMinAssures(Order.TRUMP));
        main_ = new HandBelote();
        main_.ajouter(CardBelote.HEART_JACK);
        main_.ajouter(CardBelote.HEART_9);
        main_.ajouter(CardBelote.HEART_1);
        main_.ajouter(CardBelote.HEART_10);
        main_.ajouter(CardBelote.HEART_KING);
        main_.ajouter(CardBelote.HEART_QUEEN);
        main_.ajouter(CardBelote.HEART_8);
        main_.ajouter(CardBelote.HEART_7);
        assertEq(8, main_.nombreDePlisMinAssures(Order.TRUMP));
        main_ = new HandBelote();
        main_.ajouter(CardBelote.HEART_9);
        assertEq(0, main_.nombreDePlisMinAssures(Order.TRUMP));
        main_ = new HandBelote();
        assertEq(0, main_.nombreDePlisMinAssures(Order.TRUMP));
        main_.ajouter(CardBelote.HEART_10);
        main_.ajouter(CardBelote.HEART_KING);
        main_.ajouter(CardBelote.HEART_QUEEN);
        main_.ajouter(CardBelote.HEART_8);
        main_.ajouter(CardBelote.HEART_7);
        assertEq(2, main_.nombreDePlisMinAssures(Order.TRUMP));
        main_ = new HandBelote();
        main_.ajouter(CardBelote.HEART_1);
        main_.ajouter(CardBelote.HEART_10);
        main_.ajouter(CardBelote.HEART_JACK);
        assertEq(2, main_.nombreDePlisMinAssures(Order.SUIT));
        main_ = new HandBelote();
        main_.ajouter(CardBelote.HEART_1);
        main_.ajouter(CardBelote.HEART_10);
        main_.ajouter(CardBelote.HEART_9);
        assertEq(2, main_.nombreDePlisMinAssures(Order.SUIT));
        main_ = new HandBelote();
        main_.ajouter(CardBelote.HEART_1);
        main_.ajouter(CardBelote.HEART_KING);
        main_.ajouter(CardBelote.HEART_9);
        assertEq(1, main_.nombreDePlisMinAssures(Order.SUIT));
        main_ = new HandBelote();
        main_.ajouter(CardBelote.HEART_1);
        main_.ajouter(CardBelote.HEART_KING);
        main_.ajouter(CardBelote.HEART_QUEEN);
        main_.ajouter(CardBelote.HEART_9);
        main_.ajouter(CardBelote.HEART_8);
        assertEq(3, main_.nombreDePlisMinAssures(Order.SUIT));
        main_ = new HandBelote();
        main_.ajouter(CardBelote.HEART_1);
        main_.ajouter(CardBelote.HEART_KING);
        main_.ajouter(CardBelote.HEART_QUEEN);
        main_.ajouter(CardBelote.HEART_9);
        assertEq(2, main_.nombreDePlisMinAssures(Order.SUIT));
        main_ = new HandBelote();
        main_.ajouter(CardBelote.HEART_1);
        main_.ajouter(CardBelote.HEART_10);
        main_.ajouter(CardBelote.HEART_KING);
        main_.ajouter(CardBelote.HEART_QUEEN);
        main_.ajouter(CardBelote.HEART_JACK);
        main_.ajouter(CardBelote.HEART_8);
        main_.ajouter(CardBelote.HEART_7);
        assertEq(6, main_.nombreDePlisMinAssures(Order.SUIT));
        main_ = new HandBelote();
        main_.ajouter(CardBelote.HEART_1);
        main_.ajouter(CardBelote.HEART_10);
        main_.ajouter(CardBelote.HEART_KING);
        main_.ajouter(CardBelote.HEART_QUEEN);
        main_.ajouter(CardBelote.HEART_JACK);
        main_.ajouter(CardBelote.HEART_9);
        main_.ajouter(CardBelote.HEART_8);
        main_.ajouter(CardBelote.HEART_7);
        assertEq(8, main_.nombreDePlisMinAssures(Order.SUIT));
        main_ = new HandBelote();
        main_.ajouter(CardBelote.HEART_9);
        assertEq(0, main_.nombreDePlisMinAssures(Order.SUIT));
        main_ = new HandBelote();
        assertEq(0, main_.nombreDePlisMinAssures(Order.SUIT));
        main_.ajouter(CardBelote.HEART_10);
        main_.ajouter(CardBelote.HEART_KING);
        main_.ajouter(CardBelote.HEART_QUEEN);
        main_.ajouter(CardBelote.HEART_8);
        main_.ajouter(CardBelote.HEART_7);
        assertEq(2, main_.nombreDePlisMinAssures(Order.SUIT));
    }

    private HandBelote eclater(){
        HandBelote main_ = new HandBelote();
        main_.ajouter(CardBelote.HEART_9);
        main_.ajouter(CardBelote.HEART_10);
        main_.ajouter(CardBelote.HEART_KING);
        main_.ajouter(CardBelote.HEART_7);
        return main_;
    }

    private HandBelote initCartesJoueesEclaterTest(){
        HandBelote main_ = new HandBelote();
        main_.ajouter(CardBelote.HEART_1);
        return main_;
    }

    private HandBelote initCartesJoueesVideEclaterTest(){
        HandBelote main_ = new HandBelote();
        return main_;
    }

    @Test
    public void eclater1Test(){
        HandBelote main_ = eclater();
        EqList<HandBelote> suites_ = new EqList<HandBelote>();
        HandBelote suite_ = new HandBelote();
        suite_.ajouter(CardBelote.HEART_9);
        suites_.add(suite_);
        suite_ = new HandBelote();
        suite_.ajouter(CardBelote.HEART_10);
        suite_.ajouter(CardBelote.HEART_KING);
        suites_.add(suite_);
        suite_ = new HandBelote();
        suite_.ajouter(CardBelote.HEART_7);
        suites_.add(suite_);
        HandBelote cartesJouees_ = initCartesJoueesVideEclaterTest();
        Suit couleur_ = Suit.HEART;

        BidBeloteSuit enchereCouleur_ = new BidBeloteSuit();
        enchereCouleur_.setEnchere(BidBelote.SUIT);
        enchereCouleur_.setCouleur(couleur_);


        EnumMap<Suit,HandBelote> repartitionCartesJouees_ = cartesJouees_.couleurs(enchereCouleur_);
        asserting(suites_,main_.eclater(repartitionCartesJouees_, enchereCouleur_));
        enchereCouleur_.setEnchere(BidBelote.OTHER_SUIT);
        asserting(suites_,main_.eclater(repartitionCartesJouees_, enchereCouleur_));
        enchereCouleur_.setEnchere(BidBelote.ALL_TRUMP);
        asserting(suites_,main_.eclater(repartitionCartesJouees_, enchereCouleur_));
        couleur_ = Suit.DIAMOND;
        suites_ = new EqList<HandBelote>();
        suite_ = new HandBelote();
        suite_.ajouter(CardBelote.HEART_10);
        suite_.ajouter(CardBelote.HEART_KING);
        suites_.add(suite_);
        suite_ = new HandBelote();
        suite_.ajouter(CardBelote.HEART_9);
        suites_.add(suite_);
        suite_ = new HandBelote();
        suite_.ajouter(CardBelote.HEART_7);
        suites_.add(suite_);
        enchereCouleur_.setCouleur(couleur_);
        enchereCouleur_.setEnchere(BidBelote.SUIT);
        asserting(suites_,main_.eclater(repartitionCartesJouees_, enchereCouleur_));
        enchereCouleur_.setEnchere(BidBelote.OTHER_SUIT);
        asserting(suites_,main_.eclater(repartitionCartesJouees_, enchereCouleur_));
        enchereCouleur_.setEnchere(BidBelote.NO_TRUMP);
        asserting(suites_,main_.eclater(repartitionCartesJouees_, enchereCouleur_));
        cartesJouees_ = initCartesJoueesEclaterTest();
        enchereCouleur_.setEnchere(BidBelote.SUIT);
        repartitionCartesJouees_ = cartesJouees_.couleurs(enchereCouleur_);
        couleur_ = Suit.HEART;
        enchereCouleur_.setCouleur(couleur_);
        suites_ = new EqList<HandBelote>();
        suite_ = new HandBelote();
        suite_.ajouter(CardBelote.HEART_9);
        suite_.ajouter(CardBelote.HEART_10);
        suite_.ajouter(CardBelote.HEART_KING);
        suites_.add(suite_);
        suite_ = new HandBelote();
        suite_.ajouter(CardBelote.HEART_7);
        suites_.add(suite_);
        enchereCouleur_.setEnchere(BidBelote.SUIT);
        asserting(suites_,main_.eclater(repartitionCartesJouees_, enchereCouleur_));
        enchereCouleur_.setEnchere(BidBelote.OTHER_SUIT);
        asserting(suites_,main_.eclater(repartitionCartesJouees_, enchereCouleur_));
        couleur_ = Suit.DIAMOND;
        enchereCouleur_.setCouleur(couleur_);
        suites_ = new EqList<HandBelote>();
        suite_ = new HandBelote();
        suite_.ajouter(CardBelote.HEART_10);
        suite_.ajouter(CardBelote.HEART_KING);
        suites_.add(suite_);
        suite_ = new HandBelote();
        suite_.ajouter(CardBelote.HEART_9);
        suites_.add(suite_);
        suite_ = new HandBelote();
        suite_.ajouter(CardBelote.HEART_7);
        suites_.add(suite_);
        enchereCouleur_.setEnchere(BidBelote.SUIT);
        asserting(suites_,main_.eclater(repartitionCartesJouees_, enchereCouleur_));
        enchereCouleur_.setEnchere(BidBelote.OTHER_SUIT);
        asserting(suites_,main_.eclater(repartitionCartesJouees_, enchereCouleur_));
        cartesJouees_ = new HandBelote();
        cartesJouees_.ajouter(CardBelote.HEART_10);
        main_ = new HandBelote();
        main_.ajouter(CardBelote.HEART_1);
        main_.ajouter(CardBelote.HEART_KING);
        main_.ajouter(CardBelote.HEART_9);
        enchereCouleur_.setEnchere(BidBelote.SUIT);
        repartitionCartesJouees_ = cartesJouees_.couleurs(enchereCouleur_);
        suites_ = new EqList<HandBelote>();
        suite_ = new HandBelote();
        suite_.ajouter(CardBelote.HEART_1);
        suite_.ajouter(CardBelote.HEART_KING);
        suites_.add(suite_);
        suite_ = new HandBelote();
        suite_.ajouter(CardBelote.HEART_9);
        suites_.add(suite_);
        asserting(suites_,main_.eclater(repartitionCartesJouees_, enchereCouleur_));
        enchereCouleur_.setEnchere(BidBelote.OTHER_SUIT);
        asserting(suites_,main_.eclater(repartitionCartesJouees_, enchereCouleur_));
        enchereCouleur_.setEnchere(BidBelote.NO_TRUMP);
        asserting(suites_,main_.eclater(repartitionCartesJouees_, enchereCouleur_));
        suites_ = new EqList<HandBelote>();
        suite_ = new HandBelote();
        suite_.ajouter(CardBelote.HEART_9);
        suite_.ajouter(CardBelote.HEART_1);
        suite_.ajouter(CardBelote.HEART_KING);
        suites_.add(suite_);
        enchereCouleur_.setEnchere(BidBelote.ALL_TRUMP);
        asserting(suites_,main_.eclater(repartitionCartesJouees_, enchereCouleur_));
    }

    @Parameters(method="bids")
    @Test
    public void eclater2Test(BidBelote _bid){
        HandBelote main_;
        BidBeloteSuit enchereCouleur_;
        HandBelote cartesJouees_;
        EnumMap<Suit,HandBelote> repartitionCartesJouees_;
        EqList<HandBelote> suites_;
        enchereCouleur_ = new BidBeloteSuit();
        enchereCouleur_.setEnchere(_bid);
        cartesJouees_ = new HandBelote();
        cartesJouees_.ajouter(CardBelote.HEART_10);
        repartitionCartesJouees_ = cartesJouees_.couleurs(enchereCouleur_);
        main_ = new HandBelote();
        suites_ = new EqList<HandBelote>();
        asserting(suites_,main_.eclater(repartitionCartesJouees_, enchereCouleur_));
        cartesJouees_ = new HandBelote();
        repartitionCartesJouees_ = cartesJouees_.couleurs(enchereCouleur_);
        asserting(suites_,main_.eclater(repartitionCartesJouees_, enchereCouleur_));
    }

    @Parameters(method="bidsSuits")
    @Test
    public void eclater3Test(BidBelote _bid, Suit _suit){
        HandBelote main_;
        BidBeloteSuit enchereCouleur_;
        HandBelote cartesJouees_;
        EnumMap<Suit,HandBelote> repartitionCartesJouees_;
        EqList<HandBelote> suites_;
        enchereCouleur_ = new BidBeloteSuit();
        enchereCouleur_.setEnchere(_bid);
        enchereCouleur_.setCouleur(_suit);
        cartesJouees_ = new HandBelote();
        cartesJouees_.ajouter(CardBelote.HEART_10);
        repartitionCartesJouees_ = cartesJouees_.couleurs(enchereCouleur_);
        main_ = new HandBelote();
        suites_ = new EqList<HandBelote>();
        asserting(suites_,main_.eclater(repartitionCartesJouees_, enchereCouleur_));
        cartesJouees_ = new HandBelote();
        repartitionCartesJouees_ = cartesJouees_.couleurs(enchereCouleur_);
        asserting(suites_,main_.eclater(repartitionCartesJouees_, enchereCouleur_));
    }

    @Test
    public void annonce1Test(){
        Suit couleurAtout_ = Suit.UNDEFINED;
        BidBelote enchere_ = BidBelote.NO_TRUMP;
        BidBeloteSuit enchereCouleur_ = new BidBeloteSuit();
        enchereCouleur_.setEnchere(enchere_);
        enchereCouleur_.setCouleur(couleurAtout_);
        EnumList<DeclaresBelote> annoncesAutorisees_ = DeclaresBelote.annoncesValides();
        HandBelote main_ = new HandBelote(Order.SUIT);
        main_.ajouter(CardBelote.DIAMOND_1);
        main_.ajouter(CardBelote.DIAMOND_10);
        main_.ajouter(CardBelote.DIAMOND_KING);
        main_.ajouter(CardBelote.DIAMOND_QUEEN);
        main_.ajouter(CardBelote.HEART_JACK);
        main_.ajouter(CardBelote.SPADE_7);
        main_.ajouter(CardBelote.CLUB_9);
        main_.ajouter(CardBelote.CLUB_1);
        DeclareHandBelote annonceMain_ = main_.annonce(annoncesAutorisees_, enchereCouleur_);
        HandBelote res_ = new HandBelote();
        res_.ajouter(CardBelote.DIAMOND_1);
        res_.ajouter(CardBelote.DIAMOND_KING);
        res_.ajouter(CardBelote.DIAMOND_QUEEN);
        assertEq(res_, annonceMain_.getMain());
        assertEq(DeclaresBelote.THIRTY, annonceMain_.getAnnonce());
        main_ = new HandBelote(Order.SUIT);
        main_.ajouter(CardBelote.DIAMOND_1);
        main_.ajouter(CardBelote.DIAMOND_JACK);
        main_.ajouter(CardBelote.DIAMOND_KING);
        main_.ajouter(CardBelote.DIAMOND_QUEEN);
        main_.ajouter(CardBelote.HEART_JACK);
        main_.ajouter(CardBelote.SPADE_7);
        main_.ajouter(CardBelote.CLUB_9);
        main_.ajouter(CardBelote.CLUB_1);
        annonceMain_ = main_.annonce(annoncesAutorisees_, enchereCouleur_);
        res_ = new HandBelote();
        res_.ajouter(CardBelote.DIAMOND_1);
        res_.ajouter(CardBelote.DIAMOND_KING);
        res_.ajouter(CardBelote.DIAMOND_QUEEN);
        res_.ajouter(CardBelote.DIAMOND_JACK);
        assertEq(res_, annonceMain_.getMain());
        assertEq(DeclaresBelote.FIFTY, annonceMain_.getAnnonce());
        main_ = new HandBelote(Order.SUIT);
        main_.ajouter(CardBelote.DIAMOND_1);
        main_.ajouter(CardBelote.DIAMOND_10);
        main_.ajouter(CardBelote.DIAMOND_KING);
        main_.ajouter(CardBelote.DIAMOND_QUEEN);
        main_.ajouter(CardBelote.DIAMOND_9);
        main_.ajouter(CardBelote.DIAMOND_8);
        main_.ajouter(CardBelote.CLUB_9);
        main_.ajouter(CardBelote.CLUB_1);
        annonceMain_ = main_.annonce(annoncesAutorisees_, enchereCouleur_);
        res_ = new HandBelote();
        res_.ajouter(CardBelote.DIAMOND_1);
        res_.ajouter(CardBelote.DIAMOND_KING);
        res_.ajouter(CardBelote.DIAMOND_QUEEN);
        assertEq(res_, annonceMain_.getMain());
        assertEq(DeclaresBelote.THIRTY, annonceMain_.getAnnonce());
        main_ = new HandBelote(Order.SUIT);
        main_.ajouter(CardBelote.DIAMOND_1);
        main_.ajouter(CardBelote.HEART_KING);
        main_.ajouter(CardBelote.DIAMOND_KING);
        main_.ajouter(CardBelote.DIAMOND_QUEEN);
        main_.ajouter(CardBelote.HEART_QUEEN);
        main_.ajouter(CardBelote.HEART_JACK);
        main_.ajouter(CardBelote.CLUB_9);
        main_.ajouter(CardBelote.CLUB_1);
        annonceMain_ = main_.annonce(annoncesAutorisees_, enchereCouleur_);
        res_ = new HandBelote();
        res_.ajouter(CardBelote.DIAMOND_1);
        res_.ajouter(CardBelote.DIAMOND_KING);
        res_.ajouter(CardBelote.DIAMOND_QUEEN);
        assertEq(res_, annonceMain_.getMain());
        assertEq(DeclaresBelote.THIRTY, annonceMain_.getAnnonce());
        main_ = new HandBelote(Order.SUIT);
        main_.ajouter(CardBelote.DIAMOND_1);
        main_.ajouter(CardBelote.HEART_KING);
        main_.ajouter(CardBelote.DIAMOND_KING);
        main_.ajouter(CardBelote.DIAMOND_10);
        main_.ajouter(CardBelote.HEART_QUEEN);
        main_.ajouter(CardBelote.SPADE_9);
        main_.ajouter(CardBelote.CLUB_9);
        main_.ajouter(CardBelote.CLUB_1);
        annonceMain_ = main_.annonce(annoncesAutorisees_, enchereCouleur_);
        res_ = new HandBelote();
        assertEq(res_, annonceMain_.getMain());
        assertEq(DeclaresBelote.UNDEFINED, annonceMain_.getAnnonce());
        main_ = new HandBelote(Order.SUIT);
        main_.ajouter(CardBelote.DIAMOND_1);
        main_.ajouter(CardBelote.DIAMOND_KING);
        main_.ajouter(CardBelote.DIAMOND_QUEEN);
        main_.ajouter(CardBelote.DIAMOND_JACK);
        main_.ajouter(CardBelote.HEART_1);
        main_.ajouter(CardBelote.SPADE_1);
        main_.ajouter(CardBelote.CLUB_9);
        main_.ajouter(CardBelote.CLUB_1);
        annonceMain_ = main_.annonce(annoncesAutorisees_, enchereCouleur_);
        res_ = new HandBelote();
        res_.ajouter(CardBelote.HEART_1);
        res_.ajouter(CardBelote.SPADE_1);
        res_.ajouter(CardBelote.DIAMOND_1);
        res_.ajouter(CardBelote.CLUB_1);
        assertEq(res_, annonceMain_.getMain());
        assertEq(DeclaresBelote.FOUR_1, annonceMain_.getAnnonce());
        couleurAtout_ = Suit.DIAMOND;
        enchere_ = BidBelote.SUIT;
        enchereCouleur_.setEnchere(enchere_);
        enchereCouleur_.setCouleur(couleurAtout_);
        main_ = new HandBelote(Order.TRUMP);
        main_.ajouter(CardBelote.DIAMOND_1);
        main_.ajouter(CardBelote.DIAMOND_KING);
        main_.ajouter(CardBelote.DIAMOND_QUEEN);
        main_.ajouter(CardBelote.DIAMOND_JACK);
        main_.ajouter(CardBelote.HEART_1);
        main_.ajouter(CardBelote.HEART_KING);
        main_.ajouter(CardBelote.HEART_QUEEN);
        main_.ajouter(CardBelote.HEART_JACK);
        annonceMain_ = main_.annonce(annoncesAutorisees_, enchereCouleur_);
        res_ = new HandBelote();
        res_.ajouter(CardBelote.DIAMOND_1);
        res_.ajouter(CardBelote.DIAMOND_KING);
        res_.ajouter(CardBelote.DIAMOND_QUEEN);
        res_.ajouter(CardBelote.DIAMOND_JACK);
        assertEq(res_, annonceMain_.getMain());
        assertEq(DeclaresBelote.FIFTY, annonceMain_.getAnnonce());
        main_ = new HandBelote(Order.SUIT);
        main_.ajouter(CardBelote.DIAMOND_1);
        main_.ajouter(CardBelote.DIAMOND_KING);
        main_.ajouter(CardBelote.HEART_KING);
        main_.ajouter(CardBelote.CLUB_KING);
        main_.ajouter(CardBelote.HEART_1);
        main_.ajouter(CardBelote.SPADE_KING);
        main_.ajouter(CardBelote.SPADE_1);
        main_.ajouter(CardBelote.CLUB_1);
        annonceMain_ = main_.annonce(annoncesAutorisees_, enchereCouleur_);
        res_ = new HandBelote();
        res_.ajouter(CardBelote.HEART_1);
        res_.ajouter(CardBelote.SPADE_1);
        res_.ajouter(CardBelote.DIAMOND_1);
        res_.ajouter(CardBelote.CLUB_1);
        assertEq(res_, annonceMain_.getMain());
        assertEq(DeclaresBelote.FOUR_1, annonceMain_.getAnnonce());
        main_ = new HandBelote(Order.SUIT);
        main_.ajouter(CardBelote.DIAMOND_7);
        main_.ajouter(CardBelote.DIAMOND_KING);
        main_.ajouter(CardBelote.HEART_KING);
        main_.ajouter(CardBelote.CLUB_KING);
        main_.ajouter(CardBelote.HEART_7);
        main_.ajouter(CardBelote.SPADE_KING);
        main_.ajouter(CardBelote.SPADE_7);
        main_.ajouter(CardBelote.CLUB_7);
        annonceMain_ = main_.annonce(annoncesAutorisees_, enchereCouleur_);
        res_ = new HandBelote();
        res_.ajouter(CardBelote.HEART_KING);
        res_.ajouter(CardBelote.SPADE_KING);
        res_.ajouter(CardBelote.DIAMOND_KING);
        res_.ajouter(CardBelote.CLUB_KING);
        assertEq(res_, annonceMain_.getMain());
        assertEq(DeclaresBelote.FOUR_KING, annonceMain_.getAnnonce());
    }

    @Test
    public void getRepartitionsValides_repartitionsToutesValides1Test(){
        EnumList<DealingBelote> repartitionsValides_ = DealingBelote.getRepartitionsValides();
        assertTrue(!repartitionsValides_.isEmpty());
        assertEq(DealingBelote.values().length,repartitionsValides_.size());
    }

    //TODO change later
    private void asserting(EqList<HandBelote> _exp, EqList<HandBelote> _res) {
        assertEq(_exp.size(), _res.size());
        int s_ = _exp.size();
        for (int i = CustList.FIRST_INDEX; i < s_; i++) {
            assertEq(_exp.get(i), _res.get(i));
        }
    }

    @Test
    public void jeuValide1Test(){
        assertTrue(HandBelote.jeuValide());
    }
}
