package code.expressionlanguage.adv;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.ReportedMessages;
import code.expressionlanguage.guicompos.LgNamesGui;
import code.expressionlanguage.options.Options;
import code.expressionlanguage.options.ResultContext;
import code.expressionlanguage.utilcompo.ExecutingOptions;
import code.expressionlanguage.utilcompo.FileInfos;
import code.expressionlanguage.utilcompo.RunnableContextEl;
import code.expressionlanguage.utilimpl.CustContextFactory;
import code.expressionlanguage.utilimpl.ManageOptions;
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
        ResultContext r_ = nextValidate(mainFrame.getBaseResult(),mainFrame.getBaseManageOptions());
        if (!r_.getPageEl().isCustomAna()) {
            mainFrame.getStatusAnalyzeArea().append("KO");
            return;
        }
        AbstractProgramInfos frames_ = mainFrame.getCommonFrame().getFrames();
        LgNamesGui stds_ = (LgNamesGui) r_.getForwards().getGenerator();
        ExecutingOptions exec_ = stds_.getExecContent().getExecutingOptions();
        Options opt_ = r_.getForwards().getOptions();
        FileInfos file_ =stds_.getExecContent().getInfos();
        opt_.setReadOnly(true);
        ReportedMessages rep_ = r_.getReportedMessages();
        CustContextFactory.reportErrors(opt_, exec_, rep_, file_);
        ContextEl c_ = r_.getContext();
        if (c_ instanceof RunnableContextEl) {
            mainFrame.getResultContext().update(stds_.getExecContent().getCustAliases(), stds_.getContent(),(RunnableContextEl)c_,frames_);
        }
        mainFrame.getResultContext().setReportedMessages(rep_);
        mainFrame.getStatusAnalyzeArea().append("-----");
    }

    public static ResultContext nextValidate(ResultContext _base, ManageOptions _manage) {
        LgNamesGui lg_ = (LgNamesGui) _base.getForwards().getGenerator();
        ExecutingOptions exec_ = lg_.getExecContent().getExecutingOptions();
        FileInfos file_ = lg_.getExecContent().getInfos();
        ManageOptions m_ = new ManageOptions(exec_.getLgs(), WindowCdmEditor.linesConf(_manage), exec_.getListGenerator(), exec_.getLightProgramInfos().getThreadFactory());
        m_.getEx().setLightProgramInfos(exec_.getLightProgramInfos());
        m_.getEx().setListGenerator(exec_.getListGenerator());
        return RunningTest.nextValidate(_base, lg_, m_.getEx(), file_);
    }

}
