package code.maths.montecarlo;

public interface AbstractGenerator {
    double pick();
    CustomSeedGene seed();
}
