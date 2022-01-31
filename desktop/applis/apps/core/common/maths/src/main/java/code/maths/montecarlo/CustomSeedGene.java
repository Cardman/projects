package code.maths.montecarlo;

public final class CustomSeedGene {
    private final double[] rand;
    private int seedIndex;

    public CustomSeedGene() {
        this(new double[0]);
    }

    public CustomSeedGene(double[] _rand) {
        this.rand = _rand;
    }

    public static CustomSeedGene copy(CustomSeedGene _orig) {
        return new CustomSeedGene(_orig.rand);
    }

    public double pick(AbstractGenerator _gene) {
        int len_ = rand.length;
        if (seedIndex < len_) {
            double elt_ = rand[seedIndex];
            seedIndex = Math.max(0, (seedIndex+1)%len_);
            return elt_;
        }
        return _gene.pick();
    }
}
