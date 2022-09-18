package aiki.fight.util;
import aiki.fight.enums.Statistic;
import code.util.StringList;
import code.util.core.StringUtil;
import code.util.ints.Displayable;

public final class StatisticPokemon implements Displayable {

    private static final char SEPARATOR = ';';

    private final Statistic statistic;

    private final String pokemon;

    public StatisticPokemon(String _str) {
        StringList elements_ = StringUtil.splitChars(_str, SEPARATOR);
        statistic = Statistic.getStatisticByName(elements_.first());
        pokemon = elements_.last();
    }

    public StatisticPokemon(Statistic _statistic, String _pokemon) {
        statistic = _statistic;
        pokemon = _pokemon;
    }

    
    public static StatisticPokemon newStatisticPokemon(String _string) {
        return new StatisticPokemon(_string);
    }

    public boolean eq(StatisticPokemon _obj) {
        if (statistic != _obj.statistic) {
            return false;
        }
        return StringUtil.quickEq(pokemon, _obj.pokemon);
    }

    public Statistic getStatistic() {
        return statistic;
    }

    public String getPokemon() {
        return pokemon;
    }

    
    @Override
    public String display() {
        StringBuilder str_ = new StringBuilder();
        str_.append(statistic.getStatName());
        str_.append(SEPARATOR);
        str_.append(pokemon);
        return str_.toString();
    }
}
