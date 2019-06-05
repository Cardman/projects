package cards.tarot.comparators;
import cards.consts.Suit;
import cards.tarot.HandTarot;
import cards.tarot.enumerations.CardTarot;
import code.util.CustList;
import code.util.EnumMap;
import code.util.ints.Comparing;

public final class CalledSuitComparator implements Comparing<CardTarot> {

    private EnumMap<Suit,HandTarot> dealingCalledCards;
    private EnumMap<Suit,HandTarot> dealingTakerHand;

    public CalledSuitComparator(HandTarot _cartesAppeler,
                                HandTarot _mainPreneur) {
        dealingCalledCards = _cartesAppeler.couleurs();
        dealingTakerHand = _mainPreneur.couleurs();
    }
    @Override
    public int compare(CardTarot _arg0, CardTarot _arg1) {
        Suit sOne_ = _arg0.couleur();
        Suit sTwo_ = _arg1.couleur();
        if(appelImpossible(_arg0)) {
            if(appelImpossible(_arg1)) {
                return 0;
            }
            return 1;
        }
        if(appelImpossible(_arg1)) {
            return -1;
        }
        int cmp_ = cmpLoc(sOne_, sTwo_, Suit.TRUMP);
        if (cmp_ != 0) {
            return cmp_;
        }
        cmp_ = cmpLoc(sOne_, sTwo_, Suit.UNDEFINED);
        if (cmp_ != 0) {
            return cmp_;
        }
        if (_arg0.strength(sOne_) > _arg1.strength(sTwo_)) {
            return -1;
        }
        if (_arg0.strength(sOne_) < _arg1.strength(sTwo_)) {
            return 1;
        }
        if(couleur(sOne_).estVide()) {
            if(couleur(sTwo_).estVide()) {
                return 0;
            }
        }
        if(couleur(sOne_).estVide()) {
            return 1;
        }
        if(couleur(sTwo_).estVide()) {
            return -1;
        }
        if(couleur(Suit.TRUMP).total() < 8) {
            if(couleur(sOne_).total() > 4) {
                if(couleur(sTwo_).total() < 4) {
                    return 1;
                }
                if(couleur(sTwo_).total() > couleur(sOne_).total()) {
                    return -1;
                }
                return 1;
            }
            if(couleur(sTwo_).total() > 4) {
                return -1;
            }
            CardTarot carteAppelee0_ = dealingCalledCards.getVal(sOne_).premiereCarte();
            CardTarot carteAppelee1_ = dealingCalledCards.getVal(sTwo_).premiereCarte();
            HandTarot cartesPossedesNonAppelees0_ = getCharCards(sOne_, carteAppelee0_);
            HandTarot cartesPossedesNonAppelees1_ = getCharCards(sTwo_, carteAppelee1_);
            HandTarot figures0_ = cartesPossedesNonAppelees0_.charCardsBySuit(sOne_);
            HandTarot figures1_ = cartesPossedesNonAppelees1_.charCardsBySuit(sTwo_);
            int min_ = Math.min(figures0_.total(), figures1_.total());
            boolean id_ = true;
            boolean plusGrand_ = false;
            for (int i = CustList.FIRST_INDEX;i<min_;i++) {
                if(figures0_.carte(i).points() > figures1_.carte(i).points()) {
                    plusGrand_ = true;
                    id_ = false;
                    break;
                }
                if(figures0_.carte(i).points() < figures1_.carte(i).points()) {
                    id_ = false;
                    break;
                }
            }
            if(!id_) {
                if(plusGrand_) {
                    return -1;
                }
                return 1;
            }
            if(figures0_.total() > figures1_.total()) {
                return -1;
            }
            if(figures0_.total() < figures1_.total()) {
                return 1;
            }
            if(couleur(sOne_).total() < couleur(sTwo_).total()) {
                return -1;
            }
            if(couleur(sOne_).total() > couleur(sTwo_).total()) {
                return 1;
            }
            return 0;
        }
        if(couleur(sOne_).total() > couleur(sTwo_).total()) {
            return -1;
        }
        if(couleur(sOne_).total() < couleur(sTwo_).total()) {
            return 1;
        }
        return 0;

    }

    private static int cmpLoc(Suit _one, Suit _two, Suit _crit) {
        if (_one == _two) {
            return 0;
        }
        if(_one == _crit) {
            return -1;
        }
        if(_two == _crit) {
            return 1;
        }
        return 0;
    }
    private HandTarot getCharCards(Suit _suit, CardTarot _carteAppelee) {
        HandTarot cartesPossedesNonAppelees_ = new HandTarot();
        for(CardTarot c:HandTarot.couleurComplete(_suit)) {
            if(!c.isCharacter()) {
                continue;
            }
            if(c == _carteAppelee) {
                continue;
            }
            if(!couleur(_suit).contient(c)) {
                continue;
            }
            cartesPossedesNonAppelees_.ajouter(c);
        }
        return cartesPossedesNonAppelees_;
    }

    /**
    @param _arg0
    @return
    */
    private HandTarot couleur(Suit _arg0) {
        return dealingTakerHand.getVal(_arg0);
    }
    /**
    @param _arg0
    @return
    */
    private boolean appelImpossible(CardTarot _arg0) {
        Suit s_ = _arg0.couleur();
        return couleur(s_).contient(_arg0);
    }

}
