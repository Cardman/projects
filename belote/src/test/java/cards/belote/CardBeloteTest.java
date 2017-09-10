package cards.belote;
import static cards.belote.EquallableBeloteUtil.assertEq;
import static junitparams.JUnitParamsRunner.$;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import code.util.CustList;
import cards.belote.enumerations.BidBelote;
import cards.belote.enumerations.CardBelote;
import cards.consts.Suit;

@RunWith(JUnitParamsRunner.class)
@Ignore
@SuppressWarnings("static-method")
public class CardBeloteTest {

    Object[] figures() {
        return $($(CardBelote.HEART_JACK), $(CardBelote.HEART_KING), $(CardBelote.HEART_QUEEN),
                $(CardBelote.SPADE_JACK), $(CardBelote.SPADE_KING), $(CardBelote.SPADE_QUEEN),
                $(CardBelote.DIAMOND_JACK), $(CardBelote.DIAMOND_KING), $(CardBelote.DIAMOND_QUEEN),
                $(CardBelote.CLUB_JACK), $(CardBelote.CLUB_KING), $(CardBelote.CLUB_QUEEN));
    }

    Object[] valeurs() {
        return $($(CardBelote.HEART_1), $(CardBelote.HEART_10), $(CardBelote.HEART_9),
                $(CardBelote.HEART_8), $(CardBelote.HEART_7),
                $(CardBelote.SPADE_1), $(CardBelote.SPADE_10), $(CardBelote.SPADE_9),
                $(CardBelote.SPADE_8), $(CardBelote.SPADE_7),
                $(CardBelote.DIAMOND_1), $(CardBelote.DIAMOND_10), $(CardBelote.DIAMOND_9),
                $(CardBelote.DIAMOND_8), $(CardBelote.DIAMOND_7),
                $(CardBelote.CLUB_1), $(CardBelote.CLUB_10), $(CardBelote.CLUB_9),
                $(CardBelote.CLUB_8), $(CardBelote.CLUB_7));
    }

    Object[] cards() {
        return $($(CardBelote.HEART_JACK), $(CardBelote.HEART_KING), $(CardBelote.HEART_QUEEN),
                $(CardBelote.SPADE_JACK), $(CardBelote.SPADE_KING), $(CardBelote.SPADE_QUEEN),
                $(CardBelote.DIAMOND_JACK), $(CardBelote.DIAMOND_KING), $(CardBelote.DIAMOND_QUEEN),
                $(CardBelote.CLUB_JACK), $(CardBelote.CLUB_KING), $(CardBelote.CLUB_QUEEN),
                $(CardBelote.HEART_1), $(CardBelote.HEART_10), $(CardBelote.HEART_9),
                $(CardBelote.HEART_8), $(CardBelote.HEART_7),
                $(CardBelote.SPADE_1), $(CardBelote.SPADE_10), $(CardBelote.SPADE_9),
                $(CardBelote.SPADE_8), $(CardBelote.SPADE_7),
                $(CardBelote.DIAMOND_1), $(CardBelote.DIAMOND_10), $(CardBelote.DIAMOND_9),
                $(CardBelote.DIAMOND_8), $(CardBelote.DIAMOND_7),
                $(CardBelote.CLUB_1), $(CardBelote.CLUB_10), $(CardBelote.CLUB_9),
                $(CardBelote.CLUB_8), $(CardBelote.CLUB_7));
    }

    @Test
    @Parameters(method="cards")
    public void isPlayable1Test(CardBelote _card) {
        assertTrue(_card.isPlayable());
        assertNotEquals(Suit.UNDEFINED, _card.couleur());
        assertNotEquals(Suit.TRUMP, _card.couleur());
    }

    @Test
    public void isPlayable2Test(){
        assertTrue(!CardBelote.WHITE.isPlayable());
        assertTrue(!CardBelote.WHITE.isCharacter());
        assertEq(0, CardBelote.WHITE.valeur());
        assertEq(Suit.UNDEFINED, CardBelote.WHITE.couleur());
    }

    Object[] couleursDemandeesAtouts() {
        return $($(Suit.HEART,Suit.SPADE),
                $(Suit.HEART,Suit.DIAMOND),
                $(Suit.HEART,Suit.CLUB),
                $(Suit.SPADE,Suit.HEART),
                $(Suit.SPADE,Suit.DIAMOND),
                $(Suit.SPADE,Suit.CLUB),
                $(Suit.DIAMOND,Suit.HEART),
                $(Suit.DIAMOND,Suit.SPADE),
                $(Suit.DIAMOND,Suit.CLUB),
                $(Suit.CLUB,Suit.HEART),
                $(Suit.CLUB,Suit.SPADE),
                $(Suit.CLUB,Suit.DIAMOND));
    }

    Object[] couleursAtouts() {
        return $($(Suit.HEART),$(Suit.SPADE),$(Suit.DIAMOND),$(Suit.CLUB));
    }

    @Test
    @Parameters(method="valeurs")
    public void isCharacter_false1Test(CardBelote _carte){
        assertTrue(!_carte.isCharacter());
        assertTrue(_carte.valeur()>0);
    }
    @Test
    @Parameters(method="figures")
    public void isCharacter_true2Test(CardBelote _carte){
        assertTrue(_carte.isCharacter());
        assertEq(0, _carte.valeur());
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
    @Parameters(method="cardsSuits")
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
    @Parameters(method="cardsSuitsTrump")
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
    @Parameters(method="cardsSuitsTrumpOther")
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
    @Parameters(method="cardsSuitsTrumpZeroCards")
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
    @Parameters(method="cardsSuitsTrumpCards")
    public void strength5Test(Suit _couleurDemandee,Suit _couleurAtout, CardBelote _card) {
        BidBeloteSuit enchereCouleur_ = new BidBeloteSuit();
        enchereCouleur_.setEnchere(BidBelote.SUIT);
        enchereCouleur_.setCouleur(_couleurAtout);
        byte force_ = _card.strength(_couleurAtout, _couleurDemandee);
        assertEq(force_, _card.strength(_couleurDemandee, enchereCouleur_));
    }

    @Test
    @Parameters(method="cardsSuitsTrumpCards")
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
    @Parameters(method="cardsSuitsTrumpTrick")
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
    @Parameters(method="cardsSuitsTrumpTrickZeroCards")
    public void strength8Test(Suit _couleurAtout, CardBelote _card) {
        byte force_ = _card.strength(_couleurAtout, _couleurAtout);
        assertEq(0, force_);
    }

    @Test
    @Parameters(method="cardsSuitsTrumpTrick")
    public void strength9Test(Suit _couleurAtout, CardBelote _trumpCard, CardBelote _trumpCardTwo) {
        BidBeloteSuit enchereCouleur_ = new BidBeloteSuit();
        enchereCouleur_.setEnchere(BidBelote.ALL_TRUMP);
        byte force_ = _trumpCard.strength(_couleurAtout, enchereCouleur_);
        byte forceTwo_ = _trumpCardTwo.strength(_couleurAtout, enchereCouleur_);
        assertNotEquals(force_, forceTwo_);
        assertTrue(force_ > 0);
    }

    @Test
    @Parameters(method="cardsSuitsTrumpTrickZeroCards")
    public void strength10Test(Suit _couleurAtout, CardBelote _card) {
        BidBeloteSuit enchereCouleur_ = new BidBeloteSuit();
        enchereCouleur_.setEnchere(BidBelote.ALL_TRUMP);
        byte force_ = _card.strength(_couleurAtout, enchereCouleur_);
        assertEq(0, force_);
    }

    @Test
    @Parameters(method="cardsSuitsTrumpTrick")
    public void strength11Test(Suit _couleurAtout, CardBelote _trumpCard, CardBelote _trumpCardTwo) {
        BidBeloteSuit enchereCouleur_ = new BidBeloteSuit();
        enchereCouleur_.setEnchere(BidBelote.NO_TRUMP);
        byte force_ = _trumpCard.strength(_couleurAtout, enchereCouleur_);
        byte forceTwo_ = _trumpCardTwo.strength(_couleurAtout, enchereCouleur_);
        assertNotEquals(force_, forceTwo_);
        assertTrue(force_ > 0);
    }

    @Test
    @Parameters(method="cardsSuitsTrumpTrickZeroCards")
    public void strength12Test(Suit _couleurAtout, CardBelote _card) {
        BidBeloteSuit enchereCouleur_ = new BidBeloteSuit();
        enchereCouleur_.setEnchere(BidBelote.NO_TRUMP);
        byte force_ = _card.strength(_couleurAtout, enchereCouleur_);
        assertEq(0, force_);
    }

    @Test
    public void points_CouleurAtout1Test(){
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
    public void points_SansAtout2Test(){
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
    public void points_ToutAtout3Test(){
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
    public void strength_CouleurDemandeCouleurAtoutIndeterminee13Test(){
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

