package code.maths.random;

import code.maths.montecarlo.AbstractGenerator;

import java.security.SecureRandom;

public final class AdvancedGenerator implements AbstractGenerator {
    private final SecureRandom random = new SecureRandom();

    @Override
    public double pick() {
        return random.nextDouble();
    }
}
