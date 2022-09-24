package cards.belote;

import code.util.core.StringUtil;
import org.junit.Test;

import cards.belote.enumerations.BidBelote;
import cards.belote.enumerations.CardBelote;
import cards.belote.enumerations.DealingBelote;
import cards.belote.enumerations.DeclaresBelote;
import cards.consts.Order;
import cards.consts.Suit;
import code.util.CustList;
import code.util.EnumList;
import code.util.IdMap;


public class HandBeloteTest extends EquallableBeloteUtil {
    @Test
    public void pileBase1(){
        HandBelote main_ = HandBelote.pileBase();
        assertEq(32, main_.total());
        assertTrue(!main_.contient(CardBelote.WHITE));
        assertNotNull(StringUtil.concatNb(CardBelote.WHITE.getId().getNo(), ""));
        EnumList<CardBelote> one_ = new EnumList<CardBelote>();
        one_.add(CardBelote.DIAMOND_1);
        EnumList<CardBelote> two_ = new EnumList<CardBelote>();
        assertTrue(!CardBelote.equalsCards(one_,two_));
    }
    @Test
    public void pileBase2(){
        EnumList<CardBelote> one_ = new EnumList<CardBelote>();
        one_.add(CardBelote.DIAMOND_1);
        EnumList<CardBelote> two_ = new EnumList<CardBelote>();
        two_.add(CardBelote.SPADE_7);
        assertTrue(!CardBelote.equalsCards(one_,two_));
    }
    @Test
    public void pileBase3(){
        EnumList<CardBelote> one_ = new EnumList<CardBelote>();
        one_.add(CardBelote.DIAMOND_1);
        EnumList<CardBelote> two_ = new EnumList<CardBelote>();
        two_.add(CardBelote.DIAMOND_1);
        assertTrue(CardBelote.equalsCards(one_,two_));
    }
    @Test
    public void trierUnicolore1(){
        HandBelote main_ = new HandBelote(Order.SUIT);
        main_.ajouter(CardBelote.HEART_10);
        main_.ajouter(CardBelote.HEART_1);
        main_.ajouter(CardBelote.HEART_KING);
        HandBelote resAtt_ = new HandBelote(Order.SUIT);
        resAtt_.ajouter(CardBelote.HEART_1);
        resAtt_.ajouter(CardBelote.HEART_10);
        resAtt_.ajouter(CardBelote.HEART_KING);
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
    public void couleurComplete1(){
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
    public void trier1(){
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
    public void couleurs1(){
        HandBelote main_ = new HandBelote();
        IdMap<Suit,HandBelote> hash_ = new IdMap<Suit,HandBelote>();
        BidBeloteSuit enchereCouleur_ = new BidBeloteSuit();
        enchereCouleur_.setBid(BidBelote.SUIT);
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
        enchereCouleur_.setSuit(couleurAtout_);
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
        enchereCouleur_.setBid(BidBelote.SUIT);
        IdMap<Suit,HandBelote> res_ = main_.couleurs(enchereCouleur_);
        assertEq(hash_.getVal(Suit.HEART), res_.getVal(Suit.HEART));
        assertEq(hash_.getVal(Suit.SPADE), res_.getVal(Suit.SPADE));
        assertEq(hash_.getVal(Suit.DIAMOND), res_.getVal(Suit.DIAMOND));
        assertEq(hash_.getVal(Suit.CLUB), res_.getVal(Suit.CLUB));
        enchereCouleur_.setBid(BidBelote.OTHER_SUIT);
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
        enchereCouleur_.setSuit(couleurAtout_);
        enchereCouleur_.setBid(BidBelote.SUIT);
        res_ = main_.couleurs(enchereCouleur_);
        assertEq(hash_.getVal(Suit.HEART), res_.getVal(Suit.HEART));
        assertEq(hash_.getVal(Suit.SPADE), res_.getVal(Suit.SPADE));
        assertEq(hash_.getVal(Suit.DIAMOND), res_.getVal(Suit.DIAMOND));
        assertEq(hash_.getVal(Suit.CLUB), res_.getVal(Suit.CLUB));
        enchereCouleur_.setBid(BidBelote.OTHER_SUIT);
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
        enchereCouleur_.setSuit(couleurAtout_);
        enchereCouleur_.setBid(BidBelote.SUIT);
        res_ = main_.couleurs(enchereCouleur_);
        assertEq(hash_.getVal(Suit.HEART), res_.getVal(Suit.HEART));
        assertEq(hash_.getVal(Suit.SPADE), res_.getVal(Suit.SPADE));
        assertEq(hash_.getVal(Suit.DIAMOND), res_.getVal(Suit.DIAMOND));
        assertEq(hash_.getVal(Suit.CLUB), res_.getVal(Suit.CLUB));
        enchereCouleur_.setBid(BidBelote.OTHER_SUIT);
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
        enchereCouleur_.setSuit(couleurAtout_);
        enchereCouleur_.setBid(BidBelote.SUIT);
        res_ = main_.couleurs(enchereCouleur_);
        assertEq(hash_.getVal(Suit.HEART), res_.getVal(Suit.HEART));
        assertEq(hash_.getVal(Suit.SPADE), res_.getVal(Suit.SPADE));
        assertEq(hash_.getVal(Suit.DIAMOND), res_.getVal(Suit.DIAMOND));
        assertEq(hash_.getVal(Suit.CLUB), res_.getVal(Suit.CLUB));
        enchereCouleur_.setBid(BidBelote.OTHER_SUIT);
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
        enchereCouleur_.setSuit(couleurAtout_);
        enchereCouleur_.setBid(BidBelote.NO_TRUMP);
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
        enchereCouleur_.setSuit(couleurAtout_);
        enchereCouleur_.setBid(BidBelote.ALL_TRUMP);
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
        enchereCouleur_.setSuit(couleurAtout_);
        enchereCouleur_.setBid(BidBelote.NO_TRUMP);
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
        enchereCouleur_.setSuit(couleurAtout_);
        enchereCouleur_.setBid(BidBelote.ALL_TRUMP);
        res_ = main_.couleurs(enchereCouleur_);
        assertEq(hash_.getVal(Suit.HEART), res_.getVal(Suit.HEART));
        assertEq(hash_.getVal(Suit.SPADE), res_.getVal(Suit.SPADE));
        assertEq(hash_.getVal(Suit.DIAMOND), res_.getVal(Suit.DIAMOND));
        assertEq(hash_.getVal(Suit.CLUB), res_.getVal(Suit.CLUB));
    }
    @Test
    public void couleurs2Test(){
        HandBelote main_ = new HandBelote();
        BidBeloteSuit enchereCouleur_ = new BidBeloteSuit();
        enchereCouleur_.setBid(BidBelote.SUIT);
        enchereCouleur_.setSuit(Suit.HEART);
        IdMap<Suit,HandBelote> res_ = main_.couleurs(enchereCouleur_);
        assertEq(4,res_.size());
        assertEq(0,res_.getVal(Suit.HEART).total());
        assertEq(0,res_.getVal(Suit.SPADE).total());
        assertEq(0,res_.getVal(Suit.DIAMOND).total());
        assertEq(0,res_.getVal(Suit.CLUB).total());
    }
    @Test
    public void couleurs3Test(){
        HandBelote main_ = new HandBelote();
        BidBeloteSuit enchereCouleur_ = new BidBeloteSuit();
        enchereCouleur_.setBid(BidBelote.SUIT);
        enchereCouleur_.setSuit(Suit.SPADE);
        IdMap<Suit,HandBelote> res_ = main_.couleurs(enchereCouleur_);
        assertEq(4,res_.size());
        assertEq(0,res_.getVal(Suit.HEART).total());
        assertEq(0,res_.getVal(Suit.SPADE).total());
        assertEq(0,res_.getVal(Suit.DIAMOND).total());
        assertEq(0,res_.getVal(Suit.CLUB).total());
    }
    @Test
    public void couleurs4Test(){
        HandBelote main_ = new HandBelote();
        BidBeloteSuit enchereCouleur_ = new BidBeloteSuit();
        enchereCouleur_.setBid(BidBelote.SUIT);
        enchereCouleur_.setSuit(Suit.DIAMOND);
        IdMap<Suit,HandBelote> res_ = main_.couleurs(enchereCouleur_);
        assertEq(4,res_.size());
        assertEq(0,res_.getVal(Suit.HEART).total());
        assertEq(0,res_.getVal(Suit.SPADE).total());
        assertEq(0,res_.getVal(Suit.DIAMOND).total());
        assertEq(0,res_.getVal(Suit.CLUB).total());
    }
    @Test
    public void couleurs5Test(){
        HandBelote main_ = new HandBelote();
        BidBeloteSuit enchereCouleur_ = new BidBeloteSuit();
        enchereCouleur_.setBid(BidBelote.SUIT);
        enchereCouleur_.setSuit(Suit.CLUB);
        IdMap<Suit,HandBelote> res_ = main_.couleurs(enchereCouleur_);
        assertEq(4,res_.size());
        assertEq(0,res_.getVal(Suit.HEART).total());
        assertEq(0,res_.getVal(Suit.SPADE).total());
        assertEq(0,res_.getVal(Suit.DIAMOND).total());
        assertEq(0,res_.getVal(Suit.CLUB).total());
    }
    @Test
    public void couleurs6Test(){
        HandBelote main_ = new HandBelote();
        BidBeloteSuit enchereCouleur_ = new BidBeloteSuit();
        enchereCouleur_.setBid(BidBelote.OTHER_SUIT);
        enchereCouleur_.setSuit(Suit.HEART);
        IdMap<Suit,HandBelote> res_ = main_.couleurs(enchereCouleur_);
        assertEq(4,res_.size());
        assertEq(0,res_.getVal(Suit.HEART).total());
        assertEq(0,res_.getVal(Suit.SPADE).total());
        assertEq(0,res_.getVal(Suit.DIAMOND).total());
        assertEq(0,res_.getVal(Suit.CLUB).total());
    }
    @Test
    public void couleurs7Test(){
        HandBelote main_ = new HandBelote();
        BidBeloteSuit enchereCouleur_ = new BidBeloteSuit();
        enchereCouleur_.setBid(BidBelote.OTHER_SUIT);
        enchereCouleur_.setSuit(Suit.SPADE);
        IdMap<Suit,HandBelote> res_ = main_.couleurs(enchereCouleur_);
        assertEq(4,res_.size());
        assertEq(0,res_.getVal(Suit.HEART).total());
        assertEq(0,res_.getVal(Suit.SPADE).total());
        assertEq(0,res_.getVal(Suit.DIAMOND).total());
        assertEq(0,res_.getVal(Suit.CLUB).total());
    }
    @Test
    public void couleurs8Test(){
        HandBelote main_ = new HandBelote();
        BidBeloteSuit enchereCouleur_ = new BidBeloteSuit();
        enchereCouleur_.setBid(BidBelote.OTHER_SUIT);
        enchereCouleur_.setSuit(Suit.DIAMOND);
        IdMap<Suit,HandBelote> res_ = main_.couleurs(enchereCouleur_);
        assertEq(4,res_.size());
        assertEq(0,res_.getVal(Suit.HEART).total());
        assertEq(0,res_.getVal(Suit.SPADE).total());
        assertEq(0,res_.getVal(Suit.DIAMOND).total());
        assertEq(0,res_.getVal(Suit.CLUB).total());
    }
    @Test
    public void couleurs9Test(){
        HandBelote main_ = new HandBelote();
        BidBeloteSuit enchereCouleur_ = new BidBeloteSuit();
        enchereCouleur_.setBid(BidBelote.OTHER_SUIT);
        enchereCouleur_.setSuit(Suit.CLUB);
        IdMap<Suit,HandBelote> res_ = main_.couleurs(enchereCouleur_);
        assertEq(4,res_.size());
        assertEq(0,res_.getVal(Suit.HEART).total());
        assertEq(0,res_.getVal(Suit.SPADE).total());
        assertEq(0,res_.getVal(Suit.DIAMOND).total());
        assertEq(0,res_.getVal(Suit.CLUB).total());
    }
    @Test
    public void couleurs10Test(){
        HandBelote main_ = new HandBelote();
//        Map<Suit, HandBelote> hash_;
//        hash_ = new Map<>();
//        hash_.put(Suit.HEART, new HandBelote());
//        hash_.put(Suit.SPADE, new HandBelote());
//        hash_.put(Suit.DIAMOND, new HandBelote());
//        hash_.put(Suit.CLUB, new HandBelote());
        BidBeloteSuit enchereCouleur_ = new BidBeloteSuit();
        enchereCouleur_.setBid(BidBelote.NO_TRUMP);
        IdMap<Suit,HandBelote> res_ = main_.couleurs(enchereCouleur_);
        assertEq(4,res_.size());
        assertEq(0,res_.getVal(Suit.HEART).total());
        assertEq(0,res_.getVal(Suit.SPADE).total());
        assertEq(0,res_.getVal(Suit.DIAMOND).total());
        assertEq(0,res_.getVal(Suit.CLUB).total());
    }
    @Test
    public void couleurs11Test(){
        HandBelote main_ = new HandBelote();
//        Map<Suit, HandBelote> hash_;
//        hash_ = new Map<>();
//        hash_.put(Suit.HEART, new HandBelote());
//        hash_.put(Suit.SPADE, new HandBelote());
//        hash_.put(Suit.DIAMOND, new HandBelote());
//        hash_.put(Suit.CLUB, new HandBelote());
        BidBeloteSuit enchereCouleur_ = new BidBeloteSuit();
        enchereCouleur_.setBid(BidBelote.ALL_TRUMP);
        IdMap<Suit,HandBelote> res_ = main_.couleurs(enchereCouleur_);
        assertEq(4,res_.size());
        assertEq(0,res_.getVal(Suit.HEART).total());
        assertEq(0,res_.getVal(Suit.SPADE).total());
        assertEq(0,res_.getVal(Suit.DIAMOND).total());
        assertEq(0,res_.getVal(Suit.CLUB).total());
    }
    @Test
    public void nombreDePlisMinAssures1(){
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
    public void eclater1(){
        HandBelote main_ = eclater();
        HandBelote cartesJouees_ = initCartesJoueesVideEclaterTest();

        BidBeloteSuit enchereCouleur_ = new BidBeloteSuit();
        enchereCouleur_.setBid(BidBelote.SUIT);
        enchereCouleur_.setSuit(Suit.HEART);


        CustList<HandBelote> normalOne_ = eclater(main_, cartesJouees_, enchereCouleur_);
        assertEq(3, normalOne_.size());
        assertEq(create(CardBelote.HEART_9), normalOne_.get(0));
        assertEq(create(CardBelote.HEART_10,CardBelote.HEART_KING), normalOne_.get(1));
        assertEq(create(CardBelote.HEART_7), normalOne_.get(2));
        enchereCouleur_.setBid(BidBelote.OTHER_SUIT);
        CustList<HandBelote> otherSuit_ = eclater(main_, cartesJouees_, enchereCouleur_);
        assertEq(3, otherSuit_.size());
        assertEq(create(CardBelote.HEART_9), otherSuit_.get(0));
        assertEq(create(CardBelote.HEART_10,CardBelote.HEART_KING), otherSuit_.get(1));
        assertEq(create(CardBelote.HEART_7), otherSuit_.get(2));
        enchereCouleur_.setBid(BidBelote.ALL_TRUMP);
        CustList<HandBelote> allTrump_ = eclater(main_, cartesJouees_, enchereCouleur_);
        assertEq(3, allTrump_.size());
        assertEq(create(CardBelote.HEART_9), allTrump_.get(0));
        assertEq(create(CardBelote.HEART_10,CardBelote.HEART_KING), allTrump_.get(1));
        assertEq(create(CardBelote.HEART_7), allTrump_.get(2));
        enchereCouleur_.setSuit(Suit.DIAMOND);
        enchereCouleur_.setBid(BidBelote.SUIT);
        CustList<HandBelote> normalTwo_ = eclater(main_, cartesJouees_, enchereCouleur_);
        assertEq(3, normalTwo_.size());
        assertEq(create(CardBelote.HEART_10, CardBelote.HEART_KING), normalTwo_.get(0));
        assertEq(create(CardBelote.HEART_9), normalTwo_.get(1));
        assertEq(create(CardBelote.HEART_7), normalTwo_.get(2));
        enchereCouleur_.setBid(BidBelote.OTHER_SUIT);
        CustList<HandBelote> otherSuitTwo_ = eclater(main_, cartesJouees_, enchereCouleur_);
        assertEq(3, otherSuitTwo_.size());
        assertEq(create(CardBelote.HEART_10, CardBelote.HEART_KING), otherSuitTwo_.get(0));
        assertEq(create(CardBelote.HEART_9), otherSuitTwo_.get(1));
        assertEq(create(CardBelote.HEART_7), otherSuitTwo_.get(2));
        enchereCouleur_.setBid(BidBelote.NO_TRUMP);
        CustList<HandBelote> noTrumpTwo_ = eclater(main_, cartesJouees_, enchereCouleur_);
        assertEq(3, noTrumpTwo_.size());
        assertEq(create(CardBelote.HEART_10, CardBelote.HEART_KING), noTrumpTwo_.get(0));
        assertEq(create(CardBelote.HEART_9), noTrumpTwo_.get(1));
        assertEq(create(CardBelote.HEART_7), noTrumpTwo_.get(2));
        cartesJouees_ = initCartesJoueesEclaterTest();
        enchereCouleur_.setBid(BidBelote.SUIT);
        enchereCouleur_.setSuit(Suit.HEART);
        enchereCouleur_.setBid(BidBelote.SUIT);
        CustList<HandBelote> normalFour_ = eclater(main_, cartesJouees_, enchereCouleur_);
        assertEq(2, normalFour_.size());
        assertEq(create(CardBelote.HEART_9,CardBelote.HEART_10,CardBelote.HEART_KING), normalFour_.get(0));
        assertEq(create(CardBelote.HEART_7), normalFour_.get(1));
        enchereCouleur_.setBid(BidBelote.OTHER_SUIT);
        CustList<HandBelote> otherSuitFour_ = eclater(main_, cartesJouees_, enchereCouleur_);
        assertEq(2, otherSuitFour_.size());
        assertEq(create(CardBelote.HEART_9,CardBelote.HEART_10,CardBelote.HEART_KING), otherSuitFour_.get(0));
        assertEq(create(CardBelote.HEART_7), otherSuitFour_.get(1));
        enchereCouleur_.setSuit(Suit.DIAMOND);
        enchereCouleur_.setBid(BidBelote.SUIT);
        CustList<HandBelote> normalFive_ = eclater(main_, cartesJouees_, enchereCouleur_);
        assertEq(3, normalFive_.size());
        assertEq(create(CardBelote.HEART_10,CardBelote.HEART_KING), normalFive_.get(0));
        assertEq(create(CardBelote.HEART_9), normalFive_.get(1));
        assertEq(create(CardBelote.HEART_7), normalFive_.get(2));
        enchereCouleur_.setBid(BidBelote.OTHER_SUIT);
        CustList<HandBelote> otherSuitFive_ = eclater(main_, cartesJouees_, enchereCouleur_);
        assertEq(3, otherSuitFive_.size());
        assertEq(create(CardBelote.HEART_10,CardBelote.HEART_KING), otherSuitFive_.get(0));
        assertEq(create(CardBelote.HEART_9), otherSuitFive_.get(1));
        assertEq(create(CardBelote.HEART_7), otherSuitFive_.get(2));
        cartesJouees_ = new HandBelote();
        cartesJouees_.ajouter(CardBelote.HEART_10);
        main_ = new HandBelote();
        main_.ajouter(CardBelote.HEART_1);
        main_.ajouter(CardBelote.HEART_KING);
        main_.ajouter(CardBelote.HEART_9);
        enchereCouleur_.setBid(BidBelote.SUIT);
        CustList<HandBelote> normalThree_ = eclater(main_, cartesJouees_, enchereCouleur_);
        assertEq(2, normalThree_.size());
        assertEq(create(CardBelote.HEART_1,CardBelote.HEART_KING), normalThree_.get(0));
        assertEq(create(CardBelote.HEART_9), normalThree_.get(1));
        enchereCouleur_.setBid(BidBelote.OTHER_SUIT);
        CustList<HandBelote> otherSuitThree_ = eclater(main_, cartesJouees_, enchereCouleur_);
        assertEq(2, otherSuitThree_.size());
        assertEq(create(CardBelote.HEART_1,CardBelote.HEART_KING), otherSuitThree_.get(0));
        assertEq(create(CardBelote.HEART_9), otherSuitThree_.get(1));
        enchereCouleur_.setBid(BidBelote.NO_TRUMP);
        CustList<HandBelote> noTrumpThree_ = eclater(main_, cartesJouees_, enchereCouleur_);
        assertEq(2, noTrumpThree_.size());
        assertEq(create(CardBelote.HEART_1,CardBelote.HEART_KING), noTrumpThree_.get(0));
        assertEq(create(CardBelote.HEART_9), noTrumpThree_.get(1));
        enchereCouleur_.setBid(BidBelote.ALL_TRUMP);
        CustList<HandBelote> allTrumpSix_ = eclater(main_, cartesJouees_, enchereCouleur_);
        assertEq(1, allTrumpSix_.size());
        assertEq(create(CardBelote.HEART_9,CardBelote.HEART_1,CardBelote.HEART_KING), allTrumpSix_.get(0));
    }
    @Test
    public void eclater2Test(){
        HandBelote main_;
        BidBeloteSuit enchereCouleur_;
        HandBelote cartesJouees_;
        IdMap<Suit,HandBelote> repartitionCartesJouees_;
        CustList<HandBelote> suites_;
        enchereCouleur_ = new BidBeloteSuit();
        enchereCouleur_.setBid(BidBelote.NO_TRUMP);
        cartesJouees_ = new HandBelote();
        cartesJouees_.ajouter(CardBelote.HEART_10);
        repartitionCartesJouees_ = cartesJouees_.couleurs(enchereCouleur_);
        main_ = new HandBelote();
        suites_ = new CustList<HandBelote>();
        assertTrue(main_.eclater(repartitionCartesJouees_, enchereCouleur_).isEmpty());
        cartesJouees_ = new HandBelote();
        repartitionCartesJouees_ = cartesJouees_.couleurs(enchereCouleur_);
        assertTrue(main_.eclater(repartitionCartesJouees_, enchereCouleur_).isEmpty());
    }
    @Test
    public void eclater3Test(){
        HandBelote main_;
        BidBeloteSuit enchereCouleur_;
        HandBelote cartesJouees_;
        IdMap<Suit,HandBelote> repartitionCartesJouees_;
        CustList<HandBelote> suites_;
        enchereCouleur_ = new BidBeloteSuit();
        enchereCouleur_.setBid(BidBelote.ALL_TRUMP);
        cartesJouees_ = new HandBelote();
        cartesJouees_.ajouter(CardBelote.HEART_10);
        repartitionCartesJouees_ = cartesJouees_.couleurs(enchereCouleur_);
        main_ = new HandBelote();
        suites_ = new CustList<HandBelote>();
        assertTrue(main_.eclater(repartitionCartesJouees_, enchereCouleur_).isEmpty());
        cartesJouees_ = new HandBelote();
        repartitionCartesJouees_ = cartesJouees_.couleurs(enchereCouleur_);
        assertTrue(main_.eclater(repartitionCartesJouees_, enchereCouleur_).isEmpty());
    }
    @Test
    public void eclater4Test(){
        HandBelote main_;
        BidBeloteSuit enchereCouleur_;
        HandBelote cartesJouees_;
        IdMap<Suit,HandBelote> repartitionCartesJouees_;
        CustList<HandBelote> suites_;
        enchereCouleur_ = new BidBeloteSuit();
        enchereCouleur_.setBid(BidBelote.SUIT);
        enchereCouleur_.setSuit(Suit.HEART);
        cartesJouees_ = new HandBelote();
        cartesJouees_.ajouter(CardBelote.HEART_10);
        repartitionCartesJouees_ = cartesJouees_.couleurs(enchereCouleur_);
        main_ = new HandBelote();
        suites_ = new CustList<HandBelote>();
        assertTrue(main_.eclater(repartitionCartesJouees_, enchereCouleur_).isEmpty());
        cartesJouees_ = new HandBelote();
        repartitionCartesJouees_ = cartesJouees_.couleurs(enchereCouleur_);
        assertTrue(main_.eclater(repartitionCartesJouees_, enchereCouleur_).isEmpty());
    }
    @Test
    public void eclater5Test(){
        HandBelote main_;
        BidBeloteSuit enchereCouleur_;
        HandBelote cartesJouees_;
        IdMap<Suit,HandBelote> repartitionCartesJouees_;
        CustList<HandBelote> suites_;
        enchereCouleur_ = new BidBeloteSuit();
        enchereCouleur_.setBid(BidBelote.SUIT);
        enchereCouleur_.setSuit(Suit.SPADE);
        cartesJouees_ = new HandBelote();
        cartesJouees_.ajouter(CardBelote.HEART_10);
        repartitionCartesJouees_ = cartesJouees_.couleurs(enchereCouleur_);
        main_ = new HandBelote();
        suites_ = new CustList<HandBelote>();
        assertTrue(main_.eclater(repartitionCartesJouees_, enchereCouleur_).isEmpty());
        cartesJouees_ = new HandBelote();
        repartitionCartesJouees_ = cartesJouees_.couleurs(enchereCouleur_);
        assertTrue(main_.eclater(repartitionCartesJouees_, enchereCouleur_).isEmpty());
    }
    @Test
    public void eclater6Test(){
        HandBelote main_;
        BidBeloteSuit enchereCouleur_;
        HandBelote cartesJouees_;
        IdMap<Suit,HandBelote> repartitionCartesJouees_;
        CustList<HandBelote> suites_;
        enchereCouleur_ = new BidBeloteSuit();
        enchereCouleur_.setBid(BidBelote.SUIT);
        enchereCouleur_.setSuit(Suit.DIAMOND);
        cartesJouees_ = new HandBelote();
        cartesJouees_.ajouter(CardBelote.HEART_10);
        repartitionCartesJouees_ = cartesJouees_.couleurs(enchereCouleur_);
        main_ = new HandBelote();
        suites_ = new CustList<HandBelote>();
        assertTrue(main_.eclater(repartitionCartesJouees_, enchereCouleur_).isEmpty());
        cartesJouees_ = new HandBelote();
        repartitionCartesJouees_ = cartesJouees_.couleurs(enchereCouleur_);
        assertTrue(main_.eclater(repartitionCartesJouees_, enchereCouleur_).isEmpty());
    }
    @Test
    public void eclater7Test(){
        HandBelote main_;
        BidBeloteSuit enchereCouleur_;
        HandBelote cartesJouees_;
        IdMap<Suit,HandBelote> repartitionCartesJouees_;
        CustList<HandBelote> suites_;
        enchereCouleur_ = new BidBeloteSuit();
        enchereCouleur_.setBid(BidBelote.SUIT);
        enchereCouleur_.setSuit(Suit.CLUB);
        cartesJouees_ = new HandBelote();
        cartesJouees_.ajouter(CardBelote.HEART_10);
        repartitionCartesJouees_ = cartesJouees_.couleurs(enchereCouleur_);
        main_ = new HandBelote();
        suites_ = new CustList<HandBelote>();
        assertTrue(main_.eclater(repartitionCartesJouees_, enchereCouleur_).isEmpty());
        cartesJouees_ = new HandBelote();
        repartitionCartesJouees_ = cartesJouees_.couleurs(enchereCouleur_);
        assertTrue(main_.eclater(repartitionCartesJouees_, enchereCouleur_).isEmpty());
    }
    @Test
    public void eclater8Test(){
        HandBelote main_;
        BidBeloteSuit enchereCouleur_;
        HandBelote cartesJouees_;
        IdMap<Suit,HandBelote> repartitionCartesJouees_;
        CustList<HandBelote> suites_;
        enchereCouleur_ = new BidBeloteSuit();
        enchereCouleur_.setBid(BidBelote.OTHER_SUIT);
        enchereCouleur_.setSuit(Suit.HEART);
        cartesJouees_ = new HandBelote();
        cartesJouees_.ajouter(CardBelote.HEART_10);
        repartitionCartesJouees_ = cartesJouees_.couleurs(enchereCouleur_);
        main_ = new HandBelote();
        suites_ = new CustList<HandBelote>();
        assertTrue(main_.eclater(repartitionCartesJouees_, enchereCouleur_).isEmpty());
        cartesJouees_ = new HandBelote();
        repartitionCartesJouees_ = cartesJouees_.couleurs(enchereCouleur_);
        assertTrue(main_.eclater(repartitionCartesJouees_, enchereCouleur_).isEmpty());
    }
    @Test
    public void eclater9Test(){
        HandBelote main_;
        BidBeloteSuit enchereCouleur_;
        HandBelote cartesJouees_;
        IdMap<Suit,HandBelote> repartitionCartesJouees_;
        CustList<HandBelote> suites_;
        enchereCouleur_ = new BidBeloteSuit();
        enchereCouleur_.setBid(BidBelote.OTHER_SUIT);
        enchereCouleur_.setSuit(Suit.SPADE);
        cartesJouees_ = new HandBelote();
        cartesJouees_.ajouter(CardBelote.HEART_10);
        repartitionCartesJouees_ = cartesJouees_.couleurs(enchereCouleur_);
        main_ = new HandBelote();
        suites_ = new CustList<HandBelote>();
        assertTrue(main_.eclater(repartitionCartesJouees_, enchereCouleur_).isEmpty());
        cartesJouees_ = new HandBelote();
        repartitionCartesJouees_ = cartesJouees_.couleurs(enchereCouleur_);
        assertTrue(main_.eclater(repartitionCartesJouees_, enchereCouleur_).isEmpty());
    }
    @Test
    public void eclater10Test(){
        HandBelote main_;
        BidBeloteSuit enchereCouleur_;
        HandBelote cartesJouees_;
        IdMap<Suit,HandBelote> repartitionCartesJouees_;
        CustList<HandBelote> suites_;
        enchereCouleur_ = new BidBeloteSuit();
        enchereCouleur_.setBid(BidBelote.OTHER_SUIT);
        enchereCouleur_.setSuit(Suit.DIAMOND);
        cartesJouees_ = new HandBelote();
        cartesJouees_.ajouter(CardBelote.HEART_10);
        repartitionCartesJouees_ = cartesJouees_.couleurs(enchereCouleur_);
        main_ = new HandBelote();
        suites_ = new CustList<HandBelote>();
        assertTrue(main_.eclater(repartitionCartesJouees_, enchereCouleur_).isEmpty());
        cartesJouees_ = new HandBelote();
        repartitionCartesJouees_ = cartesJouees_.couleurs(enchereCouleur_);
        assertTrue(main_.eclater(repartitionCartesJouees_, enchereCouleur_).isEmpty());
    }
    @Test
    public void eclater11Test(){
        HandBelote main_;
        BidBeloteSuit enchereCouleur_;
        HandBelote cartesJouees_;
        IdMap<Suit,HandBelote> repartitionCartesJouees_;
        CustList<HandBelote> suites_;
        enchereCouleur_ = new BidBeloteSuit();
        enchereCouleur_.setBid(BidBelote.OTHER_SUIT);
        enchereCouleur_.setSuit(Suit.CLUB);
        cartesJouees_ = new HandBelote();
        cartesJouees_.ajouter(CardBelote.HEART_10);
        repartitionCartesJouees_ = cartesJouees_.couleurs(enchereCouleur_);
        main_ = new HandBelote();
        suites_ = new CustList<HandBelote>();
        assertTrue(main_.eclater(repartitionCartesJouees_, enchereCouleur_).isEmpty());
        cartesJouees_ = new HandBelote();
        repartitionCartesJouees_ = cartesJouees_.couleurs(enchereCouleur_);
        assertTrue(main_.eclater(repartitionCartesJouees_, enchereCouleur_).isEmpty());
    }
    @Test
    public void annonce1(){
        Suit couleurAtout_ = Suit.UNDEFINED;
        BidBelote enchere_ = BidBelote.NO_TRUMP;
        BidBeloteSuit enchereCouleur_ = new BidBeloteSuit();
        enchereCouleur_.setBid(enchere_);
        enchereCouleur_.setSuit(couleurAtout_);
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
        assertEq(res_, annonceMain_.getHand());
        assertEq(DeclaresBelote.THIRTY, annonceMain_.getDeclare());
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
        assertEq(res_, annonceMain_.getHand());
        assertEq(DeclaresBelote.FIFTY, annonceMain_.getDeclare());
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
        assertEq(res_, annonceMain_.getHand());
        assertEq(DeclaresBelote.THIRTY, annonceMain_.getDeclare());
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
        assertEq(res_, annonceMain_.getHand());
        assertEq(DeclaresBelote.THIRTY, annonceMain_.getDeclare());
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
        assertEq(res_, annonceMain_.getHand());
        assertEq(DeclaresBelote.UNDEFINED, annonceMain_.getDeclare());
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
        assertEq(res_, annonceMain_.getHand());
        assertEq(DeclaresBelote.FOUR_1, annonceMain_.getDeclare());
        couleurAtout_ = Suit.DIAMOND;
        enchere_ = BidBelote.SUIT;
        enchereCouleur_.setBid(enchere_);
        enchereCouleur_.setSuit(couleurAtout_);
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
        assertEq(res_, annonceMain_.getHand());
        assertEq(DeclaresBelote.FIFTY, annonceMain_.getDeclare());
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
        assertEq(res_, annonceMain_.getHand());
        assertEq(DeclaresBelote.FOUR_1, annonceMain_.getDeclare());
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
        assertEq(res_, annonceMain_.getHand());
        assertEq(DeclaresBelote.FOUR_KING, annonceMain_.getDeclare());
        couleurAtout_ = Suit.HEART;
        enchere_ = BidBelote.SUIT;
        enchereCouleur_.setBid(enchere_);
        enchereCouleur_.setSuit(couleurAtout_);
        main_ = new HandBelote(Order.SUIT);
        main_.ajouter(CardBelote.DIAMOND_1);
        main_.ajouter(CardBelote.DIAMOND_JACK);
        main_.ajouter(CardBelote.DIAMOND_KING);
        main_.ajouter(CardBelote.DIAMOND_QUEEN);
        main_.ajouter(CardBelote.HEART_JACK);
        main_.ajouter(CardBelote.SPADE_1);
        main_.ajouter(CardBelote.SPADE_KING);
        main_.ajouter(CardBelote.SPADE_QUEEN);
        annonceMain_ = main_.annonce(annoncesAutorisees_, enchereCouleur_);
        res_ = new HandBelote();
        res_.ajouter(CardBelote.DIAMOND_1);
        res_.ajouter(CardBelote.DIAMOND_KING);
        res_.ajouter(CardBelote.DIAMOND_QUEEN);
        res_.ajouter(CardBelote.DIAMOND_JACK);
        assertEq(res_, annonceMain_.getHand());
        assertEq(DeclaresBelote.FIFTY, annonceMain_.getDeclare());
        couleurAtout_ = Suit.HEART;
        enchere_ = BidBelote.SUIT;
        enchereCouleur_.setBid(enchere_);
        enchereCouleur_.setSuit(couleurAtout_);
        main_ = new HandBelote(Order.SUIT);
        main_.ajouter(CardBelote.DIAMOND_KING);
        main_.ajouter(CardBelote.HEART_1);
        main_.ajouter(CardBelote.DIAMOND_JACK);
        main_.ajouter(CardBelote.DIAMOND_1);
        main_.ajouter(CardBelote.DIAMOND_QUEEN);
        main_.ajouter(CardBelote.CLUB_1);
        main_.ajouter(CardBelote.DIAMOND_10);
        main_.ajouter(CardBelote.SPADE_1);
        annonceMain_ = main_.annonce(annoncesAutorisees_, enchereCouleur_);
        res_ = new HandBelote();
        res_.ajouter(CardBelote.HEART_1);
        res_.ajouter(CardBelote.SPADE_1);
        res_.ajouter(CardBelote.DIAMOND_1);
        res_.ajouter(CardBelote.CLUB_1);
        assertEq(res_, annonceMain_.getHand());
        assertEq(DeclaresBelote.FOUR_1, annonceMain_.getDeclare());
        couleurAtout_ = Suit.UNDEFINED;
        enchere_ = BidBelote.NO_TRUMP;
        enchereCouleur_.setBid(enchere_);
        enchereCouleur_.setSuit(couleurAtout_);
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
        assertEq(res_, annonceMain_.getHand());
        assertEq(DeclaresBelote.FIFTY, annonceMain_.getDeclare());
    }
    @Test
    public void getRepartitionsValides_repartitionsToutesValides1(){
        EnumList<DealingBelote> repartitionsValides_ = DealingBelote.getRepartitionsValides();
        assertEq(2,repartitionsValides_.size());
    }
    @Test
    public void vientAvant1Test() {
        EnumList<Suit> suits_ = new EnumList<Suit>();
        suits_.add(Suit.SPADE);
        suits_.add(Suit.HEART);
        suits_.add(Suit.CLUB);
        suits_.add(Suit.DIAMOND);
        assertTrue(CardBelote.SPADE_KING.vientAvant(CardBelote.HEART_KING,false,Order.SUIT,suits_));
    }
    @Test
    public void vientAvant2Test() {
        EnumList<Suit> suits_ = new EnumList<Suit>();
        suits_.add(Suit.SPADE);
        suits_.add(Suit.HEART);
        suits_.add(Suit.CLUB);
        suits_.add(Suit.DIAMOND);
        assertTrue(CardBelote.SPADE_QUEEN.vientAvant(CardBelote.SPADE_KING,false,Order.SUIT,suits_));
    }
    @Test
    public void vientAvant3Test() {
        EnumList<Suit> suits_ = new EnumList<Suit>();
        suits_.add(Suit.SPADE);
        suits_.add(Suit.HEART);
        suits_.add(Suit.CLUB);
        suits_.add(Suit.DIAMOND);
        assertTrue(CardBelote.SPADE_KING.vientAvant(CardBelote.HEART_KING,true,Order.SUIT,suits_));
    }
    @Test
    public void vientAvant4Test() {
        EnumList<Suit> suits_ = new EnumList<Suit>();
        suits_.add(Suit.SPADE);
        suits_.add(Suit.HEART);
        suits_.add(Suit.CLUB);
        suits_.add(Suit.DIAMOND);
        assertTrue(!CardBelote.SPADE_QUEEN.vientAvant(CardBelote.SPADE_KING,true,Order.SUIT,suits_));
    }
    @Test
    public void vientAvant5Test() {
        EnumList<Suit> suits_ = new EnumList<Suit>();
        suits_.add(Suit.SPADE);
        suits_.add(Suit.HEART);
        suits_.add(Suit.CLUB);
        suits_.add(Suit.DIAMOND);
        assertTrue(CardBelote.SPADE_KING.vientAvant(CardBelote.HEART_KING,false,Order.TRUMP,suits_));
    }
    @Test
    public void vientAvant6Test() {
        EnumList<Suit> suits_ = new EnumList<Suit>();
        suits_.add(Suit.SPADE);
        suits_.add(Suit.HEART);
        suits_.add(Suit.CLUB);
        suits_.add(Suit.DIAMOND);
        assertTrue(CardBelote.SPADE_QUEEN.vientAvant(CardBelote.SPADE_KING,false,Order.TRUMP,suits_));
    }
    @Test
    public void vientAvant7Test() {
        EnumList<Suit> suits_ = new EnumList<Suit>();
        suits_.add(Suit.SPADE);
        suits_.add(Suit.HEART);
        suits_.add(Suit.CLUB);
        suits_.add(Suit.DIAMOND);
        assertTrue(CardBelote.SPADE_KING.vientAvant(CardBelote.HEART_KING,true,Order.TRUMP,suits_));
    }
    @Test
    public void vientAvant8Test() {
        EnumList<Suit> suits_ = new EnumList<Suit>();
        suits_.add(Suit.SPADE);
        suits_.add(Suit.HEART);
        suits_.add(Suit.CLUB);
        suits_.add(Suit.DIAMOND);
        assertTrue(!CardBelote.SPADE_QUEEN.vientAvant(CardBelote.SPADE_KING,true,Order.TRUMP,suits_));
    }
    @Test
    public void vientAvant9Test() {
        EnumList<Suit> suits_ = new EnumList<Suit>();
        suits_.add(Suit.SPADE);
        suits_.add(Suit.HEART);
        suits_.add(Suit.CLUB);
        suits_.add(Suit.DIAMOND);
        assertTrue(!CardBelote.HEART_KING.vientAvant(CardBelote.SPADE_KING,true,Order.SUIT,suits_));
    }
    @Test
    public void vientAvant10Test() {
        EnumList<Suit> suits_ = new EnumList<Suit>();
        suits_.add(Suit.SPADE);
        suits_.add(Suit.HEART);
        suits_.add(Suit.CLUB);
        suits_.add(Suit.DIAMOND);
        assertTrue(!CardBelote.HEART_KING.vientAvant(CardBelote.SPADE_KING,true,Order.TRUMP,suits_));
    }
    @Test
    public void nombreCartesPoints1Test() {
        HandBelote h_ = new HandBelote();
        h_.ajouter(CardBelote.HEART_7);
        h_.ajouter(CardBelote.HEART_10);
        h_.ajouter(CardBelote.HEART_9);
        h_.ajouter(CardBelote.SPADE_7);
        h_.ajouter(CardBelote.SPADE_10);
        h_.ajouter(CardBelote.SPADE_9);
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.SUIT);
        b_.setSuit(Suit.HEART);
        assertEq(3, h_.nombreCartesPoints(b_));
    }
    @Test
    public void nombreCartesPoints2Test() {
        HandBelote h_ = new HandBelote();
        h_.ajouter(CardBelote.HEART_7);
        h_.ajouter(CardBelote.HEART_10);
        h_.ajouter(CardBelote.HEART_9);
        h_.ajouter(CardBelote.SPADE_7);
        h_.ajouter(CardBelote.SPADE_10);
        h_.ajouter(CardBelote.SPADE_9);
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.NO_TRUMP);
        assertEq(2, h_.nombreCartesPoints(b_));
    }
    @Test
    public void nombreCartesPoints3Test() {
        HandBelote h_ = new HandBelote();
        h_.ajouter(CardBelote.HEART_7);
        h_.ajouter(CardBelote.HEART_10);
        h_.ajouter(CardBelote.HEART_9);
        h_.ajouter(CardBelote.SPADE_7);
        h_.ajouter(CardBelote.SPADE_10);
        h_.ajouter(CardBelote.SPADE_9);
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.ALL_TRUMP);
        assertEq(4, h_.nombreCartesPoints(b_));
    }
    @Test
    public void cartesPlisAssures1Test() {
        HandBelote h_ = new HandBelote();
        h_.ajouter(CardBelote.HEART_7);
        h_.ajouter(CardBelote.HEART_10);
        h_.ajouter(CardBelote.HEART_9);
        h_.ajouter(CardBelote.SPADE_7);
        h_.ajouter(CardBelote.SPADE_10);
        h_.ajouter(CardBelote.SPADE_9);
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setSuit(Suit.HEART);
        b_.setBid(BidBelote.SUIT);
        IdMap<Suit, HandBelote> rep_ = h_.couleurs(b_);
        assertEq(0, rep_.getVal(Suit.CLUB).cartesPlisAssures(b_).total());
    }
    @Test
    public void cartesPlisAssures2Test() {
        HandBelote h_ = new HandBelote();
        h_.ajouter(CardBelote.HEART_7);
        h_.ajouter(CardBelote.HEART_10);
        h_.ajouter(CardBelote.HEART_9);
        h_.ajouter(CardBelote.SPADE_7);
        h_.ajouter(CardBelote.SPADE_10);
        h_.ajouter(CardBelote.SPADE_9);
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setSuit(Suit.HEART);
        b_.setBid(BidBelote.SUIT);
        IdMap<Suit, HandBelote> rep_ = h_.couleurs(b_);
        assertEq(0, rep_.getVal(Suit.SPADE).cartesPlisAssures(b_).total());
    }
    @Test
    public void cartesPlisAssures3Test() {
        HandBelote h_ = new HandBelote();
        h_.ajouter(CardBelote.HEART_7);
        h_.ajouter(CardBelote.HEART_10);
        h_.ajouter(CardBelote.HEART_9);
        h_.ajouter(CardBelote.SPADE_7);
        h_.ajouter(CardBelote.SPADE_1);
        h_.ajouter(CardBelote.SPADE_9);
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setSuit(Suit.HEART);
        b_.setBid(BidBelote.SUIT);
        IdMap<Suit, HandBelote> rep_ = h_.couleurs(b_);
        HandBelote cards_ = rep_.getVal(Suit.SPADE).cartesPlisAssures(b_);
        assertEq(1, cards_.total());
        assertTrue(cards_.contient(CardBelote.SPADE_1));
    }
    @Test
    public void cartesPlisAssures4Test() {
        HandBelote h_ = new HandBelote();
        h_.ajouter(CardBelote.HEART_7);
        h_.ajouter(CardBelote.HEART_1);
        h_.ajouter(CardBelote.HEART_9);
        h_.ajouter(CardBelote.SPADE_7);
        h_.ajouter(CardBelote.SPADE_1);
        h_.ajouter(CardBelote.SPADE_9);
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setSuit(Suit.HEART);
        b_.setBid(BidBelote.SUIT);
        IdMap<Suit, HandBelote> rep_ = h_.couleurs(b_);
        HandBelote cards_ = rep_.getVal(Suit.HEART).cartesPlisAssures(b_);
        assertEq(1, cards_.total());
        assertTrue(cards_.contient(CardBelote.HEART_9));
    }
    @Test
    public void cartesPlisAssures5Test() {
        HandBelote h_ = new HandBelote();
        h_.ajouter(CardBelote.HEART_JACK);
        h_.ajouter(CardBelote.HEART_1);
        h_.ajouter(CardBelote.HEART_9);
        h_.ajouter(CardBelote.SPADE_7);
        h_.ajouter(CardBelote.SPADE_1);
        h_.ajouter(CardBelote.SPADE_9);
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setSuit(Suit.HEART);
        b_.setBid(BidBelote.SUIT);
        IdMap<Suit, HandBelote> rep_ = h_.couleurs(b_);
        HandBelote cards_ = rep_.getVal(Suit.HEART).cartesPlisAssures(b_);
        assertEq(3, cards_.total());
        assertTrue(cards_.contient(CardBelote.HEART_JACK));
        assertTrue(cards_.contient(CardBelote.HEART_9));
        assertTrue(cards_.contient(CardBelote.HEART_1));
    }
    @Test
    public void cartesPlisAssures6Test() {
        HandBelote h_ = new HandBelote();
        h_.ajouter(CardBelote.HEART_10);
        h_.ajouter(CardBelote.HEART_1);
        h_.ajouter(CardBelote.HEART_9);
        h_.ajouter(CardBelote.SPADE_7);
        h_.ajouter(CardBelote.SPADE_1);
        h_.ajouter(CardBelote.SPADE_9);
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setSuit(Suit.HEART);
        b_.setBid(BidBelote.SUIT);
        IdMap<Suit, HandBelote> rep_ = h_.couleurs(b_);
        HandBelote cards_ = rep_.getVal(Suit.HEART).cartesPlisAssures(b_);
        assertEq(2, cards_.total());
        assertTrue(cards_.contient(CardBelote.HEART_9));
        assertTrue(cards_.contient(CardBelote.HEART_1));
    }
    @Test
    public void cartesPlisAssures7Test() {
        HandBelote h_ = new HandBelote();
        h_.ajouter(CardBelote.HEART_KING);
        h_.ajouter(CardBelote.HEART_1);
        h_.ajouter(CardBelote.HEART_9);
        h_.ajouter(CardBelote.HEART_QUEEN);
        h_.ajouter(CardBelote.SPADE_1);
        h_.ajouter(CardBelote.SPADE_9);
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setSuit(Suit.HEART);
        b_.setBid(BidBelote.SUIT);
        IdMap<Suit, HandBelote> rep_ = h_.couleurs(b_);
        HandBelote cards_ = rep_.getVal(Suit.HEART).cartesPlisAssures(b_);
        assertEq(2, cards_.total());
        assertTrue(cards_.contient(CardBelote.HEART_9));
        assertTrue(cards_.contient(CardBelote.HEART_KING));
    }
    @Test
    public void cartesPlisAssures8Test() {
        HandBelote h_ = new HandBelote();
        h_.ajouter(CardBelote.HEART_KING);
        h_.ajouter(CardBelote.HEART_1);
        h_.ajouter(CardBelote.HEART_9);
        h_.ajouter(CardBelote.HEART_QUEEN);
        h_.ajouter(CardBelote.SPADE_1);
        h_.ajouter(CardBelote.SPADE_9);
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setSuit(Suit.UNDEFINED);
        b_.setBid(BidBelote.NO_TRUMP);
        IdMap<Suit, HandBelote> rep_ = h_.couleurs(b_);
        HandBelote cards_ = rep_.getVal(Suit.HEART).cartesPlisAssures(b_);
        assertEq(2, cards_.total());
        assertTrue(cards_.contient(CardBelote.HEART_1));
        assertTrue(cards_.contient(CardBelote.HEART_KING));
    }
    @Test
    public void pointsAvg1Test() {
        HandBelote h_ = new HandBelote();
        h_.ajouter(CardBelote.HEART_KING);
        h_.ajouter(CardBelote.HEART_JACK);
        h_.ajouter(CardBelote.HEART_9);
        h_.ajouter(CardBelote.HEART_QUEEN);
        h_.ajouter(CardBelote.SPADE_1);
        h_.ajouter(CardBelote.SPADE_9);
        IdMap<Suit, Long> res_ = h_.pointsAvg(4,8);
        assertEq(122, res_.getVal(Suit.HEART));
        assertEq(69, res_.getVal(Suit.SPADE));
        assertEq(62, res_.getVal(Suit.DIAMOND));
        assertEq(62, res_.getVal(Suit.CLUB));
    }
    @Test
    public void pointsAvg2Test() {
        HandBelote h_ = new HandBelote();
        h_.ajouter(CardBelote.HEART_KING);
        h_.ajouter(CardBelote.HEART_1);
        h_.ajouter(CardBelote.HEART_9);
        h_.ajouter(CardBelote.HEART_QUEEN);
        h_.ajouter(CardBelote.SPADE_1);
        h_.ajouter(CardBelote.SPADE_9);
        IdMap<Suit, Long> res_ = h_.pointsAvg(4, 8);
        assertEq(97, res_.getVal(Suit.HEART));
        assertEq(77, res_.getVal(Suit.SPADE));
        assertEq(73, res_.getVal(Suit.DIAMOND));
        assertEq(73, res_.getVal(Suit.CLUB));
    }
    @Test
    public void pointsAvg3Test() {
        HandBelote h_ = new HandBelote();
        h_.ajouter(CardBelote.HEART_KING);
        h_.ajouter(CardBelote.HEART_10);
        h_.ajouter(CardBelote.HEART_9);
        h_.ajouter(CardBelote.HEART_QUEEN);
        h_.ajouter(CardBelote.SPADE_10);
        h_.ajouter(CardBelote.SPADE_9);
        IdMap<Suit, Long> res_ = h_.pointsAvg(4, 8);
        assertEq(65, res_.getVal(Suit.HEART));
        assertEq(42, res_.getVal(Suit.SPADE));
        assertEq(42, res_.getVal(Suit.DIAMOND));
        assertEq(42, res_.getVal(Suit.CLUB));
    }
    @Test
    public void pointsAvg4Test() {
        HandBelote h_ = new HandBelote();
        h_.ajouter(CardBelote.HEART_8);
        h_.ajouter(CardBelote.HEART_10);
        h_.ajouter(CardBelote.HEART_9);
        h_.ajouter(CardBelote.HEART_QUEEN);
        h_.ajouter(CardBelote.SPADE_10);
        h_.ajouter(CardBelote.SPADE_9);
        IdMap<Suit, Long> res_ = h_.pointsAvg(4, 8);
        assertEq(17, res_.getVal(Suit.HEART));
        assertEq(13, res_.getVal(Suit.SPADE));
        assertEq(13, res_.getVal(Suit.DIAMOND));
        assertEq(13, res_.getVal(Suit.CLUB));
    }
    @Test
    public void pointsAvg5Test() {
        HandBelote h_ = new HandBelote();
        h_.ajouter(CardBelote.HEART_1);
        h_.ajouter(CardBelote.HEART_JACK);
        h_.ajouter(CardBelote.HEART_9);
        h_.ajouter(CardBelote.CLUB_1);
        h_.ajouter(CardBelote.SPADE_1);
        h_.ajouter(CardBelote.DIAMOND_1);
        IdMap<Suit, Long> res_ = h_.pointsAvg(4,8);
        assertEq(168, res_.getVal(Suit.HEART));
        assertEq(76, res_.getVal(Suit.SPADE));
        assertEq(76, res_.getVal(Suit.DIAMOND));
        assertEq(76, res_.getVal(Suit.CLUB));
    }
    @Test
    public void pointsBid1Test() {
        HandBelote h_ = new HandBelote();
        h_.ajouter(CardBelote.HEART_1);
        h_.ajouter(CardBelote.HEART_JACK);
        h_.ajouter(CardBelote.HEART_9);
        h_.ajouter(CardBelote.CLUB_1);
        h_.ajouter(CardBelote.SPADE_1);
        h_.ajouter(CardBelote.DIAMOND_1);
        assertEq(144, h_.pointsBid(4,8,BidBelote.NO_TRUMP));
    }
    @Test
    public void pointsBid2Test() {
        HandBelote h_ = new HandBelote();
        h_.ajouter(CardBelote.HEART_7);
        h_.ajouter(CardBelote.HEART_8);
        h_.ajouter(CardBelote.HEART_JACK);
        h_.ajouter(CardBelote.CLUB_JACK);
        h_.ajouter(CardBelote.SPADE_JACK);
        h_.ajouter(CardBelote.DIAMOND_JACK);
        assertEq(133, h_.pointsBid(4,8,BidBelote.ALL_TRUMP));
    }

    private static CustList<HandBelote> eclater(HandBelote _main, HandBelote _cartesJouees, BidBeloteSuit _enchereCouleur) {
        return _main.eclater(_cartesJouees.couleurs(_enchereCouleur), _enchereCouleur);
    }

}