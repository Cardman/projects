package aiki.fight.util;

public class StatisticCategoryParam<T> {
    private final StatisticCategory statistic;
    private final T value;

    public StatisticCategoryParam(StatisticCategory _statistic, T _value) {
        this.statistic = _statistic;
        this.value = _value;
    }

    public StatisticCategory getStatistic() {
        return statistic;
    }

    public T getValue() {
        return value;
    }
}
