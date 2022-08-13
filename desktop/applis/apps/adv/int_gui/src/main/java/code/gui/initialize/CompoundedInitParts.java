package code.gui.initialize;

import code.expressionlanguage.filenames.*;
import code.expressionlanguage.utilcompo.*;
import code.gui.images.*;
import code.stream.core.*;
import code.threads.AbstractThreadFactory;

public final class CompoundedInitParts {
    private final AbstractThreadFactory threadFactory;
    private final AbstractZipFact zipFact;
    private final AbstractNameValidating validator;
    private final AbsCompoFactory compoFactory;
    private final AbstractImageFactory imageFactory;
    private final AbstractInterceptor interceptor;

    public CompoundedInitParts(AbstractThreadFactory _t,AbstractZipFact _z, AbstractNameValidating _v, AbsCompoFactory _c, AbstractImageFactory _i, AbstractInterceptor _p) {
        this.threadFactory = _t;
        this.zipFact = _z;
        this.validator = _v;
        this.compoFactory = _c;
        this.imageFactory = _i;
        this.interceptor = _p;
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

    public AbstractInterceptor getInterceptor() {
        return interceptor;
    }

    public AbstractNameValidating getValidator() {
        return validator;
    }

    public AbstractZipFact getZipFact() {
        return zipFact;
    }
}
