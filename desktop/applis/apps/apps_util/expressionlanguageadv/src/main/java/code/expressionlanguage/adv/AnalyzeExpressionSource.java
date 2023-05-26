package code.expressionlanguage.adv;

import code.expressionlanguage.guicompos.LgNamesGui;
import code.expressionlanguage.options.ResultContext;
import code.expressionlanguage.utilcompo.CustAliases;
import code.expressionlanguage.utilcompo.FileInfos;
import code.expressionlanguage.utilimpl.CustContextFactory;
import code.expressionlanguage.utilimpl.RunningTest;
import code.gui.initialize.AbstractProgramInfos;
import code.util.StringMap;

public final class AnalyzeExpressionSource implements Runnable {
    private final WindowCdmEditor mainFrame;

    public AnalyzeExpressionSource(WindowCdmEditor _e) {
        this.mainFrame = _e;
    }

    @Override
    public void run() {
        mainFrame.getAnalyzeMenu().setEnabled(false);
        mainFrame.getStatusAnalyzeArea().setText("");
        ResultContext base_ = mainFrame.getBaseResult();
        LgNamesGui lg_ = (LgNamesGui) base_.getForwards().getGenerator();
        FileInfos fileInfos_ = lg_.getExecContent().getInfos();
        AbstractProgramInfos frames_ = mainFrame.getCommonFrame().getFrames();
        StringMap<String> added_ = addedExp(mainFrame);
        ResultContext resUser_ = RunningTest.nextValidateQuick(mainFrame.getBaseResult(), lg_, lg_.getExecContent().getExecutingOptions(), fileInfos_, added_);
        if (!resUser_.getPageEl().isCustomAna()) {
            mainFrame.getStatusAnalyzeArea().append("KO\n");
            mainFrame.getStatusAnalyzeArea().append(CustAliases.getDateTimeText(frames_.getThreadFactory()));
            mainFrame.getAnalyzeMenu().setEnabled(true);
            return;
        }
        CustContextFactory.reportErrors(base_.getForwards().getOptions(), lg_.getExecContent().getExecutingOptions(), resUser_.getReportedMessages(), fileInfos_);
        mainFrame.setUserResultGene(resUser_);
        if (resUser_.getPageEl().notAllEmptyErrors()) {
            mainFrame.getStatusAnalyzeArea().append(CustAliases.getDateTimeText(frames_.getThreadFactory()));
            mainFrame.getAnalyzeMenu().setEnabled(true);
            return;
        }
        mainFrame.getStatusAnalyzeArea().append(CustAliases.getDateTimeText(frames_.getThreadFactory()));
        mainFrame.setUserResult(resUser_);
        mainFrame.getAnalyzeMenu().setEnabled(true);
    }

    static StringMap<String> addedExp(WindowCdmEditor _w) {
        StringMap<String> added_ = new StringMap<String>();
        for (WindowExpressionEditor s: _w.getExpressionEditors()) {
            added_.addAllEntries(added(s));
        }
        return added_;
    }
    static StringMap<String> added(WindowWithTreeImpl _w) {
        StringMap<String> added_ = new StringMap<String>();
        for (TabEditor t: _w.getTabs()) {
            added_.addEntry(t.getRelPath(),t.getCenter().getText());
        }
        return added_;
    }

}
