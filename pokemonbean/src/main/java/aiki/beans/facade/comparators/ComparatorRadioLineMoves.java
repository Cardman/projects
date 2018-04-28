package aiki.beans.facade.comparators;
import code.util.ints.Comparing;

import aiki.beans.facade.simulation.dto.RadioLineMove;

public final class ComparatorRadioLineMoves implements Comparing<RadioLineMove> {

    @Override
    public int compare(RadioLineMove _arg0, RadioLineMove _arg1) {
        return _arg0.getDisplayName().compareTo(_arg1.getDisplayName());
    }

}