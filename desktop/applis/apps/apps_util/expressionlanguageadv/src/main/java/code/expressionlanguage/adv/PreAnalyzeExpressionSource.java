package code.expressionlanguage.adv;

import code.expressionlanguage.options.ResultContext;
import code.expressionlanguage.utilcompo.AbsResultContextNext;
import code.expressionlanguage.utilcompo.CustAliases;
import code.expressionlanguage.utilimpl.ManageOptions;

public final class PreAnalyzeExpressionSource implements Runnable {
    private final WindowCdmEditor mainFrame;

    public PreAnalyzeExpressionSource(WindowCdmEditor _e) {
        this.mainFrame = _e;
    }

    @Override
    public void run() {
        mainFrame.getStatusAnalyzeArea().setText("");
        ResultContext res_ = baseValidate(mainFrame);
        if (!res_.getPageEl().notAllEmptyErrors()) {
            mainFrame.getAnalyzeMenu().setEnabled(true);
            mainFrame.setBaseResult(res_);
            mainFrame.getAnalyzeState().append(CustAliases.getDateTimeText(mainFrame.getCommonFrame().getFrames().getThreadFactory())+":OK");
        } else {
            mainFrame.getAnalyzeState().append(CustAliases.getDateTimeText(mainFrame.getCommonFrame().getFrames().getThreadFactory())+":KO");
        }
        mainFrame.getEvents().selectIndex(1);
        mainFrame.getStatusAnalyzeArea().append(CustAliases.getDateTimeText(mainFrame.getCommonFrame().getFrames().getThreadFactory()));
    }

    public static ResultContext baseValidate(WindowCdmEditor _window) {
        ManageOptions man_ = _window.manage(_window.getSoftParams().getLines());
        AbsResultContextNext n_ = _window.getResultContextNext();
        return n_.init(man_.getOptions());
    }
}
