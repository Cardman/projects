package code.stream.core;

public final class TechStreams {
    private final AbstractBinFact binFact;
    private final AbstractTextFact textFact;
    private final AbstractZipFact zipFact;

    public TechStreams(AbstractBinFact _binFact,AbstractTextFact _textFact, AbstractZipFact _zipFact) {
        this.binFact = _binFact;
        this.textFact = _textFact;
        this.zipFact = _zipFact;
    }

    public AbstractBinFact getBinFact() {
        return binFact;
    }

    public AbstractTextFact getTextFact() {
        return textFact;
    }

    public AbstractZipFact getZipFact() {
        return zipFact;
    }
}
