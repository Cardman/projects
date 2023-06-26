package code.expressionlanguage.utilimpl;

import code.expressionlanguage.filenames.AbstractNameValidating;
import code.expressionlanguage.fwd.AbsLightContextGenerator;
import code.expressionlanguage.guicompos.LgNamesGui;
import code.expressionlanguage.options.ResultContext;
import code.expressionlanguage.utilcompo.*;
import code.gui.initialize.AbstractLightProgramInfos;
import code.stream.core.ReadFiles;
import code.threads.AbstractAtomicBoolean;
import code.util.StringMap;

public final class MemoResultContextNext implements AbsLightMemoResultContextNext {
    private final FileInfos fileInfos;
    public MemoResultContextNext(ResultContext _base, MemInputFiles _input, AbstractIssuer _issuer) {
        LgNamesGui lg_ = (LgNamesGui) _base.getForwards().getGenerator();
        MemoryReporter m_ = (MemoryReporter) lg_.getExecContent().getInfos().getReporter();
        MemInputFiles src_ = new MemInputFiles(m_.getConf(),_input.getSrc(),_input.getFiles());
        this.fileInfos = fileInfos(lg_.getExecContent().getExecutingOptions().getLightProgramInfos(), _issuer, src_);
    }

    public static FileInfos fileInfos(AbstractLightProgramInfos _frames, AbstractIssuer _issuer, MemInputFiles _input) {
        AbstractNameValidating validator_ = _frames.getValidator();
        return FileInfos.buildMemoryFromFile(_frames, _frames.getGenerator(),
                validator_, _issuer, _input, _frames.getZipFact(), _frames.getThreadFactory());
    }

    @Override
    public ResultContext next(ResultContext _r, StringMap<String> _files) {
        LgNamesWithNewAliases lg_ = (LgNamesWithNewAliases) _r.getForwards().getGenerator();
        ExecutingOptions exec_ = lg_.getExecContent().getExecutingOptions();
        String archive_ = exec_.getAccess();
        ReadFiles result_ = fileInfos.getReporter().getFiles(archive_);
        result_.getZipFiles().putAllMap(_files);
        StringMap<String> list_ = RunningTest.tryGetSrc(archive_, exec_, fileInfos, result_);
        if (list_ == null) {
            return null;
        }
        return ResultContext.def(_r, list_,exec_.getSrcFolder());
    }


    @Override
    public AbsLightContextGenerator generate(AbstractAtomicBoolean _at) {
        return new MemoContextGenerator(_at);
    }
}
