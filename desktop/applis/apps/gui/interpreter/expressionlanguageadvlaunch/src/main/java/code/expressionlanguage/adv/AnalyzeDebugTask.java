package code.expressionlanguage.adv;

import code.expressionlanguage.analyze.blocks.FileBlock;
import code.expressionlanguage.exec.dbg.DbgStackStopper;
import code.expressionlanguage.options.ResultContext;
import code.expressionlanguage.utilcompo.AbsResultContextNext;
import code.gui.AbsTextArea;
import code.util.EntryCust;
import code.util.StringMap;

public final class AnalyzeDebugTask implements Runnable {
    private final ResultContext base;
    private final AbsDebuggerGui gui;
    private final StringMap<String> src;

    public AnalyzeDebugTask(ResultContext _b,AbsDebuggerGui _g, StringMap<String> _s) {
        this.base = _b;
        this.gui = _g;
        this.src = _s;
    }

    @Override
    public void run() {
        AbsResultContextNext gen_ = gui.getResultContextNext();
        StringMap<String> all_ = gen_.files(base, src);
        if (base != null) {
            for (EntryCust<String, FileBlock> m: base.getPageEl().getPreviousFilesBodies().entryList()) {
                all_.addEntry(m.getKey(),m.getValue().getContent());
            }
        }
        AbsTextArea ta_ = gui.getFrames().getCompoFactory().newTextArea();
        ta_.setEditable(false);
        ResultContext ana_ = gen_.next(base, src, new DbgStackStopper(new AdvLogDbg(ta_)));
        if (ana_ == null) {
            gui.getAnalyzeMenu().setEnabled(true);
            return;
        }
        gen_.generate(ana_);
//        Forwards f_ = ana_.getForwards();
//        AnalyzedPageEl page_ = ana_.getPageEl();
//        ForwardInfos.generalForward(page_,f_);
//        ContextEl ctx_ = gn_.gene(f_);
//        Classes.forwardAndClear(ctx_);
//        ana_.setContext(ctx_);
        gui.setViewable(all_);
        gui.getStatusDbgAreaScroll().setViewportView(ta_);
        gui.update(ana_, src);
        gui.getAnalyzeMenu().setEnabled(true);
    }
}
