package aiki.beans.facade.comparators;
import java.util.Comparator;

import code.util.EnumMap;
import code.util.StringMap;
import aiki.DataBase;
import aiki.comparators.ComparatorTrStringStatistic;
import aiki.comparators.ComparatorTrStrings;
import aiki.fight.enums.Statistic;
import aiki.fight.util.StatisticPokemon;

public class ComparatorStatisticPokemon implements Comparator<StatisticPokemon> {

    private DataBase data;

    private String language;

    public ComparatorStatisticPokemon(DataBase _data, String _language) {
        data = _data;
        language = _language;
    }

    @Override
    public int compare(StatisticPokemon _o1, StatisticPokemon _o2) {
        EnumMap<Statistic,String> translatedStatisticsCmp_ = data.getTranslatedStatistics().getVal(language);
        int res_ = ComparatorTrStringStatistic.compare(translatedStatisticsCmp_, _o1.getStatistic(), _o2.getStatistic());
        if (res_ != 0) {
            return res_;
        }
        StringMap<String> translatedPokemonCmp_ = data.getTranslatedPokemon().getVal(language);
        return ComparatorTrStrings.compare(translatedPokemonCmp_, _o1.getPokemon(), _o2.getPokemon());
    }

}
