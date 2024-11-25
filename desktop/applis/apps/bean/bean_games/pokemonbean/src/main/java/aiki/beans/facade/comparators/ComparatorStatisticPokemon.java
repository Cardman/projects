package aiki.beans.facade.comparators;
import aiki.comparators.*;
import aiki.db.*;
import aiki.fight.enums.*;
import aiki.fight.util.*;
import code.util.*;
import code.util.ints.*;

public final class ComparatorStatisticPokemon implements Comparing<StatisticPokemon> {

    private final AbsMap<Statistic, String> val;
    private final AbsMap<String, String> pk;

    public ComparatorStatisticPokemon(DataBase _data, String _language) {
        this(_data.getTranslatedStatistics().getVal(_language), _data.getTranslatedPokemon().getVal(_language));
    }

    public ComparatorStatisticPokemon(AbsMap<Statistic, String> _s, AbsMap<String, String> _p) {
        val = _s;
        pk = _p;
    }

    @Override
    public int compare(StatisticPokemon _o1, StatisticPokemon _o2) {
        return comparePairs(_o1, _o2, val, pk);
    }

    public static int comparePairs(StatisticPokemon _o1, StatisticPokemon _o2, AbsMap<Statistic, String> _stats, AbsMap<String, String> _pk) {
        int res_ = ComparatorTrStrings.compareStatistic(_stats, _o1.getStatistic(), _o2.getStatistic());
        if (res_ != 0) {
            return res_;
        }
        return ComparatorTrStrings.compare(_pk, _o1.getPokemon(), _o2.getPokemon());
    }

}