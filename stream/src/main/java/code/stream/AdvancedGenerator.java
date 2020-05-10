package code.stream;

import code.maths.montecarlo.AbstractGenerator;

public final class AdvancedGenerator implements AbstractGenerator {
    @Override
    public double pick() {
        return Math.random();
    }
}
