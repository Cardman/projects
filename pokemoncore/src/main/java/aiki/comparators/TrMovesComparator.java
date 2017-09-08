package aiki.comparators;
import java.util.Comparator;

import aiki.DataBase;

public final class TrMovesComparator implements Comparator<String> {

    private DataBase data;

    public TrMovesComparator(DataBase _data) {
        data = _data;
    }

    @Override
    public int compare(String _keyOne, String _keyTwo) {
        String moveOne_ = data.translateMove(_keyOne);
        String moveTwo_ = data.translateMove(_keyTwo);
        return moveOne_.compareTo(moveTwo_);
    }

}
