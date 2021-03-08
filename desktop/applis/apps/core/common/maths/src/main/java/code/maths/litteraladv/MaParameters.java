package code.maths.litteraladv;

import code.maths.montecarlo.AbstractGenerator;
import code.util.StringMap;

public final class MaParameters {
    private final AbstractGenerator generator;
    private final StringMap<String> mapping;

    public MaParameters(AbstractGenerator _generator, StringMap<String> _mapping) {
        this.generator = _generator;
        this.mapping = _mapping;
    }

    public AbstractGenerator getGenerator() {
        return generator;
    }

    public StringMap<String> getMapping() {
        return mapping;
    }
}
