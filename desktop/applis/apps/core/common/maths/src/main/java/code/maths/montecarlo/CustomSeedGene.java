package code.maths.montecarlo;

import code.util.core.NumberUtil;

public final class CustomSeedGene {
    private final double[] rand;
    private int seedIndex;
    private AbsDoubleToStrConverter converter = new DefDoubleToStrConverter();

    public CustomSeedGene() {
        this(new double[0]);
    }

    public CustomSeedGene(double[] _rand) {
        this.rand = _rand;
    }

    public static CustomSeedGene copy(CustomSeedGene _orig) {
        CustomSeedGene g_ = new CustomSeedGene(_orig.rand);
        g_.setConverter(_orig.getConverter());
        return g_;
    }

    public double pick(AbstractGenerator _gene) {
        int len_ = rand.length;
        if (seedIndex < len_) {
            double elt_ = rand[seedIndex];
            seedIndex = NumberUtil.max(0, (seedIndex+1)%len_);
            return elt_;
        }
        return _gene.pick();
    }
    public boolean custom() {
        return rand.length > 0;
    }

    public AbsDoubleToStrConverter getConverter() {
        return converter;
    }

    public void setConverter(AbsDoubleToStrConverter _c) {
        this.converter = _c;
    }

    int getRandLen() {
        return rand.length;
    }

    double getRandValue() {
        return rand[seedIndex];
    }

    int getSeedIndex() {
        return seedIndex;
    }

    void setSeedIndex(int _s) {
        seedIndex = _s;
    }
}
