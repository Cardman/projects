package cards.belote.comparators;
import code.util.ints.Comparing;

import cards.belote.enumerations.CardBelote;

public final class DeclareStrengthCardBeloteComparator implements Comparing<CardBelote> {

    @Override
    public int compare(CardBelote _o1, CardBelote _o2) {
        if(_o1.forceAnnonce() > _o2.forceAnnonce()){
            return -1;
        }
        if(_o1.forceAnnonce() < _o2.forceAnnonce()){
            return 1;
        }
        return 0;
    }

}
