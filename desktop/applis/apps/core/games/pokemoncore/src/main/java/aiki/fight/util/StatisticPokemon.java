package aiki.fight.util;
import aiki.fight.enums.Statistic;
import code.util.StringList;
import code.util.core.StringUtil;
import code.util.ints.Displayable;

public final class StatisticPokemon extends AbsStatisticKey implements Displayable {

    private static final char SEPARATOR = ';';

    private String pokemon;

    public StatisticPokemon(String _str) {
        StringList elements_ = StringUtil.splitChars(_str, SEPARATOR);
        setStatistic(Statistic.getStatisticByName(elements_.first()));
        pokemon = elements_.last();
    }

    public StatisticPokemon(Statistic _statistic, String _pokemon) {
        setStatistic(_statistic);
        pokemon = _pokemon;
    }

    
    public static StatisticPokemon newStatisticPokemon(String _string) {
        return new StatisticPokemon(_string);
    }

    public boolean eq(StatisticPokemon _obj) {
        if (getStatistic() != _obj.getStatistic()) {
            return false;
        }
        return StringUtil.quickEq(pokemon, _obj.pokemon);
    }

    public String getPokemon() {
        return pokemon;
    }

    public void setPokemon(String _p) {
        this.pokemon = _p;
    }

    @Override
    public String display() {
        StringBuilder str_ = new StringBuilder();
        str_.append(getStatistic().getStatName());
        str_.append(SEPARATOR);
        str_.append(pokemon);
        return str_.toString();
    }
}
