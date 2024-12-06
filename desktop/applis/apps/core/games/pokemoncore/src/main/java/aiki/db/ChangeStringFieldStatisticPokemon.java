package aiki.db;

import aiki.fight.util.*;

public final class ChangeStringFieldStatisticPokemon implements ChangeStringField {
    private final StatisticPokemon statisticPokemon;

    public ChangeStringFieldStatisticPokemon(StatisticPokemon _l) {
        this.statisticPokemon = _l;
    }

    @Override
    public String value() {
        return statisticPokemon.getPokemon();
    }

    @Override
    public void value(String _v) {
        statisticPokemon.setPokemon(_v);
    }
}
