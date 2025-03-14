package aiki.fight.util;

import code.util.*;

public final class StatisticPokemons extends AbsBasicMap<StatisticPokemon,Long> {
    public StatisticPokemons() {
    }
    public StatisticPokemons(CollCapacity _cap) {
        super(_cap);
    }

    @Override
    protected boolean matchKeys(StatisticPokemon _k, StatisticPokemon _e) {
        return _k.eq(_e);
    }

    @Override
    protected Long def() {
        return 0L;
    }

}
