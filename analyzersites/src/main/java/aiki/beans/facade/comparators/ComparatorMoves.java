package aiki.beans.facade.comparators;
import java.util.Comparator;

import aiki.beans.facade.simulation.dto.SelectLineMove;

public final class ComparatorMoves implements Comparator<SelectLineMove> {

    @Override
    public int compare(SelectLineMove _arg0, SelectLineMove _arg1) {
        return _arg0.getDisplayName().compareTo(_arg1.getDisplayName());
    }

}
