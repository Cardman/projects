package code.maths.litteraladv;

import code.maths.montecarlo.AbsDoubleToStrConverter;
import code.maths.montecarlo.AbstractGenerator;
import code.maths.montecarlo.CustomSeedGene;

public final class MaParameters {
    private final AbstractGenerator generator;
    private final CustomSeedGene cust;
    private final AbsDoubleToStrConverter converter;
//    private final StringMap<String> mapping;

//    public MaParameters(AbstractGenerator _generator, StringMap<String> _mapping) {
//        this.generator = _generator;
////        this.mapping = _mapping;
//    }

    public MaParameters(AbstractGenerator _generator, CustomSeedGene _cust, AbsDoubleToStrConverter _conv) {
        this.generator = _generator;
        this.cust = _cust;
        this.converter = _conv;
//        this.mapping = _mapping;
    }

    public AbstractGenerator getGenerator() {
        return generator;
    }

    public CustomSeedGene getCust() {
        return cust;
    }

    public AbsDoubleToStrConverter getConverter() {
        return converter;
    }

    //    public StringMap<String> getMapping() {
//        return mapping;
//    }
}
