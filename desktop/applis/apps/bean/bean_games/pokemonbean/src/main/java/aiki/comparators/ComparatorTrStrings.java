package aiki.comparators;

import aiki.fight.enums.*;
import code.util.*;
import code.util.core.*;

public final class ComparatorTrStrings {

    private ComparatorTrStrings() {
    }

    public static int compare(AbsMap<String,String> _trStrings, String _e1, String _e2) {
        return StringUtil.compareStrings(StringUtil.nullToEmpty(_trStrings.getVal(_e1)), StringUtil.nullToEmpty(_trStrings.getVal(_e2)));
    }

    public static int compareStatistic(AbsMap<Statistic,String> _translator, Statistic _e1, Statistic _e2) {
        return StringUtil.compareStrings(StringUtil.nullToEmpty(_translator.getVal(_e1)), StringUtil.nullToEmpty(_translator.getVal(_e2)));
    }
}
