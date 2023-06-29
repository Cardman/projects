package code.expressionlanguage.adv;

import code.expressionlanguage.options.ResultContext;
import code.expressionlanguage.utilcompo.AbsResultContextNext;
import code.expressionlanguage.utilcompo.CustAliases;
import code.expressionlanguage.utilimpl.ManageOptions;

public final class PreAnalyzeExpressionSource implements Runnable {
    private final WindowCdmEditor mainFrame;
    private final boolean dbg;

    public PreAnalyzeExpressionSource(WindowCdmEditor _e, boolean _d) {
        this.mainFrame = _e;
        this.dbg = _d;
    }

    @Override
    public void run() {
        if (!dbg) {
            mainFrame.getStatusAnalyzeArea().setText("");
        }
        ResultContext res_ = baseValidate(mainFrame, dbg);
        if (!res_.getPageEl().notAllEmptyErrors()) {
            if (dbg) {
                mainFrame.setBaseResultDbg(res_);
            } else {
                mainFrame.getAnalyzeMenu().setEnabled(true);
                mainFrame.setBaseResult(res_);
            }
            appendState(CustAliases.getDateTimeText(mainFrame.getCommonFrame().getFrames().getThreadFactory())+":OK");
        } else {
            appendState(CustAliases.getDateTimeText(mainFrame.getCommonFrame().getFrames().getThreadFactory())+":KO");
        }
        if (dbg) {
            return;
        }
        mainFrame.getEvents().selectIndex(1);
        mainFrame.getStatusAnalyzeArea().append(CustAliases.getDateTimeText(mainFrame.getCommonFrame().getFrames().getThreadFactory()));
    }
    private void appendState(String _str) {
        if (dbg) {
            return;
        }
        mainFrame.getAnalyzeState().append(_str);
    }

    public static ResultContext baseValidate(WindowCdmEditor _window, boolean _light) {
        ManageOptions man_ = _window.manage(_window.getSoftParams().getLines());
        AbsResultContextNext n_ = _window.getResultContextNext();
        return n_.init(man_.getOptions(),_light);
    }
}
