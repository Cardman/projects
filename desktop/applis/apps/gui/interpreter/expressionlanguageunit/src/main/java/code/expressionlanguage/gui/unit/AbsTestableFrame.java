package code.expressionlanguage.gui.unit;

import code.expressionlanguage.filenames.AbstractNameValidating;
import code.expressionlanguage.utilcompo.AbstractInterceptor;
import code.expressionlanguage.utilcompo.AbstractIssuer;
import code.expressionlanguage.utilcompo.FileInfos;
import code.expressionlanguage.utilcompo.MemInputFiles;
import code.gui.CdmFactory;
import code.gui.initialize.*;
import code.threads.AbstractThreadFactory;

public abstract class AbsTestableFrame implements TestableFrame {
    private final AbstractLightProgramInfos frames;
    private final AbstractIssuer issuer;
    private final CdmFactory interceptor;

    protected AbsTestableFrame(AbstractLightProgramInfos _frames, AbstractIssuer _issuer, CdmFactory _inter) {
        this.frames = _frames;
        this.issuer = _issuer;
        interceptor = _inter;
    }

    @Override
    public FileInfos getInfos() {
        AbstractNameValidating validator_ = frames.getValidator();
        return FileInfos.buildMemoryFromFile(frames,frames.getGenerator(),
                validator_,issuer, getInputs(), frames.getZipFact(), frames.getThreadFactory());
    }

    @Override
    public CdmFactory getFactory() {
        return interceptor;
    }

    public AbstractInterceptor getIntercept() {
        return interceptor.getInterceptor();
    }
    public AbstractThreadFactory getThreadFactory() {
        return frames.getThreadFactory();
    }

    public AbstractLightProgramInfos getFrames() {
        return frames;
    }

    public abstract MemInputFiles getInputs();
}
