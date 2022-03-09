package code.expressionlanguage.gui.unit;

import code.expressionlanguage.filenames.AbstractNameValidating;
import code.expressionlanguage.utilcompo.AbstractInterceptor;
import code.expressionlanguage.utilcompo.AbstractIssuer;
import code.expressionlanguage.utilcompo.FileInfos;
import code.expressionlanguage.utilcompo.MemInputFiles;
import code.gui.initialize.AbstractLightProgramInfos;
import code.gui.initialize.AbstractProgramInfos;
import code.threads.AbstractThreadFactory;

public abstract class AbsTestableFrame implements TestableFrame {
    private final AbstractLightProgramInfos frames;
    private final AbstractIssuer issuer;

    protected AbsTestableFrame(AbstractLightProgramInfos _frames, AbstractIssuer _issuer) {
        this.frames = _frames;
        this.issuer = _issuer;
    }

    @Override
    public FileInfos getInfos() {
        AbstractNameValidating validator_ = frames.getValidator();
        return FileInfos.buildMemoryFromFile(frames.getGenerator(),
                validator_,issuer, getInputs(), frames.getZipFact(), frames.getThreadFactory());
    }

    public AbstractInterceptor getIntercept() {
        return frames.getInterceptor();
    }
    public AbstractThreadFactory getThreadFactory() {
        return frames.getThreadFactory();
    }

    public AbstractLightProgramInfos getFrames() {
        return frames;
    }

    public abstract MemInputFiles getInputs();
}
