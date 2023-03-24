package code.expressionlanguage.adv;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.ReportedMessages;
import code.expressionlanguage.guicompos.LgNamesGui;
import code.expressionlanguage.options.Options;
import code.expressionlanguage.options.ResultContext;
import code.expressionlanguage.utilcompo.CustAliases;
import code.expressionlanguage.utilcompo.ExecutingOptions;
import code.expressionlanguage.utilcompo.FileInfos;
import code.expressionlanguage.utilcompo.RunnableContextEl;
import code.expressionlanguage.utilimpl.CustContextFactory;
import code.expressionlanguage.utilimpl.RunningTest;
import code.gui.initialize.AbstractProgramInfos;

public final class AnalyzeExpressionSource implements Runnable {
    private final WindowCdmEditor mainFrame;

    public AnalyzeExpressionSource(WindowCdmEditor _e) {
        this.mainFrame = _e;
    }

    @Override
    public void run() {
        mainFrame.getStatusAnalyzeArea().setText("");
        AbstractProgramInfos frames_ = mainFrame.getCommonFrame().getFrames();
        ResultContext r_ = nextValidate(mainFrame.getBaseResult());
        if (!r_.getPageEl().isCustomAna()) {
            mainFrame.getStatusAnalyzeArea().append("KO\n");
            mainFrame.getStatusAnalyzeArea().append(CustAliases.getDateTimeText(frames_.getThreadFactory()));
            return;
        }
        LgNamesGui stds_ = (LgNamesGui) r_.getForwards().getGenerator();
        ExecutingOptions exec_ = stds_.getExecContent().getExecutingOptions();
        Options opt_ = r_.getForwards().getOptions();
        FileInfos file_ =stds_.getExecContent().getInfos();
        opt_.setReadOnly(true);
        ReportedMessages rep_ = r_.getReportedMessages();
        CustContextFactory.reportErrors(opt_, exec_, rep_, file_);
        ContextEl c_ = r_.getContext();
        if (c_ instanceof RunnableContextEl) {
            mainFrame.getStatusAnalyzeArea().append(mainFrame.getResultContext().update(stds_.getExecContent().getCustAliases(), stds_.getContent(),(RunnableContextEl)c_,frames_));
        } else {
            mainFrame.getStatusAnalyzeArea().append(CustAliases.getDateTimeText(frames_.getThreadFactory()));
        }
        mainFrame.getResultContext().setReportedMessages(rep_);
    }

    public static ResultContext nextValidate(ResultContext _base) {
        LgNamesGui lg_ = (LgNamesGui) _base.getForwards().getGenerator();
        ExecutingOptions exec_ = lg_.getExecContent().getExecutingOptions();
        FileInfos file_ = lg_.getExecContent().getInfos();
        return RunningTest.nextValidate(_base, lg_, exec_, file_);
    }

}
