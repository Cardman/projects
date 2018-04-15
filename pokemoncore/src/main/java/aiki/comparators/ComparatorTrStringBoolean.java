package aiki.comparators;
import java.util.Comparator;

import aiki.DataBase;
import code.util.EnumMap;
import code.util.pagination.SelectedBoolean;

public final class ComparatorTrStringBoolean implements Comparator<SelectedBoolean> {

    private EnumMap<SelectedBoolean,String> translator;

    public ComparatorTrStringBoolean(EnumMap<SelectedBoolean,String> _translator) {
        translator = _translator;
    }

    @Override
    public int compare(SelectedBoolean _e1, SelectedBoolean _e2) {
        return compare(translator, _e1, _e2);
    }

    public static int compare(EnumMap<SelectedBoolean,String> _translator, SelectedBoolean _e1, SelectedBoolean _e2) {
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
