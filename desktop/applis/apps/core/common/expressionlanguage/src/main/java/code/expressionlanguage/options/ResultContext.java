package code.expressionlanguage.options;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.ReportedMessages;
import code.expressionlanguage.analyze.blocks.ClassesUtil;
import code.expressionlanguage.exec.Classes;
import code.expressionlanguage.fwd.Forwards;
import code.expressionlanguage.stds.BuildableLgNames;
import code.expressionlanguage.stds.LoggableLgNames;
import code.util.StringMap;

public final class ResultContext {
    private final AnalyzedPageEl pageEl;
    private ContextEl context;
    private final Forwards forwards;
    private final ReportedMessages reportedMessages;

    public ResultContext(AnalyzedPageEl _page, Forwards _fwd, ReportedMessages _reportedMessages) {
        pageEl = _page;
        this.forwards = _fwd;
        this.reportedMessages = _reportedMessages;
    }

    public static ResultContext def(ResultContext _base, BuildableLgNames _generator, LoggableLgNames _loggable, StringMap<String> _files, String _filter) {
        StringMap<String> srcFiles_ = ContextFactory.filter(_files, _filter);
        AnalyzedPageEl copy_ = AnalyzedPageEl.copy(_base.getPageEl());
        copy_.getFoundTypes().addAllElts(_base.getPageEl().getFoundTypes());
        copy_.addResources(_files);
        AnalyzedPageEl resultAna_ = ClassesUtil.buildUserCode(srcFiles_, copy_);
        Classes.postValidate(resultAna_);
        Forwards forwards_ = new Forwards(_generator, _loggable, _base.getForwards().getFileBuilder(), _base.getForwards().getOptions());
        forwards_.getClasses().getCommon().setStaticFields(resultAna_.getStaticFields());
        return new ResultContext(resultAna_, forwards_, resultAna_.getMessages());
    }
    public void setContext(ContextEl _c) {
        this.context = _c;
    }

    public AnalyzedPageEl getPageEl() {
        return pageEl;
    }

    public Forwards getForwards() {
        return forwards;
    }

    public ContextEl getContext() {
        return context;
    }

    public ReportedMessages getReportedMessages() {
        return reportedMessages;
    }
}
