package code.stream.core;

public final class TechStreams {
    private final AbstractBinFact binFact;
    private final AbstractZipFact zipFact;

    public TechStreams(AbstractBinFact _binFact, AbstractZipFact _zipFact) {
        this.binFact = _binFact;
        this.zipFact = _zipFact;
    }

    public AbstractBinFact getBinFact() {
        return binFact;
    }

    public AbstractZipFact getZipFact() {
        return zipFact;
    }
}
