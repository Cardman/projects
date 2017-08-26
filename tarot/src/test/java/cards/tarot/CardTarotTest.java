package cards.tarot;
import static cards.tarot.EquallableTarotUtil.assertEq;
import static code.util.opers.EquallableUtil.assertEq;
import static junitparams.JUnitParamsRunner.$;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import code.util.CustList;
import cards.consts.Suit;
import cards.tarot.enumerations.CardTarot;

@SuppressWarnings("static-method")
@RunWith(JUnitParamsRunner.class)
@Ignore
public class CardTarotTest {

    Object[] figures() {
        return $($(CardTarot.HEART_JACK), $(CardTarot.HEART_KNIGHT), $(CardTarot.HEART_KING), $(CardTarot.HEART_QUEEN),
                $(CardTarot.SPADE_JACK), $(CardTarot.SPADE_KNIGHT), $(CardTarot.SPADE_KING), $(CardTarot.SPADE_QUEEN),
                $(CardTarot.DIAMOND_JACK), $(CardTarot.DIAMOND_KNIGHT), $(CardTarot.DIAMOND_KING), $(CardTarot.DIAMOND_QUEEN),
                $(CardTarot.CLUB_JACK), $(CardTarot.CLUB_KNIGHT), $(CardTarot.CLUB_KING), $(CardTarot.CLUB_QUEEN));
    }

    Object[] valeurs() {
        return $($(CardTarot.HEART_1), $(CardTarot.HEART_10), $(CardTarot.HEART_9),
                $(CardTarot.HEART_8), $(CardTarot.HEART_7), $(CardTarot.HEART_6),
                $(CardTarot.HEART_5), $(CardTarot.HEART_4), $(CardTarot.HEART_3),
                $(CardTarot.HEART_2),
                $(CardTarot.SPADE_1), $(CardTarot.SPADE_10), $(CardTarot.SPADE_9),
                $(CardTarot.SPADE_8), $(CardTarot.SPADE_7), $(CardTarot.SPADE_6),
                $(CardTarot.SPADE_5), $(CardTarot.SPADE_4), $(CardTarot.SPADE_3),
                $(CardTarot.SPADE_2),
                $(CardTarot.DIAMOND_1), $(CardTarot.DIAMOND_10), $(CardTarot.DIAMOND_9),
                $(CardTarot.DIAMOND_8), $(CardTarot.DIAMOND_7), $(CardTarot.DIAMOND_6),
                $(CardTarot.DIAMOND_5), $(CardTarot.DIAMOND_4), $(CardTarot.DIAMOND_3),
                $(CardTarot.DIAMOND_2),
                $(CardTarot.CLUB_1), $(CardTarot.CLUB_10), $(CardTarot.CLUB_9),
                $(CardTarot.CLUB_8), $(CardTarot.CLUB_7), $(CardTarot.CLUB_6),
                $(CardTarot.CLUB_5), $(CardTarot.CLUB_4), $(CardTarot.CLUB_3),
                $(CardTarot.CLUB_2));
    }

    Object[] atouts(){
        return $($(CardTarot.TRUMP_21),$(CardTarot.TRUMP_20),$(
                CardTarot.TRUMP_19),$(CardTarot.TRUMP_18),$(
                CardTarot.TRUMP_17),$(CardTarot.TRUMP_16),$(
                CardTarot.TRUMP_15),$(CardTarot.TRUMP_14),$(
                CardTarot.TRUMP_13),$(CardTarot.TRUMP_12),$(
                CardTarot.TRUMP_11),$(CardTarot.TRUMP_10),$(
                CardTarot.TRUMP_9),$(CardTarot.TRUMP_8),$(
                CardTarot.TRUMP_7),$(CardTarot.TRUMP_6),$(
                CardTarot.TRUMP_5),$(CardTarot.TRUMP_4),$(
                CardTarot.TRUMP_3),$(CardTarot.TRUMP_2),$(
                CardTarot.TRUMP_1));
    }
    Object[] couleursOrdinaires() {
        return $($(Suit.HEART),$(Suit.SPADE),$(Suit.DIAMOND),$(Suit.CLUB));
    }
    @Test
    public void isPlayable1Test(){
        assertTrue(!CardTarot.WHITE.isPlayable());
        assertTrue(!CardTarot.WHITE.isCharacter());
        assertEq(0, CardTarot.WHITE.valeur());
        assertEq(Suit.UNDEFINED, CardTarot.WHITE.couleur());
        assertTrue(!CardTarot.EXCUSE.isCharacter());
        assertEq(Suit.UNDEFINED,CardTarot.EXCUSE.couleur());
        assertEq(0,CardTarot.EXCUSE.valeur());
    }

    @Parameters(method="atouts")
    @Test
    public void isPlayable2Test(CardTarot _card){
        assertTrue(_card.isPlayable());
    }

    @Parameters(method="valeurs")
    @Test
    public void isPlayable3Test(CardTarot _card){
        assertTrue(_card.isPlayable());
    }

    @Parameters(method="figures")
    @Test
    public void isPlayable4Test(CardTarot _card){
        assertTrue(_card.isPlayable());
    }

    @Test
    @Parameters(method="valeurs")
    public void isCharacter_false1Test(CardTarot _carte){
        assertTrue(!_carte.isCharacter());
        assertTrue(_carte.valeur()>0);
        assertNotEquals(Suit.TRUMP, _carte.couleur());
        assertNotEquals(Suit.UNDEFINED, _carte.couleur());
    }
    @Test
    @Parameters(method="figures")
    public void isCharacter_true2Test(CardTarot _carte){
        assertTrue(_carte.isCharacter());
        assertEq(0, _carte.valeur());
        assertNotEquals(Suit.TRUMP, _carte.couleur());
        assertNotEquals(Suit.UNDEFINED, _carte.couleur());
    }
    @Test
    @Parameters(method="atouts")
    public void isCharacter_trump3Test(CardTarot _carte){
        assertTrue(!_carte.isCharacter());
        assertTrue(_carte.valeur()>0);
        assertEq(Suit.TRUMP, _carte.couleur());
        assertNotEquals(Suit.UNDEFINED, _carte.couleur());
    }

    Object[] cardsSuits() {
        CustList<Object[]> args_ = new CustList<Object[]>();
        for (Suit d: Suit.couleursOrdinaires()) {
            for (CardTarot tCard_: CardTarot.values()) {
                if (tCard_.couleur() != Suit.TRUMP) {
                    continue;
                }
                for (CardTarot dCard_: CardTarot.values()) {
                    if (dCard_.couleur() != d) {
                        continue;
                    }
                    args_.add(new Object[]{d,dCard_,tCard_});
                }
            }
        }
        return args_.toArray();
    }

    @Test
    @Parameters(method="cardsSuits")
    public void strength1Test(Suit _couleurDemandee,CardTarot _demCards, CardTarot _trumpCard) {
        byte force_ = _trumpCard.strength(_couleurDemandee);
        byte forceTwo_ = _demCards.strength(_couleurDemandee);
        assertTrue(force_>forceTwo_);
    }

    Object[] cardsSuitsTrump() {
        CustList<Object[]> args_ = new CustList<Object[]>();
        for (Suit d: Suit.couleursOrdinaires()) {
            for (CardTarot tCard_: CardTarot.values()) {
                if (tCard_.couleur() != Suit.TRUMP) {
                    continue;
                }
                for (CardTarot dCard_: CardTarot.values()) {
                    if (dCard_.couleur() != Suit.TRUMP) {
                        continue;
                    }
                    if (tCard_ == dCard_) {
                        continue;
                    }
                    args_.add(new Object[]{d,dCard_,tCard_});
                }
            }
        }
        for (CardTarot tCard_: CardTarot.values()) {
            if (tCard_.couleur() != Suit.TRUMP) {
                continue;
            }
            for (CardTarot dCard_: CardTarot.values()) {
                if (dCard_.couleur() != Suit.TRUMP) {
                    continue;
                }
                if (tCard_ == dCard_) {
                    continue;
                }
                args_.add(new Object[]{Suit.TRUMP,dCard_,tCard_});
            }
        }
        return args_.toArray();
    }

    @Test
    @Parameters(method="cardsSuitsTrump")
    public void strength2Test(Suit _couleurDemandee, CardTarot _trumpCard, CardTarot _trumpCardTwo) {
        byte force_ = _trumpCard.strength(_couleurDemandee);
        byte forceTwo_ = _trumpCardTwo.strength(_couleurDemandee);
        assertNotEquals(force_, forceTwo_);
        assertTrue(force_ > 0);
    }

    Object[] cardsSuitsTrumpOther() {
        CustList<Object[]> args_ = new CustList<Object[]>();
        for (Suit d: Suit.couleursOrdinaires()) {
            for (CardTarot tCard_: CardTarot.values()) {
                if (tCard_.couleur() != d) {
                    continue;
                }
                for (CardTarot dCard_: CardTarot.values()) {
                    if (dCard_.couleur() != d) {
                        continue;
                    }
                    if (tCard_ == dCard_) {
                        continue;
                    }
                    args_.add(new Object[]{d,dCard_,tCard_});
                }
            }
        }
        for (CardTarot tCard_: CardTarot.values()) {
            if (tCard_.couleur() != Suit.TRUMP) {
                continue;
            }
            for (CardTarot dCard_: CardTarot.values()) {
                if (dCard_.couleur() != Suit.TRUMP) {
                    continue;
                }
                if (tCard_ == dCard_) {
                    continue;
                }
                args_.add(new Object[]{Suit.TRUMP,dCard_,tCard_});
            }
        }
        return args_.toArray();
    }

    @Test
    @Parameters(method="cardsSuitsTrumpOther")
    public void strength3Test(Suit _couleurDemandee, CardTarot _trumpCard, CardTarot _trumpCardTwo) {
        byte force_ = _trumpCard.strength(_couleurDemandee);
        byte forceTwo_ = _trumpCardTwo.strength(_couleurDemandee);
        assertNotEquals(force_, forceTwo_);
        assertTrue(force_ > 0);
    }

    Object[] cardsSuitsZeros() {
        CustList<Object[]> args_ = new CustList<Object[]>();
        for (Suit d: Suit.couleursOrdinaires()) {
            for (CardTarot dCard_: CardTarot.values()) {
                if (!dCard_.isPlayable()) {
                    continue;
                }
                if (dCard_.couleur() == Suit.TRUMP) {
                    continue;
                }
                if (dCard_.couleur() == d) {
                    continue;
                }
                args_.add(new Object[]{d,dCard_});
            }
        }
        for (CardTarot dCard_: CardTarot.values()) {
            if (!dCard_.isPlayable()) {
                continue;
            }
            if (dCard_.couleur() == Suit.TRUMP) {
                continue;
            }
            args_.add(new Object[]{Suit.TRUMP,dCard_});
        }
        return args_.toArray();
    }

    @Test
    @Parameters(method="cardsSuitsZeros")
    public void strength4Test(Suit _couleurDemandee, CardTarot _card) {
        byte force_ = _card.strength(_couleurDemandee);
        assertEq(0, force_);
    }
}
