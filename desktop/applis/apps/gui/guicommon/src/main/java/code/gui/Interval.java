package code.gui;

public final class Interval {
    private final int min;
    private final int max;

    public Interval(int _min, int _max) {
        this.min = _min;
        this.max = _max;
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }
}
