package aiki.fight.util;
import aiki.fight.enums.Statistic;
import code.serialize.CheckedData;
import code.sml.FromAndToString;
import code.util.StringList;
import code.util.ints.Equallable;

@CheckedData
public final class StatisticPokemon implements Equallable<StatisticPokemon> {

    private static final char SEPARATOR = ';';

    private final Statistic statistic;

    private final String pokemon;

    public StatisticPokemon(String _str) {
        StringList elements_ = StringList.splitChars(_str, SEPARATOR);
        statistic = Statistic.valueOf(elements_.first());
        pokemon = elements_.last();
    }

    public StatisticPokemon(Statistic _statistic, String _pokemon) {
        statistic = _statistic;
        pokemon = _pokemon;
    }

    @FromAndToString
    public static StatisticPokemon newStatisticPokemon(String _string) {
        return new StatisticPokemon(_string);
    }

    @Override
    public boolean eq(StatisticPokemon _obj) {
        if (statistic != _obj.statistic) {
            return false;
        }
        if (!StringList.quickEq(pokemon, _obj.pokemon)) {
            return false;
        }
        return true;
    }

    @FromAndToString
    @Override
    public String toString() {
        return statistic.toString()+SEPARATOR+pokemon;
    }

    public Statistic getStatistic() {
        return statistic;
    }

    public String getPokemon() {
        return pokemon;
    }
}
