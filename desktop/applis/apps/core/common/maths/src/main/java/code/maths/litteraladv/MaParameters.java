package code.maths.litteraladv;

import code.maths.montecarlo.AbstractGenerator;
import code.maths.montecarlo.CustomSeedGene;

public final class MaParameters {
    private final AbstractGenerator generator;
    private final CustomSeedGene cust;
//    private final StringMap<String> mapping;

//    public MaParameters(AbstractGenerator _generator, StringMap<String> _mapping) {
//        this.generator = _generator;
////        this.mapping = _mapping;
//    }

    public MaParameters(AbstractGenerator _generator, CustomSeedGene _cust) {
        this.generator = _generator;
        this.cust = _cust;
//        this.mapping = _mapping;
    }

    public AbstractGenerator getGenerator() {
        return generator;
    }

    public CustomSeedGene getCust() {
        return cust;
    }

    //    public StringMap<String> getMapping() {
//        return mapping;
//    }
}
