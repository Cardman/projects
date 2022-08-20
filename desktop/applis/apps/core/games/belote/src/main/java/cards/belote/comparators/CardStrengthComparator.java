package cards.belote.comparators;

import cards.belote.HandBelote;
import cards.belote.enumerations.CardBelote;

interface CardStrengthComparator {
    CardBelote card(HandBelote _hand);
    int rate();
}
