package cards.belote.comparators;

import cards.belote.HandBelote;
import cards.belote.enumerations.CardBelote;

final class CardStrengthComparatorFirst extends CardStrengthComparatorAbs{
    CardStrengthComparatorFirst() {
        super(-1);
    }

    @Override
    public CardBelote card(HandBelote _hand) {
        return _hand.premiereCarte();
    }
}
