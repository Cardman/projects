package cards.belote;
import static cards.belote.EquallableBeloteUtil.assertEq;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Ignore;
import org.junit.Test;

import cards.belote.enumerations.BidBelote;
import cards.belote.enumerations.CardBelote;
import cards.consts.Suit;
import code.util.CustList;

@Ignore
@SuppressWarnings("static-method")
public class CardBeloteTest {
    @Test
    public void isPlayable1Test(){
        assertTrue(CardBelote.HEART_JACK.isPlayable());
        assertNotEquals(Suit.UNDEFINED, CardBelote.HEART_JACK.couleur());
        assertNotEquals(Suit.TRUMP, CardBelote.HEART_JACK.couleur());
    }
    @Test
    public void isPlayable2Test(){
        assertTrue(CardBelote.HEART_KING.isPlayable());
        assertNotEquals(Suit.UNDEFINED, CardBelote.HEART_KING.couleur());
        assertNotEquals(Suit.TRUMP, CardBelote.HEART_KING.couleur());
    }
    @Test
    public void isPlayable3Test(){
        assertTrue(CardBelote.HEART_QUEEN.isPlayable());
        assertNotEquals(Suit.UNDEFINED, CardBelote.HEART_QUEEN.couleur());
        assertNotEquals(Suit.TRUMP, CardBelote.HEART_QUEEN.couleur());
    }
    @Test
    public void isPlayable4Test(){
        assertTrue(CardBelote.SPADE_JACK.isPlayable());
        assertNotEquals(Suit.UNDEFINED, CardBelote.SPADE_JACK.couleur());
        assertNotEquals(Suit.TRUMP, CardBelote.SPADE_JACK.couleur());
    }
    @Test
    public void isPlayable5Test(){
        assertTrue(CardBelote.SPADE_KING.isPlayable());
        assertNotEquals(Suit.UNDEFINED, CardBelote.SPADE_KING.couleur());
        assertNotEquals(Suit.TRUMP, CardBelote.SPADE_KING.couleur());
    }
    @Test
    public void isPlayable6Test(){
        assertTrue(CardBelote.SPADE_QUEEN.isPlayable());
        assertNotEquals(Suit.UNDEFINED, CardBelote.SPADE_QUEEN.couleur());
        assertNotEquals(Suit.TRUMP, CardBelote.SPADE_QUEEN.couleur());
    }
    @Test
    public void isPlayable7Test(){
        assertTrue(CardBelote.DIAMOND_JACK.isPlayable());
        assertNotEquals(Suit.UNDEFINED, CardBelote.DIAMOND_JACK.couleur());
        assertNotEquals(Suit.TRUMP, CardBelote.DIAMOND_JACK.couleur());
    }
    @Test
    public void isPlayable8Test(){
        assertTrue(CardBelote.DIAMOND_KING.isPlayable());
        assertNotEquals(Suit.UNDEFINED, CardBelote.DIAMOND_KING.couleur());
        assertNotEquals(Suit.TRUMP, CardBelote.DIAMOND_KING.couleur());
    }
    @Test
    public void isPlayable9Test(){
        assertTrue(CardBelote.DIAMOND_QUEEN.isPlayable());
        assertNotEquals(Suit.UNDEFINED, CardBelote.DIAMOND_QUEEN.couleur());
        assertNotEquals(Suit.TRUMP, CardBelote.DIAMOND_QUEEN.couleur());
    }
    @Test
    public void isPlayable10Test(){
        assertTrue(CardBelote.CLUB_JACK.isPlayable());
        assertNotEquals(Suit.UNDEFINED, CardBelote.CLUB_JACK.couleur());
        assertNotEquals(Suit.TRUMP, CardBelote.CLUB_JACK.couleur());
    }
    @Test
    public void isPlayable11Test(){
        assertTrue(CardBelote.CLUB_KING.isPlayable());
        assertNotEquals(Suit.UNDEFINED, CardBelote.CLUB_KING.couleur());
        assertNotEquals(Suit.TRUMP, CardBelote.CLUB_KING.couleur());
    }
    @Test
    public void isPlayable12Test(){
        assertTrue(CardBelote.CLUB_QUEEN.isPlayable());
        assertNotEquals(Suit.UNDEFINED, CardBelote.CLUB_QUEEN.couleur());
        assertNotEquals(Suit.TRUMP, CardBelote.CLUB_QUEEN.couleur());
    }
    @Test
    public void isPlayable13Test(){
        assertTrue(CardBelote.HEART_1.isPlayable());
        assertNotEquals(Suit.UNDEFINED, CardBelote.HEART_1.couleur());
        assertNotEquals(Suit.TRUMP, CardBelote.HEART_1.couleur());
    }
    @Test
    public void isPlayable14Test(){
        assertTrue(CardBelote.HEART_10.isPlayable());
        assertNotEquals(Suit.UNDEFINED, CardBelote.HEART_10.couleur());
        assertNotEquals(Suit.TRUMP, CardBelote.HEART_10.couleur());
    }
    @Test
    public void isPlayable15Test(){
        assertTrue(CardBelote.HEART_9.isPlayable());
        assertNotEquals(Suit.UNDEFINED, CardBelote.HEART_9.couleur());
        assertNotEquals(Suit.TRUMP, CardBelote.HEART_9.couleur());
    }
    @Test
    public void isPlayable16Test(){
        assertTrue(CardBelote.HEART_8.isPlayable());
        assertNotEquals(Suit.UNDEFINED, CardBelote.HEART_8.couleur());
        assertNotEquals(Suit.TRUMP, CardBelote.HEART_8.couleur());
    }
    @Test
    public void isPlayable17Test(){
        assertTrue(CardBelote.HEART_7.isPlayable());
        assertNotEquals(Suit.UNDEFINED, CardBelote.HEART_7.couleur());
        assertNotEquals(Suit.TRUMP, CardBelote.HEART_7.couleur());
    }
    @Test
    public void isPlayable18Test(){
        assertTrue(CardBelote.SPADE_1.isPlayable());
        assertNotEquals(Suit.UNDEFINED, CardBelote.SPADE_1.couleur());
        assertNotEquals(Suit.TRUMP, CardBelote.SPADE_1.couleur());
    }
    @Test
    public void isPlayable19Test(){
        assertTrue(CardBelote.SPADE_10.isPlayable());
        assertNotEquals(Suit.UNDEFINED, CardBelote.SPADE_10.couleur());
        assertNotEquals(Suit.TRUMP, CardBelote.SPADE_10.couleur());
    }
    @Test
    public void isPlayable20Test(){
        assertTrue(CardBelote.SPADE_9.isPlayable());
        assertNotEquals(Suit.UNDEFINED, CardBelote.SPADE_9.couleur());
        assertNotEquals(Suit.TRUMP, CardBelote.SPADE_9.couleur());
    }
    @Test
    public void isPlayable21Test(){
        assertTrue(CardBelote.SPADE_8.isPlayable());
        assertNotEquals(Suit.UNDEFINED, CardBelote.SPADE_8.couleur());
        assertNotEquals(Suit.TRUMP, CardBelote.SPADE_8.couleur());
    }
    @Test
    public void isPlayable22Test(){
        assertTrue(CardBelote.SPADE_7.isPlayable());
        assertNotEquals(Suit.UNDEFINED, CardBelote.SPADE_7.couleur());
        assertNotEquals(Suit.TRUMP, CardBelote.SPADE_7.couleur());
    }
    @Test
    public void isPlayable23Test(){
        assertTrue(CardBelote.DIAMOND_1.isPlayable());
        assertNotEquals(Suit.UNDEFINED, CardBelote.DIAMOND_1.couleur());
        assertNotEquals(Suit.TRUMP, CardBelote.DIAMOND_1.couleur());
    }
    @Test
    public void isPlayable24Test(){
        assertTrue(CardBelote.DIAMOND_10.isPlayable());
        assertNotEquals(Suit.UNDEFINED, CardBelote.DIAMOND_10.couleur());
        assertNotEquals(Suit.TRUMP, CardBelote.DIAMOND_10.couleur());
    }
    @Test
    public void isPlayable25Test(){
        assertTrue(CardBelote.DIAMOND_9.isPlayable());
        assertNotEquals(Suit.UNDEFINED, CardBelote.DIAMOND_9.couleur());
        assertNotEquals(Suit.TRUMP, CardBelote.DIAMOND_9.couleur());
    }
    @Test
    public void isPlayable26Test(){
        assertTrue(CardBelote.DIAMOND_8.isPlayable());
        assertNotEquals(Suit.UNDEFINED, CardBelote.DIAMOND_8.couleur());
        assertNotEquals(Suit.TRUMP, CardBelote.DIAMOND_8.couleur());
    }
    @Test
    public void isPlayable27Test(){
        assertTrue(CardBelote.DIAMOND_7.isPlayable());
        assertNotEquals(Suit.UNDEFINED, CardBelote.DIAMOND_7.couleur());
        assertNotEquals(Suit.TRUMP, CardBelote.DIAMOND_7.couleur());
    }
    @Test
    public void isPlayable28Test(){
        assertTrue(CardBelote.CLUB_1.isPlayable());
        assertNotEquals(Suit.UNDEFINED, CardBelote.CLUB_1.couleur());
        assertNotEquals(Suit.TRUMP, CardBelote.CLUB_1.couleur());
    }
    @Test
    public void isPlayable29Test(){
        assertTrue(CardBelote.CLUB_10.isPlayable());
        assertNotEquals(Suit.UNDEFINED, CardBelote.CLUB_10.couleur());
        assertNotEquals(Suit.TRUMP, CardBelote.CLUB_10.couleur());
    }
    @Test
    public void isPlayable30Test(){
        assertTrue(CardBelote.CLUB_9.isPlayable());
        assertNotEquals(Suit.UNDEFINED, CardBelote.CLUB_9.couleur());
        assertNotEquals(Suit.TRUMP, CardBelote.CLUB_9.couleur());
    }
    @Test
    public void isPlayable31Test(){
        assertTrue(CardBelote.CLUB_8.isPlayable());
        assertNotEquals(Suit.UNDEFINED, CardBelote.CLUB_8.couleur());
        assertNotEquals(Suit.TRUMP, CardBelote.CLUB_8.couleur());
    }
    @Test
    public void isPlayable32Test(){
        assertTrue(CardBelote.CLUB_7.isPlayable());
        assertNotEquals(Suit.UNDEFINED, CardBelote.CLUB_7.couleur());
        assertNotEquals(Suit.TRUMP, CardBelote.CLUB_7.couleur());
    }
    @Test
    public void isPlayable33(){
        assertTrue(!CardBelote.WHITE.isPlayable());
        assertTrue(!CardBelote.WHITE.isCharacter());
        assertEq(0, CardBelote.WHITE.valeur());
        assertEq(Suit.UNDEFINED, CardBelote.WHITE.couleur());
    }
    @Test
    public void isCharacter_false1Test(){
        assertTrue(!CardBelote.HEART_1.isCharacter());
        assertTrue(CardBelote.HEART_1.valeur()>0);
    }
    @Test
    public void isCharacter_false2Test(){
        assertTrue(!CardBelote.HEART_10.isCharacter());
        assertTrue(CardBelote.HEART_10.valeur()>0);
    }
    @Test
    public void isCharacter_false3Test(){
        assertTrue(!CardBelote.HEART_9.isCharacter());
        assertTrue(CardBelote.HEART_9.valeur()>0);
    }
    @Test
    public void isCharacter_false4Test(){
        assertTrue(!CardBelote.HEART_8.isCharacter());
        assertTrue(CardBelote.HEART_8.valeur()>0);
    }
    @Test
    public void isCharacter_false5Test(){
        assertTrue(!CardBelote.HEART_7.isCharacter());
        assertTrue(CardBelote.HEART_7.valeur()>0);
    }
    @Test
    public void isCharacter_false6Test(){
        assertTrue(!CardBelote.SPADE_1.isCharacter());
        assertTrue(CardBelote.SPADE_1.valeur()>0);
    }
    @Test
    public void isCharacter_false7Test(){
        assertTrue(!CardBelote.SPADE_10.isCharacter());
        assertTrue(CardBelote.SPADE_10.valeur()>0);
    }
    @Test
    public void isCharacter_false8Test(){
        assertTrue(!CardBelote.SPADE_9.isCharacter());
        assertTrue(CardBelote.SPADE_9.valeur()>0);
    }
    @Test
    public void isCharacter_false9Test(){
        assertTrue(!CardBelote.SPADE_8.isCharacter());
        assertTrue(CardBelote.SPADE_8.valeur()>0);
    }
    @Test
    public void isCharacter_false10Test(){
        assertTrue(!CardBelote.SPADE_7.isCharacter());
        assertTrue(CardBelote.SPADE_7.valeur()>0);
    }
    @Test
    public void isCharacter_false11Test(){
        assertTrue(!CardBelote.DIAMOND_1.isCharacter());
        assertTrue(CardBelote.DIAMOND_1.valeur()>0);
    }
    @Test
    public void isCharacter_false12Test(){
        assertTrue(!CardBelote.DIAMOND_10.isCharacter());
        assertTrue(CardBelote.DIAMOND_10.valeur()>0);
    }
    @Test
    public void isCharacter_false13Test(){
        assertTrue(!CardBelote.DIAMOND_9.isCharacter());
        assertTrue(CardBelote.DIAMOND_9.valeur()>0);
    }
    @Test
    public void isCharacter_false14Test(){
        assertTrue(!CardBelote.DIAMOND_8.isCharacter());
        assertTrue(CardBelote.DIAMOND_8.valeur()>0);
    }
    @Test
    public void isCharacter_false15Test(){
        assertTrue(!CardBelote.DIAMOND_7.isCharacter());
        assertTrue(CardBelote.DIAMOND_7.valeur()>0);
    }
    @Test
    public void isCharacter_false16Test(){
        assertTrue(!CardBelote.CLUB_1.isCharacter());
        assertTrue(CardBelote.CLUB_1.valeur()>0);
    }
    @Test
    public void isCharacter_false17Test(){
        assertTrue(!CardBelote.CLUB_10.isCharacter());
        assertTrue(CardBelote.CLUB_10.valeur()>0);
    }
    @Test
    public void isCharacter_false18Test(){
        assertTrue(!CardBelote.CLUB_9.isCharacter());
        assertTrue(CardBelote.CLUB_9.valeur()>0);
    }
    @Test
    public void isCharacter_false19Test(){
        assertTrue(!CardBelote.CLUB_8.isCharacter());
        assertTrue(CardBelote.CLUB_8.valeur()>0);
    }
    @Test
    public void isCharacter_false20Test(){
        assertTrue(!CardBelote.CLUB_7.isCharacter());
        assertTrue(CardBelote.CLUB_7.valeur()>0);
    }
    @Test
    public void isCharacter_true1Test(){
        assertTrue(CardBelote.HEART_JACK.isCharacter());
        assertEq(0, CardBelote.HEART_JACK.valeur());
    }
    @Test
    public void isCharacter_true2Test(){
        assertTrue(CardBelote.HEART_KING.isCharacter());
        assertEq(0, CardBelote.HEART_KING.valeur());
    }
    @Test
    public void isCharacter_true3Test(){
        assertTrue(CardBelote.HEART_QUEEN.isCharacter());
        assertEq(0, CardBelote.HEART_QUEEN.valeur());
    }
    @Test
    public void isCharacter_true4Test(){
        assertTrue(CardBelote.SPADE_JACK.isCharacter());
        assertEq(0, CardBelote.SPADE_JACK.valeur());
    }
    @Test
    public void isCharacter_true5Test(){
        assertTrue(CardBelote.SPADE_KING.isCharacter());
        assertEq(0, CardBelote.SPADE_KING.valeur());
    }
    @Test
    public void isCharacter_true6Test(){
        assertTrue(CardBelote.SPADE_QUEEN.isCharacter());
        assertEq(0, CardBelote.SPADE_QUEEN.valeur());
    }
    @Test
    public void isCharacter_true7Test(){
        assertTrue(CardBelote.DIAMOND_JACK.isCharacter());
        assertEq(0, CardBelote.DIAMOND_JACK.valeur());
    }
    @Test
    public void isCharacter_true8Test(){
        assertTrue(CardBelote.DIAMOND_KING.isCharacter());
        assertEq(0, CardBelote.DIAMOND_KING.valeur());
    }
    @Test
    public void isCharacter_true9Test(){
        assertTrue(CardBelote.DIAMOND_QUEEN.isCharacter());
        assertEq(0, CardBelote.DIAMOND_QUEEN.valeur());
    }
    @Test
    public void isCharacter_true10Test(){
        assertTrue(CardBelote.CLUB_JACK.isCharacter());
        assertEq(0, CardBelote.CLUB_JACK.valeur());
    }
    @Test
    public void isCharacter_true11Test(){
        assertTrue(CardBelote.CLUB_KING.isCharacter());
        assertEq(0, CardBelote.CLUB_KING.valeur());
    }
    @Test
    public void isCharacter_true12Test(){
        assertTrue(CardBelote.CLUB_QUEEN.isCharacter());
        assertEq(0, CardBelote.CLUB_QUEEN.valeur());
    }


    Object[] cardsSuits() {
        CustList<Object[]> args_ = new CustList<Object[]>();
        for (Suit t: Suit.couleursOrdinaires()) {
            for (Suit d: Suit.couleursOrdinaires()) {
                if (t == d) {
                    continue;
                }
                for (CardBelote tCard_: CardBelote.values()) {
                    if (tCard_.couleur() != t) {
                        continue;
                    }
                    for (CardBelote dCard_: CardBelote.values()) {
                        if (dCard_.couleur() != d) {
                            continue;
                        }
                        args_.add(new Object[]{d,t,dCard_,tCard_});
                    }
                }
            }
        }
        return args_.toArray();
    }

    @Test
    public void strength1Test(Suit _couleurDemandee,Suit _couleurAtout, CardBelote _demCards, CardBelote _trumpCard) {
        byte force_ = _trumpCard.strength(_couleurAtout, _couleurDemandee);
        byte forceTwo_ = _demCards.strength(_couleurAtout, _couleurDemandee);
        assertTrue(force_>forceTwo_);
    }

    Object[] cardsSuitsTrump() {
        CustList<Object[]> args_ = new CustList<Object[]>();
        for (Suit t: Suit.couleursOrdinaires()) {
            for (Suit d: Suit.couleursOrdinaires()) {
                if (t == d) {
                    continue;
                }
                for (CardBelote tCard_: CardBelote.values()) {
                    if (tCard_.couleur() != t) {
                        continue;
                    }
                    for (CardBelote tCardTwo_: CardBelote.values()) {
                        if (tCardTwo_.couleur() != t) {
                            continue;
                        }
                        if (tCard_ == tCardTwo_) {
                            continue;
                        }
                        args_.add(new Object[]{d,t,tCard_, tCardTwo_});
                    }
                }
            }
        }
        return args_.toArray();
    }

    @Test
    public void strength2Test(Suit _couleurDemandee,Suit _couleurAtout, CardBelote _trumpCard, CardBelote _trumpCardTwo) {
        byte force_ = _trumpCard.strength(_couleurAtout, _couleurDemandee);
        byte forceTwo_ = _trumpCardTwo.strength(_couleurAtout, _couleurDemandee);
        assertNotEquals(force_, forceTwo_);
        assertTrue(force_ > 0);
    }

    Object[] cardsSuitsTrumpOther() {
        CustList<Object[]> args_ = new CustList<Object[]>();
        for (Suit t: Suit.couleursOrdinaires()) {
            for (Suit d: Suit.couleursOrdinaires()) {
                if (t == d) {
                    continue;
                }
                for (CardBelote tCard_: CardBelote.values()) {
                    if (tCard_.couleur() != d) {
                        continue;
                    }
                    for (CardBelote tCardTwo_: CardBelote.values()) {
                        if (tCardTwo_.couleur() != d) {
                            continue;
                        }
                        if (tCard_ == tCardTwo_) {
                            continue;
                        }
                        args_.add(new Object[]{d,t,tCard_, tCardTwo_});
                    }
                }
            }
        }
        return args_.toArray();
    }

    @Test
    public void strength3Test(Suit _couleurDemandee,Suit _couleurAtout, CardBelote _trumpCard, CardBelote _trumpCardTwo) {
        byte force_ = _trumpCard.strength(_couleurAtout, _couleurDemandee);
        byte forceTwo_ = _trumpCardTwo.strength(_couleurAtout, _couleurDemandee);
        assertNotEquals(force_, forceTwo_);
        assertTrue(force_ > 0);
    }

    Object[] cardsSuitsTrumpZeroCards() {
        CustList<Object[]> args_ = new CustList<Object[]>();
        for (Suit t: Suit.couleursOrdinaires()) {
            for (Suit d: Suit.couleursOrdinaires()) {
                if (t == d) {
                    continue;
                }
                for (CardBelote card_: CardBelote.values()) {
                    if (card_ == CardBelote.WHITE) {
                        continue;
                    }
                    if (card_.couleur() == d) {
                        continue;
                    }
                    if (card_.couleur() == t) {
                        continue;
                    }
                    args_.add(new Object[]{d,t,card_});
                }
            }
        }
        return args_.toArray();
    }

    @Test
    public void strength4Test(Suit _couleurDemandee,Suit _couleurAtout, CardBelote _card) {
        byte force_ = _card.strength(_couleurAtout, _couleurDemandee);
        assertEq(0, force_);
    }

    Object[] cardsSuitsTrumpCards() {
        CustList<Object[]> args_ = new CustList<Object[]>();
        for (Suit t: Suit.couleursOrdinaires()) {
            for (Suit d: Suit.couleursOrdinaires()) {
                for (CardBelote card_: CardBelote.values()) {
                    if (card_ == CardBelote.WHITE) {
                        continue;
                    }
                    args_.add(new Object[]{d,t,card_});
                }
            }
        }
        return args_.toArray();
    }

    @Test
    public void strength5Test(Suit _couleurDemandee,Suit _couleurAtout, CardBelote _card) {
        BidBeloteSuit enchereCouleur_ = new BidBeloteSuit();
        enchereCouleur_.setEnchere(BidBelote.SUIT);
        enchereCouleur_.setCouleur(_couleurAtout);
        byte force_ = _card.strength(_couleurAtout, _couleurDemandee);
        assertEq(force_, _card.strength(_couleurDemandee, enchereCouleur_));
    }

    @Test
    public void strength6Test(Suit _couleurDemandee,Suit _couleurAtout, CardBelote _card) {
        BidBeloteSuit enchereCouleur_ = new BidBeloteSuit();
        enchereCouleur_.setEnchere(BidBelote.OTHER_SUIT);
        enchereCouleur_.setCouleur(_couleurAtout);
        byte force_ = _card.strength(_couleurAtout, _couleurDemandee);
        assertEq(force_, _card.strength(_couleurDemandee, enchereCouleur_));
    }

    Object[] cardsSuitsTrumpTrick() {
        CustList<Object[]> args_ = new CustList<Object[]>();
        for (Suit t: Suit.couleursOrdinaires()) {
            for (CardBelote tCard_: CardBelote.values()) {
                if (tCard_.couleur() != t) {
                    continue;
                }
                for (CardBelote tCardTwo_: CardBelote.values()) {
                    if (tCardTwo_.couleur() != t) {
                        continue;
                    }
                    if (tCard_ == tCardTwo_) {
                        continue;
                    }
                    args_.add(new Object[]{t,tCard_, tCardTwo_});
                }
            }
        }
        return args_.toArray();
    }

    @Test
    public void strength7Test(Suit _couleurAtout, CardBelote _trumpCard, CardBelote _trumpCardTwo) {
        byte force_ = _trumpCard.strength(_couleurAtout, _couleurAtout);
        byte forceTwo_ = _trumpCardTwo.strength(_couleurAtout, _couleurAtout);
        assertNotEquals(force_, forceTwo_);
        assertTrue(force_ > 0);
    }

    Object[] cardsSuitsTrumpTrickZeroCards() {
        CustList<Object[]> args_ = new CustList<Object[]>();
        for (Suit t: Suit.couleursOrdinaires()) {
            for (CardBelote tCard_: CardBelote.values()) {
                if (tCard_ == CardBelote.WHITE) {
                    continue;
                }
                if (tCard_.couleur() == t) {
                    continue;
                }
                args_.add(new Object[]{t,tCard_});
            }
        }
        return args_.toArray();
    }

    @Test
    public void strength8Test(Suit _couleurAtout, CardBelote _card) {
        byte force_ = _card.strength(_couleurAtout, _couleurAtout);
        assertEq(0, force_);
    }

    @Test
    public void strength9Test(Suit _couleurAtout, CardBelote _trumpCard, CardBelote _trumpCardTwo) {
        BidBeloteSuit enchereCouleur_ = new BidBeloteSuit();
        enchereCouleur_.setEnchere(BidBelote.ALL_TRUMP);
        byte force_ = _trumpCard.strength(_couleurAtout, enchereCouleur_);
        byte forceTwo_ = _trumpCardTwo.strength(_couleurAtout, enchereCouleur_);
        assertNotEquals(force_, forceTwo_);
        assertTrue(force_ > 0);
    }

    @Test
    public void strength10Test(Suit _couleurAtout, CardBelote _card) {
        BidBeloteSuit enchereCouleur_ = new BidBeloteSuit();
        enchereCouleur_.setEnchere(BidBelote.ALL_TRUMP);
        byte force_ = _card.strength(_couleurAtout, enchereCouleur_);
        assertEq(0, force_);
    }

    @Test
    public void strength11Test(Suit _couleurAtout, CardBelote _trumpCard, CardBelote _trumpCardTwo) {
        BidBeloteSuit enchereCouleur_ = new BidBeloteSuit();
        enchereCouleur_.setEnchere(BidBelote.NO_TRUMP);
        byte force_ = _trumpCard.strength(_couleurAtout, enchereCouleur_);
        byte forceTwo_ = _trumpCardTwo.strength(_couleurAtout, enchereCouleur_);
        assertNotEquals(force_, forceTwo_);
        assertTrue(force_ > 0);
    }

    @Test
    public void strength12Test(Suit _couleurAtout, CardBelote _card) {
        BidBeloteSuit enchereCouleur_ = new BidBeloteSuit();
        enchereCouleur_.setEnchere(BidBelote.NO_TRUMP);
        byte force_ = _card.strength(_couleurAtout, enchereCouleur_);
        assertEq(0, force_);
    }    @Test
    public void points_CouleurAtout1(){
        Suit couleurAtout_ = Suit.SPADE;
        BidBeloteSuit enchereCouleur_ = new BidBeloteSuit();
        enchereCouleur_.setEnchere(BidBelote.SUIT);
        enchereCouleur_.setCouleur(couleurAtout_);

        assertEq(11,CardBelote.HEART_1.points(enchereCouleur_));
        assertEq(10,CardBelote.HEART_10.points(enchereCouleur_));
        assertEq(4,CardBelote.HEART_KING.points(enchereCouleur_));
        assertEq(3,CardBelote.HEART_QUEEN.points(enchereCouleur_));
        assertEq(2,CardBelote.HEART_JACK.points(enchereCouleur_));
        assertEq(0,CardBelote.HEART_9.points(enchereCouleur_));
        assertEq(0,CardBelote.HEART_8.points(enchereCouleur_));
        assertEq(0,CardBelote.HEART_7.points(enchereCouleur_));

        assertEq(20,CardBelote.SPADE_JACK.points(enchereCouleur_));
        assertEq(14,CardBelote.SPADE_9.points(enchereCouleur_));
        assertEq(11,CardBelote.SPADE_1.points(enchereCouleur_));
        assertEq(10,CardBelote.SPADE_10.points(enchereCouleur_));
        assertEq(4,CardBelote.SPADE_KING.points(enchereCouleur_));
        assertEq(3,CardBelote.SPADE_QUEEN.points(enchereCouleur_));
        assertEq(0,CardBelote.SPADE_8.points(enchereCouleur_));
        assertEq(0,CardBelote.SPADE_7.points(enchereCouleur_));

        assertEq(11,CardBelote.DIAMOND_1.points(enchereCouleur_));
        assertEq(10,CardBelote.DIAMOND_10.points(enchereCouleur_));
        assertEq(4,CardBelote.DIAMOND_KING.points(enchereCouleur_));
        assertEq(3,CardBelote.DIAMOND_QUEEN.points(enchereCouleur_));
        assertEq(2,CardBelote.DIAMOND_JACK.points(enchereCouleur_));
        assertEq(0,CardBelote.DIAMOND_9.points(enchereCouleur_));
        assertEq(0,CardBelote.DIAMOND_8.points(enchereCouleur_));
        assertEq(0,CardBelote.DIAMOND_7.points(enchereCouleur_));

        assertEq(11,CardBelote.CLUB_1.points(enchereCouleur_));
        assertEq(10,CardBelote.CLUB_10.points(enchereCouleur_));
        assertEq(4,CardBelote.CLUB_KING.points(enchereCouleur_));
        assertEq(3,CardBelote.CLUB_QUEEN.points(enchereCouleur_));
        assertEq(2,CardBelote.CLUB_JACK.points(enchereCouleur_));
        assertEq(0,CardBelote.CLUB_9.points(enchereCouleur_));
        assertEq(0,CardBelote.CLUB_8.points(enchereCouleur_));
        assertEq(0,CardBelote.CLUB_7.points(enchereCouleur_));

        enchereCouleur_.setEnchere(BidBelote.OTHER_SUIT);

        assertEq(11,CardBelote.HEART_1.points(enchereCouleur_));
        assertEq(10,CardBelote.HEART_10.points(enchereCouleur_));
        assertEq(4,CardBelote.HEART_KING.points(enchereCouleur_));
        assertEq(3,CardBelote.HEART_QUEEN.points(enchereCouleur_));
        assertEq(2,CardBelote.HEART_JACK.points(enchereCouleur_));
        assertEq(0,CardBelote.HEART_9.points(enchereCouleur_));
        assertEq(0,CardBelote.HEART_8.points(enchereCouleur_));
        assertEq(0,CardBelote.HEART_7.points(enchereCouleur_));

        assertEq(20,CardBelote.SPADE_JACK.points(enchereCouleur_));
        assertEq(14,CardBelote.SPADE_9.points(enchereCouleur_));
        assertEq(11,CardBelote.SPADE_1.points(enchereCouleur_));
        assertEq(10,CardBelote.SPADE_10.points(enchereCouleur_));
        assertEq(4,CardBelote.SPADE_KING.points(enchereCouleur_));
        assertEq(3,CardBelote.SPADE_QUEEN.points(enchereCouleur_));
        assertEq(0,CardBelote.SPADE_8.points(enchereCouleur_));
        assertEq(0,CardBelote.SPADE_7.points(enchereCouleur_));

        assertEq(11,CardBelote.DIAMOND_1.points(enchereCouleur_));
        assertEq(10,CardBelote.DIAMOND_10.points(enchereCouleur_));
        assertEq(4,CardBelote.DIAMOND_KING.points(enchereCouleur_));
        assertEq(3,CardBelote.DIAMOND_QUEEN.points(enchereCouleur_));
        assertEq(2,CardBelote.DIAMOND_JACK.points(enchereCouleur_));
        assertEq(0,CardBelote.DIAMOND_8.points(enchereCouleur_));
        assertEq(0,CardBelote.DIAMOND_7.points(enchereCouleur_));

        assertEq(11,CardBelote.CLUB_1.points(enchereCouleur_));
        assertEq(10,CardBelote.CLUB_10.points(enchereCouleur_));
        assertEq(4,CardBelote.CLUB_KING.points(enchereCouleur_));
        assertEq(3,CardBelote.CLUB_QUEEN.points(enchereCouleur_));
        assertEq(2,CardBelote.CLUB_JACK.points(enchereCouleur_));
        assertEq(0,CardBelote.CLUB_9.points(enchereCouleur_));
        assertEq(0,CardBelote.CLUB_8.points(enchereCouleur_));
        assertEq(0,CardBelote.CLUB_7.points(enchereCouleur_));

    }
    @Test
    public void points_SansAtout1(){
        Suit couleurAtout_ = Suit.UNDEFINED;

        BidBeloteSuit enchereCouleur_ = new BidBeloteSuit();
        enchereCouleur_.setEnchere(BidBelote.NO_TRUMP);
        enchereCouleur_.setCouleur(couleurAtout_);

        assertEq(19,CardBelote.HEART_1.points(enchereCouleur_));
        assertEq(10,CardBelote.HEART_10.points(enchereCouleur_));
        assertEq(4,CardBelote.HEART_KING.points(enchereCouleur_));
        assertEq(3,CardBelote.HEART_QUEEN.points(enchereCouleur_));
        assertEq(2,CardBelote.HEART_JACK.points(enchereCouleur_));
        assertEq(0,CardBelote.HEART_9.points(enchereCouleur_));
        assertEq(0,CardBelote.HEART_8.points(enchereCouleur_));
        assertEq(0,CardBelote.HEART_7.points(enchereCouleur_));

        assertEq(19,CardBelote.SPADE_1.points(enchereCouleur_));
        assertEq(10,CardBelote.SPADE_10.points(enchereCouleur_));
        assertEq(4,CardBelote.SPADE_KING.points(enchereCouleur_));
        assertEq(3,CardBelote.SPADE_QUEEN.points(enchereCouleur_));
        assertEq(2,CardBelote.SPADE_JACK.points(enchereCouleur_));
        assertEq(0,CardBelote.SPADE_9.points(enchereCouleur_));
        assertEq(0,CardBelote.SPADE_8.points(enchereCouleur_));
        assertEq(0,CardBelote.SPADE_7.points(enchereCouleur_));

        assertEq(19,CardBelote.DIAMOND_1.points(enchereCouleur_));
        assertEq(10,CardBelote.DIAMOND_10.points(enchereCouleur_));
        assertEq(4,CardBelote.DIAMOND_KING.points(enchereCouleur_));
        assertEq(3,CardBelote.DIAMOND_QUEEN.points(enchereCouleur_));
        assertEq(2,CardBelote.DIAMOND_JACK.points(enchereCouleur_));
        assertEq(0,CardBelote.DIAMOND_9.points(enchereCouleur_));
        assertEq(0,CardBelote.DIAMOND_8.points(enchereCouleur_));
        assertEq(0,CardBelote.DIAMOND_7.points(enchereCouleur_));

        assertEq(19,CardBelote.CLUB_1.points(enchereCouleur_));
        assertEq(10,CardBelote.CLUB_10.points(enchereCouleur_));
        assertEq(4,CardBelote.CLUB_KING.points(enchereCouleur_));
        assertEq(3,CardBelote.CLUB_QUEEN.points(enchereCouleur_));
        assertEq(2,CardBelote.CLUB_JACK.points(enchereCouleur_));
        assertEq(0,CardBelote.CLUB_9.points(enchereCouleur_));
        assertEq(0,CardBelote.CLUB_8.points(enchereCouleur_));
        assertEq(0,CardBelote.CLUB_7.points(enchereCouleur_));

    }
    @Test
    public void points_ToutAtout1(){
        Suit couleurAtout_ = Suit.UNDEFINED;

        BidBeloteSuit enchereCouleur_ = new BidBeloteSuit();
        enchereCouleur_.setEnchere(BidBelote.ALL_TRUMP);
        enchereCouleur_.setCouleur(couleurAtout_);

        assertEq(14,CardBelote.HEART_JACK.points(enchereCouleur_));
        assertEq(9,CardBelote.HEART_9.points(enchereCouleur_));
        assertEq(6,CardBelote.HEART_1.points(enchereCouleur_));
        assertEq(4,CardBelote.HEART_10.points(enchereCouleur_));
        assertEq(3,CardBelote.HEART_KING.points(enchereCouleur_));
        assertEq(2,CardBelote.HEART_QUEEN.points(enchereCouleur_));
        assertEq(0,CardBelote.HEART_8.points(enchereCouleur_));
        assertEq(0,CardBelote.HEART_7.points(enchereCouleur_));

        assertEq(14,CardBelote.SPADE_JACK.points(enchereCouleur_));
        assertEq(9,CardBelote.SPADE_9.points(enchereCouleur_));
        assertEq(6,CardBelote.SPADE_1.points(enchereCouleur_));
        assertEq(4,CardBelote.SPADE_10.points(enchereCouleur_));
        assertEq(3,CardBelote.SPADE_KING.points(enchereCouleur_));
        assertEq(2,CardBelote.SPADE_QUEEN.points(enchereCouleur_));
        assertEq(0,CardBelote.SPADE_8.points(enchereCouleur_));
        assertEq(0,CardBelote.SPADE_7.points(enchereCouleur_));

        assertEq(14,CardBelote.DIAMOND_JACK.points(enchereCouleur_));
        assertEq(9,CardBelote.DIAMOND_9.points(enchereCouleur_));
        assertEq(6,CardBelote.DIAMOND_1.points(enchereCouleur_));
        assertEq(4,CardBelote.DIAMOND_10.points(enchereCouleur_));
        assertEq(3,CardBelote.DIAMOND_KING.points(enchereCouleur_));
        assertEq(2,CardBelote.DIAMOND_QUEEN.points(enchereCouleur_));
        assertEq(0,CardBelote.DIAMOND_8.points(enchereCouleur_));
        assertEq(0,CardBelote.DIAMOND_7.points(enchereCouleur_));

        assertEq(14,CardBelote.CLUB_JACK.points(enchereCouleur_));
        assertEq(9,CardBelote.CLUB_9.points(enchereCouleur_));
        assertEq(6,CardBelote.CLUB_1.points(enchereCouleur_));
        assertEq(4,CardBelote.CLUB_10.points(enchereCouleur_));
        assertEq(3,CardBelote.CLUB_KING.points(enchereCouleur_));
        assertEq(2,CardBelote.CLUB_QUEEN.points(enchereCouleur_));
        assertEq(0,CardBelote.CLUB_8.points(enchereCouleur_));
        assertEq(0,CardBelote.CLUB_7.points(enchereCouleur_));

    }
    @Test
    public void strength_CouleurDemandeCouleurAtoutIndeterminee1(){
        Suit couleurAtout_ = Suit.UNDEFINED;
        Suit couleurDemandee_ = Suit.HEART;
        assertEq(CardBelote.HEART_1.strength(Suit.SPADE, couleurDemandee_),CardBelote.HEART_1.strength(couleurAtout_, couleurDemandee_));
        assertEq(CardBelote.HEART_10.strength(Suit.SPADE, couleurDemandee_),CardBelote.HEART_10.strength(couleurAtout_, couleurDemandee_));
        assertEq(CardBelote.HEART_KING.strength(Suit.SPADE, couleurDemandee_),CardBelote.HEART_KING.strength(couleurAtout_, couleurDemandee_));
        assertEq(CardBelote.HEART_QUEEN.strength(Suit.SPADE, couleurDemandee_),CardBelote.HEART_QUEEN.strength(couleurAtout_, couleurDemandee_));
        assertEq(CardBelote.HEART_JACK.strength(Suit.SPADE, couleurDemandee_),CardBelote.HEART_JACK.strength(couleurAtout_, couleurDemandee_));
        assertEq(CardBelote.HEART_9.strength(Suit.SPADE, couleurDemandee_),CardBelote.HEART_9.strength(couleurAtout_, couleurDemandee_));
        assertEq(CardBelote.HEART_8.strength(Suit.SPADE, couleurDemandee_),CardBelote.HEART_8.strength(couleurAtout_, couleurDemandee_));
        assertEq(CardBelote.HEART_7.strength(Suit.SPADE, couleurDemandee_),CardBelote.HEART_7.strength(couleurAtout_, couleurDemandee_));
    }
}