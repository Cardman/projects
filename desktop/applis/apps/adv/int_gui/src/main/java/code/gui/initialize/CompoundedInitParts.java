package code.gui.initialize;

import code.expressionlanguage.filenames.AbstractNameValidating;
import code.gui.images.AbstractImageFactory;
import code.stream.core.AbstractZipFact;
import code.threads.AbstractThreadFactory;

public final class CompoundedInitParts {
    private final AbstractThreadFactory threadFactory;
    private final AbstractZipFact zipFact;
    private final AbstractNameValidating validator;
    private final AbsCompoFactory compoFactory;
    private final AbstractImageFactory imageFactory;

    public CompoundedInitParts(AbstractThreadFactory _t, AbstractZipFact _z, AbstractNameValidating _v, AbsCompoFactory _c, AbstractImageFactory _i) {
        this.threadFactory = _t;
        this.zipFact = _z;
        this.validator = _v;
        this.compoFactory = _c;
        this.imageFactory = _i;
    }

    public AbstractThreadFactory getThreadFactory() {
        return threadFactory;
    }

    public AbsCompoFactory getCompoFactory() {
        return compoFactory;
    }

    public AbstractImageFactory getImageFactory() {
        return imageFactory;
    }

    public AbstractNameValidating getValidator() {
        return validator;
    }

    public AbstractZipFact getZipFact() {
        return zipFact;
    }
}
