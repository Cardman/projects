package cards.tarot.comparators;
import cards.consts.Suit;
import cards.tarot.HandTarot;
import cards.tarot.enumerations.CardTarot;
import code.util.IdMap;
import code.util.core.IndexConstants;
import code.util.core.NumberUtil;
import code.util.ints.Comparing;

public final class CalledSuitComparator implements Comparing<CardTarot> {

    private final IdMap<Suit,HandTarot> dealingCalledCards;
    private final IdMap<Suit,HandTarot> dealingTakerHand;

    public CalledSuitComparator(HandTarot _cartesAppeler,
                                HandTarot _mainPreneur) {
        dealingCalledCards = _cartesAppeler.couleurs();
        dealingTakerHand = _mainPreneur.couleurs();
    }
    @Override
    public int compare(CardTarot _arg0, CardTarot _arg1) {
        Suit sOne_ = _arg0.getId().getCouleur();
        Suit sTwo_ = _arg1.getId().getCouleur();
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
        if (couleur(sOne_).estVide() && couleur(sTwo_).estVide()) {
            return 0;
        }
        if(couleur(sOne_).estVide()) {
            return 1;
        }
        if(couleur(sTwo_).estVide()) {
            return -1;
        }
        return notEmpty(sOne_, sTwo_);

    }

    private int notEmpty(Suit _sOne, Suit _sTwo) {
        if (couleur(Suit.TRUMP).total() >= 8) {
            if (couleur(_sOne).total() > couleur(_sTwo).total()) {
                return -1;
            }
            if (couleur(_sOne).total() < couleur(_sTwo).total()) {
                return 1;
            }
            return 0;
        }
        if (couleur(_sOne).total() > 4) {
            if (couleur(_sTwo).total() < 4) {
                return 1;
            }
            if (couleur(_sTwo).total() > couleur(_sOne).total()) {
                return -1;
            }
            return 1;
        }
        if (couleur(_sTwo).total() > 4) {
            return -1;
        }
        return end(_sOne, _sTwo);
    }

    private int end(Suit _sOne, Suit _sTwo) {
        CardTarot carteAppelee0_ = dealingCalledCards.getVal(_sOne).premiereCarte();
        CardTarot carteAppelee1_ = dealingCalledCards.getVal(_sTwo).premiereCarte();
        HandTarot cartesPossedesNonAppelees0_ = getCharCards(_sOne, carteAppelee0_);
        HandTarot cartesPossedesNonAppelees1_ = getCharCards(_sTwo, carteAppelee1_);
        HandTarot figures0_ = cartesPossedesNonAppelees0_.charCardsBySuit(_sOne);
        HandTarot figures1_ = cartesPossedesNonAppelees1_.charCardsBySuit(_sTwo);
        int min_ = Math.min(figures0_.total(), figures1_.total());
        int eq_ = 0;
        for (int i = IndexConstants.FIRST_INDEX; i < min_; i++) {
            int tmp_ = -NumberUtil.compareLg(figures0_.carte(i).points(), figures1_.carte(i).points());
            if (tmp_ != 0) {
                eq_ = tmp_;
                break;
            }
        }
        if (eq_ != 0) {
            return eq_;
        }
        if (figures0_.total() > figures1_.total()) {
            return -1;
        }
        if (figures0_.total() < figures1_.total()) {
            return 1;
        }
        if (couleur(_sOne).total() < couleur(_sTwo).total()) {
            return -1;
        }
        if (couleur(_sOne).total() > couleur(_sTwo).total()) {
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
            if (!c.isCharacter() || c == _carteAppelee || !couleur(_suit).contient(c)) {
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
        Suit s_ = _arg0.getId().getCouleur();
        return couleur(s_).contient(_arg0);
    }

}
