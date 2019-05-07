package cards.tarot.comparators;
import cards.consts.Suit;
import cards.tarot.HandTarot;
import cards.tarot.enumerations.CardTarot;
import code.util.CustList;
import code.util.EnumMap;
import code.util.ints.Comparing;

public final class CalledSuitComparator implements Comparing<Suit> {

    private EnumMap<Suit,HandTarot> dealingCalledCards;
    private EnumMap<Suit,HandTarot> dealingTakerHand;

    public CalledSuitComparator(HandTarot _cartesAppeler,
            HandTarot _mainPreneur) {
        dealingCalledCards = _cartesAppeler.couleurs();
        dealingTakerHand = _mainPreneur.couleurs();
    }
    @Override
    public int compare(Suit _arg0, Suit _arg1) {
        if(dealingCalledCards.getVal(_arg1).estVide()) {
            if(dealingCalledCards.getVal(_arg0).estVide()) {
                return 0;
            }
            return 1;
        }
        if(dealingCalledCards.getVal(_arg0).estVide()) {
            return -1;
        }
        if(couleur(_arg0).estVide()) {
            if(couleur(_arg1).estVide()) {
                return 0;
            }
        }
        if(appelImpossible(_arg0)) {
            if(appelImpossible(_arg1)) {
                return 0;
            }
            return -1;
        }
        if(appelImpossible(_arg1)) {
            return 1;
        }
        if(couleur(_arg0).estVide()) {
            return -1;
        }
        if(couleur(_arg1).estVide()) {
            return 1;
        }
        if(_arg0 == Suit.TRUMP) {
            if(_arg1 == CardTarot.EXCUSE.couleur()) {
                if(couleur(Suit.TRUMP).contient(CardTarot.vingtEtUn())) {
                    if(couleur(CardTarot.EXCUSE.couleur()).contient(CardTarot.EXCUSE)) {
                        if(couleur(Suit.TRUMP).contient(CardTarot.petit())) {
                            return 0;
                        }
                        return 1;
                    }
                    return -1;
                }
                return 1;
            }
            if(!couleur(Suit.TRUMP).contient(CardTarot.vingtEtUn())) {
                return 1;
            }
            if(!couleur(Suit.TRUMP).contient(CardTarot.petit())) {
                return 1;
            }
            return -1;
        }
        if(_arg0 == CardTarot.EXCUSE.couleur()) {
            if(_arg1 == Suit.TRUMP) {
                if(couleur(Suit.TRUMP).contient(CardTarot.vingtEtUn())) {
                    if(couleur(CardTarot.EXCUSE.couleur()).contient(CardTarot.EXCUSE)) {
                        if(couleur(Suit.TRUMP).contient(CardTarot.petit())) {
                            return 0;
                        }
                        return -1;
                    }
                    return 1;
                }
                return -1;
            }
            if(!couleur(CardTarot.EXCUSE.couleur()).contient(CardTarot.EXCUSE)) {
                return 1;
            }
            return -1;
        }
        if(couleur(Suit.TRUMP).total() < 8) {
            if(couleur(_arg0).total() > 4) {
                if(couleur(_arg1).total() < 4) {
                    return -1;
                }
                if(couleur(_arg1).total() > couleur(_arg0).total()) {
                    return 1;
                }
                return -1;
            }
            if(couleur(_arg1).total() > 4) {
                return 1;
            }
            CardTarot carteAppelee0_ = dealingCalledCards.getVal(_arg0).premiereCarte();
            CardTarot carteAppelee1_ = dealingCalledCards.getVal(_arg1).premiereCarte();
            HandTarot cartesPossedesNonAppelees0_ = getCharCards(_arg0, carteAppelee0_);
            HandTarot cartesPossedesNonAppelees1_ = getCharCards(_arg1, carteAppelee1_);
            HandTarot figures0_ = cartesPossedesNonAppelees0_.charCardsBySuit(_arg0);
            HandTarot figures1_ = cartesPossedesNonAppelees1_.charCardsBySuit(_arg1);
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
                    return 1;
                }
                return -1;
            }
            if(figures0_.total() > figures1_.total()) {
                return 1;
            }
            if(figures0_.total() < figures1_.total()) {
                return -1;
            }
            if(couleur(_arg0).total() < couleur(_arg1).total()) {
                return 1;
            }
            if(couleur(_arg0).total() > couleur(_arg1).total()) {
                return -1;
            }
            return 0;
        }
        if(couleur(_arg0).total() > couleur(_arg1).total()) {
            return 1;
        }
        if(couleur(_arg0).total() < couleur(_arg1).total()) {
            return -1;
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
    private boolean appelImpossible(Suit _arg0) {
        return couleur(_arg0).contientCartes(dealingCalledCards.getVal(_arg0));
    }

}
