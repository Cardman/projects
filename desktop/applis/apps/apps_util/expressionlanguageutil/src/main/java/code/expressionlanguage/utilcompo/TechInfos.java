package code.expressionlanguage.utilcompo;

import code.stream.core.AbstractBinFact;
import code.stream.core.AbstractZipFact;
import code.stream.core.TechStreams;
import code.threads.AbstractThreadFactory;

public final class TechInfos {
    private final AbstractThreadFactory threadFactory;
    private final TechStreams techStreams;

    public TechInfos(AbstractThreadFactory _threadFactory, TechStreams _zipFact) {
        this.threadFactory = _threadFactory;
        this.techStreams = _zipFact;
    }

    public TechStreams getTechStreams() {
        return techStreams;
    }

    public AbstractZipFact getZipFact() {
        return getTechStreams().getZipFact();
    }

    public AbstractThreadFactory getThreadFactory() {
        return threadFactory;
    }

    public AbstractBinFact getBinFact() {
        return getTechStreams().getBinFact();
    }
}
