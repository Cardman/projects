package aiki.beans.facade.comparators;
import java.util.Comparator;

import aiki.beans.facade.simulation.dto.RadioLineMove;

public final class ComparatorRadioLineMoves implements Comparator<RadioLineMove> {

    @Override
    public int compare(RadioLineMove _arg0, RadioLineMove _arg1) {
        return _arg0.getDisplayName().compareTo(_arg1.getDisplayName());
    }

}
