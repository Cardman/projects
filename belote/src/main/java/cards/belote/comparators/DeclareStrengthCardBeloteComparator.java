package cards.belote.comparators;
import java.util.Comparator;

import cards.belote.enumerations.CardBelote;

public final class DeclareStrengthCardBeloteComparator implements Comparator<CardBelote> {

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
