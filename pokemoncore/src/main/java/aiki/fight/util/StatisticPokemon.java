package aiki.fight.util;
import aiki.fight.enums.Statistic;
import code.serialize.CheckedData;
import code.sml.FromAndToString;
import code.util.StringList;
import code.util.ints.Displayable;
import code.util.ints.Equallable;

@CheckedData
public final class StatisticPokemon implements Equallable<StatisticPokemon>, Displayable {

    private static final char SEPARATOR = ';';

    private final Statistic statistic;

    private final String pokemon;

    public StatisticPokemon(String _str) {
        StringList elements_ = StringList.splitChars(_str, SEPARATOR);
        statistic = Statistic.getStatisticByName(elements_.first());
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

    public Statistic getStatistic() {
        return statistic;
    }

    public String getPokemon() {
        return pokemon;
    }

    @FromAndToString
    @Override
    public String display() {
        StringBuilder str_ = new StringBuilder();
        str_.append(statistic.name());
        str_.append(SEPARATOR);
        str_.append(pokemon);
        return str_.toString();
    }
}
