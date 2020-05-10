package code.stream;

import code.maths.montecarlo.AbstractGenerator;

import java.security.SecureRandom;

public final class AdvancedGenerator implements AbstractGenerator {
    private SecureRandom random = new SecureRandom();
    public void setSeed(long _seed) {
        random.setSeed(_seed);
    }
    @Override
    public double pick() {
        return random.nextDouble();
    }
}
