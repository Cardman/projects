package aiki.beans.facade.comparators;
import java.util.Comparator;

import aiki.beans.facade.dto.MoveLine;

public class ComparatorMoves implements Comparator<MoveLine> {

    @Override
    public int compare(MoveLine _arg0, MoveLine _arg1) {
        return _arg0.getDisplayName().compareTo(_arg1.getDisplayName());
    }

}
