package code.vi.maths.random;

import code.maths.montecarlo.AbstractGenerator;
import code.maths.montecarlo.CustomSeedGene;

import java.security.SecureRandom;

public final class AdvancedGenerator implements AbstractGenerator {
    private final SecureRandom random = new SecureRandom();

    @Override
    public double pick() {
        return random.nextDouble();
    }

    @Override
    public CustomSeedGene seed() {
        return new CustomSeedGene();
    }
}
