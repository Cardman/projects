package code.maths.montecarlo;

import code.util.core.NumberUtil;

public final class DefaultGenerator implements AbstractGenerator {

    private static final double VALUE = 0.5;

    private final CustomSeedGene rand;

    public DefaultGenerator(double _se) {
        rand = new CustomSeedGene(oneEltArr(_se));
    }

    public DefaultGenerator(CustomSeedGene _se) {
        rand = _se;
    }

    public static AbstractGenerator oneElt() {
        return new DefaultGenerator(oneEltGene());
    }

    public static CustomSeedGene oneEltGene() {
        return oneEltGene(VALUE);
    }

    public static CustomSeedGene oneEltGene(double _v) {
        return new CustomSeedGene(oneEltArr(_v));
    }

    public static AbstractGenerator sizeOne() {
        return new DefaultGenerator(new CustomSeedGene(new double[1]));
    }

    public static double[] oneEltArr() {
        return oneEltArr(VALUE);
    }

    public static double[] oneEltArr(double _v) {
        double[] v_ = new double[1];
        v_[0]=_v;
        return v_;
    }
    @Override
    public double pick() {
        int len_ = rand.getRandLen();
        double elt_ = rand.getRandValue();
        rand.setSeedIndex(NumberUtil.max(0, (rand.getSeedIndex() + 1) % len_));
        return elt_;
    }

    @Override
    public CustomSeedGene seed() {
        return rand;
    }
}
