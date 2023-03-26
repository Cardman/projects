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
import code.expressionlanguage.utilimpl.ManageOptions;
import code.expressionlanguage.utilimpl.RunningTest;
import code.gui.initialize.AbstractProgramInfos;
import code.util.StringList;

public final class AnalyzeExpressionSource implements Runnable {
    private final WindowCdmEditor mainFrame;

    public AnalyzeExpressionSource(WindowCdmEditor _e) {
        this.mainFrame = _e;
    }

    @Override
    public void run() {
        mainFrame.getStatusAnalyzeArea().setText("");
        ResultContext base_ = mainFrame.getBaseResult();
        LgNamesGui lg_ = (LgNamesGui) base_.getForwards().getGenerator();
        FileInfos fileInfos_ = lg_.getExecContent().getInfos();
        ManageOptions options_ = mainFrame.manage(mainFrame.getSoftParams().getLines());
        ExecutingOptions ex_ = options_.getEx();
        ex_.setLightProgramInfos(lg_.getExecContent().getExecutingOptions().getLightProgramInfos());
        ex_.setListGenerator(lg_.getExecContent().getExecutingOptions().getListGenerator());
        LgNamesGui lgCopy_ = new LgNamesGui(fileInfos_,mainFrame.getFactory().getInterceptor());
        lgCopy_.getContent().getStandards().addAllEntries(lg_.getContent().getStandards());
        lgCopy_.getContent().getPrimTypes().getPrimitiveTypes().addAllEntries(lg_.getContent().getPrimTypes().getPrimitiveTypes());
        lgCopy_.getExecContent().updateTranslations(ex_.getLightProgramInfos().getTranslations(),ex_.getLightProgramInfos().getLanguage(),ex_.getLg());
        CustContextFactory.aliases(ex_,lgCopy_.getContent(), lgCopy_.getExecContent().getCustAliases(), lgCopy_.getGuiAliases());
        CustContextFactory.parts(ex_,lgCopy_,new StringList());
        mainFrame.setAnalyzeEx(ex_);
        mainFrame.getAnalyzeMenuCancel().setEnabled(true);
        AbstractProgramInfos frames_ = mainFrame.getCommonFrame().getFrames();
        ResultContext r_ = RunningTest.nextValidate(mainFrame.getBaseResult(),lgCopy_,ex_,fileInfos_);
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
        end(r_);
        mainFrame.getResultContext().setReportedMessages(rep_);
    }

    void end(ResultContext _r) {
        LgNamesGui stds_ = (LgNamesGui) _r.getForwards().getGenerator();
        AbstractProgramInfos frames_ = mainFrame.getCommonFrame().getFrames();
        ContextEl c_ = _r.getContext();
        if (!mainFrame.getAnalyzeEx().getInterrupt().get()&&c_ instanceof RunnableContextEl) {
            mainFrame.getStatusAnalyzeArea().append(mainFrame.getResultContext().update(stds_.getExecContent().getCustAliases(), stds_.getContent(),(RunnableContextEl)c_,frames_));
        } else {
            mainFrame.getStatusAnalyzeArea().append(CustAliases.getDateTimeText(frames_.getThreadFactory()));
        }
    }

}
