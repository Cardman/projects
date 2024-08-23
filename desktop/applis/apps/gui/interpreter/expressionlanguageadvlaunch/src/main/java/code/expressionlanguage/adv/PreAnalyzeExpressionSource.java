package code.expressionlanguage.adv;

import code.expressionlanguage.options.ResultContext;
import code.expressionlanguage.utilcompo.AbsResultContextNext;
import code.expressionlanguage.utilcompo.CustAliases;
import code.expressionlanguage.utilimpl.ManageOptions;
import code.gui.GuiConstants;

public final class PreAnalyzeExpressionSource implements Runnable {
    private final WindowCdmEditor mainFrame;
    private final int dbg;

    public PreAnalyzeExpressionSource(WindowCdmEditor _e, int _d) {
        this.mainFrame = _e;
        this.dbg = _d;
    }

    @Override
    public void run() {
        if (dbg == 0) {
            mainFrame.getStatusAnalyzeArea().setText(AbsEditorTabList.EMPTY_STRING);
        }
        mainFrame.getAnalyzeState().setLineBorder(GuiConstants.BLACK);
        ResultContext res_ = baseValidate(mainFrame);
        if (!res_.getPageEl().notAllEmptyErrors()) {
            if (dbg == 1) {
                mainFrame.setBaseResultDbg(res_);
            } else if (dbg == 2) {
                mainFrame.setBaseResultDbgInit(res_);
            } else {
                mainFrame.getAnalyzeMenu().setEnabled(true);
                mainFrame.setBaseResult(res_);
            }
            mainFrame.getAnalyzeState().setLineBorder(GuiConstants.GREEN);
        } else {
            mainFrame.getAnalyzeState().setLineBorder(GuiConstants.RED);
        }
        appendState(CustAliases.getDateTimeText(mainFrame.getFrames().getThreadFactory()));
        if (dbg != 0) {
            return;
        }
        mainFrame.getEvents().selectIndex(1);
        mainFrame.getStatusAnalyzeArea().append(CustAliases.getDateTimeText(mainFrame.getFrames().getThreadFactory()));
    }
    private void appendState(String _str) {
        if (dbg != 0) {
            return;
        }
        mainFrame.getAnalyzeState().append(_str);
    }

    public static ResultContext baseValidate(WindowCdmEditor _window) {
        ManageOptions man_ = _window.manage(_window.getSoftParams().getLines());
        AbsResultContextNext n_ = _window.getResultContextNext();
        return n_.init(man_.getOptions());
    }
}
