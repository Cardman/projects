package aiki.fight.util;

import code.util.*;

public final class StatisticPokemons extends AbsBasicMap<StatisticPokemon,Byte> {
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
    protected Byte def() {
        return (byte)0;
    }

}
