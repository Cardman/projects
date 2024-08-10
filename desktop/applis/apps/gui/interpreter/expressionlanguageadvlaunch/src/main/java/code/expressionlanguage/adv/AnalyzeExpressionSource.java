package code.expressionlanguage.adv;

import code.expressionlanguage.options.ResultContext;
import code.expressionlanguage.utilcompo.CustAliases;
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
        mainFrame.getStatusAnalyzeArea().setText(AbsEditorTabList.EMPTY_STRING);
        AbstractProgramInfos frames_ = mainFrame.getCommonFrame().getFrames();
        StringMap<String> added_ = addedExp(mainFrame);
        ResultContext resUser_ = RunningTest.nextValidateQuick(mainFrame.getResultContextNext(),mainFrame.getBaseResult(), added_);
        if (resUser_ == null || !resUser_.getPageEl().isCustomAna()) {
            mainFrame.getStatusAnalyzeArea().append("KO"+AbsEditorTabList.LINE_RETURN);
            mainFrame.getStatusAnalyzeArea().append(CustAliases.getDateTimeText(frames_.getThreadFactory()));
            mainFrame.getAnalyzeMenu().setEnabled(true);
            return;
        }
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
            added_.addEntry(t.getRelPath(),t.centerText());
        }
        return added_;
    }

}
