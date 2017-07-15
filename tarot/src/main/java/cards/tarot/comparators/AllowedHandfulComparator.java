package cards.tarot.comparators;
import java.util.Comparator;

import code.util.CustList;
import code.util.EnumMap;
import cards.tarot.enumerations.Handfuls;

public final class AllowedHandfulComparator implements Comparator<Handfuls> {

    private EnumMap<Handfuls,Integer> allowedHandfuls;

    public AllowedHandfulComparator(EnumMap<Handfuls,Integer> _allowedHandfuls) {
        allowedHandfuls = _allowedHandfuls;
    }

    @Override
    public int compare(Handfuls _hand1, Handfuls _hand2) {
        if (allowedHandfuls.getVal(_hand1) > allowedHandfuls.getVal(_hand2)) {
            return CustList.NO_SWAP_SORT;
        }
        if (allowedHandfuls.getVal(_hand1) < allowedHandfuls.getVal(_hand2)) {
            return CustList.SWAP_SORT;
        }
        return CustList.EQ_CMP;
    }

}
