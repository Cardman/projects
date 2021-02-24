package aiki.fight.util;

public final class StatisticPokemonByte {

    private final StatisticPokemon stat;
    private final byte value;

    public StatisticPokemonByte(StatisticPokemon _stat, byte _value) {
        this.stat = _stat;
        this.value = _value;
    }

    public StatisticPokemon getStat() {
        return stat;
    }

    public byte getValue() {
        return value;
    }
}
