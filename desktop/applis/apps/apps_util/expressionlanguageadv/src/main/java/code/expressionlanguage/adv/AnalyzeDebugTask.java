package code.expressionlanguage.adv;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.exec.Classes;
import code.expressionlanguage.fwd.Forwards;
import code.expressionlanguage.fwd.blocks.ForwardInfos;
import code.expressionlanguage.options.ResultContext;
import code.expressionlanguage.utilcompo.AbsAdvContextGenerator;
import code.expressionlanguage.utilcompo.AbsResultContextNext;
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
        if (base == null||base.getPageEl().notAllEmptyErrors()) {
            return;
        }
        base.getForwards().getOptions().setDebugging(true);
        AbsResultContextNext gen_ = gui.getResultContextNext();
        ResultContext ana_ = gen_.next(base, src);
        if (ana_ == null) {
            return;
        }
        Forwards f_ = ana_.getForwards();
        AnalyzedPageEl page_ = ana_.getPageEl();
        ForwardInfos.generalForward(page_,f_);
        AbsAdvContextGenerator gn_ = gen_.generate();
        ContextEl ctx_ = gn_.gene(f_);
        Classes.forwardAndClear(ctx_);
        ana_.setContext(ctx_);
        gui.update(ana_);
        gui.setStopDbg(gn_.getStop());
    }
}
