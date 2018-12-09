package aiki.beans.facade.comparators;
import aiki.beans.facade.simulation.dto.SelectLineMove;
import code.util.ints.Comparing;

public final class ComparatorMoves implements Comparing<SelectLineMove> {

    @Override
    public int compare(SelectLineMove _arg0, SelectLineMove _arg1) {
        return _arg0.getDisplayName().compareTo(_arg1.getDisplayName());
    }

}