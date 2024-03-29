package aiki.beans.facade.comparators;
import aiki.comparators.ComparatorTrStrings;
import aiki.db.DataBase;
import aiki.fight.enums.Statistic;
import aiki.fight.util.StatisticPokemon;
import code.util.AbsMap;
import code.util.StringMap;
import code.util.ints.Comparing;

public final class ComparatorStatisticPokemon implements Comparing<StatisticPokemon> {

    private final DataBase data;

    private final String language;

    public ComparatorStatisticPokemon(DataBase _data, String _language) {
        data = _data;
        language = _language;
    }

    @Override
    public int compare(StatisticPokemon _o1, StatisticPokemon _o2) {
        AbsMap<Statistic,String> translatedStatisticsCmp_ = data.getTranslatedStatistics().getVal(language);
        int res_ = ComparatorTrStrings.compareStatistic(translatedStatisticsCmp_, _o1.getStatistic(), _o2.getStatistic());
        if (res_ != 0) {
            return res_;
        }
        StringMap<String> translatedPokemonCmp_ = data.getTranslatedPokemon().getVal(language);
        return ComparatorTrStrings.compare(translatedPokemonCmp_, _o1.getPokemon(), _o2.getPokemon());
    }

}