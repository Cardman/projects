package aiki.fight.util;

public final class StatisticTypeParam<T> {
    private final StatisticType statistic;
    private final T value;

    public StatisticTypeParam(StatisticType _statistic, T _value) {
        this.statistic = _statistic;
        this.value = _value;
    }

    public StatisticType getStatistic() {
        return statistic;
    }

    public T getValue() {
        return value;
    }
}
