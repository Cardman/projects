package aiki.beans.facade.comparators;
import aiki.beans.abilities.*;
import aiki.comparators.*;
import aiki.db.*;
import aiki.fight.enums.*;
import aiki.fight.util.*;
import code.util.*;
import code.util.ints.*;

public final class ComparatorStatisticPokemon implements Comparing<TranslatedKeyPair> {

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
    public int compare(TranslatedKeyPair _o1, TranslatedKeyPair _o2) {
        return comparePairs(convert(_o1), convert(_o2), val, pk);
    }

    private StatisticPokemon convert(TranslatedKeyPair _o) {
        return new StatisticPokemon(Statistic.getStatisticByName(_o.getFirst().getKey()), _o.getSecond().getKey());
    }
    public static int comparePairs(StatisticPokemon _o1, StatisticPokemon _o2, AbsMap<Statistic, String> _stats, AbsMap<String, String> _pk) {
        int res_ = ComparatorTrStrings.compareStatistic(_stats, _o1.getStatistic(), _o2.getStatistic());
        if (res_ != 0) {
            return res_;
        }
        return ComparatorTrStrings.compare(_pk, _o1.getPokemon(), _o2.getPokemon());
    }

}