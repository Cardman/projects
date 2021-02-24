package aiki.fight.util;

public final class StatisticStatusByte {

    private final StatisticStatus stat;
    private final byte value;

    public StatisticStatusByte(StatisticStatus _stat, byte _value) {
        this.stat = _stat;
        this.value = _value;
    }

    public StatisticStatus getStat() {
        return stat;
    }

    public byte getValue() {
        return value;
    }
}
