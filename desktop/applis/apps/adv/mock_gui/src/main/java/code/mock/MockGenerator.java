package code.mock;

import code.maths.montecarlo.AbstractGenerator;
import code.maths.montecarlo.CustomSeedGene;

public final class MockGenerator implements AbstractGenerator {

    private final CustomSeedGene rand;

    public MockGenerator(double[] _se) {
        rand = new CustomSeedGene(_se);
    }
    @Override
    public double pick() {
        return 0.5;
    }

    @Override
    public CustomSeedGene seed() {
        return rand;
    }
}
