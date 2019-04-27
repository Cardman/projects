package code.maths.montecarlo;

import code.util.ints.Equallable;

public final class SampleEquallable implements Equallable<SampleEquallable> {
    private final int info;
    public SampleEquallable(int _info) {
        info = _info;
    }
    @Override
    public boolean eq(SampleEquallable _g) {
        return info == _g.info;
    }
}
