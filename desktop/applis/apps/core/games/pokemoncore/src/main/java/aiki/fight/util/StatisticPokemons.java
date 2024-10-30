package aiki.fight.util;

import aiki.util.*;
import code.util.*;

public final class StatisticPokemons extends CommonMap<StatisticPokemon,Byte> {
    public StatisticPokemons() {
    }
    public StatisticPokemons(CollCapacity _cap) {
        super(_cap);
    }

    @Override
    protected boolean eq(StatisticPokemon _k, StatisticPokemon _e) {
        return _k.eq(_e);
    }

    @Override
    protected Byte def() {
        return (byte)0;
    }

}
