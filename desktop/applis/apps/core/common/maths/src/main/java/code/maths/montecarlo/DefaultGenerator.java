package code.maths.montecarlo;

public final class DefaultGenerator implements AbstractGenerator {
    @Override
    public double pick() {
        return 0.5;
    }

    @Override
    public CustomSeedGene seed() {
        return new CustomSeedGene();
    }
}
