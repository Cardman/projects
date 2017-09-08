package aiki.comparators;
import java.util.Comparator;

import code.util.EnumMap;
import aiki.DataBase;
import aiki.map.levels.enums.EnvironmentType;

public final class ComparatorTrStringEnv implements Comparator<EnvironmentType> {

    private EnumMap<EnvironmentType,String> translator;

    public ComparatorTrStringEnv(EnumMap<EnvironmentType,String> _translator) {
        translator = _translator;
    }

    @Override
    public int compare(EnvironmentType _e1, EnvironmentType _e2) {
        return compare(translator, _e1, _e2);
    }

    public static int compare(EnumMap<EnvironmentType,String> _translator, EnvironmentType _e1, EnvironmentType _e2) {
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
