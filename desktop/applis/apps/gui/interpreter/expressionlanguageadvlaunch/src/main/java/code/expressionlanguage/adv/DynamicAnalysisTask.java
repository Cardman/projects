package code.expressionlanguage.adv;

import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.WatchResults;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.options.ResultContext;
import code.expressionlanguage.utilcompo.AbsResultContextNext;
import code.gui.*;
import code.threads.AbstractAtomicBoolean;
import code.util.StringMap;
import code.util.core.StringUtil;

public final class DynamicAnalysisTask implements Runnable {
    private final AbsDebuggerGui window;
    private final String dynamic;
    private final StackCall stackCall;
    private final AbstractPageEl call;
    private final ResultContext resultContext;
    private final AbsResultContextNext resultContextNext;
    private final AbstractAtomicBoolean interrupted;
    private final AbsTreeGui tree;
    private final DbgRootStruct root;
    private final AbsTextArea textArea;
    private final AbsButton stButton;
    private final AbsButton refreshButton;
    private final AbsScrollPane scroll;

    public DynamicAnalysisTask(AbsDebuggerGui _w, AbstractPageEl _c, ResultContext _rc, AbsTreeGui _tr, DbgRootStruct _root) {
        StringMap<String> mes_ = MessagesIde.valSessionForm(_w.getFrames().currentLg());
        this.window = _w;
        this.dynamic = StringUtil.nullToEmpty(_w.getDynamicEval().getText());
        this.stackCall = _w.getStackCall();
        this.call = _c;
        this.resultContext = _rc;
        this.resultContextNext = _w.getResultContextNext();
        this.interrupted = _w.getThreadFactory().newAtomicBoolean();
        this.tree = _tr;
        this.root = _root;
        AbsPanel buttons_ = window.getCompoFactory().newPageBox();
        stButton = window.getCompoFactory().newPlainButton(StringUtil.nullToEmpty(mes_.getVal(MessagesIde.IDE_POINTS_SESSION_FORM_CANCEL_EVAL))+":" + dynamic);
        buttons_.add(stButton);
        stButton.addActionListener(new CancelEvalDynEvent(_w, stButton,interrupted));
        textArea = window.getCompoFactory().newTextArea();
        textArea.setEditable(false);
        textArea.setEditable(false);
        refreshButton = window.getCompoFactory().newPlainButton(StringUtil.nullToEmpty(mes_.getVal(MessagesIde.IDE_POINTS_SESSION_FORM_REFRESH_RENDER)));
        refreshButton.addActionListener(new RefreshRenderDynEvent(_w,_tr,_root));
        buttons_.add(refreshButton);
        scroll = window.getCompoFactory().newAbsScrollPane();
        AbsSplitPane elt_ = window.getCompoFactory().newVerticalSplitPane(window.getCompoFactory().newVerticalSplitPane(buttons_,window.getCompoFactory().newAbsScrollPane(textArea)), scroll);
        window.getWatches().addIntTab(StringUtil.nullToEmpty(mes_.getVal(MessagesIde.IDE_POINTS_SESSION_FORM_VARS)),elt_, dynamic.trim());
        window.getCommonFrame().pack();
    }

    public AbsButton getStButton() {
        return stButton;
    }

    public AbsScrollPane getScroll() {
        return scroll;
    }

    public AbsButton getRefreshButton() {
        return refreshButton;
    }

    public AbsTreeGui getTree() {
        return tree;
    }

    public DbgRootStruct getRoot() {
        return root;
    }

    public AbsDebuggerGui getWindow() {
        return window;
    }

    @Override
    public void run() {
        WatchResults wr_ = AbsDebuggerGui.dynamicAnalyze(dynamic, stackCall, call, resultContext, resultContextNext.generateAdv(interrupted),new AdvLogDbg(textArea));
        window.getCompoFactory().invokeNow(new DynamicAnalysisLater(this, wr_));
    }
}
