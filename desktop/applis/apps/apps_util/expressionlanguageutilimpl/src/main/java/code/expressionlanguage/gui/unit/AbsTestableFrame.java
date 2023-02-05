package code.expressionlanguage.gui.unit;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.filenames.AbstractNameValidating;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.utilcompo.*;
import code.gui.AbstractAdvGraphicListGeneratorStruct;
import code.gui.CdmFactory;
import code.gui.initialize.*;
import code.threads.AbstractThreadFactory;

public abstract class AbsTestableFrame implements TestableFrame {
    private final AbstractLightProgramInfos frames;
    private final AbstractIssuer issuer;
    private final CdmFactory interceptor;
    private final CommonExecution commonExecution;

    protected AbsTestableFrame(AbstractLightProgramInfos _frames, AbstractIssuer _issuer, AbstractInterceptor _inter, AbstractAdvGraphicListGenerator _adv, AbstractAdvGraphicListGeneratorStruct _cr, ProgTestBarInt _b) {
        this.frames = _frames;
        this.issuer = _issuer;
        interceptor = new CdmFactory(_frames,_inter,_adv,_cr);
        commonExecution = new CommonExecution(_b);
    }

    @Override
    public void showProgress(ContextEl _ctx, Struct _infos, LgNamesWithNewAliases _evolved) {
        commonExecution.showProgress(_ctx, _infos, _evolved);
    }

    @Override
    public void finish(Struct _infos, LgNamesWithNewAliases _evolved) {
        commonExecution.finish(_infos, _evolved);
    }

    @Override
    public void setResults(ContextEl _ctx, Argument _res, LgNamesWithNewAliases _evolved) {
        commonExecution.setResults(_ctx, _res, _evolved);
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

    public AbstractThreadFactory getThreadFactory() {
        return getFrames().getThreadFactory();
    }

    public AbstractLightProgramInfos getFrames() {
        return frames;
    }

    public abstract MemInputFiles getInputs();
}
