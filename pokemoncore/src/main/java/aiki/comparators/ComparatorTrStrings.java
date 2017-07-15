package aiki.comparators;
import java.util.Comparator;

import code.util.StringMap;
import aiki.DataBase;

public class ComparatorTrStrings implements Comparator<String> {

    private StringMap<String> translator;

    public ComparatorTrStrings(StringMap<String> _translator) {
        translator = _translator;
    }

    @Override
    public int compare(String _e1, String _e2) {
        return compare(translator, _e1, _e2);
    }

    public static int compare(StringMap<String> _translator, String _e1, String _e2) {
        String trOne_;
        if (_translator.contains(_e1)) {
            trOne_ = _translator.getVal(_e1);
        } else {
            trOne_ = DataBase.EMPTY_STRING;
        }
        String trTwo_;
        if (_translator.contains(_e2)) {
            trTwo_ = _translator.getVal(_e2);
        } else {
            trTwo_ = DataBase.EMPTY_STRING;
        }
        return trOne_.compareTo(trTwo_);
    }
}
