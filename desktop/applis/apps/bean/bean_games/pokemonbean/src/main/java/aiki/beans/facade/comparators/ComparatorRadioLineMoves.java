package aiki.beans.facade.comparators;
import aiki.beans.facade.simulation.dto.RadioLineMove;
import code.util.core.StringUtil;
import code.util.ints.Comparing;

public final class ComparatorRadioLineMoves implements Comparing<RadioLineMove> {

    @Override
    public int compare(RadioLineMove _arg0, RadioLineMove _arg1) {
        return StringUtil.compareStrings(_arg0.getDisplayName(),_arg1.getDisplayName());
    }

}